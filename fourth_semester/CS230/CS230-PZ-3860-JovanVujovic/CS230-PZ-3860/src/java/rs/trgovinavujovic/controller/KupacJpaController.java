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
import rs.trgovinavujovic.entities.Porudzbina;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import rs.trgovinavujovic.controller.exceptions.IllegalOrphanException;
import rs.trgovinavujovic.controller.exceptions.NonexistentEntityException;
import rs.trgovinavujovic.controller.exceptions.RollbackFailureException;
import rs.trgovinavujovic.entities.Kupac;

/**
 *
 * @author Jovan Vujovic
 */
public class KupacJpaController implements Serializable {

    public KupacJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Kupac kupac) throws RollbackFailureException, Exception {
        if (kupac.getPorudzbinaList() == null) {
            kupac.setPorudzbinaList(new ArrayList<Porudzbina>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            List<Porudzbina> attachedPorudzbinaList = new ArrayList<Porudzbina>();
            for (Porudzbina porudzbinaListPorudzbinaToAttach : kupac.getPorudzbinaList()) {
                porudzbinaListPorudzbinaToAttach = em.getReference(porudzbinaListPorudzbinaToAttach.getClass(), porudzbinaListPorudzbinaToAttach.getPorudzbinaId());
                attachedPorudzbinaList.add(porudzbinaListPorudzbinaToAttach);
            }
            kupac.setPorudzbinaList(attachedPorudzbinaList);
            em.persist(kupac);
            for (Porudzbina porudzbinaListPorudzbina : kupac.getPorudzbinaList()) {
                Kupac oldKupacIdOfPorudzbinaListPorudzbina = porudzbinaListPorudzbina.getKupacId();
                porudzbinaListPorudzbina.setKupacId(kupac);
                porudzbinaListPorudzbina = em.merge(porudzbinaListPorudzbina);
                if (oldKupacIdOfPorudzbinaListPorudzbina != null) {
                    oldKupacIdOfPorudzbinaListPorudzbina.getPorudzbinaList().remove(porudzbinaListPorudzbina);
                    oldKupacIdOfPorudzbinaListPorudzbina = em.merge(oldKupacIdOfPorudzbinaListPorudzbina);
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

    public void edit(Kupac kupac) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Kupac persistentKupac = em.find(Kupac.class, kupac.getKupacId());
            List<Porudzbina> porudzbinaListOld = persistentKupac.getPorudzbinaList();
            List<Porudzbina> porudzbinaListNew = kupac.getPorudzbinaList();
            List<String> illegalOrphanMessages = null;
            for (Porudzbina porudzbinaListOldPorudzbina : porudzbinaListOld) {
                if (!porudzbinaListNew.contains(porudzbinaListOldPorudzbina)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Porudzbina " + porudzbinaListOldPorudzbina + " since its kupacId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Porudzbina> attachedPorudzbinaListNew = new ArrayList<Porudzbina>();
            for (Porudzbina porudzbinaListNewPorudzbinaToAttach : porudzbinaListNew) {
                porudzbinaListNewPorudzbinaToAttach = em.getReference(porudzbinaListNewPorudzbinaToAttach.getClass(), porudzbinaListNewPorudzbinaToAttach.getPorudzbinaId());
                attachedPorudzbinaListNew.add(porudzbinaListNewPorudzbinaToAttach);
            }
            porudzbinaListNew = attachedPorudzbinaListNew;
            kupac.setPorudzbinaList(porudzbinaListNew);
            kupac = em.merge(kupac);
            for (Porudzbina porudzbinaListNewPorudzbina : porudzbinaListNew) {
                if (!porudzbinaListOld.contains(porudzbinaListNewPorudzbina)) {
                    Kupac oldKupacIdOfPorudzbinaListNewPorudzbina = porudzbinaListNewPorudzbina.getKupacId();
                    porudzbinaListNewPorudzbina.setKupacId(kupac);
                    porudzbinaListNewPorudzbina = em.merge(porudzbinaListNewPorudzbina);
                    if (oldKupacIdOfPorudzbinaListNewPorudzbina != null && !oldKupacIdOfPorudzbinaListNewPorudzbina.equals(kupac)) {
                        oldKupacIdOfPorudzbinaListNewPorudzbina.getPorudzbinaList().remove(porudzbinaListNewPorudzbina);
                        oldKupacIdOfPorudzbinaListNewPorudzbina = em.merge(oldKupacIdOfPorudzbinaListNewPorudzbina);
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
                Integer id = kupac.getKupacId();
                if (findKupac(id) == null) {
                    throw new NonexistentEntityException("The kupac with id " + id + " no longer exists.");
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
            Kupac kupac;
            try {
                kupac = em.getReference(Kupac.class, id);
                kupac.getKupacId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The kupac with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Porudzbina> porudzbinaListOrphanCheck = kupac.getPorudzbinaList();
            for (Porudzbina porudzbinaListOrphanCheckPorudzbina : porudzbinaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Kupac (" + kupac + ") cannot be destroyed since the Porudzbina " + porudzbinaListOrphanCheckPorudzbina + " in its porudzbinaList field has a non-nullable kupacId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(kupac);
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

    public List<Kupac> findKupacEntities() {
        return findKupacEntities(true, -1, -1);
    }

    public List<Kupac> findKupacEntities(int maxResults, int firstResult) {
        return findKupacEntities(false, maxResults, firstResult);
    }

    private List<Kupac> findKupacEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Kupac.class));
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

    public Kupac findKupac(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Kupac.class, id);
        } finally {
            em.close();
        }
    }

    public int getKupacCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Kupac> rt = cq.from(Kupac.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
