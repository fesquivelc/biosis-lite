package biz.juvitec.entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="rol_acceso")
public class RolAcceso implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(targetEntity = Acceso.class)
    private Acceso acceso;
    @ManyToOne(targetEntity = Rol.class)
    private Rol rol;

    public RolAcceso() {

    }
   
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
   
    public Acceso getAcceso() {
        return this.acceso;
    }

    public void setAcceso(Acceso acceso) {
        this.acceso = acceso;
    }
   
    public Rol getRol() {
        return this.rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
