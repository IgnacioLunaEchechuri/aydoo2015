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

}
