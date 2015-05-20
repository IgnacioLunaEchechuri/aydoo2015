public interface Promocion {
	

	public abstract boolean devolverPromocion(Usuario usuario,
			Itinerario itinerario);

	public abstract boolean validarFechaPromocion();
	
	public abstract boolean getAplicarPromocion();
	
	public abstract void setAplicarPromocion(boolean cambiarEstado);
	public int getIdentificadorPromocion();
	
}
