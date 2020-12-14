package kr.ac.sunmoon.urs.member;

import java.io.Serializable;

public class Member implements Serializable {
    private int memberNo;
    private int deptNo;
    private String name;
    private String password;
    private String cardNo;
    private String phone;
    private String type;
    
    private int itemStart;
    private int itemSizePerPage;
    
    private String deptName;
    
    private boolean isRememberMemberNo;

    public Member() {
    }

    public Member(int memberNo, int deptNo, String name, String password, String cardNo, String phone, String type, int itemStart, int itemSizePerPage, boolean isRememberMemberNo) {
        this.memberNo = memberNo;
        this.deptNo = deptNo;
        this.name = name;
        this.password = password;
        this.cardNo = cardNo;
        this.phone = phone;
        this.type = type;

        this.itemStart = itemStart;
        this.itemSizePerPage = itemSizePerPage;
        
        this.isRememberMemberNo = isRememberMemberNo;
    }

    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
    }

    public int getMemberNo() {
        return this.memberNo;
    }

    public void setDeptNo(int deptSeqNo) {
        this.deptNo = deptSeqNo;
    }

    public int getDeptNo() {
        return this.deptNo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardNo() {
        return this.cardNo;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void setItemStart(int itemStart) {
        this.itemStart = itemStart;
    }

    public int getItemStart() {
        return this.itemStart;
    }

    public void setItemSizePerPage(int itemSizePerPage) {
        this.itemSizePerPage = itemSizePerPage;
    }

    public int getItemSizePerPage() {
        return this.itemSizePerPage;
    }
    
    public void setDeptName(String deptName) {
    	this.deptName = deptName;
    }
    
    public String getDeptName() {
    	return this.deptName;
    }

	public boolean isRememberMemberNo() {
		return isRememberMemberNo;
	}

	public void setRememberMemberNo(boolean isRememberMemberNo) {
		this.isRememberMemberNo = isRememberMemberNo;
	}
}