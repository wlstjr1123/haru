-- 승현
-- findByEmailAndPassword 비밀번호와 이메일로 유저 번호, 이름, 이메일, 프로필 사진, 배경화면 가져오기
 select user_no as userNo, user_name as userName, user_email as userEmail, user_photo as userPhoto, user_bg as userBg
   from user  
  where user_email = ''
	and user_password = '';

-- getAllUser
-- 
select project.project_no 
  from project, userproject, user
 where project.project_no = userproject.project_no 
  and userproject.user_no = user.user_no
  and user.user_no = '';

select distinct user.user_no as userNo, 
				user_name as userName, 
				user_email as userEmail,  
				user_photo as userPhoto
from (select project.project_no 
		from project, userproject, user
		where project.project_no = userproject.project_no 
		and userproject.user_no = user.user_no
		and user.user_no = '') as b, userproject, user
where b.project_no = userproject.project_no
and userproject.user_no = user.user_no
and user.user_no != '';

insert
  into user(user_email, user_name, user_password, user_number, user_birth, user_title, user_dept, user_photo, user_bg, user_key)
values ("ghksxk1002@naver.com", "이승현", "1234", null, null, null, null, "img.png", null, null);

select * from user;

