package com.kh.projects.domain.board.dao;

import com.kh.projects.domain.entity.Board;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class BoardControllerTest {

  @Autowired
  BoardDAO boardDAO;

  @Test
  @DisplayName("게시글등록")
  void save() {
    Board board = new Board();
    int start = 1;
    int end = 115;

    for (long i = start; i <= end; i++) {
      board.setBname("user1@kh.com");
      board.setTitle("안녕하세요"+ i);
      board.setUserContent("안녕하세요"+ i);
      Long boardId = boardDAO.save(board);
    }

    log.info("board={}", board);
  }

  @Test
  @DisplayName("목록(페이징)")
  void findAllByPaging() {
    List<Board> list = boardDAO.findAll(9L,10L);
    for (Board board : list) {
      log.info("product={}", board);
    }
    log.info("size={}", list.size());
  }
}