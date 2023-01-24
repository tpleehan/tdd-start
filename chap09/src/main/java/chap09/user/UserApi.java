package chap09.user;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import chap09.common.ErrorResponse;

@RestController
public class UserApi {

	@Autowired
	private UserRegister userRegister;

	@PostMapping("/users")
	public ResponseEntity<Object> regiter(@RequestBody UserRegReq req) {
		try {
			userRegister.register(req.getId(), req.getPw(), req.getEmail());
			return ResponseEntity.created(URI.create("/users/" + req.getId())).build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(ErrorResponse.error(e));
		}
	}
}
