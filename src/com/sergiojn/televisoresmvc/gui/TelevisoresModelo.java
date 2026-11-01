package com.sergiojn.televisoresmvc.gui;

import com.sergiojn.televisoresmvc.base.Televisor;
import com.sergiojn.televisoresmvc.base.TelevisorLED;
import com.sergiojn.televisoresmvc.base.TelevisorOLED;
import com.sergiojn.televisoresmvc.base.TelevisorQLED;

import java.time.LocalDate;
import java.util.ArrayList;

public class TelevisoresModelo {

    private ArrayList<Televisor> listaTelevisores = new ArrayList<>();

    public ArrayList<Televisor> obtenerListaTelevisores(){
        return listaTelevisores;
    }
    public void altaLED(String numSerie, double precio, String marca, String modelo,
                        int pulgadas, LocalDate fechaFabricacion, int consumoVatios) {
        TelevisorLED nuevoLED = new TelevisorLED(numSerie, precio, marca, modelo, pulgadas, fechaFabricacion, consumoVatios);
        listaTelevisores.add(nuevoLED);
    }
    public void altaOLED(String numSerie, double precio, String marca, String modelo,
                         int pulgadas, LocalDate fechaFabricacion, int aniosGarantiaPantalla) {
        TelevisorOLED nuevoOLED = new TelevisorOLED(numSerie, precio, marca, modelo, pulgadas, fechaFabricacion, aniosGarantiaPantalla);
        listaTelevisores.add(nuevoOLED);
    }

    public void altaQLED(String numSerie, double precio, String marca, String modelo,
                         int pulgadas, LocalDate fechaFabricacion, int horasVidaUtil) {
        TelevisorQLED nuevoQLED = new TelevisorQLED(numSerie, precio, marca, modelo, pulgadas, fechaFabricacion, horasVidaUtil);
        listaTelevisores.add(nuevoQLED);
    }

    public boolean existeNumSerie (String numSerie){
        for (Televisor unTelevisor : listaTelevisores){
            if (unTelevisor.getNumSerie().equals(numSerie)){
                return true;
            }

        }
        return false;
    }

}
