/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
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
@Table(name = "voluntarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Voluntarios.findAll", query = "SELECT v FROM Voluntarios v"),
    @NamedQuery(name = "Voluntarios.findByIdVoluntario", query = "SELECT v FROM Voluntarios v WHERE v.idVoluntario = :idVoluntario"),
    @NamedQuery(name = "Voluntarios.findByNombres", query = "SELECT v FROM Voluntarios v WHERE v.nombres = :nombres"),
    @NamedQuery(name = "Voluntarios.findByApellidos", query = "SELECT v FROM Voluntarios v WHERE v.apellidos = :apellidos"),
    @NamedQuery(name = "Voluntarios.findByDui", query = "SELECT v FROM Voluntarios v WHERE v.dui = :dui"),
    @NamedQuery(name = "Voluntarios.findByNit", query = "SELECT v FROM Voluntarios v WHERE v.nit = :nit"),
    @NamedQuery(name = "Voluntarios.findByNacionalidad", query = "SELECT v FROM Voluntarios v WHERE v.nacionalidad = :nacionalidad"),
    @NamedQuery(name = "Voluntarios.findByFechaNacimiento", query = "SELECT v FROM Voluntarios v WHERE v.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "Voluntarios.findByNumPasaporte", query = "SELECT v FROM Voluntarios v WHERE v.numPasaporte = :numPasaporte"),
    @NamedQuery(name = "Voluntarios.findByIdBrigada", query = "SELECT v FROM Voluntarios v WHERE v.idBrigada = :idBrigada")})
public class Voluntarios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_voluntario")
    private Integer idVoluntario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "nombres")
    private String nombres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "apellidos")
    private String apellidos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "dui")
    private String dui;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 17)
    @Column(name = "nit")
    private String nit;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "nacionalidad")
    private String nacionalidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "num_pasaporte")
    private String numPasaporte;
    @Column(name = "id_brigada")
    private Integer idBrigada;

    public Voluntarios() {
    }

    public Voluntarios(Integer idVoluntario) {
        this.idVoluntario = idVoluntario;
    }

    public Voluntarios(Integer idVoluntario, String nombres, String apellidos, String dui, String nit, String nacionalidad, Date fechaNacimiento, String numPasaporte) {
        this.idVoluntario = idVoluntario;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.dui = dui;
        this.nit = nit;
        this.nacionalidad = nacionalidad;
        this.fechaNacimiento = fechaNacimiento;
        this.numPasaporte = numPasaporte;
    }

    public Integer getIdVoluntario() {
        return idVoluntario;
    }

    public void setIdVoluntario(Integer idVoluntario) {
        this.idVoluntario = idVoluntario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNumPasaporte() {
        return numPasaporte;
    }

    public void setNumPasaporte(String numPasaporte) {
        this.numPasaporte = numPasaporte;
    }

    public Integer getIdBrigada() {
        return idBrigada;
    }

    public void setIdBrigada(Integer idBrigada) {
        this.idBrigada = idBrigada;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVoluntario != null ? idVoluntario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Voluntarios)) {
            return false;
        }
        Voluntarios other = (Voluntarios) object;
        if ((this.idVoluntario == null && other.idVoluntario != null) || (this.idVoluntario != null && !this.idVoluntario.equals(other.idVoluntario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Voluntarios[ idVoluntario=" + idVoluntario + " ]";
    }
    
}
