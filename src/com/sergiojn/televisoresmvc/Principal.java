package com.sergiojn.televisoresmvc;

import com.sergiojn.televisoresmvc.gui.TelevisoresControlador;
import com.sergiojn.televisoresmvc.gui.TelevisoresModelo;
import com.sergiojn.televisoresmvc.gui.Ventana;

import javax.swing.*;

public class Principal {
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