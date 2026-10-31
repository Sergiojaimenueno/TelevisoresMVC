package com.sergiojn.televisoresmvc;

import com.sergiojn.televisoresmvc.gui.TelevisoresControlador;
import com.sergiojn.televisoresmvc.gui.TelevisoresModelo;
import com.sergiojn.televisoresmvc.gui.Ventana;

public class Principal {
    public static void main(String[] args) {
        Ventana vista = new Ventana();
        TelevisoresModelo modelo = new TelevisoresModelo();
        TelevisoresControlador controlador = new TelevisoresControlador(vista, modelo);
    }
}