package kr.ac.sunmoon.urs.emp;

import java.util.List;

import kr.ac.sunmoon.urs.member.Member;

public interface EmpService {
	public void addEmp(Member member) throws Exception;
	public List<Member> listEmp(Member member) throws Exception;
	public Member viewEmp(Member member) throws Exception;
	public void editEmp(Member member) throws Exception;
	public boolean deleteEmp(Member member) throws Exception;
}
