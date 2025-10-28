package mx.gob.infonavit.emailmarketing.model.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * DTO para la creación de una nueva campaña de correo electrónico.
 * 
 * @author Ivan Garcia Martinez igarciam@desarrollo-ultrasist.com.mx
 * @version 1.0.0
 * @since 2025-10-27
 */
@ApiModel(description = "Datos requeridos para crear una nueva campaña de correo electrónico")
public class CampaignCreateRequest {

    @ApiModelProperty(value = "Nombre de la campaña", required = true, example = "Campaña de Bienvenida Q4 2024")
    @NotBlank(message = "El nombre de la campaña es obligatorio")
    @Size(max = 255, message = "El nombre no puede exceder 255 caracteres")
    private String name;

    @ApiModelProperty(value = "Descripción de la campaña", required = true, example = "Campaña de bienvenida para nuevos afiliados")
    @NotBlank(message = "La descripción es obligatoria")
    @Size(max = 1000, message = "La descripción no puede exceder 1000 caracteres")
    private String description;

    @ApiModelProperty(value = "Asunto del correo electrónico", required = true, example = "¡Bienvenido a INFONAVIT!")
    @NotBlank(message = "El asunto es obligatorio")
    @Size(max = 500, message = "El asunto no puede exceder 500 caracteres")
    private String subject;

    @ApiModelProperty(value = "Dirección de correo electrónico del remitente", required = true, example = "noreply@infonavit.org.mx")
    @NotBlank(message = "El campo 'from' es obligatorio")
    @Size(max = 255, message = "El campo 'from' no puede exceder 255 caracteres")
    private String from;

    @ApiModelProperty(value = "Fecha programada para la ejecución de la campaña", example = "2024-12-01T10:00:00")
    private LocalDateTime scheduledAt;

    @ApiModelProperty(value = "Usuario que creó la campaña", required = false, example = "user")
    private String createdBy;

    @ApiModelProperty(value = "ID de la plantilla a utilizar", required = false, example = "1")
    private Long templateId;

    @ApiModelProperty(value = "ID del archivo adjunto", required = false, example = "1")
    private Long attachmentId;

    @ApiModelProperty(value = "ID del archivo de contactos", required = true, example = "1")
    @NotNull(message = "El ID del archivo de contactos es obligatorio")
    private Long contactFileId;

    public CampaignCreateRequest() {
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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
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
        StringBuilder sb = new StringBuilder("CampaignCreateRequest{");
        sb.append("name='").append(name);
        sb.append(", description='").append(description);
        sb.append(", from='").append(from);
        sb.append(", subject='").append(subject);
        sb.append(", scheduledAt=").append(scheduledAt);
        sb.append(", createdBy='").append(createdBy);
        sb.append(", templateId=").append(templateId);
        sb.append(", attachmentId=").append(attachmentId);
        sb.append(", contactFileId=").append(contactFileId);
        sb.append('}');
        return sb.toString();
    }
}
