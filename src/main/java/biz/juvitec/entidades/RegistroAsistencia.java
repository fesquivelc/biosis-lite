package biz.juvitec.entidades;

import java.io.Serializable;

import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="registro_asistencia")
public  class RegistroAsistencia implements Serializable {


    @Column(nullable=false)
    @Basic
    private long milisegundosTrabajados;


    @Column(nullable=false)
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(optional=false,targetEntity=Horario.class)
    @JoinColumn(name="horario_codigo",referencedColumnName="codigo",nullable=false)
    private Horario horario;


    @OneToMany(fetch=FetchType.LAZY,targetEntity=DetalleRegistroAsistencia.class,mappedBy="registroAsistencia")
    private List<DetalleRegistroAsistencia> detalleRegistroAsistenciaList;


    @ManyToOne(targetEntity=Permiso.class)
    private Permiso permiso;


    @Column(nullable=false)
    @Temporal(TemporalType.DATE)
    @Basic
    private Date fecha;


    @Column(name="emleado_nro_documento")
    @Basic
    private String empleado;


    @ManyToOne(targetEntity=Feriado.class)
    @JoinColumn(name="feriado_fecha_inicio",referencedColumnName="fecha_inicio")
    private Feriado feriado;


    @Column(nullable=false)
    @Basic
    private long milisegundosTardanza;


    @ManyToOne(targetEntity=Vacacion.class)
    @JoinColumn(name="vacacion_id",referencedColumnName="id")
    private Vacacion vacacion;


    @Column(name="tipo_asistencia",nullable=false)
    @Basic
    private char tipoAsistencia;

    public RegistroAsistencia(){

    }


   public long getMilisegundosTrabajados() {
        return this.milisegundosTrabajados;
    }


  public void setMilisegundosTrabajados (long milisegundosTrabajados) {
        this.milisegundosTrabajados = milisegundosTrabajados;
    }



   public Long getId() {
        return this.id;
    }


  public void setId (Long id) {
        this.id = id;
    }



   public Horario getHorario() {
        return this.horario;
    }


  public void setHorario (Horario horario) {
        this.horario = horario;
    }



   public List<DetalleRegistroAsistencia> getDetalleRegistroAsistenciaList() {
        return this.detalleRegistroAsistenciaList;
    }


  public void setDetalleRegistroAsistenciaList (List<DetalleRegistroAsistencia> detalleRegistroAsistenciaList) {
        this.detalleRegistroAsistenciaList = detalleRegistroAsistenciaList;
    }



   public Permiso getPermiso() {
        return this.permiso;
    }


  public void setPermiso (Permiso permiso) {
        this.permiso = permiso;
    }



   public Date getFecha() {
        return this.fecha;
    }


  public void setFecha (Date fecha) {
        this.fecha = fecha;
    }



   public String getEmpleado() {
        return this.empleado;
    }


  public void setEmpleado (String empleado) {
        this.empleado = empleado;
    }



   public Feriado getFeriado() {
        return this.feriado;
    }


  public void setFeriado (Feriado feriado) {
        this.feriado = feriado;
    }



   public long getMilisegundosTardanza() {
        return this.milisegundosTardanza;
    }


  public void setMilisegundosTardanza (long milisegundosTardanza) {
        this.milisegundosTardanza = milisegundosTardanza;
    }



   public Vacacion getVacacion() {
        return this.vacacion;
    }


  public void setVacacion (Vacacion vacacion) {
        this.vacacion = vacacion;
    }



   public char getTipoAsistencia() {
        return this.tipoAsistencia;
    }


  public void setTipoAsistencia (char tipoAsistencia) {
        this.tipoAsistencia = tipoAsistencia;
    }

}

