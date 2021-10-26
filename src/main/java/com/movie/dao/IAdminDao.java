package com.movie.dao;

import java.util.ArrayList;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Mapper;

import com.movie.dto.AdminVO;
import com.movie.dto.MemberVO;
import com.movie.dto.MovieVO;
import com.movie.dto.Paging;
import com.movie.dto.QnaVO;

@Mapper
public interface IAdminDao {

	AdminVO getManager(String id);

	void updateManager(@Valid AdminVO adminvo);

	int getAllCount(String tablename, String fieldname, String key);

	ArrayList<MovieVO> listMovie(Paging paging, String key);

	void insertMovie(MovieVO mvo);

	ArrayList<MemberVO> listMember(Paging paging, String key);

	ArrayList<QnaVO> listQna(Paging paging, String key);

	QnaVO getQna(int qseq);

	void updateQna(QnaVO qvo);

	void updateMovie(MovieVO mvo);

	int getAllCount2();
	
	int getAllCount3();

	ArrayList<QnaVO> listQna2(String id, Paging paging);

	void movieDelete(int movieno);

}
