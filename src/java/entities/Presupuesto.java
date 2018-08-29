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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "presupuesto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Presupuesto.findAll", query = "SELECT p FROM Presupuesto p"),
    @NamedQuery(name = "Presupuesto.findByIdPresupuesto", query = "SELECT p FROM Presupuesto p WHERE p.idPresupuesto = :idPresupuesto"),
    @NamedQuery(name = "Presupuesto.findByNumCredito", query = "SELECT p FROM Presupuesto p WHERE p.numCredito = :numCredito"),
    @NamedQuery(name = "Presupuesto.findByFecha", query = "SELECT p FROM Presupuesto p WHERE p.fecha = :fecha")})
public class Presupuesto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_presupuesto")
    private Integer idPresupuesto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "num_credito")
    private int numCredito;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "num_expediente", referencedColumnName = "num_expediente")
    @ManyToOne(optional = false)
    private Expediente numExpediente;

    public Presupuesto() {
    }

    public Presupuesto(Integer idPresupuesto) {
        this.idPresupuesto = idPresupuesto;
    }

    public Presupuesto(Integer idPresupuesto, int numCredito, Date fecha) {
        this.idPresupuesto = idPresupuesto;
        this.numCredito = numCredito;
        this.fecha = fecha;
    }

    public Integer getIdPresupuesto() {
        return idPresupuesto;
    }

    public void setIdPresupuesto(Integer idPresupuesto) {
        this.idPresupuesto = idPresupuesto;
    }

    public int getNumCredito() {
        return numCredito;
    }

    public void setNumCredito(int numCredito) {
        this.numCredito = numCredito;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Expediente getNumExpediente() {
        return numExpediente;
    }

    public void setNumExpediente(Expediente numExpediente) {
        this.numExpediente = numExpediente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPresupuesto != null ? idPresupuesto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Presupuesto)) {
            return false;
        }
        Presupuesto other = (Presupuesto) object;
        if ((this.idPresupuesto == null && other.idPresupuesto != null) || (this.idPresupuesto != null && !this.idPresupuesto.equals(other.idPresupuesto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Presupuesto[ idPresupuesto=" + idPresupuesto + " ]";
    }
    
}
