package nz.ac.massey.cs159272.ass1.id19023254;

import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;

/**
 * TestCloningStudents class
 */
public class TestCloningStudents {
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

    }

    @AfterClass
    public static void setUpAfterClass() throws Exception {

    }

    /**
     * Test clone method
     */
    @Test
    public void testClone() {
        Address address = new Address("town", "street", 1234, 5678);
        Course course = new Course("Course01", "course");
        Activity activity = new Activity("activity", "typeA");
        Student student = new Student("Fred Chia", "Fred", "00001", new Date(), course, activity, address);

        Student copy = student.clone();
        assertEquals(copy, student);

        assertSame(copy.getCourse(), student.getCourse());
        assertSame(copy.getActivity(), student.getActivity());
        assertNotSame(copy.getAddress(), student.getAddress());
        assertNotSame(copy.getDob(), student.getDob());
    }
}
