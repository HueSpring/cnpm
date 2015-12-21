

package dut.itf.dao;

import dut.itf.entity.OpenClose;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class OpenCloseDAO {

    public OpenCloseDAO() {
    }
    
    public boolean create(Date ot, Date od, Date ct, Date cd) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "INSERT INTO open_close(timeopen, dateopen, timeclose, dateclose) VALUES('"
                    + toStringTime(ot) + "','" + toStringDate(od) + "','"
                    + toStringTime(ct) + "','" + toStringDate(cd) + "')";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                return ps.executeUpdate() > 0;
            }
        }
    }

    public boolean delete(int id) throws SQLException, ClassNotFoundException,
            InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "DELETE FROM open_close WHERE id = '" + id + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                return ps.executeUpdate() > 0;
            }
        }
    }

    public boolean update(int id, Date ot, Date od, Date ct, Date cd) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "UPDATE open_close SET timeopen = '" + toStringTime(ot) + "',dateopen = '" + toStringDate(od)
                    + "',timeclose = '" + toStringTime(ct) + "',dateclose = '" + toStringDate(cd)
                    + "' WHERE id = '" + id + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                return ps.executeUpdate() > 0;
            }
        }
    }

    public String toStringDate(Date date) {
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        return d.format(date);
    }

    public String toStringTime(Date time) {
        SimpleDateFormat d = new SimpleDateFormat("HH:mm:ss");
        return d.format(time);
    }
    
    public String toStringTimeDate(Date timedate) {
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return d.format(timedate);
    }
    
    public OpenClose findAllById(int id) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT * FROM open_close WHERE id = '" + id + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                OpenClose entity = new OpenClose();
                while (rs.next()) {
                    entity.setId(rs.getInt("id"));
                    entity.setTimeopen(rs.getTime("timeopen"));
                    entity.setDayopen(rs.getDate("dateopen"));
                    entity.setTimeclose(rs.getTime("timeclose"));
                    entity.setDayclose(rs.getDate("dateclose"));
                }
                return entity;
            }
        }
    }

    public ArrayList<OpenClose> findAll() throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT * FROM open_close";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                ArrayList<OpenClose> list = new ArrayList<>();
                while (rs.next()) {
                    OpenClose entity = new OpenClose();
                    entity.setId(rs.getInt("id"));
                    entity.setTimeopen(rs.getTime("timeopen"));
                    entity.setDayopen(rs.getDate("dateopen"));
                    entity.setTimeclose(rs.getTime("timeclose"));
                    entity.setDayclose(rs.getDate("dateclose"));
                    list.add(entity);
                }
                return list;
            }
        }
    }

    public int count() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT COUNT(*) FROM open_close";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                int i = 0;
                while (rs.next()) {
                    i = rs.getInt("COUNT(*)");
                }
                return i;
            }
        }
    }
    
    public boolean createForClose(String idregister, Date endtime) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException{
         try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "INSERT INTO close(idregister, endtime) VALUES('"
                    + idregister + "','" + toStringTimeDate(endtime) + "')";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                return ps.executeUpdate() > 0;
            }
        }
    }
}
