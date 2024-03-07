package com.kh.projects.domain.board.dao;

import com.kh.projects.domain.entity.Board;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository
public class BoardDAOImpl implements BoardDAO {

  private final NamedParameterJdbcTemplate template;

  BoardDAOImpl(NamedParameterJdbcTemplate template) {
    this.template = template;
  }

  //작성
  @Override
  public Long save(Board board) {
    StringBuffer sql = new StringBuffer();
    sql.append("insert into board(board_id,member_id,bname,title,user_content) ");
    sql.append("values(board_board_id_seq.nextval, :memberId, :bname, :title, :userContent) ");

    //sql파라미터 자동매핑
    SqlParameterSource param = new BeanPropertySqlParameterSource(board);
    KeyHolder keyHolder = new GeneratedKeyHolder();
    template.update(sql.toString(),param,keyHolder,new String[]{"board_id"});

    Long boardId = ((BigDecimal)keyHolder.getKeys().get("board_id")).longValue();

    return boardId;
  }

  //조회
  @Override
  public Optional<Board> findById(Long boardId) {
    StringBuffer sql = new StringBuffer();
    sql.append("select board_id,bname,title,user_content,cdate,udate ");
    sql.append("  from board ");
    sql.append(" where board_id = :boardId ");

    try {
      Map<String,Long> map = Map.of("boardId",boardId);
      Board board = template.queryForObject(sql.toString(), map, BeanPropertyRowMapper.newInstance(Board.class));
      return Optional.of(board);

    }catch (EmptyResultDataAccessException e){
      //조회결과가 없는경우
      return Optional.empty();
    }
  }

  //삭제
  @Override
  public int deleteById(Long boardId) {
    StringBuffer sql = new StringBuffer();
    sql.append("delete from board");
    sql.append(" where board_id = :boardId ");

    SqlParameterSource param = new MapSqlParameterSource()
            .addValue("boardId", boardId);
    int deleteRowCnt = template.update(sql.toString(), param);

    return deleteRowCnt;
  }

  //수정
  @Override
  public int updateById(Long boardId, Board board) {
    StringBuffer sql = new StringBuffer();
    sql.append("update board ") ;
    sql.append("   set title = :title, ");
    sql.append("       user_content = :userContent, ");
    sql.append("       udate = default ");
    sql.append(" where board_id = :boardId ");

    //sql 파라미터 변수에 값 매핑
    SqlParameterSource param = new MapSqlParameterSource()
            .addValue("title", board.getTitle())
            .addValue("userContent", board.getUserContent())
            .addValue("boardId", boardId);

    //update 수행 후 변경된 행수 반환
    int updateRowCnt = template.update(sql.toString(), param);

    return updateRowCnt;
  }

  //목록
  @Override
  public List<Board> findAll() {
    StringBuffer sql = new StringBuffer();
    sql.append("  select board_id, bname, title, user_content, cdate, udate  ");
    sql.append("    from board ");
    sql.append("order by board_id desc ");

    List<Board> list = template.query(sql.toString(), BeanPropertyRowMapper.newInstance(Board.class));

    return list;
  }
}
