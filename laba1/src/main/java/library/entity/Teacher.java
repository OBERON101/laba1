package library.entity;

public class Teacher extends People {
    private String surname;
    private String department;

    public Teacher(String firstname, String lastname,String surname, String department) {
        super(firstname, lastname);
        this.surname = surname;
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public String toString() {
        return super.toString() + " " + surname;
    }
}
