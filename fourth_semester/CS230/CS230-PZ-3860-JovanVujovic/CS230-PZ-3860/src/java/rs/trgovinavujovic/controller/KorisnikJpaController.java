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
import rs.trgovinavujovic.entities.Korisnik;
import rs.trgovinavujovic.entities.Rola;

/**
 *
 * @author Jovan Vujovic
 */
public class KorisnikJpaController implements Serializable {

    public KorisnikJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Korisnik korisnik) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Rola rolaId = korisnik.getRolaId();
            if (rolaId != null) {
                rolaId = em.getReference(rolaId.getClass(), rolaId.getRolaId());
                korisnik.setRolaId(rolaId);
            }
            em.persist(korisnik);
            if (rolaId != null) {
                rolaId.getKorisnikList().add(korisnik);
                rolaId = em.merge(rolaId);
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

    public void edit(Korisnik korisnik) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Korisnik persistentKorisnik = em.find(Korisnik.class, korisnik.getUserId());
            Rola rolaIdOld = persistentKorisnik.getRolaId();
            Rola rolaIdNew = korisnik.getRolaId();
            if (rolaIdNew != null) {
                rolaIdNew = em.getReference(rolaIdNew.getClass(), rolaIdNew.getRolaId());
                korisnik.setRolaId(rolaIdNew);
            }
            korisnik = em.merge(korisnik);
            if (rolaIdOld != null && !rolaIdOld.equals(rolaIdNew)) {
                rolaIdOld.getKorisnikList().remove(korisnik);
                rolaIdOld = em.merge(rolaIdOld);
            }
            if (rolaIdNew != null && !rolaIdNew.equals(rolaIdOld)) {
                rolaIdNew.getKorisnikList().add(korisnik);
                rolaIdNew = em.merge(rolaIdNew);
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
                Integer id = korisnik.getUserId();
                if (findKorisnik(id) == null) {
                    throw new NonexistentEntityException("The korisnik with id " + id + " no longer exists.");
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
            Korisnik korisnik;
            try {
                korisnik = em.getReference(Korisnik.class, id);
                korisnik.getUserId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The korisnik with id " + id + " no longer exists.", enfe);
            }
            Rola rolaId = korisnik.getRolaId();
            if (rolaId != null) {
                rolaId.getKorisnikList().remove(korisnik);
                rolaId = em.merge(rolaId);
            }
            em.remove(korisnik);
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

    public List<Korisnik> findKorisnikEntities() {
        return findKorisnikEntities(true, -1, -1);
    }

    public List<Korisnik> findKorisnikEntities(int maxResults, int firstResult) {
        return findKorisnikEntities(false, maxResults, firstResult);
    }

    private List<Korisnik> findKorisnikEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Korisnik.class));
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

    public Korisnik findKorisnik(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Korisnik.class, id);
        } finally {
            em.close();
        }
    }

    public int getKorisnikCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Korisnik> rt = cq.from(Korisnik.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
