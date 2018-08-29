/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Boris Cornejo
 */
@Embeddable
public class SolicitudPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_cliente")
    private int idCliente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "num_solicitud")
    private int numSolicitud;

    public SolicitudPK() {
    }

    public SolicitudPK(int idCliente, int numSolicitud) {
        this.idCliente = idCliente;
        this.numSolicitud = numSolicitud;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
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
        hash += (int) idCliente;
        hash += (int) numSolicitud;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SolicitudPK)) {
            return false;
        }
        SolicitudPK other = (SolicitudPK) object;
        if (this.idCliente != other.idCliente) {
            return false;
        }
        if (this.numSolicitud != other.numSolicitud) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.SolicitudPK[ idCliente=" + idCliente + ", numSolicitud=" + numSolicitud + " ]";
    }
    
}
