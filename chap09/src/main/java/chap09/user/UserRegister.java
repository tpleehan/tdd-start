package chap09.user;

import javax.transaction.Transactional;

public class UserRegister {

	private WeakPasswordChecker passwordChecker;
	private UserRepository userRepository;
	private EmailNotifier emailNotifier;

	@Transactional
	public void register(String id, String pw, String email) {
		if (passwordChecker.checkPasswordWeak(pw)) {
			throw new WeakPasswordException();
		}
		User user = userRepository.findById(id);
		if (user != null) {
			throw new DupIdException();
		}
		userRepository.save(new User(id, pw, email));

		emailNotifier.sendRegisterEmail(email);
	}
}
