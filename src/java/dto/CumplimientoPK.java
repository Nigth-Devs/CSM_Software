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
public class CumplimientoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "materia_periodo_grupo_id")
    private int materiaPeriodoGrupoId;
    @Basic(optional = false)
    @Column(name = "contenido_unidad_id")
    private int contenidoUnidadId;

    public CumplimientoPK() {
    }

    public CumplimientoPK(int materiaPeriodoGrupoId, int contenidoUnidadId) {
        this.materiaPeriodoGrupoId = materiaPeriodoGrupoId;
        this.contenidoUnidadId = contenidoUnidadId;
    }

    public int getMateriaPeriodoGrupoId() {
        return materiaPeriodoGrupoId;
    }

    public void setMateriaPeriodoGrupoId(int materiaPeriodoGrupoId) {
        this.materiaPeriodoGrupoId = materiaPeriodoGrupoId;
    }

    public int getContenidoUnidadId() {
        return contenidoUnidadId;
    }

    public void setContenidoUnidadId(int contenidoUnidadId) {
        this.contenidoUnidadId = contenidoUnidadId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) materiaPeriodoGrupoId;
        hash += (int) contenidoUnidadId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CumplimientoPK)) {
            return false;
        }
        CumplimientoPK other = (CumplimientoPK) object;
        if (this.materiaPeriodoGrupoId != other.materiaPeriodoGrupoId) {
            return false;
        }
        if (this.contenidoUnidadId != other.contenidoUnidadId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.CumplimientoPK[ materiaPeriodoGrupoId=" + materiaPeriodoGrupoId + ", contenidoUnidadId=" + contenidoUnidadId + " ]";
    }
    
}
