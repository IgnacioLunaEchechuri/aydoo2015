import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class TestPromocionAbsoluta {

	private List<Atraccion> listaDeatracciones;
	private List<Atraccion> listaDeAtraccionesFallida;
	private Itinerario listaItinerario;
	
	Date Fechainicio = new Date();
	Date fechaFinal = new Date();

	@Test
	public void testValidarPromocion() {
		this.llenarPaqueteDeAtracciones();
		this.configurarFecha();

		PromocionAbsoluta promocionAbsoluta = new PromocionAbsoluta(this.Fechainicio, this.fechaFinal,
				this.listaDeatracciones, 20);
					
		Assert.assertTrue(promocionAbsoluta.validarPromocion(this.listaItinerario));
		
		promocionAbsoluta.setListaDeAtracciones(this.listaDeAtraccionesFallida);
		Assert.assertFalse(promocionAbsoluta.validarPromocion(this.listaItinerario));
		
		this.configurarFechaFueraDeRango();
		
		PromocionAbsoluta promocionAbsolutaFueraDeFecha = new PromocionAbsoluta(this.Fechainicio, this.fechaFinal,
				this.listaDeatracciones, 20);
		
		Assert.assertFalse(promocionAbsolutaFueraDeFecha.validarPromocion(this.listaItinerario));
		
		
	}

		
	

	@Test
	public void testDevolverDinero() {
		this.llenarPaqueteDeAtracciones();
		this.configurarFecha();
		
		PosicionPorCoordenadas posicion = new PosicionPorCoordenadas(10, 10);
		Usuario jose = new Usuario(1000, 10, 160, TipoDeAtraccion.paisaje,posicion);
				
		PromocionAbsoluta promocionAbsoluta = new PromocionAbsoluta(this.Fechainicio, this.fechaFinal,
				this.listaDeatracciones, 20);
		
		Assert.assertEquals(jose.getPresupuesto(),1000,0);
		
		promocionAbsoluta.DevolverPromocion(jose, this.listaItinerario);

		Assert.assertEquals(jose.getPresupuesto(), 1020, 0);
		
		Assert.assertTrue(promocionAbsoluta.DevolverPromocion(jose, this.listaItinerario));
		
		Assert.assertEquals(jose.getPresupuesto(), 1040, 0);
	
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
