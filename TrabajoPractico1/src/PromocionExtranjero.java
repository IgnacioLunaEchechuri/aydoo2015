import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class PromocionExtranjero implements Promocion {
	private Date fechaInicio;
	private Date fechaFinal;
	private final float distanciaMinimaAtraccion = 200000;
	private boolean promocionAplicada;
	private List<Atraccion> listaDeAtracciones;
	private float distanciaAlParque;
	private int identidicadorPromicion;

	public PromocionExtranjero(Date fechaInicio, Date fechaFinal,
			List<Atraccion> listaDeAtracciones) {
		this.fechaInicio = fechaInicio;
		this.fechaFinal = fechaFinal;
		this.promocionAplicada = false;
		this.listaDeAtracciones = listaDeAtracciones;
		this.identidicadorPromicion=4;

	}
	
	@Override
	public int getIdentificadorPromocion() {

		return this.identidicadorPromicion;
	}

	
	public float calcularDistanciaUsuarioAlParque(PosicionPorCoordenadas posicionUsuario) {

		Iterator<Atraccion> iteradorListaDeAtracciones = this.listaDeAtracciones
				.iterator();

		float distanciaMedidaAnterior = 0;
		while (iteradorListaDeAtracciones.hasNext()) {
			Atraccion atraccion = iteradorListaDeAtracciones.next();
			float distanciaMedidaActual = CalcularDistanciaYTiempo
					.calcularDistancia(posicionUsuario, atraccion.getPosicion());
			if (distanciaMedidaAnterior < distanciaMedidaActual)
				distanciaMedidaAnterior = distanciaMedidaActual;
		}
		this.distanciaAlParque = distanciaMedidaAnterior;
		return this.distanciaAlParque;
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
		if ((this.validarFechaPromocion())
				&& (this.calcularDistanciaUsuarioAlParque(usuario.getPosicion()) > this.distanciaMinimaAtraccion)) {
			usuario.sumarAlPresupuesto(listaItinerario.costoTotalItinerario() / 2);
			confirmarPromocion = true;
		} else
			confirmarPromocion = false;

		return confirmarPromocion;
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
