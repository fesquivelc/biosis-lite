package biz.juvitec.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn)
public class Acceso implements Serializable {

    @Basic
    private String nombre;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @OneToMany(fetch = FetchType.LAZY,targetEntity = RolAcceso.class,mappedBy = "acceso")
    private List<RolAcceso> rolAccesoList;
    @Basic
    private String clase;

    public Acceso() {

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
   
    public List<RolAcceso> getRolAccesoList() {
        return this.rolAccesoList;
    }

    public void setRolAccesoList(List<RolAcceso> rolAccesoList) {
        this.rolAccesoList = rolAccesoList;
    }
   
    public String getClase() {
        return this.clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }
}
