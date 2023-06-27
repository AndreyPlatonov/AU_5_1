package ru.netology.web;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;


import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;


public class CallbackTest {

    @BeforeEach
    public void setUp() {
        open("http://localhost:9999/");
    }

    @Test
    void submitSuccessRePlannedRequest() {

        FormatDate dateFormatter = new FormatDate();
        String dateMeeting = dateFormatter.currentPlusDays(4);

        DataGenerator clientInfo = new DataGenerator();
        RegistrationByClientInfo infoClient = clientInfo.generatedByInfo();


        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='city'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='phone'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='name'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=city] input").setValue(infoClient.getCity());

        $("[data-test-id=date] input").setValue(dateMeeting);
        $("[data-test-id=name] input").setValue(infoClient.getFullName());
        $("[data-test-id=phone] input").setValue(infoClient.getPhone());
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Запланировать")).click();
        $("[data-test-id=success-notification] .notification__content").shouldHave(Condition.text("Встреча успешно запланирована на " + dateMeeting), Duration.ofSeconds(5));

        dateMeeting = dateFormatter.currentPlusDays(14);

        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='city'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='phone'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='name'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=city] input").setValue(infoClient.getCity());
        $("[data-test-id=date] input").setValue(dateMeeting);
        $("[data-test-id=name] input").setValue(infoClient.getFullName());
        $("[data-test-id=phone] input").setValue(infoClient.getPhone());
        //    $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Запланировать")).click();
        $("[data-test-id=replan-notification] .notification__content").shouldHave(Condition.text("У вас уже запланирована встреча на другую дату. Перепланировать?"), Duration.ofSeconds(5));
        $$("button").find(exactText("Перепланировать")).click();
        $("[data-test-id=success-notification] .notification__content").shouldHave(Condition.text("Встреча успешно запланирована на " + dateMeeting), Duration.ofSeconds(5));

    }

    @Test
    void submitSuccessRequest() {

        FormatDate dateFormatter = new FormatDate();
        String dateMeeting = dateFormatter.currentPlusDays(4);

        DataGenerator clientInfo = new DataGenerator();
        RegistrationByClientInfo infoClient = clientInfo.generatedByInfo();

        $("[data-test-id=city] input").setValue(infoClient.getCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(dateMeeting);
        $("[data-test-id=name] input").setValue(infoClient.getFullName());
        $("[data-test-id=phone] input").setValue(infoClient.getPhone());
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Запланировать")).click();
        $("[data-test-id=success-notification] .notification__content").shouldHave(Condition.text("Встреча успешно запланирована на " + dateMeeting), Duration.ofSeconds(5));

    }

    @Test
    void submitFailedNullCity() {

        FormatDate dateFormatter = new FormatDate();
        String dateMeeting = dateFormatter.currentPlusDays(4);

        DataGenerator clientInfo = new DataGenerator();
        RegistrationByClientInfo infoClient = clientInfo.generatedByInfo();

        $("[data-test-id=city] input").setValue("");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(dateMeeting);
        $("[data-test-id=name] input").setValue(infoClient.getFullName());
        $("[data-test-id=phone] input").setValue(infoClient.getPhone());
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Запланировать")).click();
        $("[data-test-id=city].input_invalid .input__sub").shouldHave(Condition.text("Поле обязательно для заполнения")).shouldBe(Condition.visible);

    }

    @Test
    void submitFailedWrongCity() {

        FormatDate dateFormatter = new FormatDate();
        String dateMeeting = dateFormatter.currentPlusDays(4);

        DataGenerator clientInfo = new DataGenerator();
        RegistrationByClientInfo infoClient = clientInfo.generatedByInfo();

        $("[data-test-id=city] input").setValue("ввввввв");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(dateMeeting);
        $("[data-test-id=name] input").setValue(infoClient.getFullName());
        $("[data-test-id=phone] input").setValue(infoClient.getPhone());
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Запланировать")).click();
        $("[data-test-id=city].input_invalid .input__sub").shouldHave(Condition.exactText("Доставка в выбранный город недоступна")).shouldBe(Condition.visible);

    }

    @Test
    void submitFailedNullDate() {

        DataGenerator clientInfo = new DataGenerator();
        RegistrationByClientInfo infoClient = clientInfo.generatedByInfo();

        $("[data-test-id=city] input").setValue(infoClient.getCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue("");
        $("[data-test-id=name] input").setValue(infoClient.getFullName());
        $("[data-test-id=phone] input").setValue(infoClient.getPhone());
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Запланировать")).click();
        $("[data-test-id=date] .input.input_invalid .input__sub").shouldHave(Condition.exactText("Неверно введена дата")).shouldBe(Condition.visible);


    }

    @Test
    void submitSuccessBoundDate() {

        FormatDate dateFormatter = new FormatDate();
        String dateMeeting = dateFormatter.currentPlusDays(3);

        DataGenerator clientInfo = new DataGenerator();
        RegistrationByClientInfo infoClient = clientInfo.generatedByInfo();

        $("[data-test-id=city] input").setValue(infoClient.getCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(dateMeeting);
        $("[data-test-id=name] input").setValue(infoClient.getFullName());
        $("[data-test-id=phone] input").setValue(infoClient.getPhone());
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Запланировать")).click();
        $("[data-test-id=success-notification] .notification__content").shouldHave(Condition.text("Встреча успешно запланирована на " + dateMeeting), Duration.ofSeconds(5));

    }

    @Test
    void submitFailedBeforeDate() {

        FormatDate dateFormatter = new FormatDate();
        String dateMeeting = dateFormatter.currentPlusDays(1);

        DataGenerator clientInfo = new DataGenerator();
        RegistrationByClientInfo infoClient = clientInfo.generatedByInfo();

        $("[data-test-id=city] input").setValue(infoClient.getCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(dateMeeting);
        $("[data-test-id=name] input").setValue(infoClient.getFullName());
        $("[data-test-id=phone] input").setValue(infoClient.getPhone());
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Запланировать")).click();
        $("[data-test-id=date] .input.input_invalid .input__sub").shouldHave(Condition.exactText("Заказ на выбранную дату невозможен")).shouldBe(Condition.visible);

    }

    @Test
    void submitFailedNullName() {

        FormatDate dateFormatter = new FormatDate();
        String dateMeeting = dateFormatter.currentPlusDays(4);

        DataGenerator clientInfo = new DataGenerator();
        RegistrationByClientInfo infoClient = clientInfo.generatedByInfo();

        $("[data-test-id=city] input").setValue(infoClient.getCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(dateMeeting);
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue(infoClient.getPhone());
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Запланировать")).click();
        $("[data-test-id=name].input_invalid.input .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения")).shouldBe(Condition.visible);

    }

    @Test
    void submitFailedWrongName() {

        FormatDate dateFormatter = new FormatDate();
        String dateMeeting = dateFormatter.currentPlusDays(4);

        DataGenerator clientInfo = new DataGenerator();
        RegistrationByClientInfo infoClient = clientInfo.generatedByInfo();

        $("[data-test-id=city] input").setValue(infoClient.getCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(dateMeeting);
        $("[data-test-id=name] input").setValue("Иванов Иван@@222");
        $("[data-test-id=phone] input").setValue(infoClient.getPhone());
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Запланировать")).click();
        $("[data-test-id=name].input.input_invalid .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.")).shouldBe(Condition.visible);

    }

    @Test
    void submitFailedNullPhone() {

        FormatDate dateFormatter = new FormatDate();
        String dateMeeting = dateFormatter.currentPlusDays(4);

        DataGenerator clientInfo = new DataGenerator();
        RegistrationByClientInfo infoClient = clientInfo.generatedByInfo();

        $("[data-test-id=city] input").setValue(infoClient.getCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(dateMeeting);
        $("[data-test-id=name] input").setValue(infoClient.getFullName());
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Запланировать")).click();
        $("[data-test-id=phone].input.input_invalid .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения")).shouldBe(Condition.visible);

    }

    /*@Test
    void submitFailedWrongPhone() {

        FormatDate dateFormatter = new FormatDate();
        String dateMeeting = dateFormatter.currentPlusDays(4);

        DataGenerator clientInfo = new DataGenerator();
        RegistrationByClientInfo infoClient = clientInfo.generatedByInfo();

        $("[data-test-id=city] input").setValue(infoClient.getCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(dateMeeting);
        $("[data-test-id=name] input").setValue(infoClient.getFullName());
        $("[data-test-id=phone] input").setValue("+79201SSFF234");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Запланировать")).click();
        $("[data-test-id=phone].input.input_invalid .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.")).shouldBe(Condition.visible);

    }*/

    @Test
    void submitFailedNotCheck() {

        FormatDate dateFormatter = new FormatDate();
        String dateMeeting = dateFormatter.currentPlusDays(4);

        DataGenerator clientInfo = new DataGenerator();
        RegistrationByClientInfo infoClient = clientInfo.generatedByInfo();

        $("[data-test-id=city] input").setValue(infoClient.getCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(dateMeeting);
        $("[data-test-id=name] input").setValue(infoClient.getFullName());
        $("[data-test-id=phone] input").setValue(infoClient.getPhone());
        $$("button").find(exactText("Запланировать")).click();
        $("[data-test-id=agreement]").shouldHave(Condition.cssClass("input_invalid"));

    }


}
