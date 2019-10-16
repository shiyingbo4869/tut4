package nz.ac.massey.cs159272.ass1.id19023254;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentListEditor extends JFrame implements ActionListener {
    // to store students, courses and activities
    private List<Student> students = new ArrayList<>();
    private List<Course> courses = new ArrayList<>();
    private List<Activity> activities = new ArrayList<>();

    // all components
    private JPanel main_panel = new JPanel();

    private JPanel panel_toolbar = new JPanel();
    private JButton btn_exit = new JButton("Exit");
    private JButton btn_load = new JButton("Load");
    private JButton btn_save = new JButton("Save");
    private JButton btn_addStudent = new JButton("Add Student");
    private JButton btn_cloneStudent = new JButton("Clone Student");
    private JButton btn_addCourse = new JButton("Add Course");
    private JButton btn_addActivity = new JButton("Add Activity");

    private JList<Student> list_students = new JList<>();

    private JPanel panel_table = new JPanel();
    private JLabel lbl_id = new JLabel("id:", JLabel.RIGHT);
    private JLabel lbl_firstName = new JLabel("first name:", JLabel.RIGHT);
    private JLabel lbl_name = new JLabel("name:", JLabel.RIGHT);
    private JLabel lbl_dateOfBirth = new JLabel("date of birth (yyyy-MM-dd):", JLabel.RIGHT);
    private JLabel lbl_address = new JLabel("address:", JLabel.RIGHT);
    private JLabel lbl_course = new JLabel("course:", JLabel.RIGHT);
    private JLabel lbl_activity = new JLabel("activity:", JLabel.RIGHT);
    private JTextField txt_id = new JTextField();
    private JTextField txt_firstName = new JTextField();
    private JTextField txt_name = new JTextField();
    private JTextField txt_dateOfBirth = new JTextField();
    private JPanel panel_address = new JPanel();
    private JLabel lbl_addressInfo = new JLabel();
    private JButton btn_address = new JButton("Modify");
    private JComboBox<Course> combo_courses = new JComboBox<>();
    private JComboBox<Activity> combo_activities = new JComboBox<>();
    private JButton btn_apply = new JButton("Apply");

    /**
     * Set the layout of all components
     */
    private void initLayout() {
        setTitle("Student Editor");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // main panel
        main_panel.setBorder(new EmptyBorder(0, 20, 0, 20));
        main_panel.setLayout(new BorderLayout(0, 0));
        getContentPane().add(main_panel);

        // tool bar
        panel_toolbar.setLayout(new FlowLayout(FlowLayout.LEFT));

        panel_toolbar.add(btn_exit);
        panel_toolbar.add(btn_load);
        panel_toolbar.add(btn_save);
        panel_toolbar.add(btn_addStudent);
        panel_toolbar.add(btn_cloneStudent);
        panel_toolbar.add(btn_addActivity);
        panel_toolbar.add(btn_addCourse);
        main_panel.add(BorderLayout.NORTH, panel_toolbar);
        main_panel.add(BorderLayout.WEST, new JScrollPane(list_students));

        // the grid panel
        panel_table.setLayout(new GridLayout(8, 2));
        panel_table.add(lbl_id);
        panel_table.add(txt_id);
        panel_table.add(lbl_firstName);
        panel_table.add(txt_firstName);
        panel_table.add(lbl_name);
        panel_table.add(txt_name);
        panel_table.add(lbl_dateOfBirth);
        panel_table.add(txt_dateOfBirth);
        panel_table.add(lbl_address);
        panel_address.setLayout(new FlowLayout());
        panel_address.add(lbl_addressInfo);
        panel_address.add(btn_address);
        panel_table.add(panel_address);
        panel_table.add(lbl_course);
        panel_table.add(combo_courses);
        panel_table.add(lbl_activity);
        panel_table.add(combo_activities);
        panel_table.add(new JLabel(""));
        panel_table.add(btn_apply);

        // add listeners
        btn_exit.addActionListener(this);
        btn_load.addActionListener(this);
        btn_save.addActionListener(this);
        btn_addCourse.addActionListener(this);
        btn_apply.addActionListener(this);
        btn_cloneStudent.addActionListener(this);
        btn_addActivity.addActionListener(this);
        btn_address.addActionListener(this);
        btn_addStudent.addActionListener(this);

        // JList of students
        list_students.setFixedCellWidth(150);
        list_students.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list_students.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Student student = list_students.getSelectedValue();
                if (student != null) {
                    txt_firstName.setText(student.getFirstName());
                    txt_name.setText(student.getName());
                    txt_dateOfBirth.setText(student.getDobString());
                    txt_id.setText(student.getId());
                    lbl_addressInfo.setText(student.getAddress().toString());
                    for (int i = 0; i < activities.size(); i++) {
                        if (activities.get(i).equals(student.getActivity())) {
                            combo_activities.setSelectedIndex(i);
                        }
                    }
                    for (int i = 0; i < courses.size(); i++) {
                        if (courses.get(i).equals(student.getCourse())) {
                            combo_courses.setSelectedIndex(i);
                        }
                    }
                }
            }
        });

        // initialize
        list_students.setListData(students.toArray(new Student[students.size()]));
        list_students.setSelectedIndex(0);

        main_panel.add(BorderLayout.CENTER, panel_table);

        // paint the frame
        setVisible(true);
    }

    /**
     * Constructor
     */
    public StudentListEditor() {
        initLayout();
    }

    /**
     * Load from the file
     */
    private void loadFile() {
        // create a JFilChooser object
        JFileChooser chooser = new JFileChooser();
        chooser.showDialog(new JLabel(), "Choose");
        File file = chooser.getSelectedFile();
        if (file != null) {
            // load from the file
            try {
                students = StudentStorage.load(file);
            } catch (Exception e) {
                // do nothing
            }
            activities.clear();
            courses.clear();
            combo_courses.removeAllItems();
            combo_activities.removeAllItems();
            // get all courses and activities
            for (Student student : students) {
                if (!activities.contains(student.getActivity())) {
                    activities.add(student.getActivity());
                    combo_activities.addItem(student.getActivity());
                }
                if (!courses.contains(student.getCourse())) {
                    courses.add(student.getCourse());
                    combo_courses.addItem(student.getCourse());
                }
            }
            // update the data
            list_students.setListData(students.toArray(new Student[students.size()]));
            list_students.setSelectedIndex(0);
        }
    }

    /**
     * Save to the file
     */
    private void saveFile() {
        String filename = JOptionPane.showInputDialog("Enter a file name: ");
        try {
            StudentStorage.save(students, filename);
        } catch (IOException e) {
            // do nothing
        }
    }

    /**
     * Add a new course
     */
    private void addCourse() {
        // create a new frame
        JFrame frame = new JFrame("Add Course");
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        JLabel lbl_number = new JLabel("Course Number", JLabel.RIGHT);
        JTextField txt_number = new JTextField();
        JLabel lbl_name = new JLabel("Course Name", JLabel.RIGHT);
        JTextField txt_name = new JTextField();
        JButton btn_add = new JButton("Add");

        frame.add(txt_number);
        frame.add(txt_name);
        frame.add(btn_add);
        frame.add(lbl_number);
        frame.add(lbl_name);

        lbl_number.setBounds(100, 30, 100, 50);
        txt_number.setBounds(200, 40, 100, 30);

        lbl_name.setBounds(100, 100, 100, 50);
        txt_name.setBounds(200, 110, 100, 30);

        btn_add.setBounds(150, 180, 100, 30);

        frame.setVisible(true);
        btn_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // add new course and close the frame
                String number = txt_number.getText();
                String name = txt_name.getText();
                if (number.length() > 0 && name.length() > 0) {
                    Course course = new Course(number, name);
                    if (!courses.contains(course)) {
                        courses.add(course);
                        combo_courses.addItem(course);
                    }
                    frame.dispose();
                }
            }
        });
    }

    /**
     * Modify the address of a student
     */
    private void modifyAddress() {
        // create a new frame
        JFrame frame = new JFrame("Modify Address");
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(400, 500);
        frame.setLocationRelativeTo(null);

        int index = list_students.getSelectedIndex();
        if (index != -1) {
            Student student = students.get(index);
            JLabel lbl_town = new JLabel("Town", JLabel.RIGHT);
            JTextField txt_town = new JTextField(student.getAddress().getTown());
            JLabel lbl_street = new JLabel("Street", JLabel.RIGHT);
            JTextField txt_street = new JTextField(student.getAddress().getStreet());
            JLabel lbl_houseNumber = new JLabel("House Number", JLabel.RIGHT);
            JTextField txt_houseNumber = new JTextField(student.getAddress().getHouseNumber());
            JLabel lbl_postCodeNumber = new JLabel("Postcode Number", JLabel.RIGHT);
            JTextField txt_postCodeNumber = new JTextField(student.getAddress().getPostCodeNumber());
            JButton btn_confirm = new JButton("Confirm");

            frame.add(lbl_town);
            frame.add(txt_town);
            frame.add(lbl_street);
            frame.add(txt_street);
            frame.add(lbl_houseNumber);
            frame.add(txt_houseNumber);
            frame.add(lbl_postCodeNumber);
            frame.add(txt_postCodeNumber);
            frame.add(btn_confirm);

            lbl_town.setBounds(100, 30, 100, 50);
            txt_town.setBounds(200, 40, 100, 30);

            lbl_street.setBounds(100, 100, 100, 50);
            txt_street.setBounds(200, 110, 100, 30);

            lbl_houseNumber.setBounds(100, 170, 100, 50);
            txt_houseNumber.setBounds(200, 170, 100, 30);

            lbl_postCodeNumber.setBounds(50, 240, 150, 50);
            txt_postCodeNumber.setBounds(200, 240, 100, 30);

            btn_confirm.setBounds(150, 330, 100, 30);

            frame.setVisible(true);
            btn_confirm.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // save the modified information and close the frame
                    String town = txt_town.getText();
                    String street = txt_street.getText();
                    int houseNumber = Integer.parseInt(txt_houseNumber.getText());
                    int postCodeNumber = Integer.parseInt(txt_postCodeNumber.getText());
                    if (town.length() > 0 && street.length() > 0) {
                        int index = list_students.getSelectedIndex();
                        Student student = students.get(index);
                        student.getAddress().setTown(town);
                        student.getAddress().setStreet(street);
                        student.getAddress().setHouseNumber(houseNumber);
                        student.getAddress().setPostCodeNumber(postCodeNumber);
                        list_students.setListData(students.toArray(new Student[students.size()]));
                        list_students.setSelectedIndex(index);
                        frame.dispose();
                    }
                }
            });
        }
    }

    /**
     * Add a new student and select it to modify the information
     */
    private void addStudent() {
        students.add(new Student());
        list_students.setListData(students.toArray(new Student[students.size()]));
        list_students.setSelectedIndex(students.size() - 1);
    }

    /**
     * Add a new activity
     */
    private void addActivity() {
        JFrame frame = new JFrame("Add Activity");
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        JLabel lbl_name = new JLabel("Activity Name", JLabel.RIGHT);
        JTextField txt_name = new JTextField();
        JLabel lbl_type = new JLabel("Activity Type", JLabel.RIGHT);
        JTextField txt_type = new JTextField();
        JButton btn_add = new JButton("Add");

        frame.add(txt_name);
        frame.add(txt_type);
        frame.add(btn_add);
        frame.add(lbl_type);
        frame.add(lbl_name);

        lbl_name.setBounds(100, 30, 100, 50);
        txt_name.setBounds(200, 40, 100, 30);

        lbl_type.setBounds(100, 100, 100, 50);
        txt_type.setBounds(200, 110, 100, 30);

        btn_add.setBounds(150, 180, 100, 30);

        frame.setVisible(true);
        btn_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = txt_name.getText();
                String type = txt_type.getText();
                if (name.length() > 0 && type.length() > 0) {
                    Activity activity = new Activity(name, type);
                    if (!activities.contains(activity)) {
                        activities.add(activity);
                        combo_activities.addItem(activity);
                    }
                    frame.dispose();
                }
            }
        });
    }

    /**
     * Apply the modification on the information of a student
     */
    private void applyModify() {
        int index = list_students.getSelectedIndex();
        if (index != -1) {
            Student student = students.get(index);
            student.setId(txt_id.getText());
            student.setFirstName(txt_firstName.getText());
            student.setName(txt_name.getText());
            String[] parts = txt_dateOfBirth.getText().split("-");
            student.setDob(new Date(Integer.parseInt(parts[0]) - 1900, Integer.parseInt(parts[1]) - 1, Integer.parseInt(parts[2])));
            student.setCourse(courses.get(combo_courses.getSelectedIndex()));
            student.setActivity(activities.get(combo_activities.getSelectedIndex()));

            list_students.setListData(students.toArray(new Student[students.size()]));
            list_students.setSelectedIndex(index);
        }
    }

    /**
     * Clone a selected student, automatically select the new student to modify the information
     */
    private void cloneStudent() {
        int index = list_students.getSelectedIndex();
        if (index != -1) {
            Student student = students.get(index);
            Student copy = student.clone();
            students.add(copy);
            list_students.setListData(students.toArray(new Student[students.size()]));
            list_students.setSelectedIndex(students.size() - 1);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Load")) {
            loadFile();
        } else if (command.equals("Save")) {
            saveFile();
        } else if (command.equals("Exit")) {
            System.exit(0);
        } else if (command.equals("Add Student")) {
            addStudent();
        } else if (command.equals("Clone Student")) {
            cloneStudent();
        } else if (command.equals("Add Activity")) {
            addActivity();
        } else if (command.equals("Add Course")) {
            addCourse();
        } else if (command.equals("Modify")) {
            modifyAddress();
        } else if (command.equals("Apply")) {
            applyModify();
        }
    }

    public static void main(String[] args) {
        StudentListEditor editor = new StudentListEditor();
    }
}
