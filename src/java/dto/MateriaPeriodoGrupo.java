/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Sachikia
 */
@Entity
@Table(name = "materia_periodo_grupo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MateriaPeriodoGrupo.findAll", query = "SELECT m FROM MateriaPeriodoGrupo m")
    , @NamedQuery(name = "MateriaPeriodoGrupo.findById", query = "SELECT m FROM MateriaPeriodoGrupo m WHERE m.id = :id")
    , @NamedQuery(name = "MateriaPeriodoGrupo.findByGrupo", query = "SELECT m FROM MateriaPeriodoGrupo m WHERE m.grupo = :grupo")})
public class MateriaPeriodoGrupo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "grupo")
    private String grupo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "materiaPeriodoGrupo")
    private List<Cumplimiento> cumplimientoList;
    @JoinColumn(name = "docente_codigo", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Docente docenteCodigo;
    @JoinColumn(name = "materia_periodo_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private MateriaPeriodo materiaPeriodoId;

    public MateriaPeriodoGrupo() {
    }

    public MateriaPeriodoGrupo(Integer id) {
        this.id = id;
    }

    public MateriaPeriodoGrupo(Integer id, String grupo) {
        this.id = id;
        this.grupo = grupo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    @XmlTransient
    public List<Cumplimiento> getCumplimientoList() {
        return cumplimientoList;
    }

    public void setCumplimientoList(List<Cumplimiento> cumplimientoList) {
        this.cumplimientoList = cumplimientoList;
    }

    public Docente getDocenteCodigo() {
        return docenteCodigo;
    }

    public void setDocenteCodigo(Docente docenteCodigo) {
        this.docenteCodigo = docenteCodigo;
    }

    public MateriaPeriodo getMateriaPeriodoId() {
        return materiaPeriodoId;
    }

    public void setMateriaPeriodoId(MateriaPeriodo materiaPeriodoId) {
        this.materiaPeriodoId = materiaPeriodoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MateriaPeriodoGrupo)) {
            return false;
        }
        MateriaPeriodoGrupo other = (MateriaPeriodoGrupo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.MateriaPeriodoGrupo[ id=" + id + " ]";
    }
    
}
