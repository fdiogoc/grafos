package unifil;

import unifil.lib.Utils;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Queue;
import java.util.stream.Stream;

/**
 * ESTA CLASSE CRIA O GRAFO A PARTIR DOS ARQUIVOS E GERA O OUTPUT DESEJADO.
 **/

final class atividade2 {

    static final String COMPONENT_PATH = "./assets/atividade2";

    private atividade2() {
        super();
    }

    /**
     * ESTA CLASSE ADICIONA UMA ARESTA, VERIFICA SE O CICLO FOI FORMADO, CASO SIM O
     * REMOVE E RETORNA UMA STRING.
     *
     * @param graph - grafo que irá ser utilizado
     * @param x     - valor da aresta x sendo adicionada
     * @param y     - valor da aresta y sendo adicionada
     * @return retorna uma string, com o valor de Componente conexas, e CICLO
     *         FORMADO, caso um ciclo tenha sido formado.
     */

    public static String addEdge(Graph graph, int x, int y) {

        graph.addEdge(x, y);
        if (Boolean.TRUE.equals(graph.isCyclic())) {
            graph.delEdge(x, y);
            return String.valueOf(graph.connectedComponents()) + " CICLO FORMADO!";
        } else {
            return String.valueOf(graph.connectedComponents());

        }
    }

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        // https://www.baeldung.com/java-stream-operated-upon-or-closed-exception
        // ITERA TODOS ARQUIVOS DA PASTA
        for (File file : Utils.readFilesFromDir(COMPONENT_PATH)) {
            if (file.isFile()) {

                BufferedWriter writer = new BufferedWriter(
                        new FileWriter(COMPONENT_PATH + "/output/out_" + file.getName()));
                Graph g1 = new Graph(0);

                int index = 0;
                try (Stream<String> lines = Files.lines(Paths.get(file.getAbsolutePath()))) {
                    String[] object = lines.toArray(String[]::new);

                    for (String line : object) {

                        if (index == 0) {

                            String[] values;
                            if (!line.isBlank()) {
                                values = line.split(" ");
                                g1 = new Graph(Integer.parseInt(values[0]));
                            }

                        } else {
                            String[] values;
                            if (!line.isBlank()) {
                                values = line.split(" ");
                                addEdge(g1, Integer.parseInt(values[0]), Integer.parseInt(values[1]));
                            }

                        }

                        index++;
                    }
                    int max = 0;

                    Queue<Integer> components = g1.componentsSize();
                    int mComponent = components.poll();
                    while (components.size() >= 1) {
                        for (int i : components) {
                            max = max + mComponent * i;

                        }
                        mComponent = components.poll();
                    }

                    writer.write(max + " " + g1.biggerComponentSize() + " " + g1.connectedComponents());
                    System.out.println(max + " " + g1.biggerComponentSize() + " " + g1.connectedComponents());
                } finally {
                    writer.close();
                }

            }
        }
    }

}
