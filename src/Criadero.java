import java.util.LinkedList;

public class Criadero {

	private LinkedList<EstanqueAbstracta> estanques;
	private int volumen;
	private String desborde;
	private int estanquesLlenos;
	

	public Criadero(LinkedList<EstanqueAbstracta> estanques, int volumen) {
		this.volumen = volumen;
		this.estanques = estanques;
	}

	public Criadero resolver() {

		
		int capacidad = 0;
		float alturaCaņo = 0;

		for (EstanqueAbstracta est : estanques) {
			capacidad += est.getSuperficie() * est.getProfundidadEstanque();
		}

		if (capacidad < volumen) {

			this.setDesborde("hay desborde: " + Integer.toString((volumen - capacidad)));
			return this;

		}

		int i = 0;
		int volumenCaņo = 0;
		int cantTanques = 0;
		int alturaUltimoEst = 0;

		while (i < estanques.size() - 1 && volumen >= volumenCaņo) {

			Estanque est = (Estanque) estanques.get(i);
			volumenCaņo = (est.getProfundidadEstanque() - est.getProfundidadCaņo()) * est.getSuperficie();
			alturaCaņo = est.getProfundidadEstanque() - est.getProfundidadCaņo();

			if (volumen >= volumenCaņo) {
				cantTanques++;
				volumen -= volumenCaņo;
				estanques.get(i).setAlturaAgua(alturaCaņo);

			} else {
				int auxvol = 0;
				auxvol = volumenCaņo;
				cantTanques++;
				volumen -= volumenCaņo;
				alturaCaņo = ((-volumen) * alturaCaņo) / volumenCaņo;
				estanques.get(i).setAlturaAgua(alturaCaņo);

			}

			i++;
		}

		Estanque est = (Estanque) estanques.get(i);
		volumenCaņo = (est.getProfundidadEstanque() - est.getProfundidadCaņo()) * est.getSuperficie();

		if (volumen >= volumenCaņo) {

			cantTanques++;
			volumen -= volumenCaņo;
			estanques.get(i).setAlturaAgua(alturaCaņo);
		}

			if (volumen > 0) {

				cantTanques++;
				alturaUltimoEst = est.getProfundidadCaņo();
				i++;
				alturaCaņo = ((-volumen) * alturaCaņo) / volumenCaņo;
				estanques.get(i).setAlturaAgua(alturaCaņo);
				
			}
			
			for(i=estanques.size()-1;i>=cantTanques;i--) {
				estanques.remove(i);
			}
		
		this.setEstanquesLlenos(cantTanques);
		
		return this;

	}

	public LinkedList<EstanqueAbstracta> getEstanques() {
		return estanques;
	}

	public void setEstanques(LinkedList<EstanqueAbstracta> estanques) {
		this.estanques = estanques;
	}

	public int getVolumen() {
		return volumen;
	}

	public void setVolumen(int volumen) {
		this.volumen = volumen;
	}

	public String getDesborde() {
		return desborde;
	}

	public void setDesborde(String desborde) {
		this.desborde = desborde;
	}

	public int getEstanquesLlenos() {
		return estanquesLlenos;
	}

	public void setEstanquesLlenos(int estanquesLlenos) {
		this.estanquesLlenos = estanquesLlenos;
	}
}
