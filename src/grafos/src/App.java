package grafos.src;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) throws Exception {
       //https://www.baeldung.com/java-stream-operated-upon-or-closed-exception

        try (Stream<String> lines = Files.lines(Paths.get("/home/fdiogoc/unifil/grafos/terceiroTRAB/assets/input00.txt"))) {
            String[] object = lines.toArray(String[]::new);
            //System.out.println(lines.findFirst() + "LINHA 1");

            
            for (String line : object)
            {           
                String array1[]= line.split(" ");
                for (String temp: array1){
                      System.out.println(temp);
                }
            }
        }
    }
}
