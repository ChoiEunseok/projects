package com.kh.projects.web;

import com.kh.projects.domain.entity.Board;
import com.kh.projects.domain.board.svc.BoardSVC;
import com.kh.projects.web.form.board.AddForm;
import com.kh.projects.web.form.board.UpdateForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Slf4j
@Controller
@RequestMapping("/boards")
public class BoardController {
  private BoardSVC boardSVC;

  BoardController(BoardSVC boardSVC) {this.boardSVC = boardSVC;}

  //게시글작성양식
  @GetMapping("/add")         // Get, http://localhost:9080/forums/add
  public String addForm(Model model) {
    model.addAttribute("addForm", new AddForm());
    return "board/add";     // view이름  게시판등록화면
  }

  //게시글작성처리
  @PostMapping("/add")
  public String add( AddForm addForm, Model model,
                     RedirectAttributes redirectAttributes ) {
    log.info("addForm={}", addForm);

    //유효성 체크
    //필드레벨
    //1-1) 제목
    String pattern = "^[a-zA-Z0-9가-힣\s~!.@#$%^&*()_=+-]{3,30}$";

    if (!Pattern.matches(pattern, addForm.getTitle())) {
      model.addAttribute("addForm", addForm);
      model.addAttribute("s_err_title","영문/숫자/한글/특수문자(~!@#$%^&*()_=+-)가능, 3~30자");
      return "board/add";
    }
//    //1-2) 작성자
//    pattern = "^[ㄱ-ㅎ가-힣]{1,3}$";
//
//    if (!Pattern.matches(pattern, addForm.getBname()))  {
//      model.addAttribute("addForm", addForm);
//      model.addAttribute("s_err_bname", "한글 1~3자리");
//      return "forum/add";
//    }
    //1-3) 내용
    pattern = "^[a-zA-Z0-9가-힣\s~!.@#$%^&*()_=+-]{3,500}$";

    if (!Pattern.matches(pattern, String.valueOf(addForm.getUserContent()))) {
      model.addAttribute("addForm", addForm);
      model.addAttribute("s_err_userContent", "영문/숫자/한글/특수문자(~!@#$%^&*()_=+-)가능, 3~500자");
      return "board/add";
    }

    //상품등록
    Board board = new Board();
    board.setMemberId(addForm.getMemberId());
    board.setBname(addForm.getBname());
    board.setTitle(addForm.getTitle());
    board.setUserContent(addForm.getUserContent());

    Long boardId = boardSVC.save(board);
    log.info("상품번호={}", boardId);

    redirectAttributes.addAttribute("pid", boardId);

    return "redirect:/boards/{pid}/detail";
  }

  //조회
  @GetMapping("/{pid}/detail")       //GET http://localhost:9080/forums/상품번호/detail
  public String findById(@PathVariable("pid") Long boardId, Model model){

    Optional<Board> findedBoard = boardSVC.findById(boardId);
    Board board = findedBoard.orElseThrow();
    model.addAttribute("board", board);

    return "board/detailForm";
  }

  //삭제
  @GetMapping("/{pid}/del")
  public String deleteById(@PathVariable("pid") Long boardId) {
    log.info("deleteById={}", boardId);

    int deletedRowCnt = boardSVC.deleteById(boardId);

    return "redirect:/boards";    //  GET http://localhost:9080/forums/
  }


  //수정양식
  @GetMapping("/{pid}/edit")    // GET http://localhost:9080/forums/상품번호/edit
  public String updateForm(
          @PathVariable("pid") Long boardId,
          Model model) {

    Optional<Board> optionalBoard = boardSVC.findById(boardId);
    Board findedBoard = optionalBoard.orElseThrow();
    model.addAttribute("board", findedBoard);

    return "board/updateForm";
  }
  //수정처리
  @PostMapping("/{pid}/edit")
  public String update(
          @PathVariable("pid") Long boardId,
          UpdateForm updateForm,
          RedirectAttributes redirectAttributes,
          Model model) {

    //유효성 체크
    //1-1) 제목
    String pattern = "^[a-zA-Z0-9가-힣\s~!.@#$%^&*()_=+-]{3,30}$";
    if (!Pattern.matches(pattern, updateForm.getTitle())) {
      model.addAttribute("board", updateForm);
      model.addAttribute("s_err_title", "영문/숫자/한글/특수문자(~!@#$%^&*()_=+-)가능, 3~30자");
      return "board/updateForm";
    }
    //1-2) 내용
    pattern = "^[a-zA-Z0-9가-힣\s~!.@#$%^&*()_=+-]{3,500}$";
    if (!Pattern.matches(pattern, updateForm.getUserContent())) {
      model.addAttribute("board", updateForm);
      model.addAttribute("s_err_userContent", "영문/숫자/한글/특수문자(~!@#$%^&*()_=+-)가능, 3~500자");
      return "board/updateForm";
    }

    //정상 처리
    Board board = new Board();
    board.setTitle(updateForm.getTitle());
    board.setUserContent(updateForm.getUserContent());
    int updateRowCnt = boardSVC.updateById(boardId, board);
    redirectAttributes.addAttribute("pid", boardId);

    return "redirect:/boards/{pid}/detail";
  }

  //목록
  @GetMapping   // GET http://localhost:9080/forums
  public String findAll(Model model) {

    List<Board> list = boardSVC.findAll();
    model.addAttribute("list", list);

    return "board/all";
  }

}
