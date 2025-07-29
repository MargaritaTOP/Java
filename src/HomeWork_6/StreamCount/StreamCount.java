package HomeWork_6.StreamCount;

import java.util.OptionalDouble;
import java.util.stream.IntStream;

public class StreamCount {
    public static void main(String[] args) {
        System.out.println("=== АНАЛИЗ ЧИСЕЛ ОТ -5 ДО 5 ===\n");
        // Исходные данные
        int[] numbers = {-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5};
        // 1. Количество положительных чисел
        long count = IntStream.of(numbers)
                .filter(w -> w > 0)
                .count();
        System.out.println("1. Количество положительных чисел: " + count);
        // 2. Среднее значение чисел, меньших 2 (с безопасной обработкой)
        OptionalDouble averageOpt = IntStream.of(numbers)
                .filter(w -> w < 2)
                .average();
        averageOpt.ifPresentOrElse(
                avg -> System.out.println("2. Среднее чисел < 2: " + String.format("%.2f", avg)),
                () -> System.out.println("2. Нет чисел для вычисления среднего")
        );
        // 3. Сумма всех чисел
        int sum = IntStream.of(numbers).sum();
        System.out.println("3. Сумма всех чисел: " + sum);
        // 4. Минимум и максимум
        OptionalDouble min = IntStream.of(numbers).min().stream().average(); // Обходной путь для min()
        OptionalDouble max = IntStream.of(numbers).max().stream().average();
        min.ifPresent(m -> System.out.println("4. Минимум: " + (int) m));
        max.ifPresent(m -> System.out.println("4. Максимум: " + (int) m));
        // 5. Чётные числа
        System.out.print("5. Чётные числа: ");
        IntStream.of(numbers)
                .filter(n -> n % 2 == 0)
                .forEach(n -> System.out.print(n + " "));
        System.out.println("\n");
        // 6. Квадраты чисел
        System.out.print("6. Квадраты чисел: ");
        IntStream.of(numbers)
                .map(n -> n * n)
                .forEach(n -> System.out.print(n + " "));
        System.out.println("\n");
        // 7. Количество чётных чисел
        long evenCount = IntStream.of(numbers).filter(n -> n % 2 == 0).count();
        System.out.println("7. Количество чётных чисел: " + evenCount);
        // 8. Произведение положительных чисел (с BigInteger для защиты от переполнения)
        long product = IntStream.of(numbers)
                .filter(n -> n > 0)
                .reduce(1, (a, b) -> a * b);
        System.out.println("8. Произведение положительных чисел: " + product);
        // 9. Все числа, по модулю больше 3
        System.out.print("9. Числа, |n| > 3: ");
        IntStream.of(numbers)
                .filter(n -> Math.abs(n) > 3)
                .forEach(n -> System.out.print(n + " "));
        System.out.println("\n");
        // 10. Есть ли хотя бы одно отрицательное число?
        boolean hasNegative = IntStream.of(numbers).anyMatch(n -> n < 0);
        System.out.println("10. Есть отрицательные числа: " + hasNegative);
        // 11. Все ли числа в диапазоне [-5, 5]?
        boolean allInRange = IntStream.of(numbers).allMatch(n -> n >= -5 && n <= 5);
        System.out.println("11. Все числа в диапазоне [-5, 5]: " + allInRange);
        // 12. Первое положительное число
        OptionalDouble firstPositive = IntStream.of(numbers)
                .filter(n -> n > 0)
                .findFirst()
                .stream()
                .mapToDouble(Double::valueOf)
                .findAny();
        firstPositive.ifPresent(fp -> System.out.println("12. Первое положительное число: " + (int) fp));
        System.out.println("\n=== КОНЕЦ ===");
    }
}
