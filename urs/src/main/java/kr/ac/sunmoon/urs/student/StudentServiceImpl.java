package kr.ac.sunmoon.urs.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.sunmoon.urs.dept.Dept;
import kr.ac.sunmoon.urs.dept.DeptMapper;
import kr.ac.sunmoon.urs.member.Member;
import kr.ac.sunmoon.urs.member.MemberMapper;
import kr.ac.sunmoon.urs.rental.Rental;
import kr.ac.sunmoon.urs.rental.RentalMapper;
import kr.ac.sunmoon.urs.rental.RentalServiceImpl;

@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private RentalMapper rentalMapper;
	
	@Autowired
	private DeptMapper deptMapper;
	
	@Autowired
	private RentalServiceImpl rentalService;
	
	@Override
	public void addStudent(Member member) throws Exception {
		Dept dept = new Dept();
		dept.setNo(member.getDeptNo());
		if (deptMapper.count(dept) == 0) {
			
			return;
		}
		
		Member tempMember = new Member();
		tempMember.setMemberNo(member.getMemberNo());
		
		if (memberMapper.count(tempMember) == 0) {
			member.setType("S");
			memberMapper.insert(member);
		}
	}

	@Override
	public List<Member> listStudent(Member member) throws Exception {
		List<Member> rows = null;
		
		member.setType("S");
		rows = memberMapper.list(member);
		
		return rows;
	}

	@Override
	public Member viewStudent(Member member) throws Exception {
		member.setType("S");
		member = memberMapper.select(member);
		
		if (member != null) {
			Dept dept = new Dept();
			dept.setNo(member.getDeptNo());
			
			dept = deptMapper.select(dept);
			member.setDeptName(dept.getName());
		}
		
		return member;
	}

	@Override
	@Transactional
	public void editStudent(Member member) throws Exception {
		Member tempMember = new Member();
		tempMember.setMemberNo(member.getMemberNo());
		
		tempMember = memberMapper.select(tempMember);
		if (memberMapper.count(tempMember) != 0) {
			memberMapper.update(member);
		}	
	}

	@Override
	@Transactional
	public boolean deleteStudent(Member member) throws Exception {
		member.setType("S");
		List<Member> rows = memberMapper.list(member);
		if (rows != null) {
			for (Member row : rows) {
				Rental rental = new Rental();
				rental.setStudentNo(row.getMemberNo());
				
				if (rentalMapper.count(rental) > 0) {
					if (rentalService.deleteRental(rental)) {
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
		
		return true;
	}
}
