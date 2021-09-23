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
@Table(name = "contenido_unidad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContenidoUnidad.findAll", query = "SELECT c FROM ContenidoUnidad c")
    , @NamedQuery(name = "ContenidoUnidad.findById", query = "SELECT c FROM ContenidoUnidad c WHERE c.id = :id")
    , @NamedQuery(name = "ContenidoUnidad.findByContenido", query = "SELECT c FROM ContenidoUnidad c WHERE c.contenido = :contenido")
    , @NamedQuery(name = "ContenidoUnidad.findByTrabajoPresencial", query = "SELECT c FROM ContenidoUnidad c WHERE c.trabajoPresencial = :trabajoPresencial")
    , @NamedQuery(name = "ContenidoUnidad.findByTrabajoIndependiente", query = "SELECT c FROM ContenidoUnidad c WHERE c.trabajoIndependiente = :trabajoIndependiente")})
public class ContenidoUnidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "contenido")
    private String contenido;
    @Column(name = "trabajo_presencial")
    private String trabajoPresencial;
    @Column(name = "trabajo_independiente")
    private String trabajoIndependiente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contenidoUnidad")
    private List<Cumplimiento> cumplimientoList;
    @JoinColumn(name = "unidad_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Unidad unidadId;

    public ContenidoUnidad() {
    }

    public ContenidoUnidad(Integer id) {
        this.id = id;
    }

    public ContenidoUnidad(Integer id, String contenido) {
        this.id = id;
        this.contenido = contenido;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getTrabajoPresencial() {
        return trabajoPresencial;
    }

    public void setTrabajoPresencial(String trabajoPresencial) {
        this.trabajoPresencial = trabajoPresencial;
    }

    public String getTrabajoIndependiente() {
        return trabajoIndependiente;
    }

    public void setTrabajoIndependiente(String trabajoIndependiente) {
        this.trabajoIndependiente = trabajoIndependiente;
    }

    @XmlTransient
    public List<Cumplimiento> getCumplimientoList() {
        return cumplimientoList;
    }

    public void setCumplimientoList(List<Cumplimiento> cumplimientoList) {
        this.cumplimientoList = cumplimientoList;
    }

    public Unidad getUnidadId() {
        return unidadId;
    }

    public void setUnidadId(Unidad unidadId) {
        this.unidadId = unidadId;
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
        if (!(object instanceof ContenidoUnidad)) {
            return false;
        }
        ContenidoUnidad other = (ContenidoUnidad) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.ContenidoUnidad[ id=" + id + " ]";
    }
    
}
