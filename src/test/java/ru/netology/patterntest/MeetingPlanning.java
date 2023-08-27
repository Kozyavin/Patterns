package ru.netology.patterntest;

import com.codeborne.selenide.Condition;

import com.github.javafaker.Faker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.Keys;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class MeetingPlanning {
        Faker faker;

        @BeforeEach
        void setUpAll() {
            faker = new Faker(new Locale("ru"));
        }

        @ParameterizedTest
        @CsvFileSource(files = {"src/test/resources/vacationTownAndName.csv"})

        void positiveTest(String town, String name) {

            SimpleDateFormat reDay = new SimpleDateFormat("dd.MM.yyyy");

            //String address = faker.address().cityName();
            String data = reDay.format(faker.date().future(1000,3, TimeUnit.DAYS));
            //String name = faker.name().fullName();
            String phone = faker.phoneNumber().phoneNumber();



            open("http://localhost:9999");

            $("[data-test-id='city'] input").setValue(town);
            $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);//очистка поля даты
            $("[data-test-id='date'] input").setValue(data);
            $("[data-test-id='name'] input").setValue(name);
            $("[data-test-id='phone'] input").setValue(phone);
            $("[data-test-id='agreement'].checkbox").click();
            $(".button").click();

            $(".notification__content")
                    .shouldHave(text("Встреча успешно запланирована на " + data), Duration.ofSeconds(10))
                    .shouldBe(Condition.visible);
            //Condition - метод , для проверки различных свойств элементов  определённым условиям,в начале вызвать метод should()
            //Condition.text - проверка, что элемент содержит текст
            //Condition.visible - проверка, что элемент видимый на странице

        }
    @Test
    void positiveTest2() {

        SimpleDateFormat reDay = new SimpleDateFormat("dd.MM.yyyy");

        String address = faker.address().cityName();
        String data = reDay.format(faker.date().future(10,3, TimeUnit.DAYS));
        String data2 = reDay.format(faker.date().future(20,11, TimeUnit.DAYS));//2-я дата
        String name = faker.name().fullName();
        String phone = faker.phoneNumber().phoneNumber();
        System.out.println(data);


        open("http://localhost:9999");
        //Выбор первой даты встречи
        $("[data-test-id='city'] input").setValue(address);
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);//очистка поля даты
        $("[data-test-id='date'] input").setValue(data);//ввод даты(со смещением на +4 дня) с применением метода
        $("[data-test-id='name'] input").setValue(name);
        $("[data-test-id='phone'] input").setValue(phone);
        $("[data-test-id='agreement'].checkbox").click();
        $(".button").click();
        //Ввод другой даты (2-й) при сохранении того же имени, телефона и города
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);//очистка поля даты
        $("[data-test-id='date'] input").setValue(data2);//здесь 2-я дата
        $(".button").click();
        //Появляется предложение переназначить, жмём "Перепланировать". В поле даты, остаётся 2-я выбранныя дата. Жмём
        $(byText("Перепланировать")).click();

        $(".notification__content")
                .shouldHave(text("Встреча успешно запланирована на " + data2), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
        //Condition - метод , для проверки различных свойств элементов  определённым условиям,в начале вызвать метод should()
        //Condition.text - проверка, что элемент содержит текст
        //Condition.visible - проверка, что элемент видимый на странице

    }
    }

