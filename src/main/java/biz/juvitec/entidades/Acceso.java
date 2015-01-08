package biz.juvitec.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Acceso implements Serializable {

    @Basic
    private String nombre;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @ManyToMany(fetch = FetchType.LAZY,targetEntity = Rol.class,mappedBy = "accesoList")
    private List<Rol> rolList;
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
   
    public List<Rol> getRolList() {
        return this.rolList;
    }

    public void setRolList(List<Rol> rolList) {
        this.rolList = rolList;
    }
   
    public String getClase() {
        return this.clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }
}
