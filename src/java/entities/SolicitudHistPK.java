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
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Boris Cornejo
 */
@Embeddable
public class SolicitudHistPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_cliente")
    private int idCliente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_cambio_estado")
    @Temporal(TemporalType.DATE)
    private Date fechaCambioEstado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "num_solicitud")
    private String numSolicitud;

    public SolicitudHistPK() {
    }

    public SolicitudHistPK(int idCliente, Date fechaCambioEstado, String numSolicitud) {
        this.idCliente = idCliente;
        this.fechaCambioEstado = fechaCambioEstado;
        this.numSolicitud = numSolicitud;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Date getFechaCambioEstado() {
        return fechaCambioEstado;
    }

    public void setFechaCambioEstado(Date fechaCambioEstado) {
        this.fechaCambioEstado = fechaCambioEstado;
    }

    public String getNumSolicitud() {
        return numSolicitud;
    }

    public void setNumSolicitud(String numSolicitud) {
        this.numSolicitud = numSolicitud;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idCliente;
        hash += (fechaCambioEstado != null ? fechaCambioEstado.hashCode() : 0);
        hash += (numSolicitud != null ? numSolicitud.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SolicitudHistPK)) {
            return false;
        }
        SolicitudHistPK other = (SolicitudHistPK) object;
        if (this.idCliente != other.idCliente) {
            return false;
        }
        if ((this.fechaCambioEstado == null && other.fechaCambioEstado != null) || (this.fechaCambioEstado != null && !this.fechaCambioEstado.equals(other.fechaCambioEstado))) {
            return false;
        }
        if ((this.numSolicitud == null && other.numSolicitud != null) || (this.numSolicitud != null && !this.numSolicitud.equals(other.numSolicitud))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.SolicitudHistPK[ idCliente=" + idCliente + ", fechaCambioEstado=" + fechaCambioEstado + ", numSolicitud=" + numSolicitud + " ]";
    }
    
}
