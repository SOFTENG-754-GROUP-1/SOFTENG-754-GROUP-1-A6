package nz.ac.auckland.se754.web.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class ProfilePage {
    private final WebDriver driver;
    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.ID, using = "newUsername")
    private WebElement newUsername;

    @FindBy(how = How.ID, using = "changeUsernameBtn")
    private WebElement changeUsernameButton;

    @FindBy(how = How.ID, using = "alertMessage")
    private WebElement alertMessage;

    @FindBy(how = How.ID, using = "newProfilePicture")
    private WebElement newProfilePicture;

    @FindBy(how = How.ID, using = "changeProfilePictureBtn")
    private WebElement changeProfilePictureButton;

    @FindBy(how = How.ID, using = "newBannerPicture")
    private WebElement newBannerPicture;

    @FindBy(how = How.ID, using = "changeBannerPictureBtn")
    private WebElement changeBannerPictureButton;

    @FindBy(how = How.ID, using = "changeFlagsBtn")
    private WebElement changeFlagsButton;

    @FindBy(how = How.ID, using = "flagNZ")
    private WebElement flagNZ;

    @FindBy(how = How.ID, using = "flagAustralia")
    private WebElement flagAustralia;

    @FindBy(how = How.ID, using = "flagChina")
    private WebElement flagChina;

    @FindBy(how = How.ID, using = "flagUSA")
    private WebElement flagUSA;

    @FindBy(how = How.ID, using = "flagUK")
    private WebElement flagUK;

    @FindBy(how = How.ID, using = "statusMessage")
    private WebElement statusMessage;

    @FindBy(how = How.ID, using = "changeStatusBtn")
    private WebElement changeStatusButton;

    @FindBy(how = How.ID, using = "theme")
    private WebElement themeDropdown;

    @FindBy(how = How.ID, using = "changeThemeBtn")
    private WebElement changeThemeButton;

    @FindBy(how = How.ID, using = "username")
    private WebElement username;

    @FindBy(how = How.ID, using = "profilePicture")
    private WebElement profilePicture;

    @FindBy(how = How.ID, using = "bannerPicture")
    private WebElement bannerPicture;

    @FindBy(how = How.ID, using = "removeProfilePictureBtn")
    private WebElement removeProfilePictureButton;

    @FindBy(how = How.ID, using = "removeBannerPictureBtn")
    private WebElement removeBannerPictureButton;


    public void enterNewUsername(String newUsername) {
        this.newUsername.sendKeys(newUsername);
    }

    public void clickChangeUsernameButton() {
        this.changeUsernameButton.click();
    }

    public String getAlertText() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 2);
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            return alert.getText();
        } catch (NoAlertPresentException e) {
            return null;
        }
    }

    public void uploadNewProfilePicture(String profilePicture) {
        this.newProfilePicture.sendKeys(profilePicture);
    }

    public void clickChangeProfilePictureButton() {
        this.changeProfilePictureButton.click();
    }

    public void uploadNewBannerPicture(String bannerPicture) {
        newBannerPicture.sendKeys(bannerPicture);
    }

    public void clickChangeBannerPictureButton() {
        this.changeBannerPictureButton.click();
    }

    public void selectFlags(String[] flags) {
        for (String flag : flags) {
            switch (flag) {
                case "New Zealand" -> {
                    if (!flagNZ.isSelected()) {
                        flagNZ.click();
                    }
                }
                case "Australia" -> {
                    if (!flagAustralia.isSelected()) {
                        flagAustralia.click();
                    }
                }
                case "China" -> {
                    if (!flagChina.isSelected()) {
                        flagChina.click();
                    }
                }
                case "USA" -> {
                    if (!flagUSA.isSelected()) {
                        flagUSA.click();
                    }
                }
                case "UK" -> {
                    if (!flagUK.isSelected()) {
                        flagUK.click();
                    }
                }
            }
        }
    }


    public void clickChangeFlagsButton() {
        this.changeFlagsButton.click();
    }

    public void enterStatusMessage(String message) {
        this.statusMessage.sendKeys(message);
    }

    public void clickChangeStatusButton() {
        this.changeStatusButton.click();
    }

    public void selectTheme(String theme) {
        Select dropdown = new Select(this.themeDropdown);
        dropdown.selectByVisibleText(theme);
    }

    public void clickChangeThemeButton() {
        this.changeThemeButton.click();
    }

    public String getUsernameText() {
        return username.getText();
    }

    public String getProfilePicture() {
        return profilePicture.getText();
    }

    public String getBannerPicture() {
        return bannerPicture.getText();
    }

    public String[] getCurrentFlags() {
        List<String> selectedFlags = new ArrayList<>();

        if (flagNZ.isSelected()) {
            selectedFlags.add("New Zealand");
        }
        if (flagAustralia.isSelected()) {
            selectedFlags.add("Australia");
        }
        if (flagChina.isSelected()) {
            selectedFlags.add("China");
        }
        if (flagUSA.isSelected()) {
            selectedFlags.add("USA");
        }
        if (flagUK.isSelected()) {
            selectedFlags.add("UK");
        }

        return selectedFlags.toArray(new String[0]);
    }
    public String getStatusMessage() {
        return statusMessage.getAttribute("value");
    }

    public String getCurrentTheme() {
        Select dropdown = new Select(this.themeDropdown);
        return dropdown.getFirstSelectedOption().getText();
    }

    public void clickRemoveProfilePictureButton() {
        removeProfilePictureButton.click();
    }

    public void clickRemoveBannerPictureButton() {
        removeBannerPictureButton.click();
    }

}
