package ru.netology.patterntest;
import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.Locale;
import java.util.Random;

public class DataGenerator {

    private DataGenerator() {}

    public static String generateDate(int shift) {
        // объявление переменной date и задания её значения, для генерации строки с датой

        return LocalDate.now().plusDays(shift).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String generateCity() {
        // объявление переменной city и задания её значения
        //используем массив валидных городов и класс Random

        var city = new String[]{"Абакан","Анадырь","Архангельск","Барнаул","Владикавказ","Горно-Алтайск","Самара","Хабаровск"};
        return city[new Random().nextInt(city.length)];
    }

    public static String generateName(String locale) {

        var faker = new Faker(new Locale(locale));
        return faker.name().lastName() + " " + faker.name().firstName();
    }

    public static String generatePhone(String locale) {

        var faker = new Faker(new Locale(locale));
        return faker.phoneNumber().phoneNumber();
    }

    public static class Registration {
        private Registration() {
        }

        public static UserInfo generateUser(String locale) {
            // создание пользователя user с использованием методов generateCity(locale),generateName(locale),generatePhone(locale)

            return new UserInfo(generateCity(),generateName(locale),generatePhone(locale));
        }
    }

    @Value                             //дата класс - определяет структуру объекта
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }
}