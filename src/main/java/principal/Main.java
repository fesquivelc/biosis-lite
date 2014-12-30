/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import biz.juvitec.vistas.Principal;
import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 *
 * @author fesquivelc
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }
        Principal principal = new Principal();
        principal.setVisible(true);
        principal.setExtendedState(principal.getExtendedState() | JFrame.MAXIMIZED_BOTH);
    }

}
