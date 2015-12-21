
package dut.itf.entity;

import java.util.Date;


public class Student {
    private int idstudent;
    private String password;
    private String name;
    private String classes;
    private Date dob;
    private String address;
    private String phone;
    private String email;

    public Student() {
    }

    public Student(int idstudent, String password, String name, String classes, Date dob, String address, String phone, String email) {
        this.idstudent = idstudent;
        this.password = password;
        this.name = name;
        this.classes = classes;
        this.dob = dob;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public int getIdstudent() {
        return idstudent;
    }

    public void setIdstudent(int idstudent) {
        this.idstudent = idstudent;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
