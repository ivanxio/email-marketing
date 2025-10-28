package mx.gob.infonavit.emailmarketing.service.repository;

import mx.gob.infonavit.emailmarketing.service.entity.Campaign;
import mx.gob.infonavit.emailmarketing.service.entity.CampaignStatus;

import java.util.List;
import java.util.Optional;

/**
 * Interface para el repositorio de campañas de correo electrónico.
 * Define las operaciones de acceso a datos para la entidad Campaign.
 * 
 * @author  Ivan Garcia Martínez igarciam@desarrollo-ultrasist.com.mx
 * @version 1.0.0
 * @since 2025-10-27
 */
public interface ICampaignRepository {

    /**
     * Guarda una nueva campaña en la base de datos.
     * 
     * @param campaign La campaña a guardar
     * @return La campaña guardada con su ID generado
     */
    Campaign save(Campaign campaign);

    /**
     * Actualiza una campaña existente en la base de datos.
     * 
     * @param campaign La campaña a actualizar
     * @return La campaña actualizada
     */
    Campaign update(Campaign campaign);

    /**
     * Busca una campaña por su ID.
     * Utiliza Named Query: Campaign.findById
     * 
     * @param id El ID de la campaña
     * @return Optional con la campaña encontrada o vacío si no existe
     */
    Optional<Campaign> findById(Long id);

    /**
     * Obtiene todas las campañas ordenadas por fecha de creación descendente con paginación.
     * Utiliza Named Query: Campaign.findAllOrderedByDate
     * 
     * @param offset desplazamiento para paginación
     * @param limit número máximo de resultados
     * @return Lista de campañas paginadas ordenadas por fecha de creación
     */
    List<Campaign> findAll(int offset, int limit);
}
