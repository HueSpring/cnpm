package dut.itf.entity;

import java.util.Date;

public class OpenClose {

    private int id;
    private Date timeopen;
    private Date dayclose;
    private Date timeclose;
    private Date dayopen;

    public OpenClose() {
    }

    public OpenClose(int id, Date timeopen, Date dayclose, Date timeclose, Date dayopen) {
        this.id = id;
        this.timeopen = timeopen;
        this.dayclose = dayclose;
        this.timeclose = timeclose;
        this.dayopen = dayopen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTimeopen() {
        return timeopen;
    }

    public void setTimeopen(Date timeopen) {
        this.timeopen = timeopen;
    }

    public Date getDayclose() {
        return dayclose;
    }

    public void setDayclose(Date dayclose) {
        this.dayclose = dayclose;
    }

    public Date getTimeclose() {
        return timeclose;
    }

    public void setTimeclose(Date timeclose) {
        this.timeclose = timeclose;
    }

    public Date getDayopen() {
        return dayopen;
    }

    public void setDayopen(Date dayopen) {
        this.dayopen = dayopen;
    }

}
