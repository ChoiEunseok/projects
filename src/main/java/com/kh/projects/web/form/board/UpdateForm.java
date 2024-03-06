package com.kh.projects.web.form.board;

import lombok.Data;

@Data
public class UpdateForm {
  private Long boardId;
  private String bname;
  private String title;
  private String userContent;
}
