public class Usuario {

	private float presupuesto, velocidadDeTransaldo;
	private float tiempoDisponible;
	private TipoDeAtraccion atracccionPreferida;
	private PosicionPorCoordenadas posicion;
	private int cantidadDeEntradas;

	public Usuario(float presupuesto, float velocidadDeTranslado,
			float tiempoDisponible, TipoDeAtraccion atracccionPreferida,
			PosicionPorCoordenadas posicion, int cantidadDeEntradas) {

		this.presupuesto = presupuesto;
		this.velocidadDeTransaldo = velocidadDeTranslado;
		this.tiempoDisponible = tiempoDisponible;
		this.atracccionPreferida = atracccionPreferida;
		this.posicion = posicion;
		this.cantidadDeEntradas = cantidadDeEntradas;

	}

	public boolean estadoTiempoDisponible(float tiempoRecorridoAtraccion) {
		return ((this.getTiempoDisponible() > tiempoRecorridoAtraccion) || (this
				.getTiempoDisponible() == tiempoRecorridoAtraccion));
	}

	public boolean estadoPresupuestoDisponible(float costoAtraccion) {
		return ((this.presupuesto > costoAtraccion) || (this.presupuesto == costoAtraccion));
	}

	public void sumarAlPresupuesto(float dineroADevolver) {
		this.presupuesto += dineroADevolver;
		
	}

	public void restarDelPresupuesto(float dineroADescontar) {
		this.presupuesto -= dineroADescontar;
		 
	}

	public void restartiempo(float tiempoParaDescontar) {
		this.tiempoDisponible -= tiempoParaDescontar;
	}

	public PosicionPorCoordenadas getPosicion() {
		return posicion;
	}

	public void setPosicion(PosicionPorCoordenadas posicion) {
		this.posicion = posicion;
	}

	public TipoDeAtraccion getAtracccionPreferida() {
		return atracccionPreferida;
	}

	public void setAtracccionPreferida(TipoDeAtraccion atracccionPreferida) {
		this.atracccionPreferida = atracccionPreferida;
	}

	public float getTiempoDisponible() {
		return tiempoDisponible;
	}

	public void setTiempoDisponible(int tiempoDisponible) {
		this.tiempoDisponible = tiempoDisponible;
	}

	public float getVelocidadTranslado() {
		return velocidadDeTransaldo;
	}

	public void setVelocidadTranslado(float velocidadTranslado) {
		this.velocidadDeTransaldo = velocidadTranslado;
	}

	public float getPresupuesto() {
		return presupuesto;
	}

	public void setPresupuesto(float presupuesto) {
		this.presupuesto = presupuesto;
	}

	public int getCantidadDeEntradas() {
		return cantidadDeEntradas;
	}

	public void setCantidadDeEntradas(int cantidadDeEntradas) {
		this.cantidadDeEntradas = cantidadDeEntradas;
	}

	public float devolverTiempoDeRecorridoMasTiempoAtraccion(
			PosicionPorCoordenadas posicionAtraccion, float tiempoAtraccion) {
		float distaciaARecorrer = this.calcularDistancia(posicionAtraccion);
		float tiempo;
		if (distaciaARecorrer == 0)
			tiempo = 0;
		else
			tiempo = this.getVelocidadTranslado() / distaciaARecorrer;
		return tiempo + tiempoAtraccion;
	}

	public float calcularDistancia(PosicionPorCoordenadas posicionAtraccion) {
		float distancia = (float) Math.sqrt(Math.pow(
				posicionAtraccion.getLatitud()
						- this.getPosicion().getLatitud(), 2)
				+ Math.pow(posicionAtraccion.getLongitud()
						- this.getPosicion().getLongitud(), 2));

		return distancia;
	}
}
