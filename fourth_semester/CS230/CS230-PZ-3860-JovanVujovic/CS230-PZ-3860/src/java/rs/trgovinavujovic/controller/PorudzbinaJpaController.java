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
import rs.trgovinavujovic.entities.Kupac;
import rs.trgovinavujovic.entities.Detaljiporudzbine;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import rs.trgovinavujovic.controller.exceptions.IllegalOrphanException;
import rs.trgovinavujovic.controller.exceptions.NonexistentEntityException;
import rs.trgovinavujovic.controller.exceptions.RollbackFailureException;
import rs.trgovinavujovic.entities.Porudzbina;

/**
 *
 * @author Jovan Vujovic
 */
public class PorudzbinaJpaController implements Serializable {

    public PorudzbinaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Porudzbina porudzbina) throws RollbackFailureException, Exception {
        if (porudzbina.getDetaljiporudzbineList() == null) {
            porudzbina.setDetaljiporudzbineList(new ArrayList<Detaljiporudzbine>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Kupac kupacId = porudzbina.getKupacId();
            if (kupacId != null) {
                kupacId = em.getReference(kupacId.getClass(), kupacId.getKupacId());
                porudzbina.setKupacId(kupacId);
            }
            List<Detaljiporudzbine> attachedDetaljiporudzbineList = new ArrayList<Detaljiporudzbine>();
            for (Detaljiporudzbine detaljiporudzbineListDetaljiporudzbineToAttach : porudzbina.getDetaljiporudzbineList()) {
                detaljiporudzbineListDetaljiporudzbineToAttach = em.getReference(detaljiporudzbineListDetaljiporudzbineToAttach.getClass(), detaljiporudzbineListDetaljiporudzbineToAttach.getDetaljiPorudzbineId());
                attachedDetaljiporudzbineList.add(detaljiporudzbineListDetaljiporudzbineToAttach);
            }
            porudzbina.setDetaljiporudzbineList(attachedDetaljiporudzbineList);
            em.persist(porudzbina);
            if (kupacId != null) {
                kupacId.getPorudzbinaList().add(porudzbina);
                kupacId = em.merge(kupacId);
            }
            for (Detaljiporudzbine detaljiporudzbineListDetaljiporudzbine : porudzbina.getDetaljiporudzbineList()) {
                Porudzbina oldPorudzbinaIdOfDetaljiporudzbineListDetaljiporudzbine = detaljiporudzbineListDetaljiporudzbine.getPorudzbinaId();
                detaljiporudzbineListDetaljiporudzbine.setPorudzbinaId(porudzbina);
                detaljiporudzbineListDetaljiporudzbine = em.merge(detaljiporudzbineListDetaljiporudzbine);
                if (oldPorudzbinaIdOfDetaljiporudzbineListDetaljiporudzbine != null) {
                    oldPorudzbinaIdOfDetaljiporudzbineListDetaljiporudzbine.getDetaljiporudzbineList().remove(detaljiporudzbineListDetaljiporudzbine);
                    oldPorudzbinaIdOfDetaljiporudzbineListDetaljiporudzbine = em.merge(oldPorudzbinaIdOfDetaljiporudzbineListDetaljiporudzbine);
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

    public void edit(Porudzbina porudzbina) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Porudzbina persistentPorudzbina = em.find(Porudzbina.class, porudzbina.getPorudzbinaId());
            Kupac kupacIdOld = persistentPorudzbina.getKupacId();
            Kupac kupacIdNew = porudzbina.getKupacId();
            List<Detaljiporudzbine> detaljiporudzbineListOld = persistentPorudzbina.getDetaljiporudzbineList();
            List<Detaljiporudzbine> detaljiporudzbineListNew = porudzbina.getDetaljiporudzbineList();
            List<String> illegalOrphanMessages = null;
            for (Detaljiporudzbine detaljiporudzbineListOldDetaljiporudzbine : detaljiporudzbineListOld) {
                if (!detaljiporudzbineListNew.contains(detaljiporudzbineListOldDetaljiporudzbine)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detaljiporudzbine " + detaljiporudzbineListOldDetaljiporudzbine + " since its porudzbinaId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (kupacIdNew != null) {
                kupacIdNew = em.getReference(kupacIdNew.getClass(), kupacIdNew.getKupacId());
                porudzbina.setKupacId(kupacIdNew);
            }
            List<Detaljiporudzbine> attachedDetaljiporudzbineListNew = new ArrayList<Detaljiporudzbine>();
            for (Detaljiporudzbine detaljiporudzbineListNewDetaljiporudzbineToAttach : detaljiporudzbineListNew) {
                detaljiporudzbineListNewDetaljiporudzbineToAttach = em.getReference(detaljiporudzbineListNewDetaljiporudzbineToAttach.getClass(), detaljiporudzbineListNewDetaljiporudzbineToAttach.getDetaljiPorudzbineId());
                attachedDetaljiporudzbineListNew.add(detaljiporudzbineListNewDetaljiporudzbineToAttach);
            }
            detaljiporudzbineListNew = attachedDetaljiporudzbineListNew;
            porudzbina.setDetaljiporudzbineList(detaljiporudzbineListNew);
            porudzbina = em.merge(porudzbina);
            if (kupacIdOld != null && !kupacIdOld.equals(kupacIdNew)) {
                kupacIdOld.getPorudzbinaList().remove(porudzbina);
                kupacIdOld = em.merge(kupacIdOld);
            }
            if (kupacIdNew != null && !kupacIdNew.equals(kupacIdOld)) {
                kupacIdNew.getPorudzbinaList().add(porudzbina);
                kupacIdNew = em.merge(kupacIdNew);
            }
            for (Detaljiporudzbine detaljiporudzbineListNewDetaljiporudzbine : detaljiporudzbineListNew) {
                if (!detaljiporudzbineListOld.contains(detaljiporudzbineListNewDetaljiporudzbine)) {
                    Porudzbina oldPorudzbinaIdOfDetaljiporudzbineListNewDetaljiporudzbine = detaljiporudzbineListNewDetaljiporudzbine.getPorudzbinaId();
                    detaljiporudzbineListNewDetaljiporudzbine.setPorudzbinaId(porudzbina);
                    detaljiporudzbineListNewDetaljiporudzbine = em.merge(detaljiporudzbineListNewDetaljiporudzbine);
                    if (oldPorudzbinaIdOfDetaljiporudzbineListNewDetaljiporudzbine != null && !oldPorudzbinaIdOfDetaljiporudzbineListNewDetaljiporudzbine.equals(porudzbina)) {
                        oldPorudzbinaIdOfDetaljiporudzbineListNewDetaljiporudzbine.getDetaljiporudzbineList().remove(detaljiporudzbineListNewDetaljiporudzbine);
                        oldPorudzbinaIdOfDetaljiporudzbineListNewDetaljiporudzbine = em.merge(oldPorudzbinaIdOfDetaljiporudzbineListNewDetaljiporudzbine);
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
                Integer id = porudzbina.getPorudzbinaId();
                if (findPorudzbina(id) == null) {
                    throw new NonexistentEntityException("The porudzbina with id " + id + " no longer exists.");
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
            Porudzbina porudzbina;
            try {
                porudzbina = em.getReference(Porudzbina.class, id);
                porudzbina.getPorudzbinaId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The porudzbina with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Detaljiporudzbine> detaljiporudzbineListOrphanCheck = porudzbina.getDetaljiporudzbineList();
            for (Detaljiporudzbine detaljiporudzbineListOrphanCheckDetaljiporudzbine : detaljiporudzbineListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Porudzbina (" + porudzbina + ") cannot be destroyed since the Detaljiporudzbine " + detaljiporudzbineListOrphanCheckDetaljiporudzbine + " in its detaljiporudzbineList field has a non-nullable porudzbinaId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Kupac kupacId = porudzbina.getKupacId();
            if (kupacId != null) {
                kupacId.getPorudzbinaList().remove(porudzbina);
                kupacId = em.merge(kupacId);
            }
            em.remove(porudzbina);
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

    public List<Porudzbina> findPorudzbinaEntities() {
        return findPorudzbinaEntities(true, -1, -1);
    }

    public List<Porudzbina> findPorudzbinaEntities(int maxResults, int firstResult) {
        return findPorudzbinaEntities(false, maxResults, firstResult);
    }

    private List<Porudzbina> findPorudzbinaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Porudzbina.class));
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

    public Porudzbina findPorudzbina(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Porudzbina.class, id);
        } finally {
            em.close();
        }
    }

    public int getPorudzbinaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Porudzbina> rt = cq.from(Porudzbina.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
