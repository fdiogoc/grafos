package grafos.src;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ComponenteConexa {

    // ESTA CLASSE ADICIONA, VERIFICA SE O CICLO FOI FORMADO, CASO SIM O REMOVE
    public static Graph addCheckRemoveCycle(Graph graph, int x, int y) {

        graph.addEdge(x, y);

        if (graph.isCyclic()) {
            graph.connectedComponents();
            System.out.print(" CICLO FORMADO!");
            System.out.println();
            graph.delEdge(x, y);

        } else {
            graph.connectedComponents();
            System.out.println();
        }

        return graph;

    }

    public static void main(String[] args) throws Exception {

        // https://www.baeldung.com/java-stream-operated-upon-or-closed-exception
        try (Stream<String> lines = Files
                .lines(Paths.get("/home/fdiogoc/unifil/grafos/terceiroTRAB/assets/input00.txt"))) {
            String[] object = lines.toArray(String[]::new);
            Graph g1 = new Graph(0);
            int index = 0;
            for (String line : object) {
                if (index == 0) {
                    g1 = new Graph(Integer.parseInt(object[0]) + 1);
                } else {
                    String array1[] = line.split(" ");
                    g1 = addCheckRemoveCycle(g1, Integer.parseInt(array1[0]), Integer.parseInt(array1[1]));

                }

                index++;
            }
        }
    }
}
