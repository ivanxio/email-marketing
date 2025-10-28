package mx.gob.infonavit.emailmarketing.service.mapper;

import mx.gob.infonavit.emailmarketing.model.dto.request.CampaignCreateRequest;
import mx.gob.infonavit.emailmarketing.model.dto.request.CampaignUpdateRequest;
import mx.gob.infonavit.emailmarketing.model.dto.response.CampaignResponse;
import mx.gob.infonavit.emailmarketing.service.entity.Campaign;
import mx.gob.infonavit.emailmarketing.service.entity.CampaignStatus;

import javax.ejb.Stateless;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper para convertir entre entidades Campaign y DTOs.
 * 
 * @author Ivan Garcia Martinez igarciam@desarrollo-ultrasist.com.mx
 * @version 1.0.0
 * @since 2025-10-27
 */
@Stateless
public class CampaignMapper {

    public Campaign createCampaignEntity(CampaignCreateRequest request) {
        Campaign campaign = new Campaign();
        campaign.setName(request.getName());
        campaign.setDescription(request.getDescription());
        campaign.setSubject(request.getSubject());
        campaign.setFrom(request.getFrom());
        campaign.setScheduledAt(request.getScheduledAt());
        campaign.setCreatedAt(LocalDateTime.now());
        campaign.setCreatedBy(request.getCreatedBy());
        campaign.setTemplateId(request.getTemplateId());
        campaign.setAttachmentId(request.getAttachmentId());        
        campaign.setContactFileId(request.getContactFileId());
        campaign.setUpdatedAt(null);
        campaign.setUpdatedBy(null);        
        CampaignStatus status = request.getScheduledAt() == null
            ? CampaignStatus.DRAFT : CampaignStatus.SCHEDULED;
        campaign.setStatus(status);
        campaign.setTotal(0);
        campaign.setSentCount(0);
        campaign.setFailedCount(0);
        campaign.setOpenedCount(0);
        campaign.setClickedCount(0);
        campaign.setInboundCount(0);
        campaign.setBouncedCount(0);
        campaign.setUnsubscribedCount(0);
        campaign.setSpamCount(0);
        campaign.setStartedAt(null);
        campaign.setCompletedAt(null);
        return campaign;
    }

    public Campaign updateCampaignEntity(Campaign campaign, CampaignUpdateRequest request) {
        campaign.setName(request.getName());
        campaign.setDescription(request.getDescription());
        campaign.setSubject(request.getSubject());
        campaign.setFrom(request.getFrom());        
        campaign.setScheduledAt(request.getScheduledAt());
        campaign.setAttachmentId(request.getAttachmentId());
        campaign.setTemplateId(request.getTemplateId());
        campaign.setUpdatedAt(LocalDateTime.now());
        campaign.setUpdatedBy(request.getUpdatedBy());
        return campaign;
    }

    public CampaignResponse toDto(Campaign campaign) {
        CampaignResponse response = new CampaignResponse();
        response.setId(campaign.getId());
        response.setName(campaign.getName());
        response.setDescription(campaign.getDescription());
        response.setSubject(campaign.getSubject());
        response.setFrom(campaign.getFrom());
        response.setScheduledDate(campaign.getScheduledAt());
        response.setCreatedAt(campaign.getCreatedAt());
        response.setCreatedBy(campaign.getCreatedBy());
        response.setUpdatedAt(campaign.getUpdatedAt());
        response.setUpdatedBy(campaign.getUpdatedBy());
        response.setTemplateId(campaign.getTemplateId());
        response.setAttachmentId(campaign.getAttachmentId());
        response.setContactFileId(campaign.getContactFileId());
        response.setStatus(campaign.getStatus() != null ? campaign.getStatus().name() : null);
        response.setStartedAt(campaign.getStartedAt());
        response.setCompletedAt(campaign.getCompletedAt());
        response.setTotal(campaign.getTotal());
        response.setSentCount(campaign.getSentCount());
        response.setFailedCount(campaign.getFailedCount());
        response.setOpenedCount(campaign.getOpenedCount());
        response.setClickedCount(campaign.getClickedCount());
        response.setInboundCount(campaign.getInboundCount());
        response.setBouncedCount(campaign.getBouncedCount());
        response.setUnsubscribedCount(campaign.getUnsubscribedCount());
        response.setSpamCount(campaign.getSpamCount());        
        return response;
    }

    public List<CampaignResponse> toDtoList(List<Campaign> campaigns) {
        if (campaigns == null || campaigns.isEmpty()) {
            return Collections.emptyList();
        }

        return campaigns.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Campaign pauseCampaign(Campaign campaign, String updatedBy) {
        campaign.setStatus(CampaignStatus.PAUSED);
        campaign.setUpdatedAt(LocalDateTime.now());
        campaign.setUpdatedBy(updatedBy);
        return campaign;
    }

    public Campaign resumeCampaign(Campaign campaign, String updatedBy) {
        campaign.setStatus(CampaignStatus.IN_PROGRESS);
        campaign.setUpdatedAt(LocalDateTime.now());
        campaign.setUpdatedBy(updatedBy);
        return campaign;
    }

    public Campaign runCampaign(Campaign campaign, String updatedBy) {
        campaign.setStatus(CampaignStatus.IN_PROGRESS);
        campaign.setStartedAt(LocalDateTime.now());
        campaign.setUpdatedAt(LocalDateTime.now());
        campaign.setUpdatedBy(updatedBy);
        return campaign;
    }

    public Campaign completeCampaign(Campaign campaign, String updatedBy) {
        campaign.setStatus(CampaignStatus.COMPLETED);
        campaign.setCompletedAt(LocalDateTime.now());
        campaign.setUpdatedAt(LocalDateTime.now());
        campaign.setUpdatedBy(updatedBy);
        return campaign;
    }
}