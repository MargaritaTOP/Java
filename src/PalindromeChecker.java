import java.util.Scanner;

public class PalindromeChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите фразу для проверки на палиндром:");
        String phrase = scanner.nextLine();
        String processed = phrase.replaceAll("[^а-яА-Яa-zA-Z]", "").toLowerCase();
        boolean isPalindrome = processed.contentEquals(new StringBuilder(processed).reverse());
        System.out.println("Фраза \"" + phrase + "\" " +
                (isPalindrome ? "является палиндромом" : "не является палиндромом"));
        scanner.close();
    }
}