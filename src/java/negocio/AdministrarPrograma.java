/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import dao.ProgramaJpaController;
import dto.Programa;
import util.Conexion;

/**
 *
 * @author Sachikia
 */
public class AdministrarPrograma {
    public Programa refreshPrograma(Programa programa){
        return new ProgramaJpaController(Conexion.getConexion().getBd()).findPrograma(programa.getCodigo());
    }
}
