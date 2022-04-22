package com.company;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;


public class AbsArchivo<date> {


       // ___ En este punto ya se cargaron los parámetros de configuracion del archivo
    // INICIALIZACION DE ATRIBUTOS DE LA CLASE:  ARREGLOS Y VARIABLES A SER UTILIZADOS EN EL PROCESAMIENTO DEL ARCHIVO

    public String sDirectorioTrabajo; // es el directorio de trabajo asignado en el constructor y se define en la clase AbsMediciones
    public int lineasEncabezadoArchivoMonofasico=10; //Valor para identificar el valor del encabezado monofásico
    public int lineasEncabezadoArchivoAMI =14; //Valor para identificar el valor del encabezado Trifásico

    public String PrimeraPalabraValidaArchivo;// contiene la primera palabra de referencia de la linea 1 para identificar validez del tipo de formato viene de AbsMediciones
    public String tempPrimeraPalabraLeidaArchivo;// contiene la primera palabra de la primera linea del archivo que se esta procesando, Cmbia dinamicamente
    public String tipodeFormatoaLeer=""; // Es el nombre del formato estandar que será leido Formato_01... Formato_nn
    public int numeroTotalDigitosNombreArchivoxtipoFormato=10;//numero de digitos que componen el archivo de acuerdo al tipo de formato elegido


    public String nombArch; // variable donde dinámicamente se va identificando el archivo que se está procesando
    public int flagArchPlanillaDecartable = 0; // "0" el archivo no es planilla  "1" archivo descartable de ser abalizado

    public List listaPrueba=new ArrayList();
    public String tipoArchivo="M"; //Valor que define el tipo de archivo a leer "M" Monofasico "T" trifasico  "E" Especial

    public int tipoToroide=0; // valor de del toroide valores= 800/200/100/35
    public String strTipoToroide="0"; // Valor inicial

    public int archtamaño;
    public String nombArchivoParaError; // variable que contiene el nombre del archivo que se esta procesando para el manejo de Errores
    public int Anumlineas;

    public List<String> archIdMedidorAMI = new ArrayList<>();
    public List<String> archFecha = new ArrayList<>();
    public List<String> archHora = new ArrayList<>();
    public List <Double> archVoltaje = new ArrayList<>();
    public List <Double> archVoltajeB = new ArrayList<>();
    public List <Double> archVoltajeC = new ArrayList<>();

    public List <Double> archCorriente = new ArrayList<>();
    public List <Double> archEnergia = new ArrayList<>();
    public List <Double> archEnergiaReactiva = new ArrayList<>();
    public List <Double> archPotencia = new ArrayList<>();
    public List <Double> archPotenciaReactiva = new ArrayList<>();
    public List <Double> potenciaArchOrigGenerador = new ArrayList<>();
    public List <Double> energiaArchOrigGenerador = new ArrayList<>();

    public List <Double> archFP = new ArrayList<>();
    public List <String> archAnormalidad = new ArrayList<>();
    public List <String> archflagAnormalidad = new ArrayList<>();

    public  String multiploUnidadPotencia="";// unidad que se asocia a la magnitud de la lectura
    public  String multiploUnidadPotenciaReactiva="";// unidad que se asocia a la magnitud de la lectura

    public List <Integer> nroMuestraconAarchAnormalidad = new ArrayList<>();

    public int numMuestraAnormal=0;
    public double archTotalEnergia=0.00;
    public double archTotalEnergiaReactiva=0.00;

    public List <String> listaErroresArch =new ArrayList<>();

    //*****  NUEVAS VARIABLE INCORPORADAS PARA RELACIONAR LOS ARCHIVOS DE MEDICIONES CON EL ID DE USUARIOS
    //       VARIABLES LEIDAS DEL ARCHIVO PLANILLA_XXXXX ADEMÁS   **********************************************
    public List<String> idUsuarioArchInstalacion = new ArrayList<>(); // Campo "IdUsuario" leido del archivo complementario " Planilla...."
    public List<String> nombArchMedicionInstalacionTXT = new ArrayList<>(); // Campo "Nombre del archivo de mediciones" leido del archivo complementario " Planilla...."
    public List<String> tarifaPlanillaTXT = new ArrayList<>(); // Campo "Tarifa asociado al usuario" leido del archivo complementario " Planilla...."
    public List<String> condicionUPR = new ArrayList<>(); // Productor  renovable

    public List<String> factorCorreccionTranformacion=new ArrayList<>();// factor que se está en
                                                        // el archivo Planilla de Instalacion


    public List<String> archPlanillaIdUsuarioArchInstalacion = new ArrayList<>(); // Campo "IdUsuario" leido del archivo complementario " Planilla...."
    public List<String> archPlanillaNombArchMedicionInstalacionTXT = new ArrayList<>(); // Campo "Nombre del archivo de mediciones" leido del archivo complementario " Planilla...."
    public List<String> archPlanillaTarifaPlanillaTXT = new ArrayList<>(); // Campo "Tarifa asociado al usuario" leido del archivo complementario " Planilla...."

    public List<Double> getArchPotenciaReactiva() {
        return archPotenciaReactiva;
    }

    public void setArchPotenciaReactiva(List<Double> archPotenciaReactiva) {
        this.archPotenciaReactiva = archPotenciaReactiva;
    }


// Getters ansd Setters

    public String getNombArch() {
        return nombArch;
    }

    public void setNombArch(String nombArch) {
        this.nombArch = nombArch;
    }

    public String getNombArchivoParaError() {
        return nombArchivoParaError;
    }

    public void setNombArchivoParaError(String nombArchivoParaError) {
        this.nombArchivoParaError = nombArchivoParaError;
    }

    public String getPrimeraPalabraValidaArchivo() {
        return PrimeraPalabraValidaArchivo;
    }

    public void setPrimeraPalabraValidaArchivo(String primeraPalabraValidaArchivo) {
        PrimeraPalabraValidaArchivo = primeraPalabraValidaArchivo;
    }

    public String getTempPrimeraPalabraLeidaArchivo() {
        return tempPrimeraPalabraLeidaArchivo;
    }

    public void setTempPrimeraPalabraLeidaArchivo(String tempPrimeraPalabraLeidaArchivo) {
        this.tempPrimeraPalabraLeidaArchivo = tempPrimeraPalabraLeidaArchivo;
    }

    public int getLineasEncabezadoArchivoMonofasico() {
        return lineasEncabezadoArchivoMonofasico;
    }

    public void setLineasEncabezadoArchivoMonofasico(int lineasEncabezadoArchivoMonofasico) {
        this.lineasEncabezadoArchivoMonofasico = lineasEncabezadoArchivoMonofasico;
    }
    public int getLineasEncabezadoArchivoTrifasico() {
        return lineasEncabezadoArchivoAMI;
    }

    public void setLineasEncabezadoArchivoTrifasico(int lineasEncabezadoArchivoTrifasico) {
        this.lineasEncabezadoArchivoAMI = lineasEncabezadoArchivoTrifasico;
    }

    public int getFlagArchPlanillaDecartable() {
        return flagArchPlanillaDecartable;
    }

    public void setFlagArchPlanillaDecartable(int flagArchPlanillaDecartable) {
        this.flagArchPlanillaDecartable = flagArchPlanillaDecartable;
    }

    public int getTipoToroide() {
        return tipoToroide;
    }

    public void setTipoToroide(int tipoToroide) {
        this.tipoToroide = tipoToroide;
    }

    public List<String> getListaErroresArch() {
        return listaErroresArch;
    }

    public void setListaErroresArch(List<String> listaErroresArch) {
        this.listaErroresArch = listaErroresArch;
    }

    public String getsDirectorioTrabajo() {
        return sDirectorioTrabajo;
    }

    public void setsDirectorioTrabajo(String sDirectorioTrabajo) {
        this.sDirectorioTrabajo = sDirectorioTrabajo;
    }

    public String getMultiploUnidadPotencia() {
        return multiploUnidadPotencia;
    }

    public void setMultiploUnidadPotencia(String multiploUnidadPotencia) {
        this.multiploUnidadPotencia = multiploUnidadPotencia;
    }

    public String getTipodeFormatoaLeer() {
        return tipodeFormatoaLeer;
    }

    public void setTipodeFormatoaLeer(String tipodeFormatoaLeer) {
        this.tipodeFormatoaLeer = tipodeFormatoaLeer;
    }

    public List<Double> getArchPotencia() {
        return archPotencia;
    }

    public void setArchPotencia(List<Double> archPotencia) {
        this.archPotencia = archPotencia;
    }

    public List<Double> getPotenciaArchOrigGenerador() {
        return potenciaArchOrigGenerador;
    }

    public void setPotenciaArchOrigGenerador(List<Double> potenciaArchOrigGenerador) {
        this.potenciaArchOrigGenerador = potenciaArchOrigGenerador;
    }

    public List<Double> getEnergiaArchOrigGenerador() {
        return energiaArchOrigGenerador;
    }

    public void setEnergiaArchOrigGenerador(List<Double> energiaArchOrigGenerador) {
        this.energiaArchOrigGenerador = energiaArchOrigGenerador;
    }

    public List<String> getIdUsuarioArchInstalacion() {
        return idUsuarioArchInstalacion;
    }

    public void setIdUsuarioArchInstalacion(List<String> idUsuarioArchInstalacion) {
        this.idUsuarioArchInstalacion = idUsuarioArchInstalacion;
    }

    public List<String> getNombArchMedicionInstalacionTXT() {
        return nombArchMedicionInstalacionTXT;
    }

    public void setNombArchMedicionInstalacionTXT(List<String> nombArchMedicionInstalacionTXT) {
        this.nombArchMedicionInstalacionTXT = nombArchMedicionInstalacionTXT;
    }

    public List<String> getTarifaPlanillaTXT() {
        return tarifaPlanillaTXT;
    }

