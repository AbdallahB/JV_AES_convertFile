package com.company;
import javax.swing.*;
import java.io.*;
import java.sql.SQLOutput;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class CompilArchivo {


    //*****  NUEVAS VARIABLE INCORPORADAS PARA RELACIONAR LOS ARCHIVOS DE MEDICIONES CON EL ID DE USUARIOS
    //       VARIABLES LEIDAS DEL ARCHIVO PLANILLA_XXXXX ADEMÁS   **********************************************
    public List<String> idUsuarioArchInstalacion = new ArrayList<>(); // Campo "IdUsuario" leido del archivo complementario " Planilla...."
    public List<String> nombArchMedicionInstalacionTXT = new ArrayList<>(); // Campo "Nombre del archivo de mediciones" leido del archivo complementario " Planilla...."
    public List<String> tarifaPlanillaTXT = new ArrayList<>(); // Campo "Tarifa asociado al usuario" leido del archivo complementario " Planilla...."
    public int flagArchPlanillaDecartable = 0; // "0" el archivo no es planilla  "1" archivo descartable de ser abalizado

    public List<String> archPlanillaIdUsuarioArchInstalacion = new ArrayList<>(); // Campo "IdUsuario" leido del archivo complementario " Planilla...."
    public List<String> archPlanillaNombArchMedicionInstalacionTXT = new ArrayList<>(); // Campo "Nombre del archivo de mediciones" leido del archivo complementario " Planilla...."
    public List<String> archPlanillaTarifaPlanillaTXT = new ArrayList<>(); // Campo "Tarifa asociado al usuario" leido del archivo complementario " Planilla...."


    // INICIALIZACION DE ATRIBUTOS DE LA CLASE:  ARREGLOS Y VARIABLES A SER UTILIZADOS EN EL PROCESAMIENTO DEL ARCHIVO

    public int lineasEncabezadoArchivoMonofasico = 14; //Valor para identificar el valor del encabezado monofásico
    public int AutolineasEncabezadoArchivoMonofasico = 10; //Valor para identificar el valor del encabezado monofásico
    public int lineasEncabezadoArchivoTrifasico = 14; //Valor para identificar el valor del encabezado Trifásico

    public List listaPrueba = new ArrayList();
    public String tipoArchivo = "M"; //Valor que define el tipo de archivo a leer "M" Monofasico "T" trifasico  "E" Especial

    public int tipoToroide = 0; // valor de del toroide valores= 800/200/100/35
    public String strTipoToroide = "0"; // Valor inicial
    public String  multiploUnidadPotencia=""; // Variable que permite  contener el valor Multiplo de la unidad de potencia
    // de cada archivo durante su procesamiento

    public int archtamaño;
    public String archnombre;
    public int Anumlineas;
    public List<String> archFecha = new ArrayList<>();
    public List<String> archHora = new ArrayList<>();
    public List<Double> archVoltaje = new ArrayList<>();
    public List<Double> archVoltajeB = new ArrayList<>();
    public List<Double> archVoltajeC = new ArrayList<>();

    public List<Double> archCorriente = new ArrayList<>();
    public List<Double> archEnergia = new ArrayList<>();

    public List<Double> archFP = new ArrayList<>();
    public List<String> archAnormalidad = new ArrayList<>();
    public List<String> archflagAnormalidad = new ArrayList<>();

    public List<Integer> nroMuestraconAarchAnormalidad = new ArrayList<>();

    public int numMuestraAnormal = 0;
    public double archTotalEnergia = 0.00;


    // CONSTRUCTOR
    public CompilArchivo(int anumlineas) {
        Anumlineas = anumlineas;
    }
    //*********************************************************
    // GETTER y SETTEr


    public int getFlagArchPlanillaDecartable() {
        return flagArchPlanillaDecartable;
    }

    public void setFlagArchPlanillaDecartable(int flagArchPlanillaDecartable) {
        this.flagArchPlanillaDecartable = flagArchPlanillaDecartable;
    }

    public List<String> getArchPlanillaIdUsuarioArchInstalacion() {
        return archPlanillaIdUsuarioArchInstalacion;
    }

    public void setArchPlanillaIdUsuarioArchInstalacion(List<String> archPlanillaIdUsuarioArchInstalacion) {
        this.archPlanillaIdUsuarioArchInstalacion = archPlanillaIdUsuarioArchInstalacion;
    }

    public List<String> getArchPlanillaNombArchMedicionInstalacionTXT() {
        return archPlanillaNombArchMedicionInstalacionTXT;
    }

    public void setArchPlanillaNombArchMedicionInstalacionTXT(List<String> archPlanillaNombArchMedicionInstalacionTXT) {
        this.archPlanillaNombArchMedicionInstalacionTXT = archPlanillaNombArchMedicionInstalacionTXT;
    }

    public List<String> getArchPlanillaTarifaPlanillaTXT() {
        return archPlanillaTarifaPlanillaTXT;
    }

    public void setArchPlanillaTarifaPlanillaTXT(List<String> archPlanillaTarifaPlanillaTXT) {
        this.archPlanillaTarifaPlanillaTXT = archPlanillaTarifaPlanillaTXT;
    }

    public int getLineasEncabezadoArchivoMonofasico() {
        return lineasEncabezadoArchivoMonofasico;
    }

    public void setLineasEncabezadoArchivoMonofasico(int lineasEncabezadoArchivoMonofasico) {
        this.lineasEncabezadoArchivoMonofasico = lineasEncabezadoArchivoMonofasico;
    }

    public int getAutolineasEncabezadoArchivoMonofasico() {
        return AutolineasEncabezadoArchivoMonofasico;
    }

    public void setAutolineasEncabezadoArchivoMonofasico(int autolineasEncabezadoArchivoMonofasico) {
        AutolineasEncabezadoArchivoMonofasico = autolineasEncabezadoArchivoMonofasico;
    }

    public int getLineasEncabezadoArchivoTrifasico() {
        return lineasEncabezadoArchivoTrifasico;
    }

    public void setLineasEncabezadoArchivoTrifasico(int lineasEncabezadoArchivoTrifasico) {
        this.lineasEncabezadoArchivoTrifasico = lineasEncabezadoArchivoTrifasico;
    }

    public int getTipoToroide() {
        return tipoToroide;
    }

    public void setTipoToroide(int tipoToroide) {
        this.tipoToroide = tipoToroide;
    }

    public int contarLineas(String path, String nombArch, int condicionEspecial) throws IOException {

        //CONTAR LINEAS DEL ARCHIVO SELECCIONADO
        //________________________________________________________________________________
        //AJUSTE DE LINEAS DE ENCABEZADO DEL ARCHIVO
        // inicializacion para discriminar el valor de la linea donde empiezan las muestras
        char[] str3 = new char[10]; // buscar extraer el primer caracter de blinea
        int autoLinea = 0; // valor de la primera linea
        int limiteEncabezado = 14; //valor limite donde puede llegar el encabezado
        AutolineasEncabezadoArchivoMonofasico = 0;

        flagArchPlanillaDecartable = 0;// inicializa la variable "0" no descarta   "1" se descarta el archivo por coincidir con el encabezado de la planilla
        //________________________________________________________________________
        //************************************************** FIN DEL AJUSTE PARA IDENTIFICAR LIMITE ENCABEZADO

        // Apertura  y lectura de cantidad de lineas del archivo

        // File input = new File(path); // Lectura del archivo a ser leido

        FileReader fileReader = new FileReader(nombArch);
        int numLines = 0;

        //lineasEncabezadoArchivoMonofasico : esta variable declarada de forma general para leer los archivos
        //Verdadero int lineasEncabezadoArchivo=lineasEncabezadoArchivoMonofasico; // valor fijo de las lineas descriptivas del archivo
        int lineasEncabezadoArchivo = 15; // valor fijo de las lineas descriptivas del archivo

        try {
            BufferedReader buffer = new BufferedReader(fileReader);
            String blinea;

            //_____________
            String deteccionLinea = ""; // variable del algoritmo que cambia a "Detectado" cuando ubica el primer valor numérico
            String strLineanumerica = "";
            //___________________
            int indicadorPosic = 0;
            while ((blinea = buffer.readLine()) != null) {
                strLineanumerica = blinea; // asigna contenido de la lectura del archivo a la variable

                String strAnalisisDescarteTipoArchivoPlanilla = blinea; // guarda en una variable auxiliar el contenido de la linea leida del archivo de arjustes

                // Inicio-->__________________________________________________________________________________________________
                //*** se buscar identificar hasta donde llega el encabezado y empiezan las muestras

                autoLinea = autoLinea + 1; // incrementa contador
                //strLineanumerica.getChars(0,1,str3,0); // llena el vector el primer caracter de la linea buscando cuando aparece el primer número
                String[] str4 = strLineanumerica.split("/");
                String varTempstr4 = str4[0];


                // System.out.println( autoLinea+"AUTOLINEAS="+AutolineasEncabezadoArchivoMonofasico);
                //Fin---> del bloque donde termina la deteccion de la linea donde empieza el bloque de muestras______________________________________


                //******** lineas adicionadas para discriminar registros monofasicos y trifasicos
                //linea 4 palabra 1: 100=M  800=T  ; configuracion especial x = E

                int numMuestra = indicadorPosic - lineasEncabezadoArchivo + 1;// va indicando el numero de la linea para cada  muestra

                //System.out.println("line  en registro de prueba "+ blinea);
                indicadorPosic = indicadorPosic + 1;
                String[] parts = blinea.split("\\s+");
                // MANEJO DEL ENCABEZADO
                //____________________________________________________________________


                //*************************************************************************************************
                //------ Variable utilizada para descartar lectura erroneas de las planillas Uuario  e Instalacion


                String[] partsArchivoPlanilla = strAnalisisDescarteTipoArchivoPlanilla.split(";");

                //System.out.println(" valor de la primera linea de la planilla "+strAnalisisDescarteTipoArchivoPlanilla);

                if (indicadorPosic == 1) {// selectivamente se activa ante la primera linea del archivo para discriminar el tipo de archivo
                    String varTEmp = partsArchivoPlanilla[0];
                    if (varTEmp.equals("Mes")) {
                        // System.out.println(indicadorPosic + " valor de la primera palabra de la planilla " + partsArchivoPlanilla[0]);
                        flagArchPlanillaDecartable = 1;// ACTIVA EL FLAG para descartar el tipo de archivo Planilla Usuario
                        // System.out.println(" flag de descarte de archivo=" + flagArchPlanillaDecartable + "  valor linea=" + strAnalisisDescarteTipoArchivoPlanilla);
                    }
                }


                //____________________________________________________________________________________________________________________________


                // deteccion del numero de la linea donde coienzan las muestras


                //___________________________________________________________________


                if (indicadorPosic < lineasEncabezadoArchivo) { //valor que delimita la lectura del encabezado
                    // System.out.println("contenido del encabezado, linea nro " + indicadorPosic + " numero de partes"+ parts.length+" contenido: " + blinea);//

                    for (int encb = 0; encb < parts.length; encb++) {
                        // System.out.println(" Division de linea ENCABEZADO linea " + indicadorPosic + " nro palabra " + encb + " Encabezado: " + parts[encb]);

// >>>>>>>>>>>>>>>>>>>>>>>>>>>>> ACTUALIZA VALOR DEL TOROIDE UTILIZADO
                        if ((indicadorPosic == 4) && (encb == 1)) { // *****    actualiza el valor de tipoToroide con valor del archivo
                            strTipoToroide = parts[encb];// carga el valor del toroide desde el archivo
                            // System.out.println("> Tipo de toroide " + parts[encb] +"valor String->int"+strTipoToroide+" "+tipoToroide);
                        }
                        // FIN PROCESO CARGA DEL TOROIDE <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


                        if ((indicadorPosic == 12) && (encb == 0) && ((parts[encb].contentEquals("Fecha")))) {

                            AutolineasEncabezadoArchivoMonofasico = indicadorPosic + 2;

                            System.out.println(indicadorPosic+"zzz AutolineasEncabezado.."+AutolineasEncabezadoArchivoMonofasico+"lineas de Encabezado"+ lineasEncabezadoArchivoMonofasico);

                            lineasEncabezadoArchivoMonofasico = AutolineasEncabezadoArchivoMonofasico;

                            //**********************************************
                            // CAPTURA DE MULTIPLO DE LA UNIDAD DEL ARCHIVO


                            //*************************************




                        }


// ASIGNACION DE LA CONDICION DEL ARCHIVO CASO "0"
                        if ((indicadorPosic == 4) && (encb == 1) && (parts[encb].contentEquals("100")) && condicionEspecial == 0) {
                            tipoToroide = Integer.parseInt(parts[encb]); // este caso carga 100
                            // tipoArchivo = "M";
                            // System.out.println("CondicionEspecial="+condicionEspecial+" tipo de toroide="+tipoToroide+
                            //         " TIPO ARCHIVO="+tipoArchivo + " "+parts[encb]+ "  "+nombArch);
                        } else {
                            if ((indicadorPosic == 4) && (encb == 1) && (parts[encb].contentEquals("800")) && condicionEspecial == 0) {
                                tipoToroide = Integer.parseInt(parts[encb]);// este caso carga 800
                                // tipoArchivo = "T";
                                //    System.out.println("CondicionEspecial="+condicionEspecial+" tipo de toroide="+tipoToroide+
                                //            " TIPO ARCHIVO="+tipoArchivo + " "+parts[encb]+ "  "+nombArch);
                            }
                        }
                        // System.out.println("CondicionEspecial="+condicionEspecial+" tipo de toroide="+tipoToroide+ " TIPO ARCHIVO="+tipoArchivo + " "+parts[encb]+ "  "+nombArch);

                        // condicion especial       // ASIGNACION DE LA CONDICION DEL ARCHIVO CASO "1"
                        if (condicionEspecial != 0 && condicionEspecial != 1) { // iniciliza la variable con el valor no reconocido
                            tipoArchivo = "X";
                        }
                        if ((indicadorPosic == 4) && (encb == 1) && ((parts[encb].contentEquals("35") ||
                                parts[encb].contentEquals("100") || parts[encb].contentEquals("200") ||
                                parts[encb].contentEquals("800"))) && condicionEspecial == 1) {
                            tipoToroide = Integer.parseInt(parts[encb]);// este caso carga 800
                            tipoArchivo = "E";
                            //  System.out.println("CondicionEspecial="+condicionEspecial+" tipo de toroide="+tipoToroide+
                            //        " TIPO ARCHIVO="+tipoArchivo + " "+parts[encb]+ "  "+nombArch);
                        }

                    }

                    // CASO ADICIONAL PARA DETECTAR EL TIPO DE ARCHIVO QUE SE ESTA LEYENDO
                } else { //condicion en el cual esta leyendo las muestras del archivo

                    if ((parts.length >= 5 && parts.length < 7) && (indicadorPosic > lineasEncabezadoArchivo + 5) && tipoArchivo != "E") {

                        // System.out.println(nombArch+" El archivo detectado es MONOFÁSICO" );
                        tipoArchivo = "M";
                    } else {
                        if ((parts.length >= 7 && parts.length <= 8) && (indicadorPosic > lineasEncabezadoArchivo + 5 && tipoArchivo != "E")) {
                            //  System.out.println(nombArch + " El archivo detectado es TRIFÁSICO");
                            tipoArchivo = "T";
                        }
                    }


                }
                //*************************************************************
                numLines++;
            }
        } catch (Exception ex) {
            //  GENERA UN ARCHIVO DE ERROR CON LA DESCRIPCION DEL PROBLEMA>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
            System.out.println("Excepcion- Antes de proseguir Cierre todos los archivos abiertos en el directorio de analisis ");
            String archivoError = path + "_ERROR.txt";
            File fERROR = new File(path + "\\" + archivoError);
            FileWriter fWarchivoError = new FileWriter(fERROR);
            BufferedWriter bWarchivoError = new BufferedWriter(fWarchivoError);
            PrintWriter printArchivoError = new PrintWriter(bWarchivoError);
            printArchivoError.write("Elimine el archivo " + path + "  del directorio y vuelva a correr el analizador de mediciones. Existe un error de formato");
            printArchivoError.close();
            bWarchivoError.close();
            System.exit(0);

            ex.printStackTrace();
        }
//----------------------------------------------------------------------------------------------------------------------------
// EVALUA CONDICION para asignar la condicion especial al archivo "E"

//ESTRAERNOMBRE
        String extraccExtenArchi = nombArch.substring(nombArch.length() - 12);// Extrae lkos ultimos digitos de la extension del archivo
        // System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxvalor fijo a seguir "+extraccExtenArchi+nombArch);//cccccXXXXXX   esta en l
        // if (extraccExtenArchi.equals("_Detalle.txt") || extraccExtenArchi.equals("esultado.txt")){

        //System.out.println("valor fijo a seguir "+extraccExtenArchi);//cccccXXXXXX   esta en la
        // }else{

        // }
        //-----------------------------------------------------------------------------------------------------------------

        // if (condicionEspecial==1){ // ASIGNA condicion tipo ARCHIVO "E" en caso de activarse el Flag caso esepcial

        // Asigna condicion "E" esepcial en caso de tener flag y valor numerico en el toroide
        //   if (strTipoToroide.equals("35")||strTipoToroide.equals("200")||strTipoToroide.equals("800")){
        //     tipoArchivo="E";
        //System.out.println("cambio categoria a Especial "+strTipoToroide+ "  "+tipoArchivo);
        //}else{
        //      tipoArchivo="X";// valor que indica la no coincidencia de valores conocidos
        //}
        //strTipoToroide="";// NUEVO XXXXX
        //}

        //System.out.println("lectura correcta lineas en el archivo del tipo "+tipoArchivo);
        System.out.println(nombArch + " Tipo de Archivo = " + tipoArchivo);
        //System.out.println("Fin  AutolineasEncabezado.... "+AutolineasEncabezadoArchivoMonofasico+" lineas de Encabezado= "+ lineasEncabezadoArchivoMonofasico);

        return (numLines);

    }// fin de la funcion contar líneas ****************




    public String generarTokensMonofasico(String path, String nombArch, int numlineas) throws IOException, ParseException {

        // System.out.println("www Contenido de lineas encabezado que viene seteado"+lineasEncabezadoArchivoMonofasico);

        int lineasEncabezado = lineasEncabezadoArchivoMonofasico; //  esta variable se declara al inicio de la clase archivo

        // System.out.println(" Monofasico Verdadero Valor Autolineas encabezado="+lineasEncabezado);
        //int lineasEncabezado=14; //  esta variable se declara al inicio de la clase archivo
        // VALOR NORMAL 10 ---   LINEAS A CONSIDERAR COMO ENCABEZADO DEL ARCHIVO

        //***************************************************************************************
        //******** GENERAR EL ARCHIVO ESPEJO CON EL AJUSTE EN LA MEDICION DE POTENCIA
        //**************************************************************************************
        String nombDirectorioNvoNormalizado = "\\nuevo\\"; // Designacion del nuevo directorio donde guardas archivos normalizados
        // estos son los archivos con muestras con signo negativo y
        // se le aplica la función módulo

        // Extrae de la ruta el nombre del archivo
        //System.out.println("nombArch "+nombArch);
        String[] srtpartNombFile = nombArch.split("/"); // que conformara parte del nombre de archivos de salida
        String NombreArchivo = srtpartNombFile[srtpartNombFile.length - 1];// Nombre puro extraido de la ruta
        // Quita la extensión del archivo dejando solo el nombre Base
        String base = srtpartNombFile[srtpartNombFile.length - 1];
        //System.out.println("base--->"+base);
        srtpartNombFile = base.split("\\.");
        String nvoNombreArchAsignado = "\\" + srtpartNombFile[0] + "_N.TXT"; // Se tiene la ruta +
        //*************************************************************************************************************
        //*** Definir archivo Integrador de datos_ extension "_I.TXT
        String nvoNombArchIntegrado = "\\" + srtpartNombFile[0] + "_I.TXT"; // archivo a ser construido integrando a las lecturas los valores de Usuario, tarifa
        String raizNombreArchivo = srtpartNombFile[0];


        // archivo para agregar sufijo y extension
        srtpartNombFile = nvoNombreArchAsignado.split("\\\\");


        //************************************************************************************************************
        //-------------- ESTA SECCION EXTRAE LA RAIZ DEL ARCHIVO --------------------
        String[] srtpartRaizNombreArchivo = raizNombreArchivo.split("\\\\");
        //System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%   VALOR BASE DEL ARCHIVO "+ srtpartRaizNombreArchivo[srtpartRaizNombreArchivo.length-1]);
        raizNombreArchivo = srtpartRaizNombreArchivo[srtpartRaizNombreArchivo.length - 1];
        // El valor base del archivo sin extension se encuentra en la variable raizNombreArchivo en este punto

        // QUITA EL SUFIJO "_N"
        char[] listsrtRaizNombre = new char[raizNombreArchivo.length()]; // dimensiona longitud del arreglo al largo del nombre
        String tempcharRaizNombre = "";
        raizNombreArchivo.getChars(0, raizNombreArchivo.length() - 2, listsrtRaizNombre, 0);
        for (int i = 0; i < raizNombreArchivo.length() - 2; i++) {
            tempcharRaizNombre = tempcharRaizNombre + listsrtRaizNombre[i];

        }
        //Solo para pruebas
        // System.out.println(" EL NOMBRE RAIZ ES "+tempcharRaizNombre+ "  "+tempcharRaizNombre.length());
        // System.out.println(tempcharRaizNombre+"  Funcion BUsqueda codigo id usuario ="+buscarUsuarioxNombArchivoInstalacion(tempcharRaizNombre));
        //**************************************************************************************************************


        String rutaBaseDirectorio = "";
        for (int p = 0; p < srtpartNombFile.length; p++) {
            //System.out.println(p+" partedirectrelativo "+srtpartNombFile[p]);
            if (p < 2) {
                rutaBaseDirectorio = rutaBaseDirectorio + srtpartNombFile[p];// excepcion para el comienzoo de ruta
                // System.out.println("0 - raiz"+rutaBaseDirectorio);
            } else {
                rutaBaseDirectorio = rutaBaseDirectorio + "\\" + srtpartNombFile[p];
                //System.out.println(p+"p"+rutaBaseDirectorio);
            }
        }
        //System.out.println(" 1   Ruta construida "+rutaBaseDirectorio);//Ruta construida para llegar al archivo


        //Extrae del path la rutabase para crear el directorio donde se colocaran los nuevos archivos
        rutaBaseDirectorio = "";
        for (int i = 0; i < srtpartNombFile.length; i++) {
            if (i < 2) {
                rutaBaseDirectorio = rutaBaseDirectorio + srtpartNombFile[i];
            } else {
                if (i < srtpartNombFile.length - 1) {
                    rutaBaseDirectorio = rutaBaseDirectorio + "\\" + srtpartNombFile[i];
                } else {
                    rutaBaseDirectorio = rutaBaseDirectorio + nombDirectorioNvoNormalizado + srtpartNombFile[i];
                }
            }
        }
        //ESTA ES LA RUTA CORRECTA A SER UTILIZADA PARA SALVAR LOS ARCHIVOS
        // Este ae la uniforme para completar el despliegue de los directorios
        //System.out.println("2   path="+path+"xx"+srtpartNombFile.length+"Ruta del nvo Directorio--->= "+rutaBaseDirectorio);

        // Incorpora la variable CRLF como Ascii
        StringBuffer sbCrLf = new StringBuffer();
        sbCrLf.append((char) 13);
        sbCrLf.append((char) 10);
        String crlfFinLinea = sbCrLf.toString();

        // Incorpora la variable HT (horizontal Tab)como Ascii
        StringBuffer sbHTab = new StringBuffer();
        sbHTab.append((char) 9);
        String hTabEspacio = sbHTab.toString();

        //------------------------------------------------------------------




        // ************************************************
        // ** Creación del nuevo directorio
        File directorioResultados = new File(path + "\\Nuevo");// instancia el objeto archivo tipo Directorio
        directorioResultados.mkdirs();//crea un directorio


        //*** **** **** ***** ******
        // creacion  DEL ARCHIVO NUEVO clonado y el archivo integrado con mediciones + idusuario + tarifa
        //
        //*********************************************************
        //*** Clonación del nombre del archivo a ser ajustado
        //System.out.println("3 nvoNombreArchAsignado"+nvoNombreArchAsignado);
        //File fnvoAsignado = new File( nvoNombreArchAsignado);

        File fnvoAsignado = new File(rutaBaseDirectorio);
        FileWriter fWarchAsignado = new FileWriter(fnvoAsignado);

        // Creacion archivo Integrado
      //  File fnIntegrado = new File(nvoNombArchIntegrado);  // se crea el archivo en el directorio nuevo que permite integrar tarifas y el IDusuario
       // FileWriter fWarchIntegrado = new FileWriter(fnIntegrado);

       // fWarchIntegrado.write("Fechas"+"|"+"Hora"+"|"+"Voltaje"+"|"+"Potencia"+"|"+"FP"+"|"+"Anomalia"+"|"+
       //         "IdUsuario"+"|"+"Tarifa-Estrato"+"|"+"NombreArchivo"+"|"+"MultiploUnidadPotencia"+sbCrLf   );

        //System.out.println("Nombre Archivo Integrado= "+ruta="+ "creacion del archivo integrado ");

        //BufferedWriter bWarchivoDetalle= new BufferedWriter(fWarchivoDetalle);
        //PrintWriter printArchivoDet = new PrintWriter(bWarchivoDetalle);
        // Fin de ajuste al archivo****************************************************
        //*********************************************************************************


        // Continua.. lazo if para preparación de lectura del archivo de medición
        //************************************************************************
        // ¨*** PROCESAMIENTO DEL ARCHIVO ORIGINAL LECTURA DE MUESTRAS
        // File input = new File(path);
        //INICIALIZACION


        FileReader fileReader = new FileReader(nombArch);
        int numLines = 0;
        try {
            BufferedReader buffer = new BufferedReader(fileReader);
            String blinea;
            int indPosicion = 0;
            multiploUnidadPotencia="";//  variable publica declarada como atributo de la clases
            // campo para capturar la Multiplo/unidad en que viene la potencia

            // LAZO DO WHILE PARA BARRER LINEAS DEL ARCHIVO
            //***********************************************************************
            while ((blinea = buffer.readLine()) != null) {

                //Pendiente dupplicado fWarchAsignado.write(blinea+sbCrLf); // Escribe en archivo clon--> la linea completa


                int numMuestra = indPosicion - lineasEncabezado + 1;// va indicando el numero de la linea para cada  muestra

                indPosicion = indPosicion + 1;
                String[] parts = blinea.split("\\s+");

                // MANEJO DEL ENCABEZADO **************************************
                if (indPosicion < lineasEncabezado) { //valor que delimita la lectura del encabezado
                    // System.out.println("contenido del encabezado, linea nro " + indPosicion + " contenido: " + blinea);//

                    // escritura en el archivo CLON

                    fWarchAsignado.write(blinea + sbCrLf); // Escribe en archivo CLON--> la linea completa
                    // Fin escritura en el archivo CLON




                    for (int encb = 0; encb < parts.length; encb++) {
                        //System.out.println("ENCABEZADO ===>linea " + indPosicion + " nro palabra " + encb + " Encabezado: " + parts[encb]);

                        if (indPosicion==lineasEncabezadoArchivoMonofasico-1 && encb==2){

                            multiploUnidadPotencia=parts[encb]; // guarda el multiploUnidad de potencia

                            System.out.println(lineasEncabezadoArchivoMonofasico+"======>Multiplo/potencia= "+multiploUnidadPotencia);

                        }
                    }

                } else { // **** *** INICIO DEL PROCESAMIENTO DE LAS MUESTRAS
                    // System.out.println("linea leida"+blinea);
                    //System.out.println(indPosicion+" lineas y la cantidad de partes de la linea "+parts.length);
                    //System.out.println( numMuestra+" Muestra   "+ (indPosicion-lineasEncabezado+1) +" valor: "+ parts[m]);
                    //Lazo para almacenar las muestras en variables para cada
                    //***************************************************************************************************
                    // _________________ las siguientes dos lineas crea una lista con el flag de registros marcados
                    if (parts.length > 5) {
                        archflagAnormalidad.add("A");
                    } else {
                        archflagAnormalidad.add(" ");// existe anormalia en las mediciones

                    }
                    //*************************************************************************************************

                    for (int m = 0; m < parts.length; m++) {
                        switch (m) {
                            case 0:
                                archFecha.add(parts[m]);

                                fWarchAsignado.write(parts[m]);// Escribe fecha en el clon
                                fWarchAsignado.write(hTabEspacio);

                                //fWarchIntegrado.write(parts[m]);// Escribe en el archivo integrado
                               // fWarchIntegrado.write("|");

                                break;
                            case 1:
                                archHora.add(parts[m]);

                                fWarchAsignado.write(parts[m]);// Escribe Hora en el clon
                                fWarchAsignado.write(hTabEspacio);
                                ;

                              //  fWarchIntegrado.write(parts[m]);// Escribe en el archivo integrado
                               // fWarchIntegrado.write("|");

                                break;
                            case 2:
                                String strVoltTemp = (parts[m]);
                                strVoltTemp = strVoltTemp.replace(",", ".");

                                // System.out.println(" campo variable de voltaje A="+strVoltTemp);
                                archVoltaje.add(Double.valueOf(strVoltTemp));

                                //archVoltaje.add(Double.valueOf(parts[m]));

                                fWarchAsignado.write(strVoltTemp);// Escribe Voltaje en el clon
                                fWarchAsignado.write(hTabEspacio);

                               // fWarchIntegrado.write(strVoltTemp);// Escribe en el archivo integrado
                               // fWarchIntegrado.write("|");

                                break;
                            case 3:

                                String strTotEnergTemp = (parts[m]);
                                strTotEnergTemp = strTotEnergTemp.replace(",", ".");
                                archTotalEnergia = archTotalEnergia + Double.parseDouble(strTotEnergTemp);
                                //archTotalEnergia = archTotalEnergia + Double.valueOf(parts[m]);

                                String strEnerg = (parts[m]);
                                strEnerg = strEnerg.replace(",", ".");
                                archEnergia.add(Double.valueOf(strEnerg));
                                //archEnergia.add(Double.valueOf(parts[m]));

                                //------------------
                                Double varTempSigno = Double.valueOf(strEnerg);
                                if (varTempSigno < 0) { // discrimina si es negativo el valor

                                    varTempSigno = varTempSigno * -1;// Multiplica por -1 para invertir el signo de la medida
                                }
                                fWarchAsignado.write(String.valueOf(varTempSigno));// Escribe Potencia/Energia en el clon
                                fWarchAsignado.write(hTabEspacio);

                                //fWarchIntegrado.write(String.valueOf(varTempSigno)); //Escribe en el archivo  Integrador
                                //fWarchIntegrado.write("|");


                                break;
                            case 4:
                                String strFpTemp = (parts[m]);
                                strFpTemp = strFpTemp.replace(",", ".");
                                archFP.add(Double.valueOf(strFpTemp));
                                //archFP.add(Double.valueOf(parts[m]));

                                //Datos archivos clon
                                fWarchAsignado.write(strFpTemp);// Escribe Potencia/Energia en el clon
                                fWarchAsignado.write(hTabEspacio);

                                //fWarchIntegrado.write(strFpTemp);//Escribe en el archivo  Integrador
                                //fWarchIntegrado.write("|");


                                if (parts.length == 5) { // gestiona la excepcion del campo 5 ("A") el cual no se va a presentar y registra el vacio

                                    //fWarchIntegrado.write("|"); // INdica en el archivo integrador que no hay lineas "A"

                                }

                                break;
                            case 5:

                                archAnormalidad.add(parts[m]);
                                nroMuestraconAarchAnormalidad.add(numLines);//guarda indice de la muestra marcada
                                if (parts[m].contentEquals("A")) {
                                    numMuestraAnormal = numMuestraAnormal + 1;
                                }

                                // Datos archivo clon
                                fWarchAsignado.write(parts[m]);// Escribe Potencia/Energia en el clon
                                fWarchAsignado.write(hTabEspacio);

                                //fWarchIntegrado.write(parts[m]);// Escribe en el archovo Integrador
                                //fWarchIntegrado.write("|");
                                break;
                        }
                        // SE INCORPORAN LOS CAMPOS ADICIONALES DE Idusuario + nombArchivo y Tarifa
                        archPlanillaIdUsuarioArchInstalacion.add(buscarUsuarioxNombArchivoInstalacion(tempcharRaizNombre)); // escibe en los arreglos arch
                        archPlanillaTarifaPlanillaTXT.add(buscarTarifaUsuarioxNombArchivoInstalacion(tempcharRaizNombre));
                        archPlanillaNombArchMedicionInstalacionTXT.add(tempcharRaizNombre);
                    }


                    // escribe directamente en el archivo de medicion_" i "
                    //fWarchIntegrado.write(buscarUsuarioxNombArchivoInstalacion(tempcharRaizNombre) + "|"); // escribe el ID Usuario
                   // fWarchIntegrado.write(buscarTarifaUsuarioxNombArchivoInstalacion(tempcharRaizNombre) + "|"); // escribe la TARIFA
                   // fWarchIntegrado.write(tempcharRaizNombre + "|") ;// escribe el nombre del archivo
                   // fWarchIntegrado.write(multiploUnidadPotencia);


                   // fWarchIntegrado.write(crlfFinLinea);// Incorpora fin de la linea CR+LF

                    // System.out.println(numLines+"-----<CONTROL DE LINEA-->="+parts.length);
                    // Incorpora fin CR + LF si esta leyendo lineas
                    if (parts.length > 0) { // sigue procesando
                        //  System.out.println(numLines+" vs numlineas (ANTES) "+numlineas);
                        // fWarchAsignado.write(" >>>>>>");
                        fWarchAsignado.write(hTabEspacio);
                        fWarchAsignado.write(crlfFinLinea); //Escribe en el clon

                    } else {
                        //fWarchAsignado.write(" >>14 >>>>");// marca cierrre del archivo

                        break;// obliga a romper el ciclo While porque el condicional  parts linea =0
                    }

                    numLines++;// fin del lazo- incrementa la variable para una nueva interaccion
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            //_________________________________________________
            // Manejo de errores

            // Manejo de errores de excepcion por error en formato
             sbCrLf = new StringBuffer();
             sbCrLf.append((char)13);
            sbCrLf.append((char)10);
            String crlfTerminator = sbCrLf.toString();

            System.out.println("error ruta archivo:  "+path+"\\error.txt");
            FileWriter fwErrorArchivoReport = new FileWriter(path+"\\ERROR.TXT");
            BufferedWriter bWErrorArchivoReport= new BufferedWriter(fwErrorArchivoReport);
            PrintWriter printErrorArchivoReport = new PrintWriter(bWErrorArchivoReport);

            Date currentDate = Calendar.getInstance(TimeZone.getDefault()).getTime();
            printErrorArchivoReport.write("RESUMEN DE ERRORES ENCONTRADOS    "+ "Fecha del reporte:  "+ String.valueOf(currentDate)+sbCrLf);
            printErrorArchivoReport.write("Retire el archivo trifásico  "+nombArch +"  y vuelva a correr la aplicación...");

            bWErrorArchivoReport.close();
            fwErrorArchivoReport.close();
            JOptionPane.showMessageDialog(null, "Se encontró un error de formato archivo Trifásico: "+nombArch +"  REMUEVA EL ARCHIVO E INTENTE NUEVAMENTE");



            System.exit(0);// Salida abrupta del programa
        }

        //}


        //Cierre de archivos
        // fWarchAsignado.write("null");
        fWarchAsignado.close(); // cierre de los  archivos nuevos a los que se le aplican la funcion módulo
        //fWarchIntegrado.close(); // cierra el archivo que integra los


        return ("String");
    }

    public String generarTokensTRIFASICO(String path, String nombArch, int numlineas) throws IOException, ParseException {

        int lineasEncabezado = lineasEncabezadoArchivoTrifasico; // LINEAS A CONSIDERAR COMO ENCABEZADO DEL ARCHIVO
        // esta variable de inicializacion fue declarada al inicio de la clase Archivo
        // declarar el valor al inicio de la clase

        // System.out.println(" Trifasico Verdadero Valor Autolineas encabezado="+lineasEncabezado);
        //System.out.println(" numlineas archivo (genrarTokensTrifasico)="+numlineas);

        //***************************************************************************************
        //******** GENERAR EL ARCHIVO ESPEJO CON EL AJUSTE EN LA MEDICION DE POTENCIA
        //**************************************************************************************
        StringBuffer sbCrLf = new StringBuffer();
        sbCrLf.append((char) 13);
        sbCrLf.append((char) 10);
        String crlfFinLinea = sbCrLf.toString();

        // Incorpora la variable HT (horizontal Tab)como Ascii
        StringBuffer sbHTab = new StringBuffer();
        sbHTab.append((char) 9);
        String hTabEspacio = sbHTab.toString();
        //_____ ______ ______ __________ _____ ________ __________


        String nombDirectorioNvoNormalizado = "\\nuevo\\"; // Designacion del nuevo directorio donde guardas archivos normalizados
        // estos son los archivos con muestras con signo negativo y
        // se le aplica la función módulo
        // Extrae de la ruta el nombre del archivo
        //System.out.println("nombArch "+nombArch);
        String[] srtpartNombFile = nombArch.split("/"); // que conformara parte del nombre de archivos de salida
        String NombreArchivo = srtpartNombFile[srtpartNombFile.length - 1];// Nombre puro extraido de la ruta

        // Quita la extensión del archivo dejando solo el nombre Base
        String base = srtpartNombFile[srtpartNombFile.length - 1];

        //System.out.println("base--->"+base);
        srtpartNombFile = base.split("\\.");
        String nvoNombreArchAsignado = "\\" + srtpartNombFile[0] + "_N.TXT"; // Se tiene la ruta +
        // archivo para agregar sufijo y extension

        //*************************************************************************************************************

        //*** Definir archivo Integrador de datos_ extension "_I.TXT
        String nvoNombArchIntegrado = "\\" + srtpartNombFile[0] + "_I.TXT"; // archivo a ser construido integrando a las lecturas los valores de Usuario, tarifa

        String raizNombreArchivo = srtpartNombFile[0];

        srtpartNombFile = nvoNombreArchAsignado.split("\\\\");

        //************************************************************************************************************
        //-------------- ESTA SECCION EXTRAE LA RAIZ DEL ARCHIVO --------------------
        String[] srtpartRaizNombreArchivo = raizNombreArchivo.split("\\\\");
        //System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%   VALOR BASE DEL ARCHIVO "+ srtpartRaizNombreArchivo[srtpartRaizNombreArchivo.length-1]);
        raizNombreArchivo = srtpartRaizNombreArchivo[srtpartRaizNombreArchivo.length - 1];
        // El valor base del archivo sin extension se encuentra en la variable raizNombreArchivo en este punto

        // QUITA EL SUFIJO "_N"
        char[] listsrtRaizNombre = new char[raizNombreArchivo.length()]; // dimensiona longitud del arreglo al largo del nombre
        String tempcharRaizNombre = "";
        raizNombreArchivo.getChars(0, raizNombreArchivo.length() - 2, listsrtRaizNombre, 0);
        for (int i = 0; i < raizNombreArchivo.length() - 2; i++) {
            tempcharRaizNombre = tempcharRaizNombre + listsrtRaizNombre[i];

        }
        //Solo para pruebas
        // System.out.println(" EL NOMBRE RAIZ ES "+tempcharRaizNombre+ "  "+tempcharRaizNombre.length());
        // System.out.println(tempcharRaizNombre+"  Funcion BUsqueda codigo id usuario ="+buscarUsuarioxNombArchivoInstalacion(tempcharRaizNombre));
        //******************************************************************************


        String rutaBaseDirectorio = "";
        for (int p = 0; p < srtpartNombFile.length; p++) {
            //System.out.println(p+" partedirectrelativo "+srtpartNombFile[p]);
            if (p < 2) {
                rutaBaseDirectorio = rutaBaseDirectorio + srtpartNombFile[p];// excepcion para el comienzoo de ruta
                // System.out.println("0 - raiz"+rutaBaseDirectorio);
            } else {
                rutaBaseDirectorio = rutaBaseDirectorio + "\\" + srtpartNombFile[p];
                //System.out.println(p+"p"+rutaBaseDirectorio);
            }
        }
        //System.out.println(" 1   Ruta construida "+rutaBaseDirectorio);//Ruta construida para llegar al archivo


        //Extrae del path la rutabase para crear el directorio donde se colocaran los nuevos archivos
        rutaBaseDirectorio = "";
        for (int i = 0; i < srtpartNombFile.length; i++) {
            if (i < 2) {
                rutaBaseDirectorio = rutaBaseDirectorio + srtpartNombFile[i];
            } else {
                if (i < srtpartNombFile.length - 1) {
                    rutaBaseDirectorio = rutaBaseDirectorio + "\\" + srtpartNombFile[i];
                } else {
                    rutaBaseDirectorio = rutaBaseDirectorio + nombDirectorioNvoNormalizado + srtpartNombFile[i];
                }
            }
        }
        //ESTA ES LA RUTA CORRECTA A SER UTILIZADA PARA SALVAR LOS ARCHIVOS
        // Este ae la uniforme para completar el despliegue de los directorios
        // System.out.println("2   path="+path+"xx"+srtpartNombFile.length+"Ruta del nvo Directorio--->= "+rutaBaseDirectorio);

        // Se debe crear un directorio donde ubicar los nuevos archivos
        File directorioResultados = new File(path + "\\Nuevo");// instancia el objeto archivo tipo Directorio
        directorioResultados.mkdirs();//crea un directorio

        // Apertura del archivo
        //System.out.println("nombre asignado al archivo ---->"+nvoNombreArchAsignado);
        //File fnvoAsignado = new File( nvoNombreArchAsignado);
        System.out.println("3 nvoNombreArchAsignado" + nvoNombreArchAsignado);
        //File fnvoAsignado = new File( nvoNombreArchAsignado);
        File fnvoAsignado = new File(rutaBaseDirectorio);
        FileWriter fWarchAsignado = new FileWriter(fnvoAsignado);
        //BufferedWriter bWarchivoDetalle= new BufferedWriter(fWarchivoDetalle);
        //PrintWriter printArchivoDet = new PrintWriter(bWarchivoDetalle);


        // Creacion archivo Integrado
        File fnIntegrado = new File(nvoNombArchIntegrado);  // se crea el archivo en el directorio nuevo que permite integrar tarifas y el IDusuario
        FileWriter fWarchIntegrado = new FileWriter(fnIntegrado);


        // Fin de ajuste al archivo****************************************************
        //*********************************************************************************

        //File input = new File(path);
        FileReader fileReader = new FileReader(nombArch);
        int numLines = 0;
        try {
            BufferedReader buffer = new BufferedReader(fileReader);
            String blinea;

            int indPosicion = 0;
            while ((blinea = buffer.readLine()) != null) {

                //Pendiente duplicado ---fWarchAsignado.write(blinea+sbCrLf); // Escribe en archivo clon--> la linea completa

                int numMuestra = indPosicion - lineasEncabezado + 1;    // va indicando el numero de la linea para cada  muestra

                //System.out.println("line  en registro de prueba "+ blinea);
                indPosicion = indPosicion + 1;
                String[] parts = blinea.split("\\s+");

                // MANEJO DEL ENCABEZADO **************************************
                if (indPosicion < lineasEncabezado) {             //valor que delimita la lectura del encabezado
                    //System.out.println("contenido del encabezado, linea nro " + indPosicion + " contenido: " + blinea);//

                    // Escritura en el archivo CLON
                    fWarchAsignado.write(blinea + sbCrLf); // Escribe en archivo CLON--> la linea completa
                    // Fin escritura en el archivo CLON

                    for (int encb = 0; encb < parts.length; encb++) {
                        System.out.println("linea " + indPosicion + " nro palabra " + encb + " Encabezado: " + parts[encb]);

                        if (indPosicion==13 && encb==4){
                            multiploUnidadPotencia=parts[encb]; // extrae el múltiplo que tiene la unidad de potencia
                        }
                    }

                } else { // **** *** INICIO DEL PROCESAMIENTO DE LAS MUESTRAS  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡

                    //System.out.println( numMuestra+" Muestra   "+ (indPosicion-lineasEncabezado+1) +" valor: "+ parts[m]);
                    //Lazo para almacenar las muestras en variables para cada
                    for (int m = 0; m < parts.length; m++) {
                        //  System.out.println("m "+m+" muestra "+parts[m]);
                    }
                    //*************************************************************************************************
                    if (parts.length > 7) {    // ---- Las siguientes dos lineas alimentan las lista con los flags
                        archflagAnormalidad.add("A");
                    } else {
                        archflagAnormalidad.add(" ");
                    }
                    //*************************************************************************************************

                    for (int m = 0; m < parts.length; m++) {

                        //System.out.println("numero de partes e q divisiona un arch trif "+parts.length);
                        switch (m) {
                            case 0:
                                archFecha.add(parts[m]);

                                // Escribe en el clon
                                fWarchAsignado.write(parts[m]);// Escribe fecha en el clon
                                fWarchAsignado.write(hTabEspacio);

                                fWarchIntegrado.write(parts[m]);// Escribe en el archivo integrado
                                fWarchIntegrado.write("|");
                                break;
                            case 1:
                                archHora.add(parts[m]);

                                // Escribe en el clon
                                fWarchAsignado.write(parts[m]);// Escribe Hora en el clon
                                fWarchAsignado.write(hTabEspacio);

                                fWarchIntegrado.write(parts[m]);// Escribe en el archivo integrado
                                fWarchIntegrado.write("|");

                                break;
                            case 2:

                                String strVoltTemp = (parts[m]);
                                strVoltTemp = strVoltTemp.replace(",", ".");
                                archVoltaje.add(Double.valueOf(strVoltTemp));

                                //archVoltaje.add(Double.valueOf(parts[m]));


                                // Escribe en el clon
                                fWarchAsignado.write(strVoltTemp);// Escribe fecha en el clon
                                fWarchAsignado.write(hTabEspacio);

                                fWarchIntegrado.write(strVoltTemp);// Escribe en el archivo integrado
                                fWarchIntegrado.write("|");


                                break;
                            case 3:
                                String strVoltBTemp = (parts[m]);
                                strVoltBTemp = strVoltBTemp.replace(",", ".");
                                archVoltajeB.add(Double.valueOf(strVoltBTemp));

                                //archVoltajeB.add(Double.valueOf(parts[m]));

                                // Escribe en el clon
                                fWarchAsignado.write(strVoltBTemp);// Escribe fecha en el clon
                                fWarchAsignado.write(hTabEspacio);

                                fWarchIntegrado.write(strVoltBTemp);// Escribe en el archivo integrado
                                fWarchIntegrado.write("|");

                                break;
                            case 4:
                                String strVoltCTemp = (parts[m]);
                                strVoltCTemp = strVoltCTemp.replace(",", ".");
                                archVoltajeC.add(Double.valueOf(strVoltCTemp));

                                //archVoltajeC.add(Double.valueOf(parts[m]));

                                // Escribe en el  archivo clon
                                fWarchAsignado.write(strVoltCTemp);// Escribe fecha en el clon
                                fWarchAsignado.write(hTabEspacio);

                                fWarchIntegrado.write(strVoltCTemp);// Escribe en el archivo integrado
                                fWarchIntegrado.write("|");

                                break;
                            case 5:
                                String strTotEnergTemp = (parts[m]);
                                strTotEnergTemp = strTotEnergTemp.replace(",", ".");
                                archTotalEnergia = archTotalEnergia + Double.parseDouble(strTotEnergTemp);

                                String strEnerg = (parts[m]);
                                strEnerg = strEnerg.replace(",", ".");
                                archEnergia.add(Double.valueOf(strEnerg));

                                //archTotalEnergia = archTotalEnergia + Double.valueOf(parts[m]);
                                //archEnergia.add(Double.valueOf(parts[m]));

                                //---------------------------------------------------------------
                                // Escribe en el archivo clon
                                // fWarchAsignado.write(strEnerg);// Escribe Potencia/Energia en el clon
                                //fWarchAsignado.write(hTabEspacio);

                                Double varTempSigno = Double.valueOf(strEnerg);
                                if (varTempSigno < 0) { // discrimina si es negativo el valor

                                    varTempSigno = varTempSigno * -1;// Multiplica por -1 para invertir el signo de la medida
                                }
                                fWarchAsignado.write(String.valueOf(varTempSigno));// Escribe Potencia/Energia en el clon
                                fWarchAsignado.write(hTabEspacio);

                                fWarchIntegrado.write(String.valueOf(varTempSigno)); //Escribe en el archivo  Integrador
                                fWarchIntegrado.write("|");

                                //------------------------------------------------------------------------------------
                                break;
                            case 6:
                                String strFpTemp = (parts[m]);
                                strFpTemp = strFpTemp.replace(",", ".");


                                archFP.add(Double.valueOf(strFpTemp));

                                //archFP.add(Double.valueOf(parts[m]));

                                // Escribe en el archivo clon
                                fWarchAsignado.write(strFpTemp);// Escribe FP en el clon
                                fWarchAsignado.write(hTabEspacio);

                                fWarchIntegrado.write(strFpTemp);//Escribe en el archivo  Integrador
                                fWarchIntegrado.write("|");


                                if (parts.length == 7) { // gestiona la excepcion del campo 5 ("A") el cual no se va a presentar y registra el vacio

                                    fWarchIntegrado.write("|"); // INdica en el archivo integrador que no hay lineas "A"

                                }


                                break;
                            case 7:
                                archAnormalidad.add(parts[m]);
                                nroMuestraconAarchAnormalidad.add(numLines);//guarda indice de la muestra marcada

                                String s = parts[m];
                                if (parts[m].contentEquals("A")) {
                                    numMuestraAnormal = numMuestraAnormal + 1;
                                }

                                // Escribe en el archivo clon
                                fWarchAsignado.write(parts[m]);// Escribe fecha en el clon
                                fWarchAsignado.write(hTabEspacio);

                                fWarchIntegrado.write(parts[m]);// Escribe en el archovo Integrador
                                fWarchIntegrado.write("|");

                                break;
                        }
                        // SE INCORPORAN LOS CAMPOS ADICIONALES DE Idusuario + nombArchivo y Tarifa
                    }
                    // SE INCORPORAN LOS CAMPOS ADICIONALES DE Idusuario + nombArchivo y Tarifa

                    fWarchIntegrado.write(buscarUsuarioxNombArchivoInstalacion(tempcharRaizNombre) + "|"); // escribe el ID Usuario
                    fWarchIntegrado.write(buscarTarifaUsuarioxNombArchivoInstalacion(tempcharRaizNombre) + "|"); // escribe la TARIFA
                    fWarchIntegrado.write(tempcharRaizNombre+ "|");// escribe el nombre del archivo
                    fWarchIntegrado.write(multiploUnidadPotencia);

                    fWarchIntegrado.write(crlfFinLinea);// Incorpora fin de la linea CR+LF

                    archPlanillaIdUsuarioArchInstalacion.add(buscarUsuarioxNombArchivoInstalacion(tempcharRaizNombre)); // escibe en los arreglos arch
                    archPlanillaTarifaPlanillaTXT.add(buscarTarifaUsuarioxNombArchivoInstalacion(tempcharRaizNombre));
                    archPlanillaNombArchMedicionInstalacionTXT.add(tempcharRaizNombre);


                    //System.out.println(numLines+"-----<CONTROL DE LINEA-->="+parts.length);
                    // Incorpora fin CR + LF si esta leyendo lineas
                    if (parts.length > 0) { // sigue procesando
                        // System.out.println(numLines+" vs numlineas (ANTES) "+numlineas);
                        // fWarchAsignado.write(" >>>>>>");
                        fWarchAsignado.write(hTabEspacio);
                        fWarchAsignado.write(crlfFinLinea); //Escribe en el clon

                    } else {
                        //fWarchAsignado.write(" >>14 >>>>");// marca cierrre del archivo

                        break;// obliga a romper el ciclo While porque el condicional  parts linea =0
                    }
                    numLines++;

                }
                // Proceso de cierre del archivo clon

            } // fin del While
        } catch (Exception ex) {
            ex.printStackTrace();
            //_________________________________________________
            // Manejo de errores

            // Manejo de errores de excepcion por error en formato
            //StringBuffer sbCrLf = new StringBuffer();
           // sbCrLf.append((char)13);
            //sbCrLf.append((char)10);
            //String crlfTerminator = sbCrLf.toString();

            System.out.println("error ruta archivo:  "+path+"\\error.txt");
            FileWriter fwErrorArchivoReport = new FileWriter(path+"\\ERROR.TXT");
            BufferedWriter bWErrorArchivoReport= new BufferedWriter(fwErrorArchivoReport);
            PrintWriter printErrorArchivoReport = new PrintWriter(bWErrorArchivoReport);

            Date currentDate = Calendar.getInstance(TimeZone.getDefault()).getTime();
            printErrorArchivoReport.write("RESUMEN DE ERRORES ENCONTRADOS    "+ "Fecha del reporte:  "+ String.valueOf(currentDate)+sbCrLf);
            printErrorArchivoReport.write("Retire el archivo trifásico  "+nombArch +"  y vuelva a correr la aplicación...");

            bWErrorArchivoReport.close();
            fwErrorArchivoReport.close();
            JOptionPane.showMessageDialog(null, "Se encontró un error de formato archivo Trifásico: "+nombArch +"  REMUEVA EL ARCHIVO E INTENTE NUEVAMENTE");



            System.exit(0);// Salida abrupta del programa
        }
            //fina manejo de errores
            //------------------------------------------




        //Cierre de archivos
        //fWarchAsignado.write(char(00));


        fWarchAsignado.close(); // Cierre del archivo que se está clonando en este lazo de programación
        fWarchIntegrado.close();// cierre del archvo integrador

        return (tipoArchivo); // Fin del metodo generarTokensTrifasico
    }
    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX


    //################################################################################################################
    // CASOS ESPECIALES: Lectura de archivos  especiales >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    public String generarTokensEspecialUno(String path, String nombArch, int numlineas) throws IOException, ParseException {

        int lineasEncabezado = 10; // LINEAS A CONSIDERAR COMO ENCABEZADO DEL ARCHIVO
        int suicheVerifLineas = 1;// 0 archivo lineas =10 "1" archivo con 14 lineas
        File input = new File(path);
        FileReader fileReader = new FileReader(nombArch);
        int numLines = 0;

        BufferedReader buffer = new BufferedReader(fileReader);
        String blinea;

        int indPosicion = 0;
        while ((blinea = buffer.readLine()) != null) {
            int numMuestra = indPosicion - lineasEncabezado + 1;// va indicando el numero de la linea para cada  muestra

            indPosicion = indPosicion + 1;
            String[] parts = blinea.split("\\s+");

            // MANEJO DEL ENCABEZADO **************************************
            if (indPosicion < lineasEncabezado) { //valor que delimita la lectura del encabezado
                //System.out.println("contenido del encabezado, linea nro " + indPosicion + " contenido (blanco?)" + blinea);//

                for (int encb = 0; encb < parts.length; encb++) {
                    //System.out.println(archnombre+"Encabezado linea " + indPosicion + " nro palabra " + encb + " Encabezado: " + parts[encb]);
                }

                // verificar cuando llega a la linea 10 para discriminar el tipo de archivo

                if (indPosicion == 9 && parts[1].equals("Wh")) {
                    // if (indPosicion==9 && parts[1].equals("Wh")&& lineasEncabezado>9){ // si se cumple esta condicion de tener "Wh" en la linea 9 las muestras
                    //empiezan en la linea 10 en caso contrario en la linea 14
                    //System.out.println("LINEA 9             "+parts[1]);
                    suicheVerifLineas = 0;// suiche para cambiar el numero de cuentas de lineas
                    //System.out.println(nombArch+" ARCHIVO 10 LINEAS"+lineasEncabezado);
                    lineasEncabezado = 10;
                } else {

                    lineasEncabezado = 14;  // recordar que se declaró al inicio dos variables "lineasEncabezadoArchivoMonofasico=10
                    // "lineaEncabezadoTrifasico=14" a fin de hacer uniforme el archivo
                    // este condicional podria eliminarse

                }
                //System.out.println("Lineas configuradas de encabezado="+lineasEncabezado);

            } else { // **** *** INICIO DEL PROCESAMIENTO DE LAS MUESTRAS
                // System.out.println("linea leida"+blinea);
                //System.out.println(indPosicion+" lineas y la cantidad de partes de la linea "+parts.length);
                //System.out.println( numMuestra+" Muestra   "+ (indPosicion-lineasEncabezado+1) +" valor: "+ parts[m]);
                //Lazo para almacenar las muestras en variables para cada

                for (int encb = 0; encb < parts.length; encb++) {
                    //System.out.println("Muestras linea " + indPosicion + " nro palabra " + encb + " Encabezado: " + parts[encb]);
                }

                //***************************************************************************************************
                // _________________ las siguientes dos lineas crea una lista con el flag de registros marcados

                if (parts.length > 3) {
                    archflagAnormalidad.add("A");
                } else {
                    archflagAnormalidad.add(" ");
                    //System.out.println("incluido como anomalo ");
                }
                //*************************************************************************************************

                for (int m = 0; m < parts.length; m++) {
                    switch (m) {
                        case 0:
                            archFecha.add(parts[m]);
                            break;
                        case 1:
                            archHora.add(parts[m]);
                            break;
                        case 2:
                            archTotalEnergia = archTotalEnergia + Double.valueOf(parts[m]);
                            //System.out.println("lectura energia "+archTotalEnergia);
                            archEnergia.add(Double.valueOf(parts[m]));
                            break;
                        case 3:
                            archAnormalidad.add(parts[m]);
                            nroMuestraconAarchAnormalidad.add(numLines);//guarda indice de la muestra marcada
                            if (parts[m].contentEquals("A")) {
                                numMuestraAnormal = numMuestraAnormal + 1;
                            }
                            break;
                    }
                }
                numLines++;
            }
        }


        return ("String");
    }


    //  <<<<<<<<<<<<<<<<<<<<            FIn TRATAMIENTO CASOS ESPECIALES         <<<<<<<<<<<<<<<<<<<<<
    //________________________________________________________________________________________________________________


    public List<String> xlistarDirectorio(String path, int casoEspecialActivado) throws IOException {
        File micarpeta = new File(path);
        File[] listaFicheros = micarpeta.listFiles();
        List<String> xlistaArchivo = new ArrayList<>();
        int nDirectorio = 0;
        int nArchivos = 0;

        try {

            for (int i = 0; i < listaFicheros.length; i++)
                if (listaFicheros[i].isDirectory()) {
                    //System.out.println("Este es un directorio " + listaFicheros[i].getName());
                    nDirectorio = nDirectorio + 1;

                } else { // Llena la lista de los archivos contenidos en el directorio

                    nArchivos = nArchivos + 1;

                    // REVISAR Y CONFIRMAR LA VALIDEZ DEL NOMBRE DEL ARCHIVO
                    String extArchivo = "";// variable donde se carga la extension del archivo
                    String xcadena = listaFicheros[i].toString();
                    String xpath = listaFicheros[i].toString();
                    extArchivo = xcadena.substring(xcadena.length() - 4);


                    //System.out.println("nombre del archivo 1,1 "+ cadena.substring(cadena.length()-4, cadena.length()));
                    tipoArchivo = "";//inicializa la variable
                    // System.out.println("Intenta leer archivo "+ xpath);

                    // llama al método contar lineas solo en el caso
                    //  if ((extArchivo.equals(".txt") || extArchivo.equals(".TXT")||extArchivo.equals(".DAT") || extArchivo.equals(".dat"))) {
                    contarLineas(xpath, xpath, casoEspecialActivado);//-----------------------------------------------------------------------------Cuenta lineas y clasifica el archivo
                    //System.out.println(" el tipo es xxxxxx antes de procesar "+tipoArchivo);
                    //}

                    // System.out.println(flagArchPlanillaDecartable+" flag del archivo "+listaFicheros[i]);

                    // Suma a la lista solo aquellos archivos que cumplen las condiciones  de extension y valor indicadas
                    if ((extArchivo.equals(".txt") || extArchivo.equals(".TXT") || extArchivo.equals(".DAT") || extArchivo.equals(".dat")) &&

                            (flagArchPlanillaDecartable == 0) &&
                            (tipoArchivo == "M" || tipoArchivo == "T" || tipoArchivo == "E")) {
                        // System.out.println(">> Tipo DE ARCHIVO  "+tipoArchivo+" Archivo valido " + listaFicheros[i]+" tipo toroide"+tipoToroide);

                        // System.out.println(strTipoToroide+" toroide "+tipoToroide+ i+" lista ficheros "+listaFicheros[i]);// lista ficheros configurados a procesar


                        // chequeo para excluir de la lista los archivos de reporte

                        String excluir_Nomb_reporte = xcadena.substring(xcadena.length() - 4);

                        // >>>>>>>>>>      SUMA UN NUEVO ARCHIVO ALA LISTA DE ARCHIVOS VALIDOS       >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<
                        xlistaArchivo.add(listaFicheros[i].toString());//agrega un nuevo archivo a la lista
                    }

                    //********************************************************************************
                    // System.out.println("Archivo detalle del nombre "+ listaFicheros[i].getName());
                }
            System.out.println("Numero total de archivos en el directorio: " +
                    nArchivos + "  Lista de archivos validos a procesar: " + xlistaArchivo.size());


        } catch (EOFException ex) { // manejo de errores en caso de no poder leer los archivos
            System.out.println("Excepcion leyendo archivos del directorio- Antes de proseguir Cierre " +
                    "todos los archivos abiertos en el directorio de analisis ");
        }
        return (xlistaArchivo);
    }


    public void incializaValoresClaseArchivo() {

        List listaPrueba = new ArrayList();

        this.archtamaño = 0;
        //this.archnombre
        this.Anumlineas = 0;
        int longArreglos = archFecha.size();

        archFecha.clear();
        archHora.clear();
        archVoltaje.clear();
        archCorriente.clear();
        archEnergia.clear();
        archFP.clear();
        archAnormalidad.clear();
        numMuestraAnormal = 0;
        archTotalEnergia = 0.00;
        archflagAnormalidad.clear();

        lineasEncabezadoArchivoMonofasico = 10;

        archPlanillaNombArchMedicionInstalacionTXT.clear(); //arreglos asociados al archivo de integracion único
        archPlanillaIdUsuarioArchInstalacion.clear();//arreglos asociados al archivo de integracion único
        archPlanillaTarifaPlanillaTXT.clear();//arreglos asociados al archivo de integracion único

    }

    public long calcularDiasDate(Date fechaFinal, Date fechaInicial) throws ParseException {

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String temporal;

        Date fechaInicialMuestras = dateFormat.parse(this.archFecha.get(1));
        int indiceTemp = this.archFecha.size() - 1;
        temporal = this.archFecha.get((indiceTemp));
        Date fechaFinalMuestras = dateFormat.parse(temporal);
        long nroDias = ((fechaFinalMuestras.getTime() - fechaInicialMuestras.getTime()) / 86400000);
        return (nroDias);
    }

    public long calcularDiasString(String fechaFinal, String fechaInicial) throws ParseException {

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String temporal;

        Date fechaInicialMuestras = dateFormat.parse(fechaInicial);
        long nroDias;
        int indiceTemp = this.archFecha.size() - 1;
        // temporal= this.archFecha.get((indiceTemp));
        //System.out.println("xxx  fecha inicial "+fechaInicial+" Fechafinal "+fechaFinal);
        temporal = fechaFinal;
        Date fechaFinalMuestras = dateFormat.parse(temporal);

        if (fechaFinalMuestras != fechaInicialMuestras) {
            nroDias = ((fechaFinalMuestras.getTime() - fechaInicialMuestras.getTime()) / 86400000);
        } else {
            nroDias = 0;
        }
        return (nroDias);
    }

    public String calificarMedicionM(String nombrearchivo,
                                     List inicialNombOperadora,
                                     int diasdePermanencia,
                                     int diasMinValidez,
                                     List digitoValidoAno, List BitHabilitacionChequeo) throws ParseException {


        String resultado = ""; // Variable que concatena las pruebas ABCDEFGH
        char charBitdeHabilitacion;
        String auxVarBitHabilitacion;
        for (int i = 0; i < BitHabilitacionChequeo.size(); i++) {
            //System.out.println("Valor del Byte de habilitacion "+BitHabilitacionChequeo.get(i));
            auxVarBitHabilitacion = (String) BitHabilitacionChequeo.get(i);
            charBitdeHabilitacion = auxVarBitHabilitacion.charAt(0);
            switch (i) {  // Derivacion de los casos que se habilitaran según el archivo de configuración
                case 0:
                    if (charBitdeHabilitacion == '1') {
                        resultado = resultado + cumplimientoPruebaA(diasdePermanencia); //System.out.println("Habilitada prueba A");
                    } else {
                        // System.out.println("Des habilitada prueba A");
                    }
                    break;
                case 1:
                    if (charBitdeHabilitacion == '1') {
                        resultado = resultado + cumplimientoPruebaB(diasMinValidez);//System.out.println("Habilitada prueba B");
                        //System.out.println("Habilitada prueba b con "+ " dias de validez="+diasMinValidez);
                    } else {
                        // System.out.println("Des habilitada prueba B ");
                    }
                    break;
                case 2:
                    if (charBitdeHabilitacion == '1') {
                        resultado = resultado + cumplimientoPruebaC();//System.out.println("Habilitada prueba C");
                    } else {
                        // System.out.println("Des habilitada prueba C");
                    }
                    break;
                case 3:
                    if (charBitdeHabilitacion == '1') {
                        resultado = resultado + cumplimientoPruebaD();//System.out.println("Habilitada prueba D");
                    } else {
                        // System.out.println("Des habilitada prueba D");
                    }
                    break;
                case 4:
                    if (charBitdeHabilitacion == '1') {
                        resultado = resultado + cumplimientoPruebaE();//System.out.println("Habilitada prueba E");
                    } else {
                        System.out.println("Des habilitada prueba E");
                    }
                    break;
                case 5:
                    if (charBitdeHabilitacion == '1') {
                        resultado = resultado + cumplimientoPruebaF();//System.out.println("Habilitada prueba F");
                    } else {
                        //System.out.println("Des habilitada prueba F");
                    }
                    break;
                case 6:
                    if (charBitdeHabilitacion == '1') {
                        resultado = resultado + cumplimientoPruebaG(nombrearchivo, inicialNombOperadora, digitoValidoAno);
                        //System.out.println("Habilitada prueba G");
                    } else {
                        // System.out.println("Des habilitada prueba G");
                    }
                    break;
                case 7:
                    if (charBitdeHabilitacion == '1') {
                        resultado = resultado + cumplimientoPruebaH();//System.out.println("Habilitada prueba H");
                    } else {
                        //System.out.println("Des habilitada prueba H");
                    }

                    break;
            }
        }
        return (resultado);
    }

    //************************************
    //METODOS DE PRUEBAS DE CUMPLIMIENTO PARA GENERAR LOS CODIGOS RESULTADO

    private String cumplimientoPruebaA(int diasdePermanencia) throws ParseException {// Prueba que revisa el cumplimiento

        String resultado = "";
        long dias;
        if (archFecha.size() > 1) {
            dias = calcularDiasString(archFecha.get((archFecha.size() - 1)),
                    (archFecha.get(1)));
        } else {
            dias = 0;
        }

        if (dias < diasdePermanencia) {
            resultado = "A";
        } else {
            resultado = "";
        }

        return (resultado);
    }

    private String cumplimientoPruebaB(int diasValidosdeRegistro) throws ParseException {
        // Identifica la cantidad de dias con muestras sin desviación "A" o marca  anómalo "A"
        String resultado = "";
        String pruebaBfechaInicio = archFecha.get(0);// inicializa el valor en el primer registro
        String pruebaBfechaFinal = archFecha.get(0);
        long dias = 0;
        int flagFechaInicial = 0;// flag para registrar marcado de fecha de inicio

        for (int i = 0; i < archFecha.size(); i++) { // Barre toda la lista

            //System.out.println( i+" fecha contenido"+ archFecha.get(i)+" Flag A= "+ archflagAnormalidad.get(i)+ " registro fecha inicial "+ pruebaBfechaInicio);


            if (archflagAnormalidad.get(i).equals("A")) { // caso en que descarta las muestras marcadas
                //System.out.println("Muestra marcada = "+archflagAnormalidad.get(i));

            } else { // REGISTROS VALIDOS NO MARCADOS ----------------------------
                //System.out.println("Contenido de Anormalidad = "+archflagAnormalidad.get(i)+" Prueba B----- registro sin marca "+archFecha.get(i));

                if (flagFechaInicial == 0) {    // Inicializa pruebaFechaInicial con una fecha de registro sin marca
                    pruebaBfechaInicio = archFecha.get(i);
                    // System.out.println("fecha final deslizante  "+ pruebaBfechaInicio);
                    pruebaBfechaFinal = pruebaBfechaInicio; // Iguala las fechas de registro inicial = final
                    flagFechaInicial = 1;     // evita que se cambie la fecha de inicio durante el for
                }

                pruebaBfechaFinal = archFecha.get(i);   // va cambiando de contenido hasta el ultmo valor vaalido de la lista.

                //System.out.println("fecha final deslizante fuera del lazo"+ pruebaBfechaFinal);
            }
        }

        //System.out.println(" fecha inicio = "+pruebaBfechaInicio+ "  Fecha final ="+pruebaBfechaFinal);
        dias = calcularDiasString(pruebaBfechaFinal, pruebaBfechaInicio);
        //System.out.println("dias calculados"+ dias+ "dias de reg>>>>> fecha inicial "+pruebaBfechaInicio+" fecha final "+pruebaBfechaFinal);
        if (dias < diasValidosdeRegistro) {       // >>> CALIFICA CON LA LETRA B DE ACUERDO A LOS DIAS MINIMOS DE REGISTRO *****************
            resultado = "B";
        } else {
            resultado = "";
        }

        return (resultado);
    }

    // // OCURRENCIA COINCIDENCIA DE LA CADENCIA DE 15 MINUTOS EN MAS DE 90% DE LAS MUESTRAS
    // valua formato de cadencia cada 15 minutos
    //____________________________________________________________________________________________________
    private String cumplimientoPruebaC() throws ParseException { // CADENCIA,   se indica abajo el valor nominal abajo y este valor se verifica
        // restando las horas de registro sucesivos ( "n" y "n+1" ) si supera  10%
        // de valoress distintos se activa el calificador " C" dentro de la estructura de valores que se
        // publican en el reporte
        long cadenciaNominal = 15;// valor inicial de comparacion para chequear cadencia
        String resultado = "";
        // System.out.println(" calificacion C");
        long dias;

//*****************************************************************************************************************
        double contCoincidencia = 0;
        double contNoCoincidencia = 0;


        for (int i = 0; i < archHora.size() - 1; i++) {

            //__________________________________________________________________________________
            long resultCadencia = 0;
            resultCadencia = calculocadenciaPruebaC(15, archHora.get(i), archHora.get(i + 1));
            //System.out.println( "La cadencia es "+resultCadencia+" muestra m "+archHora.get(i)+" muestra m "+archHora.get(i+1));

//****   // *********************************************************************************
            //System.out.println(resultCadencia+" cadencia real vs cadencia nominal "+cadenciaNominal);
            if ((resultCadencia == cadenciaNominal) && (archflagAnormalidad.get(i).equals(" "))) {
                //&& (archflagAnormalidad.get(i).equals(" ")){
                //System.out.println("suma valor resultadoCoincidencia "+resultCadencia+"  coincidencia + flagAnormalidad="+archflagAnormalidad.get(i));

                contCoincidencia = contCoincidencia + 1; // Este contador mide la coincidencia en caso de ver el valor nominal "15"
            } else {
                if (archflagAnormalidad.get(i).equals(" ")) { // este condicional excluye de contar  aquellos registros  que se encuentran marcados como anomalos
                    contNoCoincidencia = contNoCoincidencia + 1; // Este contador mide la no cincidencia e incluye contar aquellos que tiene valor "A", ya que caen dentro de este lazo
                }
            }
        }
        //****************************************************************************************************************
        double calcTemp = contCoincidencia / (contCoincidencia + contNoCoincidencia);
        //System.out.println("resultado razon coinVsNocoinc"+calcTemp+" contaCoincide "+contCoincidencia+ " contaNOcoinc "+contNoCoincidencia);
        if (calcTemp > 0.9) {
            resultado = "";
        } else {
            resultado = "C";
        }
        //System.out.println(((contCoincidencia/(contNoCoincidencia+contCoincidencia)+
        //     "  Calificativo="+resultado+" VALOR COINCIDENCIA "+contCoincidencia+" VALOR DE NO coincidencia "+contNoCoincidencia)));
        return (resultado);
    }

    private long calculocadenciaPruebaC(long cadencia, String fechaInicial, String fechaFinal) throws ParseException {


        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        String time1 = fechaInicial;
        String time2 = fechaFinal;
        Date date1 = format.parse(time1);
        Date date2 = format.parse(time2);
        long difference = date2.getTime() - date1.getTime();
        //System.out.println("diferencia de tiempo " + difference);

        if (difference < 0) { /// valor es negativo al realizar la diferencia
            difference = ((86400000) + (difference)); //sumar algebraicamente el valor
        }

        return (difference / 60000);

    }


    //________________________________________________________________________________________________________________________________________
    // Formato de fecha y hora incorrecta (dd/mm/yy, hh:mm)
    private String cumplimientoPruebaD() throws ParseException {
        String resultado = "";

        long dias;

        //Revisión del formato de fecha dd/mm/aa ______________________________________________________________________________________________

        for (int n = 0; n < archFecha.size(); n++) {
            String lengTempFecha = String.valueOf(archFecha.size());//archFecha.size();
            String tempFecha = archFecha.get(n);
            String extraccChar_dd = archFecha.get(n);
            char[] str2 = new char[9];// configura el valor del arreglo para contener la palabra de referencia del nombre del archivo

            String[] parts = tempFecha.split("/");
            //for (int p=0;p<4;p++){
            //System.out.println(p+ " parte="+parts[0]+ " "+parts[1]+" "+parts[2]+ " "+parts.length);
            //}

            //--dias -----
            int cDD = Integer.parseInt(parts[0]);

            if (cDD > 0 && cDD < 32) {
                //System.out.println("el dia esta en formato");
            } else {
                //error en el formato de dias
                resultado = "D";
            }

            //-- Mes -----
            int cMes = Integer.parseInt(parts[1]);

            if (cMes > 0 && cMes < 13) {
                //System.out.println("el Mes esta en formato");
            } else {

                //error en el formato de Mes
                resultado = "D";
            }

            //--  año   -----
            int cAno = Integer.parseInt(parts[2]);

            //System.out.println(cAno+" año ");
            if ((cAno > 15 && cAno < 30) || (cAno > 2015 && cAno < 2030)) { // desde 2015 al 2030 .......
                // System.out.println("el año esta en formato");
            } else {

                //error en el formato de año
                resultado = "D";
            }

            //  REVISION Y CHEQUEO DEL FORMATO DE LA HORA
            String tempHora = archHora.get(n);
            String[] horaParts = tempHora.split(":");
            //System.out.println("HORA "+ horaParts[0]+" "+horaParts[1]);
            int hora = Integer.parseInt(horaParts[0]);// horas
            int min = Integer.parseInt(horaParts[1]);//minutos
            if ((hora >= 0 && hora < 25) && (min >= 0 && min < 60)) {

            } else {
                //error en el formato de hora y minutos
                resultado = "D";
            }
        }

        return (resultado);
    }

    //___________________________________________________________________________________________________________________
    // Archivo con total de energia igual a cero EN TODOS SUS REGISTROS
    private String cumplimientoPruebaE() throws ParseException {// Prueba de energia total
        String resultado = "";
        double sumaEng = 0;

        for (int n = 0; n < archEnergia.size(); n++) {

            if (archflagAnormalidad.get(n).equals(" ")) {
                sumaEng = sumaEng + archEnergia.get(n);
                //System.out.println("LA SUMA ES="+sumaEng);
            }

        }

        if (sumaEng == 0) { // CALIFICA DE ACUERDO AL RESULTADOS DE LA SUMA

            resultado = "E";
        } else {
            resultado = "";
        }
        return (resultado);
    }

    //____________________________________________________________________________________________________________________________________
    //ENERGIA Energía con igual valor (distinta de cero) entre períodos consecutivos en más del 80% de los casos
    private String cumplimientoPruebaF() throws ParseException {

        String resultado = "";
        // double promedio=calcularPromedio(archEnergia);
        // double desviacionStanddlMuestra=calcularDesviacionMuestra(archEnergia,3) ;
        int indice = 0;
        int nMuestrasConstantes = 0;
        //double muestrasiguiente=0.00;

        for (int m = 0; m < archEnergia.size() - 1; m++) {

            //  System.out.println("muestra "+archEnergia.get(m)+"   muestra siguiente "+archEnergia.get(m+1));
            double muestraM0 = archEnergia.get(m); // carga valor de la muestra en variable temporal
            // System.out.println("valor de la muestra de potencia "+muestraM0);

            //System.out.println("Muestra nro= "+ m +"  >>muestra valor inicial M0= "+
            //    archEnergia.get(m)+ " muestra M+1= "+archEnergia.get(m+1));

            if ((archEnergia.get(m).equals(archEnergia.get(m + 1)) && muestraM0 > 0 &&
                    archflagAnormalidad.get(m).equals(" ") && archflagAnormalidad.get(m).equals(" "))) {

                nMuestrasConstantes = nMuestrasConstantes + 1; // Incrementa el contador de muestras iguales
                // System.out.println("Muestra nro= |"+ m + " |fecha= |"+archFecha.get(m)+ "  |Hora= |"+archHora.get(m)+"  |>>muestra valor inicial M0= |"+
                //        archEnergia.get(m)+ " |muestra M+1= |"+archEnergia.get(m+1));
            }
        }
        //System.out.println(" Numero d emuestras constantes ="+nMuestrasConstantes+ " total de muestras del archivo = "+archEnergia.size());
        if (nMuestrasConstantes < archEnergia.size() * 90 / 100) {// compara con el 90% de las mmuestras
            // si lasnMuestrasConstantes o repetidas son mayores al 90% se activa la calificación "F"

            resultado = "";
        } else {
            resultado = "F";
        }
        // System.out.println("nro registros amomalos="+nroMuestraconAarchAnormalidad.size()+" "+ "Resultado Prueba="+resultado+
        //       " nro muestras ctes= "+nMuestrasConstantes+" cantidad de muestras "+archEnergia.size());

        //System.out.println("resultado="+resultado+"  " +nMuestrasConstantes+"  muestras constantes  "+archEnergia.size()+" total de muestras");
        return (resultado);
    }

    //___________________________________________________________________________________________________________________________________________________
    // Verificar del cumplimiento en la asignacion del NOMBRE de archivo
    private String cumplimientoPruebaG(String nombreArchivo,
                                       List inicialNombEmpresa,
                                       List ultimoDigitoAnoValido) throws ParseException {
        String resultado = "";

        //System.out.println("prueba G ensayos  nombre archivo:" +nombreArchivo);
        String ThisnombArchivo = nombreArchivo.substring(nombreArchivo.length() - 12);
        String extArchivo = "";// variable donde se carga la extension del archivo
        String extraccExtenArchi = nombreArchivo;
        String extOrdinalesP678 = nombreArchivo.substring(nombreArchivo.length() - 12);// extrae del nombre del archivo los 12 caracteres q lo definen;
        String extraccAnoP5 = nombreArchivo.substring(nombreArchivo.length() - 12);
        String extraccMesP4 = nombreArchivo.substring(nombreArchivo.length() - 12);
        String extraccEstratoP3 = nombreArchivo.substring(nombreArchivo.length() - 12);
        String extraccTarifas2 = nombreArchivo.substring(nombreArchivo.length() - 12);
        String extraccEmpresaP1 = nombreArchivo.substring(nombreArchivo.length() - 12);

        String extraccCaract = nombreArchivo;

        char[] str2 = new char[12];// configura el valor del arreglo para contener la palabra de referencia del nombre del archivo

        extraccExtenArchi = nombreArchivo.substring(nombreArchivo.length() - 4);// Extrae lkos ultimos digitos de la extension del archivo
        if (extraccExtenArchi.equals(".txt") || extraccExtenArchi.equals(".DAT")
                || extraccExtenArchi.equals(".dat")
                || extraccExtenArchi.equals(".TXT")) {

        } else {
            resultado = "G";
        }

        //EXTRAE LOS DIGITOS 6 7 Y 8 LOS CUALES DEBEN SER NUMERICOS
        extOrdinalesP678.getChars(5, 8, str2, 0);

        char c = str2[0];
        if (str2[0] >= '0' && str2[0] <= '9' && str2[1] >= '0' && str2[1] <= '9' && str2[2] >= '0' && str2[2] <= '9') {           //){


        } else {
            resultado = "G";
        }

        // EXTRAE DIGITO 5 DEL ANO DEL PROYECTO 2018=8 2019=9 2020=0 /////////////////////   DIGITO AÑ0S VALIDOS            ////////////////////////////////////
        extraccAnoP5.getChars(4, 5, str2, 0);
        // if (str2[0]!='9'&&str2[0]!='8'&&str2[0]!='0'){

        //    resultado="G";
        //} else{

        //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

        for (int i = 0; i < ultimoDigitoAnoValido.size(); i++) {
            //System.out.println("Antes "+nombreArchivo+ " " +ultimoDigitoAnoValido.get(i)+" "+str2[0]);

            String StrnCaracter = (String) ultimoDigitoAnoValido.get(i); //estas lineas extraen el digito (caracter) del String que viene del archivo ConfigMed
            char caracter = StrnCaracter.charAt(0);

            if (caracter != str2[0]) {
                if (i == ultimoDigitoAnoValido.size() - 1) {
                    // System.out.println(nombreArchivo+" incumple la norma");
                    resultado = "G";
                }
            } else {
                // System.out.println("despues "+ nombreArchivo+">>...xxxx....... Valor " + str2[0] + "=" + ultimoDigitoAnoValido.get(i) + "=== " + caracter);
                break;
            }
        }
        //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

        // EXTRAE DIGITO 4 DEL mes 1 a 9 y O N D
        extraccMesP4.getChars(3, 4, str2, 0);
        if ((str2[0] >= '0' && str2[0] <= '9') || (str2[0] == 'O' || str2[0] == 'N' || str2[0] == 'D')) {


        } else {
            resultado = "G";
        }

        // EXTRAE DIGITO 3 asociado a los estratos del 1 al 7
        extraccEstratoP3.getChars(2, 3, str2, 0);

        if (str2[0] < '1' && str2[0] > '7') {
            resultado = "G";
        }

        // EXTRAE DIGITO 2 Categoria de TARIFAS R G 4 2
        extraccTarifas2.getChars(1, 2, str2, 0);

        if (str2[0] != 'R' && str2[0] != 'G' && str2[0] != '4' && str2[0] != '2') {
            resultado = "G";
        }
        //---------------------------------------------------------------------------
        // EXTRAE DIGITO 1 asociado a cada una de las empresas
        extraccEmpresaP1.getChars(0, 1, str2, 0);

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~xxxxxxxxxxxxxxxxxxxxxxxxxxxx

        for (int i = 0; i < inicialNombEmpresa.size(); i++) { // barra la lista con las iniciales de las empresas
            //System.out.println("Antes barrido "+nombreArchivo+ " " +inicialNombEmpresa.get(i)+" "+str2[0]);

            String StrnCaracter = (String) inicialNombEmpresa.get(i); //estas lineas extraen el digito (caracter) del String que viene del archivo ConfigMed
            char caracter = StrnCaracter.charAt(0);

            if (caracter != str2[0]) {
                if (i == inicialNombEmpresa.size() - 1) { //si no encuentra coincidencia con las lista de iniciales asume el incumplimiento de la convension
                    // System.out.println(nombreArchivo+" incumple la norma");
                    resultado = "G";
                }
            } else {
                //  System.out.println("coincidencia Linicial Empresa "+ nombreArchivo+">>...xxxx....... Valor " + str2[0] + "=" + inicialNombEmpresa.get(i) + "=== " + caracter);
                break;
            }
        }
        //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

        return (resultado);
    }

    //---------------------------------------------------------------------------------------------
    private String cumplimientoPruebaH() throws ParseException { // control de energia
        int valorUmbral = 1 / 10;
        String resultado = "";
        double energProm = archTotalEnergia / archFP.size() / 4;
        double sumaMuestrasEnerg = 0.00;
        int cantMuestras = 0;
        //______________________________________________________________________________________
        double muestraEnergia = 0;

        for (int n = 0; n < archEnergia.size(); n++) {

            if (archflagAnormalidad.get(n).equals(" ")) {

                sumaMuestrasEnerg = sumaMuestrasEnerg + archEnergia.get(n);
                muestraEnergia = archEnergia.get(n);
                // System.out.println("valoresmanejados "+muestraEnergia+"  "+archEnergia.get(n));

                if (muestraEnergia < 0) { // sia existe alguna muestra de energia
                    cantMuestras = cantMuestras + 1;

                }
            }
        }
        if (cantMuestras > 0) {
            // System.out.println("RESULTADO eval =" + resultado+" cantmuestrasnegativas"+cantMuestras);
            resultado = "H";
        }
        return (resultado);
    }

    //**********************************************************************************************************************
    public double calcularPromedio(List array) {
        double resultado = 0.00;
        for (int i = 0; i < array.size(); i++) {
            resultado = resultado + Double.parseDouble(String.valueOf(array.get(i)));
        }
        resultado = resultado / array.size();

        return (resultado);
    }

    //*********************************
    public double calcularDesviacionMuestra(List array, double media) {

        double resultIntermedio = 0.00;
        double resultado = 0.00;

        for (int i = 0; i < array.size(); i++) {
            resultIntermedio = resultIntermedio + Math.pow((Double.parseDouble(String.valueOf(array.get(i))) - media), 2);//calcula diferencia de la media a cuadrado
        }
        resultado = (resultIntermedio) / (Double.parseDouble(String.valueOf(array.size())));
        resultado = Math.sqrt(resultado);

        return (resultado);
    }


    //*****************************
    // Este metodo carga los valores de archivo y usuario en los vectores  que seran utilizados para incorporarlos
    // al momento de construir el nuevoarchivo unificado de datos

    public void leerArchInstalacion(String sdirectorioTrabajo,String nombArchivoIntalacion) throws IOException {
        //System.out.println(">>>>  >>>>>>>  >>>>> >>>>>> EL archivo de instalacion a  considerar es : "+nombArchivoIntalacion);

        //  NOMBRE DEL ARCHIVO PLANILLA DE INSTALACION A EDITAR PARA CONSTRUIR NUEVO ARCHIVO
        // String archInstalacion="LC196_PLANILLA_INTALACION.txt"; //  1- PLANILLA instalacion   IDUSUARIO Y ARCHIVO
        //String archInstalacion = "NC196_USUARIOS_SELECCIONADOS.txt";//  2- PLANILLA usuarios seleccionados IDUSUARIO Y ARCHIVO
        // String ruta_ArchInstalacion = sdirectorioTrabajo + "\\" + archInstalacion;
        String ruta_ArchInstalacion = nombArchivoIntalacion; // toma de los parametros que llegan del constructor para definir
        // el archivo de "usuarios_seleccionados.txt" a procesar
        //*******************************************************

        boolean flagArchivoUsuarioconMes=false; // flag activacion cambio estructura de lectura

        //String pathArchInstalacion=System.getProperty("user.dir");
        // System.out.println(archInstalacion + " Mensaje desde Archivo leerConfInstalación path=" + ruta_ArchInstalacion);

        System.out.println(" Mensaje desde Archivo leerConfInstalación path=" + ruta_ArchInstalacion); // indica ruta y nombre del archivo
        // "xxxx_Usuarios_Seleccionados-txt" a ser leido

        File cargaConfigMed = new File(ruta_ArchInstalacion);
        FileReader configReader = null;

        try {
            configReader = new FileReader(ruta_ArchInstalacion);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int numLinesConfigMed = 0;
        int lineasEncabezadoArchivo = 10; // valor fijo de las lineas descriptivas del archivo ConfigMed.TXT
        //try {
        BufferedReader buffer = new BufferedReader(configReader);
        String blinea;
        String[] parts;
        int indicadorPosic = 0;
        while ((blinea = buffer.readLine()) != null) {
            //******** lineas para ller la configuracion y cargar en las variables
            int numMuestra = indicadorPosic - lineasEncabezadoArchivo + 1;// va indicando el numero de la linea para cada  muestra

            //System.out.println("line  en registro de prueba "+ blinea);
            indicadorPosic = indicadorPosic + 1;

            if (flagArchivoUsuarioconMes) { // caso de utilizar campo mes
                parts = blinea.split(";");
            }else {
                parts = blinea.split("\\|");
            }

            if (indicadorPosic > 1) { // este es el condicional para que solo procese los valores y salte el encabezado

                for (int p = 0; p < parts.length; p++) {
                    //System.out.println(" valor del  campo "+p+" parts "+parts[p] );
                    // swich de bifurcacion
                    switch (p) {
                        case 0:
                            if (flagArchivoUsuarioconMes){

                            }else{// campo nombreArch SW 0
                                System.out.println(p+"CAMPO SW0 nombre archivo "+parts[p]);
                                nombArchMedicionInstalacionTXT.add(parts[p]); // Carga el nombre del archivo de medicion
                            }
                            break;

                        case 1:

                            if (flagArchivoUsuarioconMes){// campo nombreArch SW 1
                                System.out.println(p+"CAMPO SW1 nombre archivo "+parts[p]);
                                nombArchMedicionInstalacionTXT.add(parts[p]); // Carga el nombre del archivo de medicion
                            }else{ // Caso arch sin campo mes, se corre Id usuario a este punto

                                System.out.println(p + "CAMPO ID " + parts[p]);
                                idUsuarioArchInstalacion.add(parts[p]);// carga el número de usuario asociado a la medicion
                            }

                            //System.out.println(indicadorPosic+" valor del primer campo del archivo de instalacion "+parts[1] );
                            break;
                        case 2:

                            if (flagArchivoUsuarioconMes) {// caso sw 2
                                System.out.println(p + "CAMPO ID " + parts[p]);
                                idUsuarioArchInstalacion.add(parts[p]);// carga el número de usuario asociado a la medicion
                            }else{

                            }

                            //System.out.println(indicadorPosic+ " valor del segundo campo del archivo de instalacion "+parts[2]);
                            break;
                        case 8: // caso de habilitacion de la tarifa para archivos sin mes
                            if (flagArchivoUsuarioconMes) {// caso sw 9 campo de tarifa

                            }else{// caso sw 8 captura tarifa archivo con campo mes
                                System.out.println(p + "CAMPO  tarifa " + parts[p]);
                                tarifaPlanillaTXT.add(parts[p] + "-" + parts[p + 1]);// carga el número de usuario asociado a la medicion
                            }

                            break;
                        case 9:
                            //System.out.println(p + "CAMPO Tarifa " + parts[p]+ parts[p + 1]);
                            if (flagArchivoUsuarioconMes) {// caso sw 9 campo de tarifa
                                System.out.println(p + "CAMPO  tarifa " + parts[p]);
                                tarifaPlanillaTXT.add(parts[p] + "-" + parts[p + 1]);// carga el número de usuario asociado a la medicion
                            }else{

                            }
                            //System.out.println(indicadorPosic+ " valor del  campo tarifa del archivo de instalacion "+parts[9]);
                            break;
                    }
                }
            }else { // TRATAMIENTO DEL ENCABEZADO

                System.out.println( "Encabezado Blinea "+blinea);
                String[] partsFlag = blinea.split(";");

                if (partsFlag[0].equals("Mes")){// CASO EDELAP
                    flagArchivoUsuarioconMes=true;
                    System.out.println(flagArchivoUsuarioconMes+" CSO EDELAP "+partsFlag[0]+" "+partsFlag[1]);

                }else{// CASO EDES
                    System.out.println( flagArchivoUsuarioconMes+" CASO EDES "+" "+partsFlag[0]);
                }
            }




        }
    } // fin metodo leerArchInstalacion

    //**************************************************************************************
    //METODO PARA LA BUSQUEDA DEL NUMERO DE USUARIO DADO EL NOMBRE DEL ARCHIVO
    public String buscarUsuarioxNombArchivoInstalacion(String stringNombArchivoMedicionTXT) {

        //System.out.println(nombArchMedicionInstalacionTXT.size()+" codigo buscado en buscarUsuarioxNOmbreArchivoIntalacion "+stringNombArchivoMedicionTXT);

        String resultado = "NoEncontrado"; // nro de usuario resultante luego de buscar nombre archv
        // sino hay coincidencia colocara resultado="NoEncontrado"

        int coincidencia = 0; // variable de control

        for (int i = 0; i < nombArchMedicionInstalacionTXT.size(); i++) {
            //System.out.println(i+" valor del vector "+nombArchMedicionInstalacionTXT.get(i));
            if (stringNombArchivoMedicionTXT.equals(nombArchMedicionInstalacionTXT.get(i))) {

                // System.out.println("coincidencia en el lazo de busqueda");
                coincidencia = 1;
                resultado = idUsuarioArchInstalacion.get(i); // Id usuario corespondiente al nombre de archivo de la lista
            }
        }

        return (resultado);
    }

    //**************************************************************************************
    //METODO PARA LA BUSQUEDA DE LA TARIFA del USUARIO DADO EL NOMBRE DEL ARCHIVO
    public String buscarTarifaUsuarioxNombArchivoInstalacion(String stringNombArchivoMedicionTXT) {

        //System.out.println(nombArchMedicionInstalacionTXT.size()+" codigo buscado en buscarUsuarioxNOmbreArchivoIntalacion "+stringNombArchivoMedicionTXT);

        String resultado = "NoEncontrado"; // nro de usuario resultante luego de buscar nombre archv
        // sino hay coincidencia colocara resultado="NoEncontrado"

        int coincidencia = 0; // variable de control

        for (int i = 0; i < nombArchMedicionInstalacionTXT.size(); i++) {
            //System.out.println(i+" valor del vector "+nombArchMedicionInstalacionTXT.get(i));
            if (stringNombArchivoMedicionTXT.equals(nombArchMedicionInstalacionTXT.get(i))) {

                // System.out.println("coincidencia en el lazo de busqueda");
                coincidencia = 1;
                resultado = tarifaPlanillaTXT.get(i); // Id usuario corespondiente al nombre de archivo de la lista
            }
        }

        return (resultado);
    }

}
