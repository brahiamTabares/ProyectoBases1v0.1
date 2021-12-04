package co.edu.uniquindio.software.safepet.logica.security;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationException;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.authentication.mechanism.http.*;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStoreHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.HttpHeaders;
import java.util.HashSet;

import static java.util.Arrays.asList;

@AutoApplySession // For "Is user already logged-in?"
//@RememberMe(isRememberMeExpression = "httpMessageContext.authParameters.rememberMe", cookieMaxAgeSeconds = 60 * 60 * 24 * 14) // 14 days
//@LoginToContinue(loginPage = "/autenticacion/index.xhtml?continued=true", errorPage = "", useForwardToLogin = false)
@LoginToContinue(loginPage = "/autenticacion/index.xhtml", errorPage = "/autenticacion/error.xhtml", useForwardToLogin = false)
@ApplicationScoped
public class SeguridadAuthtenticationMechanism implements HttpAuthenticationMechanism {

    @Inject
    private IdentityStoreHandler identityStoreHandler;

    @Override
    public AuthenticationStatus validateRequest(HttpServletRequest request, HttpServletResponse response, HttpMessageContext httpMessageContext) throws AuthenticationException {
        if( httpMessageContext.isAuthenticationRequest() && httpMessageContext.getAuthParameters().isNewAuthentication() ){
            CredentialValidationResult result = identityStoreHandler.validate(
                    httpMessageContext.getAuthParameters().getCredential() );
            if( result == CredentialValidationResult.INVALID_RESULT ){
                return AuthenticationStatus.SEND_FAILURE;
            }

            return httpMessageContext.notifyContainerAboutLogin( result );
        }

        return httpMessageContext.doNothing();
    }
}
