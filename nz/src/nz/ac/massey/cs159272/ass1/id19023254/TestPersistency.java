package nz.ac.massey.cs159272.ass1.id19023254;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * TestPersistency class
 */
public class TestPersistency {
    @BeforeClass
    public static void setUpBeforClass() throws Exception {

    }

    @AfterClass
    public static void setUpAfterClass() throws Exception {

    }

    /**
     * Test save and load methods
     */
    @Test
    public void testSaveAndLoad() {
        Address address = new Address("town", "street", 1, 1);
        Course course1 = new Course("Course0001", "course0001");
        Activity activity1 = new Activity("activity0001", "type0001");

        Course course2 = new Course("Course0002", "course0002");
        Activity activity2 = new Activity("activity0002", "type0002");

        Student student1 = new Student("Student1", "Student1", "0001", new Date(), course1, activity1, address);
        Student student2 = new Student("Student2", "Student2", "0002", new Date(), course1, activity1, address);
        Student student3 = new Student("Student3", "Student3", "0003", new Date(), course2, activity2, address);

        List<Student> list1 = new ArrayList<>();
        list1.add(student1);
        list1.add(student2);
        list1.add(student3);
        try {
            StudentStorage.save(list1, "testFile");
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Student> list2 = null;
        try {
            list2 = StudentStorage.load(new File("testFile"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(list1.size(), list2.size());

        for (int i = 0; i < list1.size(); i++) {
            assertEquals(list1.get(i), list2.get(i));
        }

        // check referential integrity
        assertSame(list1.get(0).getCourse(), list1.get(1).getCourse());
        assertSame(list1.get(0).getActivity(), list1.get(1).getActivity());
        assertSame(list2.get(0).getCourse(), list2.get(1).getCourse());
        assertSame(list2.get(0).getActivity(), list2.get(1).getActivity());
    }

    /**
     * Test load method with exception
     *
     * @throws IOException            IOException
     * @throws ClassNotFoundException ClassNotFoundException
     */
    @Test(expected = IOException.class)
    public void testException() throws IOException, ClassNotFoundException {
        StudentStorage.load(new File("notExistFile"));
    }
}
