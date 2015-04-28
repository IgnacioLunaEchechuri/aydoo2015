import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class PromocionPorcentual implements Promociones {

	private float porcentajePromocion;
	private float costoTotalAtracciones;
	private Date fechaInicio;
	private Date fechaFinal;
	private List<Atraccion> listaDeAtracciones = new LinkedList<Atraccion>();

	public PromocionPorcentual(Date fechaInicio, Date fechaFinal,
			float porcentajePromocion, List<Atraccion> listaAtracciones) {
		this.setFechaInicio(fechaInicio);
		this.setFechaFinal(fechaFinal);
		this.setPorcentajePromocion(porcentajePromocion);
		this.listaDeAtracciones = listaAtracciones;
	}

	public float getPorcentajePromocion() {
		return porcentajePromocion;
	}

	public void setPorcentajePromocion(float porcentajePromocion) {
		this.porcentajePromocion = porcentajePromocion;
	}

	public void setCostoTotalAtracciones(float costoTotalAtracciones) {
		this.costoTotalAtracciones = costoTotalAtracciones;
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

	public float montoDelPorcentajeADevolver(float costo) {
		return (costo * this.porcentajePromocion) / 100;
	}

	@Override
	public boolean DevolverPromocion(Usuario usuario,
			List<Atraccion> sugerenciaDeAtracciones) {
		boolean confirmarPromocion;
		if (this.validarPromocion(sugerenciaDeAtracciones)) {
			usuario.sumarAlPresupuesto((this.costoTotalAtracciones * this.porcentajePromocion) / 100);
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
