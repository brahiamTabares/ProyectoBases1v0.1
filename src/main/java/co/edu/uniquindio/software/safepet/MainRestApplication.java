package co.edu.uniquindio.software.safepet;

import javax.annotation.security.DeclareRoles;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


@DeclareRoles({"Usuario"})
@ApplicationPath("/data")
@ApplicationScoped
public class MainRestApplication extends Application {
}
