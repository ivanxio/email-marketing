package mx.gob.infonavit.emailmarketing.web.rest;

import io.swagger.annotations.*;
import mx.gob.infonavit.emailmarketing.model.dto.request.CampaignCreateRequest;
import mx.gob.infonavit.emailmarketing.model.dto.request.CampaignUpdateRequest;
import mx.gob.infonavit.emailmarketing.model.dto.response.CampaignResponse;
import mx.gob.infonavit.emailmarketing.model.dto.response.ErrorResponse;
import mx.gob.infonavit.emailmarketing.service.exception.CampaignNotFoundException;
import mx.gob.infonavit.emailmarketing.service.exception.InvalidCampaignException;
import mx.gob.infonavit.emailmarketing.service.use_case.ICampaignService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Controlador REST para la gestión de campañas de correo electrónico.
 * Implementa todos los endpoints definidos en los casos de uso del módulo de campañas.
 * 
 * Casos de Uso implementados:
 * - CU-009: Crear nueva campaña de correo electrónico
 * - CU-010: Buscar campaña de correo electrónico por id
 * - CU-012: Recuperar campañas de correo electrónico ordenado por fecha de creación y paginado
 * - CU-013: Una campaña puede actualizarse en estatus DRAFT, SCHEDULED (Antes de la fecha de ejecución)
 * - CU-014: Una campaña IN_PROGRESS puede pausarse
 * - CU-015: Una campaña COMPLETED no puede modificarse
 * - CU-016: Una campaña PAUSED puede ser reanudada
 * 
 * @author Ivan Garcia igarciam@desarrollo-ultrasis.com.mx
 * @version 1.0.0
 * @since 2025-10-20
 */
