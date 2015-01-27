/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.juvitec.biosislite;

import java.util.Calendar;

/**
 *
 * @author RyuujiMD
 */
public class pruebaAnio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        for(int i = 1; i <= 2000; i++){
            System.out.println("\""+conCeros(i)+"\"");
        }
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
