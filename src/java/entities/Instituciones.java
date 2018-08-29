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
@Table(name = "instituciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Instituciones.findAll", query = "SELECT i FROM Instituciones i"),
    @NamedQuery(name = "Instituciones.findByCodInstitucion", query = "SELECT i FROM Instituciones i WHERE i.codInstitucion = :codInstitucion"),
    @NamedQuery(name = "Instituciones.findByNomInstitucion", query = "SELECT i FROM Instituciones i WHERE i.nomInstitucion = :nomInstitucion"),
    @NamedQuery(name = "Instituciones.findByDireccionInstitucion", query = "SELECT i FROM Instituciones i WHERE i.direccionInstitucion = :direccionInstitucion"),
    @NamedQuery(name = "Instituciones.findByTelefonoInstitucion", query = "SELECT i FROM Instituciones i WHERE i.telefonoInstitucion = :telefonoInstitucion")})
public class Instituciones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_institucion")
    private Integer codInstitucion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "nom_institucion")
    private String nomInstitucion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "direccion_institucion")
    private String direccionInstitucion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "telefono_institucion")
    private String telefonoInstitucion;

    public Instituciones() {
    }

    public Instituciones(Integer codInstitucion) {
        this.codInstitucion = codInstitucion;
    }

    public Instituciones(Integer codInstitucion, String nomInstitucion, String direccionInstitucion, String telefonoInstitucion) {
        this.codInstitucion = codInstitucion;
        this.nomInstitucion = nomInstitucion;
        this.direccionInstitucion = direccionInstitucion;
        this.telefonoInstitucion = telefonoInstitucion;
    }

    public Integer getCodInstitucion() {
        return codInstitucion;
    }

    public void setCodInstitucion(Integer codInstitucion) {
        this.codInstitucion = codInstitucion;
    }

    public String getNomInstitucion() {
        return nomInstitucion;
    }

    public void setNomInstitucion(String nomInstitucion) {
        this.nomInstitucion = nomInstitucion;
    }

    public String getDireccionInstitucion() {
        return direccionInstitucion;
    }

    public void setDireccionInstitucion(String direccionInstitucion) {
        this.direccionInstitucion = direccionInstitucion;
    }

    public String getTelefonoInstitucion() {
        return telefonoInstitucion;
    }

    public void setTelefonoInstitucion(String telefonoInstitucion) {
        this.telefonoInstitucion = telefonoInstitucion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codInstitucion != null ? codInstitucion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Instituciones)) {
            return false;
        }
        Instituciones other = (Instituciones) object;
        if ((this.codInstitucion == null && other.codInstitucion != null) || (this.codInstitucion != null && !this.codInstitucion.equals(other.codInstitucion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Instituciones[ codInstitucion=" + codInstitucion + " ]";
    }
    
}
