package datetime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DateTest {
    public static void main(String[] args) throws ParseException {

        LocalDate localDate = LocalDate.now();
        LocalDate localDate1 = LocalDate.of(2019, 9, 16);
        System.out.println(localDate);
        System.out.println(localDate1);
        int year = localDate.getYear();
        int year1 = localDate1.get(ChronoField.YEAR);
        System.out.println(year);
        System.out.println(year1);
        Month month = localDate.getMonth();
        int month1 = localDate1.get(ChronoField.MONTH_OF_YEAR);
        System.out.println(month.getValue());
        System.out.println(month1);
        int day = localDate.getDayOfMonth();
        int day1 = localDate1.get(ChronoField.DAY_OF_MONTH);
        System.out.println(day);
        System.out.println(day1);
        LocalTime localTime = LocalTime.now();
        LocalTime localTime1 = LocalTime.of(23, 10, 20);
        System.out.println(localTime);
        System.out.println(localTime1);

        System.out.println(LocalDate.now());
        System.out.println(LocalTime.now());
        System.out.println(LocalDateTime.now());
        System.out.println(ZoneId.systemDefault());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        LocalDateTime time = LocalDateTime.now();
        System.out.println(time);
        System.out.println(ChronoUnit.SECONDS.between(sdf.parse("20190813 18:43:13").toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(),time ));
    }
}
