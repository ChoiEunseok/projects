package com.kh.projects.domain.member.dao;

import com.kh.projects.domain.entity.Member;

import java.util.Optional;

public interface MemberDAO {
  //회원가입
  Long insertMember(Member member);

  //회원 아이디 조회
  boolean existEmail(String email);

  //회원조회
  Optional<Member> findByEmailAndPasswd(String email, String passwd);

  //회원수정

  //회원탈퇴

}
