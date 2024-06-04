package nz.ac.auckland.se754.web.controller;

import nz.ac.auckland.se754.web.model.User;
import nz.ac.auckland.se754.web.service.Database;
import nz.ac.auckland.se754.web.service.Profile;
import org.mockito.Mockito;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class ProfileController {

    private final User dummyUser = Mockito.mock(User.class);
    private final Database mockDB = Mockito.mock(Database.class);
    private final Profile service = new Profile(mockDB);

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String showProfilePage() {
        return "profile";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
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

}
