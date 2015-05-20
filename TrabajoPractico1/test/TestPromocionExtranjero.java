import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class TestPromocionExtranjero {
	private List<Atraccion> listaDeatracciones;
	private List<Atraccion> listaDeAtraccionesFallida;
	private Itinerario listaItinerario;

	Date Fechainicio = new Date();
	Date fechaFinal = new Date();

	@Test
	public void ValidarConFechasValidas() {
		this.llenarPaqueteDeAtracciones();
		this.configurarFecha();

		Promocion promocionExtranjero = new PromocionExtranjero(
				this.Fechainicio, this.fechaFinal,this.listaDeatracciones);

		boolean validarFechas = promocionExtranjero.validarFechaPromocion();
		Assert.assertTrue(validarFechas);
	}

	@Test
	public void ValidarConFechasInvalidas() {

		this.llenarPaqueteDeAtracciones();
		this.configurarFechaFueraDeRango();
		Promocion promocionExtranjero = new PromocionExtranjero(
				this.Fechainicio, this.fechaFinal,this.listaDeatracciones);

		boolean validarFechas = promocionExtranjero.validarFechaPromocion();

		Assert.assertFalse(validarFechas);
	}

	@Test
	public void AplicarPromocionConFechasInvalidas() {
		this.llenarPaqueteDeAtracciones();
		this.configurarFechaFueraDeRango();
		Usuario jose = new Usuario(1000, 10, 160, TipoDeAtraccion.paisaje,
				this.getPosicionUsuarioMasDoscientosKilometros(),4);
		Promocion promocionExtranjero = new PromocionExtranjero(
				this.Fechainicio, this.fechaFinal,this.listaDeatracciones);

		boolean validarDevolverPromocion = promocionExtranjero
				.devolverPromocion(jose, this.listaItinerario);
		Assert.assertFalse(validarDevolverPromocion);
	}

	@Test
	public void AplicarPromocionConFechasValidas() {
		this.llenarPaqueteDeAtracciones();
		this.configurarFecha();
		Usuario jose = new Usuario(1000, 10, 160, TipoDeAtraccion.paisaje,
				this.getPosicionUsuarioMasDoscientosKilometros(), 4);
		Promocion promocionExtranjero = new PromocionExtranjero(
				this.Fechainicio, this.fechaFinal,this.listaDeatracciones);

		boolean validarDevolverPromocion = promocionExtranjero
				.devolverPromocion(jose, this.listaItinerario);

		Assert.assertTrue(validarDevolverPromocion);
	}

	@Test
	public void AplicarPromocionConDistanciaInferiorADoscientos() {
		this.llenarPaqueteDeAtracciones();
		this.configurarFecha();
		Usuario jose = new Usuario(1000, 10, 160, TipoDeAtraccion.paisaje,
				this.getPosicionUsuarioMenosDoscientosKilometros(), 4);
		Promocion promocionExtranjero = new PromocionExtranjero(
				this.Fechainicio, this.fechaFinal,this.listaDeatracciones);

		boolean validarDevolverPromocion = promocionExtranjero
				.devolverPromocion(jose, this.listaItinerario);

		Assert.assertFalse(validarDevolverPromocion);
	}

	@Test
	public void AplicarPromocionConDistanciaSuperiorADoscientos() {
		this.llenarPaqueteDeAtracciones();
		this.configurarFecha();
		Usuario jose = new Usuario(1000, 10, 160, TipoDeAtraccion.paisaje,
				this.getPosicionUsuarioMasDoscientosKilometros(), 4);
		Promocion promocionExtranjero = new PromocionExtranjero(
				this.Fechainicio, this.fechaFinal,this.listaDeatracciones);

		boolean validarDevolverPromocion = promocionExtranjero
				.devolverPromocion(jose, this.listaItinerario);

		Assert.assertTrue(validarDevolverPromocion);
	}

	@Test
	public void DistanciaMaximaAlParqueInvalida() {
		this.llenarPaqueteDeAtracciones();
		
		Usuario jose = new Usuario(1000, 10, 160, TipoDeAtraccion.aventura,
				this.getPosicionUsuarioMenosDoscientosKilometros(), 1);
		Promocion promocionExtranjero = new PromocionExtranjero(
				this.Fechainicio, this.fechaFinal,this.listaDeatracciones);
		
		boolean distanciaAlParque = (((PromocionExtranjero) promocionExtranjero).calcularDistanciaUsuarioAlParque(jose.getPosicion()) > 200000);
		
		Assert.assertFalse(distanciaAlParque);

	}

	@Test
	public void DistanciaMaximaAlParqueValida() {
		this.llenarPaqueteDeAtracciones();
		Usuario jose = new Usuario(100, 300, 160, TipoDeAtraccion.aventura,
				this.getPosicionUsuarioMasDoscientosKilometros(),1);
		Promocion promocionExtranjero = new PromocionExtranjero(
				this.Fechainicio, this.fechaFinal,this.listaDeatracciones);
		boolean distanciaAlParque = (((PromocionExtranjero) promocionExtranjero).calcularDistanciaUsuarioAlParque(jose.getPosicion()) > 200000);
		
		Assert.assertTrue(distanciaAlParque);

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

	private PosicionPorCoordenadas getPosicionUsuarioMenosDoscientosKilometros() {
		PosicionPorCoordenadas posicionUsuario = new PosicionPorCoordenadas(10,
				10);
		return posicionUsuario;
	}

	private PosicionPorCoordenadas getPosicionUsuarioMasDoscientosKilometros() {
		PosicionPorCoordenadas posicionUsuario = new PosicionPorCoordenadas(
				205000, 200000);
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
