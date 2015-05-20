import org.junit.Assert;
import org.junit.Test;

public class TestAtraccion {

	@Test
	public void tesCapacidad() {
		PosicionPorCoordenadas posicion = new PosicionPorCoordenadas(10, 100);
		Atraccion atraccion=new Atraccion(posicion,1200,30,20,TipoDeAtraccion.aventura);
		Assert.assertTrue(atraccion.comprobarCapacidad(29));
                Assert.assertFalse(atraccion.comprobarCapacidad(31));
		
        }
	@Test
	public void testPosicion() {
		PosicionPorCoordenadas posicion = new PosicionPorCoordenadas(10, 100);
		Atraccion atraccion=new Atraccion(posicion,1200,30,20,TipoDeAtraccion.aventura);
		Assert.assertEquals(100, atraccion.getPosicion().getLongitud(), 0);
		Assert.assertEquals(10, atraccion.getPosicion().getLatitud(), 0);
	}
	@Test
	public void testCapacidadVisitantes() {
		PosicionPorCoordenadas posicion = new PosicionPorCoordenadas(10, 100);
		Atraccion atraccion=new Atraccion(posicion,1200,30,20,TipoDeAtraccion.aventura);
		Assert.assertEquals(30, atraccion.getCapacidadDeVisitantes(), 0);
		atraccion.descontarCapacidad(15);
		Assert.assertEquals(15, atraccion.getCapacidadDeVisitantes(), 0);

	}
	@Test
	public void testPrecio() {
		PosicionPorCoordenadas posicion = new PosicionPorCoordenadas(10, 100);
		Atraccion atraccion=new Atraccion(posicion,1200,30,20,TipoDeAtraccion.aventura);
		Assert.assertEquals(1200, atraccion.getPrecio(), 0);
		atraccion.setPrecio(300);
		Assert.assertEquals(300, atraccion.getPrecio(), 0);

	}
	@Test
	public void testTiempo() {
		PosicionPorCoordenadas posicion = new PosicionPorCoordenadas(10, 100);
		Atraccion atraccion=new Atraccion(posicion,1200,30,20,TipoDeAtraccion.aventura);
		Assert.assertEquals(20, atraccion.getTiempoDelRecorrido(), 0);
		atraccion.setTiempoDelRecorrido(50);
		Assert.assertEquals(50, atraccion.getTiempoDelRecorrido(), 0);

	}

}
