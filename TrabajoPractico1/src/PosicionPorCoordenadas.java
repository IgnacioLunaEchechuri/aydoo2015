
public class PosicionPorCoordenadas {
	private float longitud,latitud;

	public PosicionPorCoordenadas (int latitud,int longitud) {
		this.latitud=latitud;
		this.longitud=longitud;
	}
	
	
	
	public float getLongitud() {
		return longitud;
	}

	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}

	public float getLatitud() {
		return latitud;
	}

	public void setLatitud(float latitud) {
		this.latitud = latitud;
	}

}
