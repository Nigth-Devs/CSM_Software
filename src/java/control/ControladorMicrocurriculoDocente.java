/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dto.Docente;
import dto.Usuario;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import negocio.AdministrarMicrocurriculo;
import negocio.Login;
import negocio.RolDocente;

/**
 *
 * @author Jhoser
 */
@WebServlet(name = "ControladorMicrocurriculoDocente", urlPatterns = {"/ControladorMicrocurriculoDocente"})
public class ControladorMicrocurriculoDocente extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        if (accion.equalsIgnoreCase("listarTodos")) {
            microDocentes(request, response);
        }
        if (accion.equalsIgnoreCase("solicitarCambio")) {
            solicitarCambio(request, response);
        }
        if (accion.equalsIgnoreCase("Solicitud")) {
            solicitud(request, response);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    public void microDocentes(HttpServletRequest request, HttpServletResponse response) throws IOException {
        RolDocente rd = new RolDocente();
        Usuario u = (Usuario) request.getSession().getAttribute("usuario");
        Usuario nuevoUser = rd.buscarUsuario(u.getDocente().getCodigoDocente());
        Login l = new Login();
        Docente d = l.obtenerDocente(nuevoUser.getDocente().getCodigoDocente());
        int codigo = nuevoUser.getDocente().getCodigoDocente();
        request.getSession().setAttribute("misMicrocurriculos", rd.microcurriculosDocentes(codigo));
        response.sendRedirect("jspTest/microcurriculoDocente.jsp");
    }

    public static void listar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("materias");
        AdministrarMicrocurriculo adminMicrocurriculo = new AdministrarMicrocurriculo();
        dto.Usuario usuario = (dto.Usuario) request.getSession().getAttribute("usuario");
        List<dto.Materia> materias = adminMicrocurriculo.obtenerTodasMateria(usuario.getDocente().getProgramaList().get(0).getCodigo());
        request.getSession().setAttribute("areasFormacion", adminMicrocurriculo.obtenerAreasFormacion());
        request.getSession().setAttribute("tipoAsignatura", adminMicrocurriculo.obtenerTiposAisgnatura());
        request.getSession().setAttribute("materias", materias);
        response.sendRedirect("jspTest/microcurriculoDocente.jsp");
    }

    public void editar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("idMicrocurriculo"));
        int codigoPensum = Integer.parseInt(request.getParameter("codigoPensum"));
        int codigoMateria = Integer.parseInt(request.getParameter("codigoMateria"));
        negocio.AdministrarMicrocurriculo adminMicrocurriculo = new negocio.AdministrarMicrocurriculo();
        dto.Microcurriculo microcurriculo = adminMicrocurriculo.obtenerMicrocurriculo(id, codigoMateria, codigoPensum);
        request.getSession().setAttribute("tablas", negocio.AdministrarMicrocurriculo.ordenarTablaInfo(microcurriculo));
        request.getSession().setAttribute("microcurriculo", microcurriculo);
        response.sendRedirect("jspTest/microcurriculoDocente.jsp");
    }

    public void solicitud(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("idMicrocurriculo"));
        int codigoPensum = Integer.parseInt(request.getParameter("codigoPensum"));
        int codigoMateria = Integer.parseInt(request.getParameter("codigoMateria"));
        negocio.AdministrarMicrocurriculo adminMicrocurriculo = new negocio.AdministrarMicrocurriculo();
        dto.Microcurriculo microcurriculo = adminMicrocurriculo.obtenerMicrocurriculo(id, codigoMateria, codigoPensum);
        request.getSession().setAttribute("tablas", negocio.AdministrarMicrocurriculo.ordenarTablaInfo(microcurriculo));
        request.getSession().setAttribute("microcurriculo", microcurriculo);
        response.sendRedirect("CSM_Software/CSM/docente/dashboard/microcurriculo/solicitud-microcurriculo.jsp");
    }

    public void consultar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("idMicrocurriculo"));
        int codigoPensum = Integer.parseInt(request.getParameter("codigoPensum"));
        int codigoMateria = Integer.parseInt(request.getParameter("codigoMateria"));
        negocio.AdministrarMicrocurriculo adminMicrocurriculo = new negocio.AdministrarMicrocurriculo();
        dto.Microcurriculo microcurriculo = adminMicrocurriculo.obtenerMicrocurriculo(id, codigoMateria, codigoPensum);
        request.getSession().setAttribute("tablas", negocio.AdministrarMicrocurriculo.ordenarTablaInfo(microcurriculo));
        request.getSession().setAttribute("microcurriculo", microcurriculo);
        response.sendRedirect("CSM_Software/CSM/docente/dashboard/microcurriculo/ver-microcurriculo.jsp");
    }

    private void solicitarCambio(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idSeccionMicrocurriculo = request.getParameter("idseccionmicrocurriculo");
        String contenido = request.getParameter("contenidonuevo");
        Integer idseccionMi = Integer.parseInt(idSeccionMicrocurriculo);
        negocio.AdministrarMicrocurriculo am = new negocio.AdministrarMicrocurriculo();
        am.realizarSolicitudCambio(idseccionMi, contenido);
        listar(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
