package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Horario implements Serializable {

    @Column(unique=false,updatable=true,insertable=true,nullable=false,length=255,scale=0,precision=0)
    @Basic
    private boolean sabado;
    @Column(unique=false,updatable=true,insertable=true,nullable=false,length=255,scale=0,precision=0)
    @Basic
    private String nombre;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=45,scale=0,precision=0)
    @Id
    private String codigo;
    @Column(unique=false,updatable=true,insertable=true,nullable=false,length=255,scale=0,precision=0)
    @Basic
    private boolean jueves;
    @Column(unique=false,updatable=true,insertable=true,nullable=false,length=255,scale=0,precision=0)
    @Basic
    private boolean viernes;
    @Column(unique=false,updatable=true,insertable=true,nullable=false,length=255,scale=0,precision=0)
    @Basic
    private boolean miercoles;
    @Column(unique=false,updatable=true,insertable=true,nullable=false,length=255,scale=0,precision=0)
    @Basic
    private boolean lunes;
    @Column(name="fecha_fin",unique=false,updatable=true,insertable=true,nullable=false,length=255,scale=0,precision=0)
    @Temporal(TemporalType.DATE)
    @Basic
    private Date fechaFin;
    @Column(name="documento",unique=false,updatable=true,insertable=true,nullable=false,length=255,scale=0,precision=0)
    @Basic
    private String documento;
    @Column(unique=false,updatable=true,insertable=true,nullable=false,length=255,scale=0,precision=0)
    @Basic
    private boolean domingo;
    @OneToMany(fetch = FetchType.LAZY,targetEntity = AsignacionHorario.class,mappedBy = "horario")
    private List<AsignacionHorario> asignacionHorarioList;
    @Column(name="fecha_inicio",unique=false,updatable=true,insertable=true,nullable=false,length=255,scale=0,precision=0)
    @Temporal(TemporalType.DATE)
    @Basic
    private Date fechaInicio;
    @Column(unique=false,updatable=true,insertable=true,nullable=false,length=255,scale=0,precision=0)
    @Basic
    private boolean martes;
    @ManyToOne(optional=false,targetEntity = Jornada.class)
    @JoinColumn(name="jornada_codigo",referencedColumnName="codigo",insertable=true,nullable=false,unique=false,updatable=true)
    private Jornada jornada;

    public Horario() {

    }
    
    public boolean isSabado() {
        return this.sabado;
    }

    public void setSabado(boolean sabado) {
        this.sabado = sabado;
    }
   
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
   
    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    public boolean isJueves() {
        return this.jueves;
    }

    public void setJueves(boolean jueves) {
        this.jueves = jueves;
    }
    
    public boolean isViernes() {
        return this.viernes;
    }

    public void setViernes(boolean viernes) {
        this.viernes = viernes;
    }
    
    public boolean isMiercoles() {
        return this.miercoles;
    }

    public void setMiercoles(boolean miercoles) {
        this.miercoles = miercoles;
    }
    
    public boolean isLunes() {
        return this.lunes;
    }

    public void setLunes(boolean lunes) {
        this.lunes = lunes;
    }
   
    public Date getFechaFin() {
        return this.fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
   
    public String getDocumento() {
        return this.documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }
    
    public boolean isDomingo() {
        return this.domingo;
    }

    public void setDomingo(boolean domingo) {
        this.domingo = domingo;
    }
   
    public List<AsignacionHorario> getAsignacionHorarioList() {
        return this.asignacionHorarioList;
    }

    public void setAsignacionHorarioList(List<AsignacionHorario> asignacionHorarioList) {
        this.asignacionHorarioList = asignacionHorarioList;
    }
   
    public Date getFechaInicio() {
        return this.fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    
    public boolean isMartes() {
        return this.martes;
    }

    public void setMartes(boolean martes) {
        this.martes = martes;
    }
   
    public Jornada getJornada() {
        return this.jornada;
    }

    public void setJornada(Jornada jornada) {
        this.jornada = jornada;
    }
}
