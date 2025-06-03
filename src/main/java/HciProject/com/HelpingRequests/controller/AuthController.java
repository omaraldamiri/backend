package HciProject.com.HelpingRequests.controller;

import HciProject.com.HelpingRequests.dto.LoginRequest;
import HciProject.com.HelpingRequests.dto.RegisterRequest;
import HciProject.com.HelpingRequests.entity.User;
import HciProject.com.HelpingRequests.service.LoginService;
import HciProject.com.HelpingRequests.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
public class AuthController {
    private LoginService loginService;
    private UserService userService;


    public AuthController(LoginService loginService, UserService userService) {
        this.loginService = loginService;
        this.userService = userService;
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request, HttpSession session){
        String s=loginService.loginChecker(request.getUsername(),request.getPassword());
        if(s.equals("Username not found")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Username not found");}
        else
        if(s.equals("Incorrect password")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect Password");
        }
        else {

            User user=userService.getUserByUsername(request.getUsername());
            session.setAttribute("user",user);

            System.out.println("Session ID: " + session.getId());


            return ResponseEntity.ok("Login Successful");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest){
        String s=userService.registerUser(registerRequest);
        if(s.equals("Username is taken!"))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username is taken!");
        else
            return ResponseEntity.ok("Registered Successfully");
    }
    @GetMapping("/profile")
    public ResponseEntity<String> profileInformation(HttpSession httpSession){
        User user=(User) httpSession.getAttribute("user");
        if(user==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Please login first");
        }
        else
            return ResponseEntity.ok("Welcome , " +user.getUsername());
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession httpSession){
        httpSession.invalidate();
        return ResponseEntity.ok("Logged out successfully");
    }





}