    public void setTarifaPlanillaTXT(List<String> tarifaPlanillaTXT) {
        this.tarifaPlanillaTXT = tarifaPlanillaTXT;
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

    public double getArchTotalEnergiaReactiva() {
        return archTotalEnergiaReactiva;
    }

    public void setArchTotalEnergiaReactiva(double archTotalEnergiaReactiva) {
        this.archTotalEnergiaReactiva = archTotalEnergiaReactiva;
    }

    public List<Double> getArchEnergiaReactiva() {
        return archEnergiaReactiva;
    }

    public void setArchEnergiaReactiva(List<Double> archEnergiaReactiva) {
        this.archEnergiaReactiva = archEnergiaReactiva;
    }

    public int getNumeroTotalDigitosNombreArchivoxtipoFormato() {
        return numeroTotalDigitosNombreArchivoxtipoFormato;
    }

    public void setNumeroTotalDigitosNombreArchivoxtipoFormato(int numeroTotalDigitosNombreArchivoxtipoFormato) {
        this.numeroTotalDigitosNombreArchivoxtipoFormato = numeroTotalDigitosNombreArchivoxtipoFormato;
    }

    public void setArchFecha(List<String> archFecha) {
        this.archFecha = archFecha;
    }

    public String getMultiploUnidadPotenciaReactiva() {
        return multiploUnidadPotenciaReactiva;
    }

    public void setMultiploUnidadPotenciaReactiva(String multiploUnidadPotenciaReactiva) {
        this.multiploUnidadPotenciaReactiva = multiploUnidadPotenciaReactiva;
    }

    public List<String> getArchHora() {
        return archHora;
    }

    public void setArchHora(List<String> archHora) {
        this.archHora = archHora;
    }

    public List<String> getCondicionUPR() {
        return condicionUPR;
    }

    public void setCondicionUPR(List<String> condicionUPR) {
        this.condicionUPR = condicionUPR;
    }

    public List<String> getFactorCorreccionTranformacion() {
        return factorCorreccionTranformacion;
    }

    public void setFactorCorreccionTranformacion(List<String> factorCorreccionTranformacion) {
        this.factorCorreccionTranformacion = factorCorreccionTranformacion;
    }

    public List<String> getArchIdMedidorAMI() {
        return archIdMedidorAMI;
    }

    public void setArchIdMedidorAMI(List<String> archIdMedidorAMI) {
        this.archIdMedidorAMI = archIdMedidorAMI;
    }
    // fin getter a setters
//-----------------------------------------------------------------------
    // CONSTRUCTOR

    public AbsArchivo( String primeraPalabraValidaArchivo, int anumlineas, String sDirectorioTrabajo,String tipoFormatoaLeer,int numeroTotalDigitosNombreArchivoxtipoFormato) {
        PrimeraPalabraValidaArchivo = primeraPalabraValidaArchivo;
        this.Anumlineas = anumlineas;
        this.sDirectorioTrabajo=sDirectorioTrabajo;
        this.tipodeFormatoaLeer=tipoFormatoaLeer;
        this.numeroTotalDigitosNombreArchivoxtipoFormato=numeroTotalDigitosNombreArchivoxtipoFormato;
    }  // fin constructor




    public int contarLineas(String path, String nombArch,int condicionEspecial) throws IOException {

        //incrementarBarraProgreso();

        //CONTAR LINEAS DEL ARCHIVO SELECCIONADO
        // Apertura  y lectura de cantidad de lineas del archivo

        flagArchPlanillaDecartable = 0;// inicializa la variable "0" no descarta   "1" se descarta el archivo por coincidir con el encabezado de la planilla
        File input = new File(path); // Lectura del archivo a ser leido
        FileReader fileReader = new FileReader(nombArch);
        int numLines = 0;
        //lineasEncabezadoArchivoMonofasico : esta variable declarada de forma general para leer los archivos
        int lineasEncabezadoArchivo=lineasEncabezadoArchivoMonofasico; // valor fijo de las lineas descriptivas del archivo
        try {
            BufferedReader buffer = new BufferedReader(fileReader);
            String blinea;

            int indicadorPosic=0;
            while ((blinea=buffer.readLine())!=null){
                //revisar


                //------------------
                // Variable para descartar archivos ---
                String strAnalisisDescarteTipoArchivoPlanilla = blinea; // guarda en una variable auxiliar el contenido de la linea leida del archivo de arjustes
                //******** lineas adicionadas para discriminar registros monofasicos y trifasicos
                //linea 4 palabra 1: 100=M  800=T  ; configuracion especial x = E
                int numMuestra=indicadorPosic-lineasEncabezadoArchivo+1;// va indicando el numero de la linea para cada  muestra
               // System.out.println(+indicadorPosic+"linea del archivo  "+ blinea);
                indicadorPosic = indicadorPosic + 1;
                String[] parts = blinea.split("\\s+");

                // MANEJO DEL ENCABEZADO
                //*************************************************************************************************
                //------ Variable utilizada para descartar lectura erroneas de las planillas Uuario  e Instalacion
                String[] partsArchivoPlanilla = strAnalisisDescarteTipoArchivoPlanilla.split(";");
                //System.out.println(" valor de la primera linea de la planilla "+strAnalisisDescarteTipoArchivoPlanilla);

                //____________________________________________________________________________________________________________________________


        // ** CONFIGURACION CON BASE EN EL CONTENIDO DEL ENCABEZADO DEL ARCHIVO
                if (indicadorPosic < lineasEncabezadoArchivo) { //valor que delimita la lectura del encabezado
                    // System.out.println("contenido del encabezado, linea nro " + indicadorPosic + " numero de partes"+ parts.length+" contenido: " + blinea);//

                    for (int encb = 0; encb < parts.length; encb++) {
                         //System.out.println(" Division de linea ENCABEZADO linea " + indicadorPosic + " nro palabra " + encb + " Encabezado: " + parts[encb]);

// >>>>>>>>>>>>>>>>>>>>>>>>>>>>> ACTUALIZA VALOR DEL TOROIDE UTILIZADO
                        if ((indicadorPosic == 4) && (encb == 1)) { // *****    actualiza el valor de tipoToroide con valor del archivo
                            strTipoToroide = parts[encb];// carga el valor del toroide desde el archivo
                            // System.out.println("> Tipo de toroide " + parts[encb] +"valor String->int"+strTipoToroide+" "+tipoToroide);
                        }// FIN PROCESO CARGA DEL TOROIDE <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

                        //*** carga primera palabra del archivo a ser procesado
                        if((indicadorPosic == 1) && (encb == 0)){

                            tempPrimeraPalabraLeidaArchivo= parts[0];// variable accesible en toda la clase con el contenido de la primera palabra del archivo

                            // ASIGNA TIPO PARA LOS FORMARTOS F1,2,3,4,5,6,,8,9,N, EXCEPTO EL FORMATO 2 QUE ES LA BASE abstracta Base
                            // El formato 2 tiene tipo M=1F y T=3F

                            if ((tipodeFormatoaLeer.equals("FORMATO_01"))||
                                    (tipodeFormatoaLeer.equals("FORMATO_03")|| (tipodeFormatoaLeer.equals("FORMATO_04") ||
                                            (tipodeFormatoaLeer.equals("FORMATO_05")|| (tipodeFormatoaLeer.equals("FORMATO_06")||
                                            (tipodeFormatoaLeer.equals("FORMATO_07")|| (tipodeFormatoaLeer.equals("FORMATO_09")
                                            ))))))){
                                tipoArchivo="F";
                            }

                            System.out.println("Nombre Archivo "+nombArch+" °°°°°°°°°°°°°°°°°°°°| ---> Referencia valor de la palabra= "+parts[0] );
                        }//-----------------------------------------------------------

                       // System.out.println("CondicionEspecial="+condicionEspecial+" tipo de toroide="+tipoToroide+ " TIPO ARCHIVO="+tipoArchivo + " "+parts[encb]+ "  "+nombArch);

                        // condicion especial       // ASIGNACION DE LA CONDICION ESPECIAL DEL ARCHIVO CASO "1"
                        if (condicionEspecial!=0 && condicionEspecial!=1) { // iniciliza la variable con el valor no reconocido
                            tipoArchivo = "X";
                        }
                        if ((indicadorPosic == 4) && (encb == 1) && ((parts[encb].contentEquals("35")||
                                parts[encb].contentEquals("100")||parts[encb].contentEquals("200")||
                                parts[encb].contentEquals("800")))&& condicionEspecial==1) {
                            tipoToroide = Integer.parseInt(parts[encb]);// este caso carga 800
                            tipoArchivo = "E";
                          //  System.out.println("CondicionEspecial="+condicionEspecial+" tipo de toroide="+tipoToroide+
                            //        " TIPO ARCHIVO="+tipoArchivo + " "+parts[encb]+ "  "+nombArch);
                        }

                    }

                    // CASO ADICIONAL PARA DETECTAR EL TIPO DE ARCHIVO QUE SE ESTA LEYENDO
                } else{ //condicion en el cual esta leyendo las muestras del archivo

                    //System.out.println("zseg nombArchivoyTipo" + nombArch+" partes "+parts.length);

                   if ((parts.length>=5 && parts.length<7)&&(indicadorPosic > lineasEncabezadoArchivo+5)&& tipoArchivo != "E"){
                       // System.out.println(nombArch+" El archivo detectado es MONOFÁSICO" );
                        tipoArchivo = "M";
                    }else{
                       // if ((parts.length>=7 && parts.length<=8)&&(indicadorPosic > lineasEncabezadoArchivo+5&& tipoArchivo != "E")) {
                if ((parts.length>=7 && parts.length<=17)&&(indicadorPosic > lineasEncabezadoArchivo+5&& tipoArchivo != "E")){
                          //  System.out.println(nombArch + " El archivo detectado es TRIFÁSICO");
                            tipoArchivo = "T";
                 }
                 if ((parts.length>=7 && parts.length<27)&&(indicadorPosic > lineasEncabezadoArchivo+5&& tipoArchivo != "E")){
                     //  System.out.println(nombArch + " El archivo detectado es TRIFÁSICO");
                     tipoArchivo = "T";
                 }

               }
               }
                //*************************************************************
                numLines++;
                //-------- ROMPE EL CICLO WHILE DESPUES DE LA LINEA 50 --------------
                if(numLines>50){// rompe el lazo while se presume en general que el encabezado es menor a 50 lineas
                   break;
                }
            }
        } catch (Exception ex) {
            //  GENERA UN ARCHIVO DE ERROR CON LA DESCRIPCION DEL PROBLEMA>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
            System.out.println("Excepcion- Antes de proseguir Cierre todos los archivos abiertos en el directorio de analisis ");
            JOptionPane.showMessageDialog(null, "Se encontró un error de formato de Fecha quite el archivo : "+nombArchivoParaError );

            System.out.println("error ruta archivo:  "+path+"\\Resultados\\ReporteERROR2.txt");
            FileWriter fwErrorArchivoReport = new FileWriter(path+"\\Resultados\\ReporteERROR2.TXT");
            BufferedWriter bWErrorArchivoReport= new BufferedWriter(fwErrorArchivoReport);
            PrintWriter printErrorArchivoReport = new PrintWriter(bWErrorArchivoReport);

            printErrorArchivoReport.write("Elimine el archivo "+path+"  del directorio y vuelva a correr el analizador de mediciones. Existe un error de formato");
            printErrorArchivoReport.close();
            bWErrorArchivoReport.close();
            System.exit(0);

            ex.printStackTrace();
        }
//----------------------------------------------------------------------------------------------------------------------------

        return (numLines);
    }

    public String generarTokensMonofasico(String path, String nombArch, int numlineas) throws IOException, ParseException {

        // Prueba para generar archivos normalizados en la misma corrida

        if (habilitadaSalidaArchNormalizado()){// llama a la subrutina para identificar si está habilitada
            CompilArchivo compilArchivo=new CompilArchivo(numlineas);
            System.out.println("HABILITACION SALIDA ARCHIVOS "+habilitadaSalidaArchNormalizado());
            compilArchivo.generarTokensMonofasico(path,nombArch,numlineas); // genera los archivos normalizados en el directorio Nuevo
        }else{
            System.out.println("NO HABILITADA la generacion de archivos estandar");
        }


        int lineasEncabezado=lineasEncabezadoArchivoMonofasico; //  esta variable se declara al inicio de la clase archivo
                                                    // VALOR NORMAL 10 ---   LINEAS A CONSIDERAR COMO ENCABEZADO DEL ARCHIVO
        File input = new File(path);
        FileReader fileReader = new FileReader(nombArch);
        int numLines = 0;
        try {
            BufferedReader buffer = new BufferedReader(fileReader);
            String blinea;

            int indPosicion = 0;
            while ((blinea = buffer.readLine()) != null) {
                int numMuestra=indPosicion-lineasEncabezado+1;// va indicando el numero de la linea para cada  muestra

                indPosicion = indPosicion + 1;
                String[] parts = blinea.split("\\s+");

                //System.out.println("numero linea "+ indPosicion+"archivo "+nombArch+" blinea: "+blinea);

                // MANEJO DEL ENCABEZADO **************************************
                if (indPosicion < lineasEncabezado) { //valor que delimita la lectura del encabezado
                   // System.out.println("contenido del encabezado, linea nro " + indPosicion + " contenido: " + blinea);//

                    for (int encb = 0; encb < parts.length; encb++) {
                       //  System.out.println("linea " + indPosicion + " nro palabra " + encb + " Encabezado: " + parts[encb]);
                        if (indPosicion==lineasEncabezadoArchivoMonofasico-1 && encb==2){
                            multiploUnidadPotencia= (parts[encb]); // guarda el multiploUnidad de potencia
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
                    if (parts.length>5){
                        archflagAnormalidad.add("A");}else{archflagAnormalidad.add(" ");}
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
                                String  strVoltTemp=(parts[m]);
                                strVoltTemp=strVoltTemp.replace(",",".");

                               // System.out.println(" campo variable de voltaje A="+strVoltTemp);
                                archVoltaje.add(Double.valueOf(strVoltTemp));

                                //archVoltaje.add(Double.valueOf(parts[m]));
                                break;
                            case 3:

                                String strTotEnergTemp=(parts[m]);
                                strTotEnergTemp=strTotEnergTemp.replace(",",".");
                               // System.out.println("Valor energia --> "+strTotEnergTemp);
                                archTotalEnergia = archTotalEnergia+(Double.parseDouble( strTotEnergTemp)/4);// se divide por 4 para una cadencia de 15
                                //archTotalEnergia = archTotalEnergia + Double.valueOf(parts[m]);

                                String strEnerg=(parts[m]);
                                strEnerg=strEnerg.replace(",",".");
                                Double tempPotencia= Double.valueOf(strEnerg);

                                archEnergia.add(tempPotencia/4); // se divide por 4 para una cadencia de 15
                                archPotencia.add(tempPotencia);// Potencia
                                //archEnergia.add(Double.valueOf(parts[m]));
                                break;
                            case 4:
                                String strFpTemp=(parts[m]);
                                strFpTemp=strFpTemp.replace(",",".");
                                archFP.add(Double.valueOf(strFpTemp));
                                //archFP.add(Double.valueOf(parts[m]));

                                break;
                            case 5:

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
        } catch (Exception ex) {

            System.out.println("Se encontró un error en el formato de las variables del archivo");
            JOptionPane.showMessageDialog(null, "Se encontró un error de formato archivo monofásico: "+nombArch );
            ex.printStackTrace();

            // Manejo de errores de excepcion por falla en formato



            System.out.println("error ruta archivo:  "+path+"\\error.txt");
            FileWriter fwErrorArchivoReport = new FileWriter(path+"\\ERROR.TXT");
            BufferedWriter bWErrorArchivoReport= new BufferedWriter(fwErrorArchivoReport);
            PrintWriter printErrorArchivoReport = new PrintWriter(bWErrorArchivoReport);

            Date currentDate = Calendar.getInstance(TimeZone.getDefault()).getTime();
            printErrorArchivoReport.write("RESUMEN DE ERRORES ENCONTRADOS    "+ "Fecha del reporte:  "+ String.valueOf(currentDate)+sbCrLf());
            printErrorArchivoReport.write("Retire el archivo trifásico"+nombArch +"  y vuelva a correr la aplicación...");

            bWErrorArchivoReport.close();
            fwErrorArchivoReport.close();
            JOptionPane.showMessageDialog(null, "Se encontró un error de formato archivo Trifásico: "+nombArch +"  " +path);
            System.exit(0);// Salida abrupta del programa
        }
        return ("true");
    }


    public String generarTokensTRIFASICO(String path, String nombArch, int numlineas) throws IOException, ParseException {

        int lineasEncabezado= lineasEncabezadoArchivoAMI; // LINEAS A CONSIDERAR COMO ENCABEZADO DEL ARCHIVO
                                                                // esta variable de inicializacion fue declarada al inicio de la clase Archivo
                                                                // declarar el valor al inicio de la clase

        //File input = new File(path);
        FileReader fileReader = new FileReader(nombArch);
        int numLines = 0;
        try {
            BufferedReader buffer = new BufferedReader(fileReader);
            String blinea;

            int indPosicion = 0;
            while ((blinea = buffer.readLine()) != null) {
                int numMuestra=indPosicion-lineasEncabezado+1;    // va indicando el numero de la linea para cada  muestra

                //System.out.println("line  en registro de prueba "+ blinea);
                indPosicion = indPosicion + 1;
                String[] parts = blinea.split("\\s+");

               // System.out.println("numero linea "+ indPosicion+"archivo "+nombArch+" blinea: "+blinea);

                // MANEJO DEL ENCABEZADO **************************************
                if (indPosicion < lineasEncabezado) {             //valor que delimita la lectura del encabezado
                    //System.out.println("contenido del encabezado, linea nro " + indPosicion + " contenido: " + blinea);//

                    for (int encb = 0; encb < parts.length; encb++) {
                        // System.out.println("linea " + indPosicion + " nro palabra " + encb + " Encabezado: " + parts[encb]);

                        if (indPosicion==13 && encb==4){
                            multiploUnidadPotencia= (parts[encb]); // extrae el múltiplo que tiene la unidad de potencia
                        }

                    }

                } else { // **** *** INICIO DEL PROCESAMIENTO DE LAS MUESTRAS  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡

                //    System.out.println( numMuestra+" Muestra   "+ (indPosicion-lineasEncabezado+1) +" valor: "+ parts[m]);
                    //Lazo para almacenar las muestras en variables para cada
                  // for (int m = 0; m < parts.length; m++) {
                  //       //System.out.println("m "+m+" muestra "+parts[m]);
                  // }
                    //*************************************************************************************************
                    if (parts.length>7){    // ---- Las siguientes dos lineas alimentan las lista con los flags
                        archflagAnormalidad.add("A");
                    }else {archflagAnormalidad.add(" ");}
                    //*************************************************************************************************


                    for (int m = 0; m < parts.length; m++) {
                        //System.out.println("numero de partes e q divisiona un arch trif "+parts.length);
                        switch (m) {
                            case 0:
                                if (parts[m]==""||parts[m]==null||parts[m]==" "){
                                    JOptionPane.showMessageDialog(null, "Se encontró un error de formato archivo Trifásico: "+nombArch +"  " +path);
                                }
                                archFecha.add(parts[m]);
                                break;
                            case 1:
                                if (parts[m]==""||parts[m]==null||parts[m]==" "){
                                    JOptionPane.showMessageDialog(null, "Se encontró un error de formato archivo Trifásico: "+nombArch +"  " +path);
                                }
                                archHora.add(parts[m]);
                                break;
                            case 2:

                                String  strVoltTemp=(parts[m]);
                                strVoltTemp=strVoltTemp.replace(",",".");
                                archVoltaje.add(Double.valueOf(strVoltTemp));

                                //archVoltaje.add(Double.valueOf(parts[m]));
                                break;
                            case 3:
                                String  strVoltBTemp=(parts[m]);
                                strVoltBTemp=strVoltBTemp.replace(",",".");
                                archVoltajeB.add(Double.valueOf(strVoltBTemp));


                                //archVoltajeB.add(Double.valueOf(parts[m]));
                                break;
                            case 4:
                                String  strVoltCTemp=(parts[m]);
                                strVoltCTemp=strVoltCTemp.replace(",",".");
                                archVoltajeC.add(Double.valueOf(strVoltCTemp));


                                //archVoltajeC.add(Double.valueOf(parts[m]));
                                break;
                            case 5:
                                String strTotEnergTemp=(parts[m]);
                                strTotEnergTemp=strTotEnergTemp.replace(",",".");
                                archTotalEnergia = archTotalEnergia+(Double.parseDouble( strTotEnergTemp)/4);


                                String strEnerg=(parts[m]);
                                strEnerg=strEnerg.replace(",",".");
                                Double tempPotencia= Double.valueOf(strEnerg);
                                archEnergia.add(tempPotencia/4);
                                archPotencia.add(tempPotencia);

                                //archTotalEnergia = archTotalEnergia + Double.valueOf(parts[m]);
                                //archEnergia.add(Double.valueOf(parts[m]));
                                break;
                            case 6:

                                String strFpTemp=(parts[m]);
                                strFpTemp=strFpTemp.replace(",",".");
                                archFP.add(Double.valueOf(strFpTemp));


                                //archFP.add(Double.valueOf(parts[m]));
                                break;
                            case 7:
                                archAnormalidad.add(parts[m]);
                                nroMuestraconAarchAnormalidad.add(numLines);//guarda indice de la muestra marcada

                                String s = parts[m];
                                if (parts[m].contentEquals("A")) {
                                    numMuestraAnormal = numMuestraAnormal + 1;
                                }
                                break;

                        }
                    }
                    numLines++;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();

            // Manejo de errores de excepcion por error en formato
            StringBuffer sbCrLf = new StringBuffer();
            sbCrLf.append((char)13);
            sbCrLf.append((char)10);
            String crlfTerminator = sbCrLf.toString();

            System.out.println("error ruta archivo:  "+path+"\\error.txt");
            FileWriter fwErrorArchivoReport = new FileWriter(path+"\\ERROR.TXT");
            BufferedWriter bWErrorArchivoReport= new BufferedWriter(fwErrorArchivoReport);
            PrintWriter printErrorArchivoReport = new PrintWriter(bWErrorArchivoReport);

            Date currentDate = Calendar.getInstance(TimeZone.getDefault()).getTime();
            printErrorArchivoReport.write("RESUMEN DE ERRORES ENCONTRADOS    "+ "Fecha del reporte:  "+ String.valueOf(currentDate)+sbCrLf);
            printErrorArchivoReport.write("Retire el archivo trifásico"+nombArch +"  y vuelva a correr la aplicación...");

            bWErrorArchivoReport.close();
            fwErrorArchivoReport.close();
            JOptionPane.showMessageDialog(null, "Se encontró un error de formato archivo Trifásico: "+nombArch +"  " +path);
            System.exit(0);// Salida abrupta del programa
        }

        return (tipoArchivo);
    }
    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

    // CASOS ESPECIALES: Lectura de archivos  especiales >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    public String generarTokensEspecialUno(String path, String nombArch, int numlineas) throws IOException, ParseException {

        int lineasEncabezado=10; // LINEAS A CONSIDERAR COMO ENCABEZADO DEL ARCHIVO
        int suicheVerifLineas=1;// 0 archivo lineas =10 "1" archivo con 14 lineas
        File input = new File(path);
        FileReader fileReader = new FileReader(nombArch);
        int numLines = 0;

            BufferedReader buffer = new BufferedReader(fileReader);
            String blinea;

            int indPosicion = 0;
            while ((blinea = buffer.readLine()) != null) {
                int numMuestra=indPosicion-lineasEncabezado+1;// va indicando el numero de la linea para cada  muestra

                indPosicion = indPosicion + 1;
                String[] parts = blinea.split("\\s+");

                // MANEJO DEL ENCABEZADO **************************************
                if (indPosicion < lineasEncabezado) { //valor que delimita la lectura del encabezado
                    //System.out.println("contenido del encabezado, linea nro " + indPosicion + " contenido (blanco?)" + blinea);//

                   // for (int encb = 0; encb < parts.length; encb++) {
                   //      //System.out.println(archnombre+"Encabezado linea " + indPosicion + " nro palabra " + encb + " Encabezado: " + parts[encb]);
                   // }

                    // verificar cuando llega a la linea 10 para discriminar el tipo de archivo

                     if (indPosicion==9 && parts[1].equals("Wh") ){
                   // if (indPosicion==9 && parts[1].equals("Wh")&& lineasEncabezado>9){ // si se cumple esta condicion de tener "Wh" en la linea 9 las muestras
                                                                    //empiezan en la linea 10 en caso contrario en la linea 14
                        //System.out.println("LINEA 9             "+parts[1]);
                         suicheVerifLineas=0;// suiche para cambiar el numero de cuentas de lineas
                         //System.out.println(nombArch+" ARCHIVO 10 LINEAS"+lineasEncabezado);
                         lineasEncabezado=10;
                    }else {

                    lineasEncabezado=14;  // recordar que se declaró al inicio dos variables "lineasEncabezadoArchivoMonofasico=10
                                          // "lineaEncabezadoTrifasico=14" a fin de hacer uniforme el archivo
                                          // este condicional podria eliminarse
                    }
                    //System.out.println("Lineas configuradas de encabezado="+lineasEncabezado);

                } else { // **** *** INICIO DEL PROCESAMIENTO DE LAS MUESTRAS
                    // System.out.println("linea leida"+blinea);
                    //System.out.println(indPosicion+" lineas y la cantidad de partes de la linea "+parts.length);
                    //System.out.println( numMuestra+" Muestra   "+ (indPosicion-lineasEncabezado+1) +" valor: "+ parts[m]);
                    //Lazo para almacenar las muestras en variables para cada

                  // for (int encb = 0; encb < parts.length; encb++) {
                  //     //System.out.println("Muestras linea " + indPosicion + " nro palabra " + encb + " Encabezado: " + parts[encb]);
                  // }

                    //***************************************************************************************************
                    // _________________ las siguientes dos lineas crea una lista con el flag de registros marcados

                    if (parts.length>3){
                        archflagAnormalidad.add("A");}else{archflagAnormalidad.add(" ");
                        //System.out.println("incluido como anomalo ");
                    }
                    //*************************************************************************************************

                    for (int m = 0; m < parts.length; m++) {
                        switch (m) {
                            case 0:
                                archFecha.add(parts[m]);
                                System.out.println(parts[m]);
                                break;
                            case 1:
                                archHora.add(parts[m]);
                                System.out.println(parts[m]);
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
        return ("String");// se esperaba regresar el string procesado ...
    }
    //  <<<<<<<<<<<<<<<<<<<<            FIn TRATAMIENTO CASOS ESPECIALES         <<<<<<<<<<<<<<<<<<<<<
    //________________________________________________________________________________________________________________

    public List<String> xlistarDirectorio(String path,int casoEspecialActivado, int maximalongitudNombredeArchivo) throws IOException {
        System.out.println("crea lista de archivos desde directorio de trabajo");
        File micarpeta = new File(path);
        File[] listaFicheros = micarpeta.listFiles();
        List<String> xlistaArchivo = new ArrayList<>();
        int nDirectorio = 0;
        int nArchivos = 0;
        String auxRegPath;

        //---------nn

        //************ barra de indicacion de avance en el proceso de armado de lista de archivos
        // CONFIGURA lA BARRA DE PROGRESO
        //----------------------------------------
        // Inicio del despliegue de la barra
        JFrame frame=new JFrame("Armando lista de archivos procesables ");

        JProgressBar jProgressBar=new JProgressBar();
        jProgressBar.setStringPainted(true);
        jProgressBar.setValue(0); // inicializa el valor de avance en la barra

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(1,10));
        frame.setSize(720,160);              // Coloca el tamaño de la barra de progreso
        ImageIcon logoBaes=new ImageIcon("img\\logo_pie.jpg");
        frame.setIconImage(logoBaes.getImage().getScaledInstance(100,80,Image.SCALE_SMOOTH));

        frame.setLocationRelativeTo(null);
        frame.add(jProgressBar,0,0);
        jProgressBar.setValue(3);
        frame.setVisible(true);
        //-------------nn

        try {

            for (int i = 0; i < listaFicheros.length; i++) {
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

                    // llama al método contar lineas, Asignando en consecuencia el tipo de archivo "F","M","T","E"
                    //  if ((extArchivo.equals(".txt") || extArchivo.equals(".TXT")||extArchivo.equals(".DAT") || extArchivo.equals(".dat"))) {
                    contarLineas(xpath, xpath, casoEspecialActivado);//-----------------------------------------------------------------------------Cuenta lineas y clasifica el archivo
                    //System.out.println(" el tipo es xxxxxx antes de procesar "+tipoArchivo);
                    //}

                    // Suma a la lista solo aquellos archivos que cumplen las condiciones  de extension y valor indicadas
                    //System.out.println("*******    palabra 1 L1= " + getPrimeraPalabraValidaArchivo() + "  palabra referencia formato= " + tempPrimeraPalabraLeidaArchivo);


                    if (getPrimeraPalabraValidaArchivo().equals(tempPrimeraPalabraLeidaArchivo)) {
                        // System.out.println(">>>>>>>>  palabra 1 L1= " + getPrimeraPalabraValidaArchivo() + "palabra referenciaformato= " + tempPrimeraPalabraLeidaArchivo);
                    }
                    System.out.println("TipoArchivo= " + tipoArchivo + sbCrLf() + "PalabradeReferencia = " + getPrimeraPalabraValidaArchivo()
                            + sbCrLf() +
                            "ValorLeidoReferencia= " + tempPrimeraPalabraLeidaArchivo + sbCrLf() +
                            "Procesando tipo de formato= " + tipodeFormatoaLeer + "  | NombreArchivo= " + nombArch);

                    // -------- CONDICION ESPECIAL PARA CONFIRMAR 1era PALABRA LEIDA EN  FORMATO --------------------------------------------------------
                    System.out.println("Palabra Válida - Palabra leida" + getPrimeraPalabraValidaArchivo() +" = "+ tipodeFormatoaLeer);
                    if (tipodeFormatoaLeer == "FORMATO_07") {
                        tempPrimeraPalabraLeidaArchivo = "nombreArchivo";
                    }
                    //-------------------------------------------------------------------------------
                    //Calcula la cantidad de caracteres que tiene el nombre del archivo

                    //------->>>>- activacion de suiche para incluir en la lista archivos con la longitud de digitos  convenida
                    int tempLongitudNombreArchivo = extraeNombreArchivoBaseSinrutaNiextension(listaFicheros[i].toString()).length();
                    int suicheLongituddigitosArchivo = 1;// suiche que permite sumar a xlistaArchivos

                   // if (tipodeFormatoaLeer.equals("FORMATO_05") || tipodeFormatoaLeer.equals("FORMATO_04")) {
                   //     if (maximalongitudNombredeArchivo != tempLongitudNombreArchivo) {
                   //         suicheLongituddigitosArchivo = 0; // indica que no debe agregarse el archivo a la lista
                   //     }
                   // }

                    if (tipodeFormatoaLeer.equals("FORMATO_04")) {
                             if (maximalongitudNombredeArchivo != tempLongitudNombreArchivo) {
                                 suicheLongituddigitosArchivo = 0; // indica que no debe agregarse el archivo a la lista
                             }
                         }

                    if (tipodeFormatoaLeer.equals("FORMATO_05")) {
                        if (tempLongitudNombreArchivo<maximalongitudNombredeArchivo) {
                            suicheLongituddigitosArchivo = 0; // indica que no debe agregarse el archivo a la lista
                        }
                    }

                    if (tipodeFormatoaLeer.equals("FORMATO_08")) {
                        if (maximalongitudNombredeArchivo <= tempLongitudNombreArchivo) {
                            suicheLongituddigitosArchivo = 0; // indica que no debe agregarse el archivo a la lista
                        }
                    }


                    //----->>> Ajuste
                  //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


                    //-----------------------------
                    System.out.println("xvaluacion:" + extraeNombreArchivoBaseSinrutaNiextension(listaFicheros[i].toString()) +
                            " |longNamArch " + tempLongitudNombreArchivo + " | max " + maximalongitudNombredeArchivo);

                    if ((extArchivo.equals(".txt") || extArchivo.equals(".TXT") || extArchivo.equals(".DAT") || extArchivo.equals(".dat"))
                            && (flagArchPlanillaDecartable == 0) && (getPrimeraPalabraValidaArchivo().equals(tempPrimeraPalabraLeidaArchivo))
                            && (suicheLongituddigitosArchivo == 1) // esta condicion obliga a cumplir conla longitud de digitos en nombArchivo
                            && (tipoArchivo == "M" || tipoArchivo == "T" || tipoArchivo == "E" || tipoArchivo == "F")) {

                        // System.out.println(">> Tipo DE ARCHIVO  "+tipoArchivo+" Archivo valido " + listaFicheros[i]+" tipo toroide"+tipoToroide);
                        // System.out.println(strTipoToroide+" toroide "+tipoToroide+ i+" lista ficheros "+listaFicheros[i]);// lista ficheros configurados a procesar
                        // chequeo para excluir de la lista los archivos de reporte

                        String excluir_Nomb_reporte = xcadena.substring(xcadena.length() - 4);

                        // >>> SUMA UN NUEVO ARCHIVO ALA LISTA DE ARCHIVOS VALIDOS       >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<

                        xlistaArchivo.add(listaFicheros[i].toString());//agrega un nuevo archivo a la lista

                        // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
                        //-----------
                        if (jProgressBar.getValue() == 100) {// chequea la cuenta del progress bar para reinicializar si alcanza 100%
                            jProgressBar.setValue(0);
                        } else {
                            jProgressBar.setValue(jProgressBar.getValue() + 1);// se actualiza la barra
                            jProgressBar.setString("Archivos identificados " + xlistaArchivo.size());//con el número de archivos proce
                        }

                        //-------------
                    } else {
                        System.out.println("El siguiente archivo no es procesable: ---->" + listaFicheros[i].toString());
                        listaErroresArch.add("El siguiente archivo no es procesable: ---->" + listaFicheros[i].toString() + sbCrLf());
                    }

                    //********************************************************************************
                    // System.out.println("Archivo detalle del nombre "+ listaFicheros[i].getName());
                }

            }//rx
                    //----------------
                    System.out.println("Numero total de archivos en el directorio: " +
                    nArchivos + "  Lista de archivos validos a procesar: " + xlistaArchivo.size()+" | Formto= "+tipodeFormatoaLeer);


        } catch (EOFException ex) { // manejo de errores en caso de no poder leer los archivos
                System.out.println("Excepcion leyendo archivos del directorio- Antes de proseguir Cierre " +
                    "todos los archivos abiertos en el directorio de analisis ");
                listaErroresArch.add("Excepcion leyendo archivos del directorio- Antes de proseguir Cierre " +
                        "todos los archivos abiertos en el directorio de analisis "+sbCrLf());
        }
        // OCULTA  la barra de progreso de la lista de archivos
        jProgressBar.setVisible(false);
        frame.dispose();// destruye el frame y barra de progreso
        if (xlistaArchivo.size()==0){
            JOptionPane.showMessageDialog(null, "Revise el directorio. No existen archivos válidos que procesar!!!" );

        }
        //------------

        return (xlistaArchivo);// retorno del metodo armar lista de archivos procesables
    }



    public void incializaValoresClaseArchivo(){

        List listaPrueba=new ArrayList();

        this.archtamaño=0;
        //this.archnombre
        this.Anumlineas=0;
        int longArreglos=archFecha.size();

        archFecha.clear();
        archHora.clear();
        archVoltaje.clear();
        archCorriente.clear();
        archEnergia.clear();
        archEnergiaReactiva.clear();
        archPotencia.clear();
        archPotenciaReactiva.clear();
        archFP.clear();
        archAnormalidad.clear();
        archflagAnormalidad.clear();

        archTotalEnergiaReactiva=0.00;
        archTotalEnergia=0.00;
        numMuestraAnormal=0;




    }

    public long calcularDiasDate (Date fechaFinal, Date fechaInicial) throws ParseException {

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String temporal;

        Date fechaInicialMuestras=dateFormat.parse(this.archFecha.get(1));
        int indiceTemp=this.archFecha.size()-1;
        temporal= this.archFecha.get((indiceTemp));
        Date fechaFinalMuestras=dateFormat.parse(temporal);
        long nroDias=((fechaFinalMuestras.getTime()-fechaInicialMuestras.getTime())/ 86400000);
        return (nroDias);
    }

    //--------------------------------------------------------------------------------------------
    // Formato de entrada DD/MM/AAAA
    public long calcularDiasString (String fechaFinal, String fechaInicial) throws ParseException {

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String temporal;
        Date fechaInicialMuestras= new Date();// Asigna tipo e Inicializa la variable fechaInicialMuestras

        try {
            fechaInicialMuestras=dateFormat.parse(fechaInicial);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Se encontró un error de formato en la 'fecha' corrija el archivo : "+nombArchivoParaError );

            System.exit(0);
            e.printStackTrace();

        }


        long nroDias;
        int indiceTemp=this.archFecha.size()-1;
       // temporal= this.archFecha.get((indiceTemp));
        temporal=fechaFinal;
        Date fechaFinalMuestras=dateFormat.parse(temporal);
        if (fechaFinalMuestras != fechaInicialMuestras) {
            nroDias = ((fechaFinalMuestras.getTime() - fechaInicialMuestras.getTime()) / 86400000);
        }else{nroDias=0;}
        return (nroDias);
    }
    //----------------------------------------------------------------


    //---------------------------------------------------------------
    public String calificarMedicionM (String nombrearchivo,
                                     List inicialNombOperadora,    int diasdePermanencia,    int diasMinValidez,
                                      List digitoValidoAno, List BitHabilitacionChequeo,int cadenciaMuestra, List cEstratosSetup,List cNombTarifaSetup) throws ParseException {



        nombArchivoParaError=nombrearchivo; // actualiza valor de la variable con el archivo que se está procesando
        //System.out.println("PARAMETROS DE ENTRADA RUTINA de CALIFICACION: NombreOperadora "+nombrearchivo+" |InicialNombOperadora"+inicialNombOperadora+sbCrLf()+
         //       " |diasminPermanencia "+diasdePermanencia+" |diasminValidez "+diasMinValidez+" |listaDigAño "+digitoValidoAno+
           //     "  | BitHabilitacionChequeo "+BitHabilitacionChequeo+ "  | cadenciamuestra "+cadenciaMuestra+sbCrLf());

       // for (int i=0;i<archFecha.size();i++) {
       //     //System.out.println("PosicionVector " + i + "vectores fecha y hora " + archFecha.get(i) + " | " + archHora.get(i)+ "  |potencia "+archPotencia.get(i));
       // }
        System.out.println("INICIO DE RUTINA CALIFICAR: arhEnergia="+archEnergia.size()+ " | archivoPotencia " +archPotencia.size()+ " | archAnormalidad="+archAnormalidad.size()+"  | "+ " | Date "+
                archFecha.size()+"  | Hora "+archHora.size());

        String resultado=""; // Variable que concatena las pruebas ABCDEFGH
        char charBitdeHabilitacion;
        String auxVarBitHabilitacion;
        for (int i=0;i<BitHabilitacionChequeo.size();i++) {
            //System.out.println("Valor del Byte de habilitacion "+BitHabilitacionChequeo);
            auxVarBitHabilitacion= (String) BitHabilitacionChequeo.get(i);
            charBitdeHabilitacion=auxVarBitHabilitacion.charAt(0);
            switch (i) {  // Derivacion de los casos que se habilitaran según el archivo de configuración
                case 0:
                    if (charBitdeHabilitacion=='1' ){resultado = resultado + cumplimientoPruebaA(diasdePermanencia); //System.out.println("Habilitada prueba A");
                    }else
                    {
                        // System.out.println("Des habilitada prueba A");
                    }
                    break;
                case 1:
                    if (charBitdeHabilitacion=='1'){resultado = resultado + cumplimientoPruebaB(diasMinValidez);//System.out.println("Habilitada prueba B");
                        //System.out.println("Habilitada prueba b con "+ " dias de validez="+diasMinValidez);
                    }else
                    {
                        // System.out.println("Des habilitada prueba B ");
                    }
                    break;
                case 2:
                    if (charBitdeHabilitacion=='1'){resultado = resultado + cumplimientoPruebaC(cadenciaMuestra);//System.out.println("Habilitada prueba C");
                    }else
                    {
                        // System.out.println("Des habilitada prueba C");
                    }
                    break;
                case 3:
                    if (charBitdeHabilitacion=='1'){ resultado = resultado + cumplimientoPruebaD();//System.out.println("Habilitada prueba D");
                    }else
                    {
                        // System.out.println("Des habilitada prueba D");
                    }
                    break;
                case 4:
                    if (charBitdeHabilitacion=='1'){resultado = resultado + cumplimientoPruebaE();//System.out.println("Habilitada prueba E")
                        ;}else
                    {
                      //  System.out.println("Des habilitada prueba E");
                    }
                    break;
                case 5:
                    if (charBitdeHabilitacion=='1'){ resultado = resultado + cumplimientoPruebaF();//System.out.println("Habilitada prueba F");
                    }else
                    {
                        //System.out.println("Des habilitada prueba F");
                    }
                    break;
                case 6:
                    if (charBitdeHabilitacion=='1'){resultado = resultado + cumplimientoPruebaG(nombrearchivo, inicialNombOperadora, digitoValidoAno,cEstratosSetup,cNombTarifaSetup);
                        //System.out.println("Habilitada prueba G");
                    }else
                    {
                        // System.out.println("Des habilitada prueba G");
                    }
                    break;
                case 7:
                    if (charBitdeHabilitacion=='1'){resultado = resultado + cumplimientoPruebaH();//System.out.println("Habilitada prueba H");
                    }else
                    {
                        //System.out.println("Des habilitada prueba H");
                    }

                    break;
            }
        }
        System.out.println("fin rutina de calificacion-->resultado= "+resultado);
        return (resultado);
    }// Fin cumplimientoM
     //--------------------------------------------------------------------
     //---------------------------------------------------------------
     public String calificarMedicionM_Formato04_AES_PD(String nombrearchivo,
                                       List inicialNombOperadora,    int diasdePermanencia,    int diasMinValidez,
                                       List digitoValidoAno, List BitHabilitacionChequeo,int cadenciaMuestra, List cEstratosSetup,List cNombTarifaSetup) throws ParseException {



         nombArchivoParaError=nombrearchivo; // actualiza valor de la variable con el archivo que se está procesando
         //System.out.println("PARAMETROS DE ENTRADA RUTINA de CALIFICACION: NombreOperadora "+nombrearchivo+" |InicialNombOperadora"+inicialNombOperadora+sbCrLf()+
         //       " |diasminPermanencia "+diasdePermanencia+" |diasminValidez "+diasMinValidez+" |listaDigAño "+digitoValidoAno+
         //     "  | BitHabilitacionChequeo "+BitHabilitacionChequeo+ "  | cadenciamuestra "+cadenciaMuestra+sbCrLf());

         // for (int i=0;i<archFecha.size();i++) {
         //     //System.out.println("PosicionVector " + i + "vectores fecha y hora " + archFecha.get(i) + " | " + archHora.get(i)+ "  |potencia "+archPotencia.get(i));
         // }
         System.out.println("INICIO DE RUTINA CALIFICAR: arhEnergia="+archEnergia.size()+ " | archivoPotencia " +archPotencia.size()+ " | archAnormalidad="+archAnormalidad.size()+"  | "+ " | Date "+
                 archFecha.size()+"  | Hora "+archHora.size());

         String resultado=""; // Variable que concatena las pruebas ABCDEFGH
         char charBitdeHabilitacion;
         String auxVarBitHabilitacion;
         for (int i=0;i<BitHabilitacionChequeo.size();i++) {
             //System.out.println("Valor del Byte de habilitacion "+BitHabilitacionChequeo);
             auxVarBitHabilitacion= (String) BitHabilitacionChequeo.get(i);
             charBitdeHabilitacion=auxVarBitHabilitacion.charAt(0);
             switch (i) {  // Derivacion de los casos que se habilitaran según el archivo de configuración
                 case 0:
                     if (charBitdeHabilitacion=='1' ){resultado = resultado + cumplimientoPruebaA_formato0405(diasdePermanencia); //System.out.println("Habilitada prueba A");
                     }else
                     {
                         // System.out.println("Des habilitada prueba A");
                     }
                     break;
                 case 1:
                     if (charBitdeHabilitacion=='1'){resultado = resultado + cumplimientoPruebaB_formato0405(diasMinValidez);//System.out.println("Habilitada prueba B");
                         //System.out.println("Habilitada prueba b con "+ " dias de validez="+diasMinValidez);
                     }else
                     {
                         // System.out.println("Des habilitada prueba B ");
                     }
                     break;
                 case 2:
                     if (charBitdeHabilitacion=='1'){resultado = resultado + cumplimientoPruebaC(cadenciaMuestra);//System.out.println("Habilitada prueba C");
                     }else
                     {
                         // System.out.println("Des habilitada prueba C");
                     }
                     break;
                 case 3:
                     if (charBitdeHabilitacion=='1'){ resultado = resultado + cumplimientoPruebaD_Formato_0405();//System.out.println("Habilitada prueba D");
                     }else
                     {
                         // System.out.println("Des habilitada prueba D");
                     }
                     break;
                 case 4:
                     if (charBitdeHabilitacion=='1'){resultado = resultado + cumplimientoPruebaE();//System.out.println("Habilitada prueba E")
                         ;}else
                     {
                         System.out.println("Des habilitada prueba E");
                     }
                     break;
                 case 5:
                     if (charBitdeHabilitacion=='1'){ resultado = resultado + cumplimientoPruebaF();//System.out.println("Habilitada prueba F");
                     }else
                     {
                         //System.out.println("Des habilitada prueba F");
                     }
                     break;
                 case 6:
                     if (charBitdeHabilitacion=='1'){resultado = resultado + cumplimientoPruebaG(nombrearchivo, inicialNombOperadora, digitoValidoAno,cEstratosSetup,cNombTarifaSetup);
                         //System.out.println("Habilitada prueba G");
                     }else
                     {
                         // System.out.println("Des habilitada prueba G");
                     }
                     break;
                 case 7:
                     if (charBitdeHabilitacion=='1'){resultado = resultado + cumplimientoPruebaH();//System.out.println("Habilitada prueba H");
                     }else
                     {
                         //System.out.println("Des habilitada prueba H");
                     }
                     break;
                 case 8:
                     if (charBitdeHabilitacion=='1'){resultado = resultado + cumplimientoPruebaI();//System.out.println("Habilitada prueba H");
                     }else
                     {
                         //System.out.println("Des habilitada prueba I");
                     }
                     break;
             } // Fin del case
         }
         System.out.println("fin rutina de calificacion-->resultado= "+resultado);
         return (resultado);
     }// Fin cumplimientoM



    //----------------------------------------------------------------------
    // INICIO CumplimientoFormato05_AES_MG  usuarios Medianos y grandes __________________________________________________________________
    // Cambia el CONTROL "G" por el armado del nombre de archivo
    public String calificarMedicion_Formato05_AES_MG (String nombrearchivo,
                                      List inicialNombOperadora,
                                      int diasdePermanencia,
                                      int diasMinValidez,
                                      List digitoValidoAno, List BitHabilitacionChequeo,int cadenciaMuestra,List cEstratosSetup,List cNombTarifaSetup) throws ParseException {



        nombArchivoParaError=nombrearchivo; // actualiza valor de la variable con el archivo que se está procesando
        System.out.println("PARAMETROS DE ENTRADA RUTINA de CALIFICACION: NombreOperadora "+nombrearchivo+" |InicialNombOperadora"+inicialNombOperadora+sbCrLf()+
                " |diasminPermanencia "+diasdePermanencia+" |diasminValidez "+diasMinValidez+" |listaDigAño "+digitoValidoAno+
                "  | BitHabilitacionChequeo "+BitHabilitacionChequeo+ "  | cadenciamuestra "+cadenciaMuestra+sbCrLf());

       // for (int i=0;i<archFecha.size();i++) {
       //     //System.out.println("PosicionVector " + i + "vectores fecha y hora " + archFecha.get(i) + " | " + archHora.get(i)+ "  |potencia "+archPotencia.get(i));
       // }
        System.out.println("INICIO DE RUTINA CALIFICAR: arhEnergia="+archEnergia.size()+ " | archivoPotencia " +archPotencia.size()+ " | archAnormalidad="+archAnormalidad.size()+"  | "+ " | Date "+
                archFecha.size()+"  | Hora "+archHora.size());

        String resultado=""; // Variable que concatena las pruebas ABCDEFGH
        char charBitdeHabilitacion;
        String auxVarBitHabilitacion;
        for (int i=0;i<BitHabilitacionChequeo.size();i++) {
            //System.out.println("Valor del Byte de habilitacion "+BitHabilitacionChequeo);
            auxVarBitHabilitacion= (String) BitHabilitacionChequeo.get(i);
            charBitdeHabilitacion=auxVarBitHabilitacion.charAt(0);
            switch (i) {  // Derivacion de los casos que se habilitaran según el archivo de configuración
                case 0:
                    if (charBitdeHabilitacion=='1' ){resultado = resultado + cumplimientoPruebaA_formato0405(diasdePermanencia); //System.out.println("Habilitada prueba A");
                    }else
                    {
                        // System.out.println("Des habilitada prueba A");
                    }
                    break;
                case 1:
                    if (charBitdeHabilitacion=='1'){resultado = resultado + cumplimientoPruebaB_formato0405(diasMinValidez);//System.out.println("Habilitada prueba B");
                        //System.out.println("Habilitada prueba b con "+ " dias de validez="+diasMinValidez);
                    }else
                    {
                        // System.out.println("Des habilitada prueba B ");
                    }
                    break;
                case 2:
                    if (charBitdeHabilitacion=='1'){resultado = resultado + cumplimientoPruebaC(cadenciaMuestra);//System.out.println("Habilitada prueba C");
                    }else
                    {
                        // System.out.println("Des habilitada prueba C");
                    }
                    break;
                case 3:
                    if (charBitdeHabilitacion=='1'){ resultado = resultado + cumplimientoPruebaD_Formato_0405();//System.out.println("Habilitada prueba D");
                    }else
                    {
                        // System.out.println("Des habilitada prueba D");
                    }
                    break;
                case 4:
                    if (charBitdeHabilitacion=='1'){resultado = resultado + cumplimientoPruebaE();//System.out.println("Habilitada prueba E")
                        ;}else
                    {
                        System.out.println("Des habilitada prueba E");
                    }
                    break;
                case 5:
                    if (charBitdeHabilitacion=='1'){ resultado = resultado + cumplimientoPruebaF();//System.out.println("Habilitada prueba F");
                    }else
                    {
                        //System.out.println("Des habilitada prueba F");
                    }
                    break;
                case 6:
                    if (charBitdeHabilitacion=='1'){resultado = resultado + cumplimientoPruebaG_Formato05(nombrearchivo, inicialNombOperadora, digitoValidoAno,cEstratosSetup,cNombTarifaSetup);
                        //System.out.println("Habilitada prueba G");
                    }else
                    {
                        // System.out.println("Des habilitada prueba G");
                    }
                    break;
                case 7:
                    if (charBitdeHabilitacion=='1'){resultado = resultado + cumplimientoPruebaH();//System.out.println("Habilitada prueba H");
                    }else
                    {
                        //System.out.println("Des habilitada prueba H");
                    }

                    break;
                case 8:
                    if (charBitdeHabilitacion=='1'){resultado = resultado + cumplimientoPruebaI();//System.out.println("Habilitada prueba H");
                    }else
                    {
                        //System.out.println("Des habilitada prueba I");
                    }
                    break;
            }// fin del case
        }
        //-------------------------------

        //------------------------------b
        System.out.println("fin rutina de calificacion-->resultado= "+resultado);
        return (resultado);
    }// Fin cumplimientoM



    //************************************


    //METODOS DE PRUEBAS DE CUMPLIMIENTO PARA GENERAR LOS CODIGOS RESULTADO
    //---------------------------------------------------------- formato DD//MM/AAAA
    private String cumplimientoPruebaA(int diasdePermanencia) throws ParseException {// Prueba que revisa el cumplimiento

        String resultado="";
        long dias;
        if (archFecha.size()>1) {
            dias = calcularDiasString(archFecha.get((archFecha.size() - 1)),
                    (archFecha.get(1)));
        }else{ dias=0;}

        if(dias<diasdePermanencia ){
            resultado="A";
        }else{
            resultado="";
        }

        return(resultado);
    }
    //------------------------------------variante que requiere conversion  a----- formato MM/DD/AAAA
    private String cumplimientoPruebaA_formato0405(int diasdePermanencia) throws ParseException {// Prueba que revisa el cumplimiento
        String resultado="";
        long dias;
        //-------------------------------------------------------------------
        // INCORPORACION DEL CONTROL DE CRONOLOGIA EN EN SIGUIENTE SEGEMENTO
        //-------------//llamada a metodo dentro del condicional IF, para validar secuencia cronológica del bloque
        //boolean cronologiaCorrecta=CtrlCronologia(archFecha,archHora);//llamada al metodo de cheqo cronologico
       String ResultadoChequeoCrono="";//CtrlCronologia(archFecha,archHora);//llamada al metodo de cheqo cronologico
   //  if (cronologiaCorrecta){// archFecha tiene formato dd/MM/AAAA
        if (ResultadoChequeoCrono==""){// archFecha tiene formato dd/MM/AAAA

        }else{
             resultado=resultado+"ErrCrono_L"+ResultadoChequeoCrono+ " "; // asigna resultado de la prueba cronologica
        }
        //*******************************************
        String fechaInicial=convertirDateformatoddMMAAAaMMddAAAA(archFecha.get(0).toString());
        System.out.println("Conversion fecha 1 listo");
        String fechaFinal=convertirDateformatoddMMAAAaMMddAAAA(archFecha.get((archFecha.size() - 1)).toString());
        System.out.println("Conversion fecha 2 listo");

        if (archFecha.size()>1) {// fechaFinal - FechaInicial
            dias = calcularDiasString(fechaFinal,fechaInicial);
        }else{ dias=0;}

        if(dias<diasdePermanencia ){
            resultado=resultado+"A";
        }else{
            resultado=resultado+"";
        }

        //------------------------------------------------------------------------

        return(resultado);// fin del método control de calificación A
    }
//------------------------------------------------------------------------------------------------
    private String cumplimientoPruebaB(int diasValidosdeRegistro) throws ParseException {
        // Identifica la cantidad de dias con muestras sin desviación "A" o marca  anómalo "A"
        String resultado="";
        String pruebaBfechaInicio=archFecha.get(0);// inicializa el valor en el primer registro
        String pruebaBfechaFinal=archFecha.get(0);
        long dias=0;
        int flagFechaInicial=0;// flag para registrar marcado de fecha de inicio

      for (int i=0;i<archFecha.size();i++){ // Barre toda la lista

         //System.out.println( i+" fecha contenido"+ archFecha.get(i)+" Flag A= "+ archflagAnormalidad.get(i)+ " registro fecha inicial "+ pruebaBfechaInicio);


          if (archflagAnormalidad.get(i).equals("A")){ // caso en que descarta las muestras marcadas
              //System.out.println("Muestra marcada = "+archflagAnormalidad.get(i));

          }else{ // REGISTROS VALIDOS NO MARCADOS ----------------------------
              //System.out.println("Contenido de Anormalidad = "+archflagAnormalidad.get(i)+" Prueba B----- registro sin marca "+archFecha.get(i));

              if(flagFechaInicial==0){    // Inicializa pruebaFechaInicial con una fecha de registro sin marca
                  pruebaBfechaInicio=archFecha.get(i);
                 // System.out.println("fecha final deslizante  "+ pruebaBfechaInicio);
                  pruebaBfechaFinal=pruebaBfechaInicio; // Iguala las fechas de registro inicial = final
                  flagFechaInicial=1;     // evita que se cambie la fecha de inicio durante el for
              }

              pruebaBfechaFinal=archFecha.get(i);   // va cambiando de contenido hasta el ultmo valor vaalido de la lista.

              //System.out.println("fecha final deslizante fuera del lazo"+ pruebaBfechaFinal);
          }
}
        //System.out.println(" fecha inicio = "+pruebaBfechaInicio+ "  Fecha final ="+pruebaBfechaFinal);
        dias=calcularDiasString(pruebaBfechaFinal,pruebaBfechaInicio);
        //System.out.println("dias calculados"+ dias+ "dias de reg>>>>> fecha inicial "+pruebaBfechaInicio+" fecha final "+pruebaBfechaFinal);
        if(dias<diasValidosdeRegistro){       // >>> CALIFICA CON LA LETRA B DE ACUERDO A LOS DIAS MINIMOS DE REGISTRO *****************
            resultado="B";
        }else{
            resultado="";
        }

        return(resultado);
    }
    //------------------------------------------------------------------------------------------------------
    private String cumplimientoPruebaB_formato0405(int diasValidosdeRegistro) throws ParseException {

        //conversion de formato de fecha MM/DD/YYYY a DD/MM/YYYY
        String fechaInicial=convertirDateformatoddMMAAAaMMddAAAA(archFecha.get(0).toString());
       // String fechaFinal=convertirDateformatoddMMAAAaMMddAAAA(archFecha.get((archFecha.size() - 1)).toString());

        // Identifica la cantidad de dias con muestras sin desviación "A" o marca  anómalo "A"
        String resultado="";
        String pruebaBfechaInicio=fechaInicial;// inicializa el valor en el primer registro
        String pruebaBfechaFinal=fechaInicial;
        long dias=0;
        int flagFechaInicial=0;// flag para registrar marcado de fecha de inicio

        for (int i=0;i<archFecha.size();i++){ // Barre toda la lista

            //System.out.println( i+" fecha contenido"+ archFecha.get(i)+" Flag A= "+ archflagAnormalidad.get(i)+ " registro fecha inicial "+ pruebaBfechaInicio);
            if (archflagAnormalidad.get(i).equals("A")){ // caso en que descarta las muestras marcadas
                //System.out.println("Muestra marcada = "+archflagAnormalidad.get(i));

            }else{ // REGISTROS VALIDOS NO MARCADOS ----------------------------
                //System.out.println("Contenido de Anormalidad = "+archflagAnormalidad.get(i)+" Prueba B----- registro sin marca "+archFecha.get(i));

                if(flagFechaInicial==0){    // Inicializa pruebaFechaInicial con una fecha de registro sin marca
                    //convierte el formato de las fechas
                   // pruebaBfechaInicio=pruebaBfechaInicio;// carga la nueva fecha
                    // System.out.println("fecha final deslizante  "+ pruebaBfechaInicio);

                    pruebaBfechaFinal=convertirDateformatoddMMAAAaMMddAAAA(archFecha.get(i)); // Iguala las fechas de registro inicial = final
                    flagFechaInicial=1;     // evita que se cambie la fecha de inicio durante el for
                }
                    //convierte el formato de las fechas
                pruebaBfechaFinal=convertirDateformatoddMMAAAaMMddAAAA(archFecha.get(i));   // va cambiando de contenido hasta el ultmo valor vaalido de la lista.

                //System.out.println("fecha final deslizante fuera del lazo"+ pruebaBfechaFinal);
            }
        }
        System.out.println(" fecha inicio = "+pruebaBfechaInicio+ "  Fecha final ="+pruebaBfechaFinal);
        dias=calcularDiasString(pruebaBfechaFinal,pruebaBfechaInicio);
        //System.out.println("dias calculados"+ dias+ "dias de reg>>>>> fecha inicial "+pruebaBfechaInicio+" fecha final "+pruebaBfechaFinal);
        if(dias<diasValidosdeRegistro){       // >>> CALIFICA CON LA LETRA B DE ACUERDO A LOS DIAS MINIMOS DE REGISTRO *****************
            resultado="B";
        }else{
            resultado="";
        }

        return(resultado);
    }
    //________________________________________________________________________________________________________
    // // OCURRENCIA COINCIDENCIA DE LA CADENCIA DE 15 MINUTOS EN MAS DE 90% DE LAS MUESTRAS
    // valua formato de cadencia cada 15 minutos
    //____________________________________________________________________________________________________
    private String cumplimientoPruebaC(int cadenciaMuestra) throws ParseException { // CADENCIA,   se indica abajo el valor nominal abajo y este valor se verifica
                                                                // restando las horas de registro sucesivos ( "n" y "n+1" ) si supera  10%
                                                                // de valoress distintos se activa el calificador " C" dentro de la estructura de valores que se
                                                                // publican en el reporte
        System.out.println("Cadencia seteada en ConfigMed= "+cadenciaMuestra);
        long cadenciaNominal=cadenciaMuestra;// valor inicial de comparacion para chequear cadencia
                                             // Valor tomado de ConfigMed.TXT
        String resultado="";
       // System.out.println(" calificacion C");
        long dias;
//*****************************************************************************************************************
         double contCoincidencia=0;
         double contNoCoincidencia=0;
        for (int i=0;i<archHora.size()-1;i++){
            //__________________________________________________________________________________
            long resultCadencia=0;
             resultCadencia=calculocadenciaPruebaC(15, archHora.get(i), archHora.get(i+1));
             // System.out.println( "La cadencia es "+resultCadencia+" muestra m "+archHora.get(i)+" muestra m "+archHora.get(i+1));
    //****   // *********************************************************************************
             //System.out.println(resultCadencia+" cadencia real vs cadencia nominal "+cadenciaNominal);
            if (((resultCadencia==cadenciaNominal) && (archflagAnormalidad.get(i).equals(" "))||((resultCadencia==cadenciaNominal) && (archflagAnormalidad.get(i).equals(""))))){
           // if ((resultCadencia==cadenciaNominal) ){
                //&& (archflagAnormalidad.get(i).equals(" ")){
                //System.out.println("suma valor resultadoCoincidencia "+resultCadencia+"  coincidencia + flagAnormalidad="+archflagAnormalidad.get(i));

                    contCoincidencia=contCoincidencia+1; // Este contador mide la coincidencia en caso de ver el valor nominal "15"
            }else{
                if  (archflagAnormalidad.get(i).equals(" ")||archflagAnormalidad.get(i).equals("")) { // este condicional excluye de contar  aquellos registros  que se encuentran marcados como anomalos
                    contNoCoincidencia = contNoCoincidencia + 1; // Este contador mide la no cincidencia e incluye contar aquellos que tiene valor "A", ya que caen dentro de este lazo
                }
                }
          }
                //****************************************************************************************************************
        double calcTemp= 1-((contNoCoincidencia/2)/(contCoincidencia+contNoCoincidencia));
        System.out.println("Resultado razon coincidenciaVsNocoinc "+calcTemp+" contaCoincide "+contCoincidencia+ " contaNOcoinc "+contNoCoincidencia);
        if(calcTemp>=0.88){
                resultado="";
        }else{
            resultado="C";
        }
        //System.out.println(((contCoincidencia/(contNoCoincidencia+contCoincidencia)+
          //     "  Calificativo="+resultado+" VALOR COINCIDENCIA "+contCoincidencia+" VALOR DE NO coincidencia "+contNoCoincidencia)));
        return(resultado);
    } // Fin cumplimiento control Prueba C cadencia

//-----------------------------------------------------------------------------------------------------------------------
    // Esta rutina es llamada por el metodo cumplimiento prueba "C" para medir la desviación de la cadencia
  private long calculocadenciaPruebaC (long cadencia,String fechaInicial, String fechaFinal) throws ParseException {
        // esta rutina convierte la diferencia de horas en milisegundos para finalmente dividirla por 60.000 para convertirla en minutos

      SimpleDateFormat format = new SimpleDateFormat("HH:mm");
      String time1 = fechaInicial;
      String time2 = fechaFinal;
      Date date1 = format.parse(time1);
      Date date2 = format.parse(time2);
      long difference = date2.getTime() - date1.getTime();
      //System.out.println("diferencia de tiempo " + difference);

      if(difference<0){ /// valor es negativo al realizar la diferencia
          difference=((86400000)+(difference)); //sumar algebraicamente el valor
      }

      return (difference/60000);

  } // fin metodo para calcular diferencia de dias


    //________________________________________________________________________________________________________________________________________
    // Formato de fecha y hora incorrecta (dd/mm/yy, hh:mm)
    private String cumplimientoPruebaD() throws ParseException {
        String resultado="";

        long dias;

        //Revisión del formato de fecha dd/mm/aa ______________________________________________________________________________________________

        for (int n=0;n<archFecha.size();n++){
            String lengTempFecha = String.valueOf(archFecha.size());//archFecha.size();
            String tempFecha=archFecha.get(n);
            String extraccChar_dd=archFecha.get(n);
            char[] str2=new char[9];// configura el valor del arreglo para contener la palabra de referencia del nombre del archivo

            String[] parts = tempFecha.split("/");
            //for (int p=0;p<4;p++){
                //System.out.println(p+ " parte="+parts[0]+ " "+parts[1]+" "+parts[2]+ " "+parts.length);
            //}

            //--dias -----
            int cDD= Integer.parseInt(parts[0]);

            if (cDD>0&& cDD<32) {
                //System.out.println("el dia esta en formato");
            }else{

                //error en el formato de dias
                if (archflagAnormalidad.get(n).equals(" ")) {// solo consideran aquellos registros que no tienen anormalidad
                    resultado = "D";
                }
            }

            //-- Mes -----
            int cMes= Integer.parseInt(parts[1]);

            if (cMes>0&& cMes<13) {
                //System.out.println("el Mes esta en formato");
            }else{

                //error en el formato de Mes
                if (archflagAnormalidad.get(n).equals(" ")) {// solo consideran aquellos registros que no tienen anormalidad
                    resultado = "D";
                }
            }

            //--  año   -----
            int cAno= Integer.parseInt(parts[2]);

            //System.out.println(cAno+" año ");
            if ((cAno>15&& cAno<30)||(cAno>2015&& cAno<2030)) { // desde 2015 al 2030 .......
               // System.out.println("el año esta en formato");
            }else{

                //error en el formato de año
                if (archflagAnormalidad.get(n).equals(" ")) {// solo consideran aquellos registros que no tienen anormalidad
                    resultado = "D";
                }
            }

            //  REVISION Y CHEQUEO DEL FORMATO DE LA HORA
            String tempHora=archHora.get(n);
            String[] horaParts = tempHora.split(":");
            //System.out.println("HORA "+ horaParts[0]+" "+horaParts[1]);
            int hora= Integer.parseInt(horaParts[0]);// horas
            int min= Integer.parseInt(horaParts[1]);//minutos

            if ((hora>=0 && hora<25) && ( min==00||min==0 || min==15 || min==30 || min==45) ){

              }else{
              //error en el formato de hora y minutos
                    if (archflagAnormalidad.get(n).equals(" ")) {// solo consideran aquellos registros que no tienen anormalidad
                        resultado = "D";
                    }
              }
       }
        return(resultado);
    }

    // Formato de fecha y hora incorrecta (dd/mm/yy, hh:mm)
    private String cumplimientoPruebaD_Formato_0405() throws ParseException {
        String resultado="";

        long dias;

        //Revisión del formato de fecha dd/mm/aa ______________________________________________________________________________________________

        for (int n=0;n<archFecha.size();n++){
            String lengTempFecha = String.valueOf(archFecha.size());//archFecha.size();
            String tempFecha=archFecha.get(n);
            String extraccChar_dd=archFecha.get(n);
            char[] str2=new char[9];// configura el valor del arreglo para contener la palabra de referencia del nombre del archivo

            String[] parts = tempFecha.split("/");
            //for (int p=0;p<4;p++){
            //System.out.println(p+ " parte="+parts[0]+ " "+parts[1]+" "+parts[2]+ " "+parts.length);
            //}

            //--dias -----
            int cDD= Integer.parseInt(parts[1]);// SEGUNDA PARTE DE PARTS FECHA

            if (cDD>0&& cDD<32) {
                //System.out.println("el dia esta en formato");
            }else{

                //error en el formato de dias
                if (archflagAnormalidad.get(n).equals(" ")) {// solo consideran aquellos registros que no tienen anormalidad
                    resultado = "D";
                }
            }

            //-- Mes -----
            int cMes= Integer.parseInt(parts[0]);// PRIMERA PARTE DE PARTS FECHA

            if (cMes>0&& cMes<13) {
                //System.out.println("el Mes esta en formato");
            }else{

                //error en el formato de Mes
                if (archflagAnormalidad.get(n).equals(" ")) {// solo consideran aquellos registros que no tienen anormalidad
                    resultado ="D";
                }
            }

            //--  año   -----
            int cAno= Integer.parseInt(parts[2]);

            //System.out.println(cAno+" año ");
            if ((cAno>15&& cAno<30)||(cAno>2015&& cAno<2030)) { // desde 2015 al 2030 .......
                // System.out.println("el año esta en formato");
            }else{

                //error en el formato de año
                if (archflagAnormalidad.get(n).equals(" ")) {// solo consideran aquellos registros que no tienen anormalidad
                    resultado = "D";
                }
            }

            //  REVISION Y CHEQUEO DEL FORMATO DE LA HORA
            String tempHora=archHora.get(n);
            String[] horaParts = tempHora.split(":");
            //System.out.println("HORA "+ horaParts[0]+" "+horaParts[1]);
            int hora= Integer.parseInt(horaParts[0]);// horas
            int min= Integer.parseInt(horaParts[1]);//minutos

            if ((hora>=0 && hora<25) && ( min==00||min==0 || min==15 || min==30 || min==45) ){

            }else{
                //error en el formato de hora y minutos
                if (archflagAnormalidad.get(n).equals(" ")) {// solo consideran aquellos registros que no tienen anormalidad
                    resultado = "D";
                }
            }
        }
        return(resultado);
    }



    //___________________________________________________________________________________________________________________
    // Archivo con total de energia igual a cero EN TODOS SUS REGISTROS
    private String cumplimientoPruebaE() throws ParseException {// Prueba de energia total
        String resultado="";
       double sumaEng=0;

        for (int n=0;n<archEnergia.size();n++){

            if (archflagAnormalidad.get(n).equals(" ")){
                sumaEng=sumaEng+archEnergia.get(n);
               // System.out.println("Calificacion (E) LA SUMAEnerg ES="+sumaEng+ " El campo es: "+archflagAnormalidad.get(n));
            }

        }

        if(sumaEng==0){ // CALIFICA DE ACUERDO AL RESULTADOS DE LA SUMA

            resultado="E";
        }else{
            resultado="";
        }

        return(resultado);
    }
//____________________________________________________________________________________________________________________________________
    //ENERGIA Energía con igual valor (distinta de cero) entre períodos consecutivos en más del 80% de los casos
    private String cumplimientoPruebaF() throws ParseException {

        String resultado="";
       // double promedio=calcularPromedio(archEnergia);
       // double desviacionStanddlMuestra=calcularDesviacionMuestra(archEnergia,3) ;
        int indice=0;
        int nMuestrasConstantes=0;
        //double muestrasiguiente=0.00;

        for (int m=0;m<archEnergia.size()-1;m++){

            //  System.out.println("muestra "+archEnergia.get(m)+"   muestra siguiente "+archEnergia.get(m+1));
            double muestraM0=archEnergia.get(m); // carga valor de la muestra en variable temporal
           // System.out.println("valor de la muestra de potencia "+muestraM0);

            //System.out.println("Muestra nro= "+ m +"  >>muestra valor inicial M0= "+
              //    archEnergia.get(m)+ " muestra M+1= "+archEnergia.get(m+1));

            if ((archEnergia.get(m).equals(archEnergia.get(m+1))&& muestraM0>0&&
                    archflagAnormalidad.get(m).equals(" ")&&archflagAnormalidad.get(m).equals(" "))){

                nMuestrasConstantes = nMuestrasConstantes + 1; // Incrementa el contador de muestras iguales
              // System.out.println("Muestra nro= |"+ m + " |fecha= |"+archFecha.get(m)+ "  |Hora= |"+archHora.get(m)+"  |>>muestra valor inicial M0= |"+
                //        archEnergia.get(m)+ " |muestra M+1= |"+archEnergia.get(m+1));
            }
        }
       //System.out.println(" Numero d emuestras constantes ="+nMuestrasConstantes+ " total de muestras del archivo = "+archEnergia.size());
        if (nMuestrasConstantes<archEnergia.size()*90/100){// compara con el 90% de las mmuestras
                                                           // si lasnMuestrasConstantes o repetidas son mayores al 90% se activa la calificación "F"

            resultado="";
        }else{
            resultado="F";
        }
        //System.out.println("nro registros amomalos="+nroMuestraconAarchAnormalidad.size()+" "+ "Resultado Prueba="+resultado+
          //     " nro muestras ctes= "+nMuestrasConstantes+" cantidad de muestras "+archEnergia.size());

        //System.out.println("resultado="+resultado+"  " +nMuestrasConstantes+"  muestras constantes  "+archEnergia.size()+" total de muestras");
        return(resultado);
    }
//___________________________________________________________________________________________________________________________________________________
    // Verificar del cumplimiento en la asignacion del NOMBRE de archivo
    private String cumplimientoPruebaG(String nombreArchivo,
                                      List inicialNombEmpresa,
                                      List ultimoDigitoAnoValido,List cEstratosSetup,List listaTarifasSetup) throws ParseException {

        System.out.println("Entrada calificacion"+nombreArchivo+ " "+inicialNombEmpresa+" "+ultimoDigitoAnoValido);
        String resultado="";

        //System.out.println("Prueba G ensayos  nombre archivo:" +nombreArchivo);
        String ThisnombArchivo=nombreArchivo.substring(nombreArchivo.length()-12);
        String extArchivo = "";// variable donde se carga la extension del archivo
        String [] nombArchSplitCalf=nombreArchivo.split("\\\\");
        String raiznombExt=nombArchSplitCalf[nombArchSplitCalf.length-1];
        System.out.println("Nombre archivo calificacion "+raiznombExt);

        String extraccExtenArchi=nombreArchivo;

       // String extOrdinalesP678=nombreArchivo.substring(nombreArchivo.length()-12);// extrae del nombre del archivo los 12 caracteres q lo definen;
        //String extraccAnoP5=nombreArchivo.substring(nombreArchivo.length()-12);
        //String extraccMesP4=nombreArchivo.substring(nombreArchivo.length()-12);
        //String extraccEstratoP3=nombreArchivo.substring(nombreArchivo.length()-12);
        //String extraccTarifas2=nombreArchivo.substring(nombreArchivo.length()-12);
        //String extraccEmpresaP1=nombreArchivo.substring(nombreArchivo.length()-12);

        String extOrdinalesP678=raiznombExt;// extrae del nombre del archivo los 12 caracteres q lo definen;
        String extraccAnoP5=raiznombExt;
        String extraccMesP4=raiznombExt;
        String extraccEstratoP3=raiznombExt;
        String extraccTarifas2=raiznombExt;
        String extraccEmpresaP1=raiznombExt;



        String extraccCaract = nombreArchivo;

        char[] str2=new char[12];// configura el valor del arreglo para contener la palabra de referencia del nombre del archivo

        extraccExtenArchi=nombreArchivo.substring(nombreArchivo.length()-4);// Extrae lkos ultimos digitos de la extension del archivo
        if (extraccExtenArchi.equals(".txt") || extraccExtenArchi.equals(".TXT") ||extraccExtenArchi.equals(".DAT")|| extraccExtenArchi.equals(".dat")){

        }else{
            resultado="G";
        }

        //EXTRAE LOS DIGITOS 6 7 Y 8 LOS CUALES DEBEN SER NUMERICOS
        extOrdinalesP678.getChars(5,8,str2,0);

        char c=str2[0];
        if (str2[0]>='0'&&str2[0]<='9'&&str2[1]>='0'&&str2[1]<='9'&&str2[2]>='0'&&str2[2]<='9'){           //){


        }else{
            resultado="G";
        }

        // EXTRAE DIGITO 5 DEL ANO DEL PROYECTO 2018=8 2019=9 2020=0 /////////////////////   DIGITO AÑ0S VALIDOS            ////////////////////////////////////
        extraccAnoP5.getChars(4,5,str2,0);
        //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
            for (int i=0;i<ultimoDigitoAnoValido.size();i++) {
               //System.out.println("Antes "+nombreArchivo+ " " +ultimoDigitoAnoValido.get(i)+" "+str2[0]);

                String StrnCaracter = (String) ultimoDigitoAnoValido.get(i); //estas lineas extraen el digito (caracter) del String que viene del archivo ConfigMed
                char caracter=StrnCaracter.charAt(0);

                if (caracter!=str2[0]) {
                    if (i==ultimoDigitoAnoValido.size()-1){
                     // System.out.println(nombreArchivo+" incumple la norma");
                        resultado="G";
                    }
                }else{
                   // System.out.println("despues "+ nombreArchivo+">>...xxxx....... Valor " + str2[0] + "=" + ultimoDigitoAnoValido.get(i) + "=== " + caracter);
                    break;
                }
            }
            //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

        // EXTRAE DIGITO 4 DEL mes 1 a 9 y O N D
        extraccMesP4.getChars(3,4,str2,0);
        if ((str2[0]>='0'&&str2[0]<='9')||(str2[0]=='O'||str2[0]=='N'||str2[0]=='D')) {


        }else{
            resultado="G";
        }
//---------------------------------
        //xestratos EXTRAE DIGITO 3 asociado a los estratos del 1 al 7
        extraccEstratoP3.getChars(2,3,str2,0);
        //for (int i=0;i<cEstratosSetup.size();i++) {
       // System.out.println("extraccion ESTRATOS "+ str2[0]+"  "+extraccTarifas2);
        //---------------
        boolean flagCoincidenciaEstrato=false;
        for(int t=0;t<cEstratosSetup.size();t++){
            String strcEstrato= String.valueOf(cEstratosSetup.get(t));
            char charEstrato=strcEstrato.charAt(0);
           // System.out.println("evaluacion tarifa setup zz  " + charEstrato + " | "+str2[0]);
            if (charEstrato == str2[0]) {
               // System.out.println(nombreArchivo+"evaluacion tarifa Coincidente zx " + charEstrato+ " | "+str2[0]);
                flagCoincidenciaEstrato=true;
            }
        }
        if(flagCoincidenciaEstrato==false){
            resultado="G";
        }


        //----------------------------------------------------------
        // EXTRAE DIGITO 2 Categoria de TARIFAS R G 4 2
        extraccTarifas2.getChars(1,2,str2,0);
        //System.out.println("extraccion tarifas "+ str2[0]+"  "+extraccTarifas2);
        //---------------
        boolean flagCoincidenciaTarifa=false;
        for(int t=0;t<listaTarifasSetup.size();t++){
            String strtarifaT= String.valueOf(listaTarifasSetup.get(t));
            char charTarifa=strtarifaT.charAt(0);
            //System.out.println("evaluacion tarifa setup zz  " + charTarifa + " | "+str2[0]);
            if (charTarifa == str2[0]) {
                System.out.println(nombreArchivo+"evaluacion tarifa Coincidente zx " + charTarifa+ " | "+str2[0]);
                flagCoincidenciaTarifa=true;
            }
        }
        if(flagCoincidenciaTarifa==false){
            resultado="G";
        }
        //---------------------------------------------------------------------------
        // EXTRAE DIGITO 1 asociado a cada una de las empresas

        extraccEmpresaP1.getChars(0,1,str2,0);

       // System.out.println("raizNombExt "+raiznombExtCalf+ "    ExtraP1 "+extraccEmpresaP1);
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~xxxxxxxxxxxxxxxxxxxxxxxxxxxx

        for (int i=0;i<inicialNombEmpresa.size();i++) { // barra la lista con las iniciales de las empresas
            //System.out.println("Antes barrido "+nombreArchivo+ " " +inicialNombEmpresa.get(i)+" "+str2[0]);

            String StrnCaracter = (String) inicialNombEmpresa.get(i); //estas lineas extraen el digito (caracter) del String que viene del archivo ConfigMed
            char caracter=StrnCaracter.charAt(0);
            //System.out.println("InicialEmpresaCalificacion = "+caracter+ " Letra arch = "+StrnCaracter.charAt(0)+ " segundo "+StrnCaracter);

            if (caracter!=str2[0]) {
                if (i==inicialNombEmpresa.size()-1){ //si no encuentra coincidencia con las lista de iniciales asume el incumplimiento de la convension
                    // System.out.println(nombreArchivo+" incumple la norma");
                    resultado="G";
                }
            }else{
               //  System.out.println("coincidencia Linicial Empresa "+ nombreArchivo+">>...xxxx....... Valor " + str2[0] + "=" + inicialNombEmpresa.get(i) + "=== " + caracter);
                break;
            }
        }
        //------------------------------
        // identifica si el numero de caracteres que componen el "nombre +EXTension archivo" coincide con el número correspondiente a ese formato
        if ((raiznombExt.length()>(numeroTotalDigitosNombreArchivoxtipoFormato+4))||(raiznombExt.length()<(numeroTotalDigitosNombreArchivoxtipoFormato+4))){
            resultado="G";
        }

        //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

        return(resultado);
    }// Fin prueba G
//---------------------------------------------------------------------------------------------

    // Verificar del cumplimiento en la asignacion del NOMBRE de archivo FORMATO_05
    // ESPECIAL variante para FORMATO_05
    private String cumplimientoPruebaG_Formato05(String nombreArchivo,
                                                 List inicialNombEmpresa,
                                                 List ultimoDigitoAnoValido,List cEstratosSetup,List listaTarifasSetup) throws ParseException {

        System.out.println("Entrada calificacion"+nombreArchivo+ " "+inicialNombEmpresa+" "+ultimoDigitoAnoValido);
        String resultado="";

        //System.out.println("Prueba G ensayos  nombre archivo:" +nombreArchivo);
        String ThisnombArchivo=nombreArchivo.substring(nombreArchivo.length()-12);
        String extArchivo = "";// variable donde se carga la extension del archivo
        String [] nombArchSplitCalf=nombreArchivo.split("\\\\");
        String raiznombExt=nombArchSplitCalf[nombArchSplitCalf.length-1];
        System.out.println("Nombre archivo calificacion: "+raiznombExt+ " | nroDigitos "+raiznombExt.length());

        String extraccExtenArchi=nombreArchivo;

        String extOrdinalesP678=raiznombExt;// extrae del nombre del archivo los 12 caracteres q lo definen;

        String extraccNICP4=raiznombExt;//5. NIC estrae caracter de caracter 14-->
        String extraccNISP3=raiznombExt;// 4. NIS del caracter 5 al 13
        String extraccMESP3=raiznombExt;// 3. mes validos del corte (mes 1--> mes 12)
        String extraccAnoP2=raiznombExt;// 2. digito para años configurados de vigencia
        String extraccEmpresaP1=raiznombExt;//1.Inicial Nmbre
        String extraccTarifas2=raiznombExt;// Tarifas
        String  extraccAnoP5=raiznombExt;// validez del año de extraccion de medicion
        String extraccCaract = nombreArchivo;



        try {


            char[] str2 = new char[12];// configura el valor del arreglo para contener la palabra de referencia del nombre del archivo

            extraccExtenArchi = nombreArchivo.substring(nombreArchivo.length() - 4);// Extrae lkos ultimos digitos de la extension del archivo
            if (extraccExtenArchi.equals(".txt") || extraccExtenArchi.equals(".TXT") || extraccExtenArchi.equals(".DAT") ||
                    extraccExtenArchi.equals(".dat")) {
            } else {
                resultado = "G";
            }
            //=====================================================================
            //----------------------------
            //EXTRAE los 2 cracateres separadores "_" y "-"

            //=====================================================================
            //EXTRAE 5 Digitos NIC y chequea longitud


            //<<<<<<<<<<<<<<<<
            int NIC_longitud = 7; // Canitidad de digitos que componen el NIC
            int digitoInicialNIC = 13;// Lugar donde inicia el 1er digito en el nombre del archivo

            //-------------------------------------------------------------
            // este bloque extrae el digito inicial del nombre del archivo para identificar la empresa
            // Si es D digitos Nis=9 otro digitosNis=7
            String StrDigitos = extraeNombreArchivoBaseSinrutaNiextension(nombreArchivo); //estas lineas extraen el digito (caracter) del String que viene del archivo ConfigMed
            char digitoIncialEmpresa = StrDigitos.charAt(0);

            System.out.println("DigitoInicial empresa: "+digitoIncialEmpresa+" | nombrearchivo:"+extraeNombreArchivoBaseSinrutaNiextension(nombreArchivo)
            );
            if (digitoIncialEmpresa=='D'){
                NIC_longitud=9; // asigna 9 digitos para la calificacion en caso de la empresa DELSUR
                digitoInicialNIC = 15;// Lugar donde inicia el 1er digito en el nombre del archivo
            }
            //-------------------------------------------------------------




            extraccNICP4.getChars(digitoInicialNIC, (digitoInicialNIC + NIC_longitud), str2, 0);
            for (int n = 0; n < NIC_longitud; n++) {
                if ((str2[n] >= '0' && str2[n] <= '9')) {
              //      System.out.println("Digitos NIC  " + extraccNICP4 + " " + " " + str2[n]);
                } else {
                    resultado = "G";
                }
            }
             String[] NIC = extraccNICP4.split("-"); // revisa la longitud del string del NIS
             //System.out.println(NIC[1] + " Nis long= " + NIC[0].length() + " " + NIC_longitud);
             String strTempNic=NIC[1];
             NIC = strTempNic.split("\\.");// aisla los digitos del NIC para confirmar longitud correcta
            int nicLongitud=NIC[0].length();

            if (nicLongitud != NIC_longitud) {
                resultado = resultado + "G";
            }
            System.out.println(NIC[0] + " Resultado NiC long= " + NIC[0].length() + " " + NIC_longitud);

            //=================================================================================
            // EXTRAE 4  Contenido del NIS  y Chequea que todos los digitos sea numéricos

            int NIS_longitud = 7;

            //-------------------------------------------------------------
            // este bloque extrae el digito inicial del nombre del archivo para identificar la empresa
            // Si es D digitos Nis=9 otro digitosNis=7
            String StrDigitos2 = extraeNombreArchivoBaseSinrutaNiextension(nombreArchivo); //estas lineas extraen el digito (caracter) del String que viene del archivo ConfigMed
            char digitoIncialEmpresa2 = StrDigitos.charAt(0);

            System.out.println("DigitoInicial empresa: "+digitoIncialEmpresa2+" | nombrearchivo:"+extraeNombreArchivoBaseSinrutaNiextension(nombreArchivo)
            );
            if (digitoIncialEmpresa=='D'){
                NIS_longitud=9; // asigna 9 digitos para la calificacion en caso de la empresa DELSUR
            }
            //-------------------------------------------------------------

            int digitoInicialNIS = 5;
            extraccNISP3.getChars(digitoInicialNIS, (digitoInicialNIS + NIS_longitud), str2, 0);
            for (int n = 0; n < NIS_longitud; n++) {
                if ((str2[n] >= '0' && str2[n] <= '9')) {
                    //System.out.println("Digitos NIS  " + nombreArchivo + " " + " " + str2[n]);
                } else {
                    resultado = "G";
                }
            }
            String[] NIS = extraccNISP3.split("_"); // revisa la longitud del string del NIS
            NIS = NIS[1].split("-");
            int nisLongitud=NIS[0].length();

            if (nisLongitud != NIS_longitud) {
                resultado = "G";
            }
            System.out.println(NIS[0] + " Nis long= " + NIS[0].length() + " " + NIS_longitud);


            //==========================================================================
            // EXTRAE DIGITO 3 y 4  :  asociado a los meses (01-->12)
            extraccMESP3.getChars(2, 3, str2, 0); //Extrae 1er digito mes
            Character digito1mes = str2[0];
            System.out.println("Digito 1X Mes Antes " + nombreArchivo + " " + " " + digito1mes);
            if (digito1mes >= '0' && digito1mes < '2') {

            } else {
                resultado = "G";
            }
            extraccMESP3.getChars(3, 4, str2, 0);// Extrae 2do digito mes
            Character digito2mes = str2[0];
            //System.out.println("Digito X1 Mes Antes "+nombreArchivo+ " " +" "+digito2mes);
            if (digito2mes >= '0' && digito2mes <= '9') {

            } else {
                resultado = "G";
            }

            if ((digito1mes == '0' && digito2mes == '0') || (digito1mes == '1' && digito2mes > '2') || (digito1mes > '1' && digito2mes == '0')) {// chequea la combinacion de ambos digitos del mes
                resultado = "G";
            }
            //============================================================================
            // EXTRAE DIGITO 2 Año de extraccion de medición
            extraccAnoP5.getChars(1, 2, str2, 0);
            //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
            for (int i = 0; i < ultimoDigitoAnoValido.size(); i++) {
                System.out.println("Antes " + nombreArchivo + " " + ultimoDigitoAnoValido.get(i) + " =? " + str2[0]);

                String StrnCaracter = (String) ultimoDigitoAnoValido.get(i); //estas lineas extraen el digito (caracter) del String que viene del archivo ConfigMed
                char caracter = StrnCaracter.charAt(0);

                if (caracter != str2[0]) {
                    if (i == ultimoDigitoAnoValido.size() - 1) {
                        System.out.println(nombreArchivo + " incumple la norma " + caracter + " != " + str2[0]);
                        resultado = "G";
                    }
                } else {
                    // System.out.println("despues "+ nombreArchivo+">>...xxxx....... Valor " + str2[0] + "=" + ultimoDigitoAnoValido.get(i) + "=== " + caracter);
                    break;
                }
            }
            //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
            //=================================================================================
            // EXTRAE DIGITO 1 asociado a cada una de las empresas
            extraccEmpresaP1.getChars(0, 1, str2, 0);
            //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~xxxxxxxxxxxxxxxxxxxxxxxxxxxx

            for (int i = 0; i < inicialNombEmpresa.size(); i++) { // barra la lista con las iniciales de las empresas
                //System.out.println("Antes barrido "+nombreArchivo+ " " +inicialNombEmpresa.get(i)+" "+str2[0]);

                String StrnCaracter = (String) inicialNombEmpresa.get(i); //estas lineas extraen el digito (caracter) del String que viene del archivo ConfigMed
                char caracter = StrnCaracter.charAt(0);
                //System.out.println("InicialEmpresaCalificacion = "+caracter+ " Letra arch = "+StrnCaracter.charAt(0)+ " segundo "+StrnCaracter);

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
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("falla formato Nomrbre archivo");
            resultado="G";
        }
        return(resultado);
    }// Fin prueba G FORMATO_05
//---------------------------------------------------------------------------------------------




    private String cumplimientoPruebaH() throws ParseException { // control de energia
        int valorUmbral=1/10;
        String resultado="";
       double energProm= archTotalEnergia/archFP.size()/4;
       double sumaMuestrasEnerg=0.00;
       int cantMuestras=0;
       //______________________________________________________________________________________
        double muestraEnergia=0;

        System.out.println("Prueba H: arhEnergia="+archEnergia.size()+" | archAnormalidad="+archAnormalidad.size()+"  | " + archEnergia.size());
        for (int n=0;n<archEnergia.size();n++){
            if (archflagAnormalidad.get(n).equals(" ")){
                sumaMuestrasEnerg=sumaMuestrasEnerg+archEnergia.get(n);
                muestraEnergia=archEnergia.get(n);
               // System.out.println("valoresmanejados "+muestraEnergia+"  "+archEnergia.get(n));
                if (muestraEnergia<0) { // sia existe alguna muestra de energia
                    cantMuestras=cantMuestras+1;
                }
            }
        }
        if (cantMuestras>0) {
           // System.out.println("RESULTADO eval =" + resultado+" cantmuestrasnegativas"+cantMuestras);
            resultado="H";
        }
        System.out.println("retorno prueba H= " + resultado);
        return(resultado);
    }
    //--------------------------------------------------------------------------------

    private String cumplimientoPruebaI() throws ParseException{
        String resultado="";

        // se incluye el indicador I para detectar cuando la Energía reactiva > Energia Real
        if (archTotalEnergia<archTotalEnergiaReactiva){
            resultado=resultado+"I";
        }
        return(resultado);
    }


    //**********************************************************************************************************************
    //----------------------------------------------------------------------------------
    public double calcularPromedio( List array) {

        double resultado = 0.00;
        for (int i=0;i<array.size();i++){
            resultado=resultado+ Double.parseDouble(String.valueOf(array.get(i)));
        }
        resultado= resultado/array.size();
        return (resultado);
    }
    //---------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------
    public double calcularMinimo( List array) {// retorna el valor más bajo del arreglo

        double resultado = (double) array.get(0);
        for (int i=0;i<array.size()-1;i++){
            double tempArray= (double) array.get(i);
            if (resultado>tempArray){
                resultado=tempArray;
            }
        }
        return (resultado); // retorna el mín valor del arreglo
    }
    //---------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------
    public double calcularMinimoSinRegAnomalos( List array) {// retorna el valor más bajo del arreglo
        //asignacion valor inicial
        double resultado = 0;
        Double valorminimoprimerregistro=0.0;
        for (int i=0;i<array.size()-1;i++){//Asigna el primer valor con registro no marcado

            if (archflagAnormalidad.get(i).equals(" ")) {
                valorminimoprimerregistro = (Double) array.get(i);
                System.out.println("primervalorminimo="+valorminimoprimerregistro);
                break;
            }
        }

        for (int i=0;i<array.size()-1;i++){
            double tempArray= (double) array.get(i);
            if (valorminimoprimerregistro>tempArray){
                if (archflagAnormalidad.get(i).equals(" ")) {
                    valorminimoprimerregistro = tempArray;
                    System.out.println("valorminimoenLazo="+valorminimoprimerregistro);
                }
            }
        }
        System.out.println("valorfinalMinimo="+valorminimoprimerregistro);
        return (valorminimoprimerregistro); // retorna el mín valor del arreglo
    }
    //---------------------------------------------
    public double redondeaDouble2decimales(double x){ // recibe numero double y devuelve un double con 2 decimales
        DecimalFormat numeroDoubleFormateado=new DecimalFormat("#.##");
        x= Double.parseDouble(numeroDoubleFormateado.format(x).replace(",","."));
        return x;// regresa numero formateado
    }


    //----------------------------------------------------------------------------------
    public double calcularMaximo( List array) { // retorna el valor más alto del arreglo
        double resultado = (double) array.get(0);
        for (int i=0;i<array.size()-1;i++){
            double tempArray= (double) array.get(i);
            if (resultado<tempArray){
                resultado=tempArray;
            }
        }
        return (resultado);
    }
    //---------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------
    public double calcularMaximoSinAnomalos( List array) { // retorna el valor más alto del arreglo

        double valorMaximo=0.0;

        //asigna primer valor
        for (int i=0;i<array.size()-1;i++){
            if (archflagAnormalidad.get(i).equals(" ")) {
                valorMaximo = (Double) array.get(i);
                break;
            }
        }

        for (int i=0;i<array.size()-1;i++){
            double tempArray= (double) array.get(i);
            if (valorMaximo<tempArray){
                if (archflagAnormalidad.get(i).equals(" ")) {
                    valorMaximo = tempArray;
                }
            }
        }
        return (valorMaximo);
    }
    //---------------------------------------------------------------------------------

    public double calcularDesviacionMuestra( List array,double media) {

        double resultIntermedio = 0.00;
        double resultado=0.00;

        for (int i=0;i<array.size();i++){
            resultIntermedio=resultIntermedio+Math.pow((Double.parseDouble(String.valueOf(array.get(i)))-media),2);//calcula diferencia de la media a cuadrado
        }
        resultado=(resultIntermedio)/(Double.parseDouble(String.valueOf(array.size())));
        resultado=Math.sqrt(resultado);

        return (resultado);
    }

public boolean habilitadaSalidaArchNormalizado() throws IOException {
    // CARGA CONFIGURACION DESDE EL ARCHIVO ConfigMed >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><

    Setup setuparch = new Setup();// instancia la clase Setup, donde estan la configuración inicial que sera leida
    setuparch.inicializaConfiguracionBaseTxt(); //llama al metod que lee el archivo "configMed.txt"
    System.out.println("Leida configuracion CPMFIG MED ciudad: " + setuparch.ClugarProyecto);

    String var = String.valueOf(setuparch.habilitaGenerarArchEstand);
    String var2= (setuparch.getBtnHabilitacionFormato().get(5));
    if (var.equals("1") && var2.equals("1")) {
        return true;
    } else {
        return false;
    }
}
public StringBuffer sbCrLf(){ // regresa un string Ascii CR + LF
    StringBuffer sbCrLf = new StringBuffer();
    sbCrLf.append((char)13);
    sbCrLf.append((char)10);

      return sbCrLf;
}
//------------------------------------------------
public StringBuffer spaceAscii(int n){ // regresa n espacios charAscii (20)
    StringBuffer spaceVisor = new StringBuffer();
    for (int i=0;i<n;i++) {
        spaceVisor.append(" ");
    }
    return spaceVisor;
}

    //---------------------------------------

    public String ConvertFecha(boolean flagFecha, char[]strF,String palabraFecha){ // esta procedimiento es utilizado por el case para leer y convertir la fecha al formato adecuado

        //System.out.println("subrutina de ajuste de fecha");

        palabraFecha.getChars(0,palabraFecha.length(),strF,0);// carga los caracteres de la palabra en el arreglo "strF"
        //System.out.println("Palabra evaluada " + palabraFecha+" -->longitud="+strF.length+" tiene la fecha en posicion nro.="+p+ " ");
        String nvoformatoFecha="";
        String tempMes="";
        String tempDia="";
        if (flagFecha) {
            if(strF[1]=='/'){ // si mes es de 1 solo digito
                tempMes=tempMes+strF[0];
                if (strF[3]=='/') {//asignacion dia con 1 digito
                    tempDia = tempDia + strF[2];
                }
                if (strF[4]=='/') {//asignacion dia con 1 digito
                    tempDia = tempDia + strF[2]+strF[3];
                }
            }else{ // es un mes de dos digitos
                tempMes=tempMes+strF[0]+strF[1];
                if (strF[4]=='/') {
                    tempDia = tempDia+strF[3];
                    //System.out.println("dia " + tempDia);
                }
                if (strF[5]=='/') {
                    tempDia = tempDia+strF[3]+strF[4];
                    // System.out.println("dia " + tempDia);
                }
            }

            nvoformatoFecha=tempDia+"/"+tempMes+"/"+palabraFecha.substring(palabraFecha.length()-4);
            //**************** fIN DETECCION DEL CAMPO FECHA......................................
            //   horaArchOrig.add(horaParts[1]); // el ordinal cambia porque la parte 0 fué dividida para separar hora y fecha
            //System.out.println("Hora=>"+horaParts[1]);
        }


        return (nvoformatoFecha);
    } //****  fin del procedimiento captura fecha

    //------------------------------------------------------




    // Este metodo permite habilitar la ventana de dialogo para seleccionar la ubicacion del archivo de usuarios
    public void principalCompilacionArchivos(String directoriodeTrabajo) throws IOException {
        System.out.println("zx2 Selecciona archivo de usuario para C O M P I L A C I O N");


        //*****     **************    ***********************    *********************     **************************   *****************************
        //**** LLAMA LA VENTANA DE EXPLORACION PARA DEFINIR EL ARCHIVO DE USUARIOS SELECCIONADOS PARA EXTRAER LOS DATOS REQUERIDOS DE USUARIO  Y TARIFA
        // Este dato se utiliza en el metodo de Archivo.leerArchivoInstalacion que se llama mas abajo
        Boolean old2 = UIManager.getBoolean("FileChooser.readOnly");// apaga edicion
        UIManager.put("FileChooser.readOnly", Boolean.TRUE);// apaga edicion
        JFileChooser jfc2 = new JFileChooser(directoriodeTrabajo);
        // prueba de filtrado de archivos
        jfc2.setAcceptAllFileFilterUsed(false);// desactiva listar todos los tipos de archivo
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos", "txt"); // Estas son las extesiones que seran filtradas en la visualzacion
        jfc2.addChoosableFileFilter(filter);// suma la condicion de filtrado
        //jfc2.showOpenDialog(null);
        jfc2.setFileFilter(filter);
        UIManager.put("FileChooser.readOnly", old2);//======================================== apaga edicion
        jfc2.setDialogTitle("SELECCIONE EL ARCHIVO DE USUARIOS SELECCIONADOS A SER PROCESADO ");
        //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        String nombreArchivoUsuarioSelecc = ""; // Variable que capturara mas abajo el valor de archivo de usuario sleccionado
        // en el explorador
        //jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jfc2.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnValue2 = jfc2.showOpenDialog(null);

        if (returnValue2 == JFileChooser.APPROVE_OPTION) {
            nombreArchivoUsuarioSelecc = String.valueOf(jfc2.getSelectedFile()); // Variable que carga el valor de archivo de usuario sleccionado
            System.out.println("zx3 El archivo de usuario seleccionado es: " + jfc2.getSelectedFile());

            leerArchInstalacion(directoriodeTrabajo,nombreArchivoUsuarioSelecc);


            if (jfc2.getSelectedFile().isDirectory()) {
                //System.out.println("El archivo seleccionado es: " + jfc.getSelectedFile());
            }
        } else {
            System.out.println("SELECCION CANCELADA");
            System.exit(0);// aborta el programa al 0primir cancel en el explorador
        } // Fin proceso leer archivo de instalacion y/o usuario

    }// fin principalCompilacionArchivos
//-----------------------------------------------------------------

    // Este metodo permite habilitar la ventana de dialogo para seleccionar la ubicacion del archivo de usuarios
    public void principalCompilacionArchivosFormato0405(String directoriodeTrabajo) throws IOException {
        System.out.println(" Selecciona archivo de usuario para C O M P I L A C I O N");


        //*****     **************    ***********************    *********************     **************************   *****************************
        //**** LLAMA LA VENTANA DE EXPLORACION PARA DEFINIR EL ARCHIVO DE USUARIOS SELECCIONADOS PARA EXTRAER LOS DATOS REQUERIDOS DE USUARIO  Y TARIFA
        // Este dato se utiliza en el metodo de Archivo.leerArchivoInstalacion que se llama mas abajo
        Boolean old2 = UIManager.getBoolean("FileChooser.readOnly");// apaga edicion
        UIManager.put("FileChooser.readOnly", Boolean.TRUE);// apaga edicion
        JFileChooser jfc2 = new JFileChooser(directoriodeTrabajo);
        // prueba de filtrado de archivos
        jfc2.setAcceptAllFileFilterUsed(false);// desactiva listar todos los tipos de archivo
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos", "txt"); // Estas son las extesiones que seran filtradas en la visualzacion
        jfc2.addChoosableFileFilter(filter);// suma la condicion de filtrado
        //jfc2.showOpenDialog(null);
        jfc2.setFileFilter(filter);
        UIManager.put("FileChooser.readOnly", old2);//======================================== apaga edicion
        jfc2.setDialogTitle("SELECCIONE EL ARCHIVO DE USUARIOS SELECCIONADOS A SER PROCESADO ");
        //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        String nombreArchivoUsuarioSelecc = ""; // Variable que capturara mas abajo el valor de archivo de usuario sleccionado
        // en el explorador
        //jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jfc2.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnValue2 = jfc2.showOpenDialog(null);

        if (returnValue2 == JFileChooser.APPROVE_OPTION) {
            nombreArchivoUsuarioSelecc = String.valueOf(jfc2.getSelectedFile()); // Variable que carga el valor de archivo de usuario sleccionado
            System.out.println("El archivo de usuario seleccionado es: " + jfc2.getSelectedFile());

            leerArchInstalacionFormato0405(directoriodeTrabajo,nombreArchivoUsuarioSelecc);


            if (jfc2.getSelectedFile().isDirectory()) {
                //System.out.println("El archivo seleccionado es: " + jfc.getSelectedFile());
            }
        } else {
            System.out.println("SELECCION CANCELADA");
            System.exit(0);// aborta el programa al 0primir cancel en el explorador
        } // Fin proceso leer archivo de instalacion y/o usuario

    }// fin principalCompilacionArchivosFormato0405
//-----------------------------------------------------------------








    // Este metodo es llamado por la clase mediciones a fin de cubrir el proceso de leer archivo usuarios/instalacion
    // y generar archivo Med Integrada
    public void leerArchInstalacion(String sdirectorioTrabajo,String nombArchivoIntalacion) throws IOException {
       // System.out.println("zx>>>>  >>>>>>>  >>>>> >>>>>> EL archivo de instalacion a  considerar es : "+nombArchivoIntalacion);

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




        FileReader   configReader = new FileReader(ruta_ArchInstalacion);

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
                    try {
                        switch (p) {
                            case 0:
                                if (flagArchivoUsuarioconMes) {

                                } else {// campo nombreArch SW 0
                                    // System.out.println(p+"CAMPO SW0 nombre archivo "+parts[p]);
                                    nombArchMedicionInstalacionTXT.add(parts[p]); // Carga el nombre del archivo de medicion
                                }
                                break;

                            case 1:

                                if (flagArchivoUsuarioconMes) {// campo nombreArch SW 1
                                    //System.out.println(p+"CAMPO SW1 nombre archivo "+parts[p]);
                                    nombArchMedicionInstalacionTXT.add(parts[p]); // Carga el nombre del archivo de medicion
                                } else { // Caso arch sin campo mes, se corre Id usuario a este punto

                                    //  System.out.println(p + "CAMPO ID " + parts[p]);
                                    idUsuarioArchInstalacion.add(parts[p]);// carga el número de usuario asociado a la medicion

                                   // JOptionPane.showMessageDialog(null, "idUsuario"+parts[p]+" | "+parts[p+1]);
                                    factorCorreccionTranformacion.add(parts[p+1]);
                                }

                                //System.out.println(indicadorPosic+" valor del primer campo del archivo de instalacion "+parts[1] );
                                break;
                            case 2:

                                if (flagArchivoUsuarioconMes) {// caso sw 2
                                    //System.out.println(p + "CAMPO ID " + parts[p]);
                                    idUsuarioArchInstalacion.add(parts[p]);// carga el número de usuario asociado a la medicion
                                } else {


                                }

                                //System.out.println(indicadorPosic+ " valor del segundo campo del archivo de instalacion "+parts[2]);
                                break;


                            case 8: // caso de habilitacion de la tarifa para archivos sin mes
                                if (flagArchivoUsuarioconMes) {// caso sw 9 campo de tarifa

                                } else {// caso sw 8 captura tarifa archivo con campo mes
                                    // System.out.println(p + "CAMPO  tarifa " + parts[p]);
                                    tarifaPlanillaTXT.add(parts[p] + "-" + parts[p + 1]);// carga el número de usuario asociado a la medicion
                                }

                                break;
                            case 9:
                                //System.out.println(p + "CAMPO Tarifa " + parts[p]+ parts[p + 1]);
                                if (flagArchivoUsuarioconMes) {// caso sw 9 campo de tarifa
                                    // System.out.println(p + "CAMPO  tarifa " + parts[p]);
                                    tarifaPlanillaTXT.add(parts[p] + "-" + parts[p + 1]);// carga el número de usuario asociado a la medicion
                                } else {

                                }
                                //System.out.println(indicadorPosic+ " valor del  campo tarifa del archivo de instalacion "+parts[9]);
                                break;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Ocurrió un problema al cargar BD Usuarios seleccionada");
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
        System.out.println("Zx4 PlanillausuariosInstalacion: "+idUsuarioArchInstalacion.size() +
               " |ArchivoTarifa "+tarifaPlanillaTXT.size()+ " | archPlanillaNombArchMedicionInstalacionTXT "
                +nombArchMedicionInstalacionTXT.size() );
    } // fin metodo leerArchInstalacion

    //------------------------------------------------------------
    // Este metodo es llamado por la clase mediciones a fin de cubrir el proceso de leer archivo usuarios/instalacion
    // y generar archivo Med Integrada
    public void leerArchInstalacionFormato0405(String sdirectorioTrabajo,String nombArchivoIntalacion) throws IOException {
        //System.out.println(">>>>  >>>>>>>  >>>>> >>>>>> EL archivo de instalacion a  considerar es : "+nombArchivoIntalacion);

        //  NOMBRE DEL ARCHIVO PLANILLA DE INSTALACION A EDITAR PARA CONSTRUIR NUEVO ARCHIVO
        // String archInstalacion="LC196_PLANILLA_INTALACION.txt"; //  1- PLANILLA instalacion   IDUSUARIO Y ARCHIVO
        //String archInstalacion = "NC196_USUARIOS_SELECCIONADOS.txt";//  2- PLANILLA usuarios seleccionados IDUSUARIO Y ARCHIVO
        // String ruta_ArchInstalacion = sdirectorioTrabajo + "\\" + archInstalacion;

        String ruta_ArchInstalacion = nombArchivoIntalacion; // toma de los parametros que llegan del constructor para definir
        // el archivo de "usuarios_seleccionados.txt" a procesar
        //*******************************************************

        //String pathArchInstalacion=System.getProperty("user.dir");
        // System.out.println(archInstalacion + " Mensaje desde Archivo leerConfInstalación path=" + ruta_ArchInstalacion);

        System.out.println(" Mensaje desde Archivo leerConfInstalación path=" + ruta_ArchInstalacion); // indica ruta y nombre del archivo
        // "xxxx_Usuarios_Seleccionados-txt" a ser leido

        FileReader   configReader = new FileReader(ruta_ArchInstalacion);

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

            System.out.println("line  en registro de prueba "+ blinea);
            indicadorPosic = indicadorPosic + 1;

             parts = blinea.split("\\|");

            if (indicadorPosic > 1) { // este es el condicional para que solo procese los valores y salte el encabezado

                for (int p = 0; p < parts.length; p++) {
                    System.out.println(" valor del  campo "+p+" parts "+parts[p] );
                    // swich de bifurcacion
                    try {
                        switch (p) {
                            case 0:
                                    nombArchMedicionInstalacionTXT.add(parts[p]); // Carga el nombre del archivo de medicion

                                break;

                            case 1:

                                  idUsuarioArchInstalacion.add(parts[p]);// carga el número de usuario asociado a la medicion

                                //System.out.println(indicadorPosic+" valor del primer campo del archivo de instalacion "+parts[1] );
                                break;
                            case 2:

                                break;
                            case 8://UPR
                                condicionUPR.add(parts[p]);// condicion UPR
                                System.out.println("condicion UPR  "+parts[p]);
                                break;
                            case 9: // Tarifa ... se captura tarifa (p) y estrato (p+1) en este caso
                                    tarifaPlanillaTXT.add(parts[p] + "-" + parts[p + 1]);// carga el número de usuario asociado a la medicion
                                break;
                            case 10://estrato, se incorpora en case (9) caso anterior

                                                               //System.out.println(indicadorPosic+ " valor del  campo tarifa del archivo de instalacion "+parts[9]);
                                break;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Ocurrió un problema al cargar BD Usuarios seleccionada. Revisar formato");
                        System.exit(0);
                    }
                }
            }else { // TRATAMIENTO DEL ENCABEZADO

                System.out.println( "Encabezado Blinea "+blinea);
                String[] partsFlag = blinea.split("|");
            }
        }
        System.out.println("PlanillausuariosInstalacion: "+idUsuarioArchInstalacion.size() +
                " |ArchivoTarifa "+tarifaPlanillaTXT.size()+ " | archPlanillaNombArchMedicionInstalacionTXT "
                +nombArchMedicionInstalacionTXT.size() );

    } // fin metodo leerArchInstalacionFormato0405

    //--------------------------------------------------------------



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
                break;
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
                break;
            }
        }

        return (resultado);
    }
//--------------------------------------------------
//METODO PARA LA BUSQUEDA DE LA CONDICION upr USUARIO PROVEEDOR RENOVABLE
public String buscarUsuarioUPR(String stringNombArchivoMedicionTXT) {

    //System.out.println(nombArchMedicionInstalacionTXT.size()+" codigo buscado en buscarUsuarioxNOmbreArchivoIntalacion "+stringNombArchivoMedicionTXT);

    String resultado = "NoEncontrado"; // nro de usuario resultante luego de buscar nombre archv
    // sino hay coincidencia colocara resultado="NoEncontrado"

    int coincidencia = 0; // variable de control

    for (int i = 0; i < nombArchMedicionInstalacionTXT.size(); i++) {
        //System.out.println(i+" valor del vector "+nombArchMedicionInstalacionTXT.get(i));
        if (stringNombArchivoMedicionTXT.equals(nombArchMedicionInstalacionTXT.get(i))) {

            // System.out.println("coincidencia en el lazo de busqueda");
            coincidencia = 1;
            resultado = condicionUPR.get(i); // Id usuario corespondiente al nombre de archivo de la lista
            break;
        }
    }

    return (resultado);
}
//----------------------------------------------------------------------------------

    //METODO PARA LA BUSQUEDA DEL NUMERO DE USUARIO DADO EL NOMBRE DEL ARCHIVO
    public String buscarFactorTransformacionxNombArchivoInstalacion(String stringNombArchivoMedicionTXT) {

        //System.out.println(nombArchMedicionInstalacionTXT.size()+" codigo buscado en buscarUsuarioxNOmbreArchivoIntalacion "+stringNombArchivoMedicionTXT);

        String resultado = "1"; // nro de usuario resultante luego de buscar nombre archv
        // sino hay coincidencia colocara resultado="NoEncontrado"

        int coincidencia = 0; // variable de control

        for (int i = 0; i < nombArchMedicionInstalacionTXT.size(); i++) {
            //System.out.println(i+" valor del vector "+nombArchMedicionInstalacionTXT.get(i));
            if (stringNombArchivoMedicionTXT.equals(nombArchMedicionInstalacionTXT.get(i))) {

                // System.out.println("coincidencia en el lazo de busqueda");
                coincidencia = 1;
                resultado = factorCorreccionTranformacion.get(i); // Factor K al nombre de archivo de la lista
                break;
            }
        }

        return (resultado);
    }






//----------------------------------------------------
//---------- metodo de conversion de formato fecha de dd/mm/YYYY a mm/dd/AAAA
public String convertirDateformatoddMMAAAaMMddAAAA(String fecha){
    String F1=fecha;//Fecha Final
    String [] partfechatemporal=F1.split("/");
    F1=partfechatemporal[1]+"/"+partfechatemporal[0]+"/"+partfechatemporal[2];

    return F1;
}
//-------------------------------------------
//------------------------------
// Corregir hora 24:00:00 y fecha en las listas ArchHora y ArchFecha
public void correccionFechaHora24() throws ParseException {// metodo activo para los formato 04 y 05
    System.out.println("INICIO CORRECCION FECHA HORA 24:00 ");
    for (int i = 0; i < archHora.size(); i++) {
        if (archHora.get(i).equals("24:00:00")) {
            archHora.set(i, "00:00"); // Asigna hora 00:00 equivalente a 24:00:00
            if (archHora.size() > i + 1) {// si aun no llega al final toma el valor de fecha del siguiente registro
                // archFecha.set(i, archFecha.get(i + 1));

                //-----------------------------------------------------------------------------------
                // CODIGO PARA CARGAR CALENDARIO Y PODER CALCULAR LA FECHA SIGUIENTE AL SUMAR +1 DIA
                String fechaEntrada=archFecha.get(i); // carga la fecha de la última línea del arreglo
                    // Date date = new Date();
                DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                Calendar calendarioAjusteFecha=Calendar.getInstance();

                Date fechaaIncrementar=new Date();
                fechaaIncrementar=dateFormat.parse(fechaEntrada);
                calendarioAjusteFecha.setTime(fechaaIncrementar);
                calendarioAjusteFecha.add(Calendar.DAY_OF_YEAR, 1);  // numero de días a añadir, o restar en caso de días<0
                fechaaIncrementar=calendarioAjusteFecha.getTime();
                archFecha.set(i, dateFormat.format(fechaaIncrementar));

                System.out.println("fecha ajustada por incremento= "+dateFormat.format(fechaaIncrementar) );
                //------------------------=====================================================

            } else {// carga el calendario e incrmenta 1 día para obtener la fecha correspondiente

        //-----------------------------------------------------------------------------------
                // CODIGO PARA CARGAR CALENDARIO Y PODER CALCULAR LA FECHA SIGUIENTE AL SUMAR +1 DIA
                String fechaEntrada=archFecha.get(i); // carga la fecha de la última línea del arreglo
                Date date = new Date();
                DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                Calendar calendarioAjusteFecha=Calendar.getInstance();

                Date fechaaIncrementar=new Date();
                fechaaIncrementar=dateFormat.parse(fechaEntrada);
                calendarioAjusteFecha.setTime(fechaaIncrementar);
                calendarioAjusteFecha.add(Calendar.DAY_OF_YEAR, 1);  // numero de días a añadir, o restar en caso de días<0
                fechaaIncrementar=calendarioAjusteFecha.getTime();

                System.out.println("fecha ajustada por incremento= "+dateFormat.format(fechaaIncrementar) );
         //------------------------=====================================================

               // archFecha.set(i, archFecha.get(i));// mantiene la fecha al no haber más registro porteriores
                archFecha.set(i, dateFormat.format(fechaaIncrementar));// mantiene la fecha al no haber más registro porteriores
                archHora.set(i, "00:00");// asigna la última hora de esae dia por no haber más registtros para definir fecha siguiente


            }
        } else { // La hora no es igual a 24:00:00

            //System.out.println("hora " + archHora.get(i) + "  | fecha " + archFecha.get(i));
            //...........................................

            //===================================================================
            // Correccion provisional al error de cronología scrip 00:00
            //    consiste en un cambio al nuevo día con hora 00:00 manteniendo la fecha del dia anterior solo en ese registro 00:00
            //===============================================================

              // if (archHora.get(i).equals("00:00")) {
              //     // CODIGO PARA CARGAR CALENDARIO Y PODER CALCULAR LA FECHA SIGUIENTE AL SUMAR +1 DIA
              //     String fechaEntrada=archFecha.get(i-1); // carga la fecha anterior a la linea
              //     // Date date = new Date();
              //     DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
              //     Calendar calendarioAjusteFecha=Calendar.getInstance();

              //     Date fechaaIncrementar=new Date();
              //     fechaaIncrementar=dateFormat.parse(fechaEntrada);
              //     calendarioAjusteFecha.setTime(fechaaIncrementar);
              //     calendarioAjusteFecha.add(Calendar.DAY_OF_YEAR, 1);  // numero de días a añadir, o restar en caso de días<0
              //     fechaaIncrementar=calendarioAjusteFecha.getTime();
              //     archFecha.set(i, dateFormat.format(fechaaIncrementar));

              //     System.out.println("Fechareal Errada "+archFecha.get(i)+" fecha propuesta ajustada por incremento= "+dateFormat.format(fechaaIncrementar) );
              //     //archHora.set(i, "00:00"); // Asigna hora 00:00 equivalente a 24:00:00
              // }

            //
            //Fin de codigo provisional de correccion ============================================================.....................


        }// fin lazo cn grupo de horas distintas a 24:00

    }
    //
}// Fin del metodo correccion FechaHora24
//---------------------------------------------------------

    //-----------------------------------------------
    public String formateoNumeroDoubleaString(Double Numero){
        Double Resultado=0.0;
        String strResultado="";
        String  ntest= String.valueOf(Numero);
        String []strDecimal =ntest.split("\\.");

        System.out.println(strDecimal.length);

        strResultado=strDecimal[0]+"."+strResultado+strDecimal[1].charAt(0);
        if(strDecimal[1].length()>1){
            strResultado=strDecimal[0]+"."+strDecimal[1].charAt(0)+strDecimal[1].charAt(1);
        }
        Resultado= Double.valueOf(strResultado);
        System.out.println("resultado= "+strResultado);

        return strResultado;
    }

    //---------------------------------------------------
//---------- metodo de conversion de formato fecha de dd/mm/YYYY a mm/dd/AAAA
    public String enroqueFecha_diaMes(String fecha){
        String F1=fecha;//Fecha Final
        String [] partfechatemporal=F1.split("/");
        F1=partfechatemporal[1]+"/"+partfechatemporal[0]+"/"+partfechatemporal[2];

        return F1;
    }
    //--------------------------
    //--------------------------
// suma los registros de la Lista recibida que coinciden con Anomalias "A"
    public Double sumadeRegistrosMarcadoAnomalo(List listaDouble){
        Double resultado=0.0;
        for(int i=0;i<archflagAnormalidad.size();i++){
            if (archflagAnormalidad.get(i).equals("A")){
                resultado= resultado+(Double) listaDouble.get(i);
            }
        }
        return resultado;
    }
//---------------------------------------------------------------------------
    // Este metodo es llamado por absArchivoFormatoXX en la lectura inicial de datos del archivo que esta procesando
public Boolean ErrorformatoDias(String dias){
    Boolean resultadoError=false;

    if(dias.length()!=2){ // Error si Mes caracteres<>2
      // resultadoError=true;
    }
    for (int i=0;i<dias.length();i++){// revisar si existe un caracter no numerico
        if(dias.charAt(i)>='0'&& dias.charAt(i)<='9'){

        }else{
            resultadoError=true;
        }
    }
    return resultadoError;
}
//-------------------------------------
// Este metodo es llamado por absArchivoFormatoXX en la lectura inicial de datos del archivo que esta procesando
public boolean Errorformatomes(String mes){
    Boolean resultadoError=false;

    if(mes.length()!=2){ // Error si Mes caracteres<>2
        //resultadoError=true;
    }
    for (int i=0;i<mes.length();i++){// revisar si existe un caracter no numerico
        if(mes.charAt(i)>='0'&& mes.charAt(i)<='9'){

        }else{
            resultadoError=true;
        }
    }
    return resultadoError;
}
//-----------------------------------------
//-------------------------------------
// Este metodo es llamado por absArchivoFormatoXX en la lectura inicial de datos del archivo que esta procesando
public boolean ErrorformatoAno(String ano){
    Boolean resultadoError=false;

    if(ano.length()!=4){ // Error si Mes caracteres<>2
        resultadoError=true;
    }
    for (int i=0;i<ano.length();i++){// revisar si existe un caracter no numerico
        if(ano.charAt(i)>='0'&& ano.charAt(i)<='9'){

        }else{
            resultadoError=true;
        }
    }
    return resultadoError;
}
//------------------
//------------CONTROL CRONOLOGICO DEL BLOQUE---------------------------------------
    public String CtrlCronologia(List listafechaMed, List listahoraMed) throws ParseException {

        boolean cronologiaCorrecta=true;
        String LineaFallaCronologia="";

        for (int i=0;i<listafechaMed.size()-1;i++){
       // ----------------------------------------------
       // Procedimiento para detectar fallas cronológicas en el archivo
                     //------------

                     //String fechaInicial="27/02/2021";
                     String fechaInicial= String.valueOf(listafechaMed.get(i));//carga los valores de la lista segun el lazo FOR
                    // String horaInicial="00:00";
                     String horaInicial= String.valueOf(listahoraMed.get(i));
                     String fechaSiguiente= String.valueOf(listafechaMed.get(i+1));
                     String horaSiguiente= String.valueOf(listahoraMed.get(i+1));

                     /// ---------- conversion minutos
                     String[]  horaInicialInt=horaInicial.split(":");
                     double horaInicialNumerica = Integer.parseInt(horaInicialInt[0])*60 + Integer.parseInt(horaInicialInt[1]);
                     String[]  horaFinalInt=horaSiguiente.split(":");
                     double horaFinalNumerica = Integer.parseInt(horaFinalInt[0])*60 + Integer.parseInt(horaFinalInt[1]);

                   //  System.out.println("hora entera "+horaInicialNumerica + "  "+horaFinalNumerica);

                     //Date date = new Date();
                     DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                                        //  String temporal;
                     Date fechaInicialMuestras= new Date();// Asigna tipo e Inicializa la variable fechaInicialMuestras
                     try {
                         fechaInicialMuestras=dateFormat.parse(fechaInicial);

                     } catch (ParseException e) {
                         JOptionPane.showMessageDialog(null, "Se encontró un error de formato en la 'fecha' corrija el archivo : ");
                         System.exit(0);
                         e.printStackTrace();
                     }
                     long nroDias;
                                          //int indiceTemp=this.archFecha.size()-1;
                     int indiceTemp=1;
                                        // temporal= this.archFecha.get((indiceTemp));
                                        // temporal=fechaSiguiente;
                     Date fechaFinalMuestras=dateFormat.parse(fechaSiguiente);
                     Date nuevaFecha=dateFormat.parse("01/00/00");

                     //---------------------------------------------------------------------
                     // sumar o restar un día a la fecha base ------------------------------
                     Calendar calendar = Calendar.getInstance();
                     calendar.setTime(fechaFinalMuestras); // Configuramos la fecha que se recibe
                     calendar.add(Calendar.DAY_OF_YEAR, 0);  // numero de días a añadir, o restar en caso de días<0
                     fechaFinalMuestras=calendar.getTime();
                     //----------------------------------------------------------------------_____________________
                     nroDias = ((fechaFinalMuestras.getTime() - fechaInicialMuestras.getTime()) / 86400000);

                   //  if (fechaFinalMuestras.getTime() - fechaInicialMuestras.getTime()>=0 && horaFinalNumerica-horaInicialNumerica>0) {
                     if ( (nroDias==0 && horaFinalNumerica-horaInicialNumerica>0) ||
                             (nroDias>=1)){
                           //  System.out.println("cronologia CORRECTA= "+cronologiaCorrecta+" dias= "+nroDias +" ("+fechaInicial+" "+fechaSiguiente+") -->"+
                           //  "   fechaIni "+dateFormat.format(fechaInicialMuestras)+ "   fechaFin "+ dateFormat.format(fechaFinalMuestras)+
                           //  "   HoraIni "+listahoraMed.get(i)+"  " +listahoraMed.get(i+1)+ "  lineafalla "+
                           //  "   HoraIni "+horaInicialNumerica+"  " +horaFinalNumerica+ "  lineafalla "+i);

                     }else{
                             //cronologiaCorrecta=false;// error en cronología
                             cronologiaCorrecta=false;
                             LineaFallaCronologia= String.valueOf(i+3);
                             System.out.println("Cronologia ERRADA  "+ cronologiaCorrecta+ " dias= "+nroDias+" ("+fechaInicial+" "+fechaSiguiente+") -->"+
                                    "   fechaIni "+dateFormat.format(fechaInicialMuestras)+ "   fechaFin "+dateFormat.format(fechaFinalMuestras)+
                                   "   HoraIni "+listahoraMed.get(i)+" HoraIni " +listahoraMed.get(i+1)+ "  lineafalla "+i);
                                  // "   HoraIni "+horaInicialNumerica+"  " +horaFinalNumerica+ "  lineafalla "+i+ extraeNombreArchivoBaseSinrutaNiextension(nombArch));
                             break;
                     } ;

   // fin del procedimiento falla cronologica ---------------------------------------------------------
             }// fin del lazo FORbb
      //  System.out.println("retorno Crtl resultado = "+cronologiaCorrecta);
    //return cronologiaCorrecta;
        return LineaFallaCronologia; // retorna el número de línea donde identificó el error
    }
}