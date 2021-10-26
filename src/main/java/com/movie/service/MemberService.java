package com.movie.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.dao.IMemberDao;
import com.movie.dao.IOrderDao;
import com.movie.dto.MemberVO;

@Service
public class MemberService {

	@Autowired
	IMemberDao mdao;

	public MemberVO getMember(String id) {
		return mdao.getMember(id);
	}

	public void insertMember(MemberVO membervo) {
		mdao.insertMember(membervo);
	}

	public void updateMember(MemberVO membervo) {
		mdao.updateMember(membervo);
	}

	public void deleteMember(String id) {
		mdao.deleteQnaMember(id);
		mdao.deleteOrdersMember(id);
		mdao.deleteMember(id);
	}

	public MemberVO confirmPhone(String name, String phone) {
		return mdao.confirmPhone(name,phone);
	}

	public void resetPwd(MemberVO mvo) {
		mdao.resetPwd(mvo);
	}

	public MemberVO confirmIdnamePhone(String id, String name, String phone) {
		return mdao.confirmIdnamePhone(id,name,phone);
	}
}
