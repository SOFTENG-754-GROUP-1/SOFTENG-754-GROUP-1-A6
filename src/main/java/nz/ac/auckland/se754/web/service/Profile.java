package nz.ac.auckland.se754.web.service;

import nz.ac.auckland.se754.web.model.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Profile {
    private final Database db;
    private static final String USERNAME_PATTERN = "^[a-zA-Z0-9]+$";
    private static final Pattern pattern = Pattern.compile(USERNAME_PATTERN);

    public Profile(Database db) {
        this.db = db;
    }

    public Boolean validateUsername(String username) {
        boolean isUsernameTaken = db.checkUsernameExists(username);

        Matcher matcher = pattern.matcher(username);
        boolean isValidUsername = matcher.matches();

        return !isUsernameTaken && isValidUsername;

    }

    public void updateUsername(User user, String username) {
        user.setUsername(username);
    }

    public boolean isImageFile(String fileName) {
        // check if file name ends with .png or .jpg
        String lowerCaseFileName = fileName.toLowerCase();
        return lowerCaseFileName.endsWith(".png") || lowerCaseFileName.endsWith(".jpg");
    }

    public void updateProfilePicture(User user, String profilePicture)  {user.setProfilePicture(profilePicture);}
    public void updateBannerPicture(User user, String bannerPicture) {user.setBannerPicture(bannerPicture);}
    public void updateFlags(User user, String[] flags) {user.setFlags(flags);}
    public void updateStatusMessage(User user, String message) {user.setStatusMessage(message);}
    public void updateTheme(User user, String theme) {user.setTheme(theme);}
}
