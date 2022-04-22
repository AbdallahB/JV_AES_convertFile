package com.company;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.util.ArrayList;

public class ReporteadorArchivosDirectorio {

    public void listarArchivos() throws IOException {// Guarda el listados de los nombres de archivos contenidos en el directorio seleccionado

        String directorioTrabajo="";

        // CONOCER EL DIRECTORIO DE TRABAJO
        Boolean old = UIManager.getBoolean("FileChooser.readOnly");// apaga edicion
        UIManager.put("FileChooser.readOnly", Boolean.TRUE);// apaga edicion
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        UIManager.put("FileChooser.readOnly", old);//======================================== apaga edicion

        jfc.setDialogTitle("SELECCIONE EL DIRECTORIO PARA ELABORAR UNA LISTA DE LOS ARCHIVOS QUE CONTIENE");
        //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

        //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnValue = jfc.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            if (jfc.getSelectedFile().isDirectory()) {
                // System.out.println("Directorio seleccionado " + jfc.getSelectedFile());
            }
        } else {

            System.out.println("SELECCION CANCELADA");
            System.exit(0);// aborta el programa al primir cancel en el explorador
        }// fin delUIManager

        directorioTrabajo= String.valueOf(jfc.getSelectedFile()); // inicializa la variable con el directorio seleccionado
        String sDirectorioTrabajo=directorioTrabajo;


        System.out.println("el directorio es "+directorioTrabajo);

        //--------------------------------------------------------------

        // -- filtrar archivos del directorio
        String path = sDirectorioTrabajo;// establece el directorio actual como directorio de trabajo


        File archivosenPath = new File(path);


        File[] listaFicheros = archivosenPath.listFiles();
        ArrayList<String> xlistaArchivos = new ArrayList<>();

// Elabora Lista de archivos TXT con las MedIntegradas dentro del directorio
        for (int i = 0; i < listaFicheros.length; i++) {// lista de archivos
            String xcadena = listaFicheros[i].toString();
            String extArchivo = xcadena.substring(xcadena.length() - 4);

            if (listaFicheros[i].isFile() ) {
                //System.out.println("Nombre archivo " + listaFicheros[i] + " xlist ");
                xlistaArchivos.add(listaFicheros[i].toString());
            }

        }

        for(int i = 0; i < xlistaArchivos.size(); i++) {
            System.out.println(i+ " nombre archivo " + xlistaArchivos.get(i));
        }

// ASIGNA NOMBRE A LOS ARCHIVOS DE SALIDA  E INSTANCIA LOS FILES Y PRINTER PARA GENERAR LAS SALIDAS

        String partesDirString = sDirectorioTrabajo; // Estas dos lineas extraen el prefijo del directorio de trabajo
        String[] srtpartNombFile = partesDirString.split("\\\\"); // que conformara parte del nombre de archivos de salida

        File directorioResultados = new File(sDirectorioTrabajo + "\\Reporte");
        directorioResultados.mkdirs();
        System.out.println("creada carpeta de Reporte");

        // crea archivo de salida

        File archReporte = new File(directorioResultados + "\\" + "Reporte.txt");
        FileWriter fWarchReporte = new FileWriter(archReporte);
        BufferedWriter BarchReporte = new BufferedWriter(fWarchReporte);
        PrintWriter PWarchReporte = new PrintWriter(BarchReporte);

        String partesNombArchString;

        //vacia contenido del arreglo en archivo de reporte
        for(int i = 0; i < xlistaArchivos.size(); i++) {
            partesNombArchString =xlistaArchivos.get(i) ; // Estas dos lineas extraen el prefijo del directorio de trabajo
            String[] parteDelNombre = partesNombArchString.split("\\\\"); // que conforma el nombre de archivos analizado

            PWarchReporte.write(parteDelNombre[parteDelNombre.length - 1]+ (char)13+(char)10);
        }


        PWarchReporte.close();
        fWarchReporte.close();





























    }



}
