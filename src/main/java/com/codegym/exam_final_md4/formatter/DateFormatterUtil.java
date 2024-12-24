package com.codegym.exam_final_md4.formatter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFormatterUtil {
    public static String formatLocalDate(LocalDate date) {
        if (date != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return date.format(formatter);
        }
        return "";
    }
}
