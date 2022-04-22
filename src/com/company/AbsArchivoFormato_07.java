package com.company;

import javax.swing.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Pattern;

public class AbsArchivoFormato_07 extends AbsArchivo {
    public AbsArchivoFormato_07(String primeraPalabraValidaArchivo, int anumlineas, String sDirectorioTrabajo, String tipoFormatoaLeer, int numeroTotalDigitosNombreArchivoxtipoFormato) {
        super(primeraPalabraValidaArchivo, anumlineas, sDirectorioTrabajo, tipoFormatoaLeer, numeroTotalDigitosNombreArchivoxtipoFormato);
    }

//****** METODOS

    public String generarTokensFormato_07(String path, String nombArch, int numlineas) throws IOException {

        System.out.println("ejecucion del metodo generarTokensFormato_07");

        // Configura la instancia de la clase File para acceder al archivo

        File cargaConfigMed = new File(nombArch);
        FileReader configReader = null;

        try {
            configReader = new FileReader(nombArch);

            int numLinesConfigMed = 0;//
            //-----------

            int lineasEncabezadoArchivo = 0; // valor fijo de las lineas descriptivas del archivo ConfigMed.TXT
            multiploUnidadPotencia=("KW"); // Se asume el valor de unidad en KW
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
                String[] parts = blinea.split(Pattern.quote("|"));
                String[] fechaParts;
                String[] horaParts;
                String[] tempMultiploUnidad;
                if (indicadorPosic > 0) { // este es el condicional para que solo procese los valores y salte el encabezado


                    for (int p = 0; p < parts.length; p++) {
                       // System.out.println("Indicador linea= "+indicadorPosic+"  valor parte "+ p + " archivo de medicion" +" "+parts[p] );


                        // swich de bifurcacion
                        switch (p) {
                            case 0: // se requiere cambiar el formato de fecha de "YYYYY/MM/dd" a "dd/MM/YYYY"

                                break;
                            case 1:


                                break;

                            case 2:
                                String fechaLeida = eliminaEspaciosyCharEspecialesEntreCaracteres(parts[2]);
                                //LocalDate fechaTomada = LocalDate.parse((fechaLeida));
                                //DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/YY");
                                //String fechaNvoFormato = df.format(fechaTomada);
                                //System.out.println("formato fecha inicial "+ fechaParts[0]+ " Formato final "+df.format(fechaTomada));

                                archFecha.add(fechaLeida); // el ordinal cambia porque la parte 0 fué dividida para separar hora y fecha
                                //System.out.println("Fecha =>"+fechaParts[0]);
                                break;

                            case 3:

                                archHora.add(eliminaEspaciosyCharEspecialesEntreCaracteres(parts[3])); // el ordinal cambia porque la parte 0 fué dividida para separar hora y fecha
                                //System.out.println("Hora=>"+horaParts[1]);
                                break;
                            case 4:
                                String tempEnergia = eliminaEspaciosyCharEspecialesEntreCaracteres(parts[4]);//
                                String varTempEnergia = tempEnergia.replace(",", ".");
                                String variable=parts[p];

                                //System.out.println(tempEnergia.length()+"<<<<<" +varTempEnergia.length()+ " partV "+variable.length());
                                double dvartempEnergia= Double.parseDouble((varTempEnergia));
                                archEnergia.add(dvartempEnergia);
                                break;

                            case 5: //Factor de potencia y Anomalia de lectura
                                String varPotencia = eliminaEspaciosyCharEspecialesEntreCaracteres(parts[5]);//
                                varPotencia = varPotencia.replace(",", ".");
                                double dvarPotencia= Double.parseDouble(varPotencia);

                                archPotencia.add(dvarPotencia);

                                //------VARIABLES NO DECLARADAS----------
                                archVoltaje.add(Double.valueOf(0));
                                archAnormalidad.add(" ");
                                archflagAnormalidad.add(" ");
                                archFP.add(1.0);
                                //----------------


                                break;
                        }
                    }
                } else {   // lee 1era línea de encabezado del archivo que está siendo procesado y se captura la unida múltiplo
                    System.out.println("Unidad del encabezado --> Se asume KW"  );

                    multiploUnidadPotencia=("KW"); // Se asume el valor de unidad en KW
                }
            } // fin de lectura del archivo de datos


            //********************* XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

            Double sumaPotenciaOrg = 0.0;
            Double sumaEnergia = 0.0;

            System.out.println("Potencia " + archPotencia.size() + sbCrLf() + " Energia " + archEnergia.size());
            // System.out.println("Inicio de líneas a revisar");

            //&&  for (int i=0;i<potenciaArchOrig.size();i++){ // lazo que calcula el acumulado de potencias para comprarlos en el sout
            //&&         sumaPotenciaOrg=sumaPotenciaOrg+potenciaArchOrig.get(i);
            // for (int i=0;i<archEnergia.size();i++){ // lazo que calcula el acumulado de potencias para comprarlos en el sout
            //     Double tempEnergiaMedida= (Double) archEnergia.get(i);
            //     sumaPotenciaOrg= sumaPotenciaOrg + tempEnergiaMedida;
            Double sumaEnergiaMedida = 0.0;
            Double sumaPotenciaMedida = 0.0;
            for (int i = 0; i < archPotencia.size(); i++) { // lazo que calcula el acumulado de potencias para comprarlos en el sout
               double tempPotencia= (double) archPotencia.get(i);
               double tempEnergia= (double) archEnergia.get(i);
              sumaPotenciaMedida =  sumaPotenciaMedida +tempPotencia;
              sumaEnergiaMedida=sumaEnergiaMedida+tempEnergia;


            }
            //&& System.out.println(nombArchivoIntalacion+" ===>  Suma de Potencia  medida= "+sumaPotenciaOrg);

            // Se calcula el valor de Energia dividiendo entre la cadencia especificada
            // Cadencia =15 divisor=4 | Cadencia =30 divisor=2 | Cadencia =1 divisor=1

            archTotalEnergia = sumaEnergiaMedida;
            sumaPotenciaOrg = sumaPotenciaMedida;

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

    //---------------------------------------------------------------------
    // metodo que elimina caracteres especiales
    public String eliminaEspaciosyCharEspecialesEntreCaracteres(String cadenaEntrada) {
        String varTemp = cadenaEntrada;
        char[] caracter = varTemp.toCharArray();
        String newVar = "";
        for (int ch = 0; ch < caracter.length; ch++) {
            if ((caracter[ch] >= '0' && caracter[ch] <= '9') || caracter[ch] == '.' || caracter[ch] == ',' || caracter[ch] == '/' || caracter[ch] == ':') {
                newVar = newVar + caracter[ch];
            }
        }
        return newVar;// retorna el String sin espacios y permitiendo los caracteres indicados en el IF
    }
  }
