package ru.netology.patterntest;

import com.codeborne.selenide.Condition;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import java.time.Duration;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class MeetingPlanning {
    Faker faker;

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
        ;
    }

    @Test
    void planingMeetingTest(String town, String name) {
        DataGenerator.UserInfo User = DataGenerator.Registration.generateUser("ru");//создали объект User с данными
        int daysAddFirstMeeting = 4;
        String firstDayMeeting = DataGenerator.generateDate(daysAddFirstMeeting);
        int daysAddSecondMeeting = 10;
        String secondDayMeeting = DataGenerator.generateDate(daysAddSecondMeeting);

        $("[data-test-id='city'] input").setValue(DataGenerator.generateCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);//очистка поля даты
        $("[data-test-id='date'] input").setValue(firstDayMeeting);
        $("[data-test-id='name'] input").setValue(DataGenerator.generateName("ru"));
        $("[data-test-id='phone'] input").setValue(DataGenerator.generatePhone("ru"));
        $("[data-test-id='agreement'].checkbox").click();
        $(".button").click();
        $(".notification__content")
                .shouldHave(text("Встреча успешно запланирована на " + firstDayMeeting), Duration.ofSeconds(13))
                .shouldBe(Condition.visible);

    }
    @Test
    void changeMeetingTest(String town, String name) {
        DataGenerator.UserInfo User = DataGenerator.Registration.generateUser("ru");//создали объект User с данными
        int daysAddFirstMeeting = 4;
        String firstDayMeeting = DataGenerator.generateDate(daysAddFirstMeeting);
        int daysAddSecondMeeting = 10;
        String secondDayMeeting = DataGenerator.generateDate(daysAddSecondMeeting);

        $("[data-test-id='city'] input").setValue(DataGenerator.generateCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);//очистка поля даты
        $("[data-test-id='date'] input").setValue(firstDayMeeting);
        $("[data-test-id='name'] input").setValue(DataGenerator.generateName("ru"));
        $("[data-test-id='phone'] input").setValue(DataGenerator.generatePhone("ru"));
        $("[data-test-id='agreement'].checkbox").click();
        $(".button").click();
        $(".notification__content")
                .shouldHave(text("Встреча успешно запланирована на " + firstDayMeeting), Duration.ofSeconds(13))
                .shouldBe(Condition.visible);

        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);//очистка поля даты
        $("[data-test-id='date'] input").setValue(secondDayMeeting);
        $(".button").click();
        $(byText("Запланировать")).click();


        $("[data-test-id='replan-notification'].notification__content")
                .shouldHave(text("У вас уже запланирована встреча на другую дату.Перепланировать?"), Duration.ofSeconds(13))
                .shouldBe(Condition.visible);
        $(byText("Перепланировать")).click();

        $(".notification__content")
                .shouldHave(text("Встреча успешно запланирована на " + secondDayMeeting), Duration.ofSeconds(13))
                .shouldBe(Condition.visible);

    }
}
