package inholland.java.endassignment682474.model;

import java.io.Serial;
import java.time.LocalDate;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

public class Member implements java.io.Serializable{


    @Serial
    private static final long serialVersionUID = -3347972910521345654L;

    public static final AtomicInteger count = new AtomicInteger(0);
    public int id;
    public String firstname;
    public String lastname;
    public LocalDate birthdate;
    public Member(int id,String firstname,String lastname,LocalDate birthdate){
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }
}
