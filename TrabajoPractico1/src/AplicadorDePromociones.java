import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class AplicadorDePromociones {
	private List<Promocion> listaPromociones;
	private boolean aplicacionPromocionNoAcumulable;
	private boolean aplicacionPromocionesAcumulables;

	public AplicadorDePromociones() {

		listaPromociones = new LinkedList<Promocion>();
		this.aplicacionPromocionNoAcumulable = false;
		this.aplicacionPromocionesAcumulables = false;
	}

	public void agregarPromociones(Promocion promocion) {
		this.listaPromociones.add(promocion);
	}

	public List<Promocion> getListaDePromociones() {

		return this.listaPromociones;
	}

	public boolean aplicacionPromocionNoAcumulable() {
		return this.aplicacionPromocionNoAcumulable;

	}

	public boolean aplicacionPromocionAcumulable() {
		return this.aplicacionPromocionesAcumulables;

	}

	public void ejecutarPromociones(Usuario usuario, Itinerario listaItinerario) {

		if (this.listaPromociones.size() != 0) {
			Promocion promocion;
			Iterator<Promocion> iteradorPromociones = this.listaPromociones
					.iterator();

			while (iteradorPromociones.hasNext()) {

				promocion = iteradorPromociones.next();

				if (usuario.getCantidadDeEntradas() > 3) {
					if (promocion.getIdentificadorPromocion() == 1) {
						this.aplicacionPromocionNoAcumulable = promocion
								.devolverPromocion(usuario, listaItinerario);
						promocion.setAplicarPromocion(true);

					}
				} else {

					if (promocion.devolverPromocion(usuario, listaItinerario)) {
						this.aplicacionPromocionesAcumulables = true;
						promocion.setAplicarPromocion(true);
					}

				}
			}
		}

	}
}
