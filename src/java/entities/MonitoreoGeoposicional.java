/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author BorisECornejo
 */
@Entity
@Table(name = "monitoreo_geoposicional")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MonitoreoGeoposicional.findAll", query = "SELECT m FROM MonitoreoGeoposicional m"),
    @NamedQuery(name = "MonitoreoGeoposicional.findByIdPunto", query = "SELECT m FROM MonitoreoGeoposicional m WHERE m.idPunto = :idPunto"),
    @NamedQuery(name = "MonitoreoGeoposicional.findByIdSav", query = "SELECT m FROM MonitoreoGeoposicional m WHERE m.idSav = :idSav"),
    @NamedQuery(name = "MonitoreoGeoposicional.findByIdBrigada", query = "SELECT m FROM MonitoreoGeoposicional m WHERE m.idBrigada = :idBrigada"),
    @NamedQuery(name = "MonitoreoGeoposicional.findByNumExpediente", query = "SELECT m FROM MonitoreoGeoposicional m WHERE m.numExpediente = :numExpediente"),
    @NamedQuery(name = "MonitoreoGeoposicional.findByFecha", query = "SELECT m FROM MonitoreoGeoposicional m WHERE m.fecha = :fecha"),
    @NamedQuery(name = "MonitoreoGeoposicional.findByLongitud", query = "SELECT m FROM MonitoreoGeoposicional m WHERE m.longitud = :longitud"),
    @NamedQuery(name = "MonitoreoGeoposicional.findByLatitud", query = "SELECT m FROM MonitoreoGeoposicional m WHERE m.latitud = :latitud")})
public class MonitoreoGeoposicional implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_punto")
    private Integer idPunto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_sav")
    private int idSav;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_brigada")
    private int idBrigada;
    @Basic(optional = false)
    @NotNull
    @Column(name = "num_expediente")
    private int numExpediente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "longitud")
    private BigDecimal longitud;
    @Basic(optional = false)
    @NotNull
    @Column(name = "latitud")
    private BigDecimal latitud;

    public MonitoreoGeoposicional() {
    }

    public MonitoreoGeoposicional(Integer idPunto) {
        this.idPunto = idPunto;
    }

    public MonitoreoGeoposicional(Integer idPunto, int idSav, int idBrigada, int numExpediente, Date fecha, BigDecimal longitud, BigDecimal latitud) {
        this.idPunto = idPunto;
        this.idSav = idSav;
        this.idBrigada = idBrigada;
        this.numExpediente = numExpediente;
        this.fecha = fecha;
        this.longitud = longitud;
        this.latitud = latitud;
    }

    public Integer getIdPunto() {
        return idPunto;
    }

    public void setIdPunto(Integer idPunto) {
        this.idPunto = idPunto;
    }

    public int getIdSav() {
        return idSav;
    }

    public void setIdSav(int idSav) {
        this.idSav = idSav;
    }

    public int getIdBrigada() {
        return idBrigada;
    }

    public void setIdBrigada(int idBrigada) {
        this.idBrigada = idBrigada;
    }

    public int getNumExpediente() {
        return numExpediente;
    }

    public void setNumExpediente(int numExpediente) {
        this.numExpediente = numExpediente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getLongitud() {
        return longitud;
    }

    public void setLongitud(BigDecimal longitud) {
        this.longitud = longitud;
    }

    public BigDecimal getLatitud() {
        return latitud;
    }

    public void setLatitud(BigDecimal latitud) {
        this.latitud = latitud;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPunto != null ? idPunto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MonitoreoGeoposicional)) {
            return false;
        }
        MonitoreoGeoposicional other = (MonitoreoGeoposicional) object;
        if ((this.idPunto == null && other.idPunto != null) || (this.idPunto != null && !this.idPunto.equals(other.idPunto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MonitoreoGeoposicional[ idPunto=" + idPunto + " ]";
    }
    
}
