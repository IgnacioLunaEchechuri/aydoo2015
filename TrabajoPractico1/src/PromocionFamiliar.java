import java.util.Date;
import java.util.Iterator;

public class PromocionFamiliar implements Promocion {
	private Date fechaInicio;
	private Date fechaFinal;
	private final float cantidadMinimaDeEntradas = 4;
	private boolean promocionAplicada;
	private int identidicadorPromicion;

	public PromocionFamiliar(Date fechaInicio, Date fechaFinal) {
		this.fechaInicio = fechaInicio;
		this.fechaFinal = fechaFinal;
		this.promocionAplicada = false;
		this.identidicadorPromicion = 1;

	}

	@Override
	public int getIdentificadorPromocion() {

		return this.identidicadorPromicion;
	}

	@Override
	public boolean validarFechaPromocion() {
		Date fechaActual = new Date();

		return (fechaActual.after(this.fechaInicio) && fechaActual
				.before(this.fechaFinal));
	}

	@Override
	public boolean devolverPromocion(Usuario usuario, Itinerario listaItinerario) {

		boolean confirmarPromocion = false;
		if ((this.validarFechaPromocion())
				&& (usuario.getCantidadDeEntradas() > this.cantidadMinimaDeEntradas - 1)) {
			usuario.sumarAlPresupuesto(calcularValorPromocion(listaItinerario,
					usuario.getCantidadDeEntradas()));
			confirmarPromocion = true;
		} else
			confirmarPromocion = false;

		return confirmarPromocion;
	}

	public float calcularValorPromocion(Itinerario listaItinerario,
			int cantidadDeEntradas) {
		float valorADevolver = 0;
		Atraccion atraccionSugerida = null;
		cantidadDeEntradas -= this.cantidadMinimaDeEntradas;
		Iterator<Atraccion> iteradorItinerario = listaItinerario
				.getItinerario().iterator();

		while (iteradorItinerario.hasNext()) {
			atraccionSugerida = iteradorItinerario.next();
			if (cantidadDeEntradas == 0)
				valorADevolver += atraccionSugerida.getPrecio() * 0.1;
			else
				valorADevolver += atraccionSugerida.getPrecio() * 0.1
						+ atraccionSugerida.getPrecio() * 0.3
						* cantidadDeEntradas;

		}
		return valorADevolver;

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
