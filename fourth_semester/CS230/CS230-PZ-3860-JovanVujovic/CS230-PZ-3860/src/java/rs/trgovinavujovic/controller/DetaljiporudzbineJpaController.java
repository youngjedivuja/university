/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.trgovinavujovic.controller;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;
import rs.trgovinavujovic.controller.exceptions.NonexistentEntityException;
import rs.trgovinavujovic.controller.exceptions.RollbackFailureException;
import rs.trgovinavujovic.entities.Detaljiporudzbine;
import rs.trgovinavujovic.entities.Porudzbina;
import rs.trgovinavujovic.entities.Proizvod;

/**
 *
 * @author Jovan Vujovic
 */
public class DetaljiporudzbineJpaController implements Serializable {

    public DetaljiporudzbineJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detaljiporudzbine detaljiporudzbine) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Porudzbina porudzbinaId = detaljiporudzbine.getPorudzbinaId();
            if (porudzbinaId != null) {
                porudzbinaId = em.getReference(porudzbinaId.getClass(), porudzbinaId.getPorudzbinaId());
                detaljiporudzbine.setPorudzbinaId(porudzbinaId);
            }
            Proizvod proizvodId = detaljiporudzbine.getProizvodId();
            if (proizvodId != null) {
                proizvodId = em.getReference(proizvodId.getClass(), proizvodId.getProizvodId());
                detaljiporudzbine.setProizvodId(proizvodId);
            }
            em.persist(detaljiporudzbine);
            if (porudzbinaId != null) {
                porudzbinaId.getDetaljiporudzbineList().add(detaljiporudzbine);
                porudzbinaId = em.merge(porudzbinaId);
            }
            if (proizvodId != null) {
                proizvodId.getDetaljiporudzbineList().add(detaljiporudzbine);
                proizvodId = em.merge(proizvodId);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detaljiporudzbine detaljiporudzbine) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Detaljiporudzbine persistentDetaljiporudzbine = em.find(Detaljiporudzbine.class, detaljiporudzbine.getDetaljiPorudzbineId());
            Porudzbina porudzbinaIdOld = persistentDetaljiporudzbine.getPorudzbinaId();
            Porudzbina porudzbinaIdNew = detaljiporudzbine.getPorudzbinaId();
            Proizvod proizvodIdOld = persistentDetaljiporudzbine.getProizvodId();
            Proizvod proizvodIdNew = detaljiporudzbine.getProizvodId();
            if (porudzbinaIdNew != null) {
                porudzbinaIdNew = em.getReference(porudzbinaIdNew.getClass(), porudzbinaIdNew.getPorudzbinaId());
                detaljiporudzbine.setPorudzbinaId(porudzbinaIdNew);
            }
            if (proizvodIdNew != null) {
                proizvodIdNew = em.getReference(proizvodIdNew.getClass(), proizvodIdNew.getProizvodId());
                detaljiporudzbine.setProizvodId(proizvodIdNew);
            }
            detaljiporudzbine = em.merge(detaljiporudzbine);
            if (porudzbinaIdOld != null && !porudzbinaIdOld.equals(porudzbinaIdNew)) {
                porudzbinaIdOld.getDetaljiporudzbineList().remove(detaljiporudzbine);
                porudzbinaIdOld = em.merge(porudzbinaIdOld);
            }
            if (porudzbinaIdNew != null && !porudzbinaIdNew.equals(porudzbinaIdOld)) {
                porudzbinaIdNew.getDetaljiporudzbineList().add(detaljiporudzbine);
                porudzbinaIdNew = em.merge(porudzbinaIdNew);
            }
            if (proizvodIdOld != null && !proizvodIdOld.equals(proizvodIdNew)) {
                proizvodIdOld.getDetaljiporudzbineList().remove(detaljiporudzbine);
                proizvodIdOld = em.merge(proizvodIdOld);
            }
            if (proizvodIdNew != null && !proizvodIdNew.equals(proizvodIdOld)) {
                proizvodIdNew.getDetaljiporudzbineList().add(detaljiporudzbine);
                proizvodIdNew = em.merge(proizvodIdNew);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = detaljiporudzbine.getDetaljiPorudzbineId();
                if (findDetaljiporudzbine(id) == null) {
                    throw new NonexistentEntityException("The detaljiporudzbine with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Detaljiporudzbine detaljiporudzbine;
            try {
                detaljiporudzbine = em.getReference(Detaljiporudzbine.class, id);
                detaljiporudzbine.getDetaljiPorudzbineId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detaljiporudzbine with id " + id + " no longer exists.", enfe);
            }
            Porudzbina porudzbinaId = detaljiporudzbine.getPorudzbinaId();
            if (porudzbinaId != null) {
                porudzbinaId.getDetaljiporudzbineList().remove(detaljiporudzbine);
                porudzbinaId = em.merge(porudzbinaId);
            }
            Proizvod proizvodId = detaljiporudzbine.getProizvodId();
            if (proizvodId != null) {
                proizvodId.getDetaljiporudzbineList().remove(detaljiporudzbine);
                proizvodId = em.merge(proizvodId);
            }
            em.remove(detaljiporudzbine);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detaljiporudzbine> findDetaljiporudzbineEntities() {
        return findDetaljiporudzbineEntities(true, -1, -1);
    }

    public List<Detaljiporudzbine> findDetaljiporudzbineEntities(int maxResults, int firstResult) {
        return findDetaljiporudzbineEntities(false, maxResults, firstResult);
    }

    private List<Detaljiporudzbine> findDetaljiporudzbineEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detaljiporudzbine.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Detaljiporudzbine findDetaljiporudzbine(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detaljiporudzbine.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetaljiporudzbineCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detaljiporudzbine> rt = cq.from(Detaljiporudzbine.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
