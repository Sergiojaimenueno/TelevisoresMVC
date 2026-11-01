package com.sergiojn.televisoresmvc.gui;

import com.sergiojn.televisoresmvc.base.Televisor;
import util.Util;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.time.LocalDate;
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
        String actionCommand = e.getActionCommand();

        switch (actionCommand) {
            case "Nuevo":
                if (hayCamposVacios()) {
                    Util.mensajeError("Rellena todos los campos");
                    break;
                }
                if (modelo.existeNumSerie(vista.numSerieTxt.getText())) {
                    Util.mensajeError("Ya existe un televisor con ese número de serie");
                    break;
                }
                try {
                    String numSerie = vista.numSerieTxt.getText();
                    double precio = Double.parseDouble(vista.precioTxt.getText());
                    String marca = vista.marcaTxt.getText();
                    String modeloTv = vista.modeloTxt.getText();
                    int pulgadas = Integer.parseInt(vista.pulgadasTxt.getText());
                    LocalDate fecha = vista.fechaFabricacionDPicker.getDate();
                    int extra = Integer.parseInt(vista.datoExtraTxt.getText());

                    if (vista.ledRadioButton.isSelected()) {
                        modelo.altaLED(numSerie, precio, marca, modeloTv, pulgadas, fecha, extra);
                    } else if (vista.oledRadioButton.isSelected()) {
                        modelo.altaOLED(numSerie, precio, marca, modeloTv, pulgadas, fecha, extra);
                    } else if (vista.qledRadioButton.isSelected()) {
                        modelo.altaQLED(numSerie, precio, marca, modeloTv, pulgadas, fecha, extra);
                    } else {
                        Util.mensajeError("Selecciona un tipo de televisor");
                        break;
                    }
                    limpiarCampos();
                    refrescar();
                } catch (NumberFormatException ex) {
                    Util.mensajeError("Precio, pulgadas y el último dato tienen que ser números");
                }
                break;
            case "LED":
                vista.datoExtraLabel.setText("Consumo (W)");
                break;
            case "OLED":
                vista.datoExtraLabel.setText("Años garantía pantalla");
                break;
            case "QLED":
                vista.datoExtraLabel.setText("Horas vida útil");
                break;
        }
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
    private boolean hayCamposVacios() {
        if (vista.numSerieTxt.getText().isEmpty() ||
                vista.precioTxt.getText().isEmpty() ||
                vista.marcaTxt.getText().isEmpty() ||
                vista.modeloTxt.getText().isEmpty() ||
                vista.pulgadasTxt.getText().isEmpty() ||
                vista.fechaFabricacionDPicker.getText().isEmpty() ||
                vista.datoExtraTxt.getText().isEmpty()) {
            return true;
        }
        return false;
    }

    private void limpiarCampos() {
        vista.numSerieTxt.setText(null);
        vista.precioTxt.setText(null);
        vista.marcaTxt.setText(null);
        vista.modeloTxt.setText(null);
        vista.pulgadasTxt.setText(null);
        vista.fechaFabricacionDPicker.setText(null);
        vista.datoExtraTxt.setText(null);
        vista.numSerieTxt.requestFocus();
    }

    private void refrescar() {
        vista.dlmTelevisor.clear();
        for (Televisor unTelevisor : modelo.obtenerListaTelevisores()) {
            vista.dlmTelevisor.addElement(unTelevisor);
        }
    }
}
