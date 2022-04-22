package com.company;

import javax.swing.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class AbsArchivoFormato_03 extends AbsArchivo {
    public AbsArchivoFormato_03(String primeraPalabraValidaArchivo, int anumlineas, String sDirectorioTrabajo, String tipoFormatoaLeer, int numeroTotalDigitosNombreArchivoxtipoFormato) {
        super(primeraPalabraValidaArchivo, anumlineas, sDirectorioTrabajo, tipoFormatoaLeer, numeroTotalDigitosNombreArchivoxtipoFormato);
    }


    //****** METODOS

    public String generarTokensFormato_03(String path, String nombArch, int numlineas) throws IOException {

        System.out.println("ejecucion del metodo generarTokensFormato_03");

        // Configura la instancia de la clase File para acceder al archivo

        File cargaConfigMed = new File(nombArch);
        FileReader configReader = null;

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
                //System.out.println("valor de la linea "+blinea);

                int numMuestra = indicadorPosic - lineasEncabezadoArchivo + 1;// va indicando el numero de la linea para cada  muestra

                //System.out.println("line  en registro de prueba "+ blinea);
                indicadorPosic = indicadorPosic + 1;
                String[] parts = blinea.split(";");
                String[] fechaParts;
                String[] horaParts;
                String[] tempMultiploUnidad;
                if (indicadorPosic > 1) { // este es el condicional para que solo procese los valores y salte el encabezado

                    for (int p = 0; p < parts.length; p++) {
                        //System.out.println("Indicador linea= "+indicadorPosic+"  valor parte "+ p + " archivo de medicion" +" "+parts[p] );

                        fechaParts = parts[0].split("\\s+");
                        horaParts = parts[0].split("\\s+");

                        if (p < 2) {
                            //System.out.println("palabra fecha 0= " + fechaParts[0]);
                            //System.out.println("palabra hora 1= " + horaParts[1]);
                        }


                        // swich de bifurcacion
                        switch (p) {
                            case 0: // se requiere cambiar el formato de fecha de "YYYYY/MM/dd" a "dd/MM/YYYY"
                                String fechaLeida = fechaParts[0];
                                fechaLeida = fechaLeida.replace("/", "-");


                                LocalDate fechaTomada = LocalDate.parse(fechaLeida);
                                DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/YYYY");
                                String fechaNvoFormato = df.format(fechaTomada);
                                //System.out.println("formato fecha inicial "+ fechaParts[0]+ " Formato final "+df.format(fechaTomada));

                                archFecha.add(fechaNvoFormato); // el ordinal cambia porque la parte 0 fué dividida para separar hora y fecha
                                //System.out.println("Fecha =>"+fechaParts[0]);

                                archHora.add(horaParts[1]); // el ordinal cambia porque la parte 0 fué dividida para separar hora y fecha
                                //System.out.println("Hora=>"+horaParts[1]);
                                break;
                            case 1:
                                //System.out.println("Voltaje 1 Caso 1 "+parts[1]);
                                String varTempV = ((parts[p]));//
                                varTempV = varTempV.replace(",", ".");

                                archVoltaje.add(Double.valueOf(varTempV));//
                                //System.out.println("Voltaje"+varTempV);

                                break;

                            case 6:

                                break;

                            case 7:
                                // System.out.println("Potencia Caso 7-> "+parts[7]);
                                String varTempEnergia = ((parts[p]));//
                                varTempEnergia = varTempEnergia.replace(",", ".");
                                Double dbVarTemEnergia = Double.valueOf(varTempEnergia);
                                //System.out.println(" valor potencia Carga Usuario "+dbVarTemPotencia);
                                if (dbVarTemEnergia >= 0) { // Considera valores positivos
                                    archPotencia.add(dbVarTemEnergia * 4);
                                    archEnergia.add(dbVarTemEnergia);
                                } else {// descarta valores negativos
                                    archPotencia.add(dbVarTemEnergia * 4 * -1);
                                    archEnergia.add(dbVarTemEnergia * -1);
                                }
                                break;

                            case 10: //Factor de potencia y Anomalia de lectura
                                //System.out.println("Factor de potencia->"+parts[p]);
                                // String varTempFPc=(parts[p]);//
                                //  Double vFPc= Double.valueOf(parts[p]);// esta variable carga el valor medio FP de cliente
                                // System.out.println("valor aceptado de vFPc "+ vFPc);
                                archFP.add(1.0);

                                archAnormalidad.add(" ");
                                archflagAnormalidad.add(" ");

                                break;

                            case 12: // Calidad de la muestra
                                //String varTempAnomalia=((parts[p]));//
                                //varTempAnomalia= varTempAnomalia.replace(",", ".");
                                //  System.out.println(varTempAnomalia+" valor de p en l lazo "+p);
                                // Double varTemp= Double.valueOf(varTempAnomalia);
                                //if (varTemp!=100){
                                //    anomaliaArchOrig.add("A");
                                //} else {
                                // anomaliaArchOrig.add("");
                                // }
                                break;
                            case 25: //


                                break;
                            case 28: //

                                break;
                        }
                    }
                } else {   // lee 1era línea de encabezado del archivo que está siendo procesado y se captura la unida múltiplo
                    System.out.println("Unidad del encabezado --> " + parts[7]);
                    if(parts[7].equals("Wh")||parts[7].equals("WH")||parts[7].equals("W")){
                        multiploUnidadPotencia=("W"); // Este arreglo toma la unidad y multiplos para preservar consistencia
                    }else {// en caso de no ser el valor la unidad, se copia exactamente el multiplo y unidad indicada
                        multiploUnidadPotencia = (parts[7]); // Este arreglo toma la unidad y multiplos para preservar consistencia
                    }
                }
            } // fin de lectura del archivo de datos


            //********************* XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

            Double sumaPotenciaOrg = 0.0;
            Double sumaPotenciaGenerOrig = 0.0;

            System.out.println("Potencia " + archPotencia + sbCrLf() + " Energia " + archEnergia);
            // System.out.println("Inicio de líneas a revisar");

            //&&  for (int i=0;i<potenciaArchOrig.size();i++){ // lazo que calcula el acumulado de potencias para comprarlos en el sout
            //&&         sumaPotenciaOrg=sumaPotenciaOrg+potenciaArchOrig.get(i);
            // for (int i=0;i<archEnergia.size();i++){ // lazo que calcula el acumulado de potencias para comprarlos en el sout
            //     Double tempEnergiaMedida= (Double) archEnergia.get(i);
            //     sumaPotenciaOrg= sumaPotenciaOrg + tempEnergiaMedida;
            for (int i = 0; i < archPotencia.size(); i++) { // lazo que calcula el acumulado de potencias para comprarlos en el sout
                Double tempPotenciaMedida = (Double) archPotencia.get(i);
                sumaPotenciaOrg = sumaPotenciaOrg + tempPotenciaMedida;
            }
            //&& System.out.println(nombArchivoIntalacion+" ===>  Suma de Potencia  medida= "+sumaPotenciaOrg);

            // Se calcula el valor de Energia dividiendo entre la cadencia especificada
            // Cadencia =15 divisor=4 | Cadencia =30 divisor=2 | Cadencia =1 divisor=1

            archTotalEnergia = sumaPotenciaOrg / 4;

            System.out.println(nombArch + " ===>  Suma medida de: Total Potencia |TotalEnergia " + sumaPotenciaOrg + sumaPotenciaOrg / 4);


            //if (sumaPotenciaGenerOrig>sumaPotenciaOrg) {// si potenciaGen> PotenciaOrig  inicializa el vector y copia los valores de PotencGen

            sumaPotenciaOrg = 0.0;
            //&& potenciaArchOrig.clear();
            //archPotencia.clear();

            //   for (int i=0;i<potenciaArchOrigGenerador.size();i++) { // lazo para copiar los valores de Gen a PotenciaArchOrig
            //   potenciaArchOrig.add(potenciaArchOrigGenerador.get(i));

            //  }

            //for (int i=0;i<potenciaArchOrig.size();i++) {// lazo solo para comprobar el resultado suma del
            //   sumaPotenciaOrg=sumaPotenciaOrg+potenciaArchOrig.get(i);
            //}

            // System.out.println("Suma acumulada de potencia  ===>  " + sumaPotenciaOrg );
            // }

        }catch (Exception ex) {
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
            JOptionPane.showMessageDialog(null, "Se encontró un error de formato archivo: "+nombArch +"  " +path);
            System.exit(0);// Salida abrupta del programa
        }


        System.out.println("RESULTADO LECTURA DE VECTORES: ArchAnormalidad "+archAnormalidad.size()+" |archFP " +archFP.size()+
                " | archEnergia "+archEnergia.size()+" |archVoltaje "+archVoltaje.size()+ "  | archHora "+archHora.size()+ "  |archFecha "+archFecha.size());

        //*********XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
        return ("true");
    } // fin metodo leerArchInstalacion






}
