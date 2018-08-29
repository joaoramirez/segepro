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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Boris Cornejo
 */
@Entity
@Table(name = "orden_compra_det", catalog = "db_segepro", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrdenCompraDet.findAll", query = "SELECT o FROM OrdenCompraDet o")
    , @NamedQuery(name = "OrdenCompraDet.findByIdOrden", query = "SELECT o FROM OrdenCompraDet o WHERE o.ordenCompraDetPK.idOrden = :idOrden")
    , @NamedQuery(name = "OrdenCompraDet.findByIdProveedor", query = "SELECT o FROM OrdenCompraDet o WHERE o.idProveedor = :idProveedor")
    , @NamedQuery(name = "OrdenCompraDet.findByIdMaterial", query = "SELECT o FROM OrdenCompraDet o WHERE o.idMaterial = :idMaterial")
    , @NamedQuery(name = "OrdenCompraDet.findByIdUnidad", query = "SELECT o FROM OrdenCompraDet o WHERE o.idUnidad = :idUnidad")
    , @NamedQuery(name = "OrdenCompraDet.findByDescripcion", query = "SELECT o FROM OrdenCompraDet o WHERE o.descripcion = :descripcion")
    , @NamedQuery(name = "OrdenCompraDet.findByPrecioUnit", query = "SELECT o FROM OrdenCompraDet o WHERE o.precioUnit = :precioUnit")
    , @NamedQuery(name = "OrdenCompraDet.findBySubtotal", query = "SELECT o FROM OrdenCompraDet o WHERE o.subtotal = :subtotal")
    , @NamedQuery(name = "OrdenCompraDet.findByCantidad", query = "SELECT o FROM OrdenCompraDet o WHERE o.cantidad = :cantidad")
    , @NamedQuery(name = "OrdenCompraDet.findByTotal", query = "SELECT o FROM OrdenCompraDet o WHERE o.total = :total")
    , @NamedQuery(name = "OrdenCompraDet.findBySecuencia", query = "SELECT o FROM OrdenCompraDet o WHERE o.ordenCompraDetPK.secuencia = :secuencia")})
public class OrdenCompraDet implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OrdenCompraDetPK ordenCompraDetPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_proveedor")
    private int idProveedor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_material")
    private int idMaterial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_unidad")
    private int idUnidad;
    @Size(max = 150)
    @Column(name = "descripcion")
    private String descripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio_unit")
    private BigDecimal precioUnit;
    @Basic(optional = false)
    @NotNull
    @Column(name = "subtotal")
    private BigDecimal subtotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private int cantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total")
    private BigDecimal total;
    @JoinColumn(name = "id_orden", referencedColumnName = "id_orden", insertable = false, updatable = false)
    @ManyToOne
    private OrdenCompra ordenCompra;

    public OrdenCompraDet() {
    }

    public OrdenCompraDet(OrdenCompraDetPK ordenCompraDetPK) {
        this.ordenCompraDetPK = ordenCompraDetPK;
    }

    public OrdenCompraDet(OrdenCompraDetPK ordenCompraDetPK, int idProveedor, int idMaterial, int idUnidad, BigDecimal precioUnit, BigDecimal subtotal, int cantidad, BigDecimal total) {
        this.ordenCompraDetPK = ordenCompraDetPK;
        this.idProveedor = idProveedor;
        this.idMaterial = idMaterial;
        this.idUnidad = idUnidad;
        this.precioUnit = precioUnit;
        this.subtotal = subtotal;
        this.cantidad = cantidad;
        this.total = total;
    }

    public OrdenCompraDet(int idOrden, int secuencia) {
        this.ordenCompraDetPK = new OrdenCompraDetPK(idOrden, secuencia);
    }

    public OrdenCompraDetPK getOrdenCompraDetPK() {
        return ordenCompraDetPK;
    }

    public void setOrdenCompraDetPK(OrdenCompraDetPK ordenCompraDetPK) {
        this.ordenCompraDetPK = ordenCompraDetPK;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public int getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(int idMaterial) {
        this.idMaterial = idMaterial;
    }

    public int getIdUnidad() {
        return idUnidad;
    }

    public void setIdUnidad(int idUnidad) {
        this.idUnidad = idUnidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecioUnit() {
        return precioUnit;
    }

    public void setPrecioUnit(BigDecimal precioUnit) {
        this.precioUnit = precioUnit;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public OrdenCompra getOrdenCompra() {
        return ordenCompra;
    }

    public void setOrdenCompra(OrdenCompra ordenCompra) {
        this.ordenCompra = ordenCompra;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ordenCompraDetPK != null ? ordenCompraDetPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrdenCompraDet)) {
            return false;
        }
        OrdenCompraDet other = (OrdenCompraDet) object;
        if ((this.ordenCompraDetPK == null && other.ordenCompraDetPK != null) || (this.ordenCompraDetPK != null && !this.ordenCompraDetPK.equals(other.ordenCompraDetPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.OrdenCompraDet[ ordenCompraDetPK=" + ordenCompraDetPK + " ]";
    }
    
}
