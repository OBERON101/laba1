package library.windows;

import library.LibraryImitator;
import library.entity.People;
import library.entity.Student;
import library.entity.Teacher;
import library.generation.LiteratureGenerator;
import library.entity.Literature;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Window extends JFrame {
    private final LibraryImitator libraryImitator;
    private JPanel panel;
    private JLabel roleSpace;
    private JLabel nameSpace;
    private JList list;
    private JButton deleteButton;
    private JLabel groupOrChairLabel;
    private JScrollPane Window;
    private People people;
    private LiteratureGenerator literatureGenerator;
    private final Map<String, String> getMethods = new LinkedHashMap<>() {
        {
            put("getAuthor", "Автор: ");
            put("getLang", "Язык: ");
            put("getTitle", "Название: ");
            put("getLevel", "Уровень:");
            put("getParam", "Параметр:");
            put("getDiscipline", "Дисциплина:");
        }
    };

    public Window(People people, LibraryImitator libraryImitator) {
        setContentPane(panel);
        setTitle("Читательский билет");
        setSize(500, 300);

        this.people = people;
        this.libraryImitator = libraryImitator;

        fillTag();
        fillBookList();
        addListeners();

        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void fillTag() {
        if (people.getClass() == Student.class) {
            Student student = (Student) people;
            roleSpace.setText("Студент");
            groupOrChairLabel.setText("Группа: " + student.getGroup());
            nameSpace.setText(student.getFirstname() + " " + student.getLastname());
        } else {
            Teacher teacher = (Teacher) people;
            roleSpace.setText("Преподаватель");
            groupOrChairLabel.setText("Кафедра: " + teacher.getDepartment());
            nameSpace.setText(teacher.getFirstname() + " " + teacher.getLastname() + " " + teacher.getSurname());
        }
    }

    private void fillBookList() {
        Set<Literature> set = libraryImitator.getPeopleBooks(people);
        DefaultListModel<Literature> model = new DefaultListModel<>();

        for (Literature literature : set) {
            model.addElement(literature);
        }
        list.setModel(model);
    }


    public String getMessageForLiterature(Literature literature) {
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append(literature.getTitle()).append("\n");

        Set<String> methodNames = new HashSet<>();
        for (Method method : literature.getClass().getMethods()) {
            methodNames.add(method.getName());
        }

        for (Map.Entry<String, String> entry : getMethods.entrySet()) {
            String methodName = entry.getKey();
            String methodDescription = entry.getValue();
            if (methodNames.contains(methodName)) {
                messageBuilder.append(methodDescription);
                try {
                    Method method = literature.getClass().getMethod(methodName);
                    Object result = method.invoke(literature);
                    messageBuilder.append(result).append("\n");
                } catch (NoSuchMethodException | IllegalAccessException |
                         InvocationTargetException ignored) {
                }
            }
        }

        return messageBuilder.toString();
    }

    private void addListeners() {
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedIndex = list.getSelectedIndex();
                    if (selectedIndex != -1) {
                        String message = getMessageForLiterature((Literature) list.getModel().getElementAt(selectedIndex));
                        JOptionPane.showMessageDialog(Window.this, message);
                    }
                }
            }
        });
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    deleteButton.setEnabled(list.getSelectedIndex() != -1);
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                libraryImitator.returnBook(people, (Literature) list.getModel().getElementAt(list.getSelectedIndex()));
                fillBookList();
            }
        });
    }
}
