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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author BorisECornejo
 */
@Entity
@Table(name = "descrip_bienes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DescripBienes.findAll", query = "SELECT d FROM DescripBienes d"),
    @NamedQuery(name = "DescripBienes.findByCodDescip", query = "SELECT d FROM DescripBienes d WHERE d.codDescip = :codDescip"),
    @NamedQuery(name = "DescripBienes.findByCodCliente", query = "SELECT d FROM DescripBienes d WHERE d.codCliente = :codCliente"),
    @NamedQuery(name = "DescripBienes.findByDescripcion", query = "SELECT d FROM DescripBienes d WHERE d.descripcion = :descripcion"),
    @NamedQuery(name = "DescripBienes.findByLugar", query = "SELECT d FROM DescripBienes d WHERE d.lugar = :lugar"),
    @NamedQuery(name = "DescripBienes.findByPrecioCompra", query = "SELECT d FROM DescripBienes d WHERE d.precioCompra = :precioCompra"),
    @NamedQuery(name = "DescripBienes.findByCuota", query = "SELECT d FROM DescripBienes d WHERE d.cuota = :cuota"),
    @NamedQuery(name = "DescripBienes.findByNumCuotas", query = "SELECT d FROM DescripBienes d WHERE d.numCuotas = :numCuotas"),
    @NamedQuery(name = "DescripBienes.findByEstado", query = "SELECT d FROM DescripBienes d WHERE d.estado = :estado"),
    @NamedQuery(name = "DescripBienes.findByFecha", query = "SELECT d FROM DescripBienes d WHERE d.fecha = :fecha"),
    @NamedQuery(name = "DescripBienes.findByNomCliente", query = "SELECT d FROM DescripBienes d WHERE d.nomCliente = :nomCliente"),
    @NamedQuery(name = "DescripBienes.findByNomProduc", query = "SELECT d FROM DescripBienes d WHERE d.nomProduc = :nomProduc"),
    @NamedQuery(name = "DescripBienes.findByNumSolicitud", query = "SELECT d FROM DescripBienes d WHERE d.numSolicitud = :numSolicitud")})
public class DescripBienes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_descip")
    private Integer codDescip;
    @Column(name = "cod_cliente")
    private Integer codCliente;
    @Size(max = 200)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 200)
    @Column(name = "lugar")
    private String lugar;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio_compra")
    private BigDecimal precioCompra;
    @Column(name = "cuota")
    private BigDecimal cuota;
    @Column(name = "num_cuotas")
    private Integer numCuotas;
    @Size(max = 50)
    @Column(name = "estado")
    private String estado;
    @Size(max = 10)
    @Column(name = "fecha")
    private String fecha;
    @Size(max = 200)
    @Column(name = "nom_cliente")
    private String nomCliente;
    @Size(max = 150)
    @Column(name = "nom_produc")
    private String nomProduc;
    @Column(name = "num_solicitud")
    private Integer numSolicitud;

    public DescripBienes() {
    }

    public DescripBienes(Integer codDescip) {
        this.codDescip = codDescip;
    }

    public Integer getCodDescip() {
        return codDescip;
    }

    public void setCodDescip(Integer codDescip) {
        this.codDescip = codDescip;
    }

    public Integer getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(Integer codCliente) {
        this.codCliente = codCliente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public BigDecimal getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(BigDecimal precioCompra) {
        this.precioCompra = precioCompra;
    }

    public BigDecimal getCuota() {
        return cuota;
    }

    public void setCuota(BigDecimal cuota) {
        this.cuota = cuota;
    }

    public Integer getNumCuotas() {
        return numCuotas;
    }

    public void setNumCuotas(Integer numCuotas) {
        this.numCuotas = numCuotas;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNomCliente() {
        return nomCliente;
    }

    public void setNomCliente(String nomCliente) {
        this.nomCliente = nomCliente;
    }

    public String getNomProduc() {
        return nomProduc;
    }

    public void setNomProduc(String nomProduc) {
        this.nomProduc = nomProduc;
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
        hash += (codDescip != null ? codDescip.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DescripBienes)) {
            return false;
        }
        DescripBienes other = (DescripBienes) object;
        if ((this.codDescip == null && other.codDescip != null) || (this.codDescip != null && !this.codDescip.equals(other.codDescip))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.DescripBienes[ codDescip=" + codDescip + " ]";
    }
    
}