@Path("/campaigns")
@Api(value = "/campaigns", description = "Gestión de Campañas de Correo Electrónico")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CampaignController {

    @EJB
    private ICampaignService campaignService;

    @POST
    @Path("/create")
    @ApiOperation(
        value = "Crear nueva campaña",
        notes = "CU-009: Crea una nueva campaña de correo electrónico con estado DRAFT",
        response = CampaignResponse.class
    )
    @ApiResponses({
        @ApiResponse(code = 201, message = "Campaña creada exitosamente"),
        @ApiResponse(code = 400, message = "Datos inválidos"),
        @ApiResponse(code = 500, message = "Error interno del servidor")
    })
    public Response createCampaign(
            @ApiParam(value = "Datos de la nueva campaña", required = true) 
            CampaignCreateRequest request,
            @ApiParam(value = "Usuario que crea la campaña", required = true)
            @HeaderParam("X-User") String createdBy) {
        
        request.setCreatedBy(createdBy);
        
        try {
            CampaignResponse response = campaignService.createCampaign(request);
            return Response.status(Response.Status.CREATED)
                    .entity(response)
                    .build();
        } catch (InvalidCampaignException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(ErrorResponse.validationError(e.getMessage()))
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ErrorResponse.internalServerError("Error interno del servidor"))
                    .build();
        }
    }

    @GET
    @Path("/{campaignId}")
    @ApiOperation(
        value = "Buscar campaña por ID",
        notes = "CU-010: Obtiene una campaña específica por su identificador único",
        response = CampaignResponse.class
    )
    @ApiResponses({
        @ApiResponse(code = 200, message = "Campaña encontrada exitosamente"),
        @ApiResponse(code = 400, message = "ID inválido"),
        @ApiResponse(code = 404, message = "Campaña no encontrada"),
        @ApiResponse(code = 500, message = "Error interno del servidor")
    })
    public Response findCampaignById(
            @ApiParam(value = "ID de la campaña", required = true, example = "1")
            @PathParam("campaignId") Long campaignId) {
        
        try {
            CampaignResponse response = campaignService.findCampaignById(campaignId);
            return Response.ok(response).build();
        } catch (InvalidCampaignException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(ErrorResponse.validationError(e.getMessage()))
                    .build();
        } catch (CampaignNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ErrorResponse.notFound(e.getMessage()))
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ErrorResponse.internalServerError("Error interno del servidor"))
                    .build();
        }
    }

    @GET
    @ApiOperation(
        value = "Obtener todas las campañas",
        notes = "CU-012: Obtiene todas las campañas ordenadas por fecha de creación descendente con paginación",
        response = CampaignResponse.class,
        responseContainer = "List"
    )
    @ApiResponses({
        @ApiResponse(code = 200, message = "Campañas obtenidas exitosamente"),
        @ApiResponse(code = 400, message = "Parámetros de paginación inválidos"),
        @ApiResponse(code = 500, message = "Error interno del servidor")
    })
    public Response findAllCampaigns(
            @ApiParam(value = "Número de página (base 0)", defaultValue = "0") 
            @QueryParam("page") @DefaultValue("0") int page,
            @ApiParam(value = "Tamaño de página (máximo 100)", defaultValue = "10") 
            @QueryParam("size") @DefaultValue("10") int size) {
        
        try {
            List<CampaignResponse> responses = campaignService.findAllCampaigns(page, size);
            return Response.ok(responses).build();
        } catch (InvalidCampaignException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(ErrorResponse.validationError(e.getMessage()))
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ErrorResponse.internalServerError("Error interno del servidor"))
                    .build();
        }
    }

    @PUT
    @Path("/{campaignId}")
    @ApiOperation(
        value = "Actualizar campaña",
        notes = "CU-013: Actualiza una campaña existente. Solo permite actualización en estados borrador o programada antes de la fecha de ejecución. Las campañas completadas no pueden modificarse.",
        response = CampaignResponse.class
    )
    @ApiResponses({
        @ApiResponse(code = 200, message = "Campaña actualizada exitosamente"),
        @ApiResponse(code = 400, message = "Datos inválidos o campaña no puede ser actualizada"),
        @ApiResponse(code = 404, message = "Campaña no encontrada"),
        @ApiResponse(code = 500, message = "Error interno del servidor")
    })
    public Response updateCampaign(
            @ApiParam(value = "ID de la campaña", required = true, example = "1")
            @PathParam("campaignId") Long campaignId,
            @ApiParam(value = "Datos de actualización de la campaña", required = true)
            CampaignUpdateRequest request,
            @ApiParam(value = "Usuario que actualiza la campaña", required = true)
            @HeaderParam("X-User") String updatedBy) {
        
        request.setUpdatedBy(updatedBy);
        
        try {
            CampaignResponse response = campaignService.updateCampaign(campaignId, request);
            return Response.ok(response).build();
        } catch (InvalidCampaignException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(ErrorResponse.validationError(e.getMessage()))
                    .build();
        } catch (CampaignNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ErrorResponse.notFound(e.getMessage()))
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ErrorResponse.internalServerError("Error interno del servidor"))
                    .build();
        }
    }

    @POST
    @Path("/{campaignId}/run")
    @ApiOperation(
        value = "Ejecutar campaña",
        notes = "CU-017: Ejecuta una campaña que está programada o pausada",
        response = CampaignResponse.class
    )
    @ApiResponses({
        @ApiResponse(code = 200, message = "Campaña ejecutada exitosamente"),
        @ApiResponse(code = 400, message = "Campaña no puede ser ejecutada"),
        @ApiResponse(code = 404, message = "Campaña no encontrada"),
        @ApiResponse(code = 500, message = "Error interno del servidor")
    })
    public Response runCampaign(
            @ApiParam(value = "ID de la campaña", required = true, example = "1")
            @PathParam("campaignId") Long campaignId,
            @ApiParam(value = "Usuario que ejecuta la campaña", required = true)
            @HeaderParam("X-User") String updatedBy) {
        
        try {
            CampaignResponse response = campaignService.runCampaign(campaignId, updatedBy);
            return Response.ok(response).build();
        } catch (InvalidCampaignException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(ErrorResponse.validationError(e.getMessage()))
                    .build();
        } catch (CampaignNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ErrorResponse.notFound(e.getMessage()))
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ErrorResponse.internalServerError("Error interno del servidor"))
                    .build();
        }
    }

    @POST
    @Path("/{campaignId}/pause")
    @ApiOperation(
        value = "Pausar campaña",
        notes = "CU-014: Pausa una campaña que esta en progreso",
        response = CampaignResponse.class
    )
    @ApiResponses({
        @ApiResponse(code = 200, message = "Campaña pausada exitosamente"),
        @ApiResponse(code = 400, message = "Campaña no puede ser pausada"),
        @ApiResponse(code = 404, message = "Campaña no encontrada"),
        @ApiResponse(code = 500, message = "Error interno del servidor")
    })
    public Response pauseCampaign(
            @ApiParam(value = "ID de la campaña", required = true, example = "1")
            @PathParam("campaignId") Long campaignId,
            @ApiParam(value = "Usuario que pausa la campaña", required = true)
            @HeaderParam("X-User") String updatedBy) {
        
        try {
            CampaignResponse response = campaignService.pauseCampaign(campaignId, updatedBy);
            return Response.ok(response).build();
        } catch (InvalidCampaignException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(ErrorResponse.validationError(e.getMessage()))
                    .build();
        } catch (CampaignNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ErrorResponse.notFound(e.getMessage()))
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ErrorResponse.internalServerError("Error interno del servidor"))
                    .build();
        }
    }

    @POST
    @Path("/{campaignId}/resume")
    @ApiOperation(
        value = "Reanudar campaña",
        notes = "CU-016: Reanuda una campaña que esta pausada",
        response = CampaignResponse.class
    )
    @ApiResponses({
        @ApiResponse(code = 200, message = "Campaña reanudada exitosamente"),
        @ApiResponse(code = 400, message = "Campaña no puede ser reanudada"),
        @ApiResponse(code = 404, message = "Campaña no encontrada"),
        @ApiResponse(code = 500, message = "Error interno del servidor")
    })
    public Response resumeCampaign(
            @ApiParam(value = "ID de la campaña", required = true, example = "1")
            @PathParam("campaignId") Long campaignId,
            @ApiParam(value = "Usuario que reanuda la campaña", required = true)
            @HeaderParam("X-User") String updatedBy) {
        
        try {
            CampaignResponse response = campaignService.resumeCampaign(campaignId, updatedBy);
            return Response.ok(response).build();
        } catch (InvalidCampaignException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(ErrorResponse.validationError(e.getMessage()))
                    .build();
        } catch (CampaignNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ErrorResponse.notFound(e.getMessage()))
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ErrorResponse.internalServerError("Error interno del servidor"))
                    .build();
        }
    }
}
