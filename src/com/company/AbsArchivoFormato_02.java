package com.company;

import javax.swing.*;
import java.io.*;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class AbsArchivoFormato_02 extends AbsArchivo{
    public AbsArchivoFormato_02(String primeraPalabraValidaArchivo, int anumlineas, String sDirectorioTrabajo, String tipoFormatoaLeer, int numeroTotalDigitosNombreArchivoxtipoFormato) {
        super(primeraPalabraValidaArchivo, anumlineas, sDirectorioTrabajo, tipoFormatoaLeer, numeroTotalDigitosNombreArchivoxtipoFormato);
    }


    //****** METODOS  ********

    public String generarTokensFormato_02Monofasico(String path, String nombArch, int numlineas) throws IOException, ParseException {

        System.out.println("generarTokensFormato_02Monofasico");

        multiploUnidadPotencia="";
        lineasEncabezadoArchivoMonofasico=10;
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
                       //   System.out.println("composicion linea " + indPosicion + " nro palabra " + encb + " Encabezado: " + parts[encb]);
                   //----------------------------
                        // condicionales para ajustar encabezado de forma dinamica
                            if ((indPosicion==lineasEncabezado-1) && encb==1 && parts[encb].equals("V") ){
                                    //System.out.println(" La linea corresponde a " + indPosicion + "part " + parts[encb]+ " lineaEncabezado= "+lineasEncabezadoArchivoMonofasico+"|"+lineasEncabezado);
                                    multiploUnidadPotencia= (parts[2]); // guarda el multiploUnidad de potencia
                                    break;
                            }else{
                                if(multiploUnidadPotencia.equals("") && indPosicion==9&& encb>1){// precisa el barrido encb para asegurar que no se cumplio la condicion anterior

                                    System.out.println("xycambio valor lINEA"+multiploUnidadPotencia);
                                    lineasEncabezadoArchivoMonofasico=14;
                                    lineasEncabezado=lineasEncabezadoArchivoMonofasico;
                                }

                            }
                    //------------------------------
                        if (indPosicion==lineasEncabezadoArchivoMonofasico-1 && encb==2){

                            multiploUnidadPotencia= (parts[encb]); // guarda el multiploUnidad de potencia

                            System.out.println(lineasEncabezadoArchivoMonofasico+"======>Multiplo/potencia= "+multiploUnidadPotencia);
                        }

                    }

                } else { // **** *** INICIO DEL PROCESAMIENTO DE LAS MUESTRAS
                     //System.out.println("linea leida  "+blinea);
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

            System.out.println("error ruta archivo:  "+path+"\\Resutado\\error.txt");
            FileWriter fwErrorArchivoReport = new FileWriter(path+"\\Resultados\\ERROR.TXT");
            BufferedWriter bWErrorArchivoReport= new BufferedWriter(fwErrorArchivoReport);
            PrintWriter printErrorArchivoReport = new PrintWriter(bWErrorArchivoReport);

            Date currentDate = Calendar.getInstance(TimeZone.getDefault()).getTime();
            printErrorArchivoReport.write("RESUMEN DE ERRORES ENCONTRADOS    "+ "Fecha del reporte:  "+ String.valueOf(currentDate)+sbCrLf());
            printErrorArchivoReport.write("Retire el archivo Monofásico"+nombArch +"  y vuelva a correr la aplicación...");

            bWErrorArchivoReport.close();
            fwErrorArchivoReport.close();
            JOptionPane.showMessageDialog(null, "Se encontró un error de formato archivo Monofásico: "+nombArch +"  " +path);
            System.exit(0);// Salida abrupta del programa
        }
        return ("true");
    } // fIN TOKEN MONOFÁSICO

    public String generarTokensFormato_02Trifasico(String path, String nombArch, int numlineas) throws IOException, ParseException {
        System.out.println("generarTokensFormato_02Trifasico");
        multiploUnidadPotencia="";
        lineasEncabezadoArchivoAMI =10;

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
                        // System.out.println(lineasEncabezado+ "linea " + indPosicion + " nro palabra " + encb + " Encabezado: " + parts[encb]);

                        //----------------------------
                        // condicionales para ajustar encabezado de forma dinamica
                        if ((indPosicion==lineasEncabezado-1) && encb==1 && parts[encb].equals("V") ){
                           // System.out.println(" La linea corresponde a " + indPosicion + "part " + parts[encb]+ " lineaEncabezado= "+lineasEncabezadoArchivoMonofasico+"|"+lineasEncabezado);
                            multiploUnidadPotencia= (parts[4]); // guarda el multiploUnidad de potencia
                            break;
                        }else{
                            if(multiploUnidadPotencia.equals("") && indPosicion==9 && encb>1){// precisa el barrido encb para asegurar que no se cumplio la condicion anterior

                                System.out.println("xycambio valor lINEA"+multiploUnidadPotencia);
                                lineasEncabezadoArchivoAMI =14;
                                lineasEncabezado= lineasEncabezadoArchivoAMI;
                            }
                        }
                        //------------------------------
                    }

                } else { // **** *** INICIO DEL PROCESAMIENTO DE LAS MUESTRAS  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡
                    //System.out.println("blinea inicio de muestras: "+blinea);
                    //    System.out.println( numMuestra+" Muestra   "+ (indPosicion-lineasEncabezado+1) +" valor: "+ parts[m]);
                    //Lazo para almacenar las muestras en variables para cada
                   // for (int m = 0; m < parts.length; m++) {
                   //     //System.out.println("m "+m+" muestra "+parts[m]);
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
                                //


                                String strEnerg=(parts[m]);
                                strEnerg=strEnerg.replace(",",".");
                                Double tempPotencia= Double.valueOf(strEnerg);
                                archEnergia.add(tempPotencia/4);
                                archPotencia.add(tempPotencia);
                                archTotalEnergia = archTotalEnergia+((tempPotencia/4));

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


            System.out.println("error ruta archivo:  "+path+"\\Resultados\\ReporteERROR2.txt");
            FileWriter fwErrorArchivoReport = new FileWriter(path+"\\Resultados\\ReporteERROR2.TXT");
            BufferedWriter bWErrorArchivoReport= new BufferedWriter(fwErrorArchivoReport);
            PrintWriter printErrorArchivoReport = new PrintWriter(bWErrorArchivoReport);

            Date currentDate = Calendar.getInstance(TimeZone.getDefault()).getTime();
            printErrorArchivoReport.write("RESUMEN DE ERRORES ENCONTRADOS    "+ "Fecha del reporte:  "+ String.valueOf(currentDate)+sbCrLf());
            printErrorArchivoReport.write("Retire el archivo trifásico "+nombArch +"  y vuelva a correr la aplicación...");

            bWErrorArchivoReport.close();
            fwErrorArchivoReport.close();
            JOptionPane.showMessageDialog(null, "Se encontró un error de formato archivo Trifásico: "+nombArch +"  " +path);
            System.exit(0);// Salida abrupta del programa
        }

        return ("true");
    }
    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX


}
