/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author BorisECornejo
 */
@Entity
@Table(name = "expediente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Expediente.findAll", query = "SELECT e FROM Expediente e"),
    @NamedQuery(name = "Expediente.findByNumExpediente", query = "SELECT e FROM Expediente e WHERE e.numExpediente = :numExpediente"),
    @NamedQuery(name = "Expediente.findByIdCliente", query = "SELECT e FROM Expediente e WHERE e.idCliente = :idCliente"),
    @NamedQuery(name = "Expediente.findByNumCredito", query = "SELECT e FROM Expediente e WHERE e.numCredito = :numCredito"),
    @NamedQuery(name = "Expediente.findByNumSolicitud", query = "SELECT e FROM Expediente e WHERE e.numSolicitud = :numSolicitud"),
    @NamedQuery(name = "Expediente.findByIdPunto", query = "SELECT e FROM Expediente e WHERE e.idPunto = :idPunto"),
    @NamedQuery(name = "Expediente.findByFechaCreacion", query = "SELECT e FROM Expediente e WHERE e.fechaCreacion = :fechaCreacion")})
public class Expediente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "num_expediente")
    private Integer numExpediente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_cliente")
    private int idCliente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "num_credito")
    private int numCredito;
    @Basic(optional = false)
    @NotNull
    @Column(name = "num_solicitud")
    private int numSolicitud;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_punto")
    private int idPunto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "numExpediente")
    private List<Presupuesto> presupuestoList;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario idUsuario;
    @JoinColumn(name = "id_mejora", referencedColumnName = "id_mejora")
    @ManyToOne(optional = false)
    private Mejora idMejora;
    @JoinColumn(name = "id_vivienda", referencedColumnName = "id_vivienda")
    @ManyToOne(optional = false)
    private ConstruVivienda idVivienda;
    @JoinColumn(name = "id_agencia", referencedColumnName = "id_agencia")
    @ManyToOne(optional = false)
    private Agencia idAgencia;

    public Expediente() {
    }

    public Expediente(Integer numExpediente) {
        this.numExpediente = numExpediente;
    }

    public Expediente(Integer numExpediente, int idCliente, int numCredito, int numSolicitud, int idPunto, Date fechaCreacion) {
        this.numExpediente = numExpediente;
        this.idCliente = idCliente;
        this.numCredito = numCredito;
        this.numSolicitud = numSolicitud;
        this.idPunto = idPunto;
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getNumExpediente() {
        return numExpediente;
    }

    public void setNumExpediente(Integer numExpediente) {
        this.numExpediente = numExpediente;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getNumCredito() {
        return numCredito;
    }

    public void setNumCredito(int numCredito) {
        this.numCredito = numCredito;
    }

    public int getNumSolicitud() {
        return numSolicitud;
    }

    public void setNumSolicitud(int numSolicitud) {
        this.numSolicitud = numSolicitud;
    }

    public int getIdPunto() {
        return idPunto;
    }

    public void setIdPunto(int idPunto) {
        this.idPunto = idPunto;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @XmlTransient
    public List<Presupuesto> getPresupuestoList() {
        return presupuestoList;
    }

    public void setPresupuestoList(List<Presupuesto> presupuestoList) {
        this.presupuestoList = presupuestoList;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Mejora getIdMejora() {
        return idMejora;
    }

    public void setIdMejora(Mejora idMejora) {
        this.idMejora = idMejora;
    }

    public ConstruVivienda getIdVivienda() {
        return idVivienda;
    }

    public void setIdVivienda(ConstruVivienda idVivienda) {
        this.idVivienda = idVivienda;
    }

    public Agencia getIdAgencia() {
        return idAgencia;
    }

    public void setIdAgencia(Agencia idAgencia) {
        this.idAgencia = idAgencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numExpediente != null ? numExpediente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Expediente)) {
            return false;
        }
        Expediente other = (Expediente) object;
        if ((this.numExpediente == null && other.numExpediente != null) || (this.numExpediente != null && !this.numExpediente.equals(other.numExpediente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Expediente[ numExpediente=" + numExpediente + " ]";
    }
    
}
