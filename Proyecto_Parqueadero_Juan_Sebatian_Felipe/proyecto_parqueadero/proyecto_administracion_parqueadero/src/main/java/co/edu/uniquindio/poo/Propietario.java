package co.edu.uniquindio.poo;

public class Propietario {
    private String nombre;

    public Propietario(String nombre) {
        this.nombre = nombre;
        assert nombre != null && !nombre.isBlank() : "Ingrese un nombre valido: ";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}