package mx.gob.infonavit.emailmarketing.service.exception;

/**
 * Excepción lanzada cuando una plantilla no es encontrada.
 * 
 * Esta excepción se utiliza para indicar que una plantilla solicitada
 * no existe en el sistema.
 * 
 * @author INFONAVIT - Email Marketing Team
 * @version 1.0.0
 * @since 2025-10-20
 */
public class TemplateNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final Long templateId;

    /**
     * Constructor con mensaje personalizado.
     * 
     * @param message mensaje de error
     */
    public TemplateNotFoundException(String message) {
        super(message);
        this.templateId = null;
    }

    /**
     * Constructor con ID de la plantilla no encontrada.
     * 
     * @param templateId ID de la plantilla
     */
    public TemplateNotFoundException(Long templateId) {
        super(String.format("Plantilla con ID %d no encontrada", templateId));
        this.templateId = templateId;
    }

    /**
     * Constructor con ID y causa.
     * 
     * @param templateId ID de la plantilla
     * @param cause causa de la excepción
     */
    public TemplateNotFoundException(Long templateId, Throwable cause) {
        super(String.format("Plantilla con ID %d no encontrada", templateId), cause);
        this.templateId = templateId;
    }

    /**
     * Obtiene el ID de la plantilla que no fue encontrada.
     * 
     * @return ID de la plantilla o null si no se especificó
     */
    public Long getTemplateId() {
        return templateId;
    }
}

