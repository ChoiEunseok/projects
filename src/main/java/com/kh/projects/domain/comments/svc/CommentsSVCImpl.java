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
  public int deleteById(Long commentId) {
    return commentsDAO.deleteById(commentId);
  }

  @Override
  public int updateById(Long commentId, Comments comments) {
    return commentsDAO.updateById(commentId, comments);
  }

  @Override
  public List<Comments> findByIdAll(Long boardId) {
    return commentsDAO.findByIdAll(boardId);
  }

  @Override
  public List<Comments> findByAll(Long boardId, Long reqPage, Long reqCnt) {
    return commentsDAO.findByAll(boardId, reqPage, reqCnt);
  }

  @Override
  public int totalCnt() {
    return commentsDAO.totalCnt();
  }
}
