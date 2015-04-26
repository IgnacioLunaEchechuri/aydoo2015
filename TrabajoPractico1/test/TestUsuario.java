import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class TestUsuario {

	@Test
	public void testCoordenadas() {
		PosicionPorCoordenadas posicion = new PosicionPorCoordenadas(10, 10);
		Usuario jose = new Usuario(1000, 10, 160, TipoDeAtraccion.aventura,
				posicion);
		Assert.assertTrue(jose.getPosicion().getLatitud() == 10);
		Assert.assertEquals(10, jose.getPosicion().getLongitud(), 0);
	}

	@Test
	public void testTiempo() {
		PosicionPorCoordenadas posicion = new PosicionPorCoordenadas(10, 10);
		Usuario jose = new Usuario(1000, 10, 160, TipoDeAtraccion.paisaje,
				posicion);
		Assert.assertTrue(jose.getTiempoDisponible() == 160);
		jose.restartiempo(20);
		Assert.assertFalse(jose.getTiempoDisponible() == 160);
		Assert.assertEquals(140, jose.getTiempoDisponible(), 0);
	}

	@Test
	public void testPresupuesto() {
		PosicionPorCoordenadas posicion = new PosicionPorCoordenadas(40, 10);
		Usuario ignacio = new Usuario(1000, 10, 160,
				TipoDeAtraccion.degustacion, posicion);
		Assert.assertTrue(ignacio.getPresupuesto() == 1000);
		ignacio.restarDelPresupuesto(500);
		Assert.assertFalse(ignacio.getPresupuesto() == 1000);
		Assert.assertEquals(500, ignacio.getPresupuesto(), 0);
		ignacio.restarDelPresupuesto(70);
		Assert.assertEquals(430, ignacio.getPresupuesto(), 0);
		ignacio.sumarAlPresupuesto(70);
		Assert.assertEquals(500,ignacio.getPresupuesto(), 0);
	}

}
