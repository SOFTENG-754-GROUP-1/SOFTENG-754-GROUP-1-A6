package nz.ac.auckland.se754.web.stepdefs;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import nz.ac.auckland.se754.web.pages.ProfilePage;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProfilePersonalisationStepDefinitions {

    private WebDriver driver;
    private ProfilePage profilePage;

    @Before("@profilePersonalisation")
    public void setup() {
        driver = ChromeWebDriverManager.getDriver();
        profilePage = new ProfilePage(driver);
    }

    @Given("I visit the profile page")
    public void i_visit_the_profile_page() {
        driver.get("http://localhost:8080/profile");
    }

    @When("I enter {string} in the new username field")
    public void i_enter_in_the_new_username_field(String newUsername) {
        profilePage.enterNewUsername(newUsername);
    }

    @When("I press the change username button")
    public void i_press_the_change_username_button() {
        profilePage.clickChangeUsernameButton();
    }

    @Then("I should see an alert message pop-up saying username successfully changed")
    public void i_should_see_an_alert_message_pop_up_saying_username_successfully_changed() {
        String alertText = profilePage.getAlertText();
        String expectedAlertText = "Your username has been changed to user1";
        assertEquals(expectedAlertText, alertText);
    }
}
