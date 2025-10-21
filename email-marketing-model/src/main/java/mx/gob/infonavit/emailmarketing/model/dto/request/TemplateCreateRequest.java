package mx.gob.infonavit.emailmarketing.model.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * DTO para la creación de una nueva plantilla de correo electrónico.
 * 
 * @author Ivan Garcia igarciam@desarrollo-ultrasis.com.mx
 * @version 1.0.0
 * @since 2025-10-20
 */
@ApiModel(description = "Datos para crear una nueva plantilla de correo electrónico HTML")
public class TemplateCreateRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(
        value = "Nombre de la plantilla", 
        required = true, 
        example = "Plantilla Bienvenida",
        position = 1
    )
    @NotBlank(message = "El nombre de la plantilla es obligatorio")
    @Size(min = 3, max = 255, message = "El nombre debe tener entre 3 y 255 caracteres")
    private String name;

    @ApiModelProperty(
        value = "Descripción de la plantilla", 
        example = "Plantilla para dar bienvenida a nuevos usuarios",
        position = 2
    )
    @Size(max = 500, message = "La descripción no puede exceder 500 caracteres")
    private String description;

    @ApiModelProperty(
        value = "Contenido HTML de la plantilla", 
        required = true, 
        example = "<html><body><h1>Bienvenido</h1></body></html>",
        position = 3
    )
    @NotBlank(message = "El contenido HTML es obligatorio")
    @Size(min = 10, message = "El contenido HTML debe tener al menos 10 caracteres")
    private String htmlContent;

    public TemplateCreateRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TemplateCreateRequest [");
        sb.append("name='").append(name);
        sb.append(", description='").append(description);
        sb.append(", htmlContent='").append(htmlContent);
        sb.append("]");
        return sb.toString();
    }
}

