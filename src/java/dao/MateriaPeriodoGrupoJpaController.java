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
import dto.Docente;
import dto.MateriaPeriodo;
import dto.Cumplimiento;
import dto.MateriaPeriodoGrupo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Sachikia
 */
public class MateriaPeriodoGrupoJpaController implements Serializable {

    public MateriaPeriodoGrupoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MateriaPeriodoGrupo materiaPeriodoGrupo) {
        if (materiaPeriodoGrupo.getCumplimientoList() == null) {
            materiaPeriodoGrupo.setCumplimientoList(new ArrayList<Cumplimiento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Docente docenteCodigo = materiaPeriodoGrupo.getDocenteCodigo();
            if (docenteCodigo != null) {
                docenteCodigo = em.getReference(docenteCodigo.getClass(), docenteCodigo.getCodigo());
                materiaPeriodoGrupo.setDocenteCodigo(docenteCodigo);
            }
            MateriaPeriodo materiaPeriodoId = materiaPeriodoGrupo.getMateriaPeriodoId();
            if (materiaPeriodoId != null) {
                materiaPeriodoId = em.getReference(materiaPeriodoId.getClass(), materiaPeriodoId.getId());
                materiaPeriodoGrupo.setMateriaPeriodoId(materiaPeriodoId);
            }
            List<Cumplimiento> attachedCumplimientoList = new ArrayList<Cumplimiento>();
            for (Cumplimiento cumplimientoListCumplimientoToAttach : materiaPeriodoGrupo.getCumplimientoList()) {
                cumplimientoListCumplimientoToAttach = em.getReference(cumplimientoListCumplimientoToAttach.getClass(), cumplimientoListCumplimientoToAttach.getCumplimientoPK());
                attachedCumplimientoList.add(cumplimientoListCumplimientoToAttach);
            }
            materiaPeriodoGrupo.setCumplimientoList(attachedCumplimientoList);
            em.persist(materiaPeriodoGrupo);
            if (docenteCodigo != null) {
                docenteCodigo.getMateriaPeriodoGrupoList().add(materiaPeriodoGrupo);
                docenteCodigo = em.merge(docenteCodigo);
            }
            if (materiaPeriodoId != null) {
                materiaPeriodoId.getMateriaPeriodoGrupoList().add(materiaPeriodoGrupo);
                materiaPeriodoId = em.merge(materiaPeriodoId);
            }
            for (Cumplimiento cumplimientoListCumplimiento : materiaPeriodoGrupo.getCumplimientoList()) {
                MateriaPeriodoGrupo oldMateriaPeriodoGrupoOfCumplimientoListCumplimiento = cumplimientoListCumplimiento.getMateriaPeriodoGrupo();
                cumplimientoListCumplimiento.setMateriaPeriodoGrupo(materiaPeriodoGrupo);
                cumplimientoListCumplimiento = em.merge(cumplimientoListCumplimiento);
                if (oldMateriaPeriodoGrupoOfCumplimientoListCumplimiento != null) {
                    oldMateriaPeriodoGrupoOfCumplimientoListCumplimiento.getCumplimientoList().remove(cumplimientoListCumplimiento);
                    oldMateriaPeriodoGrupoOfCumplimientoListCumplimiento = em.merge(oldMateriaPeriodoGrupoOfCumplimientoListCumplimiento);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MateriaPeriodoGrupo materiaPeriodoGrupo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MateriaPeriodoGrupo persistentMateriaPeriodoGrupo = em.find(MateriaPeriodoGrupo.class, materiaPeriodoGrupo.getId());
            Docente docenteCodigoOld = persistentMateriaPeriodoGrupo.getDocenteCodigo();
            Docente docenteCodigoNew = materiaPeriodoGrupo.getDocenteCodigo();
            MateriaPeriodo materiaPeriodoIdOld = persistentMateriaPeriodoGrupo.getMateriaPeriodoId();
            MateriaPeriodo materiaPeriodoIdNew = materiaPeriodoGrupo.getMateriaPeriodoId();
            List<Cumplimiento> cumplimientoListOld = persistentMateriaPeriodoGrupo.getCumplimientoList();
            List<Cumplimiento> cumplimientoListNew = materiaPeriodoGrupo.getCumplimientoList();
            List<String> illegalOrphanMessages = null;
            for (Cumplimiento cumplimientoListOldCumplimiento : cumplimientoListOld) {
                if (!cumplimientoListNew.contains(cumplimientoListOldCumplimiento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Cumplimiento " + cumplimientoListOldCumplimiento + " since its materiaPeriodoGrupo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (docenteCodigoNew != null) {
                docenteCodigoNew = em.getReference(docenteCodigoNew.getClass(), docenteCodigoNew.getCodigo());
                materiaPeriodoGrupo.setDocenteCodigo(docenteCodigoNew);
            }
            if (materiaPeriodoIdNew != null) {
                materiaPeriodoIdNew = em.getReference(materiaPeriodoIdNew.getClass(), materiaPeriodoIdNew.getId());
                materiaPeriodoGrupo.setMateriaPeriodoId(materiaPeriodoIdNew);
            }
            List<Cumplimiento> attachedCumplimientoListNew = new ArrayList<Cumplimiento>();
            for (Cumplimiento cumplimientoListNewCumplimientoToAttach : cumplimientoListNew) {
                cumplimientoListNewCumplimientoToAttach = em.getReference(cumplimientoListNewCumplimientoToAttach.getClass(), cumplimientoListNewCumplimientoToAttach.getCumplimientoPK());
                attachedCumplimientoListNew.add(cumplimientoListNewCumplimientoToAttach);
            }
            cumplimientoListNew = attachedCumplimientoListNew;
            materiaPeriodoGrupo.setCumplimientoList(cumplimientoListNew);
            materiaPeriodoGrupo = em.merge(materiaPeriodoGrupo);
            if (docenteCodigoOld != null && !docenteCodigoOld.equals(docenteCodigoNew)) {
                docenteCodigoOld.getMateriaPeriodoGrupoList().remove(materiaPeriodoGrupo);
                docenteCodigoOld = em.merge(docenteCodigoOld);
            }
            if (docenteCodigoNew != null && !docenteCodigoNew.equals(docenteCodigoOld)) {
                docenteCodigoNew.getMateriaPeriodoGrupoList().add(materiaPeriodoGrupo);
                docenteCodigoNew = em.merge(docenteCodigoNew);
            }
            if (materiaPeriodoIdOld != null && !materiaPeriodoIdOld.equals(materiaPeriodoIdNew)) {
                materiaPeriodoIdOld.getMateriaPeriodoGrupoList().remove(materiaPeriodoGrupo);
                materiaPeriodoIdOld = em.merge(materiaPeriodoIdOld);
            }
            if (materiaPeriodoIdNew != null && !materiaPeriodoIdNew.equals(materiaPeriodoIdOld)) {
                materiaPeriodoIdNew.getMateriaPeriodoGrupoList().add(materiaPeriodoGrupo);
                materiaPeriodoIdNew = em.merge(materiaPeriodoIdNew);
            }
            for (Cumplimiento cumplimientoListNewCumplimiento : cumplimientoListNew) {
                if (!cumplimientoListOld.contains(cumplimientoListNewCumplimiento)) {
                    MateriaPeriodoGrupo oldMateriaPeriodoGrupoOfCumplimientoListNewCumplimiento = cumplimientoListNewCumplimiento.getMateriaPeriodoGrupo();
                    cumplimientoListNewCumplimiento.setMateriaPeriodoGrupo(materiaPeriodoGrupo);
                    cumplimientoListNewCumplimiento = em.merge(cumplimientoListNewCumplimiento);
                    if (oldMateriaPeriodoGrupoOfCumplimientoListNewCumplimiento != null && !oldMateriaPeriodoGrupoOfCumplimientoListNewCumplimiento.equals(materiaPeriodoGrupo)) {
                        oldMateriaPeriodoGrupoOfCumplimientoListNewCumplimiento.getCumplimientoList().remove(cumplimientoListNewCumplimiento);
                        oldMateriaPeriodoGrupoOfCumplimientoListNewCumplimiento = em.merge(oldMateriaPeriodoGrupoOfCumplimientoListNewCumplimiento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = materiaPeriodoGrupo.getId();
                if (findMateriaPeriodoGrupo(id) == null) {
                    throw new NonexistentEntityException("The materiaPeriodoGrupo with id " + id + " no longer exists.");
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
            MateriaPeriodoGrupo materiaPeriodoGrupo;
            try {
                materiaPeriodoGrupo = em.getReference(MateriaPeriodoGrupo.class, id);
                materiaPeriodoGrupo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The materiaPeriodoGrupo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Cumplimiento> cumplimientoListOrphanCheck = materiaPeriodoGrupo.getCumplimientoList();
            for (Cumplimiento cumplimientoListOrphanCheckCumplimiento : cumplimientoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This MateriaPeriodoGrupo (" + materiaPeriodoGrupo + ") cannot be destroyed since the Cumplimiento " + cumplimientoListOrphanCheckCumplimiento + " in its cumplimientoList field has a non-nullable materiaPeriodoGrupo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Docente docenteCodigo = materiaPeriodoGrupo.getDocenteCodigo();
            if (docenteCodigo != null) {
                docenteCodigo.getMateriaPeriodoGrupoList().remove(materiaPeriodoGrupo);
                docenteCodigo = em.merge(docenteCodigo);
            }
            MateriaPeriodo materiaPeriodoId = materiaPeriodoGrupo.getMateriaPeriodoId();
            if (materiaPeriodoId != null) {
                materiaPeriodoId.getMateriaPeriodoGrupoList().remove(materiaPeriodoGrupo);
                materiaPeriodoId = em.merge(materiaPeriodoId);
            }
            em.remove(materiaPeriodoGrupo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MateriaPeriodoGrupo> findMateriaPeriodoGrupoEntities() {
        return findMateriaPeriodoGrupoEntities(true, -1, -1);
    }

    public List<MateriaPeriodoGrupo> findMateriaPeriodoGrupoEntities(int maxResults, int firstResult) {
        return findMateriaPeriodoGrupoEntities(false, maxResults, firstResult);
    }

    private List<MateriaPeriodoGrupo> findMateriaPeriodoGrupoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MateriaPeriodoGrupo.class));
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

    public MateriaPeriodoGrupo findMateriaPeriodoGrupo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MateriaPeriodoGrupo.class, id);
        } finally {
            em.close();
        }
    }

    public int getMateriaPeriodoGrupoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MateriaPeriodoGrupo> rt = cq.from(MateriaPeriodoGrupo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
