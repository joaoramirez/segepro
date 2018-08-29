/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
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
 * @author Boris Cornejo
 */
@Entity
@Table(name = "orden_compra", catalog = "db_segepro", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrdenCompra.findAll", query = "SELECT o FROM OrdenCompra o")
    , @NamedQuery(name = "OrdenCompra.findByIdOrden", query = "SELECT o FROM OrdenCompra o WHERE o.idOrden = :idOrden")
    , @NamedQuery(name = "OrdenCompra.findByFecha", query = "SELECT o FROM OrdenCompra o WHERE o.fecha = :fecha")
    , @NamedQuery(name = "OrdenCompra.findByIdAgencia", query = "SELECT o FROM OrdenCompra o WHERE o.idAgencia = :idAgencia")
    , @NamedQuery(name = "OrdenCompra.findByTiempoEntrega", query = "SELECT o FROM OrdenCompra o WHERE o.tiempoEntrega = :tiempoEntrega")
    , @NamedQuery(name = "OrdenCompra.findByDireccionOficina", query = "SELECT o FROM OrdenCompra o WHERE o.direccionOficina = :direccionOficina")
    , @NamedQuery(name = "OrdenCompra.findByDireccionEntrega", query = "SELECT o FROM OrdenCompra o WHERE o.direccionEntrega = :direccionEntrega")
    , @NamedQuery(name = "OrdenCompra.findByIdProyecto", query = "SELECT o FROM OrdenCompra o WHERE o.idProyecto = :idProyecto")})
public class OrdenCompra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_orden")
    private Integer idOrden;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_agencia")
    private int idAgencia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "tiempo_entrega")
    private String tiempoEntrega;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "direccion_oficina")
    private String direccionOficina;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "direccion_entrega")
    private String direccionEntrega;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_proyecto")
    private int idProyecto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ordenCompra")
    private List<OrdenCompraDet> ordenCompraDetList;

    public OrdenCompra() {
    }

    public OrdenCompra(Integer idOrden) {
        this.idOrden = idOrden;
    }

    public OrdenCompra(Integer idOrden, Date fecha, int idAgencia, String tiempoEntrega, String direccionOficina, String direccionEntrega, int idProyecto) {
        this.idOrden = idOrden;
        this.fecha = fecha;
        this.idAgencia = idAgencia;
        this.tiempoEntrega = tiempoEntrega;
        this.direccionOficina = direccionOficina;
        this.direccionEntrega = direccionEntrega;
        this.idProyecto = idProyecto;
    }

    public Integer getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(Integer idOrden) {
        this.idOrden = idOrden;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdAgencia() {
        return idAgencia;
    }

    public void setIdAgencia(int idAgencia) {
        this.idAgencia = idAgencia;
    }

    public String getTiempoEntrega() {
        return tiempoEntrega;
    }

    public void setTiempoEntrega(String tiempoEntrega) {
        this.tiempoEntrega = tiempoEntrega;
    }

    public String getDireccionOficina() {
        return direccionOficina;
    }

    public void setDireccionOficina(String direccionOficina) {
        this.direccionOficina = direccionOficina;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    @XmlTransient
    public List<OrdenCompraDet> getOrdenCompraDetList() {
        return ordenCompraDetList;
    }

    public void setOrdenCompraDetList(List<OrdenCompraDet> ordenCompraDetList) {
        this.ordenCompraDetList = ordenCompraDetList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOrden != null ? idOrden.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrdenCompra)) {
            return false;
        }
        OrdenCompra other = (OrdenCompra) object;
        if ((this.idOrden == null && other.idOrden != null) || (this.idOrden != null && !this.idOrden.equals(other.idOrden))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.OrdenCompra[ idOrden=" + idOrden + " ]";
    }
    
}
