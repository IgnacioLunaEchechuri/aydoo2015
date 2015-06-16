public interface Promocion {
	

	public abstract void aplicarPromocion(Usuario usuario,
			Itinerario itinerario,boolean estadoPromocionAcumulable);

	public abstract boolean estadoFechaDePromocionValida();
	

	
	
	
	
}
