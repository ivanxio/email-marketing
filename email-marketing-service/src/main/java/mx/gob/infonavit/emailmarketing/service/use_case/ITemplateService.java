package mx.gob.infonavit.emailmarketing.service.use_case;

import mx.gob.infonavit.emailmarketing.model.dto.request.TemplateCreateRequest;
import mx.gob.infonavit.emailmarketing.model.dto.request.TemplateUpdateRequest;
import mx.gob.infonavit.emailmarketing.model.dto.response.TemplateResponse;

import javax.ejb.Local;
import java.util.List;

/**
 * Interfaz del servicio de gestión de plantillas de correo electrónico.
 * 
 * Define el contrato para las operaciones de negocio relacionadas con plantillas.
 * Implementa el patrón Service Layer para encapsular la lógica de negocio.
 * 
 * @author Ivan Garcia igarciam@desarrollo-ultrasis.com.mx
 * @version 1.0.0
 * @since 2025-10-20
 */
@Local
public interface ITemplateService {

    /**
     * Crea una nueva plantilla de correo electrónico.
     * 
     * @param request datos de la plantilla a crear
     * @param userId ID del usuario que crea la plantilla
     * @return plantilla creada
     */
    TemplateResponse createTemplate(TemplateCreateRequest request, Long userId);

    /**
     * Actualiza una plantilla existente.
     * 
     * @param id ID de la plantilla a actualizar
     * @param request datos de actualización
     * @return plantilla actualizada
     */
    TemplateResponse updateTemplate(Long id, TemplateUpdateRequest request);

    /**
     * Busca una plantilla por su ID.
     *
     * @param id ID de la plantilla
     * @return plantilla encontrada
     */
    TemplateResponse findTemplateById(Long id);

    /**
     * Recupera plantillas paginadas ordenadas por fecha de creación.
     * 
     * @param page número de página (base 0)
     * @param size tamaño de página
     * @return lista paginada de plantillas activas/inactivas
     */
    List<TemplateResponse> findAllTemplates(int page, int size);
}

