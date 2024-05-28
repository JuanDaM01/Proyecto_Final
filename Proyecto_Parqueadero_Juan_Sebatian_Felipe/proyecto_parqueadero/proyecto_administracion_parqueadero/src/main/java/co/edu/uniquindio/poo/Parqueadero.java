package co.edu.uniquindio.poo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.LinkedList;

import javax.swing.JOptionPane;

public class Parqueadero {
    private Puesto[][] puestos; // Matriz //
    private Collection<IngresoDiario> ingresosDiarios;
    private Collection<IngresoMensual> ingresosMensuales;
    private Collection<Carro> carros;
    private Collection<Moto> motos;

    public Parqueadero(Puesto[][] puestos) {
        this.puestos = puestos;
        this.ingresosDiarios = new LinkedList<>();
        this.ingresosMensuales = new LinkedList<>();
        this.carros = new LinkedList<>();
        this.motos = new LinkedList<>();
    }

    public void agregarCarroAlParqueadero(Carro carro) {
        assert validarPlacaExista(carro.getPlaca()) == false;
        carros.add(carro);
    }

    private boolean validarPlacaExista(String placa) {
        for (Carro carro : carros) {
            if (carro.getPlaca().equals(placa)) {
                return true;
            }
        }
        return false;
    }

    public void agregarMotoAlParqueadero(Moto moto) {
        assert validarPlacaExiste(moto.getPlaca()) == false;
        motos.add(moto);
    }

    private boolean validarPlacaExiste(String placa) {
        for (Moto moto : motos) {
            if (moto.getPlaca().equals(placa)) {
                return true;
            }
        }
        return false;
    }

    public Parqueadero(int filas, int columnas) {
        puestos = new Puesto[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                puestos[i][j] = new Puesto(i, j);
            }
        }
        this.ingresosDiarios = new LinkedList<>();
        this.ingresosMensuales = new LinkedList<>();
        this.carros = new LinkedList<>();
        this.motos = new LinkedList<>();
    }

    public boolean estaDisponible(int i, int j) {
        return !puestos[i][j].estaOcupado();
    }

    public boolean asignarVehiculoAPuesto(int i, int j, Vehiculo vehiculo) {
        if (estaDisponible(i, j)) {
            puestos[i][j].ocuparPuesto(vehiculo);
            return true;
        }
        return false;
    }

    public double desocuparPuesto(int i, int j) {
        if (!estaDisponible(i, j)) {
            double costo = calcularCosto(i, j);
            String tipoVehiculo = puestos[i][j].obtenerVehiculo().getClass().getSimpleName();
            actualizarIngresosDiarios(tipoVehiculo, costo);
            String mesActual = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM"));
            actualizarIngresosMensuales(mesActual, costo);
            puestos[i][j].desocuparPuesto();
            return costo;
        }
        return 0;
    }

    private void actualizarIngresosDiarios(String tipoVehiculo, double costo) {
        for (IngresoDiario ingreso : ingresosDiarios) {
            if (ingreso.getTipoVehiculo().equals(tipoVehiculo)) {
                ingreso.setIngreso(ingreso.getIngreso() + costo);

                return;
            }
        }
        ingresosDiarios.add(new IngresoDiario(tipoVehiculo, costo));
    }

    private void actualizarIngresosMensuales(String mes, double costo) {
        for (IngresoMensual ingreso : ingresosMensuales) {
            if (ingreso.getMes().equals(mes)) {
                ingreso.setIngreso(ingreso.getIngreso() + costo);
                return;
            }
        }
        ingresosMensuales.add(new IngresoMensual(mes, costo));
    }

    public double calcularCosto(int i, int j) {
        if (!estaDisponible(i, j)) {
            LocalDateTime ahora = LocalDateTime.now();
            long horas = ChronoUnit.HOURS.between(puestos[i][j].obtenerHoraInicio(), ahora);
            if (horas == 0) {
                horas = 1;
            }
            double costo = puestos[i][j].obtenerVehiculo().calcularCosto((int) horas);
            return costo;
        }
        return 0;
    }

    public String imprimirReporteDiario() {
        if (ingresosDiarios.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se han registrado ingresos hoy.");
        } else {
            JOptionPane.showMessageDialog(null, "Reporte diario de ingresos:");
            for (IngresoDiario ingreso : ingresosDiarios) {
                JOptionPane.showMessageDialog(null, ingreso.getTipoVehiculo() + ": " + ingreso.getIngreso());
            }
        }
        return "";
    }

    public void imprimirReporteMensual() {
        if (ingresosMensuales.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se han registrado ingresos este mes.");
        } else {
            JOptionPane.showMessageDialog(null, "Reporte mensual de ingresos:");
            for (IngresoMensual ingreso : ingresosMensuales) {
                JOptionPane.showMessageDialog(null, ingreso.getMes() + ": " + ingreso.getIngreso());
            }
        }
    }

    public Puesto[][] getPuestos() {
        return puestos;
    }

    public Collection<IngresoDiario> getIngresosDiarios() {
        return ingresosDiarios;
    }

    public Collection<IngresoMensual> getIngresosMensuales() {
        return ingresosMensuales;
    }

    public Collection<Carro> getCarros() {
        return carros;
    }

    public void setCarros(Collection<Carro> carros) {
        this.carros = carros;
    }

    public Collection<Moto> getMotos() {
        return motos;
    }

    public void setMotos(Collection<Moto> motos) {
        this.motos = motos;
    }

    private class IngresoDiario {
        private String tipoVehiculo;
        private double ingreso;

        public IngresoDiario(String tipoVehiculo, double ingreso) {
            this.tipoVehiculo = tipoVehiculo;
            this.ingreso = ingreso;
        }

        public String getTipoVehiculo() {
            return tipoVehiculo;
        }

        public double getIngreso() {
            return ingreso;
        }

        public void setIngreso(double ingreso) {
            this.ingreso = ingreso;
        }
    }

    private class IngresoMensual {
        private String mes;
        private double ingreso;

        public IngresoMensual(String mes, double ingreso) {
            this.mes = mes;
            this.ingreso = ingreso;
        }

        public String getMes() {
            return mes;
        }

        public double getIngreso() {
            return ingreso;
        }

        public void setIngreso(double ingreso) {
            this.ingreso = ingreso;
        }
    }
}
