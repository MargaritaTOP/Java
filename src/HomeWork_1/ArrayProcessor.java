package HomeWork_1;

import java.util.Arrays;

public class ArrayProcessor {
    public static int[] getDivisibleBy3Or5(int[] array) {
        int[] temp = new int[array.length];
        int count = 0;
        for (int num : array) {
            if (isDivisibleBy3Or5(num)) {
                temp[count++] = num;
            }
        }
        return Arrays.copyOf(temp, count);
    }

    public static int[] getPrimes(int[] array) {
        int[] temp = new int[array.length];
        int count = 0;
        for (int num : array) {
            if (isPrime(num)) {
                temp[count++] = num;
            }
        }
        return Arrays.copyOf(temp, count);
    }

    public static int getMax(int[] array) {
        int max = array[0];
        for (int num : array) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    public static int getMin(int[] array) {
        int min = array[0];
        for (int num : array) {
            if (num < min) {
                min = num;
            }
        }
        return min;
    }

    public static double getAverage(int[] array) {
        int sum = 0;
        for (int num : array) {
            sum += num;
        }
        return (double) sum / array.length;
    }

    private static boolean isDivisibleBy3Or5(int number) {
        return number % 3 == 0 || number % 5 == 0;
    }

    private static boolean isPrime(int number) {
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