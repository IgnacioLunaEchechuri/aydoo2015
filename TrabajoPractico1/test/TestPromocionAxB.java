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
	public void ValidarAplicarPromocion() {
		this.llenarPaqueteDeAtracciones();
		this.configurarFecha();

		Promocion promocionAxB = new PromocionAxB(
				this.Fechainicio, this.fechaFinal,atraccionGratis, this.listaDeatracciones);

		boolean estadoPromocion = promocionAxB.getAplicarPromocion();
		Assert.assertFalse(estadoPromocion);

		promocionAxB.setAplicarPromocion(true);
		estadoPromocion = promocionAxB.getAplicarPromocion();
		Assert.assertTrue(estadoPromocion);

	}

	@Test
	public void ValidarConFechasValidas() {
		this.llenarPaqueteDeAtracciones();
		this.configurarFecha();

		Promocion promocionAxB = new PromocionAxB(
				this.Fechainicio, this.fechaFinal,atraccionGratis, this.listaDeatracciones);
		boolean validarFechas = promocionAxB.validarFechaPromocion();
		Assert.assertTrue(validarFechas);
	}
	
	@Test
	public void ValidarConFechasInvalidas() {
		
		this.llenarPaqueteDeAtracciones();
		this.configurarFechaFueraDeRango();
		Promocion promocionAxB = new PromocionAxB(
				this.Fechainicio, this.fechaFinal,atraccionGratis, this.listaDeatracciones);
		
		boolean validarFechas = promocionAxB.validarFechaPromocion();
		
		Assert.assertFalse(validarFechas);
	}

	@Test
	public void AplicarPromocionConFechasInvalidas() {
		this.llenarPaqueteDeAtracciones();
		this.configurarFechaFueraDeRango();
		Usuario jose = new Usuario(1000, 10, 160, TipoDeAtraccion.paisaje,this.getPosicionUsuario(),4);
		
		Promocion promocionAxB = new PromocionAxB(
				this.Fechainicio, this.fechaFinal,atraccionGratis, this.listaDeatracciones);
		
		boolean validarDevolverPromocion = promocionAxB.devolverPromocion(jose, this.listaItinerario);
		
		Assert.assertFalse(validarDevolverPromocion);
	}
	@Test
	public void AplicarPromocionConFechasValidas() {
		this.llenarPaqueteDeAtracciones();
		this.configurarFecha();
		Usuario jose = new Usuario(1000, 10, 160, TipoDeAtraccion.paisaje,this.getPosicionUsuario(),4);
		Promocion promocionAxB = new PromocionAxB(
				this.Fechainicio, this.fechaFinal,atraccionGratis, this.listaDeatracciones);
		boolean validarDevolverPromocion = promocionAxB.devolverPromocion(jose, this.listaItinerario);
		
		Assert.assertTrue(validarDevolverPromocion);
	}
	
	@Test
	public void AtraccionesIncluidasEnPromocionValidas() {
		this.llenarPaqueteDeAtracciones();
		this.configurarFecha();
		
		Promocion promocionAxB = new PromocionAxB(
				this.Fechainicio, this.fechaFinal, atraccionGratis, this.listaDeatracciones);
		boolean validarDevolverPromocion = ((PromocionAxB) promocionAxB).validarAtraccionesdeLaPromocion(listaItinerario);
		
		Assert.assertTrue(validarDevolverPromocion);
	}
	
	@Test
	public void AtraccionesIncluidasEnPromocionInvalidas() {
		this.llenarPaqueteDeAtracciones();
		this.configurarFecha();
		
		Promocion promocionAxB = new PromocionAxB(
				this.Fechainicio, this.fechaFinal,atraccionGratis, this.listaDeAtraccionesFallida);
		
		boolean validarDevolverPromocion = ((PromocionAxB) promocionAxB).validarAtraccionesdeLaPromocion(listaItinerario);
		
		Assert.assertFalse(validarDevolverPromocion);
	}
	
	@Test
	public void AgregarAtraccionAlItinerario() {
		this.llenarPaqueteDeAtracciones();
		this.configurarFecha();
		Usuario jose = new Usuario(1000, 10, 300, TipoDeAtraccion.paisaje,
				this.getPosicionUsuario(), 4);
		Promocion promocionAxB = new PromocionAxB(
				this.Fechainicio, this.fechaFinal,atraccionGratis, this.listaDeatracciones);
		
		int cantidadDeAtraccionesItinerario = this.listaItinerario
				.tamanoItinerario();
		Assert.assertEquals(cantidadDeAtraccionesItinerario, 2);

		boolean validarDevolverPromocion = promocionAxB.devolverPromocion(
				jose, this.listaItinerario);
		Assert.assertTrue(validarDevolverPromocion);

		cantidadDeAtraccionesItinerario = this.listaItinerario
				.tamanoItinerario();
		
		Assert.assertEquals(cantidadDeAtraccionesItinerario, 3);
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