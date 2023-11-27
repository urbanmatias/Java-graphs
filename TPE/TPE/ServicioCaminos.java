package TPE;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ServicioCaminos {
	private Grafo<?> grafo;
	private int origen;
	private int destino;
	private int lim;
	
	// Servicio caminos
	public ServicioCaminos(Grafo<?> grafo, int origen, int destino, int lim) {
		this.grafo = grafo;
		this.origen = origen;
		this.destino = destino;
		this.lim = lim;
	}

	public List<List<Integer>> caminos() {
		List<List<Integer>> result = new ArrayList<>();
		ArrayList<Arco<?>> visited = new ArrayList<>(); //HAY QUE USAR SET???
		List<Integer> actualRoad = new ArrayList<>();
		buscarCaminos(origen,result,actualRoad,visited);
		return result;
	}
	
	private void buscarCaminos(int actual, List<List<Integer>> result, List<Integer> actualRoad, ArrayList<Arco<?>> visited){
		actualRoad.add(actual);
		if(visited.size() <= lim){
				if(actual == (this.destino)){
					result.add(new ArrayList<>(actualRoad));
				} 
					Iterator<Integer> ad = this.grafo.obtenerAdyacentes(actual);
					while(ad.hasNext() && visited.size()<=lim){
						Integer x = ad.next();
						Arco<?> ar = grafo.obtenerArco(actual, x);
						if(!visited.contains(ar)){
							visited.add(ar);
							buscarCaminos(x,result,actualRoad,visited);	
							visited.remove(visited.size()-1);
							actualRoad.remove(actualRoad.size()-1);
						}
					}
		}
	}
}
