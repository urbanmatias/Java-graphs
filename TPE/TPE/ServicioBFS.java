package TPE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ServicioBFS {
private Grafo<?> grafo;
	
	public ServicioBFS(Grafo<?> grafo) {
		this.grafo = grafo;
	}
	
	public List<Integer> bfsForest() {
		List<Integer> aux = new ArrayList<>();
		Map<Integer,String> visited = new HashMap<>();
		Iterator<Integer> it = grafo.obtenerVertices();
		while(it.hasNext()){
			visited.put(it.next(), "Blanco");
		}
		it = grafo.obtenerVertices();
		while(it.hasNext()){
			Integer vertex = it.next();
			if(visited.get(vertex).equals("Blanco")){
				aux.addAll(bfs(vertex,visited));
			}
		}
		return aux;
	}
	
	private List<Integer> bfs(Integer vertex, Map<Integer,String> visited){
		visited.put(vertex, "Yellow");
		List<Integer> aux = new ArrayList<>();
		List<Integer> fila = new ArrayList<>();
		fila.add(vertex);
		while(!fila.isEmpty()){
			Integer x = fila.get(0);
			fila.remove(0);
			Iterator<Integer> it3 = grafo.obtenerAdyacentes(x);
			aux.add(x);
			while(it3.hasNext()){
				Integer y = it3.next();
				if(visited.get(y).equals("Blanco")){
					visited.put(y, "Yellow");
					fila.add(y);
				}
			}
		}
		return aux;
	}
}
