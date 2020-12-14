package kr.ac.sunmoon.urs.lockdevice;

import java.util.List;

import javax.servlet.http.HttpSession;

import kr.ac.sunmoon.urs.common.Message;
import kr.ac.sunmoon.urs.member.Member;

public interface LockDeviceService {
	public void addLockDevice(LockDevice lockingDevice, HttpSession session) throws Exception;
	public List<LockDevice> listLockDevice(LockDevice lockDevice) throws Exception;
	public LockDevice viewLockDevice(LockDevice lockDevice) throws Exception;
	public void editLockDevice(LockDevice lockDevice) throws Exception;
	public boolean deleteLockDevice(LockDevice lockDevice) throws Exception;
	public Message checkLockDevice(Member member, Message message) throws Exception;
}
