package kr.ac.sunmoon.urs.lockdevice;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.sunmoon.urs.common.Message;
import kr.ac.sunmoon.urs.dept.Dept;
import kr.ac.sunmoon.urs.dept.DeptMapper;
import kr.ac.sunmoon.urs.member.Member;
import kr.ac.sunmoon.urs.member.MemberMapper;
import kr.ac.sunmoon.urs.rental.Rental;
import kr.ac.sunmoon.urs.rental.RentalMapper;
import kr.ac.sunmoon.urs.rental.RentalServiceImpl;

@Service
public class LockDeviceServiceImpl implements LockDeviceService {
	@Autowired
	public LockDeviceMapper lockDeviceMapper;
	
	@Autowired
	public MemberMapper memberMapper;
	
	@Autowired
	public DeptMapper deptMapper;
	
	@Autowired
	public RentalServiceImpl rentalService;
	
	@Autowired
	public RentalMapper rentalMapper;
	
	@Override
	public void addLockDevice(LockDevice lockDevice, HttpSession session) throws Exception {
		if (lockDeviceMapper.count(lockDevice) > 0) {
			
			return;
		}
		
		Dept dept = new Dept();
		dept.setNo(lockDevice.getDeptNo());
		if (deptMapper.count(dept) == 0) {
			
			return;
		}
		
		Member member = new Member();
		member.setMemberNo(Integer.parseInt(String.valueOf(session.getAttribute("memberNo"))));
		Member selectedMember = memberMapper.select(member);
		
		lockDevice.setEmpNo(Integer.parseInt(String.valueOf(session.getAttribute("memberNo"))));
		lockDevice.setDeptNo(selectedMember.getDeptNo());
		lockDevice.setStatus("C");
		lockDeviceMapper.insert(lockDevice);
	}

	@Override
	public List<LockDevice> listLockDevice(LockDevice lockDevice) throws Exception {
		List<LockDevice> rows = lockDeviceMapper.list(lockDevice);
		
		return rows;
	}

	@Override
	public LockDevice viewLockDevice(LockDevice lockDevice) throws Exception {
		LockDevice row = lockDeviceMapper.select(lockDevice);
		if (row != null) {
			Member member = new Member();
			member.setMemberNo(row.getEmpNo());
			row.setEmpName(memberMapper.select(member).getName());
			
			Dept dept = new Dept();
			dept.setNo(row.getDeptNo());
			row.setDeptName(deptMapper.select(dept).getName());
		}
		
		return row;
	}
	
	@Override
	public void editLockDevice(LockDevice lockDevice) throws Exception {
		if (lockDevice != null) {
			LockDevice tempLockDevice = new LockDevice();
			tempLockDevice.setNo(lockDevice.getNo());
			if (lockDeviceMapper.count(tempLockDevice) > 0) {
				lockDeviceMapper.update(lockDevice);
			}
		}
	}

	@Override
	public boolean deleteLockDevice(LockDevice lockDevice) throws Exception {
		List<LockDevice> rows = lockDeviceMapper.list(lockDevice);
		if (rows != null) {
			for (LockDevice row : rows) {		
				if (row.getStatus().equals("O")) {
					return false;
				}
				
				Rental rental = new Rental();
				rental.setLockDeviceNo(row.getNo());
				if (rentalMapper.count(rental) > 0) {
					if (rentalService.deleteRental(rental)) {
						lockDeviceMapper.delete(row);
						
						return true;
					} else {
						
						return false;
					}
				} else {
					lockDeviceMapper.delete(row);
					
					return true;
				}
			}
		} 
		
		return true;
	}
	
	public Message checkLockDevice(Member member, Message message) throws Exception {
		if (memberMapper.count(member) > 0) {
			Member selectedMember = memberMapper.select(member);
			
			LockDevice lockDevice = new LockDevice();
			lockDevice.setEmpNo(selectedMember.getMemberNo());
			lockDevice.setNo(message.getLockDeviceNo());
			LockDevice selectedLockDevice = lockDeviceMapper.select(lockDevice);
			if (selectedLockDevice != null) {
				if (message.getStatus().equals("O")) {
					selectedLockDevice.setStatus("C");
					message.setStatus("C");
				} else if (message.getStatus().equals("C")) {
					selectedLockDevice.setStatus("O");
					message.setStatus("O");
				}
				lockDeviceMapper.update(selectedLockDevice);
			}
		}
		
		return message;
	}
}
