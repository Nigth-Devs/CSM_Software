/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import dao.CumplimientoJpaController;
import dao.DocenteJpaController;
import dao.MateriaPeriodoGrupoJpaController;
import dao.MateriaPeriodoJpaController;
import dao.exceptions.NonexistentEntityException;
import dto.ContenidoUnidad;
import dto.Cumplimiento;
import dto.CumplimientoPK;
import dto.Docente;
import dto.MateriaPeriodo;
import dto.MateriaPeriodoGrupo;
import dto.Microcurriculo;
import dto.Programa;
import dto.Unidad;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import util.Conexion;

/**
 *
 * @author Sachikia
 */
public class AdministrarSeguimiento {

    public List<Object[]> getLastSeguimientoGeneral(Programa programa) {
        Calendar date = Calendar.getInstance();
        int periodo = date.get(Calendar.MONTH) + 1 <= 6 ? 1 : 2;
        return this.getSeguimientoGeneral(programa, date.get(Calendar.YEAR), periodo);
    }

    public List<Object[]> getSeguimientoGeneral(Programa programa, int anio, int periodo) {
        List<Object[]> data = new ArrayList<>();
        Conexion con = Conexion.getConexion();
        
        MateriaPeriodoJpaController mpDao = new MateriaPeriodoJpaController(con.getBd());
        List<MateriaPeriodo> materiasPeriodo = mpDao.findMateriaPeriodoEntities();
        
        for (MateriaPeriodo materiaPeriodo : materiasPeriodo) {
            if (materiaPeriodo.getAnio() == anio && materiaPeriodo.getPeriodo() == periodo && materiaPeriodo.getMateria().getPensum().getPrograma().equals(programa)) {
                data.add(getArrayFor(materiaPeriodo));
            }
        }
        return data;
    }

    public List<Object[]> getLastSeguimientoGeneral(Docente docente) {
        Calendar date = Calendar.getInstance();
        int periodo = date.get(Calendar.MONTH) + 1 <= 6 ? 1 : 2;
        return this.getSeguimientoGeneral(docente, date.get(Calendar.YEAR), periodo);
    }
    
    public List<Object[]> getSeguimientoGeneral(Docente docente, int anio, int periodo) {
        List<Object[]> data = new ArrayList<>();
        Conexion con = Conexion.getConexion();
        docente = new DocenteJpaController(con.getBd()).findDocente(docente.getCodigo());//update docente
        
        for (MateriaPeriodoGrupo materiaPeriodoGrupo : docente.getMateriaPeriodoGrupoList()) {
            MateriaPeriodo materiaPeriodo = materiaPeriodoGrupo.getMateriaPeriodoId();
            if (materiaPeriodo.getAnio() == anio && materiaPeriodo.getPeriodo() == periodo && materiaPeriodo.getMateria().getPensum().getPrograma().equals(docente.getProgramaCodigo())) {
                data.add(this.getArrayFor(materiaPeriodo));
            }
        }
        return data;
    }

    private Object[] getArrayFor(MateriaPeriodo materiaPeriodo) {
        Object info[] = new Object[6];
        info[0] = materiaPeriodo.getMateria().getMateriaPK().getCodigo();
        info[1] = materiaPeriodo.getMateria().getNombre();
        info[2] = materiaPeriodo.getMateria().getCreditos();
        info[3] = materiaPeriodo.getMateria().getSemestre();
        info[4] = (int) this.getPercentageMateriaPeriodo(materiaPeriodo);
        info[5] = materiaPeriodo.getId();
        return info;
    }

    public double getPercentageMateriaPeriodo(MateriaPeriodo materiaPeriodo) {
        double sumCheck = 0;
        for (MateriaPeriodoGrupo materiaPeriodoGrupo : materiaPeriodo.getMateriaPeriodoGrupoList()) {
            sumCheck += this.getPercentageMateriaPeriodoGrupo(materiaPeriodoGrupo) / (double) materiaPeriodo.getMateriaPeriodoGrupoList().size();
        }
        return sumCheck;
    }

    public double getPercentageMateriaPeriodoGrupo(MateriaPeriodoGrupo materiaPeriodoGrupo) {
        double check = 0;
        for (Cumplimiento cumplimiento : materiaPeriodoGrupo.getCumplimientoList()) {
            check += cumplimiento.getEstado();
        }
        return (check / (double) materiaPeriodoGrupo.getCumplimientoList().size()) * 100;
    }
    
