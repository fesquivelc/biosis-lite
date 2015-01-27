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

    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Id
    private String sistema;
    @Column(name="fecha_cero",unique=false,updatable=true,insertable=true,nullable=false,length=255,scale=0,precision=0)
    @Temporal(TemporalType.DATE)
    @Basic
    private Date fechaCero;
    @Column(name="hora_cero",unique=false,updatable=true,insertable=true,nullable=false,length=255,scale=0,precision=0)
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
