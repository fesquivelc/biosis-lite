package biz.juvitec.entidades;

import java.io.Serializable;

import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public  class Horario implements Serializable {


    @Column(nullable=false)
    @Basic
    private boolean sabado;


    @Column(nullable=false)
    @Basic
    private String nombre;


    @Column(length=45)
    @Id
    private String codigo;


    @Column(nullable=false)
    @Basic
    private boolean jueves;


    @Column(nullable=false)
    @Basic
    private boolean viernes;


    @Column(nullable=false)
    @Basic
    private boolean miercoles;


    @Column(nullable=false)
    @Basic
    private boolean lunes;


    @Column(nullable=false)
    @Basic
    private boolean domingo;


    @OneToMany(fetch=FetchType.LAZY,targetEntity=AsignacionHorario.class,mappedBy="horario")
    private List<AsignacionHorario> asignacionHorarioList;


    @Column(nullable=false)
    @Basic
    private boolean martes;


    @ManyToOne(optional=false,targetEntity=Jornada.class)
    @JoinColumn(name="jornada_codigo",referencedColumnName="codigo",nullable=false)
    private Jornada jornada;

    public Horario(){

    }


    public boolean isSabado() {
        return this.sabado;
    }


  public void setSabado (boolean sabado) {
        this.sabado = sabado;
    }



   public String getNombre() {
        return this.nombre;
    }


  public void setNombre (String nombre) {
        this.nombre = nombre;
    }



   public String getCodigo() {
        return this.codigo;
    }


  public void setCodigo (String codigo) {
        this.codigo = codigo;
    }



    public boolean isJueves() {
        return this.jueves;
    }


  public void setJueves (boolean jueves) {
        this.jueves = jueves;
    }



    public boolean isViernes() {
        return this.viernes;
    }


  public void setViernes (boolean viernes) {
        this.viernes = viernes;
    }



    public boolean isMiercoles() {
        return this.miercoles;
    }


  public void setMiercoles (boolean miercoles) {
        this.miercoles = miercoles;
    }



    public boolean isLunes() {
        return this.lunes;
    }


  public void setLunes (boolean lunes) {
        this.lunes = lunes;
    }



    public boolean isDomingo() {
        return this.domingo;
    }


  public void setDomingo (boolean domingo) {
        this.domingo = domingo;
    }



   public List<AsignacionHorario> getAsignacionHorarioList() {
        return this.asignacionHorarioList;
    }


  public void setAsignacionHorarioList (List<AsignacionHorario> asignacionHorarioList) {
        this.asignacionHorarioList = asignacionHorarioList;
    }



    public boolean isMartes() {
        return this.martes;
    }


  public void setMartes (boolean martes) {
        this.martes = martes;
    }



   public Jornada getJornada() {
        return this.jornada;
    }


  public void setJornada (Jornada jornada) {
        this.jornada = jornada;
    }

}

