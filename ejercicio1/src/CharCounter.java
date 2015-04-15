import java.util.HashMap;

public class CharCounter {
	private String frase;

	public CharCounter(String frase) {
		this.frase = frase;
	}

	public Object howMany(char c) {
		int contadorLetrasEncontradas = 0;
		if (c=='$')
			contadorLetrasEncontradas = -1;
        else
			
			for (int indice = 0; indice < this.frase.length(); indice++)
				if (frase.charAt(indice) == c)
					contadorLetrasEncontradas++;
			
	  return contadorLetrasEncontradas;

	}

}
