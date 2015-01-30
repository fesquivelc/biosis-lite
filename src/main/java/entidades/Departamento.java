package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="TB_USER_DEPT")
public class Departamento implements Serializable {

    @Column(name="sName")
    @Basic
    private String nombre;
    @Column(name="nDepartmentIdn")
    @Id
    private Long id;
    @OneToMany(fetch = FetchType.LAZY,targetEntity = EmpleadoBiostar.class,mappedBy = "departamento")
    private List<EmpleadoBiostar> empleadoList;

    public Departamento() {

    }
   
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
   
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
   
    public List<EmpleadoBiostar> getEmpleadoList() {
        return this.empleadoList;
    }

    public void setEmpleadoList(List<EmpleadoBiostar> empleadoList) {
        this.empleadoList = empleadoList;
    }
}
