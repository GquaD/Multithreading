package section14;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AppBook1 {
    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        books.add(new Book("B", "Hi", 560, Type.PHILOSOPHY));
        books.add(new Book("Tt", "F", 240, Type.NOVEL));
        books.add(new Book("Dn", "Ag", 370, Type.THRILLER));
        books.add(new Book("Ag", "R", 435, Type.HISTORY));
        books.add(new Book("Ar", "R", 860, Type.HISTORY));
        books.add(new Book("Dv", "Hr", 590, Type.NOVEL));
        books.add(new Book("Ts", "Al", 560, Type.NOVEL));

        //books.stream().forEach(System.out::println);

        List<String> result = books.stream()
                .filter(b -> b.getType() == Type.NOVEL)
                .sorted(Comparator.comparing(Book::getPages))
                .map(Book::getAuthor).toList();
        result.forEach(System.out::println);


        //task is to group by type (result is hashmap)
        //grouping by type
        Map<Type, List<Book>> booksByType = books.stream().collect(Collectors.groupingBy(Book::getType));

        //booksByType.entrySet().stream().forEach(System.out::println);

        //finding 2 longest books (number of pages)
        List<String> longestBooks = books.stream().filter(p -> p.getPages() > 500)
                .map(Book::getTitle).limit(2).toList();
        System.out.println();
        longestBooks.forEach(System.out::println);

        System.out.println();
        books.stream().filter(b -> b.getTitle().length() > 1).forEach(System.out::println);

    }
}
