package unit.java8.streams;

import org.intellij.lang.annotations.Language;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.Set;
import java.util.stream.Collectors;

public class StreamExamples {

    @Test
    public void simpleStreamExample() throws Exception {
        List<String> dogs = Arrays.asList("Vizsla", "Lab", "Golden", "GSP", "Poodle", "Yorkie", "Mutt");

        dogs.stream()
                .forEach(System.out::println);
    }

    @Test
    public void parallelStreamExample() throws Exception {
        List<String> dogs = Arrays.asList("Vizsla", "Lab", "Golden", "GSP", "Poodle", "Yorkie", "Mutt");

        dogs.parallelStream()
                .forEach(System.out::println);
    }

    @Test
    public void mapStreamExample() throws Exception {
        List<String> dogs = Arrays.asList("Vizsla", "Lab", "Golden", "GSP", "Poodle", "Yorkie", "Mutt");
        dogs.stream()
                .map(String::length)
                .forEach(System.out::println);
    }

    @Test
    public void filterStreamExample() throws Exception {
        List<String> dogs = Arrays.asList("Vizsla", "Lab", "Golden", "GSP", "Poodle", "Yorkie", "Mutt");
        dogs.stream()
                .filter(s -> s.length() == 6 )
                .forEach(System.out::println);
    }

    @Test
    public void filterAndLimitStreamExample() throws Exception {
        List<String> dogs = Arrays.asList("Vizsla", "Lab", "123456", "GSP", "123456", "Yorkie", "Mutt");
        dogs.stream()
                .filter(s -> s.length() == 6 )
                .limit(2)
                .forEach(System.out::println);
    }

    @Test
    public void filterAndSortStreamExample() throws Exception {
        List<String> dogs = Arrays.asList("Vizsla", "Lab", "Golden", "GSP", "Poodle", "Yorkie", "Mutt");
        dogs.stream()
                .filter(s -> s.length() == 6 )
                .sorted()
                .forEach(System.out::println);
    }

    @Test
    public void filterAndSortStreamWithCollectorExample() throws Exception {
        List<String> dogs = Arrays.asList("Vizsla", "Lab", "Golden", "GSP", "Poodle", "Yorkie", "Mutt");

        String dogString =dogs.stream()
                .filter(s -> s.length() == 6 )
                .sorted()
                .collect(Collectors.joining(", "));

        System.out.println(dogString);
    }

    @Test
    public void filterAndSortStreamWithFunctionalComp() throws Exception {
        List<String> dogs = Arrays.asList("Vizsla", "Lab", "Golden", "GSP", "Poodle", "Yorkie", "Mutt");

        String dogString =dogs.stream()
                .filter(s -> s.length() == 6 )
                .limit(3)
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.joining(", "));

        System.out.println(dogString);
    }

    @Test
    public void testStats() throws Exception {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        IntSummaryStatistics statistics = numbers.stream()
                .mapToInt(x -> x) //simple operation
                .summaryStatistics();

        System.out.println(statistics.toString());
    }

    @Test
    public void testGetMax() throws Exception {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        OptionalInt max = numbers.stream()
                .mapToInt(x -> x) //simple operation
                .max();

        System.out.println("Max: " + max.getAsInt());
    }

    @Test
    public void testSum() throws Exception {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        int numbersSum = numbers.stream()
                .mapToInt(x -> x) //simple operation
                .sum();

        System.out.println("Sum: " + numbersSum);
    }

    @Test
    public void testSumCollector() throws Exception {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        Integer numbersSum = numbers.stream()
                .collect(Collectors.summingInt(value -> value));

        System.out.println("Sum: " + numbersSum);
    }

    @Test
    public void testGroupingBy() throws Exception {
        List<String> dogs = Arrays.asList("Vizsla", "Lab", "Golden", "GSP", "Poodle", "Yorkie", "Mutt");

        Map<Integer, Set<String>> groupedMap = dogs.stream()
                .collect(Collectors.groupingBy(String::length, Collectors.toSet()));

        groupedMap.entrySet().stream()
                .forEach(System.out::println);
    }

    @Test
    public void testFlatMap() throws Exception {
        List<List<Integer>> listOfLists = Arrays.asList(Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6));

        List<Integer> numbers = listOfLists.stream()
                .flatMap(Collection::stream) //stream numbers of each list, 'flattening' stream
                .collect(Collectors.toList());

        numbers.forEach(System.out::println);
    }

    @Test
    public void testReduction() throws Exception {
        List<String> dogs = Arrays.asList("Vizsla", "Lab", "Golden", "GSP", "Poodle", "Yorkie", "Mutt");

        String reducedString = dogs.stream()
                .reduce((a, b) -> a + " - " + b)
                .get();

        System.out.println(reducedString);
    }

    @Test
    public void testArrayToStream() throws Exception {

        @Language("JSON") String jsonWithArrays = "{\n" +
                "  \"simple-object\": \"String with words\",\n" +
                "  \"simple-array\": [\"first elem\", \"second elem\", \"thrid elem\"],\n" +
                "  \"complex-array\": [\n" +
                "    { \"obj-array-1\": [\"first elem 1\", \"second elem 1\", \"thrid elem\"] },\n" +
                "    { \"obj-array-2\": [\"first elem 2\", \"second elem 2\", \"thrid elem\"] },\n" +
                "    { \"obj-array-3\": [\"first elem 3\", \"second elem 3\", \"thrid elem\"] }]\n" +
                "}";

        List<String> example = Arrays.asList(jsonWithArrays);


    }

}
