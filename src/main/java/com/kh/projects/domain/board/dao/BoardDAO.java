package com.kh.projects.domain.board.dao;

import com.kh.projects.domain.entity.Board;

import java.util.List;
import java.util.Optional;

public interface BoardDAO {
  //작성
  Long save(Board board);
  //조회
  Optional<Board> findById(Long boardId);
  //삭제
  int deleteById(Long boardId);
  //수정
  int updateById(Long boardId, Board board);
  //목록
  List<Board> findAll();
  List<Board> findAll(Long reqPage, Long reqCnt);
  //총레코드 건수
  int totalCnt();
}
