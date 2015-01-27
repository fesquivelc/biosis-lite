package entidades;

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
public class Rol implements Serializable {

    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Basic
    private String nombre;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Id
    private String codigo;
    @OneToMany(cascade={CascadeType.ALL},targetEntity = RolAcceso.class,mappedBy = "rol")
    private List<RolAcceso> rolAccesoList;
    @OneToMany(fetch = FetchType.LAZY,targetEntity = Usuario.class,mappedBy = "rol")
    private List<Usuario> usuarioList;

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
   
    public List<RolAcceso> getRolAccesoList() {
        return this.rolAccesoList;
    }

    public void setRolAccesoList(List<RolAcceso> rolAccesoList) {
        this.rolAccesoList = rolAccesoList;
    }
   
    public List<Usuario> getUsuarioList() {
        return this.usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }
}
