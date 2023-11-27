package TPE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class GrafoDirigido<T> implements Grafo<T> {
	
	private HashMap<Integer, ArrayList<Arco<T>>> vertices;
	private int cantArcos;
	
	public GrafoDirigido(){
		 this.vertices = new HashMap<Integer, ArrayList<Arco<T>>>();
		this.cantArcos = 0;
	}
	

	@Override
	public void agregarVertice(int verticeId) {
		if(!vertices.containsKey(verticeId)){
			vertices.put(verticeId, new ArrayList<Arco<T>>());
		}
		/*
		 * Complejidad: O(1) ya que el método verifica que no existe el verticeId que se quiere agregar y en el caso 
		 * de que no exista, lo agrega.
		 */
	}

	@Override
	public void borrarVertice(int verticeId) {
		for(Integer i : vertices.keySet()){
			borrarArco(i,verticeId);
		}
		this.cantArcos -= vertices.get(verticeId).size();
		vertices.remove(verticeId);
		/*
		 * Complejidad: O(n*a) siendo n la cantidad de vertices total y a es el número de arcos asociados al vértice borrado.
		 * El método remove tiene complidad O(1) ya que es un método propio de HashMap.
		 */

	}

	@Override
	public void agregarArco(int verticeId1, int verticeId2, Integer etiqueta) {
		if(vertices.containsKey(verticeId1) && vertices.containsKey(verticeId2)){
			Arco<T> ar = new Arco<T>(verticeId1,verticeId2,etiqueta);
			if(!vertices.get(verticeId1).contains(ar)){
				vertices.get(verticeId1).add(ar);
				this.cantArcos++;
			}
		}
		/*
		 * Complejidad: O(n) donde n es la cantidad de arcos que contiene verticeId1, ya que el método contains
		 * recorre un ArrayList de arcos, verificando que no se encuentre en dicho ArrayList antes de agregarlo.
		 */

	}

	@Override
	public void borrarArco(int verticeId1, int verticeId2) {
		if(vertices.containsKey(verticeId1) && vertices.containsKey(verticeId2)){
			Arco<T> ar = new Arco<T>(verticeId1,verticeId2,null);
			vertices.get(verticeId1).remove(ar);
			this.cantArcos--;
		}
		/*
		 * Compleidad: O(n) siendo n la cantidad de arcos que posee el vértice verticeId1.
		 */

	}

	@Override
	public boolean contieneVertice(int verticeId) {
		return vertices.containsKey(verticeId);
		/*
		 * Complejidad: O(1) ya que se implementa un método propio de HashMap.
		 */
	}

	@Override
	public boolean existeArco(int verticeId1, int verticeId2) {
		if(vertices.containsKey(verticeId1) && vertices.containsKey(verticeId2)){
			Arco<T> ar = new Arco<T>(verticeId1,verticeId2,null);
			return vertices.get(verticeId1).contains(ar);
		}else{
			return false;
		}
		/*
		 * Complejidad: O(n) donde n es la cantidad de arcos que posee el vértie verticeId1, ya que utiliza el método contains de
		 * ArrayList. 
		 */
	}

	@Override
	public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
		for(Arco<T> i: vertices.get(verticeId1)){
			if(i.getVerticeDestino() == (verticeId2)){
				return i;
			}
		}
		return null;
		/*
		 * Complejidad: O(n) donde n es la cantidad de arcos que posee el vértice verticeId1.
		 */
	}

	
	@Override
	public int cantidadVertices() {
		return vertices.size();
		/*
		 * Complejidad: O(1) ya que el método sólo retorna el valor del método size que implementa HashMap.
		 */
	}

	
	@Override
	public int cantidadArcos() {
		return this.cantArcos;
		/*
		 *Complejidad: O(1) ya que el método sólo retorna el valor que tiene del atributo cantArcos de la clase GrafoDirigido.
		 */
	}

	@Override
	public Iterator<Integer> obtenerVertices() {
		return vertices.keySet().iterator();
		/*
		 * Complejidad:: O(1) ya que implementa un método propio de HashMap. 
		 */
	}

	@Override
	public Iterator<Integer> obtenerAdyacentes(int verticeId) {
		ArrayList<Arco<T>> arcos = vertices.get(verticeId);
		ArrayList<Integer> adyacentes = new ArrayList<>();
		for(Arco<T> i: arcos){
			adyacentes.add(i.getVerticeDestino());
		}
		return adyacentes.iterator();
		/*
		 * Complejidad: O(n) siendo n la cantidad de arcos salientes del vértice que el método recibe por parámetro.
		 */
	}

	@Override
	public Iterator<Arco<T>> obtenerArcos() {
		ArrayList<Arco<T>> arcos = new ArrayList<>();
		for(ArrayList<Arco<T>> i: vertices.values()){
			arcos.addAll(i);
		}
		return arcos.iterator();
		/*
		 * Complejidad: O(n*a) siendo n la cantidad de vértices que posea el Grafo. El método recorre todos los vértices para obtener
		 * sus arcos, y a la cantidad de arcso de cada vertice.
		 */
	}

	@Override
	public Iterator<Arco<T>> obtenerArcos(int verticeId) {
		return vertices.get(verticeId).iterator();
		/*
		 * Complejidad: O(1) ya que implementa un método que es propio de HashMap. El método retorna un iterador porpio del ArrayList de
		 * arcos que contiene dicho vértice.
		 */
	}

}