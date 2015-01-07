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
import javax.persistence.Table;

@Entity
@Table(name="grupo_horario")
public class GrupoHorario implements Serializable {

    @Column(nullable=false)
    @Basic
    private String nombre;
    @Column(length=45)
    @Id
    private String codigo;
    @OneToMany(fetch = FetchType.LAZY,targetEntity = AsignacionHorario.class,mappedBy = "grupoHorario")
    private List<AsignacionHorario> asignacionHorarioList;
    @OneToMany(cascade={CascadeType.ALL},fetch = FetchType.LAZY,targetEntity = DetalleGrupoHorario.class,mappedBy = "grupoHorario")
    private List<DetalleGrupoHorario> detalleGrupoHorarioList;

    public GrupoHorario() {

    }
   
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
   
    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
   
    public List<AsignacionHorario> getAsignacionHorarioList() {
        return this.asignacionHorarioList;
    }

    public void setAsignacionHorarioList(List<AsignacionHorario> asignacionHorarioList) {
        this.asignacionHorarioList = asignacionHorarioList;
    }
   
    public List<DetalleGrupoHorario> getDetalleGrupoHorarioList() {
        return this.detalleGrupoHorarioList;
    }

    public void setDetalleGrupoHorarioList(List<DetalleGrupoHorario> detalleGrupoHorarioList) {
        this.detalleGrupoHorarioList = detalleGrupoHorarioList;
    }
}
