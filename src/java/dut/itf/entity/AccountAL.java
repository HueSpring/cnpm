
package dut.itf.entity;

import java.util.Date;


public class AccountAL {
    private String idaccount;
    private String password;
    private String name;
    private Date dob;
    private String degree;
    private String address;
    private String phone;
    private String email;

    public AccountAL() {
    }

    public AccountAL(String idaccount, String password, String name, Date dob, String degree, String address, String phone, String email) {
        this.idaccount = idaccount;
        this.password = password;
        this.name = name;
        this.dob = dob;
        this.degree = degree;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public String getIdaccount() {
        return idaccount;
    }

    public void setIdaccount(String idaccount) {
        this.idaccount = idaccount;
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

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
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
