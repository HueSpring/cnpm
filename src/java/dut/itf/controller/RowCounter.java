package dut.itf.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class RowCounter {

    private int row = 0;
    private int row1 = 0;
    
    public int getRow() {
    	return ++row;
    }

    public int getRow1() {
    	return ++row1;
    }

}
