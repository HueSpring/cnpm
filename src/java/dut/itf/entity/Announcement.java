/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dut.itf.entity;

import java.util.Date;

public class Announcement {
    private String subject;
    private String content;
    private Date date;
    private int receiver;

    public Announcement(String subject, String content, Date date, int receiver) {
        this.subject = subject;
        this.content = content;
        this.date = date;
        this.receiver = receiver;
    }
    
    public Announcement() {
    }
    
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getReceiver() {
        return receiver;
    }

    public void setReceiver(int receiver) {
        this.receiver = receiver;
    }
    
    
    
}
