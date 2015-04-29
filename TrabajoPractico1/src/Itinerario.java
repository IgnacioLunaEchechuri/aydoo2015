import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Itinerario {

	private List<Atraccion> itinerario;

	public Itinerario() {

		this.setItinerario(new LinkedList<Atraccion>());

	}

	public List<Atraccion> getItinerario() {
		return itinerario;
	}

	public void setItinerario(List<Atraccion> itinerario) {
		this.itinerario = itinerario;
	}

	public void agregarAtraccion(Atraccion atraccion){
	this.itinerario.add(atraccion);
	}
	
	public int tamanoItinerario(){
		
		return itinerario.size();
	}
	
	public float costoTotalItinerario(){
	Atraccion atraccion;
	float costoTotal=0;
	
	Iterator<Atraccion> iteradorItinerario = this.itinerario.iterator();

	while (iteradorItinerario.hasNext()) {
			atraccion = iteradorItinerario.next();
			costoTotal+=atraccion.getPrecio();
     }
	
	return costoTotal;
     }

}