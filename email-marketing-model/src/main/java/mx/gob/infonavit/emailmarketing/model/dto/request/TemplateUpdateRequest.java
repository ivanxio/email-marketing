package mx.gob.infonavit.emailmarketing.model.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * DTO para la actualización de una plantilla existente.
 * 
 * @author Ivan Garcia igarciam@desarrollo-ultrasis.com.mx
 * @version 1.0.0
 * @since 2025-10-20
 */
@ApiModel(description = "Datos para actualizar una plantilla existente (todos los campos son opcionales)")
public class TemplateUpdateRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "Nombre de la plantilla", example = "Plantilla Bienvenida V2")
    @Size(min = 3, max = 255, message = "El nombre debe tener entre 3 y 255 caracteres")
    private String name;

    @ApiModelProperty(value = "Descripción de la plantilla", example = "Versión mejorada de la plantilla")
    @Size(max = 500, message = "La descripción no puede exceder 500 caracteres")
    private String description;

    @ApiModelProperty(value = "Contenido HTML actualizado", example = "<html><body><h1>Nuevo contenido</h1></body></html>")
    @Size(min = 10, message = "El contenido HTML debe tener al menos 10 caracteres")
    private String htmlContent;

    @ApiModelProperty(value = "Estado activo de la plantilla", example = "true")
    private Boolean isActive;

    public TemplateUpdateRequest() {
    }

    public boolean hasUpdates() {
        return name != null || description != null || htmlContent != null || isActive != null;
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

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TemplateUpdateRequest [");
        sb.append("name='").append(name);
        sb.append(", description='").append(description);
        sb.append(", htmlContent='").append(htmlContent);
        sb.append(", isActive='").append(isActive);
        sb.append("]");
        return sb.toString();
    }
}

