package HomeWork_6.Matrix_Stream;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Matrix_Stream {
    private final int rows;
    private final int cols;
    private final int[][] data;

    // Конструктор для создания матрицы заданного размера
    public Matrix_Stream(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.data = new int[rows][cols];
    }
    // Заполнение матрицы случайными числами в диапазоне [min, max)
    public void fillRandom(int min, int max) {
        Random random = new Random();
        IntStream.range(0, rows)
                .forEach(i -> IntStream.range(0, cols)
                        .forEach(j -> data[i][j] = random.nextInt(max - min) + min));
    }
    // Получение значения элемента
    public int get(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        return data[row][col];
    }
    // Установка значения элемента
    public void set(int row, int col, int value) {
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        data[row][col] = value;
    }
    // Транспонирование матрицы с использованием Stream API
    public Matrix_Stream transpose() {
        Matrix_Stream result = new Matrix_Stream(cols, rows);
        IntStream.range(0, rows)
                .forEach(i -> IntStream.range(0, cols)
                        .forEach(j -> result.set(j, i, data[i][j])));
        return result;
    }
    // Сложение двух матриц
    public Matrix_Stream add(Matrix_Stream other) {
        if (rows != other.rows || cols != other.cols) {
            throw new IllegalArgumentException("Matrices must have the same dimensions");
        }
        Matrix_Stream result = new Matrix_Stream(rows, cols);
        IntStream.range(0, rows)
                .forEach(i -> IntStream.range(0, cols)
                        .forEach(j -> result.set(i, j, data[i][j] + other.get(i, j))));
        return result;
    }
    // Умножение двух матриц
    public Matrix_Stream multiply(Matrix_Stream other) {
        if (cols != other.rows) {
            throw new IllegalArgumentException("Number of columns in first matrix must equal number of rows in second matrix");
        }
        Matrix_Stream result = new Matrix_Stream(rows, other.cols);
        IntStream.range(0, rows)
                .forEach(i -> IntStream.range(0, other.cols)
                        .forEach(j -> result.set(i, j,
                                IntStream.range(0, cols)
                                        .map(k -> data[i][k] * other.get(k, j))
                                        .sum())));
        return result;
    }
    // Поиск минимального элемента
    public int findMin() {
        return Arrays.stream(data)
                .flatMapToInt(Arrays::stream)
                .min()
                .orElseThrow(() -> new IllegalStateException("Matrix is empty"));
    }
    // Поиск максимального элемента
    public int findMax() {
        return Arrays.stream(data)
                .flatMapToInt(Arrays::stream)
                .max()
                .orElseThrow(() -> new IllegalStateException("Matrix is empty"));
    }
    // Вывод матрицы в консоль
    public void print() {
        Arrays.stream(data)
                .forEach(row -> {
                    Arrays.stream(row)
                            .forEach(element -> System.out.printf("%4d", element));
                    System.out.println();
                });
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
    // main
    public static void main(String[] args) {
        // Создаем две матрицы 3x3
        Matrix_Stream matrix1 = new Matrix_Stream(3, 3);
        Matrix_Stream matrix2 = new Matrix_Stream(3, 3);
        // Заполняем матрицы случайными числами от 1 до 10
        matrix1.fillRandom(1, 10);
        matrix2.fillRandom(1, 10);
        // Выводим матрицы
        System.out.println("Matrix 1:");
        matrix1.print();
        System.out.println("Matrix 2:");
        matrix2.print();
        // Транспонирование матрицы 1
        System.out.println("Transposed Matrix 1:");
        Matrix_Stream transposed = matrix1.transpose();
        transposed.print();
        // Сложение матриц
        System.out.println("Matrix 1 + Matrix 2:");
        Matrix_Stream sum = matrix1.add(matrix2);
        sum.print();
        // Умножение матриц
        System.out.println("Matrix 1 * Matrix 2:");
        Matrix_Stream product = matrix1.multiply(matrix2);
        product.print();
        // Поиск минимального и максимального элемента в матрице 1
        System.out.println("Minimum element in Matrix 1: " + matrix1.findMin());
        System.out.println("Maximum element in Matrix 1: " + matrix1.findMax());
    }
}