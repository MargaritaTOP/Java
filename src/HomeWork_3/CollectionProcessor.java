import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CollectionProcessor {
    public static void processCollection(ArrayList<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException("Коллекция не может быть пустой");
        }
        // Числа, делящиеся на 3 или 5 
        Predicate<Integer> divisibleBy3Or5 = num -> num % 3 == 0 || num % 5 == 0;
        List<Integer> divisibleNumbers = numbers.stream()
                .filter(divisibleBy3Or5)
                .collect(Collectors.toList());
        // Простые числа 
        List<Integer> primeNumbers = numbers.stream()
                .filter(CollectionProcessor::isPrime)
                .collect(Collectors.toList());
        // max и min знач-я 
        int max = Collections.max(numbers);
        int min = Collections.min(numbers);
        // Среднее значение 
        double average = numbers.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0);
        System.out.println("Числа, делящиеся на 3 или 5: " + divisibleNumbers);
        System.out.println("Простые числа: " + primeNumbers);
        System.out.println("Максимальное значение: " + max);
        System.out.println("Минимальное значение: " + min);
        System.out.printf("Среднее значение: %.2f\n", average);
    }
    private static boolean isPrime(int number) {
        if (number <= 1) return false;
        if (number == 2) return true;
        if (number % 2 == 0) return false;
        return IntStream.iterate(3, i -> i * i <= number, i -> i + 2)
                .noneMatch(i -> number % i == 0);
    }
}