/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.exceptions.IllegalOrphanException;
import dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import dto.Materia;
import dto.TipoMateria;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Sachikia
 */
public class TipoMateriaJpaController implements Serializable {

    public TipoMateriaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoMateria tipoMateria) {
        if (tipoMateria.getMateriaList() == null) {
            tipoMateria.setMateriaList(new ArrayList<Materia>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Materia> attachedMateriaList = new ArrayList<Materia>();
            for (Materia materiaListMateriaToAttach : tipoMateria.getMateriaList()) {
                materiaListMateriaToAttach = em.getReference(materiaListMateriaToAttach.getClass(), materiaListMateriaToAttach.getMateriaPK());
                attachedMateriaList.add(materiaListMateriaToAttach);
            }
            tipoMateria.setMateriaList(attachedMateriaList);
            em.persist(tipoMateria);
            for (Materia materiaListMateria : tipoMateria.getMateriaList()) {
                TipoMateria oldTipoIdOfMateriaListMateria = materiaListMateria.getTipoId();
                materiaListMateria.setTipoId(tipoMateria);
                materiaListMateria = em.merge(materiaListMateria);
                if (oldTipoIdOfMateriaListMateria != null) {
                    oldTipoIdOfMateriaListMateria.getMateriaList().remove(materiaListMateria);
                    oldTipoIdOfMateriaListMateria = em.merge(oldTipoIdOfMateriaListMateria);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoMateria tipoMateria) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoMateria persistentTipoMateria = em.find(TipoMateria.class, tipoMateria.getId());
            List<Materia> materiaListOld = persistentTipoMateria.getMateriaList();
            List<Materia> materiaListNew = tipoMateria.getMateriaList();
            List<String> illegalOrphanMessages = null;
            for (Materia materiaListOldMateria : materiaListOld) {
                if (!materiaListNew.contains(materiaListOldMateria)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Materia " + materiaListOldMateria + " since its tipoId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Materia> attachedMateriaListNew = new ArrayList<Materia>();
            for (Materia materiaListNewMateriaToAttach : materiaListNew) {
                materiaListNewMateriaToAttach = em.getReference(materiaListNewMateriaToAttach.getClass(), materiaListNewMateriaToAttach.getMateriaPK());
                attachedMateriaListNew.add(materiaListNewMateriaToAttach);
            }
            materiaListNew = attachedMateriaListNew;
            tipoMateria.setMateriaList(materiaListNew);
            tipoMateria = em.merge(tipoMateria);
            for (Materia materiaListNewMateria : materiaListNew) {
                if (!materiaListOld.contains(materiaListNewMateria)) {
                    TipoMateria oldTipoIdOfMateriaListNewMateria = materiaListNewMateria.getTipoId();
                    materiaListNewMateria.setTipoId(tipoMateria);
                    materiaListNewMateria = em.merge(materiaListNewMateria);
                    if (oldTipoIdOfMateriaListNewMateria != null && !oldTipoIdOfMateriaListNewMateria.equals(tipoMateria)) {
                        oldTipoIdOfMateriaListNewMateria.getMateriaList().remove(materiaListNewMateria);
                        oldTipoIdOfMateriaListNewMateria = em.merge(oldTipoIdOfMateriaListNewMateria);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoMateria.getId();
                if (findTipoMateria(id) == null) {
                    throw new NonexistentEntityException("The tipoMateria with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoMateria tipoMateria;
            try {
                tipoMateria = em.getReference(TipoMateria.class, id);
                tipoMateria.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoMateria with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Materia> materiaListOrphanCheck = tipoMateria.getMateriaList();
            for (Materia materiaListOrphanCheckMateria : materiaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TipoMateria (" + tipoMateria + ") cannot be destroyed since the Materia " + materiaListOrphanCheckMateria + " in its materiaList field has a non-nullable tipoId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tipoMateria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoMateria> findTipoMateriaEntities() {
        return findTipoMateriaEntities(true, -1, -1);
    }

    public List<TipoMateria> findTipoMateriaEntities(int maxResults, int firstResult) {
        return findTipoMateriaEntities(false, maxResults, firstResult);
    }

    private List<TipoMateria> findTipoMateriaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoMateria.class));
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

    public TipoMateria findTipoMateria(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoMateria.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoMateriaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoMateria> rt = cq.from(TipoMateria.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
