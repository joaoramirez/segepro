/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
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
@Table(name = "agencia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Agencia.findAll", query = "SELECT a FROM Agencia a"),
    @NamedQuery(name = "Agencia.findByIdAgencia", query = "SELECT a FROM Agencia a WHERE a.idAgencia = :idAgencia"),
    @NamedQuery(name = "Agencia.findByTelefono", query = "SELECT a FROM Agencia a WHERE a.telefono = :telefono"),
    @NamedQuery(name = "Agencia.findByEncargado", query = "SELECT a FROM Agencia a WHERE a.encargado = :encargado"),
    @NamedQuery(name = "Agencia.findByDireccion", query = "SELECT a FROM Agencia a WHERE a.direccion = :direccion"),
    @NamedQuery(name = "Agencia.findByNombre", query = "SELECT a FROM Agencia a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "Agencia.findByIdDepto", query = "SELECT a FROM Agencia a WHERE a.idDepto = :idDepto"),
    @NamedQuery(name = "Agencia.findByNomMuni", query = "SELECT a FROM Agencia a WHERE a.nomMuni = :nomMuni"),
    @NamedQuery(name = "Agencia.findByEstado", query = "SELECT a FROM Agencia a WHERE a.estado = :estado"),
    @NamedQuery(name = "Agencia.findByLongitud", query = "SELECT a FROM Agencia a WHERE a.longitud = :longitud"),
    @NamedQuery(name = "Agencia.findByLatitude", query = "SELECT a FROM Agencia a WHERE a.latitude = :latitude")})
public class Agencia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_agencia")
    private Integer idAgencia;
    @Size(max = 9)
    @Column(name = "telefono")
    private String telefono;
    @Size(max = 100)
    @Column(name = "encargado")
    private String encargado;
    @Size(max = 250)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 150)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "id_depto")
    private Integer idDepto;
    @Size(max = 100)
    @Column(name = "nom_muni")
    private String nomMuni;
    @Size(max = 100)
    @Column(name = "estado")
    private String estado;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "longitud")
    private BigDecimal longitud;
    @Column(name = "latitude")
    private BigDecimal latitude;

    public Agencia() {
    }

    public Agencia(Integer idAgencia) {
        this.idAgencia = idAgencia;
    }

    public Integer getIdAgencia() {
        return idAgencia;
    }

    public void setIdAgencia(Integer idAgencia) {
        this.idAgencia = idAgencia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEncargado() {
        return encargado;
    }

    public void setEncargado(String encargado) {
        this.encargado = encargado;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdDepto() {
        return idDepto;
    }

    public void setIdDepto(Integer idDepto) {
        this.idDepto = idDepto;
    }

    public String getNomMuni() {
        return nomMuni;
    }

    public void setNomMuni(String nomMuni) {
        this.nomMuni = nomMuni;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public BigDecimal getLongitud() {
        return longitud;
    }

    public void setLongitud(BigDecimal longitud) {
        this.longitud = longitud;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAgencia != null ? idAgencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Agencia)) {
            return false;
        }
        Agencia other = (Agencia) object;
        if ((this.idAgencia == null && other.idAgencia != null) || (this.idAgencia != null && !this.idAgencia.equals(other.idAgencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Agencia[ idAgencia=" + idAgencia + " ]";
    }

    public Object getUsuarioList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setUsuarioList(ArrayList<Usuario> arrayList) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
