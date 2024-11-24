package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class lesson3 {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = true;
        Configuration.timeout = 5000; // default 4000
    }

    @Test
    void successfulSearchTest() {
        open("https://demoqa.com/automation-practice-form");
        $("[id=firstName]").setValue("Julia");
        $("[id=lastName]").setValue("Oshmarina");
        $("[id=userEmail]").setValue("juffgdfgd@gmail.com");
        $(byText("Male")).click();
        $("[id=userNumber]").setValue("4544263545");
        $("[id=dateOfBirthInput]").click();
        $("[class~=react-datepicker__year-select]").click();
        $(".react-datepicker__year-select").selectOption("2001");
        $(".react-datepicker__year-select").click();
        $("[class~=react-datepicker__month-select]").click();
        $(".react-datepicker__month-select").selectOption("April");
        $(".react-datepicker__month-select").click();
        $("[class~=react-datepicker__day--018]").click();
        $("#subjectsInput").setValue("English").pressEnter();
        $(byText("Sports")).click();
        $("#uploadPicture").uploadFromClasspath("1.png");



    }
}
