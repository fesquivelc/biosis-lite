/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.reportes.beans;

import java.util.Date;

/**
 *
 * @author fesquivelc
 */
public class RTardanzaFaltaBean {
    private String codigoModular;
    
    private String nombre;

    /**
     * Get the value of nombre
     *
     * @return the value of nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Set the value of nombre
     *
     * @param nombre new value of nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    private String documentoIdentidad;
    private Date horaMarcacion;

    public String getCodigoModular() {
        return codigoModular;
    }

    public void setCodigoModular(String codigoModular) {
        this.codigoModular = codigoModular;
    }

    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public void setDocumentoIdentidad(String documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }

    public Date getHoraMarcacion() {
        return horaMarcacion;
    }

    public void setHoraMarcacion(Date horaMarcacion) {
        this.horaMarcacion = horaMarcacion;
    }
    
    
}
