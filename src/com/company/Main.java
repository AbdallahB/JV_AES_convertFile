package com.company;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.ParseException;
import java.time.format.TextStyle;


public class Main {


    public static void main(String[] args) throws IOException, ParseException {

       Versionamiento versionamiento= new Versionamiento();// Clase donde se registran los detalles de cada revisión

       System.out.println("respuesta de versionamiento = "+versionamiento.versionActual());

       JFrame framePrincipal=new JFrame(versionamiento.versionActual());

       framePrincipal.setForeground(new Color(153,152,153));
       framePrincipal.setBackground(new Color(0,18,90));
       framePrincipal.setFont(Font.getFont(String.valueOf(Font.ROMAN_BASELINE)));

        framePrincipal.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        framePrincipal.setLayout(new GridLayout(1,2));
        framePrincipal.setSize(620,300); // tamaño del contenedor Jframe
        framePrincipal.setLocationRelativeTo(null);
        String className = UIManager.getSystemLookAndFeelClassName();
        System.out.println("className = " + className);



        //********** implementar metodos border
        // *** implementar metodo boton_________________________________________________________
        ImageIcon logoBaes=new ImageIcon("img\\logo_pie.jpg");
        ImageIcon logoInfo=new ImageIcon("img\\Signo_interrg.jpg");
        ImageIcon logoHelp=new ImageIcon("img\\Help.jpg");
        ImageIcon logoMedidor=new ImageIcon("img\\imgMedidor.jpg");
        ImageIcon logoIndustria1=new ImageIcon("img\\imgIndustria.jpg");
        ImageIcon logoUnificarMedInt=new ImageIcon("img\\UnificarMedInt.png");
        ImageIcon logojava=new ImageIcon("img\\java.png");

        framePrincipal.setIconImage(logoBaes.getImage().getScaledInstance(100,80,Image.SCALE_SMOOTH));




        //______________________________________________________________________________________
        //**** Leer archivo de configuracion para configurar habilitacion de botones y opciones

        // pruebas de carga del setup ###############################################################################
        System.out.println("SE INSTANCIa LA CLASE Setup");
        Setup leerConfigMed=new Setup(); // Se instancia para leer el archivo ConfigMed
        leerConfigMed.inicializaConfiguracionBaseTxt();
        System.out.println("Configuración del Proyecto: "+leerConfigMed.stringConfiguracion());

        // fin de prueba ##############################################################################################




        // ** fin del proceso de lectura del archivo de configuración
        //--------------------------------------------------------------------------------------


        // AGREGAR BOTOM MEDICION

        //Boton BaesMed = corre los controles sobre le formato estandarizado



        //  CASO: Formato 01 EDEA  Base
        JButton btnFormato_01=new JButton("Default");// Etiqueta Boton de Aplicación
        btnFormato_01.setForeground(new Color(153,153,153));
        btnFormato_01.setText("F-01 (EDEA CIRCUTOR1)");

        btnFormato_01.setHorizontalTextPosition(SwingConstants.CENTER);
        btnFormato_01.setVerticalTextPosition(SwingConstants.BOTTOM);

        btnFormato_01.setPreferredSize(new Dimension(190, 110));// Tamaño de boton Aplicacion
        btnFormato_01.setBackground(Color.white);
        btnFormato_01.setBorder(new RoundedBorder(10)); // redondea el borde del contorno
        btnFormato_01.setIcon(new ImageIcon (logoBaes.getImage().getScaledInstance(70,80,Image.SCALE_SMOOTH)));

        // Se agrega el boton EDEA conversion caso especial nro 2
        JButton btnFormato_02=new JButton("Default");// Etiqueta Boton de Aplicación
        btnFormato_02.setForeground(new Color(153,153,153));
        btnFormato_02.setText("F-02 (ECAMEC)");
        btnFormato_02.setHorizontalTextPosition(SwingConstants.CENTER);
        btnFormato_02.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnFormato_02.setPreferredSize(new Dimension(190, 110));// Tamaño de boton Aplicacion
        btnFormato_02.setBackground(Color.white);
        btnFormato_02.setBorder(new RoundedBorder(10)); // redondea el borde del contorno
        btnFormato_02.setIcon(new ImageIcon (logoBaes.getImage().getScaledInstance(70,80,Image.SCALE_SMOOTH)));

        //****** CASO: Formato 03   EDEA
        // Se agrega el boton EDEA conversion caso especial nro 3
        JButton btnFormato_03=new JButton("Default");// Etiqueta Boton de Aplicación
        btnFormato_03.setForeground(new Color(153,153,153));
        btnFormato_03.setText("F-03 (EDEA CIRCUTOR 2)");
        btnFormato_03.setHorizontalTextPosition(SwingConstants.CENTER);
        btnFormato_03.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnFormato_03.setPreferredSize(new Dimension(190, 110));// Tamaño de boton Aplicacion
        btnFormato_03.setBackground(Color.white);
        btnFormato_03.setBorder(new RoundedBorder(10)); // redondea el borde del contorno
        btnFormato_03.setIcon(new ImageIcon (logoBaes.getImage().getScaledInstance(70,80,Image.SCALE_SMOOTH)));

        //****** CASO Formato 04 DISPONIBLE
        // Se agrega el boton EDEA conversion caso especial nro 3
        JButton btnFormato_04=new JButton("Default");// Etiqueta Boton de Aplicación
        btnFormato_04.setForeground(new Color(153,153,153));
        btnFormato_04.setText("F-04 PEQ.DEMANDA");
        btnFormato_04.setHorizontalTextPosition(SwingConstants.CENTER);
        btnFormato_04.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnFormato_04.setPreferredSize(new Dimension(190, 110));// Tamaño de boton Aplicacion
        btnFormato_04.setBackground(Color.white);
        btnFormato_04.setBorder(new RoundedBorder(10)); // redondea el borde del contorno
        btnFormato_04.setIcon(new ImageIcon (logoMedidor.getImage().getScaledInstance(110,75,Image.SCALE_SMOOTH)));

        // CASO: Formato 05 Disponible
        JButton btnFormato_05=new JButton("F-05 M/G DEMANDA");// Etiqueta Boton de Aplicación
        btnFormato_05.setForeground(new Color(153,153,153));
        btnFormato_05.setHorizontalTextPosition(SwingConstants.CENTER);
        btnFormato_05.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnFormato_05.setPreferredSize(new Dimension(190, 110));// Tamaño de boton Aplicacion
        btnFormato_05.setBackground(Color.white);
        btnFormato_05.setBorder(new RoundedBorder(10)); // redondea el borde del contorno
        btnFormato_05.setIcon(new ImageIcon (logoIndustria1.getImage().getScaledInstance(120,75,Image.SCALE_SMOOTH)));

//****** CASO Formato 6
        // Se agrega el boton Formato 6
        JButton btnFormato_06=new JButton("Default");// Etiqueta Boton de Aplicación
        btnFormato_06.setForeground(new Color(153,153,153));
        btnFormato_06.setText("F-06 (EDELAP LANDIS)");
        btnFormato_06.setHorizontalTextPosition(SwingConstants.CENTER);
        btnFormato_06.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnFormato_06.setPreferredSize(new Dimension(190, 110));// Tamaño de boton Aplicacion
        btnFormato_06.setBackground(Color.white);
        btnFormato_06.setBorder(new RoundedBorder(10)); // redondea el borde del contorno
        btnFormato_06.setIcon(new ImageIcon (logoBaes.getImage().getScaledInstance(70,80,Image.SCALE_SMOOTH)));

        // CASO: Formato 07 Baes Med
        JButton btnFormato_07=new JButton("F-07 (EDEN GD)");// Etiqueta Boton de Aplicación
        btnFormato_07.setForeground(new Color(153,153,153));
        btnFormato_07.setHorizontalTextPosition(SwingConstants.CENTER);
        btnFormato_07.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnFormato_07.setIcon(new ImageIcon (logoBaes.getImage().getScaledInstance(70,80,Image.SCALE_SMOOTH)));

        // CASO: Formato 08
        JButton btnFormato_08=new JButton("F-08 (EDEA 10Col)");// Etiqueta Boton de Aplicación
        btnFormato_08.setForeground(new Color(153,153,153));
        btnFormato_08.setHorizontalTextPosition(SwingConstants.CENTER);
        btnFormato_08.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnFormato_08.setIcon(new ImageIcon (logoBaes.getImage().getScaledInstance(70,80,Image.SCALE_SMOOTH)));

        // CASO: Formato 09
        JButton btnFormato_09=new JButton("F-09 (AMI EGGSA)");// Etiqueta Boton de Aplicación
        btnFormato_09.setForeground(new Color(153,153,153));
        btnFormato_09.setHorizontalTextPosition(SwingConstants.CENTER);
        btnFormato_09.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnFormato_09.setIcon(new ImageIcon (logoBaes.getImage().getScaledInstance(70,80,Image.SCALE_SMOOTH)));

        //-------------------------------------------------------------

        // Se incorpora el boton de EDEA al frame
        framePrincipal.getContentPane().add(btnFormato_01,BorderLayout.AFTER_LAST_LINE);
        btnFormato_01.setFocusable(false);
        btnFormato_01.setBorderPainted(false); // atributo activado desde el evento focus button

        // Se incorpora el boton de EDEA espacial Caso 2
        framePrincipal.getContentPane().add(btnFormato_02,BorderLayout.AFTER_LAST_LINE);
        btnFormato_02.setFocusable(false);
        btnFormato_02.setBorderPainted(false); // atributo activado desde el evento focus button



        // CASO 3 EDEA incorpora boton al frame
        // Se incorpora el boton de EDEA espacial Caso 3
        framePrincipal.getContentPane().add(btnFormato_03,BorderLayout.AFTER_LAST_LINE);
        btnFormato_03.setFocusable(false);
        btnFormato_03.setBorderPainted(false); // atributo activado desde el evento focus button

        // CASO TeleMed EDELAP se incorpora boton al frame
        // Se incorpora el boton de EDEA espacial Caso 3
        framePrincipal.getContentPane().add(btnFormato_04,BorderLayout.AFTER_LAST_LINE);
        btnFormato_04.setFocusable(false);
        btnFormato_04.setBorderPainted(false); // atributo activado desde el evento focus button


        // Se incorpora el boton de EDES al frame
        framePrincipal.getContentPane().add(btnFormato_05,BorderLayout.AFTER_LAST_LINE);
        btnFormato_05.setFocusable(false);
        btnFormato_05.setBorderPainted(false); // atributo activado desde el evento focus button

        // Se incorpora el boton Formato 6 al Frame
        framePrincipal.getContentPane().add(btnFormato_06,BorderLayout.AFTER_LAST_LINE);
        btnFormato_06.setFocusable(false);
        btnFormato_06.setBorderPainted(false); // atributo activado desde el evento focus button

        //Formato 07
        framePrincipal.getContentPane().setLayout(new FlowLayout());
        framePrincipal.getContentPane().add(btnFormato_07,BorderLayout.CENTER);
        framePrincipal.getContentPane().setBackground(Color.white);
        framePrincipal.setUndecorated(false);

        //Formato 08
        framePrincipal.getContentPane().setLayout(new FlowLayout());
        framePrincipal.getContentPane().add(btnFormato_08,BorderLayout.CENTER);
        framePrincipal.getContentPane().setBackground(Color.white);
        framePrincipal.setUndecorated(false);

        //Formato 09
        framePrincipal.getContentPane().setLayout(new FlowLayout());
        framePrincipal.getContentPane().add(btnFormato_09,BorderLayout.CENTER);
        framePrincipal.getContentPane().setBackground(Color.white);
        framePrincipal.setUndecorated(false);

        JButton btnAyuda= new JButton("AYUDA");// Etiqueta Boton de Ayuda
        btnAyuda.setForeground(new Color(153,153,153));
        btnAyuda.setHorizontalTextPosition(SwingConstants.CENTER);
        btnAyuda.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnAyuda.setFocusable(false);
        btnAyuda.setIcon(new ImageIcon (logoHelp.getImage().getScaledInstance(70,50,Image.SCALE_SMOOTH)));
        btnAyuda.setBackground(Color.white);
        btnAyuda.setBorderPainted(false);// atributo activado desde el evento focus button

        btnFormato_07.setFocusable(false);
        btnFormato_07.setPreferredSize(new Dimension(190, 110));// Tamaño de boton Aplicacion
        btnFormato_07.setBackground(Color.white);
        btnFormato_07.setBorderPainted(false); // atributo activado desde el evento focus button
        btnFormato_07.setBorder(new RoundedBorder(10)); // redondea el borde del contorno
        btnAyuda.setPreferredSize(new Dimension(190,110)); // Tamaño boton d Ayuda
        btnAyuda.setBorder(new RoundedBorder(10));// redondea el borde del contorno
        Border colorBorde= BorderFactory.createLineBorder(Color.BLUE);

        btnFormato_08.setFocusable(false);
        btnFormato_08.setPreferredSize(new Dimension(190, 110));// Tamaño de boton Aplicacion
        btnFormato_08.setBackground(Color.white);
        btnFormato_08.setBorderPainted(false); // atributo activado desde el evento focus button
        btnFormato_08.setBorder(new RoundedBorder(10)); // redondea el borde del contorno

        btnFormato_09.setFocusable(false);
        btnFormato_09.setPreferredSize(new Dimension(190, 110));// Tamaño de boton Aplicacion
        btnFormato_09.setBackground(Color.white);
        btnFormato_09.setBorderPainted(false); // atributo activado desde el evento focus button
        btnFormato_09.setBorder(new RoundedBorder(10)); // redondea el borde del contorno


        //****** Compilador de Mediciones
        // Se agrega el boton Editar Configuración = ConfigMed.txt
        JButton btnEditarConfigMed=new JButton("Default");// Etiqueta Boton de Aplicación
        btnEditarConfigMed.setForeground(new Color(153,153,153));
        btnEditarConfigMed.setText("Edita ConfigMed");
        btnEditarConfigMed.setHorizontalTextPosition(SwingConstants.CENTER);
        btnEditarConfigMed.setVerticalTextPosition(SwingConstants.BOTTOM);

        btnEditarConfigMed.setPreferredSize(new Dimension(190, 110));// Tamaño de boton Aplicacion
        btnEditarConfigMed.setBackground(Color.white);
        btnEditarConfigMed.setBorder(new RoundedBorder(10)); // redondea el borde del contorno
        btnEditarConfigMed.setIcon(new ImageIcon (logoBaes.getImage().getScaledInstance(70,80,Image.SCALE_SMOOTH)));
        //
        // Se agrega el boton UnificadorMedInt
        JButton btnUnificadorMedInteg=new JButton("Default");// Etiqueta Boton de Aplicación
       btnUnificadorMedInteg.setForeground(new Color(153,153,153));
       btnUnificadorMedInteg.setText("Unificar ArchMedIntg");
       btnUnificadorMedInteg.setHorizontalTextPosition(SwingConstants.CENTER);
       btnUnificadorMedInteg.setVerticalTextPosition(SwingConstants.BOTTOM);

       btnUnificadorMedInteg.setPreferredSize(new Dimension(190, 110));// Tamaño de boton Aplicacion
       btnUnificadorMedInteg.setBackground(Color.white);
       btnUnificadorMedInteg.setBorder(new RoundedBorder(10)); // redondea el borde del contorno
       btnUnificadorMedInteg.setIcon(new ImageIcon (logoUnificarMedInt.getImage().getScaledInstance(150,80,Image.SCALE_SMOOTH)));

        // Se agrega el boton Reportedor del listado de archivos en un directorio
        JButton btnReporteadorListadoArchivos=new JButton("Default");// Etiqueta Boton de Aplicación
        btnReporteadorListadoArchivos.setForeground(new Color(153,153,153));
        btnReporteadorListadoArchivos.setText("Listar Arch.");
        btnReporteadorListadoArchivos.setHorizontalTextPosition(SwingConstants.CENTER);
        btnReporteadorListadoArchivos.setVerticalTextPosition(SwingConstants.BOTTOM);

        btnReporteadorListadoArchivos.setPreferredSize(new Dimension(190, 110));// Tamaño de boton Aplicacion
        btnReporteadorListadoArchivos.setBackground(Color.white);
        btnReporteadorListadoArchivos.setBorder(new RoundedBorder(10)); // redondea el borde del contorno
        btnReporteadorListadoArchivos.setIcon(new ImageIcon (logoBaes.getImage().getScaledInstance(70,80,Image.SCALE_SMOOTH)));





        // Se incorpora el boton Editar configuracion al Frame
        framePrincipal.getContentPane().add(btnEditarConfigMed,BorderLayout.AFTER_LAST_LINE);
        btnEditarConfigMed.setFocusable(false);
        btnEditarConfigMed.setBorderPainted(false); // atributo activado desde el evento focus button

        btnEditarConfigMed.setBorder(new RoundedBorder(10)); // redondea el borde del contorno
        btnEditarConfigMed.setPreferredSize(new Dimension(190,110)); // Tamaño boton d Ayuda
        btnEditarConfigMed.setBorder(new RoundedBorder(10));// redondea el borde del contorno

        // se incorpora el boton UnificarMedIntegrada
        framePrincipal.getContentPane().add(btnUnificadorMedInteg,BorderLayout.AFTER_LAST_LINE);
        btnUnificadorMedInteg.setFocusable(false);
        btnUnificadorMedInteg.setBorderPainted(false); // atributo activado desde el evento focus button

        btnUnificadorMedInteg.setBorder(new RoundedBorder(10)); // redondea el borde del contorno
        btnUnificadorMedInteg.setPreferredSize(new Dimension(190,110)); // Tamaño boton d Ayuda
        btnUnificadorMedInteg.setBorder(new RoundedBorder(10));// redondea el borde del contorno
        //---------------------------------------------------------------------------------------

        // se incorpora el boton Reporteador listado de archivos
        framePrincipal.getContentPane().add(btnReporteadorListadoArchivos,BorderLayout.AFTER_LAST_LINE);
        btnReporteadorListadoArchivos.setFocusable(false);
        btnReporteadorListadoArchivos.setBorderPainted(false); // atributo activado desde el evento focus button

        btnReporteadorListadoArchivos.setBorder(new RoundedBorder(10)); // redondea el borde del contorno
        btnReporteadorListadoArchivos.setPreferredSize(new Dimension(190,110)); // Tamaño boton d Ayuda
        btnReporteadorListadoArchivos.setBorder(new RoundedBorder(10));// redondea el borde del contorno


        //--------------------------------------------------------------------------------------


        //framePrincipal.add(btnAppMed);
        framePrincipal.add(btnAyuda);
        framePrincipal.setVisible(true);
        //btnAppMed.setVisible(true); // visible  Aplicación
        //btnAyuda.setVisible(true); // visible Ayuda

        //***********************************************************************
        // __ Logica para visualizar botones de acuerdo a al archivo ConfigMed.TXT
        // Empieza a contar desde la izquierda Bit 1--> 0|0|0|0|0|0|0|0|0|0 |0 |
        //                                              1|2|3|4|5|6|7|8|9|10|11
        //_________________________________________________________________________
        // Formato 1: EDEA Convert Linea11 del archivo
        int formatoHabilitado0= Integer.parseInt(leerConfigMed.btnHabilitacionFormato.get(0)); // imagen del archivo ConfigMed Linea 11
        if (formatoHabilitado0==1){ //activa visualizador del formato 1
            btnFormato_01.setVisible(true);
        }else{
            btnFormato_01.setVisible(false);
        }

        // Formato 2: Formato 02 (Ecamed)
        int formatoHabilitado1= Integer.parseInt(leerConfigMed.btnHabilitacionFormato.get(1)); // imagen del archivo ConfigMed Linea 11
        if (formatoHabilitado1==1){ //activa visualizador del formato 1
            btnFormato_02.setVisible(true);
        }else{
            btnFormato_02.setVisible(false);
        }
        // Formato 3: Formato_03 (EDEA 800)
        int formatoHabilitado2= Integer.parseInt(leerConfigMed.btnHabilitacionFormato.get(2)); // imagen del archivo ConfigMed Linea 11
        if (formatoHabilitado2==1){ //activa visualizador del formato 1
            btnFormato_03.setVisible(true);
        }else{
            btnFormato_03.setVisible(false);
        }
        // Bit 3: Formato 4: AES Pequeña Demanda
        int formatoHabilitado3= Integer.parseInt(leerConfigMed.btnHabilitacionFormato.get(3)); // imagen del archivo ConfigMed Linea 11
        if (formatoHabilitado3==1){ //activa visualizador del formato 1
            btnFormato_04.setVisible(true);
        }else{
            btnFormato_04.setVisible(false);
        }
        // Bit 4: Formato 5: AES M/G Demanda
        int formatoHabilitado4= Integer.parseInt(leerConfigMed.btnHabilitacionFormato.get(4)); // imagen del archivo ConfigMed Linea 11
        if (formatoHabilitado4==1){ //activa visualizador del formato 1
            btnFormato_05.setVisible(true);
        }else{
            btnFormato_05.setVisible(false);
        }
        // Bit 5: Formato 6: Edelap Reg
        int formatoHabilitado5= Integer.parseInt(leerConfigMed.btnHabilitacionFormato.get(5)); // imagen del archivo ConfigMed Linea 11
        if (formatoHabilitado5==1){ //activa visualizador del formato 1
            btnFormato_06.setVisible(true);
            //btnAppMed.setVisible(true);
        }else{
            //btnAppMed.setVisible(false);
            btnFormato_06.setVisible(false);
        }
        // Bit 6: Formato 7 : Compilador ww
        int formatoHabilitado6= Integer.parseInt(leerConfigMed.btnHabilitacionFormato.get(6)); // imagen del archivo ConfigMed Linea 11
        if (formatoHabilitado6==1 ){ //activa visualizador del formato 1
            btnFormato_07.setVisible(true);
        }else{
            btnFormato_07.setVisible(false);
        }

        // Bit 7 Formato 8: Disponible
        int formatoHabilitado7= Integer.parseInt(leerConfigMed.btnHabilitacionFormato.get(7)); // imagen del archivo ConfigMed Linea 11
        if (formatoHabilitado7==1){ //activa visualizador del formato 1

            btnFormato_08.setVisible(true);
           // System.out.println("se habilita aplicacion Formato 8");

        }else{
           btnFormato_08.setVisible(false);
        }

        // Bit 8 Formato 9: Disponible
        int formatoHabilitado8= Integer.parseInt(leerConfigMed.btnHabilitacionFormato.get(8)); // imagen del archivo ConfigMed Linea 11
        if (formatoHabilitado8==1){ //activa visualizador del formato 1

            btnFormato_09.setVisible(true);
            //System.out.println("se habilita aplicacion Formato 9");

        }else{
            btnFormato_09.setVisible(false);
        }




        //------------------ FIN CONFIGURACION BOTONES

        //********************************************************************************
        //************ AJUSTE DEL TAMAÑO DEL FRAME PRINCIPAL DE ACUERDO A LOS BOTONES QUE SE ACTIVAN
        int k= leerConfigMed.cantidadFormatoxActivarse();

        System.out.println("Cantidad de botones a ser activado= "+ k);
        if (k<=2){
            framePrincipal.setSize((700 + 50 * (1 + k-1)), (170 + (85 * k / 6))); // tamaño del contenedor Jframe
        }else {
            framePrincipal.setSize((400 + 50 * (1 + k)), (300 + (100 * k / 6))); // tamaño del contenedor Jframe
        }
        // ---------------------------------------- fIN DE AJUSTE D ELOS BOTONES ---------

        //........................................................>
        // LOGICA PARA CONFIGURAR LOS BOTONES DE COMPILACION y ABORTAR CORRIDA SIN NIVEL DE AUTORIZACION
        int varHabilitaCompilador= Integer.parseInt(leerConfigMed.getBtnHabIntegrador().get(0));// linea 11
                                                                                                // Flag 0 izq =habilita compilacion
        if (varHabilitaCompilador==1){// hace visibles los botones adicionales cuando se habilita la compilacion
            if (leerConfigMed.equivCodigoHash(leerConfigMed.codigoApp).equals("Nivel-3")){
                System.out.println("habilitado control de compilacion nivel 3");
                framePrincipal.setSize((600 + 50 * (1 + k)), (300 + (100 * k / 6)));
            }else {// En esta condicion bloquea el programa

                System.out.println("NO TIENE PERMISO PARA REALIZAR CAMBIOS CONFIGURACION");
                JOptionPane.showMessageDialog(null, "No está autorizado a estas funciones con "
                        +leerConfigMed.equivCodigoHash(leerConfigMed.codigoApp)+ " como código, consulte a BAES!!!");
                System.exit(0);

            }
            btnEditarConfigMed.setVisible(true);
            btnUnificadorMedInteg.setVisible(true);
            btnReporteadorListadoArchivos.setVisible(true);

        }else{
            btnEditarConfigMed.setVisible(false);
            btnUnificadorMedInteg.setVisible(false);
            btnReporteadorListadoArchivos.setVisible(false);
        }


        //------------------------------------fin de la lógica para configurar los botones




        //_________________________ Añadir cuadro de texto con la configuración inicial

        JLabel label=new JLabel("x");
        label.setText("valor del codigo");
        framePrincipal.getContentPane().add(label);
        label.setVisible(false);



        //--------------------------------------------------------------------------------------------------------

        btnAyuda.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) { }
            @Override
            public void mousePressed(MouseEvent e) {  }
            @Override
            public void mouseReleased(MouseEvent e) {  }
            @Override
            public void mouseEntered(MouseEvent e) {
                btnAyuda.setBorderPainted(true);// coloca contorno boton
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnAyuda.setBorderPainted(false);// suprime contorno del boton
            }
        });

        btnFormato_01.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnFormato_01.setBorderPainted(true);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnFormato_01.setBorderPainted(false);

            }
        });

        btnFormato_01.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("se ha oprimido el boton de EDEA Conversion FORMATO_01");

               // LecturaArchEDEA lecturaArchEDEA=new LecturaArchEDEA(); // se instancia la clase para leer
                AbsMedicionesFormato_01 absMedicionesFormat01 =new AbsMedicionesFormato_01("FORMATO_01",framePrincipal);

                absMedicionesFormat01.execute();// Se ejecuta el método principal doInBackground donde ejecuta
                                                // metodoPoliforfismo "PrincipalDevMediciones()"
                                                //absMedicionesFormat01.PrincipalDevMediciones();
                framePrincipal.setVisible(false);

            }
        });
        btnFormato_02.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("se ha oprimido el boton de EDEA Caso Especial 2");

                //LecturaArchEDEACasoEspecial2 lecturaArchEDEACasoEspecial2=new LecturaArchEDEACasoEspecial2(); // Instancia clase EDEA Caso especial
                AbsMedicionesFormato_02 absMedicionesFormat02 =new AbsMedicionesFormato_02("FORMATO_02",framePrincipal);

                framePrincipal.setVisible(false);
                absMedicionesFormat02.execute();// Se ejecuta el método principal doInBackground donde ejecuta
                                                // metodoPoliforfismo "PrincipalDevMediciones()"
            }
        });

        btnFormato_02.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnFormato_02.setBorderPainted(true); // activa linea de contorno boton

            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnFormato_02.setBorderPainted(false);// desactiva linea de contorno boton

            }
        });

        //****
        btnFormato_03.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnFormato_03.setBorderPainted(true); // activa linea de contorno boton
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnFormato_03.setBorderPainted(false); // activa linea de contorno boton
            }
        });

        btnFormato_04.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnFormato_04.setBorderPainted(true);// pinta contorno del boton
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnFormato_04.setBorderPainted(false);// pinta contorno del boton
            }
        });

        //*****





        btnFormato_07.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {      }
            @Override
            public void mousePressed(MouseEvent e) {      }
            @Override
            public void mouseReleased(MouseEvent e) {     }
            @Override
            public void mouseEntered(MouseEvent e) {
            btnFormato_07.setBorderPainted(true); // activa linea de contorno boton
            }

            @Override
            public void mouseExited(MouseEvent e) {
            btnFormato_07.setBorderPainted(false);// desactiva linea de contorno boton
                }
        });

       btnFormato_07.addFocusListener(new FocusListener() {
                                         @Override
                                         public void focusGained(FocusEvent e) {
                                             btnFormato_07.setFocusable(true);
                                         }

                                         @Override
                                         public void focusLost(FocusEvent e) {
                                             btnFormato_07.setFocusable(false);
                                         }
                                     });

        //***************** telemed y caso 3 edea
        //*********
        btnFormato_03.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("se ha oprimido el boton de Caso especial 3");

                framePrincipal.setVisible(false);
                AbsMedicionesFormato_03 absMedicionesFormat03 =new AbsMedicionesFormato_03("FORMATO_03",framePrincipal);

                //lecturaArchEDEACasoEsp3.principalLecturaArchEdeaC3();
                absMedicionesFormat03.execute();// Se ejecuta el método principal doInBackground donde ejecuta
                                                 // metodoPoliforfismo "PrincipalDevMediciones()"
            }
        });
