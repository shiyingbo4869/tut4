package nz.ac.massey.cs159272.ass1.id19023254;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Student implements Serializable {
    private String name;
    private String firstName;
    private String id;
    private Date dob;
    private Course course;
    private Activity activity;
    private Address address;

    /**
     * Constructor
     */
    public Student() {
        address = new Address();
    }

    /**
     * Constructor
     *
     * @param name      name
     * @param firstName firstName
     * @param id        id
     * @param dob       date of birth
     * @param course    course
     * @param activity  activity
     * @param address   address
     */
    public Student(String name, String firstName, String id, Date dob, Course course, Activity activity, Address address) {
        this.name = name;
        this.firstName = firstName;
        this.id = id;
        this.dob = dob;
        this.course = course;
        this.activity = activity;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Clone a student
     *
     * @return the copy of the student
     */
    public Student clone() {
        Student student = new Student();    // create a new Student object
        // shallow copy
        student.name = name;
        student.firstName = firstName;
        student.id = id;
        student.course = course;
        student.activity = activity;

        // deep copy
        student.address = new Address(address.getTown(), address.getStreet(), address.getPostCodeNumber(), address.getHouseNumber());
        if (dob != null) {
            student.dob = new Date(dob.getTime());
        } else {
            student.dob = null;
        }
        return student;
    }

    /**
     * Get the string of date of birth in the format of "yyyy-MM-dd"
     *
     * @return the string of date of birth
     */
    public String getDobString() {
        if (dob == null) {
            return "";
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(dob);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(name, student.name) &&
                Objects.equals(firstName, student.firstName) &&
                Objects.equals(id, student.id) &&
                Objects.equals(dob, student.dob) &&
                Objects.equals(course, student.course) &&
                Objects.equals(activity, student.activity) &&
                Objects.equals(address, student.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, firstName, id, dob, course, activity, address);
    }

    @Override
    public String toString() {
        if (name == null || name.length() == 0) {
            return "No name";
        } else {
            return name;
        }
    }
}
