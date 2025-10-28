package mx.gob.infonavit.emailmarketing.model.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * DTO de respuesta para información de campaña de correo electrónico.
 * 
 * @author Ivan Garcia Martinez igarciam@desarrollo-ultrasist.com.mx
 * @version 1.0.0
 * @since 2025-10-27
 */
@ApiModel(description = "Información de una campaña de correo electrónico")
public class CampaignResponse {

    @ApiModelProperty(value = "Identificador único de la campaña", example = "1")
    private Long id;

    @ApiModelProperty(value = "Nombre de la campaña", example = "Campaña de Bienvenida Q4 2024")
    private String name;

    @ApiModelProperty(value = "Descripción de la campaña", example = "Campaña de bienvenida para nuevos afiliados")
    private String description;

    @ApiModelProperty(value = "Asunto del correo electrónico", example = "¡Bienvenido a INFONAVIT!")
    private String subject;

    @ApiModelProperty(value = "Dirección de correo electrónico del remitente", example = "noreply@infonavit.org.mx")
    private String from;

    @ApiModelProperty(value = "Fecha programada para la ejecución", example = "2024-12-01T10:00:00")
    private LocalDateTime scheduledDate;

    @ApiModelProperty(value = "Fecha de creación", example = "2024-10-21T15:30:00")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "Usuario que creó la campaña", example = "admin@infonavit.org.mx")
    private String createdBy;

    @ApiModelProperty(value = "Fecha de última actualización", example = "2024-10-21T16:45:00")
    private LocalDateTime updatedAt;

    @ApiModelProperty(value = "Usuario que actualizó la campaña", example = "admin@infonavit.org.mx")
    private String updatedBy;

    @ApiModelProperty(value = "ID de la plantilla utilizada", example = "1")
    private Long templateId;

    @ApiModelProperty(value = "ID del archivo adjunto utilizado", example = "1")
    private Long attachmentId;

    @ApiModelProperty(value = "ID del archivo de contactos utilizado", example = "1")
    private Long contactFileId;

    @ApiModelProperty(value = "Estado actual de la campaña", example = "DRAFT")
    private String status;

    @ApiModelProperty(value = "Fecha de inicio de la campaña", example = "2024-12-01T10:00:00")
    private LocalDateTime startedAt;

    @ApiModelProperty(value = "Fecha de finalización de la campaña", example = "2024-12-01T12:30:00")
    private LocalDateTime completedAt;

    @ApiModelProperty(value = "Total de correos a enviar", example = "1000")
    private Integer total;

    @ApiModelProperty(value = "Correos enviados exitosamente", example = "950")
    private Integer sentCount;

    @ApiModelProperty(value = "Correos que fallaron al enviar", example = "50")
    private Integer failedCount;

    @ApiModelProperty(value = "Cantidad de correos abiertos", example = "850")
    private Integer openedCount;

    @ApiModelProperty(value = "Cantidad de correos con clics", example = "120")
    private Integer clickedCount;

    @ApiModelProperty(value = "Cantidad de correos inbound", example = "5")
    private Integer inboundCount;

    @ApiModelProperty(value = "Cantidad de correos rebotados", example = "25")
    private Integer bouncedCount;

    @ApiModelProperty(value = "Cantidad de desuscripciones", example = "8")
    private Integer unsubscribedCount;

    @ApiModelProperty(value = "Cantidad de correos marcados como spam", example = "3")
    private Integer spamCount;

    public CampaignResponse() {
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

    public LocalDateTime getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(LocalDateTime scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getSentCount() {
        return sentCount;
    }

    public void setSentCount(Integer sentCount) {
        this.sentCount = sentCount;
    }

    public Integer getFailedCount() {
        return failedCount;
    }

    public void setFailedCount(Integer failedCount) {
        this.failedCount = failedCount;
    }

    public Integer getOpenedCount() {
        return openedCount;
    }

    public void setOpenedCount(Integer openedCount) {
        this.openedCount = openedCount;
    }

    public Integer getClickedCount() {
        return clickedCount;
    }

    public void setClickedCount(Integer clickedCount) {
        this.clickedCount = clickedCount;
    }

    public Integer getInboundCount() {
        return inboundCount;
    }

    public void setInboundCount(Integer inboundCount) {
        this.inboundCount = inboundCount;
    }

    public Integer getBouncedCount() {
        return bouncedCount;
    }

    public void setBouncedCount(Integer bouncedCount) {
        this.bouncedCount = bouncedCount;
    }

    public Integer getUnsubscribedCount() {
        return unsubscribedCount;
    }

    public void setUnsubscribedCount(Integer unsubscribedCount) {
        this.unsubscribedCount = unsubscribedCount;
    }

    public Integer getSpamCount() {
        return spamCount;
    }

    public void setSpamCount(Integer spamCount) {
        this.spamCount = spamCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { 
            return true; 
        }
        if (o == null || getClass() != o.getClass()) { 
            return false; 
        }
        CampaignResponse that = (CampaignResponse) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(subject, that.subject) &&
                Objects.equals(from, that.from) &&
                Objects.equals(scheduledDate, that.scheduledDate) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(createdBy, that.createdBy) &&
                Objects.equals(updatedAt, that.updatedAt) &&
                Objects.equals(updatedBy, that.updatedBy) &&
                Objects.equals(templateId, that.templateId) &&
                Objects.equals(attachmentId, that.attachmentId) &&
                Objects.equals(contactFileId, that.contactFileId) &&
                Objects.equals(status, that.status) &&
                Objects.equals(startedAt, that.startedAt) &&
                Objects.equals(completedAt, that.completedAt) &&
                Objects.equals(total, that.total) &&
                Objects.equals(sentCount, that.sentCount) &&
                Objects.equals(failedCount, that.failedCount) &&
                Objects.equals(openedCount, that.openedCount) &&
                Objects.equals(clickedCount, that.clickedCount) &&
                Objects.equals(inboundCount, that.inboundCount) &&
                Objects.equals(bouncedCount, that.bouncedCount) &&
                Objects.equals(unsubscribedCount, that.unsubscribedCount) &&
                Objects.equals(spamCount, that.spamCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, subject, from, scheduledDate, createdAt, createdBy, 
                updatedAt, updatedBy, templateId, attachmentId, contactFileId, status, startedAt, 
                completedAt, total, sentCount, failedCount, openedCount, clickedCount, inboundCount, 
                bouncedCount, unsubscribedCount, spamCount);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CampaignResponse{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name);
        sb.append(", description='").append(description);
        sb.append(", subject='").append(subject);
        sb.append(", from='").append(from);
        sb.append(", scheduledDate=").append(scheduledDate);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", createdBy='").append(createdBy);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", updatedBy='").append(updatedBy);
        sb.append(", templateId=").append(templateId);
        sb.append(", attachmentId=").append(attachmentId);
        sb.append(", contactFileId=").append(contactFileId);
        sb.append(", status='").append(status);
        sb.append(", startedAt=").append(startedAt);
        sb.append(", completedAt=").append(completedAt);
        sb.append(", total=").append(total);
        sb.append(", sentCount=").append(sentCount);
        sb.append(", failedCount=").append(failedCount);
        sb.append(", openedCount=").append(openedCount);
        sb.append(", clickedCount=").append(clickedCount);
        sb.append(", inboundCount=").append(inboundCount);
        sb.append(", bouncedCount=").append(bouncedCount);
        sb.append(", unsubscribedCount=").append(unsubscribedCount);
        sb.append(", spamCount=").append(spamCount);
        sb.append('}');
        return sb.toString();
    }
}
