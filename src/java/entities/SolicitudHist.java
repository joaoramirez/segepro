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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
 * @author Boris Cornejo
 */
@Entity
@Table(name = "solicitud_hist", catalog = "db_segepro", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SolicitudHist.findAll", query = "SELECT s FROM SolicitudHist s")
    , @NamedQuery(name = "SolicitudHist.findByIdCliente", query = "SELECT s FROM SolicitudHist s WHERE s.solicitudHistPK.idCliente = :idCliente")
    , @NamedQuery(name = "SolicitudHist.findByEstado", query = "SELECT s FROM SolicitudHist s WHERE s.estado = :estado")
    , @NamedQuery(name = "SolicitudHist.findByFechaCambioEstado", query = "SELECT s FROM SolicitudHist s WHERE s.solicitudHistPK.fechaCambioEstado = :fechaCambioEstado")
    , @NamedQuery(name = "SolicitudHist.findByMunicipio", query = "SELECT s FROM SolicitudHist s WHERE s.municipio = :municipio")
    , @NamedQuery(name = "SolicitudHist.findByComunidad", query = "SELECT s FROM SolicitudHist s WHERE s.comunidad = :comunidad")
    , @NamedQuery(name = "SolicitudHist.findByOficinaRegional", query = "SELECT s FROM SolicitudHist s WHERE s.oficinaRegional = :oficinaRegional")
    , @NamedQuery(name = "SolicitudHist.findByDestinoCredito", query = "SELECT s FROM SolicitudHist s WHERE s.destinoCredito = :destinoCredito")
    , @NamedQuery(name = "SolicitudHist.findByDuenioPropiedad", query = "SELECT s FROM SolicitudHist s WHERE s.duenioPropiedad = :duenioPropiedad")
    , @NamedQuery(name = "SolicitudHist.findByParentesco", query = "SELECT s FROM SolicitudHist s WHERE s.parentesco = :parentesco")
    , @NamedQuery(name = "SolicitudHist.findByDetalleObra", query = "SELECT s FROM SolicitudHist s WHERE s.detalleObra = :detalleObra")
    , @NamedQuery(name = "SolicitudHist.findByModeloVivienda", query = "SELECT s FROM SolicitudHist s WHERE s.modeloVivienda = :modeloVivienda")
    , @NamedQuery(name = "SolicitudHist.findByPlazo", query = "SELECT s FROM SolicitudHist s WHERE s.plazo = :plazo")
    , @NamedQuery(name = "SolicitudHist.findByFormaPago", query = "SELECT s FROM SolicitudHist s WHERE s.formaPago = :formaPago")
    , @NamedQuery(name = "SolicitudHist.findByFechaPago", query = "SELECT s FROM SolicitudHist s WHERE s.fechaPago = :fechaPago")
    , @NamedQuery(name = "SolicitudHist.findByComentarioIniciativa", query = "SELECT s FROM SolicitudHist s WHERE s.comentarioIniciativa = :comentarioIniciativa")
    , @NamedQuery(name = "SolicitudHist.findByComentarioEvaluacion", query = "SELECT s FROM SolicitudHist s WHERE s.comentarioEvaluacion = :comentarioEvaluacion")
    , @NamedQuery(name = "SolicitudHist.findByComentarioGarantia", query = "SELECT s FROM SolicitudHist s WHERE s.comentarioGarantia = :comentarioGarantia")
    , @NamedQuery(name = "SolicitudHist.findByNomCliente", query = "SELECT s FROM SolicitudHist s WHERE s.nomCliente = :nomCliente")
    , @NamedQuery(name = "SolicitudHist.findByCodAgencia", query = "SELECT s FROM SolicitudHist s WHERE s.codAgencia = :codAgencia")
    , @NamedQuery(name = "SolicitudHist.findByPresupuesto", query = "SELECT s FROM SolicitudHist s WHERE s.presupuesto = :presupuesto")
    , @NamedQuery(name = "SolicitudHist.findByMontoSolicitado", query = "SELECT s FROM SolicitudHist s WHERE s.montoSolicitado = :montoSolicitado")
    , @NamedQuery(name = "SolicitudHist.findByCuota", query = "SELECT s FROM SolicitudHist s WHERE s.cuota = :cuota")
    , @NamedQuery(name = "SolicitudHist.findByFechaEmision", query = "SELECT s FROM SolicitudHist s WHERE s.fechaEmision = :fechaEmision")
    , @NamedQuery(name = "SolicitudHist.findByNumSolicitud", query = "SELECT s FROM SolicitudHist s WHERE s.solicitudHistPK.numSolicitud = :numSolicitud")
    , @NamedQuery(name = "SolicitudHist.findBySecuencia", query = "SELECT s FROM SolicitudHist s WHERE s.secuencia = :secuencia")})
