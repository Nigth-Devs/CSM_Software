/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sachikia
 */
@Entity
@Table(name = "tabla_seccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TablaSeccion.findAll", query = "SELECT t FROM TablaSeccion t")
    , @NamedQuery(name = "TablaSeccion.findBySeccionMicrocurriculoId", query = "SELECT t FROM TablaSeccion t WHERE t.seccionMicrocurriculoId = :seccionMicrocurriculoId")})
public class TablaSeccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "seccion_microcurriculo_id")
    private Integer seccionMicrocurriculoId;
    @JoinColumn(name = "seccion_microcurriculo_id", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private SeccionMicrocurriculo seccionMicrocurriculo;
    @JoinColumn(name = "tabla_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Tabla tablaId;

    public TablaSeccion() {
    }

    public TablaSeccion(Integer seccionMicrocurriculoId) {
        this.seccionMicrocurriculoId = seccionMicrocurriculoId;
    }

    public Integer getSeccionMicrocurriculoId() {
        return seccionMicrocurriculoId;
    }

    public void setSeccionMicrocurriculoId(Integer seccionMicrocurriculoId) {
        this.seccionMicrocurriculoId = seccionMicrocurriculoId;
    }

    public SeccionMicrocurriculo getSeccionMicrocurriculo() {
        return seccionMicrocurriculo;
    }

    public void setSeccionMicrocurriculo(SeccionMicrocurriculo seccionMicrocurriculo) {
        this.seccionMicrocurriculo = seccionMicrocurriculo;
    }

    public Tabla getTablaId() {
        return tablaId;
    }

    public void setTablaId(Tabla tablaId) {
        this.tablaId = tablaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (seccionMicrocurriculoId != null ? seccionMicrocurriculoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TablaSeccion)) {
            return false;
        }
        TablaSeccion other = (TablaSeccion) object;
        if ((this.seccionMicrocurriculoId == null && other.seccionMicrocurriculoId != null) || (this.seccionMicrocurriculoId != null && !this.seccionMicrocurriculoId.equals(other.seccionMicrocurriculoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.TablaSeccion[ seccionMicrocurriculoId=" + seccionMicrocurriculoId + " ]";
    }
    
}
