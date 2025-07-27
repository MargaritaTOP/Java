import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class Next_USA_Election {
    public static void main(String[] args) {
        int electionYear = 2028;
        LocalDate novemberFirst = LocalDate.of(electionYear, 11, 1);
        LocalDate firstMondayAfter = novemberFirst.with(TemporalAdjusters.nextOrSame(java.time.DayOfWeek.MONDAY));
        LocalDate electionDay = firstMondayAfter.plusDays(1);
        System.out.println("Следующие выборы президента США состоятся: " + electionDay);
        System.out.println("Это " + "високосный" + " год");
    }
}