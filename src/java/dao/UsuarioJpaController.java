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
import dto.Rol;
import dto.Docente;
import dto.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Sachikia
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        Docente docenteOrphanCheck = usuario.getDocente();
        if (docenteOrphanCheck != null) {
            Usuario oldUsuarioOfDocente = docenteOrphanCheck.getUsuario();
            if (oldUsuarioOfDocente != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Docente " + docenteOrphanCheck + " already has an item of type Usuario whose docente column cannot be null. Please make another selection for the docente field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rol rolId = usuario.getRolId();
            if (rolId != null) {
                rolId = em.getReference(rolId.getClass(), rolId.getId());
                usuario.setRolId(rolId);
            }
            Docente docente = usuario.getDocente();
            if (docente != null) {
                docente = em.getReference(docente.getClass(), docente.getCodigo());
                usuario.setDocente(docente);
            }
            em.persist(usuario);
            if (rolId != null) {
                rolId.getUsuarioList().add(usuario);
                rolId = em.merge(rolId);
            }
            if (docente != null) {
                docente.setUsuario(usuario);
                docente = em.merge(docente);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUsuario(usuario.getDocenteCodigo()) != null) {
                throw new PreexistingEntityException("Usuario " + usuario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getDocenteCodigo());
            Rol rolIdOld = persistentUsuario.getRolId();
            Rol rolIdNew = usuario.getRolId();
            Docente docenteOld = persistentUsuario.getDocente();
            Docente docenteNew = usuario.getDocente();
            List<String> illegalOrphanMessages = null;
            if (docenteNew != null && !docenteNew.equals(docenteOld)) {
                Usuario oldUsuarioOfDocente = docenteNew.getUsuario();
                if (oldUsuarioOfDocente != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Docente " + docenteNew + " already has an item of type Usuario whose docente column cannot be null. Please make another selection for the docente field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (rolIdNew != null) {
                rolIdNew = em.getReference(rolIdNew.getClass(), rolIdNew.getId());
                usuario.setRolId(rolIdNew);
            }
            if (docenteNew != null) {
                docenteNew = em.getReference(docenteNew.getClass(), docenteNew.getCodigo());
                usuario.setDocente(docenteNew);
            }
            usuario = em.merge(usuario);
            if (rolIdOld != null && !rolIdOld.equals(rolIdNew)) {
                rolIdOld.getUsuarioList().remove(usuario);
                rolIdOld = em.merge(rolIdOld);
            }
            if (rolIdNew != null && !rolIdNew.equals(rolIdOld)) {
                rolIdNew.getUsuarioList().add(usuario);
                rolIdNew = em.merge(rolIdNew);
            }
            if (docenteOld != null && !docenteOld.equals(docenteNew)) {
                docenteOld.setUsuario(null);
                docenteOld = em.merge(docenteOld);
            }
            if (docenteNew != null && !docenteNew.equals(docenteOld)) {
                docenteNew.setUsuario(usuario);
                docenteNew = em.merge(docenteNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuario.getDocenteCodigo();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
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
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getDocenteCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            Rol rolId = usuario.getRolId();
            if (rolId != null) {
                rolId.getUsuarioList().remove(usuario);
                rolId = em.merge(rolId);
            }
            Docente docente = usuario.getDocente();
            if (docente != null) {
                docente.setUsuario(null);
                docente = em.merge(docente);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
