package mx.gob.infonavit.emailmarketing.service.mapper;

import mx.gob.infonavit.emailmarketing.model.dto.request.TemplateCreateRequest;
import mx.gob.infonavit.emailmarketing.model.dto.request.TemplateUpdateRequest;
import mx.gob.infonavit.emailmarketing.model.dto.response.TemplateResponse;
import mx.gob.infonavit.emailmarketing.service.entity.Template;

import javax.ejb.Stateless;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper para convertir entre entidades Template y DTOs.
 * 
 * Implementa el patrón Mapper para separar el modelo de dominio (entidades)
 * del modelo de transferencia de datos (DTOs).
 * 
 * @author Ivan Garcia igarciam@desarrollo-ultrasis.com.mx
 * @version 1.0.0
 * @since 2025-10-20
 */
@Stateless
public class TemplateMapper {

    public Template toEntity(TemplateCreateRequest request, Long createdBy) {
        if (request == null) {
            return null;
        }

        Template template = new Template();
        template.setName(request.getName());
        template.setDescription(request.getDescription());
        template.setHtmlContent(request.getHtmlContent());
        template.setCreatedBy(createdBy);
        template.setCreatedAt(LocalDateTime.now());
        template.setUpdatedAt(LocalDateTime.now());
        template.setIsActive(Boolean.TRUE);

        return template;
    }

    public void updateEntityFromRequest(Template template, TemplateUpdateRequest request) {
        if (template == null || request == null) {
            return;
        }

        if (request.getName() != null) {
            template.setName(request.getName());
        }

        if (request.getDescription() != null) {
            template.setDescription(request.getDescription());
        }

        if (request.getHtmlContent() != null) {
            template.setHtmlContent(request.getHtmlContent());
        }

        if (request.getIsActive() != null) {
            template.setIsActive(request.getIsActive());
        }

        template.setUpdatedAt(LocalDateTime.now());
    }

    public TemplateResponse toDto(Template template) {
        if (template == null) {
            return new TemplateResponse();
        }

        TemplateResponse response = new TemplateResponse();
        response.setId(template.getId());
        response.setName(template.getName());
        response.setDescription(template.getDescription());
        response.setHtmlContent(template.getHtmlContent());
        response.setCreatedBy(template.getCreatedBy());
        response.setIsActive(template.getIsActive());
        response.setCreatedAt(template.getCreatedAt());
        response.setUpdatedAt(template.getUpdatedAt());

        return response;
    }

    public List<TemplateResponse> toDtoList(List<Template> templates) {
        if (templates == null || templates.isEmpty()) {
            return Collections.emptyList();
        }

        return templates.stream()
                .map(template -> this.toDto(template))
                .collect(java.util.stream.Collectors.toList());
    }
}

