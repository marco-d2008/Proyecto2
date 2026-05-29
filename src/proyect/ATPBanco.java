package proyect;

import java.util.ArrayList;
import java.util.Scanner;
import javax.sound.sampled.*; 
import java.io.File;

public class ATPBanco {

    private static double saldo = 1000.0;
    private static String pin = "1234";
    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<String> historial = new ArrayList<>();

   
    private static void reproducirSonido(String ruta) {
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(new File(ruta));
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        } catch (Exception e) {
           
        }
    }

    public static void main(String[] args) {
        if(login()) {
            reproducirSonido("success.wav"); 
            menu();
        } else {
            reproducirSonido("error.wav"); 
            System.out.println("PIN incorrecto. Acceso denegado.");
        }
    }

    // LOGIN CON 3 INTENTOS
    private static boolean login() {
        int intentos = 3;

        while(intentos > 0) {
            System.out.print("Ingrese su PIN: ");
            String ingreso = sc.nextLine();

            if(ingreso.equals(pin)) {
                System.out.println("Acceso concedido.");
                
                return true;
            } else {
                intentos--;
                reproducirSonido("error.wav"); 
                if(intentos > 0) {
                    System.out.println("PIN incorrecto.");
                    System.out.println("Intentos restantes: " + intentos);
                }
            }
        }

        System.out.println("Ha excedido el número de intentos.");
        return false;
    }

    private static void menu() {
        int opcion;

        do {
            System.out.println("\n  MENÚ CAJERO ");
            System.out.println("1. Consultar saldo");
            System.out.println("2. Depositar");
            System.out.println("3. Retirar");
            System.out.println("4. Cambiar PIN");
            System.out.println("5. Ver historial");
            System.out.println("6. Transacciones");
            System.out.println("7. Transferir dinero");
            System.out.println("8. Salir");

            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); 

            switch(opcion) {
                case 1: consultarSaldo(); break;
                case 2: depositar(); break;
                case 3: retirar(); break;
                case 4: cambiarPin(); break;
                case 5: verHistorial(); break;
                case 6: hacerTransacciones(); break;
                case 7: transferir(); break;
                case 8: reproducirSonido("success.wav"); 
                        System.out.println("Gracias por usar el cajero."); break;
                default: reproducirSonido("error.wav"); 
                         System.out.println("Opción inválida.");
            }

        } while(opcion != 8);
    }

    private static void consultarSaldo() {
        reproducirSonido("success.wav");
        System.out.println("Su saldo actual es: $" + saldo);
    }

    private static void depositar() {
        System.out.print("Ingrese monto a depositar: ");
        double monto = sc.nextDouble();

        if(monto > 0) {
            saldo += monto;
            historial.add("Depósito: $" + monto);
            reproducirSonido("success.wav"); 
            System.out.println("Depósito exitoso. Nuevo saldo: $" + saldo);
        } else {
            reproducirSonido("error.wav"); 
            System.out.println("Monto inválido.");
        }
    }

    private static void retirar() {
        System.out.print("Ingrese monto a retirar: ");
        double monto = sc.nextDouble();

        if(monto > 0 && monto <= saldo) {
            saldo -= monto;
            historial.add("Retiro: $" + monto);
            reproducirSonido("success.wav"); 
            System.out.println("Retiro exitoso. Nuevo saldo: $" + saldo);
        } else {
            reproducirSonido("error.wav"); 
            System.out.println("Monto inválido o insuficiente.");
        }
    }

    private static void transferir() {
        System.out.print("Ingrese el nombre del destinatario: ");
        String destinatario = sc.nextLine();

        System.out.print("Ingrese el monto a transferir: ");
        double monto = sc.nextDouble();
        sc.nextLine(); 

        if(monto > 0 && monto <= saldo) {
            saldo -= monto;
            historial.add("Transferencia de $" + monto + " enviada a " + destinatario);
            reproducirSonido("success.wav"); 
            System.out.println("Transferencia realizada exitosamente.");
            System.out.println("Se enviaron $" + monto + " a " + destinatario);
            System.out.println("Saldo restante: $" + saldo);
        } else {
            reproducirSonido("error.wav"); 
            System.out.println("Monto inválido o saldo insuficiente.");
        }
    }

    private static void cambiarPin() {
        System.out.print("Ingrese nuevo PIN: ");
        String nuevoPin = sc.nextLine();

        if(nuevoPin.length() >= 4) {
            pin = nuevoPin;
            historial.add("Cambio de PIN realizado.");
            reproducirSonido("success.wav"); 
            System.out.println("PIN cambiado exitosamente.");
        } else {
            reproducirSonido("error.wav"); 
            System.out.println("PIN inválido. Debe tener al menos 4 dígitos.");
        }
    }

    private static void verHistorial() {
        System.out.println("\n--- HISTORIAL DE TRANSACCIONES ---");
        reproducirSonido("success.wav"); 

        if(historial.isEmpty()) {
            System.out.println("No hay transacciones registradas.");
        } else {
            for(String transaccion : historial) {
                System.out.println(transaccion);
            }
        }
    }

    private static void hacerTransacciones() {
        int opcion;

        do {
            System.out.println("\n--- TRANSACCIONES ---");
            System.out.println("1. Depositar");
            System.out.println("2. Retirar");
            System.out.println("3. Consultar saldo");
            System.out.println("4. Transferir dinero");
            System.out.println("5. Finalizar transacciones");

            System.out.print("Seleccione una acción: ");
            opcion = sc.nextInt();
            sc.nextLine(); 

            switch(opcion) {
                case 1: depositar(); break;
                case 2: retirar(); break;
                case 3: consultarSaldo(); break;
                case 4: transferir(); break;
                case 5: reproducirSonido("success.wav"); 
                        System.out.println("Transacciones finalizadas."); break;
                default: reproducirSonido("error.wav"); 
                         System.out.println("Opción inválida.");
            }

        } while(opcion != 5);
    }
}