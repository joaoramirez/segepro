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
@Table(name = "referencias_personales")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReferenciasPersonales.findAll", query = "SELECT r FROM ReferenciasPersonales r"),
    @NamedQuery(name = "ReferenciasPersonales.findByIdCliente", query = "SELECT r FROM ReferenciasPersonales r WHERE r.idCliente = :idCliente"),
    @NamedQuery(name = "ReferenciasPersonales.findByIdReferenciaP", query = "SELECT r FROM ReferenciasPersonales r WHERE r.idReferenciaP = :idReferenciaP"),
    @NamedQuery(name = "ReferenciasPersonales.findByNombreCompletoRef", query = "SELECT r FROM ReferenciasPersonales r WHERE r.nombreCompletoRef = :nombreCompletoRef"),
    @NamedQuery(name = "ReferenciasPersonales.findByParentesco", query = "SELECT r FROM ReferenciasPersonales r WHERE r.parentesco = :parentesco"),
    @NamedQuery(name = "ReferenciasPersonales.findByTelefono", query = "SELECT r FROM ReferenciasPersonales r WHERE r.telefono = :telefono"),
    @NamedQuery(name = "ReferenciasPersonales.findByDireccion", query = "SELECT r FROM ReferenciasPersonales r WHERE r.direccion = :direccion"),
    @NamedQuery(name = "ReferenciasPersonales.findByLugarTrabajo", query = "SELECT r FROM ReferenciasPersonales r WHERE r.lugarTrabajo = :lugarTrabajo"),
    @NamedQuery(name = "ReferenciasPersonales.findByNomSolicitante", query = "SELECT r FROM ReferenciasPersonales r WHERE r.nomSolicitante = :nomSolicitante"),
    @NamedQuery(name = "ReferenciasPersonales.findByNumSolicitud", query = "SELECT r FROM ReferenciasPersonales r WHERE r.numSolicitud = :numSolicitud")})
public class ReferenciasPersonales implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_cliente")
    private int idCliente;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_referencia_p")
    private Integer idReferenciaP;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "nombre_completo_ref")
    private String nombreCompletoRef;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "parentesco")
    private String parentesco;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "telefono")
    private String telefono;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "direccion")
    private String direccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "lugar_trabajo")
    private String lugarTrabajo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "nom_solicitante")
    private String nomSolicitante;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "num_solicitud")
    private String numSolicitud;

    public ReferenciasPersonales() {
    }

    public ReferenciasPersonales(Integer idReferenciaP) {
        this.idReferenciaP = idReferenciaP;
    }

    public ReferenciasPersonales(Integer idReferenciaP, int idCliente, String nombreCompletoRef, String parentesco, String telefono, String direccion, String lugarTrabajo, String nomSolicitante, String numSolicitud) {
        this.idReferenciaP = idReferenciaP;
        this.idCliente = idCliente;
        this.nombreCompletoRef = nombreCompletoRef;
        this.parentesco = parentesco;
        this.telefono = telefono;
        this.direccion = direccion;
        this.lugarTrabajo = lugarTrabajo;
        this.nomSolicitante = nomSolicitante;
        this.numSolicitud = numSolicitud;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdReferenciaP() {
        return idReferenciaP;
    }

    public void setIdReferenciaP(Integer idReferenciaP) {
        this.idReferenciaP = idReferenciaP;
    }

    public String getNombreCompletoRef() {
        return nombreCompletoRef;
    }

    public void setNombreCompletoRef(String nombreCompletoRef) {
        this.nombreCompletoRef = nombreCompletoRef;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLugarTrabajo() {
        return lugarTrabajo;
    }

    public void setLugarTrabajo(String lugarTrabajo) {
        this.lugarTrabajo = lugarTrabajo;
    }

    public String getNomSolicitante() {
        return nomSolicitante;
    }

    public void setNomSolicitante(String nomSolicitante) {
        this.nomSolicitante = nomSolicitante;
    }

    public String getNumSolicitud() {
        return numSolicitud;
    }

    public void setNumSolicitud(String numSolicitud) {
        this.numSolicitud = numSolicitud;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReferenciaP != null ? idReferenciaP.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReferenciasPersonales)) {
            return false;
        }
        ReferenciasPersonales other = (ReferenciasPersonales) object;
        if ((this.idReferenciaP == null && other.idReferenciaP != null) || (this.idReferenciaP != null && !this.idReferenciaP.equals(other.idReferenciaP))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ReferenciasPersonales[ idReferenciaP=" + idReferenciaP + " ]";
    }
    
}
