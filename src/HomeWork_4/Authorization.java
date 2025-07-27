package HomeWork_4;

import java.util.regex.Pattern;
public class Authorization {
    public static boolean validate(String login, String password, String confirmPassword) {
        try {
            if (login.length() < 5) {
                throw new IllegalArgumentException("Логин должен быть не менее 5 символов");
            }
            if (!Pattern.matches("^[a-zA-Z0-9_]+$", login)) {
                throw new IllegalArgumentException("Логин должен содержать только латинские буквы, цифры и знак подчеркивания");
            }
            if (password.length() < 8) {
                throw new IllegalArgumentException("Пароль должен быть не менее 8 символов");
            }
            if (!password.equals(confirmPassword)) {
                throw new IllegalArgumentException("Пароль и подтверждение пароля не совпадают");
            }
            int groupsCount = 0;
            if (Pattern.matches(".*[a-z].*", password)) groupsCount++;
            if (Pattern.matches(".*[A-Z].*", password)) groupsCount++;
            if (Pattern.matches(".*[0-9].*", password)) groupsCount++;
            if (Pattern.matches(".*_.*", password)) groupsCount++;
            if (groupsCount < 3) {
                throw new IllegalArgumentException(
                        """
                                Пароль должен содержать символы как минимум из 3 групп:
                                1. Латинские буквы нижнего регистра (a-z)
                                2. Латинские буквы верхнего регистра (A-Z)
                                3. Цифры (0-9)
                                4. Знак подчеркивания (_)"""
                );
            }
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
            return false;
        }
    }
}