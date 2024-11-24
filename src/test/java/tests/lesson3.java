package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class lesson3 {

    @BeforeAll
    static void beforeAll() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = true;
        Configuration.timeout = 5000; // default 4000
    }

    @Test
    void successfulSearchTest() {
        open("https://demoqa.com/automation-practice-form");

        //Datа
        $("[id=firstName]").setValue("Julia");
        $("[id=lastName]").setValue("Oshmarina");
        $("[id=userEmail]").setValue("juffgdfgd@gmail.com");
        $(byText("Male")).click();
        $("[id=userNumber]").setValue("4544263545");

        //birth
        $("[id=dateOfBirthInput]").click();
        $("[class~=react-datepicker__year-select]").click();
        $(".react-datepicker__year-select").selectOption("2001");
        $(".react-datepicker__year-select").click();
        $("[class~=react-datepicker__month-select]").click();
        $(".react-datepicker__month-select").selectOption("April");
        $(".react-datepicker__month-select").click();
        $("[class~=react-datepicker__day--018]").click();

        //Data
        $("#subjectsInput").setValue("English").pressEnter();
        $(byText("Sports")).click();

        //Picture
        File cv = new File("src/resources/test.jpg");
        $("#uploadPicture").uploadFile(cv);


        //Adress
        $("[id=currentAddress]").setValue("Russia");
        $("[class~=css-1wa3eu0-placeholder]").click();
        $("#react-select-3-input").setValue("Rajasthan").pressEnter();
        $("#react-select-4-input").setValue("Jaiselmer").pressEnter();

        $("#submit").click();


        //Examination
        $(".table").$(byText("Student Name")).sibling(0).shouldHave(text("Julia Oshmarina"));
        $(".table").$(byText("Student Email")).sibling(0).shouldHave(text("juffgdfgd@gmail.com"));
        $(".table").$(byText("Gender")).sibling(0).shouldHave(text("Male"));
        $(".table").$(byText("Mobile")).sibling(0).shouldHave(text("4544263545"));
        $(".table").$(byText("Date of Birth")).sibling(0).shouldHave(text("18 April,2001"));
        $(".table").$(byText("Subjects")).sibling(0).shouldHave(text("English"));
        $(".table").$(byText("Hobbies")).sibling(0).shouldHave(text("Sports"));
        $(".table").$(byText("Picture")).sibling(0).shouldHave(text("test.jpg"));
        $(".table").$(byText("Address")).sibling(0).shouldHave(text("Russia"));
        $(".table").$(byText("State and City")).sibling(0).shouldHave(text("Rajasthan Jaiselmer"));

    }
}
