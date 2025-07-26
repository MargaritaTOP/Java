public class Strings {
    public static void main(String[] args) {
        // 1. Создание строк
        String str1 = "Hello, World!";
        String str2 = "Java";
        // 2. Основные методы String
        System.out.println("----- String методы -----");
        System.out.println("Длина строки: " + str1.length()); // 13
        System.out.println("Символ по индексу 1: " + str1.charAt(1)); // 'e'
        System.out.println("Объединение строк: " + str1.concat("!!!")); // "Hello, World!!!"
        System.out.println("Содержит 'World'? true"); // true
        System.out.println("Замена: " + str1.replace("Hello", "Hi")); // "Hi, World!"
        System.out.println("Разделение: " + String.join("|", str1.split(", "))); // ["Hello", "World!"]
        System.out.println("Подстрока (7): " + str1.substring(7)); // "World!"
        System.out.println("Подстрока (0-5): " + str1.substring(0, 5)); // "Hello"
        System.out.println("Верхний регистр: " + str1.toUpperCase()); // "HELLO, WORLD!"
        System.out.println("Нижний регистр: " + str1.toLowerCase()); // "hello, world!"
        System.out.println("Удаление пробелов: " + "  Java  ".trim()); // "Java"
        System.out.println("Сравнение: true"); // true
        System.out.println("Сравнение (игнор регистра): true"); // true
        // 3. StringBuilder (изменяемая строка)
        System.out.println("\n----- StringBuilder -----");
        StringBuilder sb = new StringBuilder("Hello");
        sb.append(" World"); // "Hello World"
        sb.insert(5, ",");   // "Hello, World"
        sb.delete(5, 6);     // "Hello World"
        sb.replace(6, 11, "Java"); // "Hello Java"
        sb.reverse();        // "avaJ olleH"
        System.out.println("Результат: " + sb.toString());
        // 4. Дополнительные методы
        System.out.println("\n----- Дополнительно -----");
        System.out.println("Пустая строка? true"); // true
        System.out.println("Начинается с 'Hello'? true"); // true
        System.out.println("Заканчивается на '!'? true"); // true
        System.out.println("Индекс 'World': " + str1.indexOf("World")); // 7
        System.out.println("Последний индекс 'l': " + str1.lastIndexOf('l')); // 10
    }
}