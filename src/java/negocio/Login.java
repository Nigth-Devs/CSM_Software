/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import dao.UsuarioJpaController;
import dto.Docente;
import dto.Rol;
import dto.Usuario;
import util.Conexion;
import util.PasswordAuthentication;

/**
 *
 * @author Manuel
 */
public class Login {

    public Login() {
    }

    public Usuario validarUsuario(int codigo, String contrasena, int rol) {
        Conexion con = Conexion.getConexion();
        UsuarioJpaController daoUsuario = new UsuarioJpaController(con.getBd());
        
        Usuario usuario = daoUsuario.findUsuario(codigo);
        
        return autenticarContra(usuario, contrasena, rol) ? usuario : null;
    }
    
    public dto.Docente obtenerDocente(int codigo) {
        Conexion con = Conexion.getConexion();
        dao.DocenteJpaController daoDocente = new dao.DocenteJpaController(con.getBd());
        return daoDocente.findDocente(codigo);
    }

    public void guardarUsuario(Docente docente, String password) throws Exception {
        Conexion con = Conexion.getConexion();
        PasswordAuthentication encriptarPass = new PasswordAuthentication();
        dao.UsuarioJpaController daoUsuario = new dao.UsuarioJpaController(con.getBd());
        
        password = encriptarPass.hash(password.toCharArray()); //encriptando password
        
        Usuario usuario = new Usuario(docente.getCodigo());
        usuario.setRolId(new Rol(2));
        usuario.setDocente(docente);
        usuario.setClave(password);
        
        daoUsuario.create(usuario); //usuario creado
    }

    public boolean autenticarContra(Usuario usuario, String password, int rol) {
        PasswordAuthentication encriptarPass = new PasswordAuthentication();
        boolean correctPass = encriptarPass.authenticate(password.toCharArray(), usuario.getClave());
        return correctPass && usuario.getRolId().getId() == rol && usuario.getDocente().getEstado() == 1;
    }
}
