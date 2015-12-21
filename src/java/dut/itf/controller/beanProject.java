package dut.itf.controller;

import dut.itf.dao.ProjectDAO;
import dut.itf.entity.Project;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class beanProject {

    private Project project = new Project();
    private String message;

    public beanProject() {
    }

    public String getIdproject() {
        return project.getIdproject();
    }

    public void setIdproject(String idproject) {
        project.setIdproject(idproject);
    }

    public String getName() {
        return project.getName();
    }

    public void setName(String name) {
        project.setName(name);
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

     public ArrayList<Project> getProjects() throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException{
        ProjectDAO dao = new ProjectDAO();
        return dao.findAllProjects();
    }
     
     public int countProjects()throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException{
         return getProjects().size();
     }
    
    public String getNameProjectByIdProject(String idproject) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException{
        ProjectDAO dao = new ProjectDAO();
        return dao.findNameProjectByIdProject(idproject);
    }
    
    public String getNameProjectByIdRegister(String idregister) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException{
        ProjectDAO dao = new ProjectDAO();
        return dao.findNameProjectByIdRegister(idregister);
    }
    
    public ArrayList<Project> getProjectsByIdStudent(int idstudent) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        ProjectDAO dao = new ProjectDAO();
        return dao.findProjectsByIdStudent(idstudent);
    }
    
}
