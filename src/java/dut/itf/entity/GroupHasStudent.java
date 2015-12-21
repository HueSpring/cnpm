
package dut.itf.entity;


public class GroupHasStudent {
    
    private int idstudent;
    private String idgroup;

    public GroupHasStudent() {
    }

    public GroupHasStudent(int idstudent, String idgroup) {
        this.idstudent = idstudent;
        this.idgroup = idgroup;
    }

    public int getIdstudent() {
        return idstudent;
    }

    public void setIdstudent(int idstudent) {
        this.idstudent = idstudent;
    }

    public String getIdgroup() {
        return idgroup;
    }

    public void setIdgroup(String idgroup) {
        this.idgroup = idgroup;
    }
    
}
