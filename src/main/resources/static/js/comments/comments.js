    import {Pagination} from '/js/common.js'

    //페이징 객체 생성
    const pagination = new Pagination(10, 5); // 한페이지에 보여줄 레코드건수,한페이지에 보여줄 페이지수

    console.log(memberId, originalId, memberEmail, memberNickname);

    const $addBtn = document.querySelector('#addBtn');
    $addBtn.addEventListener('click',evt=>{
        console.log('등록')
        const formData = new FormData(document.querySelector('#commentForm'));
        const comments = {
          boardId : originalId,
          cname : memberEmail,
          userComment : formData.get('userComment')
        }
        add(comments);
    });

    list();

    //목록
    async function list() {
      const reqPage = pagination.currentPage;   //요청 페이지
      const reqCnt = pagination.recordsPerPage; //페이지당 레코드수

      const url = `http://localhost:9080/api/boards/${originalId}?reqPage=${reqPage}&reqCnt=${reqCnt}`;
      const option = {
        method:'GET',
        headers:{
          accept:'application/json'
        }
      };
      try {
        const res = await fetch(url,option);
        if(!res.ok) return new Error('서버응답오류')
        const result = await res.json(); //응답메세지 바디를 읽어 json포맷 문자열=>js객체
        if(result.header.rtcd == '00'){
          console.log(result.body);
          const str = result.body.map(item=>
                                        `<div class='comments'>
                                          <span><input type="hidden" class="commentId" value="${item.commentId}"/></span>
                                          <span class="cname">${item.cname}</span>
                                          <span><input type="text" class="userComment" value="${item.userComment}" readonly/></span>
                                          <span class="userStatus">
                                            <button class='modifyBtn' type='button'>수정</button>
                                            <button class='deleteBtn' type='button'>삭제</button>
                                          </span>
                                        </div>`).join('');
        replyList.innerHTML = str;

        //총건수는 초기 1회만
        pagination.setTotalRecords(result.totalCnt);
        pagination.displayPagination(list);
//        const userStatusSpans = document.querySelectorAll('.userStatus');
//        userStatusSpans.forEach(span => {
//            span.style.display = 'none';
//        });
        }else{
          new Error('목록 실패!');
        }
      }catch(err){
        console.error(err.message);
      }
    }

  replyList.addEventListener('click', function(evt) {
    const closestDiv = evt.target.closest('div');
    const commentIdSpan = closestDiv.querySelector('.commentId'); // commentId가 저장된 span 요소
    console.log(commentIdSpan.value);
    const cnameSpan = closestDiv.querySelector('.cname'); // cname가 저장된 span 요소
    const commentIdInput = closestDiv.querySelector('.userComment');

    const commentId = commentIdSpan.value;
    const cname = cnameSpan.textContent;

    const comments = {
          userComment : commentIdInput.value
    }
    console.log(evt.target.className);
    //삭제
    if(cname==memberEmail){
        console.log('사용자일치');
        commentIdInput.removeAttribute('readonly');

      if(evt.target.className == 'deleteBtn') {
        console.log('실행');
        console.log(closestDiv);
        console.log(commentIdSpan);
        deleteById(commentId);
        //수정
      } else if(evt.target.className == 'modifyBtn') {
          console.log('실행');
          update(commentId, comments);

      }
    } else{
        console.log('사용자불일치');
    }
  });


  //등록
  async function add(comments) {
    const url = `http://localhost:9080/api/boards`;
    const payload = comments;
    const option = {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        accept: 'application/json',
      },
      body: JSON.stringify(payload),   // js객체=>json포맷 문자열
    };
    try {
      const res = await fetch(url, option);
      if (!res.ok) return new Error('서버응답오류');
      const result = await res.json(); //응답메세지 바디를 읽어 json포맷 문자열=>js객체
      if (result.header.rtcd == '00') {
        console.log(result.body);
        list(originalId);
      } else {
        new Error('등록 실패!');
      }
    } catch (err) {
      console.error(err.message);
    }
  }


  //수정
  async function update(pid,comments) {
    const url = `http://localhost:9080/api/boards/${pid}`;
    const payload = comments
    const option = {
      method: 'PATCH',
      headers: {
        'Content-Type': 'application/json',  // 요청메세지 바디의 데이터포맷 타입
        accept: 'application/json',          // 응답메세지 바다의 데이터포맷 타입
      },
      body: JSON.stringify(payload), // js객체=>json포맷 문자열
    };
    try {
      const res = await fetch(url, option);
      if (!res.ok) return new Error('서버응답오류');
      const result = await res.json(); //응답메세지 바디를 읽어 json포맷 문자열=>js객체
      if (result.header.rtcd == '00') {
        console.log(result.body);
        list(originalId);
      } else {
        new Error('수정 실패!');
      }
    } catch (err) {
      console.error(err.message);
    }
  }

  //삭제
  async function deleteById(pid) {
    const url = `http://localhost:9080/api/boards/${pid}`;
    const option = {
      method: 'DELETE',
      headers: {
        accept: 'application/json',
      },
    };
    try {
      const res = await fetch(url, option);
      if (!res.ok) return new Error('서버응답오류');
      const result = await res.json(); //응답메세지 바디를 읽어 json포맷 문자열=>js객체
      if (result.header.rtcd == '00') {
        console.log(result.body);
        list(originalId);
      } else {
        new Error('삭제 실패!');
      }
    } catch (err) {
      console.error(err.message);
    }
  }