import org.junit.Assert;
import org.junit.Test;


public class TestItinerario {

	@Test
	public void testCostoTotal() {
		Itinerario itinerario=new Itinerario();
		
		PosicionPorCoordenadas posicionMordor = new PosicionPorCoordenadas(10,10);

		Atraccion mordor = new Atraccion(posicionMordor, 100, 50, 120,TipoDeAtraccion.aventura);

		PosicionPorCoordenadas posicionComarca = new PosicionPorCoordenadas(50,	10);

		Atraccion comarca = new Atraccion(posicionComarca, 90, 20, 30,TipoDeAtraccion.aventura);

		PosicionPorCoordenadas posicionGondor = new PosicionPorCoordenadas(50,90);

		Atraccion gondor = new Atraccion(posicionGondor, 90, 20, 30,TipoDeAtraccion.aventura);
				
		itinerario.agregarAtraccion(gondor);
		itinerario.agregarAtraccion(comarca);
		
		Assert.assertEquals(itinerario.costoTotalItinerario(), 180,0);
		
		itinerario.agregarAtraccion(mordor);
		
		Assert.assertEquals(itinerario.costoTotalItinerario(), 280,0);
		
	}
	
}
