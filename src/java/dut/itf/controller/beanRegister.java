package dut.itf.controller;

import dut.itf.dao.AccountALDAO;
import dut.itf.dao.RegisterDAO;
import dut.itf.dao.RegisterHasStudentDAO;
import dut.itf.dao.StudentDAO;
import dut.itf.entity.AccountAL;
import dut.itf.entity.Register;
import dut.itf.entity.RegisterHasStudent;
import dut.itf.entity.Student;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;

@ManagedBean
@SessionScoped
public class beanRegister {

    private String name = null;
    private Register register = new Register();
    private String message = "";

    public beanRegister() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Register getRegister() {
        return register;
    }

    public void setRegister(Register register) {
        this.register = register;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIdregister() {
        return register.getIdregister();
    }

    public void setIdregister(String idregister) {
        register.setIdregister(idregister);
    }

    public String getIdproject() {
        return register.getIdproject();
    }

    public void setIdproject(String idproject) {
        register.setIdproject(idproject);
    }

    public String getIdaccount() {
        return register.getIdaccount();
    }

    public void setIdaccount(String idaccount) {
        register.setIdaccount(idaccount);
    }

    public int getQuantity() {
        return register.getQuantity();
    }

    public void setQuantity(int quantity) {
        register.setQuantity(quantity);
    }

    public ArrayList<Register> getRegitersByIdProject(String idproject) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        RegisterDAO dao = new RegisterDAO();
        return dao.findRegistersByIdProject(idproject);
    }
    
    ArrayList<Register> list;

    public ArrayList<Register> getList() {
        return list;
    }

    public void setList(ArrayList<Register> list) {
        this.list = list;
    }
    
    private Register r = new Register();

    public Register getR() {
        return r;
    }

    public void setR(Register r) {
        this.r = r;
    }
    
    public String getRegisterByProtectID2(String projectID) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        r.setIdproject(projectID);
        list = null;
        list = this.getRegitersByIdProject(projectID);
        return "listOfRegisterByProjectID";  
    }
    
    public ArrayList<RegisterHasStudent> getAllRegisterHasStudentByIdstudent(int idstudent) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        RegisterHasStudentDAO dao = new RegisterHasStudentDAO();
        return dao.findRegisterHasStudentByIdStudent(idstudent);
    }

    public ArrayList<Register> getRegistersByIdAccount(String idaccount) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        RegisterDAO dao = new RegisterDAO();
        return dao.findRegisterByIdAccount(idaccount);
    }

    public ArrayList<Register> getRegistersByIdStudent(int idstudent) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        RegisterDAO dao = new RegisterDAO();
        return dao.findRegisterByIdStudent(idstudent);
    }

    public boolean checkGetRegisters() throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        if (getRegitersByIdProject(register.getIdproject()).isEmpty()) {
            return false;
        }
        return true;
    }

    public String createId(String id) {
        message = "";
        register.setIdproject(id);
        return "createRegister";
    }

    public void deleteRegister(String idregister) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {

        RegisterHasStudentDAO dao = new RegisterHasStudentDAO();
        ArrayList<RegisterHasStudent> list = dao.findRegisteHasStudentByIdRegister(idregister);

        if (list.isEmpty()) {
            RegisterDAO dao2 = new RegisterDAO();
            dao2.delete(idregister);
            message = "";
        } else {
            message = "Không thể hủy.";
        }
    }

    public boolean checkIdregister() throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException, ParseException {
        RegisterDAO dao = new RegisterDAO();
        ArrayList<String> list = dao.findIdRegisters();
        for (String items : list) {
            if (items.equals(register.getIdregister())) {
                return true;
            }
        }
        return false;
    }

    public boolean checkGetRegisterByIdAccount(String idaccount) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        ArrayList<Register> list = getRegistersByIdAccount(idaccount);
        if (!list.isEmpty()) {
            return true;
        }
        return false;
    }

    public boolean checkIdLecturerByIdProject() throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        RegisterDAO dao = new RegisterDAO();
        ArrayList<String> list = dao.findIdAccountsByIdProject(register.getIdproject());
        for (String item : list) {
            if (item.equals(register.getIdaccount())) {
                return true;
            }
        }
        return false;
    }

    public void creatRegister() throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException, ParseException {
        message = "";
        if (register.getIdaccount() != null && !register.getIdregister().equals("") && register.getQuantity() != 0) {
            if (!checkIdregister()) {
                if (!checkIdLecturerByIdProject()) {
                    if(register.getQuantity() <= 100 && register.getQuantity() > 20){
                    RegisterDAO dao = new RegisterDAO();
                    boolean check = dao.creat(register.getIdregister(), register.getIdproject(),
                            register.getIdaccount(), register.getQuantity());
                    if (check == true) {
                        register.setIdregister(null);
                        register.setQuantity(0);
                        message = "";
                    } else {
                        message = "Thất bại";
                    }
                    }else{
                        message = "Số lượng mở tối đa 100 sinh viên và tối thiểu 20 sinh viên.";
                    }
                } else {
                    message = "Giảng viên hướng dẫn đã có trong danh sách!";
                }
            } else {
                message = "Mã đăng kí đã tồn tại";
            }
        } else {
            message = "Điền đầy đủ các thông tin";
        }
    }

    public ArrayList<Register> getRegisters() throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        RegisterDAO dao = new RegisterDAO();
        return dao.findRegisters();
    }

    public boolean checkRegisters() throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        if (getRegisters().size() != 0) {
            return true;
        }
        return false;
    }

    public int countStudentRegister(String id) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        RegisterHasStudentDAO dao = new RegisterHasStudentDAO();
        return dao.countStudentByIdRegister(id);
    }

    public int getQuantityByIdregister(String idregister) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        RegisterDAO dao = new RegisterDAO();
        return dao.findQuantityByIdRegister(idregister);
    }

    public void deleteRegisterByIdStudent(int idstudent, String idregister) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        RegisterHasStudentDAO dao = new RegisterHasStudentDAO();
        dao.delete(idstudent, idregister);
    }

    public void autoArrangeStudent() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        beanStudent bS = new beanStudent();
        register.setIdproject(r.getIdproject());
        ArrayList<Register> registerList = getRegitersByIdProject(register.getIdproject());
        
        System.out.println("Register list: ");
        for (Register item : registerList){
            System.out.println(item.getIdregister());
        }
        
