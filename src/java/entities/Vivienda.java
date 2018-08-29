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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author BorisECornejo
 */
@Entity
@Table(name = "vivienda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vivienda.findAll", query = "SELECT v FROM Vivienda v"),
    @NamedQuery(name = "Vivienda.findByIdModelo", query = "SELECT v FROM Vivienda v WHERE v.idModelo = :idModelo"),
    @NamedQuery(name = "Vivienda.findByNombreModelo", query = "SELECT v FROM Vivienda v WHERE v.nombreModelo = :nombreModelo"),
    @NamedQuery(name = "Vivienda.findByIncluye", query = "SELECT v FROM Vivienda v WHERE v.incluye = :incluye"),
    @NamedQuery(name = "Vivienda.findByAreaConstruida", query = "SELECT v FROM Vivienda v WHERE v.areaConstruida = :areaConstruida"),
    @NamedQuery(name = "Vivienda.findByDescripcion", query = "SELECT v FROM Vivienda v WHERE v.descripcion = :descripcion"),
    @NamedQuery(name = "Vivienda.findByCosto", query = "SELECT v FROM Vivienda v WHERE v.costo = :costo")})
public class Vivienda implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_modelo")
    private Integer idModelo;
    @Size(max = 200)
    @Column(name = "nombre_modelo")
    private String nombreModelo;
    @Size(max = 200)
    @Column(name = "incluye")
    private String incluye;
    @Size(max = 200)
    @Column(name = "area_construida")
    private String areaConstruida;
    @Size(max = 300)
    @Column(name = "descripcion")
    private String descripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "costo")
    private BigDecimal costo;

    public Vivienda() {
    }

    public Vivienda(Integer idModelo) {
        this.idModelo = idModelo;
    }

    public Integer getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(Integer idModelo) {
        this.idModelo = idModelo;
    }

    public String getNombreModelo() {
        return nombreModelo;
    }

    public void setNombreModelo(String nombreModelo) {
        this.nombreModelo = nombreModelo;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idModelo != null ? idModelo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vivienda)) {
            return false;
        }
        Vivienda other = (Vivienda) object;
        if ((this.idModelo == null && other.idModelo != null) || (this.idModelo != null && !this.idModelo.equals(other.idModelo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Vivienda[ idModelo=" + idModelo + " ]";
    }
    
}
