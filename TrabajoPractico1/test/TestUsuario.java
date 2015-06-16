import org.junit.Test;
import org.junit.Assert;

public class TestUsuario {

	@Test
	public void testCoordenadas() {

		Usuario jose = new Usuario(1000, 10, 160, TipoDeAtraccion.aventura,
				this.getPosicionUsuario(), 1);

		Assert.assertEquals(0, jose.getPosicion().getLatitud(), 10);
		Assert.assertEquals(0, jose.getPosicion().getLongitud(), 15);

	}

	@Test
	public void tiempoDisponible() {
		Usuario jose = new Usuario(1000, 10, 160, TipoDeAtraccion.aventura,
				this.getPosicionUsuario(), 1);
		float tiempoDisponibleUsuario = jose.getTiempoDisponible();

		Assert.assertEquals(tiempoDisponibleUsuario, 160, 0);

	}
	@Test
	public void tiempoDisponibleRestandoTiempo() {
		Usuario jose = new Usuario(1000, 10, 160, TipoDeAtraccion.aventura,
				this.getPosicionUsuario(), 1);

		jose.restartiempo(20);
		float tiempoDisponibleUsuario = jose.getTiempoDisponible();

		Assert.assertEquals(140, tiempoDisponibleUsuario, 0);
	}

	@Test
	public void presupuestoDisponibleValido() {
		Usuario jose = new Usuario(1000, 10, 160, TipoDeAtraccion.aventura,
				this.getPosicionUsuario(), 1);
		boolean presupuestoDisponibleValido = jose
				.estadoPresupuestoDisponible(1000);

		Assert.assertTrue(presupuestoDisponibleValido);

	}

	@Test
	public void presupuestoDisponibleInvalido() {
		Usuario jose = new Usuario(1000, 10, 160, TipoDeAtraccion.aventura,
				this.getPosicionUsuario(), 1);

		boolean estadoPresupuestoDisponibleInvalido = jose
				.estadoPresupuestoDisponible(1001);

		Assert.assertFalse(estadoPresupuestoDisponibleInvalido);
	}

	@Test
	public void calcularDistancia() {
		Usuario jose = new Usuario(1000, 10, 160, TipoDeAtraccion.aventura,
				this.getPosicionUsuario(), 1);

		PosicionPorCoordenadas posicionIgualUsuario = new PosicionPorCoordenadas(
				10, 15);
		float distancia = jose.calcularDistancia(posicionIgualUsuario);
		Assert.assertEquals(0, 0, distancia);

		PosicionPorCoordenadas posicion = new PosicionPorCoordenadas(2, 4);
		distancia = jose.calcularDistancia(posicion);
		Assert.assertEquals(0, 5, distancia);
	}

	@Test
	public void tiempoDelRecorrido() {

		Usuario jose = new Usuario(1000, 10, 160, TipoDeAtraccion.aventura,
				this.getPosicionUsuario(), 1);
		PosicionPorCoordenadas posicion = new PosicionPorCoordenadas(2, 4);
		float tiempo = jose.devolverTiempoDeRecorridoMasTiempoAtraccion(
				posicion, 10);
		Assert.assertEquals(0, 10, tiempo);

		PosicionPorCoordenadas posicionIgualUsuario = new PosicionPorCoordenadas(
				10, 15);
		tiempo = jose.devolverTiempoDeRecorridoMasTiempoAtraccion(
				posicionIgualUsuario, 20);
		Assert.assertEquals(0, 20, tiempo);

	}

	private PosicionPorCoordenadas getPosicionUsuario() {
		PosicionPorCoordenadas posicionUsuario = new PosicionPorCoordenadas(10,
				15);
		return posicionUsuario;
	}

}
