package mx.gob.infonavit.emailmarketing.web.config;

import io.swagger.jaxrs.config.BeanConfig;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * Configuración de Swagger para documentación de API REST.
 * 
 * Configura Swagger 2.0 para generar documentación automática de la API.
 * La documentación estará disponible en formato JSON.
 * 
 * @author Ivan Garcia igarciam@desarrollo-ultrasis.com.mx
 * @version 1.0.0
 * @since 2025-10-20
 */
@WebServlet(name = "SwaggerConfig", urlPatterns = {"/swagger-init"}, loadOnStartup = 1)
public class SwaggerConfig extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        BeanConfig beanConfig = new BeanConfig();
        
        // Información de la API
        beanConfig.setTitle("Email Marketing API");
        beanConfig.setDescription("Sistema de Gestión de Campañas de Email Marketing - INFONAVIT");
        beanConfig.setVersion("1.0.0");
        beanConfig.setContact("Ivan Garcia - igarciam@desarrollo-ultrasis.com.mx");
        beanConfig.setLicense("Proprietary");
        beanConfig.setLicenseUrl("http://www.infonavit.gob.mx");
        
        // Configuración de paths
        beanConfig.setSchemes(new String[]{"http", "https"});
        beanConfig.setHost("localhost:9080");
        beanConfig.setBasePath("/email-marketing/api/v1");
        
        // Paquetes a escanear
        beanConfig.setResourcePackage("mx.gob.infonavit.emailmarketing.web.rest");
        
        // Escanear automáticamente
        beanConfig.setScan(true);
        beanConfig.setPrettyPrint(true);
    }
}

