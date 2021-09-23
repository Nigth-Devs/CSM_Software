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
public class MateriaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "codigo")
    private int codigo;
    @Basic(optional = false)
    @Column(name = "pensum_codigo")
    private int pensumCodigo;

    public MateriaPK() {
    }

    public MateriaPK(int codigo, int pensumCodigo) {
        this.codigo = codigo;
        this.pensumCodigo = pensumCodigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getPensumCodigo() {
        return pensumCodigo;
    }

    public void setPensumCodigo(int pensumCodigo) {
        this.pensumCodigo = pensumCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigo;
        hash += (int) pensumCodigo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MateriaPK)) {
            return false;
        }
        MateriaPK other = (MateriaPK) object;
        if (this.codigo != other.codigo) {
            return false;
        }
        if (this.pensumCodigo != other.pensumCodigo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.MateriaPK[ codigo=" + codigo + ", pensumCodigo=" + pensumCodigo + " ]";
    }
    
}
