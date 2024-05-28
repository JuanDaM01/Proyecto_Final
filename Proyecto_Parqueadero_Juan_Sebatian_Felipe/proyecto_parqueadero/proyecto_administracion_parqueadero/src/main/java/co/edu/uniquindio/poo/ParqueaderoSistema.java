package co.edu.uniquindio.poo;

import javax.swing.JOptionPane;
import java.util.Scanner;

public class ParqueaderoSistema {
    private static Scanner scanner = new Scanner(System.in);
    private static Parqueadero parqueadero = new Parqueadero(3, 3);

    public static void main(String[] args) {
        int opcion = 0;

        while (opcion != 8) {
            mostrarMenu();
            opcion = Integer.parseInt(JOptionPane.showInputDialog("Elige la opcion que desea realizar:"));

            switch (opcion) {
                case 1:
                    registrarVehiculo();
                    break;
                case 2:
                    verificarDisponibilidad();
                    break;
                case 3:
                    obtenerPropietario();
                    break;
                case 4:
                    calcularCostos();
                    break;
                case 5:
                    imprimirReporteMensual();
                    break;
                case 6:
                    imprimirReporteDiario();
                    break;
                case 7:
                    desocuparPuesto();
                    break;
                case 8:
                    JOptionPane.showMessageDialog(null, "Saliendo...");
                    break;
            }
        }
        scanner.close();
    }

    private static void mostrarMenu() {
        JOptionPane.showMessageDialog(null,
                "              Menu del Parqueadero       \n" +
                        "\n1. Registrar un vehiculo en un puesto" +
                        "\n2. Comprobar si un puesto esta libre" +
                        "\n3. Mostrar el propietario de un vehiculo en un puesto" +
                        "\n4. Costo de estacionamiento por hora" +
                        "\n5. Reporte mensual de ingresos" +
                        "\n6. Reporte diario de ingresos" +
                        "\n7. Desocupar un puesto" +
                        "\n8. Salir del sistema");
    }

    private static void registrarVehiculo() {

        int fila = Integer.parseInt(JOptionPane.showInputDialog("Ingresa la fila del puesto (0 a 2):"));
        int columna = Integer.parseInt(JOptionPane.showInputDialog("Ingresa la columna del puesto (0 a 2):"));

        String tipo = JOptionPane.showInputDialog("Tipo de vehiculo (carro o moto):");
        String placa = JOptionPane.showInputDialog("Placa del vehiculo:");
        String modelo = JOptionPane.showInputDialog("Modelo del vehiculo:");
        String propietario = JOptionPane.showInputDialog("Nombre del propietario:");

        Vehiculo vehiculo = null;
        if (tipo.equalsIgnoreCase("moto")) {

            String tipoMoto = JOptionPane.showInputDialog("Tipo de moto (clasica o hibrida):");
            int velocidadMaxima = Integer.parseInt(JOptionPane.showInputDialog("Velocidad máxima de la moto:"));
            vehiculo = new Moto(placa, modelo, propietario, tipoMoto, velocidadMaxima);
        } else if (tipo.equalsIgnoreCase("carro")) {

            vehiculo = new Carro(placa, modelo, propietario);
        }

        if (vehiculo != null && parqueadero.asignarVehiculoAPuesto(fila, columna, vehiculo)) {
            JOptionPane.showMessageDialog(null, "El vehiculo ha sido registrado con exito");
        } else {
            JOptionPane.showMessageDialog(null,
                    "No se pudo registrar el vehiculo, verifica la disponibilidad del puesto");
        }
    }

    private static void verificarDisponibilidad() {

        int fila = Integer.parseInt(JOptionPane.showInputDialog("Ingresa la fila del puesto (0 a 2):"));
        int columna = Integer.parseInt(JOptionPane.showInputDialog("Ingresa la columna del puesto (0 a 2):"));

        if (parqueadero.estaDisponible(fila, columna)) {
            JOptionPane.showMessageDialog(null, "El puesto esta libre");
        } else {
            JOptionPane.showMessageDialog(null, "El puesto esta ocupado");
        }
    }

    private static void obtenerPropietario() {

        int fila = Integer.parseInt(JOptionPane.showInputDialog("Ingresa la fila del puesto (0 a 2):"));
        int columna = Integer.parseInt(JOptionPane.showInputDialog("Ingresa la columna del puesto (0 a 2):"));

        if (!parqueadero.estaDisponible(fila, columna)) {

            Vehiculo vehiculo = parqueadero.getPuestos()[fila][columna].obtenerVehiculo();
            JOptionPane.showMessageDialog(null, "El propietario del vehiculo es: " + vehiculo.getPropietario());
        } else {
            JOptionPane.showMessageDialog(null, "El puesto esta vacio");
        }
    }

    private static void calcularCostos() {

        int fila = Integer.parseInt(JOptionPane.showInputDialog("Ingresa la fila del puesto (0 a 2):"));
        int columna = Integer.parseInt(JOptionPane.showInputDialog("Ingresa la columna del puesto (0 a 2):"));

        double costo = parqueadero.calcularCosto(fila, columna);
        if (costo > 0) {
            JOptionPane.showMessageDialog(null, "Costo del estacionamiento: " + costo);
        } else {
            JOptionPane.showMessageDialog(null,
                    "El puesto esta vacio o el vehiculo no ha sido registrado correctamente");
        }
    }

    private static void desocuparPuesto() {

        int fila = Integer.parseInt(JOptionPane.showInputDialog("Ingresa la fila del puesto (0 a 2):"));
        int columna = Integer.parseInt(JOptionPane.showInputDialog("Ingresa la columna del puesto (0 a 2):"));

        double costo = parqueadero.desocuparPuesto(fila, columna);
        if (costo > 0) {
            JOptionPane.showMessageDialog(null, "Vehiculo retirado, el costo del estacionamiento es: " + costo);
        } else {
            JOptionPane.showMessageDialog(null, "El puesto ya estaba vacio o no se ha podido calcular el costo");
        }
    }

    private static void imprimirReporteDiario() {
        parqueadero.imprimirReporteDiario();

    }

    private static void imprimirReporteMensual() {
        parqueadero.imprimirReporteMensual();
    }

    public static Scanner getScanner() {
        return scanner;
    }

    public static Parqueadero getParqueadero() {
        return parqueadero;
    }
}
