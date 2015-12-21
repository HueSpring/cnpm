
package dut.itf.entity;


public class Group {

    private String idgroup;
    private String namegroup;
    private String idregister;
    private int idofleader;

    public Group() {
    }

    public Group(String idgroup, String namegroup, String idregister, int idofleader) {
        this.idgroup = idgroup;
        this.namegroup = namegroup;
        this.idregister = idregister;
        this.idofleader = idofleader;
    }

    public String getIdgroup() {
        return idgroup;
    }

    public void setIdgroup(String idgroup) {
        this.idgroup = idgroup;
    }

    public String getNamegroup() {
        return namegroup;
    }

    public void setNamegroup(String namegroup) {
        this.namegroup = namegroup;
    }

    public String getIdregister() {
        return idregister;
    }

    public void setIdregister(String idregister) {
        this.idregister = idregister;
    }

    public int getIdofleader() {
        return idofleader;
    }

    public void setIdofleader(int idofleader) {
        this.idofleader = idofleader;
    }
    
}
