package tests;

import com.codeborne.selenide.Configuration;

import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class Lesson11 {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";


        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();

    }


    @Test
    @Tag("lesson")
    void successfulSearchTest() {
        step("Открываем главную страницу", () -> {
        open("https://demoqa.com/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        });

        step("Заполняем поля", () -> {
        //Datа
        $("[id=firstName]").setValue("Julia");
        $("[id=lastName]").setValue("Oshmarina");
        $("[id=userEmail]").setValue("juffgdfgd@gmail.com");
        //$("#gender-radio-3").parent().click(); // good
        $("[id=genterWrapper]").$(byText("Male")).click();
        $("[id=userNumber]").setValue("4544263545");

        //birth
        $("[id=dateOfBirthInput]").click();
        $("[class~=react-datepicker__year-select]").click();
        $(".react-datepicker__year-select").selectOption("2001");
        $(".react-datepicker__year-select").click();
        $("[class~=react-datepicker__month-select]").click();
        //$(.react-datepicker__month-select").selectOption("April");
        $(".react-datepicker__month-select").selectOption("April");
        $(".react-datepicker__month-select").click();
        $("[class~=react-datepicker__day--018]").click();

        //Data
        $("#subjectsInput").setValue("English").pressEnter();
        $("[id=hobbiesWrapper]").$(byText("Sports")).click();

        //Picture
        $("#uploadPicture").uploadFromClasspath("test.jpg");

        //Adress
        $("[id=currentAddress]").setValue("Russia");
        $("[class~=css-1wa3eu0-placeholder]").click();
        $("#react-select-3-input").setValue("Rajasthan").pressEnter();
        $("#react-select-4-input").setValue("Jaiselmer").pressEnter();

        $("#submit").click();
        });

        step("Проверяем заполнение полей", () -> {
        $(".table").$(byText("Student Name")).sibling(0).shouldHave(text("Julia Oshmarina"));
        $(".table").$(byText("Student Email")).sibling(0).shouldHave(text("juffgdfgd@gmail.com"));
        $(".table").$(byText("Gender")).sibling(0).shouldHave(text("Male"));
        $(".table").$(byText("Mobile")).sibling(0).shouldHave(text("4544263545"));
        $(".table").$(byText("Date of Birth")).sibling(0).shouldHave(text("18 April,2001"));
        $(".table").$(byText("Subjects")).sibling(0).shouldHave(text("куку"));
        $(".table").$(byText("Hobbies")).sibling(0).shouldHave(text("Sports"));
        $(".table").$(byText("Picture")).sibling(0).shouldHave(text("test.jpg"));
        $(".table").$(byText("Address")).sibling(0).shouldHave(text("Russia"));
        $(".table").$(byText("State and City")).sibling(0).shouldHave(text("Rajasthan Jaiselmer"));
        });
    }
}
