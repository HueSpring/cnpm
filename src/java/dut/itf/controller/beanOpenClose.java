

package dut.itf.controller;

import dut.itf.dao.OpenCloseDAO;
import dut.itf.entity.Close;
import dut.itf.entity.OpenClose;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class beanOpenClose {

    private OpenClose openClose = new OpenClose();
    private Close close = new Close();
    private String message;
    
    public beanOpenClose() {
    }

    public String getIdregister() {
        return close.getIdregister();
    }

    public void setIdregister(String idregister) {
        close.setIdregister(idregister);
    }

    public Date getEndtime() {
        return close.getEndtime();
    }

    public void setEndtime(Date endtime) {
        close.setEndtime(endtime);
    }

    public Close getClose() {
        return close;
    }

    public void setClose(Close close) {
        this.close = close;
    }

    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public OpenClose getOpenClose() {
        return openClose;
    }

    public void setOpenClose(OpenClose openClose) {
        this.openClose = openClose;
    }

    public int getId() {
        return openClose.getId();
    }

    public void setId(int id) {
        openClose.setId(id);
    }

    public Date getTimeopen() {
        return openClose.getTimeopen();
    }

    public void setTimeopen(Date timeopen) {
        openClose.setTimeopen(timeopen);
    }

    public Date getDayclose() {
        return openClose.getDayclose();
    }

    public void setDayclose(Date dayclose) {
        openClose.setDayclose(dayclose);
    }

    public Date getTimeclose() {
        return openClose.getTimeclose();
    }

    public void setTimeclose(Date timeclose) {
        openClose.setTimeclose(timeclose);
    }

    public Date getDayopen() {
        return openClose.getDayopen();
    }

    public void setDayopen(Date dayopen) {
        openClose.setDayopen(dayopen);
    }
    
    public ArrayList<OpenClose> getOpencloses() throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        OpenCloseDAO dao = new OpenCloseDAO();
        return dao.findAll();
    }
    
    public boolean checkAmountOpenClose() throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        OpenCloseDAO dao = new OpenCloseDAO();
        if (dao.count() < 1) {
            return true;
        } else {
            return false;
        }
    }
    
     public boolean checkTime() {
        OpenCloseDAO dao = new OpenCloseDAO();
        Date current = new Date();
        String time = dao.toStringTime(current);
        String date = dao.toStringDate(current);
        String dateopen = dao.toStringDate(openClose.getDayopen());
        String timeopen = dao.toStringTime(openClose.getTimeopen());
        String dateclose = dao.toStringDate(openClose.getDayclose());
        String timeclose = dao.toStringTime(openClose.getTimeclose());

        return ((dateopen.compareTo(date) > 0 || (dateopen.compareTo(date) == 0 && timeopen.compareTo(time) > 0))
                && (dateopen.compareTo(dateclose) < 0 || (dateopen.compareTo(dateclose) == 0 && timeopen.compareTo(timeclose) < 0)));
    }
    
    public void deleteOpenClose(int id) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        OpenCloseDAO dao = new OpenCloseDAO();
        boolean check = dao.delete(id);
        if (check == true) {
            openClose.setId(0);
            openClose.setTimeopen(null);
            openClose.setDayopen(null);
            openClose.setTimeclose(null);
            openClose.setDayclose(null);
            message = "";
        } else {
            message = "";
        }
    }
    
    public void creatOpenClose() throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        message = "";
        if (openClose.getTimeopen() != null
                && openClose.getTimeclose() != null
                && openClose.getDayopen() != null && openClose.getDayclose() != null) {
            if (checkTime()) {
                OpenCloseDAO dao = new OpenCloseDAO();
                boolean check = dao.create(openClose.getTimeopen(),
                        openClose.getDayopen(), openClose.getTimeclose(), openClose.getDayclose());
                if (check == true) {
                    openClose.setTimeopen(null);
                    openClose.setDayopen(null);
                    openClose.setTimeclose(null);
                    openClose.setDayclose(null);
                    message = "";
                } else {
                    message = "Tạo không thành công";
                }
            } else {
                message = "Thời gian không cho phép";
            }
        } else {
            message = "Cần nhập đầy đủ giá trị";
        }
    }
    
    public void creatEndTime(String idregister, Date endtime) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException{
        OpenCloseDAO dao = new OpenCloseDAO();
        dao.createForClose(idregister, endtime);
    }
    

}
