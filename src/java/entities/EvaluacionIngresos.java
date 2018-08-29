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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "evaluacion_ingresos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EvaluacionIngresos.findAll", query = "SELECT e FROM EvaluacionIngresos e"),
    @NamedQuery(name = "EvaluacionIngresos.findByCodEvaluacion", query = "SELECT e FROM EvaluacionIngresos e WHERE e.codEvaluacion = :codEvaluacion"),
    @NamedQuery(name = "EvaluacionIngresos.findByCodCliente", query = "SELECT e FROM EvaluacionIngresos e WHERE e.codCliente = :codCliente"),
    @NamedQuery(name = "EvaluacionIngresos.findBySecuencia", query = "SELECT e FROM EvaluacionIngresos e WHERE e.secuencia = :secuencia"),
    @NamedQuery(name = "EvaluacionIngresos.findByAlimentacion", query = "SELECT e FROM EvaluacionIngresos e WHERE e.alimentacion = :alimentacion"),
    @NamedQuery(name = "EvaluacionIngresos.findByEducacion", query = "SELECT e FROM EvaluacionIngresos e WHERE e.educacion = :educacion"),
    @NamedQuery(name = "EvaluacionIngresos.findByTransporte", query = "SELECT e FROM EvaluacionIngresos e WHERE e.transporte = :transporte"),
    @NamedQuery(name = "EvaluacionIngresos.findBySalud", query = "SELECT e FROM EvaluacionIngresos e WHERE e.salud = :salud"),
    @NamedQuery(name = "EvaluacionIngresos.findByServicios", query = "SELECT e FROM EvaluacionIngresos e WHERE e.servicios = :servicios"),
    @NamedQuery(name = "EvaluacionIngresos.findByAlquiler", query = "SELECT e FROM EvaluacionIngresos e WHERE e.alquiler = :alquiler"),
    @NamedQuery(name = "EvaluacionIngresos.findByRecreacion", query = "SELECT e FROM EvaluacionIngresos e WHERE e.recreacion = :recreacion"),
    @NamedQuery(name = "EvaluacionIngresos.findByCreditos", query = "SELECT e FROM EvaluacionIngresos e WHERE e.creditos = :creditos"),
    @NamedQuery(name = "EvaluacionIngresos.findByDescuentosLey", query = "SELECT e FROM EvaluacionIngresos e WHERE e.descuentosLey = :descuentosLey"),
    @NamedQuery(name = "EvaluacionIngresos.findByTotal1", query = "SELECT e FROM EvaluacionIngresos e WHERE e.total1 = :total1"),
    @NamedQuery(name = "EvaluacionIngresos.findByFamiliar", query = "SELECT e FROM EvaluacionIngresos e WHERE e.familiar = :familiar"),
    @NamedQuery(name = "EvaluacionIngresos.findByOtrosIngresos", query = "SELECT e FROM EvaluacionIngresos e WHERE e.otrosIngresos = :otrosIngresos"),
    @NamedQuery(name = "EvaluacionIngresos.findByTotal2", query = "SELECT e FROM EvaluacionIngresos e WHERE e.total2 = :total2"),
    @NamedQuery(name = "EvaluacionIngresos.findByDisponible", query = "SELECT e FROM EvaluacionIngresos e WHERE e.disponible = :disponible"),
    @NamedQuery(name = "EvaluacionIngresos.findByCuotaAsignada", query = "SELECT e FROM EvaluacionIngresos e WHERE e.cuotaAsignada = :cuotaAsignada"),
    @NamedQuery(name = "EvaluacionIngresos.findBySuperavit", query = "SELECT e FROM EvaluacionIngresos e WHERE e.superavit = :superavit"),
    @NamedQuery(name = "EvaluacionIngresos.findByDeficit", query = "SELECT e FROM EvaluacionIngresos e WHERE e.deficit = :deficit"),
    @NamedQuery(name = "EvaluacionIngresos.findByNomSolicitante", query = "SELECT e FROM EvaluacionIngresos e WHERE e.nomSolicitante = :nomSolicitante"),
    @NamedQuery(name = "EvaluacionIngresos.findByNumSolicitud", query = "SELECT e FROM EvaluacionIngresos e WHERE e.numSolicitud = :numSolicitud")})
