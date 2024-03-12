--테이블 삭제
drop table comments;
--시퀀스 삭제
drop sequence comments_comment_id_seq;

--테이블 생성
create table comments(
    comment_id      number(10),     --아이디 기본키
    board_id        number(10),     --원글(보드)아이디 왜래키
    cname           varchar2(30),   --작성자
    user_comment    varchar2(300),  --내용
    cdate           timestamp,      --작성일자
    udate           timestamp       --수정일자
);

--기본키
alter table comments add constraint comments_comment_id_pk primary key(comment_id);

--시퀀스생성
create sequence comments_comment_id_seq;

--왜래키 제약조건
alter table comments add constraint comments_board_id_fk foreign key(board_id) references board;

--default
alter table comments modify cdate default systimestamp;  --운영체제 일시를 기본값으로
alter table comments modify udate default systimestamp;  --운영체제 일시를 기본값으로

--not null
alter table comments modify cname not null;
alter table comments modify user_comment not null;


--생성--
insert into comments(comment_id,board_id,cname,user_comment)
     values(comments_comment_id_seq.nextval, 1, '홍길동', '안녕하세요 반갑습니다');

insert into comments(comment_id,board_id,cname,user_comment)
     values(comments_comment_id_seq.nextval, 1, '홍길순', '안녕하세요');

insert into comments(comment_id,board_id,cname,user_comment)
     values(comments_comment_id_seq.nextval, 1, '홍길남', '반갑습니다');
commit;

select * from comments;

--목록(페이징)--
SELECT b.board_id, b.title, c.comment_id, c.cname, c.user_comment, c.cdate, c.udate
from comments c join board b on c.board_id = b.board_id
     where b.board_id = 7
     order by comment_id asc
offset (:reqPage-1) * :recCnt rows fetch first :recCnt rows only;

--수정
update comments
   set user_comment = '만나서 반갑습니다.',
       udate = systimestamp
 where comment_id = 1;
