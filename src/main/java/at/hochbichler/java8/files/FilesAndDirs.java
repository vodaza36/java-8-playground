package at.hochbichler.java8.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.concurrent.TimeUnit;

public class FilesAndDirs {
    public static void main(String[] args) {
        try {

            // List all subdirectories within the tmp folder
            Files.list(Paths.get("/Users/xhocht/tmp"))
                    .filter(Files::isDirectory)
                    .forEach(System.out::println);

            // Filter *.java files
            Files.newDirectoryStream(Paths.get("/Users/xhocht/tmp"), path -> path.toString().endsWith("*.java")).forEach(System.out::println);

            // Watch for file changes
            final Path path = Paths.get("/Users/xhocht/tmp");
            final WatchService watchService = path.getFileSystem().newWatchService();
            path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
            System.out.println("Report changes within next minute");
            final WatchKey watchKey = watchService.poll(1, TimeUnit.MINUTES);

            if (watchKey != null) {
                watchKey.pollEvents().stream().forEach(e -> System.out.println(e.context()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
