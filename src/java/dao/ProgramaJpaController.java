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
import dto.Departamento;
import dto.Docente;
import java.util.ArrayList;
import java.util.List;
import dto.Pensum;
import dto.Programa;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Sachikia
 */
public class ProgramaJpaController implements Serializable {

    public ProgramaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Programa programa) throws PreexistingEntityException, Exception {
        if (programa.getDocenteList() == null) {
            programa.setDocenteList(new ArrayList<Docente>());
        }
        if (programa.getPensumList() == null) {
            programa.setPensumList(new ArrayList<Pensum>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Departamento departamentoId = programa.getDepartamentoId();
            if (departamentoId != null) {
                departamentoId = em.getReference(departamentoId.getClass(), departamentoId.getId());
                programa.setDepartamentoId(departamentoId);
            }
            List<Docente> attachedDocenteList = new ArrayList<Docente>();
            for (Docente docenteListDocenteToAttach : programa.getDocenteList()) {
                docenteListDocenteToAttach = em.getReference(docenteListDocenteToAttach.getClass(), docenteListDocenteToAttach.getCodigo());
                attachedDocenteList.add(docenteListDocenteToAttach);
            }
            programa.setDocenteList(attachedDocenteList);
            List<Pensum> attachedPensumList = new ArrayList<Pensum>();
            for (Pensum pensumListPensumToAttach : programa.getPensumList()) {
                pensumListPensumToAttach = em.getReference(pensumListPensumToAttach.getClass(), pensumListPensumToAttach.getPensumPK());
                attachedPensumList.add(pensumListPensumToAttach);
            }
            programa.setPensumList(attachedPensumList);
            em.persist(programa);
            if (departamentoId != null) {
                departamentoId.getProgramaList().add(programa);
                departamentoId = em.merge(departamentoId);
            }
            for (Docente docenteListDocente : programa.getDocenteList()) {
                Programa oldProgramaCodigoOfDocenteListDocente = docenteListDocente.getProgramaCodigo();
                docenteListDocente.setProgramaCodigo(programa);
                docenteListDocente = em.merge(docenteListDocente);
                if (oldProgramaCodigoOfDocenteListDocente != null) {
                    oldProgramaCodigoOfDocenteListDocente.getDocenteList().remove(docenteListDocente);
                    oldProgramaCodigoOfDocenteListDocente = em.merge(oldProgramaCodigoOfDocenteListDocente);
                }
            }
            for (Pensum pensumListPensum : programa.getPensumList()) {
                Programa oldProgramaOfPensumListPensum = pensumListPensum.getPrograma();
                pensumListPensum.setPrograma(programa);
                pensumListPensum = em.merge(pensumListPensum);
                if (oldProgramaOfPensumListPensum != null) {
                    oldProgramaOfPensumListPensum.getPensumList().remove(pensumListPensum);
                    oldProgramaOfPensumListPensum = em.merge(oldProgramaOfPensumListPensum);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPrograma(programa.getCodigo()) != null) {
                throw new PreexistingEntityException("Programa " + programa + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Programa programa) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Programa persistentPrograma = em.find(Programa.class, programa.getCodigo());
            Departamento departamentoIdOld = persistentPrograma.getDepartamentoId();
            Departamento departamentoIdNew = programa.getDepartamentoId();
            List<Docente> docenteListOld = persistentPrograma.getDocenteList();
            List<Docente> docenteListNew = programa.getDocenteList();
            List<Pensum> pensumListOld = persistentPrograma.getPensumList();
            List<Pensum> pensumListNew = programa.getPensumList();
            List<String> illegalOrphanMessages = null;
            for (Docente docenteListOldDocente : docenteListOld) {
                if (!docenteListNew.contains(docenteListOldDocente)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Docente " + docenteListOldDocente + " since its programaCodigo field is not nullable.");
                }
            }
            for (Pensum pensumListOldPensum : pensumListOld) {
                if (!pensumListNew.contains(pensumListOldPensum)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Pensum " + pensumListOldPensum + " since its programa field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (departamentoIdNew != null) {
                departamentoIdNew = em.getReference(departamentoIdNew.getClass(), departamentoIdNew.getId());
                programa.setDepartamentoId(departamentoIdNew);
            }
            List<Docente> attachedDocenteListNew = new ArrayList<Docente>();
            for (Docente docenteListNewDocenteToAttach : docenteListNew) {
                docenteListNewDocenteToAttach = em.getReference(docenteListNewDocenteToAttach.getClass(), docenteListNewDocenteToAttach.getCodigo());
                attachedDocenteListNew.add(docenteListNewDocenteToAttach);
            }
            docenteListNew = attachedDocenteListNew;
            programa.setDocenteList(docenteListNew);
            List<Pensum> attachedPensumListNew = new ArrayList<Pensum>();
            for (Pensum pensumListNewPensumToAttach : pensumListNew) {
                pensumListNewPensumToAttach = em.getReference(pensumListNewPensumToAttach.getClass(), pensumListNewPensumToAttach.getPensumPK());
                attachedPensumListNew.add(pensumListNewPensumToAttach);
            }
            pensumListNew = attachedPensumListNew;
            programa.setPensumList(pensumListNew);
            programa = em.merge(programa);
            if (departamentoIdOld != null && !departamentoIdOld.equals(departamentoIdNew)) {
                departamentoIdOld.getProgramaList().remove(programa);
                departamentoIdOld = em.merge(departamentoIdOld);
            }
            if (departamentoIdNew != null && !departamentoIdNew.equals(departamentoIdOld)) {
                departamentoIdNew.getProgramaList().add(programa);
                departamentoIdNew = em.merge(departamentoIdNew);
            }
            for (Docente docenteListNewDocente : docenteListNew) {
                if (!docenteListOld.contains(docenteListNewDocente)) {
                    Programa oldProgramaCodigoOfDocenteListNewDocente = docenteListNewDocente.getProgramaCodigo();
                    docenteListNewDocente.setProgramaCodigo(programa);
                    docenteListNewDocente = em.merge(docenteListNewDocente);
                    if (oldProgramaCodigoOfDocenteListNewDocente != null && !oldProgramaCodigoOfDocenteListNewDocente.equals(programa)) {
                        oldProgramaCodigoOfDocenteListNewDocente.getDocenteList().remove(docenteListNewDocente);
                        oldProgramaCodigoOfDocenteListNewDocente = em.merge(oldProgramaCodigoOfDocenteListNewDocente);
                    }
                }
            }
            for (Pensum pensumListNewPensum : pensumListNew) {
                if (!pensumListOld.contains(pensumListNewPensum)) {
                    Programa oldProgramaOfPensumListNewPensum = pensumListNewPensum.getPrograma();
                    pensumListNewPensum.setPrograma(programa);
                    pensumListNewPensum = em.merge(pensumListNewPensum);
                    if (oldProgramaOfPensumListNewPensum != null && !oldProgramaOfPensumListNewPensum.equals(programa)) {
                        oldProgramaOfPensumListNewPensum.getPensumList().remove(pensumListNewPensum);
                        oldProgramaOfPensumListNewPensum = em.merge(oldProgramaOfPensumListNewPensum);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = programa.getCodigo();
                if (findPrograma(id) == null) {
                    throw new NonexistentEntityException("The programa with id " + id + " no longer exists.");
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
            Programa programa;
            try {
                programa = em.getReference(Programa.class, id);
                programa.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The programa with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Docente> docenteListOrphanCheck = programa.getDocenteList();
            for (Docente docenteListOrphanCheckDocente : docenteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Programa (" + programa + ") cannot be destroyed since the Docente " + docenteListOrphanCheckDocente + " in its docenteList field has a non-nullable programaCodigo field.");
            }
            List<Pensum> pensumListOrphanCheck = programa.getPensumList();
            for (Pensum pensumListOrphanCheckPensum : pensumListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Programa (" + programa + ") cannot be destroyed since the Pensum " + pensumListOrphanCheckPensum + " in its pensumList field has a non-nullable programa field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Departamento departamentoId = programa.getDepartamentoId();
            if (departamentoId != null) {
                departamentoId.getProgramaList().remove(programa);
                departamentoId = em.merge(departamentoId);
            }
            em.remove(programa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Programa> findProgramaEntities() {
        return findProgramaEntities(true, -1, -1);
    }

    public List<Programa> findProgramaEntities(int maxResults, int firstResult) {
        return findProgramaEntities(false, maxResults, firstResult);
    }

    private List<Programa> findProgramaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Programa.class));
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

    public Programa findPrograma(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Programa.class, id);
        } finally {
            em.close();
        }
    }

    public int getProgramaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Programa> rt = cq.from(Programa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
