package section14;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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



        System.out.println();
        books.stream().filter(b -> b.getTitle().length() > 1).forEach(System.out::println);


        List<String> titles = new ArrayList<>();
//        for (Book b : books) {
//            titles.add(b.getTitle());
//        }
        //inherently sequential
        //no parallelization
        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) {
            titles.add(iterator.next().getTitle());
        }

        //stream API - internal iteration
        //can make parallel quite easily
        List<String> titles2 = books.stream().map(Book::getTitle).collect(Collectors.toList());
        titles2.forEach(System.out::println);


        System.out.println();
        //finding 2 longest books (number of pages)
        //short-circuiting and loop fusion
        List<String> longestBooks = books.stream()
                .filter(p -> {
                    System.out.println("Filtering " + p.getTitle());
                    return p.getPages() > 500;
                })
                .map(b -> {
                    System.out.println("Mapping " + b.getTitle());
                    return b.getTitle();
                })
                .limit(2)
                .toList();
        System.out.println();
        longestBooks.forEach(System.out::println);


        System.out.println();
        //map() and flatMap() are similar to selecting a column in SQL
        //transform original values as we want
        //number of characters in every word
        List<String> words = Arrays.asList("Adam", "Ana", "Daniel");
        List<Integer> lengths = words.stream().map(String::length).toList();
        lengths.forEach(System.out::println);

        System.out.println();
        //create a list containing the squared values
        List<Integer> nums = Arrays.asList(1,2,3,4);

        nums.stream().map(n -> n * n).toList().forEach(System.out::println);
        System.out.println();
        //flatMap()
        //"hello" "shell" - get all the unique characters (h, e, l, o, s)
        String[] arr = {"hello", "shell"};
        Arrays.stream(arr)
                .map(w -> w.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .toList()
                .forEach(System.out::println);

        System.out.println();
        List<Integer> list1 = Arrays.asList(1,2,3), list2 = Arrays.asList(4,5);
        List<List<Integer>> list = new ArrayList<>();
        list.add(list1);
        list.add(list2);

        List<List<Integer>> pairs = list1.stream()
                .flatMap(i -> list2.stream().map(j -> Arrays.asList(i, j)))
                .toList();
        pairs.forEach(System.out::println);


        System.out.println();
        System.out.println(books.stream().count());
        List<Integer> nums1 = Arrays.asList(1,2,3,4);
        System.out.println();
//        Optional<Integer> res = nums1.stream().reduce(Integer::max);
//        res.ifPresent(System.out::println);
        books.stream().map(Book::getPages).reduce(Integer::max).ifPresent(System.out::println);
        System.out.println();

        //we want to get the longest book
        books.stream()
                .reduce((a, b) -> a.getPages() > b.getPages() ? a : b)
                .ifPresent(System.out::println);

        //sum all pages of all books
        System.out.println();
        int total = books.stream().mapToInt(Book::getPages).sum();
        System.out.println("total: " + total);

        IntStream s = books.stream().mapToInt(Book::getPages);
        //boxes back to Stream type
        Stream<Integer> ss = s.boxed();


        System.out.println();
        //OptionalDouble, OptionalLong, OptionalInt
        OptionalInt max = books.stream().mapToInt(Book::getPages).max();
        max.ifPresent(System.out::println);
        System.out.println(max.orElse(0));

        //allMatch(), noneMatch(), findFirst(), findAny()

        boolean res1 = books.stream().allMatch(b -> b.getPages() > 2000);
        System.out.println(res1);

        res1 = books.stream().noneMatch(b -> b.getPages() > 100);
        System.out.println(res1);

        books.stream()
                .filter(b -> b.getType() == Type.HISTORY)
                .findAny()
                .ifPresent(System.out::println);

        books.stream()
                .filter(b -> b.getType() == Type.HISTORY)
                .findFirst()
                .ifPresent(System.out::println);
    }
}
