package hostwire.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hostwire.com.mode.User;
import hostwire.com.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	   @Autowired
	    private UserService userService;

	    @PostMapping("/signup")
	    public ResponseEntity<?> signup(@RequestBody User user) {
	        return ResponseEntity.ok(userService.register(user));
	    }

	    @PostMapping("/login")
	    public ResponseEntity<?> login(@RequestBody User loginRequest) {
	    	 try {
	    	        String token = userService.login(loginRequest.getUsername(), loginRequest.getPassword());
	    	        return ResponseEntity.ok(token);
	    	    } catch (Exception e) {
	    	        return ResponseEntity.status(401).body(e.getMessage());
	    	    }
	    }
}
