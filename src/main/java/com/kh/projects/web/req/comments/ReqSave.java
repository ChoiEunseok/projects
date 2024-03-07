package com.kh.projects.web.req.comments;

import lombok.Data;

@Data
public class ReqSave {
  private Long boardId;             //  --원글(보드)아이디 왜래키
  private String cname;             //  --작성자
  private String userComment;       //  --내용
}
