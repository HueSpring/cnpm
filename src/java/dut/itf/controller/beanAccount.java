package dut.itf.controller;

import dut.itf.dao.AccountALDAO;
import dut.itf.dao.StudentDAO;
import dut.itf.entity.AccountAL;
import dut.itf.entity.Roles;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean
@SessionScoped
public class beanAccount {

    private String dobString;
    private AccountAL account = new AccountAL();
    private String messageAI;
    private String messageAP;
    private String messageAcc;
    private String passwordOld;
    private String passwordNew1;
    private String passwordNew2;
    private ArrayList<AccountAL> list = new ArrayList<>();

    public beanAccount() {
    }

    public String getDobString() {
        return dobString;
    }

    public void setDobString(String dobString) {
        this.dobString = dobString;
    }

    public ArrayList<AccountAL> getList() {
        return list;
    }

    public void setList(ArrayList<AccountAL> list) {
        this.list = list;
    }

    public AccountAL getAccount() {
        return account;
    }

    public void setAccount(AccountAL account) {
        this.account = account;
    }

    public String getIdaccount() {
        return account.getIdaccount();
    }

    public void setIdaccount(String idaccount) {
        account.setIdaccount(idaccount);
    }

    public String getPassword() {
        return account.getPassword();
    }

    public void setPassword(String password) {
        account.setPassword(password);
    }

    public String getName() {
        return account.getName();
    }

    public void setName(String name) {
        account.setName(name);
    }

    public Date getDob() {
        return account.getDob();
    }

    public void setDob(Date dob) {
        account.setDob(dob);
    }

    public String getDegree() {
        return account.getDegree();
    }

    public void setDegree(String degree) {
        account.setDegree(degree);
    }

    public String getAddress() {
        return account.getAddress();
    }

    public void setAddress(String address) {
        account.setAddress(address);
    }

    public String getPhone() {
        return account.getPhone();
    }

    public void setPhone(String phone) {
        account.setPhone(phone);
    }

    public String getEmail() {
        return account.getEmail();
    }

    public void setEmail(String email) {
        account.setEmail(email);
    }

    public String getMessageAI() {
        return messageAI;
    }

    public String getMessageAP() {
        return messageAP;
    }

    public void setMessageAP(String messageAP) {
        this.messageAP = messageAP;
    }

    public String getMessageAcc() {
        return messageAcc;
    }

    public void setMessageAcc(String messageAcc) {
        this.messageAcc = messageAcc;
    }

    public void setMessageAI(String messageAI) {
        this.messageAI = messageAI;
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

    public boolean checkIdaccount(String idaccount) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException, ParseException {

        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.setAttribute("idaccount", account.getIdaccount());

        AccountALDAO dao = new AccountALDAO();
        AccountAL acc = dao.findAccountALByIdaccount(idaccount);
        if (acc.getIdaccount() != null) {
            return true;
        }
        return false;
    }

//checkRoles after checkIdaccount
    public int checkRoles(String idaccount) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        AccountALDAO dao = new AccountALDAO();
        ArrayList<Roles> list = dao.findRolesByIdaccount(idaccount);
        if (list.size() == 1) {
            for (Roles item : list) {
                if (item.getRole() == 0) {
                    return 0;
                }
                if (item.getRole() == 1) {
                    return 1;
                }
            }
        }
        return 2;
    }

