package com.movie.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.movie.dto.TheaterVO;

@Mapper
public interface ITheaterDao {
	
	ArrayList<TheaterVO> getTheater();
	
	TheaterVO getOrder(String cinemas);
	
	TheaterVO selectTheater(String cinemas);
		
}
