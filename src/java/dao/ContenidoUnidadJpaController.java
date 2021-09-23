/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.exceptions.IllegalOrphanException;
import dao.exceptions.NonexistentEntityException;
import dto.ContenidoUnidad;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import dto.Unidad;
import dto.Cumplimiento;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Sachikia
 */
public class ContenidoUnidadJpaController implements Serializable {

    public ContenidoUnidadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ContenidoUnidad contenidoUnidad) {
        if (contenidoUnidad.getCumplimientoList() == null) {
            contenidoUnidad.setCumplimientoList(new ArrayList<Cumplimiento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Unidad unidadId = contenidoUnidad.getUnidadId();
            if (unidadId != null) {
                unidadId = em.getReference(unidadId.getClass(), unidadId.getId());
                contenidoUnidad.setUnidadId(unidadId);
            }
            List<Cumplimiento> attachedCumplimientoList = new ArrayList<Cumplimiento>();
            for (Cumplimiento cumplimientoListCumplimientoToAttach : contenidoUnidad.getCumplimientoList()) {
                cumplimientoListCumplimientoToAttach = em.getReference(cumplimientoListCumplimientoToAttach.getClass(), cumplimientoListCumplimientoToAttach.getCumplimientoPK());
                attachedCumplimientoList.add(cumplimientoListCumplimientoToAttach);
            }
            contenidoUnidad.setCumplimientoList(attachedCumplimientoList);
            em.persist(contenidoUnidad);
            if (unidadId != null) {
                unidadId.getContenidoUnidadList().add(contenidoUnidad);
                unidadId = em.merge(unidadId);
            }
            for (Cumplimiento cumplimientoListCumplimiento : contenidoUnidad.getCumplimientoList()) {
                ContenidoUnidad oldContenidoUnidadOfCumplimientoListCumplimiento = cumplimientoListCumplimiento.getContenidoUnidad();
                cumplimientoListCumplimiento.setContenidoUnidad(contenidoUnidad);
                cumplimientoListCumplimiento = em.merge(cumplimientoListCumplimiento);
                if (oldContenidoUnidadOfCumplimientoListCumplimiento != null) {
                    oldContenidoUnidadOfCumplimientoListCumplimiento.getCumplimientoList().remove(cumplimientoListCumplimiento);
                    oldContenidoUnidadOfCumplimientoListCumplimiento = em.merge(oldContenidoUnidadOfCumplimientoListCumplimiento);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ContenidoUnidad contenidoUnidad) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ContenidoUnidad persistentContenidoUnidad = em.find(ContenidoUnidad.class, contenidoUnidad.getId());
            Unidad unidadIdOld = persistentContenidoUnidad.getUnidadId();
            Unidad unidadIdNew = contenidoUnidad.getUnidadId();
            List<Cumplimiento> cumplimientoListOld = persistentContenidoUnidad.getCumplimientoList();
            List<Cumplimiento> cumplimientoListNew = contenidoUnidad.getCumplimientoList();
            List<String> illegalOrphanMessages = null;
            for (Cumplimiento cumplimientoListOldCumplimiento : cumplimientoListOld) {
                if (!cumplimientoListNew.contains(cumplimientoListOldCumplimiento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Cumplimiento " + cumplimientoListOldCumplimiento + " since its contenidoUnidad field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (unidadIdNew != null) {
                unidadIdNew = em.getReference(unidadIdNew.getClass(), unidadIdNew.getId());
                contenidoUnidad.setUnidadId(unidadIdNew);
            }
            List<Cumplimiento> attachedCumplimientoListNew = new ArrayList<Cumplimiento>();
            for (Cumplimiento cumplimientoListNewCumplimientoToAttach : cumplimientoListNew) {
                cumplimientoListNewCumplimientoToAttach = em.getReference(cumplimientoListNewCumplimientoToAttach.getClass(), cumplimientoListNewCumplimientoToAttach.getCumplimientoPK());
                attachedCumplimientoListNew.add(cumplimientoListNewCumplimientoToAttach);
            }
            cumplimientoListNew = attachedCumplimientoListNew;
            contenidoUnidad.setCumplimientoList(cumplimientoListNew);
            contenidoUnidad = em.merge(contenidoUnidad);
            if (unidadIdOld != null && !unidadIdOld.equals(unidadIdNew)) {
                unidadIdOld.getContenidoUnidadList().remove(contenidoUnidad);
                unidadIdOld = em.merge(unidadIdOld);
            }
            if (unidadIdNew != null && !unidadIdNew.equals(unidadIdOld)) {
                unidadIdNew.getContenidoUnidadList().add(contenidoUnidad);
                unidadIdNew = em.merge(unidadIdNew);
            }
            for (Cumplimiento cumplimientoListNewCumplimiento : cumplimientoListNew) {
                if (!cumplimientoListOld.contains(cumplimientoListNewCumplimiento)) {
                    ContenidoUnidad oldContenidoUnidadOfCumplimientoListNewCumplimiento = cumplimientoListNewCumplimiento.getContenidoUnidad();
                    cumplimientoListNewCumplimiento.setContenidoUnidad(contenidoUnidad);
                    cumplimientoListNewCumplimiento = em.merge(cumplimientoListNewCumplimiento);
                    if (oldContenidoUnidadOfCumplimientoListNewCumplimiento != null && !oldContenidoUnidadOfCumplimientoListNewCumplimiento.equals(contenidoUnidad)) {
                        oldContenidoUnidadOfCumplimientoListNewCumplimiento.getCumplimientoList().remove(cumplimientoListNewCumplimiento);
                        oldContenidoUnidadOfCumplimientoListNewCumplimiento = em.merge(oldContenidoUnidadOfCumplimientoListNewCumplimiento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = contenidoUnidad.getId();
                if (findContenidoUnidad(id) == null) {
                    throw new NonexistentEntityException("The contenidoUnidad with id " + id + " no longer exists.");
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
            ContenidoUnidad contenidoUnidad;
            try {
                contenidoUnidad = em.getReference(ContenidoUnidad.class, id);
                contenidoUnidad.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The contenidoUnidad with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Cumplimiento> cumplimientoListOrphanCheck = contenidoUnidad.getCumplimientoList();
            for (Cumplimiento cumplimientoListOrphanCheckCumplimiento : cumplimientoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ContenidoUnidad (" + contenidoUnidad + ") cannot be destroyed since the Cumplimiento " + cumplimientoListOrphanCheckCumplimiento + " in its cumplimientoList field has a non-nullable contenidoUnidad field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Unidad unidadId = contenidoUnidad.getUnidadId();
            if (unidadId != null) {
                unidadId.getContenidoUnidadList().remove(contenidoUnidad);
                unidadId = em.merge(unidadId);
            }
            em.remove(contenidoUnidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ContenidoUnidad> findContenidoUnidadEntities() {
        return findContenidoUnidadEntities(true, -1, -1);
    }

    public List<ContenidoUnidad> findContenidoUnidadEntities(int maxResults, int firstResult) {
        return findContenidoUnidadEntities(false, maxResults, firstResult);
    }

    private List<ContenidoUnidad> findContenidoUnidadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ContenidoUnidad.class));
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

    public ContenidoUnidad findContenidoUnidad(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ContenidoUnidad.class, id);
        } finally {
            em.close();
        }
    }

    public int getContenidoUnidadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ContenidoUnidad> rt = cq.from(ContenidoUnidad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
