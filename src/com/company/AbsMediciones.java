package com.company;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.nio.Buffer;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;

public class AbsMediciones extends SwingWorker<Double,Integer>{ // Clase principal que se instancia como aplicacion principal Baes Mediciones

   // Atributos
    double kProgressbar=0;
    public JFrame framePrincipal;
    Setup setup = new Setup();// instancia la clase Setup, donde estan la configuración inicial que sera leida
    String leyendoFormatox; // Tipo de formato seleccionado para leer
    String partesDirString; // Estas dos lineas extraen el prefijo del directorio de trabajo
    String [] srtpartNombFile; // que conformara parte del nombre de archivos de salida
    JFrame frame;
    JProgressBar progressBar;
    String archivoDetall;// asignacion del nombre del archivo "Detalle"
    File fDetalle;// Variable asociada al archivo de reporte Detalle
    FileWriter fWarchivoDetalle; // nombre del método de escritura
    BufferedWriter bWarchivoDetalle;// nombre del buffer
    PrintWriter printArchivoDet ;// método de escritura para el archivo Detalle.xt

    String archivoReport;// asignacion de nombre del archivo "Reporte"
    File fReport ;// variable asociada al archivo de resporte
    FileWriter fWarchivoReport;// nombre del método de escritura
    BufferedWriter bWarchivoReport;// nombre del buffer
    PrintWriter printArchivoReport;// metodo de escritura

    String listErrores;// nombre archivo
    File fErrorReport ; // configura creacion archivo reporte de fallas
    FileWriter fwErrorArchivoReport; // clase writer error
    BufferedWriter bWErrorArchivoReport;// buffer writer error
    PrintWriter printErrorArchivoReport;// escritura en archivo error en salida

    File fMedIntegradas;// Declaracion de atributos archivos para manejar resultados de salida de la COMPILACION
                        // Esta declaracion anticipada permite acceder a la variables desde distintos métodos
    double nroArchivosCompilados=0;// Cantidad de archivos compilados en MedIntegrado.TXT
    FileWriter fWMedIntegradas;
    BufferedWriter bWMedIntegradas;
    PrintWriter printMedIntegradas ;



    String directorio;// variable para configurar el directorio de trabajo
    String path;// establece el directorio actual como directorio de trabajo

    List<String> listaReporteDetalle;// Arreglo que funciona como buffer para acumular las lineas a enviar archivoDetalle
    List<String> listaReporteResumen;// Arreglo que funciona como buffer para acumular las lineas a enviar archivoReporte
    List<String> xlistaArchivos;// vector donde se arma la lista de archivos a ser procesados
    int cuentalosxListaArchivosProcesados=0;// esta variable ira contando los archivos procesados

    String tipodeFormatoaLeer="";// valores deben ser FORMATO_01 A FORMATO_NN
    int longitudMaxdeNombrexListaArchivos=8;


    // GETTERS A SETTERS

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

