
package dut.itf.entity;


public class Project {
    private String idproject;
    private String name;

    public Project() {
    }

    public Project(String idproject, String name) {
        this.idproject = idproject;
        this.name = name;
    }

    public String getIdproject() {
        return idproject;
    }

    public void setIdproject(String idproject) {
        this.idproject = idproject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
