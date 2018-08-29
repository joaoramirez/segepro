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
@Table(name = "profesiones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Profesiones.findAll", query = "SELECT p FROM Profesiones p"),
    @NamedQuery(name = "Profesiones.findByIdProfesion", query = "SELECT p FROM Profesiones p WHERE p.idProfesion = :idProfesion"),
    @NamedQuery(name = "Profesiones.findByNombreProfesion", query = "SELECT p FROM Profesiones p WHERE p.nombreProfesion = :nombreProfesion")})
public class Profesiones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_profesion")
    private Integer idProfesion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "nombre_profesion")
    private String nombreProfesion;

    public Profesiones() {
    }

    public Profesiones(Integer idProfesion) {
        this.idProfesion = idProfesion;
    }

    public Profesiones(Integer idProfesion, String nombreProfesion) {
        this.idProfesion = idProfesion;
        this.nombreProfesion = nombreProfesion;
    }

    public Integer getIdProfesion() {
        return idProfesion;
    }

    public void setIdProfesion(Integer idProfesion) {
        this.idProfesion = idProfesion;
    }

    public String getNombreProfesion() {
        return nombreProfesion;
    }

    public void setNombreProfesion(String nombreProfesion) {
        this.nombreProfesion = nombreProfesion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProfesion != null ? idProfesion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Profesiones)) {
            return false;
        }
        Profesiones other = (Profesiones) object;
        if ((this.idProfesion == null && other.idProfesion != null) || (this.idProfesion != null && !this.idProfesion.equals(other.idProfesion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Profesiones[ idProfesion=" + idProfesion + " ]";
    }
    
}
