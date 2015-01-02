package biz.juvitec.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Vacacion implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name="fecha_interrupcion",nullable=false)
    @Temporal(TemporalType.DATE)
    @Basic
    private Date fechaInterrupcion;
    @Column(name="empleado_nro_documento",nullable=false)
    @Basic
    private String empleado;
    @Column(name="hay_interrupcion",nullable=false)
    @Basic
    private boolean hayInterrupcion;
    @Column(name="fecha_fin",nullable=false)
    @Temporal(TemporalType.DATE)
    @Basic
    private Date fechaFin;
    @ManyToOne(optional=false,targetEntity = Periodo.class)
    @JoinColumn(name="periodo_anio",referencedColumnName="anio",nullable=false)
    private Periodo periodo;
    @Column(name="fecha_inicio",nullable=false)
    @Temporal(TemporalType.DATE)
    @Basic
    private Date fechaInicio;

    public Vacacion() {

    }
   
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
   
    public Date getFechaInterrupcion() {
        return this.fechaInterrupcion;
    }

    public void setFechaInterrupcion(Date fechaInterrupcion) {
        this.fechaInterrupcion = fechaInterrupcion;
    }
   
    public String getEmpleado() {
        return this.empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }
    
    public boolean isHayInterrupcion() {
        return this.hayInterrupcion;
    }

    public void setHayInterrupcion(boolean hayInterrupcion) {
        this.hayInterrupcion = hayInterrupcion;
    }
   
    public Date getFechaFin() {
        return this.fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
   
    public Periodo getPeriodo() {
        return this.periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }
   
    public Date getFechaInicio() {
        return this.fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
}
