

package dut.itf.dao;

import dut.itf.entity.Project;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProjectDAO {

    public ProjectDAO() {
    }
    
    public ArrayList<Project> findAllProjects() throws SQLException, ClassNotFoundException, 
            InstantiationException, IllegalAccessException{
        try (Connection conn = ConnectionFactory.getConnection()) {
            String query = "SELECT * FROM project ORDER BY idproject ASC";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ArrayList<Project> list = new ArrayList<>();
                ResultSet rs = ps.executeQuery(query);
                while (rs.next()) {
                    Project entity = new Project();
                    entity.setIdproject(rs.getString("idproject"));
                    entity.setName(rs.getString("name"));
                    list.add(entity);
                }
                return list;
            }
        }
    }
    
    public String findNameProjectByIdProject(String idproject) throws SQLException, 
            ClassNotFoundException, InstantiationException, IllegalAccessException{
        try (Connection conn = ConnectionFactory.getConnection()) {
            String query = "SELECT p.name FROM project p WHERE p.idproject = '" + idproject + "' ORDER BY p.name ASC";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ResultSet rs = ps.executeQuery(query);
                while (rs.next()) {
                    return rs.getString("name");
                }
                return null;
            }
        }
    }
    
    public String findNameProjectByIdRegister(String idregister) throws SQLException, 
            ClassNotFoundException, InstantiationException, IllegalAccessException{
        try (Connection conn = ConnectionFactory.getConnection()) {
            String query = "SELECT p.name FROM project p, register r"
                    + " WHERE p.idproject = r.idproject AND r.idregister = '" + idregister + "' ORDER BY p.name ASC";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ResultSet rs = ps.executeQuery(query);
                while (rs.next()) {
                    return rs.getString("name");
                }
                return null;
            }
        }
    }
    
    public ArrayList<Project> findProjectsByIdStudent(int idstudent) throws SQLException, ClassNotFoundException, 
            InstantiationException, IllegalAccessException{
        try (Connection conn = ConnectionFactory.getConnection()) {
            String query = "SELECT p.* FROM project p, project_has_student ps WHERE "
                    + "p.idproject = ps.idproject AND ps.idstudent = '" + idstudent + "' ORDER BY p.idproject ASC";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ArrayList<Project> list = new ArrayList<>();
                ResultSet rs = ps.executeQuery(query);
                while (rs.next()) {
                    Project entity = new Project();
                    entity.setIdproject(rs.getString("idproject"));
                    entity.setName(rs.getString("name"));
                    list.add(entity);
                }
                return list;
            }
        }
    }
    
    
}
