package com.company;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UnificarMedIntegradas extends SwingWorker {
    public JFrame framePrincipal;
    public boolean flagBloquearFramePrincipalPadre=true;
    public String tipoArchivo;
    public String primeraPalabraArchivo = "";
    public String tempPrimeraPalabraLeidaArchivo = "";
    public int kProgressbar=0;
    public String controlPalabraEncabezado="";// variable para comparar la semejanza de los rchivos a ser integrados

//---------- n

    Setup setup = new Setup();// instancia la clase Setup, donde estan la configuración inicial que sera leida
    String leyendoFormatox; // Tipo de formato seleccionado para leer
    String partesDirString; // Estas dos lineas extraen el prefijo del directorio de trabajo
    String[] srtpartNombFile; // que conformara parte del nombre de archivos de salida
    JFrame progressFrame;
    JProgressBar progressBar;
    String archivoDetall;// asignacion del nombre del archivo "Detalle"
    File fDetalle;// Variable asociada al archivo de reporte Detalle
    FileWriter fWarchivoDetalle; // nombre del método de escritura
    BufferedWriter bWarchivoDetalle;// nombre del buffer
    PrintWriter printArchivoDet;// método de escritura para el archivo Detalle.xt

    String archivoReport;// asignacion de nombre del archivo "Reporte"
    File fReport;// variable asociada al archivo de resporte
    FileWriter fWarchivoReport;// nombre del método de escritura
    BufferedWriter bWarchivoReport;// nombre del buffer
    PrintWriter printArchivoReport;// metodo de escritura

    String listErrores;// nombre archivo
    File fErrorReport; // configura creacion archivo reporte de fallas
    FileWriter fwErrorArchivoReport; // clase writer error
    BufferedWriter bWErrorArchivoReport;// buffer writer error
    PrintWriter printErrorArchivoReport;// escritura en archivo error en salida

    File fMedIntegradas;// Declaracion de atributos archivos para manejar resultados de salida de la COMPILACION
    // Esta declaracion anticipada permite acceder a la variables desde distintos métodos
    double nroArchivosCompilados = 0;// Cantidad de archivos compilados en MedIntegrado.TXT
    FileWriter fWMedIntegradas;
    BufferedWriter bWMedIntegradas;
    PrintWriter printMedIntegradas;
    //-----------------------------------------

    File fRegistroArchivoscompilados; // archivo Logger donde se escribe la lista de archivos que fueron compilados
    FileWriter fWRegistroArchivosCompilados;
    BufferedWriter bWRegistroArchivosCompilados;
    PrintWriter pWRegistroArchivosCompilados;


    String directorio;// variable para configurar el directorio de trabajo
    String path;// establece el directorio actual como directorio de trabajo

    List<String> listaReporteDetalle;// Arreglo que funciona como buffer para acumular las lineas a enviar archivoDetalle
    List<String> listaReporteResumen;// Arreglo que funciona como buffer para acumular las lineas a enviar archivoReporte
    List<String> xlistaArchivos;// vector donde se arma la lista de archivos a ser procesados
    int cuentalosxListaArchivosProcesados = 0;// esta variable ira contando los archivos procesados
    //n-----------


// setter and getters

    public String getTipoArchivo() {
        return tipoArchivo;
    }

    public void setTipoArchivo(String tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
    }

    public String getPrimeraPalabraArchivo() {
        return primeraPalabraArchivo;
    }

    public void setPrimeraPalabraArchivo(String primeraPalabraArchivo) {
        this.primeraPalabraArchivo = primeraPalabraArchivo;
    }

    public String getTempPrimeraPalabraLeidaArchivo() {
        return tempPrimeraPalabraLeidaArchivo;
    }

    public void setTempPrimeraPalabraLeidaArchivo(String tempPrimeraPalabraLeidaArchivo) {
        this.tempPrimeraPalabraLeidaArchivo = tempPrimeraPalabraLeidaArchivo;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Setup getSetup() {
        return setup;
    }

    public void setSetup(Setup setup) {
        this.setup = setup;
    }

    public String getLeyendoFormatox() {
        return leyendoFormatox;
    }

    public void setLeyendoFormatox(String leyendoFormatox) {
        this.leyendoFormatox = leyendoFormatox;
    }

    public String getPartesDirString() {
        return partesDirString;
    }

    public void setPartesDirString(String partesDirString) {
        this.partesDirString = partesDirString;
    }

    public String[] getSrtpartNombFile() {
        return srtpartNombFile;
    }

    public void setSrtpartNombFile(String[] srtpartNombFile) {
        this.srtpartNombFile = srtpartNombFile;
    }

    public JFrame getProgressFrame() {
        return progressFrame;
    }

    public void setProgressFrame(JFrame progressFrame) {
        this.progressFrame = progressFrame;
    }

    public JProgressBar getProgressBar() {
        return progressBar;
    }

    public void setProgressBar(JProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    public String getArchivoDetall() {
        return archivoDetall;
    }

    public void setArchivoDetall(String archivoDetall) {
        this.archivoDetall = archivoDetall;
    }

    public File getfDetalle() {
        return fDetalle;
    }

    public void setfDetalle(File fDetalle) {
        this.fDetalle = fDetalle;
    }

    public FileWriter getfWarchivoDetalle() {
        return fWarchivoDetalle;
    }

    public void setfWarchivoDetalle(FileWriter fWarchivoDetalle) {
        this.fWarchivoDetalle = fWarchivoDetalle;
    }

    public BufferedWriter getbWarchivoDetalle() {
        return bWarchivoDetalle;
    }

    public void setbWarchivoDetalle(BufferedWriter bWarchivoDetalle) {
        this.bWarchivoDetalle = bWarchivoDetalle;
    }

    public PrintWriter getPrintArchivoDet() {
        return printArchivoDet;
    }

    public void setPrintArchivoDet(PrintWriter printArchivoDet) {
        this.printArchivoDet = printArchivoDet;
    }

    public String getArchivoReport() {
        return archivoReport;
    }

    public void setArchivoReport(String archivoReport) {
        this.archivoReport = archivoReport;
    }

    public File getfReport() {
        return fReport;
    }

    public void setfReport(File fReport) {
        this.fReport = fReport;
    }

    public FileWriter getfWarchivoReport() {
        return fWarchivoReport;
    }

    public void setfWarchivoReport(FileWriter fWarchivoReport) {
        this.fWarchivoReport = fWarchivoReport;
    }

    public BufferedWriter getbWarchivoReport() {
        return bWarchivoReport;
    }

    public void setbWarchivoReport(BufferedWriter bWarchivoReport) {
        this.bWarchivoReport = bWarchivoReport;
    }

    public PrintWriter getPrintArchivoReport() {
        return printArchivoReport;
    }

    public void setPrintArchivoReport(PrintWriter printArchivoReport) {
        this.printArchivoReport = printArchivoReport;
    }

    public String getListErrores() {
        return listErrores;
    }

    public void setListErrores(String listErrores) {
        this.listErrores = listErrores;
    }

    public File getfErrorReport() {
        return fErrorReport;
    }

    public void setfErrorReport(File fErrorReport) {
        this.fErrorReport = fErrorReport;
    }

    public FileWriter getFwErrorArchivoReport() {
        return fwErrorArchivoReport;
    }

    public void setFwErrorArchivoReport(FileWriter fwErrorArchivoReport) {
        this.fwErrorArchivoReport = fwErrorArchivoReport;
    }

    public BufferedWriter getbWErrorArchivoReport() {
        return bWErrorArchivoReport;
    }

    public void setbWErrorArchivoReport(BufferedWriter bWErrorArchivoReport) {
        this.bWErrorArchivoReport = bWErrorArchivoReport;
    }

    public PrintWriter getPrintErrorArchivoReport() {
        return printErrorArchivoReport;
    }

    public void setPrintErrorArchivoReport(PrintWriter printErrorArchivoReport) {
        this.printErrorArchivoReport = printErrorArchivoReport;
    }

    public File getfMedIntegradas() {
        return fMedIntegradas;
    }

    public void setfMedIntegradas(File fMedIntegradas) {
        this.fMedIntegradas = fMedIntegradas;
    }

    public double getNroArchivosCompilados() {
        return nroArchivosCompilados;
    }

    public void setNroArchivosCompilados(double nroArchivosCompilados) {
        this.nroArchivosCompilados = nroArchivosCompilados;
    }

    public FileWriter getfWMedIntegradas() {
        return fWMedIntegradas;
    }

    public void setfWMedIntegradas(FileWriter fWMedIntegradas) {
        this.fWMedIntegradas = fWMedIntegradas;
    }

    public BufferedWriter getbWMedIntegradas() {
        return bWMedIntegradas;
    }

    public void setbWMedIntegradas(BufferedWriter bWMedIntegradas) {
        this.bWMedIntegradas = bWMedIntegradas;
    }

    public PrintWriter getPrintMedIntegradas() {
        return printMedIntegradas;
    }

    public void setPrintMedIntegradas(PrintWriter printMedIntegradas) {
        this.printMedIntegradas = printMedIntegradas;
    }

    public String getDirectorio() {
        return directorio;
    }

    public void setDirectorio(String directorio) {
        this.directorio = directorio;
    }

    public List<String> getListaReporteDetalle() {
        return listaReporteDetalle;
    }

    public void setListaReporteDetalle(List<String> listaReporteDetalle) {
        this.listaReporteDetalle = listaReporteDetalle;
    }

    public List<String> getListaReporteResumen() {
        return listaReporteResumen;
    }

    public void setListaReporteResumen(List<String> listaReporteResumen) {
        this.listaReporteResumen = listaReporteResumen;
    }

    public List<String> getXlistaArchivos() {
        return xlistaArchivos;
    }

    public void setXlistaArchivos(List<String> xlistaArchivos) {
        this.xlistaArchivos = xlistaArchivos;
    }

    public int getCuentalosxListaArchivosProcesados() {
        return cuentalosxListaArchivosProcesados;
    }

    public void setCuentalosxListaArchivosProcesados(int cuentalosxListaArchivosProcesados) {
        this.cuentalosxListaArchivosProcesados = cuentalosxListaArchivosProcesados;
    }

    public int getkProgressbar() {
        return kProgressbar;
    }

    public void setkProgressbar(int kProgressbar) {
        this.kProgressbar = kProgressbar;
    }

    public String getControlPalabraEncabezado() {
        return controlPalabraEncabezado;
    }

    public void setControlPalabraEncabezado(String controlPalabraEncabezado) {
        this.controlPalabraEncabezado = controlPalabraEncabezado;
    }

    public boolean isBloqueoFramePrincipalPadre() {
        return flagBloquearFramePrincipalPadre;
    }

    public void setBloqueoFramePrincipalPadre(boolean bloqueoFramePrincipalPadre) {
        this.flagBloquearFramePrincipalPadre = bloqueoFramePrincipalPadre;
    }



    public JFrame getFramePrincipal() {
        return framePrincipal;
    }

    public void setFramePrincipal(JFrame framePrincipal) {
        this.framePrincipal = framePrincipal;
    }

    public File getfRegistroArchivoscompilados() {
        return fRegistroArchivoscompilados;
    }

    public void setfRegistroArchivoscompilados(File fRegistroArchivoscompilados) {
        this.fRegistroArchivoscompilados = fRegistroArchivoscompilados;
    }

    public FileWriter getfWRegistroArchivosCompilados() {
        return fWRegistroArchivosCompilados;
    }

    public void setfWRegistroArchivosCompilados(FileWriter fWRegistroArchivosCompilados) {
        this.fWRegistroArchivosCompilados = fWRegistroArchivosCompilados;
    }

    public BufferedWriter getbWRegistroArchivosCompilados() {
        return bWRegistroArchivosCompilados;
    }

    public void setbWRegistroArchivosCompilados(BufferedWriter bWRegistroArchivosCompilados) {
        this.bWRegistroArchivosCompilados = bWRegistroArchivosCompilados;
    }

    public PrintWriter getpWRegistroArchivosCompilados() {
        return pWRegistroArchivosCompilados;
    }

    public void setpWRegistroArchivosCompilados(PrintWriter pWRegistroArchivosCompilados) {
        this.pWRegistroArchivosCompilados = pWRegistroArchivosCompilados;
    }

    // fin setter and Getters

    // CONSTRUCTOR -----------------

    public UnificarMedIntegradas(JFrame framePrincipal) {
        this.framePrincipal = framePrincipal;
    }


    //---------------------------






//Métodos-------------------------------

    public void principalUnificarMedInt() throws IOException {
        setup.inicializaConfiguracionBaseTxt();
        inicializaIoArchivos();// Abre explorador y crea archivos de reporte
        System.out.println("Principal UnificarMed  raiz= " + path + "  nro archivos= " + xlistaArchivos.size());

        // Listar archivos TXT a Unificar
        for (int i = 0; i < xlistaArchivos.size(); i++) {// lista de archivos
            System.out.println(i + " Nombre archivo " + xlistaArchivos.get(i));
            pWRegistroArchivosCompilados.write("Nombre archivo compilado: "+
                    extraeNombreArchivoBaseSinrutaNiextension(xlistaArchivos.get(i))+sbCrLf());

        }

        //--------------------------------------------
        //-- Leer los archivos seleccionados y unifica sus contenidos en el nuevo Medintegrado
        leerArchivosMedIntegradas();//
        //FIN proceso
        //-------------------------------------------------------------------------------

        //-------------------------------------------------------------------------------

        progressFrame.dispose();


        //----  MENSAJE DE FIN DEL PROCESO --------------------------------------
         ImageIcon logojava=new ImageIcon("img\\javapng.png");
            int  resp = JOptionPane.showOptionDialog(
                    progressFrame,
                            "CREADO CON EXITO!!! EL ARCHIVO UNIFICADO DE MEDICIONES >>> " + "Ruta : "+path,
                            "FIN DEL PROCESO ",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE,
                            logojava,    // null para icono por defecto.
                            new Object[] { "Cerrar"},   // null para YES, NO y CANCEL
                            "opcion 1");

        // mensaje
        if(resp==0) {
            System.out.println("cancel= " + resp);


        }

    }

    //------------------------------------------------------
    //****** Configura instancias de IO archivos
    public void inicializaIoArchivos() throws IOException {

        // METODO DE INICIALIZACION DE LA SALIDA DE ARCHIVO en la clase registro

              //  <<<<<<<<<<<<<<<<<<<<<---

                //------------- LAZO DE CONTROL DE CONFIRMACION del directorio de trabajo
                        String sDirectorioTrabajo="";
                        int resp=1; // 1=NO confirmado    0=confirma directorio
                        boolean flagInicio=true;

                        while (resp==1) {

                            //---- Asignacion del directorio de trabajo con el método conocerDirectorioTrabajo
                            sDirectorioTrabajo=conocerDirectorioTrabajo();// método despliegue Gui con explorador

                                  resp = JOptionPane.showOptionDialog(
                                                      null,
                                                      "Confirme si  \""+ sDirectorioTrabajo.toUpperCase() + "\"  es la carpeta seleccionada?? ",
                                                      "CARPETA DE TRABAJO",
                                                      JOptionPane.YES_NO_OPTION,
                                                      JOptionPane.WARNING_MESSAGE,
                                                      null,    // null para icono por defecto.
                                                      new Object[] { "SI","NO"},   // null para YES, NO y CANCEL
                                                      "opcion 1");

                            if (resp == JOptionPane.YES_OPTION) {
                                System.out.println("Yes option=" + resp);
                            } else {
                                System.out.println("No option=" + resp);
                            }
                        }
                        // ------------- FIN LAZO DE CONTROL CONOCER DIRECTORIO DE TRABAJO

                        System.out.println("EL directorio de trabajo es "+ sDirectorioTrabajo);// Extrae el directorio actual de trabajo del usuario

        //xxxx
        // <<<<<<<<<<<<<<<<<<<<<<<<<<<<-----   --------------------------------

        System.out.println("EL directorio de trabajo es " + sDirectorioTrabajo);// Extrae el directorio actual de trabajo del usuario


        path = sDirectorioTrabajo;// establece el directorio actual como directorio de trabajo


        File archivosenPath = new File(path);


        File[] listaFicheros = archivosenPath.listFiles();
        xlistaArchivos = new ArrayList<>();

        // Elabora Lista de archivos TXT con las MedIntegradas dentro del directorio
        for (int i = 0; i < listaFicheros.length; i++) {// lista de archivos
            String xcadena = listaFicheros[i].toString();
            String extArchivo = xcadena.substring(xcadena.length() - 4);

            if (listaFicheros[i].isFile() && extArchivo.toUpperCase().equals(".TXT")) {
                System.out.println("Nombre archivo " + listaFicheros[i] + " xlist ");
                xlistaArchivos.add(listaFicheros[i].toString());
            }

        }


        //File directorioResultados = new File(directorio);// instancia el objeto archivo tipo Directorio
        // directorioResultados.mkdirs();//crea un directorio
        //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<
        // ASIGNA NOMBRE A LOS ARCHIVOS DE SALIDA  E INSTANCIA LOS FILES Y PRINTER PARA GENERAR LAS SALIDAS

        String partesDirString = sDirectorioTrabajo; // Estas dos lineas extraen el prefijo del directorio de trabajo
        String[] srtpartNombFile = partesDirString.split("\\\\"); // que conformara parte del nombre de archivos de salida

        File directorioResultados = new File(sDirectorioTrabajo + "\\Resultados");
        directorioResultados.mkdirs();
        System.out.println("creada carpeta de Resultados");

        // archivoDetall=srtpartNombFile[srtpartNombFile.length-1]+"_Detalle.txt";// extrae parte del nombre del archivos en  el directorio
        // //fDetalle = new File( sDirectorioTrabajo+"\\"+archivoDetall);
        // fDetalle = new File( directorioResultados+"\\"+archivoDetall);
        // fWarchivoDetalle = new FileWriter(fDetalle);
        // bWarchivoDetalle= new BufferedWriter(fWarchivoDetalle);
        // printArchivoDet = new PrintWriter(bWarchivoDetalle);

        // archivoReport=srtpartNombFile[srtpartNombFile.length-1]+"_Reporte.txt";
        // //fReport = new File( sDirectorioTrabajo+"\\"+archivoReport);
        // fReport = new File( directorioResultados+"\\"+archivoReport);
        // fWarchivoReport = new FileWriter(fReport);
        // bWarchivoReport= new BufferedWriter(fWarchivoReport);
        // printArchivoReport = new PrintWriter(bWarchivoReport);


        // // implementacion del archivo de errores
        // // String sDirectorioTrabajo=System.getProperty("user.dir"); // crea la variable sDirectorioTRabajo
        // // String partesDirString=sDirectorioTrabajo; // Estas dos lineas extraen el prefijo del directorio de trabajo
        // //  String [] srtpartNombFile=partesDirString.split("\\\\"); // que conformara parte del nombre de archivos de salida

        // listErrores=srtpartNombFile[srtpartNombFile.length-1]+"_ReporteEventos.txt";
        // //fErrorReport = new File( sDirectorioTrabajo+"\\"+listErrores);
        // fErrorReport = new File( directorioResultados+"\\"+listErrores);
        // fwErrorArchivoReport = new FileWriter(fErrorReport);
        // bWErrorArchivoReport= new BufferedWriter(fwErrorArchivoReport);
        // printErrorArchivoReport = new PrintWriter(bWErrorArchivoReport);

        // xyz Archivo de resultados de compilacion
// xyz ----> BLOQUE de armado de las MEDICIONES INTEGRADAS
        int varHabilitaCompilador = Integer.parseInt(setup.getBtnHabIntegrador().get(0));// BTN habiltacion compilacion en SETUP
        System.out.println("flag de activación setup " + varHabilitaCompilador);
        if (varHabilitaCompilador == 1) {
                //fMedIntegradas = new File(sDirectorioTrabajo +"\\" + "MedIntegradas.txt");
                fMedIntegradas = new File(directorioResultados + "\\" + "SUMA_MedIntegradas.txt");
                fWMedIntegradas = new FileWriter(fMedIntegradas);
                bWMedIntegradas = new BufferedWriter(fWMedIntegradas);
                printMedIntegradas = new PrintWriter(bWMedIntegradas);

                //printMedIntegradas.write("inicio escritura");
                System.out.println("creado archivo medintegrado");
            //- se configura archivo logger -----------------------------------------------
            fRegistroArchivoscompilados = new File(directorioResultados + "\\" + "ListaArchivosCompilados.txt");
            fWRegistroArchivosCompilados = new FileWriter(fRegistroArchivoscompilados);
            bWRegistroArchivosCompilados = new BufferedWriter(fWRegistroArchivosCompilados);
            pWRegistroArchivosCompilados = new PrintWriter(bWRegistroArchivosCompilados);


        }
        System.out.println("regreso de inicializa archivos" + "  nro archivos ");


        //for(int i=0;i<xlistaArchivos.size();i++){// lista de archivos
        //    if (listaFicheros[i].isFile()){
        //        System.out.println(i+" Nombre archivo "+xlistaArchivos.get(i));

        //    }

        // }

    }//--------------------   fin de implementacion IO Archivos


    //******** buffer con el String Ascii ************************
    public StringBuffer sbCrLf() {
        StringBuffer sbCrLf = new StringBuffer();
        sbCrLf.append((char) 13);
        sbCrLf.append((char) 10);
        return sbCrLf; // retorna un string con el Ascii CRLF
    }

    public StringBuffer tab() {
        StringBuffer tab = new StringBuffer();
        tab.append((char) 9);
        return tab;
    }


    @Override
    protected Object doInBackground() throws Exception {

        System.out.println("flag bloqueo activo ="+isBloqueoFramePrincipalPadre());
        framePrincipal.setVisible(false);

        //-------- principal
        principalUnificarMedInt();// ejecuta los metodos requridos para realizar el proceso
        //---------------------

        framePrincipal.setVisible(true);

        System.out.println("flag bloqueo activo "+isBloqueoFramePrincipalPadre());


        for (int z = 0; z < 10; z++) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("interrumpido");
            }

            // Se pasa valor para la barra de progreso. ESto llamara al metodo
            // process() en el hilo de despacho de eventos.
            publish(z);

        }
        return null;
    }
    
    //-------------------------------------



    //-------------------------------------------------------------------------------------
    public void leerArchivosMedIntegradas() throws IOException {

        //Barra de avance leer y procesar archivos----------------

        // CONFIGURA lA BARRA DE PROGRESO
        //----------------------------------------
        // Inicio del despliegue de la barra
        progressFrame=new JFrame("UNIFICAR ARCHIVOS MedIntegradas ");


        progressBar=new JProgressBar();
        progressBar.setStringPainted(true);
        progressBar.setValue(0); // inicializa el valor de avance en la barra

        progressFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        progressFrame.setLayout(new GridLayout(1,10));
        progressFrame.setSize(720,160);              // Coloca el tamaño de la barra de progreso

        ImageIcon logoBaes=new ImageIcon("img\\logo_pie.jpg");
        progressFrame.setIconImage(logoBaes.getImage().getScaledInstance(100,80,Image.SCALE_SMOOTH));

        progressFrame.setLocationRelativeTo(null);
        progressFrame.add(progressBar,0,0);
        progressFrame.setVisible(true);


          //JOptionPane.showMessageDialog(null, "Barra de progreso activa " );
        //--------------------------------
        // barre la lista de archivos leyendo su contenido y escribiendolo en archivo SUMA
        for (int i = 0; i < xlistaArchivos.size(); i++) { // barre la lista de archivos a ser integrados


            String nombArchivo = xlistaArchivos.get(i);
            FileReader lecturaArchivo = new FileReader(nombArchivo);
            BufferedReader blecturaArchivo = new BufferedReader(lecturaArchivo);
            System.out.println("procesando archivo -->"+nombArchivo);
            //---- lee cada archivo y lo escribe en Suma_MedIntegrada
            String blinea;
            int indicadorPosic = 0;
            int contadorActualizacion=0;
            while ((blinea = blecturaArchivo.readLine()) != null) {

                //******** lineas para ller la configuracion y cargar en las variables
                // System.out.println("indicadorPosic= "+indicadorPosic+"  valor de la linea: "+blinea);

                //escribe el encabezado del primer archivo leido
                if(i==0 && indicadorPosic==0){
                    printMedIntegradas.write("NombreArchivoMedIntegrada|"+blinea + sbCrLf());
                    controlPalabraEncabezado=blinea;
                }


                // control de igualdad en los encabezados de los archivos que se están procesando
                if(indicadorPosic==0){
                    if (controlPalabraEncabezado.equals(blinea)) {

                    }  else{
                            System.out.println("Se detectó diferencia en encabezado del archivo " + extraeNombreArchivoBaseSinrutaNiextension(nombArchivo));
                            JOptionPane.showMessageDialog(null, "Se detectó diferencia en encabezado del archivo " + extraeNombreArchivoBaseSinrutaNiextension(nombArchivo)+". Revise!!! puede no pertenecer al grupo");
                            printMedIntegradas.write("Detectadas diferencias en el encabezado del archivo "+extraeNombreArchivoBaseSinrutaNiextension(nombArchivo)+" revise!!! puede no pertener al grupo");
                            printMedIntegradas.close();
                            File file=new File(path+"\\Resultados\\SUMA_MedIntegradas.txt");
                            file.delete();
                            System.exit(0);
                    }
                }

                ///------------------------------------

                if(indicadorPosic>0) {// solo escribe el contenido de los Archivos saltando la primera Linea del encabezado

                    printMedIntegradas.write(extraeNombreArchivoBaseSinrutaNiextension(nombArchivo)+"|" +blinea + sbCrLf());

                    //System.out.println(extraeNombreArchivoBaseSinrutaNiextension(nombArchivo)+ " nro Linea "+indicadorPosic);
                }
                indicadorPosic=indicadorPosic+1;
                contadorActualizacion++;
                if(contadorActualizacion>40){
                    contadorActualizacion=0;
                    actualizaNroLineaTituloProgressBar(extraeNombreArchivoBaseSinrutaNiextension(nombArchivo),indicadorPosic);
                }

            }//fin lazo while
            incrementarProgressBar(extraeNombreArchivoBaseSinrutaNiextension(nombArchivo));
            //-------------------------------------


           // JOptionPane.showMessageDialog(null, "Error al abrir el archivo " + xlistaArchivos.get(i));
           // System.exit(0);
//
//
        } // fin lazo for para barrer todos los archivos de la lista
        progressFrame.setTitle("NUMERO DE ARCHIVOS UNIFICADOS CON EXITO "+xlistaArchivos.size()+" !!!");
        printMedIntegradas.close();
        pWRegistroArchivosCompilados.close();

    }// fin del método que lee los archivos y escribe sumaMedInt

    //-------------------------------------------
    public String extraeNombreArchivoBaseSinrutaNiextension(String nombreArchivoconRutayExt){
        String nombreBase="";
        // Extrae de la ruta el nombre del archivo
        //System.out.println("nombArch "+nombArch);
        String[] srtpartNombFile = nombreArchivoconRutayExt.split("/"); // que conformara parte del nombre de archivos de salida
        String NombreArchivo = srtpartNombFile[srtpartNombFile.length - 1];// Nombre puro extraido de la ruta
        // Quita la extensión del archivo dejando solo el nombre Base
        String base = srtpartNombFile[srtpartNombFile.length - 1];
        //System.out.println("base--->"+base);
        srtpartNombFile = base.split("\\.");
        //System.out.println("srtpartNombFile"+srtpartNombFile[0]);
        String raizNombreArchivo = srtpartNombFile[0];
        //-------------- ESTA SECCION EXTRAE LA RAIZ DEL ARCHIVO --------------------
        String[] srtpartRaizNombreArchivo = raizNombreArchivo.split("\\\\");
        nombreBase = srtpartRaizNombreArchivo[srtpartRaizNombreArchivo.length - 1];
        // El nombre base del archivo sin extension se encuentra en la variable raizNombreArchivo en este punto

        //System.out.println("Raiz extraida= "+nombreBase);

        return (nombreBase);
    }

    public void incrementarProgressBar(String nombArchivo){
        int tempValorincremento=0;
       tempValorincremento=100/xlistaArchivos.size();

        progressBar.setValue(progressBar.getValue() + tempValorincremento);

       // System.out.println("NombArchivo "+nombArchivo+" VALORBARRA= "+progressBar.getValue()+
       //         " |valor de cuentaArchProcesados "+cuentalosxListaArchivosProcesados+
       //         " |Total Archivos a procesar  "+xlistaArchivos.size());
        progressFrame.setTitle("Procesando archivo:" + nombArchivo);

      }

    public void actualizaNroLineaTituloProgressBar(String archivoProcesando,int nroLineaArchivo){
        progressFrame.setTitle("Leyendo Linea: "+nroLineaArchivo+" del archivo:  "+archivoProcesando.toUpperCase()  );

    }



    public void completarBarraProgreso(){ // termina de completar la barra al 100%
        while (progressBar.getValue()<100){
            progressBar.setValue(progressBar.getValue()+1);
        }

    }

         //-------------------------------------------------------------
          public String conocerDirectorioTrabajo(){
              String directorioTrabajo="";

              // CONOCER EL DIRECTORIO DE TRABAJO
              Boolean old = UIManager.getBoolean("FileChooser.readOnly");// apaga edicion
              UIManager.put("FileChooser.readOnly", Boolean.TRUE);// apaga edicion
              JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
              UIManager.put("FileChooser.readOnly", old);//======================================== apaga edicion

              jfc.setDialogTitle("SELECCIONE EL DIRECTORIO PARA REALIZAR LA COMPILACION DE ARCHIVOS MedIntegrada");
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

              return directorioTrabajo;
          }//fin conocer directorio de Trabajo

    //-------------------------------------------------




    //--------------------------------------------------------------




}// fin clase UnificarMedIntegradas