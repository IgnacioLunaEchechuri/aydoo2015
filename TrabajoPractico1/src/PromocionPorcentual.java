import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class PromocionPorcentual implements Promocion {

	private float porcentajePromocion;
	
	private Date fechaInicio;
	private Date fechaFinal;
	private List<Atraccion> listaDeAtracciones;

	private boolean promocionAplicada;

	private int identificadorPromocion; 

	public PromocionPorcentual(Date fechaInicio, Date fechaFinal,
			float porcentajePromocion, List<Atraccion> listaAtracciones) {
		this.listaDeAtracciones=new LinkedList<Atraccion>();
		this.fechaInicio = fechaInicio;
		this.fechaFinal = fechaFinal;
		this.porcentajePromocion=porcentajePromocion;
		this.listaDeAtracciones = listaAtracciones;
		this.promocionAplicada=false;
		this.identificadorPromocion=5;
	}

	@Override
	public int getIdentificadorPromocion() {

		return this.identificadorPromocion;
	}

	
	public float montoDelPorcentajeADevolver(float costo) {
		return (costo * this.porcentajePromocion) / 100;
	}

	@Override
	public boolean validarFechaPromocion() {
		Date fechaActual = new Date();
		boolean validar = (fechaActual.after(this.fechaInicio) && fechaActual
				.before(this.fechaFinal));

		return validar;
	}
	@Override
	public boolean devolverPromocion(Usuario usuario,Itinerario listaItinerario) {

		boolean confirmarPromocion=false;
		if (this.validarAtraccionesdeLaPromocion(listaItinerario)){
			usuario.sumarAlPresupuesto((listaItinerario.costoTotalItinerario() * this.porcentajePromocion) / 100);
			confirmarPromocion = true;
		} else
			confirmarPromocion = false;

		return confirmarPromocion;
	}
	
	

	public boolean validarAtraccionesdeLaPromocion(Itinerario listaItinerario) {
		Boolean validacion = false, validarFecha = this.validarFechaPromocion();
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
	
	@Override
	public boolean getAplicarPromocion() {
		
		return this.promocionAplicada;
	}

	@Override
	public void setAplicarPromocion(boolean cambiarEstado) {
		this.promocionAplicada=cambiarEstado;
		
	}


}
