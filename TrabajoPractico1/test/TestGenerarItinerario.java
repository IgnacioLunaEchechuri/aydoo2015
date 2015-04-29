import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class TestGenerarItinerario {
	
	private List<Atraccion> listaDeatraccionesUnaAtraccion;
	private List<Atraccion> listaDeatraccionesDosAtracciones;
	private List<Atraccion> listaDeAtraccionesTresAtracciones;
	private Itinerario listaItinerario;
	Atraccion atraccionExtra;
	Date Fechainicio = new Date();
	Date fechaFinal = new Date();

	@Test
	public void testGenerarItinerarioSinPromos() {
		this.configurarFecha();
		this.llenarPaqueteDeAtracciones();
		PosicionPorCoordenadas posicion = new PosicionPorCoordenadas(10, 10);
		Usuario jose = new Usuario(500, 10, 160, TipoDeAtraccion.aventura,
				posicion);

		GenerarItinerario itinerario = new GenerarItinerario();
		itinerario.generarItinerario(jose, this.listaDeatraccionesDosAtracciones, 2);

		Assert.assertEquals(2, itinerario.getListaItinerario().tamanoItinerario(), 0);
		Assert.assertEquals(310, jose.getPresupuesto(), 0);
		Assert.assertEquals(9.79, jose.getTiempoDisponible(), 2);
		
		itinerario.getListaItinerario().agregarAtraccion(atraccionExtra);
			
		Assert.assertEquals(3, itinerario.getListaItinerario().tamanoItinerario(), 0);
		
		Usuario ignacio = new Usuario(500, 10, 320, TipoDeAtraccion.aventura,
				posicion);

		GenerarItinerario itinerarioTres = new GenerarItinerario();
		itinerarioTres.generarItinerario(ignacio, this.listaDeAtraccionesTresAtracciones, 2);

		Assert.assertEquals(3, itinerarioTres.getListaItinerario().tamanoItinerario(), 0);
		Assert.assertEquals(220, ignacio.getPresupuesto(), 0);
		Assert.assertEquals(139.5, ignacio.getTiempoDisponible(),1);
		
		itinerarioTres.getListaItinerario().agregarAtraccion(atraccionExtra);
			
		Assert.assertEquals(4, itinerarioTres.getListaItinerario().tamanoItinerario(), 0);
		
		
	}

	@Test
	public void testConPromocionAxB() {
		this.configurarFecha();
		this.llenarPaqueteDeAtracciones();
		PosicionPorCoordenadas posicion = new PosicionPorCoordenadas(10, 10);
		Usuario jose = new Usuario(500, 10, 160, TipoDeAtraccion.aventura,
				posicion);

		GenerarItinerario itinerario = new GenerarItinerario();
		
			itinerario.agregarPromociones(this.promocionAXB());

		itinerario.generarItinerario(jose, this.listaDeatraccionesDosAtracciones, 2);
	
		Assert.assertEquals(2, itinerario.getListaItinerario().tamanoItinerario(), 0);
		Assert.assertEquals(310, jose.getPresupuesto(), 0);
		Assert.assertEquals(9.79, jose.getTiempoDisponible(), 2);
		
		itinerario.getListaItinerario().agregarAtraccion(atraccionExtra);
			
		Assert.assertEquals(3, itinerario.getListaItinerario().tamanoItinerario(), 0);
		
		
		Usuario ignacio = new Usuario(500, 10, 320, TipoDeAtraccion.aventura,
				posicion);

		GenerarItinerario itinerarioConAxB = new GenerarItinerario();
		itinerarioConAxB.agregarPromociones(this.promocionAXB());
		itinerarioConAxB.generarItinerario(ignacio, this.listaDeatraccionesDosAtracciones, 2);

		Assert.assertEquals(3, itinerarioConAxB.getListaItinerario().tamanoItinerario(), 0);
		Assert.assertEquals(310, ignacio.getPresupuesto(), 0);
				
		itinerarioConAxB.getListaItinerario().agregarAtraccion(atraccionExtra);
			
		Assert.assertEquals(4, itinerarioConAxB.getListaItinerario().tamanoItinerario(), 0);
		

		Usuario pedro = new Usuario(500, 10, 160, TipoDeAtraccion.aventura,
				posicion);
		
		this.configurarFechaFueraDeRango();
		GenerarItinerario itinerarioPromoFueraDeFecha = new GenerarItinerario();
		
		itinerarioPromoFueraDeFecha.agregarPromociones(this.promocionAXB());
		

		itinerarioPromoFueraDeFecha.generarItinerario(pedro, this.listaDeatraccionesDosAtracciones, 2);

		Assert.assertEquals(2, itinerarioPromoFueraDeFecha.getListaItinerario().tamanoItinerario(), 0);
		Assert.assertEquals(310, pedro.getPresupuesto(), 0);
		Assert.assertEquals(9.79, pedro.getTiempoDisponible(), 2);
	
		itinerarioPromoFueraDeFecha.getListaItinerario().agregarAtraccion(atraccionExtra);
		
		Assert.assertEquals(3, itinerarioPromoFueraDeFecha.getListaItinerario().tamanoItinerario(), 0);
	
		
	}
	

	@Test
	public void testConPromocionAbsoluto() {
		this.configurarFecha();
		this.llenarPaqueteDeAtracciones();
		PosicionPorCoordenadas posicion = new PosicionPorCoordenadas(10, 10);
		Usuario jose = new Usuario(500, 10, 160, TipoDeAtraccion.aventura,
				posicion);

		GenerarItinerario itinerario = new GenerarItinerario();
		
			itinerario.agregarPromociones(this.promocionAbsoluta());

		itinerario.generarItinerario(jose, this.listaDeatraccionesDosAtracciones, 2);
	
		Assert.assertEquals(2, itinerario.getListaItinerario().tamanoItinerario(), 0);
		Assert.assertEquals(330, jose.getPresupuesto(), 0);
		Assert.assertEquals(9.79, jose.getTiempoDisponible(), 2);
		
		itinerario.getListaItinerario().agregarAtraccion(atraccionExtra);
			
		Assert.assertEquals(3, itinerario.getListaItinerario().tamanoItinerario(), 0);
		
		
		Usuario ignacio = new Usuario(500, 10, 320, TipoDeAtraccion.aventura,
				posicion);
		

		GenerarItinerario itinerarioSinPorcentaje = new GenerarItinerario();
		this.promocionAbsoluta().setValorPromocion(100);
		itinerarioSinPorcentaje.agregarPromociones(this.promocionAbsoluta());
		itinerarioSinPorcentaje.generarItinerario(ignacio, this.listaDeAtraccionesTresAtracciones, 2);

	    Assert.assertEquals(3, itinerarioSinPorcentaje.getListaItinerario().tamanoItinerario(), 0);
		Assert.assertEquals(220, ignacio.getPresupuesto(), 0);
				
		itinerarioSinPorcentaje.getListaItinerario().agregarAtraccion(atraccionExtra);
			
		Assert.assertEquals(4, itinerarioSinPorcentaje.getListaItinerario().tamanoItinerario(), 0);
		
		
		Usuario pedro = new Usuario(500, 10, 160, TipoDeAtraccion.aventura,
				posicion);
		
		this.configurarFechaFueraDeRango();
		GenerarItinerario itinerarioPromoFueraDeFecha = new GenerarItinerario();
		
		itinerarioPromoFueraDeFecha.agregarPromociones(this.promocionAbsoluta());
		

		itinerarioPromoFueraDeFecha.generarItinerario(pedro, this.listaDeatraccionesDosAtracciones, 2);

		Assert.assertEquals(2, itinerarioPromoFueraDeFecha.getListaItinerario().tamanoItinerario(), 0);
		Assert.assertEquals(310, pedro.getPresupuesto(), 0);
		Assert.assertEquals(9.79, pedro.getTiempoDisponible(), 2);
	
		itinerarioPromoFueraDeFecha.getListaItinerario().agregarAtraccion(atraccionExtra);
		
		Assert.assertEquals(3, itinerarioPromoFueraDeFecha.getListaItinerario().tamanoItinerario(), 0);
	
	
	}
	
	
	@Test
	public void testConPromocionPorcentual() {
		this.configurarFecha();
		this.llenarPaqueteDeAtracciones();
		PosicionPorCoordenadas posicion = new PosicionPorCoordenadas(10, 10);
		Usuario jose = new Usuario(500, 10, 160, TipoDeAtraccion.aventura,
				posicion);

		GenerarItinerario itinerario = new GenerarItinerario();
		
			itinerario.agregarPromociones(this.promocionPorcentual());

		itinerario.generarItinerario(jose, this.listaDeatraccionesDosAtracciones, 2);
	
		Assert.assertEquals(2, itinerario.getListaItinerario().tamanoItinerario(), 0);
		Assert.assertEquals(329, jose.getPresupuesto(), 0);
		Assert.assertEquals(9.79, jose.getTiempoDisponible(), 2);
		
		itinerario.getListaItinerario().agregarAtraccion(atraccionExtra);
			
		Assert.assertEquals(3, itinerario.getListaItinerario().tamanoItinerario(), 0);
		
		
		Usuario ignacio = new Usuario(500, 10, 320, TipoDeAtraccion.aventura,
				posicion);
		
		
		GenerarItinerario itinerarioSinPorcentaje = new GenerarItinerario();
		this.promocionAbsoluta().setValorPromocion(100);
		itinerarioSinPorcentaje.agregarPromociones(this.promocionPorcentual());
		itinerarioSinPorcentaje.generarItinerario(ignacio, this.listaDeAtraccionesTresAtracciones, 2);

	    Assert.assertEquals(3, itinerarioSinPorcentaje.getListaItinerario().tamanoItinerario(), 0);
		Assert.assertEquals(220, ignacio.getPresupuesto(), 0);
				
		itinerarioSinPorcentaje.getListaItinerario().agregarAtraccion(atraccionExtra);
			
		Assert.assertEquals(4, itinerarioSinPorcentaje.getListaItinerario().tamanoItinerario(), 0);
		
		
		
		Usuario pedro = new Usuario(500, 10, 160, TipoDeAtraccion.aventura,
				posicion);
				
		this.configurarFechaFueraDeRango();
		GenerarItinerario itinerarioPromoFueraDeFecha = new GenerarItinerario();
		
		itinerarioPromoFueraDeFecha.agregarPromociones(this.promocionPorcentual());
		

		itinerarioPromoFueraDeFecha.generarItinerario(pedro, this.listaDeatraccionesDosAtracciones, 2);

		Assert.assertEquals(2, itinerarioPromoFueraDeFecha.getListaItinerario().tamanoItinerario(), 0);
		Assert.assertEquals(310, pedro.getPresupuesto(), 0);
		Assert.assertEquals(9.79, pedro.getTiempoDisponible(), 2);
	
		itinerarioPromoFueraDeFecha.getListaItinerario().agregarAtraccion(atraccionExtra);
		
		Assert.assertEquals(3, itinerarioPromoFueraDeFecha.getListaItinerario().tamanoItinerario(), 0);
		
	}
	
	@Test
	public void testConTodasPromociones() {
		this.configurarFecha();
		this.llenarPaqueteDeAtracciones();
		PosicionPorCoordenadas posicion = new PosicionPorCoordenadas(10, 10);
		
		Usuario jose = new Usuario(500, 10, 360, TipoDeAtraccion.aventura,
				posicion);

		GenerarItinerario itinerario=this.itinerario(this.listaDeatraccionesDosAtracciones);		

		itinerario.generarItinerario(jose, this.listaDeatraccionesDosAtracciones, 2);
	
		Assert.assertEquals(3, itinerario.getListaItinerario().tamanoItinerario(), 0);
		Assert.assertEquals(310, jose.getPresupuesto(), 0);
				
		itinerario.getListaItinerario().agregarAtraccion(atraccionExtra);
			
		Assert.assertEquals(4, itinerario.getListaItinerario().tamanoItinerario(), 0);
		
		}
	
	
	private GenerarItinerario itinerario(List <Atraccion> listaAtracciones){
		GenerarItinerario itinerario = new GenerarItinerario();
		PromocionAxB promocionAxB = new PromocionAxB(this.Fechainicio,
		this.fechaFinal,this.getAtraccionGratis(),listaAtracciones );
		
		PromocionAbsoluta promocionAbsoluta = new PromocionAbsoluta(this.Fechainicio, this.fechaFinal,
				this.listaDeAtraccionesTresAtracciones, 20);
		
		PromocionPorcentual promocionPorcentual = new PromocionPorcentual(
				this.Fechainicio, this.fechaFinal,10,this.listaDeatraccionesUnaAtraccion);
		itinerario.agregarPromociones(promocionAxB);
		itinerario.agregarPromociones(promocionAbsoluta);
		itinerario.agregarPromociones(promocionPorcentual);
		return itinerario;
	}	
	
	
	private PromocionAxB promocionAXB(){


		PromocionAxB promocionAxB = new PromocionAxB(this.Fechainicio,
		this.fechaFinal,this.getAtraccionGratis(), this.listaDeatraccionesDosAtracciones);

		return promocionAxB;
	}
	
	private PromocionAbsoluta promocionAbsoluta(){


		PromocionAbsoluta promocionAbsoluta = new PromocionAbsoluta(this.Fechainicio, this.fechaFinal,
				this.listaDeatraccionesDosAtracciones, 20);

		return promocionAbsoluta;
	}
	
	private PromocionPorcentual promocionPorcentual(){


		PromocionPorcentual promocionPorcentual = new PromocionPorcentual(
				this.Fechainicio, this.fechaFinal,10,this.listaDeatraccionesDosAtracciones);

		return promocionPorcentual;
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
	
	

	private Atraccion getAtraccionGratis() {
		PosicionPorCoordenadas posicionMordor = new PosicionPorCoordenadas(10,
				40);
		Atraccion atraccion = new Atraccion(posicionMordor, 100, 50, 120,
				TipoDeAtraccion.aventura);
		return atraccion;
	}

	private void llenarPaqueteDeAtracciones() {		

		this.listaDeatraccionesDosAtracciones = new LinkedList<Atraccion>();
		this.listaDeAtraccionesTresAtracciones = new LinkedList<Atraccion>();

		this.listaDeatraccionesUnaAtraccion=new LinkedList<Atraccion>();
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
		
		this.listaDeatraccionesUnaAtraccion.add(gondor);
		
		this.listaDeatraccionesDosAtracciones.add(mordor);		
		this.listaDeatraccionesDosAtracciones.add(comarca);
		
		this.listaDeAtraccionesTresAtracciones.add(comarca);
		this.listaDeAtraccionesTresAtracciones.add(gondor);
		this.listaDeAtraccionesTresAtracciones.add(mordor);
		this.atraccionExtra=gondor;

	

	}
}
