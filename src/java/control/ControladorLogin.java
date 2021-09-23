/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dto.Pensum;
import dto.Usuario;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import negocio.AdministrarDocentes;
import negocio.AdministrarPensum;
import negocio.AdministrarSeguimiento;
import negocio.Login;

/**
 *
 * @author Manuel
 */
@WebServlet(name = "ControladorLogin", urlPatterns = {"/ControladorLogin"})
public class ControladorLogin extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion.equals("Iniciar Sesion")) {
            verificarUsuario(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    public void verificarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int codigo = Integer.parseInt(request.getParameter("codigo"));
        String contrasena = request.getParameter("clave");
        int rol = Integer.parseInt(request.getParameter("rol"));
        
        Login login = new Login();
        Usuario usuario = login.validarUsuario(codigo, contrasena, rol);
        
        if (usuario!=null) {
            cargarInformacion(request, response, usuario);
            response.sendRedirect("CSM_Software/CSM/"+(rol==1 ? "director" : "docente")+"/dashboard/dashboard.jsp");
        } else {
            response.sendRedirect("CSM_Software/CSM/sign-in/singin.jsp");
        }
    }

    public void cargarInformacion(HttpServletRequest request, HttpServletResponse response, Usuario usuario) {
        AdministrarSeguimiento adminSeg = new AdministrarSeguimiento();
        request.getSession().setAttribute("usuario", usuario);
        List<Object[]> info = null;
        cargarLastPensum(request, response, usuario);
        if (usuario.getRolId().getId() == 1) {
            info = adminSeg.getLastSeguimientoGeneral(usuario.getDocente().getProgramaCodigo());
            request.getSession().setAttribute("numDocActivos", new AdministrarDocentes().getNumDocentesActivos());
        }else{
            info = adminSeg.getLastSeguimientoGeneral(usuario.getDocente());
        }
        request.getSession().setAttribute("listSeguimiento", info);
    }
    
    public void cargarLastPensum(HttpServletRequest request, HttpServletResponse response, dto.Usuario usuario) {
        AdministrarPensum adminPensum = new AdministrarPensum();
        Pensum pensum = adminPensum.getLastPensum(usuario);
        if(pensum != null){
            request.getSession().setAttribute("pensum", pensum);
            request.getSession().setAttribute("materiasSemestre", adminPensum.cargarMateriasPorSemestre(pensum));
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
