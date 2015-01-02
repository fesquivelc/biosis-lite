package biz.juvitec.entidades;

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

    @Column(nullable=false)
    @Basic
    private String nombre;
    @Column(nullable=false)
    @Temporal(TemporalType.TIME)
    @Basic
    private Date refrigerioHE;
    @Column(nullable=false,length=45)
    @Id
    private String codigo;
    @OneToMany(fetch = FetchType.LAZY,targetEntity = Horario.class,mappedBy = "jornada")
    private List<Horario> horarioList;
    @Column(name="desde_marcacion_he")
    @Temporal(TemporalType.TIME)
    @Basic
    private Date desdeHE;
    @Column(name="tardanza_turno_he",nullable=false)
    @Temporal(TemporalType.TIME)
    @Basic
    private Date tardanzaHE;
    @Column(name="tolerancia_turno_he",nullable=false)
    @Temporal(TemporalType.TIME)
    @Basic
    private Date toleranciaHE;
    @Column(name="refrigerio_hs",nullable=false)
    @Temporal(TemporalType.TIME)
    @Basic
    private Date refrigerioHS;
    @Column(name="turno_he",nullable=false)
    @Temporal(TemporalType.TIME)
    @Basic
    private Date turnoHE;
    @Column(name="tolerancia_refrigerio_he",nullable=false)
    @Temporal(TemporalType.TIME)
    @Basic
    private Date toleranciaRefrigerioHE;
    @Column(name="turno_hs",nullable=false)
    @Temporal(TemporalType.TIME)
    @Basic
    private Date turnoHS;

    public Jornada() {

    }
   
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
   
    public Date getRefrigerioHE() {
        return this.refrigerioHE;
    }

    public void setRefrigerioHE(Date refrigerioHE) {
        this.refrigerioHE = refrigerioHE;
    }
   
    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
   
    public List<Horario> getHorarioList() {
        return this.horarioList;
    }

    public void setHorarioList(List<Horario> horarioList) {
        this.horarioList = horarioList;
    }
   
    public Date getDesdeHE() {
        return this.desdeHE;
    }

    public void setDesdeHE(Date desdeHE) {
        this.desdeHE = desdeHE;
    }
   
    public Date getTardanzaHE() {
        return this.tardanzaHE;
    }

    public void setTardanzaHE(Date tardanzaHE) {
        this.tardanzaHE = tardanzaHE;
    }
   
    public Date getToleranciaHE() {
        return this.toleranciaHE;
    }

    public void setToleranciaHE(Date toleranciaHE) {
        this.toleranciaHE = toleranciaHE;
    }
   
    public Date getRefrigerioHS() {
        return this.refrigerioHS;
    }

    public void setRefrigerioHS(Date refrigerioHS) {
        this.refrigerioHS = refrigerioHS;
    }
   
    public Date getTurnoHE() {
        return this.turnoHE;
    }

    public void setTurnoHE(Date turnoHE) {
        this.turnoHE = turnoHE;
    }
   
    public Date getToleranciaRefrigerioHE() {
        return this.toleranciaRefrigerioHE;
    }

    public void setToleranciaRefrigerioHE(Date toleranciaRefrigerioHE) {
        this.toleranciaRefrigerioHE = toleranciaRefrigerioHE;
    }
   
    public Date getTurnoHS() {
        return this.turnoHS;
    }

    public void setTurnoHS(Date turnoHS) {
        this.turnoHS = turnoHS;
    }
}
