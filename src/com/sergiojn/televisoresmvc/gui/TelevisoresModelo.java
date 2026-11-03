package com.sergiojn.televisoresmvc.gui;

import com.sergiojn.televisoresmvc.base.Televisor;
import com.sergiojn.televisoresmvc.base.TelevisorLED;
import com.sergiojn.televisoresmvc.base.TelevisorOLED;
import com.sergiojn.televisoresmvc.base.TelevisorQLED;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Modelo de la aplicación: guarda la lista de televisores y la exporta o importa en XML.
 * @author Sergio
 */
public class TelevisoresModelo {

    private ArrayList<Televisor> listaTelevisores = new ArrayList<>();

    /**
     * Devuelve la lista de televisores.
     * @return la lista de televisores
     */
    public ArrayList<Televisor> obtenerListaTelevisores(){
        return listaTelevisores;
    }
    /**
     * Da de alta un televisor LED y lo añade a la lista.
     * @param numSerie número de serie
     * @param precio precio del tv
     * @param marca marca
     * @param modelo modelo
     * @param pulgadas tamaño en pulgadas
     * @param fechaFabricacion fecha de fabricación
     * @param consumoVatios consumo en vatios
     */
    public void altaLED(String numSerie, double precio, String marca, String modelo,
                        int pulgadas, LocalDate fechaFabricacion, int consumoVatios) {
        TelevisorLED nuevoLED = new TelevisorLED(numSerie, precio, marca, modelo, pulgadas, fechaFabricacion, consumoVatios);
        listaTelevisores.add(nuevoLED);
    }
    /**
     * Da de alta un televisor OLED y lo añade a la lista.
     * @param numSerie número de serie
     * @param precio precio en euros
     * @param marca marca
     * @param modelo modelo
     * @param pulgadas tamaño en pulgadas
     * @param fechaFabricacion fecha de fabricación
     * @param aniosGarantia años de garantía
     */
    public void altaOLED(String numSerie, double precio, String marca, String modelo,
                         int pulgadas, LocalDate fechaFabricacion, int aniosGarantia) {
        TelevisorOLED nuevoOLED = new TelevisorOLED(numSerie, precio, marca, modelo, pulgadas, fechaFabricacion, aniosGarantia);
        listaTelevisores.add(nuevoOLED);
    }

    /**
     * Da de alta un televisor QLED y lo añade a la lista.
     * @param numSerie número de serie
     * @param precio precio del televisor
     * @param marca marca
     * @param modelo modelo
     * @param pulgadas tamaño en pulgadas
     * @param fechaFabricacion fecha de fabricación
     * @param horasVidaUtil horas de vida útil
     */
    public void altaQLED(String numSerie, double precio, String marca, String modelo,
                         int pulgadas, LocalDate fechaFabricacion, int horasVidaUtil) {
        TelevisorQLED nuevoQLED = new TelevisorQLED(numSerie, precio, marca, modelo, pulgadas, fechaFabricacion, horasVidaUtil);
        listaTelevisores.add(nuevoQLED);
    }

    /**
     * Comprueba si ya existe un televisor con ese número de serie.
     * @param numSerie número de serie a buscar
     * @return true si ya existe, false si no
     */
    public boolean existeNumSerie (String numSerie){
        for (Televisor unTelevisor : listaTelevisores){
            if (unTelevisor.getNumSerie().equals(numSerie)){
                return true;
            }

        }
        return false;
    }

    /**
     * Elimina un televisor de la lista.
     * @param televisor televisor que se quiere eliminar
     */
    public void eliminarTelevisor(Televisor televisor) {
        listaTelevisores.remove(televisor);
    }

    /**
     * Guarda todos los televisores de la lista en un fichero XML usando DOM.
     * @param fichero fichero XML de destino
     * @throws ParserConfigurationException si no se puede crear el documento
     * @throws TransformerException si no se puede escribir el fichero
     */
    public void exportarXML(File fichero) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation dom = builder.getDOMImplementation();

        Document documento = dom.createDocument(null, "xml", null);
        Element raiz = documento.createElement("televisores");
        documento.getDocumentElement().appendChild(raiz);

        Element nodoTelevisor = null;
        Element nodoDatos = null;
        Text texto = null;

