package kr.ac.sunmoon.urs.dept;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.sunmoon.urs.emp.EmpServiceImpl;
import kr.ac.sunmoon.urs.lockdevice.LockDevice;
import kr.ac.sunmoon.urs.lockdevice.LockDeviceMapper;
import kr.ac.sunmoon.urs.lockdevice.LockDeviceServiceImpl;
import kr.ac.sunmoon.urs.member.Member;
import kr.ac.sunmoon.urs.member.MemberMapper;
import kr.ac.sunmoon.urs.student.StudentServiceImpl;

@Service
public class DeptServiceImpl implements DeptService {
	@Autowired
	private DeptMapper deptMapper;

	@Autowired
	private MemberMapper memberMapper;

	@Autowired
	private StudentServiceImpl studentService;

	@Autowired
	private EmpServiceImpl empService;

	@Override
	public void addDept(Dept dept) throws Exception {
		Dept tempDept = new Dept();

		tempDept.setName(dept.getName());

		if (deptMapper.count(tempDept) == 0) {
			deptMapper.insert(dept);
		}
	}

	@Override
	public List<Dept> listDept(Dept dept) throws Exception {
		return deptMapper.list(dept);
	}

	@Override
	public Dept viewDept(Dept dept) throws Exception {
		dept = deptMapper.select(dept);

		if (dept != null) {
			Member member = new Member();
			member.setDeptNo(dept.getNo());
			member.setType("E");
			member = memberMapper.select(member);

			if (member != null) {
				dept.setEmpName(member.getName());
				dept.setPhone(member.getPhone());
			}
		}

		return dept;
	}

	@Override
	@Transactional
	public void editDept(Dept dept) throws Exception {
		Dept tempDept = new Dept();
		tempDept.setNo(dept.getNo());
		tempDept = deptMapper.select(tempDept);
		
		if (dept.getNo() == tempDept.getNo()) {
			deptMapper.update(dept);
		}
	}

	@Override
	@Transactional
	public boolean deleteDept(Dept dept) throws Exception {
		Dept selectedDept = deptMapper.select(dept);

		if (deptMapper.count(selectedDept) > 0) {
			Member member = new Member();
			member.setDeptNo(dept.getNo());
			System.out.println(dept.getNo());

			if (memberMapper.count(member) > 0) { 
				if (studentService.deleteStudent(member)) {
					if (empService.deleteEmp(member)) {
						deptMapper.delete(selectedDept);
						
						return true;
					} else {
						
						return false;
					}
				} else {
					
					return false;
				}
			} else {
				deptMapper.delete(selectedDept);
				
				return true;
			}
		}
	
		return false;
	}
}
