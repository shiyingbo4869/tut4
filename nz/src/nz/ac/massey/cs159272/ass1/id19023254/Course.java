package nz.ac.massey.cs159272.ass1.id19023254;

import java.io.Serializable;
import java.util.Objects;

/**
 * Course class
 */
public class Course implements Serializable {
    private String number;
    private String name;

    /**
     * Constructor
     */
    public Course() {
    }

    /**
     * Constructor
     *
     * @param number number
     * @param name   name
     */
    public Course(String number, String name) {
        this.number = number;
        this.name = name;
    }

    /**
     * Getter of number
     *
     * @return course number
     */
    public String getNumber() {
        return number;
    }

    /**
     * Setter of number
     *
     * @param number course number
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * Getter of name
     *
     * @return course name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter of name
     *
     * @param name course name
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(number, course.number) &&
                Objects.equals(name, course.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, name);
    }

    @Override
    public String toString() {
        return name + " " + number;
    }
}
