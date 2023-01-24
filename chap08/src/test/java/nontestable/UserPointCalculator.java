package nontestable;

import java.time.LocalDate;

import subs.NoSubscriptionException;
import subs.Product;
import subs.ProductDao;
import subs.Subscription;
import subs.SubscriptionDao;
import subs.User;
import testable.PointRule;

public class UserPointCalculator {

	private PointRule pointRule = new PointRule(); // 기본 구현을 사용
	private SubscriptionDao subscriptionDao;
	private ProductDao productDao;

	public UserPointCalculator(SubscriptionDao subscriptionDao, ProductDao productDao) {
		this.subscriptionDao = subscriptionDao;
		this.productDao = productDao;
	}

	// 별도로 분리한 계산 기능을 주입할 수 있는 세터 추가
	// 테스트 코드에서 대역으로 계산 기능을 대체할 수 있게한다.
	public void setPointRule(PointRule pointRule) {
		this.pointRule = pointRule;
	}

	public int calculatePoint(User u) {
		Subscription s = subscriptionDao.selectByUser(u.getId());

		if (s == null) {
			throw new NoSubscriptionException();
		}

		Product p = productDao.selectById(s.getProductId());
		LocalDate now = LocalDate.now();

		return new PointRule().calculate(s, p, now);
	}
}
