package autodebit;

import static autodebit.CardValidity.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AutoDebitRegisterTest {

	private AutoDebitRegister register;

	@BeforeEach
	void setUp() {
		CardNumberValidator validator = new CardNumberValidator();
		AutoDebitInfoRepository repository = new JpaAutoDebitInfoRepository();
		register = new AutoDebitRegister(validator, repository);
	}

	@Test
	void validCard() {
		// 업체에서 받은 테스트용 유효한 카드번호 사용
		AutoDebitReq req = new AutoDebitReq("user1", "1234123412341234");
		RegisterResult result = this.register.register(req);
		Assertions.assertEquals(VALID, result.getValidity());
	}

	@Test
	void thieftCard() {
		// 업체에서 받은 도난 테스트용 카드번호 사용
		AutoDebitReq req = new AutoDebitReq("user1", "1234567890123456");
		RegisterResult result = this.register.register(req);
		Assertions.assertEquals(THEFT, result.getValidity());
	}

}
