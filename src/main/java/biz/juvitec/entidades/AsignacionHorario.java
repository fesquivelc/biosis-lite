package biz.juvitec.entidades;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="asignacion_horario")
public  class AsignacionHorario implements Serializable {


    @Column(nullable=false)
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(optional=false,targetEntity=Horario.class)
    @JoinColumn(name="horario_codigo",referencedColumnName="codigo",nullable=false)
    private Horario horario;


    @Column(name="empleado_nro_documento")
    @Basic
    private String empleado;


    @ManyToOne(targetEntity=GrupoHorario.class)
    @JoinColumn(name="grupo_horario_codigo",referencedColumnName="codigo")
    private GrupoHorario grupoHorario;


    @Column(name="por_grupo",nullable=false)
    @Basic
    private boolean porGrupo;

    public AsignacionHorario(){

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



   public String getEmpleado() {
        return this.empleado;
    }


  public void setEmpleado (String empleado) {
        this.empleado = empleado;
    }



   public GrupoHorario getGrupoHorario() {
        return this.grupoHorario;
    }


  public void setGrupoHorario (GrupoHorario grupoHorario) {
        this.grupoHorario = grupoHorario;
    }



    public boolean isPorGrupo() {
        return this.porGrupo;
    }


  public void setPorGrupo (boolean porGrupo) {
        this.porGrupo = porGrupo;
    }

}

