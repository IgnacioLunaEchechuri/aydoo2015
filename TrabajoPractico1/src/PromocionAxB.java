import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class PromocionAxB implements Promociones {

	private Atraccion atraccionGratis;
	private Date fechaInicio;
	private Date fechaFinal;
	private List<Atraccion> listaDeAtracciones;

	public void setListaDeAtracciones(List<Atraccion> listaDeAtracciones) {
		this.listaDeAtracciones = listaDeAtracciones;
	}

	public PromocionAxB(Date fechaInicio, Date fechaFinal,
			Atraccion atraccionGratis, List<Atraccion> listaDeAtracciones) {
		this.setFechaInicio(fechaInicio);
		this.setFechaFinal(fechaFinal);
		this.setAtraccionGratis(atraccionGratis);
		this.listaDeAtracciones = listaDeAtracciones;
	}

	public Date getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public List<Atraccion> getListaDeAtracciones() {
		return listaDeAtracciones;
	}

	public void agregarAtraccion(Atraccion atraccion) {
		this.listaDeAtracciones.add(atraccion);
	}

	public Atraccion getAtraccionGratis() {
		return atraccionGratis;
	}

	public void setAtraccionGratis(Atraccion atraccionGratis) {
		this.atraccionGratis = atraccionGratis;
	}

	public float devolverTiempo(Usuario usuario, Atraccion atraccion) {

		float distanciaUsuarioAtraccion = CalcularDistanciaYTiempo
				.calcularDistancia(usuario.getPosicion(),
						atraccion.getPosicion());

		return CalcularDistanciaYTiempo
				.devolverTiempoDeRecorridoMasTiempoAtraccion(
						distanciaUsuarioAtraccion,
						usuario.getVelocidadTranslado(),
						atraccion.getTiempoDelRecorrido());
	}

	

	@Override
	public boolean DevolverPromocion(Usuario usuario,Itinerario listaItinerario) {

		boolean confirmarPromocion=false;
		if ((this.validarPromocion(listaItinerario))&&(usuario.consultaTiempoDisponible(this.devolverTiempo(usuario,
				this.getAtraccionGratis())))) {
			listaItinerario.agregarAtraccion(this.atraccionGratis);
			confirmarPromocion = true;
		} else
			confirmarPromocion = false;

		return confirmarPromocion;
	}

	public boolean ValidarFechaPromocion() {
		Date fechaActual = new Date();
		boolean validar = (fechaActual.after(this.fechaInicio) && fechaActual
				.before(this.fechaFinal));

		return validar;

	}

	public boolean validarPromocion(Itinerario listaItinerario) {
		Boolean validacion = false, validarFecha = this.ValidarFechaPromocion();
		Atraccion atraccionPaquete = null, atraccionSugerida = null;
		if ((listaItinerario.tamanoItinerario() == this.listaDeAtracciones.size()) && (validarFecha)) {

			Iterator<Atraccion> iteradorListaDeAtracciones = this.listaDeAtracciones.iterator();
			Iterator<Atraccion> iteradorItinerario = listaItinerario.getItinerario().iterator();
			
			while (iteradorListaDeAtracciones.hasNext())
				atraccionPaquete = iteradorListaDeAtracciones.next();{

					while (iteradorItinerario.hasNext()) {
						atraccionSugerida = iteradorItinerario.next();
						if ((atraccionSugerida.getPosicion().getLatitud() == atraccionPaquete.getPosicion().getLatitud())
						&& (atraccionSugerida.getPosicion().getLongitud() == atraccionPaquete.getPosicion().getLongitud())) {

					        validacion = true;
			        	} else
					       validacion = false;

			        }
     		}
		}
		return validacion;

	}

}