    public PrintWriter getintArchivoDet() {
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

    public String getDirectorio() {
        return directorio;
    }

    public void setDirectorio(String directorio) {
        this.directorio = directorio;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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

    public Setup getSetup() {
        return setup;
    }

    public void setSetup(Setup setup) {
        this.setup = setup;
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JProgressBar getProgressBar() {
        return progressBar;
    }

    public void setProgressBar(JProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    public String getLeyendoFormatox() {
        return leyendoFormatox;
    }

    public void setLeyendoFormatox(String leyendoFormatox) {
        this.leyendoFormatox = leyendoFormatox;
    }

    public String getTipodeFormatoaLeer() {
        return tipodeFormatoaLeer;
    }

    public void setTipodeFormatoaLeer(String tipodeFormatoaLeer) {
        this.tipodeFormatoaLeer = tipodeFormatoaLeer;
    }

    public int getCuentalosxListaArchivosProcesados() {
        return cuentalosxListaArchivosProcesados;
    }

    public void setCuentalosxListaArchivosProcesados(int cuentalosxListaArchivosProcesados) {
        this.cuentalosxListaArchivosProcesados = cuentalosxListaArchivosProcesados;
    }

    public double getkProgressbar() {
        return kProgressbar;
    }

    public void setkProgressbar(double kProgressbar) {
        this.kProgressbar = kProgressbar;
    }

    public File getfMedIntegradas() {
        return fMedIntegradas;
    }

    public void setfMedIntegradas(File fMedIntegradas) {
        this.fMedIntegradas = fMedIntegradas;
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

    public double getNroArchivosCompilados() {
        return nroArchivosCompilados;
    }

    public void setNroArchivosCompilados(int nroArchivosCompilados) {
        this.nroArchivosCompilados = nroArchivosCompilados;
    }

    public int getLongitudMaxdeNombrexListaArchivos() {
        return longitudMaxdeNombrexListaArchivos;
    }

    public void setLongitudMaxdeNombrexListaArchivos(int longitudMaxdeNombrexListaArchivos) {
        this.longitudMaxdeNombrexListaArchivos = longitudMaxdeNombrexListaArchivos;
    }

    public JFrame getFramePrincipal() {
        return framePrincipal;
    }

    public void setFramePrincipal(JFrame framePrincipal) {
        this.framePrincipal = framePrincipal;
    }


// CONSTRUCTOR

    public AbsMediciones(String tipodeFormatoaLeer) {
        this.tipodeFormatoaLeer = tipodeFormatoaLeer;
    }


    @Override
    protected Double doInBackground() throws Exception {
        System.out.println("doInBackground() esta en el hilo:  " + Thread.currentThread().getName());

        HiloPrincipalDevMediciones(); // llama al metodo PrincipalDevMediciones duplicado en cada claseFormatoxx


        for (int z = 0; z < 10; z++) {
           try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("interrumpido");
            }

            // Se pasa valor para la barra de progreso. ESto llamara al metodo
            // process() en el hilo de despacho de eventos.
            publish(z);
           if (progressBar.getValue()==100||cuentalosxListaArchivosProcesados==0){// Kill del HILO con el for de la variable Z
               break;//z=10000;
           }
        }

        // Supuesto resultado de la tarea que tarda mucho.
        System.out.println("RETORNO proceso background ");
        return 100.0;
    }

    @Override
    protected void process(List<Integer> chunks) {

        if (progressBar.getValue()==0 && cuentalosxListaArchivosProcesados==0){ // revisa avance de barra y archivos procesados
            frame.setTitle("MsgProcess: Revise carpeta de archivos!!: NO HAY ARCHIVOS PROCESABLES");
        }else {

            System.out.println("barra progreso "+cuentalosxListaArchivosProcesados);
        }
        System.out.println("Suichado por PROCESS en I= "+progressBar.getValue());
        //System.out.println("process() esta en el hilo "
         //       + Thread.currentThread().getName()+"| "+chunks.get(1)); // muestra si esta activo el hilo de proceso de barra de progreso

    }

    public void HiloPrincipalDevMediciones() throws IOException, ParseException {
        PrincipalDevMediciones(); // recibe la llamada y direcciona al PrincipalDevMediciones para crear el polimorfismo
                                // salta de acuerdo a la clase Mediciones que se este implementando
    }


    // ****   METODOS
    // Este método es sobre "PISADO" de acuerdo a las reglas de polimorfismo
    // y ejecuta el método con el mismo nombre en caso de existir en la clase AbsMediciones Formato_xx que se está implemntando
    public  void PrincipalDevMediciones() throws IOException, ParseException { // Aplicacion principal para el chequeo de las mediciones

        //::: Proceso 1: Lee configuracion y define directorio de trabajo
        //**** ********** *********
        setup.inicializaConfiguracionBaseTxt(); // carga los atributos desde el archivo del configuracion
        System.out.println("Confguracion cargad: "+ setup.stringConfiguracion()); // salida por consola de la configuracion

        //*******************************************************************************
        inicializaIoArchivos(); // esta rutina inicializa los archivos de entrada y salida además de instanciar Setup


        //  ******************  PROCESO LISTAR DIRECTORIO DE ARCHIVOS
        //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
        //-- Lista de Palabras de referencia que se identifican en cada tipo de archivo
        String palabraFormato1L1="\"Data\"";
        String palabraFormato2L1="";
        String palabraFormato3L1="";
        String palabraFormato4L1="";
        String palabraFormato5L1="";
        String palabraFormato6L1="meterId";
        String palabraFormato7L1="Equipo";
        String palabraFormato8L1="";
        String palabraFormato9L1="";
        String palabraFormato10L1="";
        String palabraFormato11L1="";
        String palabraFormato12L1="";

        // De existir coincidencia en el formato y nombre de la clase AbsArchivo + formato
        //instanciarClsAbsArchivo();
        int longitudDigitosNombreArchivo=8;
        AbsArchivo absArchivo=new AbsArchivo(palabraFormato7L1,0,getDirectorio(),tipodeFormatoaLeer,longitudDigitosNombreArchivo) ;// Define el formato a ser leido -->Instancia la clase Archivo e inicializa numerolinea con cero>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><

        System.out.println(" Formato base Abs 07 ------------------------>>>> el directorio definido que va a a clase Achivo es "+directorio);

        //**** ********** *********
        //setup.inicializaConfiguracionBaseTxt(); // carga los atributos desde el archivo del configuracion
        System.out.println("Confguracion cargad: "+ setup.stringConfiguracion()); // salida por consola de la configuracion

        //*******************************************************************************
        //inicializaIoArchivos(); // esta rutina inicializa los archivos de entrada y salida además de instanciar Setup

        armaENcabezadoArchReporte(); // Arma encabezados en los archivos: ReporteDetalle.txt y Reporte.txt

        String nombreArchivo=""; //// Inicializa variable
        int numlineas=0;// variable de uso local
        String [] listaArchivos =new String[0];
        xlistaArchivos =new ArrayList<>();// crea el arreglo tipo lista para contener archivos validos a procesar

        System.out.println(" supuesto directorio= "+directorio);
        xlistaArchivos=absArchivo.xlistarDirectorio(directorio,  setup.CnroAplicacionEspecial,longitudDigitosNombreArchivo);//Extrae lista de archivos validos en el directorio  a procesar metodo listar directorios


     // EJECUTA LA LECTURA DE MEDICIONES SEGUN LISTA DE ARCHIVOS
        //lista archivos a procesar segun lectura de los archivos
        //for (int i=1;i<xlistaArchivos.size();i++){
            //System.out.println(i+ "  archivo a procesa "+xlistaArchivos.get(i));
        //}

        // CONFIGURA lA BARRA DE PROGRESO
        //----------------------------------------
        // Inicio del despliegue de la barra
        frame=new JFrame("BAES MEDICIONES ");
        progressBar=new JProgressBar();
        progressBar.setStringPainted(true);
        progressBar.setValue(0); // inicializa el valor de avance en la barra
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(1,10));
        frame.setSize(780,160);              // Coloca el tamaño de la barra de progreso
        ImageIcon logoBaes=new ImageIcon("img\\logo_pie.jpg");
        frame.setIconImage(logoBaes.getImage().getScaledInstance(100,80,Image.SCALE_SMOOTH));
        frame.setLocationRelativeTo(null);
        frame.add(progressBar,0,0);
        frame.setVisible(true);

        //xyz se requiere el llamado a la rutina de carga del archivo de usuarios

        absArchivo.principalCompilacionArchivos(directorio);

        //************************************************************************************************************               ****   *****
        lazoprincipal2(absArchivo,nombreArchivo,numlineas); // Rutina complemento del lazo principal
        // >>>>*******************<< LAZO PARA BARRER TODOS LOS ARCHIVOS DETECTADOS EN EL DIRECTORIO**************     ********                     ******


        //*****************


        // FIN DE PROCESO DE LOS ARCHIVOS LISTADOS EN EL DIRECTORIO
        //***************************************************************************************************
        // ***  Este metodo vacia el buffer de los resultados en el bufferdearchivo de reporte <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        escribeResultadosenBufferReporte(absArchivo);//

        completarBarraProgreso();// Este método asegura llevar la barra de progreso hasta el 100%
        //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

        System.out.println("antes compilacion");

        // En este punto fueron leidos todos los archivos a ser procesados y generados los reportes de salida

        progressBar.setString("compilacion");

        completarBarraProgreso();


        if (xlistaArchivos.size()==0){//(progress<16) {
            progressBar.setValue(0);
            frame.setTitle("Revise!!: NO HAY ARCHIVOS QUE PROCESAR");
            frame.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent mouseEvent) {      }
                @Override
                public void mousePressed(MouseEvent mouseEvent) {      }

                @Override
                public void mouseReleased(MouseEvent mouseEvent) {      }

                @Override
                public void mouseEntered(MouseEvent mouseEvent) {   frame.dispose();      }

                @Override
                public void mouseExited(MouseEvent mouseEvent) {    frame.dispose();      }});
        }else{
            frame.setTitle("PROCESADOS CON EXITO "+xlistaArchivos.size()+ " ARCHIVOS");
            frame.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent mouseEvent) {             }
                @Override
                public void mousePressed(MouseEvent mouseEvent) {               }
                @Override
                public void mouseReleased(MouseEvent mouseEvent) {                }
                @Override
                public void mouseEntered(MouseEvent mouseEvent) {
                   // frame.dispose();

                }
                @Override
                public void mouseExited(MouseEvent mouseEvent) {

                    frame.dispose();
                }            });
        }
    } //********      FIN PRINCIPAL MEDICIONES  ---------------------------------------------------------------------



    // *********      Carga configuracion Setup
    public void cargaConfiguracionSetup() throws IOException {
        Setup setup=new Setup();// instancia la clase Setup, donde estan la configuración inicial que sera leida
        setup.inicializaConfiguracionBaseTxt(); //llama al metod que lee el archivo "configMed.txt"
        System.out.println("Confguracion cargad: "+setup.stringConfiguracion());
        //setup.mostrarConfiguracionCargada(); // Este metodo simplemente lista la configuracion por consola
        // ___ En este punto ya se cargaron los parámetros de configuracion del archivo

        String auxRegPath;
        // Incorpora la variable CRLF como Ascii
        StringBuffer sbCrLf = new StringBuffer();
        sbCrLf.append((char)13);
        sbCrLf.append((char)10);
        //String crlfTerminator = sbCrLf.toString();
        System.out.println("PROGRAMA PARA ANALIZAR ARCHIVOS DE MEDICION DE MUESTRAS  BAES Analizador  V 1.4 ");
    }

    //******** buffer con el String Ascii ************************
    public StringBuffer sbCrLf(){
        StringBuffer sbCrLf = new StringBuffer();
        sbCrLf.append((char)13);
        sbCrLf.append((char)10);
        return sbCrLf; // retorna un string con el Ascii CRLF
    }
    public StringBuffer tab(){
        StringBuffer tab=new StringBuffer();
        tab.append((char)9);
        return tab;
    }
