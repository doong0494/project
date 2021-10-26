package com.movie.dao;

import org.apache.ibatis.annotations.Mapper;

import com.movie.dto.QnaVO;

@Mapper
public interface IQnaDao {
	
	QnaVO getQna(int qseq);
	
	void insertQna(QnaVO qvo);
	
	void deleteQna(int qseq);
	
	void reviseQna(QnaVO qvo);
	
	void deleteQnaMember(String id);
}
