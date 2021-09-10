/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.DocenteJpaController;
import dao.UsuarioJpaController;
import dto.Docente;
import dto.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import negocio.AdministrarDocentes;
import negocio.Login;
import util.Conexion;

/**
 *
 * @author Jhoser
 */
@WebServlet(name = "ControladorDocente", urlPatterns = {"/ControladorDocente"})
public class ControladorDocente extends HttpServlet {
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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControladorDocente</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorDocente at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            switch (request.getParameter("action")) {
                case "listarDocente":
                    this.listarDocente(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        try {
            switch (request.getParameter("action")) {
                case "Registrar":
                    this.guardarDocente(request, response);
                    break;
                case "activarDocente":
                    this.activarDocente(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void activarDocente(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
        AdministrarDocentes docentes = new AdministrarDocentes();
        String codDocente = request.getParameter("cod");
        Docente d = docentes.obtenerDocente(Integer.parseInt(codDocente));
        docentes.activarDocente(d, d.getEstado()!=1);
        request.getSession().setAttribute("numDocActivos", docentes.getNumDocentesActivos());
    }

    public void guardarDocente(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
        //lectura de datos
        int codigo = Integer.parseInt(request.getParameter("txtCodigo"));
        String nombre = request.getParameter("txtNombre");
        String apellido = request.getParameter("txtApellido");
        int departamento = Integer.parseInt(request.getParameter("optionDepartamento"));
        short estado = 1;
        String password = request.getParameter("txtPassword");
        //Creacion del docente
        AdministrarDocentes docentes = new AdministrarDocentes();
        docentes.guardarDocente(nombre, apellido, departamento, codigo, estado);
        //Creacion del usuario
        Login l = new Login();
        l.guardarDocente(password, codigo);
        response.sendRedirect("CSM_Software/CSM/sign-in/singin.jsp");
    }

    public void listarDocente(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
        AdministrarDocentes d = new AdministrarDocentes();
        List<Docente> docentes = d.listarDocentes();
        request.getSession().setAttribute("listaDocentes", docentes);
        response.sendRedirect("CSM_Software/CSM/director/dashboard/docentes.jsp");
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
