/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author BorisECornejo
 */
@Entity
@Table(name = "record_cred")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RecordCred.findAll", query = "SELECT r FROM RecordCred r"),
    @NamedQuery(name = "RecordCred.findBySecuencia", query = "SELECT r FROM RecordCred r WHERE r.secuencia = :secuencia"),
    @NamedQuery(name = "RecordCred.findByLugar", query = "SELECT r FROM RecordCred r WHERE r.lugar = :lugar"),
    @NamedQuery(name = "RecordCred.findByFecha", query = "SELECT r FROM RecordCred r WHERE r.fecha = :fecha"),
    @NamedQuery(name = "RecordCred.findByEstado", query = "SELECT r FROM RecordCred r WHERE r.estado = :estado"),
    @NamedQuery(name = "RecordCred.findByNumeroCuotas", query = "SELECT r FROM RecordCred r WHERE r.numeroCuotas = :numeroCuotas"),
    @NamedQuery(name = "RecordCred.findByDescrip", query = "SELECT r FROM RecordCred r WHERE r.descrip = :descrip"),
    @NamedQuery(name = "RecordCred.findByMonto", query = "SELECT r FROM RecordCred r WHERE r.monto = :monto"),
    @NamedQuery(name = "RecordCred.findByCuota", query = "SELECT r FROM RecordCred r WHERE r.cuota = :cuota"),
    @NamedQuery(name = "RecordCred.findByNumSolicitud", query = "SELECT r FROM RecordCred r WHERE r.numSolicitud = :numSolicitud"),
    @NamedQuery(name = "RecordCred.findByCodCliente", query = "SELECT r FROM RecordCred r WHERE r.codCliente = :codCliente")})
public class RecordCred implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "secuencia")
    private Integer secuencia;
    @Size(max = 200)
    @Column(name = "lugar")
    private String lugar;
    @Size(max = 10)
    @Column(name = "fecha")
    private String fecha;
    @Size(max = 50)
    @Column(name = "estado")
    private String estado;
    @Column(name = "numero_cuotas")
    private Integer numeroCuotas;
    @Size(max = 200)
    @Column(name = "descrip")
    private String descrip;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "monto")
    private BigDecimal monto;
    @Column(name = "cuota")
    private BigDecimal cuota;
    @Size(max = 20)
    @Column(name = "num_solicitud")
    private String numSolicitud;
    @Column(name = "cod_cliente")
    private Integer codCliente;

    public RecordCred() {
    }

    public RecordCred(Integer secuencia) {
        this.secuencia = secuencia;
    }

    public Integer getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(Integer secuencia) {
        this.secuencia = secuencia;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getNumeroCuotas() {
        return numeroCuotas;
    }

    public void setNumeroCuotas(Integer numeroCuotas) {
        this.numeroCuotas = numeroCuotas;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public BigDecimal getCuota() {
        return cuota;
    }

    public void setCuota(BigDecimal cuota) {
        this.cuota = cuota;
    }

    public String getNumSolicitud() {
        return numSolicitud;
    }

    public void setNumSolicitud(String numSolicitud) {
        this.numSolicitud = numSolicitud;
    }

    public Integer getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(Integer codCliente) {
        this.codCliente = codCliente;
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
        if (!(object instanceof RecordCred)) {
            return false;
        }
        RecordCred other = (RecordCred) object;
        if ((this.secuencia == null && other.secuencia != null) || (this.secuencia != null && !this.secuencia.equals(other.secuencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.RecordCred[ secuencia=" + secuencia + " ]";
    }
    
}
