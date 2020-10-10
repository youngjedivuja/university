/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.trgovinavujovic.controller;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import rs.trgovinavujovic.entities.Detaljiporudzbine;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import rs.trgovinavujovic.controller.exceptions.IllegalOrphanException;
import rs.trgovinavujovic.controller.exceptions.NonexistentEntityException;
import rs.trgovinavujovic.controller.exceptions.RollbackFailureException;
import rs.trgovinavujovic.entities.Proizvod;

/**
 *
 * @author Jovan Vujovic
 */
public class ProizvodJpaController implements Serializable {

    public ProizvodJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Proizvod proizvod) throws RollbackFailureException, Exception {
        if (proizvod.getDetaljiporudzbineList() == null) {
            proizvod.setDetaljiporudzbineList(new ArrayList<Detaljiporudzbine>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            List<Detaljiporudzbine> attachedDetaljiporudzbineList = new ArrayList<Detaljiporudzbine>();
            for (Detaljiporudzbine detaljiporudzbineListDetaljiporudzbineToAttach : proizvod.getDetaljiporudzbineList()) {
                detaljiporudzbineListDetaljiporudzbineToAttach = em.getReference(detaljiporudzbineListDetaljiporudzbineToAttach.getClass(), detaljiporudzbineListDetaljiporudzbineToAttach.getDetaljiPorudzbineId());
                attachedDetaljiporudzbineList.add(detaljiporudzbineListDetaljiporudzbineToAttach);
            }
            proizvod.setDetaljiporudzbineList(attachedDetaljiporudzbineList);
            em.persist(proizvod);
            for (Detaljiporudzbine detaljiporudzbineListDetaljiporudzbine : proizvod.getDetaljiporudzbineList()) {
                Proizvod oldProizvodIdOfDetaljiporudzbineListDetaljiporudzbine = detaljiporudzbineListDetaljiporudzbine.getProizvodId();
                detaljiporudzbineListDetaljiporudzbine.setProizvodId(proizvod);
                detaljiporudzbineListDetaljiporudzbine = em.merge(detaljiporudzbineListDetaljiporudzbine);
                if (oldProizvodIdOfDetaljiporudzbineListDetaljiporudzbine != null) {
                    oldProizvodIdOfDetaljiporudzbineListDetaljiporudzbine.getDetaljiporudzbineList().remove(detaljiporudzbineListDetaljiporudzbine);
                    oldProizvodIdOfDetaljiporudzbineListDetaljiporudzbine = em.merge(oldProizvodIdOfDetaljiporudzbineListDetaljiporudzbine);
                }
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

    public void edit(Proizvod proizvod) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Proizvod persistentProizvod = em.find(Proizvod.class, proizvod.getProizvodId());
            List<Detaljiporudzbine> detaljiporudzbineListOld = persistentProizvod.getDetaljiporudzbineList();
            List<Detaljiporudzbine> detaljiporudzbineListNew = proizvod.getDetaljiporudzbineList();
            List<String> illegalOrphanMessages = null;
            for (Detaljiporudzbine detaljiporudzbineListOldDetaljiporudzbine : detaljiporudzbineListOld) {
                if (!detaljiporudzbineListNew.contains(detaljiporudzbineListOldDetaljiporudzbine)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detaljiporudzbine " + detaljiporudzbineListOldDetaljiporudzbine + " since its proizvodId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Detaljiporudzbine> attachedDetaljiporudzbineListNew = new ArrayList<Detaljiporudzbine>();
            for (Detaljiporudzbine detaljiporudzbineListNewDetaljiporudzbineToAttach : detaljiporudzbineListNew) {
                detaljiporudzbineListNewDetaljiporudzbineToAttach = em.getReference(detaljiporudzbineListNewDetaljiporudzbineToAttach.getClass(), detaljiporudzbineListNewDetaljiporudzbineToAttach.getDetaljiPorudzbineId());
                attachedDetaljiporudzbineListNew.add(detaljiporudzbineListNewDetaljiporudzbineToAttach);
            }
            detaljiporudzbineListNew = attachedDetaljiporudzbineListNew;
            proizvod.setDetaljiporudzbineList(detaljiporudzbineListNew);
            proizvod = em.merge(proizvod);
            for (Detaljiporudzbine detaljiporudzbineListNewDetaljiporudzbine : detaljiporudzbineListNew) {
                if (!detaljiporudzbineListOld.contains(detaljiporudzbineListNewDetaljiporudzbine)) {
                    Proizvod oldProizvodIdOfDetaljiporudzbineListNewDetaljiporudzbine = detaljiporudzbineListNewDetaljiporudzbine.getProizvodId();
                    detaljiporudzbineListNewDetaljiporudzbine.setProizvodId(proizvod);
                    detaljiporudzbineListNewDetaljiporudzbine = em.merge(detaljiporudzbineListNewDetaljiporudzbine);
                    if (oldProizvodIdOfDetaljiporudzbineListNewDetaljiporudzbine != null && !oldProizvodIdOfDetaljiporudzbineListNewDetaljiporudzbine.equals(proizvod)) {
                        oldProizvodIdOfDetaljiporudzbineListNewDetaljiporudzbine.getDetaljiporudzbineList().remove(detaljiporudzbineListNewDetaljiporudzbine);
                        oldProizvodIdOfDetaljiporudzbineListNewDetaljiporudzbine = em.merge(oldProizvodIdOfDetaljiporudzbineListNewDetaljiporudzbine);
                    }
                }
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
                Integer id = proizvod.getProizvodId();
                if (findProizvod(id) == null) {
                    throw new NonexistentEntityException("The proizvod with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Proizvod proizvod;
            try {
                proizvod = em.getReference(Proizvod.class, id);
                proizvod.getProizvodId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The proizvod with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Detaljiporudzbine> detaljiporudzbineListOrphanCheck = proizvod.getDetaljiporudzbineList();
            for (Detaljiporudzbine detaljiporudzbineListOrphanCheckDetaljiporudzbine : detaljiporudzbineListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Proizvod (" + proizvod + ") cannot be destroyed since the Detaljiporudzbine " + detaljiporudzbineListOrphanCheckDetaljiporudzbine + " in its detaljiporudzbineList field has a non-nullable proizvodId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(proizvod);
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

    public List<Proizvod> findProizvodEntities() {
        return findProizvodEntities(true, -1, -1);
    }

    public List<Proizvod> findProizvodEntities(int maxResults, int firstResult) {
        return findProizvodEntities(false, maxResults, firstResult);
    }

    private List<Proizvod> findProizvodEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Proizvod.class));
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

    public Proizvod findProizvod(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Proizvod.class, id);
        } finally {
            em.close();
        }
    }

    public int getProizvodCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Proizvod> rt = cq.from(Proizvod.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
