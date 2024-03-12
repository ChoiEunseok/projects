package com.kh.projects.domain.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Board {
  private Long boardId;           //number(10),     --아이디
  private String bname;           //varchar(30),    --작성자
  private String title;           //varchar(100),   --제목
  private String userContent;    //CLOB,           --내용
  private LocalDateTime cdate;    //timestamp,      --작성일자
  private LocalDateTime udate;    //timestamp       --수정일자
}