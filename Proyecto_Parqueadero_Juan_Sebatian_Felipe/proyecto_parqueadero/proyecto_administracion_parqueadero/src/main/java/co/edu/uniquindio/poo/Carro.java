package co.edu.uniquindio.poo;

public class Carro extends Vehiculo {

    public Carro(String placa, String modelo, String propietario) {
        super(placa, modelo, propietario);

        if (placa == null || placa.isBlank()) {
            throw new IllegalArgumentException("Ingrese una placa valida");
        }
        if (modelo == null || modelo.isBlank()) {
            throw new IllegalArgumentException("Ingrese un modelo valido");
        }
        if (propietario == null || propietario.isBlank()) {
            throw new IllegalArgumentException("Ingrese un propietario valido");
        }
    }

    @Override
    public double calcularCosto(int horas) {
        double tarifa = 3000;
        return horas > 0 ? horas * tarifa : tarifa;
    }
}
