package com.kh.projects.web.req.comments;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResSave {
  private String cname;             //  --작성자
  private String userComment;       //  --내용
}
