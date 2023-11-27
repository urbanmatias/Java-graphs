package TPE;

import java.util.ArrayList;
import java.util.Iterator;

public class BackTracking {
    private Grafo<?> graph;
    private ArrayList<Arco<?>> shortestPath;
    private ArrayList<Arco<?>> tuneles;
    private int shortestPathLength;
    private int cantidadR;
   
    public BackTracking(Grafo<?> graph) {
        this.graph = graph;
        this.shortestPath = new ArrayList<>();
        this.shortestPathLength = Integer.MAX_VALUE;
        this.cantidadR = 0;
        this.tuneles = new ArrayList<>();
    }

    public ArrayList<Arco<?>> findShortestPath() {
        ArrayList<Arco<?>> arcos = new ArrayList<>();
        Iterator<? extends Arco<?>> it = graph.obtenerArcos();
        while(it.hasNext()) {
        	arcos.add(it.next());
        }
 
        backTrack(0, 0,cantidadR+1,arcos);
        return shortestPath;
    }

    private void backTrack(int actual, int km, int recursiones, ArrayList<Arco<?>> arcos) {
        if (actual >= arcos.size()) {
            if (esSolucion(tuneles)) {
             if(km < shortestPathLength) {
            	 shortestPathLength = km;
            	 shortestPath = new ArrayList<>(tuneles);
             }
            }
        } else {
        	Arco<?> arco = arcos.get(actual);
        	if(arcosSuficientes(tuneles, actual)) {
        		backTrack(actual + 1, km, this.cantidadR, arcos);
        	}
        	tuneles.add(arco);
        	if(!poda(tuneles,km)) {
        		backTrack(actual + 1, km + arco.getEtiqueta(), this.cantidadR += 1, arcos);
        	}
        	tuneles.remove(tuneles.size() - 1);
        }
    }

    private boolean arcosSuficientes(ArrayList<Arco<?>> tuneles, int actual){
        return graph.cantidadArcos() - actual >= (graph.cantidadVertices()-1 - tuneles.size());
     }

    
    private boolean poda(ArrayList<Arco<?>> solucionActual, int costoActual) {
        return (costoActual > this.shortestPathLength || solucionActual.size() >= this.graph.cantidadVertices());
    }
    
    private boolean esSolucion(ArrayList<Arco<?>> conjuntoSolucion){

        int cantVertices = graph.cantidadVertices();
        if(cantVertices - 1 == conjuntoSolucion.size()){
            ArrayList<int[]> edges =  new ArrayList<>();
            for(Arco<?> a : conjuntoSolucion){
                int u = a.getVerticeOrigen()-1;
                int v = a.getVerticeDestino()-1;
                edges.add(new int[] {u,v});
            }
            return isGraphConnected(edges, cantVertices);
        }
        else {
            return false;
        }
    }

    public boolean isGraphConnected(ArrayList<int[]> edges, int cantVertices) {
        UnionFind uf = new UnionFind(cantVertices);
        for (int[] edge : edges) {
            uf.union(edge[0], edge[1]);
        }
        int root = uf.find(0);
        for (int i = 1; i < cantVertices; i++) {
            if (uf.find(i) != root) {
                return false;
            }
        }
        return true;
    }

	public int getShortestPathLength() {
		return shortestPathLength;
	}

	public int getCantidadR() {
		return cantidadR;
	}

}