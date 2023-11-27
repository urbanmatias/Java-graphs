package TPE;

public class Main {

	public static void main(String[] args) {
		Grafo<Integer> grafo = new GrafoDirigido<>();

        String path = "src/tpe/datasets/dataset1.txt";
        CSVReader reader = new CSVReader(path, grafo);
        reader.read();

        BackTracking backTuneles = new BackTracking(grafo);

        System.out.println("Backtracking ");
        System.out.println(backTuneles.findShortestPath());
        System.out.println("Km: " + backTuneles.getShortestPathLength());
        System.out.println("Cantidad de iteraciones: " + backTuneles.getCantidadR());



        System.out.println(" ");
        

        Greedy greedy = new Greedy(grafo);
        System.out.println("Greedy");
        System.out.println(greedy.findMinimumSpanningTree());
        System.out.println("Km: " + greedy.getCosto());
        System.out.println("Cantidad de iteraciones:  " + greedy.getCantidadR());
        System.out.println(" ");

       
	}
}

