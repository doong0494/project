package com.movie.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class OrderVO {
	private Integer orderno;
	private String id;
	private Integer movieno;
	private Integer quantity1;
	private Integer quantity2;
	private String cinemas;
	private String seat;
	private String movietime;
	private String moviedate;
	private Integer totalprice;
	private Timestamp orderdate;
	private String title;
	private String image;
}
