import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Date_and_Time {
    public static void main(String[] args) {
        // 1. Текущая дата и время
        System.out.println("----- Текущие дата и время -----");
        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();
        LocalDateTime currentDateTime = LocalDateTime.now();
        System.out.println("Сегодня: " + today);
        System.out.println("Сейчас: " + now);
        System.out.println("Дата и время: " + currentDateTime);
        // 2. Создание конкретной даты/времени
        System.out.println("\n----- Указанные дата и время -----");
        LocalDate newYearDate = LocalDate.of(2023, Month.DECEMBER, 31);
        LocalTime midnight = LocalTime.of(23, 59, 59);
        LocalDateTime newYear = LocalDateTime.of(newYearDate, midnight);
        System.out.println("Новый год: " + newYear);
        // 3. Парсинг строки в дату
        System.out.println("\n----- Парсинг даты -----");
        LocalDate parsedDate = LocalDate.parse("2023-12-31");
        LocalDateTime parsedDateTime = LocalDateTime.parse("2023-12-31T23:59:59");
        System.out.println("Дата из строки: " + parsedDate);
        System.out.println("Дата и время из строки: " + parsedDateTime);
        // 4. Операции с датами
        System.out.println("\n----- Операции с датами -----");
        LocalDate tomorrow = today.plusDays(1);
        LocalDate nextMonth = today.plusMonths(1);
        LocalDate previousYear = today.minusYears(1);
        System.out.println("Завтра: " + tomorrow);
        System.out.println("Через месяц: " + nextMonth);
        System.out.println("Год назад: " + previousYear);
        // 5. Сравнение дат
        System.out.println("\n----- Сравнение дат -----");
        System.out.println("Завтра после сегодня? " + tomorrow.isAfter(today)); // true
        System.out.println("Сегодня равно сейчас? " + today.equals(LocalDate.now())); // true
        // 6. Период и продолжительность
        System.out.println("\n----- Период и Duration -----");
        Period period = Period.between(today, newYearDate);
        System.out.printf("До Нового года: %d месяцев, %d дней\n",
                period.getMonths(), period.getDays());
        Duration duration = Duration.between(LocalTime.now(), midnight);
        System.out.println("До полуночи: " + duration.toHours() + " часов");
        // 7. Форматирование даты
        System.out.println("\n----- Форматирование -----");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        String formattedDate = currentDateTime.format(formatter);
        System.out.println("Форматированная дата: " + formattedDate);
        // 8. Разница между датами
        System.out.println("\n----- Разница между датами -----");
        long daysBetween = ChronoUnit.DAYS.between(today, newYearDate);
        System.out.println("Дней до Нового года: " + daysBetween);
    }
}