/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.juvitec.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author fesquivelc
 */
@Entity
@Table(name = "tc_sistema")
public class TCSistema implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String sistema;
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_cero")
    private Date fechaCero;
    @Temporal(TemporalType.TIME)
    @Column(name = "hora_cero")
    private Date horaCero;

    public String getSistema() {
        return sistema;
    }

    public void setSistema(String sistema) {
        this.sistema = sistema;
    }

    public Date getFechaCero() {
        return fechaCero;
    }

    public void setFechaCero(Date fechaCero) {
        this.fechaCero = fechaCero;
    }

    public Date getHoraCero() {
        return horaCero;
    }

    public void setHoraCero(Date horaCero) {
        this.horaCero = horaCero;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sistema != null ? sistema.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TCSistema)) {
            return false;
        }
        TCSistema other = (TCSistema) object;
        if ((this.sistema == null && other.sistema != null) || (this.sistema != null && !this.sistema.equals(other.sistema))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "biz.juvitec.entidades.TCSistema[ id=" + sistema + " ]";
    }
    
}
