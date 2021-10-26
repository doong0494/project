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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.movie.dto.MemberVO;
import com.movie.dto.OrderVO;
import com.movie.dto.Paging;
import com.movie.service.AdminService;
import com.movie.service.MemberService;
import com.movie.service.OrderService;

@Controller
public class MemberController {

	@Autowired
	MemberService ms;
	
	@Autowired
	OrderService os;
	
	@Autowired
	AdminService as;
	
	@RequestMapping("loginForm")
	public String loginForm(Model model, HttpServletRequest request) {
		return "member/login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@ModelAttribute("dto") @Valid MemberVO membervo,
			BindingResult result, Model model, HttpServletRequest request) {
		if( result.getFieldError("id")!=null) {
			model.addAttribute("message", result.getFieldError("id").getDefaultMessage());
			return "member/login";
		}else if( result.getFieldError("pwd")!=null) {
			model.addAttribute("message", result.getFieldError("pwd").getDefaultMessage());
			return "member/login";
		}
		
		MemberVO mvo = ms.getMember(membervo.getId());
		if( mvo==null) {
			model.addAttribute("message", "ID가 없습니다");
			return "member/login";
		}else if(mvo.getPwd()==null) {
			model.addAttribute("message", "관리자에게 문의하세요");
			return "member/login";
		}else if( !mvo.getPwd().equals(membervo.getPwd() ) ){
			model.addAttribute("message", "비번이 맞지 않습니다");
			return "member/login";
		}else if( mvo.getPwd().equals(membervo.getPwd() ) ) {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", mvo);
			return "redirect:/";
		}else {
			return "member/login";
		}
	}
	
	@RequestMapping("joinForm")
	public String joinnForm(Model model, HttpServletRequest request) {
		return "member/join";
	}
	
	@RequestMapping("idCheckForm")
	public String id_check_form(Model model, @RequestParam("id") String id) {
		
		MemberVO mvo = ms.getMember(id);
		int result = 0;
		if(mvo == null) result = -1;
		else result = 1;
		model.addAttribute("result", result);
		model.addAttribute("id", id);
		return "member/idCheck";
	}
	
	@RequestMapping(value = "join", method=RequestMethod.POST)
	public String join(@ModelAttribute("dto") @Valid MemberVO membervo,
			BindingResult result, Model model, HttpServletRequest request, 
			@RequestParam("reid") String reid, @RequestParam("pwdCheck") String pwdCheck) {
		if( result.getFieldError("id")!=null) {
			model.addAttribute("message", result.getFieldError("id").getDefaultMessage());
			model.addAttribute("reid", reid);
			return "member/joinForm";
		}else if( result.getFieldError("pwd")!=null) {
			model.addAttribute("message", result.getFieldError("pwd").getDefaultMessage());
			model.addAttribute("reid", reid);
			return "member/joinForm";
		}else if( result.getFieldError("name")!=null) {
			model.addAttribute("message", result.getFieldError("name").getDefaultMessage());
			model.addAttribute("reid", reid);
			return "member/joinForm";
		}else if( result.getFieldError("email")!=null) {
			model.addAttribute("message", result.getFieldError("email").getDefaultMessage());
			model.addAttribute("reid", reid);
			return "member/joinForm";
		}else if( !request.getParameter("reid").equals(membervo.getId()) ) {
			model.addAttribute("message", "아이디 중복체크를 하지 않으셨습니다");
			model.addAttribute("reid", reid);
			return "member/joinForm";
		}else if( !request.getParameter("pwdCheck").equals( membervo.getPwd() ) ) {
			model.addAttribute("message", "비밀번호 확인 일치하지 않습니다");
			model.addAttribute("reid", reid);
			return "member/joinForm";
		}
		ms.insertMember(membervo);
		return "member/login";
			
	}
	
	@RequestMapping("logout")
	public String logout(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("loginUser");
		return "redirect:/";
	}
	
	@RequestMapping("memberEditForm")
	public String memberUpdateForm(Model model, HttpServletRequest request) {
model.addAttribute("message", request.getParameter("message"));
		
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		
		request.setAttribute("member", mvo);
	
		return "member/updateForm";
	}

	@RequestMapping(value = "memberUpdate",  method=RequestMethod.POST)
	public String memberUpdate(@ModelAttribute("member") @Valid MemberVO membervo,
			BindingResult result, Model model, HttpServletRequest request) {
	
		if( result.getFieldError("pwd")!=null) {
			request.setAttribute("message", result.getFieldError("pwd").getDefaultMessage());
			return "redirect:/memberEditForm";
		}else if( result.getFieldError("name")!=null) {
			request.setAttribute("message", result.getFieldError("name").getDefaultMessage());
			return "redirect:/memberEditForm";
		}else if( result.getFieldError("email")!=null) {
			request.setAttribute("message", result.getFieldError("email").getDefaultMessage());
			return "redirect:/memberEditForm";
		}else if( !request.getParameter("pwdCheck").equals( membervo.getPwd() ) ) {
			request.setAttribute("message", "비밀번호 확인 일치하지 않습니다");
			return "redirect:/memberEditForm";
		}
		ms.updateMember(membervo);
		HttpSession session = request.getSession();
		session.setAttribute("loginUser", membervo);
		return "redirect:/";
	}
	
