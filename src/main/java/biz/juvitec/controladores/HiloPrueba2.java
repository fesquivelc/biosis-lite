/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.juvitec.controladores;

import biz.juvitec.vistas.Principal;

/**
 *
 * @author fesquivelc
 */
public class HiloPrueba2 extends Thread{

    @Override
    public void run() {
        Principal principal = new Principal();
        System.out.println("TERMINADO PRINCIPAL");
        principal.setVisible(true);
        
    }
    
}
