package biz.juvitec.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="registro_asistencia")
public class RegistroAsistencia implements Serializable {

    @Column(name="minutos_compensados")
    @Basic
    private BigDecimal minCompensados;
    @Column(name="minutos_tardanza")
    @Basic
    private BigDecimal minTardanza;
    @ManyToOne(targetEntity = Horario.class)
    @JoinColumn(name="horario_codigo",referencedColumnName="codigo")
    private Horario horario;
    @OneToMany(cascade={CascadeType.ALL},fetch = FetchType.LAZY,targetEntity = DetalleRegistroAsistencia.class,mappedBy = "registroAsistencia")
    private List<DetalleRegistroAsistencia> detalleRegistroAsistenciaList;
    @Column(nullable=false)
    @Temporal(TemporalType.DATE)
    @Basic
    private Date fecha;
    @Column(name="empleado_nro_documento")
    @Basic
    private String empleado;
    @Column(name="tipo_asistencia",nullable=false)
    @Basic
    private char tipoAsistencia;
    @Column(nullable=false)
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(targetEntity = Permiso.class)
    private Permiso permiso;
    @ManyToOne(targetEntity = Feriado.class)
    @JoinColumn(name="feriado_fecha_inicio",referencedColumnName="fecha_inicio")
    private Feriado feriado;
    @Basic
    private boolean compensacion;
    @Column(name="minutos_trabajados")
    @Basic
    private BigDecimal minTrabajados;
    @ManyToOne(targetEntity = Vacacion.class)
    @JoinColumn(name="vacacion_id",referencedColumnName="id")
    private Vacacion vacacion;

    public RegistroAsistencia() {

    }
   
    public BigDecimal getMinCompensados() {
        return this.minCompensados;
    }

    public void setMinCompensados(BigDecimal minCompensados) {
        this.minCompensados = minCompensados;
    }
   
    public BigDecimal getMinTardanza() {
        return this.minTardanza;
    }

    public void setMinTardanza(BigDecimal minTardanza) {
        this.minTardanza = minTardanza;
    }
   
    public Horario getHorario() {
        return this.horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }
   
    public List<DetalleRegistroAsistencia> getDetalleRegistroAsistenciaList() {
        return this.detalleRegistroAsistenciaList;
    }

    public void setDetalleRegistroAsistenciaList(List<DetalleRegistroAsistencia> detalleRegistroAsistenciaList) {
        this.detalleRegistroAsistenciaList = detalleRegistroAsistenciaList;
    }
   
    public Date getFecha() {
        return this.fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
   
    public String getEmpleado() {
        return this.empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }
   
    public char getTipoAsistencia() {
        return this.tipoAsistencia;
    }

    public void setTipoAsistencia(char tipoAsistencia) {
        this.tipoAsistencia = tipoAsistencia;
    }
   
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
   
    public Permiso getPermiso() {
        return this.permiso;
    }

    public void setPermiso(Permiso permiso) {
        this.permiso = permiso;
    }
   
    public Feriado getFeriado() {
        return this.feriado;
    }

    public void setFeriado(Feriado feriado) {
        this.feriado = feriado;
    }
    
    public boolean isCompensacion() {
        return this.compensacion;
    }

    public void setCompensacion(boolean compensacion) {
        this.compensacion = compensacion;
    }
   
    public BigDecimal getMinTrabajados() {
        return this.minTrabajados;
    }

    public void setMinTrabajados(BigDecimal minTrabajados) {
        this.minTrabajados = minTrabajados;
    }
   
    public Vacacion getVacacion() {
        return this.vacacion;
    }

    public void setVacacion(Vacacion vacacion) {
        this.vacacion = vacacion;
    }
}
