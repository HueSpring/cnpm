/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dut.itf.dao;

import dut.itf.entity.Announcement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Date;

public class AnnouncementDao {

    public AnnouncementDao() {
    }

    public boolean create(String subject, String content, Date date, int receiver) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "insert into announcement (subject, content, date, receiver) values ('" + subject + "','" + content + "','" + toStringDate(date) + "','" + receiver + "')";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                return ps.executeUpdate() > 0;
            }
        }
    }

    public String toStringDate(Date date) {
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        return d.format(date);
    }

    public ArrayList<Announcement> getAllAnnouncement(int receiver) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT * FROM announcement where receiver = " + receiver + " ORDER BY date DESC";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ArrayList<Announcement> list = new ArrayList<>();
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    list.add(this.createAnnouncement(rs));
                }
                return list;
            }
        }
    }

    public ArrayList<Announcement> getLast10Announcement(int receiver) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT * FROM announcement where receiver = " + receiver + " ORDER BY announceID DESC LIMIT 10";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ArrayList<Announcement> list = new ArrayList<>();
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    list.add(this.createAnnouncement(rs));
                }
                return list;
            }
        }
    }

    public Announcement createAnnouncement(ResultSet rs) throws SQLException {
        Announcement announcement = new Announcement(rs.getString("subject"), rs.getString("content"), rs.getDate("date"), rs.getInt("receiver"));
        return announcement;
    }

}
