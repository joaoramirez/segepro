/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author BorisECornejo
 */
@Entity
@Table(name = "constru_vivienda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConstruVivienda.findAll", query = "SELECT c FROM ConstruVivienda c"),
    @NamedQuery(name = "ConstruVivienda.findByIdVivienda", query = "SELECT c FROM ConstruVivienda c WHERE c.idVivienda = :idVivienda"),
    @NamedQuery(name = "ConstruVivienda.findByIdAvanceConstru", query = "SELECT c FROM ConstruVivienda c WHERE c.idAvanceConstru = :idAvanceConstru"),
    @NamedQuery(name = "ConstruVivienda.findByModeloVivienda", query = "SELECT c FROM ConstruVivienda c WHERE c.modeloVivienda = :modeloVivienda"),
    @NamedQuery(name = "ConstruVivienda.findByCosto", query = "SELECT c FROM ConstruVivienda c WHERE c.costo = :costo"),
    @NamedQuery(name = "ConstruVivienda.findByDescripcion", query = "SELECT c FROM ConstruVivienda c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "ConstruVivienda.findByAreaConstruida", query = "SELECT c FROM ConstruVivienda c WHERE c.areaConstruida = :areaConstruida"),
    @NamedQuery(name = "ConstruVivienda.findByFechaInicio", query = "SELECT c FROM ConstruVivienda c WHERE c.fechaInicio = :fechaInicio")})
public class ConstruVivienda implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_vivienda")
    private Integer idVivienda;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_avance_constru")
    private int idAvanceConstru;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "modelo_vivienda")
    private String modeloVivienda;
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
    @Column(name = "area_construida")
    private BigDecimal areaConstruida;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idVivienda")
    private List<Expediente> expedienteList;

    public ConstruVivienda() {
    }

    public ConstruVivienda(Integer idVivienda) {
        this.idVivienda = idVivienda;
    }

    public ConstruVivienda(Integer idVivienda, int idAvanceConstru, String modeloVivienda, BigDecimal costo, String descripcion, BigDecimal areaConstruida, Date fechaInicio) {
        this.idVivienda = idVivienda;
        this.idAvanceConstru = idAvanceConstru;
        this.modeloVivienda = modeloVivienda;
        this.costo = costo;
        this.descripcion = descripcion;
        this.areaConstruida = areaConstruida;
        this.fechaInicio = fechaInicio;
    }

    public Integer getIdVivienda() {
        return idVivienda;
    }

    public void setIdVivienda(Integer idVivienda) {
        this.idVivienda = idVivienda;
    }

    public int getIdAvanceConstru() {
        return idAvanceConstru;
    }

    public void setIdAvanceConstru(int idAvanceConstru) {
        this.idAvanceConstru = idAvanceConstru;
    }

    public String getModeloVivienda() {
        return modeloVivienda;
    }

    public void setModeloVivienda(String modeloVivienda) {
        this.modeloVivienda = modeloVivienda;
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

    public BigDecimal getAreaConstruida() {
        return areaConstruida;
    }

    public void setAreaConstruida(BigDecimal areaConstruida) {
        this.areaConstruida = areaConstruida;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
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
        hash += (idVivienda != null ? idVivienda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConstruVivienda)) {
            return false;
        }
        ConstruVivienda other = (ConstruVivienda) object;
        if ((this.idVivienda == null && other.idVivienda != null) || (this.idVivienda != null && !this.idVivienda.equals(other.idVivienda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ConstruVivienda[ idVivienda=" + idVivienda + " ]";
    }
    
}
