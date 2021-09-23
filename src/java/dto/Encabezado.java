/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sachikia
 */
@Entity
@Table(name = "encabezado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Encabezado.findAll", query = "SELECT e FROM Encabezado e")
    , @NamedQuery(name = "Encabezado.findByColumna", query = "SELECT e FROM Encabezado e WHERE e.encabezadoPK.columna = :columna")
    , @NamedQuery(name = "Encabezado.findByTablaId", query = "SELECT e FROM Encabezado e WHERE e.encabezadoPK.tablaId = :tablaId")
    , @NamedQuery(name = "Encabezado.findByEncabezado", query = "SELECT e FROM Encabezado e WHERE e.encabezado = :encabezado")})
public class Encabezado implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EncabezadoPK encabezadoPK;
    @Basic(optional = false)
    @Column(name = "encabezado")
    private String encabezado;
    @JoinColumn(name = "tabla_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Tabla tabla;

    public Encabezado() {
    }

    public Encabezado(EncabezadoPK encabezadoPK) {
        this.encabezadoPK = encabezadoPK;
    }

    public Encabezado(EncabezadoPK encabezadoPK, String encabezado) {
        this.encabezadoPK = encabezadoPK;
        this.encabezado = encabezado;
    }

    public Encabezado(int columna, int tablaId) {
        this.encabezadoPK = new EncabezadoPK(columna, tablaId);
    }

    public EncabezadoPK getEncabezadoPK() {
        return encabezadoPK;
    }

    public void setEncabezadoPK(EncabezadoPK encabezadoPK) {
        this.encabezadoPK = encabezadoPK;
    }

    public String getEncabezado() {
        return encabezado;
    }

    public void setEncabezado(String encabezado) {
        this.encabezado = encabezado;
    }

    public Tabla getTabla() {
        return tabla;
    }

    public void setTabla(Tabla tabla) {
        this.tabla = tabla;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (encabezadoPK != null ? encabezadoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Encabezado)) {
            return false;
        }
        Encabezado other = (Encabezado) object;
        if ((this.encabezadoPK == null && other.encabezadoPK != null) || (this.encabezadoPK != null && !this.encabezadoPK.equals(other.encabezadoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.Encabezado[ encabezadoPK=" + encabezadoPK + " ]";
    }
    
}
