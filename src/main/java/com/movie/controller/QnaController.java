package com.movie.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.movie.dto.MemberVO;
import com.movie.dto.Paging;
import com.movie.dto.QnaVO;
import com.movie.service.AdminService;
import com.movie.service.QnaService;

@Controller
public class QnaController {
		
	@Autowired
	QnaService qs;
	
	@Autowired
	AdminService as;
	
	@RequestMapping("qnaList")
	public ModelAndView qnaList(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		ModelAndView mav = new ModelAndView();
		if (loginUser==null) { mav.setViewName("member/login");
		}else {
			if( request.getParameter("first")!=null ) {
				session.removeAttribute("page");
			}
			int page  = 1;
			if( request.getParameter("page") != null ) {
				page = Integer.parseInt(request.getParameter("page"));
				session.setAttribute("page", page);
			} else if( session.getAttribute("page")!= null ) {
				page = (int) session.getAttribute("page");
			} else {
				page = 1;
				session.removeAttribute("page");
			}
			Paging paging = new Paging();
			paging.setPage(page);
			int count = as.getAllCount2();
			paging.setTotalCount(count);
			paging.paging();
			ArrayList<QnaVO> qnalist = as.listQna2(loginUser.getId(),paging);
			
			mav.addObject("qnaVO", qnalist);
			mav.addObject("paging", paging);
			mav.setViewName("qna/qnaList");
		}
		return mav;
	}

	@RequestMapping("qnaView")
	public ModelAndView qnaView(Model model, HttpServletRequest request, @RequestParam("qseq") int qseq) {
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		ModelAndView mav = new ModelAndView();
		if (loginUser==null) { mav.setViewName("member/login");
		}else {
			QnaVO qvo = qs.getQna(qseq);
			mav.addObject("qnaVO", qvo);
			mav.setViewName("qna/qnaView");
		}
		return mav;
	}
	
	@RequestMapping("qnaWriteForm")
	public ModelAndView qnaWriteForm(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		ModelAndView mav = new ModelAndView();
		if (loginUser==null) { mav.setViewName("member/login");
		}else {
			mav.setViewName("qna/qnaWrite");
		}
		return mav;
	}
	
	@RequestMapping("qnaWrite")
	public ModelAndView qnaWrite( @ModelAttribute("dto") @Valid QnaVO qvo, 
			BindingResult result, Model model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		if(result.getFieldError("subject") != null ) {
			model.addAttribute("message", "제목을 입력하세요");
			mav.setViewName("qna/qnaWrite");
			return mav;
		}else if(result.getFieldError("content") != null ) {
			model.addAttribute("message", "내용을 입력하세요");
			mav.setViewName("qna/qnaWrite");
			return mav;
		}
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		if (loginUser==null) { mav.setViewName("member/login");
		}else {
			qvo.setId(loginUser.getId());
			qs.insertQna(qvo);
			mav.setViewName("redirect:/qnaList");
		}
		return mav;
	}
	
	@RequestMapping("qnaRevise")
	public ModelAndView qnaRevise( @ModelAttribute("dto") @Valid QnaVO qvo,
			BindingResult result, Model model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		if(result.getFieldError("subject") != null ) {
			model.addAttribute("message", "수정할 제목을 입력하세요");
			qvo = qs.getQna(Integer.parseInt(request.getParameter("qseq")));
			mav.addObject("qnaVO", qvo);
			mav.setViewName("qna/qnaRevise");
			return mav;
		}else if(result.getFieldError("content") != null ) {
			model.addAttribute("message", "수정할 내용을 입력하세요");
			qvo = qs.getQna(Integer.parseInt(request.getParameter("qseq")));
			mav.addObject("qnaVO", qvo);
			mav.setViewName("qna/qnaRevise");
			return mav;
		}
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		if (loginUser==null) { mav.setViewName("member/login");
		}else {
			qvo.setQseq(Integer.parseInt(request.getParameter("qseq")));
			qvo.setSubject(request.getParameter("subject"));
			qvo.setContent(request.getParameter("content"));
			qvo.setCategory(request.getParameter("category"));
			qs.reviseQna(qvo);
			mav.setViewName("redirect:/qnaList");
		}
		return mav;
	}
	
	@RequestMapping("qnaDelete")
	public ModelAndView qnaDelete(Model model, HttpServletRequest request, @RequestParam("qseq") int qseq) {
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		ModelAndView mav = new ModelAndView();
		if (loginUser==null) { mav.setViewName("member/login");
		}else {
			qs.deleteQna(qseq);
			mav.setViewName("redirect:/qnaList");
		}
		return mav;
	}
	
	@RequestMapping("qnaReviseForm")
	public ModelAndView qnaReviseForm(Model model, HttpServletRequest request, @RequestParam("qseq") int qseq) {
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		ModelAndView mav = new ModelAndView();
		if (loginUser==null) { mav.setViewName("member/login");
		}else {
			QnaVO qvo = qs.getQna(qseq);
			mav.addObject("qnaVO", qvo);
			mav.setViewName("qna/qnaRevise");
		}
		return mav;
	}
}
