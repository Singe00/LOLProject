-- member_roles 테이블 삭제 (CASCADE 옵션 추가)
DROP TABLE IF EXISTS user_authority CASCADE;

-- member_roles 테이블 생성
CREATE TABLE user_authority (
                            user_id INT,
                            authority_name VARCHAR(32),
                            FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE
);