package readAndWriteJAR;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FindPath {
        private Path path;

        public FindPath() {
            URI uri = null;
            try {
                uri = this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            path = Paths.get(uri).toAbsolutePath();
        }

        /*public void printPath(){
            System.out.format("toString: %s%n", path.toString());
            System.out.format("getFileName: %s%n", path.getFileName());
            System.out.format("getName(0): %s%n", path.getName(0));
            System.out.format("getNameCount: %d%n", path.getNameCount());
            System.out.format("subpath(0,2): %s%n", path.subpath(0, 2));
            System.out.format("getParent: %s%n", path.getParent());
            System.out.format("getRoot: %s%n", path.getRoot());
            System.out.println("");
        }*/

        public Path addToPath (String... args) {
            Path tempPath = path.getParent();
            for (String arg : args) {
                tempPath = tempPath.resolve(Paths.get(arg));
            }
            return tempPath;
        }

    }
