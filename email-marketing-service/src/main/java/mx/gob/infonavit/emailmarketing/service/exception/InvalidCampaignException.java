package mx.gob.infonavit.emailmarketing.service.exception;

/**
 * Excepción lanzada cuando los datos de una campaña son inválidos.
 * 
 * Se utiliza para validaciones de negocio que fallan, como datos de entrada
 * incorrectos o reglas de negocio no cumplidas.
 * 
 * @author Ivan Garcia Martínez igarciam@desarrollo-ultrasist.com.mx
 * @version 1.0.0
 * @since 2025-10-27
 */
public class InvalidCampaignException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidCampaignException(String message) {
        super(message);
    }


    public static InvalidCampaignException campaignIdRequired() {
        return new InvalidCampaignException("El ID de la campaña es obligatorio");
    }

    public static InvalidCampaignException campaignIdInvalid() {
        return new InvalidCampaignException("El ID de la campaña debe ser mayor a 0");
    }

    public static InvalidCampaignException campaignIdMismatch() {
        return new InvalidCampaignException("El ID de la campaña en el path no coincide con el ID en el body");
    }

    public static InvalidCampaignException cannotBeUpdated(String status) {
        return new InvalidCampaignException("La campaña no puede ser actualizada en estado: " + status);
    }

    public static InvalidCampaignException nameRequired() {
        return new InvalidCampaignException("El nombre de la campaña es obligatorio");
    }

    public static InvalidCampaignException nameMaxLength() {
        return new InvalidCampaignException("El nombre no puede exceder 255 caracteres");
    }

    public static InvalidCampaignException nameInvalidCharacters() {
        return new InvalidCampaignException("El nombre contiene caracteres no válidos");
    }

    public static InvalidCampaignException subjectRequired() {
        return new InvalidCampaignException("El asunto es obligatorio");
    }

    public static InvalidCampaignException subjectMaxLength() {
        return new InvalidCampaignException("El asunto no puede exceder 500 caracteres");
    }

    public static InvalidCampaignException subjectInvalidCharacters() {
        return new InvalidCampaignException("El asunto contiene caracteres no válidos");
    }

    public static InvalidCampaignException descriptionMaxLength() {
        return new InvalidCampaignException("La descripción no puede exceder 1000 caracteres");
    }

    public static InvalidCampaignException descriptionInvalidCharacters() {
        return new InvalidCampaignException("La descripción contiene caracteres no válidos");
    }

    public static InvalidCampaignException fromRequired() {
        return new InvalidCampaignException("El campo 'from' es obligatorio");
    }

    public static InvalidCampaignException fromMaxLength() {
        return new InvalidCampaignException("El campo 'from' no puede exceder 255 caracteres");
    }

    public static InvalidCampaignException fromInvalidEmail() {
        return new InvalidCampaignException("El campo 'from' debe ser un email válido");
    }

    public static InvalidCampaignException scheduledDateInPast() {
        return new InvalidCampaignException("La fecha programada no puede ser anterior a la fecha actual");
    }

    public static InvalidCampaignException templateIdInvalid() {
        return new InvalidCampaignException("El ID de la plantilla debe ser mayor a 0");
    }

    public static InvalidCampaignException attachmentIdInvalid() {
        return new InvalidCampaignException("El ID del archivo adjunto debe ser mayor a 0");
    }

    public static InvalidCampaignException contactFileIdRequired() {
        return new InvalidCampaignException("El ID del archivo de contactos es obligatorio");
    }

    public static InvalidCampaignException contactFileIdInvalid() {
        return new InvalidCampaignException("El ID del archivo de contactos debe ser mayor a 0");
    }

    public static InvalidCampaignException cannotBePaused(String status) {
        return new InvalidCampaignException("La campaña no puede ser pausada en estado: " + status);
    }

    public static InvalidCampaignException cannotBeResumed(String status) {
        return new InvalidCampaignException("La campaña no puede ser reanudada en estado: " + status);
    }

    public static InvalidCampaignException cannotBeRun(String status) {
        return new InvalidCampaignException("La campaña no puede ser ejecutada en estado: " + status);
    }

    public static InvalidCampaignException invalidPageNumber() {
        return new InvalidCampaignException("El número de página no puede ser negativo");
    }

    public static InvalidCampaignException invalidPageSize() {
        return new InvalidCampaignException("El tamaño de página debe ser mayor a cero");
    }

    public static InvalidCampaignException pageSizeTooLarge() {
        return new InvalidCampaignException("El tamaño de página no puede exceder 100 elementos");
    }
}
