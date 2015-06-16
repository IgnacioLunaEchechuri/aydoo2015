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
	private Atraccion atraccionGratis;

	Date Fechainicio = new Date();
	Date fechaFinal = new Date();

	

	@Test
	public void aplicarPromocionConFechasValidas() {
		this.llenarPaqueteDeAtracciones();
		this.configurarFecha();

		Promocion promocionAxB = new PromocionAxB(this.Fechainicio,
				this.fechaFinal, atraccionGratis, this.listaDeatracciones);
		boolean estadoFechasValidas = promocionAxB
				.estadoFechaDePromocionValida();

		Assert.assertEquals(this.listaItinerario.tamanoItinerario(), 2, 0);
		Assert.assertTrue(estadoFechasValidas);
		Usuario jose = new Usuario(1000, 10, 160, TipoDeAtraccion.paisaje,
				this.getPosicionUsuario(), 4);
		promocionAxB.aplicarPromocion(jose, this.listaItinerario, true);
		Assert.assertEquals(this.listaItinerario.tamanoItinerario(), 3, 0);

	}
	
	@Test
	public void aplicarPromocionConFechasInvalidas() {
		this.llenarPaqueteDeAtracciones();
		this.configurarFechaFueraDeRango();

		Promocion promocionAxB = new PromocionAxB(this.Fechainicio,
				this.fechaFinal, atraccionGratis, this.listaDeatracciones);
		boolean estadoFechasInvalidas = promocionAxB
				.estadoFechaDePromocionValida();

		Assert.assertEquals(this.listaItinerario.tamanoItinerario(), 2, 0);
		Assert.assertFalse(estadoFechasInvalidas);
		Usuario jose = new Usuario(1000, 10, 160, TipoDeAtraccion.paisaje,
				this.getPosicionUsuario(), 4);
		promocionAxB.aplicarPromocion(jose, this.listaItinerario, true);
		Assert.assertEquals(this.listaItinerario.tamanoItinerario(), 2, 0);

	}
	
	@Test
	public void aplicarPromocionConUsuarioSinTiempo() {
		this.llenarPaqueteDeAtracciones();
		this.configurarFecha();

		Promocion promocionAxB = new PromocionAxB(this.Fechainicio,
				this.fechaFinal, atraccionGratis, this.listaDeatracciones);
		boolean estadoFechasValidas = promocionAxB
				.estadoFechaDePromocionValida();

		Assert.assertEquals(this.listaItinerario.tamanoItinerario(), 2, 0);
		Assert.assertTrue(estadoFechasValidas);
		Usuario jose = new Usuario(1000, 10, 0, TipoDeAtraccion.paisaje,
				this.getPosicionUsuario(), 4);
		promocionAxB.aplicarPromocion(jose, this.listaItinerario, true);
		Assert.assertEquals(this.listaItinerario.tamanoItinerario(), 2, 0);

	}
	
	
	@Test
	public void aplicarPromocionConAtraccionesNoIncluidasEnItinerario() {
		this.llenarPaqueteDeAtracciones();
		this.configurarFecha();

		Promocion promocionAxB = new PromocionAxB(this.Fechainicio,
				this.fechaFinal, atraccionGratis, this.listaDeAtraccionesFallida);
		boolean estadoFechasValidas = promocionAxB
				.estadoFechaDePromocionValida();

		Assert.assertEquals(this.listaItinerario.tamanoItinerario(), 2, 0);
		Assert.assertTrue(estadoFechasValidas);
		Usuario jose = new Usuario(1000, 10, 0, TipoDeAtraccion.paisaje,
				this.getPosicionUsuario(), 4);
		promocionAxB.aplicarPromocion(jose, this.listaItinerario, true);
		Assert.assertEquals(this.listaItinerario.tamanoItinerario(), 2, 0);
		
	}
	
	@Test
	public void aplicarPromocionCuandoNoSonAcumulables() {
		this.llenarPaqueteDeAtracciones();
		this.configurarFecha();

		Promocion promocionAxB = new PromocionAxB(this.Fechainicio,
				this.fechaFinal, atraccionGratis, this.listaDeatracciones);
		boolean estadoFechasValidas = promocionAxB
				.estadoFechaDePromocionValida();

		Assert.assertEquals(this.listaItinerario.tamanoItinerario(), 2, 0);
		Assert.assertTrue(estadoFechasValidas);
		Usuario jose = new Usuario(1000, 10, 160, TipoDeAtraccion.paisaje,
				this.getPosicionUsuario(), 4);
		promocionAxB.aplicarPromocion(jose, this.listaItinerario, false);
		Assert.assertEquals(this.listaItinerario.tamanoItinerario(), 2, 0);

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

	private PosicionPorCoordenadas getPosicionUsuario(){
		PosicionPorCoordenadas posicionUsuario = new PosicionPorCoordenadas(10,
				10);
		return  posicionUsuario ;
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
		
		this.atraccionGratis=gondor;

	}

}