        for (Televisor unTelevisor : listaTelevisores) {
            if (unTelevisor instanceof TelevisorLED) {
                nodoTelevisor = documento.createElement("TelevisorLED");
            } else if (unTelevisor instanceof TelevisorOLED) {
                nodoTelevisor = documento.createElement("TelevisorOLED");
            } else {
                nodoTelevisor = documento.createElement("TelevisorQLED");
            }
            raiz.appendChild(nodoTelevisor);
            nodoDatos = documento.createElement("numserie");
            nodoTelevisor.appendChild(nodoDatos);
            texto = documento.createTextNode(unTelevisor.getNumSerie());
            nodoDatos.appendChild(texto);
            nodoDatos = documento.createElement("precio");
            nodoTelevisor.appendChild(nodoDatos);
            texto = documento.createTextNode(String.valueOf(unTelevisor.getPrecio()));
            nodoDatos.appendChild(texto);
            nodoDatos = documento.createElement("marca");
            nodoTelevisor.appendChild(nodoDatos);
            texto = documento.createTextNode(unTelevisor.getMarca());
            nodoDatos.appendChild(texto);
            nodoDatos = documento.createElement("modelo");
            nodoTelevisor.appendChild(nodoDatos);
            texto = documento.createTextNode(unTelevisor.getModelo());
            nodoDatos.appendChild(texto);
            nodoDatos = documento.createElement("pulgadas");
            nodoTelevisor.appendChild(nodoDatos);
            texto = documento.createTextNode(String.valueOf(unTelevisor.getPulgadas()));
            nodoDatos.appendChild(texto);
            nodoDatos = documento.createElement("fechafabricacion");
            nodoTelevisor.appendChild(nodoDatos);
            texto = documento.createTextNode(unTelevisor.getFechaFabricacion().toString());
            nodoDatos.appendChild(texto);
            if (unTelevisor instanceof TelevisorLED) {
                nodoDatos = documento.createElement("consumovatios");
                texto = documento.createTextNode(String.valueOf(((TelevisorLED) unTelevisor).getConsumoVatios()));
            } else if (unTelevisor instanceof TelevisorOLED) {
                nodoDatos = documento.createElement("aniosgarantia");
                texto = documento.createTextNode(String.valueOf(((TelevisorOLED) unTelevisor).getAniosGarantia()));
            } else {
                nodoDatos = documento.createElement("horasvidautil");
                texto = documento.createTextNode(String.valueOf(((TelevisorQLED) unTelevisor).getHorasVidaUtil()));
            }
            nodoTelevisor.appendChild(nodoDatos);
            nodoDatos.appendChild(texto);
        }
        Source source = new DOMSource(documento);
        Result resultado = new StreamResult(fichero);
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(source, resultado);
    }
    /**
     * Lee un fichero XML y carga sus televisores en la lista (borra los anteriores).
     * @param fichero fichero XML que se lee
     * @throws ParserConfigurationException si no se puede crear el lector
     * @throws IOException si no se puede leer el fichero
     * @throws SAXException si el XML no es válido
     */
    public void importarXML(File fichero) throws ParserConfigurationException, IOException, SAXException {
        listaTelevisores = new ArrayList<Televisor>();
        TelevisorLED nuevoLED = null;
        TelevisorOLED nuevoOLED = null;
        TelevisorQLED nuevoQLED = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document documento = builder.parse(fichero);
        NodeList listaElementos = documento.getElementsByTagName("*");
        for (int i = 0; i < listaElementos.getLength(); i++) {
            Element nodoTelevisor = (Element) listaElementos.item(i);
            if (nodoTelevisor.getTagName().equals("TelevisorLED")) {
                nuevoLED = new TelevisorLED();
                nuevoLED.setNumSerie(nodoTelevisor.getChildNodes().item(0).getTextContent());
                nuevoLED.setPrecio(Double.parseDouble(nodoTelevisor.getChildNodes().item(1).getTextContent()));
                nuevoLED.setMarca(nodoTelevisor.getChildNodes().item(2).getTextContent());
                nuevoLED.setModelo(nodoTelevisor.getChildNodes().item(3).getTextContent());
                nuevoLED.setPulgadas(Integer.parseInt(nodoTelevisor.getChildNodes().item(4).getTextContent()));
                nuevoLED.setFechaFabricacion(LocalDate.parse(nodoTelevisor.getChildNodes().item(5).getTextContent()));
                nuevoLED.setConsumoVatios(Integer.parseInt(nodoTelevisor.getChildNodes().item(6).getTextContent()));
                listaTelevisores.add(nuevoLED);
            } else if (nodoTelevisor.getTagName().equals("TelevisorOLED")) {
                nuevoOLED = new TelevisorOLED();
                nuevoOLED.setNumSerie(nodoTelevisor.getChildNodes().item(0).getTextContent());
                nuevoOLED.setPrecio(Double.parseDouble(nodoTelevisor.getChildNodes().item(1).getTextContent()));
                nuevoOLED.setMarca(nodoTelevisor.getChildNodes().item(2).getTextContent());
                nuevoOLED.setModelo(nodoTelevisor.getChildNodes().item(3).getTextContent());
                nuevoOLED.setPulgadas(Integer.parseInt(nodoTelevisor.getChildNodes().item(4).getTextContent()));
                nuevoOLED.setFechaFabricacion(LocalDate.parse(nodoTelevisor.getChildNodes().item(5).getTextContent()));
                nuevoOLED.setAniosGarantia(Integer.parseInt(nodoTelevisor.getChildNodes().item(6).getTextContent()));
                listaTelevisores.add(nuevoOLED);
            } else if (nodoTelevisor.getTagName().equals("TelevisorQLED")) {
                nuevoQLED = new TelevisorQLED();
                nuevoQLED.setNumSerie(nodoTelevisor.getChildNodes().item(0).getTextContent());
                nuevoQLED.setPrecio(Double.parseDouble(nodoTelevisor.getChildNodes().item(1).getTextContent()));
                nuevoQLED.setMarca(nodoTelevisor.getChildNodes().item(2).getTextContent());
                nuevoQLED.setModelo(nodoTelevisor.getChildNodes().item(3).getTextContent());
                nuevoQLED.setPulgadas(Integer.parseInt(nodoTelevisor.getChildNodes().item(4).getTextContent()));
                nuevoQLED.setFechaFabricacion(LocalDate.parse(nodoTelevisor.getChildNodes().item(5).getTextContent()));
                nuevoQLED.setHorasVidaUtil(Integer.parseInt(nodoTelevisor.getChildNodes().item(6).getTextContent()));
                listaTelevisores.add(nuevoQLED);
            }
        }
    }


}
