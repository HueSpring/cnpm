package dut.itf.controller;

import dut.itf.dao.ScheduleDAO;
import dut.itf.entity.GroupHasSchedule;
import dut.itf.entity.Schedule;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;

@ManagedBean
@SessionScoped
public class beanSchedule {

    private ArrayList<Schedule> listSchedule = new ArrayList<>();
    private Schedule schedule = new Schedule();
    private String message;

    public beanSchedule() {
    }

    public ArrayList<Schedule> getListSchedule() {
        return listSchedule;
    }

    public void setListSchedule(ArrayList<Schedule> listSchedule) {
        this.listSchedule = listSchedule;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getIdentify() {
        return schedule.getIdentify();
    }

    public void setIdentify(int identify) {
        schedule.setIdentify(identify);
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public String getIdschedule() {
        return schedule.getIdschedule();
    }

    public void setIdschedule(String idschedule) {
        schedule.setIdschedule(idschedule);
    }

    public String getNameschedule() {
        return schedule.getNameschedule();
    }

    public void setNameschedule(String nameschedule) {
        schedule.setNameschedule(nameschedule);
    }

    public Date getTime() {
        return schedule.getTime();
    }

    public void setTime(Date time) {
        schedule.setTime(time);
    }

    public Date getDate() {
        return schedule.getDate();
    }

    public void setDate(Date date) {
        schedule.setDate(date);
    }

    public String getAddress() {
        return schedule.getAddress();
    }

    public void setAddress(String address) {
        schedule.setAddress(address);
    }

    public String getIdregister() {
        return schedule.getIdregister();
    }

    public void setIdregister(String idregister) {
        schedule.setIdregister(idregister);
    }

    public String prepareCreatSchedule(String idregister) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        schedule.setIdregister(idregister);
        return "createSchedule";
    }

    public void creatNewSchedule() throws SQLException, ClassNotFoundException,
            IllegalAccessException, InstantiationException {
        ScheduleDAO dao = new ScheduleDAO();
        schedule.setIdschedule(schedule.getIdregister() + ".L" + countSchedule(schedule.getIdregister()));
        schedule.setNameschedule("Lịch " + countSchedule(schedule.getIdregister()));
        dao.createSchedule(schedule.getIdschedule(), schedule.getNameschedule(),
                schedule.getTime(), schedule.getDate(), schedule.getAddress(), schedule.getIdregister());
    }

    public int countSchedule(String idregister) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        return getSchedulesByIdRegister(idregister).size() + 1;
    }

