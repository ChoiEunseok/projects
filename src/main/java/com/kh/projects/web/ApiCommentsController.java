package com.kh.projects.web;


import com.kh.projects.domain.comments.svc.CommentsSVC;
import com.kh.projects.domain.entity.Comments;
import com.kh.projects.web.api.ApiResponse;
import com.kh.projects.web.api.ResCode;
import com.kh.projects.web.req.comments.ReqSave;
import com.kh.projects.web.req.comments.ResSave;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController //  @Controller + @ResponseBody
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class ApiCommentsController {

  private final CommentsSVC commentsSVC;


  //등록
  @PostMapping        //  POST http://localhost:9080/api/products
  public ApiResponse<?> add(
          //@RequestBody : 요청메세지 바디의 json 포맷 문자열 => 자바객체로 매핑
          @RequestBody ReqSave reqSave
  ) {
    log.info("reqSave={}", reqSave);

    //1)유효성검증

    //2)상품등록처리
    Comments comments = new Comments();
    BeanUtils.copyProperties(reqSave, comments);
    Long commentId = commentsSVC.save(comments);

    ResSave resSave = new ResSave(reqSave.getCname(), reqSave.getUserComment());

    String rtDetail = "댓글 " + commentId + "번이 등록되었습니다.";
    ApiResponse<ResSave> res = ApiResponse.createApiResponseDetail(
            ResCode.OK.getCode(), ResCode.OK.name(), rtDetail, resSave);

    return res;
  }

  //조회/목록
  @GetMapping("/{pid}")
  public ApiResponse<?> findById(@PathVariable("pid") Long pid) {
    log.info("pid={}", pid);

    List<Comments> list = commentsSVC.findByIdAll(pid);
    ApiResponse<List<Comments>> res = null;

    if (list.size() > 0) {
      res = ApiResponse.createApiResponse(ResCode.OK.getCode(), ResCode.OK.name(), list);
    } else {
      res = ApiResponse.createApiResponseDetail(ResCode.OK.getCode(), ResCode.OK.name(), "등록된 상품이 1건도 없습니다.", list);
    }
    return res;
  }

}
