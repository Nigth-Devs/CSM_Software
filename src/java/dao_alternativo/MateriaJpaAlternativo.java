/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_alternativo;

import dto.EquivalenciaMateria;
import dto.Materia;
import dto.Pensum;
import dto.PrerrequisitoMateria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Dunke
 */
public class MateriaJpaAlternativo {

    private Connection connection;

    public MateriaJpaAlternativo(Connection connection) {
        this.connection = connection;
    }

    public void create(Pensum pensum) throws SQLException {
        PreparedStatement ps = this.connection.prepareStatement("INSERT INTO materia(codigo, nombre, creditos, semestre, pensum_codigo, "
                + "programa_codigo, ht, hp, hti, cr, tipo_id) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
        for (Materia m : pensum.getMateriaList()) {
            ps.setInt(1, m.getMateriaPK().getCodigo());
            ps.setString(2, m.getNombre());
            ps.setInt(3, m.getCreditos());
            ps.setInt(4, m.getSemestre());
            ps.setInt(5, pensum.getPensumPK().getCodigo());
            ps.setInt(6, pensum.getPensumPK().getProgramaCodigo());
            ps.setInt(7, m.getHt());
            ps.setInt(8, m.getHp());
            ps.setInt(9, m.getHti());
            ps.setInt(10, m.getCr());
            ps.setInt(11, m.getTipoId().getId());
            ps.execute();
        }
        ps = this.connection.prepareStatement("INSERT INTO prerrequisito_materia(materia_codigo, materia_pensum_codigo, materia_codigo_prerreq, materia_pensum_codigo_prerreq) VALUES (?,?,?,?)");
        for (Materia m : pensum.getMateriaList()) {
            for (PrerrequisitoMateria m_r : m.getPrerrequisitoMateriaList()) {
                ps.setInt(1, m_r.getMateria().getMateriaPK().getCodigo());
                ps.setInt(2, pensum.getPensumPK().getCodigo());
                ps.setInt(3, m_r.getMateria1().getMateriaPK().getCodigo());
                ps.setInt(4, pensum.getPensumPK().getCodigo());
                ps.execute();
            }
        }
        ps = this.connection.prepareStatement("INSERT INTO equivalencia_materia(equivalencia_materia, nombre, materia_codigo, materia_pensum_codigo) VALUES (?,?,?,?)");
        for (Materia m : pensum.getMateriaList()) {
            for (EquivalenciaMateria m_p : m.getEquivalenciaMateriaList()) {
                ps.setInt(1, m_p.getEquivalenciaMateria());
                ps.setString(2, m_p.getNombre());
                ps.setInt(3, m_p.getMateria().getMateriaPK().getCodigo());
                ps.setInt(4, pensum.getPensumPK().getCodigo());
                ps.execute();
            }
        }
        this.connection.close();
    }
}