btnFormato_04.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("se ha oprimido el boton de Caso AES Pequeña Demanda");

        AbsMedicionesFormato_04 absMedicionesFormat04 =new AbsMedicionesFormato_04("FORMATO_04",framePrincipal);// Se instancia Formato

        framePrincipal.setVisible(false);// bloquea al usuario el frame principal
        absMedicionesFormat04.execute();// Se ejecuta el método principal doInBackground donde ejecuta
                                        // metodoPoliforfismo "PrincipalDevMediciones()"
                                        //  Se ejecuta el método principal doInBackground donde ejecuta
                                        // metodoPoliforfismo "PrincipalDevMediciones()"

    }
});   // ***************   fin

btnEditarConfigMed.addMouseListener(new MouseListener() {
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        btnEditarConfigMed.setBorderPainted(true);// pinta contorno del boton
    }

    @Override
    public void mouseExited(MouseEvent e) {
        btnEditarConfigMed.setBorderPainted(false);// pinta contorno del boton
    }
});

   btnEditarConfigMed.addActionListener(new ActionListener() {
       @Override
       public void actionPerformed(ActionEvent e) {
// System.out.println(" Se activo el boton aplicacion");

         //cfg Editor de configuracion
           try {
               Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+"ConfigMed.TXT"); // carga el manual de ayuda
           } catch (IOException ex) {
               ex.printStackTrace();
               JOptionPane.showMessageDialog(null, "Ocurrió un error abriendo el archivo de configuracion ConfigMed.TXT");
           }
       }
   });

      // Opcion para implementar el formato_07 EDEN carga concetrada Grandes demandas

        btnFormato_07.addActionListener(new ActionListener() { // button AppMed= Baes Mediciones
                                           @Override
                                           public void actionPerformed(ActionEvent e) {
                                               System.out.println(" Se activo el boton aplicacion Formato_07");

                                               // Nuevo
                                               AbsMedicionesFormato_07 absMedicionesFormat03 =new AbsMedicionesFormato_07("FORMATO_07",framePrincipal);

                                               framePrincipal.setVisible(false);
                                               absMedicionesFormat03.execute();// Se

                                               //Nuevo




                                               //AbsMediciones absmediciones=new AbsMediciones("FORMATO_07");// formato 7 clase base "Abstracta"  AbsMediciones

                                               //absmediciones.execute();// Se ejecuta el método principal doInBackground donde ejecuta
                                               // metodoPoliforfismo "PrincipalDevMediciones()"
                                               // absmediciones.principalDevMediciones(); // Activa la clase Medición llamando al programa de medición y Archivo
                                               // System.out.println("activa el boton");

                                           }
                                       }  );
        //----------------------------------------------------------
        btnAyuda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println(" se activo el boton de Ayuda");

                try {
                       // SE DIRECCIONA EL ACCESO AL ARCHIVO MANUAL DE AYUDA
                    Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+"Manual de BAES Mediciones - V 3.0.5.PDF"); // carga el manual de ayuda

                    // Las siguientes lineas despliegan la configuracion  en el FramePrincipal
                    // instancia la clase Setup y lee archivo de configuracion
                    // corre el metodo leer configuracion
                    //Setup leerConfigMed=new Setup();
                    //leerConfigMed.inicializaConfiguracionBaseTxt();
                    //System.out.println("Configuracion Cargada: "+leerConfigMed.stringConfiguracion());

                    //leerConfigMed.mostrarConfiguracionCargada();
                 //  label.setFont(Font.getFont(Font.SANS_SERIF));
                 //  //zz

                 //  //------------------
                 //label.setForeground(new Color(28,28,144));

                 //  //---------------
                 //  label.setFont(new Font("Arial", Font.ROMAN_BASELINE, 11));
                 //  label.setText(leerConfigMed.stringConfiguracion());
                 //  label.setVisible(true); // hace visible la etiqueta dentro del frame "JLabel" con el código de configuracion

                } catch (IOException err) {
                    err.printStackTrace();
                }

            }

        });//// fin action listener method

        // inicio listener Button EDES converter

        btnFormato_05.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
            btnFormato_05.setBorderPainted(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {

                btnFormato_05.setBorderPainted(false);

            }
        }); // fin EDES mouse Listener

        btnFormato_05.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(" Se ha activado el boton de conversión de EDES");

                AbsMedicionesFormato_05 absMedicionesFormat05 =new AbsMedicionesFormato_05("FORMATO_05",framePrincipal);// Se instancia Formato

                framePrincipal.setVisible(false);// bloquea al usuario frame padre

                absMedicionesFormat05.execute();// Se ejecuta el método principal doInBackground donde ejecuta
                // metodoPoliforfismo "PrincipalDevMediciones()"
                //  Se ejecuta el método principal doInBackground donde ejecuta

            }
        });  // fin  bloque de listener

