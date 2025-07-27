package Generics;

// Объявление generic класса с параметром типа T
public class class_Generic<T> {
    private T content;  // Поле типа T
    // Generic конструктор
    public class_Generic(T content) {
        this.content = content;
    }
    // Generic метод
    public T getContent() {
        return content;
    }
    public void setContent(T content) {
        this.content = content;
    }
    public static void main(String[] args) {
        // Создание Box для String
        class_Generic<String> stringBox = new class_Generic<>("Hello Generics");
        System.out.println("String box contains: " + stringBox.getContent());
        // Создание Box для Integer
        class_Generic<Integer> intBox = new class_Generic<>(42);
        System.out.println("Integer box contains: " + intBox.getContent());
    }
}