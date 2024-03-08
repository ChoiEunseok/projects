package com.kh.projects.domain.comments.svc;

import com.kh.projects.domain.entity.Comments;

import java.util.List;

public interface CommentsSVC {
  //작성
  Long save(Comments comments);

  //삭제
  int deleteById(Long commentId);

  //수정
  int updateById(Long commentId, Comments comments);

  //목록
  List<Comments> findByIdAll(Long boardId);

  //목록(페이징)
  List<Comments> findByAll(Long boardId, Long reqPage, Long reqCnt);

  //총레코드 건수
  int totalCnt();
}
