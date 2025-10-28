package mx.gob.infonavit.emailmarketing.service.exception;

/**
 * Excepción lanzada cuando una plantilla no es encontrada.
 * 
 * Esta excepción se utiliza para indicar que una plantilla solicitada
 * no existe en el sistema.
 * 
 * @author Ivan Garcia Martínez igarciam@desarrollo-ultrasist.com.mx
 * @version 1.0.0
 * @since 2025-10-27
 */
public class TemplateNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final Long templateId;

    public TemplateNotFoundException(String message) {
        super(message);
        this.templateId = null;
    }

    public TemplateNotFoundException(Long templateId) {
        super(String.format("Plantilla con ID %d no encontrada", templateId));
        this.templateId = templateId;
    }

    public TemplateNotFoundException(Long templateId, Throwable cause) {
        super(String.format("Plantilla con ID %d no encontrada", templateId), cause);
        this.templateId = templateId;
    }

    public Long getTemplateId() {
        return templateId;
    }
}

