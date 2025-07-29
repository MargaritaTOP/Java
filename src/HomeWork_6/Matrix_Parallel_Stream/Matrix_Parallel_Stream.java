package HomeWork_6.Matrix_Parallel_Stream;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

public class Matrix_Parallel_Stream {
    private final int rows;
    private final int cols;
    private final int[][] data;
    // Конструктор для создания матрицы заданного размера
    public Matrix_Parallel_Stream(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.data = new int[rows][cols];
    }
    // Заполнение матрицы случайными числами (последовательный вариант)
    public void fillRandomSequential(int min, int max) {
        Random random = new Random();
        IntStream.range(0, rows)
                .forEach(i -> IntStream.range(0, cols)
                        .forEach(j -> data[i][j] = random.nextInt(max - min) + min));
    }
    // Заполнение матрицы случайными числами (параллельный вариант с ForkJoinPool)
    public void fillRandomParallel(int min, int max, ForkJoinPool pool) {
        Random random = new Random();
        pool.submit(() -> IntStream.range(0, rows)
                        .parallel() // Параллельный поток для строк
                        .forEach(i -> IntStream.range(0, cols)
                                .forEach(j -> data[i][j] = random.nextInt(max - min) + min)))
                .join();
    }
    // Получение значения элемента
    public int get(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            throw new IllegalArgumentException("Индекс вне диапазона");
        }
        return data[row][col];
    }
    // Установка значения элемента
    public void set(int row, int col, int value) {
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            throw new IllegalArgumentException("Индекс вне диапазона");
        }
        data[row][col] = value;
    }
    // Транспонирование матрицы (последовательный вариант)
    public Matrix_Parallel_Stream transposeSequential() {
        Matrix_Parallel_Stream result = new Matrix_Parallel_Stream(cols, rows);
        IntStream.range(0, rows)
                .forEach(i -> IntStream.range(0, cols)
                        .forEach(j -> result.set(j, i, data[i][j])));
        return result;
    }
    // Транспонирование матрицы (параллельный вариант с ForkJoinPool)
    public Matrix_Parallel_Stream transposeParallel(ForkJoinPool pool) {
        Matrix_Parallel_Stream result = new Matrix_Parallel_Stream(cols, rows);
        pool.submit(() -> IntStream.range(0, rows)
                        .parallel()
                        .forEach(i -> IntStream.range(0, cols)
                                .forEach(j -> result.set(j, i, data[i][j]))))
                .join();
        return result;
    }
    // Сложение двух матриц (последовательный вариант)
    public Matrix_Parallel_Stream addSequential(Matrix_Parallel_Stream other) {
        if (rows != other.rows || cols != other.cols) {
            throw new IllegalArgumentException("Матрицы должны иметь одинаковые размеры");
        }
        Matrix_Parallel_Stream result = new Matrix_Parallel_Stream(rows, cols);
        IntStream.range(0, rows)
                .forEach(i -> IntStream.range(0, cols)
                        .forEach(j -> result.set(i, j, data[i][j] + other.get(i, j))));
        return result;
    }
    // Сложение двух матриц (параллельный вариант с ForkJoinPool)
    public Matrix_Parallel_Stream addParallel(Matrix_Parallel_Stream other, ForkJoinPool pool) {
        if (rows != other.rows || cols != other.cols) {
            throw new IllegalArgumentException("Матрицы должны иметь одинаковые размеры");
        }
        Matrix_Parallel_Stream result = new Matrix_Parallel_Stream(rows, cols);
        pool.submit(() -> IntStream.range(0, rows)
                        .parallel()
                        .forEach(i -> IntStream.range(0, cols)
                                .forEach(j -> result.set(i, j, data[i][j] + other.get(i, j)))))
                .join();
        return result;
    }
    // Умножение двух матриц (последовательный вариант)
    public Matrix_Parallel_Stream multiplySequential(Matrix_Parallel_Stream other) {
        if (cols != other.rows) {
            throw new IllegalArgumentException("Количество столбцов в первой матрице должно быть равно количеству столбцов во второй матрице");
        }
        Matrix_Parallel_Stream result = new Matrix_Parallel_Stream(rows, other.cols);
        IntStream.range(0, rows)
                .forEach(i -> IntStream.range(0, other.cols)
                        .forEach(j -> result.set(i, j,
                                IntStream.range(0, cols)
                                        .map(k -> data[i][k] * other.get(k, j))
                                        .sum())));
        return result;
    }
    // Умножение двух матриц (параллельный вариант с ForkJoinPool)
    public Matrix_Parallel_Stream multiplyParallel(Matrix_Parallel_Stream other, ForkJoinPool pool) {
        if (cols != other.rows) {
            throw new IllegalArgumentException("Количество столбцов в первой матрице должно быть равно количеству столбцов во второй матрице");
        }
        Matrix_Parallel_Stream result = new Matrix_Parallel_Stream(rows, other.cols);
        pool.submit(() -> IntStream.range(0, rows)
                        .parallel()
                        .forEach(i -> IntStream.range(0, other.cols)
                                .forEach(j -> result.set(i, j,
                                        IntStream.range(0, cols)
                                                .map(k -> data[i][k] * other.get(k, j))
                                                .sum()))))
                .join();
        return result;
    }
    // Поиск минимального элемента (последовательный вариант)
    public int findMinSequential() {
        return Arrays.stream(data)
                .flatMapToInt(Arrays::stream)
                .min()
                .orElseThrow(() -> new IllegalStateException("Матрица пуста"));
    }
    // Поиск минимального элемента (параллельный вариант с ForkJoinPool)
    public int findMinParallel(ForkJoinPool pool) {
        return pool.submit(() -> Arrays.stream(data)
                        .parallel()
                        .flatMapToInt(Arrays::stream)
                        .min()
                        .orElseThrow(() -> new IllegalStateException("Матрица пуста")))
                .join();
    }
    // Поиск максимального элемента (последовательный вариант)
    public int findMaxSequential() {
        return Arrays.stream(data)
                .flatMapToInt(Arrays::stream)
                .max()
                .orElseThrow(() -> new IllegalStateException("Матрица пуста"));
    }
    // Поиск максимального элемента (параллельный вариант с ForkJoinPool)
    public int findMaxParallel(ForkJoinPool pool) {
        return pool.submit(() -> Arrays.stream(data)
                        .parallel()
                        .flatMapToInt(Arrays::stream)
                        .max()
                        .orElseThrow(() -> new IllegalStateException("Матрица пуста")))
                .join();
    }
    // Вывод матрицы в консоль (первые 3x3 для читаемости)
    public void print() {
        for (int i = 0; i < Math.min(3, rows); i++) {
            for (int j = 0; j < Math.min(3, cols); j++) {
                System.out.printf("%4d", data[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
    // Получение количества строк
    public int getRows() {
        return rows;
    }
    // Получение количества столбцов
    public int getCols() {
        return cols;
    }
    // Пример использования с измерением времени выполнения
    public static void main(String[] args) {
        // Создаем пул потоков с 4 потоками
        ForkJoinPool pool = new ForkJoinPool(4);

        // Создаем две большие матрицы 500x500
        Matrix_Parallel_Stream matrix1 = new Matrix_Parallel_Stream(500, 500);
        Matrix_Parallel_Stream matrix2 = new Matrix_Parallel_Stream(500, 500);

        // Измеряем время заполнения
        long startTime = System.nanoTime();
        matrix1.fillRandomSequential(1, 100);
        long sequentialFillTime = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        matrix2.fillRandomParallel(1, 100, pool);
        long parallelFillTime = System.nanoTime() - startTime;

        System.out.println("Fill Time (Sequential): " + sequentialFillTime / 1_000_000 + " ms");
        System.out.println("Fill Time (Parallel): " + parallelFillTime / 1_000_000 + " ms");
        System.out.println("Matrix 1 (first 3x3):");
        matrix1.print();
        System.out.println("Matrix 2 (first 3x3):");
        matrix2.print();

        // Измеряем время транспонирования
        startTime = System.nanoTime();
        Matrix_Parallel_Stream transposedSequential = matrix1.transposeSequential();
        long sequentialTransposeTime = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        Matrix_Parallel_Stream transposedParallel = matrix1.transposeParallel(pool);
        long parallelTransposeTime = System.nanoTime() - startTime;

        System.out.println("Transpose Time (Sequential): " + sequentialTransposeTime / 1_000_000 + " ms");
        System.out.println("Transpose Time (Parallel): " + parallelTransposeTime / 1_000_000 + " ms");
        System.out.println("Transposed Matrix (first 3x3):");
        transposedParallel.print();

        // Измеряем время сложения
        startTime = System.nanoTime();
        Matrix_Parallel_Stream sumSequential = matrix1.addSequential(matrix2);
        long sequentialAddTime = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        Matrix_Parallel_Stream sumParallel = matrix1.addParallel(matrix2, pool);
        long parallelAddTime = System.nanoTime() - startTime;

        System.out.println("Add Time (Sequential): " + sequentialAddTime / 1_000_000 + " ms");
        System.out.println("Add Time (Parallel): " + parallelAddTime / 1_000_000 + " ms");
        System.out.println("Sum Matrix (first 3x3):");
        sumParallel.print();

        // Измеряем время умножения
        startTime = System.nanoTime();
        Matrix_Parallel_Stream productSequential = matrix1.multiplySequential(matrix2);
        long sequentialMultiplyTime = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        Matrix_Parallel_Stream productParallel = matrix1.multiplyParallel(matrix2, pool);
        long parallelMultiplyTime = System.nanoTime() - startTime;

        System.out.println("Multiply Time (Sequential): " + sequentialMultiplyTime / 1_000_000 + " ms");
        System.out.println("Multiply Time (Parallel): " + parallelMultiplyTime / 1_000_000 + " ms");
        System.out.println("Product Matrix (first 3x3):");
        productParallel.print();

        // Измеряем время поиска минимума
        startTime = System.nanoTime();
        int minSequential = matrix1.findMinSequential();
        long sequentialMinTime = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        int minParallel = matrix1.findMinParallel(pool);
        long parallelMinTime = System.nanoTime() - startTime;

        System.out.println("Min Element (Sequential): " + minSequential + " (Time: " + sequentialMinTime / 1_000_000 + " ms)");
        System.out.println("Min Element (Parallel): " + minParallel + " (Time: " + parallelMinTime / 1_000_000 + " ms)");

        // Измеряем время поиска максимума
        startTime = System.nanoTime();
        int maxSequential = matrix1.findMaxSequential();
        long sequentialMaxTime = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        int maxParallel = matrix1.findMaxParallel(pool);
        long parallelMaxTime = System.nanoTime() - startTime;

        System.out.println("Max Element (Sequential): " + maxSequential + " (Time: " + parallelMaxTime / 1_000_000 + " ms)");
        System.out.println("Max Element (Parallel): " + maxParallel + " (Time: " + parallelMaxTime / 1_000_000 + " ms)");

        // Закрываем пул потоков
        pool.shutdown();
    }
}
