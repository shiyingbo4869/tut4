package nz.ac.massey.cs159272.ass1.id19023254;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentStorage {
    /**
     * Save a list of students in a file
     *
     * @param students a list of students
     * @param file     the file to output
     * @throws IOException IOException
     */
    public static void save(List<Student> students, File file) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
        objectOutputStream.writeInt(students.size());
        for (Student student : students) {
            objectOutputStream.writeObject(student);
        }
        objectOutputStream.close();
    }

    /**
     * Save a list of students in a file by given file name
     *
     * @param students a list of students
     * @param fileName the file name
     * @throws IOException IOException
     */
    public static void save(List<Student> students, String fileName) throws IOException {
        save(students, new File(fileName));
    }

    /**
     * Load a list of students from the given file
     *
     * @param file the given file
     * @return a list of students restored from the given file
     * @throws IOException            IOException
     * @throws ClassNotFoundException ClassNotFoundException
     */
    public static List<Student> load(File file) throws IOException, ClassNotFoundException {
        List<Student> students = new ArrayList<>();
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
        int numOfStudents = objectInputStream.readInt();
        for (int i = 0; i < numOfStudents; i++) {
            students.add((Student) objectInputStream.readObject());
        }

        return students;
    }
}
