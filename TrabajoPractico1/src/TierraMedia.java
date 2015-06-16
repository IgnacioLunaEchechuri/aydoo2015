import java.util.LinkedList;
import java.util.List;

public class TierraMedia {
	private List<Promocion> listaPromociones;
	private List<Atraccion> listaDeAtracciones;
	private Usuario usuario;
	private GeneradorDelItinerario generarItinerario;
	private Itinerario itinerario;
	private Atraccion atraccionInicial;

	private final float distanciaMinimaAtraccion = 200000;

	public TierraMedia() {

		this.listaPromociones = new LinkedList<Promocion>();
		this.listaDeAtracciones = new LinkedList<Atraccion>();
		this.generarItinerario = new GeneradorDelItinerario();
	}

	public void agregarAtraccionInicial(Atraccion atraccionInicial) {
		this.atraccionInicial = atraccionInicial;
	}

	public void agregarAtraccion(Atraccion atraccion) {
		this.listaDeAtracciones.add(atraccion);
	}

	public void agregarPromociones(Promocion promocion) {
		this.listaPromociones.add(promocion);
	}

	public void agregarUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void generarItinerario() {

		this.generarItinerario.generarItinerarioPorPreferencias(usuario,
				listaDeAtracciones);
		this.itinerario = this.generarItinerario.getListaItinerario();
	}

	public List<Promocion> getListaDePromociones() {

		return this.listaPromociones;
	}

	public Itinerario getItinerario() {

		return this.itinerario;

	}

	public void aplicarItinerarioAlUsuario() {
		
		usuario.restarDelPresupuesto(this.itinerario.costoTotalItinerario()
				* usuario.getCantidadDeEntradas());
		usuario.restartiempo(this.itinerario.tiempoTotalItinerario());
		this.ejecutarPromociones();

	}

	public void ejecutarPromociones() {

		if (this.listaPromociones.size() != 0)

			for (Promocion promocion : this.listaPromociones){

				promocion.aplicarPromocion(this.usuario,this.itinerario,this.usuario
										.calcularDistancia(this.atraccionInicial
												.getPosicion()) <= this.distanciaMinimaAtraccion);
			}
	}
}
