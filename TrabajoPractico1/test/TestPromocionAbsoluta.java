import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class TestPromocionAbsoluta {

	private List<Atraccion> atraccionSugerida;
	Date Fechainicio = new Date();
	Date fechaFinal = new Date();

	@Test
	public void testValidarPromocion() {
		
		this.configurarFecha();

		PromocionAbsoluta promocionAbsoluta = new PromocionAbsoluta(this.Fechainicio, this.fechaFinal,
				this.llenarPaqueteDeAtracciones(), 20);
		Assert.assertTrue(promocionAbsoluta.validarPromocion(this.llenarPaqueteDeAtracciones()));

		Assert.assertFalse(promocionAbsoluta.validarPromocion(this.atraccionSugerida));
		
		this.configurarFechaFueraDeRango();
		
		PromocionAbsoluta promocionAbsolutaFueraDeFecha = new PromocionAbsoluta(this.Fechainicio, this.fechaFinal,
				this.llenarPaqueteDeAtracciones(), 20);
		
		Assert.assertFalse(promocionAbsolutaFueraDeFecha.validarPromocion(this.llenarPaqueteDeAtracciones()));
		
		Assert.assertFalse(promocionAbsolutaFueraDeFecha.validarPromocion(this.atraccionSugerida));
	}

	@Test
	public void testDevolverDinero() {
		
		this.configurarFecha();
		
		PosicionPorCoordenadas posicion = new PosicionPorCoordenadas(10, 10);
		Usuario jose = new Usuario(1000, 10, 160, TipoDeAtraccion.paisaje,posicion);
				
		PromocionAbsoluta promocionAbsoluta = new PromocionAbsoluta(
				this.Fechainicio, this.fechaFinal,
				this.llenarPaqueteDeAtracciones(), 20);
		
		Assert.assertEquals(jose.getPresupuesto(),1000,0);
		
		promocionAbsoluta.DevolverPromocion(jose, this.llenarPaqueteDeAtracciones());

		Assert.assertEquals(jose.getPresupuesto(), 1020, 0);
		
		Assert.assertTrue(promocionAbsoluta.DevolverPromocion(jose, this.llenarPaqueteDeAtracciones()));
		
		Assert.assertEquals(jose.getPresupuesto(), 1040, 0);
	
	}

	private void configurarFecha() {

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		this.Fechainicio = calendar.getTime();
		calendar.add(Calendar.DATE, 2);
		this.fechaFinal = calendar.getTime();
	}

	private void configurarFechaFueraDeRango() {

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		this.Fechainicio = calendar.getTime();
		calendar.add(Calendar.DATE, -2);
		this.fechaFinal = calendar.getTime();
	}

	
	private List<Atraccion> llenarPaqueteDeAtracciones() {

		List<Atraccion> paqueteDeAtracciones;

		this.atraccionSugerida = new LinkedList<Atraccion>();

		paqueteDeAtracciones = new LinkedList<Atraccion>();

		PosicionPorCoordenadas posicionMordor = new PosicionPorCoordenadas(10,10);

		Atraccion mordor = new Atraccion(posicionMordor, 100, 50, 120,TipoDeAtraccion.aventura);

		PosicionPorCoordenadas posicionComarca = new PosicionPorCoordenadas(50,10);

		Atraccion comarca = new Atraccion(posicionComarca, 90, 20, 30,TipoDeAtraccion.aventura);

		PosicionPorCoordenadas posicionGondor = new PosicionPorCoordenadas(50,90);

		Atraccion gondor = new Atraccion(posicionGondor, 90, 20, 30,TipoDeAtraccion.aventura);
		
		paqueteDeAtracciones.add(mordor);
		paqueteDeAtracciones.add(comarca);
		
		this.atraccionSugerida.add(mordor);
		this.atraccionSugerida.add(gondor);
		this.atraccionSugerida.add(comarca);

		return paqueteDeAtracciones;

	}

}
