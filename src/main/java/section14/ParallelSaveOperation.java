package section14;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class ParallelSaveOperation {

    public static final String DIRECTORY = System.getProperty("user.dir") + "/test";

    public static void main(String[] args) throws IOException {
        //create dir
        Files.createDirectories(Paths.get(DIRECTORY));
        ParallelSaveOperation app = new ParallelSaveOperation();
        List<Person> listPeople = app.generatePeople(100_000);

        //sequential algo
        long start = System.currentTimeMillis();
        listPeople.stream().forEach(ParallelSaveOperation::save);

        System.out.println("Time taken sequential: " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        listPeople.parallelStream().forEach(ParallelSaveOperation::save);

        System.out.println("Time taken parallel: " + (System.currentTimeMillis() - start));
    }

    private static void save(Person person) {
        try (FileOutputStream fos =
                     new FileOutputStream(new File(DIRECTORY + person.getPersonId() + ".txt"))) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Person> generatePeople(int num) {
        return Stream.iterate(0, n -> n + 1)
                .limit(num)
                .map(Person::new)
                .toList();
    }
}
