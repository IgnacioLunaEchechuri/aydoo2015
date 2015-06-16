import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class TestTierraMedia {

	private List<Atraccion> listaDeatracciones;
	private List<Atraccion> listaDeAtraccionesFallida;
	private Itinerario listaItinerario;
	private Atraccion atraccionGratis;
	private TierraMedia tierraMedia;

	Date Fechainicio = new Date();
	Date fechaFinal = new Date();

	@Test
	public void aplicarPromocionesAcumulablesConFechaValida() {
		this.tierraMedia = new TierraMedia();

		this.llenarPaqueteDeAtracciones();
		this.configurarFecha();

		Usuario jose = new Usuario(1000, 10, 160, TipoDeAtraccion.aventura,
				this.getPosicionUsuario(), 4);

		this.tierraMedia.agregarAtraccion(this.mordor());
		this.tierraMedia.agregarAtraccion(this.comarca());
		this.tierraMedia.agregarAtraccionInicial(this.mordor());
		this.tierraMedia.agregarUsuario(jose);

		tierraMedia.agregarPromociones(new PromocionFamiliar(this.Fechainicio,
				this.fechaFinal));

		tierraMedia.agregarPromociones(new PromocionAxB(this.Fechainicio,
				this.fechaFinal, atraccionGratis, this.listaDeatracciones));

		tierraMedia
				.agregarPromociones(new PromocionPorcentual(this.Fechainicio,
						this.fechaFinal, 30, this.listaDeatracciones));

		tierraMedia.agregarPromociones(new PromocionAbsoluta(this.Fechainicio,
				this.fechaFinal, this.listaDeatracciones, 20));

		tierraMedia.agregarPromociones(new PromocionExtranjero(
				this.Fechainicio, this.fechaFinal, this.listaDeatracciones));

		this.tierraMedia.generarItinerario();

		Assert.assertEquals(
				this.tierraMedia.getItinerario().tamanoItinerario(), 2);
		Assert.assertEquals(jose.getPresupuesto(), 1000, 0);

		this.tierraMedia.ejecutarPromociones();
		Assert.assertEquals(jose.getPresupuesto(), 1180, 0);
		Assert.assertEquals(this.tierraMedia.getItinerario().tamanoItinerario(), 3);

	}

	@Test
	public void aplicarPromocionesAcumulablesConFechaInvalida() {
		this.tierraMedia = new TierraMedia();

		this.llenarPaqueteDeAtracciones();
		this.configurarFechaFueraDeRango();
		Usuario jose = new Usuario(1000, 10, 160, TipoDeAtraccion.aventura,
				this.getPosicionUsuario(), 4);

		this.tierraMedia.agregarAtraccion(this.mordor());
		this.tierraMedia.agregarAtraccion(this.comarca());
		this.tierraMedia.agregarAtraccionInicial(this.mordor());
		this.tierraMedia.agregarUsuario(jose);

		tierraMedia.agregarPromociones(new PromocionFamiliar(this.Fechainicio,
				this.fechaFinal));

		tierraMedia.agregarPromociones(new PromocionAxB(this.Fechainicio,
				this.fechaFinal, atraccionGratis, this.listaDeatracciones));

		tierraMedia
				.agregarPromociones(new PromocionPorcentual(this.Fechainicio,
						this.fechaFinal, 30, this.listaDeatracciones));

		tierraMedia.agregarPromociones(new PromocionAbsoluta(this.Fechainicio,
				this.fechaFinal, this.listaDeatracciones, 20));

		tierraMedia.agregarPromociones(new PromocionExtranjero(
				this.Fechainicio, this.fechaFinal, this.listaDeatracciones));

		this.tierraMedia.generarItinerario();

		Assert.assertEquals(
				this.tierraMedia.getItinerario().tamanoItinerario(), 2);
		Assert.assertEquals(jose.getPresupuesto(), 1000, 0);

		this.tierraMedia.ejecutarPromociones();
		Assert.assertEquals(jose.getPresupuesto(), 1000, 0);
		Assert.assertEquals(
				this.tierraMedia.getItinerario().tamanoItinerario(), 2);

	}

	@Test
	public void aplicarPromocionesNoAcumulablesConFechaValida() {
		this.tierraMedia = new TierraMedia();

		this.llenarPaqueteDeAtracciones();
		this.configurarFecha();

		Usuario jose = new Usuario(1000, 10, 160, TipoDeAtraccion.aventura,
				this.getPosicionUsuarioMasDoscientosKilometros(), 4);

		this.tierraMedia.agregarAtraccion(this.mordor());
		this.tierraMedia.agregarAtraccion(this.comarca());
		this.tierraMedia.agregarAtraccionInicial(this.mordor());
		this.tierraMedia.agregarUsuario(jose);

		tierraMedia.agregarPromociones(new PromocionFamiliar(this.Fechainicio,
				this.fechaFinal));

		tierraMedia.agregarPromociones(new PromocionAxB(this.Fechainicio,
				this.fechaFinal, atraccionGratis, this.listaDeatracciones));

		tierraMedia
				.agregarPromociones(new PromocionPorcentual(this.Fechainicio,
						this.fechaFinal, 30, this.listaDeatracciones));

		tierraMedia.agregarPromociones(new PromocionAbsoluta(this.Fechainicio,
				this.fechaFinal, this.listaDeatracciones, 20));

		tierraMedia.agregarPromociones(new PromocionExtranjero(
				this.Fechainicio, this.fechaFinal, this.listaDeatracciones));

		this.tierraMedia.generarItinerario();

		Assert.assertEquals(
				this.tierraMedia.getItinerario().tamanoItinerario(), 2);
		Assert.assertEquals(jose.getPresupuesto(), 1000, 0);

		this.tierraMedia.ejecutarPromociones();
		Assert.assertEquals(jose.getPresupuesto(), 1380, 0);

	}

	@Test
	public void aplicarPromocionesNoAcumulablesConFechaInvalida() {
		this.tierraMedia = new TierraMedia();

		this.llenarPaqueteDeAtracciones();
		this.configurarFechaFueraDeRango();

		Usuario jose = new Usuario(1000, 10, 160, TipoDeAtraccion.aventura,
				this.getPosicionUsuarioMasDoscientosKilometros(), 4);

		this.tierraMedia.agregarAtraccion(this.mordor());
		this.tierraMedia.agregarAtraccion(this.comarca());
		this.tierraMedia.agregarAtraccionInicial(this.mordor());
		this.tierraMedia.agregarUsuario(jose);

		tierraMedia.agregarPromociones(new PromocionFamiliar(this.Fechainicio,
				this.fechaFinal));

		tierraMedia.agregarPromociones(new PromocionAxB(this.Fechainicio,
				this.fechaFinal, atraccionGratis, this.listaDeatracciones));

		tierraMedia
				.agregarPromociones(new PromocionPorcentual(this.Fechainicio,
						this.fechaFinal, 30, this.listaDeatracciones));

		tierraMedia.agregarPromociones(new PromocionAbsoluta(this.Fechainicio,
				this.fechaFinal, this.listaDeatracciones, 20));

		tierraMedia.agregarPromociones(new PromocionExtranjero(
				this.Fechainicio, this.fechaFinal, this.listaDeatracciones));

		this.tierraMedia.generarItinerario();

		Assert.assertEquals(
				this.tierraMedia.getItinerario().tamanoItinerario(), 2);
		Assert.assertEquals(jose.getPresupuesto(), 1000, 0);

		this.tierraMedia.ejecutarPromociones();
		Assert.assertEquals(jose.getPresupuesto(), 1000,0);

	}
@Test
	public void aplicarPromocionesAcumulablesConFechaValidaYAplicandoItinerarioAlPresupuestoDelUsuario() {
		this.tierraMedia = new TierraMedia();

		this.llenarPaqueteDeAtracciones();
		this.configurarFecha();

		Usuario jose = new Usuario(1000, 10, 160, TipoDeAtraccion.aventura,
				this.getPosicionUsuario(), 4);

		this.tierraMedia.agregarAtraccion(this.mordor());
		this.tierraMedia.agregarAtraccion(this.comarca());
		this.tierraMedia.agregarAtraccionInicial(this.mordor());
		this.tierraMedia.agregarUsuario(jose);

		tierraMedia.agregarPromociones(new PromocionFamiliar(this.Fechainicio,
				this.fechaFinal));

		tierraMedia.agregarPromociones(new PromocionAxB(this.Fechainicio,
				this.fechaFinal, atraccionGratis, this.listaDeatracciones));

		tierraMedia
				.agregarPromociones(new PromocionPorcentual(this.Fechainicio,
						this.fechaFinal, 30, this.listaDeatracciones));

		tierraMedia.agregarPromociones(new PromocionAbsoluta(this.Fechainicio,
				this.fechaFinal, this.listaDeatracciones, 20));

		tierraMedia.agregarPromociones(new PromocionExtranjero(
				this.Fechainicio, this.fechaFinal, this.listaDeatracciones));

		this.tierraMedia.generarItinerario();

		Assert.assertEquals(
				this.tierraMedia.getItinerario().tamanoItinerario(), 2);
		Assert.assertEquals(jose.getPresupuesto(), 1000, 0);
		Assert.assertEquals(jose.getTiempoDisponible(), 160,0);
		
		this.tierraMedia.aplicarItinerarioAlUsuario();
		
		Assert.assertEquals(jose.getPresupuesto(), 420, 0);
		Assert.assertEquals(jose.getTiempoDisponible(),100,0);
		Assert.assertEquals(this.tierraMedia.getItinerario().tamanoItinerario(), 3);

	}
	

@Test
	public void aplicarPromocionesNoAcumulablesConFechaValidaYAplicandoItinerarioAlPresupuestoDelUsuario() {
		this.tierraMedia = new TierraMedia();

		this.llenarPaqueteDeAtracciones();
		this.configurarFecha();

		Usuario jose = new Usuario(1000, 10, 160, TipoDeAtraccion.aventura,
				this.getPosicionUsuarioMasDoscientosKilometros(), 4);

		this.tierraMedia.agregarAtraccion(this.mordor());
		this.tierraMedia.agregarAtraccion(this.comarca());
		this.tierraMedia.agregarAtraccionInicial(this.mordor());
		this.tierraMedia.agregarUsuario(jose);

		tierraMedia.agregarPromociones(new PromocionFamiliar(this.Fechainicio,
				this.fechaFinal));

		tierraMedia.agregarPromociones(new PromocionAxB(this.Fechainicio,
				this.fechaFinal, atraccionGratis, this.listaDeatracciones));

		tierraMedia
				.agregarPromociones(new PromocionPorcentual(this.Fechainicio,
						this.fechaFinal, 30, this.listaDeatracciones));

		tierraMedia.agregarPromociones(new PromocionAbsoluta(this.Fechainicio,
				this.fechaFinal, this.listaDeatracciones, 20));

		tierraMedia.agregarPromociones(new PromocionExtranjero(
				this.Fechainicio, this.fechaFinal, this.listaDeatracciones));

		this.tierraMedia.generarItinerario();

		Assert.assertEquals(
				this.tierraMedia.getItinerario().tamanoItinerario(), 2);
		Assert.assertEquals(jose.getPresupuesto(), 1000, 0);
		Assert.assertEquals(jose.getTiempoDisponible(), 160,0);
		
		this.tierraMedia.aplicarItinerarioAlUsuario();
		
		Assert.assertEquals(jose.getPresupuesto(), 620, 0);
		Assert.assertEquals(jose.getTiempoDisponible(),100,0);
		Assert.assertEquals(this.tierraMedia.getItinerario().tamanoItinerario(), 2);

	}
	private PosicionPorCoordenadas getPosicionUsuarioMasDoscientosKilometros() {
		PosicionPorCoordenadas posicionUsuario = new PosicionPorCoordenadas(
				205000, 200000);
		return posicionUsuario;
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

	private Atraccion mordor() {
		PosicionPorCoordenadas posicionMordor = new PosicionPorCoordenadas(10,
				10);

		Atraccion mordor = new Atraccion(posicionMordor, 100, 50, 30,
				TipoDeAtraccion.aventura);
		return mordor;
	}

	private Atraccion comarca() {
		PosicionPorCoordenadas posicionComarca = new PosicionPorCoordenadas(50,
				10);

		Atraccion comarca = new Atraccion(posicionComarca, 90, 20, 30,
				TipoDeAtraccion.aventura);
		return comarca;

	}

	private Atraccion gondor() {
		PosicionPorCoordenadas posicionGondor = new PosicionPorCoordenadas(50,
				90);

		Atraccion gondor = new Atraccion(posicionGondor, 90, 20, 30,
				TipoDeAtraccion.aventura);
		return gondor;

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

		listaItinerario.agregarAtraccion(this.mordor());
		listaItinerario.agregarAtraccion(this.comarca());

		this.listaDeatracciones.add(this.mordor());
		this.listaDeatracciones.add(this.comarca());

		this.listaDeAtraccionesFallida.add(this.comarca());
		this.listaDeAtraccionesFallida.add(this.gondor());

		this.atraccionGratis = this.gondor();

	}

}
