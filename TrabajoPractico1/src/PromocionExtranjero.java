import java.util.Date;
import java.util.List;

public class PromocionExtranjero implements Promocion {
	private Date fechaInicio;
	private Date fechaFinal;
	
	public PromocionExtranjero(Date fechaInicio, Date fechaFinal,
			List<Atraccion> listaDeAtracciones) {
		this.fechaInicio = fechaInicio;
		this.fechaFinal = fechaFinal;
		
	}


	@Override
	public boolean estadoFechaDePromocionValida() {
		Date fechaActual = new Date();
		boolean validar = (fechaActual.after(this.fechaInicio) && fechaActual
				.before(this.fechaFinal));

		return validar;
	}

	@Override
	public void aplicarPromocion(Usuario usuario, Itinerario listaItinerario,boolean estadoPromocionAcumulable) {

		if ((this.estadoFechaDePromocionValida()) && !estadoPromocionAcumulable)
			usuario.sumarAlPresupuesto(listaItinerario.costoTotalItinerario()*usuario.getCantidadDeEntradas() / 2);

	}

}
