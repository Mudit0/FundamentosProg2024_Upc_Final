import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class ExamenFinal {

    private static Map<Integer, String> paquetes = new HashMap<>();
    private static Map<Integer, String> transportistas = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);
    private static String nombreCliente;
    private static String apellidosCliente;
    private static String dniCliente;
    private static int pesoPaquete;
    private static double costoEnvio = 2.50;
    private static String direccion;

    public static void main(String[] args) {
        int opcion;

        do {
            System.out.println("#=========================================#");
            System.out.println("Bienvenido al sistema logístico");
            System.out.println("1. Registrar un nuevo paquete");
            System.out.println("2. Asignar un transportista a un paquete");
            System.out.println("3. Seguimiento en tiempo real de un paquete");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    registrarPaquete();
                    break;
                case 2:
                    asignarTransportista();
                    break;
                case 3:
                    seguimientoEnTiempoReal();
                    break;
                case 4:
                    System.out.println("Saliendo del sistema logístico...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 4);
    }

    public static void registrarPaquete() {
        System.out.println("#=========================================#");
        System.out.print("Ingrese el contenido del paquete: ");
        String contenido = scanner.nextLine();
        int idPaquete = generarId();

        limpiarBuffer(scanner);

        System.out.print("Nombre del cliente: ");
        nombreCliente = scanner.nextLine();

        System.out.print("Apellidos del cliente: ");
        apellidosCliente = scanner.nextLine();

        System.out.print("DNI del cliente: ");
        dniCliente = scanner.nextLine();

        System.out.print("Peso del paquete (Kg): ");
        pesoPaquete = scanner.nextInt();

        limpiarBuffer(scanner);

        System.out.print("Direccion de envio: ");
        direccion = scanner.nextLine();

        paquetes.put(idPaquete, contenido);
        System.out.println("Paquete registrado con ID: " + idPaquete);
        System.out.println("Costo de envio: S/" + costoEnvio);
    }

    public static void asignarTransportista() {
        System.out.println("#=========================================#");
        System.out.print("Ingrese el ID del paquete: ");
        int idPaquete = scanner.nextInt();
        limpiarBuffer(scanner);
        if (paquetes.containsKey(idPaquete)) {
            System.out.print("Ingrese el nombre del transportista: ");
            String transportista = scanner.nextLine();
            transportistas.put(idPaquete, transportista);
            System.out.println("Transportista asignado al paquete con ID " + idPaquete + ": " + transportista);
        } else {
            System.out.println("El paquete con ID " + idPaquete + " no está registrado.");
        }
    }

    public static void seguimientoEnTiempoReal() {
        System.out.println("#=========================================#");
        System.out.print("Ingrese el ID del paquete para seguimiento: ");
        int idPaquete = scanner.nextInt();

        if (paquetes.containsKey(idPaquete) && transportistas.containsKey(idPaquete)) {
            System.out.println("Estado del paquete con ID " + idPaquete + ":");
            System.out.println("Contenido: " + paquetes.get(idPaquete));
            System.out.println("Transportista asignado: " + transportistas.get(idPaquete));
            System.out.println("Ubicación actual: " + obtenerUbicacionAleatoria());
            System.out.println("Nombre de Cliente: " + nombreCliente);
            System.out.println("Apellidos de Cliente: " + apellidosCliente);
            System.out.println("DNI de Cliente: " + dniCliente);
            System.out.println("Peso del paquete: " + pesoPaquete + " kg");
            System.out.println("Direccion: " + direccion);
            System.out.println("Importe a pagar: S/" + (costoEnvio*pesoPaquete));
        } else {
            System.out.println("El paquete con ID " + idPaquete + " no se encuentra registrado o no tiene asignado un transportista.");
        }
    }

    private static int generarId() {
        Random random = new Random();
        return random.nextInt(1000);
    }

    private static String obtenerUbicacionAleatoria() {
        Random random = new Random();
        String[] ubicaciones = {"Almacén central", "Centro de distribución", "En ruta", "Entregado"};
        int index = random.nextInt(ubicaciones.length);
        return ubicaciones[index];
    }

    private static void limpiarBuffer(Scanner scanner) {
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
    }
}
