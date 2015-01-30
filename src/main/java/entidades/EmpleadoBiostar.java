package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TB_USER")
public class EmpleadoBiostar implements Serializable {

    @Column(name="sUserName")
    @Basic
    private String nombre;
    @Column(name="sUserID")
    @Id
    private Integer id;
    @ManyToOne(targetEntity = Departamento.class)
    @JoinColumn(name="nDepartmentIdn",referencedColumnName="nDepartmentIdn")
    private Departamento departamento;

    public EmpleadoBiostar() {

    }
   
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
   
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
   
    public Departamento getDepartamento() {
        return this.departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
}
