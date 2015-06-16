import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class PromocionPorcentual implements Promocion {

	private float porcentajePromocion;

	private Date fechaInicio;
	private Date fechaFinal;
	private List<Atraccion> listaDeAtracciones;

	public PromocionPorcentual(Date fechaInicio, Date fechaFinal,
			float porcentajePromocion, List<Atraccion> listaAtracciones) {
		this.listaDeAtracciones = new LinkedList<Atraccion>();
		this.fechaInicio = fechaInicio;
		this.fechaFinal = fechaFinal;
		this.porcentajePromocion = porcentajePromocion;
		this.listaDeAtracciones = listaAtracciones;

	}

	public float montoDelPorcentajeADevolver(float costo) {
		return (costo * this.porcentajePromocion) / 100;
	}

	@Override
	public boolean estadoFechaDePromocionValida() {
		Date fechaActual = new Date();
		boolean validar = (fechaActual.after(this.fechaInicio) && fechaActual
				.before(this.fechaFinal));

		return validar;
	}

	@Override
	public void aplicarPromocion(Usuario usuario, Itinerario listaItinerario,boolean  estadoPromocionAcumulable) {

		if (this.encontradoAtraccionesdeLaPromocionEnItinerario(listaItinerario) && estadoPromocionAcumulable)
			usuario.sumarAlPresupuesto((listaItinerario.costoTotalItinerario() * this.porcentajePromocion) / 100);

	}

public boolean encontradoAtraccionesdeLaPromocionEnItinerario(Itinerario listaItinerario) {
		
		int contadorAtraccionesEncontradas = 0;
		
		for (Atraccion sugerida: listaItinerario.getItinerario())
				for (Atraccion promocion:this.listaDeAtracciones)
					if(promocion.comparadoAtracciones(sugerida))
						contadorAtraccionesEncontradas++;
		
		return (contadorAtraccionesEncontradas==this.listaDeAtracciones.size())&&(this.estadoFechaDePromocionValida());
}

}
