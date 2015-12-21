package dut.itf.dao;

import dut.itf.entity.AccountAL;
import dut.itf.entity.Roles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AccountALDAO {

    public AccountALDAO() {
    }

    public boolean create(String idaccount, String password, String name) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "INSERT INTO account_a_l (idccount, password, name) VALUES('" + idaccount
                    + "','" + password + "','" + name + "')";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                return ps.executeUpdate() > 0;
            }
        }
    }

    public boolean delete(String idaccount) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "DELETE FROM account_a_l WHERE idccount = '" + idaccount + "')";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                return ps.executeUpdate() > 0;
            }
        }
    }

    public boolean update(Date dob, String degree, String address, String phone, String email, String idaccount)
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "UPDATE account_a_l SET  dob = '" + toString(dob) + "', degree = '" + degree
                    + "', address = '" + address + "', phone = '" + phone
                    + "', email = '" + email + "' WHERE idaccount = '" + idaccount + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                return ps.executeUpdate() > 0;
            }
        }
    }

    public boolean updatePassword(String password, String idaccount) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "UPDATE account_a_l SET password = '" + password + "' "
                    + "WHERE idaccount = '" + idaccount + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                return ps.executeUpdate() > 0;
            }
        }
    }

    public ArrayList<AccountAL> findAllAccountALs() throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException, ParseException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String query = "SELECT * FROM account_a_l";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ArrayList<AccountAL> list = new ArrayList<>();
                ResultSet rs = ps.executeQuery(query);
                while (rs.next()) {
                    AccountAL entity = new AccountAL();
                    entity.setIdaccount(rs.getString("idaccount"));
                    entity.setPassword(rs.getString("password"));
                    entity.setName(rs.getString("name"));
                    entity.setDob(rs.getDate("dob"));
                    entity.setDegree(rs.getString("degree"));
                    entity.setAddress(rs.getString("address"));
                    entity.setPhone(rs.getString("phone"));
                    entity.setEmail(rs.getString("email"));
                    list.add(entity);
                }
                return list;
            }
        }
    }

    public AccountAL findAccountALByIdaccount(String idaccount) throws SQLException, ClassNotFoundException,
            InstantiationException, IllegalAccessException, ParseException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT a.* FROM account_a_l a WHERE a.idaccount = '" + idaccount + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                AccountAL entity = new AccountAL();
                ResultSet rs = ps.executeQuery(sql);
                while (rs.next()) {
                    entity.setIdaccount(rs.getString("idaccount"));
                    entity.setPassword(rs.getString("password"));
                    entity.setName(rs.getString("name"));
                    entity.setDob(rs.getDate("dob"));
                    entity.setDegree(rs.getString("degree"));
                    entity.setAddress(rs.getString("address"));
                    entity.setPhone(rs.getString("phone"));
                    entity.setEmail(rs.getString("email"));
                }
                return entity;
            }
        }
    }

    public String toString(Date date) {
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        return d.format(date);
    }

    //find list lecturer
    public ArrayList<AccountAL> findAllLectrers() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT a.* FROM account_a_l a, roles r WHERE a.idaccount = r.idaccount"
                    + " AND r.role = '1'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery(sql);
                ArrayList<AccountAL> list = new ArrayList<>();
                while (rs.next()) {
                    AccountAL entity = new AccountAL();
                    entity.setIdaccount(rs.getString("idaccount"));
                    entity.setPassword(rs.getString("password"));
                    entity.setName(rs.getString("name"));
                    entity.setDob(rs.getDate("dob"));
                    entity.setDegree(rs.getString("degree"));
                    entity.setAddress(rs.getString("address"));
                    entity.setPhone(rs.getString("phone"));
                    entity.setEmail(rs.getString("email"));
                    list.add(entity);
                }
                return list;
            }
        }
    }

    public ArrayList<AccountAL> findAccountByIdproject(String idproject) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT a.* FROM account_a_l a, register r WHERE a.idaccount = r.idaccount "
                    + "AND r.idproject = '" + idproject + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery(sql);
                ArrayList<AccountAL> list = new ArrayList<>();
                while (rs.next()) {
                    AccountAL entity = new AccountAL();
                    entity.setIdaccount(rs.getString("idaccount"));
                    entity.setPassword(rs.getString("password"));
                    entity.setName(rs.getString("name"));
                    entity.setDob(rs.getDate("dob"));
                    entity.setDegree(rs.getString("degree"));
                    entity.setAddress(rs.getString("address"));
                    entity.setPhone(rs.getString("phone"));
                    entity.setEmail(rs.getString("email"));
                    list.add(entity);
                }
                return list;
            }
        }
    }

    public ArrayList<AccountAL> findAccountByIdregister(String idregister) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT a.* FROM account_a_l a, register r WHERE a.idaccount = r.idaccount "
                    + "AND r.idregister = '" + idregister + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery(sql);
                ArrayList<AccountAL> list = new ArrayList<>();
                while (rs.next()) {
                    AccountAL entity = new AccountAL();
                    entity.setIdaccount(rs.getString("idaccount"));
                    entity.setPassword(rs.getString("password"));
                    entity.setName(rs.getString("name"));
                    entity.setDob(rs.getDate("dob"));
                    entity.setDegree(rs.getString("degree"));
                    entity.setAddress(rs.getString("address"));
                    entity.setPhone(rs.getString("phone"));
                    entity.setEmail(rs.getString("email"));
                    list.add(entity);
                }
                return list;
            }
        }
    }

    public String findNameAccountByIdregister(String idregister) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT a.name FROM account_a_l a, register r WHERE a.idaccount = r.idaccount "
                    + "AND r.idregister = '" + idregister + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery(sql);
                String nameLecturer = null;
                while (rs.next()) {
                    nameLecturer = rs.getString("name");
                }
                return nameLecturer;
            }
        }
    }
    public String findNameAccountByIdAccount(String idaccount) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
           String sql = "SELECT l.name FROM account_a_l l WHERE l.idaccount ='" + idaccount + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery(sql);
                String nameLecturer = null;
                while (rs.next()) {
                    nameLecturer = rs.getString("name");
                }
                return nameLecturer;
            }
        }
    }

    public AccountAL findAccountByIdAccount(String idaccount) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT l.* FROM account_a_l l WHERE l.idaccount ='" + idaccount + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery(sql);
                AccountAL entity = new AccountAL();
                while (rs.next()) {
                    entity.setIdaccount(rs.getString("idaccount"));
                    entity.setPassword(rs.getString("password"));
                    entity.setName(rs.getString("name"));
                    entity.setDob(rs.getDate("dob"));
                    entity.setDegree(rs.getString("degree"));
                    entity.setAddress(rs.getString("address"));
                    entity.setPhone(rs.getString("phone"));
                    entity.setEmail(rs.getString("email"));
                }
                return entity;
            }
        }
    }

    /*process roles */
    public ArrayList<Roles> findRolesByIdaccount(String idaccount) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT r.* FROM roles r WHERE r.idaccount = '" + idaccount + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery(sql);
                ArrayList<Roles> list = new ArrayList<>();
                while (rs.next()) {
                    Roles entity = new Roles();
                    entity.setIdaccount(rs.getString("idaccount"));
                    entity.setRole(rs.getInt("role"));
                    list.add(entity);
                }
                return list;
            }
        }
    }

}
