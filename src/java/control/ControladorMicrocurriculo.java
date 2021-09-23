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
import dto.Unidad;
import dto.Usuario;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import negocio.AdministrarMicrocurriculo;
import negocio.AdministrarPrograma;
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
        String accion = request.getParameter("accion");
        if (accion.equalsIgnoreCase("listarTodos")) {
            listar(request, response);
        }
        if (accion.equalsIgnoreCase("to-editar")) {
            toEditar(request, response);
        }
        if (accion.equalsIgnoreCase("consultar")) {
            consultar(request, response);
        }
        if (accion.equals("editar")) {
            try {
                editar(request, response);
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
        if (accion.equals("registrar")) {
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
    
    public void listar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        AdministrarMicrocurriculo adminMicrocurriculo = new AdministrarMicrocurriculo();
        Usuario usuario = (dto.Usuario) request.getSession().getAttribute("usuario");
        List<Materia> materias = null;
        String url = "";
        if (usuario.getRolId().getId() == 1) {
            materias = adminMicrocurriculo.obtenerTodasMateria(new AdministrarPrograma().refreshPrograma(usuario.getDocente().getProgramaCodigo()));
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

    public void toEditar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int codigoPensum = Integer.parseInt(request.getParameter("codigoPensum"));
        int codigoMateria = Integer.parseInt(request.getParameter("codigoMateria"));

        AdministrarMicrocurriculo adminMicrocurriculo = new AdministrarMicrocurriculo();
        Microcurriculo microcurriculo = adminMicrocurriculo.obtenerMicrocurriculo(codigoMateria, codigoPensum);

        request.getSession().setAttribute("microcurriculo", microcurriculo);
        response.sendRedirect("CSM_Software/CSM/director/dashboard/microcurriculo/editar-microcurriculo.jsp");
    }

    public void consultar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        AdministrarMicrocurriculo adminMicrocurriculo = new AdministrarMicrocurriculo();
        int codigoPensum = Integer.parseInt(request.getParameter("codigoPensum"));
        int codigoMateria = Integer.parseInt(request.getParameter("codigoMateria"));

        Microcurriculo microcurriculo = adminMicrocurriculo.obtenerMicrocurriculo(codigoMateria, codigoPensum);

        request.getSession().setAttribute("microcurriculo", microcurriculo);

        String url = "";
        dto.Usuario user = (dto.Usuario) (request.getSession().getAttribute("usuario"));
        if (user.getRolId().getId().equals(1)) {
            url = "director";
        } else {
            url = "docente";
        }
        response.sendRedirect("CSM_Software/CSM/" + url + "/dashboard/microcurriculo/ver-microcurriculo.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.processRequest(request, response);
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
        this.processRequest(request, response);
    }

    public void registrarInformacionTablas(HttpServletRequest request, HttpServletResponse response, AdministrarMicrocurriculo adminM, Microcurriculo microcurriculo) throws Exception {
        String contenidos[][][] = new String[2][][];
        for (SeccionMicrocurriculo seccion : microcurriculo.getSeccionMicrocurriculoList()) {
            if (seccion.getSeccionId().getTipoSeccionId().getId() == 2) {
                contenidos[seccion.getSeccionId().getId() == 1 ? 0 : 1] = this.getInfoTabla(request, seccion);
            }
        }
        String idContentsExist[] = request.getParameterValues("old_content");
        String idUnidsExist[] = request.getParameterValues("old_unit");
        adminM.updateUnidades(contenidos, new String[][]{idUnidsExist, idContentsExist}, microcurriculo);
    }

    private String[][] getInfoTabla(HttpServletRequest request, SeccionMicrocurriculo seccion) {
        int cantidadFilas = Integer.parseInt(request.getParameter("nfilas-" + seccion.getId()));

        String contenidos[][] = new String[cantidadFilas][seccion.getTablaSeccion().getTablaId().getEncabezadoList().size()];
        for (int i = 0; i < contenidos.length; i++) {
            for (int j = 0; j < contenidos[i].length; j++) {
                contenidos[i][j] = (String) request.getParameter("contenido-" + seccion.getSeccionId().getId() + "-" + i + "-" + j);
            }
        }
        return contenidos;
    }

    public void registrarSecciones(HttpServletRequest request, HttpServletResponse response, AdministrarMicrocurriculo adminMicro, Microcurriculo microcurriculo) throws Exception {
        List<SeccionMicrocurriculo> secciones = microcurriculo.getSeccionMicrocurriculoList();
        for (SeccionMicrocurriculo seccion : secciones) {
            if (seccion.getSeccionId().getTipoSeccionId().getId() != 2) {
                String informacion = request.getParameter("seccion-" + seccion.getSeccionId().getId());
                adminMicro.ingresarContenidoSeccion(informacion, seccion);
            }
        }
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        AdministrarMicrocurriculo adminMicrocurriculo = new AdministrarMicrocurriculo();
        int areaFormacion = Integer.parseInt(request.getParameter("areasFormacion"));
        Microcurriculo microcurriculo = (dto.Microcurriculo) request.getSession().getAttribute("microcurriculo");

        adminMicrocurriculo.actualizarAreaFormacionMicrocurriculo(microcurriculo, areaFormacion);
        registrarInformacionTablas(request, response, adminMicrocurriculo, microcurriculo);
        registrarSecciones(request, response, adminMicrocurriculo, microcurriculo);

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
            if (materia.getMateriaPK().getCodigo() == cod) {
                this.paintModal(pw, materia);
                break;
            }
        }
    }

    private void paintModal(PrintWriter pw, Materia materia) {
        AdministrarMicrocurriculo adminMicro = new AdministrarMicrocurriculo();
        Microcurriculo micro = adminMicro.obtenerMicrocurriculo(materia.getMateriaPK().getCodigo(), materia.getMateriaPK().getPensumCodigo());
        StringBuilder build = new StringBuilder();
        this.getModalHeader(materia, build);
        this.getModalBody(micro, build);
        pw.write(build.toString());
        pw.flush();
    }

    private void getModalHeader(Materia materia, StringBuilder build) {
        build.append("<div class='modal-header no-bd'>");
        build.append("<h4 class='modal-title'><b>");
        build.append(materia.getNombre());
        build.append(" - ");
        build.append(materia.getMateriaPK().getCodigo());
        build.append("</b></h4>");
        build.append("<button type='button' class='close' data-dismiss='modal' aria-label='Close'>");
        build.append("<span aria-hidden='true'>&times;</span>");
        build.append("</button>");
        build.append("</div>");
    }

    private void getModalBody(Microcurriculo micro, StringBuilder build) {
        build.append("<div class='modal-body'>");
        for (SeccionMicrocurriculo seccion : micro.getSeccionMicrocurriculoList()) {
            if (seccion.getSeccionId().getId() == 1 || seccion.getSeccionId().getId() == 9) {
                build.append("<div class='col-12'>");
                build.append("<div class='form-group form-group-default'>");
                build.append("<h6><b>");
                build.append(seccion.getSeccionId().getNombre().toUpperCase());
                build.append("</b></h6>");
                if (seccion.getSeccionId().getId() == 1) {
                    for (Unidad unidad : micro.getUnidadList()) {
                        build.append("<p>UNIDAD ");
                        build.append(unidad.getNum());
                        build.append(". ");
                        build.append(unidad.getNombre());
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
