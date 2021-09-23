/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import dao.AreaFormacionJpaController;
import dao.CambioJpaController;
import dao.ContenidoJpaController;
import dao.ContenidoUnidadJpaController;
import dao.CumplimientoJpaController;
import dao.EstadoJpaController;
import dao.MateriaJpaController;
import dao.MateriaPeriodoGrupoJpaController;
import dao.MicrocurriculoJpaController;
import dao.ProgramaJpaController;
import dao.SeccionJpaController;
import dao.SeccionMicrocurriculoJpaController;
import dao.TablaSeccionJpaController;
import dao.TipoMateriaJpaController;
import dao.UnidadJpaController;
import dao.exceptions.IllegalOrphanException;
import dao.exceptions.NonexistentEntityException;
import dto.AreaFormacion;
import dto.Cambio;
import dto.Contenido;
import dto.ContenidoUnidad;
import dto.Cumplimiento;
import dto.Docente;
import dto.Estado;
import dto.Materia;
import dto.MateriaPK;
import dto.MateriaPeriodoGrupo;
import dto.Microcurriculo;
import dto.MicrocurriculoPK;
import dto.Pensum;
import dto.Programa;
import dto.Seccion;
import dto.SeccionCambio;
import dto.SeccionMicrocurriculo;
import dto.Tabla;
import dto.TablaSeccion;
import dto.TipoMateria;
import dto.Unidad;
import dto.Usuario;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import util.Conexion;

/**
 *
 * @author Manuel
 */
public class AdministrarMicrocurriculo {

    public AdministrarMicrocurriculo() {
    }

    public List<Materia> obtenerMateriasDocentes(Usuario user) {
        Docente docente = user.getDocente();
        Conexion con = Conexion.getConexion();
        MateriaPeriodoGrupoJpaController grupoDao = new MateriaPeriodoGrupoJpaController(con.getBd());
        List<MateriaPeriodoGrupo> grupos = grupoDao.findMateriaPeriodoGrupoEntities();
        List<Materia> materias = new ArrayList<>();
        for (MateriaPeriodoGrupo grupo : grupos) {
            if (grupo.getDocenteCodigo().equals(docente)) {
                materias.add(grupo.getMateriaPeriodoId().getMateria());
            }
        }
        return materias;
    }

    public ArrayList<Microcurriculo> obtenerMicrocurriculosPensum(int codigo, int programaCodigo) {
        AdministrarPensum administrarPensum = new AdministrarPensum();
        Pensum pensum = administrarPensum.obtenerPensum(codigo, programaCodigo);
        
        ArrayList<dto.Microcurriculo> microcurriculos = new ArrayList<>();
        for (Materia materia : pensum.getMateriaList()) {
            microcurriculos.add(materia.getMicrocurriculo());
        }
        return microcurriculos;
    }

    public List<SeccionCambio> obtenerSeccionesCambios() {
        Conexion con = Conexion.getConexion();
        dao.SeccionCambioJpaController as = new dao.SeccionCambioJpaController(con.getBd());
        return as.findSeccionCambioEntities();
    }

    public Microcurriculo obtenerMicrocurriculo(int codigoMateria, int codigoPensum) {
        Conexion con = Conexion.getConexion();
        MicrocurriculoJpaController microDao = new MicrocurriculoJpaController(con.getBd());
        Microcurriculo microcurriculo = microDao.findMicrocurriculo(new MicrocurriculoPK(codigoMateria, codigoPensum));
        return microcurriculo;
    }

    public List<Materia> obtenerTodasMateria(Programa programa) {
        List<Pensum> pensums = programa.getPensumList();
        
        List<Materia> listmaterias = new ArrayList<>();
        for (Pensum pensum : pensums) {
            listmaterias.addAll(pensum.getMateriaList());
        }
        return listmaterias;
    }
    
    public void actualizarAreaFormacionMicrocurriculo(Microcurriculo microcurriculo, int areaFormacion) throws NonexistentEntityException, Exception {
        Conexion con = Conexion.getConexion();
        MicrocurriculoJpaController daoMicrocurriculo = new MicrocurriculoJpaController(con.getBd());
        microcurriculo.setAreaDeFormacionId(new dto.AreaFormacion(areaFormacion));
        daoMicrocurriculo.edit(microcurriculo);
    }

