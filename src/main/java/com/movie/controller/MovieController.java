package com.movie.controller;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.movie.dto.MovieVO;
import com.movie.service.MovieService;

@Controller
public class MovieController {
	
	@Autowired
	MovieService ms;

	@RequestMapping("/")
	public String index(Model model) {
		return "index";
	}
	
	@RequestMapping("Cinfo")
    public String Cinfo(Model model, HttpServletRequest request) {
       return "movie/Cinfo";
    }

	
	@RequestMapping("movieDetail")
	public ModelAndView movieDetail(Model model, HttpServletRequest request, @RequestParam("movieno") int movieno) {
		ModelAndView mav = new ModelAndView();
		MovieVO mvo = ms.getMovie(movieno);
		mav.addObject("movieVO", mvo);
		mav.setViewName("movie/movieDetail");
		return mav;
	}
	
	@RequestMapping("movieInformation")
	public ModelAndView movieInformation(Model model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		ArrayList<MovieVO> list = ms.MovieList();
		ArrayList<MovieVO> listDrama = ms.getGenreDrama();
		ArrayList<MovieVO> listFantasy = ms.getGenreFantasy();
		ArrayList<MovieVO> listAnimation = ms.getGenreAnimation();
		ArrayList<MovieVO> listAction = ms.getGenreAction();
		ArrayList<MovieVO> listThriller = ms.getGenreThriller();
		ArrayList<MovieVO> listComedy = ms.getGenreComedy();
		
		mav.addObject("movieVO", list);
		mav.addObject("Drama", listDrama);
		mav.addObject("Fantasy", listFantasy);
		mav.addObject("Animation", listAnimation);
		mav.addObject("Action", listAction);
		mav.addObject("Thriller", listThriller);
		mav.addObject("Comedy", listComedy);
		mav.setViewName("movie/information");
		return mav;
	}
	
}
