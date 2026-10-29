package com.sergiojn.televisoresmvc.gui;

import com.github.lgooddatepicker.components.DatePicker;
import com.sergiojn.televisoresmvc.base.Televisor;

import javax.swing.*;

public class Ventana {
    private JPanel panel1;
    public JRadioButton ledRadioButton;
    public JRadioButton oledRadioButton;
    public JRadioButton qledRadioButton;
    public JTextField numSerieTxt;
    public JTextField marcaTxt;
    public JTextField modeloTxt;
    public JTextField pulgadasTxt;
    public DatePicker fechaFabricacionDPicker;
    public JLabel datoExtraLabel;
    public JTextField datoExtraTxt;
    public JTextField precioTxt;
    public JButton nuevoBoton;
    public JButton exportarBoton;
    public JButton importarBoton;
    public JList lista1;

    public JFrame frame;
    public DefaultListModel<Televisor> dlmTelevisor;

    public Ventana() {
        frame = new JFrame("Gestor de Televisores");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        initComponents();
    }

    private void initComponents() {
        dlmTelevisor = new DefaultListModel<>();
        lista1.setModel(dlmTelevisor);
    }
}