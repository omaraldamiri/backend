package HciProject.com.HelpingRequests.controller;

import HciProject.com.HelpingRequests.dto.RequestDTO;
import HciProject.com.HelpingRequests.entity.Request;
import HciProject.com.HelpingRequests.entity.User;
import HciProject.com.HelpingRequests.service.RequestService;
import HciProject.com.HelpingRequests.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/apis")
public class RequestController {

    private RequestService requestService;
    private UserService userService;


    public RequestController(RequestService requestService, UserService userService) {
        this.requestService = requestService;
        this.userService = userService;
    }


    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }
    
    @GetMapping("/requests")
    public List<RequestDTO> getRequests(){
        return requestService.returnAvaiableRequests();

    }

    @GetMapping("/users")
    public List<User> returnUsers(){
        return userService.returnUsers();

    }
    @GetMapping("/currentUser")
    public User getCurrentUser(HttpSession session) {

        User user = (User)session.getAttribute("user");
        
        return user;
    }

    @GetMapping("/yourRequests")
    public List<RequestDTO> getUserRequests(HttpSession session) {
        User user = (User) session.getAttribute("user");

        if(user==null) throw new RuntimeException("cannot show requests,log in first");

        else{
            return requestService.getRequestByCreatorId(user.getId());
        }
    }
    
    

    @PostMapping("/create")
    public ResponseEntity<?> createRequest(@RequestBody RequestDTO requestDTO, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (user == null) { //! this was used for debuggin so that it doesn't require a user 
            // user = userService.getUserById((long) 1);

            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("status","error","message","current user unavailable , log out and log in again"));

        }
        requestDTO.setCreatorId(user.getId());
        if (requestService.createRequest(requestDTO) == 0) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("status", "error", "message", "ID Not found"));
        } else {

            return ResponseEntity.ok(Map.of("status", "success", "message", "Request Created!","id",requestDTO.getId()));

        }
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<String> updateBoolean(@PathVariable Long id, HttpSession httpSession){
        // if(requestService.updateStatus(id).equals("Request not found"))
        //     return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Request not found");
    // User user=(User) httpSession.getAttribute("user");
    // if(requestService.getRequestById(id).getCreatorId()!=user.getId())
    //     return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You are not the owner of the request");
    //     requestService.updateStatus(id);
    //     return ResponseEntity.ok("Request Completed");


        User user = (User) httpSession.getAttribute("user");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No User Found");
        }

        Request req = requestService.getRequestById((long)id);
        if(user.getId() != req.getCreatorId()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You are not the owner of the request");
        }
        requestService.updateStatus((long)id);

    return ResponseEntity.ok("success");
    }







}
