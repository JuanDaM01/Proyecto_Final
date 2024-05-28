package co.edu.uniquindio.poo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.logging.Logger;
import org.junit.jupiter.api.Test;

/*
 * Metodo a Probar                       | Entrada                       | Salida Esperada
 * --------------------------------------|-------------------------------|----------------------------------------------------
 * TestMotoDatosCompletos()              | Moto con datos completos      | Los datos de la moto son correctamente obtenidos
 * TestMotoDatosNulos()                  | Moto con datos nulos          | Se lanza una excepcion al pasar datos nulos
 * TestMotoDatosVacios()                 | Moto con datos vacios         | Se lanza una excepcion al pasar datos vacios
 * TestMotoVelocidadMaximaNegativa()     | Moto con velocidad negativa   | Se lanza una excepcion al pasar velocidad negativa
 * TestCalcularCostoParqueaderoMoto()    | Moto y tiempo estacionado     | El costo calculado es igual al costo esperado
 */

public class MotoTest {
    private static final Logger LOG = Logger.getLogger(MotoTest.class.getName());

    @Test
    public void TestMotoDatosCompletos() {
        LOG.info("Iniciando TestMotoDatosCompletos");

        Moto moto = new Moto("ABC15G", "Pulsar 200", "Roberto", "clasica", 150);

        assertEquals("ABC15G", moto.getPlaca());
        assertEquals("Pulsar 200", moto.getModelo());
        assertEquals("Roberto", moto.getPropietario());
        assertEquals("clasica", moto.getTipo());
        assertEquals(150, moto.getVelocidadMaxima());

        LOG.info("Finalizando TestMotoDatosCompletos");
    }

    @Test
    public void TestMotoDatosNulos() {
        LOG.info("Iniciando TestMotoDatosNulos");

        assertThrows(Throwable.class, () -> new Moto(null, null, null, null, 150));

        LOG.info("Finalizando TestMotoDatosNulos");
    }

    @Test
    public void TestMotoDatosVacios() {
        LOG.info("Iniciando TestMotoDatosVacios");

        assertThrows(Throwable.class, () -> new Moto("", "", "", "", 150));

        LOG.info("Finalizando TestMotoDatosVacios");
    }

    @Test
    public void TestMotoVelocidadMaximaNegativa() {
        LOG.info("Iniciando TestMotoVelocidadMaximaNegativa");

        assertThrows(Throwable.class, () -> new Moto("ABC15G", "Pulsar 200", "Roberto", "clasica", -150));

        LOG.info("Finalizando TestMotoVelocidadMaximaNegativa");
    }

    @Test
    void TestCalcularCostoParqueaderoMoto() {
        LOG.info("Iniciando TestCalcularCostoParqueaderoMoto");

        Moto moto = new Moto("ABC15G", "Pulsar 200", "Roberto", "clasica", 150);

        Parqueadero parqueadero = new Parqueadero(1, 1);

        Puesto puesto = parqueadero.getPuestos()[0][0];
        puesto.ocuparPuesto(moto);

        int horasEstacionado = 2;

        double costoCalculado = moto.calcularCosto(horasEstacionado);

        double costoEsperado = 2 * 2000;
        assertEquals(costoEsperado, costoCalculado);

        LOG.info("Finalizando TestCalcularCostoParqueaderoMoto");
    }
}
