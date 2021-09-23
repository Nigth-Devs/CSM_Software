/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import dto.ContenidoUnidad;
import dto.Cumplimiento;
import dto.CumplimientoPK;
import dto.MateriaPeriodoGrupo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Sachikia
 */
public class CumplimientoJpaController implements Serializable {

    public CumplimientoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cumplimiento cumplimiento) throws PreexistingEntityException, Exception {
        if (cumplimiento.getCumplimientoPK() == null) {
            cumplimiento.setCumplimientoPK(new CumplimientoPK());
        }
        cumplimiento.getCumplimientoPK().setContenidoUnidadId(cumplimiento.getContenidoUnidad().getId());
        cumplimiento.getCumplimientoPK().setMateriaPeriodoGrupoId(cumplimiento.getMateriaPeriodoGrupo().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ContenidoUnidad contenidoUnidad = cumplimiento.getContenidoUnidad();
            if (contenidoUnidad != null) {
                contenidoUnidad = em.getReference(contenidoUnidad.getClass(), contenidoUnidad.getId());
                cumplimiento.setContenidoUnidad(contenidoUnidad);
            }
            MateriaPeriodoGrupo materiaPeriodoGrupo = cumplimiento.getMateriaPeriodoGrupo();
            if (materiaPeriodoGrupo != null) {
                materiaPeriodoGrupo = em.getReference(materiaPeriodoGrupo.getClass(), materiaPeriodoGrupo.getId());
                cumplimiento.setMateriaPeriodoGrupo(materiaPeriodoGrupo);
            }
            em.persist(cumplimiento);
            if (contenidoUnidad != null) {
                contenidoUnidad.getCumplimientoList().add(cumplimiento);
                contenidoUnidad = em.merge(contenidoUnidad);
            }
            if (materiaPeriodoGrupo != null) {
                materiaPeriodoGrupo.getCumplimientoList().add(cumplimiento);
                materiaPeriodoGrupo = em.merge(materiaPeriodoGrupo);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCumplimiento(cumplimiento.getCumplimientoPK()) != null) {
                throw new PreexistingEntityException("Cumplimiento " + cumplimiento + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cumplimiento cumplimiento) throws NonexistentEntityException, Exception {
        cumplimiento.getCumplimientoPK().setContenidoUnidadId(cumplimiento.getContenidoUnidad().getId());
        cumplimiento.getCumplimientoPK().setMateriaPeriodoGrupoId(cumplimiento.getMateriaPeriodoGrupo().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cumplimiento persistentCumplimiento = em.find(Cumplimiento.class, cumplimiento.getCumplimientoPK());
            ContenidoUnidad contenidoUnidadOld = persistentCumplimiento.getContenidoUnidad();
            ContenidoUnidad contenidoUnidadNew = cumplimiento.getContenidoUnidad();
            MateriaPeriodoGrupo materiaPeriodoGrupoOld = persistentCumplimiento.getMateriaPeriodoGrupo();
            MateriaPeriodoGrupo materiaPeriodoGrupoNew = cumplimiento.getMateriaPeriodoGrupo();
            if (contenidoUnidadNew != null) {
                contenidoUnidadNew = em.getReference(contenidoUnidadNew.getClass(), contenidoUnidadNew.getId());
                cumplimiento.setContenidoUnidad(contenidoUnidadNew);
            }
            if (materiaPeriodoGrupoNew != null) {
                materiaPeriodoGrupoNew = em.getReference(materiaPeriodoGrupoNew.getClass(), materiaPeriodoGrupoNew.getId());
                cumplimiento.setMateriaPeriodoGrupo(materiaPeriodoGrupoNew);
            }
            cumplimiento = em.merge(cumplimiento);
            if (contenidoUnidadOld != null && !contenidoUnidadOld.equals(contenidoUnidadNew)) {
                contenidoUnidadOld.getCumplimientoList().remove(cumplimiento);
                contenidoUnidadOld = em.merge(contenidoUnidadOld);
            }
            if (contenidoUnidadNew != null && !contenidoUnidadNew.equals(contenidoUnidadOld)) {
                contenidoUnidadNew.getCumplimientoList().add(cumplimiento);
                contenidoUnidadNew = em.merge(contenidoUnidadNew);
            }
            if (materiaPeriodoGrupoOld != null && !materiaPeriodoGrupoOld.equals(materiaPeriodoGrupoNew)) {
                materiaPeriodoGrupoOld.getCumplimientoList().remove(cumplimiento);
                materiaPeriodoGrupoOld = em.merge(materiaPeriodoGrupoOld);
            }
            if (materiaPeriodoGrupoNew != null && !materiaPeriodoGrupoNew.equals(materiaPeriodoGrupoOld)) {
                materiaPeriodoGrupoNew.getCumplimientoList().add(cumplimiento);
                materiaPeriodoGrupoNew = em.merge(materiaPeriodoGrupoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                CumplimientoPK id = cumplimiento.getCumplimientoPK();
                if (findCumplimiento(id) == null) {
                    throw new NonexistentEntityException("The cumplimiento with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CumplimientoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cumplimiento cumplimiento;
            try {
                cumplimiento = em.getReference(Cumplimiento.class, id);
                cumplimiento.getCumplimientoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cumplimiento with id " + id + " no longer exists.", enfe);
            }
            ContenidoUnidad contenidoUnidad = cumplimiento.getContenidoUnidad();
            if (contenidoUnidad != null) {
                contenidoUnidad.getCumplimientoList().remove(cumplimiento);
                contenidoUnidad = em.merge(contenidoUnidad);
            }
            MateriaPeriodoGrupo materiaPeriodoGrupo = cumplimiento.getMateriaPeriodoGrupo();
            if (materiaPeriodoGrupo != null) {
                materiaPeriodoGrupo.getCumplimientoList().remove(cumplimiento);
                materiaPeriodoGrupo = em.merge(materiaPeriodoGrupo);
            }
            em.remove(cumplimiento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cumplimiento> findCumplimientoEntities() {
        return findCumplimientoEntities(true, -1, -1);
    }

    public List<Cumplimiento> findCumplimientoEntities(int maxResults, int firstResult) {
        return findCumplimientoEntities(false, maxResults, firstResult);
    }

    private List<Cumplimiento> findCumplimientoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cumplimiento.class));
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

    public Cumplimiento findCumplimiento(CumplimientoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cumplimiento.class, id);
        } finally {
            em.close();
        }
    }

    public int getCumplimientoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cumplimiento> rt = cq.from(Cumplimiento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
