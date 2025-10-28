package mx.gob.infonavit.emailmarketing.service.exception;

/**
 * Excepción lanzada cuando los datos de una plantilla son inválidos.
 * 
 * Se utiliza para validaciones de negocio que fallan, como contenido HTML
 * mal formado o reglas de negocio no cumplidas.
 * 
 * @author Ivan Garcia Martínez igarciam@desarrollo-ultrasist.com.mx
 * @version 1.0.0
 * @since 2025-10-27
 */
public class InvalidTemplateException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidTemplateException(String message) {
        super(message);
    }

    public InvalidTemplateException(String message, Throwable cause) {
        super(message, cause);
    }
}

