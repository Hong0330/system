package kr.ac.sunmoon.urs.common;

import javax.servlet.http.HttpSession;

import kr.ac.sunmoon.urs.member.Member;

public interface CommonService {
	public String login(Member member, HttpSession session);
	public void logout(HttpSession session);
	public Message receiveAuthInfo(Message message);
}
