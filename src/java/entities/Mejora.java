/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author BorisECornejo
 */
@Entity
@Table(name = "mejora")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mejora.findAll", query = "SELECT m FROM Mejora m"),
    @NamedQuery(name = "Mejora.findByIdMejora", query = "SELECT m FROM Mejora m WHERE m.idMejora = :idMejora"),
    @NamedQuery(name = "Mejora.findByCosto", query = "SELECT m FROM Mejora m WHERE m.costo = :costo"),
    @NamedQuery(name = "Mejora.findByDescripcion", query = "SELECT m FROM Mejora m WHERE m.descripcion = :descripcion"),
    @NamedQuery(name = "Mejora.findByNombreMejora", query = "SELECT m FROM Mejora m WHERE m.nombreMejora = :nombreMejora"),
    @NamedQuery(name = "Mejora.findByIncluye", query = "SELECT m FROM Mejora m WHERE m.incluye = :incluye"),
    @NamedQuery(name = "Mejora.findByAreaConstruida", query = "SELECT m FROM Mejora m WHERE m.areaConstruida = :areaConstruida")})
public class Mejora implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_mejora")
    private Integer idMejora;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "costo")
    private BigDecimal costo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 800)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "nombre_mejora")
    private String nombreMejora;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "incluye")
    private String incluye;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "area_construida")
    private String areaConstruida;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMejora")
    private List<Expediente> expedienteList;

    public Mejora() {
    }

    public Mejora(Integer idMejora) {
        this.idMejora = idMejora;
    }

    public Mejora(Integer idMejora, BigDecimal costo, String descripcion, String nombreMejora, String incluye, String areaConstruida) {
        this.idMejora = idMejora;
        this.costo = costo;
        this.descripcion = descripcion;
        this.nombreMejora = nombreMejora;
        this.incluye = incluye;
        this.areaConstruida = areaConstruida;
    }

    public Integer getIdMejora() {
        return idMejora;
    }

    public void setIdMejora(Integer idMejora) {
        this.idMejora = idMejora;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombreMejora() {
        return nombreMejora;
    }

    public void setNombreMejora(String nombreMejora) {
        this.nombreMejora = nombreMejora;
    }

    public String getIncluye() {
        return incluye;
    }

    public void setIncluye(String incluye) {
        this.incluye = incluye;
    }

    public String getAreaConstruida() {
        return areaConstruida;
    }

    public void setAreaConstruida(String areaConstruida) {
        this.areaConstruida = areaConstruida;
    }

    @XmlTransient
    public List<Expediente> getExpedienteList() {
        return expedienteList;
    }

    public void setExpedienteList(List<Expediente> expedienteList) {
        this.expedienteList = expedienteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMejora != null ? idMejora.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mejora)) {
            return false;
        }
        Mejora other = (Mejora) object;
        if ((this.idMejora == null && other.idMejora != null) || (this.idMejora != null && !this.idMejora.equals(other.idMejora))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Mejora[ idMejora=" + idMejora + " ]";
    }
    
}
