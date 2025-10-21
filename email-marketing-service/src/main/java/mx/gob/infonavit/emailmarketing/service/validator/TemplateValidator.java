package mx.gob.infonavit.emailmarketing.service.validator;

import mx.gob.infonavit.emailmarketing.model.dto.request.TemplateCreateRequest;
import mx.gob.infonavit.emailmarketing.model.dto.request.TemplateUpdateRequest;
import mx.gob.infonavit.emailmarketing.service.exception.InvalidTemplateException;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Validador de reglas de negocio para plantillas.
 * 
 * Implementa validaciones específicas del dominio que van más allá
 * de las validaciones básicas de Bean Validation.
 * 
 * @author INFONAVIT - Email Marketing Team
 * @version 1.0.0
 * @since 2025-10-20
 */
@Stateless
public class TemplateValidator {

    // Constantes de validación - Expresiones regulares
    private static final String REGEX_ONLY_WHITESPACE = "\\s+";
    private static final String REGEX_ALLOWED_NAME_CHARACTERS = "[a-zA-Z0-9\\s\\-_áéíóúÁÉÍÓÚñÑ]+";

    // Constantes de validación - Longitudes
    private static final int MIN_NAME_LENGTH = 3;
    private static final int MAX_NAME_LENGTH = 255;    
    private static final int MIN_HTML_LENGTH = 50;
    private static final int MAX_HTML_LENGTH = 500000; // 500KB

    /**
     * Valida una solicitud de creación de plantilla.
     * 
     * @param request request a validar
     * @throws InvalidTemplateException si la validación falla
     */
    public void validateCreateRequest(TemplateCreateRequest request) {
        List<String> errors = new ArrayList<>();

        // Validar nombre
        validateName(request.getName(), errors);

        // Validar contenido HTML
        validateHtmlContent(request.getHtmlContent(), errors);

        if (!errors.isEmpty()) {
            throw new InvalidTemplateException("Validación de plantilla fallida: " + String.join(", ", errors));
        }
    }

    /**
     * Valida una solicitud de actualización de plantilla.
     * 
     * @param request request a validar
     * @throws InvalidTemplateException si la validación falla
     */
    public void validateUpdateRequest(TemplateUpdateRequest request) {
        List<String> errors = new ArrayList<>();

        // Validar que haya al menos un campo para actualizar
        if (!request.hasUpdates()) {
            throw new InvalidTemplateException("Debe proporcionar al menos un campo para actualizar");
        }

        // Validar nombre
        if (request.getName() != null) {
            validateName(request.getName(), errors);
        }

        // Validar contenido HTML
        if (request.getHtmlContent() != null) {
            validateHtmlContent(request.getHtmlContent(), errors);
        }

        if (!errors.isEmpty()) {
            throw new InvalidTemplateException("Validación de actualización fallida: " + String.join(", ", errors));
        }
    }

    /**
     * Valida el nombre de la plantilla.
     * 
     * @param name nombre a validar
     * @param errors lista para acumular errores
     */
    private void validateName(String name, List<String> errors) {
        if (name == null || name.trim().isEmpty()) {
            errors.add("El nombre no puede estar vacío");
            return;
        }

        String trimmedName = name.trim();
        
        // Validar longitud
        if (trimmedName.length() < MIN_NAME_LENGTH) {
            errors.add(String.format("El nombre debe tener al menos %d caracteres", MIN_NAME_LENGTH));
        }

        if (trimmedName.length() > MAX_NAME_LENGTH) {
            errors.add(String.format("El nombre no puede exceder %d caracteres", MAX_NAME_LENGTH));
        }

        // Validar que no contenga solo espacios
        if (trimmedName.matches(REGEX_ONLY_WHITESPACE)) {
            errors.add("El nombre no puede contener solo espacios en blanco");
        }

        // Validar caracteres permitidos
        if (!trimmedName.matches(REGEX_ALLOWED_NAME_CHARACTERS)) {
            errors.add("El nombre contiene caracteres no permitidos");
        }
    }

    /**
     * Valida el contenido HTML de la plantilla.
     * 
     * @param htmlContent contenido HTML a validar
     * @param errors lista para acumular errores
     */
    private void validateHtmlContent(String htmlContent, List<String> errors) {
        if (htmlContent == null || htmlContent.trim().isEmpty()) {
            errors.add("El contenido HTML no puede estar vacío");
            return;
        }

        String trimmedHtml = htmlContent.trim();

        // Validar longitud
        if (trimmedHtml.length() < MIN_HTML_LENGTH) {
            errors.add(String.format("El contenido HTML debe tener al menos %d caracteres", MIN_HTML_LENGTH));
        }

        if (trimmedHtml.length() > MAX_HTML_LENGTH) {
            errors.add(String.format("El contenido HTML no puede exceder %d caracteres", MAX_HTML_LENGTH));
        }

    }

}

