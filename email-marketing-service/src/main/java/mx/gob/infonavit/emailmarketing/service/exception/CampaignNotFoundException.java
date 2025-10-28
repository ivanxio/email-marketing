package mx.gob.infonavit.emailmarketing.service.exception;

/**
 * Excepción lanzada cuando una campaña no es encontrada.
 * 
 * Esta excepción se utiliza para indicar que una campaña solicitada
 * no existe en el sistema.
 * 
 * @author INFONAVIT - Email Marketing Team
 * @version 1.0.0
 * @since 2025-10-21
 */
public class CampaignNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final Long campaignId;

    public CampaignNotFoundException(String message) {
        super(message);
        this.campaignId = null;
    }

    public CampaignNotFoundException(Long campaignId) {
        super(String.format("Campaña con ID %d no encontrada", campaignId));
        this.campaignId = campaignId;
    }

    public CampaignNotFoundException(Long campaignId, Throwable cause) {
        super(String.format("Campaña con ID %d no encontrada", campaignId), cause);
        this.campaignId = campaignId;
    }

    public Long getCampaignId() {
        return campaignId;
    }
}
