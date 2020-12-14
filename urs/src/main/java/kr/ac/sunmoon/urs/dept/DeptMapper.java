package kr.ac.sunmoon.urs.dept;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeptMapper {
    public int count(Dept dept) throws Exception;
    public List<Dept> list(Dept dept) throws Exception;
    public Dept select(Dept dept) throws Exception;
    public void insert(Dept dept) throws Exception;
    public void update(Dept dept) throws Exception;
    public void delete(Dept dept) throws Exception;
}