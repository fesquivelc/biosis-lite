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
@Table(name="tc_sistema")
public class TCSistema implements Serializable {

    @Id
    private String sistema;
    @Column(name="fecha_cero",nullable=false)
    @Temporal(TemporalType.DATE)
    @Basic
    private Date fechaCero;
    @Column(name="hora_cero",nullable=false)
    @Temporal(TemporalType.TIME)
    @Basic
    private Date horaCero;

    public TCSistema() {

    }
   
    public String getSistema() {
        return this.sistema;
    }

    public void setSistema(String sistema) {
        this.sistema = sistema;
    }
   
    public Date getFechaCero() {
        return this.fechaCero;
    }

    public void setFechaCero(Date fechaCero) {
        this.fechaCero = fechaCero;
    }
   
    public Date getHoraCero() {
        return this.horaCero;
    }

    public void setHoraCero(Date horaCero) {
        this.horaCero = horaCero;
    }
}
