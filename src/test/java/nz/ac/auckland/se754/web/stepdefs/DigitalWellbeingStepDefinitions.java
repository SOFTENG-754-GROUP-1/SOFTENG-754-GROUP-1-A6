package nz.ac.auckland.se754.web.stepdefs;
import io.cucumber.java.Before;
import nz.ac.auckland.se754.web.pages.WelcomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.cucumber.java.en.*;
import static org.junit.Assert.*;

public class DigitalWellbeingStepDefinitions {

    WebDriver driver;
    WelcomePage welcomePage;

    @Before("@digitalWellbeing")
    public void before() {
        driver = ChromeWebDriverManager.getDriver();
        welcomePage = new WelcomePage(driver);
    }

    @Given("the user is using the language learning application")
    public void the_user_is_using_the_language_learning_application() {
        driver.get("http://localhost:8080/welcome");
    }

    @When("the user uses the application for 60 or more minutes")
    public void the_user_uses_the_application_for_sixty_or_more_minutes() {
        welcomePage.setScreenTime(65);
    }
    @Then("the user's screen time should be stored and accumulated")
    public void the_user_s_screen_time_should_be_stored_and_accumulated() {
        int screenTime = welcomePage.getScreenTime();
        int expectedScreenTime = 65;
        assertEquals(expectedScreenTime, screenTime);
    }
    @Then("the user should be prompted to take a break")
    public void the_user_should_be_prompted_to_take_a_break() {
        String expectedNotification = "You have been learning for more than 60 minutes! " +
                "You should take a break!";
        String notification = welcomePage.getNotification();
        assertEquals(expectedNotification, notification);
    }

    @Given("the user is taking a break from the application")
    public void the_user_is_taking_a_break_from_the_application() {
        // Since we're mocking, we don't need to perform any action here
    }
    @When("the user is not using the application for five or more minutes")
    public void the_user_is_not_using_the_application_for_five_or_more_minutes() {
        driver.get("http://localhost:8080/welcome");
        welcomePage.setBreakTime(6);
    }
    @Then("the user's break time should be stored and accumulated")
    public void the_user_s_break_time_should_be_stored_and_accumulated() {
        int breakTime = welcomePage.getBreakTime();
        int expectedBreakTime = 6;
        assertEquals(expectedBreakTime, breakTime);
    }
}
