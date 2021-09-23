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
@Table(name = "cumplimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cumplimiento.findAll", query = "SELECT c FROM Cumplimiento c")
    , @NamedQuery(name = "Cumplimiento.findByMateriaPeriodoGrupoId", query = "SELECT c FROM Cumplimiento c WHERE c.cumplimientoPK.materiaPeriodoGrupoId = :materiaPeriodoGrupoId")
    , @NamedQuery(name = "Cumplimiento.findByContenidoUnidadId", query = "SELECT c FROM Cumplimiento c WHERE c.cumplimientoPK.contenidoUnidadId = :contenidoUnidadId")
    , @NamedQuery(name = "Cumplimiento.findByEstado", query = "SELECT c FROM Cumplimiento c WHERE c.estado = :estado")})
public class Cumplimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CumplimientoPK cumplimientoPK;
    @Basic(optional = false)
    @Column(name = "estado")
    private short estado;
    @JoinColumn(name = "contenido_unidad_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ContenidoUnidad contenidoUnidad;
    @JoinColumn(name = "materia_periodo_grupo_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private MateriaPeriodoGrupo materiaPeriodoGrupo;

    public Cumplimiento() {
    }

    public Cumplimiento(CumplimientoPK cumplimientoPK) {
        this.cumplimientoPK = cumplimientoPK;
    }

    public Cumplimiento(CumplimientoPK cumplimientoPK, short estado) {
        this.cumplimientoPK = cumplimientoPK;
        this.estado = estado;
    }

    public Cumplimiento(int materiaPeriodoGrupoId, int contenidoUnidadId) {
        this.cumplimientoPK = new CumplimientoPK(materiaPeriodoGrupoId, contenidoUnidadId);
    }

    public CumplimientoPK getCumplimientoPK() {
        return cumplimientoPK;
    }

    public void setCumplimientoPK(CumplimientoPK cumplimientoPK) {
        this.cumplimientoPK = cumplimientoPK;
    }

    public short getEstado() {
        return estado;
    }

    public void setEstado(short estado) {
        this.estado = estado;
    }

    public ContenidoUnidad getContenidoUnidad() {
        return contenidoUnidad;
    }

    public void setContenidoUnidad(ContenidoUnidad contenidoUnidad) {
        this.contenidoUnidad = contenidoUnidad;
    }

    public MateriaPeriodoGrupo getMateriaPeriodoGrupo() {
        return materiaPeriodoGrupo;
    }

    public void setMateriaPeriodoGrupo(MateriaPeriodoGrupo materiaPeriodoGrupo) {
        this.materiaPeriodoGrupo = materiaPeriodoGrupo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cumplimientoPK != null ? cumplimientoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cumplimiento)) {
            return false;
        }
        Cumplimiento other = (Cumplimiento) object;
        if ((this.cumplimientoPK == null && other.cumplimientoPK != null) || (this.cumplimientoPK != null && !this.cumplimientoPK.equals(other.cumplimientoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.Cumplimiento[ cumplimientoPK=" + cumplimientoPK + " ]";
    }
    
}
