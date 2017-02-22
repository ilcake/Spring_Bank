
   --고객 테이블
   DROP TABLE customer;
   
   CREATE TABLE customer
   (
    custid       varchar2(20)     primary key,
    password     varchar2(20)     not null,
    name         varchar2(30)     not null,
    email        varchar2(30),
    division     varchar2(30),
      -- 개인 : 'personal', 기업 : 'company' (check 제약조건  추가 가능)
    idno         varchar2(20)     unique,
      -- 식별번호 : (개인 : 주민번호 , 기업 : 사업자번호 )
    address      varchar2(100)
    );

    --게시판 테이블
    DROP TABLE reply;    
    DROP TABLE board;
    
    CREATE TABLE board
    (
     boardnum        number              CONSTRAINT board_num_pk PRIMARY KEY,
     custid          varchar2(20)        not null,
     title           varchar2(100)       not null,
     content         varchar2(2000)      not null,
     inputdate       date                default sysdate,
     hits            number              default 0,
     originalfile    varchar2(200),     --첨부파일(원래 이름)
     savedfile       varchar2(200)     --첨부파일(실제 저장된 이름)
     );

    --게시판 시퀀스
    DROP SEQUENCE board_seq;
     
    CREATE SEQUENCE board_seq
    start with 1 increment by 1;
       --[  ] 생략가능

    --리플 테이블
    
    CREATE TABLE reply
    (
    replynum        number          CONSTRAINT reply_num_pk PRIMARY KEY,
    boardnum        number          not null,
    custid          varchar2(20)    not null,
    text            varchar2(200)   not null,
    inputdate       date            default sysdate,
      CONSTRAINT reply_fk FOREIGN KEY(boardnum) 
      REFERENCES board(boardnum) ON DELETE CASCADE
       --제약조건을 아래에서 주는것을 테이블 타입의 제약조건이라고 한다. 
    );

    --리플 시퀀스    
    DROP SEQUENCE reply_seq;
    
    CREATE SEQUENCE reply_seq
    start with 1 increment by 1;
    
    COMMIT;
    
    
select * from
   (
    select rownum rnum, --rownum을 실제 칼럼처럼 활용하기위해서 서브쿼리이용.
    b.* from (
              select * from board
              order by inputdate desc
             ) b --최신글이 위로 올라가게 하기위해서 만든 서브쿼리
    )
where rnum between 11 and 20;

    
