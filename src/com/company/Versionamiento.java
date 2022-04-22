package com.company;

public class Versionamiento {


    public String versionActual(){
        String version="BAES MEDICIONES 2021 - R 3.0.6 "; // ultima revision

        return(version);
    }

    // HISTORIA DE LAS VERSIONES

    //==========================================================================================================
    //Version  R 3.0.6    05/10/2021
    // 1) Se sobre escribe el formato F-9 de EDEA por la adaptacion el AMI, en caso de requerir leer el formato EDEA, cargar version
    //    V3.0.5, la muestra se encuentra en formatos
    // 2) Muestra los errores cronológicos dentro del resultado de las calificaciones en los reportes, impidiendo ls compilación
    // ==================================================
    //Version  R 3.0.5    8/09/2021
    // 1) Se reactiva la detección de chequeo o calificación por cadencia por default 15min
    // 2) Se carga el calendario para poder asignarle la fecha que debe tener el dia siguiente por cambio de hora hora "24:00:00"
    // 3) En revisión detección de bloques de datos repetidos
    //==========================================================================================================
    //Version  R 3.0.4    8/6/2021  - Se mejora la deteccion de errores y se agrega indicador de calificacion "I"
    // 1) Se agrega el indicador de calificacion "I" para señalar cuan la E_reactiva > E_real
    // 2) Se mejora la deteccion de error de formato de fecha, con casos que no se detectaban
    // 3) se corrige el caso en que todos los registros tienen fallas de formato y evita el exit(0)
    // 4) Se agrega al configMed.txt en linea nueve un digito Mas para controlar el indicador "I"
    //-----------------------------------------------------------------------------
    //Version  R 3.03    12/05/2021  - Se incorpora a los formatos 04 y 05 deteccion de los errores recuperables eliminando la línea
                                     // 1) Error "||||" o "x|x||x" o "x|x|x||"se elimina la linea
                                     // 2) Error de digitos en el año "mm/dd/AA" se elimina la linea
                                     // 3) Error de digitos año< 4 digito se elimina la linea
                                     // 4) Acceso unico al versionamiento mediante la clase Versionamiento
    //Version  R 3.02    12/05/2021  - Se incorpora a los formatos 04 y 05 deteccion de los errores Y acceso unico a versionamiento:
                                     // 1) Error de año que no comprende 4 digitos como el formato "/AAAA"
                                     // 2) Error de mínima cantidad de campos en Blinea debes ser minimo 4, la anormalidad en opcional
                                     // 3) Acceso unico al versionamiento mediante la clase Versionamiento
    //Version  R 3.0.1   xx/04/2021  - Se ajusta la aceptación de archivos Formato 05 para tolerar NIS y NIC de AES=7 y DELSUR=9 digitos
    //Version: R 3.0.0.1 08/03/2021  - corregido progressBar para sincronizar el fin de la barra de actualizacion con el procesamiento
    //- creado el método leerArchivoUsuarioFormato0405 para ajustlo al archivo de UsuarioRecibido
    //- ajustada la salida de MedIntegrda para incorporar el campo UPR en la compilacion
    //- Corregido el formato de la fecha y hora de los reportes - Todos los Formatos
    //Version: R 3.0.0 11/02/2021  - Incorporacion de los ajustes del cálculo a la pequeña demanda
    //Version: R 2.0.07 10/02/2021 - Ajuste en calculos min max energia total, para indicar aquellos que no tienen anomalos, cambio en la leyenda
    //formato 05 y 04
    //Version: R 2.0.06 01/02/2021 - Se cambian el formato de fecha formato 04 & 05; Se ajusta adempas los metodos de calificacion
    //Version: R 2.0.05 19/01/2021 - En el integrador de MedIntegradas, se incorpora generar un archivo con la lista de archivos que se estarian compilando
    //Version: R 2.0.04 19/01/2021 - Se incorpora en contarLineas un break para romper el lazo While despues del encabezado
    //                               que permite leer lineas para clasificar el archivo. ACELERA EL PROGRAMA AL SELECCIONAR LOS ARCHIVOS DE FORMA MÁS RÁPIDA
    //Version: R 2.0.03 18/01/2021 - Se implementa el bloqueo al usuario del frame padre. Se incorpora la clase Unificar MedIntegrada
    //
    //                               ---------------------------------
    //Version: R 2.0.02 27/11/2020 - Queda funcionando los 4 formatos F1, 2 , 3, 4, 6 y compilacion
    //Versión: R 2.0.01 25/11/2020 - Incorpora y lee del setup opciones de compilacion: 8opciones + codigosCalificacion
    // Versión: R 2.0 09/11/2020   - Incorpora 2 modulos de conversion formato 3 de EDEA y 1 formato estandar de telemedicion
    // Configuracion de los btn mediante el archivo ConfigMed.txt
    // Versión: R 1.0 21/07/2020   - Incorpora 2 modulos de conversion formato 3 de EDEA y 1 formato estandar de telemedicion

    //2.0 Normalizacion de Formatos por clase AbsArchivoFormatox y AbsMedicionesFormatoX
    //1. Revision configuracion desde archivo y opciones de habilitacion
    //________________________________________________________________________


}
