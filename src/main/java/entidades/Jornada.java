package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Jornada implements Serializable {

    @OneToMany(fetch = FetchType.LAZY,targetEntity = Horario.class,mappedBy = "jornada")
    private List<Horario> horarioList;
    @Column(name="tardanza_turno_he",unique=false,updatable=true,insertable=true,nullable=false,length=255,scale=0,precision=0)
    @Temporal(TemporalType.TIME)
    @Basic
    private Date tardanzaHE;
    @Column(name="minutos_refrigerio",unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Basic
    private int minRefrigerio;
    @Column(name="refrigerio_hs",unique=false,updatable=true,insertable=true,nullable=false,length=255,scale=0,precision=0)
    @Temporal(TemporalType.TIME)
    @Basic
    private Date refrigerioHS;
    @Column(name="refrigerio_especial",unique=false,updatable=true,insertable=true,nullable=false,length=255,scale=0,precision=0)
    @Basic
    private boolean refrigerioEspecial;
    @Column(name="turno_he",unique=false,updatable=true,insertable=true,nullable=false,length=255,scale=0,precision=0)
    @Temporal(TemporalType.TIME)
    @Basic
    private Date turnoHE;
    @Column(unique=false,updatable=true,insertable=true,nullable=false,length=45,scale=0,precision=0)
    @Id
    private String codigo;
    @Column(unique=false,updatable=true,insertable=true,nullable=false,length=255,scale=0,precision=0)
    @Temporal(TemporalType.TIME)
    @Basic
    private Date refrigerioHE;
    @Column(unique=false,updatable=true,insertable=true,nullable=false,length=255,scale=0,precision=0)
    @Basic
    private String nombre;
    @Column(name="desde_marcacion_he",unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Temporal(TemporalType.TIME)
    @Basic
    private Date desdeHE;
    @Column(name="tolerancia_turno_he",unique=false,updatable=true,insertable=true,nullable=false,length=255,scale=0,precision=0)
    @Temporal(TemporalType.TIME)
    @Basic
    private Date toleranciaHE;
    @Column(name="turno_hs",unique=false,updatable=true,insertable=true,nullable=false,length=255,scale=0,precision=0)
    @Temporal(TemporalType.TIME)
    @Basic
    private Date turnoHS;
    @Column(name="tolerancia_refrigerio_he",unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Temporal(TemporalType.TIME)
    @Basic
    private Date toleranciaRefrigerioHE;

    public Jornada() {

    }
   
    public List<Horario> getHorarioList() {
        return this.horarioList;
    }

    public void setHorarioList(List<Horario> horarioList) {
        this.horarioList = horarioList;
    }
   
    public Date getTardanzaHE() {
        return this.tardanzaHE;
    }

    public void setTardanzaHE(Date tardanzaHE) {
        this.tardanzaHE = tardanzaHE;
    }
   
    public int getMinRefrigerio() {
        return this.minRefrigerio;
    }

    public void setMinRefrigerio(int minRefrigerio) {
        this.minRefrigerio = minRefrigerio;
    }
   
    public Date getRefrigerioHS() {
        return this.refrigerioHS;
    }

    public void setRefrigerioHS(Date refrigerioHS) {
        this.refrigerioHS = refrigerioHS;
    }
    
    public boolean isRefrigerioEspecial() {
        return this.refrigerioEspecial;
    }

    public void setRefrigerioEspecial(boolean refrigerioEspecial) {
        this.refrigerioEspecial = refrigerioEspecial;
    }
   
    public Date getTurnoHE() {
        return this.turnoHE;
    }

    public void setTurnoHE(Date turnoHE) {
        this.turnoHE = turnoHE;
    }
   
    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
   
    public Date getRefrigerioHE() {
        return this.refrigerioHE;
    }

    public void setRefrigerioHE(Date refrigerioHE) {
        this.refrigerioHE = refrigerioHE;
    }
   
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
   
    public Date getDesdeHE() {
        return this.desdeHE;
    }

    public void setDesdeHE(Date desdeHE) {
        this.desdeHE = desdeHE;
    }
   
    public Date getToleranciaHE() {
        return this.toleranciaHE;
    }

    public void setToleranciaHE(Date toleranciaHE) {
        this.toleranciaHE = toleranciaHE;
    }
   
    public Date getTurnoHS() {
        return this.turnoHS;
    }

    public void setTurnoHS(Date turnoHS) {
        this.turnoHS = turnoHS;
    }
   
    public Date getToleranciaRefrigerioHE() {
        return this.toleranciaRefrigerioHE;
    }

    public void setToleranciaRefrigerioHE(Date toleranciaRefrigerioHE) {
        this.toleranciaRefrigerioHE = toleranciaRefrigerioHE;
    }
}
