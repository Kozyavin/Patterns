package ru.netology.patterntest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GenerateDay {

    public String generateDay(int day){
        LocalDate date = LocalDate.now(); // получаем текущую дату

        return date.plusDays(day).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        //now(): возвращает объект, который представляет текущую дату
        //plusDays(int n): добавляет к дате некоторое количество дней
        //format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) - представление даты согласно паттерну

    }

}
