
package dut.itf.entity;


public class RegisterHasStudent {
    private String idregister;
    private int idstudent;

    public RegisterHasStudent() {
    }

    public RegisterHasStudent(String idregister, int idstudent) {
        this.idregister = idregister;
        this.idstudent = idstudent;
    }

    public String getIdregister() {
        return idregister;
    }

    public void setIdregister(String idregister) {
        this.idregister = idregister;
    }

    public int getIdstudent() {
        return idstudent;
    }

    public void setIdstudent(int idstudent) {
        this.idstudent = idstudent;
    }

   
}
