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
 * @author Boris Cornejo
 */
@Entity
@Table(name = "cliente", catalog = "db_segepro", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c")
    , @NamedQuery(name = "Cliente.findByIdCliente", query = "SELECT c FROM Cliente c WHERE c.idCliente = :idCliente")
    , @NamedQuery(name = "Cliente.findByNombre", query = "SELECT c FROM Cliente c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "Cliente.findByApellido", query = "SELECT c FROM Cliente c WHERE c.apellido = :apellido")
    , @NamedQuery(name = "Cliente.findByDui", query = "SELECT c FROM Cliente c WHERE c.dui = :dui")
    , @NamedQuery(name = "Cliente.findByNit", query = "SELECT c FROM Cliente c WHERE c.nit = :nit")
    , @NamedQuery(name = "Cliente.findByEstadoCivil", query = "SELECT c FROM Cliente c WHERE c.estadoCivil = :estadoCivil")
    , @NamedQuery(name = "Cliente.findBySexo", query = "SELECT c FROM Cliente c WHERE c.sexo = :sexo")
    , @NamedQuery(name = "Cliente.findByLugarExp", query = "SELECT c FROM Cliente c WHERE c.lugarExp = :lugarExp")
    , @NamedQuery(name = "Cliente.findByFecExp", query = "SELECT c FROM Cliente c WHERE c.fecExp = :fecExp")
    , @NamedQuery(name = "Cliente.findByLugarNac", query = "SELECT c FROM Cliente c WHERE c.lugarNac = :lugarNac")
    , @NamedQuery(name = "Cliente.findByFecNac", query = "SELECT c FROM Cliente c WHERE c.fecNac = :fecNac")
    , @NamedQuery(name = "Cliente.findByTelefono", query = "SELECT c FROM Cliente c WHERE c.telefono = :telefono")
    , @NamedQuery(name = "Cliente.findByProfesion", query = "SELECT c FROM Cliente c WHERE c.profesion = :profesion")
    , @NamedQuery(name = "Cliente.findByDireccion", query = "SELECT c FROM Cliente c WHERE c.direccion = :direccion")
    , @NamedQuery(name = "Cliente.findByPuntoRef", query = "SELECT c FROM Cliente c WHERE c.puntoRef = :puntoRef")
    , @NamedQuery(name = "Cliente.findByResideDesde", query = "SELECT c FROM Cliente c WHERE c.resideDesde = :resideDesde")
    , @NamedQuery(name = "Cliente.findByCondicionVivienda", query = "SELECT c FROM Cliente c WHERE c.condicionVivienda = :condicionVivienda")
    , @NamedQuery(name = "Cliente.findByLugarTrabajo", query = "SELECT c FROM Cliente c WHERE c.lugarTrabajo = :lugarTrabajo")
    , @NamedQuery(name = "Cliente.findByJefeInmediato", query = "SELECT c FROM Cliente c WHERE c.jefeInmediato = :jefeInmediato")
    , @NamedQuery(name = "Cliente.findByTiempoEmpleo", query = "SELECT c FROM Cliente c WHERE c.tiempoEmpleo = :tiempoEmpleo")
    , @NamedQuery(name = "Cliente.findBySalario", query = "SELECT c FROM Cliente c WHERE c.salario = :salario")
    , @NamedQuery(name = "Cliente.findByFiador", query = "SELECT c FROM Cliente c WHERE c.fiador = :fiador")
    , @NamedQuery(name = "Cliente.findByDireccionTrabajo", query = "SELECT c FROM Cliente c WHERE c.direccionTrabajo = :direccionTrabajo")
    , @NamedQuery(name = "Cliente.findByTelefonoTrabajo", query = "SELECT c FROM Cliente c WHERE c.telefonoTrabajo = :telefonoTrabajo")})
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_cliente")
    private Integer idCliente;
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
    @Size(min = 1, max = 30)
    @Column(name = "estado_civil")
    private String estadoCivil;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "sexo")
    private String sexo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "lugar_exp")
    private String lugarExp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fec_exp")
    @Temporal(TemporalType.DATE)
    private Date fecExp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "lugar_nac")
    private String lugarNac;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fec_nac")
    @Temporal(TemporalType.DATE)
    private Date fecNac;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "telefono")
    private String telefono;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "profesion")
    private String profesion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "direccion")
    private String direccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "punto_ref")
    private String puntoRef;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "reside_desde")
    private String resideDesde;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "condicion_vivienda")
    private String condicionVivienda;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "lugar_trabajo")
    private String lugarTrabajo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "jefe_inmediato")
    private String jefeInmediato;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "tiempo_empleo")
    private String tiempoEmpleo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "salario")
    private BigDecimal salario;
    @Size(max = 1)
    @Column(name = "fiador")
    private String fiador;
    @Size(max = 300)
    @Column(name = "direccion_trabajo")
    private String direccionTrabajo;
    @Size(max = 10)
    @Column(name = "telefono_trabajo")
    private String telefonoTrabajo;

    public Cliente() {
    }

    public Cliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Cliente(Integer idCliente, String nombre, String apellido, String dui, String nit, String estadoCivil, String sexo, String lugarExp, Date fecExp, String lugarNac, Date fecNac, String telefono, String profesion, String direccion, String puntoRef, String resideDesde, String condicionVivienda, String lugarTrabajo, String jefeInmediato, String tiempoEmpleo, BigDecimal salario) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dui = dui;
        this.nit = nit;
        this.estadoCivil = estadoCivil;
        this.sexo = sexo;
        this.lugarExp = lugarExp;
        this.fecExp = fecExp;
        this.lugarNac = lugarNac;
        this.fecNac = fecNac;
        this.telefono = telefono;
        this.profesion = profesion;
        this.direccion = direccion;
        this.puntoRef = puntoRef;
        this.resideDesde = resideDesde;
        this.condicionVivienda = condicionVivienda;
        this.lugarTrabajo = lugarTrabajo;
        this.jefeInmediato = jefeInmediato;
        this.tiempoEmpleo = tiempoEmpleo;
        this.salario = salario;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
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

    public Date getFecExp() {
        return fecExp;
    }

    public void setFecExp(Date fecExp) {
        this.fecExp = fecExp;
    }

    public String getLugarNac() {
        return lugarNac;
    }

    public void setLugarNac(String lugarNac) {
        this.lugarNac = lugarNac;
    }

    public Date getFecNac() {
        return fecNac;
    }

    public void setFecNac(Date fecNac) {
        this.fecNac = fecNac;
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

    public String getFiador() {
        return fiador;
    }

    public void setFiador(String fiador) {
        this.fiador = fiador;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCliente != null ? idCliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.idCliente == null && other.idCliente != null) || (this.idCliente != null && !this.idCliente.equals(other.idCliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Cliente[ idCliente=" + idCliente + " ]";
    }
    
}
