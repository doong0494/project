package com.movie.service;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.dao.IOrderDao;
import com.movie.dao.ITheaterDao;
import com.movie.dto.OrderVO;
import com.movie.dto.Paging;
import com.movie.dto.TheaterVO;

@Service
public class OrderService {
	
	@Autowired
	IOrderDao odao;
	
	@Autowired
	ITheaterDao tdao;
	
	public OrderVO getOrder(OrderVO ordervo) {
		return odao.getOrder(ordervo);
	}
	
	public void insertOrder(OrderVO ordervo) {
		odao.insertOrder(ordervo);
	}
	
	public void deleteOrderMember(String id) {
		odao.deleteOrderMember(id);
	}
	
	public ArrayList<OrderVO> listOrder(String id, Paging paging){
		return odao.listOrder(id, paging);
	}
	
	public OrderVO getOrderList(int orderno) {
		return odao.getOrderList(orderno);
	}
	
	public void deleteMovie(int movieno) {
		odao.deleteMovie(movieno);
	}
	
	public void deleteOrder(int orderno) {
		odao.deleteOrder(orderno);
	}
	
	public ArrayList<TheaterVO> getTheater(){
		return tdao.getTheater();
	}
	
	public TheaterVO getOrder(String cinemas) {
		return tdao.getOrder(cinemas);
	}
	
	public TheaterVO selectTheater(String cinemas) {
		return tdao.selectTheater(cinemas);
	}
}
