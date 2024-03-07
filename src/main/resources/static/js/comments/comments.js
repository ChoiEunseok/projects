    console.log(memberId, originalId, memberEmail, memberNickname);

    const $addBtn = document.querySelector('#addBtn');
    $addBtn.addEventListener('click',evt=>{
        console.log('등록')
        const formData = new FormData(document.querySelector('#commentForm'));
        const comments = {
          boardId : originalId,
          cname : memberNickname,
          userComment : formData.get('userComment')
        }
        add(comments);

    });
    list(originalId);
//    목록
    async function list(pid) {
      const url = `http://localhost:9080/api/boards/${pid}`;
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
                                        `<div>
                                          <span>${item.boardId}</span>
                                          <span>${item.cname}</span>
                                          <span>${item.userComment}</span>
                                        </div>`).join('');

         replyList.innerHTML = str;
        }else{
          new Error('목록 실패!');
        }
      }catch(err){
        console.error(err.message);
      }
    }

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