package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "v_marcaciones")
public class Marcacion implements Serializable {

    @Column(name = "empleado_nombre")
    @Basic
    private String nombre;
    @Column(name = "equipo_ip", nullable = false)
    @Basic
    private String equipo;
    @Id
    private Long id;
    @Column(name = "fecha_hora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora;
    @Column(name = "empleado_nro_documento", nullable = false)
    @Basic
    private int empleado;

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Marcacion() {

    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEquipo() {
        return this.equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getEmpleado() {
        return this.empleado;
    }

    public void setEmpleado(int empleado) {
        this.empleado = empleado;
    }
}
