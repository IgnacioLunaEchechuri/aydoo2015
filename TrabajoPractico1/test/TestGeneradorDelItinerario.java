import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class TestGeneradorDelItinerario {

	private List<Atraccion> listaDeatraccionesUnaAtraccion;
	private List<Atraccion> listaDeatraccionesDosAtracciones;
	private List<Atraccion> listaDeAtraccionesTresAtracciones;
	private Itinerario listaItinerario;
	Atraccion atraccionExtra;
	Date Fechainicio = new Date();
	Date fechaFinal = new Date();

	@Test
	public void GenerarItineratrioValido() {

		this.llenarPaqueteDeAtracciones();

		Usuario jose = new Usuario(500, 10, 160, TipoDeAtraccion.aventura,
				this.getPosicionUsuarioMasDoscientosKilometros(), 4);

		GeneradorDelItinerario generadorItinerario = new GeneradorDelItinerario();
		generadorItinerario.generarItinerarioPorPreferencias(jose,
				listaDeAtraccionesTresAtracciones);
		Assert.assertTrue(generadorItinerario.getEstadoGeneradorItinerario());
	}

	@Test
	public void GenerarItineratrioInvalidoPorTiempoNoDisponible() {

		this.llenarPaqueteDeAtracciones();

		Usuario jose = new Usuario(500, 10, 0, TipoDeAtraccion.aventura,
				this.getPosicionUsuarioMasDoscientosKilometros(), 4);

		GeneradorDelItinerario generadorItinerario = new GeneradorDelItinerario();
		generadorItinerario.generarItinerarioPorPreferencias(jose,
				listaDeAtraccionesTresAtracciones);
		Assert.assertFalse(generadorItinerario.getEstadoGeneradorItinerario());
	}

	@Test
	public void GenerarItineratrioInvalidoPorNoDisponibilidadDePresupuesto() {

		this.llenarPaqueteDeAtracciones();

		Usuario jose = new Usuario(0, 10, 100, TipoDeAtraccion.aventura,
				this.getPosicionUsuarioMasDoscientosKilometros(), 4);

		GeneradorDelItinerario generadorItinerario = new GeneradorDelItinerario();
		generadorItinerario.generarItinerarioPorPreferencias(jose,
				listaDeAtraccionesTresAtracciones);
		Assert.assertFalse(generadorItinerario.getEstadoGeneradorItinerario());
	}

	
	private PosicionPorCoordenadas getPosicionUsuarioMasDoscientosKilometros() {
		PosicionPorCoordenadas posicionUsuario = new PosicionPorCoordenadas(
				205000, 200000);
		return posicionUsuario;
	}

	private void llenarPaqueteDeAtracciones() {

		this.listaDeatraccionesDosAtracciones = new LinkedList<Atraccion>();
		this.listaDeAtraccionesTresAtracciones = new LinkedList<Atraccion>();

		this.listaDeatraccionesUnaAtraccion = new LinkedList<Atraccion>();
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
		this.atraccionExtra = gondor;

	}
}
