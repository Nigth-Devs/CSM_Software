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
public class MicrocurriculoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "materia_codigo")
    private int materiaCodigo;
    @Basic(optional = false)
    @Column(name = "materia_pensum_codigo")
    private int materiaPensumCodigo;

    public MicrocurriculoPK() {
    }

    public MicrocurriculoPK(int materiaCodigo, int materiaPensumCodigo) {
        this.materiaCodigo = materiaCodigo;
        this.materiaPensumCodigo = materiaPensumCodigo;
    }

    public int getMateriaCodigo() {
        return materiaCodigo;
    }

    public void setMateriaCodigo(int materiaCodigo) {
        this.materiaCodigo = materiaCodigo;
    }

    public int getMateriaPensumCodigo() {
        return materiaPensumCodigo;
    }

    public void setMateriaPensumCodigo(int materiaPensumCodigo) {
        this.materiaPensumCodigo = materiaPensumCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) materiaCodigo;
        hash += (int) materiaPensumCodigo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MicrocurriculoPK)) {
            return false;
        }
        MicrocurriculoPK other = (MicrocurriculoPK) object;
        if (this.materiaCodigo != other.materiaCodigo) {
            return false;
        }
        if (this.materiaPensumCodigo != other.materiaPensumCodigo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.MicrocurriculoPK[ materiaCodigo=" + materiaCodigo + ", materiaPensumCodigo=" + materiaPensumCodigo + " ]";
    }
    
}
