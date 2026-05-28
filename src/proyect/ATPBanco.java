package proyect;
import java.util.ArrayList;
import java.util.Scanner;

public class ATPBanco {


		// TODO Auto-generated method stub
	private static double saldo = 1000.0;
    private static String pin = "1234";
    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<String> historial = new ArrayList<>();

    public static void main(String[] args) {
        if(login()) {
            menu();
        } else {
            System.out.println("PIN incorrecto. Acceso denegado.");
        }
    }

    private static boolean login() {
        System.out.print("Ingrese su PIN: ");
        String ingreso = sc.nextLine();
        return ingreso.equals(pin);
    }

    private static void menu() {
        int opcion;
        do {
            System.out.println("\n--- MENÚ CAJERO ---");
            System.out.println("1. Consultar saldo");
            System.out.println("2. Depositar");
            System.out.println("3. Retirar");
            System.out.println("4. Cambiar PIN");
            System.out.println("5. Ver historial");
            System.out.println("6. Transacciones"); // NUEVA OPCIÓN
            System.out.println("7. Salir");

            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch(opcion) {
                case 1: consultarSaldo(); break;
                case 2: depositar(); break;
                case 3: retirar(); break;
                case 4: cambiarPin(); break;
                case 5: verHistorial(); break;
                case 6: hacerTransacciones(); break; // NUEVA FUNCIÓN
                case 7: System.out.println("Gracias por usar el cajero."); break;
                default: System.out.println("Opción inválida.");
            }
        } while(opcion != 7);
    }

    private static void consultarSaldo() {
        System.out.println("Su saldo actual es: $" + saldo);
    }

    private static void depositar() {
        System.out.print("Ingrese monto a depositar: ");
        double monto = sc.nextDouble();
        if(monto > 0) {
            saldo += monto;
            historial.add("Depósito: $" + monto);
            System.out.println("Depósito exitoso. Nuevo saldo: $" + saldo);
        } else {
            System.out.println("Monto inválido.");
        }
    }

    private static void retirar() {
        System.out.print("Ingrese monto a retirar: ");
        double monto = sc.nextDouble();
        if(monto > 0 && monto <= saldo) {
            saldo -= monto;
            historial.add("Retiro: $" + monto);
            System.out.println("Retiro exitoso. Nuevo saldo: $" + saldo);
        } else {
            System.out.println("Monto inválido o insuficiente.");
        }
    }

    private static void cambiarPin() {
        System.out.print("Ingrese nuevo PIN: ");
        String nuevoPin = sc.nextLine();
        if(nuevoPin.length() >= 4) {
            pin = nuevoPin;
            historial.add("Cambio de PIN realizado.");
            System.out.println("PIN cambiado exitosamente.");
        } else {
            System.out.println("PIN inválido. Debe tener al menos 4 dígitos.");
        }
    }

    private static void verHistorial() {
        System.out.println("\n--- HISTORIAL DE TRANSACCIONES ---");
        if(historial.isEmpty()) {
            System.out.println("No hay transacciones registradas.");
        } else {
            for(String transaccion : historial) {
                System.out.println(transaccion);
            }
        }
    }

    // NUEVA FUNCIÓN: Transacciones múltiples
    private static void hacerTransacciones() {
        int opcion;
        do {
            System.out.println("\n--- TRANSACCIONES ---");
            System.out.println("1. Depositar");
            System.out.println("2. Retirar");
            System.out.println("3. Consultar saldo");
            System.out.println("4. Finalizar transacciones");

            System.out.print("Seleccione una acción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch(opcion) {
                case 1: depositar(); break;
                case 2: retirar(); break;
                case 3: consultarSaldo(); break;
                case 4: System.out.println("Transacciones finalizadas."); break;
                default: System.out.println("Opción inválida.");
            }
        } while(opcion != 4);
    }
}