package util;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

/**
 * Clase con métodos de utilidad para mostrar mensajes y selectores de fichero.
 * @author Sergio
 */
public class Util {
    /**
     * Muestra un mensaje de error en una ventana emergente.
     * @param mensaje texto del error
     */
    public static void mensajeError (String mensaje){
        JOptionPane.showMessageDialog(null,mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
    /**
     * Muestra un diálogo de confirmación con las opciones Sí y No.
     * @param mensaje pregunta que se muestra
     * @param titulo título de la ventana
     * @return la opción elegida (JOptionPane.YES_OPTION o NO_OPTION)
     */
    public static int mensajeConfirmacion(String mensaje, String titulo){
        return JOptionPane.showConfirmDialog(null, mensaje, titulo, JOptionPane.YES_NO_OPTION);

    }
    /**
     * Crea un selector de ficheros con una ruta por defecto y un filtro de extensión.
     * @param rutaDefecto carpeta en la que se abre el selector
     * @param tipoArchivos descripción del tipo de archivo (por ejemplo "Archivos XML")
     * @param extension extensión permitida (por ejemplo "xml")
     * @return el selector de ficheros configurado
     */
    public static JFileChooser crearSelectorFichero(File rutaDefecto, String tipoArchivos, String extension){
        JFileChooser selectorFichero = new JFileChooser();
        if (rutaDefecto != null){
            selectorFichero.setCurrentDirectory(rutaDefecto);
        }
        if (extension != null){
            FileNameExtensionFilter filtro = new FileNameExtensionFilter(tipoArchivos, extension);
            selectorFichero.setFileFilter(filtro);
        }
        return selectorFichero;

    }
}
