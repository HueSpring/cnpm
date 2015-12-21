package dut.itf.controller;

import dut.itf.dao.TopicDAO;
import dut.itf.entity.Group;
import dut.itf.entity.GroupHasStudent;
import dut.itf.entity.Topic;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class beanTopic {

    private Topic topic = new Topic();
    private Group group = new Group();
    private String idname;
    private GroupHasStudent ghs = new GroupHasStudent();
    private String message;
    private int number;
    private boolean check = false;

    public beanTopic() {
    }

    public String getIdname() {
        return idname;
    }

    public void setIdname(String idname) {
        this.idname = idname;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public int getIdstudent() {
        return ghs.getIdstudent();
    }

    public void setIdstudent(int idstudent) {
        ghs.setIdstudent(idstudent);
    }

    public String getIdgroupss() {
        return ghs.getIdgroup();
    }

    public void setIdgroupss(String idgroup) {
        ghs.setIdgroup(idgroup);
    }

    public GroupHasStudent getGhs() {
        return ghs;
    }

    public void setGhs(GroupHasStudent ghs) {
        this.ghs = ghs;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getIdgroups() {
        return group.getIdgroup();
    }

    public void setIdgroups(String idgroup) {
        group.setIdgroup(idgroup);
    }

    public String getNamegroup() {
        return group.getNamegroup();
    }

    public void setNamegroup(String namegroup) {
        group.setNamegroup(namegroup);
    }

    public String getIdregisters() {
        return group.getIdregister();
    }

    public void setIdregisters(String idregister) {
        group.setIdregister(idregister);
    }

    public int getIdofleader() {
        return group.getIdofleader();
    }

    public void setIdofleader(int idofleader) {
        group.setIdofleader(idofleader);
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public String getIdtopic() {
        return topic.getIdtopic();
    }

    public void setIdtopic(String idtopic) {
        topic.setIdtopic(idtopic);
    }

    public String getIdregister() {
        return topic.getIdregister();
    }

    public void setIdregister(String idregister) {
        topic.setIdregister(idregister);
    }

    public String getIdgroup() {
        return topic.getIdgroup();
    }

    public void setIdgroup(String idgroup) {
        topic.setIdgroup(idgroup);
    }

    public String getNametopic() {
        return topic.getNametopic();
    }

    public void setNametopic(String nametopic) {
        topic.setNametopic(nametopic);
    }

    public String getDescription() {
        return topic.getDescription();
    }

    public void setDescription(String description) {
        topic.setDescription(description);
    }

    public String getGuide() {
        return topic.getGuide();
    }

    public void setGuide(String guide) {
        topic.setGuide(guide);
    }

    public int countTopic(String idregister) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        TopicDAO dao = new TopicDAO();
        return dao.countTopicByIdregister(idregister);
    }

    public int countGroup(String idregister) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        TopicDAO dao = new TopicDAO();
        return dao.countGroupByIdregister(idregister);
    }

    public int countStudentsHasGroup(String idgroup) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        TopicDAO dao = new TopicDAO();
        return dao.countStudentHasGroup(idgroup);
    }

    public int numberTopic(String idregister) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        return countTopic(idregister) + 1;
    }

    public int numberGroup(String idregister) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        return countGroup(idregister) + 1;
    }

    public int numberStudentHasGroup(String idgroup) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        return countStudentsHasGroup(idgroup) + 1;
    }

    public String prepareCreatTopic(String idregister) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        topic.setIdregister(idregister);
        return "createTopic";
    }
    
    public ArrayList<Topic> getTopics() throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        TopicDAO dao = new TopicDAO();
        return dao.findAllTopics();
    }

    public ArrayList<Topic> getTopicsByIdregister(String idregister) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        TopicDAO dao = new TopicDAO();
        return dao.findAllTopicByIdregister(idregister);
    }

    public void createTopics() throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        TopicDAO dao = new TopicDAO();
        topic.setIdtopic(topic.getIdregister() + ".D" + numberTopic(topic.getIdregister()));
        boolean check = dao.createTopic(topic.getIdtopic(), topic.getIdregister(),
                topic.getNametopic(), topic.getDescription(), topic.getGuide());
        if (check) {
            topic.setIdtopic(topic.getIdregister() + ".D" + numberTopic(topic.getIdregister()));
            topic.setNametopic(null);
            topic.setDescription(null);
            topic.setGuide(null);
            message = "Thành công";
        } else {
            message = "Không thành công";
        }
    }

    public void createGroups() throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        TopicDAO dao = new TopicDAO();
        if (group.getIdregister() != null) {
            group.setIdgroup(group.getIdregister() + ".N" + numberGroup(group.getIdregister()));
            group.setNamegroup("Nhóm " + numberGroup(group.getIdregister()));
            ghs.setIdgroup(group.getIdgroup());
            boolean check = dao.createGroup(group.getIdgroup(), group.getNamegroup(),
                    group.getIdregister());
            if (check) {
                group.setIdgroup(group.getIdregister() + ".N" + countGroup(group.getIdregister()));
                group.setNamegroup("Nhóm " + countGroup(group.getIdregister()));
                ghs.setIdgroup(group.getIdgroup());
                message = "Thành công";
            } else {
                message = "Không thành công";
            }
        }
    }

    public void deleteTopics(String idtopic, String idregister) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        TopicDAO dao = new TopicDAO();
        dao.deleteTopic(idtopic);
        updateTopicAgain(idregister);
    }

    public void deleteStudentHasGroup(int idstudent, String idgroup) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        TopicDAO dao = new TopicDAO();
        dao.deleteStudentHasGroup(idstudent, idgroup);
    }

    public void updateTopics(String idtopic, String idtopic2) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        TopicDAO dao = new TopicDAO();
        dao.updateTopic(idtopic, idtopic2);
    }

    public void updateTopicAgain(String idregister) throws SQLException, 
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        ArrayList<Topic> list = getTopicsByIdregister(idregister);
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i).getIdregister() + ".D" + (i + 1);
            updateTopics(s, list.get(i).getIdtopic());
        }
    }

    //register topic
    public void createGroupHasTopic(String idgroup, String idtopic) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        TopicDAO dao = new TopicDAO();
        dao.creatGroupHasTopic(idgroup, idtopic);
    }

    public void deleteGroupRegisterTopic(String idtopic) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        TopicDAO dao = new TopicDAO();
        dao.deleteGroupHasTopic(idtopic);
    }

    public void addPlusStudentToGroup(int idstudent, String idregister) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        TopicDAO dao = new TopicDAO();
        ArrayList<GroupHasStudent> list = dao.findGHSsByIdregister(idregister);
        for (GroupHasStudent item : list) {
            if (item.getIdstudent() == idstudent) {
                group.setIdregister(idregister);
                group.setIdgroup(item.getIdgroup());
                ghs.setIdgroup(item.getIdgroup());
                check = true;
            }
        }
    }

    public boolean checkIdStudentByIdRegister(String idregister, int idstudent) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        TopicDAO dao = new TopicDAO();
        ArrayList<GroupHasStudent> list = dao.findGHSsByIdregister(idregister);
        for (GroupHasStudent item : list) {
            if (item.getIdstudent() == idstudent) {
                return true;
            }
        }
        return false;
    }

    public void creatNewGroup(String idregister, int idstudent) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        TopicDAO dao = new TopicDAO();
        String idgroup = idregister + ".N" + numberGroup(idregister);
        String namegroup = "Nhóm " + numberGroup(idregister);
        group.setIdregister(idregister);
        ghs.setIdgroup(idgroup);
        check = true;
        dao.createGroup(idgroup, namegroup, idregister);
        dao.addLeader(idgroup, idstudent);
        dao.createGroupHasStudent(idstudent, idgroup);
    }

    public ArrayList<Group> getGroups() throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        TopicDAO dao = new TopicDAO();
        return dao.findGroups();
    }

    public ArrayList<Group> getGroupsByIdRegister(String idregister) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        TopicDAO dao = new TopicDAO();
        return dao.findGroupsByIdRegister(idregister);
    }

    public Group getGroupByIdGroup(String idgroup) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        TopicDAO dao = new TopicDAO();
        return dao.findGroupByIdGroup(idgroup);
    }

    public Group getGroupByIdRegisterIdStudent(String idregister, int idstudent) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        TopicDAO dao = new TopicDAO();
        return dao.findGroupByIdGroupIdRegister(idregister, idstudent);
    }

    public void addStudentToGroup() throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        TopicDAO dao = new TopicDAO();
        if (!checkIdStudentByIdRegister(group.getIdregister(), ghs.getIdstudent())) {
            message = "";
            dao.addStudentToGroup(ghs.getIdstudent(), ghs.getIdgroup());
        } else {
            message = "Sinh viên đã có nhóm.";
        }
    }

    public boolean checkLeaderByidRegister(int idstudent, String idregister) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        TopicDAO dao = new TopicDAO();
        ArrayList<Group> list = dao.findGroupsByIdRegister(idregister);
        for (Group items : list) {
            if (items.getIdofleader() == idstudent) {
                return true;
            }
        }
        return false;
    }

    public boolean checkLeaderByIdGroup(int idstudent, String idgroup) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        TopicDAO dao = new TopicDAO();
        if (dao.findLeaderByIdGroup(idgroup) == idstudent) {
            return true;
        }
        return false;
    }

    public String writeLeader(int idstudent, String idgroup) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        if (checkLeaderByIdGroup(idstudent, idgroup)) {
            return "Nhóm Trưởng";
        }
        return "";
    }

    public Group getGroupByIdTopic(String idtopic) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        TopicDAO dao = new TopicDAO();
        String idg = dao.findIdGroupByIdTopic(idtopic);
        return getGroupByIdGroup(idg);
    }

    public ArrayList<Topic> getTopicByIdGroup(String idgroup) throws ClassNotFoundException,
            SQLException, IllegalAccessException, InstantiationException {
        TopicDAO dao = new TopicDAO();
        return dao.findTopicByIdGroup(idgroup);
    }

    public String getNameTopicByIdGroup(String idgroup) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException{
        ArrayList<Topic> list = getTopicByIdGroup(idgroup);
        String nametopic =null;
        for (Topic list1 : list) {
            nametopic = list1.getNametopic();
        }
        return nametopic;
    }
    
    public boolean checkGroupRegisterTopic(String idgroup) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        ArrayList<Topic> l = getTopics();
        for (Topic l1 : l) {
            if (l1.getIdgroup() != null && l1.getIdgroup().equals(idgroup)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean checkRegisterTopic(String idgroup, String idgroup2){
        if(idgroup != null && idgroup2 != null && idgroup.equals(idgroup2)) return true;
        return false;
    }
    
    
}
