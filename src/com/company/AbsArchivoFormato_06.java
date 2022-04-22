package com.company;
import javax.swing.*;
import java.io.*;
import java.util.*;


public class AbsArchivoFormato_06<date> extends AbsArchivo{
    public AbsArchivoFormato_06(String primeraPalabraValidaArchivo, int anumlineas, String sDirectorioTrabajo, String tipoFormatoaLeer, int numeroTotalDigitosNombreArchivoxtipoFormato) {
        super(primeraPalabraValidaArchivo, anumlineas, sDirectorioTrabajo, tipoFormatoaLeer, numeroTotalDigitosNombreArchivoxtipoFormato);
    }


    //****** METODOS

    public String generarTokensFormato_06(String path, String nombArch, int numlineas) throws IOException {
        System.out.println(sbCrLf()+"Inicio metodo generarTokensFormato_06");

// Configura la instancia de la clase File para acceder al archivo

       //&& File cargaConfigMed = new File(ruta_ArchInstalacion);
        File cargaConfigMed = new File(nombArch);
        FileReader configReader = null;

        try {
            //&&configReader = new FileReader(ruta_ArchInstalacion);
            configReader = new FileReader(nombArch);

            int numLinesConfigMed = 0;
            int lineasEncabezadoArchivo = 10; // valor fijo de las lineas descriptivas del archivo ConfigMed.TXT
            //try {
            BufferedReader buffer = new BufferedReader(configReader);
            String blinea;
            int indicadorPosic = -1;
            Integer offsetposicionP = 0;// offset donde comienza la información de fecha
            System.out.println("PROCESANDO ARCHIVO " + nombArch);
            while ((blinea = buffer.readLine()) != null) {


                //******** lineas para ller la configuracion y cargar en las variables
                //System.out.println("valor de la linea "+blinea);

                int numMuestra = indicadorPosic - lineasEncabezadoArchivo + 1;// va indicando el numero de la linea para cada  muestra

                //System.out.println("Linea nro="+indicadorPosic+"  Contenido="+ blinea);
                indicadorPosic = indicadorPosic + 1;
                String[] parts = blinea.split("\\s+");
                String[] fechaParts;
                String[] horaParts;
                String[] tempMultiploUnidad;
                boolean flagFecha = false;// flag=true cuando aparece "/"--->para indicar cuando se está evaluando elcampo fecha dentro de la linea (Blinea))

                if (indicadorPosic >= 1) { // este es el condicional para que solo procese los valores y salte el encabezado
                    archAnormalidad.add(" ");
                    archflagAnormalidad.add(" ");
                    archFP.add(1.0);
                    // Relleno de variables que no se encuentran en el archivo
                    //&&     //anomaliaArchOrig.add("");
                    //archAnormalidad.add(" ");
                    //archFP.add(1.0);
                    //&&    //fpArchOrig.add(1.0);

                    //************ DETECCION DE LA PALABRA QUE CONTIENE LA FECHA
                    // Definicion de variables

                    String nvoformatoFecha = "";// variable donde se almacenar la fecha al normalizar el formato de entrada
                    //identifica la presencia del caracter "/" para discriminar la fecha
                    char[] strF = new char[20];// configura máximo numero de caracteres del string a estudiar
                    String palabraFecha = "";

                    for (int p = 0; p < parts.length; p++) {
                        //  System.out.println("xIndicador linea= " + indicadorPosic+ "  valor parte P de la linea=" + p + " archivo de medicion" + " " + parts[p]);

                        //+++++++++++++++++ DETECTA POSICION DEL CAMPO FECHA EN EL ARCHIVO ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                        palabraFecha = parts[p];// carga una de las palabras de linea
                        palabraFecha.getChars(0, palabraFecha.length(), strF, 0);// carga los caracteres de la palabra en el arreglo "strF"
                        //System.out.println("Palabra evaluada " + palabraFecha+" -->longitud="+strF.length+" tiene la fecha en posicion nro.="+p+ " ");

                        for (int i = 0; i < strF.length; i++) { // barre la longitud del string
                            if (strF[i] == '/' && indicadorPosic == 1 && offsetposicionP == 0) {
                                flagFecha = false;// se vuelve a resetear para la siguiente palabra de Bline
                                offsetposicionP = p;// p es el indice del for donde se encuentra la palabra
                                //System.out.println("1 nro Campo en Blinea=" + p + "  --->FlagFecha=" + flagFecha + " Deteccion de fecha -->longitud=" + strF.length + " tiene la fecha en posicion nro.=" + p + " " + strF[i]);
                                // System.out.println("Linea="+indicadorPosic+ "  i lazo="+i+ "  01 Palabra en posicion offset " + offsetposicionP + "  contenido=" + parts[offsetposicionP]);
                            }
                        }

                    }
                    // **** FIN DE LA DETECCION   --- se encuentra arriba offsetposicion inicializada con la posicion "p" donde esta la fecha dentro de Blinea

                    for (int p = offsetposicionP; p < parts.length; p++) {

                        // ** bloque de preparacion para leer fecha
                        //System.out.println("Indicador linea= "+indicadorPosic+"  valor parte "+ p + " archivo de medicion" +" "+parts[p] );
                        palabraFecha = parts[p];// carga una de las palabras de linea
                        palabraFecha.getChars(0, palabraFecha.length(), strF, 0);// carga los caracteres de la palabra en el arreglo "strF"
                        //System.out.println("Palabra evaluada " + palabraFecha+" -->longitud="+strF.length+" tiene la fecha en posicion nro.="+p+ " ");
                        // fin bloque de preparacion

                        //&&       System.out.println("PROCESANDO ARCHIVO "+nombArchivoIntalacion);

                        // ** Al no haber Anormalidad y FP se llenan los vectores a continuacion


                        Double dbVarTemPotencia = 0.00;

                        // swich de bifurcacion
                        switch (p) {
                            case (4):

                                // ** FECHA Este bloque carga la fecha en el arreglo si el flag esta activo
                                if (p == offsetposicionP) {
                                    // se requiere cambiar el formato de fecha de "MM/dd/YYYY" a "dd/MM/YYYY" antes de almacenarlo en el vector
                                    flagFecha = true;// se habilita para realizar la convsersion de fecha solo ante coincidencia de los casos
                                    // System.out.println("p="+p+"   Llamada a subrutina conversion de fecha con offserposicionP="+offsetposicionP);
                                    nvoformatoFecha = ConvertFecha(flagFecha, strF, palabraFecha);// llamada a procedimiento que convierte el formato de fecha
                                    flagFecha = false; // borra el flag hasta leer una nueva linea
                                    //&& fechaArchOrig.add(nvoformatoFecha); // el ordinal cambia porque la parte 0 fué dividida para separar hora y fecha
                                    archFecha.add(nvoformatoFecha); // el ordinal cambia porque la parte 0 fué dividida para separar hora y fecha
                                    //System.out.println("LInea="+indicadorPosic+"    1 FECHA La nvaFormatoFecha cargada es-->"+nvoformatoFecha+ "  case (p)="+p);
                                }

                                break;
                            case (5):
                                //** FECHA
                                // ** FECHA Este bloque carga la fecha en el arreglo si el flag esta activo
                                if (p == offsetposicionP) {
                                    // se requiere cambiar el formato de fecha de "MM/dd/YYYY" a "dd/MM/YYYY" antes de almacenarlo en el vector
                                    flagFecha = true;// se habilita para realizar la convsersion de fecha solo ante coincidencia de los casos
                                    // System.out.println("p="+p+"   Llamada a subrutina conversion de fecha con offserposicionP="+offsetposicionP);
                                    nvoformatoFecha = ConvertFecha(flagFecha, strF, palabraFecha);// llamada a procedimiento que convierte el formato de fecha
                                    flagFecha = false; // borra el flag hasta leer una nueva linea
                                    //&&fechaArchOrig.add(nvoformatoFecha); // el ordinal cambia porque la parte 0 fué dividida para separar hora y fecha
                                    archFecha.add(nvoformatoFecha); // el ordinal cambia porque la parte 0 fué dividida para separar hora y fecha
                                    //System.out.println("LInea="+indicadorPosic+"    1 FECHA La nvaFormatoFecha cargada es-->"+nvoformatoFecha+ "  case (p)="+p);
                                }

                                // ** HORA
                                if (p == offsetposicionP + 1) {
                                    //System.out.println("LInea="+indicadorPosic+"   2 hora --> "+parts[p]+ "  Case (p)="+p);
                                    //&&horaArchOrig.add(parts[p]); // el ordinal cambia porque la parte 0 fué dividida para separar hora y fecha
                                    archFecha.add(parts[p]); // el ordinal cambia porque la parte 0 fué dividida para separar hora y fecha
                                }
                                break;
                            case (6):

                                //** FECHA
                                // ** FECHA Este bloque carga la fecha en el arreglo si el flag esta activo
                                if (p == offsetposicionP) {
                                    // se requiere cambiar el formato de fecha de "MM/dd/YYYY" a "dd/MM/YYYY" antes de almacenarlo en el vector
                                    flagFecha = true;// se habilita para realizar la convsersion de fecha solo ante coincidencia de los casos
                                    //System.out.println("p="+p+"   Llamada a subrutina conversion de fecha con offserposicionP="+offsetposicionP);
                                    nvoformatoFecha = ConvertFecha(flagFecha, strF, palabraFecha);// llamada a procedimiento que convierte el formato de fecha
                                    flagFecha = false; // borra el flag hasta leer una nueva linea
                                    //&&fechaArchOrig.add(nvoformatoFecha); // el ordinal cambia porque la parte 0 fué dividida para separar hora y fecha
                                    archFecha.add(nvoformatoFecha); // el ordinal cambia porque la parte 0 fué dividida para separar hora y fecha
                                    //System.out.println("LInea="+indicadorPosic+"    1 FECHA La nvaFormatoFecha cargada es-->"+nvoformatoFecha+ "  case (p)="+p);
                                }
                                // ** HORA
                                if (p == offsetposicionP + 1) {
                                    // System.out.println("LInea="+indicadorPosic+"   2 hora --> "+parts[p]+ "  Case (p)="+p);
                                    //&& horaArchOrig.add(parts[p]); // el ordinal cambia porque la parte 0 fué dividida para separar hora y fecha
                                    archHora.add(parts[p]); // el ordinal cambia porque la parte 0 fué dividida para separar hora y fecha
                                }


                                break;
                            case (7):
                                //** FECHA
                                // ** FECHA Este bloque carga la fecha en el arreglo si el flag esta activo
                                if (p == offsetposicionP) {
                                    // se requiere cambiar el formato de fecha de "MM/dd/YYYY" a "dd/MM/YYYY" antes de almacenarlo en el vector
                                    flagFecha = true;// se habilita para realizar la convsersion de fecha solo ante coincidencia de los casos
                                    //System.out.println("p="+p+"   Llamada a subrutina conversion de fecha con offserposicionP="+offsetposicionP);
                                    nvoformatoFecha = ConvertFecha(flagFecha, strF, palabraFecha);// llamada a procedimiento que convierte el formato de fecha
                                    flagFecha = false; // borra el flag hasta leer una nueva linea
                                    //&&fechaArchOrig.add(nvoformatoFecha); // el ordinal cambia porque la parte 0 fué dividida para separar hora y fecha
                                    archFecha.add(nvoformatoFecha); // el ordinal cambia porque la parte 0 fué dividida para separar hora y fecha
                                    // System.out.println("LInea="+indicadorPosic+"    1 FECHA La nvaFormatoFecha cargada es-->"+nvoformatoFecha+ "  case (p)="+p);
                                }

                                // ** HORA
                                if (p == offsetposicionP + 1) {
                                    //System.out.println("LInea="+indicadorPosic+"   2 hora --> "+parts[p]+ "  Case (p)="+p);
                                    //&& horaArchOrig.add(parts[p]); // el ordinal cambia porque la parte 0 fué dividida para separar hora y fecha
                                    archHora.add(parts[p]); // el ordinal cambia porque la parte 0 fué dividida para separar hora y fecha
                                }


                                break;
                            case (8):
                                // ** HORA
                                // ** HORA
                                if (p == offsetposicionP + 1) {
                                    // System.out.println("LInea="+indicadorPosic+"   2 hora --> "+parts[p]+ "  Case (p)="+p);
                                    //&&horaArchOrig.add(parts[p]); // el ordinal cambia porque la parte 0 fué dividida para separar hora y fecha
                                    archHora.add(parts[p]); // el ordinal cambia porque la parte 0 fué dividida para separar hora y fecha
                                }


                                //System.out.println("Voltaje 1 Caso 1 "+parts[1]);
                                // String varTempV=((parts[p]));//
                                // varTempV= varTempV.replace(",", ".");

                                //voltArchOrig.add(Double.valueOf(varTempV));//
                                //System.out.println("Voltaje"+varTempV);
                                break;

                            case (10):
                                //** POTENCIA
                                dbVarTemPotencia = 0.00;
                                if (p == offsetposicionP + 6) {
                                    //System.out.println("Potencia Caso 7-> "+parts[7]);
                                    String varTempPotencia = ((parts[p]));//

                                    if (varTempPotencia.equals("NULL")) { // si el valor es numérico
                                     //   System.out.println("LInea=" + indicadorPosic + "    nulooooo Campo Potencia " + nombArch);
                                        //&&potenciaArchOrig.add(dbVarTemPotencia);
                                        archEnergia.add(0.0);
                                        archPotencia.add(0.0);
                                    } else {

                                        //System.out.println("LInea="+indicadorPosic+"    3 Contenido campo Potencia="+varTempPotencia);
                                        varTempPotencia = varTempPotencia.replace(",", ".");
                                        dbVarTemPotencia = Double.valueOf(varTempPotencia);
                                        //$$ potenciaArchOrig.add(dbVarTemPotencia);
                                        archEnergia.add(dbVarTemPotencia / 4);
                                        archPotencia.add(dbVarTemPotencia);

                                    }
                                    //System.out.println("3 Potencia --> "+dbVarTemPotencia);
                                }
                                break;

                            case 11:
                                //** POTENCIA
                                dbVarTemPotencia = 0.00;
                                if (p == offsetposicionP + 6) {
                                    //System.out.println("Potencia Caso 7-> "+parts[7]);
                                    String varTempPotencia = ((parts[p]));//

                                    if (varTempPotencia.equals("NULL")) { // si el valor es numérico
                                      //  System.out.println("LInea=" + indicadorPosic + "    nulooooo Campo Potencia " + nombArch);
                                        //&&potenciaArchOrig.add(dbVarTemPotencia);
                                        archEnergia.add(0.0);
                                        archPotencia.add(0.0);
                                    } else {

                                        //  System.out.println("LInea="+indicadorPosic+"    3 Contenido campo Potencia="+varTempPotencia);
                                        varTempPotencia = varTempPotencia.replace(",", ".");
                                        dbVarTemPotencia = Double.valueOf(varTempPotencia);
                                        //&&potenciaArchOrig.add(dbVarTemPotencia);
                                        archEnergia.add(dbVarTemPotencia / 4);
                                        archPotencia.add(dbVarTemPotencia);

                                    }
                                    //System.out.println("3 Potencia --> "+dbVarTemPotencia);
                                }

                                break;

                            case 12:
                                //** POTENCIA
                                dbVarTemPotencia = 0.00;
                                if (p == offsetposicionP + 6) {
                                    //System.out.println("Potencia Caso 7-> "+parts[7]);
                                    String varTempPotencia = ((parts[p]));//

                                    if (varTempPotencia.equals("NULL")) { // si el valor no es numérico
                                       // System.out.println("LInea=" + indicadorPosic + "    nulooooo Campo Potencia " + nombArch);
                                        //&& potenciaArchOrig.add(dbVarTemPotencia);
                                        archEnergia.add(0.0);
                                        archPotencia.add(0.0);
                                    } else {

                                        // System.out.println("LInea="+indicadorPosic+"    3 Contenido campo Potencia="+varTempPotencia);
                                        varTempPotencia = varTempPotencia.replace(",", ".");
                                        dbVarTemPotencia = Double.valueOf(varTempPotencia);
                                        //&&potenciaArchOrig.add(dbVarTemPotencia);
                                        archEnergia.add(dbVarTemPotencia / 4);
                                        archPotencia.add(dbVarTemPotencia);
                                    }
                                    //System.out.println("3 Potencia --> "+dbVarTemPotencia);
                                }


                                break;
                            case 13: //
                                //** POTENCIA
                                dbVarTemPotencia = 0.00;
                                if (p == offsetposicionP + 6) {
                                    //System.out.println("Potencia Caso 7-> "+parts[7]);
                                    String varTempPotencia = ((parts[p]));//

                                    if (varTempPotencia.equals("NULL")) { // si el valor es numérico
                                       // System.out.println("LInea=" + indicadorPosic + "    nulooooo Campo Potencia " + nombArch);
                                        //&&potenciaArchOrig.add(dbVarTemPotencia);
                                        archEnergia.add(0.0);
                                        archPotencia.add(0.0);
                                    } else {

                                        //System.out.println("LInea="+indicadorPosic+"    3 Contenido campo Potencia="+varTempPotencia);
                                        varTempPotencia = varTempPotencia.replace(",", ".");
                                        dbVarTemPotencia = Double.valueOf(varTempPotencia);
                                        //&& potenciaArchOrig.add(dbVarTemPotencia);
                                        archEnergia.add(dbVarTemPotencia / 4);
                                        archPotencia.add(dbVarTemPotencia);
                                    }
                                    //System.out.println("3 Potencia --> "+dbVarTemPotencia);
                                }


                                //** VOLTAJE
                                if (p == offsetposicionP + 9) {
                                    String varTempV = "0.00";

                                    varTempV = ((parts[p]));//

                                    if (varTempV.equals("NULL")) {
                                        varTempV = "0.00";
                                        //&&voltArchOrig.add(Double.valueOf(varTempV));//
                                        archVoltaje.add(Double.valueOf(varTempV));//
                                      //  System.out.println("LInea=" + indicadorPosic + "    nuloooooo 4 Voltaje" + varTempV);
                                    } else {
                                        varTempV = varTempV.replace(",", ".");
                                        //&&voltArchOrig.add(Double.valueOf(varTempV));//
                                        archVoltaje.add(Double.valueOf(varTempV));//
                                        // System.out.println("LInea="+indicadorPosic+"    4 Contenido campo Voltaje"+varTempV);
                                    }
                                }
                                break;
                            case 14: //
                                //** VOLTAJE
                                if (p == offsetposicionP + 9) {
                                    String varTempV = "0.00";

                                    varTempV = ((parts[p]));//

                                    if (varTempV.equals("NULL")) {
                                        varTempV = "0.00";
                                        //&&voltArchOrig.add(Double.valueOf(varTempV));//
                                        archVoltaje.add(Double.valueOf(varTempV));//
                                       // System.out.println("LInea=" + indicadorPosic + "    nuloooooo 4 Voltaje" + varTempV);
                                    } else {
                                        varTempV = varTempV.replace(",", ".");
                                        //&& voltArchOrig.add(Double.valueOf(varTempV));//
                                        archVoltaje.add(Double.valueOf(varTempV));//
                                        //System.out.println("LInea="+indicadorPosic+"    4 Contenido campo Voltaje"+varTempV);
                                    }
                                }

                                break;
                            case (15):
                                //** VOLTAJE
                                if (p == offsetposicionP + 9) {
                                    String varTempV = "0.00";

                                    varTempV = ((parts[p]));//

                                    if (varTempV.equals("NULL")) {
                                        varTempV = "0.00";
                                        //&& voltArchOrig.add(Double.valueOf(varTempV));//
                                        archVoltaje.add(Double.valueOf(varTempV));//
                                       // System.out.println("LInea=" + indicadorPosic + "    nuloooooo 4 Voltaje" + varTempV);
                                    } else {
                                        varTempV = varTempV.replace(",", ".");
                                        //&&voltArchOrig.add(Double.valueOf(varTempV));//
                                        archVoltaje.add(Double.valueOf(varTempV));//
                                        //System.out.println("LInea="+indicadorPosic+"    4 Contenido campo Voltaje"+varTempV);
                                    }
                                }

                                break;

                            case (16):
                                //** VOLTAJE
                                if (p == offsetposicionP + 9) {
                                    String varTempV = "0.00";

                                    varTempV = ((parts[p]));//

                                    if (varTempV.equals("NULL")) {
                                        varTempV = "0.00";
                                        //&&voltArchOrig.add(Double.valueOf(varTempV));//
                                        archVoltaje.add(Double.valueOf(varTempV));//
                                       // System.out.println("LInea=" + indicadorPosic + "    nuloooooo 4 Voltaje" + varTempV);
                                    } else {
                                        varTempV = varTempV.replace(",", ".");
                                        //&&voltArchOrig.add(Double.valueOf(varTempV));//
                                        archVoltaje.add(Double.valueOf(varTempV));//
                                        //System.out.println("LInea="+indicadorPosic+"    4 Contenido campo Voltaje"+varTempV);
                                    }
                                }
                                 break;
                        }//Fin del lazo
//xabc


                    }// fin de generarToken
                } else {   // lee 1era línea de encabezado del archivo que está siendo procesado y se captura la unida múltiplo
                    //System.out.println("contenido linea= "+parts);
                    //System.out.println("Multiplo Unidad del encabezado --> " + parts[25]);
                    String varTemp=parts[25]; // Copia "unidad" entre corchetes  ("[]") del encabezado y se los elimina separando por Split
                    String[] partsMult = varTemp.split("\\[");
                    partsMult=partsMult[1].split("\\]");
                    System.out.println("Multiplo Unidad del encabezado --> " + partsMult[0]);


                    multiploUnidadPotencia=(partsMult[0]); // Este arreglo toma la unidad y multiplos para preservar consistencia

                    // archivo de medición
                }
            } // fin de lectura del archivo de datos

            //-------------------------------
            // chequeo formato fechas
          //  ArrayList<String> fechachequeada= new ArrayList<>();

            chequeoDiaMesAño();// revisa que no hayan archivos con formatos de fecha distintos ya que se han presentado casos

            System.out.println("Fecha chequeada  resultado "+ archFecha);
            //-----------------------------------



            //********************* XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

            Double sumaPotenciaOrg = 0.0;
            Double sumaPotenciaGenerOrig = 0.0;

            System.out.println("Potencia y energia " + archPotencia + " | " + archEnergia);
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

    public void chequeoDiaMesAño() { // esta procedimiento es utilizado por el case para leer y convertir la fecha al formato adecuado
        // inicio-------------------
        List<String> resultadoFecha = new ArrayList<>();
        String dia;
        String mes;
        String ano;
        String[] parts;
        int flagInvertirdia = 0;

        for (int i = 0; i < archFecha.size(); i++) {// chequea que el primer dato sea MM o actva flag de inversion
            String tempArrayFech = (String) archFecha.get(i);
            parts = tempArrayFech.split("/");
           // for(int x=0;x<parts.length;x++){
           //     System.out.println(x+" parte fecha "+ parts[x]);
           // }

            //dia = (parts[0]);
            //int d = Integer.parseInt(dia);
            mes = parts[1];
            int m = Integer.parseInt(mes);
            if (m > 12) {
                flagInvertirdia = 1;
                break;
            }
        }
        for (int i = 0; i < archFecha.size(); i++) {
            if (flagInvertirdia == 1) {
                String tempArrayFech = (String) archFecha.get(i);
                parts = tempArrayFech.split("/");
                dia = (parts[0]);
                mes = (parts[1]);
                ano = (parts[2]);
                resultadoFecha.add(mes + "/" + dia + "/" + ano);
            }
        }
        if (flagInvertirdia == 1) {
            // Se invierte el los valores de dia y mes para el arcivo de medicion analizado
                archFecha.clear();
                archFecha=resultadoFecha;
            System.out.println("cambio formato fecha "+nombArch);

        } else {
            // EL arreglo queda sin alteracion

        } //****  fin del procedimiento captura fecha

    }

    //------------------------------------------------------
}







