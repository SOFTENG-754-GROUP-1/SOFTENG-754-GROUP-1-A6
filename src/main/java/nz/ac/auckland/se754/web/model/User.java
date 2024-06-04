package nz.ac.auckland.se754.web.model;

import nz.ac.auckland.se754.web.backend.exception.NoUserFoundException;
import nz.ac.auckland.se754.web.backend.exception.PrivateProgressException;
import nz.ac.auckland.se754.web.service.LearningProgressManager;
import nz.ac.auckland.se754.web.backend.model.Course;
import nz.ac.auckland.se754.web.backend.model.CourseTags;

import java.util.ArrayList;

public class User {
    LearningProgress learningProgress;
    public String username;
    Course[] courses;
    public String profilePicture;
    public String bannerPicture;
    public String[] flags;
    public String statusMessage;
    public String theme;

    public User(String username){
        this.username = username;
        this.learningProgress = new LearningProgress();
        LearningProgressManager.startLearningProgress(this.username, learningProgress);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getBannerPicture() {
        return bannerPicture;
    }
    public void setBannerPicture(String bannerPicture) {
        this.bannerPicture = bannerPicture;
    }

    public String[] getFlags() {
        return flags;
    }

    public void setFlags(String[] flags) {
        this.flags = flags;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String message) {
        this.statusMessage = message;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public void setCourses(Course[] courses){
        this.courses = courses;
    }

    public LearningProgress getLearningProgress(){
        return  learningProgress;
    }

    public ArrayList<CourseTags> getCourseTags(){
        ArrayList<CourseTags> interestedCourses = new ArrayList<>();
        if (courses != null) {
            for (Course course : courses) {
                CourseTags courseTags = course.getTagsForCourse();
                if (courseTags.isInterested) {
                    interestedCourses.add(courseTags);
                }
            }
        }
        return interestedCourses;
    }

    public ArrayList<CourseTags> getInProgressCourses(){
        ArrayList<CourseTags> inProgressCourses = new ArrayList<>();
        if (courses != null) {
            for (Course course : courses) {
                CourseTags courseTags = course.getTagsForCourse();
                if (courseTags.getProgress().equals("In Progress") || courseTags.getProgress().equals("Completed")) {
                    inProgressCourses.add(courseTags);
                }
            }
        }
        return inProgressCourses;
    }
}
