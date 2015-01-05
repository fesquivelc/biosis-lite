package biz.juvitec.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Periodo implements Serializable {

    @Basic
    private String nombre;
    @Column(length=4)
    @Id
    private int anio;
    @OneToMany(cascade={CascadeType.ALL},fetch = FetchType.LAZY,targetEntity = Feriado.class,mappedBy = "periodo")
    private List<Feriado> feriadoList;
    @OneToMany(fetch = FetchType.LAZY,targetEntity = SaldoVacacional.class,mappedBy = "periodo")
    private List<SaldoVacacional> saldoVacacionalList;
    @OneToMany(fetch = FetchType.LAZY,targetEntity = Vacacion.class,mappedBy = "periodo",orphanRemoval = true)
    private List<Vacacion> vacacionList;
    @Column(nullable=false)
    @Basic
    private boolean vigente;

    public Periodo() {

    }
   
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
   
    public int getAnio() {
        return this.anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }
   
    public List<Feriado> getFeriadoList() {
        return this.feriadoList;
    }

    public void setFeriadoList(List<Feriado> feriadoList) {
        this.feriadoList = feriadoList;
    }
   
    public List<SaldoVacacional> getSaldoVacacionalList() {
        return this.saldoVacacionalList;
    }

    public void setSaldoVacacionalList(List<SaldoVacacional> saldoVacacionalList) {
        this.saldoVacacionalList = saldoVacacionalList;
    }
   
    public List<Vacacion> getVacacionList() {
        return this.vacacionList;
    }

    public void setVacacionList(List<Vacacion> vacacionList) {
        this.vacacionList = vacacionList;
    }
    
    public boolean isVigente() {
        return this.vigente;
    }

    public void setVigente(boolean vigente) {
        this.vigente = vigente;
    }
}
