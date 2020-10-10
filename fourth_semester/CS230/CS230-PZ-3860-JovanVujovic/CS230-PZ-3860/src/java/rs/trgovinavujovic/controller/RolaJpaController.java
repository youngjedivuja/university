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
import rs.trgovinavujovic.entities.Korisnik;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import rs.trgovinavujovic.controller.exceptions.NonexistentEntityException;
import rs.trgovinavujovic.controller.exceptions.RollbackFailureException;
import rs.trgovinavujovic.entities.Rola;

/**
 *
 * @author Jovan Vujovic
 */
public class RolaJpaController implements Serializable {

    public RolaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Rola rola) throws RollbackFailureException, Exception {
        if (rola.getKorisnikList() == null) {
            rola.setKorisnikList(new ArrayList<Korisnik>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            List<Korisnik> attachedKorisnikList = new ArrayList<Korisnik>();
            for (Korisnik korisnikListKorisnikToAttach : rola.getKorisnikList()) {
                korisnikListKorisnikToAttach = em.getReference(korisnikListKorisnikToAttach.getClass(), korisnikListKorisnikToAttach.getUserId());
                attachedKorisnikList.add(korisnikListKorisnikToAttach);
            }
            rola.setKorisnikList(attachedKorisnikList);
            em.persist(rola);
            for (Korisnik korisnikListKorisnik : rola.getKorisnikList()) {
                Rola oldRolaIdOfKorisnikListKorisnik = korisnikListKorisnik.getRolaId();
                korisnikListKorisnik.setRolaId(rola);
                korisnikListKorisnik = em.merge(korisnikListKorisnik);
                if (oldRolaIdOfKorisnikListKorisnik != null) {
                    oldRolaIdOfKorisnikListKorisnik.getKorisnikList().remove(korisnikListKorisnik);
                    oldRolaIdOfKorisnikListKorisnik = em.merge(oldRolaIdOfKorisnikListKorisnik);
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

    public void edit(Rola rola) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Rola persistentRola = em.find(Rola.class, rola.getRolaId());
            List<Korisnik> korisnikListOld = persistentRola.getKorisnikList();
            List<Korisnik> korisnikListNew = rola.getKorisnikList();
            List<Korisnik> attachedKorisnikListNew = new ArrayList<Korisnik>();
            for (Korisnik korisnikListNewKorisnikToAttach : korisnikListNew) {
                korisnikListNewKorisnikToAttach = em.getReference(korisnikListNewKorisnikToAttach.getClass(), korisnikListNewKorisnikToAttach.getUserId());
                attachedKorisnikListNew.add(korisnikListNewKorisnikToAttach);
            }
            korisnikListNew = attachedKorisnikListNew;
            rola.setKorisnikList(korisnikListNew);
            rola = em.merge(rola);
            for (Korisnik korisnikListOldKorisnik : korisnikListOld) {
                if (!korisnikListNew.contains(korisnikListOldKorisnik)) {
                    korisnikListOldKorisnik.setRolaId(null);
                    korisnikListOldKorisnik = em.merge(korisnikListOldKorisnik);
                }
            }
            for (Korisnik korisnikListNewKorisnik : korisnikListNew) {
                if (!korisnikListOld.contains(korisnikListNewKorisnik)) {
                    Rola oldRolaIdOfKorisnikListNewKorisnik = korisnikListNewKorisnik.getRolaId();
                    korisnikListNewKorisnik.setRolaId(rola);
                    korisnikListNewKorisnik = em.merge(korisnikListNewKorisnik);
                    if (oldRolaIdOfKorisnikListNewKorisnik != null && !oldRolaIdOfKorisnikListNewKorisnik.equals(rola)) {
                        oldRolaIdOfKorisnikListNewKorisnik.getKorisnikList().remove(korisnikListNewKorisnik);
                        oldRolaIdOfKorisnikListNewKorisnik = em.merge(oldRolaIdOfKorisnikListNewKorisnik);
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
                Integer id = rola.getRolaId();
                if (findRola(id) == null) {
                    throw new NonexistentEntityException("The rola with id " + id + " no longer exists.");
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
            Rola rola;
            try {
                rola = em.getReference(Rola.class, id);
                rola.getRolaId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rola with id " + id + " no longer exists.", enfe);
            }
            List<Korisnik> korisnikList = rola.getKorisnikList();
            for (Korisnik korisnikListKorisnik : korisnikList) {
                korisnikListKorisnik.setRolaId(null);
                korisnikListKorisnik = em.merge(korisnikListKorisnik);
            }
            em.remove(rola);
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

    public List<Rola> findRolaEntities() {
        return findRolaEntities(true, -1, -1);
    }

    public List<Rola> findRolaEntities(int maxResults, int firstResult) {
        return findRolaEntities(false, maxResults, firstResult);
    }

    private List<Rola> findRolaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Rola.class));
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

    public Rola findRola(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Rola.class, id);
        } finally {
            em.close();
        }
    }

    public int getRolaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Rola> rt = cq.from(Rola.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
