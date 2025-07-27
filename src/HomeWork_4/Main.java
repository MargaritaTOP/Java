package HomeWork_4;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Система авторизации ===");
        System.out.print("Введите логин: ");
        String login = scanner.nextLine();
        System.out.println("\nТребования к паролю:");
        System.out.println("1. Длина не менее 8 символов");
        System.out.println("2. Должен содержать символы как минимум из 3 групп:");
        System.out.println("   - Латинские буквы нижнего регистра (a-z)");
        System.out.println("   - Латинские буквы верхнего регистра (A-Z)");
        System.out.println("   - Цифры (0-9)");
        System.out.println("   - Знак подчеркивания (_)");
        System.out.print("\nВведите пароль: ");
        String password = scanner.nextLine();
        System.out.print("Подтвердите пароль: ");
        String confirmPassword = scanner.nextLine();
        if (Authorization.validate(login, password, confirmPassword)) {
            System.out.println("\nАвторизация успешна! Добро пожаловать, " + login + "!");
        } else {
            System.out.println("\nАвторизация не удалась. Пожалуйста, исправьте ошибки и попробуйте снова.");
        }
        scanner.close();
    }
}