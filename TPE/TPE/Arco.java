package TPE;

/*
 * La clase arco representa un arco del grafo. Contiene un vertice origen, un vertice
 *  destino y una etiqueta.
 * Nota: Para poder exponer los arcos fuera del grafo y que nadie los modifique se
 *  hizo esta clase inmutable
 * (Inmutable: una vez creado el arco no es posible cambiarle los valores).
 */
@SuppressWarnings("rawtypes")
public class Arco<T> implements Comparable{

	private int verticeOrigen;
	private int verticeDestino;
	private Integer etiqueta;

	public Arco(int verticeOrigen, int verticeDestino, Integer etiqueta) {
		this.verticeOrigen = verticeOrigen;
		this.verticeDestino = verticeDestino;
		this.etiqueta = etiqueta;
	}
	
	public int getVerticeOrigen() {
		return verticeOrigen;
	}
	
	public int getVerticeDestino() {
		return verticeDestino;
	}

	public Integer getEtiqueta() {
		return etiqueta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + verticeDestino;
		result = prime * result + verticeOrigen;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Arco<?> other = (Arco<?>) obj;
		if (verticeDestino != other.verticeDestino)
			return false;
		if (verticeOrigen != other.verticeOrigen)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "(" + verticeOrigen + ", " + verticeDestino + ")";
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof Arco) {
			Arco<?> otroArco = (Arco<?>) o;
			return this.etiqueta.compareTo(otroArco.etiqueta);
		}
		return 0;
	}
	
}
