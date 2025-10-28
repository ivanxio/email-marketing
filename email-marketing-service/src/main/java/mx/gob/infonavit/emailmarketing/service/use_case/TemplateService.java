package mx.gob.infonavit.emailmarketing.service.use_case;

import mx.gob.infonavit.emailmarketing.model.dto.request.TemplateCreateRequest;
import mx.gob.infonavit.emailmarketing.model.dto.request.TemplateUpdateRequest;
import mx.gob.infonavit.emailmarketing.model.dto.response.TemplateResponse;
import mx.gob.infonavit.emailmarketing.service.entity.Template;
import mx.gob.infonavit.emailmarketing.service.exception.InvalidTemplateException;
import mx.gob.infonavit.emailmarketing.service.exception.TemplateNotFoundException;
import mx.gob.infonavit.emailmarketing.service.mapper.TemplateMapper;
import mx.gob.infonavit.emailmarketing.service.repository.ITemplateRepository;
import mx.gob.infonavit.emailmarketing.service.validator.TemplateValidator;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;

/**
 * Implementación del servicio de gestión de plantillas.
 * 
 * @author Ivan Garcia igarciam@desarrollo-ultrasis.com.mx
 * @version 1.0.0
 * @since 2025-10-20
 */
@Stateless
public class TemplateService implements ITemplateService {

    @EJB
    private ITemplateRepository templateRepository;

    @EJB
    private TemplateMapper templateMapper;

    @EJB
    private TemplateValidator templateValidator;

    @Override
    public TemplateResponse createTemplate(TemplateCreateRequest request, Long userId) {
        templateValidator.validateCreateRequest(request);

        Template template = templateMapper.toEntity(request, userId);
        template = templateRepository.save(template);

        return templateMapper.toDto(template);
    }

    @Override
    public TemplateResponse updateTemplate(Long id, TemplateUpdateRequest request) {
        Template template = templateRepository.findById(id)
                .orElseThrow(() -> new TemplateNotFoundException(id));

        templateValidator.validateUpdateRequest(request);

        templateMapper.updateEntityFromRequest(template, request);
        template = templateRepository.update(template);

        return templateMapper.toDto(template);
    }

    @Override
    public TemplateResponse findTemplateById(Long id) {
        Template template = templateRepository.findById(id)
                .orElseThrow(() -> new TemplateNotFoundException(id));
        return templateMapper.toDto(template);
    }

    @Override
    public List<TemplateResponse> findAllTemplates(int page, int size) {
        validatePaginationParameters(page, size);
        int offset = page * size;
        List<Template> templates = templateRepository.findAll(offset, size);
        return templateMapper.toDtoList(templates);
    }

    private void validatePaginationParameters(int page, int size) {
        if (page < 0) {
            throw new InvalidTemplateException("El número de página no puede ser negativo");
        }

        if (size <= 0) {
            throw new InvalidTemplateException("El tamaño de página debe ser mayor a cero");
        }

        if (size > 100) {
            throw new InvalidTemplateException("El tamaño de página no puede exceder 100 elementos");
        }
    }
}

