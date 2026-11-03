package com.sergiojn.televisoresmvc.gui;

import com.sergiojn.televisoresmvc.base.Televisor;
import com.sergiojn.televisoresmvc.base.TelevisorLED;
import com.sergiojn.televisoresmvc.base.TelevisorOLED;
import com.sergiojn.televisoresmvc.base.TelevisorQLED;
import org.xml.sax.SAXException;
import util.Util;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
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
        vista.eliminarBoton.addActionListener(listener);

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
            case "Exportar":
                JFileChooser selectorFichero = Util.crearSelectorFichero(ultimaRutaExportada, "Archivos XML", "xml");
                int opt = selectorFichero.showSaveDialog(null);
                if (opt == JFileChooser.APPROVE_OPTION) {
                    try {
                        modelo.exportarXML(selectorFichero.getSelectedFile());
                        actualizarDatosConfiguracion(selectorFichero.getSelectedFile());
                    } catch (ParserConfigurationException | TransformerException ex) {
                        ex.printStackTrace();
                    }
                }
                break;

            case "Importar":
                JFileChooser selectorFichero2 = Util.crearSelectorFichero(ultimaRutaExportada, "Archivos XML", "xml");
                int opt2 = selectorFichero2.showOpenDialog(null);
                if (opt2 == JFileChooser.APPROVE_OPTION) {
                    try {
                        modelo.importarXML(selectorFichero2.getSelectedFile());
                    } catch (ParserConfigurationException | IOException | SAXException ex) {
                        ex.printStackTrace();
                    }
                    refrescar();
                }
                break;

            case "Eliminar":
                Televisor seleccionado = (Televisor) vista.lista1.getSelectedValue();
                if (seleccionado == null) {
                    Util.mensajeError("Selecciona un televisor de la lista");
                    break;
                }
                if (Util.mensajeConfirmacion("¿Seguro que quieres eliminarlo?", "Eliminar") == JOptionPane.YES_OPTION) {
                    modelo.eliminarTelevisor(seleccionado);
                    limpiarCampos();
                    refrescar();
                }
                break;

            case "LED":
                vista.datoExtraLabel.setText("Consumo (W)");
                break;
            case "OLED":
                vista.datoExtraLabel.setText("Años garantía");
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
        if (e.getValueIsAdjusting()) {
            Televisor seleccionado = (Televisor) vista.lista1.getSelectedValue();
            vista.numSerieTxt.setText(seleccionado.getNumSerie());
            vista.precioTxt.setText(String.valueOf(seleccionado.getPrecio()));
            vista.marcaTxt.setText(seleccionado.getMarca());
            vista.modeloTxt.setText(seleccionado.getModelo());
            vista.pulgadasTxt.setText(String.valueOf(seleccionado.getPulgadas()));
            vista.fechaFabricacionDPicker.setDate(seleccionado.getFechaFabricacion());
            if (seleccionado instanceof TelevisorLED) {
                vista.ledRadioButton.doClick();
                vista.datoExtraTxt.setText(String.valueOf(((TelevisorLED) seleccionado).getConsumoVatios()));
            } else if (seleccionado instanceof TelevisorOLED) {
                vista.oledRadioButton.doClick();
                vista.datoExtraTxt.setText(String.valueOf(((TelevisorOLED) seleccionado).getAniosGarantia()));
            } else {
                vista.qledRadioButton.doClick();
                vista.datoExtraTxt.setText(String.valueOf(((TelevisorQLED) seleccionado).getHorasVidaUtil()));
            }
        }
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
