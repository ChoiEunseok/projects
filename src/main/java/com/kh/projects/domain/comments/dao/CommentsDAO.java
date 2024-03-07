package com.kh.projects.domain.comments.dao;

import com.kh.projects.domain.entity.Comments;

import java.util.List;

public interface CommentsDAO {
  //작성
  Long save(Comments comments);
  //삭제
//  int deleteById(Long commentId);
  //수정
//  int updateById(Long commentId, Comments Comments);
  //목록
//  List<Comments> findAll();
  List<Comments> findByIdAll(Long boardId);
}
