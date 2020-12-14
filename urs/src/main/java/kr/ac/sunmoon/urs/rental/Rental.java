package kr.ac.sunmoon.urs.rental;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Rental implements Serializable {
    private int no;
    private int studentNo;
    private int lockDeviceNo;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date rentalDate;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date returnDate;
    private String status;

    private String name;
    private String phone;
    private String location;
    
    private int itemStart;
    private int itemSizePerPage;  
    
    public Rental() {
    }

    public Rental(int no, int studentNo, int lockDeviceNo, Date rentalDate, Date returnDate, String status, int itemStart, int itemSizePerPage, String location) {
        this.no = no;
        this.studentNo = studentNo;
        this.lockDeviceNo = lockDeviceNo;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.status = status;
        this.location = location;

        this.itemStart = itemStart;
        this.itemSizePerPage = itemSizePerPage;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getNo() {
        return this.no;
    }

    public void setStudentNo(int studentNo) {
        this.studentNo = studentNo;
    }

    public int getStudentNo() {
        return this.studentNo;
    }

    public void setLockDeviceNo(int lockDeviceNo) {
        this.lockDeviceNo = lockDeviceNo;
    }

    public int getLockDeviceNo() {
        return this.lockDeviceNo;
    }

    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
    }

    public Date getRentalDate() {
        return this.rentalDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Date getReturnDate() {
        return this.returnDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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
}