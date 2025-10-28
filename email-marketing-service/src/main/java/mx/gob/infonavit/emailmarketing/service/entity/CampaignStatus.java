package mx.gob.infonavit.emailmarketing.service.entity;

/**
 * Enum que representa los posibles estados de una campaña de correo electrónico.
 * 
 * @author Ivan Garcia igarciam@desarrollo-ultrasis.com.mx
 * @version 1.0.0
 * @since 2025-10-20
 */
public enum CampaignStatus {
    
    DRAFT("Borrador"),
    SCHEDULED("Programada"),
    IN_PROGRESS("En Progreso"),
    PAUSED("Pausada"),
    COMPLETED("Completada"),
    CANCELLED("Cancelada");

    private final String description;

    CampaignStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}
