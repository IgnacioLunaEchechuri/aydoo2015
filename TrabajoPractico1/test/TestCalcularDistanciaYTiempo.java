import org.junit.Assert;
import org.junit.Test;

public class TestCalcularDistanciaYTiempo {

	@Test
	public void testCalcularDistancia() {
		PosicionPorCoordenadas posicionInicial = new PosicionPorCoordenadas(10, 10);
		PosicionPorCoordenadas posicionFinal = new PosicionPorCoordenadas(2, 4);
	Assert.assertEquals(0,CalcularDistanciaYTiempo.calcularDistancia(posicionInicial,posicionInicial),0 );
	Assert.assertEquals(10,CalcularDistanciaYTiempo.calcularDistancia(posicionInicial,posicionFinal) ,2);
	}
	
	@Test
	public void testTiempoRecorrido() {
		
	Assert.assertEquals(0,CalcularDistanciaYTiempo.devolverTiempoDeRecorridoMasTiempoAtraccion(0,0,0),0 );
	Assert.assertEquals(6,CalcularDistanciaYTiempo.devolverTiempoDeRecorridoMasTiempoAtraccion(1,1,5) ,0);
	
	}
}
