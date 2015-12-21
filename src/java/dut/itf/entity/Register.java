package dut.itf.entity;

public class Register {

    private String idregister;
    private String idproject;
    private String idaccount;
    private int quantity;

    public Register() {
    }

    public Register(String idregister, String idproject, String idaccount, int quantity) {
        this.idregister = idregister;
        this.idproject = idproject;
        this.idaccount = idaccount;
        this.quantity = quantity;
    }

    public String getIdregister() {
        return idregister;
    }

    public void setIdregister(String idregister) {
        this.idregister = idregister;
    }

    public String getIdproject() {
        return idproject;
    }

    public void setIdproject(String idproject) {
        this.idproject = idproject;
    }

    public String getIdaccount() {
        return idaccount;
    }

    public void setIdaccount(String idaccount) {
        this.idaccount = idaccount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
