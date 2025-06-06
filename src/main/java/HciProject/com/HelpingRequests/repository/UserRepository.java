package HciProject.com.HelpingRequests.repository;

import HciProject.com.HelpingRequests.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsernameIgnoreCase(String username);
    boolean existsByUsername(String username);

}