    public void updateUnidades(String[][][] contenidos, String[][] oldInfo, Microcurriculo microcurriculo) throws Exception{
        Conexion con = Conexion.getConexion();
        UnidadJpaController uDao = new UnidadJpaController(con.getBd());
        ContenidoUnidadJpaController cuDao = new ContenidoUnidadJpaController(con.getBd());
        
        /*Buscar unidades ya existentes y actualizarlas*/
        List<Unidad> unitUpdates = new ArrayList<>();
        for(Unidad unidad: microcurriculo.getUnidadList()){
            for(int i=0; oldInfo[0]!=null && i<oldInfo[0].length; i++){
                if(oldInfo[0][i] == null) continue;
                if(Integer.parseInt(oldInfo[0][i]) == unidad.getId()){
                    this.updateUnit(unidad, uDao, contenidos[0]);
                    unitUpdates.add(unidad);
                    oldInfo[0][i] = null;
                    for(int j=0; j<contenidos[0].length; j++){
                        if(contenidos[0][j] != null && Integer.parseInt(contenidos[0][j][0]) == unidad.getNum()){//LA CLAVE WN
                            contenidos[0][j] = null;
                            break;
                        }
                    }
                    break;
                }
            }
        }
        
        /*Crear unidades que no fueron actualizadas, es decir, nuevas unidades*/
        List<Unidad> unitNews = new ArrayList<>(unitUpdates); 
        for(int i=0; i<contenidos[0].length; i++){
            if(contenidos[0][i]!=null){
                Unidad unidad = new Unidad();
                unidad.setNum(Integer.parseInt(contenidos[0][i][0]));
                unidad.setNombre(contenidos[0][i][1]);
                unidad.setHorasPresencial(Integer.parseInt(contenidos[0][i][2]));
                unidad.setHorasIndependiente(Integer.parseInt(contenidos[0][i][3]));
                unidad.setMicrocurriculo(microcurriculo);
                uDao.create(unidad);
                unitNews.add(unidad);
            }
        }
        
        /*Buscar contenidos por unidades ya existentes y actualizarlos*/
        List<ContenidoUnidad> contentUpdates = new ArrayList<>();
        for(Unidad unidad: microcurriculo.getUnidadList()){
            for(ContenidoUnidad contUnit: unidad.getContenidoUnidadList()){
                for(int i=0; oldInfo[1]!=null && i<oldInfo[1].length; i++){
                    if(oldInfo[1][i]==null) continue;
                    String info[] = oldInfo[1][i].split("-");
                    if(Integer.parseInt(info[0]) == contUnit.getId()){
                        this.updateContentUnit(contUnit, cuDao, contenidos[1], unitNews, Integer.parseInt(info[1]));
                        contentUpdates.add(contUnit);
                        oldInfo[1][i] = null;
                        contenidos[1][Integer.parseInt(info[1])] = null;
                    }
                }
            }
        }
        
        /*Crear contenidos que no fueron actualizados, es decir, nuevos contenidos*/
        List<ContenidoUnidad> contentNews = new ArrayList<>(contentUpdates);
        for(int i=0; i<contenidos[1].length; i++){
            if(contenidos[1][i]!=null){
                ContenidoUnidad contUnit = new ContenidoUnidad();
                contUnit.setUnidadId(this.getUnidad(unitNews, Integer.parseInt(contenidos[1][i][0])));
                contUnit.setContenido(contenidos[1][i][1]);
                contUnit.setTrabajoPresencial(contenidos[1][i][2]);
                contUnit.setTrabajoIndependiente(contenidos[1][i][3]);
                cuDao.create(contUnit);
                contentNews.add(contUnit);
            }
        }
        
        /*Borrar contenidos que no fueron actualizados o creados, contenidos viejos*/
        List<Unidad> deletes = microcurriculo.getUnidadList();
        AdministrarSeguimiento adminSeg = new AdministrarSeguimiento();
        for(Unidad unidad: deletes){
            List<ContenidoUnidad> deleteContents = unidad.getContenidoUnidadList();
            deleteContents.removeAll(contentUpdates);
            for(ContenidoUnidad contUnit: deleteContents){
                adminSeg.deleteCumplimiento(microcurriculo, contUnit);
                cuDao.destroy(contUnit.getId());
            }
        }
        
        /*Borrar unidades que no fueron actualizadas o creadas, unidades viejas*/
        deletes.removeAll(unitNews);
        for(Unidad unidad: deletes){
            uDao.destroy(unidad.getId());
        }
        adminSeg.addContents(microcurriculo, contentNews);
    }
    
    private Unidad getUnidad(List<Unidad> unidades, int num){
        for(Unidad unidad: unidades){
            if(unidad.getNum()==num)
                return unidad;
        }
        return null;//nunca retorna null
    }
    
    private void updateUnit(Unidad unidad, UnidadJpaController uDao, String[][] unidades) throws NonexistentEntityException, Exception{
        for(String []temp: unidades){
            if(temp != null && Integer.parseInt(temp[0]) == unidad.getNum()){
                unidad.setNombre(temp[1]);
                unidad.setHorasPresencial(Integer.parseInt(temp[2]));
                unidad.setHorasIndependiente(Integer.parseInt(temp[3]));
                uDao.edit(unidad);
                break;
            }
        }
    }
    
