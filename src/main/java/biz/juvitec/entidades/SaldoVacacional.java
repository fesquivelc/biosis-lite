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
@Table(name="saldo_vacacional")
public  class SaldoVacacional implements Serializable {


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;


    @Column(name="empleado_nro_documento",nullable=false)
    @Basic
    private String empleado;


    @Basic
    private String diasRestantes;


    @ManyToOne(optional=false,targetEntity=Periodo.class)
    @JoinColumn(name="periodo_anio",referencedColumnName="anio",nullable=false)
    private Periodo periodo;

    public SaldoVacacional(){

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



   public String getDiasRestantes() {
        return this.diasRestantes;
    }


  public void setDiasRestantes (String diasRestantes) {
        this.diasRestantes = diasRestantes;
    }



   public Periodo getPeriodo() {
        return this.periodo;
    }


  public void setPeriodo (Periodo periodo) {
        this.periodo = periodo;
    }

}

