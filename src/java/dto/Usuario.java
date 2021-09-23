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
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.findByDocenteCodigo", query = "SELECT u FROM Usuario u WHERE u.docenteCodigo = :docenteCodigo")
    , @NamedQuery(name = "Usuario.findByClave", query = "SELECT u FROM Usuario u WHERE u.clave = :clave")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "docente_codigo")
    private Integer docenteCodigo;
    @Basic(optional = false)
    @Column(name = "clave")
    private String clave;
    @JoinColumn(name = "rol_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Rol rolId;
    @JoinColumn(name = "docente_codigo", referencedColumnName = "codigo", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Docente docente;

    public Usuario() {
    }

    public Usuario(Integer docenteCodigo) {
        this.docenteCodigo = docenteCodigo;
    }

    public Usuario(Integer docenteCodigo, String clave) {
        this.docenteCodigo = docenteCodigo;
        this.clave = clave;
    }

    public Integer getDocenteCodigo() {
        return docenteCodigo;
    }

    public void setDocenteCodigo(Integer docenteCodigo) {
        this.docenteCodigo = docenteCodigo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Rol getRolId() {
        return rolId;
    }

    public void setRolId(Rol rolId) {
        this.rolId = rolId;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (docenteCodigo != null ? docenteCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.docenteCodigo == null && other.docenteCodigo != null) || (this.docenteCodigo != null && !this.docenteCodigo.equals(other.docenteCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.Usuario[ docenteCodigo=" + docenteCodigo + " ]";
    }
    
}
