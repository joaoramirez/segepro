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
 * @author BorisECornejo
 */
@Entity
@Table(name = "cuota_credito")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CuotaCredito.findAll", query = "SELECT c FROM CuotaCredito c"),
    @NamedQuery(name = "CuotaCredito.findBySecuencia", query = "SELECT c FROM CuotaCredito c WHERE c.secuencia = :secuencia"),
    @NamedQuery(name = "CuotaCredito.findByNumCredito", query = "SELECT c FROM CuotaCredito c WHERE c.numCredito = :numCredito"),
    @NamedQuery(name = "CuotaCredito.findByFechaPago", query = "SELECT c FROM CuotaCredito c WHERE c.fechaPago = :fechaPago"),
    @NamedQuery(name = "CuotaCredito.findByFechaCuota", query = "SELECT c FROM CuotaCredito c WHERE c.fechaCuota = :fechaCuota"),
    @NamedQuery(name = "CuotaCredito.findByEstadoCuota", query = "SELECT c FROM CuotaCredito c WHERE c.estadoCuota = :estadoCuota"),
    @NamedQuery(name = "CuotaCredito.findByNumCuota", query = "SELECT c FROM CuotaCredito c WHERE c.numCuota = :numCuota"),
    @NamedQuery(name = "CuotaCredito.findByCuota", query = "SELECT c FROM CuotaCredito c WHERE c.cuota = :cuota"),
    @NamedQuery(name = "CuotaCredito.findByInteres", query = "SELECT c FROM CuotaCredito c WHERE c.interes = :interes"),
    @NamedQuery(name = "CuotaCredito.findByMora", query = "SELECT c FROM CuotaCredito c WHERE c.mora = :mora"),
    @NamedQuery(name = "CuotaCredito.findBySaldo", query = "SELECT c FROM CuotaCredito c WHERE c.saldo = :saldo"),
    @NamedQuery(name = "CuotaCredito.findByNumSolicitud", query = "SELECT c FROM CuotaCredito c WHERE c.numSolicitud = :numSolicitud")})
public class CuotaCredito implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "secuencia")
    private Integer secuencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "num_credito")
    private int numCredito;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "fecha_pago")
    private String fechaPago;
    @Column(name = "fecha_cuota")
    @Temporal(TemporalType.DATE)
    private Date fechaCuota;
    @Size(max = 100)
    @Column(name = "estado_cuota")
    private String estadoCuota;
    @Basic(optional = false)
    @NotNull
    @Column(name = "num_cuota")
    private int numCuota;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "cuota")
    private BigDecimal cuota;
    @Basic(optional = false)
    @NotNull
    @Column(name = "interes")
    private BigDecimal interes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mora")
    private BigDecimal mora;
    @Basic(optional = false)
    @NotNull
    @Column(name = "saldo")
    private BigDecimal saldo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "num_solicitud")
    private int numSolicitud;

    public CuotaCredito() {
    }

    public CuotaCredito(Integer secuencia) {
        this.secuencia = secuencia;
    }

    public CuotaCredito(Integer secuencia, int numCredito, String fechaPago, int numCuota, BigDecimal cuota, BigDecimal interes, BigDecimal mora, BigDecimal saldo, int numSolicitud) {
        this.secuencia = secuencia;
        this.numCredito = numCredito;
        this.fechaPago = fechaPago;
        this.numCuota = numCuota;
        this.cuota = cuota;
        this.interes = interes;
        this.mora = mora;
        this.saldo = saldo;
        this.numSolicitud = numSolicitud;
    }

    public Integer getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(Integer secuencia) {
        this.secuencia = secuencia;
    }

    public int getNumCredito() {
        return numCredito;
    }

    public void setNumCredito(int numCredito) {
        this.numCredito = numCredito;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Date getFechaCuota() {
        return fechaCuota;
    }

    public void setFechaCuota(Date fechaCuota) {
        this.fechaCuota = fechaCuota;
    }

    public String getEstadoCuota() {
        return estadoCuota;
    }

    public void setEstadoCuota(String estadoCuota) {
        this.estadoCuota = estadoCuota;
    }

    public int getNumCuota() {
        return numCuota;
    }

    public void setNumCuota(int numCuota) {
        this.numCuota = numCuota;
    }

    public BigDecimal getCuota() {
        return cuota;
    }

    public void setCuota(BigDecimal cuota) {
        this.cuota = cuota;
    }

    public BigDecimal getInteres() {
        return interes;
    }

    public void setInteres(BigDecimal interes) {
        this.interes = interes;
    }

    public BigDecimal getMora() {
        return mora;
    }

    public void setMora(BigDecimal mora) {
        this.mora = mora;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public int getNumSolicitud() {
        return numSolicitud;
    }

    public void setNumSolicitud(int numSolicitud) {
        this.numSolicitud = numSolicitud;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (secuencia != null ? secuencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CuotaCredito)) {
            return false;
        }
        CuotaCredito other = (CuotaCredito) object;
        if ((this.secuencia == null && other.secuencia != null) || (this.secuencia != null && !this.secuencia.equals(other.secuencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CuotaCredito[ secuencia=" + secuencia + " ]";
    }
    
}
