package dut.itf.controller;

import dut.itf.dao.OpenCloseDAO;
import dut.itf.dao.RegisterDAO;
import dut.itf.dao.RegisterHasStudentDAO;
import dut.itf.dao.StudentDAO;
import dut.itf.entity.OpenClose;
import dut.itf.entity.Register;
import dut.itf.entity.RegisterHasStudent;
import dut.itf.entity.Student;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean
@SessionScoped
public class beanStudent {

    private String nameGroup;
    private Student student = new Student();
    private ArrayList<Register> listR = new ArrayList<>();
    private ArrayList<Student> listS = new ArrayList<>();
    private ArrayList<Student> listS2 = new ArrayList<>();
    private transient String message = "";
    private transient String messageI = "";
    private String idname;
    private String passwordOld;
    private String passwordNew1;
    private String passwordNew2;
    private boolean check;

    public beanStudent() {
    }

    public String getMessageI() {
        return messageI;
    }

    public void setMessageI(String messageI) {
        this.messageI = messageI;
    }

    public String getNameGroup() {
        return nameGroup;
    }

    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }

    public ArrayList<Student> getListS2() {
        return listS2;
    }

    public void setListS2(ArrayList<Student> listS2) {
        this.listS2 = listS2;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getPasswordOld() {
        return passwordOld;
    }

    public void setPasswordOld(String passwordOld) {
        this.passwordOld = passwordOld;
    }

    public String getPasswordNew1() {
        return passwordNew1;
    }

    public void setPasswordNew1(String passwordNew1) {
        this.passwordNew1 = passwordNew1;
    }

    public String getPasswordNew2() {
        return passwordNew2;
    }

    public void setPasswordNew2(String passwordNew2) {
        this.passwordNew2 = passwordNew2;
    }

    public String getIdname() {
        return idname;
    }

    public void setIdname(String idname) {
        this.idname = idname;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Register> getListR() {
        return listR;
    }

    public void setListR(ArrayList<Register> listR) {
        this.listR = listR;
    }

    public ArrayList<Student> getListS() {
        return listS;
    }

    public void setListS(ArrayList<Student> listS) {
        this.listS = listS;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getIdstudent() {
        return student.getIdstudent();
    }

    public void setIdstudent(int idstudent) {
        student.setIdstudent(idstudent);
    }

    public String getPassword() {
        return student.getPassword();
    }

    public void setPassword(String password) {
        student.setPassword(password);
    }

    public String getName() {
        return student.getName();
    }

    public void setName(String name) {
        student.setName(name);
    }

    public String getClasses() {
        return student.getClasses();
    }

    public void setClasses(String classes) {
        student.setClasses(classes);
    }

    public Date getDob() {
        return student.getDob();
    }

    public void setDob(Date dob) {
        student.setDob(dob);
    }

    public String getAddress() {
        return student.getAddress();
    }

    public void setAddress(String address) {
        student.setAddress(address);
    }

    public String getPhone() {
        return student.getPhone();
    }

    public void setPhone(String phone) {
        student.setPhone(phone);
    }

    public String getEmail() {
        return student.getEmail();
    }

    public void setEmail(String email) {
        student.setEmail(email);
    }

    Register r = new Register();

    public Register getR() {
        return r;
    }

    public void setR(Register r) {
        this.r = r;
    }

    public String getStudentsByIdProject(String idproject) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        r.setIdproject(idproject);
        listS = null;
        StudentDAO dao = new StudentDAO();
        listS = dao.findStudentsByIdProject(idproject);
        if (!listS.isEmpty()){
            return "listStudentByIdProject";
        }
        return "errorCreateStudent";
    }

    public String getStudentByIdregister(String idregister) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        StudentDAO dao = new StudentDAO();
        listS2 = dao.findStudentsByIdRegister(idregister);
        if (!listS2.isEmpty()) {
            return "listStudentByIdRegister";
        }
        return "errorCreateStudent";
    }

    public Student getStudentByIdStudent(int idstudent) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        StudentDAO dao = new StudentDAO();
        return dao.findStudentByIdStudent(idstudent);
    }

    public boolean checkDisplay() {
        if (listS.size() != 0) {
            return true;
        }
        return false;
    }

    public boolean checkDisplay2() {
        if (listS2.size() != 0) {
            return true;
        }
        return false;
    }

    public boolean checkIdstudent(int idstudent) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException, ParseException {
        StudentDAO dao = new StudentDAO();
        Student acc = dao.findStudentByIdStudent(idstudent);
        if (acc.getIdstudent() != 0) {
            return true;
        }
        return false;
    }

    public boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String checkLogin() throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException, ParseException {
        message = "";
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.setAttribute("idname", getIdname());
        StudentDAO dao = new StudentDAO();
        if (isInteger(idname)) {
            student.setIdstudent(Integer.parseInt(idname));
            if (checkIdstudent(student.getIdstudent())) {
                Student acc = dao.findStudentByIdStudent(student.getIdstudent());
                if (acc != null && acc.getIdstudent() == student.getIdstudent()
                        && acc.getPassword().equals(student.getPassword())
                        && student.getPassword().length() >= 6) {
                    student.setName(acc.getName());
                    student.setDob(acc.getDob());
                    student.setClasses(acc.getClasses());
                    student.setAddress(acc.getAddress());
                    student.setPhone(acc.getPhone());
                    student.setEmail(acc.getEmail());
                    message = "";
                    return "homestudent";
                }
                student.setPassword(null);
                message = "Mật khẩu không đúng";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
                return "student";
            } else {
                student.setPassword(null);
                message = "Mã đăng nhập không tồn tại.";
                return "student";
            }
        } else {
            student.setPassword(null);
            message = "Mã đăng nhập không đúng.";
            return "student";
        }
    }

    public String logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.removeAttribute("idstudent");
        student.setIdstudent(0);
        setIdname(null);
        message = "";
        return "student";
    }

    public ArrayList<Student> getStudentHasProNoPass() throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        StudentDAO dao = new StudentDAO();
        return dao.findStudentsNoPass();
    }

    public ArrayList<Student> getStudentByIdGroup(String idgroup) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        StudentDAO dao = new StudentDAO();
        return dao.findStudentsByIdGroup(idgroup);
    }

    public boolean checkGetStudentHasProNoPass() throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        if (getStudentHasProNoPass().size() != 0) {
            return true;
        }
        return false;
    }

    public String identifyNoPass() throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        if (checkGetStudentHasProNoPass()) {
            return "createStudent";
        }
        return "errorCreateStudent";
    }

    public void creatAccountStudent() throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        message = "";
        if (!getStudentHasProNoPass().isEmpty()) {
            StudentDAO dao = new StudentDAO();
            boolean check = dao.createForAllStudents();
            if (check) {
                message = "Tạo thành công.";
            } else {
                message = "Không thành công";
            }
        } else {
            message = "Không có sinh viên nào";
        }
    }

    //use for admin - creat a new password for student
    public void updateAccountStudent() throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException, ParseException {
        message = "";
        student.setIdstudent(Integer.parseInt(getIdname()));
        if (checkIdstudent(student.getIdstudent())) {
            StudentDAO dao = new StudentDAO();
            boolean check = dao.updatePassword(student.getPassword(), student.getIdstudent());
            if (check == true) {
                message = "Tạo thành công.";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
            } else {
                message = "Không thành công";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
            }
        } else {
            message = "Mã không tồn tại";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
        }
    }

    public ArrayList<Student> getStudents() throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        StudentDAO dao = new StudentDAO();
        return dao.findAllStudents();
    }

    public ArrayList<Student> getStudentsByIdRegister(String idregister) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        StudentDAO dao = new StudentDAO();
        return dao.findStudentsByIdRegister(idregister);
    }

    public ArrayList<Student> getStudentsByIdGroup(String idgroup) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        StudentDAO dao = new StudentDAO();
        return dao.findStudentsByIdGroup(idgroup);
    }

    public boolean checkIdStudent(int idstudent) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        ArrayList<Student> list = getStudents();
        for (Student item : list) {
            if (item.getIdstudent() == idstudent) {
                return true;
            }
        }
        return false;
    }

    public String getNameStudentByIdStudent(int idstudent) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        if (checkIdStudent(idstudent)) {
            StudentDAO dao = new StudentDAO();
            return dao.findStudentByIdStudent(idstudent).getName();
        } else {
            return "Không tìm thấy";
        }
    }

    public void updateStudent() throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        messageI = "";
        if (student.getDob() != null && !student.getClasses().equals("") && !student.getAddress().equals("")
                && !student.getPhone().equals("") && !student.getEmail().equals("")) {
            if(isInteger(student.getPhone())){
            StudentDAO dao = new StudentDAO();
            boolean check = dao.update(student.getDob(), student.getClasses(),
                    student.getAddress(), student.getPhone(), student.getEmail(), student.getIdstudent());
            if (check == true) {
                messageI = "Lưu thành công";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(messageI));
            } else {
                messageI = "Không thành công";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(messageI));
            }
            }else{
                messageI = "Số điện thoại không hợp lệ";
            }
        } else {
            messageI = "Cần nhập đầy đủ các giá trị";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(messageI));
        }
    }

    public void changePassword() throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        message = "";
        student.setIdstudent(Integer.parseInt(getIdname()));
        if (passwordOld.equals(student.getPassword())) {
            if (!passwordOld.equals(passwordNew1)) {
                if (passwordNew1.equals(passwordNew2)) {
                    if (passwordNew1.length() >= 6) {
                        StudentDAO dao = new StudentDAO();
                        boolean check = dao.updatePassword(passwordNew1, student.getIdstudent());
                        if (check == true) {
                            student.setPassword(passwordNew1);
                            message = "Đổi thành công";
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
                        } else {
                            message = "Đổi không thành công";
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
                        }
                    } else {
                        message = "Mật khẩu phải lớn hơn hoặc bằng 6 kí tự";
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
                    }
                } else {
                    message = "Mật khẩu nhập lại không đúng";
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
                }
            } else {
                message = "Không dùng lại mật khẩu cũ";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
            }
        } else {
            message = "Mật khẩu cũ không đúng";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
        }
    }

    public int countAmountStudentByIdRegister(String idregister) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        StudentDAO dao = new StudentDAO();
        return dao.countStudentByIdRegister(idregister);
    }

    public int countAmountStudentByIdProject(String idproject) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        StudentDAO dao = new StudentDAO();
        return dao.countStudentByIdProject(idproject);
    }

    public void getRegitersByIdProject(String id) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        RegisterDAO dao = new RegisterDAO();
        listR = dao.findRegistersByIdProject(id);
        check = true;
    }

    public void creatRegister(String idregister, String idproject) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        RegisterHasStudentDAO dao = new RegisterHasStudentDAO();
        if (checkBoth(idregister) && !checkStudentRegisterProject(idproject)) {
            boolean test = dao.create(idregister, student.getIdstudent());
            if (test) {
                message = "";
                check = false;
            } else {
                message = "Đăng kí không thành công";
            }
        } else {
            message = "Không phải thời gian đăng kí.";
        }
    }

    public boolean checkRegiterHasStudent() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        RegisterHasStudentDAO dao = new RegisterHasStudentDAO();
        ArrayList<RegisterHasStudent> list = dao.findRegisterHasStudentByIdStudent(student.getIdstudent());
        if (list.size() == 0) {
            return false;
        }
        return true;
    }

    public boolean checkStudentRegisterProject(String idproject) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        RegisterHasStudentDAO dao = new RegisterHasStudentDAO();
        ArrayList<RegisterHasStudent> list = dao.findRegisterHasStudentByIdProject(idproject);
        for (RegisterHasStudent items : list) {
            if (items.getIdstudent() == student.getIdstudent()) {
                return true;
            }
        }
        return false;
    }

    //check amount & registed
    public boolean checkBoth2(String idproject, String idregister) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        if (checkCompareAmount(idregister) && !checkStudentRegisterProject(idproject)) {
            return true;
        }
        return false;
    }

    //check amount & time
    public boolean checkBoth(String idregister) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        if (checkCompareAmount(idregister) && checkCompareTime()) {
            return true;
        }
        return false;
    }

    //check time
    public boolean checkCompareTime() throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        OpenCloseDAO dao = new OpenCloseDAO();
        ArrayList<OpenClose> l = dao.findAll();
        Date d = new Date();
        String date = toStringDate(d);
        String time = toStringTime(d);
        String timeopen = "";
        String dateopen = "";
        String timeclose = "";
        String dateclose = "";
        for (OpenClose item : l) {
            timeopen = toStringTime(item.getTimeopen());
            dateopen = toStringDate(item.getDayopen());
            timeclose = toStringTime(item.getTimeclose());
            dateclose = toStringDate(item.getDayclose());
        }
        String sCurrent = date + time;
        String sOpen = dateopen + timeopen;
        String sClose = dateclose + timeclose;

        if (sCurrent.compareTo(sOpen) > 0 && sCurrent.compareTo(sClose) < 0) {
            return true;
        }
        return false;
    }

    public boolean checkCompareAmount(String idregister) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        RegisterHasStudentDAO dao1 = new RegisterHasStudentDAO();
        RegisterDAO dao2 = new RegisterDAO();
        if (dao1.countStudentByIdRegister(idregister) < dao2.findQuantityByIdRegister(idregister)) {
            return true;
        }
        return false;
    }

    public String toStringDate(Date date) {
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        return d.format(date);
    }

    public String toStringTime(Date time) {
        SimpleDateFormat d = new SimpleDateFormat("HH:mm:ss");
        return d.format(time);
    }

    //identify register GVHD

    public String identifyRegisterLecturer() throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        if (checkCompareTime()) {
            message = "";
            return "registerLecturer";
        }
        return "error";
    }

    //identity creat GVHD

    public String indentifyCreateRegister() throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        if (checkCompareTime()) {
            return "errorRegister";
        }
        return "prepareRegister";
    }

    public String identifyRegisterGroup() throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        if (!checkCompareTime()) {
            message = "";
            return "createGroup";
        }
        return "error";
    }

    public void seeNameStudent() {
        student.setIdstudent(Integer.parseInt(idname));
    }

    public String listStudentByIdGroup(String idgroup) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        listS = getStudentByIdGroup(idgroup);
        beanTopic bt = new beanTopic();;
        nameGroup = bt.getGroupByIdGroup(idgroup).getNamegroup();
        return "listStudentByIdGroup";
    }

    //destroy message to 'trang ca nhan'
    public String destroyHomeStudent(){
        message = "";
        messageI = "";
        return "homestudent";
    }
}
