--테이블 삭제
drop table board;
--시퀀스 삭제
drop sequence board_board_id_seq;

--게시판테이블 생성
create table board(
    board_id        number(10),     --아이디
    bname           varchar2(30),    --작성자
    title           varchar2(100),   --제목
    user_content    CLOB,           --내용
    cdate           timestamp,      --작성일자
    udate           timestamp       --수정일자
);

--기본키
alter table board add constraint board_board_id_pk primary key(board_id);

--시퀀스생성
create sequence board_board_id_seq;

--default
alter table board modify cdate default systimestamp;  --운영체제 일시를 기본값으로
alter table board modify udate default systimestamp;  --운영체제 일시를 기본값으로

--not null
alter table board modify bname not null;
alter table board modify title not null;
alter table board modify user_content not null;


--생성--
insert into board(board_id,bname,title,user_content)
     values(board_board_id_seq.nextval, '홍길동', '안녕하세요', '안녕하세요 반갑습니다');

insert into board(board_id,bname,title,user_content)
     values(board_board_id_seq.nextval, '홍길순', '안녕하세요', '반갑습니다');

insert into board(board_id,bname,title,user_content)
     values(board_board_id_seq.nextval, '홍길남', '반갑습니다', '반갑습니다');
commit;

select * from board;

--목록
select board_id, bname, title, user_content, cdate, udate
     from board
 order by board_id desc;

--수정
update board
   set title = '안녕하세요',
       user_content = '만나서 반갑습니다.',
       udate = systimestamp
 where board_id = 1;

 --삭제
delete from board
 where board_id = 1;
