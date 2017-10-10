package smaug.cloud.config.jerseryConfig;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

/**
 * Created by Allen on 17/10/10.
 */
public class AnnotationJerseyConfig extends ResourceConfig {
    public AnnotationJerseyConfig() {
        register(RequestContextFilter.class);
        packages("smaug.cloud.api.interfaces");
    }
}
