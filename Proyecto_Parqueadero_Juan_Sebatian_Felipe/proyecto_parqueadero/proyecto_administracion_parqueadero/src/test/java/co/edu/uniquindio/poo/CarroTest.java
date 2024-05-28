package co.edu.uniquindio.poo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;

/*
 * Metodo a Probar                       | Entrada                       | Salida Esperada
 * --------------------------------------|-------------------------------|----------------------------------------------------
 * TestCarroDatosCompletos()             | Carro con datos completos     | Los datos del carro son correctamente obtenidos
 * TestCarroDatosNulos()                 | Carro con datos nulos         | Se lanza una excepcion al pasar datos nulos
 * TestCarroDatosVacios()                | Carro con datos vacios        | Se lanza una excepcion al pasar datos vacios
 * TestCalcularCostoParqueaderoCarro()   | Carro y tiempo estacionado    | El costo calculado es igual al costo esperado
 */

public class CarroTest {
    private static final Logger LOG = Logger.getLogger(CarroTest.class.getName());

    @Test
    public void TestCarroDatosCompletos() {
        LOG.info("Iniciando TestCarroDatosCompletos");
        Carro carro = new Carro("QWE456", "Mazda 3", "Juan");
        assertEquals("QWE456", carro.getPlaca());
        assertEquals("Mazda 3", carro.getModelo());
        assertEquals("Juan", carro.getPropietario());

        LOG.info("Finalizando TestCarroDatosCompletos");
    }

    @Test
    public void TestCarroDatosNulos() {
        LOG.info("Inicio TestCarroDatosNulos");

        assertThrows(Throwable.class, () -> new Carro(null, null, null));

        LOG.info("Finalización TestCarroDatosNulos");
    }

    @Test
    public void TestCarroDatosVacios() {
        LOG.info("Inicio TestCarroDatosVacios");

        assertThrows(Throwable.class, () -> new Carro("", "", ""));

        LOG.info("Finalización TestCarroDatosVacios");
    }

    @Test
    void TestCalcularCostoParqueaderoCarro() {
        LOG.info("Iniciando TestCalcularCostoParqueaderoCarro");

        Carro carro = new Carro("QWE456", "Mazda 3", "Juan");

        Parqueadero parqueadero = new Parqueadero(1, 1);

        Puesto puesto = parqueadero.getPuestos()[0][0];
        puesto.ocuparPuesto(carro);

        int horasEstacionado = 2;

        double costoCalculado = carro.calcularCosto(horasEstacionado);

        double costoEsperado = 6000.0;
        assertEquals(costoEsperado, costoCalculado);

        LOG.info("Finalizando TestCalcularCostoParqueaderoCarro");
    }
}
