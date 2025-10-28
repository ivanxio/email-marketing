package mx.gob.infonavit.emailmarketing.model.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

/**
 * DTO estándar para respuestas de error en la API REST.
 * Proporciona un formato consistente para todos los errores del sistema.
 * 
 * @author Ivan Garcia Martinez igarciam@desarrollo-ultrasist.com.mx
 * @version 1.0.0
 * @since 2025-10-27
 */
@ApiModel(description = "Respuesta estándar de error para la API REST")
public class ErrorResponse {

    @ApiModelProperty(value = "Código de estado HTTP", example = "400")
    private int status;

    @ApiModelProperty(value = "Mensaje descriptivo del error", example = "Los datos proporcionados son inválidos")
    private String message;

    @ApiModelProperty(value = "Timestamp del error", example = "2024-10-21T15:30:00")
    private LocalDateTime timestamp;

    // Constructores
    public ErrorResponse() {
        this.timestamp = LocalDateTime.now();
    }

    public ErrorResponse(int status, String message) {
        this();
        this.status = status;
        this.message = message;
    }

    // Métodos estáticos para crear respuestas comunes
    public static ErrorResponse badRequest(String message) {
        return new ErrorResponse(400, message);
    }

    public static ErrorResponse notFound(String message) {
        return new ErrorResponse(404, message);
    }

    public static ErrorResponse internalServerError(String message) {
        return new ErrorResponse(500, message);
    }

    public static ErrorResponse validationError(String message) {
        return new ErrorResponse(400, message);
    }

    public static ErrorResponse businessError(String message) {
        return new ErrorResponse(400, message);
    }

    // Getters y Setters
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ErrorResponse{");
        sb.append("status=").append(status);
        sb.append(", message='").append(message).append('\'');
        sb.append(", timestamp=").append(timestamp);
        sb.append('}');
        return sb.toString();
    }
}
