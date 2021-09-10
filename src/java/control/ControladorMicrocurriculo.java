/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import dto.Materia;
import dto.Microcurriculo;
import dto.Pensum;
import dto.Seccion;
import dto.SeccionMicrocurriculo;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import negocio.AdministrarMicrocurriculo;
import negocio.MicrocurriculoPDF;
import negocio.RegistroMicrocurriculoBackground;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author Manuel
 */
@WebServlet(name = "ControladorMicrocurriculo", urlPatterns = {"/ControladorMicrocurriculo"})
public class ControladorMicrocurriculo extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    public static void cargarMicrocurriculo() {
    }

    public static void listar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("materias");
        AdministrarMicrocurriculo adminMicrocurriculo = new AdministrarMicrocurriculo();
        dto.Usuario usuario = (dto.Usuario) request.getSession().getAttribute("usuario");
        List<dto.Materia> materias = null;
        String url = "";
        if (usuario.getRol().getId() == 1) {
            materias = adminMicrocurriculo.obtenerTodasMateria(usuario.getDocente().getProgramaList().get(0).getCodigo());
            url = "CSM_Software/CSM/director/dashboard/microcurriculo/consultar-microcurriculo.jsp";
        } else {
            materias = adminMicrocurriculo.obtenerMateriasDocentes(usuario);
            url = "CSM_Software/CSM/docente/dashboard/microcurriculo/consultar-microcurriculo.jsp";
        }
        request.getSession().setAttribute("areasFormacion", adminMicrocurriculo.obtenerAreasFormacion());
        request.getSession().setAttribute("tipoAsignatura", adminMicrocurriculo.obtenerTiposAisgnatura());
        request.getSession().setAttribute("materias", materias);
        response.sendRedirect(url);
    }

    public void editar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("idMicrocurriculo"));
        int codigoPensum = Integer.parseInt(request.getParameter("codigoPensum"));
        int codigoMateria = Integer.parseInt(request.getParameter("codigoMateria"));
        negocio.AdministrarMicrocurriculo adminMicrocurriculo = new negocio.AdministrarMicrocurriculo();
        dto.Microcurriculo microcurriculo = adminMicrocurriculo.obtenerMicrocurriculo(id, codigoMateria, codigoPensum);
        request.getSession().setAttribute("tablas", negocio.AdministrarMicrocurriculo.ordenarTablaInfo(microcurriculo));
        request.getSession().setAttribute("microcurriculo", microcurriculo);
        response.sendRedirect("CSM_Software/CSM/director/dashboard/microcurriculo/editar-microcurriculo.jsp");
    }

    public void consultar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("idMicrocurriculo"));
        int codigoPensum = Integer.parseInt(request.getParameter("codigoPensum"));
        int codigoMateria = Integer.parseInt(request.getParameter("codigoMateria"));
        negocio.AdministrarMicrocurriculo adminMicrocurriculo = new negocio.AdministrarMicrocurriculo();
        dto.Microcurriculo microcurriculo = adminMicrocurriculo.obtenerMicrocurriculo(id, codigoMateria, codigoPensum);
        request.getSession().setAttribute("tablas", negocio.AdministrarMicrocurriculo.ordenarTablaInfo(microcurriculo));
        request.getSession().setAttribute("microcurriculo", microcurriculo);
        String url = "";
        dto.Usuario user = (dto.Usuario) (request.getSession().getAttribute("usuario"));
        if (user.getRol().getId().equals(1)) {
            url = "director";
        } else {
            url = "docente";
        }
        response.sendRedirect("CSM_Software/CSM/" + url + "/dashboard/microcurriculo/ver-microcurriculo.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion.equalsIgnoreCase("listarTodos")) {
            listar(request, response);
        }
        if (accion.equalsIgnoreCase("editar")) {
            editar(request, response);
        }
        if (accion.equalsIgnoreCase("Consultar")) {
            consultar(request, response);
        }
        if (accion.equalsIgnoreCase("registrar")) {
            try {
                crearMicrocurriculo(request, response);
            } catch (Exception err) {
                System.out.println(err.getMessage());
            }
        }
        if (accion.equalsIgnoreCase("PDF")) {
            try {
                generarPDF(request, response);
            } catch (DocumentException ex) {
                Logger.getLogger(ControladorMicrocurriculo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void generarPDF(HttpServletRequest request, HttpServletResponse response) throws DocumentException, BadElementException, IOException {
        int codigoPensum = Integer.parseInt(request.getParameter("codigoPensum"));
        int codigoMateria = Integer.parseInt(request.getParameter("codigoMateria"));
        negocio.AdministrarMicrocurriculo admin = new AdministrarMicrocurriculo();
        dto.Microcurriculo microcurriculo = admin.obtenerMicrocurriculo(codigoMateria, codigoPensum);
        negocio.MicrocurriculoPDF pdf = new MicrocurriculoPDF(request.getServletContext().getRealPath("/"), microcurriculo);
        response.setContentType("application/pdf");
        InputStream is = pdf.createPDF();
        IOUtils.copy(is, response.getOutputStream());
        is.close();
        pdf.getFile().deleteOnExit();
        response.flushBuffer();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion.equalsIgnoreCase("Registrar")) {
            try {
                registrar(request, response);
            } catch (Exception ex) {
                Logger.getLogger(ControladorMicrocurriculo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (accion.equalsIgnoreCase("ver-materia")) {
            try {
                this.verMateria(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void registrarInformacionTablas(HttpServletRequest request, HttpServletResponse response, AdministrarMicrocurriculo adminM) throws Exception {
        dto.Microcurriculo microcurriculo = (dto.Microcurriculo) request.getSession().getAttribute("microcurriculo");
        for (dto.SeccionMicrocurriculo secciones : microcurriculo.getSeccionMicrocurriculoList()) {
            if (secciones.getSeccionId().getTipoSeccionId().getId() == 2) {
                int cantidadFilas = Integer.parseInt(request.getParameter("nfilas-" + secciones.getId()));
                secciones.getTablaMicrocurriculoList().get(0).setCantidadFilas(cantidadFilas);
                adminM.actualizarFilasTabla(secciones.getTablaMicrocurriculoList().get(0));
                String contenido[][] = new String[cantidadFilas][secciones.getTablaMicrocurriculoList().get(0).getCantidadColumnas()];
                for (int i = 0; i < contenido.length; i++) {
                    for (int j = 0; j < contenido[i].length; j++) {
                        contenido[i][j] = (String) request.getParameter("contenido-" + secciones.getSeccionId().getId() + "-" + (i) + "-" + j);
                    }
                }
                adminM.registrarContenidoTablas(contenido, secciones);
            }
        }
    }

    public void registrarSecciones(HttpServletRequest request, HttpServletResponse response, AdministrarMicrocurriculo adminM) throws Exception {
        List<dto.Seccion> secciones = adminM.obtenerSecciones();
        for (Seccion seccione : secciones) {
            if (seccione.getTipoSeccionId().getId() != 2) {
                String informacion = request.getParameter("seccion-" + seccione.getId());
                int idSeccionMicrocurriculo = Integer.parseInt(request.getParameter("seccionId-" + seccione.getId()));
                adminM.ingresarContenidoSecciones(informacion, idSeccionMicrocurriculo);
            }
        }
    }

    private void registrar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        AdministrarMicrocurriculo adminMicrocurriculo = new AdministrarMicrocurriculo();
        int areaFormacion = Integer.parseInt(request.getParameter("areasFormacion"));
        dto.Microcurriculo microcurriculo = (dto.Microcurriculo) request.getSession().getAttribute("microcurriculo");
        adminMicrocurriculo.actualizarAreaFormacionMicrocurriculo(microcurriculo, areaFormacion);
        registrarInformacionTablas(request, response, adminMicrocurriculo);
        registrarSecciones(request, response, adminMicrocurriculo);
        response.sendRedirect("CSM_Software/CSM/director/dashboard/microcurriculo/consultar-microcurriculo.jsp");
    }

    private void crearMicrocurriculo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        new RegistroMicrocurriculoBackground(((Pensum) request.getSession().getAttribute("pensum"))).start();
        response.sendRedirect("ControladorPensum?accion=listarPensum");
    }

    private void verMateria(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter pw = new PrintWriter(response.getOutputStream());
        Integer cod = Integer.parseInt(request.getParameter("cod"));
        Integer sem = Integer.parseInt(request.getParameter("sem"));
        List<Materia> materias[] = (List<Materia>[]) request.getSession().getAttribute("materiasSemestre");
        for (Materia materia : materias[sem - 1]) {
            if (materia.getMateriaPK().getCodigoMateria() == cod) {
                this.paintModal(pw, materia);
                break;
            }
        }
    }

    private void paintModal(PrintWriter pw, Materia materia) {
        AdministrarMicrocurriculo adminMicro = new AdministrarMicrocurriculo();
        Microcurriculo micro = adminMicro.obtenerMicrocurriculo(materia.getMateriaPK().getCodigoMateria(), materia.getMateriaPK().getPensumCodigo());
        List<String[][]> retrieveDataTable = AdministrarMicrocurriculo.ordenarTablaInfo(micro);
        StringBuilder build = new StringBuilder();
        this.getModalHeader(materia, build);
        this.getModalBody(micro, retrieveDataTable.get(0), build);
        pw.write(build.toString());
        pw.flush();
    }

    private void getModalHeader(Materia materia, StringBuilder build) {
        build.append("<div class='modal-header no-bd'>");
        build.append("<h4 class='modal-title'><b>");
        build.append(materia.getNombre());
        build.append(" - ");
        build.append(materia.getMateriaPK().getCodigoMateria());
        build.append("</b></h4>");
        build.append("<button type='button' class='close' data-dismiss='modal' aria-label='Close'>");
        build.append("<span aria-hidden='true'>&times;</span>");
        build.append("</button>");
        build.append("</div>");
    }

    private void getModalBody(Microcurriculo micro, String contenido[][], StringBuilder build) {
        build.append("<div class='modal-body'>");
        for (SeccionMicrocurriculo seccion : micro.getSeccionMicrocurriculoList()) {
            if (seccion.getSeccionId().getId() == 1 || seccion.getSeccionId().getId() == 9) {
                build.append("<div class='col-12'>");
                build.append("<div class='form-group form-group-default'>");
                build.append("<h6><b>");
                build.append(seccion.getSeccionId().getNombre().toUpperCase());
                build.append("</b></h6>");
                if (seccion.getSeccionId().getId() == 1) {
                    for (String row[] : contenido) {
                        build.append("<p>UNIDAD ");
                        build.append(row[0]);
                        build.append(". ");
                        build.append(row[1]);
                        build.append("</p>");
                    }
                } else {
                    build.append("<p>");
                    build.append(seccion.getContenidoList().get(0).getTexto());
                    build.append("</p>");
                }
                build.append("</div>");
                build.append("</div>");
            }
        }
        build.append("</div>");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
