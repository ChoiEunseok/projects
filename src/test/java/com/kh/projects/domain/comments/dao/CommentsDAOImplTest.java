package com.kh.projects.domain.comments.dao;

import com.kh.projects.domain.entity.Comments;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class CommentsDAOImplTest {

  @Autowired
  CommentsDAO commentsDAO;

  @Test
  @Transactional
  void save() {
    Comments comments = new Comments();
    comments.setBoardId(1L);
    comments.setCname("사용자1");
    comments.setUserComment("반갑습니다.");

    Long commentId = commentsDAO.save(comments);
    log.info("commentId={}", commentId);
  }

  @Test
  void findById() {
    Long boardId = 1L;
    List<Comments> findedComments = commentsDAO.findByIdAll(boardId);
    log.info("findedComments={}", findedComments);
  }

  @Test
  void deleteById() {
    Long commentId = 1L;
    int deleteById = commentsDAO.deleteById(commentId);
    log.info("deleteById={}", deleteById);

  }

  @Test
  void saveMultiple() {
    Comments comments = new Comments();
    int start = 1;
    int end = 55;

    for (long i = start; i <= end; i++) {
      comments.setBoardId(115L);
      comments.setCname("user1@kh.com");
      comments.setUserComment("반갑습니다."+i);
      Long commentId = commentsDAO.save(comments);
      log.info("commentId={}", commentId);
    }

  }
}