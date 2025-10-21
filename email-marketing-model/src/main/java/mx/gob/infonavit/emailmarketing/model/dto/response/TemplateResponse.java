package mx.gob.infonavit.emailmarketing.model.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO de respuesta para una plantilla de correo electrónico.
 * 
 * Encapsula los datos de una plantilla que se envían al cliente.
 * 
 * @author Ivan Garcia igarciam@desarrollo-ultrasis.com.mx
 * @version 1.0.0
 * @since 2025-10-20
 */
@ApiModel(description = "Plantilla de correo electrónico HTML")
public class TemplateResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID único de la plantilla", example = "1")
    private Long id;

    @ApiModelProperty(value = "Nombre de la plantilla", example = "Plantilla Bienvenida")
    private String name;

    @ApiModelProperty(value = "Descripción de la plantilla", example = "Plantilla para nuevos usuarios")
    private String description;

    @ApiModelProperty(value = "Contenido HTML de la plantilla", example = "<html><body><h1>Bienvenido</h1></body></html>")
    private String htmlContent;

    @ApiModelProperty(value = "ID del usuario que creó la plantilla", example = "1")
    private Long createdBy;

    @ApiModelProperty(value = "Username del creador", example = "admin")
    private String createdByUsername;

    @ApiModelProperty(value = "Indica si la plantilla está activa", example = "true")
    private Boolean isActive;

    @ApiModelProperty(value = "Fecha de creación", example = "2025-10-20T16:00:00")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "Fecha de última actualización", example = "2025-10-20T16:30:00")
    private LocalDateTime updatedAt;

    public TemplateResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedByUsername() {
        return createdByUsername;
    }

    public void setCreatedByUsername(String createdByUsername) {
        this.createdByUsername = createdByUsername;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TemplateResponse [");
        sb.append("id='").append(id).append(" ");
        sb.append(", name='").append(name);
        sb.append(", description='").append(description);
        sb.append(", htmlContent='").append(htmlContent);
        sb.append(", createdBy='").append(createdBy);
        sb.append(", createdByUsername='").append(createdByUsername);
        sb.append(", isActive='").append(isActive);
        sb.append(", createdAt='").append(createdAt);
        sb.append(", updatedAt='").append(updatedAt);
        sb.append("]");
        return sb.toString();
    }
}

