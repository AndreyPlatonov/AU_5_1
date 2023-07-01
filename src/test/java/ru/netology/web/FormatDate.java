package ru.netology.web;


import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FormatDate {
    public String currentPlusDays(int days) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate date = LocalDate.now();
        date = date.plusDays(days);
        String formatDate = dtf.format(date);
        return formatDate;
    }
}
