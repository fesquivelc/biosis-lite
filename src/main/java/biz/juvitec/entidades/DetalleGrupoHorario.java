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
@Table(name="detalle_grupo_horario")
public  class DetalleGrupoHorario implements Serializable {


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;


    @Column(name="empleado_nro_documento",nullable=false)
    @Basic
    private String empleado;


    @ManyToOne(optional=false,targetEntity=GrupoHorario.class)
    @JoinColumn(name="grupo_horario_codigo",referencedColumnName="codigo",nullable=false)
    private GrupoHorario grupoHorario;

    public DetalleGrupoHorario(){

    }


   public Long getId() {
        return this.id;
    }


  public void setId (Long id) {
        this.id = id;
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

}

