package kr.ac.sunmoon.urs.student;

import java.util.List;

import kr.ac.sunmoon.urs.member.Member;

public interface StudentService {
	public void addStudent(Member member) throws Exception;
	public List<Member> listStudent(Member member) throws Exception;
	public Member viewStudent(Member member) throws Exception;
	public void editStudent(Member member) throws Exception;
	public boolean deleteStudent(Member member) throws Exception;
}
