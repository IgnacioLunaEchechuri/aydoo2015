public class Atraccion {
	private PosicionPorCoordenadas posicion;
	private float precio;
	private int capacidadDeVisitantes;
	private int tiempoDelRecorrido;
	private TipoDeAtraccion tipoDeAtraccion;

	public Atraccion(PosicionPorCoordenadas posicion, float precio,
			int capacidad, int tiempo, TipoDeAtraccion tipoDeAtraccion) {
		this.precio = precio;
		this.capacidadDeVisitantes = capacidad;
		this.tiempoDelRecorrido = tiempo;
		this.tipoDeAtraccion = tipoDeAtraccion;
		this.posicion = posicion;

	}

	public boolean comprobarCapacidad(int cantidadDeVisitantes) {
		return this.getCapacidadDeVisitantes() >= cantidadDeVisitantes;

	}

	public void descontarCapacidad(int cantidadDeLugares) {
		this.capacidadDeVisitantes -= cantidadDeLugares;

	}

	public PosicionPorCoordenadas getPosicion() {
		return posicion;
	}

	public void setPosicion(PosicionPorCoordenadas posicion) {
		this.posicion = posicion;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public int getCapacidadDeVisitantes() {
		return capacidadDeVisitantes;
	}

	public void setCapacidadDeVisitantes(int capacidadDeVisitantes) {
		this.capacidadDeVisitantes = capacidadDeVisitantes;
	}

	public int getTiempoDelRecorrido() {
		return tiempoDelRecorrido;
	}

	public void setTiempoDelRecorrido(int tiempoDelRecorrido) {
		this.tiempoDelRecorrido = tiempoDelRecorrido;
	}

	public TipoDeAtraccion getTipoDeAtraccion() {
		return tipoDeAtraccion;
	}

	public void setTipoDeAtraccion(TipoDeAtraccion tipoDeAtraccion) {
		this.tipoDeAtraccion = tipoDeAtraccion;
	}
	public boolean comprobarTipoAtraccionUsuario(Usuario usuario){
		return usuario.getAtracccionPreferida().equals(this.getTipoDeAtraccion());
	}
	

}
