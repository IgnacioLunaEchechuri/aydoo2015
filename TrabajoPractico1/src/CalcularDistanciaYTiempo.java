public class  CalcularDistanciaYTiempo {

	public static float calcularDistancia(PosicionPorCoordenadas posicionInicial,
			PosicionPorCoordenadas posicionFinal) {

		return (float) Math.sqrt(Math.pow(posicionFinal.getLatitud()
				- posicionInicial.getLatitud(), 2)
				+ Math.pow(
						posicionFinal.getLongitud()
								- posicionInicial.getLongitud(), 2));

	}

	public static float devolverTiempoDeRecorridoMasTiempoAtraccion(float distaciaARecorrer,
			float velocidad,float tiempoAtraccion) {
		float tiempo;
		if (distaciaARecorrer == 0)
			tiempo = 0;
		else
			tiempo = velocidad / distaciaARecorrer;
		return tiempo+tiempoAtraccion;
	}
	
}
