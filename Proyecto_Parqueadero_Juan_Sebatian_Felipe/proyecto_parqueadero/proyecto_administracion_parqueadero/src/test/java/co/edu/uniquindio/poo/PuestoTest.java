package co.edu.uniquindio.poo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.logging.Logger;

/*
 * Metodo a Probar                          | Entrada                       | Salida Esperada
 * -----------------------------------------|-------------------------------|------------------------------------------------
 * TestPuestoOcupado()                      | Carro con placa unica         | Puesto ocupado despues de añadir carro
 * TestOcuparPuesto()                       | Carro con placa unica         | Puesto ocupado, carro asignado al puesto
 * TestDesocuparPuesto()                    | Carro en puesto               | Puesto desocupado, vehiculo en puesto es nulo
 */

public class PuestoTest {
    private static final Logger LOG = Logger.getLogger(PuestoTest.class.getName());

    @Test
    public void testPuestoOcupado() {
        LOG.info("Iniciando testPuestoOcupado");

        Puesto puesto = new Puesto(1, 1);
        assertFalse(puesto.estaOcupado());

        Propietario propietario = new Propietario("Juan");

        Carro carro = new Carro("QWE456", "Mazda 3", propietario.getNombre());

        puesto.ocuparPuesto(carro);

        assertTrue(puesto.estaOcupado());

        LOG.info("Finalizando testPuestoOcupado");
    }

    @Test
    public void testOcuparPuesto() {
        LOG.info("Iniciando testOcuparPuesto");

        Puesto puesto = new Puesto(1, 1);
        assertFalse(puesto.estaOcupado());

        Propietario propietario = new Propietario("Juan");
        Carro carro = new Carro("QWE456", "Mazda 3", propietario.getNombre());

        puesto.ocuparPuesto(carro);

        assertTrue(puesto.estaOcupado());

        assertEquals(carro, puesto.getVehiculo());

        LOG.info("Finalizando testOcuparPuesto");
    }

    @Test
    public void testDesocuparPuesto() {
        LOG.info("Iniciando testDesocuparPuesto");

        Propietario propietario = new Propietario("Juan");
        Carro carro = new Carro("QWE456", "Mazda 3", propietario.getNombre());

        Puesto puesto = new Puesto(0, 0);
        puesto.ocuparPuesto(carro);

        assertTrue(puesto.estaOcupado());

        puesto.desocuparPuesto();

        assertFalse(puesto.estaOcupado());
        assertEquals(null, puesto.getVehiculo());

        LOG.info("Finalizando testDesocuparPuesto");
    }
}
