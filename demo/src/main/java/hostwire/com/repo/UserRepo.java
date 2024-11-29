package hostwire.com.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import hostwire.com.mode.User;

public interface UserRepo extends JpaRepository<User, Long> {
	  Optional<User> findByUsername(String username);
}