    private void updateContentUnit(ContenidoUnidad content, ContenidoUnidadJpaController cuDao, String[][] contents, List<Unidad> unidades, int row) throws NonexistentEntityException, Exception{
        String info[] = contents[row];
        content.setUnidadId(this.getUnidad(unidades, Integer.parseInt(info[0])));
        content.setContenido(info[1]);
        content.setTrabajoPresencial(info[2]);
        content.setTrabajoIndependiente(info[3]);
        cuDao.edit(content);
    }

    public void ingresarContenidoSeccion(String info, SeccionMicrocurriculo seccionMicrocurriculo) throws NonexistentEntityException, Exception {
        Conexion con = Conexion.getConexion();
        ContenidoJpaController contDao = new ContenidoJpaController(con.getBd());
                
        Contenido contenido = seccionMicrocurriculo.getContenidoList().get(0);
        contenido.setSeccionMicrocurriculoId(seccionMicrocurriculo);
        contenido.setCantidadItemsLista(0);
        contenido.setTexto(info);
        contDao.edit(contenido);
    }

    public List<Seccion> obtenerSecciones() {
        Conexion con = Conexion.getConexion();
        SeccionJpaController daoSeccion = new SeccionJpaController(con.getBd());
        return daoSeccion.findSeccionEntities();

    }

    public void registrarMicrocurriculos(Pensum pensum) {
        new RegistroMicrocurriculoBackground(pensum).start();
    }

    public List<AreaFormacion> obtenerAreasFormacion() {
        Conexion con = Conexion.getConexion();
        AreaFormacionJpaController daoAreasFormacion = new AreaFormacionJpaController(con.getBd());

        return daoAreasFormacion.findAreaFormacionEntities();
    }

    public List<TipoMateria> obtenerTiposAisgnatura() {
        Conexion con = Conexion.getConexion();
        TipoMateriaJpaController tipoMateriaJpa = new TipoMateriaJpaController(con.getBd());
        return tipoMateriaJpa.findTipoMateriaEntities();
    }

    /**
     * Se debe ajustar al nuevo modelo
     * @param idSeccionMicrocurriculo
     * @param texto
     * @deprecated
     */
    @Deprecated
    public void realizarSolicitudCambio(int idSeccionMicrocurriculo, String texto) {
        Conexion con = Conexion.getConexion();
        dao.SeccionMicrocurriculoJpaController daoSeccionMicrocurriculo = new dao.SeccionMicrocurriculoJpaController(con.getBd());
        SeccionMicrocurriculo smAntigua = daoSeccionMicrocurriculo.findSeccionMicrocurriculo(idSeccionMicrocurriculo);

        List<SeccionMicrocurriculo> lista = daoSeccionMicrocurriculo.findSeccionMicrocurriculoEntities();
        SeccionMicrocurriculo ultimoregistro = lista.get(lista.size() - 1);

        SeccionMicrocurriculo SMCNueva = new SeccionMicrocurriculo();
        SMCNueva.setMicrocurriculo(smAntigua.getMicrocurriculo());
        SMCNueva.setSeccionId(smAntigua.getSeccionId());
        SMCNueva.setEditable(smAntigua.getEditable());
        SMCNueva.setTablaSeccion(smAntigua.getTablaSeccion());

        daoSeccionMicrocurriculo.create(SMCNueva);

        EstadoJpaController estadodao = new EstadoJpaController(con.getBd());
        Estado estado = estadodao.findEstado(1);

        Cambio c = new Cambio();
        c.setEstadoId(estado);

        CambioJpaController cdao = new CambioJpaController(con.getBd());
        cdao.create(c);

        SeccionCambio sc = new SeccionCambio();
        sc.setSeccionMicrocurriculoIdAntigua(smAntigua);
        sc.setSeccionMicrocurriculoIdNuevo(SMCNueva);
        sc.setCambioId(c);

        dao.SeccionCambioJpaController scdao = new dao.SeccionCambioJpaController(con.getBd());
        scdao.create(sc);

        Contenido cont = new Contenido();
        cont.setCantidadItemsLista(0);
        cont.setTexto(texto);
        cont.setSeccionMicrocurriculoId(SMCNueva);

        dao.ContenidoJpaController codao = new dao.ContenidoJpaController(con.getBd());
        codao.create(cont);
    }

    public static void main(String args[]) {
        AdministrarMicrocurriculo a = new AdministrarMicrocurriculo();
        a.realizarSolicitudCambio(17045, "Estos son los 80");
    }

}
