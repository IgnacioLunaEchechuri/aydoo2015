import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class TestPromocionAxB {

	private List<Atraccion> listaDeatracciones;
	private List<Atraccion> listaDeAtraccionesFallida;
	private Itinerario listaItinerario;
	Date Fechainicio = new Date();
	Date fechaFinal = new Date();

	@Test
	public void testValidarPromocion() {
		this.configurarFecha();

		this.llenarPaqueteDeAtracciones();
	
		PromocionAxB promocionAxB = new PromocionAxB(this.Fechainicio,
				this.fechaFinal,this.getAtraccionGratis(), this.listaDeatracciones);
		
		Assert.assertTrue(promocionAxB.validarPromocion(this.listaItinerario));

		promocionAxB.setListaDeAtracciones(this.listaDeAtraccionesFallida);
		Assert.assertFalse(promocionAxB.validarPromocion(this.listaItinerario));
		
		promocionAxB.setListaDeAtracciones(this.listaDeAtraccionesFallida);
		Assert.assertFalse(promocionAxB.validarPromocion(this.listaItinerario));
		
		this.configurarFechaFueraDeRango();
		
		
		PromocionAxB promocionAxBFueraDeFecha = new PromocionAxB(this.Fechainicio,
				this.fechaFinal,this.getAtraccionGratis(), this.listaDeatracciones);
		
		Assert.assertFalse(promocionAxBFueraDeFecha.validarPromocion(this.listaItinerario));
		
	}
	
	@Test
	public void testAplicarPromocion() {
		this.configurarFecha();
		this.llenarPaqueteDeAtracciones();
		PosicionPorCoordenadas posicion = new PosicionPorCoordenadas(10, 10);
		Usuario jose = new Usuario(1000, 10, 160, TipoDeAtraccion.paisaje,posicion);
		
		PromocionAxB promocionAxB = new PromocionAxB(this.Fechainicio,
				this.fechaFinal,this.getAtraccionGratis(), this.listaDeatracciones);
		
	Assert.assertEquals(this.listaItinerario.tamanoItinerario(), 2,0);	
	
	Assert.assertTrue(promocionAxB.DevolverPromocion(jose, this.listaItinerario));
	
	Assert.assertEquals(this.listaItinerario.tamanoItinerario(), 3,0);		
	
	}
	

	private void configurarFechaFueraDeRango() {

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		this.Fechainicio = calendar.getTime();
		calendar.add(Calendar.DATE, -2);
		this.fechaFinal = calendar.getTime();
	}
	
	private void configurarFecha() {

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		this.Fechainicio = calendar.getTime();
		calendar.add(Calendar.DATE, 2);
		this.fechaFinal = calendar.getTime();
	}

	private Atraccion getAtraccionGratis() {
		PosicionPorCoordenadas posicionMordor = new PosicionPorCoordenadas(10,
				40);
		Atraccion atraccion = new Atraccion(posicionMordor, 100, 50, 120,
				TipoDeAtraccion.aventura);
		return atraccion;
	}

	private void llenarPaqueteDeAtracciones() {		

		this.listaDeatracciones = new LinkedList<Atraccion>();
		this.listaDeAtraccionesFallida = new LinkedList<Atraccion>();

		listaItinerario = new Itinerario();

		PosicionPorCoordenadas posicionMordor = new PosicionPorCoordenadas(10,
				10);

		Atraccion mordor = new Atraccion(posicionMordor, 100, 50, 120,
				TipoDeAtraccion.aventura);

		PosicionPorCoordenadas posicionComarca = new PosicionPorCoordenadas(50,
				10);

		Atraccion comarca = new Atraccion(posicionComarca, 90, 20, 30,
				TipoDeAtraccion.aventura);

		PosicionPorCoordenadas posicionGondor = new PosicionPorCoordenadas(50,
				90);

		Atraccion gondor = new Atraccion(posicionGondor, 90, 20, 30,
				TipoDeAtraccion.aventura);
		
		listaItinerario.agregarAtraccion(mordor);
		listaItinerario.agregarAtraccion(comarca);
		
		this.listaDeatracciones.add(mordor);		
		this.listaDeatracciones.add(comarca);
		
		this.listaDeAtraccionesFallida.add(comarca);
		this.listaDeAtraccionesFallida.add(gondor);

	

	}

}
