create table tb_member(
  user_serial int primary key,
  user_id varchar2(30),
  user_pw varchar2(50),
  user_name varchar2(20),
  user_email varchar2(50),
  user_date date,
  user_status number(1)
);
create sequence tb_member_sequence
  start with 1
  increment by 1
  nocache
  nocycle;

insert into tb_member(
  user_serial,
  user_id,
  user_pw,
  user_name,
  user_email,
  user_date,
  user_status)
values(
  tb_member_sequence.nextval,
  'user03',
  'user03',
  '유저03',
  'ccc@naver.com',
  sysdate,
  1);
select * from tb_member;
select * from tb_member where user_id = 'user01' and user_pw = 'user01';

-------------------------------------------------------------
create table tb_board(
  board_serial int primary key,
  user_serial int,
  board_title varchar2(100),
  board_contents varchar2(1000),
  board_date date,
  board_up_date date,
  board_status number(1)
);

create sequence tb_board_sequence
  start with 1
  increment by 1
  nocache
  nocycle;
  
insert into tb_board(
  board_serial,
  user_serial,
  board_title,
  board_contents,
  board_date,
  board_up_date,
  board_status)
values(
  tb_board_sequence.nextval,
  2,
  '퓨퓨퓨',
  '뿌왁아앙 - 7번글ㅇㅇㅇㅇ',
  sysdate,
  null,
  1);

select * from tb_board;
select * from tb_board where board_serial = 1 and user_serial = 1 and board_status = 1;
update tb_board set board_status = 1 where board_serial = 1;
update tb_board set
  board_title = '제목 변경',
  board_contents = '내용 변경',
  board_up_date = sysdate
where
  board_serial =1 and user_serial = 1;

rollback;
commit;
-------------------------------------------------------------

create table tb_board_comment(
  comment_serial int primary key,
  board_serial int,
  comment_contents varchar2(500),
  user_serial int,
  comment_date date,
  comment_status number(1)
);
create sequence tb_board_comment_sequence
  start with 1
  increment by 1
  nocache
  nocycle;
insert into tb_board_comment(
  comment_serial,
  board_serial,
  comment_contents,
  user_serial,
  comment_date,
  comment_status)
values(
  tb_board_comment_sequence.nextval,
  6,
  '뿌이이이이이',
  1,
  sysdate,
  1);
select * from tb_board_comment where user_serial = 1 and comment_status = 1 order by comment_serial;
update tb_board_comment set comment_status = 1 where comment_serial = 1 and user_serial = 1;

select
  b.board_serial,
  c.comment_serial,
  c.user_serial,
  c.comment_contents,
  c.comment_date
from
  tb_board b join tb_board_comment c
  on (b.board_serial = c.board_serial)
where b.board_serial = 1;


select * from tb_board_comment;

rollback;
commit;
--------------------------------------------------------
create table tb_board_like(
  board_serial int,
  user_serial int,
  board_like_date date
);

insert into tb_board_like(board_serial, user_serial, board_like_date)
values(4, 3, sysdate) where board_serial <> 4 and user_serial <> 3;

select * from tb_board_like;


create table tb_board_comment_like(
  comment_serial int,
  board_serial int,
  comment_like_date date
);
commit;
