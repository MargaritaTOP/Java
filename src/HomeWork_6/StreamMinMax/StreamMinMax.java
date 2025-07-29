package HomeWork_6.StreamMinMax;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.Optional;

public class StreamMinMax extends JFrame {
    private final JTextField inputField;
    private final JLabel resultLabel;
    public StreamMinMax() {
        // Настройка окна
        setTitle("Min-Max Калькулятор");
        setSize(500, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // окно по центру
        setLayout(new BorderLayout());
        // Панель ввода
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Введите числа (через запятую): "));
        inputField = new JTextField(20);
        inputPanel.add(inputField);
        add(inputPanel, BorderLayout.NORTH);
        // Кнопка
        JButton calcButton = new JButton("Найти минимум и максимум");
        calcButton.addActionListener(this::onCalculate);
        add(calcButton, BorderLayout.CENTER);
        // Метка для результата
        resultLabel = new JLabel("Результат появится здесь", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        resultLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        add(resultLabel, BorderLayout.SOUTH);
        // Делаем окно видимым
        setVisible(true);
    }
    // Обработчик нажатия кнопки
    private void onCalculate(ActionEvent e) {
        String input = inputField.getText().trim();
        if (input.isEmpty()) {
            resultLabel.setText("Ошибка: введите числа");
            return;
        }
        try {
            // Парсим строку: разделяем по запятой, убираем пробелы, конвертируем в int
            int[] numbers = Arrays.stream(input.split(","))
                    .map(String::trim)
                    .mapToInt(Integer::parseInt)
                    .toArray();
            if (numbers.length == 0) {
                resultLabel.setText("Нет чисел для анализа");
                return;
            }
            // Используем Stream API для поиска min и max
            Optional<Integer> min = Arrays.stream(numbers).min().stream().boxed().findFirst();
            Optional<Integer> max = Arrays.stream(numbers).max().stream().boxed().findFirst();
            // Выводим результат
            min.ifPresentOrElse(
                    minValue -> max.ifPresent(maxValue ->
                            resultLabel.setText(String.format("Минимум: %d, Максимум: %d", minValue, maxValue))
                    ),
                    () -> resultLabel.setText("Ошибка при вычислении")
            );
        } catch (NumberFormatException ex) {
            resultLabel.setText("Ошибка: некорректные числа");
        }
    }
    // Точка входа
    public static void main(String[] args) {
        // Запуск GUI в потоке событий (EDT)
        SwingUtilities.invokeLater(StreamMinMax::new);
    }
}
