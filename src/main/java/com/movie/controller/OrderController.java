package com.movie.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.eclipse.jdt.internal.compiler.codegen.IntegerCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.movie.dto.MemberVO;
import com.movie.dto.MovieVO;
import com.movie.dto.OrderVO;
import com.movie.dto.TheaterVO;
import com.movie.service.MemberService;
import com.movie.service.MovieService;
import com.movie.service.OrderService;

@Controller
public class OrderController {

	@Autowired
	OrderService os;
	
	@Autowired
	MemberService ms;
	
	@Autowired
	MovieService mvs;
	
	//@RequestMapping("orderConfirm")
	
	@RequestMapping("orderForm")
	public ModelAndView orderForm(Model model, HttpServletRequest request, 
			@RequestParam("movieno") int movieno) {
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		ModelAndView mav = new ModelAndView();
		if (loginUser==null) { mav.setViewName("member/login");}
		else {
			MovieVO mvo = mvs.getMovie(movieno);
			mav.addObject("movie", mvo);
			List<TheaterVO> theaterList = os.getTheater();
			mav.addObject("theaterList", theaterList);
			mav.setViewName("order/orderForm");
		}
		return mav;
	}
	
	@RequestMapping("seatSelect")
	public ModelAndView seatSelect(Model model, HttpServletRequest request, 
			@RequestParam("movieno") int movieno, @RequestParam("quantity1") int quantity1, @RequestParam("time") String time,
			@RequestParam("quantity2") int quantity2, @RequestParam("date") String date, @RequestParam("cinemas") String cienmas) {
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		ModelAndView mav = new ModelAndView();
		if (loginUser==null) { mav.setViewName("member/login");}
		else {
			TheaterVO tvo = new TheaterVO();
			tvo = os.selectTheater(cienmas);
			MovieVO mvo = mvs.getMovie(movieno);
			int totalprice = (tvo.getAdult()*quantity1) + (tvo.getChild()*quantity2);
			
			mav.addObject("theater", tvo);
			mav.addObject("movie", mvo);
			mav.addObject("quantity1", quantity1);
			mav.addObject("quantity2", quantity2);
			mav.addObject("totalPrice", totalprice);
			mav.addObject("movieTime", time);
			mav.addObject("movieDate", date);
			mav.setViewName("order/seatSelect");
		}
		return mav;
	}
	
	@RequestMapping("seatSelectComplete")
	public ModelAndView seatSelectComplete(Model model, HttpServletRequest request, 
		@RequestParam("movieno") int movieno, @RequestParam("quantity1") int quantity1, @RequestParam("quantity2") int quantity2, 
		@RequestParam("seat") String seat, @RequestParam("time") String time, @RequestParam("date") String date, 
		@RequestParam("cinemas") String cinemas, @RequestParam("totalPrice") int totalPrice) {
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		ModelAndView mav = new ModelAndView();
		if (loginUser==null) { mav.setViewName("member/login");}
		else {
			MovieVO mvo = mvs.getMovie(movieno);
			TheaterVO tvo = os.selectTheater(cinemas);
			OrderVO ovo = new OrderVO();
			
			ovo.setId(loginUser.getId());
			ovo.setMovieno(movieno);
			ovo.setSeat(seat);
			ovo.setQuantity1(quantity1);
			ovo.setQuantity2(quantity2);
			ovo.setCinemas(cinemas);
			ovo.setMovietime(time);
			ovo.setMoviedate(date);
			ovo.setTotalprice(totalPrice);
			
			os.insertOrder(ovo);
			
			mav.addObject("orderVO",ovo);
			mav.addObject("movieVO",mvo);
			mav.setViewName("order/orderConfirm");
		}
		return mav;
	}
	
	@RequestMapping("orderList")
	public ModelAndView orderList(Model model, HttpServletRequest request,
			@RequestParam("orderno") int orderno) {
		HttpSession session = request.getSession();
		ModelAndView mav = new ModelAndView();
		MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
		if (mvo==null) mav.setViewName("member/login");
		else {
			OrderVO ovo = os.getOrderList(orderno);
			mav.addObject("orderVO", ovo);
			mav.setViewName("order/orderList");
		}
		return mav;
	}
	
	@RequestMapping("orderDelete")
	public ModelAndView orderDelete(Model model, HttpServletRequest request,
			@RequestParam("orderno") int orderno) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
		if (mvo==null) mav.setViewName("member/login");
		else {
			os.deleteOrder(orderno);
			mav.setViewName("redirect:/myPage");
		}
		return mav;
	}
}
