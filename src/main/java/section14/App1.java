package section14;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class App1 {
    public static void main(String[] args) {
        int[] nums = {1,5,3,-2,9,12};
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        System.out.println(sum);

        System.out.println(Arrays.stream(nums).sum());
        // :: operator or method reference
        Arrays.stream(nums).forEach(System.out::println);

        IntStream.range(0,11).forEach(a -> System.out.print(a + " "));
        System.out.println();
        IntStream.range(0,11).filter(a -> a > 5).forEach(a -> System.out.print(a + " "));

        // video 85
        System.out.println("\n");
        String[] names = {"Adam", "Daniel", "Martha", "Kevin", "Ben", "Joe", "Brad", "Susan"};
        Stream.of(names).sorted(Comparator.reverseOrder()).forEach(s -> System.out.print(s + " "));
        System.out.println();
        Stream.of(names).filter(s -> s.startsWith("B")).forEach(s -> System.out.print(s + " "));
    }
}
