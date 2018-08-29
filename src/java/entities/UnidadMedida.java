/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
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
@Table(name = "unidad_medida")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UnidadMedida.findAll", query = "SELECT u FROM UnidadMedida u"),
    @NamedQuery(name = "UnidadMedida.findByCodUnidad", query = "SELECT u FROM UnidadMedida u WHERE u.codUnidad = :codUnidad"),
    @NamedQuery(name = "UnidadMedida.findByNomUnidad", query = "SELECT u FROM UnidadMedida u WHERE u.nomUnidad = :nomUnidad"),
    @NamedQuery(name = "UnidadMedida.findByAbreviatura", query = "SELECT u FROM UnidadMedida u WHERE u.abreviatura = :abreviatura")})
public class UnidadMedida implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_unidad")
    private Integer codUnidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "nom_unidad")
    private String nomUnidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "abreviatura")
    private String abreviatura;

    public UnidadMedida() {
    }

    public UnidadMedida(Integer codUnidad) {
        this.codUnidad = codUnidad;
    }

    public UnidadMedida(Integer codUnidad, String nomUnidad, String abreviatura) {
        this.codUnidad = codUnidad;
        this.nomUnidad = nomUnidad;
        this.abreviatura = abreviatura;
    }

    public Integer getCodUnidad() {
        return codUnidad;
    }

    public void setCodUnidad(Integer codUnidad) {
        this.codUnidad = codUnidad;
    }

    public String getNomUnidad() {
        return nomUnidad;
    }

    public void setNomUnidad(String nomUnidad) {
        this.nomUnidad = nomUnidad;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codUnidad != null ? codUnidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UnidadMedida)) {
            return false;
        }
        UnidadMedida other = (UnidadMedida) object;
        if ((this.codUnidad == null && other.codUnidad != null) || (this.codUnidad != null && !this.codUnidad.equals(other.codUnidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.UnidadMedida[ codUnidad=" + codUnidad + " ]";
    }
    
}
