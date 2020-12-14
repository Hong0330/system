package kr.ac.sunmoon.urs.emp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.sunmoon.urs.dept.Dept;
import kr.ac.sunmoon.urs.dept.DeptMapper;
import kr.ac.sunmoon.urs.lockdevice.LockDevice;
import kr.ac.sunmoon.urs.lockdevice.LockDeviceMapper;
import kr.ac.sunmoon.urs.lockdevice.LockDeviceServiceImpl;
import kr.ac.sunmoon.urs.member.Member;
import kr.ac.sunmoon.urs.member.MemberMapper;
import kr.ac.sunmoon.urs.rental.RentalMapper;

@Service
public class EmpServiceImpl implements EmpService {
	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private LockDeviceServiceImpl lockDeviceService;
	
	@Autowired
	private LockDeviceMapper lockDeviceMapper;
	
	@Autowired
	private DeptMapper deptMapper;
	
	@Override
	public void addEmp(Member member) throws Exception {
		Dept dept = new Dept();
		dept.setNo(member.getDeptNo());
		if (deptMapper.count(dept) == 0) {
			
			return;
		}
		
		Member tempMember = new Member();
		tempMember.setMemberNo(member.getMemberNo());
		if (memberMapper.count(tempMember) > 0) {
			
			return;
		}
		
		tempMember = new Member();
		tempMember.setDeptNo(member.getDeptNo());
		tempMember.setType("E");
		if (memberMapper.count(tempMember) == 0) {
			member.setPassword("rainism33");
			member.setType("E");
			memberMapper.insert(member);
		}
	}

	@Override
	public List<Member> listEmp(Member member) throws Exception {
		List<Member> rows = null;
		
		member.setType("E");
		rows = memberMapper.list(member);
		
		return rows;
	}

	@Override
	public Member viewEmp(Member member) throws Exception {
		member.setType("E");
		member = memberMapper.select(member);
		
		if (member != null) {
			Dept dept = new Dept();
			dept.setNo(member.getDeptNo());
				
			dept = deptMapper.select(dept);
			
			if (dept != null) {
				member.setDeptName(dept.getName());
			}
		}
		
		return member;
	}

	@Override
	public void editEmp(Member member) throws Exception {
		Member tempMember = new Member();
		tempMember.setMemberNo(member.getMemberNo());
		
		if (memberMapper.count(tempMember) != 0) {
			memberMapper.update(member);
		}	

	}

	@Override
	public boolean deleteEmp(Member member) throws Exception {
		member.setType("E");
		
		Member selectedMember = memberMapper.select(member);
		LockDevice lockDevice = new LockDevice();
		lockDevice.setEmpNo(selectedMember.getMemberNo());
		if (lockDeviceMapper.count(lockDevice) > 0) {
			if (lockDeviceService.deleteLockDevice(lockDevice)) {
				memberMapper.delete(member);
				
				return true;
			} else {
				
				return false;
			}
		} else {
			memberMapper.delete(member);
			
			return true;
		}	
	}
}
