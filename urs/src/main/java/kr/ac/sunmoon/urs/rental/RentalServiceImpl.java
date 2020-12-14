package kr.ac.sunmoon.urs.rental;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.sunmoon.urs.common.Message;
import kr.ac.sunmoon.urs.dept.Dept;
import kr.ac.sunmoon.urs.dept.DeptMapper;
import kr.ac.sunmoon.urs.lockdevice.LockDevice;
import kr.ac.sunmoon.urs.lockdevice.LockDeviceMapper;
import kr.ac.sunmoon.urs.lockdevice.LockDeviceServiceImpl;
import kr.ac.sunmoon.urs.member.Member;
import kr.ac.sunmoon.urs.member.MemberMapper;

@Service
public class RentalServiceImpl implements RentalService {
	@Autowired
	private RentalMapper rentalMapper;
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private LockDeviceServiceImpl	lockDeviceService;
	
	@Autowired
	private LockDeviceMapper lockDeviceMapper;
	
	@Autowired
	private DeptMapper deptMapper;
	
	@Override
	public Message addRental(Rental rental) throws Exception {
		LockDevice lockDevice = new LockDevice();
		lockDevice.setNo(rental.getLockDeviceNo());
		lockDevice.setStatus("O");
		System.out.println("test");
		lockDeviceService.editLockDevice(lockDevice);
		//lockDeviceMapper.update(lockDevice);
		
		rental.setRentalDate(new Date());
		rental.setStatus("N");
		rentalMapper.insert(rental);
		Message message = new Message();
		message.setCode("200");
		message.setStatus("O");
		
		return message;
	}
	
	@Override
	public Message returnUmbrella(Rental rental) throws Exception {
		LockDevice lockDevice = new LockDevice();
		lockDevice.setNo(rental.getLockDeviceNo());
		lockDevice.setStatus("C");
		lockDeviceService.editLockDevice(lockDevice);
		//lockDeviceMapper.update(lockDevice);
		
		rental.setReturnDate(new Date());
		rental.setStatus("Y");
		rentalMapper.update(rental);
		
		Message message = new Message();
		message.setCode("200");
		message.setStatus("C");
			
		return message;
	}

	@Override
	public List<Rental> listRental(Rental rental) throws Exception {
		
		return rentalMapper.list(rental);
	}

	@Override
	public Rental viewRental(Rental rental) throws Exception {
		rental = rentalMapper.select(rental);
		
		if (rental.getNo() != 0) {
			Member member = new Member();
			member.setMemberNo(rental.getStudentNo());	
			Member selectedMember = memberMapper.select(member);
			
			rental.setName(selectedMember.getName());
			rental.setPhone(selectedMember.getPhone());
			
			Dept dept = new Dept();
			dept.setNo(selectedMember.getDeptNo());
			Dept selectedDept = deptMapper.select(dept);
			
			rental.setLocation(selectedDept.getLocation());
		}
		
		return rental;
	}

	@Override
	@Transactional
	public void editRental(Rental rental) throws Exception {
		Rental tempRental = new Rental();
		tempRental.setNo(rental.getNo());
		tempRental = rentalMapper.select(tempRental);
		
		if (rental.getNo() == tempRental.getNo()) {
			if ("Y".equals(rental.getStatus()) || "B".equals(rental.getStatus())) {
				rental.setReturnDate(new Date());
			}
			
			rentalMapper.update(rental);
		}
	}

	@Override
	@Transactional
	public boolean deleteRental(Rental rental) throws Exception {
		List<Rental> rows = rentalMapper.list(rental);
		if (rows != null) {
			for (Rental row : rows) {	
				if ("N".equals(row.getStatus()) && "B".equals(row.getStatus())) {
					
					return false;
				}	
				
				row = rentalMapper.select(row);
				if ("Y".equals(row.getStatus())) {
					rentalMapper.delete(row);
				} else {
					
					return false;
				}
			}
			
			return true;
		}
		
		return false;
	}
}
