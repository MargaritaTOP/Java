package HomeWork_6.Array_Collection_Proc_Stream;

import java.util.Arrays;

public class Array_Proc_Stream {
    // Максимальный предел для решета Эратосфена
    private static final int MAX_PRECOMPUTED = 100; // Уменьшен до 100, так как Main использует bound=100
    // Массив для хранения простых чисел (true = простое, false = не простое)
    private static final boolean[] isPrimePrecomputed = new boolean[MAX_PRECOMPUTED + 1];

    // Инициализация решета Эратосфена
    static {
        Arrays.fill(isPrimePrecomputed, true);
        isPrimePrecomputed[0] = isPrimePrecomputed[1] = false;
        for (int i = 2; i * i <= MAX_PRECOMPUTED; i++) {
            if (isPrimePrecomputed[i]) {
                for (int j = i * i; j <= MAX_PRECOMPUTED; j += i) {
                    isPrimePrecomputed[j] = false;
                }
            }
        }
    }

    // Числа, делящиеся на 3 или 5
    public static int[] getDivisibleBy3Or5(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Массив не может быть null или пустым");
        }
        return Arrays.stream(array)
                .filter(Array_Proc_Stream::isDivisibleBy3Or5)
                .toArray();
    }

    // Простые числа
    public static int[] getPrimes(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Массив не может быть null или пустым");
        }
        return Arrays.stream(array)
                .filter(Array_Proc_Stream::isPrime)
                .toArray();
    }

    // Максимальный элемент
    public static int getMax(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Массив не может быть null или пустым");
        }
        return Arrays.stream(array)
                .max()
                .orElseThrow(() -> new IllegalStateException("Не удалось найти максимум в массиве"));
    }

    // Минимальный элемент
    public static int getMin(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Массив не может быть null или пустым");
        }
        return Arrays.stream(array)
                .min()
                .orElseThrow(() -> new IllegalStateException("Не удалось найти минимум в массиве"));
    }

    // Среднее арифметическое
    public static double getAverage(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Массив не может быть null или пустым");
        }
        return Arrays.stream(array)
                .average()
                .orElseThrow(() -> new IllegalStateException("Не удалось вычислить среднее для массива"));
    }

    // Проверка делимости на 3 или 5
    private static boolean isDivisibleBy3Or5(int number) {
        return number % 3 == 0 || number % 5 == 0;
    }

    // Проверка на простоту числа с использованием решета Эратосфена
    private static boolean isPrime(int number) {
        if (number <= MAX_PRECOMPUTED) {
            return number >= 0 && isPrimePrecomputed[number];
        }
        if (number <= 1) {
            return false;
        }
        if (number == 2) {
            return true;
        }
        if (number % 2 == 0) {
            return false;
        }
        for (int i = 3; i * i <= number; i += 2) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
