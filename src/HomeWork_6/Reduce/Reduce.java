package HomeWork_6.Reduce;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;


public class Reduce {
    public static void main(String[] args) {
        System.out.println("=== РАБОТА С ПОТОКАМИ И ОПЕРАЦИЯМИ REDUCE ===\n");
        // 1. Исходные данные
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        // Пустой список для демонстрации поведения min() на пустом потоке
        List<Integer> emptyList = Collections.emptyList();
        List<Integer> bigNumbers = Arrays.asList(10, 20, 30, 40, 50);
        // 2. Перемножение чисел — с защитой от переполнения через BigInteger
        System.out.println("1. Перемножение чисел: " + numbers);
        Optional<Integer> resultInt = numbers.stream().reduce((x, y) -> x * y);
        resultInt.ifPresentOrElse(
                r -> System.out.println("   Результат (int): " + r),
                () -> System.out.println("   Нет данных для вычисления")
        );
        // Защита от переполнения — используем BigInteger
        Optional<BigInteger> resultBig = numbers.stream()
                .map(BigInteger::valueOf)
                .reduce(BigInteger::multiply);
        resultBig.ifPresent(r -> System.out.println("   Результат (BigInteger): " + r));
        System.out.println();
        // 3. Минимум, максимум, сумма, среднее
        System.out.println("2. Агрегатные операции для списка: " + bigNumbers);
        OptionalInt min = bigNumbers.stream().mapToInt(Integer::intValue).min();
        OptionalInt max = bigNumbers.stream().mapToInt(Integer::intValue).max();
        OptionalDouble avg = bigNumbers.stream().mapToDouble(Integer::doubleValue).average();
        int sum = bigNumbers.stream().mapToInt(Integer::intValue).sum();
        min.ifPresent(value -> System.out.println("   Минимум: " + value));
        max.ifPresent(value -> System.out.println("   Максимум: " + value));
        System.out.println("   Сумма: " + sum);
        avg.ifPresent(value -> System.out.println("   Среднее: " + String.format("%.2f", value)));
        System.out.println();
        // 4. Поиск минимума в пустом списке
        System.out.println("3. Поиск минимума в пустом списке:");
        Optional<Integer> emptyMin = emptyList.stream().min(Integer::compare);
        emptyMin.ifPresentOrElse(
                System.out::println,
                () -> System.out.println("   Значение не найдено — список пуст")
        );
        System.out.println();
        // 5. Фильтрация и преобразования
        System.out.println("4. Чётные числа, возведённые в квадрат:");
        List<Integer> squaredEvens = numbers.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * n)
                .toList();
        System.out.println("   Результат: " + squaredEvens);
        System.out.println();
        // 6. Вычисление факториала через reduce
        int n = 6;
        System.out.println("5. Факториал " + n + "! через reduce:");
        Optional<BigInteger> factorial = IntStream.rangeClosed(1, n)
                .mapToObj(BigInteger::valueOf)
                .reduce(BigInteger::multiply);
        factorial.ifPresent(f -> System.out.println("   " + n + "! = " + f));
        System.out.println();
        // 7. Вывод всех промежуточных шагов умножения
        System.out.println("6. Пошаговое перемножение:");
        AtomicInteger acc = new AtomicInteger(1);
        numbers.forEach(num -> {
            int prev = acc.get();
            acc.updateAndGet(value -> value * num);
            System.out.printf("   %d × %d = %d%n", prev, num, acc.get());
        });
        System.out.println();
        // 8. Обработка потенциально опасных операций
        System.out.println("7. Проверка на переполнение int:");
        List<Integer> riskyNumbers = Arrays.asList(100000, 100000, 100000); // может переполнить int
        long product = riskyNumbers.stream()
                .mapToLong(Integer::longValue)
                .reduce(1, (a, b) -> a * b);
        System.out.println("   Произведение (long): " + product);
        if (product > Integer.MAX_VALUE) {
            System.out.println("Результат больше, чем Integer.MAX_VALUE — используйте long/BigInteger");
        }
        System.out.println("\n=== КОНЕЦ ПРОГРАММЫ ===");
    }
}