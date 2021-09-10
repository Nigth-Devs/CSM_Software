/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import dao.MateriaPeriodoGrupoJpaController;
import dao.MateriaPeriodoJpaController;
import dao.exceptions.NonexistentEntityException;
import dto.Materia;
import dto.MateriaPeriodoGrupo;
import dto.MateriaPeriodoPK;
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
        return programa.getDepartamentoId().getDocenteList();
    }

    public void validarMateriaPeriodoGrupo(dto.MateriaPeriodoPK materiaPeriodo, int codigoDocente) throws Exception {
        negocio.AdministrarDocentes admin = new AdministrarDocentes();
        Conexion con = Conexion.getConexion();
        dao.MateriaPeriodoGrupoJpaController mpgDao = new dao.MateriaPeriodoGrupoJpaController(con.getBd());
        dto.MateriaPeriodoGrupo mpg = new dto.MateriaPeriodoGrupo(obtenerUlitmoGrupo(materiaPeriodo), codigoDocente, materiaPeriodo.getAnio(), materiaPeriodo.getSemestreAnio(), materiaPeriodo.getMateriaPensumCodigo(), materiaPeriodo.getMateriaCodigoMateria());
        mpg.setMateriaPeriodo(new dto.MateriaPeriodo(materiaPeriodo));
        dto.Docente docente = admin.obtenerDocente(codigoDocente);
        mpg.setDocente(docente);
        mpgDao.create(mpg);
    }

    public String obtenerUlitmoGrupo(dto.MateriaPeriodoPK materiaPeriodo) {
        Conexion con = Conexion.getConexion();
        dao.MateriaPeriodoGrupoJpaController mpgDao = new MateriaPeriodoGrupoJpaController(con.getBd());
        dao.MateriaPeriodoJpaController mpDao = new MateriaPeriodoJpaController(con.getBd());
        int letra[] = new int[27];
        dto.MateriaPeriodo mp = (mpDao.findMateriaPeriodo(materiaPeriodo));
        List<dto.MateriaPeriodoGrupo> mpg = mp.getMateriaPeriodoGrupoList();
        for (MateriaPeriodoGrupo materiaPeriodoGrupo : mpg) {
            letra[materiaPeriodoGrupo.getMateriaPeriodoGrupoPK().getGrupo().charAt(0) - 65] = 1;
        }
        for (int i = 0; i < letra.length; i++) {
            if (letra[i] == 0) {
                char x = (char) ('A' + i);
                return x + "";
            }
        }
        return null;
    }

    public dto.MateriaPeriodoPK validarMateriaPeriodo(int anio, int semestre, int codigoMateria, int codigoPensum) throws Exception {
        Conexion con = Conexion.getConexion();
        dao.MateriaPeriodoJpaController materiaPeriodoDao = new dao.MateriaPeriodoJpaController(con.getBd());
        dto.MateriaPeriodoPK materiaPeriodo = new MateriaPeriodoPK(anio, semestre + 1 <= 6 ? 1 : 2, codigoMateria, codigoPensum);
        materiaPeriodoDao.findMateriaPeriodo(materiaPeriodo);
        if (materiaPeriodoDao.findMateriaPeriodo(materiaPeriodo) == null) {
            dto.Materia materia = new Materia(codigoMateria, codigoPensum);
            dto.MateriaPeriodo mp = new dto.MateriaPeriodo(materiaPeriodo);
            mp.setMateria(materia);
            materiaPeriodoDao.create(mp);
        }
        return materiaPeriodo;
    }

    public void eliminarGrupo(String grupo, int codigoDocente, int anio, int semestre, int codigoMateria, int pensumCodigo) throws NonexistentEntityException {
        Conexion con = Conexion.getConexion();
        dao.MateriaPeriodoGrupoJpaController grupoDao = new dao.MateriaPeriodoGrupoJpaController(con.getBd());
        dto.MateriaPeriodoGrupoPK grupoDto = new dto.MateriaPeriodoGrupoPK(grupo, codigoDocente, anio, semestre, pensumCodigo, codigoMateria);
        System.out.println(grupoDto.toString());
        grupoDao.destroy(grupoDto);
    }

    public List<dto.MateriaPeriodoGrupo> obtenerMateriaPeriodoGrupo(dto.Programa programa) {
        Conexion con = Conexion.getConexion();
        dao.MateriaPeriodoGrupoJpaController mpgDao = new dao.MateriaPeriodoGrupoJpaController(con.getBd());
        List<dto.MateriaPeriodoGrupo> materias = mpgDao.findMateriaPeriodoGrupoEntities();
        List<dto.MateriaPeriodoGrupo> materiasPrograma = new ArrayList<>();
        System.out.println(materias);
        System.out.println(programa);
        for (MateriaPeriodoGrupo materia : materias) {
            if (materia.getMateriaPeriodo().getMateria().getPensum().getPrograma().getCodigo()
                    == programa.getCodigo()) {

                materiasPrograma.add(materia);
            }
        }
        return materiasPrograma;
    }

}