//-------------------------------------------------------------

        btnFormato_06.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnFormato_06.setBorderPainted(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnFormato_06.setBorderPainted(false);
            }
        });

        btnFormato_06.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("activado boton 6");

                AbsMedicionesFormato_06 absMedicionesFormat06 =new AbsMedicionesFormato_06("FORMATO_06",framePrincipal);
                framePrincipal.setVisible(false);//bloquea al usuario frame padre

                absMedicionesFormat06.execute();// Se ejecuta el método principal doInBackground donde ejecuta
                                                    // metodoPoliforfismo "PrincipalDevMediciones()"
            }
        });

        //--------------------------------



        btnUnificadorMedInteg.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnUnificadorMedInteg.setBorderPainted(true);


            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnUnificadorMedInteg.setBorderPainted(false);

            }
        });


        btnUnificadorMedInteg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Se activó el btnUnificadorMedIntg");

                UnificarMedIntegradas unificarMedIntegradas=new UnificarMedIntegradas(framePrincipal);

                unificarMedIntegradas.execute();

                if (unificarMedIntegradas.isDone()){
                    System.out.println("completado el proceso"+unificarMedIntegradas.isDone());
                }else {
                    System.out.println("valor de isdone="+unificarMedIntegradas.isDone());
                }


            }
        });
        //------------------------------------------------
        btnReporteadorListadoArchivos.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnReporteadorListadoArchivos.setBorderPainted(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnReporteadorListadoArchivos.setBorderPainted(false);
            }
        });

        btnReporteadorListadoArchivos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Se activó el btnReporteadorArchivos");
                ReporteadorArchivosDirectorio reporteadorArchivosDirectorio=new ReporteadorArchivosDirectorio();
                try {
                    reporteadorArchivosDirectorio.listarArchivos();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        //-------------------------------------------------
        btnFormato_08.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnFormato_08.setBorderPainted(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnFormato_08.setBorderPainted(false);
            }
        });
        btnFormato_08.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Se pulso el Formato 08");

                AbsMedicionesFormato_08 absMedicionesFormat08 =new AbsMedicionesFormato_08("FORMATO_08",framePrincipal);// Se instancia Formato

                framePrincipal.setVisible(false);// bloquea al usuario el frame principal
                absMedicionesFormat08.execute();// Se ejecuta el método principal doInBackground donde ejecuta
                // metodoPoliforfismo "PrincipalDevMediciones()"
                //  Se ejecuta el método principal doInBackground donde ejecuta
                // metodoPoliforfismo "PrincipalDevMediciones()"
            }
        });

        //----------------------------------------------------
        btnFormato_09.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnFormato_09.setBorderPainted(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnFormato_09.setBorderPainted(false);
            }
        });

        btnFormato_09.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Se pulso el Formato 09");

                AbsMedicionesFormato_09 absMedicionesFormat09 =new AbsMedicionesFormato_09("FORMATO_09",framePrincipal);// Se instancia Formato

                framePrincipal.setVisible(false);// bloquea al usuario el frame principal
                absMedicionesFormat09.execute();// Se ejecuta el método principal doInBackground donde ejecuta
                // metodoPoliforfismo "PrincipalDevMediciones()"
                //  Se ejecuta el método principal doInBackground donde ejecuta
                // metodoPoliforfismo "PrincipalDevMediciones()"





            }



        });






    } // fin Main...




}