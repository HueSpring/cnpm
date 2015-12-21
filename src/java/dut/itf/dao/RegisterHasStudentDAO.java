
package dut.itf.dao;

import dut.itf.entity.RegisterHasStudent;
import dut.itf.entity.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RegisterHasStudentDAO {

    public RegisterHasStudentDAO() {
    }
    
    public ArrayList<RegisterHasStudent> findRegisterHasStudentByIdProject(String idproject) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT rs.* FROM register_has_student rs, register r "
                    + "WHERE rs.idregister = r.idregister AND r.idproject = '" + idproject + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                ArrayList<RegisterHasStudent> list = new ArrayList<>();
                while (rs.next()) {
                    RegisterHasStudent entity = new RegisterHasStudent();
                    entity.setIdregister(rs.getString("idregister"));
                    entity.setIdstudent(rs.getInt("idstudent"));
                    list.add(entity);
                }
                return list;
            }
        }
    }
    
    public ArrayList<RegisterHasStudent> findRegisteHasStudentByIdRegister(String idregister) throws SQLException, 
           ClassNotFoundException, InstantiationException, IllegalAccessException{
       try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT rs.* FROM register_has_student rs WHERE rs.idregister = '" + idregister + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ArrayList<RegisterHasStudent> list = new ArrayList<>();
                ResultSet rs = ps.executeQuery(sql);
                while (rs.next()) {
                    RegisterHasStudent entity = new RegisterHasStudent();
                    entity.setIdregister(rs.getString("idregister"));
                    entity.setIdstudent(rs.getInt("idstudent"));
                    list.add(entity);
                }
                return list;
            }
        }
   }
    
    public int countStudentByIdRegister(String idregister) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT COUNT(DISTINCT idstudent) FROM register_has_student "
                    + "WHERE idregister = '" + idregister + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                int i = 0;
                while (rs.next()) {
                    i = rs.getInt("COUNT(DISTINCT idstudent)");
                }
                return i;
            }
        }
    }
    
     public ArrayList<RegisterHasStudent> findRegisterHasStudentByIdStudent(int idstudent) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT rs.* FROM register_has_student rs WHERE rs.idstudent = '" + idstudent + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ArrayList<RegisterHasStudent> list = new ArrayList<>();
                ResultSet rs = ps.executeQuery(sql);
                while (rs.next()) {
                    RegisterHasStudent entity = new RegisterHasStudent();
                    entity.setIdregister(rs.getString("idregister"));
                    entity.setIdstudent(rs.getInt("idstudent"));
                    list.add(entity);
                }
                return list;
            }
        }
    }

    public boolean create(String idregister, int idstudent) throws SQLException, 
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            Date date = new Date();
            String sql = "INSERT INTO register_has_student(idregister, idstudent) VALUES('"
                    + idregister + "','" + idstudent + "')";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                return ps.executeUpdate() > 0;
            }
        }
    }

   public void delete(int idstudent, String idregiter) throws SQLException, 
           ClassNotFoundException, InstantiationException, IllegalAccessException{
       try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "DELETE FROM register_has_student WHERE idstudent = '" + idstudent + "'"
                    + " AND idregister = '" + idregiter + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.executeUpdate();
            }
        }
   }
   
   public ArrayList<RegisterHasStudent> findAll() throws SQLException, 
           ClassNotFoundException, InstantiationException, IllegalAccessException{
       try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT rs.* FROM register_has_student rs";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ArrayList<RegisterHasStudent> list = new ArrayList<>();
                ResultSet rs = ps.executeQuery(sql);
                while (rs.next()) {
                    RegisterHasStudent entity = new RegisterHasStudent();
                    entity.setIdregister(rs.getString("idregister"));
                    entity.setIdstudent(rs.getInt("idstudent"));
                    list.add(entity);
                }
                return list;
            }
        }
   }
   
   Student student = new Student();
   StudentDAO sD = new StudentDAO();
   public ArrayList<Student> lisfOfStudentInRegister(String registerID) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "select idstudent from register_has_student where idregister = '"+registerID+"'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ArrayList<Student> studentList = new ArrayList<>();
                ResultSet rs = ps.executeQuery(sql);
                while (rs.next()){
                    student = sD.findStudentByIdStudent(rs.getInt("idstudent"));
                    studentList.add(student);
                }
                return studentList;
            }
        }
    }
}
