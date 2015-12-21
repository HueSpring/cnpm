package dut.itf.dao;

import dut.itf.entity.Register;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class RegisterDAO {

    public RegisterDAO() {
    }

    public boolean creat(String idregister, String idproject, String idlecturer, int quantity) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "INSERT INTO register(idregister, idproject, idaccount, quantity) VALUES('" + idregister
                    + "','" + idproject + "','" + idlecturer + "','" + quantity + "')";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                return ps.executeUpdate() > 0;
            }
        }
    }

    public boolean delete(String idregister) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "DELETE FROM register WHERE idregister = '" + idregister + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                return ps.executeUpdate() > 0;
            }
        }
    }

    public ArrayList<Register> findRegisters() throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String query = "SELECT * FROM register";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ArrayList<Register> list = new ArrayList<>();
                ResultSet rs = ps.executeQuery(query);
                while (rs.next()) {
                    Register entity = new Register();
                    entity.setIdproject(rs.getString("idproject"));
                    entity.setIdregister(rs.getString("idregister"));
                    entity.setIdaccount(rs.getString("idaccount"));
                    entity.setQuantity(rs.getInt("quantity"));
                    list.add(entity);
                }
                return list;
            }
        }
    }

    public ArrayList<String> findIdRegisters() throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String query = "SELECT r.idregister FROM register r";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ArrayList<String> list = new ArrayList<>();
                ResultSet rs = ps.executeQuery(query);
                while (rs.next()) {
                    String entity = rs.getString("idregister");
                    list.add(entity);
                }
                return list;
            }
        }
    }

    public int findQuantityByIdRegister(String idregister) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT r.quantity FROM register r WHERE r.idregister = '" + idregister + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                int quantity = 0;
                ResultSet rs = ps.executeQuery(sql);
                while (rs.next()) {
                    quantity = rs.getInt("quantity");
                }
                return quantity;
            }
        }
    }

    public ArrayList<String> findIdAccountsByIdProject(String idproject) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String query = "SELECT r.idAccount FROM register r WHERE r.idProject = '" + idproject + "'";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ArrayList<String> list = new ArrayList<>();
                ResultSet rs = ps.executeQuery(query);
                while (rs.next()) {
                    String entity = rs.getString("idaccount");
                    list.add(entity);
                }
                return list;
            }
        }
    }

    public ArrayList<Register> findRegistersByIdProject(String idproject) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String query = "SELECT r.* FROM register r WHERE r.idproject = '" + idproject + "'";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ArrayList<Register> list = new ArrayList<>();
                ResultSet rs = ps.executeQuery(query);
                while (rs.next()) {
                    Register entity = new Register();
                    entity.setIdregister(rs.getString("idregister"));
                    entity.setIdproject(rs.getString("idproject"));
                    entity.setIdaccount(rs.getString("idaccount"));
                    entity.setQuantity(rs.getInt("quantity"));
                    list.add(entity);
                }
                return list;
            }
        }
    }

    public Register findRegisterByIdRegister(String idregister) throws SQLException, ClassNotFoundException,
            InstantiationException, IllegalAccessException, ParseException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT r.* FROM register r WHERE r.idregister = '" + idregister + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                Register entity = new Register();
                ResultSet rs = ps.executeQuery(sql);
                while (rs.next()) {
                    entity.setIdregister(rs.getString("idregister"));
                    entity.setIdproject(rs.getString("idproject"));
                    entity.setIdaccount(rs.getString("idaccount"));
                    entity.setQuantity(rs.getInt("quantity"));
                }
                return entity;
            }
        }
    }

    public ArrayList<Register> findRegisterByIdAccount(String idaccount) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String query = "SELECT r.* FROM register r WHERE r.idaccount = '" + idaccount + "'";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ArrayList<Register> list = new ArrayList<>();
                ResultSet rs = ps.executeQuery(query);
                while (rs.next()) {
                    Register entity = new Register();
                    entity.setIdregister(rs.getString("idregister"));
                    entity.setIdproject(rs.getString("idproject"));
                    entity.setIdaccount(rs.getString("idaccount"));
                    entity.setQuantity(rs.getInt("quantity"));
                    list.add(entity);
                }
                return list;
            }
        }
    }
    
    public ArrayList<Register> findRegisterByIdStudent(int idstudent) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String query = "SELECT r.* FROM register r, register_has_student rs "
                    + "WHERE r.idregister = rs.idregister AND rs.idstudent = '" + idstudent + "'";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ArrayList<Register> list = new ArrayList<>();
                ResultSet rs = ps.executeQuery(query);
                while (rs.next()) {
                    Register entity = new Register();
                    entity.setIdregister(rs.getString("idregister"));
                    entity.setIdproject(rs.getString("idproject"));
                    entity.setIdaccount(rs.getString("idaccount"));
                    entity.setQuantity(rs.getInt("quantity"));
                    list.add(entity);
                }
                return list;
            }
        }
    }

}
