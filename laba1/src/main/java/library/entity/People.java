package library.entity;

public class People {
    private String firstname;
    private String lastname;

    public People(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    @Override
    public String toString() {
        return lastname + " " + firstname;
    }
}


