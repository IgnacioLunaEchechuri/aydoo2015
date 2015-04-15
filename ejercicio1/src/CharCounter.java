import java.util.HashMap;

public class CharCounter {
	private String frase;

	public CharCounter(String frase) {
		this.frase = frase;
	}

	public Object howMany(char c) {
		int contadorLetrasEncontradas = 0;
		switch (c) {
		case '$':
			contadorLetrasEncontradas = -1;
			break;
		case '@':
			contadorLetrasEncontradas = -2;
			break;
		default:
			for (int indice = 0; indice < this.frase.length(); indice++)
				if (frase.charAt(indice) == c)
					contadorLetrasEncontradas++;
			break;
		}
		return contadorLetrasEncontradas;

	}

}
