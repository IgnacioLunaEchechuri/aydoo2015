import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class GenerarItinerario {

	private Itinerario listaItinerario;
	private List<Promociones> listaPromociones;
	
	

	public GenerarItinerario() {
		listaItinerario = new Itinerario();
		listaPromociones = new LinkedList<Promociones>();
		//this.listaPromociones=null;
	}

	public void agregarPromociones(Promociones promocion) {
		this.listaPromociones.add(promocion);
	}

	public void ejecutarPromociones(Usuario usuario,
			Itinerario listaItinerario) {
		Promociones promocion;
		Iterator<Promociones> iteradorPromociones = this.listaPromociones
				.iterator();

		while (iteradorPromociones.hasNext()) {
			promocion = iteradorPromociones.next();
			promocion.DevolverPromocion(usuario, listaItinerario);
		}
	}

	
	private float devolverTiempo(Usuario usuario, Atraccion atraccion) {

		float distanciaUsuarioAtraccion = CalcularDistanciaYTiempo
				.calcularDistancia(usuario.getPosicion(),
						atraccion.getPosicion());

		return CalcularDistanciaYTiempo
				.devolverTiempoDeRecorridoMasTiempoAtraccion(
						distanciaUsuarioAtraccion,
						usuario.getVelocidadTranslado(),
						atraccion.getTiempoDelRecorrido());
	}

	public void generarItinerario(Usuario usuario, List<Atraccion> listaDeAtracciones, int cantidadVisitantes) {
		Atraccion atraccion;
		
		Iterator<Atraccion> iteradorListaDeAtracciones = listaDeAtracciones.iterator();
	
		float tiempoTotalRecorridoMasAtraccion;

		while (iteradorListaDeAtracciones.hasNext()) {
			atraccion = iteradorListaDeAtracciones.next();

			tiempoTotalRecorridoMasAtraccion = this.devolverTiempo(usuario,
					atraccion);

			if ((usuario
					.consultaTiempoDisponible(tiempoTotalRecorridoMasAtraccion) && (usuario
					.consultaPresupuestoDisponible(atraccion.getPrecio())))
					&& (atraccion.comprobarCapacidad(cantidadVisitantes))
					&& (atraccion.comprobarTipoAtraccionUsuario(usuario))) {

				this.listaItinerario.agregarAtraccion(atraccion);
				usuario.setPosicion(atraccion.getPosicion());
				atraccion.descontarCapacidad(cantidadVisitantes);
				usuario.restarDelPresupuesto(atraccion.getPrecio());
				usuario.restartiempo(tiempoTotalRecorridoMasAtraccion);
				
			}
		}
		if (this.listaPromociones.size()!=0)
		this.ejecutarPromociones(usuario, this.listaItinerario);
	}

	public Itinerario getListaItinerario() {
		return listaItinerario;
	}

}
