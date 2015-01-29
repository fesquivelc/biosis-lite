/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.juvitec.biosislite;

import com.personal.utiles.FormularioUtil;
import controladores.DepartamentoControlador;
import controladores.EmpleadoControlador;
import controladores.MarcacionControlador;
import entidades.Departamento;
import entidades.Empleado;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RyuujiMD
 */
public class pruebaAnio {

    public static void main(String[] args) {
        String url = FormularioUtil.chooserFichero(null, "holi");
        File file = new File(url);
        if(file.exists()){
            System.out.println("CHEVERE");
        }else{
            try {
                file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(pruebaAnio.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("NO TAN CHEVERE");
        }
        System.exit(0);
    }

    public static String conCeros(int dni) {
        if (dni <= 9) {
            return "0000000" + dni;
        } else if (dni <= 99) {
            return "000000" + dni;
        } else if (dni <= 999) {
            return "00000" + dni;
        } else {
            return "0000" + dni;
        }
    }
}
