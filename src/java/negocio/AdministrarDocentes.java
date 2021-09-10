/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import dao.DocenteJpaController;
import dao.exceptions.NonexistentEntityException;
import dto.*;
import java.util.ArrayList;
import java.util.List;
import util.Conexion;

/**
 *
 * @author Jhoser
 */
public class AdministrarDocentes {

    public AdministrarDocentes() {
    }

    public Docente obtenerDocente(int codigo) {
        Conexion con = Conexion.getConexion();
        DocenteJpaController daoDocente = new dao.DocenteJpaController(con.getBd());
        return daoDocente.findDocente(codigo);
    }

    public void activarDocente(Docente d, boolean activar) throws NonexistentEntityException, Exception {
        Conexion con = Conexion.getConexion();
        DocenteJpaController daoDocente = new dao.DocenteJpaController(con.getBd());
        short in;

        in = (short) (activar ? 1 : 0);
        d.setEstado(in);
        daoDocente.edit(d);
    }

    public List<Docente> listarDocentes() {
        Conexion con = Conexion.getConexion();
        DocenteJpaController daoDocente = new dao.DocenteJpaController(con.getBd());
        return daoDocente.findDocenteEntities();
    }

    public void guardarDocente(String nombre, String apellido, int depar, int codigo, short estado) throws Exception {
        Docente d = new Docente(codigo, nombre, apellido, estado);
        d.setDepartamentoId(new Departamento(depar));
        Conexion con = Conexion.getConexion();
        DocenteJpaController daoDocente = new dao.DocenteJpaController(con.getBd());
        daoDocente.create(d);
    }

    public List<dto.Docente> obtenerDocentesPrograma(dto.Programa programa) {
        return programa.getDepartamentoId().getDocenteList();
    }

    public int getNumDocentesActivos(){
        int count = 0;
        List<Docente> allDocentes = this.listarDocentes();
        for(Docente docente: allDocentes){
            if(docente.getEstado()==1){
                count++;
            }
        }
        return count;
    }
}
