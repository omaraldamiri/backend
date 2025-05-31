package HciProject.com.HelpingRequests.service;

import HciProject.com.HelpingRequests.entity.User;
import HciProject.com.HelpingRequests.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {
    private UserRepository userRepository;


    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    public String loginChecker(String username, String password) {
        Optional<User> userOpt = userRepository.findByUsernameIgnoreCase(username);
        if (userOpt.isEmpty()) {
            return "Username not found";
        }

        User user = userOpt.get();

        if (password.equals(user.getPassword())) {


            return "Login successful";

        } else {
            return "Incorrect password";
        }


    }






}
