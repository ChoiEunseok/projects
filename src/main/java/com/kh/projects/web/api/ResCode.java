package com.kh.projects.web.api;

import lombok.Getter;

@Getter
public enum ResCode {
  OK("00"),FAIL("01"), ETC("99"),
  EXIST("21"),NONE("22");

  private final String code;

  ResCode(String code) {
    this.code = code;
  }
}
