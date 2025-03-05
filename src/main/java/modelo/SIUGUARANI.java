/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package modelo;


import javax.swing.SwingUtilities;
import vista.index;
import vista.Main;

/**
 *
 * @author cativ
 */
public class SIUGUARANI {

    public static void main(String[] args) {
        // Ejecutar el sistema
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

}
