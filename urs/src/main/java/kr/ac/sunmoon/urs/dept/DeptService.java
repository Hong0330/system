package kr.ac.sunmoon.urs.dept;

import java.util.List;

public interface DeptService {
	public void addDept(Dept dept) throws Exception;
	public List<Dept> listDept(Dept dept) throws Exception;
	public Dept viewDept(Dept dept) throws Exception;
	public void editDept(Dept dept) throws Exception;
	public boolean deleteDept(Dept dept) throws Exception;
}
