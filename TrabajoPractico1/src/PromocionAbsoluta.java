import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class PromocionAbsoluta implements Promocion {

	private float valorPromocion;
	private Date fechaInicio;
	private Date fechaFinal;
	private List<Atraccion> listaDeAtracciones = new LinkedList<Atraccion>();
	private boolean promocionAplicada;
	private int identificadorPromocion;

	public PromocionAbsoluta(Date fechaInicio, Date fechaFinal,
			List<Atraccion> listaDeAtracciones, float valorPromocion) {
		this.fechaInicio = fechaInicio;
		this.fechaFinal = fechaFinal;
		this.valorPromocion = valorPromocion;
		this.listaDeAtracciones = listaDeAtracciones;
		this.promocionAplicada=false;
		this.identificadorPromocion=2;
	}

	@Override
	public int getIdentificadorPromocion() {

		return this.identificadorPromocion;
	}

	@Override
	public boolean devolverPromocion(Usuario usuario, Itinerario listaItinerario) {

		boolean confirmarPromocion = false;
		if (this.validarAtraccionesdeLaPromocion(listaItinerario)) {
			usuario.sumarAlPresupuesto(this.valorPromocion);
			confirmarPromocion = true;
		} else
			confirmarPromocion = false;

		return confirmarPromocion;
	}

	@Override
	public boolean validarFechaPromocion() {
		Date fechaActual = new Date();

		return (fechaActual.after(this.fechaInicio) && fechaActual
				.before(this.fechaFinal));

	}

	public boolean validarAtraccionesdeLaPromocion(Itinerario listaItinerario) {
		Boolean validarFecha = this.validarFechaPromocion();
		Atraccion atraccionPaquete = null, atraccionSugerida = null;
		int contadorAtraccionesEncontradas = 0;

		if ((listaItinerario.tamanoItinerario() == this.listaDeAtracciones
				.size()) && (validarFecha)) {

			Iterator<Atraccion> iteradorListaDeAtracciones = this.listaDeAtracciones
					.iterator();

			while (iteradorListaDeAtracciones.hasNext()) {

				atraccionPaquete = iteradorListaDeAtracciones.next();
				Iterator<Atraccion> iteradorItinerario = listaItinerario
						.getItinerario().iterator();

				while (iteradorItinerario.hasNext()) {
					atraccionSugerida = iteradorItinerario.next();

					if (atraccionPaquete.compararAtracciones(atraccionSugerida))
						contadorAtraccionesEncontradas++;

				}
			}
		}
		return contadorAtraccionesEncontradas == listaItinerario
				.tamanoItinerario();

	}

	@Override
	public boolean getAplicarPromocion() {

		return this.promocionAplicada;
	}

	@Override
	public void setAplicarPromocion(boolean cambiarEstado) {
		this.promocionAplicada = cambiarEstado;

	}

}