    public boolean identifyRole() throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        if (checkRoles(account.getIdaccount()) == 2) {
            return true;
        }
        return false;
    }

    public String transferToAdmin() {
        return "homeadmin";
    }

    public String transferToLecturer() {
        return "homelecturer";
    }

    public String checkLogin() throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException, ParseException {
        messageAcc = "";
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.setAttribute("idaccount", account.getIdaccount());
        if (checkIdaccount(account.getIdaccount())) {
            AccountALDAO dao = new AccountALDAO();
            AccountAL acc = dao.findAccountALByIdaccount(account.getIdaccount());
            if (acc != null && acc.getIdaccount().equals(account.getIdaccount())
                    && acc.getPassword().equals(account.getPassword())
                    && account.getPassword().length() >= 6) {

                account.setName(acc.getName());
                account.setDob(acc.getDob());
                account.setAddress(acc.getAddress());
                account.setPhone(acc.getPhone());
                account.setDegree(acc.getDegree());
                account.setEmail(acc.getEmail());
                messageAcc = "";
                if (checkRoles(account.getIdaccount()) == 0) {
                    return "homeadmin";
                } else {
                    return "homelecturer";
                }
            }
            account.setPassword(null);
            messageAcc = "Mật khẩu không đúng";
            return "account";
        } else {
            account.setPassword(null);
            messageAcc = "Mã đăng nhập không tồn tại.";
            return "account";
        }
    }

    //log out
    public String logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.removeAttribute("idaccount");
        account.setIdaccount(null);
        return "account";
    }

    //update personal information 
    public void updateAccount() throws SQLException, ClassNotFoundException,
            InstantiationException, IllegalAccessException, ParseException {
        messageAI = "";
        messageAP = "";
        if (account.getDob() != null && !account.getAddress().equals("") && !account.getDegree().equals("")
                && !account.getPhone().equals("") && !account.getEmail().equals("")) {
            if (isInteger(account.getPhone())) {
                AccountALDAO dao = new AccountALDAO();
                boolean check = dao.update(account.getDob(), account.getDegree(),
                        account.getAddress(), account.getPhone(), account.getEmail(), account.getIdaccount());
                if (check == true) {
                    messageAI = "Lưu thành công";
                } else {
                    messageAI = "Không thành công";
                }
            } else {
                messageAI = "Số điện thoại không hợp lệ";
            }
        } else {
            messageAI = "Nhập đầy đủ các giá trị";
        }
    }

    //change password
    public void changePassword() throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        messageAP = "";
        messageAI = "";
        if (passwordOld.equals(account.getPassword())) {
            if (!passwordOld.equals(passwordNew1)) {
                if (passwordNew1.equals(passwordNew2)) {
                    if (passwordNew1.length() >= 6) {
                        AccountALDAO dao = new AccountALDAO();
                        boolean check = dao.updatePassword(passwordNew1, account.getIdaccount());
                        if (check == true) {
                            account.setPassword(passwordNew1);
                            messageAP = "Đổi thành công";
                        } else {
                            messageAP = "Đổi không thành công";
                        }
                    } else {
                        messageAP = "Mật khẩu phải lớn hơn hoặc bằng 6 kí tự";
                    }
                } else {
                    messageAP = "Mật khẩu nhập lại không đúng";
                }
            } else {
                messageAP = "Không dùng lại mật khẩu cũ";
            }
        } else {
            messageAP = "Mật khẩu cũ không đúng";
        }
    }

    //list Lecturer
    public ArrayList<AccountAL> getAllLecturers() throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        AccountALDAO dao = new AccountALDAO();
        return dao.findAllLectrers();
    }

    public String getNameLecturerByIdAccount(String idaccount) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        AccountALDAO dao = new AccountALDAO();
        return dao.findNameAccountByIdAccount(idaccount);
    }

    public String getNameLecturerByIdregister(String idregister) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        AccountALDAO dao = new AccountALDAO();
        return dao.findNameAccountByIdregister(idregister);
    }

    public String getLecturerByIdProject(String idproject) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        AccountALDAO dao = new AccountALDAO();
        list = dao.findAccountByIdproject(idproject);
        return "listLecturerByIdProject";
    }

    public int checkAmountStudentHasProject(String id) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        StudentDAO dao = new StudentDAO();
        return dao.countStudentHasProject(id);
    }

    public boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //destroy message to 'trang ca nhan'
    public String destroyHomeAdmin() {
        messageAI = "";
        messageAP = "";
        messageAcc = "";
        return "homeadmin";
    }

    public String destroyHomeLecturer() {
        messageAI = "";
        messageAP = "";
        messageAcc = "";
        return "homelecturer";
    }
}
