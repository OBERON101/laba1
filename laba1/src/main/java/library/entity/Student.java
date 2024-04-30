package library.entity;

public class Student extends People {
    private String group;
    public Student(String firstname, String lastname, String group) {
        super(firstname, lastname);
        this.group = group;
    }
    public String getGroup() {
        return group;
    }
}
