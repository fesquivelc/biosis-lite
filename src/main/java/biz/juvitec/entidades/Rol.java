package biz.juvitec.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Rol implements Serializable {

    @Basic
    private String nombre;
    @Id
    private String codigo;
    @OneToMany(fetch = FetchType.LAZY,targetEntity = Usuario.class,mappedBy = "rol")
    private List<Usuario> usuarioList;
    @ManyToMany(targetEntity = Acceso.class)
    private List<Acceso> accesoList;

    public Rol() {

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
   
    public List<Usuario> getUsuarioList() {
        return this.usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }
   
    public List<Acceso> getAccesoList() {
        return this.accesoList;
    }

    public void setAccesoList(List<Acceso> accesoList) {
        this.accesoList = accesoList;
    }
}
