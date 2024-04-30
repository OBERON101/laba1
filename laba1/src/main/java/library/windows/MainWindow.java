package library.windows;

import library.LibraryImitator;
import library.entity.People;
import library.entity.Student;
import library.entity.Teacher;
import library.generation.PeopleGenerator;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;


public class MainWindow extends JFrame {
    private JPanel panel;
    private JTree mainTree;
    private final Window[] chosenWindow = {null};
    private final PeopleGenerator peopleGenerator;
    private final LibraryImitator libraryImitator;

    public MainWindow() {
        peopleGenerator = new PeopleGenerator();
        libraryImitator = new LibraryImitator();
        setContentPane(panel);
        setTitle("Библиотека");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void openMainWindow() {
        addListeners();
        fillTree();
    }

    private void fillTree() {
        DefaultTreeModel treeModel = (DefaultTreeModel) mainTree.getModel();
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Пользователи");
        DefaultMutableTreeNode studentNode = new DefaultMutableTreeNode("Студенты");
        DefaultMutableTreeNode teacherNode = new DefaultMutableTreeNode("Преподаватели");

        List<Student> students = peopleGenerator.generateStudent(21);
        students.forEach(student -> {
            DefaultMutableTreeNode studentTreeNode = new DefaultMutableTreeNode(student);
            studentNode.add(studentTreeNode);
        });
        List<Teacher> teachers = peopleGenerator.generateTeacher(10);
        teachers.forEach(teacher -> {
            DefaultMutableTreeNode teacherTreeNode = new DefaultMutableTreeNode(teacher);
            teacherNode.add(teacherTreeNode);
        });

        List<People> allPeoples = new ArrayList<>();
        allPeoples.addAll(students);
        allPeoples.addAll(teachers);
        libraryImitator.randomFillingMap(allPeoples);
        root.add(studentNode);
        root.add(teacherNode);
        treeModel.setRoot(root);
    }

    private void addListeners() {
        mainTree.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() != 2) { return; }
                TreePath selectionPath = mainTree.getSelectionPath();
                if (selectionPath == null) { return; }

                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) selectionPath.getLastPathComponent();
                String nodeText = selectedNode.getUserObject().toString();
                if (nodeText.equals("Пользователи") || nodeText.equals("Студенты") || nodeText.equals("Преподаватели")) {
                    return;
                }

                if (chosenWindow[0] != null) {
                    chosenWindow[0].setVisible(false);
                    chosenWindow[0].dispose();
                }

                Point currentLocation = getLocation();
                int currentX = (int) currentLocation.getX();
                int currentY = (int) currentLocation.getY();

                chosenWindow[0] = new Window((People) selectedNode.getUserObject(), libraryImitator);
                chosenWindow[0].setLocation(currentX + getWidth(), currentY);
                chosenWindow[0].setVisible(true);
                chosenWindow[0].addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        chosenWindow[0] = null;
                    }
                });
            }
        });
    }
}
