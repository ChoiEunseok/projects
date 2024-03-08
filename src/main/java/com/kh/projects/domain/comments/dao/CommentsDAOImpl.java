package com.kh.projects.domain.comments.dao;

import com.kh.projects.domain.entity.Comments;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Repository
public class CommentsDAOImpl implements CommentsDAO{

  private final NamedParameterJdbcTemplate template;

  CommentsDAOImpl(NamedParameterJdbcTemplate template) {
    this.template = template;}

  //작성
  @Override
  public Long save(Comments comments) {
    StringBuffer sql = new StringBuffer();
    sql.append("insert into comments(comment_id,board_id,cname,user_comment) ");
    sql.append("values(comments_comment_id_seq.nextval, :boardId, :cname, :userComment) ");

    SqlParameterSource param = new BeanPropertySqlParameterSource(comments);
    KeyHolder keyHolder = new GeneratedKeyHolder();
    template.update(sql.toString(), param, keyHolder, new String[]{"comment_id"});

    Long commentId = ((BigDecimal) keyHolder.getKeys().get("comment_id")).longValue();

    return commentId;
  }

  //삭제
  @Override
  public int deleteById(Long commentId) {
    StringBuffer sql = new StringBuffer();
    sql.append("delete from comments");
    sql.append(" where comment_id = :commentId ");

    SqlParameterSource param = new MapSqlParameterSource().addValue("commentId", commentId);
    int deletRowCnt = template.update(sql.toString(), param);

    return deletRowCnt;
  }

  //수정
  @Override
  public int updateById(Long commentId, Comments comments) {
    StringBuffer sql = new StringBuffer();
    sql.append("update comments ") ;
    sql.append("   set user_comment = :userComment, ");
    sql.append("       udate = default ");
    sql.append(" where comment_id = :commentId ");

    //sql 파라미터 변수에 값 매핑
    SqlParameterSource param = new MapSqlParameterSource()
            .addValue("userComment", comments.getUserComment())
            .addValue("commentId", commentId);

    //update 수행 후 변경된 행수 반환
    int updateRowCnt = template.update(sql.toString(), param);

    return updateRowCnt;
  }

  //목록
  @Override
  public List<Comments> findByIdAll(Long boardId) {
    StringBuffer sql = new StringBuffer();
    sql.append("  select b.board_id, b.title, c.comment_id, c.cname, c.user_comment, c.cdate, c.udate  ");
    sql.append("    from comments c join board b on c.board_id = b.board_id ");
    sql.append(" where b.board_id = :boardId ");
    sql.append("order by comment_id asc ");

    Map<String, Long> paramMap = Map.of("boardId", boardId);

    List<Comments> commentsList = template.query(sql.toString(), paramMap, new BeanPropertyRowMapper<>(Comments.class));

    return commentsList;
  }

  //목록(페이징)
  @Override
  public List<Comments> findByAll(Long boardId, Long reqPage, Long reqCnt) {
    StringBuffer sql = new StringBuffer();
    sql.append("  select b.board_id, b.title, c.comment_id, c.cname, c.user_comment, c.cdate, c.udate  ");
    sql.append("    from comments c join board b on c.board_id = b.board_id ");
    sql.append(" where b.board_id = :boardId ");
    sql.append("order by comment_id asc ");
    sql.append("offset (:reqPage-1) * :reqCnt rows fetch first :reqCnt rows only ");

    Map<String, Long> paramMap = Map.of("boardId", boardId, "reqPage", reqPage, "reqCnt", reqCnt);

    List<Comments> list = template.query(sql.toString(), paramMap, BeanPropertyRowMapper.newInstance(Comments.class));

    return list;
  }

  //총레코드 건수
  @Override
  public int totalCnt() {
    String sql = "select count(comment_id) from comments";

    MapSqlParameterSource param = new MapSqlParameterSource();
    Integer cnt = template.queryForObject(sql, param, Integer.class);
    return cnt;
  }
}
