
package dut.itf.entity;

import java.util.Date;


public class Schedule {
    
    private String idschedule;
    private String nameschedule;
    private Date time;
    private Date date;
    private String address;
    private String idregister;
    private int identify;

    public Schedule() {
    }

    public Schedule(String idschedule, String nameschedule, Date time, Date date, String address, String idregister, int identify) {
        this.idschedule = idschedule;
        this.nameschedule = nameschedule;
        this.time = time;
        this.date = date;
        this.address = address;
        this.idregister = idregister;
        this.identify = identify;
    }

    public String getIdschedule() {
        return idschedule;
    }

    public int getIdentify() {
        return identify;
    }

    public void setIdentify(int identify) {
        this.identify = identify;
    }

    public void setIdschedule(String idschedule) {
        this.idschedule = idschedule;
    }

    public String getNameschedule() {
        return nameschedule;
    }

    public void setNameschedule(String nameschedule) {
        this.nameschedule = nameschedule;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdregister() {
        return idregister;
    }

    public void setIdregister(String idregister) {
        this.idregister = idregister;
    }
    
}
