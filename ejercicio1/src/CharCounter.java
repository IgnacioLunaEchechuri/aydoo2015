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
	
public HashMap<Character, Integer> countAll() {
		int a = (int) 'a';
		HashMap<Character, Integer> contadorCaracteres = new HashMap<Character, Integer>();
		char[] abecedario = new char[26];
		char[] charArrayFrase = this.frase.toCharArray();

		for (int i = 0; i < abecedario.length; i++)
			contadorCaracteres.put((char) (a + i), 0);

			for (char i : charArrayFrase) {

				contadorCaracteres.put(i, contadorCaracteres.get(i) == null ? 1
						: contadorCaracteres.get(i) + 1);

			}
		return contadorCaracteres;
		}
	

}
