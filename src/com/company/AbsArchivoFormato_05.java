package com.company;

import javax.swing.*;
import java.io.*;
import java.text.ParseException;
import java.util.*;

public class AbsArchivoFormato_05 extends AbsArchivo {
    public AbsArchivoFormato_05(String primeraPalabraValidaArchivo, int anumlineas, String sDirectorioTrabajo, String tipoFormatoaLeer, int numeroTotalDigitosNombreArchivoxtipoFormato) {
        super(primeraPalabraValidaArchivo, anumlineas, sDirectorioTrabajo, tipoFormatoaLeer, numeroTotalDigitosNombreArchivoxtipoFormato);
    }


    //****** METODOS  ********

    public String generarTokensFormato_05(String path, String nombArch, int numlineas) throws IOException, ParseException {
        String ErrorTokenFormat="";// Este es un valor devuelve el metodo y en caso de error indica la linea
        Integer nroLineasConError=0; //cuenta las lineas que generan error en el archivo que se esta leyendo

        System.out.println("generarTokensFormato_05 Trifasico");

        int lineasEncabezadoArchivo=2; // LINEAS A CONSIDERAR COMO ENCABEZADO DEL ARCHIVO
        // esta variable de inicializacion fue declarada al inicio de la clase Archivo
        // declarar el valor al inicio de la clase



        //File input = new File(path);
        FileReader fileReader = new FileReader(nombArch);
        int numLines = 0;
        try {
            BufferedReader buffer = new BufferedReader(fileReader);
            String blinea;

            int indPosicion = 0;
            String tempLineaArchivo;

            while ((blinea = buffer.readLine()) != null) {
                tempLineaArchivo=blinea;
                int numMuestra=indPosicion-lineasEncabezadoArchivo+1;    // va indicando el numero de la linea para cada  muestra

                //System.out.println("line  en registro de prueba "+ blinea);
                indPosicion = indPosicion + 1;
                String[] parts = blinea.split("\\|");

                //System.out.println("numero linea "+ indPosicion+"archivo "+nombArch+" blinea: "+blinea);

                // MANEJO DEL ENCABEZADO **************************************
                if (indPosicion < lineasEncabezadoArchivo) {             //valor que delimita la lectura del encabezado
                    //System.out.println("contenido del encabezado, linea nro " + indPosicion + " contenido: " + blinea);//

                    for (int encb = 0; encb < parts.length; encb++) {
                        // System.out.println("linea " + indPosicion + " nro palabra " + encb + " Encabezado: " + parts[encb]);

                        // Asignacion del múltiplo de potencia
                        if (indPosicion==lineasEncabezadoArchivo-1 && encb==2){
                            multiploUnidadPotencia= (parts[encb]); // extrae el múltiplo que tiene la unidad de potencia
                            String [] partMultip=multiploUnidadPotencia.split("H");
                            multiploUnidadPotencia=partMultip[0];

                            //convierte la k a minuscula
                            String temp= String.valueOf(multiploUnidadPotencia.charAt(0));
                            String tempstrmultiplo=temp.toLowerCase();
                            multiploUnidadPotencia=tempstrmultiplo+multiploUnidadPotencia.charAt(1);
                            System.out.println("se asigna el multiplo de potencia con valor: "+multiploUnidadPotencia+" "+tempstrmultiplo+multiploUnidadPotencia.charAt(1));
                        }
                        // Asignacion del múltiplo de potenciaRECTIVA
                        if (indPosicion==lineasEncabezadoArchivo-1 && encb==3){
                            multiploUnidadPotenciaReactiva= (parts[encb]); // extrae el múltiplo que tiene la unidad de potencia
                            String [] partMultip=multiploUnidadPotenciaReactiva.split("H");
                            multiploUnidadPotenciaReactiva=partMultip[0];

                            //convierte la k a minuscula
                            String temp= String.valueOf(multiploUnidadPotenciaReactiva.charAt(0));
                            String tempstrmultiplo=temp.toLowerCase();
                            multiploUnidadPotenciaReactiva=tempstrmultiplo+multiploUnidadPotenciaReactiva.charAt(1)+multiploUnidadPotenciaReactiva.charAt(2)+multiploUnidadPotenciaReactiva.charAt(3);
                            System.out.println("se asigna el multiplo de potencia con valor: "+multiploUnidadPotencia + " | "+multiploUnidadPotenciaReactiva);
                        }

                    }

                } else { // **** *** INICIO DEL PROCESAMIENTO DE LAS MUESTRAS  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡

                    //    System.out.println( numMuestra+" Muestra   "+ (indPosicion-lineasEncabezado+1) +" valor: "+ parts[m]);
                    //Lazo para almacenar las muestras en variables para cada
                   // for (int m = 0; m < parts.length; m++) {
                   //     System.out.println("nro. segmento "+ m +" muestra "+parts[m]);
                   // }

                    //-------------------------------------------------
                    // control digitos del año y flag de error de campos FECHA
                    String crtl4DigitosAno="";
                    boolean FlagCamposBlineaError=false;
                    try {
                        crtl4DigitosAno = parts[0];
                        String [] tempControlfecha=crtl4DigitosAno.split("/");
//-----------------------------
                        String valorStrMes=tempControlfecha[0];//mes
                        String valorStrdia=tempControlfecha[1];//dia
                        String valorStrAno=tempControlfecha[2];//Ano

                        if(Errorformatomes(valorStrMes) || ErrorformatoDias(valorStrdia) || ErrorformatoAno(valorStrAno)){
                            //JOptionPane.showMessageDialog(null, "VER = "+ tempControlfecha[0]+ " | " +tempControlfecha[1]+" | "+tempControlfecha[2]);
                            FlagCamposBlineaError=true;

       //<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡
                        }
   //----------------------------
                    }catch (Exception ex){
                        FlagCamposBlineaError=true;
                    }
                    String [] tempCampoFecha=crtl4DigitosAno.split("/");
                    //FlagCamposBlineaError=false;// se inicializa el flag indicando error=off=false
//<<<<<<<<< ¡¡¡¡¡¡¡
                    //Chequeo de campos de potencia
                    try {
                        Double tempCampoE = Double.valueOf(parts[2]);
                        Double tempCampoER = Double.valueOf(parts[3]);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        //JOptionPane.showMessageDialog(null, "catched ");
                        FlagCamposBlineaError=true;
                    }
                    //---------------------
                    //*************************************************************************************************
                    // REVISAR FLAG DE ANORMALIDAD
                        if (parts.length > 4 && parts[0].length()>=8 && tempCampoFecha[2].length()>=4 && !FlagCamposBlineaError) {    // ---- Las siguientes dos lineas alimentan las lista con los flags
                            archflagAnormalidad.add("A");
                        } else {
                            if(parts.length==4 && parts[0].length()>=8 && tempCampoFecha[2].length()>=4 && !FlagCamposBlineaError) {
                                archflagAnormalidad.add(" ");
                            }
                        }
                    //*************************************************************************************************

                    //---------------------------------------------
                    for (int m = 0; m < parts.length; m++) {

                        //n=====================================
                        //-------------------------------------------------
                        //CHEQUEA CANTIDAD DE CAMPOS de la linea: SI ES MENOR A 4 o hay falta caracteres
                                                                     // en fecha Genera un ERROR recuperable
                        //-------------------
                        //Chequeo de campos de potencia
                        try {
                            Double tempCampoE = Double.valueOf(parts[2]);
                            Double tempCampoER = Double.valueOf(parts[3]);

                        } catch (Exception ex) {
                            ex.printStackTrace();
                           // JOptionPane.showMessageDialog(null, "catched ");
                            FlagCamposBlineaError=true;
                        }
                        //---------------------

                       // JOptionPane.showMessageDialog(null, "longitud "+parts[0].length()+" | "+tempCampoFcha[2].length());
                        if (parts.length<4 ||parts[0].length()<8||tempCampoFecha[2].length()<4||FlagCamposBlineaError||FlagCamposBlineaError){// chequea longitud char de linea y longitud de campo fecha
                            nroLineasConError=nroLineasConError+1;

                           // JOptionPane.showMessageDialog(null, "Error de formato en archivo "+
                            //       extraeNombreArchivoBaseSinrutaNiextension(nombArch)+ ";  linea nro: "+indPosicion+
                            //        " ;  con contenido \"= "+blinea +"\";  CORRIJA ARCHIVO O SERAN DESCARTADAS "+
                             //      nroLineasConError +" LINEAS CON ERROR!!"+" |"+archEnergia.size()+"|"+archFecha.size()+"|"+archflagAnormalidad.size());
                            ErrorTokenFormat="ERROR DE FORMATO | línea "+indPosicion+" (líneas descartadas: "+nroLineasConError+" de ";
                            m=1000; // Obliga al For a cerrar lazo y deriva a un caso inexistente
                        }
                        //n00000000000000000000000000000000000000000

//JOptionPane.showMessageDialog(null," xxError: "+blinea);

                        //System.out.println("numero de partes e q divisiona un arch trif "+parts.length);
                        switch (m) {
                            case 0:
                                if (parts[m]==""||parts[m]==null||parts[m]==" "){
                                    JOptionPane.showMessageDialog(null, "Se encontró un error de formato fecha archivo: "+nombArch );
                                }
                                archFecha.add(parts[m]);

                                break;
                            case 1:
                                if (parts[m]==""||parts[m]==null||parts[m]==" "){
                                    JOptionPane.showMessageDialog(null, "Se encontró un error de formato Hora archivo: "+nombArch );
                                }
                                archHora.add(parts[m]);
                                break;
                            case 2:
                                if (parts[m]==""||parts[m]==null||parts[m]==" "){
                                    JOptionPane.showMessageDialog(null, "ySe encontró un error de formato de potencia archivo: "+nombArch );
                                }
//                                JOptionPane.showMessageDialog(null, blinea+"|"+parts.length+"| eeeeerror potencia= "+parts[m]);
                                String strEnergia=(parts[m]);
                                strEnergia=strEnergia.replace(",",".");
                                Double tempEnergia= Double.valueOf(strEnergia);
                                archEnergia.add(tempEnergia);
                                archPotencia.add(tempEnergia*4);

                                archTotalEnergia = archTotalEnergia+(tempEnergia);

                                // Variables no suministradas en Archi de entrada
                                //String  strVoltTemp=(parts[m]);
                                // strVoltTemp=strVoltTemp.replace(",",".");
                                archVoltaje.add(0.0);
                                archVoltajeB.add(0.0);
                                archVoltajeC.add(0.0);
                                archFP.add(0.0);
                                break;
                            case 3:
                                String strEnergiaReactiva=(parts[m]);
                                strEnergiaReactiva=strEnergiaReactiva.replace(",",".");
                                Double tempEnergiaReactiva= Double.valueOf(strEnergiaReactiva);
                                archPotenciaReactiva.add(tempEnergiaReactiva*4);
                                archEnergiaReactiva.add(tempEnergiaReactiva);

                                archTotalEnergiaReactiva=archTotalEnergiaReactiva+(tempEnergiaReactiva);
                                break;
                            case 4:
                                archAnormalidad.add(parts[m]);
                                nroMuestraconAarchAnormalidad.add(numLines);//guarda indice de la muestra marcada

                                String s = parts[m];
                                if (parts[m].contentEquals("A")) {
                                    numMuestraAnormal = numMuestraAnormal + 1;

                                }
                                break;
                            case 5:


                                break;
                            case 6:

                                break;
                            case 7:


                                break;
                            case 8:

                                break;
                        }
                    }
                    numLines++;
                }
            } // fin de lazoBlinea para barrer cada linea del archivo
        } catch (Exception ex) {
            ex.printStackTrace();


            System.out.println("error ruta archivo:  "+path+"\\Resultados\\ReporteERROR2.txt");
            FileWriter fwErrorArchivoReport = new FileWriter(path+"\\Resultados\\ReporteERROR2.TXT");
            BufferedWriter bWErrorArchivoReport= new BufferedWriter(fwErrorArchivoReport);
            PrintWriter printErrorArchivoReport = new PrintWriter(bWErrorArchivoReport);

            Date currentDate = Calendar.getInstance(TimeZone.getDefault()).getTime();
            printErrorArchivoReport.write("RESUMEN DE ERRORES ENCONTRADOS    "+ "Fecha del reporte:  "+ String.valueOf(currentDate)+sbCrLf());
            printErrorArchivoReport.write("Retire el archivo  "+nombArch +"  y vuelva a correr la aplicación...");

            bWErrorArchivoReport.close();
            fwErrorArchivoReport.close();
            JOptionPane.showMessageDialog(null, "Se encontró un error grave de formato!!\nRevise el archivo: "+nombArch );
            System.exit(0);// Salida abrupta del programa
        }
        //------------------------------------
        // Correccion del balance total restando energia anomála
        // Balance de energias que no estan marcadas como anomalas, estas se restan
        archTotalEnergia=archTotalEnergia-sumadeRegistrosMarcadoAnomalo(archEnergia);
        archTotalEnergiaReactiva=archTotalEnergiaReactiva-sumadeRegistrosMarcadoAnomalo(archEnergiaReactiva);
        System.out.println("balance de energia marcada "+sumadeRegistrosMarcadoAnomalo(archEnergia));


        //----------------------------------------------------------------------

        System.out.println("Energia"+archEnergia+" Energias totales activa "+archTotalEnergia+ "   energia reactiva total "
                +archTotalEnergiaReactiva +"   Valores reactivos "+archEnergiaReactiva);
//---------------------------------------------------------------
        // chequeo para correccion de Hora 24:00:00 y cambiar a 00:00 marcando el día siguiente

        correccionFechaHora24();


        //barrido de chequeo para desplegar los valores de hora y fecha guardados en el arreglo
      //for(int i=0;i<archHora.size();i++){
      //    if(archHora.get(i).equals("00:00")) {
      //        System.out.println(">xx hora " + archHora.get(i) + "  | xxfecha " + archFecha.get(i));
      //    }else{
      //        System.out.println(">hora " + archHora.get(i) + "  | fecha " + archFecha.get(i));
      //    }
      //}
       //............................
        if (ErrorTokenFormat.equals("")){
           return(ErrorTokenFormat);// deja el valor "" sin añadir las lineas del archivo
        }else{
            //-------------
            if(numLines<=nroLineasConError){
                archFecha.add("00/00/0000");
                archHora.add("0:00");
                archEnergia.add(0.0);
                archEnergiaReactiva.add(0.0);
                archPotencia.add(0.0);
                archflagAnormalidad.add(" ");
                archAnormalidad.add(" ");
                archFP.add(0);
                archFecha.add("0/00/0000");
                archHora.add("0:00");
                archEnergia.add(0.0);
                archEnergiaReactiva.add(0.0);
                archPotencia.add(0.0);
                archflagAnormalidad.add(" ");
                archAnormalidad.add(" ");
                archFP.add(0);
                archTotalEnergiaReactiva=0;
                archTotalEnergia=0;
                nroMuestraconAarchAnormalidad.add(0);
                numMuestraAnormal=1;
            }
            //----------------------------
            return (ErrorTokenFormat+(numLines)+")"); // se añade al ErrorToken nro total de lineas del archivo
        }

    }
    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

    //------------------------------
    // Corregir hora 24:00:00 y fecha en las listas ArchHora y ArchFecha





}
