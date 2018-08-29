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
public class OrdenCompraDetPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_orden")
    private int idOrden;
    @Basic(optional = false)
    @NotNull
    @Column(name = "secuencia")
    private int secuencia;

    public OrdenCompraDetPK() {
    }

    public OrdenCompraDetPK(int idOrden, int secuencia) {
        this.idOrden = idOrden;
        this.secuencia = secuencia;
    }

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public int getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(int secuencia) {
        this.secuencia = secuencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idOrden;
        hash += (int) secuencia;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrdenCompraDetPK)) {
            return false;
        }
        OrdenCompraDetPK other = (OrdenCompraDetPK) object;
        if (this.idOrden != other.idOrden) {
            return false;
        }
        if (this.secuencia != other.secuencia) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.OrdenCompraDetPK[ idOrden=" + idOrden + ", secuencia=" + secuencia + " ]";
    }
    
}