public class SolicitudHist implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SolicitudHistPK solicitudHistPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "estado")
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "municipio")
    private String municipio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "comunidad")
    private String comunidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "oficina_regional")
    private String oficinaRegional;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "destino_credito")
    private String destinoCredito;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "duenio_propiedad")
    private String duenioPropiedad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "parentesco")
    private String parentesco;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "detalle_obra")
    private String detalleObra;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "modelo_vivienda")
    private String modeloVivienda;
    @Basic(optional = false)
    @NotNull
    @Column(name = "plazo")
    private int plazo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "forma_pago")
    private String formaPago;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_pago")
    @Temporal(TemporalType.DATE)
    private Date fechaPago;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "comentario_iniciativa")
    private String comentarioIniciativa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "comentario_evaluacion")
    private String comentarioEvaluacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "comentario_garantia")
    private String comentarioGarantia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "nom_cliente")
    private String nomCliente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_agencia")
    private int codAgencia;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "presupuesto")
    private BigDecimal presupuesto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "monto_solicitado")
    private BigDecimal montoSolicitado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cuota")
    private BigDecimal cuota;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_emision")
    @Temporal(TemporalType.DATE)
    private Date fechaEmision;
    @Basic(optional = false)
    @NotNull
    @Column(name = "secuencia")
    private int secuencia;

    public SolicitudHist() {
    }

    public SolicitudHist(SolicitudHistPK solicitudHistPK) {
        this.solicitudHistPK = solicitudHistPK;
    }

    public SolicitudHist(SolicitudHistPK solicitudHistPK, String estado, String municipio, String comunidad, String oficinaRegional, String destinoCredito, String duenioPropiedad, String parentesco, String detalleObra, String modeloVivienda, int plazo, String formaPago, Date fechaPago, String comentarioIniciativa, String comentarioEvaluacion, String comentarioGarantia, String nomCliente, int codAgencia, BigDecimal presupuesto, BigDecimal montoSolicitado, BigDecimal cuota, Date fechaEmision, int secuencia) {
        this.solicitudHistPK = solicitudHistPK;
        this.estado = estado;
        this.municipio = municipio;
        this.comunidad = comunidad;
        this.oficinaRegional = oficinaRegional;
        this.destinoCredito = destinoCredito;
        this.duenioPropiedad = duenioPropiedad;
        this.parentesco = parentesco;
        this.detalleObra = detalleObra;
        this.modeloVivienda = modeloVivienda;
        this.plazo = plazo;
        this.formaPago = formaPago;
        this.fechaPago = fechaPago;
        this.comentarioIniciativa = comentarioIniciativa;
        this.comentarioEvaluacion = comentarioEvaluacion;
        this.comentarioGarantia = comentarioGarantia;
        this.nomCliente = nomCliente;
        this.codAgencia = codAgencia;
        this.presupuesto = presupuesto;
        this.montoSolicitado = montoSolicitado;
        this.cuota = cuota;
        this.fechaEmision = fechaEmision;
        this.secuencia = secuencia;
    }

    public SolicitudHist(int idCliente, Date fechaCambioEstado, String numSolicitud) {
        this.solicitudHistPK = new SolicitudHistPK(idCliente, fechaCambioEstado, numSolicitud);
    }

    public SolicitudHistPK getSolicitudHistPK() {
        return solicitudHistPK;
    }

    public void setSolicitudHistPK(SolicitudHistPK solicitudHistPK) {
        this.solicitudHistPK = solicitudHistPK;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getComunidad() {
        return comunidad;
    }

    public void setComunidad(String comunidad) {
        this.comunidad = comunidad;
    }

    public String getOficinaRegional() {
        return oficinaRegional;
    }

    public void setOficinaRegional(String oficinaRegional) {
        this.oficinaRegional = oficinaRegional;
    }

    public String getDestinoCredito() {
        return destinoCredito;
    }

    public void setDestinoCredito(String destinoCredito) {
        this.destinoCredito = destinoCredito;
    }

    public String getDuenioPropiedad() {
        return duenioPropiedad;
    }

    public void setDuenioPropiedad(String duenioPropiedad) {
        this.duenioPropiedad = duenioPropiedad;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public String getDetalleObra() {
        return detalleObra;
    }

    public void setDetalleObra(String detalleObra) {
        this.detalleObra = detalleObra;
    }

    public String getModeloVivienda() {
        return modeloVivienda;
    }

    public void setModeloVivienda(String modeloVivienda) {
        this.modeloVivienda = modeloVivienda;
    }

    public int getPlazo() {
        return plazo;
    }

    public void setPlazo(int plazo) {
        this.plazo = plazo;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getComentarioIniciativa() {
        return comentarioIniciativa;
    }

    public void setComentarioIniciativa(String comentarioIniciativa) {
        this.comentarioIniciativa = comentarioIniciativa;
    }

    public String getComentarioEvaluacion() {
        return comentarioEvaluacion;
    }

    public void setComentarioEvaluacion(String comentarioEvaluacion) {
        this.comentarioEvaluacion = comentarioEvaluacion;
    }

    public String getComentarioGarantia() {
        return comentarioGarantia;
    }

    public void setComentarioGarantia(String comentarioGarantia) {
        this.comentarioGarantia = comentarioGarantia;
    }

    public String getNomCliente() {
        return nomCliente;
    }

    public void setNomCliente(String nomCliente) {
        this.nomCliente = nomCliente;
    }

    public int getCodAgencia() {
        return codAgencia;
    }

    public void setCodAgencia(int codAgencia) {
        this.codAgencia = codAgencia;
    }

    public BigDecimal getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(BigDecimal presupuesto) {
        this.presupuesto = presupuesto;
    }

    public BigDecimal getMontoSolicitado() {
        return montoSolicitado;
    }

    public void setMontoSolicitado(BigDecimal montoSolicitado) {
        this.montoSolicitado = montoSolicitado;
    }

    public BigDecimal getCuota() {
        return cuota;
    }

    public void setCuota(BigDecimal cuota) {
        this.cuota = cuota;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
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
        hash += (solicitudHistPK != null ? solicitudHistPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SolicitudHist)) {
            return false;
        }
        SolicitudHist other = (SolicitudHist) object;
        if ((this.solicitudHistPK == null && other.solicitudHistPK != null) || (this.solicitudHistPK != null && !this.solicitudHistPK.equals(other.solicitudHistPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.SolicitudHist[ solicitudHistPK=" + solicitudHistPK + " ]";
    }
    
}
