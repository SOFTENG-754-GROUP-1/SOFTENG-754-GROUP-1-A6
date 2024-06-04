package nz.ac.auckland.se754.web.stepdefs;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import nz.ac.auckland.se754.web.pages.CourseCategoryPage;
import org.openqa.selenium.WebDriver;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class CourseCategoryStepDefinitions {
    private WebDriver driver;
    private CourseCategoryPage courseCategoryPage;

    @Before("@courseCategory")
    public void setup() {
        driver = ChromeWebDriverManager.getDriver();
        courseCategoryPage = new CourseCategoryPage(driver);
    }

    @Given("I visit the course category page")
    public void i_visit_the_course_category_page() {
        driver.get("http://localhost:8080/category");
    }

    @Then("I should see all available course categories")
    public void i_should_see_all_available_course_categories() {
        String[] categories = courseCategoryPage.getCategories();
        assertArrayEquals(new String[]{"Category-1", "Category-2", "Category-3"}, categories);

    }

    @Then("I should see the courses under each category")
    public void i_should_see_the_courses_under_each_category() {
        Map<String, String[]> categoryCoursesMap = courseCategoryPage.getCategoryCoursesAsMap();

        System.out.println("hello");

        for (String name : categoryCoursesMap.keySet()) {
            System.out.println("hello");

            System.out.println(name);
        }

        String[] categoryOneCourses = categoryCoursesMap.get("Category-1");
        assertArrayEquals(new String[]{"course1", "course2"}, categoryOneCourses);

        String[] categoryTwoCourses = categoryCoursesMap.get("Category-2");
        assertArrayEquals(new String[]{"course3", "course4"}, categoryTwoCourses);

        String[] categoryThreeCourses = categoryCoursesMap.get("Category-3");
        assertArrayEquals(new String[]{"course5"}, categoryThreeCourses);

    }
}
