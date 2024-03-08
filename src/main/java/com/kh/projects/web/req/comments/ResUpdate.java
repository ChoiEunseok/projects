package com.kh.projects.web.req.comments;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResUpdate {
  private Long commentId;           // number(10),     --댓글 아이디 기본키
  private Long boardId;             // number(10),     --원글(보드)아이디 왜래키
  private String cname;              // varchar2(30),   --작성자
  private String userComment;       // varchar2(300),  --내용
  private LocalDateTime udate;       // timestamp       --수정일자
}
