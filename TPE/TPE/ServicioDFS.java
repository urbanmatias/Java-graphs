package TPE;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class ServicioDFS {
	private Grafo<?> grafo;

	public ServicioDFS(Grafo<?> grafo) {
		this.grafo = grafo;
	}

	public List<Integer> dfsForest() {
		Map<Integer, String> visited = new HashMap<>();
			
	    List<Integer> data = new ArrayList<>(); 

	    Iterator<Integer> it = grafo.obtenerVertices();
	    while (it.hasNext()) {
	        Integer vertex = it.next();
	        if (!visited.containsKey(vertex)) {
	            dfs(vertex, visited,data);
	        }
	    }
	    return data; 
	}

	private void dfs(Integer vertex, Map<Integer, String> visited,List<Integer> data) {
	    data.add(vertex);
	    visited.put(vertex, "Yellow");
	    Iterator<Integer> it = grafo.obtenerAdyacentes(vertex);
	    while (it.hasNext()) {
	    	Integer vecino = it.next();
	    	if (!visited.containsKey(vecino)) {
	            dfs(vecino, visited,data);
	        } else {
	        	System.out.println("Es ciclico");
	        }
	    }
		visited.put(vertex,"Black");
	}
}
