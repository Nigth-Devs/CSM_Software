/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import dto.Encabezado;
import dto.EncabezadoPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import dto.Tabla;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Sachikia
 */
public class EncabezadoJpaController implements Serializable {

    public EncabezadoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Encabezado encabezado) throws PreexistingEntityException, Exception {
        if (encabezado.getEncabezadoPK() == null) {
            encabezado.setEncabezadoPK(new EncabezadoPK());
        }
        encabezado.getEncabezadoPK().setTablaId(encabezado.getTabla().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tabla tabla = encabezado.getTabla();
            if (tabla != null) {
                tabla = em.getReference(tabla.getClass(), tabla.getId());
                encabezado.setTabla(tabla);
            }
            em.persist(encabezado);
            if (tabla != null) {
                tabla.getEncabezadoList().add(encabezado);
                tabla = em.merge(tabla);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEncabezado(encabezado.getEncabezadoPK()) != null) {
                throw new PreexistingEntityException("Encabezado " + encabezado + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Encabezado encabezado) throws NonexistentEntityException, Exception {
        encabezado.getEncabezadoPK().setTablaId(encabezado.getTabla().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Encabezado persistentEncabezado = em.find(Encabezado.class, encabezado.getEncabezadoPK());
            Tabla tablaOld = persistentEncabezado.getTabla();
            Tabla tablaNew = encabezado.getTabla();
            if (tablaNew != null) {
                tablaNew = em.getReference(tablaNew.getClass(), tablaNew.getId());
                encabezado.setTabla(tablaNew);
            }
            encabezado = em.merge(encabezado);
            if (tablaOld != null && !tablaOld.equals(tablaNew)) {
                tablaOld.getEncabezadoList().remove(encabezado);
                tablaOld = em.merge(tablaOld);
            }
            if (tablaNew != null && !tablaNew.equals(tablaOld)) {
                tablaNew.getEncabezadoList().add(encabezado);
                tablaNew = em.merge(tablaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                EncabezadoPK id = encabezado.getEncabezadoPK();
                if (findEncabezado(id) == null) {
                    throw new NonexistentEntityException("The encabezado with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(EncabezadoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Encabezado encabezado;
            try {
                encabezado = em.getReference(Encabezado.class, id);
                encabezado.getEncabezadoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The encabezado with id " + id + " no longer exists.", enfe);
            }
            Tabla tabla = encabezado.getTabla();
            if (tabla != null) {
                tabla.getEncabezadoList().remove(encabezado);
                tabla = em.merge(tabla);
            }
            em.remove(encabezado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Encabezado> findEncabezadoEntities() {
        return findEncabezadoEntities(true, -1, -1);
    }

    public List<Encabezado> findEncabezadoEntities(int maxResults, int firstResult) {
        return findEncabezadoEntities(false, maxResults, firstResult);
    }

    private List<Encabezado> findEncabezadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Encabezado.class));
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

    public Encabezado findEncabezado(EncabezadoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Encabezado.class, id);
        } finally {
            em.close();
        }
    }

    public int getEncabezadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Encabezado> rt = cq.from(Encabezado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
