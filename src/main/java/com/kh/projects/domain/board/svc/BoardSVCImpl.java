package com.kh.projects.domain.board.svc;

import com.kh.projects.domain.entity.Board;
import com.kh.projects.domain.board.dao.BoardDAO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardSVCImpl implements BoardSVC{
  private BoardDAO boardDAO;

  BoardSVCImpl(BoardDAO boardDAO) {
    this.boardDAO = boardDAO;
  }

  //작성
  @Override
  public Long save(Board board) {
    return boardDAO.save(board);
  }

  @Override
  public Optional<Board> findById(Long boardId) {
    return boardDAO.findById(boardId);
  }

  @Override
  public int deleteById(Long boardId) {
    return boardDAO.deleteById(boardId);
  }

  @Override
  public int updateById(Long boardId, Board board) {
    return boardDAO.updateById(boardId, board);
  }

  @Override
  public List<Board> findAll() {
    return boardDAO.findAll();
  }
}
