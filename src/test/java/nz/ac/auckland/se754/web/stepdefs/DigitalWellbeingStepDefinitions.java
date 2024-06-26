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

    @Then("the user's break time should be stored and accumulated")
    public void the_user_s_break_time_should_be_stored_and_accumulated() {
        int breakTime = welcomePage.getBreakTime();
        int expectedBreakTime = 6;
        assertEquals(expectedBreakTime, breakTime);
    }

    @When("the user takes a break that exceeds five or more minutes")
    public void the_user_takes_a_break_that_exceeds_five_or_more_minutes() {
        welcomePage.setBreakTime(6);
    }

    @Then("the accumulated screen time will reset to 0")
    public void the_accumulated_screen_time_will_reset_to_zero() {
        int expectedScreenTime = 0;
        int screenTime = welcomePage.getScreenTime();
        assertEquals(expectedScreenTime, screenTime);
    }

    @Then("the user should no longer be prompted to take a break")
    public void the_user_should_no_longer_be_prompted_to_take_a_break() {
        String expectedNotification = "";
        String notification = welcomePage.getNotification();
        assertEquals(expectedNotification, notification);
    }

}
