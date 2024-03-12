package com.kh.projects.web;

import com.kh.projects.domain.entity.Member;
import com.kh.projects.domain.member.svc.MemberSVC;
import com.kh.projects.web.form.member.JoinForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.regex.Pattern;

@Slf4j
@Controller
@RequestMapping("/members")
public class MemberController {

  private final MemberSVC memberSVC;

  public MemberController(MemberSVC memberSVC) {
    this.memberSVC = memberSVC;
  }

  //회원가입양식
  @GetMapping("/join")
  public String joinForm() {
    return "member/joinForm";
  }

  //회원가입처리
  @PostMapping("/join")
  public String join(Model model, JoinForm joinForm) {
    log.info("joinForm={}", joinForm);

    //1)유효성 검증

    String pattern =  "^.{3,10}$";

    if (!Pattern.matches(pattern, joinForm.getPasswd())) {
      model.addAttribute("joinForm", joinForm);
      model.addAttribute("s_err_pwd", "3~10자리");
      return "member/joinForm";
    }

    pattern =  "^.{3,10}$";

    if (!Pattern.matches(pattern, joinForm.getNickname())) {
      model.addAttribute("joinForm", joinForm);
      model.addAttribute("s_err_nick", "3~10자리");
      return "member/joinForm";
    }

    //2)가입처리
    Member member = new Member();
    BeanUtils.copyProperties(joinForm,member);
    Long memberId = memberSVC.joinMember(member);

    return (memberId != null) ? "redirect:/login" : "redirect:/";
  }

}
