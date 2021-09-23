/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.exceptions.IllegalOrphanException;
import dao.exceptions.NonexistentEntityException;
import dto.Docente;
import dto.MateriaPeriodo;
import dto.MateriaPeriodoGrupo;
import dto.Programa;
import dto.Usuario;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import negocio.AdministrarDocentes;
import negocio.AdministrarGrupos;
import negocio.AdministrarPrograma;

/**
 *
 * @author Jhoser
 */
@WebServlet(name = "ControladorGrupos", urlPatterns = {"/ControladorGrupos"})
public class ControladorGrupos extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            switch (request.getParameter("accion")) {
                case "registrar":
                    crearGrupo(request, response);
                    break;
                case "eliminar":
                    eliminarMateriaPG(request, response);
                    break;
                case "listar":
                    listar(request, response);;
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.processRequest(request, response);
    }

    public void eliminarMateriaPG(HttpServletRequest request, HttpServletResponse response) throws NonexistentEntityException, IOException, IllegalOrphanException {
        int id = Integer.parseInt(request.getParameter("id"));
        negocio.AdministrarGrupos admin = new AdministrarGrupos();
        admin.eliminarGrupo(id);
        listar(request, response);
    }

    public void crearGrupo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int codigoPensum = (Integer.parseInt(request.getParameter("optionPensum")));
        int optionMateria = (Integer.parseInt(request.getParameter("optionMateria")));
        int codigoDocente = (Integer.parseInt(request.getParameter("optionDocente")));
        int anio = Integer.parseInt(request.getParameter("anio"));
        int periodo = Integer.parseInt(request.getParameter("periodo"));
        
        MateriaPeriodo materiaPeriodo = validarMateriaPeriodo(request, response, anio, periodo, optionMateria, codigoPensum);
        validarMateriaPeriodoGrupo(request, response, materiaPeriodo, codigoDocente);
        
        response.sendRedirect("ControladorGrupos?accion=listar");
    }

    public void validarMateriaPeriodoGrupo(HttpServletRequest request, HttpServletResponse response, MateriaPeriodo materiaPeriodo, int codigoDocente) throws Exception {
        negocio.AdministrarGrupos admin = new negocio.AdministrarGrupos();
        admin.validarMateriaPeriodoGrupo(materiaPeriodo, codigoDocente);
    }

    public MateriaPeriodo validarMateriaPeriodo(HttpServletRequest request, HttpServletResponse response, int anio, int semestre, int codigoMateria, int codigoPensum) throws Exception {
        AdministrarGrupos admin = new AdministrarGrupos();
        return admin.validarMateriaPeriodo(anio, semestre, codigoMateria, codigoPensum);
    }

    public void listar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.loadData(request, response);
        response.sendRedirect("CSM_Software/CSM/director/dashboard/grupos.jsp");
    }

    public void loadData(HttpServletRequest request, HttpServletResponse response) {
        AdministrarGrupos adminGrupos = new AdministrarGrupos();
        AdministrarPrograma adminPrograma = new AdministrarPrograma();
        
        Usuario usuario = (Usuario)request.getSession().getAttribute("usuario");
        Programa programa = adminPrograma.refreshPrograma(usuario.getDocente().getProgramaCodigo());
        
        List<MateriaPeriodoGrupo> grupos = adminGrupos.obtenerMateriaPeriodoGrupo(programa);
        List<Docente> docentes = adminGrupos.obtenerDocentes(programa);
        
        request.getSession().setAttribute("grupos", grupos);
        request.getSession().setAttribute("docentesPrograma", docentes);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.processRequest(request, response);
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
