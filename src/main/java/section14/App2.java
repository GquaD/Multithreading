package section14;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class App2 {
    public static void main(String[] args) throws IOException {
        String path = "src/main/java/section14/names";
        Stream<String> namesStream = Files.lines(Paths.get(path));

        //namesStream.forEach(System.out::println);
        List<String> names = namesStream.toList();
//        names.forEach(System.out::println);
        names.stream().filter(n -> n.startsWith("S")).forEach(System.out::println);
    }
}
