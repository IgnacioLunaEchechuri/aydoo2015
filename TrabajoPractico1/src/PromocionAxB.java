import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class PromocionAxB implements Promociones {

	private Atraccion atraccionGratis;
	private Date fechaInicio;
	private Date fechaFinal;
	private List<Atraccion> listaDeAtracciones, listaModificada;

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

	public List<Atraccion> devolverListaModificada() {

		return listaModificada;

	}

	public void setListaModificada(List<Atraccion> listaModificada) {

		this.listaModificada = listaModificada;

	}

	@Override
	public boolean DevolverPromocion(Usuario usuario,
			List<Atraccion> sugerenciaDeAtracciones) {

		boolean confirmarPromocion=this.validarPromocion(sugerenciaDeAtracciones);
		if ((confirmarPromocion)&&(usuario.consultaTiempoDisponible(this.devolverTiempo(usuario,
				this.getAtraccionGratis())))) {
			sugerenciaDeAtracciones.add(this.atraccionGratis);
			this.setListaModificada(sugerenciaDeAtracciones);
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

	public boolean validarPromocion(List<Atraccion> sugerenciaDeAtracciones) {
		Boolean validacion = false, validarFecha = this.ValidarFechaPromocion();
		if ((sugerenciaDeAtracciones.size() == this.listaDeAtracciones.size())
				&& (validarFecha)) {

			Iterator<Atraccion> iteradorPaqueteDeAtracciones = this.listaDeAtracciones
					.iterator();
			Iterator<Atraccion> iteradorSugerenciaDeAtracciones = this.listaDeAtracciones
					.iterator();
			Atraccion atraccionPaquete = null, atraccionSugerida = null;

			while (iteradorPaqueteDeAtracciones.hasNext())
				atraccionPaquete = iteradorPaqueteDeAtracciones.next();

			while (iteradorSugerenciaDeAtracciones.hasNext()) {
				atraccionSugerida = iteradorSugerenciaDeAtracciones.next();
				if ((atraccionSugerida.getPosicion().getLatitud() == atraccionPaquete
						.getPosicion().getLatitud())
						&& (atraccionSugerida.getPosicion().getLongitud() == atraccionPaquete
								.getPosicion().getLongitud())) {

					validacion = true;
				} else
					validacion = false;

			}
		}
		return validacion;

	}

}
