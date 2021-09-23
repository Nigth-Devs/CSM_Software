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
@Table(name = "unidad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Unidad.findAll", query = "SELECT u FROM Unidad u")
    , @NamedQuery(name = "Unidad.findById", query = "SELECT u FROM Unidad u WHERE u.id = :id")
    , @NamedQuery(name = "Unidad.findByNum", query = "SELECT u FROM Unidad u WHERE u.num = :num")
    , @NamedQuery(name = "Unidad.findByNombre", query = "SELECT u FROM Unidad u WHERE u.nombre = :nombre")
    , @NamedQuery(name = "Unidad.findByHorasPresencial", query = "SELECT u FROM Unidad u WHERE u.horasPresencial = :horasPresencial")
    , @NamedQuery(name = "Unidad.findByHorasIndependiente", query = "SELECT u FROM Unidad u WHERE u.horasIndependiente = :horasIndependiente")})
public class Unidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "num")
    private int num;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "horas_presencial")
    private int horasPresencial;
    @Basic(optional = false)
    @Column(name = "horas_independiente")
    private int horasIndependiente;
    @JoinColumns({
        @JoinColumn(name = "microcurriculo_materia_codigo", referencedColumnName = "materia_codigo")
        , @JoinColumn(name = "microcurriculo_materia_pensum_codigo", referencedColumnName = "materia_pensum_codigo")})
    @ManyToOne(optional = false)
    private Microcurriculo microcurriculo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unidadId")
    private List<ContenidoUnidad> contenidoUnidadList;

    public Unidad() {
    }

    public Unidad(Integer id) {
        this.id = id;
    }

    public Unidad(Integer id, int num, String nombre, int horasPresencial, int horasIndependiente) {
        this.id = id;
        this.num = num;
        this.nombre = nombre;
        this.horasPresencial = horasPresencial;
        this.horasIndependiente = horasIndependiente;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getHorasPresencial() {
        return horasPresencial;
    }

    public void setHorasPresencial(int horasPresencial) {
        this.horasPresencial = horasPresencial;
    }

    public int getHorasIndependiente() {
        return horasIndependiente;
    }

    public void setHorasIndependiente(int horasIndependiente) {
        this.horasIndependiente = horasIndependiente;
    }

    public Microcurriculo getMicrocurriculo() {
        return microcurriculo;
    }

    public void setMicrocurriculo(Microcurriculo microcurriculo) {
        this.microcurriculo = microcurriculo;
    }

    @XmlTransient
    public List<ContenidoUnidad> getContenidoUnidadList() {
        return contenidoUnidadList;
    }

    public void setContenidoUnidadList(List<ContenidoUnidad> contenidoUnidadList) {
        this.contenidoUnidadList = contenidoUnidadList;
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
        if (!(object instanceof Unidad)) {
            return false;
        }
        Unidad other = (Unidad) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.Unidad[ id=" + id + " ]";
    }
    
}
