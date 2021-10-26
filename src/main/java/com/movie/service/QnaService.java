package com.movie.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.dao.IQnaDao;
import com.movie.dto.QnaVO;

@Service
public class QnaService {
	
	@Autowired
	IQnaDao qdao;
	
	public QnaVO getQna(int qseq) {
		return qdao.getQna(qseq);
	}
	
	public void insertQna(@Valid QnaVO qvo) {
		qdao.insertQna(qvo);
	}
	
	public void deleteQna(int qseq) {
		qdao.deleteQna(qseq);
	}
	
	public void reviseQna(QnaVO qvo) {
		qdao.reviseQna(qvo);
	}
	
	public void deleteQnaMember(String id) {
		qdao.deleteQnaMember(id);
	}
}
