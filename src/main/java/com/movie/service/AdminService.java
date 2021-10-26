package com.movie.service;



import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.dao.IAdminDao;
import com.movie.dto.AdminVO;
import com.movie.dto.MemberVO;
import com.movie.dto.MovieVO;
import com.movie.dto.Paging;
import com.movie.dto.QnaVO;

@Service
public class AdminService {

	@Autowired
	IAdminDao adao;

	public AdminVO getManager(String id) {
		
		return adao.getManager(id);
	}

	public void updateManager(AdminVO adminvo) {
		adao.updateManager(adminvo);
		
	}

	public int getAllCount(String tablename, String fieldname, String key) {
		return adao.getAllCount(tablename, fieldname,key);
	}

	public ArrayList<MovieVO> listMovie(Paging paging, String key) {
		return adao.listMovie(paging, key);
	}

	public void insertMovie(MovieVO mvo) {
		adao.insertMovie(mvo);
		
	}

	public ArrayList<MemberVO> listMember(Paging paging, String key) {
		return adao.listMember(paging, key);
	}

	public ArrayList<QnaVO> listQna(Paging paging, String key) {
		return adao.listQna(paging, key);
	}

	public QnaVO getQna(int qseq) {
		
		return adao.getQna(qseq);
	}

	public void updateQna(QnaVO qvo) {
		adao.updateQna(qvo);
		
	}

	public void updateMovie(MovieVO mvo) {
		adao.updateMovie(mvo);
		
	}

	public int getAllCount2() {
		return adao.getAllCount2();
	}
	
	public int getAllCount3() {
		return adao.getAllCount3();
	}

	public ArrayList<QnaVO> listQna2(String id, Paging paging) {
		return adao.listQna2(id, paging);
	}

	public void movieDelete(int movieno) {
		adao.movieDelete(movieno);
	}


	
}
