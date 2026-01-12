-- 진석

insert into task values(null, '2021-12-01', '2021-12-31', "라벨", 'do', '코딩', 1, now(), 1);
insert into task values(null, '2021-12-01', '2021-12-31', "라벨", 'do', '코딩', 2, now(), 1);
insert into task values(null, '2021-12-01', '2021-12-31', "라벨", 'do', '코딩', 3, now(), 1);
-- 작성자 필요

insert into tasklist values(null, 'doing', 1, 1, 'T');
insert into tasklist values(null, 'done', 2, 1, 'T');

insert into checklist values(null, '로그인', 'do', 1);
insert into checklist values(null, '회원가입', 'do', 1);
insert into checklist values(null, '비밀번호 변경', 'do', 1);
insert into checklist values(null, '구글 로그인', 'do', 1);

desc task;
