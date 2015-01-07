package biz.juvitec.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="detalle_registro_asistencia")
public class DetalleRegistroAsistencia implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIME)
    @Basic
    private Date hora;
    @ManyToOne(targetEntity = Permiso.class)
    @JoinColumn(name="permiso_id",referencedColumnName="id")
    private Permiso permiso;
    @Column(nullable=false)
    @Basic
    private char tipo;
    @Column(nullable=false)
    @Basic
    private char resultado;
    @Column(nullable=false)
    @Basic
    private char evento;
    @ManyToOne(optional=false,targetEntity = RegistroAsistencia.class)
    @JoinColumn(name="registro_asistencia_id",referencedColumnName="id",nullable=false)
    private RegistroAsistencia registroAsistencia;

    public DetalleRegistroAsistencia() {

    }
   
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
   
    public Date getHora() {
        return this.hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }
   
    public Permiso getPermiso() {
        return this.permiso;
    }

    public void setPermiso(Permiso permiso) {
        this.permiso = permiso;
    }
   
    public char getTipo() {
        return this.tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }
   
    public char getResultado() {
        return this.resultado;
    }

    public void setResultado(char resultado) {
        this.resultado = resultado;
    }
   
    public char getEvento() {
        return this.evento;
    }

    public void setEvento(char evento) {
        this.evento = evento;
    }
   
    public RegistroAsistencia getRegistroAsistencia() {
        return this.registroAsistencia;
    }

    public void setRegistroAsistencia(RegistroAsistencia registroAsistencia) {
        this.registroAsistencia = registroAsistencia;
    }
}
