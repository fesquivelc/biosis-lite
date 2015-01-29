package entidades;

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
@Table(name = "registro_asistencia")
public class RegistroAsistencia implements Serializable {

    @Column(name = "minutos_compensados", unique = false, updatable = true, insertable = true, nullable = true, length = 255, scale = 0, precision = 0)
    @Basic
    private BigDecimal minCompensados;
    @Column(name = "minutos_tardanza", unique = false, updatable = true, insertable = true, nullable = true, length = 255, scale = 0, precision = 0)
    @Basic
    private BigDecimal minTardanza;
    @ManyToOne(optional = true, targetEntity = Horario.class)
    @JoinColumn(name = "horario_codigo", referencedColumnName = "codigo", insertable = true, nullable = true, unique = false, updatable = true)
    private Horario horario;
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, targetEntity = DetalleRegistroAsistencia.class, mappedBy = "registroAsistencia")
    private List<DetalleRegistroAsistencia> detalleRegistroAsistenciaList;
    @Column(unique = false, updatable = true, insertable = true, nullable = false, length = 255, scale = 0, precision = 0)
    @Temporal(TemporalType.DATE)
    @Basic
    private Date fecha;
    @Column(name = "empleado_nro_documento", unique = false, updatable = true, insertable = true, nullable = true, length = 255, scale = 0, precision = 0)
    @Basic
    private String empleado;
    @Column(name = "tipo_asistencia", unique = false, updatable = true, insertable = true, nullable = false, length = 255, scale = 0, precision = 0)
    @Basic
    private char tipoAsistencia;
    @Column(unique = false, updatable = true, insertable = true, nullable = false, length = 255, scale = 0, precision = 0)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = true, targetEntity = Permiso.class)
    private Permiso permiso;
    @ManyToOne(optional = true, targetEntity = Feriado.class)
    @JoinColumn(name = "feriado_fecha_inicio", referencedColumnName = "fecha_inicio", insertable = true, nullable = true, unique = false, updatable = true)
    private Feriado feriado;
    @Column(unique = false, updatable = true, insertable = true, nullable = true, length = 255, scale = 0, precision = 0)
    @Basic
    private boolean compensacion;
    @Column(name = "minutos_trabajados", unique = false, updatable = true, insertable = true, nullable = true, length = 255, scale = 0, precision = 0)
    @Basic
    private BigDecimal minTrabajados;
    @Column(name = "minutos_extra", unique = false, updatable = true, insertable = true, nullable = true, length = 255, scale = 0, precision = 0)
    @Basic
    private BigDecimal minExtra;
    @ManyToOne(optional = true, targetEntity = Vacacion.class)
    @JoinColumn(name = "vacacion_id", referencedColumnName = "id", insertable = true, nullable = true, unique = false, updatable = true)
    private Vacacion vacacion;

    public RegistroAsistencia() {

    }

    public BigDecimal getMinExtra() {
        return minExtra;
    }

    public void setMinExtra(BigDecimal minExtra) {
        this.minExtra = minExtra;
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
