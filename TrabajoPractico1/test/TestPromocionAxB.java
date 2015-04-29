import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class TestPromocionAxB {

	private List<Atraccion> atraccionSugerida;
	private List<Atraccion> atraccionSugeridas;
	private Atraccion atraccion;
	Date Fechainicio = new Date();
	Date fechaFinal = new Date();

	@Test
	public void testValidarPromocion() {
		this.configurarFecha();

		PromocionAxB promocionAxB = new PromocionAxB(this.Fechainicio,
				this.fechaFinal,this.getAtraccionGratis(), this.llenarPaqueteDeAtracciones());
		
		Assert.assertTrue(promocionAxB.validarPromocion(this.llenarPaqueteDeAtracciones()));

		promocionAxB.setListaDeAtracciones(this.atraccionSugerida);
		Assert.assertFalse(promocionAxB.validarPromocion(this.llenarPaqueteDeAtracciones()));
		
		promocionAxB.setListaDeAtracciones(this.atraccionSugeridas);
		Assert.assertFalse(promocionAxB.validarPromocion(this.llenarPaqueteDeAtracciones()));
		
		this.configurarFechaFueraDeRango();
		
		PromocionAbsoluta promocionAxBFueraDeFecha = new PromocionAbsoluta(this.Fechainicio, this.fechaFinal,
				this.llenarPaqueteDeAtracciones(), 20);
		
		Assert.assertFalse(promocionAxBFueraDeFecha.validarPromocion(this.llenarPaqueteDeAtracciones()));
		
		Assert.assertFalse(promocionAxBFueraDeFecha.validarPromocion(this.atraccionSugerida));
	
	
	}
	
	@Test
	public void testAplicarPromocion() {
		this.configurarFecha();
		
		PosicionPorCoordenadas posicion = new PosicionPorCoordenadas(10, 10);
		Usuario jose = new Usuario(1000, 10, 160, TipoDeAtraccion.paisaje,posicion);
		
		PromocionAxB promocionAxB = new PromocionAxB(this.Fechainicio,
				this.fechaFinal,this.getAtraccionGratis(), this.llenarPaqueteDeAtracciones());
		
	Assert.assertEquals(this.llenarPaqueteDeAtracciones().size(), 2,0);	
	
	Assert.assertTrue(promocionAxB.DevolverPromocion(jose, this.llenarPaqueteDeAtracciones()));

	Assert.assertEquals(promocionAxB.devolverListaModificada().size(), 3,0);
	
	promocionAxB.agregarAtraccion(atraccion);
	
	Assert.assertFalse(promocionAxB.DevolverPromocion(jose, this.llenarPaqueteDeAtracciones()));
	
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

	private Atraccion getAtraccionGratis() {
		PosicionPorCoordenadas posicionMordor = new PosicionPorCoordenadas(10,
				40);
		Atraccion atraccion = new Atraccion(posicionMordor, 100, 50, 120,
				TipoDeAtraccion.aventura);
		return atraccion;
	}

	private List<Atraccion> llenarPaqueteDeAtracciones() {

		List<Atraccion> paqueteDeAtracciones;

		this.atraccionSugerida = new LinkedList<Atraccion>();
		this.atraccionSugeridas = new LinkedList<Atraccion>();

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
		
		this.atraccion=comarca;
		this.atraccionSugerida.add(mordor);
		this.atraccionSugerida.add(gondor);
		this.atraccionSugerida.add(comarca);
		this.atraccionSugeridas.add(mordor);

		return paqueteDeAtracciones;

	}

}