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
