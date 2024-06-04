package nz.ac.auckland.se754.web.controller;

import nz.ac.auckland.se754.web.model.User;
import nz.ac.auckland.se754.web.service.Database;
import nz.ac.auckland.se754.web.service.Profile;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@Controller
public class ProfileController {
    private final User dummyUser = Mockito.mock(User.class);
    private final Database mockDB = Mockito.mock(Database.class);
    private final Profile service = new Profile(mockDB);

    // Constructor to set up new user and initialize services
    public ProfileController() {
        // Initialize user profile with preset values
        Mockito.when(dummyUser.getUsername()).thenReturn("currentUsername");
        Mockito.when(dummyUser.getProfilePicture()).thenReturn("/profile.png");
        Mockito.when(dummyUser.getBannerPicture()).thenReturn("/banner.png");
        Mockito.when(dummyUser.getFlags()).thenReturn(new String[] {"New Zealand", "Australia"});
        Mockito.when(dummyUser.getStatusMessage()).thenReturn("current status message");
        Mockito.when(dummyUser.getTheme()).thenReturn("Dark");
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String showProfilePage(ModelMap model) {
        updateModelAttributes(model);
        return "profile";
    }

    private void updateModelAttributes(ModelMap model) {
        // Populate the model with user profile information
        model.addAttribute("username", dummyUser.getUsername());
        model.addAttribute("profilePicture", dummyUser.getProfilePicture());
        model.addAttribute("bannerPicture", dummyUser.getBannerPicture());
        List<String> flags = Arrays.asList(dummyUser.getFlags());
        model.addAttribute("flags", flags);
        model.addAttribute("statusMessage", dummyUser.getStatusMessage());
        model.addAttribute("theme", dummyUser.getTheme());
    }

    @RequestMapping(value = "/changeUsername", method = RequestMethod.POST)
    public String changeUsername(ModelMap model, @RequestParam String newUsername, HttpSession session) {
        Mockito.when(mockDB.checkUsernameExists("user1")).thenReturn(false);

        boolean isValidUsername = service.validateUsername(newUsername);

        if (isValidUsername) {
            service.updateUsername(dummyUser, newUsername);
            model.addAttribute("alertMessage", "Your username has been changed to " + newUsername);
        } else {
            model.addAttribute("alertMessage", "Username is taken or is invalid");
        }

        return "profile";
    }

    @RequestMapping(value = "/uploadProfilePicture", method = RequestMethod.POST)
    public String changeProfilePicture(ModelMap model, @RequestParam String newProfilePicture, HttpSession session) {

        if (!service.isImageFile(newProfilePicture)) {
            model.addAttribute("alertMessage", "Invalid file, please upload a valid image file");
            return "profile";
        }

        service.updateProfilePicture(dummyUser, newProfilePicture);
        model.addAttribute("alertMessage", "Your profile picture has been changed successfully");
        return "profile";
    }

    @RequestMapping(value = "/uploadBannerPicture", method = RequestMethod.POST)
    public String changeBannerPicture(ModelMap model, @RequestParam String newBannerPicture, HttpSession session) {

        if (!service.isImageFile(newBannerPicture)) {
            model.addAttribute("alertMessage", "Invalid file, please upload a valid image file");
            return "profile";
        }

        service.updateBannerPicture(dummyUser, newBannerPicture);
        model.addAttribute("alertMessage", "Your banner picture has been changed successfully");
        return "profile";
    }

    @RequestMapping(value = "/updateFlags", method = RequestMethod.POST)
    public String changeFlags(ModelMap model, @RequestParam(value = "flags", required = false) String[] flags, HttpSession session) {
        if (flags != null) {
            System.out.println("Selected flags: " + String.join(", ", flags));
            service.updateFlags(dummyUser, flags);
        } else {
            service.updateFlags(dummyUser, new String[] {});
        }
        model.addAttribute("alertMessage", "Your flags have been changed successfully");

        return "profile";
    }

    @RequestMapping(value = "/changeStatusMessage", method = RequestMethod.POST)
    public String changeStatusMessage(ModelMap model, @RequestParam String statusMessage, HttpSession session) {
        model.addAttribute("alertMessage", "Your status message has been changed successfully");
        service.updateStatusMessage(dummyUser, statusMessage);
        return "profile";
    }

    @RequestMapping(value = "/changeTheme", method = RequestMethod.POST)
    public String changeTheme(ModelMap model, @RequestParam("theme") String theme, HttpSession session) {
        model.addAttribute("alertMessage", "Your theme has been changed to " + theme);
        service.updateTheme(dummyUser, theme);
        return "profile";
    }

    @RequestMapping(value = "/profile/removeProfilePicture", method = RequestMethod.POST)
    public ResponseEntity<String> removeProfilePicture(ModelMap model, HttpSession session) {
        service.updateProfilePicture(dummyUser, "");
        Mockito.when(dummyUser.getProfilePicture()).thenReturn("");
        updateModelAttributes(model);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/profile/removeBannerPicture", method = RequestMethod.POST)
    public ResponseEntity<String> removeBannerPicture(ModelMap model, HttpSession session) {
        service.updateBannerPicture(dummyUser, "");
        Mockito.when(dummyUser.getBannerPicture()).thenReturn("");
        updateModelAttributes(model);
        return ResponseEntity.ok().build();
    }

}
