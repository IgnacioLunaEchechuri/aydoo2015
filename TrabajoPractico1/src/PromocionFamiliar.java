import java.util.Date;
import java.util.Iterator;

public class PromocionFamiliar implements Promocion {
	private Date fechaInicio;
	private Date fechaFinal;
	private final float cantidadMinimaDeEntradas = 4;

	public PromocionFamiliar(Date fechaInicio, Date fechaFinal) {
		this.fechaInicio = fechaInicio;
		this.fechaFinal = fechaFinal;

	}

	@Override
	public boolean estadoFechaDePromocionValida() {
		Date fechaActual = new Date();

		return (fechaActual.after(this.fechaInicio) && fechaActual
				.before(this.fechaFinal));
	}

	@Override
	public void aplicarPromocion(Usuario usuario, Itinerario listaItinerario,boolean  estadoPromocionAcumulable) {

		if ((estadoPromocionAcumulable)&&(this.estadoFechaDePromocionValida())
				&& (usuario.getCantidadDeEntradas() > this.cantidadMinimaDeEntradas - 1))
			usuario.sumarAlPresupuesto(calcularValorPromocion(listaItinerario,
					usuario.getCantidadDeEntradas()));

	}

	private float calcularValorPromocion(Itinerario listaItinerario,
			int cantidadDeEntradas) {
		float valorADevolver = 0;
		Atraccion atraccionSugerida = null;
		cantidadDeEntradas -= this.cantidadMinimaDeEntradas;
		Iterator<Atraccion> iteradorItinerario = listaItinerario
				.getItinerario().iterator();

		while (iteradorItinerario.hasNext()) {
			atraccionSugerida = iteradorItinerario.next();
			if (cantidadDeEntradas == 0)
				valorADevolver += atraccionSugerida.getPrecio() * 0.1*this.cantidadMinimaDeEntradas;
			else
				valorADevolver += atraccionSugerida.getPrecio() * 0.1*this.cantidadMinimaDeEntradas
						+ atraccionSugerida.getPrecio() * 0.3
						* cantidadDeEntradas;

		}
		return valorADevolver;

	}

}
