package nz.ac.auckland.se754.web.model;

public class Category {
    private String name;
    private final String[] courses;

    public Category(String name, String[] courses) {
        this.name = name;
        this.courses = courses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getCourses() {
        return courses;
    }
}