public class Usuario {

	private float presupuesto, velocidadDeTransaldo;
	private float tiempoDisponible;
	private TipoDeAtraccion atracccionPreferida;
	private PosicionPorCoordenadas posicion;

	public Usuario(float presupuesto, float velocidadDeTranslado,
			int tiempoDisponible, TipoDeAtraccion atracccionPreferida,
			PosicionPorCoordenadas posicion) {

		this.presupuesto = presupuesto;
		this.velocidadDeTransaldo = velocidadDeTranslado;
		this.tiempoDisponible = tiempoDisponible;
		this.atracccionPreferida = atracccionPreferida;
		this.posicion = posicion;
	}

	public boolean consultaTiempoDisponible(float tiempoRecorridoAtraccion)
	 {
		return ((this.getTiempoDisponible() > tiempoRecorridoAtraccion)||(this.getTiempoDisponible() == tiempoRecorridoAtraccion));
	}
	
	public boolean consultaPresupuestoDisponible(float costoAtraccion)
	 {
		return ((this.presupuesto > costoAtraccion)||(this.presupuesto == costoAtraccion));
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

}
