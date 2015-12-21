

package dut.itf.entity;

public class ProjectHasStudent {
    private String idproject;
    private String idstudent;

    public ProjectHasStudent() {
    }

    public ProjectHasStudent(String idproject, String idstudent) {
        this.idproject = idproject;
        this.idstudent = idstudent;
    }

    public String getIdproject() {
        return idproject;
    }

    public void setIdproject(String idproject) {
        this.idproject = idproject;
    }

    public String getIdstudent() {
        return idstudent;
    }

    public void setIdstudent(String idstudent) {
        this.idstudent = idstudent;
    }
    
}
