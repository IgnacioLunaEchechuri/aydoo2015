import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;


public class TestPromocionPorcentual {

	private List<Atraccion> listaDeatracciones;
	private List<Atraccion> listaDeAtraccionesFallida;
	private Itinerario listaItinerario;
	Date Fechainicio = new Date();
	Date fechaFinal = new Date();
	
	@Test
	public void testValidarPromocion() {
		this.llenarPaqueteDeAtracciones();
		this.configurarFecha();

		PromocionPorcentual promocionPorcentual = new PromocionPorcentual(
				this.Fechainicio, this.fechaFinal,10,this.listaDeatracciones);
		
		Assert.assertTrue(promocionPorcentual.validarPromocion(this.listaItinerario));

		promocionPorcentual.setListaDeAtracciones(this.listaDeAtraccionesFallida);
		Assert.assertFalse(promocionPorcentual.validarPromocion(this.listaItinerario));
		
		Assert.assertEquals(10,promocionPorcentual.getPorcentajePromocion(),0);
		
		this.configurarFechaFueraDeRango();
		PromocionPorcentual promocionPorcentualFueraDeFecha = new PromocionPorcentual(this.Fechainicio,
				this.fechaFinal,10, this.listaDeatracciones);
		
		Assert.assertFalse(promocionPorcentualFueraDeFecha.validarPromocion(this.listaItinerario));
		
	}

	@Test
	public void testDevolverDinero() {
		
		this.configurarFecha();
		this.llenarPaqueteDeAtracciones();
		
		PosicionPorCoordenadas posicion = new PosicionPorCoordenadas(10, 10);
		Usuario jose = new Usuario(500, 10, 160, TipoDeAtraccion.paisaje,posicion);
		
		
		PromocionPorcentual promocionPorcentual = new PromocionPorcentual(
				this.Fechainicio, this.fechaFinal,10,this.listaDeatracciones);
		
		Assert.assertTrue(promocionPorcentual.DevolverPromocion(jose, this.listaItinerario));
		
		Assert.assertEquals(jose.getPresupuesto(), 519, 0);
		
		Assert.assertTrue(promocionPorcentual.DevolverPromocion(jose, this.listaItinerario));
		
		Assert.assertEquals(jose.getPresupuesto(), 538, 0);
	
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
