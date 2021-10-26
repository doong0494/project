package com.movie.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.dao.IMovieDao;
import com.movie.dto.MovieVO;
import com.movie.dto.Paging;

@Service
public class MovieService {
	
	@Autowired
	IMovieDao mvdao;
	
	public ArrayList<MovieVO> MovieList() {
		return mvdao.MovieList();
	}
	
	public ArrayList<MovieVO> detailOrder(String movieno){
		return mvdao.detailOrder(movieno);
	}
	
	public MovieVO getMovie(int movieno) {
		return mvdao.getMovie(movieno);
	}
	
	public int getAllCount() {
		return mvdao.getAllCount();
	}
	
	public ArrayList<MovieVO> selectAll(Paging paging){
		return mvdao.selectAll(paging);
	}
	
	public ArrayList<MovieVO> getGenreDrama(){
		return mvdao.getGenreDrama();
	}
	
	public ArrayList<MovieVO> getGenreFantasy(){
		return mvdao.getGenreFantasy();
	}
	
	public ArrayList<MovieVO> getGenreAnimation(){
		return mvdao.getGenreAnimation();
	}
	
	public ArrayList<MovieVO> getGenreAction(){
		return mvdao.getGenreAction();
	}
	
	public ArrayList<MovieVO> getGenreComedy(){
		return mvdao.getGenreComedy();
	}
	
	public ArrayList<MovieVO> getGenreThriller(){
		return mvdao.getGenreThriller();
	}
	
	public MovieVO getGenre(String genre) {
		return mvdao.getGenre(genre);
	}
	
	public void deleteMovie(Integer movieno) {
		mvdao.deleteMovie(movieno);
	}
	
	public MovieVO getMovieOrder(Integer movieno) {
		return mvdao.getMovieOrder(movieno);
	}

}
