
package dut.itf.entity;

public class GroupHasSchedule {
    
    private String idschedule;
    private String idgroup;

    public GroupHasSchedule() {
    }

    public GroupHasSchedule(String idschedule, String idgroup) {
        this.idschedule = idschedule;
        this.idgroup = idgroup;
    }

    public String getIdschedule() {
        return idschedule;
    }

    public void setIdschedule(String idschedule) {
        this.idschedule = idschedule;
    }

    public String getIdgroup() {
        return idgroup;
    }

    public void setIdgroup(String idgroup) {
        this.idgroup = idgroup;
    }
    
}
