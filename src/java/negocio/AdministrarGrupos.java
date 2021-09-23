/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import dao.MateriaPeriodoGrupoJpaController;
import dao.MateriaPeriodoJpaController;
import dao.exceptions.IllegalOrphanException;
import dao.exceptions.NonexistentEntityException;
import dto.Materia;
import dto.MateriaPK;
import dto.MateriaPeriodo;
import dto.MateriaPeriodoGrupo;
import dto.Programa;
import java.util.ArrayList;
import java.util.List;
import util.Conexion;

/**
 *
 * @author Manuel
 */
public class AdministrarGrupos {

    public AdministrarGrupos() {
    }

    public List<dto.Docente> obtenerDocentes(dto.Programa programa) {
        return programa.getDocenteList();
    }

    public void validarMateriaPeriodoGrupo(dto.MateriaPeriodo materiaPeriodo, int codigoDocente) throws Exception {
        negocio.AdministrarDocentes admin = new AdministrarDocentes();
        Conexion con = Conexion.getConexion();
        dao.MateriaPeriodoGrupoJpaController mpgDao = new dao.MateriaPeriodoGrupoJpaController(con.getBd());
        
        MateriaPeriodoGrupo mpg = new MateriaPeriodoGrupo();
        mpg.setGrupo(obtenerUlitmoGrupo(materiaPeriodo));
        mpg.setMateriaPeriodoId(materiaPeriodo);
        mpg.setDocenteCodigo(admin.obtenerDocente(codigoDocente));
        
        mpgDao.create(mpg);
    }

    public String obtenerUlitmoGrupo(MateriaPeriodo materiaPeriodo) {
        Conexion con = Conexion.getConexion();
        MateriaPeriodoJpaController mpDao = new MateriaPeriodoJpaController(con.getBd());
        
        int letra[] = new int[27];
        dto.MateriaPeriodo mp = (mpDao.findMateriaPeriodo(materiaPeriodo.getId()));
        
        List<dto.MateriaPeriodoGrupo> mpg = mp.getMateriaPeriodoGrupoList();
        for (MateriaPeriodoGrupo materiaPeriodoGrupo : mpg) {
            letra[materiaPeriodoGrupo.getGrupo().charAt(0) - 65] = 1;
        }
        
        for (int i = 0; i < letra.length; i++) {
            if (letra[i] == 0) {
                char x = (char) ('A' + i);
                return x + "";
            }
        }
        return null;
    }

    public dto.MateriaPeriodo validarMateriaPeriodo(int anio, int semestre, int codigoMateria, int codigoPensum) throws Exception {
        Conexion con = Conexion.getConexion();
        dao.MateriaPeriodoJpaController materiaPeriodoDao = new dao.MateriaPeriodoJpaController(con.getBd());
        
        dto.MateriaPeriodo materiaPeriodo = new MateriaPeriodo();
        materiaPeriodo.setAnio(anio);
        materiaPeriodo.setPeriodo(semestre + 1 <= 6 ? 1 : 2);
        materiaPeriodo.setMateria(new Materia(new MateriaPK(codigoMateria, codigoPensum)));
        
        List<MateriaPeriodo> matPeriodos = materiaPeriodoDao.findMateriaPeriodoEntities();
        for(MateriaPeriodo temp: matPeriodos){
            if(temp.getAnio() == materiaPeriodo.getAnio() && 
                    temp.getPeriodo() == materiaPeriodo.getPeriodo() &&
                    temp.getMateria().equals(materiaPeriodo.getMateria())){
                materiaPeriodo = temp;
            }
        }
        if (materiaPeriodo.getId() == null) {
            materiaPeriodoDao.create(materiaPeriodo);
        }
        return materiaPeriodo;
    }

    public void eliminarGrupo(int id) throws NonexistentEntityException, IllegalOrphanException {
        Conexion con = Conexion.getConexion();
        MateriaPeriodoGrupoJpaController grupoDao = new MateriaPeriodoGrupoJpaController(con.getBd());
        grupoDao.destroy(id);
    }
    
    public List<MateriaPeriodoGrupo> obtenerMateriaPeriodoGrupo(Programa programa) {
        Conexion con = Conexion.getConexion();
        MateriaPeriodoGrupoJpaController mpgDao = new MateriaPeriodoGrupoJpaController(con.getBd());
        
        List<MateriaPeriodoGrupo> materias = mpgDao.findMateriaPeriodoGrupoEntities();
        List<MateriaPeriodoGrupo> materiasPrograma = new ArrayList<>();
        
        for (MateriaPeriodoGrupo materia : materias) {
            if (materia.getMateriaPeriodoId().getMateria().getPensum().getPrograma().equals(programa)) {
                materiasPrograma.add(materia);
            }
        }
        
        return materiasPrograma;
    }

}
