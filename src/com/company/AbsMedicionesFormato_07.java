package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;

public class AbsMedicionesFormato_07 extends AbsMediciones {
    public JFrame framePrincipal;

    // Getters and Setters
    @Override
    public JFrame getFramePrincipal() {
        return framePrincipal;
    }

    @Override
    public void setFramePrincipal(JFrame framePrincipal) {
        this.framePrincipal = framePrincipal;
    }
    // Fin G & S------------------
    // Constructor
    public AbsMedicionesFormato_07(String tipodeFormatoaLeer, JFrame framePrincipal) {
        super(tipodeFormatoaLeer);
        this.framePrincipal = framePrincipal;
    }

    //-----------------------------------------------
    public  void PrincipalDevMediciones() throws IOException, ParseException { // Aplicacion principal para el chequeo de las mediciones
        System.out.println("Inicio de PrincipalDevMediciones de Formato 07 ");
        //::: Proceso 1: Lee configuracion y define directorio de trabajo
        //**** ********** *********
        setup.inicializaConfiguracionBaseTxt(); // carga los atributos desde el archivo del configuracion
        //System.out.println("Confguracion cargad: "+ setup.stringConfiguracion()); // salida por consola de la configuracion

        //*******************************************************************************
        inicializaIoArchivos(); // esta rutina inicializa los archivos de entrada y salida además de instanciar Setup


        //  ******************  PROCESO LISTAR DIRECTORIO DE ARCHIVOS
        //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
        //-- Lista de Palabras de referencia que se identifican en cada tipo de archivo
        String palabraFormato1L1="";
        String palabraFormato2L1="";
        String palabraFormato3L1="";
        String palabraFormato4L1="";
        String palabraFormato5L1="";
        String palabraFormato6L1="";
        String palabraFormato7L1="nombreArchivo";
        String palabraFormato8L1="meterId";
        String palabraFormato9L1="";
        String palabraFormato10L1="";
        String palabraFormato11L1="";
        String palabraFormato12L1="";

        // De existir coincidencia en el formato y nombre de la clase AbsArchivo + formato
        //instanciarClsAbsArchivo();
        int longitudDigitosNombreArchivo=12;
        AbsArchivoFormato_07 absArchivo=new AbsArchivoFormato_07(palabraFormato7L1,0,getDirectorio(),tipodeFormatoaLeer,longitudDigitosNombreArchivo) ;// Define el formato a ser leido -->Instancia la clase Archivo e inicializa numerolinea con cero>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><

        System.out.println(" Formato_07 ------------------------>>>> el directorio definido que va a a clase Achivo es "+directorio);
        System.out.println("la palabra de referencia en la ClsArch.. es: "+absArchivo.getPrimeraPalabraValidaArchivo());

        //**** ********** *********
        //setup.inicializaConfiguracionBaseTxt(); // carga los atributos desde el archivo del configuracion
        //  System.out.println("Confguracion cargad: "+ setup.stringConfiguracion()); // salida por consola de la configuracion

        //*******************************************************************************




        // inicializaIoArchivos(); // esta rutina inicializa los archivos de entrada y salida además de instanciar Setup

        armaENcabezadoArchReporte(); // Arma encabezados en los archivos: ReporteDetalle.txt y Reporte.txt

        String nombreArchivo=""; //// Inicializa variable
        int numlineas=0;// variable de uso local
        String [] listaArchivos =new String[0];
        xlistaArchivos =new ArrayList<>();// crea el arreglo tipo lista para contener archivos validos a procesar


        xlistaArchivos=absArchivo.xlistarDirectorio(directorio,  setup.CnroAplicacionEspecial,longitudDigitosNombreArchivo);//Extrae lista de archivos validos en el directorio  a procesar metodo listar directorios

        //----------------------------------------------------------------------------------------------
        if(xlistaArchivos.size()==0){ // activa el frame padre si no hay archivos que procesar
            framePrincipal.setVisible(true);
        }



        //---------------------------------------------
        //Identifica la longitud máxima de caracteres en las lista de nombre de archivos
        longitudMaxdeNombrexListaArchivos= absArchivo.extraeNombreArchivoBaseSinrutaNiextension(xlistaArchivos.get(0)).length();
        for(int i=0;i<xlistaArchivos.size();i++){

            int temp= (absArchivo.extraeNombreArchivoBaseSinrutaNiextension(xlistaArchivos.get(i)).length());
            if(longitudMaxdeNombrexListaArchivos<temp){
                longitudMaxdeNombrexListaArchivos=temp;
                System.out.println("longitud nombre "+ xlistaArchivos.get(i)+ " | "+longitudMaxdeNombrexListaArchivos+" |temp"+temp);
            }
        }
        System.out.println("longitud maxima caracteres en nombre de archivo = "+longitudMaxdeNombrexListaArchivos);

        //------------------------------------------------------------------------------------------------

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
        frame.setSize(720,160);              // Coloca el tamaño de la barra de progreso

        ImageIcon logoBaes=new ImageIcon("img\\logo_pie.jpg");
        frame.setIconImage(logoBaes.getImage().getScaledInstance(100,80,Image.SCALE_SMOOTH));

        frame.setLocationRelativeTo(null);
        frame.add(progressBar,0,0);
        progressBar.setValue(3);
        frame.setVisible(true);



        //***********************************************************************

        //xyz Proceso Compilacion se requiere el llamado a la rutina de carga del archivo de usuarios
        // este metodo esta gobernado por el btnHabIntegrador de la clase Setup
        int varHabilitaCompilador= Integer.parseInt(setup.getBtnHabIntegrador().get(0));
        if(varHabilitaCompilador==1) {
            System.out.println("HABILITADA COMPILACION DE ARCHIVOS");
            absArchivo.principalCompilacionArchivos(directorio);// Metodo
        }


        //************************************************************************************************************               ****   *****
        lazoprincipal2(absArchivo,nombreArchivo,numlineas); // Rutina complemento del lazo principal

        System.out.println("regreso del Lazo 2");
        // >>>>*******************<< LAZO PARA BARRER TODOS LOS ARCHIVOS DETECTADOS EN EL DIRECTORIO**************     ********                     ******


        //*****************


        // FIN DE PROCESO DE LOS ARCHIVOS LISTADOS EN EL DIRECTORIO
        //***************************************************************************************************
        // ***  Este metodo vacia el buffer de los resultados en el bufferdearchivo de reporte <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        escribeResultadosenBufferReporte(absArchivo);//

        completarBarraProgreso();// Este método asegura llevar la barra de progreso hasta el 100%
        //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        // System.out.println(" linea 335 valor barra "+ progressBar.getValue()+" "+progress);
        //       // progressBar.setValue(progress);
        //       // progress=progressBar.getValue();

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
                    frame.dispose();
                    framePrincipal.setVisible(true);
                    int resp = JOptionPane.showConfirmDialog(null, "¿Quiere revisar el resultado de la calificacion?", "Alerta!", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
                    if(resp==JOptionPane.YES_OPTION){
                        try {
                            presentarResultadosCalificacion(tipodeFormatoaLeer,directorio);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
                @Override
                public void mouseExited(MouseEvent mouseEvent) {
                    //frame.dispose();
                }            });
        }
    } //********      FIN PRINCIPAL MEDICIONES  ---------------------------------------------------------------------






