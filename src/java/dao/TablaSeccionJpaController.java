/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.exceptions.IllegalOrphanException;
import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import dto.SeccionMicrocurriculo;
import dto.Tabla;
import dto.TablaSeccion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Sachikia
 */
public class TablaSeccionJpaController implements Serializable {

    public TablaSeccionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TablaSeccion tablaSeccion) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        SeccionMicrocurriculo seccionMicrocurriculoOrphanCheck = tablaSeccion.getSeccionMicrocurriculo();
        if (seccionMicrocurriculoOrphanCheck != null) {
            TablaSeccion oldTablaSeccionOfSeccionMicrocurriculo = seccionMicrocurriculoOrphanCheck.getTablaSeccion();
            if (oldTablaSeccionOfSeccionMicrocurriculo != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The SeccionMicrocurriculo " + seccionMicrocurriculoOrphanCheck + " already has an item of type TablaSeccion whose seccionMicrocurriculo column cannot be null. Please make another selection for the seccionMicrocurriculo field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SeccionMicrocurriculo seccionMicrocurriculo = tablaSeccion.getSeccionMicrocurriculo();
            if (seccionMicrocurriculo != null) {
                seccionMicrocurriculo = em.getReference(seccionMicrocurriculo.getClass(), seccionMicrocurriculo.getId());
                tablaSeccion.setSeccionMicrocurriculo(seccionMicrocurriculo);
            }
            Tabla tablaId = tablaSeccion.getTablaId();
            if (tablaId != null) {
                tablaId = em.getReference(tablaId.getClass(), tablaId.getId());
                tablaSeccion.setTablaId(tablaId);
            }
            em.persist(tablaSeccion);
            if (seccionMicrocurriculo != null) {
                seccionMicrocurriculo.setTablaSeccion(tablaSeccion);
                seccionMicrocurriculo = em.merge(seccionMicrocurriculo);
            }
            if (tablaId != null) {
                tablaId.getTablaSeccionList().add(tablaSeccion);
                tablaId = em.merge(tablaId);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTablaSeccion(tablaSeccion.getSeccionMicrocurriculoId()) != null) {
                throw new PreexistingEntityException("TablaSeccion " + tablaSeccion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TablaSeccion tablaSeccion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TablaSeccion persistentTablaSeccion = em.find(TablaSeccion.class, tablaSeccion.getSeccionMicrocurriculoId());
            SeccionMicrocurriculo seccionMicrocurriculoOld = persistentTablaSeccion.getSeccionMicrocurriculo();
            SeccionMicrocurriculo seccionMicrocurriculoNew = tablaSeccion.getSeccionMicrocurriculo();
            Tabla tablaIdOld = persistentTablaSeccion.getTablaId();
            Tabla tablaIdNew = tablaSeccion.getTablaId();
            List<String> illegalOrphanMessages = null;
            if (seccionMicrocurriculoNew != null && !seccionMicrocurriculoNew.equals(seccionMicrocurriculoOld)) {
                TablaSeccion oldTablaSeccionOfSeccionMicrocurriculo = seccionMicrocurriculoNew.getTablaSeccion();
                if (oldTablaSeccionOfSeccionMicrocurriculo != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The SeccionMicrocurriculo " + seccionMicrocurriculoNew + " already has an item of type TablaSeccion whose seccionMicrocurriculo column cannot be null. Please make another selection for the seccionMicrocurriculo field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (seccionMicrocurriculoNew != null) {
                seccionMicrocurriculoNew = em.getReference(seccionMicrocurriculoNew.getClass(), seccionMicrocurriculoNew.getId());
                tablaSeccion.setSeccionMicrocurriculo(seccionMicrocurriculoNew);
            }
            if (tablaIdNew != null) {
                tablaIdNew = em.getReference(tablaIdNew.getClass(), tablaIdNew.getId());
                tablaSeccion.setTablaId(tablaIdNew);
            }
            tablaSeccion = em.merge(tablaSeccion);
            if (seccionMicrocurriculoOld != null && !seccionMicrocurriculoOld.equals(seccionMicrocurriculoNew)) {
                seccionMicrocurriculoOld.setTablaSeccion(null);
                seccionMicrocurriculoOld = em.merge(seccionMicrocurriculoOld);
            }
            if (seccionMicrocurriculoNew != null && !seccionMicrocurriculoNew.equals(seccionMicrocurriculoOld)) {
                seccionMicrocurriculoNew.setTablaSeccion(tablaSeccion);
                seccionMicrocurriculoNew = em.merge(seccionMicrocurriculoNew);
            }
            if (tablaIdOld != null && !tablaIdOld.equals(tablaIdNew)) {
                tablaIdOld.getTablaSeccionList().remove(tablaSeccion);
                tablaIdOld = em.merge(tablaIdOld);
            }
            if (tablaIdNew != null && !tablaIdNew.equals(tablaIdOld)) {
                tablaIdNew.getTablaSeccionList().add(tablaSeccion);
                tablaIdNew = em.merge(tablaIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tablaSeccion.getSeccionMicrocurriculoId();
                if (findTablaSeccion(id) == null) {
                    throw new NonexistentEntityException("The tablaSeccion with id " + id + " no longer exists.");
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
            TablaSeccion tablaSeccion;
            try {
                tablaSeccion = em.getReference(TablaSeccion.class, id);
                tablaSeccion.getSeccionMicrocurriculoId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tablaSeccion with id " + id + " no longer exists.", enfe);
            }
            SeccionMicrocurriculo seccionMicrocurriculo = tablaSeccion.getSeccionMicrocurriculo();
            if (seccionMicrocurriculo != null) {
                seccionMicrocurriculo.setTablaSeccion(null);
                seccionMicrocurriculo = em.merge(seccionMicrocurriculo);
            }
            Tabla tablaId = tablaSeccion.getTablaId();
            if (tablaId != null) {
                tablaId.getTablaSeccionList().remove(tablaSeccion);
                tablaId = em.merge(tablaId);
            }
            em.remove(tablaSeccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TablaSeccion> findTablaSeccionEntities() {
        return findTablaSeccionEntities(true, -1, -1);
    }

    public List<TablaSeccion> findTablaSeccionEntities(int maxResults, int firstResult) {
        return findTablaSeccionEntities(false, maxResults, firstResult);
    }

    private List<TablaSeccion> findTablaSeccionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TablaSeccion.class));
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

    public TablaSeccion findTablaSeccion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TablaSeccion.class, id);
        } finally {
            em.close();
        }
    }

    public int getTablaSeccionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TablaSeccion> rt = cq.from(TablaSeccion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
