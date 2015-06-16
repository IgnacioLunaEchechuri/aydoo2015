import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class PromocionAbsoluta implements Promocion {

	private float valorPromocion;
	private Date fechaInicio;
	private Date fechaFinal;
	private List<Atraccion> listaDeAtracciones = new LinkedList<Atraccion>();

	public PromocionAbsoluta(Date fechaInicio, Date fechaFinal,
			List<Atraccion> listaDeAtracciones, float valorPromocion) {
		this.fechaInicio = fechaInicio;
		this.fechaFinal = fechaFinal;
		this.valorPromocion = valorPromocion;
		this.listaDeAtracciones = listaDeAtracciones;
	}

	@Override
	public void aplicarPromocion(Usuario usuario, Itinerario listaItinerario,
			boolean estadoPromocionAcumulable) {

		if ((this.encontradoAtraccionesdeLaPromocionEnItinerario(listaItinerario))
				&& estadoPromocionAcumulable)
			usuario.sumarAlPresupuesto(this.valorPromocion);
	}

	@Override
	public boolean estadoFechaDePromocionValida() {
		Date fechaActual = new Date();

		return (fechaActual.after(this.fechaInicio) && fechaActual
				.before(this.fechaFinal));

	}

	public boolean encontradoAtraccionesdeLaPromocionEnItinerario(
			Itinerario listaItinerario) {

		int contadorAtraccionesEncontradas = 0;

		for (Atraccion sugerida : listaItinerario.getItinerario())
			for (Atraccion promocion : this.listaDeAtracciones)
				if (promocion.comparadoAtracciones(sugerida))
					contadorAtraccionesEncontradas++;

		return (contadorAtraccionesEncontradas == this.listaDeAtracciones
				.size()) && (this.estadoFechaDePromocionValida());
	}

}
