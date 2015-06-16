import java.util.Iterator;
import java.util.List;

public class GeneradorDelItinerario {

	private Itinerario listaItinerario;
	private boolean estadoGeneradorItinerario;
	private Usuario usuarioCopia;

	public GeneradorDelItinerario() {
		listaItinerario = new Itinerario();

	}

	public Itinerario getListaItinerario() {
		return this.listaItinerario;

	}

	private void generarCopiaDeUsuario(Usuario usuario) {

		usuarioCopia = new Usuario(usuario.getPresupuesto(),
				usuario.getVelocidadTranslado(), usuario.getTiempoDisponible(),
				usuario.getAtracccionPreferida(), usuario.getPosicion(),
				usuario.getCantidadDeEntradas());

	}

	public boolean estadoGeneradorItinerario() {
		return estadoGeneradorItinerario;
	}
	
	public void generarItinerarioPorPreferencias(Usuario usuario,
			List<Atraccion> listaDeAtracciones) {
		Atraccion atraccion;
		this.generarCopiaDeUsuario(usuario);
		Iterator<Atraccion> iteradorListaDeAtracciones = listaDeAtracciones
				.iterator();
		this.estadoGeneradorItinerario = false;
		float tiempoDeDesplazamientoHaciaLaAtraccion;

		while (iteradorListaDeAtracciones.hasNext()) {

			atraccion = iteradorListaDeAtracciones.next();

			tiempoDeDesplazamientoHaciaLaAtraccion = usuario.devolverTiempoDeRecorridoMasTiempoAtraccion(atraccion.getPosicion(), 0);

			if ((usuarioCopia
					.estadoTiempoDisponible(tiempoDeDesplazamientoHaciaLaAtraccion) && (usuarioCopia
					.estadoPresupuestoDisponible(atraccion.getPrecio())))
					&& (atraccion.estadoCapacidad(usuarioCopia.getCantidadDeEntradas()))
					&& (atraccion.comprobadoTipoAtraccionPreferidaPorUsuario(usuarioCopia))) {

				this.estadoGeneradorItinerario = true;
				this.listaItinerario.agregarAtraccion(atraccion);
				usuarioCopia.setPosicion(atraccion.getPosicion());
				atraccion.descontarCapacidad(usuarioCopia.getCantidadDeEntradas());
				usuarioCopia.restarDelPresupuesto(atraccion.getPrecio());
				usuarioCopia.restartiempo(tiempoDeDesplazamientoHaciaLaAtraccion);

			}
		}

	}

	

}