    //quantity groups register schedule
    public int countGroupsRS(String idschedule) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        ScheduleDAO dao = new ScheduleDAO();
        return dao.countGroupHasSchedule(idschedule);
    }
    
    public ArrayList<Schedule> getSchedulesByIdRegister(String idregister) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        ScheduleDAO dao = new ScheduleDAO();
        return dao.findScheduleByIdRegister(idregister);
    }

    public ArrayList<Schedule> getSchedulesByIdRegisterIdentify(String idregister) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        ScheduleDAO dao = new ScheduleDAO();
        return dao.findScheduleByIdRegisterIdentified(idregister);
    }

    public ArrayList<GroupHasSchedule> getGHSByIdSchedule(String idschedule) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        ScheduleDAO dao = new ScheduleDAO();
        return dao.findByIdSchedule(idschedule);
    }

    public void updateSchedules(String idschedule1, String idschedule, String nameschedule) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        ScheduleDAO dao = new ScheduleDAO();
        dao.updateSchedule(idschedule1, idschedule, nameschedule);
    }

    public void updateScheduleAgain(String idregister) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        ArrayList<Schedule> list = getSchedulesByIdRegister(idregister);
        for (int i = 0; i < list.size(); i++) {
            String s1 = idregister + ".L" + (i + 1);
            String s2 = "Lịch " + (i + 1);
            updateSchedules(list.get(i).getIdschedule(), s1, s2);
        }
    }

    public void deleteSchedules(String idschedule, String idregister) throws SQLException,
            ClassNotFoundException, IllegalAccessException, InstantiationException {
        ScheduleDAO dao = new ScheduleDAO();
        dao.deleteSchedule(idschedule);
        updateScheduleAgain(idregister);
    }

    public void deleteAllSchedules(String idregister) throws SQLException,
            ClassNotFoundException, IllegalAccessException, InstantiationException {
        ArrayList<Schedule> list = getSchedulesByIdRegister(idregister);
        for (Schedule item : list) {
            deleteSchedules(item.getIdschedule(), idregister);
        }
    }

    public void deleteGroupHasSchedule(String idgroup, String idschedule) throws SQLException,
            ClassNotFoundException, IllegalAccessException, InstantiationException {
        ScheduleDAO dao = new ScheduleDAO();
        dao.deleteGroupHasSchedule(idgroup, idschedule);
    }

    public boolean checkScheduleByIdRegister(String idregister) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        if (getSchedulesByIdRegister(idregister).size() < 1) {
            return false;
        }
        return true;
    }

    public String toStringDate(Date date) throws ParseException {
        SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
        return d.format(date);
    }

    public String toStringTime(Date time) {
        SimpleDateFormat d = new SimpleDateFormat("HH:mm:ss");
        return d.format(time);
    }

    public String toStringDate2(Date date) throws ParseException {
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        return d.format(date);
    }

    public String toStringTimeDate(Date timedate) {
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return d.format(timedate);
    }

    public String getTimeScheduleFinal(String idregister) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException, ParseException {
        ArrayList<Schedule> list = getSchedulesByIdRegister(idregister);
        Schedule entity = new Schedule();
        for (Schedule item : list) {
            entity = item;
        }
        return "Lúc " + entity.getTime() + " - " + toStringDate(entity.getDate());
    }

    public String getAddressScheduleFinal(String idregister) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        ArrayList<Schedule> list = getSchedulesByIdRegister(idregister);
        Schedule entity = new Schedule();
        for (Schedule item : list) {
            entity = item;
        }
        return "Tại " + entity.getAddress();
    }

    public void updateIdentifies(String idregister) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        ScheduleDAO dao = new ScheduleDAO();
        dao.updateIdentify(idregister);
    }

    public void createGroupHasSchedules(String idschedule, String idgroup) throws SQLException,
            ClassNotFoundException, IllegalAccessException, InstantiationException, ParseException {
        ScheduleDAO dao = new ScheduleDAO();
        dao.createGroupHasSchedule(idschedule, idgroup);
    }

    public boolean check(Date time, Date date) throws ParseException {
        Date d = new Date();
        String datetime = toStringTimeDate(d);
        String datetime2 = toStringDate2(date) + " " + toStringTime(time);
        if (datetime.compareTo(datetime2) < 0) {
            return true;
        }
        return false;
    }

    public boolean checkGroupHasSchedule(String idschedule, String idgroup) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        ArrayList<GroupHasSchedule> list = getGHSByIdSchedule(idschedule);
        for (GroupHasSchedule item : list) {
            if (item.getIdgroup().equals(idgroup)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkBoth(Date time, Date date, String idschedule, String idgroup) throws ParseException,
            SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        if (check(time, date) && checkGroupHasSchedule(idschedule, idgroup)) {
            return true;
        }
        return false;
    }

    public boolean checkregister(Date time, Date date, String idschedule, String idgroup) throws ParseException,
            SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        if (check(time, date) && !checkGroupHasSchedule(idschedule, idgroup)) {
            return true;
        }
        return false;
    }

    public void seeList(AjaxBehaviorEvent event) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        listSchedule = getSchedulesByIdRegisterIdentify(schedule.getIdregister());
    }

    //check identify schedule - true: idetify = 1
    public boolean checkIndentify(String idschedule) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        ScheduleDAO dao = new ScheduleDAO();
        Schedule s = dao.findScheduleByIdSchedule(idschedule);
        if (s != null && s.getIdentify() == 1) {
            return true;
        } else {
            return false;
        }
    }
    
}
