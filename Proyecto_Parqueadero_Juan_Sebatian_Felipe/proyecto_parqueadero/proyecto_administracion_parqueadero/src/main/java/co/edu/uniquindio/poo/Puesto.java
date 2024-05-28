package co.edu.uniquindio.poo;

import java.time.LocalDateTime;

public class Puesto {
    private int i, j;
    private boolean ocupado;
    private Vehiculo vehiculo;
    private LocalDateTime horaInicio;

    public Puesto(int i, int j) {
        this.i = i;
        this.j = j;
        this.ocupado = false;
        this.vehiculo = null;
        this.horaInicio = null;
    }

    public boolean estaOcupado() {
        return ocupado;
    }

    public void ocuparPuesto(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
        this.ocupado = true;
        this.horaInicio = LocalDateTime.now();
    }

    public void desocuparPuesto() {
        this.vehiculo = null;
        this.ocupado = false;
        this.horaInicio = null;
    }

    public Vehiculo obtenerVehiculo() {
        return vehiculo;
    }

    public LocalDateTime obtenerHoraInicio() {
        return horaInicio;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public LocalDateTime getHoraInicio() {
        return horaInicio;
    }
}
