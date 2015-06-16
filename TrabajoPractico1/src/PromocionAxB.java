import java.util.Date;
import java.util.List;

public class PromocionAxB implements Promocion {

	private Atraccion atraccionGratis;
	private Date fechaInicio;
	private Date fechaFinal;
	private List<Atraccion> listaDeAtracciones;

	public PromocionAxB(Date fechaInicio, Date fechaFinal,
			Atraccion atraccionGratis, List<Atraccion> listaDeAtracciones) {
		this.fechaInicio = fechaInicio;
		this.fechaFinal = fechaFinal;
		this.setAtraccionGratis(atraccionGratis);
		this.listaDeAtracciones = listaDeAtracciones;
	}

	public Atraccion getAtraccionGratis() {
		return atraccionGratis;
	}

	public void setAtraccionGratis(Atraccion atraccionGratis) {
		this.atraccionGratis = atraccionGratis;
	}

	public float devolverTiempoEntreUsuarioYAtraccion(Usuario usuario,
			Atraccion atraccion) {

		return usuario.devolverTiempoDeRecorridoMasTiempoAtraccion(
				atraccion.getPosicion(), atraccion.getTiempoDelRecorrido());
	}

	@Override
	public boolean estadoFechaDePromocionValida() {
		Date fechaActual = new Date();
		return (fechaActual.after(this.fechaInicio) && fechaActual
				.before(this.fechaFinal));

	}

	@Override
	public void aplicarPromocion(Usuario usuario, Itinerario listaItinerario,boolean estadoPromocionAcumulable) {
		
	if ((estadoPromocionAcumulable)&&(this.encontradoAtraccionesdeLaPromocionEnItinerario(listaItinerario))
				&& (usuario.estadoTiempoDisponible(this.devolverTiempoEntreUsuarioYAtraccion(usuario,this.getAtraccionGratis()))))
			listaItinerario.agregarAtraccion(this.atraccionGratis);

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
