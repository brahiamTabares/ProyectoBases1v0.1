package co.edu.uniquindio.software.safepet.gui.beans.seguridad;

import co.edu.uniquindio.software.safepet.gui.beans.AbstracBean;
import co.edu.uniquindio.software.safepet.logica.security.LoginDTO;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

import static javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters.withParams;

@Named
@SessionScoped
public class SeguridadBean extends AbstracBean {
    private Logger LOG = Logger.getLogger(SeguridadBean.class.getName());
    private LoginDTO loginDTO;

    @Inject
    private SecurityContext securityContext;
    private boolean autenticado;

    @PostConstruct
    public void inicializar(){
        autenticado = false;
        loginDTO = new LoginDTO();
    }

    public LoginDTO getLoginDTO() {
        return loginDTO;
    }

    public void setLoginDTO(LoginDTO loginDTO) {
        this.loginDTO = loginDTO;
    }

    public String autenticar() {

        FacesContext context = FacesContext.getCurrentInstance();
        Credential credential = new UsernamePasswordCredential(loginDTO.getUsername(), new Password(loginDTO.getPassword()));

        AuthenticationStatus status = securityContext.authenticate(
                getRequest(context),
                getResponse(context),
                withParams()
                        .credential(credential)
                        .newAuthentication(true)
        );

        LOG.info("authentication result:" + status);

        if( status != AuthenticationStatus.SUCCESS ){
            showMessageError(null,"Usuario o clave incorrecta");
            return null;
        }
        autenticado = true;
        showMessageInfo(null,"Autenticación exitosa");
        return "/welcome/index.xhtml?faces-redirect=true";
    }

    private static HttpServletResponse getResponse(FacesContext context) {
        return (HttpServletResponse) context
                .getExternalContext()
                .getResponse();
    }

    private static HttpServletRequest getRequest(FacesContext context) {
        return (HttpServletRequest) context
                .getExternalContext()
                .getRequest();
    }

    public String logout() throws ServletException {
        getRequest(getFacesContext()).logout();
        getFacesContext().getExternalContext().invalidateSession();
        autenticado = false;
        return "/autenticacion/index.xhtml?faces-redirect=true";
    }

    public boolean isAutenticado() {
        return autenticado;
    }

    public void setAutenticado(boolean autenticado) {
        this.autenticado = autenticado;
    }
}
