package com.kh.projects.domain.board.svc;

import com.kh.projects.domain.entity.Board;

import java.util.List;
import java.util.Optional;

public interface BoardSVC {
  //작성
  Long save(Board board);

  //조회
  Optional<Board> findById(Long boardId);

  //단건삭제
  int deleteById(Long boardId);

  //수정
  int updateById(Long boardId, Board board);

  //목록
  List<Board> findAll();
  List<Board> findAll(Long reqPage, Long reqCnt);

  int totalCnt();

}
