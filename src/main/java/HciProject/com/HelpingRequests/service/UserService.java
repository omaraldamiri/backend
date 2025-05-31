package HciProject.com.HelpingRequests.service;

import HciProject.com.HelpingRequests.dto.RegisterRequest;
import HciProject.com.HelpingRequests.entity.User;
import HciProject.com.HelpingRequests.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String registerUser(RegisterRequest request){
        if(userRepository.existsByUsername(request.getUsername())){
            return "Username is taken!";
        }

        User user=new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhoneNumber(request.getPhoneNumber());
        userRepository.save(user);
        return "Registered Successfully";

    }
    public User getUserByUsername(String username){
        return userRepository.findByUsernameIgnoreCase(username).get();

    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Bad Request"));
    }

    public List<User> returnUsers(){
        return userRepository.findAll();
    }




}
