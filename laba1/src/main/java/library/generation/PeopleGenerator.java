package library.generation;

import com.github.javafaker.Faker;
import library.entity.Student;
import library.entity.Teacher;

import java.util.*;

public class PeopleGenerator {
    private Faker faker = new Faker(new Locale("ru"));

    public List<Student> generateStudent(int numStudents) {
        List<Student> students = new ArrayList<>();

        for (int i = 0; i < numStudents; i++) {
            String name = faker.name().firstName();
            String lastname = faker.name().lastName();
            String group = faker.bothify("Группа ?##-###");
            Student student = new Student(name, lastname, group);
            students.add(student);
        }
        students.sort((o1, o2) -> o1.toString().compareToIgnoreCase(o2.toString()));
        return students;
    }

    public List<Teacher> generateTeacher(int numTeachers) {
        List<Teacher> teachers = new ArrayList<>();

        for (int i = 0; i < numTeachers; i++) {
            String name = faker.name().firstName();
            String lastname = faker.name().lastName();
            String surname = faker.name().lastName();
            String department = getDepartments().get(new Random().nextInt(getDepartments().size()));
            Teacher teacher = new Teacher(name, lastname, surname, department);
            teachers.add(teacher);
        }
        teachers.sort((o1, o2) -> o1.toString().compareToIgnoreCase(o2.toString()));
        return teachers;
    }

    private List<String> getDepartments() {

        return List.of("Кафедра теоретической и экспериментальной физики ядерных реакторов (№5)",
                "Кафедра радиационной физики и безопасности атомных технологий (№1)",
                "Кафедра автоматики (№2)",
                "Кафедра экспериментальных методов ядерной физики (№11)",
                "Кафедра теплофизики (№13)",
                "Кафедра физического воспитания (№15)",
                "Кафедра конструирования приборов и установок (№18)",
                "Кафедра физических проблем материаловедения (№9)",
                "Кафедра стратегического планирования и методологии управления (№82) ",
                "Кафедра экономики и менеджмента в промышленности (№71)",
                "Кафедра управления бизнес-проектами (№72)",
                "Кафедра финансового мониторинга (№75)",
                "Кафедра иностранных языков (№50)",
                "Кафедра русского языка как иностранного (№49)",
                "Кафедра высшей математики (№30)",
                "Кафедра общей физики (№6)"
        );
    }
}
