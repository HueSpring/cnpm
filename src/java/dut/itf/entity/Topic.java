
package dut.itf.entity;


public class Topic {
    
    private String idtopic;
    private String idregister;
    private String idgroup;
    private String nametopic;
    private String description;
    private String guide;

    public Topic() {
    }

    public Topic(String idtopic, String idregister, String idgroup, String nametopic, String description, String guide) {
        this.idtopic = idtopic;
        this.idregister = idregister;
        this.idgroup = idgroup;
        this.nametopic = nametopic;
        this.description = description;
        this.guide = guide;
    }

    public String getIdtopic() {
        return idtopic;
    }

    public void setIdtopic(String idtopic) {
        this.idtopic = idtopic;
    }

    public String getIdregister() {
        return idregister;
    }

    public void setIdregister(String idregister) {
        this.idregister = idregister;
    }

    public String getIdgroup() {
        return idgroup;
    }

    public void setIdgroup(String idgroup) {
        this.idgroup = idgroup;
    }

    public String getNametopic() {
        return nametopic;
    }

    public void setNametopic(String nametopic) {
        this.nametopic = nametopic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGuide() {
        return guide;
    }

    public void setGuide(String guide) {
        this.guide = guide;
    }
    
    
    
}