	@RequestMapping("deleteMemberForm")
	public String deleteMemberForm(Model model, HttpServletRequest request) {
		return "member/deleteMemberForm";
	}
	@RequestMapping("deleteMember")
	public String deleteMember(Model model, HttpServletRequest request,
			@RequestParam("pwd") String pwd) {
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
		
		if( mvo.getPwd().equals(pwd)) {
			
			ms.deleteMember(mvo.getId());
			session.invalidate();
			
			request.setAttribute("deleteAttr", "Y");
		}else {
			request.setAttribute("errorMessage", "비밀번호가 맞지않습니다");
		}
		return "member/deleteMemberForm";
	}
	
	@RequestMapping("findIdPwd")
	public String findIdPwd(Model model, HttpServletRequest request) {
		return "member/findIdPwd";
	}
	
	@RequestMapping("findIdForm")
	public String findIdForm(Model model, HttpServletRequest request) {
		return "member/findIdForm";
	}
	
	@RequestMapping("findPwdForm")
	public String findPwdForm(Model model, HttpServletRequest request) {
		return "member/findPwdForm";
	}
	
	@RequestMapping("findIdStep1")
	public String findIdStep1(Model model, HttpServletRequest request, 
			@RequestParam("name") String name, @RequestParam("phone") String phone) {
		MemberVO mvo = ms.confirmPhone(name, phone);
		if(mvo == null) {
			request.setAttribute("msg", "이름과 번화번호가 일치하는 회원이 없습니다");
			request.setAttribute("name", name);
			request.setAttribute("phone", phone);
			return  "member/findIdForm";
		}
		request.setAttribute("member", mvo);
		return "member/findIdconfirmNumber";
	}
	
	@RequestMapping("findIdStep2")
	public String findIdStep2(Model model, HttpServletRequest request,@RequestParam("confirmNum") String confirmNum,
			@RequestParam("id") String id,@RequestParam("name") String name, @RequestParam("phone") String phone ) {
		
		MemberVO mvo = new MemberVO();
		mvo.setId( id );
		mvo.setName( name );
		mvo.setPhone( phone );
		
		if(!confirmNum.equals("0000")) {
			request.setAttribute("msg", "인증번호가 맞지 않습니다");
			return "member/findIdconfirmNumber";
		}
		request.setAttribute("member",mvo);
		return "member/viewId";
		
	}
	
	@RequestMapping("findPwdStep1")
	public String findPwdStep1(Model model, HttpServletRequest request, @RequestParam("id") String id,
			@RequestParam("name") String name, @RequestParam("phone") String phone) {
		
		MemberVO mvo = ms.confirmIdnamePhone(id, name, phone);
		
		if( mvo == null ) {
			request.setAttribute("msg", "id와 이름과 번화번호가 일치하는 회원이 없습니다");
			request.setAttribute("id", id);
			request.setAttribute("name", name);
			request.setAttribute("phone", phone);
			return "member/findPwdForm";
		}
		request.setAttribute("member", mvo);
		return "member/findPwdconfirmNumber";
	}
	
	@RequestMapping("findPwdStep2")
	public String findPwdStep2(Model model, HttpServletRequest request,@RequestParam("confirmNum") String confirmNum,
			@RequestParam("id") String id,@RequestParam("name") String name, @RequestParam("phone") String phone ) {
		
		MemberVO mvo = new MemberVO();

		mvo.setId( id );
		mvo.setName(name );
		mvo.setPhone( phone );
		
		if(!confirmNum.equals("0000"))
			return "member/findPwconfirmNumber";
		
		request.setAttribute("member",mvo);
		return  "member/resetPwd";
	}
	
	@RequestMapping("resetPwd")
	public String findPwdStep2(Model model, HttpServletRequest request,
			@RequestParam("id") String id,@RequestParam("pwd") String pwd) {
		MemberVO mvo = new MemberVO();
		mvo.setId( id );
		mvo.setPwd( pwd );
		
		ms.resetPwd(mvo);
		
		return"member/resetPwdComplete";
	}
	
	@RequestMapping("myPage")
	public ModelAndView myPage(Model model, HttpServletRequest request) {
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
			int count = as.getAllCount3();
			paging.setTotalCount(count);
			paging.paging();
			ArrayList<OrderVO> orderlist = os.listOrder(loginUser.getId(), paging);
			
			mav.addObject("orderVO", orderlist);
			mav.addObject("paging", paging);
			mav.setViewName("member/myPage");
		}
		return mav;
	}
	
	
}
