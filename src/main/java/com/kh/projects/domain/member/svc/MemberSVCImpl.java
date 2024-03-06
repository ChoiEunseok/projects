package com.kh.projects.domain.member.svc;

import com.kh.projects.domain.entity.Member;
import com.kh.projects.domain.member.dao.MemberDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberSVCImpl implements MemberSVC{

  private final MemberDAO memberDAO;

  //회원가입
  @Override
  public Long joinMember(Member member) {
    return memberDAO.insertMember(member);
  }

  @Override
  public boolean existEmail(String email) {
    return memberDAO.existEmail(email);
  }

  @Override
  public Optional<Member> findByEmailAndPasswd(String email, String passwd) {
    return memberDAO.findByEmailAndPasswd(email, passwd);
  }
}
