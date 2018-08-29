/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author BorisECornejo
 */
@Entity
@Table(name = "avance_constru")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AvanceConstru.findAll", query = "SELECT a FROM AvanceConstru a"),
    @NamedQuery(name = "AvanceConstru.findByIdAvanceConstru", query = "SELECT a FROM AvanceConstru a WHERE a.idAvanceConstru = :idAvanceConstru"),
    @NamedQuery(name = "AvanceConstru.findByNombre", query = "SELECT a FROM AvanceConstru a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "AvanceConstru.findByDescripcion", query = "SELECT a FROM AvanceConstru a WHERE a.descripcion = :descripcion")})
public class AvanceConstru implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_avance_constru")
    private Integer idAvanceConstru;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 800)
    @Column(name = "descripcion")
    private String descripcion;

    public AvanceConstru() {
    }

    public AvanceConstru(Integer idAvanceConstru) {
        this.idAvanceConstru = idAvanceConstru;
    }

    public AvanceConstru(Integer idAvanceConstru, String nombre, String descripcion) {
        this.idAvanceConstru = idAvanceConstru;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Integer getIdAvanceConstru() {
        return idAvanceConstru;
    }

    public void setIdAvanceConstru(Integer idAvanceConstru) {
        this.idAvanceConstru = idAvanceConstru;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAvanceConstru != null ? idAvanceConstru.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AvanceConstru)) {
            return false;
        }
        AvanceConstru other = (AvanceConstru) object;
        if ((this.idAvanceConstru == null && other.idAvanceConstru != null) || (this.idAvanceConstru != null && !this.idAvanceConstru.equals(other.idAvanceConstru))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.AvanceConstru[ idAvanceConstru=" + idAvanceConstru + " ]";
    }
    
}
