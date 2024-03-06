drop table member;
--회원테이블
create table member(
    member_id   number(10),     -- 내부관리 아이디
    email       varchar2(50),   -- 로그인 아이디
    passwd      varchar2(12),   -- 로그인 비밀번호
    nickname    varchar2(30),   -- 별칭
    gubun       varchar2(11),   -- default 'M0101', --회원구분(일반,구수,관리자1,관리자2)..
    pic         blob,           -- 사진
    cdate       timestamp,      --생성일, 가입일
    udate       timestamp       --수정일
);

--기본키 생성
alter table member add Constraint member_member_id_pk primary key(member_id);

--제약조건
alter table member modify email constraint member_email_uk unique;

--시퀀스
drop sequence member_member_id_seq;
create sequence member_member_id_seq;

--default
alter table member modify gubun default 'M0101';       --회원구분
alter table member modify cdate default systimestamp;  --운영체제 일시를 기본값으로
alter table member modify udate default systimestamp;  --운영체제 일시를 기본값으로

--샘플데이터
insert into member(member_id, email, passwd, nickname)
    values(member_member_id_seq.nextval, 'user1@kh.com', 'user1', '사용자1');
insert into member(member_id, email, passwd, nickname)
    values(member_member_id_seq.nextval, 'user2@kh.com', 'user2', '사용자2');
commit;

select * from member;