/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dut.itf.controller;

import dut.itf.dao.AnnouncementDao;
import dut.itf.entity.Announcement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped

public class beanAnnouncerment {
    
    private Announcement announcement = new Announcement();
    String message;
    
    
    public beanAnnouncerment() {
    }

    public Announcement getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(Announcement announcement) {
        this.announcement = announcement;
    }

    
    public String getSubject() {
        return announcement.getSubject();
    }

    public void setSubject(String subject) {
        announcement.setSubject(subject);
    }

    public String getContent() {
        return announcement.getContent();
    }

    public void setContent(String content) {
        announcement.setContent(content);
    }

    public Date getDate() {
        return announcement.getDate();
    }

    public void setDate(Date date) {
        announcement.setDate(date);
    }

    public int getReceiver() {
        return announcement.getReceiver();
    }

    public void setReceiver(int receiver) {
        announcement.setReceiver(receiver);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
   
    public void annouceToStudent() throws SQLException, 
            ClassNotFoundException, InstantiationException, IllegalAccessException{
        AnnouncementDao aDao = new AnnouncementDao();
        announcement.setReceiver(2);
        Date date = new Date();
        announcement.setDate(date);
        aDao.create(announcement.getSubject(), 
                announcement.getContent(), announcement.getDate(), announcement.getReceiver());
        this.clearAnnouncement();
    }
    
    public void annouceToLecturer() throws SQLException, 
            ClassNotFoundException, InstantiationException, IllegalAccessException{
        AnnouncementDao aDao = new AnnouncementDao();
        announcement.setReceiver(1);
        Date date = new Date();
        announcement.setDate(date);
        aDao.create(announcement.getSubject(),
                announcement.getContent(), announcement.getDate(), announcement.getReceiver());
        this.clearAnnouncement();
    }
    
    public String toStringDate(Date date) throws ParseException {
        SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
        return d.format(date);
    }
    
    public ArrayList<Announcement> getAllAnnouncementForLecturer() throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException{
        AnnouncementDao aDao = new AnnouncementDao();
        return aDao.getAllAnnouncement(1);
    }
    
    public ArrayList<Announcement> getTenAnnouncementForLecturer() throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException{
        AnnouncementDao aDao = new AnnouncementDao();
        return aDao.getLast10Announcement(1);
    }
    
    public ArrayList<Announcement> getAllAnnouncementForStudent() throws SQLException, 
            ClassNotFoundException, InstantiationException, IllegalAccessException{
        AnnouncementDao aDao = new AnnouncementDao();
        return aDao.getAllAnnouncement(2);
    }
    
    public ArrayList<Announcement> getTenAnnouncementForStudent() throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException{
        AnnouncementDao aDao = new AnnouncementDao();
        return aDao.getLast10Announcement(2);
    }
    
    public void clearAnnouncement(){
        this.announcement.setSubject(null);
        this.announcement.setContent(null);
    }
    
}