public class EvaluacionIngresos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_evaluacion")
    private Integer codEvaluacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_cliente")
    private int codCliente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "secuencia")
    private int secuencia;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "alimentacion")
    private BigDecimal alimentacion;
    @Column(name = "educacion")
    private BigDecimal educacion;
    @Column(name = "transporte")
    private BigDecimal transporte;
    @Column(name = "salud")
    private BigDecimal salud;
    @Column(name = "servicios")
    private BigDecimal servicios;
    @Column(name = "alquiler")
    private BigDecimal alquiler;
    @Column(name = "recreacion")
    private BigDecimal recreacion;
    @Column(name = "creditos")
    private BigDecimal creditos;
    @Column(name = "descuentos_ley")
    private BigDecimal descuentosLey;
    @Column(name = "total1")
    private BigDecimal total1;
    @Column(name = "familiar")
    private BigDecimal familiar;
    @Column(name = "otros_ingresos")
    private BigDecimal otrosIngresos;
    @Column(name = "total2")
    private BigDecimal total2;
    @Column(name = "disponible")
    private BigDecimal disponible;
    @Column(name = "cuota_asignada")
    private BigDecimal cuotaAsignada;
    @Column(name = "superavit")
    private BigDecimal superavit;
    @Column(name = "deficit")
    private BigDecimal deficit;
    @Size(max = 200)
    @Column(name = "nom_solicitante")
    private String nomSolicitante;
    @Column(name = "num_solicitud")
    private Integer numSolicitud;

    public EvaluacionIngresos() {
    }

    public EvaluacionIngresos(Integer codEvaluacion) {
        this.codEvaluacion = codEvaluacion;
    }

    public EvaluacionIngresos(Integer codEvaluacion, int codCliente, int secuencia) {
        this.codEvaluacion = codEvaluacion;
        this.codCliente = codCliente;
        this.secuencia = secuencia;
    }

    public Integer getCodEvaluacion() {
        return codEvaluacion;
    }

    public void setCodEvaluacion(Integer codEvaluacion) {
        this.codEvaluacion = codEvaluacion;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public int getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(int secuencia) {
        this.secuencia = secuencia;
    }

    public BigDecimal getAlimentacion() {
        return alimentacion;
    }

    public void setAlimentacion(BigDecimal alimentacion) {
        this.alimentacion = alimentacion;
    }

    public BigDecimal getEducacion() {
        return educacion;
    }

    public void setEducacion(BigDecimal educacion) {
        this.educacion = educacion;
    }

    public BigDecimal getTransporte() {
        return transporte;
    }

    public void setTransporte(BigDecimal transporte) {
        this.transporte = transporte;
    }

    public BigDecimal getSalud() {
        return salud;
    }

    public void setSalud(BigDecimal salud) {
        this.salud = salud;
    }

    public BigDecimal getServicios() {
        return servicios;
    }

    public void setServicios(BigDecimal servicios) {
        this.servicios = servicios;
    }

    public BigDecimal getAlquiler() {
        return alquiler;
    }

    public void setAlquiler(BigDecimal alquiler) {
        this.alquiler = alquiler;
    }

    public BigDecimal getRecreacion() {
        return recreacion;
    }

    public void setRecreacion(BigDecimal recreacion) {
        this.recreacion = recreacion;
    }

    public BigDecimal getCreditos() {
        return creditos;
    }

    public void setCreditos(BigDecimal creditos) {
        this.creditos = creditos;
    }

    public BigDecimal getDescuentosLey() {
        return descuentosLey;
    }

    public void setDescuentosLey(BigDecimal descuentosLey) {
        this.descuentosLey = descuentosLey;
    }

    public BigDecimal getTotal1() {
        return total1;
    }

    public void setTotal1(BigDecimal total1) {
        this.total1 = total1;
    }

    public BigDecimal getFamiliar() {
        return familiar;
    }

    public void setFamiliar(BigDecimal familiar) {
        this.familiar = familiar;
    }

    public BigDecimal getOtrosIngresos() {
        return otrosIngresos;
    }

    public void setOtrosIngresos(BigDecimal otrosIngresos) {
        this.otrosIngresos = otrosIngresos;
    }

    public BigDecimal getTotal2() {
        return total2;
    }

    public void setTotal2(BigDecimal total2) {
        this.total2 = total2;
    }

    public BigDecimal getDisponible() {
        return disponible;
    }

    public void setDisponible(BigDecimal disponible) {
        this.disponible = disponible;
    }

    public BigDecimal getCuotaAsignada() {
        return cuotaAsignada;
    }

    public void setCuotaAsignada(BigDecimal cuotaAsignada) {
        this.cuotaAsignada = cuotaAsignada;
    }

    public BigDecimal getSuperavit() {
        return superavit;
    }

    public void setSuperavit(BigDecimal superavit) {
        this.superavit = superavit;
    }

    public BigDecimal getDeficit() {
        return deficit;
    }

    public void setDeficit(BigDecimal deficit) {
        this.deficit = deficit;
    }

    public String getNomSolicitante() {
        return nomSolicitante;
    }

    public void setNomSolicitante(String nomSolicitante) {
        this.nomSolicitante = nomSolicitante;
    }

    public Integer getNumSolicitud() {
        return numSolicitud;
    }

    public void setNumSolicitud(Integer numSolicitud) {
        this.numSolicitud = numSolicitud;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codEvaluacion != null ? codEvaluacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EvaluacionIngresos)) {
            return false;
        }
        EvaluacionIngresos other = (EvaluacionIngresos) object;
        if ((this.codEvaluacion == null && other.codEvaluacion != null) || (this.codEvaluacion != null && !this.codEvaluacion.equals(other.codEvaluacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.EvaluacionIngresos[ codEvaluacion=" + codEvaluacion + " ]";
    }
    
}
