package mx.gob.infonavit.emailmarketing.service.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Entidad que representa una campaña de correo electrónico.
 * 
 * @author Ivan Garcia igarciam@desarrollo-ultrasis.com.mx
 * @version 1.0.0
 * @since 2025-10-20
 */
@Entity
@Table(name = "campaigns" , indexes = {
    @Index(name = "idx_campaigns_created_at", columnList = "created_at")
})
@NamedQueries({
    @NamedQuery(
        name = Campaign.FIND_ALL_ORDERED_BY_CREATED_AT,
        query = "SELECT c FROM Campaign c ORDER BY c.createdAt DESC"
    )
})
public class Campaign implements Serializable {

    static final long serialVersionUID = 1L;

    public static final String FIND_ALL_ORDERED_BY_CREATED_AT = "Campaign.findAllOrderedByCreatedAt";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "description", nullable = false, length = 1000)
    private String description;

    @Column(name = "subject", nullable = false, length = 500)
    private String subject;

    @Column(name = "from", nullable = false, length = 255)
    private String from;

    @Column(name = "scheduled_at")
    private LocalDateTime scheduledAt;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "created_by", nullable = false, length = 100)
    private String createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "template_id", nullable = false)
    private Template template;

    @Column(name = "template_id", nullable = false)
    private Long templateId;

    @Column(name = "attachment_id", nullable = false)
    private Long attachmentId;

    @Column(name = "contact_file_id", nullable = false)
    private Long contactFileId;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "updated_by", length = 100)
    private String updatedBy;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private CampaignStatus status;

    @Column(name = "started_at")
    private LocalDateTime startedAt;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;

    @Column(name = "total")
    private Integer total;

    @Column(name = "sent_count")
    private Integer sentCount;

    @Column(name = "failed_count")
    private Integer failedCount;

    @Column(name = "opened_count")
    private Integer openedCount;

    @Column(name = "clicked_count")
    private Integer clickedCount;

    @Column(name = "inbound_count")
    private Integer inboundCount;

    @Column(name = "bounced_count")
    private Integer bouncedCount;

    @Column(name = "unsubscribed_count")
    private Integer unsubscribedCount;

    @Column(name = "spam_count")
    private Integer spamCount;

    public Campaign() {
    }

    public Campaign(Long id, 
                    String name, 
                    String description, 
                    String subject, 
                    String from,
                    CampaignStatus status, 
                    LocalDateTime scheduledAt, 
                    LocalDateTime startedAt,
                    LocalDateTime completedAt, 
                    Integer total, 
                    Integer sentCount, Integer failedCount,
                    Integer openedCount, 
                    Integer clickedCount, 
                    Integer inboundCount,
                    Integer bouncedCount, 
                    Integer unsubscribedCount, 
                    Integer spamCount,
                    LocalDateTime createdAt, 
                    LocalDateTime updatedAt, 
                    String createdBy,
                    String updatedBy, 
                    Long templateId, 
                    Long attachmentId, 
                    Long contactFileId) {
    
        this.id = id;
        this.name = name;
        this.description = description;
        this.subject = subject;
        this.from = from;
        this.status = status;
        this.scheduledAt = scheduledAt;
        this.startedAt = startedAt;
        this.completedAt = completedAt;
        this.total = total;
        this.sentCount = sentCount;
        this.failedCount = failedCount;
        this.openedCount = openedCount;
        this.clickedCount = clickedCount;
        this.inboundCount = inboundCount;
        this.bouncedCount = bouncedCount;
        this.unsubscribedCount = unsubscribedCount;
        this.spamCount = spamCount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.templateId = templateId;
        this.attachmentId = attachmentId;
        this.contactFileId = contactFileId;
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

    public CampaignStatus getStatus() {
        return status;
    }

    public void setStatus(CampaignStatus status) {
        this.status = status;
    }

    public LocalDateTime getScheduledAt() {
        return scheduledAt;
    }

    public void setScheduledAt(LocalDateTime scheduledAt) {
        this.scheduledAt = scheduledAt;
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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
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
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Campaign campaign = (Campaign) o;
        return Objects.equals(id, campaign.id) &&
               Objects.equals(name, campaign.name) &&
               Objects.equals(description, campaign.description) &&
               Objects.equals(subject, campaign.subject) &&
               Objects.equals(from, campaign.from) &&
               status == campaign.status &&
               Objects.equals(scheduledAt, campaign.scheduledAt) &&
               Objects.equals(startedAt, campaign.startedAt) &&
               Objects.equals(completedAt, campaign.completedAt) &&
               Objects.equals(total, campaign.total) &&
               Objects.equals(sentCount, campaign.sentCount) &&
               Objects.equals(failedCount, campaign.failedCount) &&
               Objects.equals(openedCount, campaign.openedCount) &&
               Objects.equals(clickedCount, campaign.clickedCount) &&
               Objects.equals(inboundCount, campaign.inboundCount) &&
               Objects.equals(bouncedCount, campaign.bouncedCount) &&
               Objects.equals(unsubscribedCount, campaign.unsubscribedCount) &&
               Objects.equals(spamCount, campaign.spamCount) &&
               Objects.equals(createdAt, campaign.createdAt) &&
               Objects.equals(updatedAt, campaign.updatedAt) &&
               Objects.equals(createdBy, campaign.createdBy) &&
               Objects.equals(updatedBy, campaign.updatedBy) &&
               Objects.equals(template, campaign.template) &&
               Objects.equals(templateId, campaign.templateId) &&
               Objects.equals(attachmentId, campaign.attachmentId) &&
               Objects.equals(contactFileId, campaign.contactFileId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id, 
            name,
            description, 
            subject, 
            from, 
            status, 
            scheduledAt, 
            startedAt, 
            completedAt, 
            total, 
            sentCount, 
            failedCount, 
            openedCount, 
            clickedCount, 
            inboundCount, 
            bouncedCount, 
            unsubscribedCount, 
            spamCount, 
            createdAt, 
            updatedAt, 
            createdBy, 
            updatedBy, 
            templateId, 
            attachmentId, 
            contactFileId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Campaign{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name);
        sb.append(", description='").append(description);
        sb.append(", subject='").append(subject);
        sb.append(", from='").append(from);
        sb.append(", status=").append(status);
        sb.append(", scheduledAt=").append(scheduledAt);
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
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", createdBy='").append(createdBy);
        sb.append(", updatedBy='").append(updatedBy);
        sb.append(", templateId=").append(templateId);
        sb.append(", attachmentId=").append(attachmentId);
        sb.append(", contactFileId=").append(contactFileId);
        sb.append('}');
        return sb.toString();
    }
}
