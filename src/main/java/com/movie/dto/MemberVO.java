package com.movie.dto;

import java.sql.Timestamp;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class MemberVO {

	@NotEmpty(message="id를 입력하세요")
	private String id;
	@NotEmpty(message="비밀번호를 입력하세요")
    private String pwd;     
	@NotEmpty(message="이름을 입력하세요")
    private String name;
	@NotEmpty(message="이메일을 입력하세요")
	private String email;
	private String phone;
	private Timestamp indate;

}
