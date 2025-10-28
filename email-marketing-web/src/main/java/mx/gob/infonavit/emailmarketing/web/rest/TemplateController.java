package mx.gob.infonavit.emailmarketing.web.rest;

import io.swagger.annotations.*;
import mx.gob.infonavit.emailmarketing.model.dto.request.TemplateCreateRequest;
import mx.gob.infonavit.emailmarketing.model.dto.request.TemplateUpdateRequest;
import mx.gob.infonavit.emailmarketing.model.dto.response.TemplateResponse;
import mx.gob.infonavit.emailmarketing.model.dto.response.ErrorResponse;
import mx.gob.infonavit.emailmarketing.service.exception.InvalidTemplateException;
import mx.gob.infonavit.emailmarketing.service.exception.TemplateNotFoundException;
import mx.gob.infonavit.emailmarketing.service.use_case.ITemplateService;

import javax.ejb.EJB;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

/**
 * Controlador REST para la gestión de plantillas de correo electrónico.
 * 
 * Implementa los casos de uso del módulo de gestión de plantillas.
 * 
 * Casos de Uso implementados:
 * - CU-017: Crear plantilla de correo electrónico HTML
 * - CU-018: Buscar plantilla por id
 * - CU-019: Recuperar plantillas paginadas por fecha de creación
 * - CU-020: Editar plantilla de correo electrónico HTML
 * 
 * @author Ivan Garcia igarciam@desarrollo-ultrasis.com.mx
 * @version 1.0.0
 * @since 2025-10-20
 */
@Api(value = "Gestión de Plantillas", tags = "Templates")
@Path("/templates")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TemplateController {

    @EJB
    private ITemplateService templateService;

    @POST
    @ApiOperation(
        value = "Crear plantilla de correo electrónico", 
        notes = "CU-017: Crea una nueva plantilla HTML reutilizable para campañas",
        response = TemplateResponse.class
    )
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Plantilla creada exitosamente", response = TemplateResponse.class),
        @ApiResponse(code = 400, message = "Datos de entrada inválidos"),
        @ApiResponse(code = 401, message = "Usuario no autenticado"),
        @ApiResponse(code = 500, message = "Error interno del servidor")
    })
    public Response createTemplate(
            @ApiParam(value = "Datos de la plantilla a crear", required = true) 
            @Valid TemplateCreateRequest request,
            @ApiParam(value = "ID del usuario autenticado", required = true) 
            @HeaderParam("X-User-Id") Long userId) {
        
        try {
            if (userId == null) {
                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity(ErrorResponse.badRequest("Usuario no autenticado"))
                        .build();
            }

            TemplateResponse template = templateService.createTemplate(request, userId);
            return Response.ok(template).build();

        } catch (InvalidTemplateException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(ErrorResponse.validationError(e.getMessage()))
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ErrorResponse.internalServerError("Error interno al crear la plantilla"))
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    @ApiOperation(
        value = "Buscar plantilla por ID",
        notes = "CU-018: Obtiene una plantilla específica por su identificador",
        response = TemplateResponse.class
    )
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Plantilla encontrada", response = TemplateResponse.class),
        @ApiResponse(code = 404, message = "Plantilla no encontrada"),
        @ApiResponse(code = 500, message = "Error interno del servidor")
    })
    public Response findTemplateById(
            @ApiParam(value = "ID de la plantilla", required = true, example = "1") 
            @PathParam("id") Long id) {
        try {
            TemplateResponse template = templateService.findTemplateById(id);
            return Response.ok(template).build();

        } catch (TemplateNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ErrorResponse.notFound(e.getMessage()))
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ErrorResponse.internalServerError("Error interno al obtener la plantilla"))
                    .build();
        }
    }

    @GET
    @ApiOperation(
        value = "Listar plantillas paginadas",
        notes = "CU-019: Recupera plantillas ordenadas por fecha de creación con paginación",
        response = TemplateResponse.class,
        responseContainer = "List"
    )
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Lista de plantillas obtenida exitosamente"),
        @ApiResponse(code = 400, message = "Parámetros de paginación inválidos"),
        @ApiResponse(code = 500, message = "Error interno del servidor")
    })
    public Response findAllTemplates(
            @ApiParam(value = "Número de página (base 0)", defaultValue = "0") 
            @QueryParam("page") @DefaultValue("0") int page,
            @ApiParam(value = "Tamaño de página (máximo 100)", defaultValue = "10") 
            @QueryParam("size") @DefaultValue("10") int size) {
        
        try {
            List<TemplateResponse> templates = templateService.findAllTemplates(page, size);
            return Response.ok(templates).build();

        } catch (InvalidTemplateException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(ErrorResponse.validationError(e.getMessage()))
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ErrorResponse.internalServerError("Error interno al obtener plantillas"))
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    @ApiOperation(
        value = "Actualizar plantilla",
        notes = "CU-020: Edita una plantilla existente. Solo se actualizan los campos proporcionados (actualización parcial)",
        response = TemplateResponse.class
    )
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Plantilla actualizada exitosamente", response = TemplateResponse.class),
        @ApiResponse(code = 400, message = "Datos de entrada inválidos"),
        @ApiResponse(code = 404, message = "Plantilla no encontrada"),
        @ApiResponse(code = 500, message = "Error interno del servidor")
    })
    public Response updateTemplate(
            @ApiParam(value = "ID de la plantilla a actualizar", required = true, example = "1") 
            @PathParam("id") Long id,
            @ApiParam(value = "Datos de actualización (campos opcionales)", required = true) 
            @Valid TemplateUpdateRequest request) {
        
        try {
            TemplateResponse template = templateService.updateTemplate(id, request);
            return Response.ok(template).build();

        } catch (TemplateNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ErrorResponse.notFound(e.getMessage()))
                    .build();
        } catch (InvalidTemplateException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(ErrorResponse.validationError(e.getMessage()))
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ErrorResponse.internalServerError("Error interno al actualizar la plantilla"))
                    .build();
        }
    }
}
