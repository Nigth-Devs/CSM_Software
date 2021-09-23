/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import dto.Contenido;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import dto.SeccionMicrocurriculo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Sachikia
 */
public class ContenidoJpaController implements Serializable {

    public ContenidoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Contenido contenido) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SeccionMicrocurriculo seccionMicrocurriculoId = contenido.getSeccionMicrocurriculoId();
            if (seccionMicrocurriculoId != null) {
                seccionMicrocurriculoId = em.getReference(seccionMicrocurriculoId.getClass(), seccionMicrocurriculoId.getId());
                contenido.setSeccionMicrocurriculoId(seccionMicrocurriculoId);
            }
            em.persist(contenido);
            if (seccionMicrocurriculoId != null) {
                seccionMicrocurriculoId.getContenidoList().add(contenido);
                seccionMicrocurriculoId = em.merge(seccionMicrocurriculoId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Contenido contenido) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Contenido persistentContenido = em.find(Contenido.class, contenido.getId());
            SeccionMicrocurriculo seccionMicrocurriculoIdOld = persistentContenido.getSeccionMicrocurriculoId();
            SeccionMicrocurriculo seccionMicrocurriculoIdNew = contenido.getSeccionMicrocurriculoId();
            if (seccionMicrocurriculoIdNew != null) {
                seccionMicrocurriculoIdNew = em.getReference(seccionMicrocurriculoIdNew.getClass(), seccionMicrocurriculoIdNew.getId());
                contenido.setSeccionMicrocurriculoId(seccionMicrocurriculoIdNew);
            }
            contenido = em.merge(contenido);
            if (seccionMicrocurriculoIdOld != null && !seccionMicrocurriculoIdOld.equals(seccionMicrocurriculoIdNew)) {
                seccionMicrocurriculoIdOld.getContenidoList().remove(contenido);
                seccionMicrocurriculoIdOld = em.merge(seccionMicrocurriculoIdOld);
            }
            if (seccionMicrocurriculoIdNew != null && !seccionMicrocurriculoIdNew.equals(seccionMicrocurriculoIdOld)) {
                seccionMicrocurriculoIdNew.getContenidoList().add(contenido);
                seccionMicrocurriculoIdNew = em.merge(seccionMicrocurriculoIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = contenido.getId();
                if (findContenido(id) == null) {
                    throw new NonexistentEntityException("The contenido with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Contenido contenido;
            try {
                contenido = em.getReference(Contenido.class, id);
                contenido.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The contenido with id " + id + " no longer exists.", enfe);
            }
            SeccionMicrocurriculo seccionMicrocurriculoId = contenido.getSeccionMicrocurriculoId();
            if (seccionMicrocurriculoId != null) {
                seccionMicrocurriculoId.getContenidoList().remove(contenido);
                seccionMicrocurriculoId = em.merge(seccionMicrocurriculoId);
            }
            em.remove(contenido);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Contenido> findContenidoEntities() {
        return findContenidoEntities(true, -1, -1);
    }

    public List<Contenido> findContenidoEntities(int maxResults, int firstResult) {
        return findContenidoEntities(false, maxResults, firstResult);
    }

    private List<Contenido> findContenidoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Contenido.class));
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

    public Contenido findContenido(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Contenido.class, id);
        } finally {
            em.close();
        }
    }

    public int getContenidoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Contenido> rt = cq.from(Contenido.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
