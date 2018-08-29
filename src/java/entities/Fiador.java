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
@Table(name = "fiador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fiador.findAll", query = "SELECT f FROM Fiador f"),
    @NamedQuery(name = "Fiador.findByIdFiador", query = "SELECT f FROM Fiador f WHERE f.idFiador = :idFiador"),
    @NamedQuery(name = "Fiador.findByNombre", query = "SELECT f FROM Fiador f WHERE f.nombre = :nombre"),
    @NamedQuery(name = "Fiador.findByApellido", query = "SELECT f FROM Fiador f WHERE f.apellido = :apellido"),
    @NamedQuery(name = "Fiador.findByDui", query = "SELECT f FROM Fiador f WHERE f.dui = :dui"),
    @NamedQuery(name = "Fiador.findByNit", query = "SELECT f FROM Fiador f WHERE f.nit = :nit"),
    @NamedQuery(name = "Fiador.findByEstadoCivil", query = "SELECT f FROM Fiador f WHERE f.estadoCivil = :estadoCivil"),
    @NamedQuery(name = "Fiador.findBySexo", query = "SELECT f FROM Fiador f WHERE f.sexo = :sexo"),
    @NamedQuery(name = "Fiador.findByLugarExp", query = "SELECT f FROM Fiador f WHERE f.lugarExp = :lugarExp"),
    @NamedQuery(name = "Fiador.findByFechaExp", query = "SELECT f FROM Fiador f WHERE f.fechaExp = :fechaExp"),
    @NamedQuery(name = "Fiador.findByLugarNac", query = "SELECT f FROM Fiador f WHERE f.lugarNac = :lugarNac"),
    @NamedQuery(name = "Fiador.findByFechaNac", query = "SELECT f FROM Fiador f WHERE f.fechaNac = :fechaNac"),
    @NamedQuery(name = "Fiador.findByTelefono", query = "SELECT f FROM Fiador f WHERE f.telefono = :telefono"),
    @NamedQuery(name = "Fiador.findByProfesion", query = "SELECT f FROM Fiador f WHERE f.profesion = :profesion"),
    @NamedQuery(name = "Fiador.findByDireccion", query = "SELECT f FROM Fiador f WHERE f.direccion = :direccion"),
    @NamedQuery(name = "Fiador.findByPuntoRef", query = "SELECT f FROM Fiador f WHERE f.puntoRef = :puntoRef"),
    @NamedQuery(name = "Fiador.findByResideDesde", query = "SELECT f FROM Fiador f WHERE f.resideDesde = :resideDesde"),
    @NamedQuery(name = "Fiador.findByCondicionVivienda", query = "SELECT f FROM Fiador f WHERE f.condicionVivienda = :condicionVivienda"),
    @NamedQuery(name = "Fiador.findByLugarTrabajo", query = "SELECT f FROM Fiador f WHERE f.lugarTrabajo = :lugarTrabajo"),
    @NamedQuery(name = "Fiador.findByJefeInmediato", query = "SELECT f FROM Fiador f WHERE f.jefeInmediato = :jefeInmediato"),
    @NamedQuery(name = "Fiador.findByTiempoEmpleo", query = "SELECT f FROM Fiador f WHERE f.tiempoEmpleo = :tiempoEmpleo"),
    @NamedQuery(name = "Fiador.findBySalario", query = "SELECT f FROM Fiador f WHERE f.salario = :salario"),
    @NamedQuery(name = "Fiador.findByDireccionTrabajo", query = "SELECT f FROM Fiador f WHERE f.direccionTrabajo = :direccionTrabajo"),
    @NamedQuery(name = "Fiador.findByTelefonoTrabajo", query = "SELECT f FROM Fiador f WHERE f.telefonoTrabajo = :telefonoTrabajo"),
    @NamedQuery(name = "Fiador.findByIdCliente", query = "SELECT f FROM Fiador f WHERE f.idCliente = :idCliente")})
