package com.movie.dto;

import java.sql.Timestamp;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class QnaVO {

	private int qseq;
	@NotEmpty
	private String subject;
	private String category;
	@NotEmpty
	private String content;
	private String reply;
	private String id;
	private String rep;
	private Timestamp indate;
	
}
