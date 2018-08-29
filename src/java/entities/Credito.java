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
@Table(name = "credito")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Credito.findAll", query = "SELECT c FROM Credito c"),
    @NamedQuery(name = "Credito.findByNumCredito", query = "SELECT c FROM Credito c WHERE c.numCredito = :numCredito"),
    @NamedQuery(name = "Credito.findByIdCliente", query = "SELECT c FROM Credito c WHERE c.idCliente = :idCliente"),
    @NamedQuery(name = "Credito.findByDestinoCredito", query = "SELECT c FROM Credito c WHERE c.destinoCredito = :destinoCredito"),
    @NamedQuery(name = "Credito.findByMejoraVivienda", query = "SELECT c FROM Credito c WHERE c.mejoraVivienda = :mejoraVivienda"),
    @NamedQuery(name = "Credito.findByDetalleObra", query = "SELECT c FROM Credito c WHERE c.detalleObra = :detalleObra"),
    @NamedQuery(name = "Credito.findByPlazo", query = "SELECT c FROM Credito c WHERE c.plazo = :plazo"),
    @NamedQuery(name = "Credito.findByMontoSolicitado", query = "SELECT c FROM Credito c WHERE c.montoSolicitado = :montoSolicitado"),
    @NamedQuery(name = "Credito.findByFechaOtorgamiento", query = "SELECT c FROM Credito c WHERE c.fechaOtorgamiento = :fechaOtorgamiento"),
    @NamedQuery(name = "Credito.findByMontoCuota", query = "SELECT c FROM Credito c WHERE c.montoCuota = :montoCuota"),
    @NamedQuery(name = "Credito.findByFormaPago", query = "SELECT c FROM Credito c WHERE c.formaPago = :formaPago")})
public class Credito implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "num_credito")
    private Integer numCredito;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_cliente")
    private int idCliente;
    @Size(max = 200)
    @Column(name = "destino_credito")
    private String destinoCredito;
    @Size(max = 200)
    @Column(name = "mejora_vivienda")
    private String mejoraVivienda;
    @Size(max = 200)
    @Column(name = "detalle_obra")
    private String detalleObra;
    @Basic(optional = false)
    @NotNull
    @Column(name = "plazo")
    private int plazo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "monto_solicitado")
    private BigDecimal montoSolicitado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_otorgamiento")
    @Temporal(TemporalType.DATE)
    private Date fechaOtorgamiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "monto_cuota")
    private BigDecimal montoCuota;
    @Size(max = 100)
    @Column(name = "forma_pago")
    private String formaPago;

    public Credito() {
    }

    public Credito(Integer numCredito) {
        this.numCredito = numCredito;
    }

    public Credito(Integer numCredito, int idCliente, int plazo, BigDecimal montoSolicitado, Date fechaOtorgamiento, BigDecimal montoCuota) {
        this.numCredito = numCredito;
        this.idCliente = idCliente;
        this.plazo = plazo;
        this.montoSolicitado = montoSolicitado;
        this.fechaOtorgamiento = fechaOtorgamiento;
        this.montoCuota = montoCuota;
    }

    public Integer getNumCredito() {
        return numCredito;
    }

    public void setNumCredito(Integer numCredito) {
        this.numCredito = numCredito;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getDestinoCredito() {
        return destinoCredito;
    }

    public void setDestinoCredito(String destinoCredito) {
        this.destinoCredito = destinoCredito;
    }

    public String getMejoraVivienda() {
        return mejoraVivienda;
    }

    public void setMejoraVivienda(String mejoraVivienda) {
        this.mejoraVivienda = mejoraVivienda;
    }

    public String getDetalleObra() {
        return detalleObra;
    }

    public void setDetalleObra(String detalleObra) {
        this.detalleObra = detalleObra;
    }

    public int getPlazo() {
        return plazo;
    }

    public void setPlazo(int plazo) {
        this.plazo = plazo;
    }

    public BigDecimal getMontoSolicitado() {
        return montoSolicitado;
    }

    public void setMontoSolicitado(BigDecimal montoSolicitado) {
        this.montoSolicitado = montoSolicitado;
    }

    public Date getFechaOtorgamiento() {
        return fechaOtorgamiento;
    }

    public void setFechaOtorgamiento(Date fechaOtorgamiento) {
        this.fechaOtorgamiento = fechaOtorgamiento;
    }

    public BigDecimal getMontoCuota() {
        return montoCuota;
    }

    public void setMontoCuota(BigDecimal montoCuota) {
        this.montoCuota = montoCuota;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numCredito != null ? numCredito.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Credito)) {
            return false;
        }
        Credito other = (Credito) object;
        if ((this.numCredito == null && other.numCredito != null) || (this.numCredito != null && !this.numCredito.equals(other.numCredito))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Credito[ numCredito=" + numCredito + " ]";
    }
    
}
