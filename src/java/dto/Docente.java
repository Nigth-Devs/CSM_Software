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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Sachikia
 */
@Entity
@Table(name = "docente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Docente.findAll", query = "SELECT d FROM Docente d")
    , @NamedQuery(name = "Docente.findByCodigo", query = "SELECT d FROM Docente d WHERE d.codigo = :codigo")
    , @NamedQuery(name = "Docente.findByNombre", query = "SELECT d FROM Docente d WHERE d.nombre = :nombre")
    , @NamedQuery(name = "Docente.findByApellido", query = "SELECT d FROM Docente d WHERE d.apellido = :apellido")
    , @NamedQuery(name = "Docente.findByEstado", query = "SELECT d FROM Docente d WHERE d.estado = :estado")})
public class Docente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "apellido")
    private String apellido;
    @Basic(optional = false)
    @Column(name = "estado")
    private short estado;
    @Lob
    @Column(name = "imagen")
    private byte[] imagen;
    @JoinColumn(name = "programa_codigo", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Programa programaCodigo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "docenteCodigo")
    private List<MateriaPeriodoGrupo> materiaPeriodoGrupoList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "docente")
    private Usuario usuario;

    public Docente() {
    }

    public Docente(Integer codigo) {
        this.codigo = codigo;
    }

    public Docente(Integer codigo, String nombre, String apellido, short estado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.estado = estado;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public short getEstado() {
        return estado;
    }

    public void setEstado(short estado) {
        this.estado = estado;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public Programa getProgramaCodigo() {
        return programaCodigo;
    }

    public void setProgramaCodigo(Programa programaCodigo) {
        this.programaCodigo = programaCodigo;
    }

    @XmlTransient
    public List<MateriaPeriodoGrupo> getMateriaPeriodoGrupoList() {
        return materiaPeriodoGrupoList;
    }

    public void setMateriaPeriodoGrupoList(List<MateriaPeriodoGrupo> materiaPeriodoGrupoList) {
        this.materiaPeriodoGrupoList = materiaPeriodoGrupoList;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Docente)) {
            return false;
        }
        Docente other = (Docente) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.Docente[ codigo=" + codigo + " ]";
    }
    
}
