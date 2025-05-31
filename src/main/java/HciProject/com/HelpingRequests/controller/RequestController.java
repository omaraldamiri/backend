package HciProject.com.HelpingRequests.controller;

import HciProject.com.HelpingRequests.dto.RequestDTO;
import HciProject.com.HelpingRequests.entity.Request;
import HciProject.com.HelpingRequests.entity.User;
import HciProject.com.HelpingRequests.service.RequestService;
import HciProject.com.HelpingRequests.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apis")
public class RequestController {

    private RequestService requestService;
    private UserService userService;


    public RequestController(RequestService requestService, UserService userService) {
        this.requestService = requestService;
        this.userService = userService;
    }
    @GetMapping("/requests")
    public List<RequestDTO> getRequests(){
        return requestService.returnAvaiableRequests();

    }

    @GetMapping("/users")
    public List<User> returnUsers(){
        return userService.returnUsers();

    }

    @PostMapping("/create")
    public ResponseEntity<String> createRequest(@RequestBody RequestDTO requestDTO){
        if(requestService.createRequest(requestDTO).equals("Wrong id"))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("ID Not found");
            else
                return ResponseEntity.ok("Request Created!");
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<String> updateBoolean(@PathVariable Long id, HttpSession httpSession){
        if(requestService.updateStatus(id).equals("Request not found"))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Request not found");
//        User user=(User) httpSession.getAttribute("user");
//        if(requestService.getRequestById(id).getCreatorId()!=user.getId())
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You are not the owner of the request");
        requestService.updateStatus(id);
        return ResponseEntity.ok("Request Completed");


    }







}
