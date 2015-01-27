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
@Table(name="saldo_vacacional")
public class SaldoVacacional implements Serializable {

    @Column(name="sabado",unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Basic
    private int sabado;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name="fecha_desde",unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Temporal(TemporalType.DATE)
    @Basic
    private Date fechaDesde;
    @Column(name="empleado_nro_documento",unique=false,updatable=true,insertable=true,nullable=false,length=255,scale=0,precision=0)
    @Basic
    private String empleado;
    @Column(name="fecha_hasta",unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Temporal(TemporalType.DATE)
    @Basic
    private Date fechaHasta;
    @Column(name="domingo",unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Basic
    private int domingo;
    @Column(name="lunes_viernes",unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Basic
    private int lunesViernes;
    @Column(name="dias_restantes",unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Basic
    private int diasRestantes;
    @ManyToOne(optional=false,targetEntity = Periodo.class)
    @JoinColumn(name="periodo_anio",referencedColumnName="anio",insertable=true,nullable=false,unique=false,updatable=true)
    private Periodo periodo;

    public SaldoVacacional() {

    }
   
    public int getSabado() {
        return this.sabado;
    }

    public void setSabado(int sabado) {
        this.sabado = sabado;
    }
   
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
   
    public Date getFechaDesde() {
        return this.fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }
   
    public String getEmpleado() {
        return this.empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }
   
    public Date getFechaHasta() {
        return this.fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }
   
    public int getDomingo() {
        return this.domingo;
    }

    public void setDomingo(int domingo) {
        this.domingo = domingo;
    }
   
    public int getLunesViernes() {
        return this.lunesViernes;
    }

    public void setLunesViernes(int lunesViernes) {
        this.lunesViernes = lunesViernes;
    }
   
    public int getDiasRestantes() {
        return this.diasRestantes;
    }

    public void setDiasRestantes(int diasRestantes) {
        this.diasRestantes = diasRestantes;
    }
   
    public Periodo getPeriodo() {
        return this.periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }
}
