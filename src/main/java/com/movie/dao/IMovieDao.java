package com.movie.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.movie.dto.MovieVO;
import com.movie.dto.Paging;

@Mapper
public interface IMovieDao {
	
	ArrayList<MovieVO> MovieList();
	
	ArrayList<MovieVO> detailOrder(String movieno);
	
	MovieVO getMovie(int movieno);
	
	int getAllCount();
	
	ArrayList<MovieVO> selectAll(Paging paging);
	
	ArrayList<MovieVO> getGenreDrama();
	
	ArrayList<MovieVO> getGenreFantasy();
	
	ArrayList<MovieVO> getGenreAnimation();
	
	ArrayList<MovieVO> getGenreAction();
	
	ArrayList<MovieVO> getGenreComedy();
	
	ArrayList<MovieVO> getGenreThriller();
	
	MovieVO getGenre(String genre);
	
	void deleteMovie(Integer movieno);
	
	MovieVO getMovieOrder(Integer movieno);
	
	
}
