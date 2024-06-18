package codinginterview;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class PruebaKonecta {
    public static final String [] FESTIVOS_COLOMBIA_2024  = {"2024-01-01", "2024-01-08", "2024-03-25", "2024-03-28",
            "2024-03-29", "2024-05-01", "2024-05-13", "2024-06-03", "2024-06-10", "2024-07-01", "2024-07-20",
            "2024-08-07", "2024-08-19", "2024-10-14", "2024-11-04", "2024-11-11", "2024-12-08", "2024-12-25"};
    public static final String FORMATO_FECHA = "yyyy-MM-dd";

    public static void main(String[] args) {
        System.out.println(getProximaFechaQuincena("2024-02-05"));
        System.out.println(getProximaFechaQuincena("2024-03-30"));
        System.out.println(getProximaFechaQuincena("2024-06-30"));
        System.out.println(getProximaFechaQuincena("2024-07-15"));
    }

    // Validaciones para la fecha
    // Si fecha es menos a 15 retornar 15 o día hábil
    private static String getProximaFechaQuincena(String fechaStr) {
        int dia = Integer.parseInt(fechaStr.substring(fechaStr.length()-2, fechaStr.length()));
        if(dia <= 15) {
            fechaStr = fechaStr.substring(0, fechaStr.length()-2) + "15";
        } else {
            fechaStr = fechaStr.substring(0, fechaStr.length()-2) + "30";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATO_FECHA);
        LocalDate fecha = LocalDate.parse(fechaStr, formatter);
        DayOfWeek diaDow = fecha.getDayOfWeek();
        while(esFestivo(fecha) || !esDiaHabil(fecha)) {
            fecha = fecha.plusDays(-1);
        }
        return fecha.format(formatter);
    }

    private static boolean esFestivo(LocalDate fecha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATO_FECHA);
        String fechaStr = fecha.format(formatter);
        return Arrays.stream(FESTIVOS_COLOMBIA_2024).anyMatch(s -> s.equals(fechaStr));
    }

    private static boolean esDiaHabil(LocalDate fecha) {
        DayOfWeek dia = fecha.getDayOfWeek();
        return dia != DayOfWeek.SATURDAY && dia != DayOfWeek.SUNDAY;
    }

}
