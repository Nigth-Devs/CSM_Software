/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.exceptions.IllegalOrphanException;
import dao.exceptions.NonexistentEntityException;
import dto.Departamento;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import dto.Facultad;
import dto.Programa;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Sachikia
 */
public class DepartamentoJpaController implements Serializable {

    public DepartamentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Departamento departamento) {
        if (departamento.getProgramaList() == null) {
            departamento.setProgramaList(new ArrayList<Programa>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Facultad facultadId = departamento.getFacultadId();
            if (facultadId != null) {
                facultadId = em.getReference(facultadId.getClass(), facultadId.getId());
                departamento.setFacultadId(facultadId);
            }
            List<Programa> attachedProgramaList = new ArrayList<Programa>();
            for (Programa programaListProgramaToAttach : departamento.getProgramaList()) {
                programaListProgramaToAttach = em.getReference(programaListProgramaToAttach.getClass(), programaListProgramaToAttach.getCodigo());
                attachedProgramaList.add(programaListProgramaToAttach);
            }
            departamento.setProgramaList(attachedProgramaList);
            em.persist(departamento);
            if (facultadId != null) {
                facultadId.getDepartamentoList().add(departamento);
                facultadId = em.merge(facultadId);
            }
            for (Programa programaListPrograma : departamento.getProgramaList()) {
                Departamento oldDepartamentoIdOfProgramaListPrograma = programaListPrograma.getDepartamentoId();
                programaListPrograma.setDepartamentoId(departamento);
                programaListPrograma = em.merge(programaListPrograma);
                if (oldDepartamentoIdOfProgramaListPrograma != null) {
                    oldDepartamentoIdOfProgramaListPrograma.getProgramaList().remove(programaListPrograma);
                    oldDepartamentoIdOfProgramaListPrograma = em.merge(oldDepartamentoIdOfProgramaListPrograma);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Departamento departamento) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Departamento persistentDepartamento = em.find(Departamento.class, departamento.getId());
            Facultad facultadIdOld = persistentDepartamento.getFacultadId();
            Facultad facultadIdNew = departamento.getFacultadId();
            List<Programa> programaListOld = persistentDepartamento.getProgramaList();
            List<Programa> programaListNew = departamento.getProgramaList();
            List<String> illegalOrphanMessages = null;
            for (Programa programaListOldPrograma : programaListOld) {
                if (!programaListNew.contains(programaListOldPrograma)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Programa " + programaListOldPrograma + " since its departamentoId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (facultadIdNew != null) {
                facultadIdNew = em.getReference(facultadIdNew.getClass(), facultadIdNew.getId());
                departamento.setFacultadId(facultadIdNew);
            }
            List<Programa> attachedProgramaListNew = new ArrayList<Programa>();
            for (Programa programaListNewProgramaToAttach : programaListNew) {
                programaListNewProgramaToAttach = em.getReference(programaListNewProgramaToAttach.getClass(), programaListNewProgramaToAttach.getCodigo());
                attachedProgramaListNew.add(programaListNewProgramaToAttach);
            }
            programaListNew = attachedProgramaListNew;
            departamento.setProgramaList(programaListNew);
            departamento = em.merge(departamento);
            if (facultadIdOld != null && !facultadIdOld.equals(facultadIdNew)) {
                facultadIdOld.getDepartamentoList().remove(departamento);
                facultadIdOld = em.merge(facultadIdOld);
            }
            if (facultadIdNew != null && !facultadIdNew.equals(facultadIdOld)) {
                facultadIdNew.getDepartamentoList().add(departamento);
                facultadIdNew = em.merge(facultadIdNew);
            }
            for (Programa programaListNewPrograma : programaListNew) {
                if (!programaListOld.contains(programaListNewPrograma)) {
                    Departamento oldDepartamentoIdOfProgramaListNewPrograma = programaListNewPrograma.getDepartamentoId();
                    programaListNewPrograma.setDepartamentoId(departamento);
                    programaListNewPrograma = em.merge(programaListNewPrograma);
                    if (oldDepartamentoIdOfProgramaListNewPrograma != null && !oldDepartamentoIdOfProgramaListNewPrograma.equals(departamento)) {
                        oldDepartamentoIdOfProgramaListNewPrograma.getProgramaList().remove(programaListNewPrograma);
                        oldDepartamentoIdOfProgramaListNewPrograma = em.merge(oldDepartamentoIdOfProgramaListNewPrograma);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = departamento.getId();
                if (findDepartamento(id) == null) {
                    throw new NonexistentEntityException("The departamento with id " + id + " no longer exists.");
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
            Departamento departamento;
            try {
                departamento = em.getReference(Departamento.class, id);
                departamento.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The departamento with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Programa> programaListOrphanCheck = departamento.getProgramaList();
            for (Programa programaListOrphanCheckPrograma : programaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Departamento (" + departamento + ") cannot be destroyed since the Programa " + programaListOrphanCheckPrograma + " in its programaList field has a non-nullable departamentoId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Facultad facultadId = departamento.getFacultadId();
            if (facultadId != null) {
                facultadId.getDepartamentoList().remove(departamento);
                facultadId = em.merge(facultadId);
            }
            em.remove(departamento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Departamento> findDepartamentoEntities() {
        return findDepartamentoEntities(true, -1, -1);
    }

    public List<Departamento> findDepartamentoEntities(int maxResults, int firstResult) {
        return findDepartamentoEntities(false, maxResults, firstResult);
    }

    private List<Departamento> findDepartamentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Departamento.class));
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

    public Departamento findDepartamento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Departamento.class, id);
        } finally {
            em.close();
        }
    }

    public int getDepartamentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Departamento> rt = cq.from(Departamento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
