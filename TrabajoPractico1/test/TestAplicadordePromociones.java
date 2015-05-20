
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class TestAplicadordePromociones {

	private List<Atraccion> listaDeatracciones;
	private List<Atraccion> listaDeAtraccionesFallida;
	private Itinerario listaItinerario;
	private Atraccion atraccionGratis;
	private AplicadorDePromociones aplicadorDePromociones;

	Date Fechainicio = new Date();
	Date fechaFinal = new Date();

	@Test
	public void EjecucionPromocionNoAcumulableValida() {
		this.llenarPaqueteDeAtracciones();
		this.configurarFecha();
		this.cargarPromociones();
		Usuario jose = new Usuario(100, 10, 160, TipoDeAtraccion.paisaje,this.getPosicionUsuario(), 4);
		this.aplicadorDePromociones.ejecutarPromociones(jose,this.listaItinerario);

		float presupuestoUsuario=jose.getPresupuesto();
		boolean ejecucionPromocionAcumulable=this.aplicadorDePromociones.aplicacionPromocionAcumulable();
		boolean ejecucionPromocionNoAcumulable=this.aplicadorDePromociones.aplicacionPromocionNoAcumulable();
		
		Assert.assertEquals(presupuestoUsuario, 119, 0);
		Assert.assertFalse(ejecucionPromocionAcumulable);
		Assert.assertTrue(ejecucionPromocionNoAcumulable);
	}

	@Test
	public void EjecucionPromocionNoAcumulableInvalidaPorFechaInvalida() {
		this.llenarPaqueteDeAtracciones();
		this.configurarFechaFueraDeRango();
		this.cargarPromociones();
		Usuario jose = new Usuario(100, 10, 160, TipoDeAtraccion.paisaje,
				this.getPosicionUsuario(), 4);
		this.aplicadorDePromociones.ejecutarPromociones(jose,
				this.listaItinerario);

		
		boolean ejecucionPromocionAcumulable=this.aplicadorDePromociones.aplicacionPromocionAcumulable();
		boolean ejecucionPromocionNoAcumulable=this.aplicadorDePromociones.aplicacionPromocionNoAcumulable();
		
		
		Assert.assertFalse(ejecucionPromocionAcumulable);
		Assert.assertFalse(ejecucionPromocionNoAcumulable);
	}

	@Test
	public void EjecucionPromocionAcumulableValida() {
		this.llenarPaqueteDeAtracciones();
		this.configurarFecha();
		this.cargarPromociones();
		Usuario jose = new Usuario(100, 10, 160, TipoDeAtraccion.paisaje,
				this.getPosicionUsuario(), 3);
		this.aplicadorDePromociones.ejecutarPromociones(jose,
				this.listaItinerario);

		
		boolean ejecucionPromocionAcumulable=this.aplicadorDePromociones.aplicacionPromocionAcumulable();
		boolean ejecucionPromocionNoAcumulable=this.aplicadorDePromociones.aplicacionPromocionNoAcumulable();
		
	
		Assert.assertTrue(ejecucionPromocionAcumulable);
		Assert.assertFalse(ejecucionPromocionNoAcumulable);
	}

	@Test
	public void EjecucionPromocionAcumulableInvalidaPorFechaInvalida() {
		this.llenarPaqueteDeAtracciones();
		this.configurarFechaFueraDeRango();
		this.cargarPromociones();
		Usuario jose = new Usuario(100, 10, 160, TipoDeAtraccion.paisaje,
				this.getPosicionUsuario(), 3);
		this.aplicadorDePromociones.ejecutarPromociones(jose,
				this.listaItinerario);

		float presupuestoUsuario=jose.getPresupuesto();
		boolean ejecucionPromocionAcumulable=this.aplicadorDePromociones.aplicacionPromocionAcumulable();
		boolean ejecucionPromocionNoAcumulable=this.aplicadorDePromociones.aplicacionPromocionNoAcumulable();
		
		
		Assert.assertFalse(ejecucionPromocionAcumulable);
		Assert.assertFalse(ejecucionPromocionNoAcumulable);
	}
	
	
	@Test
	public void promocionesAcumulablesSoloEjecucionAxB() {
		this.llenarPaqueteDeAtracciones();
		this.configurarFecha();
		this.cargarPromociones();
		Usuario jose = new Usuario(100, 10, 160, TipoDeAtraccion.paisaje,
				this.getPosicionUsuario(), 3);
		this.aplicadorDePromociones.ejecutarPromociones(jose,
				this.listaItinerario);

		
		float presupuestoUsuario=jose.getPresupuesto();
		boolean ejecucionPromocionAcumulable=this.aplicadorDePromociones.aplicacionPromocionAcumulable();
		boolean ejecucionPromocionNoAcumulable=this.aplicadorDePromociones.aplicacionPromocionNoAcumulable();
		float tamanoItinerario=this.listaItinerario.tamanoItinerario();
		
		Assert.assertEquals(presupuestoUsuario, 100, 0);
		Assert.assertTrue(ejecucionPromocionAcumulable);
		Assert.assertFalse(ejecucionPromocionNoAcumulable);
		Assert.assertEquals (tamanoItinerario,3,0);
		
		
	}
	
	@Test
	public void promocionesAcumulablesAplicacionTodasLasPromociones() {
		this.llenarPaqueteDeAtracciones();
		this.configurarFecha();
		this.cargarPromocionesOtroOrden();
		Usuario jose = new Usuario(1000, 10, 160, TipoDeAtraccion.paisaje,
				this.getPosicionUsuario(), 3);
		this.aplicadorDePromociones.ejecutarPromociones(jose,
				this.listaItinerario);

		float presupuestoUsuario=jose.getPresupuesto();
		boolean ejecucionPromocionAcumulable=this.aplicadorDePromociones.aplicacionPromocionAcumulable();
		boolean ejecucionPromocionNoAcumulable=this.aplicadorDePromociones.aplicacionPromocionNoAcumulable();
		float tamanoItinerario=this.listaItinerario.tamanoItinerario();
		
		Assert.assertEquals(presupuestoUsuario, 1077, 0);
		Assert.assertTrue(ejecucionPromocionAcumulable);
		Assert.assertFalse(ejecucionPromocionNoAcumulable);
		Assert.assertEquals (tamanoItinerario,3,0);
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

	private void cargarPromociones() {
		this.aplicadorDePromociones = new AplicadorDePromociones();
		aplicadorDePromociones.agregarPromociones(new PromocionFamiliar(
				this.Fechainicio, this.fechaFinal));
		aplicadorDePromociones.agregarPromociones(new PromocionAxB(
				this.Fechainicio, this.fechaFinal, atraccionGratis,
				this.listaDeatracciones));
		aplicadorDePromociones
				.agregarPromociones(new PromocionPorcentual(this.Fechainicio,
						this.fechaFinal, 30, this.listaDeatracciones));
		aplicadorDePromociones
				.agregarPromociones(new PromocionAbsoluta(this.Fechainicio,
						this.fechaFinal, this.listaDeatracciones, 20));
		aplicadorDePromociones.agregarPromociones(new PromocionExtranjero(
				this.Fechainicio, this.fechaFinal, this.listaDeatracciones));

	}
	private void cargarPromocionesOtroOrden() {
		this.aplicadorDePromociones = new AplicadorDePromociones();
		aplicadorDePromociones.agregarPromociones(new PromocionFamiliar(
				this.Fechainicio, this.fechaFinal));
		
		aplicadorDePromociones
				.agregarPromociones(new PromocionPorcentual(this.Fechainicio,
						this.fechaFinal, 30, this.listaDeatracciones));
		aplicadorDePromociones
				.agregarPromociones(new PromocionAbsoluta(this.Fechainicio,
						this.fechaFinal, this.listaDeatracciones, 20));
		aplicadorDePromociones.agregarPromociones(new PromocionExtranjero(
				this.Fechainicio, this.fechaFinal, this.listaDeatracciones));
		aplicadorDePromociones.agregarPromociones(new PromocionAxB(
				this.Fechainicio, this.fechaFinal, atraccionGratis,
				this.listaDeatracciones));
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

		this.atraccionGratis = gondor;

	}

}
