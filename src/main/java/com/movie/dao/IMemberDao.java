package com.movie.dao;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Mapper;

import com.movie.dto.MemberVO;

@Mapper
public interface IMemberDao {

	MemberVO getMember(String id);

	void insertMember(@Valid MemberVO membervo);

	void updateMember(MemberVO membervo);

	void deleteMember(String id);

	MemberVO confirmPhone(String name, String phone);

	void resetPwd(MemberVO mvo);

	MemberVO confirmIdnamePhone(String id, String name, String phone);

	void deleteQnaMember(String id);

	void deleteOrdersMember(String id);
}
