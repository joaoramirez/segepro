/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author BorisECornejo
 */
@Entity
@Table(name = "sav")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sav.findAll", query = "SELECT s FROM Sav s"),
    @NamedQuery(name = "Sav.findByIdSav", query = "SELECT s FROM Sav s WHERE s.idSav = :idSav"),
    @NamedQuery(name = "Sav.findByNombre", query = "SELECT s FROM Sav s WHERE s.nombre = :nombre"),
    @NamedQuery(name = "Sav.findByLugar", query = "SELECT s FROM Sav s WHERE s.lugar = :lugar"),
    @NamedQuery(name = "Sav.findByFecha", query = "SELECT s FROM Sav s WHERE s.fecha = :fecha"),
    @NamedQuery(name = "Sav.findByCantFavorecidos", query = "SELECT s FROM Sav s WHERE s.cantFavorecidos = :cantFavorecidos")})
public class Sav implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_sav")
    private Integer idSav;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "lugar")
    private String lugar;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cant_favorecidos")
    private int cantFavorecidos;

    public Sav() {
    }

    public Sav(Integer idSav) {
        this.idSav = idSav;
    }

    public Sav(Integer idSav, String nombre, String lugar, Date fecha, int cantFavorecidos) {
        this.idSav = idSav;
        this.nombre = nombre;
        this.lugar = lugar;
        this.fecha = fecha;
        this.cantFavorecidos = cantFavorecidos;
    }

    public Integer getIdSav() {
        return idSav;
    }

    public void setIdSav(Integer idSav) {
        this.idSav = idSav;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getCantFavorecidos() {
        return cantFavorecidos;
    }

    public void setCantFavorecidos(int cantFavorecidos) {
        this.cantFavorecidos = cantFavorecidos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSav != null ? idSav.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sav)) {
            return false;
        }
        Sav other = (Sav) object;
        if ((this.idSav == null && other.idSav != null) || (this.idSav != null && !this.idSav.equals(other.idSav))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Sav[ idSav=" + idSav + " ]";
    }
    
}
