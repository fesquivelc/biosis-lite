package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Feriado implements Serializable {

    @Column(unique=false,updatable=true,insertable=true,nullable=false,length=255,scale=0,precision=0)
    @Basic
    private String nombre;
    @Column(name="fecha_fin",unique=false,updatable=true,insertable=true,nullable=false,length=255,scale=0,precision=0)
    @Temporal(TemporalType.DATE)
    @Basic
    private Date fechaFin;
    @ManyToOne(optional=false,targetEntity = Periodo.class)
    @JoinColumn(name="periodo_anio",referencedColumnName="anio",insertable=true,nullable=false,unique=false,updatable=true)
    private Periodo periodo;
    @Column(name="fecha_inicio",unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Id
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;

    public Feriado() {

    }
   
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
