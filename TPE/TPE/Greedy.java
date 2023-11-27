package TPE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;


public class Greedy {
	
	private Grafo<?> graph;
    private ArrayList<Arco<?>> minimumSpanningTree;
    private int cantidadR;
    private int costo;

    public Greedy(Grafo<?> graph) {
        this.graph = graph;
        this.minimumSpanningTree = new ArrayList<>();
        this.cantidadR = 0;
        this.costo = 0;
    }

    public int getCosto() {
		return costo;
	}

	public ArrayList<Arco<?>> findMinimumSpanningTree() {
    	ArrayList<Arco<?>> arcos = new ArrayList<>();
    	Iterator<? extends Arco<?>> it = graph.obtenerArcos();
        while(it.hasNext()) {
        	arcos.add(it.next());
        }
        extracted(arcos);
        UnionFind unionFind = new UnionFind(graph.cantidadVertices() + 1);

        for (Arco<?> arco : arcos) {
        	cantidadR++;
            int vertex1 = arco.getVerticeOrigen();
            int vertex2 = arco.getVerticeDestino();

            int root1 = unionFind.find(vertex1);
            int root2 = unionFind.find(vertex2);

            if (root1 != root2) {
                unionFind.union(root1, root2);
                minimumSpanningTree.add(arco);
                costo += arco.getEtiqueta();
            }
        }

        return minimumSpanningTree;
    }

	@SuppressWarnings("unchecked")
	private void extracted(ArrayList<Arco<?>> arcos) {
		Collections.sort(arcos);
	}

	public int getCantidadR() {
		return cantidadR;
	}
}
