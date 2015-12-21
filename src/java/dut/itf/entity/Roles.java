
package dut.itf.entity;


public class Roles {
    
    private String idaccount;
    private int role;

    public Roles() {
    }

    public Roles(String idaccount, int role) {
        this.idaccount = idaccount;
        this.role = role;
    }

    public String getIdaccount() {
        return idaccount;
    }

    public void setIdaccount(String idaccount) {
        this.idaccount = idaccount;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
    
}
