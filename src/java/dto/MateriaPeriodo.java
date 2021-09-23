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
import javax.persistence.JoinColumns;
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
@Table(name = "materia_periodo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MateriaPeriodo.findAll", query = "SELECT m FROM MateriaPeriodo m")
    , @NamedQuery(name = "MateriaPeriodo.findById", query = "SELECT m FROM MateriaPeriodo m WHERE m.id = :id")
    , @NamedQuery(name = "MateriaPeriodo.findByAnio", query = "SELECT m FROM MateriaPeriodo m WHERE m.anio = :anio")
    , @NamedQuery(name = "MateriaPeriodo.findByPeriodo", query = "SELECT m FROM MateriaPeriodo m WHERE m.periodo = :periodo")})
public class MateriaPeriodo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "anio")
    private int anio;
    @Basic(optional = false)
    @Column(name = "periodo")
    private int periodo;
    @JoinColumns({
        @JoinColumn(name = "codigo_materia", referencedColumnName = "codigo")
        , @JoinColumn(name = "materia_pensum_codigo", referencedColumnName = "pensum_codigo")})
    @ManyToOne(optional = false)
    private Materia materia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "materiaPeriodoId")
    private List<MateriaPeriodoGrupo> materiaPeriodoGrupoList;

    public MateriaPeriodo() {
    }

    public MateriaPeriodo(Integer id) {
        this.id = id;
    }

    public MateriaPeriodo(Integer id, int anio, int periodo) {
        this.id = id;
        this.anio = anio;
        this.periodo = periodo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    @XmlTransient
    public List<MateriaPeriodoGrupo> getMateriaPeriodoGrupoList() {
        return materiaPeriodoGrupoList;
    }

    public void setMateriaPeriodoGrupoList(List<MateriaPeriodoGrupo> materiaPeriodoGrupoList) {
        this.materiaPeriodoGrupoList = materiaPeriodoGrupoList;
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
        if (!(object instanceof MateriaPeriodo)) {
            return false;
        }
        MateriaPeriodo other = (MateriaPeriodo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.MateriaPeriodo[ id=" + id + " ]";
    }
    
}
