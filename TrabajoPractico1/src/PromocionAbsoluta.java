import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class PromocionAbsoluta implements Promociones {

	private float valorPromocion;
	private Date fechaInicio;
	private Date fechaFinal;
	private List<Atraccion> listaDeAtracciones = new LinkedList<Atraccion>();

	public PromocionAbsoluta(Date fechaInicio, Date fechaFinal,
			List<Atraccion> listaDeAtracciones, float valorPromocion) {
		this.fechaInicio = fechaInicio;
		this.fechaFinal = fechaFinal;
		this.valorPromocion = valorPromocion;
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

	
	public void setValorPromocion(float devolucionDinero) {
		this.valorPromocion = devolucionDinero;
	}

	@Override
	public boolean DevolverPromocion(Usuario usuario,
			List<Atraccion> sugerenciaDeAtracciones) {
		boolean confirmarPromocion;
		if (this.validarPromocion(sugerenciaDeAtracciones)) {
			usuario.sumarAlPresupuesto(this.valorPromocion);
			confirmarPromocion=true;
		}else
			confirmarPromocion=false;
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
