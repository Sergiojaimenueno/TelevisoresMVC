package com.sergiojn.televisoresmvc;

import com.sergiojn.televisoresmvc.gui.TelevisoresControlador;
import com.sergiojn.televisoresmvc.gui.TelevisoresModelo;
import com.sergiojn.televisoresmvc.gui.Ventana;

import javax.swing.*;

/**
 * Clase principal que arranca la aplicación de gestión de televisores.
 * @author Sergio
 */
public class Principal {
    /**
     * Método de entrada: aplica el aspecto Nimbus y crea la vista, el modelo y el controlador.
     * @param args argumentos de la línea de comandos (no se usan)
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Ventana vista = new Ventana();
        TelevisoresModelo modelo = new TelevisoresModelo();
        TelevisoresControlador controlador = new TelevisoresControlador(vista, modelo);
    }
}