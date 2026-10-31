package com.sergiojn.televisoresmvc.gui;

import util.Util;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.util.Properties;

public class TelevisoresControlador implements ActionListener, ListSelectionListener, WindowListener {

    private Ventana vista;
    private TelevisoresModelo modelo;
    private File ultimaRutaExportada;

    public TelevisoresControlador(Ventana vista, TelevisoresModelo modelo) {
        this.vista = vista;
        this.modelo = modelo;
        addActionListener(this);
        addListSelectionListener(this);
        addWindowListener(this);
        try {
            cargarDatosConfiguracion();
        } catch (IOException e) {
            ultimaRutaExportada = new File(System.getProperty("user.home"));
        }
    }
    private void addActionListener(ActionListener listener) {
        vista.nuevoBoton.addActionListener(listener);
        vista.exportarBoton.addActionListener(listener);
        vista.importarBoton.addActionListener(listener);
        vista.ledRadioButton.addActionListener(listener);
        vista.oledRadioButton.addActionListener(listener);
        vista.qledRadioButton.addActionListener(listener);
    }

    private void addListSelectionListener(ListSelectionListener listener) {
        vista.lista1.addListSelectionListener(listener);
    }

    private void addWindowListener(WindowListener listener) {
        vista.frame.addWindowListener(listener);
    }

    private void cargarDatosConfiguracion() throws IOException {
        Properties configuracion = new Properties();
        configuracion.load(new FileReader("televisores.conf"));
        ultimaRutaExportada = new File(configuracion.getProperty("Ultima ruta exportada"));
    }

    private void guardarDatosConfiguracion() throws IOException {
        Properties configuracion = new Properties();
        configuracion.setProperty("Ultima ruta exportada", ultimaRutaExportada.getAbsolutePath());
        configuracion.store(new PrintWriter("televisores.conf"), "Datos configuración televisores");
    }
    private void actualizarDatosConfiguracion(File ultimaRutaExportada){
        this.ultimaRutaExportada = ultimaRutaExportada;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        if (Util.mensajeConfirmacion("¿Quieres cerrar la ventana?", "Salir") == JOptionPane.YES_OPTION){
            try {
                guardarDatosConfiguracion();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            System.exit(0);
        }



    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }
}
