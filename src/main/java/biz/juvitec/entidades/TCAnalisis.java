package biz.juvitec.entidades;

import java.io.Serializable;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="tc_analisis")
public  class TCAnalisis implements Serializable {


    @Column(nullable=false)
    @Temporal(TemporalType.TIME)
    @Basic
    private Date hora;


    @Column(nullable=false)
    @Temporal(TemporalType.DATE)
    @Basic
    private Date fecha;


    @Column(name="empleado_nro_documento")
    @Id
    private String empleado;

    public TCAnalisis(){

    }


   public Date getHora() {
        return this.hora;
    }


  public void setHora (Date hora) {
        this.hora = hora;
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

}

