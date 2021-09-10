/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import dto.MateriaPeriodo;
import dto.MateriaPeriodoGrupo;
import dto.Usuario;
import java.util.ArrayList;
import java.util.List;
import util.Conexion;

/**
 *
 * @author Jhoser
 */
public class RolDocente {

    public RolDocente() {
    }

    public List<MateriaPeriodoGrupo> materiasGrupo() {
        Conexion con = Conexion.getConexion();
        dao.MateriaPeriodoGrupoJpaController mpgDao = new dao.MateriaPeriodoGrupoJpaController(con.getBd());
        return mpgDao.findMateriaPeriodoGrupoEntities();
    }

    public List<MateriaPeriodo> microcurriculosDocentes(int codigoDocente) {
        MateriaPeriodoGrupo mpg = new MateriaPeriodoGrupo();
        AdministrarGrupos ag = new AdministrarGrupos();
        List<MateriaPeriodoGrupo> lmg = materiasGrupo();
        List<MateriaPeriodo> mp = new ArrayList<>();
        for (MateriaPeriodoGrupo materiaGrupo : lmg) {
            System.out.println(materiaGrupo.getDocente().getCodigoDocente() + " " + codigoDocente);
            if (materiaGrupo.getDocente().getCodigoDocente() == codigoDocente) {
                System.out.println(materiaGrupo.getDocente().getCodigoDocente() + " " + codigoDocente);
                mp.add(materiaGrupo.getMateriaPeriodo());
            }
        }
        return mp;
    }

    public Usuario buscarUsuario(int codigo) {
        Login l = new Login();
        return l.obtenerUsuario(codigo, 2);
    }

}
