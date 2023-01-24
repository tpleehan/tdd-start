package chap09.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

@SpringBootTest
public class UserRegisterIntTest {
	@Autowired
	private UserRegister register;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test
	void 동일ID가_이미_존재하면_익셉션() {
		// 상황: INSERT 쿼리 실행
		jdbcTemplate.update(
			"insert into user values (?, ?, ?) " +
				"on duplicate key update password = ?, email = ?",
			"testId", "testPw", "testEmail@email.com", "testPw", "testEmail@email.com");

		// 실행, 결과 확인
		assertThrows(DupIdException.class,
			() -> register.register("testId", "strongpw", "email@email.com")
		);
	}

	@Test
	void 존재하지_않으면_저장() {
		// 상황: DELETE 쿼리 실행
		jdbcTemplate.update("delete from user where id = ?", "testID");
		// 실행
		register.register("testId", "strongpw", "email@email.com");
		// 결과 확인: SELECT 쿼리 실행
		SqlRowSet rs = jdbcTemplate.queryForRowSet(
			"select * from user where id = ?", "testId");
		rs.next();
		assertEquals("testEmail@email.com", rs.getString("email"));

	}

}