    // **** PROCESO vaciado del buffer con resultados del procesamiento de los archivos
    public void escribeResultadosenBufferReporte(AbsArchivoFormato_07 absArchivo) throws IOException {
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

        // El siguiente condicional inhibe creación de archivo MedIntegrada
        int varHabilitaCompilador= Integer.parseInt(setup.getBtnHabIntegrador().get(0));// BTN habiltacion compilacion en SETUP
        if (varHabilitaCompilador==1) {
            printMedIntegradas.close();// xyz cierre de los archivos
            bWMedIntegradas.close();
        }

    }

    // **** Rutina lazoprincipal2
    public void lazoprincipal2(AbsArchivoFormato_07 absArchivo, String nombreArchivo, int numlineas) throws IOException, ParseException {
        boolean flagFaltaEncabezadoArchivo=true;
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
            if ((setup.CnroAplicacionEspecial == 0) && (tipodeFormatoaLeer.equals("FORMATO_02"))) { // no hay caso especial y se habilita el metodo segun FORMATO XX

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
                absArchivo.generarTokensFormato_07(path,nombreArchivo,numlineas);
            }
            if ((setup.CnroAplicacionEspecial == 0) && (tipodeFormatoaLeer.equals("FORMATO_08"))) { // no hay caso especial y se habilita el metodo segun FORMATO XX
                System.out.println("<< ESTA ES LA DERIVACION FORMATO 08 ------------------");
            }
// TRATAMIENTO DE CASOS ESPECIALES  en Setup CnroAplicacionEspecial=1 que era san juan

            if ((setup.CnroAplicacionEspecial == 1)) { // no hay caso especial y se habilita el metodo segun FORMATO XX
                // System.out.println("Inicio del proceso de casos especial Nro. "+setup.CnroAplicacionEspecial);
                absArchivo.generarTokensEspecialUno(path, nombreArchivo, numlineas); // llama al metodo para leer las muestras del archivo ESPECIAL

                // CASO numero 1 Provincia de San Juan
                //---------------------------------------
            }

            //********   ESCRIBE EN EL ARCHIVO DE RESULTADOS  LA LEYENDA DEL ENCABEZADO   **********
            String partesNombArchString = nombreArchivo; // Estas dos lineas extraen el prefijo del directorio de trabajo
            String[] parteDelNombre = partesNombArchString.split("\\\\"); // que conforma el nombre de archivos analizado
            try {
                //String calificacionArchivoMedicion="";
                String calificacionArchivoMedicion=absArchivo.calificarMedicionM(nombreArchivo,   //   !!!!!!!!  LLama al metodo de calificacion de
                      setup.CinicialesNombEmpresas,
                    setup.CdiasPermanencia, setup.CdiasMinValidez,
                       setup.CDigitoAnoValido, setup.ChabilitacionChequeo, setup.Ccadencia,setup.getCestratosList(),setup.getCnombTarifas());

                // ajusta calificacion a descplegar
                String tempMensajeCalificacion="Normal";
                if (calificacionArchivoMedicion.equals("")){

                }else{
                    tempMensajeCalificacion=calificacionArchivoMedicion;// se asigna el valor que viene de la calificacion
                }

                listaReporteDetalle.add(" NOMBRE DEL ARCHIVO:      " +tab()+parteDelNombre[parteDelNombre.length - 1].toUpperCase() + sbCrLf());
                listaReporteDetalle.add(" Calificación:             " +tab()+ tab()+tempMensajeCalificacion+ sbCrLf());
                listaReporteDetalle.add(" Tipo de registro:          " + tab()+tab()+ absArchivo.tipoArchivo + sbCrLf());
                listaReporteDetalle.add(" Fecha real inicio de medición: " + tab()+ absArchivo.archFecha.get(0) + " " + absArchivo.archHora.get(0) + sbCrLf());
                listaReporteDetalle.add(" Fecha real Final de medición:  " + tab()+ absArchivo.archFecha.get(absArchivo.archFecha.size() - 1) +
                        " " + absArchivo.archHora.get(absArchivo.archHora.size() - 1) + sbCrLf());

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error de asignación por Formato");
                JOptionPane.showMessageDialog(null, "Se encontró un error de asignacion de valores con el archivo: " + nombreArchivo);
                System.exit(0);
            }
            System.out.println("inicio encabezado xx00");
            double redondeaEnergia =absArchivo.redondeaDouble2decimales(absArchivo.archTotalEnergia);// Redondeando decimales de Energia  a dos
            double redondeaEnergiaTotalReactiva = absArchivo.redondeaDouble2decimales(absArchivo.archTotalEnergiaReactiva);// Redondeando decimales de Energia  a dos
            double redondeaEnergiaPromedio=absArchivo.redondeaDouble2decimales(absArchivo.calcularPromedio((absArchivo.archEnergia)));

            double redondeaEnergiaMin=absArchivo.redondeaDouble2decimales(absArchivo.calcularMinimo(absArchivo.archEnergia));
            double redondeaEnergiaMax=absArchivo.redondeaDouble2decimales(absArchivo.calcularMaximo(absArchivo.archEnergia));
            double redondeaFP=absArchivo.redondeaDouble2decimales(absArchivo.calcularPromedio(absArchivo.archFP));

            // calcula el factor de potencia y lo formatea con 3 decimales -----------------------------
            String promFP;
            DecimalFormat fpformat = new DecimalFormat("##.###");
            promFP = fpformat.format(absArchivo.calcularPromedio(absArchivo.archFP));

            //_______________________________________________________________________________________
            System.out.println("inicio encabezado xx1");

            listaReporteDetalle.add(" Número total de registros:     " +tab()+ absArchivo.archFecha.size() + sbCrLf());
            listaReporteDetalle.add(" Número de registros válidos:   " + tab()+ (absArchivo.archFecha.size() - absArchivo.numMuestraAnormal) + sbCrLf());
            listaReporteDetalle.add(" Energía total registrada:      " + tab()+ redondeaEnergia + " "+ absArchivo.multiploUnidadPotencia+sbCrLf());
            listaReporteDetalle.add(" Energía promedio:              " + tab()+ redondeaEnergiaPromedio+ " "+ absArchivo.multiploUnidadPotencia+"h"+sbCrLf());
            listaReporteDetalle.add(" Energía min valor:              " + tab()+ redondeaEnergiaMin+ " "+ absArchivo.multiploUnidadPotencia+"h"+sbCrLf());
            listaReporteDetalle.add(" Energía max valor:              " + tab()+ redondeaEnergiaMax+ " "+ absArchivo.multiploUnidadPotencia+"h"+sbCrLf());
            listaReporteDetalle.add(" Factor de Potencia:           " + tab()+tab()+ redondeaFP + sbCrLf()); // tomado de promediar valores ver linea 205
            listaReporteDetalle.add(" Número de Registro observados: " +tab() + absArchivo.numMuestraAnormal + sbCrLf());
            listaReporteDetalle.add(" Duración medición (dias):      "+tab()
                    + (absArchivo.calcularDiasString(absArchivo.archFecha.get((absArchivo.archFecha.size() - 1)).toString(),
                    (absArchivo.archFecha.get(0).toString()))) + "" + sbCrLf());
            String ldivision = "__________________________________________________________________________________________________" + sbCrLf();

            listaReporteDetalle.add(ldivision + sbCrLf());

            //******************************************************************************
            //*******************   ESCRIBE EN EL ARCHIVO DE RESULTADOS LOS RESUMEN DE cada ARCHIVO **********

            // ** SE GENERAN LOS CONTROLES DE BRECHA EN EL METODO  QUE SIGUE CALIFICAR MEDICION
            // El encabezado fue eliminado y las siguientes lineas solo colocan los valores
       ;
           // String calificacionArchivoMedicion="";
            String calificacionArchivoMedicion=absArchivo.calificarMedicionM(nombreArchivo,   //   !!!!!!!!  LLama al metodo de calificacion de
                    setup.CinicialesNombEmpresas,
                    setup.CdiasPermanencia, setup.CdiasMinValidez,
                    setup.CDigitoAnoValido, setup.ChabilitacionChequeo, setup.Ccadencia,setup.getCestratosList(),setup.getCnombTarifas());

            //ptx
            //--------------------------------
            int nroEspaciosBlanco= longitudMaxdeNombrexListaArchivos-absArchivo.extraeNombreArchivoBaseSinrutaNiextension(nombreArchivo).length();
            // listaReporteResumen.add(parteDelNombre[parteDelNombre.length - 1].toUpperCase()+tab() +
            listaReporteResumen.add(parteDelNombre[parteDelNombre.length - 1].toUpperCase() +spaceAscii(nroEspaciosBlanco)+tab()+tab()+
                    calificacionArchivoMedicion + sbCrLf());

            // xyz ----> BLOQUE de armado de las MEDICIONES INTEGRADAS
            int varHabilitaCompilador= Integer.parseInt(setup.getBtnHabIntegrador().get(0));// BTN habiltacion compilacion en SETUP
            // HABILITA SUICHE PARA PERMITIR COMPILACION
            int suicheCalificacionCompilable=0;
            //------------------------------------------Chequeo de las calificaciones permitidas

            for (int calif=0;calif<setup.calificacionesPermitidas.size();calif++){ // Barre todas las claificaciones permitidas en SETUP
                if (setup.calificacionesPermitidas.get(calif).equals(calificacionArchivoMedicion)){
                   // System.out.println("calificacion coinciddente "+setup.calificacionesPermitidas.get(calif)+"|"+calificacionArchivoMedicion);
                    suicheCalificacionCompilable=1;
                }
            }
            //-----------------------Chequeo de registro del archivo en base usuarios
            if(absArchivo.buscarUsuarioxNombArchivoInstalacion(absArchivo.extraeNombreArchivoBaseSinrutaNiextension(nombreArchivo))=="NoEncontrado"&&varHabilitaCompilador==1){
                    printErrorArchivoReport.write("No Encontrado en base de usuarios el archivo: "+nombreArchivo+sbCrLf());
                    suicheCalificacionCompilable=0;
            }else{

            }
            //-----------------------------------------
            //-----------------------------------------

            if(varHabilitaCompilador==1 && suicheCalificacionCompilable==1){
                System.out.println("HABILITADA COMPILACION DE ARCHIVOS");
                //---Encabezado del archiv MedIntegrado
                if(flagFaltaEncabezadoArchivo) {//imprime el encabezado solo para el primer archivo
                    flagFaltaEncabezadoArchivo=false;

                    printMedIntegradas.write("Fechas" + "|" + "Hora" + "|" + "Voltaje" + "|" + "Potencia" + "|" + "FP" + "|" + "Anomalia" + "|" +
                            "IdUsuario" + "|" + "Tarifa-Estrato" + "|" + "NombreArchivo" + "|" + "MultiploUnidadPotencia" + sbCrLf());
                }
                //------------------------------------------------------------
                //Se inicia ciclo de escritura MedIntegrada en archivo de errores la condicion
                printErrorArchivoReport.write("Archivo compilado | "+ nombreArchivo + " | " + calificacionArchivoMedicion +
                        " | "+ absArchivo.extraeNombreArchivoBaseSinrutaNiextension(nombreArchivo)  +
                        " | "+ absArchivo.buscarUsuarioxNombArchivoInstalacion(absArchivo.extraeNombreArchivoBaseSinrutaNiextension(nombreArchivo))+
                        " |" + absArchivo.buscarTarifaUsuarioxNombArchivoInstalacion(absArchivo.extraeNombreArchivoBaseSinrutaNiextension(nombreArchivo))+
                        " |" +absArchivo.archFecha.get(0)+
                        " |" +absArchivo.archFecha.get(absArchivo.archFecha.size()-1)+sbCrLf());

                nroArchivosCompilados++;// incrementa el contador indicando la cantidad de archivos que se han compilado
                for (int i = 0; i < absArchivo.archHora.size(); i++) {// inicializa el lazo para barrer totos los registros leidos

                    // revisa condicionamiento Funcion módulo y calificacion del archivo de medicion
                    int varFuncionModulo= Integer.parseInt(setup.getBtnHabIntegrador().get(1));
                    double funcModuloArchPotencia= (double) absArchivo.archPotencia.get(i);
                    if (varFuncionModulo==1 && funcModuloArchPotencia<0){// Se convierten las potencias negativas a positiva
                        funcModuloArchPotencia=(-1) * funcModuloArchPotencia; //invierte signo
                        System.out.println(setup.getBtnHabIntegrador().get(1)+"Valor cambiado de signo "+funcModuloArchPotencia+ " valor original "+absArchivo.archPotencia.get(i));
                    }else {
                        //System.out.println(setup.getBtnHabIntegrador().get(1) +" se mantiene valor original");
                    }
                    //-------------------------------------------------------------------------------
                    // Escribe en el archivo MedIntegrada de acuerdo al los flags habilitados
                    //Escritura del archivo
                    //nn
                    printMedIntegradas.write(absArchivo.archFecha.get(i) + "|" + absArchivo.archHora.get(i) + "|" +
                            absArchivo.archVoltaje.get(i) + "|" + absArchivo.archPotencia.get(i) + "|" + absArchivo.archFP.get(i) + "|" +
                            absArchivo.archflagAnormalidad.get(i) + "|"+
                            absArchivo.buscarUsuarioxNombArchivoInstalacion(absArchivo.extraeNombreArchivoBaseSinrutaNiextension(nombreArchivo))+
                            "|"+absArchivo.buscarTarifaUsuarioxNombArchivoInstalacion(absArchivo.extraeNombreArchivoBaseSinrutaNiextension(nombreArchivo))+
                            "|"+ absArchivo.extraeNombreArchivoBaseSinrutaNiextension(nombreArchivo)+//
                            "|"+ absArchivo.multiploUnidadPotencia+sbCrLf());// Escritura de las variables para armar el archivo MedIntegradas
                    //nn

                }    // ******     Fin del lazo de lectura de los archivos de registros de medicion
            }else {
                System.out.println("No habitada la compilacion");// BtnHabIntegrador esta en la clase Setup
                if(varHabilitaCompilador==1) {
                    printErrorArchivoReport.write("Archivo No compilado | " + nombreArchivo + " | " + calificacionArchivoMedicion + sbCrLf());
                }
            }
            System.out.println("fin bucle formato 07");
        }//-----------------------------------------------------------------------------------------------------------------------
        //incorpora texto para informar si la funcion "Modulo esta activa"
        int funcionModuloActiva= Integer.parseInt(setup.btnHabIntegrador.get(1));
        String tempFuncionModuloActivo="";
        if (funcionModuloActiva==1){
            tempFuncionModuloActivo="Funcion Modulo Activa";
        }
        System.out.println("inicio encabezado xx2");

        // La siguiente línea imprime la configuracion actual de la aplicacion ------------------------
        listaReporteDetalle.add(sbCrLf()+"CONFIGURACION: "+ setup.stringConfiguracion()+sbCrLf());
        //---------------------------------------------------------------------------------------------
        printErrorArchivoReport.write(sbCrLf()+"Archivos procesados: "+xlistaArchivos.size()+
                " | Compilados: "+ Math.round(nroArchivosCompilados)+ " | Porcentaje: "+
                (Math.round(nroArchivosCompilados/xlistaArchivos.size()*100))+" % | "+tempFuncionModuloActivo+sbCrLf()+sbCrLf());
    }//fin lazoPrincipal2








}

