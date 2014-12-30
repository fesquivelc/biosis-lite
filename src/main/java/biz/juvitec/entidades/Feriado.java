package biz.juvitec.entidades;

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
public  class Feriado implements Serializable {


    @Column(nullable=false)
    @Basic
    private String nombre;


    @Column(name="fecha_fin",nullable=false)
    @Temporal(TemporalType.DATE)
    @Basic
    private Date fechaFin;


    @ManyToOne(optional=false,targetEntity=Periodo.class)
    @JoinColumn(name="periodo_anio",referencedColumnName="anio",nullable=false)
    private Periodo periodo;


    @Column(name="fecha_inicio")
    @Id
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;

    public Feriado(){

    }


   public String getNombre() {
        return this.nombre;
    }


  public void setNombre (String nombre) {
        this.nombre = nombre;
    }



   public Date getFechaFin() {
        return this.fechaFin;
    }


  public void setFechaFin (Date fechaFin) {
        this.fechaFin = fechaFin;
    }



   public Periodo getPeriodo() {
        return this.periodo;
    }


  public void setPeriodo (Periodo periodo) {
        this.periodo = periodo;
    }



   public Date getFechaInicio() {
        return this.fechaInicio;
    }


  public void setFechaInicio (Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

}

