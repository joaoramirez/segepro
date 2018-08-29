/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "grupo_familiar")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GrupoFamiliar.findAll", query = "SELECT g FROM GrupoFamiliar g"),
    @NamedQuery(name = "GrupoFamiliar.findByCodGrupo", query = "SELECT g FROM GrupoFamiliar g WHERE g.codGrupo = :codGrupo"),
    @NamedQuery(name = "GrupoFamiliar.findByNombrePariente", query = "SELECT g FROM GrupoFamiliar g WHERE g.nombrePariente = :nombrePariente"),
    @NamedQuery(name = "GrupoFamiliar.findByFechaPariente", query = "SELECT g FROM GrupoFamiliar g WHERE g.fechaPariente = :fechaPariente"),
    @NamedQuery(name = "GrupoFamiliar.findBySalarioPariente", query = "SELECT g FROM GrupoFamiliar g WHERE g.salarioPariente = :salarioPariente"),
    @NamedQuery(name = "GrupoFamiliar.findByLugarTrabajo", query = "SELECT g FROM GrupoFamiliar g WHERE g.lugarTrabajo = :lugarTrabajo"),
    @NamedQuery(name = "GrupoFamiliar.findByParentesco", query = "SELECT g FROM GrupoFamiliar g WHERE g.parentesco = :parentesco"),
    @NamedQuery(name = "GrupoFamiliar.findByIdCliente", query = "SELECT g FROM GrupoFamiliar g WHERE g.idCliente = :idCliente")})
public class GrupoFamiliar implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_grupo")
    private Integer codGrupo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "nombre_pariente")
    private String nombrePariente;
    @Column(name = "fecha_pariente")
    @Temporal(TemporalType.DATE)
    private Date fechaPariente;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "salario_pariente")
    private BigDecimal salarioPariente;
    @Size(max = 200)
    @Column(name = "lugar_trabajo")
    private String lugarTrabajo;
    @Size(max = 200)
    @Column(name = "parentesco")
    private String parentesco;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_cliente")
    private int idCliente;

    public GrupoFamiliar() {
    }

    public GrupoFamiliar(Integer codGrupo) {
        this.codGrupo = codGrupo;
    }

    public GrupoFamiliar(Integer codGrupo, String nombrePariente, int idCliente) {
        this.codGrupo = codGrupo;
        this.nombrePariente = nombrePariente;
        this.idCliente = idCliente;
    }

    public Integer getCodGrupo() {
        return codGrupo;
    }

    public void setCodGrupo(Integer codGrupo) {
        this.codGrupo = codGrupo;
    }

    public String getNombrePariente() {
        return nombrePariente;
    }

    public void setNombrePariente(String nombrePariente) {
        this.nombrePariente = nombrePariente;
    }

    public Date getFechaPariente() {
        return fechaPariente;
    }

    public void setFechaPariente(Date fechaPariente) {
        this.fechaPariente = fechaPariente;
    }

    public BigDecimal getSalarioPariente() {
        return salarioPariente;
    }

    public void setSalarioPariente(BigDecimal salarioPariente) {
        this.salarioPariente = salarioPariente;
    }

    public String getLugarTrabajo() {
        return lugarTrabajo;
    }

    public void setLugarTrabajo(String lugarTrabajo) {
        this.lugarTrabajo = lugarTrabajo;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codGrupo != null ? codGrupo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GrupoFamiliar)) {
            return false;
        }
        GrupoFamiliar other = (GrupoFamiliar) object;
        if ((this.codGrupo == null && other.codGrupo != null) || (this.codGrupo != null && !this.codGrupo.equals(other.codGrupo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.GrupoFamiliar[ codGrupo=" + codGrupo + " ]";
    }
    
}
