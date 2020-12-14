package kr.ac.sunmoon.urs.lockdevice;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LockDeviceMapper {
    public int count(LockDevice lockDevice) throws Exception;
    public List<LockDevice> list(LockDevice lockDevice) throws Exception;
    public LockDevice select(LockDevice lockDevice) throws Exception;
    public void insert(LockDevice lockDevice) throws Exception;
    public void update(LockDevice lockDevice) throws Exception;
    public void delete(LockDevice lockDevice) throws Exception;
}