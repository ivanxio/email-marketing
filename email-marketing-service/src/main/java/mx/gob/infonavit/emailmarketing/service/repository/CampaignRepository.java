package mx.gob.infonavit.emailmarketing.service.repository;

import mx.gob.infonavit.emailmarketing.service.entity.Campaign;         

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

/**
 * Implementación del repositorio de campañas de correo electrónico.
 * 
 * @author Ivan Garcia igarciam@desarrollo-ultrasis.com.mx
 * @version 1.0.0
 * @since 2025-10-23
 */
@Stateless
public class CampaignRepository implements ICampaignRepository {

    @PersistenceContext(unitName = "EmailMarketingPU")
    private EntityManager entityManager;

    @Override
    public Campaign save(Campaign campaign) {
        entityManager.persist(campaign);
        entityManager.flush();
        return campaign;
    }

    @Override
    public Campaign update(Campaign campaign) {
        return entityManager.merge(campaign);
    }

    @Override
    public Optional<Campaign> findById(Long id) {
        Campaign campaign = entityManager.find(Campaign.class, id);
        return Optional.ofNullable(campaign);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Campaign> findAll(int offset, int limit) {
        Query query = entityManager.createNamedQuery(Campaign.FIND_ALL_ORDERED_BY_CREATED_AT);
        query.setFirstResult(offset);
        query.setMaxResults(limit);        
        List<Campaign> campaigns = query.getResultList();
        return campaigns;
    }
}
