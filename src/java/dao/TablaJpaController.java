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
import dto.Encabezado;
import dto.Tabla;
import java.util.ArrayList;
import java.util.List;
import dto.TablaSeccion;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Sachikia
 */
public class TablaJpaController implements Serializable {

    public TablaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tabla tabla) {
        if (tabla.getEncabezadoList() == null) {
            tabla.setEncabezadoList(new ArrayList<Encabezado>());
        }
        if (tabla.getTablaSeccionList() == null) {
            tabla.setTablaSeccionList(new ArrayList<TablaSeccion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Encabezado> attachedEncabezadoList = new ArrayList<Encabezado>();
            for (Encabezado encabezadoListEncabezadoToAttach : tabla.getEncabezadoList()) {
                encabezadoListEncabezadoToAttach = em.getReference(encabezadoListEncabezadoToAttach.getClass(), encabezadoListEncabezadoToAttach.getEncabezadoPK());
                attachedEncabezadoList.add(encabezadoListEncabezadoToAttach);
            }
            tabla.setEncabezadoList(attachedEncabezadoList);
            List<TablaSeccion> attachedTablaSeccionList = new ArrayList<TablaSeccion>();
            for (TablaSeccion tablaSeccionListTablaSeccionToAttach : tabla.getTablaSeccionList()) {
                tablaSeccionListTablaSeccionToAttach = em.getReference(tablaSeccionListTablaSeccionToAttach.getClass(), tablaSeccionListTablaSeccionToAttach.getSeccionMicrocurriculoId());
                attachedTablaSeccionList.add(tablaSeccionListTablaSeccionToAttach);
            }
            tabla.setTablaSeccionList(attachedTablaSeccionList);
            em.persist(tabla);
            for (Encabezado encabezadoListEncabezado : tabla.getEncabezadoList()) {
                Tabla oldTablaOfEncabezadoListEncabezado = encabezadoListEncabezado.getTabla();
                encabezadoListEncabezado.setTabla(tabla);
                encabezadoListEncabezado = em.merge(encabezadoListEncabezado);
                if (oldTablaOfEncabezadoListEncabezado != null) {
                    oldTablaOfEncabezadoListEncabezado.getEncabezadoList().remove(encabezadoListEncabezado);
                    oldTablaOfEncabezadoListEncabezado = em.merge(oldTablaOfEncabezadoListEncabezado);
                }
            }
            for (TablaSeccion tablaSeccionListTablaSeccion : tabla.getTablaSeccionList()) {
                Tabla oldTablaIdOfTablaSeccionListTablaSeccion = tablaSeccionListTablaSeccion.getTablaId();
                tablaSeccionListTablaSeccion.setTablaId(tabla);
                tablaSeccionListTablaSeccion = em.merge(tablaSeccionListTablaSeccion);
                if (oldTablaIdOfTablaSeccionListTablaSeccion != null) {
                    oldTablaIdOfTablaSeccionListTablaSeccion.getTablaSeccionList().remove(tablaSeccionListTablaSeccion);
                    oldTablaIdOfTablaSeccionListTablaSeccion = em.merge(oldTablaIdOfTablaSeccionListTablaSeccion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tabla tabla) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tabla persistentTabla = em.find(Tabla.class, tabla.getId());
            List<Encabezado> encabezadoListOld = persistentTabla.getEncabezadoList();
            List<Encabezado> encabezadoListNew = tabla.getEncabezadoList();
            List<TablaSeccion> tablaSeccionListOld = persistentTabla.getTablaSeccionList();
            List<TablaSeccion> tablaSeccionListNew = tabla.getTablaSeccionList();
            List<String> illegalOrphanMessages = null;
            for (Encabezado encabezadoListOldEncabezado : encabezadoListOld) {
                if (!encabezadoListNew.contains(encabezadoListOldEncabezado)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Encabezado " + encabezadoListOldEncabezado + " since its tabla field is not nullable.");
                }
            }
            for (TablaSeccion tablaSeccionListOldTablaSeccion : tablaSeccionListOld) {
                if (!tablaSeccionListNew.contains(tablaSeccionListOldTablaSeccion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TablaSeccion " + tablaSeccionListOldTablaSeccion + " since its tablaId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Encabezado> attachedEncabezadoListNew = new ArrayList<Encabezado>();
            for (Encabezado encabezadoListNewEncabezadoToAttach : encabezadoListNew) {
                encabezadoListNewEncabezadoToAttach = em.getReference(encabezadoListNewEncabezadoToAttach.getClass(), encabezadoListNewEncabezadoToAttach.getEncabezadoPK());
                attachedEncabezadoListNew.add(encabezadoListNewEncabezadoToAttach);
            }
            encabezadoListNew = attachedEncabezadoListNew;
            tabla.setEncabezadoList(encabezadoListNew);
            List<TablaSeccion> attachedTablaSeccionListNew = new ArrayList<TablaSeccion>();
            for (TablaSeccion tablaSeccionListNewTablaSeccionToAttach : tablaSeccionListNew) {
                tablaSeccionListNewTablaSeccionToAttach = em.getReference(tablaSeccionListNewTablaSeccionToAttach.getClass(), tablaSeccionListNewTablaSeccionToAttach.getSeccionMicrocurriculoId());
                attachedTablaSeccionListNew.add(tablaSeccionListNewTablaSeccionToAttach);
            }
            tablaSeccionListNew = attachedTablaSeccionListNew;
            tabla.setTablaSeccionList(tablaSeccionListNew);
            tabla = em.merge(tabla);
            for (Encabezado encabezadoListNewEncabezado : encabezadoListNew) {
                if (!encabezadoListOld.contains(encabezadoListNewEncabezado)) {
                    Tabla oldTablaOfEncabezadoListNewEncabezado = encabezadoListNewEncabezado.getTabla();
                    encabezadoListNewEncabezado.setTabla(tabla);
                    encabezadoListNewEncabezado = em.merge(encabezadoListNewEncabezado);
                    if (oldTablaOfEncabezadoListNewEncabezado != null && !oldTablaOfEncabezadoListNewEncabezado.equals(tabla)) {
                        oldTablaOfEncabezadoListNewEncabezado.getEncabezadoList().remove(encabezadoListNewEncabezado);
                        oldTablaOfEncabezadoListNewEncabezado = em.merge(oldTablaOfEncabezadoListNewEncabezado);
                    }
                }
            }
            for (TablaSeccion tablaSeccionListNewTablaSeccion : tablaSeccionListNew) {
                if (!tablaSeccionListOld.contains(tablaSeccionListNewTablaSeccion)) {
                    Tabla oldTablaIdOfTablaSeccionListNewTablaSeccion = tablaSeccionListNewTablaSeccion.getTablaId();
                    tablaSeccionListNewTablaSeccion.setTablaId(tabla);
                    tablaSeccionListNewTablaSeccion = em.merge(tablaSeccionListNewTablaSeccion);
                    if (oldTablaIdOfTablaSeccionListNewTablaSeccion != null && !oldTablaIdOfTablaSeccionListNewTablaSeccion.equals(tabla)) {
                        oldTablaIdOfTablaSeccionListNewTablaSeccion.getTablaSeccionList().remove(tablaSeccionListNewTablaSeccion);
                        oldTablaIdOfTablaSeccionListNewTablaSeccion = em.merge(oldTablaIdOfTablaSeccionListNewTablaSeccion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tabla.getId();
                if (findTabla(id) == null) {
                    throw new NonexistentEntityException("The tabla with id " + id + " no longer exists.");
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
            Tabla tabla;
            try {
                tabla = em.getReference(Tabla.class, id);
                tabla.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tabla with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Encabezado> encabezadoListOrphanCheck = tabla.getEncabezadoList();
            for (Encabezado encabezadoListOrphanCheckEncabezado : encabezadoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tabla (" + tabla + ") cannot be destroyed since the Encabezado " + encabezadoListOrphanCheckEncabezado + " in its encabezadoList field has a non-nullable tabla field.");
            }
            List<TablaSeccion> tablaSeccionListOrphanCheck = tabla.getTablaSeccionList();
            for (TablaSeccion tablaSeccionListOrphanCheckTablaSeccion : tablaSeccionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tabla (" + tabla + ") cannot be destroyed since the TablaSeccion " + tablaSeccionListOrphanCheckTablaSeccion + " in its tablaSeccionList field has a non-nullable tablaId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tabla);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tabla> findTablaEntities() {
        return findTablaEntities(true, -1, -1);
    }

    public List<Tabla> findTablaEntities(int maxResults, int firstResult) {
        return findTablaEntities(false, maxResults, firstResult);
    }

    private List<Tabla> findTablaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tabla.class));
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

    public Tabla findTabla(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tabla.class, id);
        } finally {
            em.close();
        }
    }

    public int getTablaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tabla> rt = cq.from(Tabla.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
