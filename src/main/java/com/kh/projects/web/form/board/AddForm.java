package com.kh.projects.web.form.board;

import lombok.Data;

@Data
public class AddForm {
  private Long memberId;
  private String bname;
  private String title;
  private String userContent;
}
