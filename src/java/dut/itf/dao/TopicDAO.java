package dut.itf.dao;

import dut.itf.entity.Group;
import dut.itf.entity.GroupHasStudent;
import dut.itf.entity.Topic;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TopicDAO {

    public TopicDAO() {
    }

//    creat Topic
    public boolean createTopic(String idtopic, String idregister, String nametopic, String description, String guide) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "INSERT INTO topic (idtopic, idregister, nametopic, description, guide) "
                    + "VALUES('" + idtopic + "','" + idregister + "','" + nametopic
                    + "','" + description + "','" + guide + "')";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                return ps.executeUpdate() > 0;
            }
        }
    }

//    delete Topic
    public boolean deleteTopic(String idtopic) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "DELETE FROM topic WHERE idtopic = '" + idtopic + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                return ps.executeUpdate() > 0;
            }
        }
    }

    //update Topic
    public boolean updateTopic(String idtopic, String idtopic1) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "UPDATE topic SET idtopic = '" + idtopic + "' WHERE idtopic = '" + idtopic1 + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                return ps.executeUpdate() > 0;
            }
        }
    }

//    creat group register topic
    public boolean creatGroupHasTopic(String idgroup, String idtopic) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "UPDATE topic SET idgroup = '" + idgroup + "' WHERE idtopic = '" + idtopic + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                return ps.executeUpdate() > 0;
            }
        }
    }

