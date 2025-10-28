package mx.gob.infonavit.emailmarketing.service.use_case;

import mx.gob.infonavit.emailmarketing.model.dto.request.CampaignCreateRequest;
import mx.gob.infonavit.emailmarketing.model.dto.request.CampaignUpdateRequest;
import mx.gob.infonavit.emailmarketing.model.dto.response.CampaignResponse;
import mx.gob.infonavit.emailmarketing.service.exception.InvalidCampaignException;
import mx.gob.infonavit.emailmarketing.service.exception.CampaignNotFoundException;

import java.util.List;

/**
 * Interface para el servicio de gestión de campañas de correo electrónico.
 * Define las operaciones de negocio para la gestión de campañas.
 * 
 * @author Ivan Garcia igarciam@desarrollo-ultrasis.com.mx
 * @version 1.0.0
 * @since 2025-10-20
 */
public interface ICampaignService {

    /**
     * Crea una nueva campaña de correo electrónico.
     * CU-009: Crear nueva campaña de correo electrónico
     * 
     * @param request DTO con los datos de la nueva campaña
     * @return DTO de respuesta con la campaña creada
     * @throws InvalidCampaignException Si los datos de entrada son inválidos
     */
    CampaignResponse createCampaign(CampaignCreateRequest request);

    /**
     * Busca una campaña por su ID.
     * CU-010: Buscar campaña de correo electrónico por id
     * 
     * @param campaignId ID de la campaña a buscar
     * @return DTO de respuesta con la campaña encontrada
     * @throws InvalidCampaignException Si el ID es nulo o inválido
     * @throws CampaignNotFoundException Si la campaña no existe
     */
    CampaignResponse findCampaignById(Long campaignId);

    /**
     * Obtiene todas las campañas ordenadas por fecha de creación descendente con paginación.
     * CU-012: Recuperar campañas de correo electrónico ordenado por fecha de creación y paginado
     * 
     * @param page número de página (base 0)
     * @param size tamaño de página (máximo 100)
     * @return Lista de DTOs de respuesta con las campañas paginadas
     * @throws InvalidCampaignException Si los parámetros de paginación son inválidos
     */
    List<CampaignResponse> findAllCampaigns(int page, int size);

    /**
     * Actualiza una campaña existente.
     * CU-013: Una campaña puede actualizarse en estatus borrador o programada antes de la fecha de ejecución, SCHEDULED (Antes de la fecha de ejecución)
     * CU-015: Una campaña completada no puede modificarse
     * 
     * @param campaignId ID de la campaña a actualizar
     * @param request DTO con los datos de actualización
     * @return DTO de respuesta con la campaña actualizada
     * @throws InvalidCampaignException Si los datos de entrada son inválidos o la campaña no puede ser actualizada
     * @throws CampaignNotFoundException Si la campaña no existe
     */
    CampaignResponse updateCampaign(Long campaignId, CampaignUpdateRequest request);

    /**
     * Ejecuta una campaña que está programada o pausada.
     * CU-017: Una campaña programada o pausada puede ejecutarse
     * 
     * @param campaignId ID de la campaña a ejecutar
     * @param updatedBy Usuario que ejecuta la campaña
     * @return DTO de respuesta con la campaña en ejecución
     * @throws InvalidCampaignException Si el ID es nulo o inválido, o la campaña no puede ser ejecutada
     * @throws CampaignNotFoundException Si la campaña no existe
     */
    CampaignResponse runCampaign(Long campaignId, String updatedBy);

    /**
     * Pausa una campaña que está en progreso.
     * CU-014: Una campaña en progreso puede pausarse
     * 
     * @param campaignId ID de la campaña a pausar
     * @param updatedBy Usuario que pausa la campaña
     * @return DTO de respuesta con la campaña pausada
     * @throws InvalidCampaignException Si el ID es nulo o inválido, o la campaña no puede ser pausada
     * @throws CampaignNotFoundException Si la campaña no existe
     */
    CampaignResponse pauseCampaign(Long campaignId, String updatedBy);

    /**
     * Reanuda una campaña que está pausada.
     * CU-016: Una campaña pausada puede reanudarse
     * 
     * @param campaignId ID de la campaña a reanudar
     * @param updatedBy Usuario que reanuda la campaña
     * @return DTO de respuesta con la campaña reanudada
     * @throws InvalidCampaignException Si el ID es nulo o inválido, o la campaña no puede ser reanudada
     * @throws CampaignNotFoundException Si la campaña no existe
     */
    CampaignResponse resumeCampaign(Long campaignId, String updatedBy);
}
