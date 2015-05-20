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
	public void ValidarAplicarPromocion() {
		this.llenarPaqueteDeAtracciones();
		this.configurarFecha();

		Promocion promocionAbsoluta = new PromocionAbsoluta(
				this.Fechainicio, this.fechaFinal, this.listaDeatracciones, 20);

		boolean estadoPromocion = promocionAbsoluta.getAplicarPromocion();
		Assert.assertFalse(estadoPromocion);

		promocionAbsoluta.setAplicarPromocion(true);
		estadoPromocion = promocionAbsoluta.getAplicarPromocion();
		Assert.assertTrue(estadoPromocion);

	}

	@Test
	public void ValidarConFechasValidas() {
		this.llenarPaqueteDeAtracciones();
		this.configurarFecha();

		Promocion promocionAbsoluta = new PromocionAbsoluta(
				this.Fechainicio, this.fechaFinal, this.listaDeatracciones, 20);
		boolean validarFechas = promocionAbsoluta.validarFechaPromocion();
		Assert.assertTrue(validarFechas);
	}

	@Test
	public void ValidarConFechasInvalidas() {

		this.llenarPaqueteDeAtracciones();
		this.configurarFechaFueraDeRango();
		Promocion promocionAbsoluta = new PromocionAbsoluta(
				this.Fechainicio, this.fechaFinal, this.listaDeatracciones, 20);

		boolean validarFechas = promocionAbsoluta.validarFechaPromocion();

		Assert.assertFalse(validarFechas);
	}

	@Test
	public void AplicarPromocionConFechasInvalidas() {
		this.llenarPaqueteDeAtracciones();
		this.configurarFechaFueraDeRango();
		Usuario jose = new Usuario(1000, 10, 160, TipoDeAtraccion.paisaje,
				this.getPosicionUsuario(), 4);
		Promocion promocionAbsoluta = new PromocionAbsoluta(
				this.Fechainicio, this.fechaFinal, this.listaDeatracciones, 20);

		boolean validarDevolverPromocion = promocionAbsoluta.devolverPromocion(
				jose, this.listaItinerario);

		Assert.assertFalse(validarDevolverPromocion);
	}

	@Test
	public void AplicarPromocionConFechasValidas() {
		this.llenarPaqueteDeAtracciones();
		this.configurarFecha();
		Usuario jose = new Usuario(1000, 10, 160, TipoDeAtraccion.paisaje,
				this.getPosicionUsuario(), 4);
		Promocion promocionAbsoluta = new PromocionAbsoluta(
				this.Fechainicio, this.fechaFinal, this.listaDeatracciones, 20);

		boolean validarDevolverPromocion = promocionAbsoluta.devolverPromocion(
				jose, this.listaItinerario);

		Assert.assertTrue(validarDevolverPromocion);
	}

	@Test
	public void AtraccionesIncluidasEnPromocionValidas() {
		this.llenarPaqueteDeAtracciones();
		this.configurarFecha();

		Promocion promocionAbsoluta = new PromocionAbsoluta(
				this.Fechainicio, this.fechaFinal, this.listaDeatracciones, 20);
		boolean validarDevolverPromocion = ((PromocionAbsoluta) promocionAbsoluta)
				.validarAtraccionesdeLaPromocion(listaItinerario);

		Assert.assertTrue(validarDevolverPromocion);
	}

	@Test
	public void AtraccionesIncluidasEnPromocionInvalidas() {
		this.llenarPaqueteDeAtracciones();
		this.configurarFecha();

		Promocion promocionAbsoluta = new PromocionAbsoluta(
				this.Fechainicio, this.fechaFinal,
				this.listaDeAtraccionesFallida, 20);
		boolean validarDevolverPromocion = ((PromocionAbsoluta) promocionAbsoluta)
				.validarAtraccionesdeLaPromocion(listaItinerario);

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
