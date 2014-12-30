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
public  class Permiso implements Serializable {


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(optional=false,targetEntity=TipoPermiso.class)
    @JoinColumn(name="tipo_permiso_codigo",referencedColumnName="codigo",nullable=false)
    private TipoPermiso tipoPermiso;


    @Column(name="hora_inicio")
    @Temporal(TemporalType.TIME)
    @Basic
    private Date horaInicio;


    @Column(name="empleado_nro_documento",nullable=false)
    @Basic
    private String empleado;


    @Column(name="hora_fin")
    @Temporal(TemporalType.TIME)
    @Basic
    private Date horaFin;


    @Column(name="por_fecha",nullable=false)
    @Basic
    private boolean porFecha;


    @Column(name="fecha_fin",nullable=false)
    @Temporal(TemporalType.DATE)
    @Basic
    private Date fechaFin;


    @Column(name="fecha_inicio",nullable=false)
    @Temporal(TemporalType.DATE)
    @Basic
    private Date fechaInicio;

    public Permiso(){

    }


   public Long getId() {
        return this.id;
    }


  public void setId (Long id) {
        this.id = id;
    }



   public TipoPermiso getTipoPermiso() {
        return this.tipoPermiso;
    }


  public void setTipoPermiso (TipoPermiso tipoPermiso) {
        this.tipoPermiso = tipoPermiso;
    }



   public Date getHoraInicio() {
        return this.horaInicio;
    }


  public void setHoraInicio (Date horaInicio) {
        this.horaInicio = horaInicio;
    }



   public String getEmpleado() {
        return this.empleado;
    }


  public void setEmpleado (String empleado) {
        this.empleado = empleado;
    }



   public Date getHoraFin() {
        return this.horaFin;
    }


  public void setHoraFin (Date horaFin) {
        this.horaFin = horaFin;
    }



    public boolean isPorFecha() {
        return this.porFecha;
    }


  public void setPorFecha (boolean porFecha) {
        this.porFecha = porFecha;
    }



   public Date getFechaFin() {
        return this.fechaFin;
    }


  public void setFechaFin (Date fechaFin) {
        this.fechaFin = fechaFin;
    }



   public Date getFechaInicio() {
        return this.fechaInicio;
    }


  public void setFechaInicio (Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

}

