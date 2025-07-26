package The_House_That_Jack_Built;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
public class WordCounter {
    public static void main(String[] args) {
        System.out.println("Рабочая директория: " + System.getProperty("user.dir"));
        // Пути к файлам
        String fileIn = "in.txt";
        String fileOut = "out.txt";        
        // Проверка существования файла
        File inputFile = new File(fileIn);
        if (!inputFile.exists()) {
            System.out.println("Файл не найден");
            return;
        }        
        // Чтение файла
        String text;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileIn))) {
            text = reader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
            return;
        }        
        // Разделение текста на слова
        String[] words = text.split("[\\s.,;:?!()\n\r]+");
        words = Arrays.stream(words).filter(w -> !w.isEmpty()).toArray(String[]::new);    
        // Подсчёт общего количества слов
        int totalWords = words.length;        
        // Подсчёт уникальных слов и их вхождений
        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }        
        // Подсчёт количества уникальных слов
        int uniqueWordsCount = wordCount.size();       
        // Запись результатов в файл
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileOut, false))) {
            // Вывод статистики в виде таблицы
            System.out.println("\nСтатистика по словам");
            writer.write("Статистика по словам:\n");
            System.out.println("\t\t Слово:\t    Кол-во:");
            writer.write("Слово:       Кол-во:\n");
            int i = 1;
            // Сортировка по убыванию частот
            List<Map.Entry<String, Integer>> sortedEntries = wordCount.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toList());
            // Вывод результатов
            for (Map.Entry<String, Integer> entry : sortedEntries) {
                System.out.printf("%2d.\t%12s\t\t\t\t\t%d%n", i, entry.getKey(), entry.getValue());
                writer.write(String.format("%2d. %12s %3d%n", i, entry.getKey(), entry.getValue()));
                i++;
            }
            // Вывод 
            System.out.printf("Всего слов: %d", totalWords);
            writer.write(String.format("Всего слов: %d", totalWords));
            System.out.printf("\tиз них уникальных слов: %d%n", uniqueWordsCount);
            writer.write(String.format(" из них уникальных слов: %d%n", uniqueWordsCount));
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }
}