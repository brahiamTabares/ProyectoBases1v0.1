package co.edu.uniquindio.software.safepet.logica.security;


import co.edu.uniquindio.software.safepet.logica.UsuarioBO;
import co.edu.uniquindio.software.safepet.persistencia.entidades.Usuario;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import java.util.HashSet;

import static javax.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;

@ApplicationScoped
public class JPAIdentityStore implements IdentityStore {
    @Inject
    private UsuarioBO usuarioBO;

    @Override
    public CredentialValidationResult validate(Credential credential) {
        UsernamePasswordCredential userCredential = (UsernamePasswordCredential) credential;

        Usuario user = findUser(userCredential.getCaller());
        if( user != null && user.getContrasenia().equals( userCredential.getPasswordAsString() ) ){
            HashSet<String> roles = new HashSet<>();
            roles.add( user.getClass().getSimpleName() );
            return new CredentialValidationResult(user.getId(),roles);
        }
        return INVALID_RESULT;
    }

    private Usuario findUser(String id) {
        return usuarioBO.find(id);
    }
}
