import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;


public class TestPromocionPorcentual {

	private List<Atraccion> atraccionSugerida;
	Date Fechainicio = new Date();
	Date fechaFinal = new Date();

	@Test
	public void testValidarPromocion() {
		
		this.configurarFecha();

		PromocionPorcentual promocionPorcentual = new PromocionPorcentual(
				this.Fechainicio, this.fechaFinal,10, this.llenarPaqueteDeAtracciones());
		
		Assert.assertTrue(promocionPorcentual.validarPromocion(this.llenarPaqueteDeAtracciones()));

		Assert.assertFalse(promocionPorcentual.validarPromocion(this.atraccionSugerida));
		
		Assert.assertEquals(10,promocionPorcentual.getPorcentajePromocion(),0);
	}

	@Test
	public void testDevolverDinero() {
		
		this.configurarFecha();
		this.llenarPaqueteDeAtracciones();
		
		PosicionPorCoordenadas posicion = new PosicionPorCoordenadas(10, 10);
		Usuario jose = new Usuario(500, 10, 160, TipoDeAtraccion.paisaje,posicion);
		
		
		PromocionPorcentual promocionPorcentual = new PromocionPorcentual(this.Fechainicio, this.fechaFinal,10,
				                                                          this.llenarPaqueteDeAtracciones());
		promocionPorcentual.setCostoTotalAtracciones(190);
		
		Assert.assertTrue(promocionPorcentual.DevolverPromocion(jose, this.llenarPaqueteDeAtracciones()));
		
		Assert.assertEquals(jose.getPresupuesto(), 519, 0);
		
		Assert.assertFalse(promocionPorcentual.DevolverPromocion(jose, this.atraccionSugerida));
		
		Assert.assertEquals(jose.getPresupuesto(), 519, 0);
	
	}

	private void configurarFecha() {

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		this.Fechainicio = calendar.getTime();
		calendar.add(Calendar.DATE, 2);
		this.fechaFinal = calendar.getTime();
	}

	private List<Atraccion> llenarPaqueteDeAtracciones() {

		List<Atraccion> paqueteDeAtracciones;

		this.atraccionSugerida = new LinkedList<Atraccion>();

		paqueteDeAtracciones = new LinkedList<Atraccion>();

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
		paqueteDeAtracciones.add(mordor);
		paqueteDeAtracciones.add(comarca);
		this.atraccionSugerida.add(mordor);
		this.atraccionSugerida.add(gondor);
		this.atraccionSugerida.add(comarca);

		return paqueteDeAtracciones;

	}


}
