/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dto.MateriaPeriodoGrupo;
import dto.Pensum;
import dto.Programa;
import dto.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import negocio.AdministrarPensum;
import negocio.AdministrarPrograma;
import negocio.AdministrarSeguimiento;

/**
 *
 * @author Sachikia
 */
@WebServlet(name = "ControladorSeguimiento", urlPatterns = {"/ControladorSeguimiento"})
public class ControladorSeguimiento extends HttpServlet {

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
        switch(request.getParameter("accion")){
            case "list": this.listar(request, response);
            break;
            case "listTo": this.listarPara(request, response);
            break;
            case "ver-cumplimiento": this.verCumplimiento(request, response);
            break;
        }
    }

    private void listar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        AdministrarSeguimiento adminSeg = new AdministrarSeguimiento();
        Usuario user = (Usuario)request.getSession().getAttribute("usuario");
        Programa programa = new AdministrarPrograma().refreshPrograma(user.getDocente().getProgramaCodigo());
        List<Object[]> info = null;
        if(user.getRolId().getId()==1){
            info = adminSeg.getLastSeguimientoGeneral(programa);
        }else{
            info = adminSeg.getLastSeguimientoGeneral(user.getDocente());
        }
        request.getSession().setAttribute("listSeguimiento", info);
        response.sendRedirect("CSM_Software/CSM/"+(user.getRolId().getId()==1 ? "director" : "docente")+"/dashboard/seguimiento.jsp");
    }
    
    private void listarPara(HttpServletRequest request, HttpServletResponse response) throws IOException {
        AdministrarSeguimiento adminSeg = new AdministrarSeguimiento();
        Usuario user = (Usuario)request.getSession().getAttribute("usuario");
        int materiaPeriodoId = Integer.parseInt(request.getParameter("mat_per"));
        List<Object[]> info = null;
        if(user.getRolId().getId()==1){
//            info = adminSeg.getLastSeguimientoGeneral(programa);
        }else{
            info = adminSeg.getSeguimientoMateriaPeriodo(materiaPeriodoId);
        }
        request.getSession().setAttribute("listSeguimientoMateria", info);
        response.sendRedirect("CSM_Software/CSM/"+(user.getRolId().getId()==1 ? "director" : "docente")+"/dashboard/seguimiento/seguimiento-materia.jsp");
    }
    
    private void verCumplimiento(HttpServletRequest request, HttpServletResponse response) throws IOException {
        AdministrarSeguimiento adminSeg = new AdministrarSeguimiento();
        Usuario user = (Usuario)request.getSession().getAttribute("usuario");
        int materiaPeriodoGrupoId = Integer.parseInt(request.getParameter("mat_per_gr"));
        MateriaPeriodoGrupo materiaPeriodoGrupo = adminSeg.getMateriaPeriodoGrupo(materiaPeriodoGrupoId);
        int percentage = (int)adminSeg.getPercentageMateriaPeriodoGrupo(materiaPeriodoGrupo);
        Object tools[] = adminSeg.getPercentagesPerUnitFor(materiaPeriodoGrupo);
        request.getSession().setAttribute("percentages", tools[0]);
        request.getSession().setAttribute("checkeds", tools[1]);
        request.getSession().setAttribute("mat_per_gr", materiaPeriodoGrupo);
        response.sendRedirect("CSM_Software/CSM/"+(user.getRolId().getId()==1 ? "director" : "docente")+"/dashboard/seguimiento/ver-cumplimiento.jsp");
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
        processRequest(request, response);
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
