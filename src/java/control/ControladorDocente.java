/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dto.Docente;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import negocio.AdministrarDocentes;
import negocio.Login;

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
        try{
            switch (request.getParameter("action")) {
                case "registrar":
                    this.guardarDocente(request, response);
                    break;
                case "activarDocente":
                    this.activarDocente(request, response);
                    break;
                case "listarDocente":
                    this.listarDocente(request, response);
                    break;
            }
        }
        catch(Exception e){
            e.printStackTrace();
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
        this.processRequest(request, response);
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
        this.processRequest(request, response);
    }

    public void activarDocente(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
        AdministrarDocentes adminDocentes = new AdministrarDocentes();
        
        String codDocente = request.getParameter("cod");
        Docente docente = adminDocentes.obtenerDocente(Integer.parseInt(codDocente));
        
        adminDocentes.activarDocente(docente, docente.getEstado() != 1);
        
        request.getSession().setAttribute("numDocActivos", adminDocentes.getNumDocentesActivos());
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
        AdministrarDocentes adminDocentes = new AdministrarDocentes();
        Docente docente = adminDocentes.guardarDocente(nombre, apellido, departamento, codigo, estado);
        
        //Creacion del usuario
        Login l = new Login();
        l.guardarUsuario(docente, password);
        
        response.sendRedirect("CSM_Software/CSM/sign-in/singin.jsp");
    }

    public void listarDocente(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
        AdministrarDocentes adminDocentes = new AdministrarDocentes();
        List<Docente> docentes = adminDocentes.listarDocentes();
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
