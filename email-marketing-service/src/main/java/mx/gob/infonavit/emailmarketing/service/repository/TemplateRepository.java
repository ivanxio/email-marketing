package mx.gob.infonavit.emailmarketing.service.repository;

import mx.gob.infonavit.emailmarketing.service.entity.Template;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

/**
 * Implementación del repositorio para operaciones de acceso a datos de plantillas.
 * 
 * @author Ivan Garcia igarciam@desarrollo-ultrasis.com.mx
 * @version 1.0.0
 * @since 2025-10-20
 */
@Stateless
public class TemplateRepository implements ITemplateRepository {

    @PersistenceContext(unitName = "EmailMarketingPU")
    private EntityManager entityManager;

    @Override
    public Template save(Template template) {
        entityManager.persist(template);
        entityManager.flush();
        return template;
    }

    @Override
    public Template update(Template template) {
        return entityManager.merge(template);
    }

    @Override
    public Optional<Template> findById(Long id) {
        Template template = entityManager.find(Template.class, id);
        return Optional.ofNullable(template);
    }

    @Override
    public List<Template> findAll(int offset, int limit) {
        TypedQuery<Template> query = entityManager.createNamedQuery(
            Template.FIND_ALL_ORDERED_BY_DATE,
            Template.class
        );
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }
}