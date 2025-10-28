package mx.gob.infonavit.emailmarketing.model.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

/**
 * DTO para la actualización de una campaña de correo electrónico existente.
 * 
 * @author Ivan Garcia Martinez igarciam@desarrollo-ultrasist.com.mx
 * @version 1.0.0
 * @since 2025-10-27
 */
@ApiModel(description = "Datos para actualizar una campaña de correo electrónico existente")
public class CampaignUpdateRequest {

    @ApiModelProperty(value = "ID de la campaña", example = "1", required = true)
    @NotNull(message = "El ID de la campaña es obligatorio")
    @Min(value = 1, message = "El ID de la campaña debe ser mayor a 0")
    private Long id;

    @ApiModelProperty(value = "Nombre de la campaña", example = "Campaña de Bienvenida Q4 2024 - Actualizada")
    @NotBlank(message = "El nombre de la campaña es obligatorio")
    @Size(max = 255, message = "El nombre no puede exceder 255 caracteres")
    private String name;

    @ApiModelProperty(value = "Descripción de la campaña", example = "Campaña de bienvenida para nuevos afiliados - Versión actualizada")
    @NotBlank(message = "La descripción es obligatoria")
    @Size(max = 1000, message = "La descripción no puede exceder 1000 caracteres")
    private String description;

    @ApiModelProperty(value = "Asunto del correo electrónico", example = "¡Bienvenido a INFONAVIT! - Actualizado")
    @NotBlank(message = "El asunto es obligatorio")
    @Size(max = 500, message = "El asunto no puede exceder 500 caracteres")
    private String subject;

    @ApiModelProperty(value = "Dirección de correo electrónico del remitente", example = "noreply@infonavit.org.mx")
    @NotBlank(message = "El campo 'from' es obligatorio")
    @Size(max = 255, message = "El campo 'from' no puede exceder 255 caracteres")
    private String from;

    @ApiModelProperty(value = "Fecha programada para la ejecución de la campaña", example = "2024-12-15T14:00:00")
    private LocalDateTime scheduledAt;

    @ApiModelProperty(value = "Usuario que actualizó la campaña", required = false, example = "user")
    private String updatedBy;

    @ApiModelProperty(value = "ID de la plantilla a utilizar", example = "1")
    private Long templateId;

    @ApiModelProperty(value = "ID del archivo adjunto", example = "1")
    private Long attachmentId;

    @ApiModelProperty(value = "ID del archivo de contactos", example = "1")
    private Long contactFileId;

    public CampaignUpdateRequest() {
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public LocalDateTime getScheduledAt() {
        return scheduledAt;
    }

    public void setScheduledAt(LocalDateTime scheduledAt) {
        this.scheduledAt = scheduledAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public Long getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(Long attachmentId) {
        this.attachmentId = attachmentId;
    }

    public Long getContactFileId() {
        return contactFileId;
    }

    public void setContactFileId(Long contactFileId) {
        this.contactFileId = contactFileId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("CampaignUpdateRequest{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name);
        sb.append(", description='").append(description);
        sb.append(", subject='").append(subject);
        sb.append(", from='").append(from);        
        sb.append(", scheduledAt=").append(scheduledAt);
        sb.append(", updatedBy='").append(updatedBy);
        sb.append(", templateId=").append(templateId);
        sb.append(", attachmentId=").append(attachmentId);
        sb.append(", contactFileId=").append(contactFileId);
        sb.append('}');
        return sb.toString();
    }
}