package biz.juvitec.entidades;

import java.io.Serializable;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Permiso implements Serializable {

    @Column(name="cubre_salida",nullable=false)
    @Basic
    private boolean cubreSalida;
    @OneToMany(fetch = FetchType.LAZY,targetEntity = DetalleRegistroAsistencia.class,mappedBy = "permiso")
    private List<DetalleRegistroAsistencia> detalleRegistroAsistenciaList;
    @Column(name="hora_inicio")
    @Temporal(TemporalType.TIME)
    @Basic
    private Date horaInicio;
    @Column(name="a_compensar",nullable=false)
    @Basic
    private boolean aCompensar;
    @Column(name="por_fecha",nullable=false)
    @Basic
    private boolean porFecha;
    @Column(name="fecha_fin",nullable=false)
    @Temporal(TemporalType.DATE)
    @Basic
    private Date fechaFin;
    @Column(nullable=false)
    @Basic
    private String documento;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Basic
    private String motivo;
    @ManyToOne(optional=false,targetEntity = TipoPermiso.class)
    @JoinColumn(name="tipo_permiso_codigo",referencedColumnName="codigo",nullable=false)
    private TipoPermiso tipoPermiso;
    @Column(name="hora_fin")
    @Temporal(TemporalType.TIME)
    @Basic
    private Date horaFin;
    @Column(name="cubre_entrada",nullable=false)
    @Basic
    private boolean cubreEntrada;
    @OneToMany(cascade={CascadeType.ALL},fetch = FetchType.LAZY,targetEntity = AsignacionPermiso.class,mappedBy = "permiso")
    private List<AsignacionPermiso> asignacionPermisoList;
    @Column(name="fecha_inicio",nullable=false)
    @Temporal(TemporalType.DATE)
    @Basic
    private Date fechaInicio;

    public Permiso() {

    }
    
    public boolean isCubreSalida() {
        return this.cubreSalida;
    }

    public void setCubreSalida(boolean cubreSalida) {
        this.cubreSalida = cubreSalida;
    }
   
    public List<DetalleRegistroAsistencia> getDetalleRegistroAsistenciaList() {
        return this.detalleRegistroAsistenciaList;
    }

    public void setDetalleRegistroAsistenciaList(List<DetalleRegistroAsistencia> detalleRegistroAsistenciaList) {
        this.detalleRegistroAsistenciaList = detalleRegistroAsistenciaList;
    }
   
    public Date getHoraInicio() {
        return this.horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }
    
    public boolean isACompensar() {
        return this.aCompensar;
    }

    public void setACompensar(boolean aCompensar) {
        this.aCompensar = aCompensar;
    }
    
    public boolean isPorFecha() {
        return this.porFecha;
    }

    public void setPorFecha(boolean porFecha) {
        this.porFecha = porFecha;
    }
   
    public Date getFechaFin() {
        return this.fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
   
    public String getDocumento() {
        return this.documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }
   
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
   
    public String getMotivo() {
        return this.motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
   
    public TipoPermiso getTipoPermiso() {
        return this.tipoPermiso;
    }

    public void setTipoPermiso(TipoPermiso tipoPermiso) {
        this.tipoPermiso = tipoPermiso;
    }
   
    public Date getHoraFin() {
        return this.horaFin;
    }

    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }
    
    public boolean isCubreEntrada() {
        return this.cubreEntrada;
    }

    public void setCubreEntrada(boolean cubreEntrada) {
        this.cubreEntrada = cubreEntrada;
    }
   
    public List<AsignacionPermiso> getAsignacionPermisoList() {
        return this.asignacionPermisoList;
    }

    public void setAsignacionPermisoList(List<AsignacionPermiso> asignacionPermisoList) {
        this.asignacionPermisoList = asignacionPermisoList;
    }
   
    public Date getFechaInicio() {
        return this.fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
}
