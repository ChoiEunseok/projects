 import {Pagination} from '/js/common.js'

    //페이징 객체 생성
    const pagination = new Pagination(10, 10); // 한페이지에 보여줄 레코드건수,한페이지에 보여줄 페이지수
    //총 레코드 건수
    const totalCnt = window.totalCnt;
    const cpgs = window.cpgs;
    const cp = window.cp;

    pagination.setCurrentPageGroupStart(cpgs); //페이지 그룹 시작번호
    pagination.setCurrentPage(cp); //현재 페이지
    pagination.setTotalRecords(totalCnt); //총레코드 건수
    pagination.displayPagination(nextPage);

    function nextPage(){
      const reqPage = pagination.currentPage;   //요청 페이지
      const reqCnt = pagination.recordsPerPage; //페이지당 레코드수

      const cpgs = pagination.currentPageGroupStart; //페이지 그룹 시작번호
      const cp = pagination.currentPage;            //현재 페이지

      location.href = `/boards?reqPage=${reqPage}&reqCnt=${reqCnt}&cpgs=${cpgs}&cp=${cp}`;
    }

const $addBtn = document.getElementById('addBtn');
  $addBtn.addEventListener('click', evt=>{
    location.href = '/boards/add';              // GET http://localhost:9080/products/add
  });

const $rows = document.getElementById('rows');
$rows.addEventListener('click',evt=>{
  const $row = evt.target.closest('.row');
  const boardId = $row.dataset.boardId;
  location.href = `/boards/${boardId}/detail`;
});
