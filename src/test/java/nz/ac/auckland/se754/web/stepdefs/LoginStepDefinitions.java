package nz.ac.auckland.se754.web.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import nz.ac.auckland.se754.web.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginStepDefinitions {
    private WebDriver driver;
    private LoginPage loginPage;

    @Before
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        String osName = System.getProperty("os.name").toLowerCase();

        if (System.getenv().getOrDefault("headless", "false").equals("true")) {
            options.addArguments("--headless");
            System.setProperty("webdriver.chrome.driver", "webdrivers/linux/chromedriver");
        } else if(osName.contains("mac")) {
            System.setProperty("webdriver.chrome.driver", "webdrivers/macos/chromedriver");
        } else if(osName.contains("win")) {
            System.setProperty("webdriver.chrome.driver", "webdrivers/win/chromedriver.exe");
        }

        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1200");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-extensions");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
        loginPage = new LoginPage(driver);
    }

    @AfterStep
    public void afterEachStep() {
        // to make the test at human speed
        if (System.getenv().getOrDefault("headless", "false").equals("false")) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @After
    public void tearDown() {
        driver.close();
        driver.quit();
    }

    @Given("I visit the login page")
    public void i_visit_the_login_page() {
        driver.get("http://localhost:8080/login");

    }

    @When("I enter {string} as user name field")
    public void i_enter_as_user_name_field(String string) {
        loginPage.insertUserName(string);
    }

    @When("I enter {string} as password field")
    public void i_enter_as_password_field(String string) {
        loginPage.insertPassword(string);
    }

    @When("I press the submit button")
    public void i_press_the_submit_button() {
        loginPage.clickLogin();
    }

    @Then("I should see the welcome page")
    public void i_should_see_the_welcome_page() {
        assertTrue(loginPage.getMessage().contains("Welcome user1"));
    }

    @Then("I should see an error message")
    public void i_should_see_an_error_message() {
        assertTrue(loginPage.getError().contains("Invalid Credentials"));

    }


}