public class Fiador implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_fiador")
    private Integer idFiador;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "apellido")
    private String apellido;
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
    @Size(min = 1, max = 25)
    @Column(name = "estado_civil")
    private String estadoCivil;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "sexo")
    private String sexo;
    @Size(max = 150)
    @Column(name = "lugar_exp")
    private String lugarExp;
    @Size(max = 10)
    @Column(name = "fecha_exp")
    private String fechaExp;
    @Size(max = 150)
    @Column(name = "lugar_nac")
    private String lugarNac;
    @Size(max = 10)
    @Column(name = "fecha_nac")
    private String fechaNac;
    @Size(max = 10)
    @Column(name = "telefono")
    private String telefono;
    @Size(max = 150)
    @Column(name = "profesion")
    private String profesion;
    @Size(max = 300)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 150)
    @Column(name = "punto_ref")
    private String puntoRef;
    @Size(max = 100)
    @Column(name = "reside_desde")
    private String resideDesde;
    @Size(max = 100)
    @Column(name = "condicion_vivienda")
    private String condicionVivienda;
    @Size(max = 150)
    @Column(name = "lugar_trabajo")
    private String lugarTrabajo;
    @Size(max = 100)
    @Column(name = "jefe_inmediato")
    private String jefeInmediato;
    @Size(max = 100)
    @Column(name = "tiempo_empleo")
    private String tiempoEmpleo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "salario")
    private BigDecimal salario;
    @Size(max = 300)
    @Column(name = "direccion_trabajo")
    private String direccionTrabajo;
    @Size(max = 10)
    @Column(name = "telefono_trabajo")
    private String telefonoTrabajo;
    @Column(name = "id_cliente")
    private Integer idCliente;

    public Fiador() {
    }

    public Fiador(Integer idFiador) {
        this.idFiador = idFiador;
    }

    public Fiador(Integer idFiador, String nombre, String apellido, String dui, String nit, String estadoCivil, String sexo) {
        this.idFiador = idFiador;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dui = dui;
        this.nit = nit;
        this.estadoCivil = estadoCivil;
        this.sexo = sexo;
    }

    public Integer getIdFiador() {
        return idFiador;
    }

    public void setIdFiador(Integer idFiador) {
        this.idFiador = idFiador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
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

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getLugarExp() {
        return lugarExp;
    }

    public void setLugarExp(String lugarExp) {
        this.lugarExp = lugarExp;
    }

    public String getFechaExp() {
        return fechaExp;
    }

    public void setFechaExp(String fechaExp) {
        this.fechaExp = fechaExp;
    }

    public String getLugarNac() {
        return lugarNac;
    }

    public void setLugarNac(String lugarNac) {
        this.lugarNac = lugarNac;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPuntoRef() {
        return puntoRef;
    }

    public void setPuntoRef(String puntoRef) {
        this.puntoRef = puntoRef;
    }

    public String getResideDesde() {
        return resideDesde;
    }

    public void setResideDesde(String resideDesde) {
        this.resideDesde = resideDesde;
    }

    public String getCondicionVivienda() {
        return condicionVivienda;
    }

    public void setCondicionVivienda(String condicionVivienda) {
        this.condicionVivienda = condicionVivienda;
    }

    public String getLugarTrabajo() {
        return lugarTrabajo;
    }

    public void setLugarTrabajo(String lugarTrabajo) {
        this.lugarTrabajo = lugarTrabajo;
    }

    public String getJefeInmediato() {
        return jefeInmediato;
    }

    public void setJefeInmediato(String jefeInmediato) {
        this.jefeInmediato = jefeInmediato;
    }

    public String getTiempoEmpleo() {
        return tiempoEmpleo;
    }

    public void setTiempoEmpleo(String tiempoEmpleo) {
        this.tiempoEmpleo = tiempoEmpleo;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getDireccionTrabajo() {
        return direccionTrabajo;
    }

    public void setDireccionTrabajo(String direccionTrabajo) {
        this.direccionTrabajo = direccionTrabajo;
    }

    public String getTelefonoTrabajo() {
        return telefonoTrabajo;
    }

    public void setTelefonoTrabajo(String telefonoTrabajo) {
        this.telefonoTrabajo = telefonoTrabajo;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFiador != null ? idFiador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fiador)) {
            return false;
        }
        Fiador other = (Fiador) object;
        if ((this.idFiador == null && other.idFiador != null) || (this.idFiador != null && !this.idFiador.equals(other.idFiador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Fiador[ idFiador=" + idFiador + " ]";
    }
    
}
