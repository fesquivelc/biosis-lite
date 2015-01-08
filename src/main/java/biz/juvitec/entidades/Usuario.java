package biz.juvitec.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Usuario implements Serializable {

    @Column(name="empleado_nro_documento",nullable=false)
    @Basic
    private String empleado;
    @ManyToOne(optional=false,targetEntity = Rol.class)
    @JoinColumn(name="rol_codigo",referencedColumnName="codigo",nullable=false)
    private Rol rol;
    @Column(nullable=false,length=45)
    @Id
    private String login;
    @Basic
    private String password;
    @Column(nullable=false)
    @Basic
    private boolean activo;

    public Usuario() {

    }
   
    public String getEmpleado() {
        return this.empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }
   
    public Rol getRol() {
        return this.rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
   
    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
   
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean isActivo() {
        return this.activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
