package kr.ac.sunmoon.urs.common;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import kr.ac.sunmoon.urs.lockdevice.LockDevice;
import kr.ac.sunmoon.urs.lockdevice.LockDeviceMapper;
import kr.ac.sunmoon.urs.lockdevice.LockDeviceServiceImpl;
import kr.ac.sunmoon.urs.member.Member;
import kr.ac.sunmoon.urs.member.MemberMapper;
import kr.ac.sunmoon.urs.rental.Rental;
import kr.ac.sunmoon.urs.rental.RentalMapper;
import kr.ac.sunmoon.urs.rental.RentalServiceImpl;

@Service
public class CommonServiceImpl implements CommonService {
	@Autowired
	public MemberMapper memberMapper;
	
	@Autowired
	public LockDeviceMapper lockDeviceMapper;
	
	@Autowired
	public LockDeviceServiceImpl lockDeviceService;
	
	@Autowired
	public RentalMapper rentalMapper;
	
	@Autowired
	public RentalServiceImpl rentalService;
	
	@Override
	public String login(Member member, HttpSession session) {
		try {			
			member.setType("M");
			if (memberMapper.count(member) > 0) {
				session.setAttribute("memberNo", member.getMemberNo());
				session.setAttribute("type", member.getType());
				
				return "/dept";
			}
			
			member.setType("E");
			if (memberMapper.count(member) > 0) {
				session.setAttribute("memberNo", member.getMemberNo());
				session.setAttribute("type", member.getType());
				
				Member selectedMember = memberMapper.select(member);
				session.setAttribute("empName", selectedMember.getName());
				
				return "/lockdevice";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		member.setType("");
		return "/common/login/form";
	}

	@Override
	public void logout(HttpSession session) {
		if (session != null) {
			session.invalidate();
		}
	}
	
	@Override
	public Message receiveAuthInfo(Message message) {		
		try {
			Member member = new Member();
			member.setCardNo(message.getCardNo());
			Member selectedMember = memberMapper.select(member);
		
			if (selectedMember.getType().equals("E")) {
				
				return lockDeviceService.checkLockDevice(member, message);
			} else if (selectedMember.getType().equals("S")) {
				LockDevice lockDevice = new LockDevice();
				lockDevice.setNo(message.getLockDeviceNo());
				LockDevice selectedLockDevice = lockDeviceMapper.select(lockDevice);
				
				if (selectedMember.getDeptNo() == selectedLockDevice.getDeptNo() ) {
					Rental studentRental = new Rental();
					studentRental.setStudentNo(selectedMember.getMemberNo());
					//studentRental.setLockDeviceNo(selectedLockDevice.getNo());
					Rental selectedStudentRental = rentalMapper.selectByLastOne(studentRental);
					studentRental.setLockDeviceNo(selectedLockDevice.getNo());
					
					if (selectedLockDevice.getStatus().equals("C")) {					
						if (selectedStudentRental == null || selectedStudentRental.getStatus().equals("Y")) {
							
							return rentalService.addRental(studentRental);
						} else {
							
							return null;
						}
						
					} else {
						Rental lockDeviceRental = new Rental();
						lockDeviceRental.setLockDeviceNo(selectedLockDevice.getNo());
						Rental selectedLockDeviceRental = rentalMapper.selectByLastOne(lockDeviceRental);
						
						if (selectedStudentRental.getStudentNo() == selectedLockDeviceRental.getStudentNo()) {
							
							return rentalService.returnUmbrella(selectedLockDeviceRental);
						} else {
							
							return null;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		return null;
	}
}
