const $listBtn = document.getElementById('listBtn');        //목록버튼
const $deleteBtn = document.getElementById('deleteBtn');    //삭제버튼
const $modifyBtn = document.getElementById('modifyBtn');    //수정버튼

const pid = document.getElementById('boardId').value;

//수정
$modifyBtn.addEventListener('click', e=>{
  location.href=`/boards/${pid}/edit`;       // GET http://서버주소 or 서버도메인/forums/상품번호/edit
});

//삭제
$deleteBtn.addEventListener('click',e=>{
// if(!window.confirm('삭제하시겠습니까')) return;
  $delModal.showModal();
});

//목록
$listBtn.addEventListener('click',e=>{
  location.href = '/boards';      // GET http://서버주소 or 서버도메인/forums
}, false);

/*모달창 --------------------------------------------------------------------*/
const $delModal = document.getElementById('delModal');      //삭제 모달창
const $cancelBtn = document.getElementById('cancelBtn');    //취소버튼 모달창
const $delItemBtn = document.getElementById('delItemBtn');  //삭제버튼 모달창

$delModal.addEventListener('click' , evt=> {
  if($delModal.returnValue == 'ok') {
    location.href = `/boards/${pid}/del`;  // GET http://서버주소 or 서버도메인/forums/상품번호/del
  }
});

//취소버튼 모달창
cancelBtn.addEventListener('click', evt=>{
  $delModal.close('cancel');
});

//삭제버튼 모달창
$delItemBtn.addEventListener('click', evt=>{
  $delModal.close('ok');
});
/*-------------------------------------------------------------------------*/