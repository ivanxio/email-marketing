package mx.gob.infonavit.emailmarketing.service.use_case;

import mx.gob.infonavit.emailmarketing.model.dto.request.CampaignCreateRequest;
import mx.gob.infonavit.emailmarketing.model.dto.request.CampaignUpdateRequest;
import mx.gob.infonavit.emailmarketing.model.dto.response.CampaignResponse;
import mx.gob.infonavit.emailmarketing.service.entity.Campaign;
import mx.gob.infonavit.emailmarketing.service.exception.CampaignNotFoundException;
import mx.gob.infonavit.emailmarketing.service.mapper.CampaignMapper;
import mx.gob.infonavit.emailmarketing.service.repository.ICampaignRepository;
import mx.gob.infonavit.emailmarketing.service.validator.CampaignValidator;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Implementación del servicio de gestión de campañas de correo electrónico.
 * 
 * @author Ivan Garcia igarciam@desarrollo-ultrasis.com.mx
 * @version 1.0.0
 * @since 2025-10-20    
 */
@Stateless
public class CampaignService implements ICampaignService {

    @EJB
    private CampaignValidator campaignValidator;

    @EJB
    private CampaignMapper campaignMapper;

    @EJB
    private ICampaignRepository campaignRepository;

    @Override
    public CampaignResponse createCampaign(CampaignCreateRequest request) {
        campaignValidator.validateCampaignCreate(request);
        Campaign campaign = campaignMapper.createCampaignEntity(request);
        Campaign campaignSaved = campaignRepository.save(campaign);
        return campaignMapper.toDto(campaignSaved);
    }

    @Override
    public CampaignResponse findCampaignById(Long campaignId) {
        campaignValidator.validateCampaignId(campaignId);
        Campaign campaign = campaignRepository.findById(campaignId)
                .orElseThrow(() -> new CampaignNotFoundException(campaignId));
        return campaignMapper.toDto(campaign);
    }

    @Override
    public List<CampaignResponse> findAllCampaigns(int page, int size) {
        campaignValidator.validatePaginationParameters(page, size);
        int offset = page * size;
        List<Campaign> campaigns = campaignRepository.findAll(offset, size);
        return campaignMapper.toDtoList(campaigns);
    }

    @Override
    public CampaignResponse updateCampaign(Long campaignId, CampaignUpdateRequest request) {
        campaignValidator.validateCampaignUpdate(request);
        Campaign existingCampaign = campaignRepository.findById(campaignId)
                .orElseThrow(() -> new CampaignNotFoundException(campaignId));
        campaignValidator.validateCampaignCanBeUpdated(existingCampaign);
        Campaign campaign = campaignMapper.updateCampaignEntity(existingCampaign, request);
        Campaign campaignUpdated = campaignRepository.update(campaign);        
        return campaignMapper.toDto(campaignUpdated);
    }

    @Override
    public CampaignResponse runCampaign(Long campaignId, String updatedBy) {
        campaignValidator.validateCampaignId(campaignId);
        Campaign campaign = campaignRepository.findById(campaignId)
                .orElseThrow(() -> new CampaignNotFoundException(campaignId));
        campaignValidator.validateCampaignCanBeRun(campaign);
        Campaign campaignUpdated = campaignMapper.runCampaign(campaign, updatedBy);
        campaignUpdated = campaignRepository.update(campaignUpdated);
        return campaignMapper.toDto(campaignUpdated);
    }

    @Override
    public CampaignResponse pauseCampaign(Long campaignId, String updatedBy) {
        campaignValidator.validateCampaignId(campaignId);
        Campaign campaign = campaignRepository.findById(campaignId)
                .orElseThrow(() -> new CampaignNotFoundException(campaignId));
        campaignValidator.validateCampaignCanBePaused(campaign);
        Campaign campaignUpdated = campaignMapper.pauseCampaign(campaign, updatedBy);
        campaignUpdated = campaignRepository.update(campaignUpdated);
        return campaignMapper.toDto(campaignUpdated);
    }

    @Override
    public CampaignResponse resumeCampaign(Long campaignId, String updatedBy) {
        campaignValidator.validateCampaignId(campaignId);
        Campaign campaign = campaignRepository.findById(campaignId)
                .orElseThrow(() -> new CampaignNotFoundException(campaignId));
        campaignValidator.validateCampaignCanBeResumed(campaign);
        Campaign campaignUpdated = campaignMapper.resumeCampaign(campaign, updatedBy);
        campaignUpdated = campaignRepository.update(campaignUpdated);
        return campaignMapper.toDto(campaignUpdated);
    }
}
