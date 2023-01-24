package testable;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import subs.Grade;
import subs.Product;
import subs.Subscription;

public class PointRuleTest {

	@Test
	void 만료전_GOLD등급은_130포인트() {
		PointRule rule = new PointRule();

		Subscription s = new Subscription(
			"user",
			LocalDate.of(2023, 01, 10),
			Grade.GOLD);

		Product p = new Product("pid");
		p.setDefaultPoint(20);

		int point = rule.calculate(s, p, LocalDate.of(2023, 01, 01));

		assertEquals(130, point);
		
	}
}
