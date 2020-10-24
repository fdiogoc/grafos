package grafos.src.main.java.unifil;

import grafos.src.main.java.unifil.lib.Utils;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * ESTA CLASSE CRIA O GRAFO A PARTIR DOS ARQUIVOS E GERA O OUTPUT DESEJADO.
 **/

final class ComponenteConexa {

    static final String COMPONENT_PATH = "/home/fdiogoc/unifil/grafos/terceiroTRAB/assets/componenteConexa";


    private ComponenteConexa() {
        super();
    }

    /**
     * ESTA CLASSE ADICIONA UMA ARESTA, VERIFICA SE O CICLO FOI FORMADO, CASO SIM O REMOVE E
     * RETORNA UMA STRING.
     *
     * @param graph - grafo que irá ser utilizado
     * @param x     - valor da aresta x sendo adicionada
     * @param y     - valor da aresta y sendo adicionada
     * @return retorna uma string, com o valor de Componente conexas, e CICLO
     *         FORMADO, caso um ciclo tenha sido formado.
     */

    public static String addCheckRemoveCycle(Graph graph, int x, int y) {

        graph.addEdge(x, y);
        if (graph.isCyclic()) {
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
                            g1 = new Graph(Integer.parseInt(object[0]) + 1);
                        } else {
                            String[] array1 = line.split(" ");
                            writer.write(
                                    addCheckRemoveCycle(g1, Integer.parseInt(array1[0]), Integer.parseInt(array1[1])));
                            writer.newLine();
                        }

                        index++;
                    }
                }
                writer.close();
            }
        }
    }

}
