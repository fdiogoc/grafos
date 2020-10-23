package grafos.src;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ComponenteConexa {
    // LOCAL DOS ARQUIVOS DE TESTE
    final static String componentePath = "/home/fdiogoc/unifil/grafos/terceiroTRAB/assets/componenteConexa";

    // ESTA CLASSE ADICIONA, VERIFICA SE O CICLO FOI FORMADO, CASO SIM O REMOVE E SALVA NO ARQUIVO output
    public static String addCheckRemoveCycle(Graph graph, int x, int y) {
        
        // BufferedWriter writer = new BufferedWriter(new FileWriter("top"));
        // writer.write(str);

        //writer.close();
        graph.addEdge(x, y);

        if (graph.isCyclic()) {
            graph.connectedComponents();
            
            graph.delEdge(x, y);
            return (String.valueOf(graph.connectedComponents()) + " CICLO FORMADO!");
          

        } else {
            return (String.valueOf(graph.connectedComponents()));
          
        }

       

    }

    public static void main(String[] args) throws Exception {
        
        // https://www.baeldung.com/java-stream-operated-upon-or-closed-exception
     

        for (File file : readFilesFromDir(componentePath)) {
            if (file.isFile()) {
                BufferedWriter writer = new BufferedWriter(new FileWriter(componentePath+"/out_"+file.getName()));
                try (Stream<String> lines = Files.lines(Paths.get(file.getAbsolutePath()))) {
                    String[] object = lines.toArray(String[]::new);
                    Graph g1 = new Graph(0);
                    int index = 0;
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

        // Files.list(Paths.get("folder")).filter(Files::isRegularFile).forEach(filePath
        // -> {
        // try (Stream<String> lines = Files.lines(filePath)) {
        // String[] object = lines.toArray(String[]::new);
        // Graph g1 = new Graph(0);
        // int index = 0;
        // for (String line : object) {
        // if (index == 0) {
        // g1 = new Graph(Integer.parseInt(object[0]) + 1);
        // } else {
        // String array1[] = line.split(" ");
        // g1 = addCheckRemoveCycle(g1, Integer.parseInt(array1[0]),
        // Integer.parseInt(array1[1]));

        // }

        // index++;
        // }
        // }
        // });

    }

    // LE OS ARQUIVOS DA PASTA E RETORNA UMA LISTA DE ARQUIVOS
    public static List<File> readFilesFromDir(String dirPath) {
        List<File> filesInFolder = new ArrayList<File>();

        try {
            filesInFolder = Files.list(Paths.get(dirPath)).filter(Files::isRegularFile).map(Path::toFile)
                    .collect(Collectors.toList());
        } catch (IOException e) {

            e.printStackTrace();
        }
        return filesInFolder;
    }


}
