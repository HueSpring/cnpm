

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

public class StudentDAO {

    public StudentDAO() {
    }
    
    public void createForOneStudent(String pass, int id) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "UPDATE student SET password = '" + pass + "' WHERE idstudent = '" + id + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.executeUpdate();
            }
        }
    }

    public boolean createForAllStudents() throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        ArrayList<Student> list = findStudentsNoPass();
        for (Student item : list) {
            createForOneStudent(Integer.toString(item.getIdstudent()), item.getIdstudent());
        }
        return true;
    }
    
    public boolean updatePassword(String pass, int idstudent) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "UPDATE student SET password = '" + pass + "' WHERE idstudent = '" + idstudent + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                return ps.executeUpdate() > 0;
            }
        }
    }

    public boolean update(Date dob, String classes,
            String address, String phone, String email, int id) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "UPDATE student SET  dob = '" + toString(dob) + "', class = '" + classes + "', address = '" + address
                    + "', phone = '" + phone
                    + "', email = '" + email + "' WHERE idstudent = '" + id + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                return ps.executeUpdate() > 0;
            }
        }
    }
    
     public ArrayList<Student> findAllStudents() throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT * FROM student";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ArrayList<Student> list = new ArrayList<>();
                ResultSet rs = ps.executeQuery(sql);
                while (rs.next()) {
                    Student entity = new Student();
                    entity.setIdstudent(rs.getInt("idstudent"));
                    entity.setPassword(rs.getString("password"));
                    entity.setName(rs.getString("name"));
                    entity.setClasses(rs.getString("class"));
                    entity.setDob(rs.getDate("dob"));
                    entity.setAddress(rs.getString("address"));
                    entity.setPhone(rs.getString("phone"));
                    entity.setEmail(rs.getString("email"));
                    list.add(entity);
                }
                return list;
            }
        }
    }

    public Student findStudentByIdStudent(int idstudent) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT * FROM student WHERE idstudent = '" + idstudent + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                Student entity = new Student();
                ResultSet rs = ps.executeQuery(sql);
                while (rs.next()) {
                    entity.setIdstudent(rs.getInt("idstudent"));
                    entity.setPassword(rs.getString("password"));
                    entity.setName(rs.getString("name"));
                    entity.setClasses(rs.getString("class"));
                    entity.setDob(rs.getDate("dob"));
                    entity.setAddress(rs.getString("address"));
                    entity.setPhone(rs.getString("phone"));
                    entity.setEmail(rs.getString("email"));
                }
                return entity;
            }
        }
    }
    
    public ArrayList<Student> findStudentsByIdProject(String idproject) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT s.* FROM student s, project_has_student ps "
                    + "WHERE ps.idstudent = s.idstudent AND ps.idproject = '" + idproject + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ArrayList<Student> list = new ArrayList<>();
                ResultSet rs = ps.executeQuery(sql);
                while (rs.next()) {
                    Student entity = new Student();
                    entity.setIdstudent(rs.getInt("idstudent"));
                    entity.setPassword(rs.getString("password"));
                    entity.setName(rs.getString("name"));
                    entity.setClasses(rs.getString("class"));
                    entity.setDob(rs.getDate("dob"));
                    entity.setAddress(rs.getString("address"));
                    entity.setPhone(rs.getString("phone"));
                    entity.setEmail(rs.getString("email"));
                    list.add(entity);
                }
                return list;
            }
        }
    }
    
    public ArrayList<Student> findStudentsByIdRegister(String idregister) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT s.* FROM student s, register_has_student rs WHERE s.idstudent = rs.idstudent"
                    + " AND rs.idregister = '" + idregister + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ArrayList<Student> list = new ArrayList<>();
                ResultSet rs = ps.executeQuery(sql);
                while (rs.next()) {
                    Student entity = new Student();
                    entity.setIdstudent(rs.getInt("idstudent"));
                    entity.setPassword(rs.getString("password"));
                    entity.setName(rs.getString("name"));
                    entity.setClasses(rs.getString("class"));
                    entity.setDob(rs.getDate("dob"));
                    entity.setAddress(rs.getString("address"));
                    entity.setPhone(rs.getString("phone"));
                    entity.setEmail(rs.getString("email"));
                    list.add(entity);
                }
                return list;
            }
        }
    }
    
    public ArrayList<Student> findStudentsByIdGroup(String idgroup) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT s.* FROM student s, group_has_student gs WHERE s.idstudent = gs.idstudent"
                    + " AND gs.idgroup = '" + idgroup + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ArrayList<Student> list = new ArrayList<>();
                ResultSet rs = ps.executeQuery(sql);
                while (rs.next()) {
                    Student entity = new Student();
                    entity.setIdstudent(rs.getInt("idstudent"));
                    entity.setPassword(rs.getString("password"));
                    entity.setName(rs.getString("name"));
                    entity.setClasses(rs.getString("class"));
                    entity.setDob(rs.getDate("dob"));
                    entity.setAddress(rs.getString("address"));
                    entity.setPhone(rs.getString("phone"));
                    entity.setEmail(rs.getString("email"));
                    list.add(entity);
                }
                return list;
            }
        }
    }

    public ArrayList<Student> findStudentsNoPass() throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT DISTINCT s.* FROM student s, project_has_student ps "
                    + "WHERE s.idstudent = ps.idstudent AND s.password is null";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ArrayList<Student> list = new ArrayList<>();
                ResultSet rs = ps.executeQuery(sql);
                while (rs.next()) {
                    Student entity = new Student();
                    entity.setIdstudent(rs.getInt("idstudent"));
                    entity.setPassword(rs.getString("password"));
                    entity.setName(rs.getString("name"));
                    entity.setClasses(rs.getString("class"));
                    entity.setDob(rs.getDate("dob"));
                    entity.setAddress(rs.getString("address"));
                    entity.setPhone(rs.getString("phone"));
                    entity.setEmail(rs.getString("email"));
                    list.add(entity);
                }
                return list;
            }
        }
    }

    public String toString(Date date) {
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        return d.format(date);
    }

    

    public int countStudentByIdRegister(String idregister) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT COUNT(DISTINCT idstudent) FROM register_has_student "
                    + "WHERE register_has_student.idregister = '" + idregister + "'";
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

    public int countStudentByIdProject(String idproject) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT COUNT(DISTINCT idstudent) FROM project_has_student "
                    + "WHERE project_has_student.idproject = '" + idproject + "'";
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


    public ArrayList<Student> findStudentsNotRegister() throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException{
         try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT DISTINCT s.* FROM student s, project_has_student ps"
                    + " WHERE s.idstudent = ps.idstudent"
                    + " AND (s.idstudent NOT IN (SELECT DISTINCT s.idstudent "
                    + "FROM student s, register_has_student rs WHERE s.idstudent = rs.idstudent))";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ArrayList<Student> list = new ArrayList<>();
                ResultSet rs = ps.executeQuery(sql);
                while (rs.next()) {
                    Student entity = new Student();
                    entity.setIdstudent(rs.getInt("idstudent"));
                    entity.setPassword(rs.getString("password"));
                    entity.setName(rs.getString("name"));
                    entity.setClasses(rs.getString("class"));
                    entity.setDob(rs.getDate("dob"));
                    entity.setAddress(rs.getString("address"));
                    entity.setPhone(rs.getString("phone"));
                    entity.setEmail(rs.getString("email"));
                    list.add(entity);
                }
                return list;
            }
        }
    }
    
    public int countStudentHasProject(String id) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT COUNT(DISTINCT idstudent) FROM project_has_student "
                    + "WHERE project_has_student.idproject = '" + id + "'";
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
}
