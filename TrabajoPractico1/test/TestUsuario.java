import org.junit.Assert;
import org.junit.Test;

public class TestUsuario {

	
	@Test
	public void ValidarCoordenadas() {

		Usuario jose = new Usuario(1000, 10, 160, TipoDeAtraccion.aventura,
				this.getPosicionUsuario(), 1);

		boolean validarLatitud = jose.getPosicion().getLatitud() == 10;
		boolean validarLongitud = jose.getPosicion().getLongitud() == 15;

		Assert.assertTrue(validarLatitud);
		Assert.assertTrue(validarLongitud);
	}

	@Test
	public void validarTiempoDisponible() {
		Usuario jose = new Usuario(1000, 10, 160, TipoDeAtraccion.aventura,
				this.getPosicionUsuario(), 1);
		float tiempoDisponibleUsuario = jose.getTiempoDisponible();

		Assert.assertEquals(tiempoDisponibleUsuario, 160, 0);
		jose.restartiempo(20);

	}

	public void validarTiempoDisponibleRestandoTiempo() {
		Usuario jose = new Usuario(1000, 10, 160, TipoDeAtraccion.aventura,
				this.getPosicionUsuario(), 1);

		jose.restartiempo(20);
		float tiempoDisponibleUsuario = jose.getTiempoDisponible();

		Assert.assertEquals(140, tiempoDisponibleUsuario, 0);
	}

	@Test
	public void PresupuestoDisponibleValido() {
		Usuario jose = new Usuario(1000, 10, 160, TipoDeAtraccion.aventura,
				this.getPosicionUsuario(), 1);
		boolean presupuestoDisponibleValido = jose
				.consultaPresupuestoDisponible(1000);

		Assert.assertTrue(presupuestoDisponibleValido);

	}

	@Test
	public void PresupuestoDisponibleInvalido() {
		Usuario jose = new Usuario(1000, 10, 160, TipoDeAtraccion.aventura,
				this.getPosicionUsuario(), 1);

		boolean presupuestoDisponibleInvalido = jose
				.consultaPresupuestoDisponible(1001);

		Assert.assertFalse(presupuestoDisponibleInvalido);
	}

	
	private PosicionPorCoordenadas getPosicionUsuario() {
		PosicionPorCoordenadas posicionUsuario = new PosicionPorCoordenadas(10,
				15);
		return posicionUsuario;
	}

	
}
