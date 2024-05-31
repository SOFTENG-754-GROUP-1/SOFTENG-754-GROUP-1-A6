package nz.ac.auckland.se754.web.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import nz.ac.auckland.se754.web.pages.CoursesPage;
import nz.ac.auckland.se754.web.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CourseTrackingStepDefinitions{
    private WebDriver driver;
    private CoursesPage coursesPage;
    private LoginPage loginPage;
    private String userName;
    private String password;

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
        coursesPage = new CoursesPage(driver);
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

    @Given("I am new to the site")
    public void i_am_new_to_the_site() {
        userName = "newUser";
        password = "password";
    }

    @Given("I am a returning user")
    public void i_am_a_returning_user() {
        userName = "returnUser";
        password = "password";
    }

    @Given("I am logged in")
    public void i_am_logged_in() {
        driver.get("http://localhost:8080/login");
        loginPage.insertUserName(userName);
        loginPage.insertPassword(password);
        loginPage.clickLogin();
    }

    @When("I visit the courses page")
    public void i_visit_the_courses_page() {
        driver.get("http://localhost:8080/courses");
    }

    @When("I click the interested button for a course not tagged as interested")
    public void i_click_the_interested_button_for_a_course_not_tagged_as_interested() {
        coursesPage.clickInterestButton(1);
    }

    @When("I click the interested button for a course tagged as interested")
    public void i_click_the_interested_button_for_a_course_tagged_as_interested() {
        coursesPage.clickInterestButton(0);
    }


    @Then("I should see a list of courses with no tags")
    public void i_should_see_a_list_of_courses_with_no_tags() {
        assertTrue(coursesPage.getCourses().size() > 0);
        ArrayList<String> interestTags = coursesPage.getInterestTags();
        ArrayList<String> progressTags = coursesPage.getProgressTags();
        for (String tag : interestTags) {
            assertEquals("", tag);
        }
        for (String tag : progressTags) {
            assertEquals("Not Started", tag);
        }
    }

    @Then("I should see the courses I have tagged as interested")
    public void i_should_see_the_courses_I_have_tagged_as_interested() {
        assertTrue(coursesPage.getCourses().size() > 0);
        ArrayList<String> interestTags = coursesPage.getInterestTags();
        ArrayList<String> expectedInterestTags = new ArrayList<>();
        expectedInterestTags.add("Interested");
        expectedInterestTags.add("");
        expectedInterestTags.add("Interested");
        assertEquals(expectedInterestTags, interestTags);
    }

    @Then("I should see the courses progress tagged")
    public void i_should_see_the_courses_progress_tagged() {
        assertTrue(coursesPage.getCourses().size() > 0);
        ArrayList<String> progressTags = coursesPage.getProgressTags();
        ArrayList<String> expectedProgressTags = new ArrayList<>();
        expectedProgressTags.add("Not Started");
        expectedProgressTags.add("In Progress");
        expectedProgressTags.add("Completed");
        assertEquals(expectedProgressTags, progressTags);
    }

    @Then("I should see the course tagged as interested")
    public void i_should_see_the_course_tagged_as_interested() {
        assertTrue(coursesPage.getCourses().size() > 0);
        ArrayList<String> interestTags = coursesPage.getInterestTags();
        ArrayList<String> expectedInterestTags = new ArrayList<>();
        expectedInterestTags.add("");
        expectedInterestTags.add("Interested");
        assertEquals(expectedInterestTags, interestTags);
    }

    @Then("I should see the courses tag removed")
    public void i_should_see_the_courses_tag_removed() {
        assertTrue(coursesPage.getCourses().size() > 0);
        ArrayList<String> interestTags = coursesPage.getInterestTags();
        ArrayList<String> expectedInterestTags = new ArrayList<>();
        expectedInterestTags.add("");
        expectedInterestTags.add("");
        expectedInterestTags.add("Interested");
        assertEquals(expectedInterestTags, interestTags);
    }
}
