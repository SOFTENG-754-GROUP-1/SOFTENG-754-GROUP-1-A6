package nz.ac.auckland.se754.web.stepdefs;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import nz.ac.auckland.se754.web.pages.ProfilePage;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.*;

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

    @When("I upload an image file for the profile picture")
    public void i_upload_an_image_file_for_the_profile_picture() {
        profilePage.uploadNewProfilePicture("/image.png");
    }

    @When("I press the change profile picture button")
    public void i_press_the_change_profile_picture_button() {
        profilePage.clickChangeProfilePictureButton();
    }

    @Then("I should see an alert message pop-up saying profile picture successfully changed")
    public void i_should_see_an_alert_message_pop_up_saying_profile_picture_successfully_changed() {
        String alertText = profilePage.getAlertText();
        String expectedAlertText = "Your profile picture has been changed successfully";
        assertEquals(expectedAlertText, alertText);
    }

    @When("I upload an image file for the banner picture")
    public void i_upload_an_image_file_for_the_banner_picture() {
        profilePage.uploadNewBannerPicture("/image.jpg");
    }

    @When("I press the change banner picture button")
    public void i_press_the_change_banner_picture_button() {
        profilePage.clickChangeBannerPictureButton();
    }

    @Then("I should see an alert message pop-up saying banner picture successfully changed")
    public void i_should_see_an_alert_message_pop_up_saying_banner_picture_successfully_changed() {
        String alertText = profilePage.getAlertText();
        String expectedAlertText = "Your banner picture has been changed successfully";
        assertEquals(expectedAlertText, alertText);
    }

    @When("I select flags")
    public void i_selectFlags() {
        profilePage.selectFlags(new String[] {"New Zealand", "Australia"});
    }

    @When("I press the update flags button")
    public void i_press_the_update_flags_button() {
        profilePage.clickChangeFlagsButton();
    }

    @Then("I should see an alert message pop-up saying flag successfully changed")
    public void i_should_see_an_alert_message_pop_up_saying_flag_successfully_changed() {
        String alertText = profilePage.getAlertText();
        String expectedAlertText = "Your flags have been changed successfully";
        assertEquals(expectedAlertText, alertText);
    }


    @When("I enter {string} in the status field")
    public void i_enter_in_the_status_field(String message) {
        profilePage.enterStatusMessage(message);

    }

    @When("I press the update status button")
    public void i_press_the_update_status_button() {
        profilePage.clickChangeStatusButton();
    }

    @Then("I should see an alert message pop-up saying status successfully changed")
    public void i_should_see_an_alert_message_pop_up_saying_status_successfully_changed() {
        String alertText = profilePage.getAlertText();
        String expectedAlertText = "Your status message has been changed successfully";
        assertEquals(expectedAlertText, alertText);
    }

    @When("I select {string} theme")
    public void i_select_theme(String theme) {
        profilePage.selectTheme(theme);
    }

    @And("I press the change theme button")
    public void i_press_the_change_theme_button() {
        profilePage.clickChangeThemeButton();
    }

    @Then("I should see an alert message pop-up saying theme successfully changed")
    public void i_should_see_an_alert_message_pop_up_saying_status_theme_changed() {
        String alertText = profilePage.getAlertText();
        String expectedAlertText = "Your theme has been changed to dark";
        assertEquals(expectedAlertText, alertText);
    }


    @Then("I should see my current profile information")
    public void i_should_see_my_current_profile_information() {

        String username = profilePage.getUsernameText();
        String profilePicture = profilePage.getProfilePicture();
        String bannerPicture = profilePage.getBannerPicture();
        String[] flags = profilePage.getCurrentFlags();
        String statusMessage = profilePage.getStatusMessage();
        String theme = profilePage.getCurrentTheme();

        assertEquals("currentUsername", username);
        assertEquals("/profile.png", profilePicture);
        assertEquals("/banner.png", bannerPicture);
        assertArrayEquals(new String[]{"New Zealand", "Australia"}, flags);
        assertEquals("current status message", statusMessage);
        assertEquals("Dark", theme);
    }

    @When("I press the remove profile picture button")
    public void i_press_the_remove_profile_picture_button() {
        profilePage.clickRemoveProfilePictureButton();
    }

    @Then("I should see no profile picture")
    public void i_should_see_no_profile_picture() {
        String profilePicture = profilePage.getProfilePicture();
        assertEquals("No profile picture", profilePicture);
    }

    @When("I press the remove banner picture button")
    public void i_press_the_remove_banner_picture_button() {
        profilePage.clickRemoveBannerPictureButton();
    }

    @Then("I should see no banner picture")
    public void i_should_see_no_banner_picture() {
        String bannerPicture = profilePage.getBannerPicture();
        assertEquals("No banner picture", bannerPicture);
    }
}
