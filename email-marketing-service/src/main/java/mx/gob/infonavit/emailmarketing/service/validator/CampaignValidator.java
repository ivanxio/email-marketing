package mx.gob.infonavit.emailmarketing.service.validator;

import mx.gob.infonavit.emailmarketing.model.dto.request.CampaignCreateRequest;
import mx.gob.infonavit.emailmarketing.model.dto.request.CampaignUpdateRequest;
import mx.gob.infonavit.emailmarketing.service.entity.Campaign;
import mx.gob.infonavit.emailmarketing.service.entity.CampaignStatus;
import mx.gob.infonavit.emailmarketing.service.exception.InvalidCampaignException;

import javax.ejb.Stateless;
import java.time.LocalDateTime;

/**
 * Validador de campañas de correo electrónico.
 * 
 * @author Ivan Garcia Martinez igarciam@desarrollo-ultrasist.com.mx
 * @version 1.0.0
 * @since 2025-10-27
 */
@Stateless
public class CampaignValidator {

    private static final String REGEX_NAME = "^[a-zA-Z0-9\\s\\-_áéíóúÁÉÍÓÚñÑ.,:;()]+$";
    private static final String REGEX_SUBJECT = "^[a-zA-Z0-9\\s\\-_áéíóúÁÉÍÓÚñÑ.,:;()!¡?¿@#$%&*+=<>\"'\\[\\]{}|\\\\/~`]+$";
    private static final String REGEX_DESCRIPTION = "^[a-zA-Z0-9\\s\\-_áéíóúÁÉÍÓÚñÑ.,:;()!¡?¿@#$%&*+=<>\"'\\[\\]{}|\\\\/~`\\n\\r]+$";
    private static final String REGEX_EMAIL = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    public void validateCampaignCreate(CampaignCreateRequest request) {
        validateName(request.getName());
        validateDescription(request.getDescription());
        validateSubject(request.getSubject());        
        validateFrom(request.getFrom());
        validateScheduledDate(request.getScheduledAt());
        validateTemplateId(request.getTemplateId());
        validateAttachmentId(request.getAttachmentId());
        validateContactFileId(request.getContactFileId());
    }

    public void validateCampaignUpdate(CampaignUpdateRequest request) {
        validateCampaignId(request.getId());
        validateName(request.getName());
        validateSubject(request.getSubject());
        validateDescription(request.getDescription());
        validateFrom(request.getFrom());
        validateScheduledDate(request.getScheduledAt());
        validateTemplateId(request.getTemplateId());
        validateAttachmentId(request.getAttachmentId());
        validateContactFileId(request.getContactFileId());
    }

    public void validateCampaignId(Long campaignId) {
        if (campaignId == null) {
            throw InvalidCampaignException.campaignIdRequired();
        }
        if (campaignId <= 0) {
            throw InvalidCampaignException.campaignIdInvalid();
        }
    }

    public void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw InvalidCampaignException.nameRequired();
        }
        if (name.length() > 255) {
            throw InvalidCampaignException.nameMaxLength();
        }
        if (!name.matches(REGEX_NAME)) {
            throw InvalidCampaignException.nameInvalidCharacters();
        }
    }

    public void validateSubject(String subject) {
        if (subject == null || subject.trim().isEmpty()) {
            throw InvalidCampaignException.subjectRequired();
        }
        if (subject.length() > 500) {
            throw InvalidCampaignException.subjectMaxLength();
        }
        if (!subject.matches(REGEX_SUBJECT)) {
            throw InvalidCampaignException.subjectInvalidCharacters();
        }
    }

    public void validateDescription(String description) {
        if (description.length() > 1000) {
            throw InvalidCampaignException.descriptionMaxLength();
        }
        if (!description.matches(REGEX_DESCRIPTION)) {
            throw InvalidCampaignException.descriptionInvalidCharacters();
        }
    }

    public void validateScheduledDate(LocalDateTime scheduledDate) {
        if (scheduledDate != null) {
            LocalDateTime now = LocalDateTime.now();
            if (scheduledDate.isBefore(now)) {
                throw InvalidCampaignException.scheduledDateInPast();
            }
        }
    }

    public void validateFrom(String from) {
        if (from == null || from.trim().isEmpty()) {
            throw InvalidCampaignException.fromRequired();
        }
        if (from.length() > 255) {
            throw InvalidCampaignException.fromMaxLength();
        }
        if (!from.matches(REGEX_EMAIL)) {
            throw InvalidCampaignException.fromInvalidEmail();
        }
    }

    public void validateTemplateId(Long templateId) {
        if (templateId != null && templateId <= 0) {
            throw InvalidCampaignException.templateIdInvalid();
        }
    }

    public void validateAttachmentId(Long attachmentId) {
        if (attachmentId != null && attachmentId <= 0) {
            throw InvalidCampaignException.attachmentIdInvalid();
        }
    }

    public void validateContactFileId(Long contactFileId) {
        if (contactFileId == null) {
            throw InvalidCampaignException.contactFileIdRequired();
        }
        if (contactFileId <= 0) {
            throw InvalidCampaignException.contactFileIdInvalid();
        }
    }

    public void validateCampaignCanBeUpdated(Campaign campaign) {
        if (campaign == null) {
            throw InvalidCampaignException.campaignIdRequired();
        }
        if (campaign.getStatus() != CampaignStatus.DRAFT && campaign.getStatus() != CampaignStatus.SCHEDULED) {
            throw InvalidCampaignException.cannotBeUpdated(campaign.getStatus().name());
        }
        if (campaign.getScheduledAt() != null && campaign.getScheduledAt().isBefore(LocalDateTime.now())) {
            throw InvalidCampaignException.scheduledDateInPast();
        }
    }

    public void validateCampaignCanBePaused(Campaign campaign) {
        if (campaign == null) {
            throw InvalidCampaignException.campaignIdRequired();
        }
        if (campaign.getStatus() != CampaignStatus.IN_PROGRESS) {
            throw InvalidCampaignException.cannotBePaused(campaign.getStatus().name());
        }
    }

    public void validateCampaignCanBeResumed(Campaign campaign) {
        if (campaign == null) {
            throw InvalidCampaignException.campaignIdRequired();
        }
        if (campaign.getStatus() != CampaignStatus.PAUSED) {
            throw InvalidCampaignException.cannotBeResumed(campaign.getStatus().name());
        }
    }

    public void validateCampaignCanBeRun(Campaign campaign) {
        if (campaign == null) {
            throw InvalidCampaignException.campaignIdRequired();
        }
        if (campaign.getStatus() != CampaignStatus.SCHEDULED && campaign.getStatus() != CampaignStatus.PAUSED) {
            throw InvalidCampaignException.cannotBeRun(campaign.getStatus().name());
        }
    }

    public void validatePaginationParameters(int page, int size) {
        if (page < 0) {
            throw InvalidCampaignException.invalidPageNumber();
        }
        if (size <= 0) {
            throw InvalidCampaignException.invalidPageSize();
        }
        if (size > 100) {
            throw InvalidCampaignException.pageSizeTooLarge();
        }
    }
}
