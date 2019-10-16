package nz.ac.massey.cs159272.ass1.id19023254;

import java.io.Serializable;
import java.util.Objects;

/**
 * Activity class
 */
public class Activity implements Serializable {
    private String name;    // activity name
    private String type;    // activity type

    /**
     * Constructor
     */
    public Activity() {
    }

    /**
     * Constructor
     * @param name name
     * @param type type
     */
    public Activity(String name, String type) {
        this.name = name;
        this.type = type;
    }

    /**
     * Getter of name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter of name
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter of type
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * Setter of type
     * @param type type
     */
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activity activity = (Activity) o;
        return Objects.equals(name, activity.name) &&
                Objects.equals(type, activity.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type);
    }

    @Override
    public String toString() {
        return name + " " + type;
    }
}
