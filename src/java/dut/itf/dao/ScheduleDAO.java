
package dut.itf.dao;

import dut.itf.entity.GroupHasSchedule;
import dut.itf.entity.Schedule;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class ScheduleDAO {

    public ScheduleDAO() {
    }

    public boolean createSchedule(String idschedule, String nameschedule,
            Date time, Date date, String address, String idregister) throws SQLException, 
            ClassNotFoundException, IllegalAccessException, InstantiationException{
         try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "INSERT INTO schedule (idschedule, nameschedule, time, date, address, idregister) "
                    + "VALUES('" + idschedule + "','" + nameschedule 
                    + "','" + toStringTime(time) + "','" + toStringDate(date) + "','" + address + "','" + idregister + "')";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                return ps.executeUpdate() > 0;
            }
        }
    }
    
    public boolean createGroupHasSchedule(String idschedule, String idgroup) throws SQLException, 
            ClassNotFoundException, IllegalAccessException, InstantiationException{
         try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "INSERT INTO group_has_schedule (idschedule, idgroup) "
                    + "VALUES('" + idschedule + "','" + idgroup + "')";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                return ps.executeUpdate() > 0;
            }
        }
    }
    
    public boolean updateSchedule(String idschedule1, String idschedule, String nameschedule) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "UPDATE schedule SET idschedule = '" + idschedule + "',nameschedule = '" + nameschedule + 
                    "' WHERE idschedule = '" + idschedule1 + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                return ps.executeUpdate() > 0;
            }
        }
    }
    
    public boolean updateIdentify(String idregister) throws SQLException, 
            ClassNotFoundException, InstantiationException, IllegalAccessException{
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "UPDATE schedule SET identify = '" + 1 + "' WHERE idregister = '" + idregister + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                return ps.executeUpdate() > 0;
            }
        }
    }
    
    public boolean deleteSchedule(String idschedule) throws SQLException, 
            ClassNotFoundException, IllegalAccessException, InstantiationException{
         try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "DELETE FROM schedule WHERE idschedule = '" + idschedule + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                return ps.executeUpdate() > 0;
            }
        }
    }
    
    public boolean deleteGroupHasSchedule(String idgroup, String idschedule) throws SQLException, 
            ClassNotFoundException, IllegalAccessException, InstantiationException{
         try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "DELETE FROM group_has_schedule WHERE idgroup = '" + idgroup + "' "
                    + "AND idschedule = '" + idschedule + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                return ps.executeUpdate() > 0;
            }
        }
    }
    
    public ArrayList<Schedule> findAllSchedules() throws SQLException, 
            ClassNotFoundException, InstantiationException, IllegalAccessException{
         try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT * FROM schedule";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ArrayList<Schedule> list = new ArrayList<>();
                ResultSet rs = ps.executeQuery(sql);
                while (rs.next()) {
                    Schedule entity = new Schedule();
                    entity.setIdschedule(rs.getString("idschedule"));
                    entity.setNameschedule(rs.getString("nameschedule"));
                    entity.setTime(rs.getTime("time"));
                    entity.setDate(rs.getDate("date"));
                    entity.setAddress(rs.getString("address"));
                    entity.setIdentify(rs.getInt("identify"));
                    entity.setIdregister(rs.getString("idregister"));
                    list.add(entity);
                }
                return list;
            }
        }
    }
    
    public Schedule findScheduleByIdSchedule(String idschedule) throws SQLException, 
            ClassNotFoundException, InstantiationException, IllegalAccessException{
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT * FROM schedule WHERE idschedule = '" + idschedule + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                Schedule entity = new Schedule();
                ResultSet rs = ps.executeQuery(sql);
                while (rs.next()) {
                    entity.setIdschedule(rs.getString("idschedule"));
                    entity.setNameschedule(rs.getString("nameschedule"));
                    entity.setTime(rs.getTime("time"));
                    entity.setDate(rs.getDate("date"));
                    entity.setAddress(rs.getString("address"));
                    entity.setIdentify(rs.getInt("identify"));
                    entity.setIdregister(rs.getString("idregister"));
                }
                return entity;
            }
        }
    }
    
    public ArrayList<Schedule> findScheduleByIdRegister(String idregister) throws SQLException, 
            ClassNotFoundException, InstantiationException, IllegalAccessException{
         try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT * FROM schedule WHERE idregister = '" + idregister + "' ORDER BY date ASC";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ArrayList<Schedule> list = new ArrayList<>();
                ResultSet rs = ps.executeQuery(sql);
                while (rs.next()) {
                    Schedule entity = new Schedule();
                    entity.setIdschedule(rs.getString("idschedule"));
                    entity.setNameschedule(rs.getString("nameschedule"));
                    entity.setTime(rs.getTime("time"));
                    entity.setDate(rs.getDate("date"));
                    entity.setAddress(rs.getString("address"));
                    entity.setIdregister(rs.getString("idregister"));
                    entity.setIdentify(rs.getInt("identify"));
                    list.add(entity);
                }
                return list;
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
    
    public ArrayList<Schedule> findScheduleByIdRegisterIdentified(String idregister) throws SQLException, 
            ClassNotFoundException, InstantiationException, IllegalAccessException{
         try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT * FROM schedule WHERE idregister = '" + idregister + "' AND identify = '1' ORDER BY date ASC";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ArrayList<Schedule> list = new ArrayList<>();
                ResultSet rs = ps.executeQuery(sql);
                while (rs.next()) {
                    Schedule entity = new Schedule();
                    entity.setIdschedule(rs.getString("idschedule"));
                    entity.setNameschedule(rs.getString("nameschedule"));
                    entity.setTime(rs.getTime("time"));
                    entity.setDate(rs.getDate("date"));
                    entity.setAddress(rs.getString("address"));
                    entity.setIdregister(rs.getString("idregister"));
                    entity.setIdentify(rs.getInt("identify"));
                    list.add(entity);
                }
                return list;
            }
        }
    }
    
    public ArrayList<GroupHasSchedule> findByIdSchedule(String idschedule) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException{
         try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT * FROM group_has_schedule WHERE idschedule = '" + idschedule + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ArrayList<GroupHasSchedule> list = new ArrayList<>();
                ResultSet rs = ps.executeQuery(sql);
                while (rs.next()) {
                    GroupHasSchedule entity = new GroupHasSchedule();
                    entity.setIdschedule(rs.getString("idschedule"));
                    entity.setIdgroup(rs.getString("idgroup"));
                    list.add(entity);
                }
                return list;
            }
        }
    }
    
    public int countGroupHasSchedule(String idschedule) throws SQLException, 
            ClassNotFoundException, InstantiationException, IllegalAccessException{
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT COUNT(idgroup) FROM group_has_schedule WHERE idschedule = '" + idschedule + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery(sql);
                int count = 0;
                while (rs.next()) {
                    count = rs.getInt("COUNT(idgroup)");
                }
                return count;
            }
        }
    }
    
    
    
}

