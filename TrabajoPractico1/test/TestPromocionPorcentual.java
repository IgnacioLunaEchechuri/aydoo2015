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
	public void aplicarPromocionConFechasValidas() {
		this.llenarPaqueteDeAtracciones();
		this.configurarFecha();

		Promocion promocionPorcentual = new PromocionPorcentual(
				this.Fechainicio, this.fechaFinal, 30, this.listaDeatracciones);

		boolean estadoFechaValida = promocionPorcentual.estadoFechaDePromocionValida();
		Usuario jose = new Usuario(1000, 10, 160, TipoDeAtraccion.paisaje,
				this.getPosicionUsuario(), 4);
		
		promocionPorcentual.aplicarPromocion(jose, this.listaItinerario,true);
		
		Assert.assertTrue(estadoFechaValida);
		Assert.assertEquals(jose.getPresupuesto(),1057 ,0);
	}
	
	@Test
	public void aplicarPromocionConFechasInvalidas() {
		this.llenarPaqueteDeAtracciones();
		this.configurarFechaFueraDeRango();

		Promocion promocionPorcentual = new PromocionPorcentual(
				this.Fechainicio, this.fechaFinal, 30, this.listaDeatracciones);

		boolean estadoFechaInvalida = promocionPorcentual.estadoFechaDePromocionValida();
		Usuario jose = new Usuario(1000, 10, 160, TipoDeAtraccion.paisaje,
				this.getPosicionUsuario(), 4);
		
		promocionPorcentual.aplicarPromocion(jose, this.listaItinerario,true);
		
		Assert.assertFalse(estadoFechaInvalida);
		Assert.assertEquals(jose.getPresupuesto(),1000 ,0);
	}
	
	@Test
	public void aplicarPromocionConAtraccionesNoIncluidasEnItinerario(){
		this.llenarPaqueteDeAtracciones();
		this.configurarFecha();

		Promocion promocionPorcentual = new PromocionPorcentual(
				this.Fechainicio, this.fechaFinal, 30, this.listaDeAtraccionesFallida);

		boolean estadoFechaValida = promocionPorcentual.estadoFechaDePromocionValida();
		Usuario jose = new Usuario(1000, 10, 160, TipoDeAtraccion.paisaje,
				this.getPosicionUsuario(), 4);
		
		promocionPorcentual.aplicarPromocion(jose, this.listaItinerario,true);
		
		Assert.assertTrue(estadoFechaValida);
		Assert.assertEquals(jose.getPresupuesto(),1000 ,0);
	}
	
	
	@Test
	public void aplicarPromocionCuandoNoSonAcumulables(){
		this.llenarPaqueteDeAtracciones();
		this.configurarFecha();

		Promocion promocionPorcentual = new PromocionPorcentual(
				this.Fechainicio, this.fechaFinal, 30, this.listaDeatracciones);

		boolean estadoFechaValida = promocionPorcentual.estadoFechaDePromocionValida();
		Usuario jose = new Usuario(1000, 10, 160, TipoDeAtraccion.paisaje,
				this.getPosicionUsuario(), 4);
		
		promocionPorcentual.aplicarPromocion(jose, this.listaItinerario,false);
		
		Assert.assertTrue(estadoFechaValida);
		Assert.assertEquals(jose.getPresupuesto(),1000 ,0);
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

	private PosicionPorCoordenadas getPosicionUsuario() {
		PosicionPorCoordenadas posicionUsuario = new PosicionPorCoordenadas(10,
				10);
		return posicionUsuario;
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
