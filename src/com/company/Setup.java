package com.company;

import javax.swing.*;
import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Setup {

    // Inicializacion de las variables donde se carga la configuracion asociada al archivo ConfigMed.txt
    //_______________________________________________________________________________________________________
    public String CnombProyecto="";
    public  String ClugarProyecto="";
  //  public String CdiasPermanencia="";
    public int CdiasPermanencia=0;
 //   public String CdiasMinValidez="";
    public int CdiasMinValidez=0;
    public int    CnroEmpresasParticipantes=0;
    public int Ccadencia=0;
    public List<String> CinicialesNombEmpresas=new ArrayList(); // N S A L inicial de las empresas
    public int CnroTarifas=0;
    public List<String> CnombTarifas=new  ArrayList();// inicial tipo de tarifa
    public List<String> CestratosList=new  ArrayList();// inicial tipo de tarifa
    public  int Cestratos=0;
    public  int CnroAnosdelaCampana=0;
    public List<String> CDigitoAnoValido=new  ArrayList();
    public String pathConfigMed=System.getProperty("user.dir");
    public  String archConfigMed="configMed.txt";
    public List<String> ChabilitacionChequeo=new ArrayList();
    public int CnroAplicacionEspecial=0;   // identifica la aplicacion especial que se activará  "0" PBA , "1" aplicacion para San Juan
    public List<String> btnHabilitacionFormato=new ArrayList<>(); // linea 11: indica el formato que debe ser habilitado como boton
    public int habilitaGenerarArchEstand=1; // Linea 12: Indica si al leer el archivo de mediciones se crearan los _N.txt estandar
    //public int btnHabIntegrador=1;// Linea 13: Habilita el boton para realizar integración de mediciones estandarizadas.
    public List<String> btnHabIntegrador=new ArrayList<>();

    public String codigoApp=""; // Línea 14 código de habilitación de la APP
    public List<String> calificacionesPermitidas=new ArrayList<>();// Aqui se copian las calificaciones del Setup que son aceptadas

    // Setter and Getters______________________________**************************************************************

    public String getCnombProyecto() {
        return CnombProyecto;
    }

    public void setCnombProyecto(String cnombProyecto) {
        CnombProyecto = cnombProyecto;
    }

    public String getClugarProyecto() {
        return ClugarProyecto;
    }

    public void setClugarProyecto(String clugarProyecto) {
        ClugarProyecto = clugarProyecto;
    }

    public String getCdiasPermanencia() {
        return String.valueOf(CdiasPermanencia);
    }

    public void setCdiasPermanencia(String cdiasPermanencia) {
        CdiasPermanencia = Integer.parseInt(cdiasPermanencia);
    }

    public int getCdiasMinValidez() {
        return CdiasMinValidez;
    }

    public void setCdiasMinValidez(int cdiasMinValidez) {
        CdiasMinValidez = cdiasMinValidez;
    }

    public int getCnroEmpresasParticipantes() {
        return CnroEmpresasParticipantes;
    }

    public void setCnroEmpresasParticipantes(int cnroEmpresasParticipantes) {
        CnroEmpresasParticipantes = cnroEmpresasParticipantes;
    }

    public List<String> getCinicialesNombEmpresas() {
        return CinicialesNombEmpresas;
    }

    public void setCinicialesNombEmpresas(List<String> cinicialesNombEmpresas) {
        CinicialesNombEmpresas = cinicialesNombEmpresas;
    }

    public int getCnroTarifas() {
        return CnroTarifas;
    }

    public void setCnroTarifas(int cnroTarifas) {
        CnroTarifas = cnroTarifas;
    }

    public List<String> getCnombTarifas() {
        return CnombTarifas;
    }

    public void setCnombTarifas(List<String> cnombTarifas) {
        CnombTarifas = cnombTarifas;
    }

    public int getCestratos() {
        return Cestratos;
    }

    public void setCestratos(int cestratos) {
        Cestratos = cestratos;
    }

    public int getCnroAnosdelaCampana() {
        return CnroAnosdelaCampana;
    }

    public void setCnroAnosdelaCampana(int cnroAnosdelaCampana) {
        CnroAnosdelaCampana = cnroAnosdelaCampana;
    }

    public List<String> getCDigitoAnoValido() {
        return CDigitoAnoValido;
    }

    public void setCDigitoAnoValido(List<String> CDigitoAnoValido) {
        this.CDigitoAnoValido = CDigitoAnoValido;
    }

    public String getPathConfigMed() {
        return pathConfigMed;
    }

    public void setPathConfigMed(String pathConfigMed) {
        this.pathConfigMed = pathConfigMed;
    }

    public String getArchConfigMed() {
        return archConfigMed;
    }

    public void setArchConfigMed(String archConfigMed) {
        this.archConfigMed = archConfigMed;
    }

    public List<String> getChabilitacionChequeo() {
        return ChabilitacionChequeo;
    }

    public void setChabilitacionChequeo(List<String> chabilitacionChequeo) {
        ChabilitacionChequeo = chabilitacionChequeo;
    }

    public int getCnroAplicacionEspecial() {
        return CnroAplicacionEspecial;
    }

    public void setCnroAplicacionEspecial(int cnroAplicacionEspecial) {
        CnroAplicacionEspecial = cnroAplicacionEspecial;
    }

    public void setCdiasPermanencia(int cdiasPermanencia) {
        CdiasPermanencia = cdiasPermanencia;
    }


    public int getCcadencia() {
        return Ccadencia;
    }

    public void setCcadencia(int ccadencia) {
        Ccadencia = ccadencia;
    }

    public List<String> getBtnHabilitacionFormato() {
        return btnHabilitacionFormato;
    }

    public void setBtnHabilitacionFormato(List<String> btnHabilitacionFormato) {
        this.btnHabilitacionFormato = btnHabilitacionFormato;
    }

    public int getHabilitaGenerarArchEstand() {
        return habilitaGenerarArchEstand;
    }

    public void setHabilitaGenerarArchEstand(int habilitaGenerarArchEstand) {
        this.habilitaGenerarArchEstand = habilitaGenerarArchEstand;
    }

    public List<String> getBtnHabIntegrador() {
        return btnHabIntegrador;
    }

    public void setBtnHabIntegrador(List<String> btnHabIntegrador) {
        this.btnHabIntegrador = btnHabIntegrador;
    }

    public String getCodigoApp() {
        return codigoApp;
    }

    public void setCodigoApp(String codigoApp) {
        this.codigoApp = codigoApp;
    }

    public List<String> getCalificacionesPermitidas() {
        return calificacionesPermitidas;
    }

    public void setCalificacionesPermitidas(List<String> calificacionesPermitidas) {
        this.calificacionesPermitidas = calificacionesPermitidas;
    }

    public List<String> getCestratosList() {
        return CestratosList;
    }

    public void setCestratosList(List<String> cestratosList) {
        CestratosList = cestratosList;
    }

// Fin of setter and getter________________________***************************************************************




    //Fin de variables de configuracion inicial _______________________________________________



    //METODO DE INICIALIZACION PARA POBLAR LAS VARIABLES de ARRIBA CON DATOS DEL ARCHIVO ConfigMed

    public void inicializaConfiguracionBaseTxt () throws IOException {
        // Esta seccion carga las variables de umbral inicial desde el archivo ConfigMed.TXT
        // XX__ Configurador inicial de la aplcación________________________________________________________________________________

        //public int cargarConfiguracion(String path, String nombArch) throws IOException {

        //CARGAR CONFIGURACION DEL ARCHIVO  ConfigMed.TXT

        //File cargaConfigMed= new File(pathConfigMed);



        //----------------------------------

        FileReader configReader = null;
        try {
            configReader = new FileReader(archConfigMed);

        } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, "No encuentra el archivo: 'ConfigMed.txt', revise configuración y presencia del archivo" );
        }

        int numLinesConfigMed = 0;
        int lineasEncabezadoArchivo = 10; // valor fijo de las lineas descriptivas del archivo ConfigMed.TXT
        //try {
        BufferedReader buffer = new BufferedReader(configReader);
        String blinea;

        int indicadorPosic = 0;
        while ((blinea = buffer.readLine()) != null) {
            //******** lineas para ller la configuracion y cargar en las variables
            int numMuestra = indicadorPosic - lineasEncabezadoArchivo + 1;// va indicando el numero de la linea para cada  muestra
            //System.out.println("line  en registro de prueba "+ blinea);
            indicadorPosic = indicadorPosic + 1;
            String[] parts = blinea.split("\\|");
            // swich de bifurcacion
            switch (indicadorPosic) {
                case 0:
                    break;
                case 1:
                    CnombProyecto = parts[0];
                    break;
                case 2:
                    ClugarProyecto = parts[0];
                    break;

                case 3:// umbrales de la medición
                    CdiasPermanencia = Integer.parseInt((parts[0]));
                    CdiasMinValidez = Integer.parseInt((parts[1]));
                    CnroEmpresasParticipantes = Integer.parseInt((parts[2]));
                    Ccadencia= Integer.parseInt(parts[3]);
                   // System.out.println("valor cadencia"+Ccadencia);
                    break;
                case 4:// inicial de las empresas
                   // for (int i = 0; i < CnroEmpresasParticipantes; i++) {
                    for (int i = 0; i < parts.length; i++) {
                        CinicialesNombEmpresas.add(parts[i]);
                    }
                    break;
                case 5: // Tarifas existentes
                    CnroTarifas = Integer.parseInt((parts[0]));
                    break;
                case 6: // caracter inicial de cada tarifa activa
                    for (int i = 0; i < parts.length; i++) {
                        CnombTarifas.add(parts[i]);
                        //System.out.println("numero iteracion "+i);
                    }
                    break;
                case 7: // numero de estratos
                    for (int i = 0; i < parts.length; i++) {
                        CestratosList.add(parts[i]);
                        System.out.println("EstratosSetup = "+parts[i]);
                    }
                    Cestratos = getCestratosList().size();

                    break;
                case 8: // carga último digito de cada año valido de l Campaña
                    for (int i = 0; i < parts.length; i++) {
                        CDigitoAnoValido.add(parts[i]);
                    }
                    CnroAnosdelaCampana = parts.length;
                    System.out.println("xxxxa años de a campaña "+getCnroAnosdelaCampana());
                    break;
                case 9: // habilitacion de las rutinas de calificacion
                    for (int i = 0; i < parts.length; i++) { // carga las indicadas en la linea 9
                        ChabilitacionChequeo.add(parts[i]);
                    }
                    break;
                case 10:
                    CnroAplicacionEspecial = Integer.parseInt((parts[0]));
                    break;
                case 11: // Habilita el boton de los formatos que van a procesar
                    int nroFormatosaLeer = 8;  // cantidad de formatos a ser leidos

                    for (int i = 0; i < parts.length; i++) {// los existentes en la linea 11
                        btnHabilitacionFormato.add(parts[i]);
                    }
                    break;
                case 12:
                    habilitaGenerarArchEstand = Integer.parseInt(parts[0]); // habilita la generacion de los archivos _N.TXT  estandar
                    break;
                case 13:
                    int opcionesBtnHabIntegrador = 8;

                    for (int i = 0; i < parts.length; i++) {// carga la cantidad de opciones indicadas en linea 13
                        btnHabIntegrador.add(parts[i]);
                        System.out.println("Opciones compilador = "+parts[i]);
                    }

                    //btnHabIntegrador = Integer.parseInt(parts[0]);// Habilita el boton para correr la aplicaación que compila un grupo de archivos estandaarizados
                    break;
                case 14:
                    codigoApp =parts[0];
                    System.out.println("Privilegios de la versión: "+equivCodigoHash(codigoApp));
                    break;
                case 15:

                    for (int i = 0; i < parts.length; i++) {
                        calificacionesPermitidas.add(parts[i]);
                        System.out.println("Calificaciones permitidas = "+parts[i]);
                    }
                    break;
            }



            //}
            // }
            //*************************************************************
        }

        // System.out.print("Nombre "+CnombProyecto+ " Lugar "+ClugarProyecto+ " Nro dias colocacion "+CdiasPermanencia + " tmin mediciones  "
        //         +CdiasMinValidez+" empresas "+CnroEmpresasParticipantes+"\n");
        // for (int n=0;n<CnroEmpresasParticipantes;n++){
        //     System.out.println("Empresa" +(n+1)+" = "+CinicialesNombEmpresas.get(n));
        // }
        //for (int i=0;i<CnroTarifas;i++){
        //    System.out.println("Tarifa "+i+" ="+CnombTarifas+" tarifas cargadas "+CnombTarifas.size());

        //}
        //for (int i=0;i<CnroAnosdelaCampana;i++){
        //    System.out.println("Año "+i+" ="+CDigitoAnoValido.get(i));

        //}
        //for (int i=0;i<ChabilitacionChequeo.size();i++){
        //   System.out.println("habilitacion "+i+" "+ChabilitacionChequeo.get(i));

        //}
        //______________ FIN de manejo en las configuraciones iniciales//
        //System.out.println("resultado del metodo======"+estadoControlHabilitado("A"));
        //System.out.println("resultado del metodo======"+estadoControlHabilitado("B"));
        // System.out.println("resultado del metodo======"+estadoControlHabilitado("C"));
        // System.out.println("resultado del metodo======"+estadoControlHabilitado("D"));
        //  System.out.println("resultado del metodo======"+estadoControlHabilitado("E"));
        //  System.out.println("resultado del metodo======"+estadoControlHabilitado("F"));
        //  System.out.println("resultado del metodo======"+estadoControlHabilitado("G"));
        // System.out.println("resultado del metodo======"+estadoControlHabilitado("H"));

    }

    //****************************************************************************************************
    // metodo que concatena la configuracion y la carga en una variable String

    public String  stringConfiguracion() throws IOException {

        //-------------------------------------------------
        // se instancia la clase versionamiento para acceder a la historia y número de versión actual
        Versionamiento versionamiento = new Versionamiento(); // queda instanciada la clase y acceso a la version actual
        //---------------------------------------

        String versionApp = versionamiento.versionActual(); // esta version es la que aparece al desplegar la configuracion
        String sConfigMed =""; // Variable String donde se cargara la configuracion
        //this.inicializaConfiguracionBaseTxt(); // corre el metodo que lee la configuracion del archivo
       // sConfigMed = CnombProyecto + "-" + ClugarProyecto + "-" + CdiasPermanencia + CdiasMinValidez +
         //       CnroEmpresasParticipantes + Ccadencia + "-";
        sConfigMed = versionApp+" | "+CdiasPermanencia + CdiasMinValidez +
                CnroEmpresasParticipantes + Ccadencia + "-";

        //---------------

        for (int n = 0; n < CinicialesNombEmpresas.size(); n++) { // Carga las iniales de cada una de las empresas
            sConfigMed = sConfigMed + CinicialesNombEmpresas.get(n);
        }
        sConfigMed = sConfigMed + "-";

        //sConfigMed = sConfigMed + CnroTarifas;   // carga el numero de tarifas definidas
        for (int n = 0; n < CnombTarifas.size(); n++) { //linea 6: Carga las iniales de cada tarifa definida
            sConfigMed = sConfigMed + CnombTarifas.get(n);
        }
        sConfigMed = sConfigMed + "-"; //
        for (int n = 0; n < CestratosList.size(); n++) {//Linea 7: lista de estratos o UPR indicados en nombre
            sConfigMed = sConfigMed + CestratosList.get(n) ;// carga Nro estratos + Nro años que dura la campaña
        }

        sConfigMed = sConfigMed + "-";
        for (int d = 0; d < CDigitoAnoValido.size(); d++) { //Linea 8: carga ultimo digito año valido de la campaña
            sConfigMed = sConfigMed + CDigitoAnoValido.get(d);
        }
        sConfigMed = sConfigMed + "-";

        for (int h = 0; h < ChabilitacionChequeo.size(); h++) {//Linea: 9 Indica las rutinas de control habilitadas
            sConfigMed = sConfigMed + ChabilitacionChequeo.get(h);
        }

        sConfigMed=sConfigMed+"-"+CnroAplicacionEspecial; //Linea 10: carga el numero de aplicacion habilitada
        sConfigMed=sConfigMed+"-";


        for (int h = 0; h < btnHabilitacionFormato.size(); h++) {//Linea 11:Indica cuales formatos serán habilitados
            sConfigMed = sConfigMed + btnHabilitacionFormato.get(h);
        }
        sConfigMed = sConfigMed + "-";

        sConfigMed = sConfigMed + habilitaGenerarArchEstand;//Linea 12: Habilitacion generacion archivos estándar
        sConfigMed = sConfigMed + "-";

        for (int h = 0; h < btnHabIntegrador.size(); h++) {//Linea 13: Habilitacion opciones Compilador
            sConfigMed = sConfigMed + btnHabIntegrador.get(h);
        }
        sConfigMed = sConfigMed + "-" + equivCodigoHash(codigoApp);// Linea 14: Traducción nivel de acceso Codigo Hash

        System.out.println("Nivel de licencia: "+equivCodigoHash(codigoApp)+ "  codigo Hash: "+codigoApp);
        System.out.println("nivel1="+getMd5("Nivel1|2020"));
        System.out.println("nivel2="+getMd5("Nivel2|2020"));
        System.out.println("nivel3="+getMd5("Nivel3|2020"));

        sConfigMed = sConfigMed + "-" +calificacionesPermitidas ; // Linea 15: Codigos calificados para ser compilados

        int controlesHabilitados=0;
        int formatoshabilitados=0;

        for (int i=0;i<ChabilitacionChequeo.size();i++){// cuenta los 1 de la línea 9
            if(ChabilitacionChequeo.get(i).equals("1")){
                controlesHabilitados=controlesHabilitados+1;
            }

        }
        for (int i=0;i<btnHabilitacionFormato.size();i++){// cuenta los 1 de la línea 9
            if(btnHabilitacionFormato.get(i).equals("1")){
                formatoshabilitados=formatoshabilitados+1;
            }

        }
        System.out.println("Controles habilitados  " + controlesHabilitados + "   numero de formatos btnHab..=" + formatoshabilitados);

        return (sConfigMed);

    }


    //*****************************************************************************************************


    public void mostrarConfiguracionCargada(){ // DESPLIEGA POR CONSOLA LAS PRUEBAS HABILITADAS
        String auxVarBitHabilitacion;
        char charBitdeHabilitacion;


        if (CnroAplicacionEspecial==1){
            System.out.println("Habilitada la configuracion especial de San Juan " +CnroAplicacionEspecial);

        }else{
            System.out.println("Activada la configuracion 0, estudio de PBAires "+CnroAplicacionEspecial);
        }

        for (int i=0;i<this.ChabilitacionChequeo.size();i++) {
            //System.out.println("Valor del Byte de habilitacion "+BitHabilitacionChequeo.get(i));
            auxVarBitHabilitacion= (String) ChabilitacionChequeo.get(i);
            charBitdeHabilitacion=auxVarBitHabilitacion.charAt(0);
            switch (i) {  // Derivacion de los casos que se habilitaran según el archivo de configuración
                case 0:
                    if (charBitdeHabilitacion=='1' ){System.out.println("Habilitada prueba A");
                    }else
                    {
                        System.out.println("Des habilitada prueba A");
                    }

                    break;
                case 1:
                    if (charBitdeHabilitacion=='1'){System.out.println("Habilitada prueba B");
                    }else
                    {
                        System.out.println("Des habilitada prueba B");
                    }
                    break;
                case 2:
                    if (charBitdeHabilitacion=='1'){System.out.println("Habilitada prueba C");
                    }else
                    {
                         System.out.println("Des habilitada prueba C");
                    }
                    break;
                case 3:
                    if (charBitdeHabilitacion=='1'){System.out.println("Habilitada prueba D");
                    }else
                    {
                         System.out.println("Des habilitada prueba D");
                    }
                    break;
                case 4:
                    if (charBitdeHabilitacion=='1'){System.out.println("Habilitada prueba E")
                        ;}else
                    {
                        System.out.println("Des habilitada prueba E");
                    }
                    break;
                case 5:
                    if (charBitdeHabilitacion=='1'){ System.out.println("Habilitada prueba F");
                    }else
                    {
                        System.out.println("Des habilitada prueba F");
                    }
                    break;
                case 6:
                    if (charBitdeHabilitacion=='1'){System.out.println("Habilitada prueba G");
                    }else
                    {
                         System.out.println("Des habilitada prueba G");
                    }
                    break;
                case 7:
                    if (charBitdeHabilitacion=='1'){System.out.println("Habilitada prueba H");
                    }else {
                        System.out.println("Des habilitada prueba H");
                    }

                    break;
                case 8:
                    if (charBitdeHabilitacion=='1'){System.out.println("Habilitada prueba I");
                    }else {
                        System.out.println("Des habilitada prueba I");
                    }

                    break;
                  }

        }
        System.out.println("Formatos que se activan "+btnHabilitacionFormato);


    }

    public int cantidadFormatoxActivarse(){
        int resultado=0;
        for(int i=0;i<btnHabilitacionFormato.size();i++) {
            resultado= resultado+Integer.parseInt(btnHabilitacionFormato.get(i));
        }
        return resultado;
    }

    public String estadoControlHabilitado(String control){
        String resultado="";
        int c=0;
        List<String>confgControl=new ArrayList<>();
        confgControl.add("A");
        confgControl.add("B");
        confgControl.add("C");
        confgControl.add("D");
        confgControl.add("E");
        confgControl.add("F");
        confgControl.add("G");
        confgControl.add("H");
        confgControl.add("I");
        for (int i=0;i<getChabilitacionChequeo().size();i++) {// estan configurados 9 controles
                if(confgControl.get(i)==control){
                    c=i;
                    break;
                }
        }
        if (getChabilitacionChequeo().get(c).equals("1")){
           // System.out.println("Prueba del valor "+ control+"  c="+confgControl.get(c)+ " c"+c+ "habilitacion= "+getChabilitacionChequeo().get(c));
            resultado= control+"- (Activo)";
        }else{
           // System.out.println("Prueba del valor "+ control+"  c="+confgControl.get(c)+ " c"+c+ "DES-HABILITADO= "+getChabilitacionChequeo().get(c));
            resultado= control +" - (------)";
        }
            //System.out.println("Prueba del valor "+ control+"  c="+confgControl.get(c)+ " c"+c+ "habilitacion= "+getChabilitacionChequeo().get(c));
        return resultado;
    }

    // Método para el obtener el Hash
    public static String getMd5(String input) {
        try {

            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            //  of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String equivCodigoHash(String codHash){
        String codEquivalente="";
        if (codHash.equals(getMd5("Nivel1|2020"))){
            codEquivalente="Nivel-1";
        }else{
            if (codHash.equals(getMd5("Nivel2|2020"))){
                codEquivalente="Nivel-2";
            }else{
                if (codHash.equals(getMd5("Nivel3|2020"))){
                    codEquivalente="Nivel-3";
                }else{
                    codEquivalente=" Revisar Autorizacion.!!!";
                }
            }


        }
       return codEquivalente;
    }
}
