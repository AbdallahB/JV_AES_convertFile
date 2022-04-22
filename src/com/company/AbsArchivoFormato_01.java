package com.company;

import javax.swing.*;
import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class AbsArchivoFormato_01 extends AbsArchivo {


    public AbsArchivoFormato_01(String primeraPalabraValidaArchivo, int anumlineas, String sDirectorioTrabajo, String tipoFormatoaLeer, int numeroTotalDigitosNombreArchivoxtipoFormato) {
        super(primeraPalabraValidaArchivo, anumlineas, sDirectorioTrabajo, tipoFormatoaLeer, numeroTotalDigitosNombreArchivoxtipoFormato);
    }

    public String generarTokensFormato_01(String path, String nombArch, int numlineas) throws IOException {

        // Configura la instancia de la clase File para acceder al archivo

        File cargaConfigMed = new File(nombArch);
        FileReader configReader = null;
        String Camp25="";
        String Camp28="";

        try {
            configReader = new FileReader(nombArch);

            int numLinesConfigMed = 0;
            int lineasEncabezadoArchivo = 1; // valor fijo de las lineas descriptivas del archivo ConfigMed.TXT
            //try {

            BufferedReader buffer = new BufferedReader(configReader);
            String blinea;
            int indicadorPosic = 0;
            while ((blinea = buffer.readLine()) != null) {
                //******** lineas para ller la configuracion y cargar en las variables
                // System.out.println("indicadorPosic= "+indicadorPosic+"  valor de la linea: "+blinea);

                int numMuestra = indicadorPosic - lineasEncabezadoArchivo + 1;// va indicando el numero de la linea para cada  muestra

                //System.out.println("line  en registro de prueba "+ blinea);
                indicadorPosic = indicadorPosic + 1;
                String[] parts = blinea.split(";");
                String[] fechaParts;
                String[] horaParts;
                String[] tempMultiploUnidad;
                if (indicadorPosic > 1) { // este es el condicional para que solo procese los valores y salte el encabezado

                    for (int p = 0; p < parts.length; p++) {
                        //System.out.println("valor parte "+p+ " archivo de medicion" +" "+parts[p] );

                        fechaParts = parts[0].split("\"");
                        horaParts = parts[1].split("\"");

                        if (p < 2) {
                            //  System.out.println("palabra fecha 0= " + fechaParts[1]);
                            //   System.out.println("palabra hora 0= " + horaParts[1]);
                        }


                        // swich de bifurcacion
                        switch (p) {
                            case 1:
                                archFecha.add(fechaParts[1]); // palabraParts[0]= fecha sin las comillas con que vienen
                                //System.out.println("Fecha "+fechaParts[1]);
                                break;
                            case 2:
                                String horaTemp = horaParts[1];
                                horaTemp = horaTemp.substring(0, 5);

                                archHora.add(horaTemp); //
                                // System.out.println("Hora"+palabraParts[1]);
                                break;

                            case 5:
                                String multiploUnidad = parts[p];
                                //System.out.println("valor de unidad de potencia W" + multiploUnidad);

                                tempMultiploUnidad = parts[p].split("\""); // divide el string cortando por " (comillas)

                                //System.out.println("quitar comillas de "+tempMultiploUnidad[1]);
                                multiploUnidadPotencia=(tempMultiploUnidad[1]); // Este arrglo toma la unidad y multiplos para preservar consistencia
                                // archivo de mediicón

                                break;

                            case 7:
                                String varTempV = ((parts[p]));//
                                varTempV = varTempV.replace(",", ".");

                                archVoltaje.add(Double.valueOf(varTempV));//
                                //System.out.println("Voltaje"+varTempV);
                                break;

                            case 10: // Calidad de la muestra
                                String varTempAnomalia = ((parts[p]));//
                                varTempAnomalia = varTempAnomalia.replace(",", ".");
                                //  System.out.println(varTempAnomalia+" valor de p en l lazo "+p);

                                Double varTemp = Double.valueOf(varTempAnomalia);

                                if (varTemp != 100) {
                                    archAnormalidad.add("A");
                                    archflagAnormalidad.add("A");
                                } else {
                                    archAnormalidad.add(" ");
                                    archflagAnormalidad.add(" ");
                                }
                                break;
                            case 25: // Caso: Potencia Activa Usuario
                                // Captura la potencia mas alta entre el campo 25 y 28
                                double campo25= Double.parseDouble(parts[25]);
                                //-------------------------------------
                                if(campo25>=0){

                                }else{
                                    campo25=campo25*-1;//convierte a positivo el valor
                                }
                                //--------------------------------------
                                double campo28= Double.parseDouble(parts[28]);
                                if(campo28>=0){

                                }else{
                                    campo28=campo28*-1;//convierte valor a positivo
                                }
                                String varTempPotencia="";
                                if(campo25>campo28){
                                    varTempPotencia = ((parts[p]));//
                                 }else{
                                    varTempPotencia = ((parts[p+3]));//
                                }
                                varTempPotencia = varTempPotencia.replace(",", ".");
                                Double dbVarTemPotencia = Double.valueOf(varTempPotencia);

                               // String varTempPotencia = ((parts[p+3]));//
                               // varTempPotencia = varTempPotencia.replace(",", ".");
                               // Double dbVarTemPotencia = Double.valueOf(varTempPotencia);

                                 System.out.println(" case25 valor potencia Carga Usuario "+dbVarTemPotencia);
                                 //se captura solo para monitoreo de campo C25 y C28
                                 if(indicadorPosic==2){
                                     Camp25=parts[p];
                                     Camp28=parts[p+3];
                                 }
                                //Condicional para convertir a positivolos valores de potencua
                                if (dbVarTemPotencia >= 0) { // Considera valores positivos
                                    archPotencia.add(dbVarTemPotencia);

                                    archEnergia.add(dbVarTemPotencia / 4);
                                    archTotalEnergia = archTotalEnergia+((dbVarTemPotencia/4));

                                } else {// aplica función módulo
                                    archPotencia.add(dbVarTemPotencia*-1);
                                    archEnergia.add(dbVarTemPotencia*-1);
                                    archTotalEnergia = archTotalEnergia+((dbVarTemPotencia/4));
                                }

                                break;
                            case 28: // Caso: Potencia activa Generador

                                // *** ESTE CASO EN PARA GENERADORES

                                varTempPotencia = ((parts[p]));//
                                varTempPotencia = varTempPotencia.replace(",", ".");
                                dbVarTemPotencia = Double.valueOf(varTempPotencia);

                                System.out.println("case 28 Esta desactivada potencia Generador "+dbVarTemPotencia);
                                // se capruta C25 y C28 solo para monitoreo
                                if(indicadorPosic==2){
                             //       Camp28=parts[p];
                                }

                              //if (dbVarTemPotencia < 0) { // Esta condicion ubica el valor de la potencia para la generacion
                              //    //potenciaArchOrig.add(Double.valueOf(varTempPotencia));
                              //    potenciaArchOrigGenerador.add(dbVarTemPotencia * -1); // aplica modulo
                              //    energiaArchOrigGenerador.add((dbVarTemPotencia * -1) / 4);// se convierte la potencia a energia dividiendo x 4 para cadencias de 15min
                              //} else {
                              //    potenciaArchOrigGenerador.add(0.0); // descarta el valor
                              //    energiaArchOrigGenerador.add(0.0); // descarta el valor
                              //}

                                break;
                            case 47: //Factor de potencia
                                String varTempFPc = ((parts[p]));//
                                varTempFPc = varTempFPc.replace(",", ".");
                                Double vFPc = Double.valueOf(varTempFPc);// esta variable carga el valor medio FP de cliente
                                if (vFPc < 0) {
                                    vFPc = -vFPc; // aplica función modulo a la variable manteniendo positivo el valor de la variable
                                }

                                String varTempFPg = ((parts[p + 3]));//
                                varTempFPg = varTempFPg.replace(",", ".");
                                Double vFPg = Double.valueOf(varTempFPg);// carga el valor medio del FP generador
                                if (vFPg <= 0) {
                                    vFPg = -vFPg; // aplica el modulo para mantener el valor positivo
                                }


                                if (vFPc > vFPg) {
                                    archFP.add(Double.valueOf(vFPc));
                                } else {
                                    archFP.add(Double.valueOf(vFPg));
                                }

                                break;
                        }
                    }
                    //System.out.println("blinea "+blinea);
                } else { // cubre solo las lineas de encabezado

                }
            } // fin de lectura del archivo de datos
        } catch (Exception ex) {
            ex.printStackTrace();
            // Manejo de errores de excepcion por error en formato
            StringBuffer sbCrLf = new StringBuffer();
            sbCrLf.append((char)13);
            sbCrLf.append((char)10);
            String crlfTerminator = sbCrLf.toString();

            System.out.println("error ruta archivo:  "+path+"\\Resultados\\ReporteERROR2.txt");
            FileWriter fwErrorArchivoReport = new FileWriter(path+"\\Resultados\\ReporteERROR2.TXT");
            BufferedWriter bWErrorArchivoReport= new BufferedWriter(fwErrorArchivoReport);
            PrintWriter printErrorArchivoReport = new PrintWriter(bWErrorArchivoReport);

            Date currentDate = Calendar.getInstance(TimeZone.getDefault()).getTime();
            printErrorArchivoReport.write("RESUMEN DE ERRORES ENCONTRADOS    "+ "Fecha del reporte:  "+ String.valueOf(currentDate)+sbCrLf);
            printErrorArchivoReport.write("Retire el archivo "+nombArch +"  y vuelva a correr la aplicación...");

            bWErrorArchivoReport.close();
            fwErrorArchivoReport.close();
            JOptionPane.showMessageDialog(null, "Se encontró un error de formato archivo : "+nombArch +"  " +path);
            System.exit(0);// Salida abrupta del programa

        }
        System.out.println("RESULTADO LECTURA DE VECTORES: ArchAnormalidad "+archAnormalidad.size()+" |archFP " +archFP.size()+
                " | archEnergia "+archEnergia.size()+" |archVoltaje "+archVoltaje.size()+ "  | archHora "+archHora.size()+
                "  |archFecha "+archFecha.size()+" |Linea 2-> C25= "+Camp25+ "| C28= "+Camp28);



        return ("true") ;
    }



}
