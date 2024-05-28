package co.edu.uniquindio.poo;

public class Moto extends Vehiculo {
    private String tipo;
    private int velocidadMaxima;

    public Moto(String placa, String modelo, String propietario, String tipo, int velocidadMaxima) {
        super(placa, modelo, propietario);
        this.tipo = tipo;
        this.velocidadMaxima = velocidadMaxima;
        assert placa != null && !placa.isBlank() : "Ingrese una placa valida: ";
        assert modelo != null && !modelo.isBlank() : "Ingrese un modelo valido: ";
        assert propietario != null && !propietario.isBlank() : "Ingrese un propietario valido: ";
        assert tipo != null && !tipo.isBlank() : "Ingrese un tipo de moto valido: ";
        assert velocidadMaxima > 0;
    }

    @Override
    public double calcularCosto(int horas) {
        double tarifa = tipo.equals("clasica") ? 2000 : 1600;
        return horas > 0 ? horas * tarifa : tarifa;
    }

    public String getTipo() {
        return tipo;
    }

    public int getVelocidadMaxima() {
        return velocidadMaxima;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setVelocidadMaxima(int velocidadMaxima) {
        this.velocidadMaxima = velocidadMaxima;
    }
}
