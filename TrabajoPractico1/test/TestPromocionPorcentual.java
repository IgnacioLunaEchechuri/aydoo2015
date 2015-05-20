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
	public void ValidarAplicarPromocion() {
		this.llenarPaqueteDeAtracciones();
		this.configurarFecha();

		Promocion promocionPorcentual = new PromocionPorcentual(
				this.Fechainicio, this.fechaFinal, 30, this.listaDeatracciones);

		boolean estadoPromocion = promocionPorcentual.getAplicarPromocion();
		Assert.assertFalse(estadoPromocion);

		promocionPorcentual.setAplicarPromocion(true);
		estadoPromocion = promocionPorcentual.getAplicarPromocion();
		Assert.assertTrue(estadoPromocion);

	}

	@Test
	public void ValidarConFechasValidas() {
		this.llenarPaqueteDeAtracciones();
		this.configurarFecha();

		Promocion promocionPorcentual = new PromocionPorcentual(
				this.Fechainicio, this.fechaFinal, 30, this.listaDeatracciones);

		boolean validarFechas = promocionPorcentual.validarFechaPromocion();
		Assert.assertTrue(validarFechas);
	}

	@Test
	public void ValidarConFechasInvalidas() {

		this.llenarPaqueteDeAtracciones();
		this.configurarFechaFueraDeRango();
		Promocion promocionPorcentual = new PromocionPorcentual(
				this.Fechainicio, this.fechaFinal, 30, this.listaDeatracciones);

		boolean validarFechas = promocionPorcentual.validarFechaPromocion();

		Assert.assertFalse(validarFechas);
	}

	@Test
	public void AplicarPromocionConFechasInvalidas() {
		this.llenarPaqueteDeAtracciones();
		this.configurarFechaFueraDeRango();
		Usuario jose = new Usuario(1000, 10, 160, TipoDeAtraccion.paisaje,
				this.getPosicionUsuario(), 4);
		Promocion promocionPorcentual = new PromocionPorcentual(
				this.Fechainicio, this.fechaFinal, 30, this.listaDeatracciones);

		boolean validarDevolverPromocion = promocionPorcentual
				.devolverPromocion(jose, this.listaItinerario);

		Assert.assertFalse(validarDevolverPromocion);
	}

	@Test
	public void AplicarPromocionConFechasValidas() {
		this.llenarPaqueteDeAtracciones();
		this.configurarFecha();
		Usuario jose = new Usuario(1000, 10, 160, TipoDeAtraccion.paisaje,
				this.getPosicionUsuario(), 4);
		Promocion promocionPorcentual = new PromocionPorcentual(
				this.Fechainicio, this.fechaFinal, 30, this.listaDeatracciones);

		boolean validarDevolverPromocion = promocionPorcentual
				.devolverPromocion(jose, this.listaItinerario);

		Assert.assertTrue(validarDevolverPromocion);
	}

	@Test
	public void AtraccionesIncluidasEnPromocionValidas() {
		this.llenarPaqueteDeAtracciones();
		this.configurarFecha();

		Promocion promocionPorcentual = new PromocionPorcentual(
				this.Fechainicio, this.fechaFinal, 30, this.listaDeatracciones);

		boolean validarDevolverPromocion = ((PromocionPorcentual) promocionPorcentual)
				.validarAtraccionesdeLaPromocion(listaItinerario);

		Assert.assertTrue(validarDevolverPromocion);
	}

	@Test
	public void AtraccionesIncluidasEnPromocionInvalidas() {
		this.llenarPaqueteDeAtracciones();
		this.configurarFecha();

		Promocion promocionPorcentual = new PromocionPorcentual(
				this.Fechainicio, this.fechaFinal, 30, this.listaDeAtraccionesFallida);

		boolean validarDevolverPromocion =  ((PromocionPorcentual) promocionPorcentual).validarAtraccionesdeLaPromocion(listaItinerario);

		Assert.assertFalse(validarDevolverPromocion);
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
