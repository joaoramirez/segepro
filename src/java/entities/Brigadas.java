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
@Table(name = "brigadas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Brigadas.findAll", query = "SELECT b FROM Brigadas b"),
    @NamedQuery(name = "Brigadas.findByIdBrigada", query = "SELECT b FROM Brigadas b WHERE b.idBrigada = :idBrigada"),
    @NamedQuery(name = "Brigadas.findByIdAgencia", query = "SELECT b FROM Brigadas b WHERE b.idAgencia = :idAgencia"),
    @NamedQuery(name = "Brigadas.findByNomre", query = "SELECT b FROM Brigadas b WHERE b.nomre = :nomre"),
    @NamedQuery(name = "Brigadas.findByCantIntegrantes", query = "SELECT b FROM Brigadas b WHERE b.cantIntegrantes = :cantIntegrantes"),
    @NamedQuery(name = "Brigadas.findByFechaInic", query = "SELECT b FROM Brigadas b WHERE b.fechaInic = :fechaInic"),
    @NamedQuery(name = "Brigadas.findByFechaFin", query = "SELECT b FROM Brigadas b WHERE b.fechaFin = :fechaFin"),
    @NamedQuery(name = "Brigadas.findByTipoBrigada", query = "SELECT b FROM Brigadas b WHERE b.tipoBrigada = :tipoBrigada")})
public class Brigadas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_brigada")
    private Integer idBrigada;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_agencia")
    private int idAgencia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "nomre")
    private String nomre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cant_integrantes")
    private int cantIntegrantes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inic")
    @Temporal(TemporalType.DATE)
    private Date fechaInic;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "tipo_brigada")
    private String tipoBrigada;

    public Brigadas() {
    }

    public Brigadas(Integer idBrigada) {
        this.idBrigada = idBrigada;
    }

    public Brigadas(Integer idBrigada, int idAgencia, String nomre, int cantIntegrantes, Date fechaInic, Date fechaFin, String tipoBrigada) {
        this.idBrigada = idBrigada;
        this.idAgencia = idAgencia;
        this.nomre = nomre;
        this.cantIntegrantes = cantIntegrantes;
        this.fechaInic = fechaInic;
        this.fechaFin = fechaFin;
        this.tipoBrigada = tipoBrigada;
    }

    public Integer getIdBrigada() {
        return idBrigada;
    }

    public void setIdBrigada(Integer idBrigada) {
        this.idBrigada = idBrigada;
    }

    public int getIdAgencia() {
        return idAgencia;
    }

    public void setIdAgencia(int idAgencia) {
        this.idAgencia = idAgencia;
    }

    public String getNomre() {
        return nomre;
    }

    public void setNomre(String nomre) {
        this.nomre = nomre;
    }

    public int getCantIntegrantes() {
        return cantIntegrantes;
    }

    public void setCantIntegrantes(int cantIntegrantes) {
        this.cantIntegrantes = cantIntegrantes;
    }

    public Date getFechaInic() {
        return fechaInic;
    }

    public void setFechaInic(Date fechaInic) {
        this.fechaInic = fechaInic;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getTipoBrigada() {
        return tipoBrigada;
    }

    public void setTipoBrigada(String tipoBrigada) {
        this.tipoBrigada = tipoBrigada;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBrigada != null ? idBrigada.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Brigadas)) {
            return false;
        }
        Brigadas other = (Brigadas) object;
        if ((this.idBrigada == null && other.idBrigada != null) || (this.idBrigada != null && !this.idBrigada.equals(other.idBrigada))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Brigadas[ idBrigada=" + idBrigada + " ]";
    }
    
}
