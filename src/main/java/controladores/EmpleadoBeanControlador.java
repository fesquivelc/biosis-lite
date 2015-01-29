/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import dao.DAOMINEDU;
import entidades.EmpleadoBean;

/**
 *
 * @author fesquivelc
 */
public class EmpleadoBeanControlador extends Controlador<EmpleadoBean> {

    public EmpleadoBeanControlador() {
        super(EmpleadoBean.class, new DAOMINEDU(EmpleadoBean.class));
    }


}
