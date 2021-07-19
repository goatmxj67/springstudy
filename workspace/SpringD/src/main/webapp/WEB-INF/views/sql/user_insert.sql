-- 관리자 계정 하나 생성
INSERT INTO ADMIN VALUES('admin', '1111');

-- 회원 생성 
-- 테스트 계정은 이메일, 휴대폰 인증 등 사용 가능. (박도성 폰, 이메일)
INSERT INTO MEMBER VALUES(MEMBER_SEQ.NEXTVAL, '테스트', 'apple1', '1111', 'nsop4893@gmail.com', '01047206445','platinum', SYSDATE);

