package hostwire.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import hostwire.com.mode.User;
import hostwire.com.repo.UserRepo;
import hostwire.com.security.JwtTokenUtil;

@Service
public class UserService {
	  @Autowired
	    private UserRepo userRepo;

	    @Autowired
	    private PasswordEncoder passwordEncoder;

	    @Autowired
	    private JwtTokenUtil jwtTokenUtil;

	    public User register(User user) {
	    	String encodedPassword = passwordEncoder.encode(user.getPassword());
	        user.setPassword(encodedPassword);
	        return userRepo.save(user);
	    }

	    public String login(String username, String password) throws Exception{
	    	 User user = userRepo.findByUsername(username)
	    	            .orElseThrow(() -> new Exception("User not found with username: " + username));

	    	    if (passwordEncoder.matches(password, user.getPassword())) {
	    	        return jwtTokenUtil.generateToken(username);
	    	    } else {
	    	        throw new Exception("Invalid credentials");
	    	    }
	    
	    }
}