
package dut.itf.entity;

import java.util.Date;


public class Close {
    private String idregister;
    private Date endtime;

    public Close() {
    }

    public Close(String idregister, Date endtime) {
        this.idregister = idregister;
        this.endtime = endtime;
    }

    public String getIdregister() {
        return idregister;
    }

    public void setIdregister(String idregister) {
        this.idregister = idregister;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }
    
}
