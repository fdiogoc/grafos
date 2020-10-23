package grafos.src;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.stream.Stream;

import grafos.lib.utils;

public class ComponenteConexa {
    // LOCAL DOS ARQUIVOS DE TESTE
    final static String componentePath = "/home/fdiogoc/unifil/grafos/terceiroTRAB/assets/componenteConexa";

    // ESTA CLASSE ADICIONA, VERIFICA SE O CICLO FOI FORMADO, CASO SIM O REMOVE E RETORNA UMA STRING
    public static String addCheckRemoveCycle(Graph graph, int x, int y) {
        
        graph.addEdge(x, y);
        if (graph.isCyclic()) {
            graph.delEdge(x, y);
            return (String.valueOf(graph.connectedComponents()) + " CICLO FORMADO!");
        } else {
            return (String.valueOf(graph.connectedComponents()));
          
        }
    }

    public static void main(String[] args) throws Exception {
        
        // https://www.baeldung.com/java-stream-operated-upon-or-closed-exception
        //ITERA TODOS ARQUIVOS DA PASTA
        for (File file : utils.readFilesFromDir(componentePath)) {
            if (file.isFile()) {
                BufferedWriter writer = new BufferedWriter(new FileWriter(componentePath+"/out_"+file.getName()));
                Graph g1 = new Graph(0);
                int index = 0;
                try (Stream<String> lines = Files.lines(Paths.get(file.getAbsolutePath()))) {
                    String[] object = lines.toArray(String[]::new);
                    for (String line : object) {
                        if (index == 0) {
                            g1 = new Graph(Integer.parseInt(object[0]) + 1);
                        } else {
                            String array1[] = line.split(" ");
                            writer.write(addCheckRemoveCycle(g1, Integer.parseInt(array1[0]), Integer.parseInt(array1[1])));
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
