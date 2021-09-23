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
import dto.Microcurriculo;
import dto.ContenidoUnidad;
import dto.Unidad;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Sachikia
 */
public class UnidadJpaController implements Serializable {

    public UnidadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Unidad unidad) {
        if (unidad.getContenidoUnidadList() == null) {
            unidad.setContenidoUnidadList(new ArrayList<ContenidoUnidad>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Microcurriculo microcurriculo = unidad.getMicrocurriculo();
            if (microcurriculo != null) {
                microcurriculo = em.getReference(microcurriculo.getClass(), microcurriculo.getMicrocurriculoPK());
                unidad.setMicrocurriculo(microcurriculo);
            }
            List<ContenidoUnidad> attachedContenidoUnidadList = new ArrayList<ContenidoUnidad>();
            for (ContenidoUnidad contenidoUnidadListContenidoUnidadToAttach : unidad.getContenidoUnidadList()) {
                contenidoUnidadListContenidoUnidadToAttach = em.getReference(contenidoUnidadListContenidoUnidadToAttach.getClass(), contenidoUnidadListContenidoUnidadToAttach.getId());
                attachedContenidoUnidadList.add(contenidoUnidadListContenidoUnidadToAttach);
            }
            unidad.setContenidoUnidadList(attachedContenidoUnidadList);
            em.persist(unidad);
            if (microcurriculo != null) {
                microcurriculo.getUnidadList().add(unidad);
                microcurriculo = em.merge(microcurriculo);
            }
            for (ContenidoUnidad contenidoUnidadListContenidoUnidad : unidad.getContenidoUnidadList()) {
                Unidad oldUnidadIdOfContenidoUnidadListContenidoUnidad = contenidoUnidadListContenidoUnidad.getUnidadId();
                contenidoUnidadListContenidoUnidad.setUnidadId(unidad);
                contenidoUnidadListContenidoUnidad = em.merge(contenidoUnidadListContenidoUnidad);
                if (oldUnidadIdOfContenidoUnidadListContenidoUnidad != null) {
                    oldUnidadIdOfContenidoUnidadListContenidoUnidad.getContenidoUnidadList().remove(contenidoUnidadListContenidoUnidad);
                    oldUnidadIdOfContenidoUnidadListContenidoUnidad = em.merge(oldUnidadIdOfContenidoUnidadListContenidoUnidad);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Unidad unidad) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Unidad persistentUnidad = em.find(Unidad.class, unidad.getId());
            Microcurriculo microcurriculoOld = persistentUnidad.getMicrocurriculo();
            Microcurriculo microcurriculoNew = unidad.getMicrocurriculo();
            List<ContenidoUnidad> contenidoUnidadListOld = persistentUnidad.getContenidoUnidadList();
            List<ContenidoUnidad> contenidoUnidadListNew = unidad.getContenidoUnidadList();
            List<String> illegalOrphanMessages = null;
            for (ContenidoUnidad contenidoUnidadListOldContenidoUnidad : contenidoUnidadListOld) {
                if (!contenidoUnidadListNew.contains(contenidoUnidadListOldContenidoUnidad)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ContenidoUnidad " + contenidoUnidadListOldContenidoUnidad + " since its unidadId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (microcurriculoNew != null) {
                microcurriculoNew = em.getReference(microcurriculoNew.getClass(), microcurriculoNew.getMicrocurriculoPK());
                unidad.setMicrocurriculo(microcurriculoNew);
            }
            List<ContenidoUnidad> attachedContenidoUnidadListNew = new ArrayList<ContenidoUnidad>();
            for (ContenidoUnidad contenidoUnidadListNewContenidoUnidadToAttach : contenidoUnidadListNew) {
                contenidoUnidadListNewContenidoUnidadToAttach = em.getReference(contenidoUnidadListNewContenidoUnidadToAttach.getClass(), contenidoUnidadListNewContenidoUnidadToAttach.getId());
                attachedContenidoUnidadListNew.add(contenidoUnidadListNewContenidoUnidadToAttach);
            }
            contenidoUnidadListNew = attachedContenidoUnidadListNew;
            unidad.setContenidoUnidadList(contenidoUnidadListNew);
            unidad = em.merge(unidad);
            if (microcurriculoOld != null && !microcurriculoOld.equals(microcurriculoNew)) {
                microcurriculoOld.getUnidadList().remove(unidad);
                microcurriculoOld = em.merge(microcurriculoOld);
            }
            if (microcurriculoNew != null && !microcurriculoNew.equals(microcurriculoOld)) {
                microcurriculoNew.getUnidadList().add(unidad);
                microcurriculoNew = em.merge(microcurriculoNew);
            }
            for (ContenidoUnidad contenidoUnidadListNewContenidoUnidad : contenidoUnidadListNew) {
                if (!contenidoUnidadListOld.contains(contenidoUnidadListNewContenidoUnidad)) {
                    Unidad oldUnidadIdOfContenidoUnidadListNewContenidoUnidad = contenidoUnidadListNewContenidoUnidad.getUnidadId();
                    contenidoUnidadListNewContenidoUnidad.setUnidadId(unidad);
                    contenidoUnidadListNewContenidoUnidad = em.merge(contenidoUnidadListNewContenidoUnidad);
                    if (oldUnidadIdOfContenidoUnidadListNewContenidoUnidad != null && !oldUnidadIdOfContenidoUnidadListNewContenidoUnidad.equals(unidad)) {
                        oldUnidadIdOfContenidoUnidadListNewContenidoUnidad.getContenidoUnidadList().remove(contenidoUnidadListNewContenidoUnidad);
                        oldUnidadIdOfContenidoUnidadListNewContenidoUnidad = em.merge(oldUnidadIdOfContenidoUnidadListNewContenidoUnidad);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = unidad.getId();
                if (findUnidad(id) == null) {
                    throw new NonexistentEntityException("The unidad with id " + id + " no longer exists.");
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
            Unidad unidad;
            try {
                unidad = em.getReference(Unidad.class, id);
                unidad.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The unidad with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<ContenidoUnidad> contenidoUnidadListOrphanCheck = unidad.getContenidoUnidadList();
            for (ContenidoUnidad contenidoUnidadListOrphanCheckContenidoUnidad : contenidoUnidadListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Unidad (" + unidad + ") cannot be destroyed since the ContenidoUnidad " + contenidoUnidadListOrphanCheckContenidoUnidad + " in its contenidoUnidadList field has a non-nullable unidadId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Microcurriculo microcurriculo = unidad.getMicrocurriculo();
            if (microcurriculo != null) {
                microcurriculo.getUnidadList().remove(unidad);
                microcurriculo = em.merge(microcurriculo);
            }
            em.remove(unidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Unidad> findUnidadEntities() {
        return findUnidadEntities(true, -1, -1);
    }

    public List<Unidad> findUnidadEntities(int maxResults, int firstResult) {
        return findUnidadEntities(false, maxResults, firstResult);
    }

    private List<Unidad> findUnidadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Unidad.class));
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

    public Unidad findUnidad(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Unidad.class, id);
        } finally {
            em.close();
        }
    }

    public int getUnidadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Unidad> rt = cq.from(Unidad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
