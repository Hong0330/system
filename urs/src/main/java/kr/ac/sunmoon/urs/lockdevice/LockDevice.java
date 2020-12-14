package kr.ac.sunmoon.urs.lockdevice;

import java.io.Serializable;

public class LockDevice implements Serializable {
    private Integer no;
    private int empNo;
    private int deptNo;
    private String status;

    private int itemStart;
    private int itemSizePerPage;
    
    private String empName;
    private String deptName;

    public LockDevice() {
    }

    public LockDevice(Integer no, int empNo, int deptNo, String status, int itemStart, int itemSizePerPage, String deptName, String empName) {
        this.no = no;
        this.empNo = empNo;
        this.deptNo = deptNo;
        this.status = status;
        
        this.itemStart = itemStart;
        this.itemSizePerPage = itemSizePerPage;
        
        this.deptName = deptName;
        this.empName = empName;
    }

	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public int getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(int deptNo) {
		this.deptNo = deptNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getItemStart() {
		return itemStart;
	}

	public void setItemStart(int itemStart) {
		this.itemStart = itemStart;
	}

	public int getItemSizePerPage() {
		return itemSizePerPage;
	}

	public void setItemSizePerPage(int itemSizePerPage) {
		this.itemSizePerPage = itemSizePerPage;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
}