package com.kh.projects.domain.comments.svc;

import com.kh.projects.domain.entity.Comments;

import java.util.List;

public interface CommentsSVC {
  //작성
  Long save(Comments comments);

  //목록
  List<Comments> findByIdAll(Long boardId);
}
