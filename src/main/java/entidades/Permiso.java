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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Permiso implements Serializable {

    @Column(name="cubre_salida",unique=false,updatable=true,insertable=true,nullable=false,length=255,scale=0,precision=0)
    @Basic
    private boolean cubreSalida;
    @Column(name="diferencia_minutos",unique=false,updatable=true,insertable=true,nullable=false,length=255,scale=0,precision=0)
    @Basic
    private BigDecimal diferencia;
    @OneToMany(fetch = FetchType.LAZY,targetEntity = DetalleRegistroAsistencia.class,mappedBy = "permiso")
    private List<DetalleRegistroAsistencia> detalleRegistroAsistenciaList;
    @Column(name="hora_inicio",unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Temporal(TemporalType.TIME)
    @Basic
    private Date horaInicio;
    @Column(name="a_compensar",unique=false,updatable=true,insertable=true,nullable=false,length=255,scale=0,precision=0)
    @Basic
    private boolean aCompensar;
    @Column(name="por_fecha",unique=false,updatable=true,insertable=true,nullable=false,length=255,scale=0,precision=0)
    @Basic
    private boolean porFecha;
    @Column(name="fecha_fin",unique=false,updatable=true,insertable=true,nullable=false,length=255,scale=0,precision=0)
    @Temporal(TemporalType.DATE)
    @Basic
    private Date fechaFin;
    @Column(unique=false,updatable=true,insertable=true,nullable=false,length=255,scale=0,precision=0)
    @Basic
    private String documento;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional=false,targetEntity = TipoPermiso.class, fetch = FetchType.EAGER)
    @JoinColumn(name="tipo_permiso_codigo",referencedColumnName="codigo",insertable=true,nullable=false,unique=false,updatable=true)
    private TipoPermiso tipoPermiso;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Basic
    private String motivo;
    @Column(name="hora_fin",unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Temporal(TemporalType.TIME)
    @Basic
    private Date horaFin;
    @Column(name="cubre_entrada",unique=false,updatable=true,insertable=true,nullable=false,length=255,scale=0,precision=0)
    @Basic
    private boolean cubreEntrada;
    @OneToMany(cascade={CascadeType.ALL},fetch = FetchType.EAGER,targetEntity = AsignacionPermiso.class,mappedBy = "permiso",orphanRemoval = true)
    private List<AsignacionPermiso> asignacionPermisoList;
    @Column(name="fecha_inicio",unique=false,updatable=true,insertable=true,nullable=false,length=255,scale=0,precision=0)
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
   
    public BigDecimal getDiferencia() {
        return this.diferencia;
    }

    public void setDiferencia(BigDecimal diferencia) {
        this.diferencia = diferencia;
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
   
    public TipoPermiso getTipoPermiso() {
        return this.tipoPermiso;
    }

    public void setTipoPermiso(TipoPermiso tipoPermiso) {
        this.tipoPermiso = tipoPermiso;
    }
   
    public String getMotivo() {
        return this.motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
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
