package com.movie.dto;

import java.sql.Timestamp;
import lombok.Data;

@Data
public class AdminVO {

	private String id;
	private String pwd;
	private String name;
	private String email;
	private String phone;
	private Timestamp indate;
}
