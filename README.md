# SpringMemberBoardProject
## MemberDTO
```
	private String m_id;
	private String m_password;
	private String m_name;
	private String m_email;
	private String m_phone;
	private MultipartFile m_profile;
	private String m_profilename;
```
## CommentDTO
```
	private long c_number;
	private long b_number;
	private String c_writer;
	private String c_contents;
	private Timestamp c_date;
```
## BoardDTO
```
	private long b_number;
	private String b_writer;
	private String b_title;
	private String b_contents;
	private Timestamp b_date;
	private int b_hits;
	private MultipartFile b_file;
	private String b_filename; 
```
## PageDTO
```
	private int page;
	private int maxPage;
	private int startPage;
	private int endPage;
```

## 테이블 정의
```
create table member_table (
    m_id varchar(20),
    m_password varchar(30),
    m_name varchar(20),
    m_email varchar(50),
    m_phone varchar(30),
    m_profilename varchar(100),
    constraint primary key(m_id)
);

create table board_table (
	b_number bigint auto_increment,
    b_writer varchar(20),
    b_title varchar(50),
    b_contents varchar(500),
    b_filename varchar(200),
    b_hits int,
    b_date datetime,
	constraint primary key(b_number),
    constraint foreign key(b_writer) references member_table(m_id) on delete cascade
);

create table comment_table (
	c_number bigint auto_increment,
    c_writer varchar(20),
    c_contents varchar(200),
    c_date datetime,
    b_number bigint,
    constraint primary key(c_number),
    constraint foreign key(b_number) references board_table(b_number) on delete cascade,
	constraint foreign key(c_writer) references member_table(m_id) on delete cascade
);
```
