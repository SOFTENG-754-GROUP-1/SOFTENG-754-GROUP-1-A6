package nz.ac.auckland.se754.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseCategoryPage {
    private final WebDriver driver;
    @FindBy(how = How.CLASS_NAME, using = "category-item")
    private List<WebElement> categoryItems;

    @FindBy(how = How.CLASS_NAME, using = "category-name")
    private List<WebElement> categoryNames;

    public CourseCategoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String[] getCategories() {
        return categoryNames.stream()
                .map(WebElement::getText)
                .toArray(String[]::new);
    }

    public Map<String, String[]> getCategoryCoursesAsMap() {

        Map<String, String[]> categoryCoursesMap = new HashMap<>();

        for (int i = 0; i < categoryNames.size(); i++) {
            WebElement categoryItem = categoryItems.get(i);
            String categoryName = categoryNames.get(i).getText();
            List<WebElement> courseElements = categoryItem.findElements(By.className(categoryName + "-course-name"));
            List<String> courses = courseElements.stream()
                    .map(WebElement::getText)
                    .toList();

            categoryCoursesMap.put(categoryName, courses.toArray(new String[0]));
        }

        return categoryCoursesMap;
    }
}