    public List<Object[]> getSeguimientoMateriaPeriodo(int materiaPeriodoId){
        Conexion con = Conexion.getConexion();
        MateriaPeriodo materiaPeriodo = new MateriaPeriodoJpaController(con.getBd()).findMateriaPeriodo(materiaPeriodoId);
        
        List<Object[]> data = new ArrayList<>();
        int i=0;
        for(MateriaPeriodoGrupo materiaPeriodoGrupo: materiaPeriodo.getMateriaPeriodoGrupoList()){
            Object info[] = new Object[6];
            info[0] = ++i;
            info[1] = materiaPeriodoGrupo.getDocenteCodigo().getCodigo();
            info[2] = materiaPeriodoGrupo.getDocenteCodigo().getNombre()+" "+materiaPeriodoGrupo.getDocenteCodigo().getApellido();
            info[3] = materiaPeriodoGrupo.getGrupo();
            info[4] = this.getPercentageMateriaPeriodoGrupo(materiaPeriodoGrupo);
            info[5] = materiaPeriodoGrupo.getId();
            data.add(info);
        }
        
        return data;
    }

    public MateriaPeriodoGrupo getMateriaPeriodoGrupo(int materiaPeriodoGrupoId){
        return new MateriaPeriodoGrupoJpaController(Conexion.getConexion().getBd()).findMateriaPeriodoGrupo(materiaPeriodoGrupoId);
    }
    
    public Object[] getPercentagesPerUnitFor(MateriaPeriodoGrupo materiaPeriodoGrupo){
        Microcurriculo micro = materiaPeriodoGrupo.getMateriaPeriodoId().getMateria().getMicrocurriculo();
        int percentages[] = new int[micro.getUnidadList().size()+1];
        Map<Integer, Boolean> checkeds = new HashMap<>();
        percentages[0] = (int)this.getPercentageMateriaPeriodoGrupo(materiaPeriodoGrupo);
        for(Unidad unidad: micro.getUnidadList()){
            for(Cumplimiento c: materiaPeriodoGrupo.getCumplimientoList()){
                if(c.getContenidoUnidad().getUnidadId().equals(unidad)){
                    percentages[unidad.getNum()] += (int)(((double)c.getEstado()/(double)unidad.getContenidoUnidadList().size())*100);
                    checkeds.put(c.getContenidoUnidad().getId(), c.getEstado() == 1);
                }
            }
        }
        return new Object[]{percentages, checkeds};
    }
    
    public void addContents(Microcurriculo microcurriculo, List<ContenidoUnidad> contenidos) throws Exception {
        Conexion con = Conexion.getConexion();

        CumplimientoJpaController cDao = new CumplimientoJpaController(con.getBd());
        for (MateriaPeriodo materiaPeriodo : microcurriculo.getMateria().getMateriaPeriodoList()) {
            if (materiaPeriodo.getMateria().getPensum().getPrograma().equals(microcurriculo.getMateria().getPensum().getPrograma())) {
                for (MateriaPeriodoGrupo materiaPeriodoGrupo : materiaPeriodo.getMateriaPeriodoGrupoList()) {
                    for (ContenidoUnidad contenidoUnidad : contenidos) {
                        Cumplimiento c = new Cumplimiento(new CumplimientoPK(materiaPeriodoGrupo.getId(), contenidoUnidad.getId()));
                        c.setContenidoUnidad(contenidoUnidad);
                        c.setMateriaPeriodoGrupo(materiaPeriodoGrupo);
                        c.setEstado((short) 0);
                        if (cDao.findCumplimiento(c.getCumplimientoPK()) == null) {
                            cDao.create(c);
                        }
                    }
                }
            }
        }
    }

    public void deleteCumplimiento(Microcurriculo microcurriculo, ContenidoUnidad contUnit) throws NonexistentEntityException {
        Conexion con = Conexion.getConexion();

        CumplimientoJpaController cDao = new CumplimientoJpaController(con.getBd());
        MateriaPeriodoJpaController mpDao = new MateriaPeriodoJpaController(con.getBd());
        List<MateriaPeriodo> materiasPeriodo = mpDao.findMateriaPeriodoEntities();
        for (MateriaPeriodo materiaPeriodo : materiasPeriodo) {
            for (MateriaPeriodoGrupo materiaPeriodoGrupo : materiaPeriodo.getMateriaPeriodoGrupoList()) {
                for (Cumplimiento cumplimiento : materiaPeriodoGrupo.getCumplimientoList()) {
                    if (cumplimiento.getContenidoUnidad().equals(contUnit)) {
                        cDao.destroy(cumplimiento.getCumplimientoPK());
                    }
                }
            }
        }
    }
}
