package unifil.lib;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * * Métodos extraidos para não repetir código WIP.
*/

public final class Utils {

    /**
     * @param dirPath
     * @return List<File>
     */
    private Utils() {
        super();
    }

    /**
     * @param dirPath
     * @return List<File>
     */
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
