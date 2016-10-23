package ch.chrisport.goeuro.config;

import ch.chrisport.goeuro.controller.RouteInformationController;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(RouteInformationController.class);
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
    }
}
