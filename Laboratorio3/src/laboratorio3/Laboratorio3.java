package Laboratorio3;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class Laboratorio3 {
    private static int tiempoAlarma = 0; // Tiempo para la alarma en segundos
    private static Timer timerAlarma; // Timer para la alarma
    private static int segundos = 0; // Segundos para el cronómetro
    private static Timer timerCronometro; // Timer para el cronómetro

    public static void main(String[] args) {
        JFrame frame = new JFrame("Cronómetro");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(null);

        JLabel labelHora = new JLabel("00:00:00");
        labelHora.setBounds(100, 30, 100, 30);
        frame.add(labelHora);

        JButton botonIniciar = new JButton("Iniciar");
        botonIniciar.setBounds(20, 70, 100, 30);
        frame.add(botonIniciar);

        JButton botonDetener = new JButton("Detener");
        botonDetener.setBounds(140, 70, 100, 30);
        frame.add(botonDetener);

        JButton botonConfigurar = new JButton("Configurar Alarma");
        botonConfigurar.setBounds(20, 110, 220, 30);
        frame.add(botonConfigurar);

        JTextField campoTiempo = new JTextField("Tiempo (minutos)");
        campoTiempo.setBounds(20, 150, 150, 30);
        frame.add(campoTiempo);

        timerCronometro = new Timer(); // Inicializamos el Timer

        botonIniciar.addActionListener(e -> {
            timerCronometro = new Timer();
            timerCronometro.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    segundos++;
                    int horas = segundos / 3600;
                    int minutos = (segundos % 3600) / 60;
                    int sec = segundos % 60;
                    labelHora.setText(String.format("%02d:%02d:%02d", horas, minutos, sec));
                }
            }, 0, 1000); // Actualizar cada segundo
        });

        botonDetener.addActionListener(e -> {
            timerCronometro.cancel();
        });

        botonConfigurar.addActionListener(e -> {
            try {
                int minutos = Integer.parseInt(campoTiempo.getText());
                tiempoAlarma = minutos * 60; // Convertir minutos a segundos
                iniciarAlarma();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Ingrese un número válido");
            }
        });

        frame.setVisible(true);
    }

    private static void iniciarAlarma() {
        if (timerAlarma != null) {
            timerAlarma.cancel();
        }
    }

}