//    delete group register topic
    public boolean deleteGroupHasTopic(String idtopic) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "UPDATE topic SET idgroup = null WHERE idtopic = '" + idtopic + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                return ps.executeUpdate() > 0;
            }
        }
    }

    //delete student on group
    public boolean deleteStudentHasGroup(int idstudent, String idgroup) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()){ 
            String sql = "DELETE FROM group_has_student WHERE idstudent = '" + idstudent + "' "
                    + "AND idgroup = '" + idgroup + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                return ps.executeUpdate() > 0;
            }
        }
    }

    
    public int countTopicByIdregister(String idregister) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT COUNT(idtopic) FROM topic WHERE idregister = '" + idregister + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                int count = 0;
                while (rs.next()) {
                    count = rs.getInt("COUNT(idtopic)");
                }
                return count;
            }
        }
    }

    public int countGroupByIdregister(String idregister) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT COUNT(DISTINCT idgroup) FROM `group` WHERE idregister = '" + idregister + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                int count = 0;
                while (rs.next()) {
                    count = rs.getInt("COUNT(DISTINCT idgroup)");
                }
                return count;
            }
        }
    }

    public int countStudentHasGroup(String idgroup) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT COUNT(DISTINCT idstudent) FROM group_has_student WHERE idgroup = '" + idgroup + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                int count = 0;
                while (rs.next()) {
                    count = rs.getInt("COUNT(DISTINCT idstudent)");
                }
                return count;
            }
        }
    }

    public ArrayList<Topic> findAllTopicByIdregister(String idregister) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT * FROM topic WHERE idregister = '" + idregister + "' ORDER BY idtopic ASC";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ArrayList<Topic> list = new ArrayList<>();
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Topic entity = new Topic();
                    entity.setIdtopic(rs.getString("idtopic"));
                    entity.setIdregister(rs.getString("idregister"));
                    entity.setNametopic(rs.getString("nametopic"));
                    entity.setDescription(rs.getString("description"));
                    entity.setIdgroup(rs.getString("idgroup"));
                    entity.setGuide(rs.getString("guide"));
                    list.add(entity);
                }
                return list;
            }
        }
    }
    public ArrayList<Topic> findAllTopics() throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT * FROM topic ORDER BY idtopic";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ArrayList<Topic> list = new ArrayList<>();
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Topic entity = new Topic();
                    entity.setIdtopic(rs.getString("idtopic"));
                    entity.setIdregister(rs.getString("idregister"));
                    entity.setNametopic(rs.getString("nametopic"));
                    entity.setDescription(rs.getString("description"));
                    entity.setIdgroup(rs.getString("idgroup"));
                    entity.setGuide(rs.getString("guide"));
                    list.add(entity);
                }
                return list;
            }
        }
    }

    //ceate group
    public boolean createGroup(String idgroup, String namegroup, String idregister)
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "INSERT INTO `group` (idgroup, namegroup, idregister) "
                    + "VALUES('" + idgroup + "','" + namegroup + "','" + idregister + "')";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                return ps.executeUpdate() > 0;
            }
        }
    }
    
    //add leader
    public boolean addLeader(String idgroup, int idstudent)
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "UPDATE `group` SET idofleader = '" + idstudent + "' WHERE idgroup = '" + idgroup + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                return ps.executeUpdate() > 0;
            }
        }
    }
    
    public boolean createGroupHasStudent(int idstudent, String idgroup)
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "INSERT INTO group_has_student (idstudent, idgroup) "
                    + "VALUES('" + idstudent + "','" + idgroup + "')";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                return ps.executeUpdate() > 0;
            }
        }
    }
    
    public boolean addStudentToGroup(int idstudent, String idgroup)
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "INSERT INTO group_has_student (idstudent, idgroup) "
                    + "VALUES('" + idstudent + "','" + idgroup + "')";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                return ps.executeUpdate() > 0;
            }
        }
    }

    public ArrayList<GroupHasStudent> findGHSsByIdregister(String idregister) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT gs.* FROM group_has_student gs, `group` g"
                    + " WHERE g.idgroup = gs.idgroup AND g.idregister = '" + idregister + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                ArrayList<GroupHasStudent> list = new ArrayList<>();
                while (rs.next()) {
                    GroupHasStudent entity = new GroupHasStudent();
                    entity.setIdgroup(rs.getString("idgroup"));
                    entity.setIdstudent(rs.getInt("idstudent"));
                    list.add(entity);
                }
                return list;
            }
        }
    }

    public ArrayList<Group> findGroups() throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException{
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT * FROM `group`";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                ArrayList<Group> list = new ArrayList<>();
                while (rs.next()) {
                    Group entity = new Group();
                    entity.setIdgroup(rs.getString("idgroup"));
                    entity.setNamegroup(rs.getString("namegroup"));
                    entity.setIdregister(rs.getString("idregister"));
                    entity.setIdofleader(rs.getInt("idofleader"));
                    list.add(entity);
                }
                return list;
            }
        }
    }
    
    public int findLeaderByIdGroup(String idgroup) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException{
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT idofleader FROM `group` WHERE idgroup = '" + idgroup + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                int id = 0;
                while (rs.next()) {
                    id = rs.getInt("idofleader");
                }
                return id;
            }
        }
    }
    
    public ArrayList<Group> findGroupsByIdRegister(String idregister) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException{
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT g.* FROM `group` g WHERE g.idregister = '" + idregister + "' ORDER BY g.namegroup";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                ArrayList<Group> list = new ArrayList<>();
                while (rs.next()) {
                    Group entity = new Group();
                    entity.setIdgroup(rs.getString("idgroup"));
                    entity.setNamegroup(rs.getString("namegroup"));
                    entity.setIdregister(rs.getString("idregister"));
                    entity.setIdofleader(rs.getInt("idofleader"));
                    list.add(entity);
                }
                return list;
            }
        }
    }
    
     public Group findGroupByIdGroup(String idgroup) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException{
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT g.* FROM `group` g WHERE g.idgroup = '" + idgroup + "' ORDER BY g.idgroup";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                Group entity = new Group();
                while (rs.next()) {
                    entity.setIdgroup(rs.getString("idgroup"));
                    entity.setNamegroup(rs.getString("namegroup"));
                    entity.setIdregister(rs.getString("idregister"));
                    entity.setIdofleader(rs.getInt("idofleader"));
                }
                return entity;
            }
        }
    }
    
     public Group findGroupByIdGroupIdRegister(String idregister, int idstudent) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException{
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT g.* FROM `group` g, group_has_student gs WHERE g.idgroup = gs.idgroup"
                    + " AND g.idregister = '" + idregister + "' AND gs.idstudent = '" + idstudent + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                Group entity = new Group();
                while (rs.next()) {
                    entity.setIdgroup(rs.getString("idgroup"));
                    entity.setNamegroup(rs.getString("namegroup"));
                    entity.setIdregister(rs.getString("idregister"));
                    entity.setIdofleader(rs.getInt("idofleader"));
                }
                return entity;
            }
        }
    }
     
    public String findIdGroupByIdTopic(String idtopic) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException{
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT t.idgroup FROM topic t WHERE t.idtopic = '" + idtopic + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                String entity = null;
                while (rs.next()) {
                    entity = rs.getString("idgroup");
                }
                return entity;
            }
        }
    }
    
    public ArrayList<Topic> findTopicByIdGroup(String idgroup) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException{
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT * FROM topic WHERE idgroup = '" + idgroup + "' ORDER BY idtopic ASC";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ArrayList<Topic> list = new ArrayList<>();
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Topic entity = new Topic();
                    entity.setIdtopic(rs.getString("idtopic"));
                    entity.setIdregister(rs.getString("idregister"));
                    entity.setNametopic(rs.getString("nametopic"));
                    entity.setDescription(rs.getString("description"));
                    entity.setGuide(rs.getString("guide"));
                    list.add(entity);
                }
                return list;
            }
        }
    }
    
     
}