//        beanListProject bLP = new beanListProject();
        ArrayList<Student> studentAlreadyRegisterList = listOfStudentRegisterdInProject(registerList);
        
        System.out.println("student already registered: ");
        for (Student item : studentAlreadyRegisterList) {
            System.out.println(item.getIdstudent());
        }
        
        StudentDAO sD = new StudentDAO();
//        bLP.setList(sD.findStudentByIdRegister(bR.getIdproject()));
        ArrayList<Student> studentList = sD.findStudentsByIdProject(register.getIdproject());
        
        System.out.println("List of student in project:");
        for (Student item : studentList) {
            System.out.println(item.getIdstudent());
        }
        
        ArrayList<Student> studentRestList = studentRestList(studentAlreadyRegisterList, studentList);
        int i = 0;
        
        int totalOpen = 0;
        for (Register item : registerList) {
            totalOpen += item.getQuantity();
        }
        int a;
        if(totalOpen<studentRestList.size()){
            a = totalOpen;
        }
        else{
            a = studentRestList.size();
        }
        while(i < a){
            for (Register item : registerList) {
            
                RegisterHasStudentDAO dao = new RegisterHasStudentDAO();
                if (bS.checkCompareAmount(item.getIdregister())) {
                    dao.create(item.getIdregister(), studentRestList.get(i).getIdstudent());
                    System.out.println("Create i: "+i);
                    i++;
                }           
                System.out.println(i);
                if (i >= a) {
                    System.out.println("break i: "+i);
                    break ;
                }
            }
        }
    }
    
    public boolean studentAlreadyProject(int studentID, ArrayList<Student> studentList) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        boolean check = false;
        for (Student item : studentList) {
            if (item.getIdstudent() == studentID) {
                check = true;
                break;
            }
        }
        return check;
    }

    public ArrayList<Student> studentRestList(ArrayList<Student> studentAlreadyRegisterList, ArrayList<Student> studentList) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        ArrayList<Student> studentRestList = new ArrayList<>();
        for (Student item : studentList) {
            if (!studentAlreadyProject(item.getIdstudent(), studentAlreadyRegisterList)) {
                studentRestList.add(item);
            }
        }
        return studentRestList;
    }

    public ArrayList<Student> listOfStudentRegisterdInProject(ArrayList<Register> registerList) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        RegisterHasStudentDAO rhsD = new RegisterHasStudentDAO();
        ArrayList<Student> studentList = new ArrayList<>();
        for (Register item : registerList) {
            studentList.addAll(rhsD.lisfOfStudentInRegister(item.getIdregister()));
        }
        return studentList;
    }

    public void autoCreateRegister() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        message = "";
        beanAccount bL = new beanAccount();
        ArrayList<AccountAL> list = bL.getAllLecturers();
        int count = 0;
        for (AccountAL item : list) {
            count++;
        }
        beanAccount bA = new beanAccount();
        int number = bA.checkAmountStudentHasProject(register.getIdproject());
        int studentPerLecturer = number / count;
        int phandu = number - studentPerLecturer * count;
        int maDangKyINT = 1;

        String maDangKyString;
        for (AccountAL item : list) {
            register.setIdaccount(item.getIdaccount());
            maDangKyString = Integer.toString(maDangKyINT);
            maDangKyINT++;
            register.setIdregister(register.getIdproject() + "." + maDangKyString);
            register.setQuantity(studentPerLecturer + phandu);
            message += register.getIdregister() + "," + register.getIdproject() + ","
                    + register.getIdaccount()+ "," + register.getQuantity() + "|";
            RegisterDAO rD = new RegisterDAO();
            boolean check = rD.creat(register.getIdregister(), register.getIdproject(),
                    register.getIdaccount(), register.getQuantity());
            if (check == true) {
                register.setIdregister(null);
                register.setQuantity(0);
                message = "Thành công";
            } else {
                message = "Thất bại";
            }
        }
    }
    
    public void getNameLecturer(AjaxBehaviorEvent event) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        AccountALDAO dao = new AccountALDAO();
        ArrayList<AccountAL> list = dao.findAllLectrers();
        for (AccountAL items : list) {
            if(register.getIdaccount().equals(items.getIdaccount())){
                name = dao.findNameAccountByIdAccount(register.getIdaccount());
            }
        }
    }
    
}
