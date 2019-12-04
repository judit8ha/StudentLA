package edu.utep.cs.cs4330.sla;

import java.util.ArrayList;
import java.util.List;

public class User {
    public String name;
    public String email;
    public int id;
    public List<Course> courses = new ArrayList<Course>();

    public User() {
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Course> getCourses() {
        return (ArrayList<Course>) courses;
    }

    public void setCourse(Course course) {
        courses.add(course);
    }
}
