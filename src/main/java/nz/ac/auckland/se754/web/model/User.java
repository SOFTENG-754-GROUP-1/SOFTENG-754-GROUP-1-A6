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

    public User(String username){
        this.username = username;
        LearningProgressManager.startLearningProgress(this.username);
        try {
            learningProgress = LearningProgressManager.getLearningProgress(this.username);
        } catch (NoUserFoundException | PrivateProgressException e) {
            throw new RuntimeException(e);
        }
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