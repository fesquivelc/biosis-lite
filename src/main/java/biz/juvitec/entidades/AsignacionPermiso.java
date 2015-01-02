package biz.juvitec.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class AsignacionPermiso implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional=false,targetEntity = Permiso.class)
    @JoinColumn(name="permiso_id",referencedColumnName="id",nullable=false)
    private Permiso permiso;
    @Column(name="empleado_nro_documento",nullable=false)
    @Basic
    private String empleado;

    public AsignacionPermiso() {

    }
   
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
   
    public Permiso getPermiso() {
        return this.permiso;
    }

    public void setPermiso(Permiso permiso) {
        this.permiso = permiso;
    }
   
    public String getEmpleado() {
        return this.empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }
}
