package mx.gob.infonavit.emailmarketing.service.exception;

/**
 * Excepción lanzada cuando los datos de una plantilla son inválidos.
 * 
 * Se utiliza para validaciones de negocio que fallan, como contenido HTML
 * mal formado o reglas de negocio no cumplidas.
 * 
 * @author INFONAVIT - Email Marketing Team
 * @version 1.0.0
 * @since 2025-10-20
 */
public class InvalidTemplateException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor con mensaje.
     * 
     * @param message mensaje de error
     */
    public InvalidTemplateException(String message) {
        super(message);
    }

    /**
     * Constructor con mensaje y causa.
     * 
     * @param message mensaje de error
     * @param cause causa de la excepción
     */
    public InvalidTemplateException(String message, Throwable cause) {
        super(message, cause);
    }
}

