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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Boris Cornejo
 */
@Entity
@Table(name = "supervisiones", catalog = "db_segepro", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Supervisiones.findAll", query = "SELECT s FROM Supervisiones s")
    , @NamedQuery(name = "Supervisiones.findByIdSupervision", query = "SELECT s FROM Supervisiones s WHERE s.idSupervision = :idSupervision")
    , @NamedQuery(name = "Supervisiones.findByTipoSupervision", query = "SELECT s FROM Supervisiones s WHERE s.tipoSupervision = :tipoSupervision")
    , @NamedQuery(name = "Supervisiones.findByFecha", query = "SELECT s FROM Supervisiones s WHERE s.fecha = :fecha")
    , @NamedQuery(name = "Supervisiones.findByIdAgencia", query = "SELECT s FROM Supervisiones s WHERE s.idAgencia = :idAgencia")
    , @NamedQuery(name = "Supervisiones.findBySupervisior", query = "SELECT s FROM Supervisiones s WHERE s.supervisior = :supervisior")
    , @NamedQuery(name = "Supervisiones.findByIdCliente", query = "SELECT s FROM Supervisiones s WHERE s.idCliente = :idCliente")
    , @NamedQuery(name = "Supervisiones.findByDireccion", query = "SELECT s FROM Supervisiones s WHERE s.direccion = :direccion")
    , @NamedQuery(name = "Supervisiones.findByNumSolicitud", query = "SELECT s FROM Supervisiones s WHERE s.numSolicitud = :numSolicitud")
    , @NamedQuery(name = "Supervisiones.findByObservaciones", query = "SELECT s FROM Supervisiones s WHERE s.observaciones = :observaciones")
    , @NamedQuery(name = "Supervisiones.findByLongitud", query = "SELECT s FROM Supervisiones s WHERE s.longitud = :longitud")
    , @NamedQuery(name = "Supervisiones.findByLatitud", query = "SELECT s FROM Supervisiones s WHERE s.latitud = :latitud")
    , @NamedQuery(name = "Supervisiones.findByMedidaTerreno", query = "SELECT s FROM Supervisiones s WHERE s.medidaTerreno = :medidaTerreno")
    , @NamedQuery(name = "Supervisiones.findByIdAvanceConstru", query = "SELECT s FROM Supervisiones s WHERE s.idAvanceConstru = :idAvanceConstru")
    , @NamedQuery(name = "Supervisiones.findByInfoProy", query = "SELECT s FROM Supervisiones s WHERE s.infoProy = :infoProy")})
public class Supervisiones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_supervision")
    private Integer idSupervision;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "tipo_supervision")
    private String tipoSupervision;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_agencia")
    private int idAgencia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "supervisior")
    private String supervisior;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_cliente")
    private int idCliente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "direccion")
    private String direccion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "num_solicitud")
    private int numSolicitud;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "observaciones")
    private String observaciones;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "longitud")
    private BigDecimal longitud;
    @Column(name = "latitud")
    private BigDecimal latitud;
    @Column(name = "medida_terreno")
    private BigDecimal medidaTerreno;
    @Column(name = "id_avance_constru")
    private Integer idAvanceConstru;
    @Size(max = 300)
    @Column(name = "info_proy")
    private String infoProy;

    public Supervisiones() {
    }

    public Supervisiones(Integer idSupervision) {
        this.idSupervision = idSupervision;
    }

    public Supervisiones(Integer idSupervision, String tipoSupervision, Date fecha, int idAgencia, String supervisior, int idCliente, String direccion, int numSolicitud, String observaciones) {
        this.idSupervision = idSupervision;
        this.tipoSupervision = tipoSupervision;
        this.fecha = fecha;
        this.idAgencia = idAgencia;
        this.supervisior = supervisior;
        this.idCliente = idCliente;
        this.direccion = direccion;
        this.numSolicitud = numSolicitud;
        this.observaciones = observaciones;
    }

    public Integer getIdSupervision() {
        return idSupervision;
    }

    public void setIdSupervision(Integer idSupervision) {
        this.idSupervision = idSupervision;
    }

    public String getTipoSupervision() {
        return tipoSupervision;
    }

    public void setTipoSupervision(String tipoSupervision) {
        this.tipoSupervision = tipoSupervision;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdAgencia() {
        return idAgencia;
    }

    public void setIdAgencia(int idAgencia) {
        this.idAgencia = idAgencia;
    }

    public String getSupervisior() {
        return supervisior;
    }

    public void setSupervisior(String supervisior) {
        this.supervisior = supervisior;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getNumSolicitud() {
        return numSolicitud;
    }

    public void setNumSolicitud(int numSolicitud) {
        this.numSolicitud = numSolicitud;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
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

    public BigDecimal getMedidaTerreno() {
        return medidaTerreno;
    }

    public void setMedidaTerreno(BigDecimal medidaTerreno) {
        this.medidaTerreno = medidaTerreno;
    }

    public Integer getIdAvanceConstru() {
        return idAvanceConstru;
    }

    public void setIdAvanceConstru(Integer idAvanceConstru) {
        this.idAvanceConstru = idAvanceConstru;
    }

    public String getInfoProy() {
        return infoProy;
    }

    public void setInfoProy(String infoProy) {
        this.infoProy = infoProy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSupervision != null ? idSupervision.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Supervisiones)) {
            return false;
        }
        Supervisiones other = (Supervisiones) object;
        if ((this.idSupervision == null && other.idSupervision != null) || (this.idSupervision != null && !this.idSupervision.equals(other.idSupervision))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Supervisiones[ idSupervision=" + idSupervision + " ]";
    }
    
}
