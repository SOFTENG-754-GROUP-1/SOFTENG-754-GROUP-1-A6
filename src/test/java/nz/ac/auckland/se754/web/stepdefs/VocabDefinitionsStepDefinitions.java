package nz.ac.auckland.se754.web.stepdefs;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import nz.ac.auckland.se754.web.pages.LessonPage;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VocabDefinitionsStepDefinitions {
    private WebDriver driver;
    private LessonPage lessonPage;
    private String word;

    @Before("@vocabDefinitions")
    public void before() {
        driver = ChromeWebDriverManager.getDriver();
        lessonPage = new LessonPage(driver);
    }

    @Given("The word has a definition")
    public void the_word_has_a_definition() {
        word = "valid word";
        lessonPage.insertWord(word);
    }

    @Given("The word does not have a definition")
    public void the_word_does_not_have_a_definition() {
        word = "invalid word";
        lessonPage.insertWord(word);
    }

    @Given("The word has an example")
    public void the_word_has_an_example() {
        word = "valid word";
        lessonPage.insertWord(word);
    }

    @Given("The word does not have any examples")
    public void the_word_does_not_have_any_examples() {
        word = "invalid word";
        lessonPage.insertWord(word);
    }

    @Given("The word has synonyms and antonyms")
    public void the_word_has_synonyms_and_antonyms() {
        word = "valid word";
        lessonPage.insertWord(word);
    }

    @Given("The word does not have any synonyms or antonyms")
    public void the_word_does_not_have_any_synonyms_or_antonyms() {
        word = "invalid word";
        lessonPage.insertWord(word);
    }

    @Given("The server is down")
    public void the_server_is_down() {
        word = "server down";
        lessonPage.insertWord(word);
    }

    @When("I click on the definition button")
    public void i_click_on_the_definition_button() {
        lessonPage.clickDefinitionButton();
    }

    @When("I click on the example button")
    public void i_click_on_the_example_button() {
        lessonPage.clickExampleButton();
    }

    @When("I click on the synonyms and antonyms button")
    public void i_click_on_the_synonyms_and_antonyms_button() {
        lessonPage.clickSynonymsAndAntonymsButton();
    }

    @Then("I should see the definition of the word")
    public void i_should_see_the_definition_of_the_word() {
        // @todo this is a workaround to fix getDefinition not getting any result
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String definition = lessonPage.getDefinition();
        String expectedDefinition = "valid definition";
        assertEquals(expectedDefinition, definition);
    }

    @Then("I should see an alert message pop-up saying unable to find definition")
    public void i_should_see_an_alert_message_pop_up_saying_unable_to_find_definition() {
        String alertText = lessonPage.getAlertText();
        String expectedAlertText = "Unable to find definition";
        assertEquals(expectedAlertText, alertText);
    }

    @Then("I should see the example of the word")
    public void i_should_see_the_example_of_the_word() {
        String example = lessonPage.getExample();
        String expectedExpected = "valid example";
        assertEquals(expectedExpected, example);
    }

    @Then("I should see an alert message pop-up saying unable to find any examples")
    public void i_should_see_an_alert_message_pop_up_saying_unable_to_find_any_examples() {
        String alertText = lessonPage.getAlertText();
        String expectedAlertText = "Unable to find any examples";
        assertEquals(expectedAlertText, alertText);
    }

    @Then("I should see the synonyms and antonyms of the word")
    public void i_should_see_the_synonyms_and_antonyms_of_the_word() {
        ArrayList<String> synonymsAndAntonyms = lessonPage.getSynonymsAndAntonyms();
        ArrayList<String> expectedSynonymsAndAntonyms = new ArrayList<>();
        expectedSynonymsAndAntonyms.add("Synonyms: ");
        expectedSynonymsAndAntonyms.add("synonym1");
        expectedSynonymsAndAntonyms.add("synonym2");
        expectedSynonymsAndAntonyms.add("Antonyms: ");
        expectedSynonymsAndAntonyms.add("antonym1");
        expectedSynonymsAndAntonyms.add("antonym2");
        assertEquals(expectedSynonymsAndAntonyms, synonymsAndAntonyms);
    }

    @Then("I should see an alert message pop-up saying unable to find any synonyms or antonyms")
    public void i_should_see_an_alert_message_pop_up_saying_unable_to_find_any_synonyms_or_antonyms() {
        String alertText = lessonPage.getAlertText();
        String expectedAlertText = "Unable to find any synonyms or antonyms";
        assertEquals(expectedAlertText, alertText);
    }

    @Then("I should see an alert message pop-up saying connection error")
    public void i_should_see_an_alert_message_pop_up_saying_connection_error() {
        String alertText = lessonPage.getAlertText();
        String expectedAlertText = "Connection error";
        assertEquals(expectedAlertText, alertText);
    }
}
