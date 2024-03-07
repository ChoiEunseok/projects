package com.kh.projects.domain.comments.svc;

import com.kh.projects.domain.comments.dao.CommentsDAO;
import com.kh.projects.domain.entity.Comments;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentsSVCImpl implements CommentsSVC {

  private CommentsDAO commentsDAO;

  CommentsSVCImpl(CommentsDAO commentsDAO) {
    this.commentsDAO = commentsDAO;
  }
  @Override
  public Long save(Comments comments) {
    return commentsDAO.save(comments);
  }

  @Override
  public List<Comments> findByIdAll(Long boardId) {
    return commentsDAO.findByIdAll(boardId);
  }
}
