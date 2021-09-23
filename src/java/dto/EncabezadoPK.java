/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Sachikia
 */
@Embeddable
public class EncabezadoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "columna")
    private int columna;
    @Basic(optional = false)
    @Column(name = "tabla_id")
    private int tablaId;

    public EncabezadoPK() {
    }

    public EncabezadoPK(int columna, int tablaId) {
        this.columna = columna;
        this.tablaId = tablaId;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public int getTablaId() {
        return tablaId;
    }

    public void setTablaId(int tablaId) {
        this.tablaId = tablaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) columna;
        hash += (int) tablaId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EncabezadoPK)) {
            return false;
        }
        EncabezadoPK other = (EncabezadoPK) object;
        if (this.columna != other.columna) {
            return false;
        }
        if (this.tablaId != other.tablaId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.EncabezadoPK[ columna=" + columna + ", tablaId=" + tablaId + " ]";
    }
    
}
