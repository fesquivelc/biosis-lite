package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Bitacora implements Serializable {

    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Basic
    private String valorAnterior;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Id
    private Long id;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Basic
    private String valorPosterior;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Basic
    private String campo;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Basic
    private String usuario;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Temporal(TemporalType.TIMESTAMP)
    @Basic
    private Date fechaHora;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Basic
    private char accion;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Basic
    private String tabla;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Basic
    private String ip;

    public Bitacora() {

    }
   
    public String getValorAnterior() {
        return this.valorAnterior;
    }

    public void setValorAnterior(String valorAnterior) {
        this.valorAnterior = valorAnterior;
    }
   
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
   
    public String getValorPosterior() {
        return this.valorPosterior;
    }

    public void setValorPosterior(String valorPosterior) {
        this.valorPosterior = valorPosterior;
    }
   
    public String getCampo() {
        return this.campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }
   
    public String getUsuario() {
        return this.usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
   
    public Date getFechaHora() {
        return this.fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }
   
    public char getAccion() {
        return this.accion;
    }

    public void setAccion(char accion) {
        this.accion = accion;
    }
   
    public String getTabla() {
        return this.tabla;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }
   
    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
