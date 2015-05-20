import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class PromocionAxB implements Promocion {

	private Atraccion atraccionGratis;
	private Date fechaInicio;
	private Date fechaFinal;
	private List<Atraccion> listaDeAtracciones;
	private boolean promocionAplicada;
	private int identificadorPromocion;

	public PromocionAxB(Date fechaInicio, Date fechaFinal,
			Atraccion atraccionGratis, List<Atraccion> listaDeAtracciones) {
		this.fechaInicio = fechaInicio;
		this.fechaFinal = fechaFinal;
		this.setAtraccionGratis(atraccionGratis);
		this.listaDeAtracciones = listaDeAtracciones;
		this.promocionAplicada = false;
		this.identificadorPromocion=3;
	}

	@Override
	public int getIdentificadorPromocion() {

		return this.identificadorPromocion;
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
	public boolean validarFechaPromocion() {
		Date fechaActual = new Date();
		boolean validar = (fechaActual.after(this.fechaInicio) && fechaActual
				.before(this.fechaFinal));

		return validar;
	}

	@Override
	public boolean devolverPromocion(Usuario usuario, Itinerario listaItinerario) {

		boolean confirmarPromocion = false;

		if ((this.validarAtraccionesdeLaPromocion(listaItinerario))
				&& (usuario.consultaTiempoDisponible(this.devolverTiempo(
						usuario, this.getAtraccionGratis())))) {
			listaItinerario.agregarAtraccion(this.atraccionGratis);
			confirmarPromocion = true;
		} else
			confirmarPromocion = false;

		return confirmarPromocion;
	}

	public boolean validarAtraccionesdeLaPromocion(Itinerario listaItinerario) {
		Boolean validacion = false, validarFecha = this.validarFechaPromocion();
		Atraccion atraccionPaquete = null, atraccionSugerida = null;
		if ((listaItinerario.tamanoItinerario() == this.listaDeAtracciones
				.size()) && (validarFecha)) {

			Iterator<Atraccion> iteradorListaDeAtracciones = this.listaDeAtracciones
					.iterator();
			Iterator<Atraccion> iteradorItinerario = listaItinerario
					.getItinerario().iterator();

			while (iteradorListaDeAtracciones.hasNext())
				atraccionPaquete = iteradorListaDeAtracciones.next();
			{

				while (iteradorItinerario.hasNext()) {
					atraccionSugerida = iteradorItinerario.next();
					if ((atraccionSugerida.getPosicion().getLatitud() == atraccionPaquete
							.getPosicion().getLatitud())
							&& (atraccionSugerida.getPosicion().getLongitud() == atraccionPaquete
									.getPosicion().getLongitud())) {

						validacion = true;
					} else
						validacion = false;

				}
			}
		}
		return validacion;

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
