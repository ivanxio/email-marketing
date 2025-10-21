package mx.gob.infonavit.emailmarketing.service.repository;

import mx.gob.infonavit.emailmarketing.service.entity.Template;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz del repositorio para operaciones de acceso a datos de plantillas.
 * 
 * Define el contrato para las operaciones de persistencia de plantillas.
 * 
 * @author Ivan Garcia igarciam@desarrollo-ultrasis.com.mx
 * @version 1.0.0
 * @since 2025-10-20
 */
public interface ITemplateRepository {

    /**
     * Guarda una nueva plantilla en la base de datos.
     * 
     * @param template plantilla a guardar
     * @return plantilla guardada con ID generado
     */
    Template save(Template template);

    /**
     * Actualiza una plantilla existente.
     * 
     * @param template plantilla con los cambios
     * @return plantilla actualizada
     */
    Template update(Template template);

    /**
     * Busca una plantilla por su ID.
     * 
     * @param id ID de la plantilla
     * @return Optional con la plantilla si existe
     */
    Optional<Template> findById(Long id);

    /**
     * Obtiene todas las plantillas ordenadas por fecha de creación.
     * 
     * @param offset desplazamiento para paginación
     * @param limit número máximo de resultados
     * @return lista de plantillas
     */
    List<Template> findAll(int offset, int limit);
}