//-------------------------------------
public StringBuffer spaceAscii(int n){ // regresa n espacios charAscii (20)
    StringBuffer spaceVisor = new StringBuffer();
    for (int i=0;i<n;i++) {
        spaceVisor.append(" ");
    }
    return spaceVisor;
}
    //---------------------------------
    //****** Configura instancias de IO archivos
    public void inicializaIoArchivos() throws IOException {


            // METODO DE INICIALIZACION DE LA SALIDA DE ARCHIVO en la clase registro




            //-----------------------------------------------------------------------------------
            // inicio de confirmacion del directorio de trabajo



        //***********************************************************************************
       // String sDirectorioTrabajo=System.getProperty("user.dir"); // crea la variable sDirectorioTRabajo
        //sDirectorioTrabajo= String.valueOf(jfc.getSelectedFile()); // inicializa la variable con el directorio seleccionado
        String sDirectorioTrabajo="";

        //------------- LAZO DE CONTROL DE CONFIRMACION del directorio de trabajo
        int resp=1; // 1=NO confirmado    0=confirma directorio
        boolean flagInicio=true;

        while (resp==1) {

            //---- Asignacion del directorio de trabajo con el método conocerDirectorioTrabajo
            sDirectorioTrabajo=conocerDirectorioTrabajo();// método despliegue Gui con explorador

            //resp = JOptionPane.showConfirmDialog(null, ("Confirme si  \""+ sDirectorioTrabajo.toUpperCase() + "\"  es el directorio de trabajo?? "),
            //        String.valueOf(JOptionPane.YES_NO_OPTION), JOptionPane.ERROR_MESSAGE);
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

        directorio=sDirectorioTrabajo;
        path=sDirectorioTrabajo;// establece el directorio actual como directorio de trabajo

        //File directorioResultados = new File(directorio);// instancia el objeto archivo tipo Directorio
        // directorioResultados.mkdirs();//crea un directorio
        //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<
        // ASIGNA NOMBRE A LOS ARCHIVOS DE SALIDA  E INSTANCIA LOS FILES Y PRINTER PARA GENERAR LAS SALIDAS

        partesDirString=sDirectorioTrabajo; // Estas dos lineas extraen el prefijo del directorio de trabajo
        String [] srtpartNombFile=partesDirString.split("\\\\"); // que conformara parte del nombre de archivos de salida

        File directorioResultados=new File(sDirectorioTrabajo+"\\Resultados");
        directorioResultados.mkdirs();

        archivoDetall=srtpartNombFile[srtpartNombFile.length-1]+"_Detalle.txt";// extrae parte del nombre del archivos en  el directorio
        //fDetalle = new File( sDirectorioTrabajo+"\\"+archivoDetall);
        fDetalle = new File( directorioResultados+"\\"+archivoDetall);
        fWarchivoDetalle = new FileWriter(fDetalle);
        bWarchivoDetalle= new BufferedWriter(fWarchivoDetalle);
        printArchivoDet = new PrintWriter(bWarchivoDetalle);

        archivoReport=srtpartNombFile[srtpartNombFile.length-1]+"_Reporte.txt";
        //fReport = new File( sDirectorioTrabajo+"\\"+archivoReport);
        fReport = new File( directorioResultados+"\\"+archivoReport);
        fWarchivoReport = new FileWriter(fReport);
        bWarchivoReport= new BufferedWriter(fWarchivoReport);
        printArchivoReport = new PrintWriter(bWarchivoReport);


        // implementacion del archivo de errores
        // String sDirectorioTrabajo=System.getProperty("user.dir"); // crea la variable sDirectorioTRabajo
        // String partesDirString=sDirectorioTrabajo; // Estas dos lineas extraen el prefijo del directorio de trabajo
        //  String [] srtpartNombFile=partesDirString.split("\\\\"); // que conformara parte del nombre de archivos de salida

        listErrores=srtpartNombFile[srtpartNombFile.length-1]+"_ReporteEventos.txt";
        //fErrorReport = new File( sDirectorioTrabajo+"\\"+listErrores);
        fErrorReport = new File( directorioResultados+"\\"+listErrores);
        fwErrorArchivoReport = new FileWriter(fErrorReport);
        bWErrorArchivoReport= new BufferedWriter(fwErrorArchivoReport);
        printErrorArchivoReport = new PrintWriter(bWErrorArchivoReport);

        // xyz Archivo de resultados de compilacion
// xyz ----> BLOQUE de armado de las MEDICIONES INTEGRADAS
        int varHabilitaCompilador= Integer.parseInt(setup.getBtnHabIntegrador().get(0));// BTN habiltacion compilacion en SETUP
        if(varHabilitaCompilador==1) {
            //fMedIntegradas = new File(sDirectorioTrabajo +"\\" + "MedIntegradas.txt");
            fMedIntegradas = new File(directorioResultados + "\\" + "MedIntegradas.txt");
            fWMedIntegradas = new FileWriter(fMedIntegradas);
            bWMedIntegradas = new BufferedWriter(fWMedIntegradas);
            printMedIntegradas = new PrintWriter(bWMedIntegradas);
        }
        System.out.println("regreso de inicializa archivos");

    }//-------------------- Fin implementacion IO salidas

    //-------------------------------------------------------------
    public String conocerDirectorioTrabajo(){
        String directorioTrabajo="";


        // CONOCER EL DIRECTORIO DE TRABAJO
        Boolean old = UIManager.getBoolean("FileChooser.readOnly");// apaga edicion
        UIManager.put("FileChooser.readOnly", Boolean.TRUE);// apaga edicion
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        UIManager.put("FileChooser.readOnly", old);//======================================== apaga edicion

        jfc.setDialogTitle("SELECCIONE UN DIRECTORIO PARA REALIZAR EL ANALISIS DE MEDICIONES " + tipodeFormatoaLeer);
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

    //--------------------------------------------------------------

    // **** PROCESO vaciado del buffer con resultados del procesamiento de los archivos
        public void escribeResultadosenBufferReporte(AbsArchivo absArchivo) throws IOException {
            // Barre el arreglo llenado con las lineas de informacion de los archivos y los escribe en el archivo de reporte
            for (int rD=0; rD<listaReporteDetalle.size();rD++) {
                //registro.adEscribirRegistro(listaReporteDetalle.get(rD));//Descarga lista en archivo de Detalle
                printArchivoDet.write(listaReporteDetalle.get(rD));

            }

            for (int rR=0;rR<listaReporteResumen.size();rR++){
                //registroResumen.adEscribirRegistroResumen(listaReporteResumen.get(rR));
                printArchivoReport.write(listaReporteResumen.get(rR));
            }

            for (int rErr=0;rErr<absArchivo.listaErroresArch.size();rErr++){
                printErrorArchivoReport.write(String.valueOf(absArchivo.listaErroresArch.get(rErr)));
            }

            //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><



            printArchivoDet.close(); //cierre de los archivos de reporte q se encuentran declarados al inicio del Main
            bWarchivoDetalle.close();
            printArchivoReport.close();
            bWarchivoReport.close();

            printErrorArchivoReport.close(); //cierre de los archivos de reporte q se encuentran declarados al inicio del Main
            bWErrorArchivoReport.close();

// xyz ----> inhabilita la creacion del archivo
            int varHabilitaCompilador= Integer.parseInt(setup.getBtnHabIntegrador().get(0));// BTN habiltacion compilacion en SETUP
            if(varHabilitaCompilador==1) {
                printMedIntegradas.close(); //xyz cierre del archivo con la compilacion
                bWMedIntegradas.close();
            }
        }


    //********* ENCABEZADOS

    public void armaENcabezadoArchReporte() {// de los archivos de salida
        System.out.println("inicio metodo armaEncabezadoArchReporte");

        SimpleDateFormat dateFormat = new SimpleDateFormat("d'/'MM'/'yyyy 'hora:'HH:mm");
        String currentDate=dateFormat.format(new Date());


        printErrorArchivoReport.write("RESUMEN DE EVENTOS    "+ "Fecha del reporte:  "+ String.valueOf(currentDate)+sbCrLf()+ sbCrLf());

        // Registro registro=new Registro(sDirectorioTrabajo);// Instancia Clase REGISTRO QUE CONTIENE Printer Detalle
        String nombreArchivoResumen="Valida Mediciones - Resumen.txt";
        //RegistroResumen registroResumen=new RegistroResumen(sDirectorioTrabajo,nombreArchivoResumen);// Instancia Clase REGISTRO QUE CONTIENE LOS PRINTER Resumen
        //RegistroResumen registroResumen=new RegistroResumen();// Instancia Clase REGISTRO QUE CONTIENE LOS PRINTER Resumen

        // INICIO REPORTE DETALLE
        listaReporteDetalle =new ArrayList<String>();// contenedor de los datos que se imprimiran en el reporte
        listaReporteDetalle.add("                 VALIDACION DE MEDICIONES - DETALLE " + sbCrLf());
        listaReporteDetalle.add("             "+currentDate+ " - "+ tipodeFormatoaLeer + sbCrLf());
        String ldivision="__________________________________________________________________________________________________"+sbCrLf();
        listaReporteDetalle.add(ldivision);

        // Inicio REPORTE RESUMEN
        listaReporteResumen =new ArrayList<String>();// contenedor de los datos que se imprimiran en el reporte
        listaReporteResumen.add("                 VALIDACION DE MEDICIONES - RESUMEN "+sbCrLf());
        listaReporteResumen.add("              "+currentDate+ " - "+ tipodeFormatoaLeer+sbCrLf());
        listaReporteResumen.add(ldivision);
        listaReporteResumen.add("LEYENDA DE CODIGOS DE CUMPLIMIENTO APLICADOS A LAS MEDICIONES OBSERVADAS - "+tipodeFormatoaLeer+sbCrLf()+sbCrLf());
        listaReporteResumen.add( setup.estadoControlHabilitado("A")+tab()+"  Fecha final real - Fecha inicial real < "+  setup.CdiasPermanencia+" días de registros válidos"+ sbCrLf());// carga de setup umbral
        listaReporteResumen.add( setup.estadoControlHabilitado("B")+tab()+"  Duración mínima para validar registro < "+ setup.getCdiasMinValidez()+" días de registros válidos"+ sbCrLf());
        listaReporteResumen.add( setup.estadoControlHabilitado("C")+tab()+"  Diferencia de intervalos que sean distintas a "+ setup.getCcadencia()+" minutos en más del 10% de las muestras "+sbCrLf());
        listaReporteResumen.add( setup.estadoControlHabilitado("D")+tab()+"  Formato de fecha y hora incorrecta (dd/mm/yy, hh:mm) en registros sin 'Anormalidad'   "+sbCrLf());
        listaReporteResumen.add( setup.estadoControlHabilitado("E")+tab()+"  Energía registrada igual a cero en todos los registros válidos o No existencia de registros válidos"+sbCrLf());
        listaReporteResumen.add( setup.estadoControlHabilitado("F")+tab()+"  Energía con igual valor (distinto de cero) entre muestras válidas consecutivas en más del 90% de los casos "+sbCrLf());
        listaReporteResumen.add( setup.estadoControlHabilitado("G")+tab()+"  Nombre de archivo mal asignado"+sbCrLf());
        listaReporteResumen.add( setup.estadoControlHabilitado("H")+tab()+"  Valores negativos de energía en uno o varios registros sin 'Anormalidad'"+sbCrLf());
        listaReporteResumen.add(ldivision);
        listaReporteResumen.add("RESULTADOS: "+sbCrLf()+sbCrLf());
        // listaReporteResumen.add("     Nro."+"  Nombre Archivo "+"  Tipo (M/T)"+"  Calificación\n");


        // AJUSTA ENCABEZADO DE ACUERDO AL FORMATO
        if (tipodeFormatoaLeer.equals("FORMATO_05")){
            listaReporteResumen.add("NOMBRE ARCHIVO"+tab()+tab()+"CALIFICACION" );
        }else{
            listaReporteResumen.add("NOMBRE ARCHIVO"+spaceAscii(longitudMaxdeNombrexListaArchivos)+"CALIFICACION" );
        }

        listaReporteResumen.add(""+sbCrLf());
        // INICIO ENCABEZADO ARCHIVO DE SALIDA  DE COMPILACION
        //Encabezado de MedIntegrada
       //SE ESCRIBE EL ENCABEZADO EN CADA UNA DE LAS CLASES DE MEDICION DE ACUERDO  A LAS VARIABLES QUE CORRESPONDA
        // printMedIntegradas.write("Fechas"+"|"+"Hora"+"|"+"Voltaje"+"|"+"Potencia"+"|"+"FP"+"|"+"Anomalia"+"|"+
        //        "IdUsuario"+"|"+"Tarifa-Estrato"+"|"+"NombreArchivo"+"|"+"MultiploUnidadPotencia"+sbCrLf());

    }

    //--------------------------------------------------------------------------
    //********* ENCABEZADOS

    public void armaENcabezadoArchReporte0405() {// de los archivos de salida
        System.out.println("inicio metodo armaEncabezadoArchReporte");

        //Date currentDate = Calendar.getInstance(TimeZone.getDefault()).getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("d'/'MM'/'yyyy 'hora:'HH:mm");
        String currentDate=dateFormat.format(new Date());


        printErrorArchivoReport.write("RESUMEN DE EVENTOS    "+ "Fecha del reporte:  "+ String.valueOf(currentDate)+sbCrLf()+ sbCrLf());

        // Registro registro=new Registro(sDirectorioTrabajo);// Instancia Clase REGISTRO QUE CONTIENE Printer Detalle
        String nombreArchivoResumen="Valida Mediciones - Resumen.txt";
        //RegistroResumen registroResumen=new RegistroResumen(sDirectorioTrabajo,nombreArchivoResumen);// Instancia Clase REGISTRO QUE CONTIENE LOS PRINTER Resumen
        //RegistroResumen registroResumen=new RegistroResumen();// Instancia Clase REGISTRO QUE CONTIENE LOS PRINTER Resumen

        // INICIO REPORTE DETALLE
        listaReporteDetalle =new ArrayList<String>();// contenedor de los datos que se imprimiran en el reporte
        listaReporteDetalle.add("                 VALIDACION DE MEDICIONES - DETALLE " + sbCrLf());
        listaReporteDetalle.add("                  "+currentDate+ " - "+ tipodeFormatoaLeer + sbCrLf());
        String ldivision="__________________________________________________________________________________________________"+sbCrLf();
        listaReporteDetalle.add(ldivision);

        // Inicio REPORTE RESUMEN ENCABEZADO
        listaReporteResumen =new ArrayList<String>();// contenedor de los datos que se imprimiran en el reporte
        listaReporteResumen.add("                 VALIDACION DE MEDICIONES - RESUMEN "+sbCrLf());
        listaReporteResumen.add("                  "+currentDate+ " - "+ tipodeFormatoaLeer+sbCrLf());
        listaReporteResumen.add(ldivision);
        listaReporteResumen.add("LEYENDA DE CODIGOS DE CUMPLIMIENTO APLICADOS A LAS MEDICIONES OBSERVADAS - "+tipodeFormatoaLeer+sbCrLf()+sbCrLf());
        listaReporteResumen.add( setup.estadoControlHabilitado("A")+tab()+"  Fecha final real - Fecha inicial real < "+  setup.CdiasPermanencia+ sbCrLf());// carga de setup umbral
        listaReporteResumen.add( setup.estadoControlHabilitado("B")+tab()+"  Duración mínima para validar registro < "+ setup.getCdiasMinValidez()+" días de registros válidos"+ sbCrLf());
        listaReporteResumen.add( setup.estadoControlHabilitado("C")+tab()+"  Diferencia de intervalos que sean distintas a "+ setup.getCcadencia()+" minutos "+sbCrLf());
        listaReporteResumen.add( setup.estadoControlHabilitado("D")+tab()+"  Formato de fecha y hora incorrecta (mm/dd/yyyy, hh:mm) en registros sin 'Anormalidad'   "+sbCrLf());
        listaReporteResumen.add( setup.estadoControlHabilitado("E")+tab()+"  Energía registrada igual a cero en todos los registros válidos o No existencia de registros válidos"+sbCrLf());
        listaReporteResumen.add( setup.estadoControlHabilitado("F")+tab()+"  Energía con igual valor (distinto de cero) entre muestras válidas consecutivas"+sbCrLf());
        listaReporteResumen.add( setup.estadoControlHabilitado("G")+tab()+"  Nombre de archivo mal asignado"+sbCrLf());
        listaReporteResumen.add( setup.estadoControlHabilitado("H")+tab()+"  Valores negativos de energía en uno o varios registros sin 'Anormalidad'"+sbCrLf());
        listaReporteResumen.add( " "+ setup.estadoControlHabilitado("I")+tab()+"  Energía total reactiva superior al total de energia real "+sbCrLf());
        listaReporteResumen.add(ldivision);
        listaReporteResumen.add("RESULTADOS: "+sbCrLf()+sbCrLf());
        // listaReporteResumen.add("     Nro."+"  Nombre Archivo "+"  Tipo (M/T)"+"  Calificación\n");


        // AJUSTA ENCABEZADO DE ACUERDO AL FORMATO
        if (tipodeFormatoaLeer.equals("FORMATO_05")){
            listaReporteResumen.add("NOMBRE ARCHIVO"+tab()+tab()+"CALIFICACION" );
        }else{
            listaReporteResumen.add("NOMBRE ARCHIVO"+spaceAscii(longitudMaxdeNombrexListaArchivos)+"CALIFICACION" );
        }

        listaReporteResumen.add(""+sbCrLf());
        // INICIO ENCABEZADO ARCHIVO DE SALIDA  DE COMPILACION
        //Encabezado de MedIntegrada
        //SE ESCRIBE EL ENCABEZADO EN CADA UNA DE LAS CLASES DE MEDICION DE ACUERDO  A LAS VARIABLES QUE CORRESPONDA
        // printMedIntegradas.write("Fechas"+"|"+"Hora"+"|"+"Voltaje"+"|"+"Potencia"+"|"+"FP"+"|"+"Anomalia"+"|"+
        //        "IdUsuario"+"|"+"Tarifa-Estrato"+"|"+"NombreArchivo"+"|"+"MultiploUnidadPotencia"+sbCrLf());

    }


    //-------------------------------------------------------------------------

  // **** Rutina lazoprincipal2
    public void lazoprincipal2(AbsArchivo absArchivo, String nombreArchivo,int numlineas) throws IOException, ParseException {


        // >>>>*******************<< LAZO PARA BARRER TODOS LOS ARCHIVOS DETECTADOS EN EL DIRECTORIO**************     ********                     ******

        for (int l=0;l<xlistaArchivos.size();l++) {
            cuentalosxListaArchivosProcesados=cuentalosxListaArchivosProcesados+1;// este contador contiene el numero de archivos procesados
            // LIMPIA E INICIALIZA LAS VARIABLES PARA COMENZAR UN NUEVO CICLO DE LECTURA EN EL PROXIMO ARCHIVO

            absArchivo.incializaValoresClaseArchivo();//inicializavalores de los arreglos y variables a utiiizar
            //************ SE CARGA NUEVO ARCHIVO DE LA LISTA A SER ANAALIZADO ____________________********************************_______________

            nombreArchivo = xlistaArchivos.get(l);                   //Carga el nuevo valor del archivo a ser analizado

            absArchivo.nombArchivoParaError = nombreArchivo = xlistaArchivos.get(l); // actualiza el nombre del archivo a ser procesado solo para el manejo de errores

            incrementarProgressBar(nombreArchivo); //Actualiza barra Progressbar
            //___________________________________________________________________________________________________________________________________________

            System.out.println("El archivo a ser procesado es " + nombreArchivo);

            // ***** Carga el archivo de la lista y llama al metodo contar lineas

            numlineas = absArchivo.contarLineas(path, nombreArchivo, setup.CnroAplicacionEspecial);//Llama al método contar lineas clase Archivo y asigna configuracion y
            // tipo de archivo
            // ajusta valor de inicializacion con valor real deL nro de lineas

            //DERIVACION DE CASOS SEGUN EL FORMATO A LEER
            if ((setup.CnroAplicacionEspecial == 0) && (tipodeFormatoaLeer.equals("FORMATO_01"))) { // no hay caso especial y se habilita el metodo segun FORMATO XX

            }
            if ((setup.CnroAplicacionEspecial == 0) && (tipodeFormatoaLeer.equals("FORMATO_02"))) {
                // no hay caso especial y se habilita el metodo segun FORMATO XX

            }
            if ((setup.CnroAplicacionEspecial == 0) && (tipodeFormatoaLeer.equals("FORMATO_03"))) { // no hay caso especial y se habilita el metodo segun FORMATO XX

            }
            if ((setup.CnroAplicacionEspecial == 0) && (tipodeFormatoaLeer.equals("FORMATO_04"))) { // no hay caso especial y se habilita el metodo segun FORMATO XX

            }
            if ((setup.CnroAplicacionEspecial == 0) && (tipodeFormatoaLeer.equals("FORMATO_05"))) { // no hay caso especial y se habilita el metodo segun FORMATO XX

            }
            if ((setup.CnroAplicacionEspecial == 0) && (tipodeFormatoaLeer.equals("FORMATO_06"))) { // no hay caso especial y se habilita el metodo segun FORMATO XX

            }

            if ((setup.CnroAplicacionEspecial == 0) && (tipodeFormatoaLeer.equals("FORMATO_07"))) { // no hay caso especial y se habilita el metodo segun FORMATO XX
                System.out.println("<< ESTA ES LA DERIVACION FORMATO 07 ------------------");
                if ((absArchivo.tipoArchivo == "M")) {             // ******* Define procesamiento de los archivos tipo MONOFASICO
                    try{
                        absArchivo.generarTokensMonofasico(path, nombreArchivo, numlineas); //**** llama PROCESAR EL ARCHIVO MONOFASICO CON LAS MUESTRAS
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("error Formato 7 IO");
                    } catch (ParseException e) {
                        e.printStackTrace();
                        System.out.println("error Formato 7 parse");
                    }

                } else { // ***************                 Define tratamiento de los archivos TRIFASICOS
                    absArchivo.generarTokensTRIFASICO(path, nombreArchivo, numlineas);
                }
            }
            if ((setup.CnroAplicacionEspecial == 0) && (tipodeFormatoaLeer.equals("FORMATO_08"))) { // no hay caso especial y se habilita el metodo segun FORMATO XX

            }
            if ((setup.CnroAplicacionEspecial == 0) && (tipodeFormatoaLeer.equals("FORMATO_09"))) { // no hay caso especial y se habilita el metodo segun FORMATO XX

            }

// TRATAMIENTO DE CASOS ESPECIALES  en Setup CnroAplicacionEspecial=1 que era san juan

            if ((setup.CnroAplicacionEspecial == 1)) { // no hay caso especial y se habilita el metodo segun FORMATO XX

                // System.out.println("Inicio del proceso de casos especial Nro. "+setup.CnroAplicacionEspecial);
                //absArchivo.generarTokensEspecialUno(path, nombreArchivo, numlineas); // llama al metodo para leer las muestras del archivo ESPECIAL

                // CASO numero 1 Provincia de San Juan
                //---------------------------------------
            }


            //********   ESCRIBE EN EL ARCHIVO DE RESULTADOS  LA LEYENDA DEL ENCABEZADO   **********
            String partesNombArchString = nombreArchivo; // Estas dos lineas extraen el prefijo del directorio de trabajo
            String[] parteDelNombre = partesNombArchString.split("\\\\"); // que conforma el nombre de archivos analizado
            try {
                listaReporteDetalle.add(" NOMBRE DEL ARCHIVO:           " + parteDelNombre[parteDelNombre.length - 1].toUpperCase() + sbCrLf());
                listaReporteDetalle.add(" Tipo de registro:              " + absArchivo.tipoArchivo +sbCrLf());
                listaReporteDetalle.add(" Fecha real inicio de medición: " + absArchivo.archFecha.get(0) + " " + absArchivo.archHora.get(0) + sbCrLf());
                listaReporteDetalle.add(" Fecha real Final de medición:  " + absArchivo.archFecha.get(absArchivo.archFecha.size() - 1) +
                        " " + absArchivo.archHora.get(absArchivo.archHora.size() - 1) + sbCrLf());

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error de asignación por Formato");
                JOptionPane.showMessageDialog(null, "Se encontró un error de asignacion de valores con el archivo: " + nombreArchivo);
                System.exit(0);
            }
            double redondeaEnergia = Math.round(absArchivo.archTotalEnergia);// Redondeando decimales de Energia  a dos

            // calcula el factor de potencia y lo formatea con 3 decimales -----------------------------
            String promFP;
            DecimalFormat fpformat = new DecimalFormat("##.###");
            promFP = fpformat.format(absArchivo.calcularPromedio(absArchivo.archFP));

            //_______________________________________________________________________________________

            listaReporteDetalle.add(" Número total de registros:     " + absArchivo.archFecha.size() + sbCrLf());
            listaReporteDetalle.add(" Nuúmero de registros vlidos:  ´" + (absArchivo.archFecha.size() - absArchivo.numMuestraAnormal) + sbCrLf());
            listaReporteDetalle.add(" Energía total registrada:      " + redondeaEnergia + " "+ absArchivo.multiploUnidadPotencia+"h"+sbCrLf());
            listaReporteDetalle.add(" Factor de carga:               " + promFP + sbCrLf()); // tomado de promediar valores ver linea 205
            listaReporteDetalle.add(" Número de Registro observados: " + absArchivo.numMuestraAnormal + sbCrLf());
            listaReporteDetalle.add(" Duración medición (dias):      "
                    + (absArchivo.calcularDiasString(absArchivo.archFecha.get((absArchivo.archFecha.size() - 1)).toString(),
                    (absArchivo.archFecha.get(0).toString()))) + "" + sbCrLf());
            String ldivision = "__________________________________________________________________________________________________" + sbCrLf();

            listaReporteDetalle.add(ldivision + sbCrLf());
            //******************************************************************************
            //*******************   ESCRIBE EN EL ARCHIVO DE RESULTADOS LOS RESUMEN DE cada ARCHIVO **********

            // ** SE GENERAN LOS CONTROLES DE BRECHA EN EL METODO  QUE SIGUE CALIFICAR MEDICION
            // El encabezado fue eliminado y las siguientes lineas solo colocan los valores
            listaReporteResumen.add(parteDelNombre[parteDelNombre.length - 1].toUpperCase() +"\t" +
                    absArchivo.calificarMedicionM(nombreArchivo,   //                                LLama al metodo de calificacion de
                            setup.CinicialesNombEmpresas,
                            setup.CdiasPermanencia, setup.CdiasMinValidez,
                            setup.CDigitoAnoValido, setup.ChabilitacionChequeo, setup.Ccadencia,setup.getCestratosList(),setup.getCnombTarifas()) + sbCrLf());


            // ----> BLOQUE de armado de las MEDICIONES INTEGRADAS
            for (int i = 0; i < absArchivo.archHora.size(); i++) {// inicializa el lazo para barrer totos los registros leidos
                  printMedIntegradas.write(absArchivo.archFecha.get(i) + "|" + absArchivo.archHora.get(i) + "|" +
                     absArchivo.archVoltaje.get(i) + "|" + absArchivo.archPotencia.get(i) + "|" + absArchivo.archFP.get(i) + "|" +
                     absArchivo.archflagAnormalidad.get(i) + "|anormalidad="+absArchivo.archflagAnormalidad.get(i)+"|"+
                     absArchivo.buscarUsuarioxNombArchivoInstalacion(absArchivo.extraeNombreArchivoBaseSinrutaNiextension(nombreArchivo))+
                     "|"+absArchivo.buscarTarifaUsuarioxNombArchivoInstalacion(absArchivo.extraeNombreArchivoBaseSinrutaNiextension(nombreArchivo))+
                     "|"+ absArchivo.extraeNombreArchivoBaseSinrutaNiextension(nombreArchivo)+//
                     "|"+ absArchivo.multiploUnidadPotencia+sbCrLf());// Escritura de las variables para armar el archivo MedIntegradas

            } // ******     Fin del lazo de lectura de los archivos de registros de medicion

        }//-----------------------------------------------------------------------------------------------------------------------

    }  // FIN LAZOPRINCIPAL2 ---

    // Actualiza Barra de progresso
    public void incrementarProgressBar(String nombArchivo){
        int tempValorincremento=0;
        double numeroArchivos=xlistaArchivos.size();
        if (xlistaArchivos.size()<=100) {
            tempValorincremento=(int) (100/xlistaArchivos.size());
            progressBar.setValue(progressBar.getValue() + tempValorincremento);
        }else{
            int factor=100;
            System.out.println("kProgressBar= "+kProgressbar);
            if(kProgressbar>=Math.round(numeroArchivos/factor)){// si supera el valr K de escalamiento
                                                                                       // genera un incremento de la barraProgreso reinicia K=0
               kProgressbar=0;
               tempValorincremento= 1;
               progressBar.setValue(progressBar.getValue() + tempValorincremento);
            }else{
                kProgressbar=kProgressbar+1; // si es no supera el factor "xlist/factor" incrementa
            }
        }
        System.out.println("NombArchivo "+nombArchivo+" VALORBARRA= "+progressBar.getValue()+
                " |valor de cuentaArchProcesados "+cuentalosxListaArchivosProcesados+
                " |Total Archivos a procesar  "+xlistaArchivos.size());
        frame.setTitle("Procesando archivo:" + nombArchivo);

        if (cuentalosxListaArchivosProcesados<1){// en caso de no existir archivos válidos impide cambios en Barra de Progreso
            progressBar.setValue(0);
            frame.setTitle("Msg B.Progreso:Revise carpeta de archivos!!: NO HAY ARCHIVOS PROCESABLES");
        }
    }
//-----------------------------------------------------------------------------------

    public void completarBarraProgreso(){ // termina de completar la barra al 100%
        while (progressBar.getValue()<100){
            progressBar.setValue(progressBar.getValue()+1);
        }

    }

    // IMPLEMENTACION DE COMPILACION DE LOS ARCHIVOS DE MEDICION CON NUMERO DE  USUARIOS


    //DESPLEGAR RESULTADOS DE LA CALIFICACION
    public void presentarResultadosCalificacion(String formato,String directorio) throws IOException {
        // Crea el Frame donde se vaciaran los datos
        JFrame frameResultados = new JFrame("VISOR DE RESULTADOS - "+formato+"  Directorio: "+directorio);
        frameResultados.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frameResultados.setLayout(new GridLayout(1, 2));
        frameResultados.setSize(880, 600); // tamaño del contenedor Jframe
        frameResultados.setLocationRelativeTo(null);
        frameResultados.setVisible(true);
        ImageIcon logoBaes=new ImageIcon("img\\logo_pie.jpg");
        frameResultados.setIconImage(logoBaes.getImage().getScaledInstance(100,80,Image.SCALE_SMOOTH));

        TextArea textArea = new TextArea("");
            textArea.setFont(new Font("Arial", Font.ROMAN_BASELINE, 12));

        frameResultados.add(textArea);

        textArea.isVisible();
        frameResultados.setDefaultCloseOperation(1);// 0 Nada| 1=dispose | 3=exit from program

        // Extrae el ultimo tramo del directorio de trabajo
        String directorioTrabajo=directorio;
        String[] partesDirectorioTrabajo = directorioTrabajo.split("\\\\");
        System.out.println("componenteBuscado= "+partesDirectorioTrabajo[partesDirectorioTrabajo.length-1]);
        //Lectura de Archivo de errores
        FileReader errores =new FileReader(directorio+"\\Resultados\\"+partesDirectorioTrabajo[partesDirectorioTrabajo.length-1]+"_ReporteEventos.txt");
        BufferedReader breporteErrores = new BufferedReader(errores,2000 * 8192);
        String blinea;
       // textArea.append("RESUMEN DE LOS ERRORES ENCONTRADOS"+sbCrLf());
        while ((blinea = breporteErrores.readLine()) != null) {
            textArea.append(blinea + (char) 13 + (char) 10 );
        }

        textArea.append(sbCrLf()+"_________________________________________________________________________________________________"+sbCrLf()+sbCrLf());

        // Lectura del archivo de Reporte
        FileReader reporte =new FileReader(directorio+"\\Resultados\\"+partesDirectorioTrabajo[partesDirectorioTrabajo.length-1]+"_Reporte.txt");
        BufferedReader breporte = new BufferedReader(reporte,2000 * 8192);


        //Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+ directorio+"\\Resultados\\"+
         //       partesDirectorioTrabajo[partesDirectorioTrabajo.length-1]+"_Reporte.txt");

        while ((blinea = breporte.readLine()) != null) {// Lectura Archivo de Reporte
            textArea.append(blinea + (char) 13 + (char) 10);
        }


        // Lectura del archivo de Detalle
        textArea.append(sbCrLf() +  "_________________________________________________________________________________________________"
                + sbCrLf() + sbCrLf());
        FileReader detalle =new FileReader(directorio+"\\Resultados\\"+partesDirectorioTrabajo[partesDirectorioTrabajo.length-1]+"_Detalle.txt");
        BufferedReader bDetalle = new BufferedReader(detalle,2000 * 8192);
        //Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+ directorio+"\\Resultados\\"+
         //       partesDirectorioTrabajo[partesDirectorioTrabajo.length-1]+"_Detalle.txt");


        while ((blinea = bDetalle.readLine()) != null) { // Lectura rchivo de detalles
            textArea.append(blinea + sbCrLf());

        }

        textArea.setCaretPosition(1);// LLeva el cursor a ala posicion inicial
        textArea.requestFocus();// Hace foco sobre el TextArea
    }
    //----------------------------------
    //Genera un "FALSE" de return en caso de identificar la etra D en la calificacion
    public boolean flagPresentardiasenResumen(String calificacion){
        boolean flag=true;
        for(int i=0;i<calificacion.length();i++){
            if(calificacion.charAt(i)=='D'){
                flag=false;
                break;
            }
        }
        System.out.println("resultado= "+flag);
        return flag;
    }

    //-------------------------------------------








  }







