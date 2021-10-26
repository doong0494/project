package com.movie.dao;

import java.util.ArrayList;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Mapper;

import com.movie.dto.OrderVO;
import com.movie.dto.Paging;


@Mapper
public interface IOrderDao {
	
	OrderVO getOrder(@Valid OrderVO ordervo);
	
	void insertOrder(@Valid OrderVO ordervo);
	
	void deleteOrderMember(String id);
	
	ArrayList<OrderVO> listOrder(String id, Paging paging);
	
	OrderVO getOrderList(int orderno);
	
	void deleteMovie(int movieno);
	
	void deleteOrder(int orderno);
	
}
