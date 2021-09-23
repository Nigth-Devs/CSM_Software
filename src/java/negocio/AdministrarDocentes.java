/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import dao.DocenteJpaController;
import dao.exceptions.NonexistentEntityException;
import dto.*;
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
        DocenteJpaController daoDocente = new DocenteJpaController(con.getBd());
        return daoDocente.findDocente(codigo);
    }

    public void activarDocente(Docente d, boolean activar) throws NonexistentEntityException, Exception {
        Conexion con = Conexion.getConexion();
        DocenteJpaController daoDocente = new DocenteJpaController(con.getBd());
        short in;

        in = (short) (activar ? 1 : 0);
        d.setEstado(in);
        daoDocente.edit(d);
    }

    public List<Docente> listarDocentes() {
        Conexion con = Conexion.getConexion();
        DocenteJpaController daoDocente = new DocenteJpaController(con.getBd());
        return daoDocente.findDocenteEntities();
    }

    public Docente guardarDocente(String nombre, String apellido, int programa, int codigo, short estado) throws Exception {
        Conexion con = Conexion.getConexion();
        DocenteJpaController daoDocente = new DocenteJpaController(con.getBd());
        
        Docente docente = new Docente(codigo, nombre, apellido, estado);
        docente.setProgramaCodigo(new Programa(programa));
        daoDocente.create(docente);
        
        return docente;
    }
    
    public int getNumDocentesActivos(){
        int count = 0;
        List<Docente> allDocentes = this.listarDocentes();
        for(Docente docente: allDocentes){
            if(docente.getEstado()==1 && docente.getUsuario().getRolId().getId()==2){
                count++;
            }
        }
        return count;
    }
}
