package mx.gob.infonavit.emailmarketing.web.config;

import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import mx.gob.infonavit.emailmarketing.web.rest.TemplateController;
import mx.gob.infonavit.emailmarketing.web.rest.CampaignController;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Configuración de la aplicación JAX-RS.
 * 
 * Define la ruta base de la API REST y registra los recursos necesarios
 * incluyendo los endpoints de Swagger para documentación.
 * 
 * @author Ivan Garcia igarciam@desarrollo-ultrasis.com.mx
 * @version 1.0.0
 * @since 2025-10-20
 */
@ApplicationPath("/api/v1")
public class JaxRsApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        
        // Recursos de Swagger
        resources.add(ApiListingResource.class);
        resources.add(SwaggerSerializers.class);
        
        // REST Controllers
        resources.add(TemplateController.class);
        resources.add(CampaignController.class);
        
        return resources;
    }
}

