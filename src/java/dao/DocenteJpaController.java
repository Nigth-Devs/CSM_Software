/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.exceptions.IllegalOrphanException;
import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import dto.Docente;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import dto.Programa;
import dto.Usuario;
import dto.MateriaPeriodoGrupo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Sachikia
 */
public class DocenteJpaController implements Serializable {

    public DocenteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Docente docente) throws PreexistingEntityException, Exception {
        if (docente.getMateriaPeriodoGrupoList() == null) {
            docente.setMateriaPeriodoGrupoList(new ArrayList<MateriaPeriodoGrupo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Programa programaCodigo = docente.getProgramaCodigo();
            if (programaCodigo != null) {
                programaCodigo = em.getReference(programaCodigo.getClass(), programaCodigo.getCodigo());
                docente.setProgramaCodigo(programaCodigo);
            }
            Usuario usuario = docente.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getDocenteCodigo());
                docente.setUsuario(usuario);
            }
            List<MateriaPeriodoGrupo> attachedMateriaPeriodoGrupoList = new ArrayList<MateriaPeriodoGrupo>();
            for (MateriaPeriodoGrupo materiaPeriodoGrupoListMateriaPeriodoGrupoToAttach : docente.getMateriaPeriodoGrupoList()) {
                materiaPeriodoGrupoListMateriaPeriodoGrupoToAttach = em.getReference(materiaPeriodoGrupoListMateriaPeriodoGrupoToAttach.getClass(), materiaPeriodoGrupoListMateriaPeriodoGrupoToAttach.getId());
                attachedMateriaPeriodoGrupoList.add(materiaPeriodoGrupoListMateriaPeriodoGrupoToAttach);
            }
            docente.setMateriaPeriodoGrupoList(attachedMateriaPeriodoGrupoList);
            em.persist(docente);
            if (programaCodigo != null) {
                programaCodigo.getDocenteList().add(docente);
                programaCodigo = em.merge(programaCodigo);
            }
            if (usuario != null) {
                Docente oldDocenteOfUsuario = usuario.getDocente();
                if (oldDocenteOfUsuario != null) {
                    oldDocenteOfUsuario.setUsuario(null);
                    oldDocenteOfUsuario = em.merge(oldDocenteOfUsuario);
                }
                usuario.setDocente(docente);
                usuario = em.merge(usuario);
            }
            for (MateriaPeriodoGrupo materiaPeriodoGrupoListMateriaPeriodoGrupo : docente.getMateriaPeriodoGrupoList()) {
                Docente oldDocenteCodigoOfMateriaPeriodoGrupoListMateriaPeriodoGrupo = materiaPeriodoGrupoListMateriaPeriodoGrupo.getDocenteCodigo();
                materiaPeriodoGrupoListMateriaPeriodoGrupo.setDocenteCodigo(docente);
                materiaPeriodoGrupoListMateriaPeriodoGrupo = em.merge(materiaPeriodoGrupoListMateriaPeriodoGrupo);
                if (oldDocenteCodigoOfMateriaPeriodoGrupoListMateriaPeriodoGrupo != null) {
                    oldDocenteCodigoOfMateriaPeriodoGrupoListMateriaPeriodoGrupo.getMateriaPeriodoGrupoList().remove(materiaPeriodoGrupoListMateriaPeriodoGrupo);
                    oldDocenteCodigoOfMateriaPeriodoGrupoListMateriaPeriodoGrupo = em.merge(oldDocenteCodigoOfMateriaPeriodoGrupoListMateriaPeriodoGrupo);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDocente(docente.getCodigo()) != null) {
                throw new PreexistingEntityException("Docente " + docente + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Docente docente) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Docente persistentDocente = em.find(Docente.class, docente.getCodigo());
            Programa programaCodigoOld = persistentDocente.getProgramaCodigo();
            Programa programaCodigoNew = docente.getProgramaCodigo();
            Usuario usuarioOld = persistentDocente.getUsuario();
            Usuario usuarioNew = docente.getUsuario();
            List<MateriaPeriodoGrupo> materiaPeriodoGrupoListOld = persistentDocente.getMateriaPeriodoGrupoList();
            List<MateriaPeriodoGrupo> materiaPeriodoGrupoListNew = docente.getMateriaPeriodoGrupoList();
            List<String> illegalOrphanMessages = null;
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Usuario " + usuarioOld + " since its docente field is not nullable.");
            }
            for (MateriaPeriodoGrupo materiaPeriodoGrupoListOldMateriaPeriodoGrupo : materiaPeriodoGrupoListOld) {
                if (!materiaPeriodoGrupoListNew.contains(materiaPeriodoGrupoListOldMateriaPeriodoGrupo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain MateriaPeriodoGrupo " + materiaPeriodoGrupoListOldMateriaPeriodoGrupo + " since its docenteCodigo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (programaCodigoNew != null) {
                programaCodigoNew = em.getReference(programaCodigoNew.getClass(), programaCodigoNew.getCodigo());
                docente.setProgramaCodigo(programaCodigoNew);
            }
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getDocenteCodigo());
                docente.setUsuario(usuarioNew);
            }
            List<MateriaPeriodoGrupo> attachedMateriaPeriodoGrupoListNew = new ArrayList<MateriaPeriodoGrupo>();
            for (MateriaPeriodoGrupo materiaPeriodoGrupoListNewMateriaPeriodoGrupoToAttach : materiaPeriodoGrupoListNew) {
                materiaPeriodoGrupoListNewMateriaPeriodoGrupoToAttach = em.getReference(materiaPeriodoGrupoListNewMateriaPeriodoGrupoToAttach.getClass(), materiaPeriodoGrupoListNewMateriaPeriodoGrupoToAttach.getId());
                attachedMateriaPeriodoGrupoListNew.add(materiaPeriodoGrupoListNewMateriaPeriodoGrupoToAttach);
            }
            materiaPeriodoGrupoListNew = attachedMateriaPeriodoGrupoListNew;
            docente.setMateriaPeriodoGrupoList(materiaPeriodoGrupoListNew);
            docente = em.merge(docente);
            if (programaCodigoOld != null && !programaCodigoOld.equals(programaCodigoNew)) {
                programaCodigoOld.getDocenteList().remove(docente);
                programaCodigoOld = em.merge(programaCodigoOld);
            }
            if (programaCodigoNew != null && !programaCodigoNew.equals(programaCodigoOld)) {
                programaCodigoNew.getDocenteList().add(docente);
                programaCodigoNew = em.merge(programaCodigoNew);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                Docente oldDocenteOfUsuario = usuarioNew.getDocente();
                if (oldDocenteOfUsuario != null) {
                    oldDocenteOfUsuario.setUsuario(null);
                    oldDocenteOfUsuario = em.merge(oldDocenteOfUsuario);
                }
                usuarioNew.setDocente(docente);
                usuarioNew = em.merge(usuarioNew);
            }
            for (MateriaPeriodoGrupo materiaPeriodoGrupoListNewMateriaPeriodoGrupo : materiaPeriodoGrupoListNew) {
                if (!materiaPeriodoGrupoListOld.contains(materiaPeriodoGrupoListNewMateriaPeriodoGrupo)) {
                    Docente oldDocenteCodigoOfMateriaPeriodoGrupoListNewMateriaPeriodoGrupo = materiaPeriodoGrupoListNewMateriaPeriodoGrupo.getDocenteCodigo();
                    materiaPeriodoGrupoListNewMateriaPeriodoGrupo.setDocenteCodigo(docente);
                    materiaPeriodoGrupoListNewMateriaPeriodoGrupo = em.merge(materiaPeriodoGrupoListNewMateriaPeriodoGrupo);
                    if (oldDocenteCodigoOfMateriaPeriodoGrupoListNewMateriaPeriodoGrupo != null && !oldDocenteCodigoOfMateriaPeriodoGrupoListNewMateriaPeriodoGrupo.equals(docente)) {
                        oldDocenteCodigoOfMateriaPeriodoGrupoListNewMateriaPeriodoGrupo.getMateriaPeriodoGrupoList().remove(materiaPeriodoGrupoListNewMateriaPeriodoGrupo);
                        oldDocenteCodigoOfMateriaPeriodoGrupoListNewMateriaPeriodoGrupo = em.merge(oldDocenteCodigoOfMateriaPeriodoGrupoListNewMateriaPeriodoGrupo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = docente.getCodigo();
                if (findDocente(id) == null) {
                    throw new NonexistentEntityException("The docente with id " + id + " no longer exists.");
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
            Docente docente;
            try {
                docente = em.getReference(Docente.class, id);
                docente.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The docente with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Usuario usuarioOrphanCheck = docente.getUsuario();
            if (usuarioOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Docente (" + docente + ") cannot be destroyed since the Usuario " + usuarioOrphanCheck + " in its usuario field has a non-nullable docente field.");
            }
            List<MateriaPeriodoGrupo> materiaPeriodoGrupoListOrphanCheck = docente.getMateriaPeriodoGrupoList();
            for (MateriaPeriodoGrupo materiaPeriodoGrupoListOrphanCheckMateriaPeriodoGrupo : materiaPeriodoGrupoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Docente (" + docente + ") cannot be destroyed since the MateriaPeriodoGrupo " + materiaPeriodoGrupoListOrphanCheckMateriaPeriodoGrupo + " in its materiaPeriodoGrupoList field has a non-nullable docenteCodigo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Programa programaCodigo = docente.getProgramaCodigo();
            if (programaCodigo != null) {
                programaCodigo.getDocenteList().remove(docente);
                programaCodigo = em.merge(programaCodigo);
            }
            em.remove(docente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Docente> findDocenteEntities() {
        return findDocenteEntities(true, -1, -1);
    }

    public List<Docente> findDocenteEntities(int maxResults, int firstResult) {
        return findDocenteEntities(false, maxResults, firstResult);
    }

    private List<Docente> findDocenteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Docente.class));
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

    public Docente findDocente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Docente.class, id);
        } finally {
            em.close();
        }
    }

    public int getDocenteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Docente> rt = cq.from(Docente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
