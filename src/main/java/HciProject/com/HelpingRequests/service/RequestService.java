package HciProject.com.HelpingRequests.service;

import HciProject.com.HelpingRequests.dto.RequestDTO;
import HciProject.com.HelpingRequests.entity.Request;
import HciProject.com.HelpingRequests.entity.User;
import HciProject.com.HelpingRequests.repository.RequestRepository;
import HciProject.com.HelpingRequests.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RequestService {
    private RequestRepository requestRepository;
    private UserRepository userRepository;
    public RequestService(RequestRepository requestRepository, UserRepository userRepository) {
        this.requestRepository = requestRepository;
        this.userRepository= userRepository;
    }

    public Request getRequestById(Long id){
        return requestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request Not Found"));

    }

    public List<RequestDTO> returnAvaiableRequests() {
        List<Request> list = requestRepository.findByIsDoneOrderByCreatedAtDesc(false);
        List<RequestDTO> dto = new ArrayList<>();
        for (Request r : list) {
            userRepository.findById(r.getCreatorId()).ifPresent(user->dto.add(new RequestDTO(r,user)));
        }
        return dto;
    }

    public String createRequest(RequestDTO requestDTO){
            Optional<User> userOptional=userRepository.findById(requestDTO.getCreatorId());

            if(!userOptional.isPresent()) {
                return "Wrong id";
            }
            User user=userOptional.get();
            Request request=new Request();
            request.setRequestBody(requestDTO.getRequestBody());
            request.setLocation(requestDTO.getLocation());
            request.setCreatorId(requestDTO.getCreatorId());
            request.setRequestedDate(requestDTO.getRequestedDate());
            request.setRequestedTime(requestDTO.getRequestedTime());
            request.setContactNumber(user.getPhoneNumber());
            requestRepository.save(request);

            return "Request has been created with the name " + user.getFirstName() + " " + user.getLastName();






    }
    public String updateStatus(Long id){
        Optional<Request> requestOpt=requestRepository.findById(id);
        if(!requestOpt.isPresent()){
            return "Request not found";
        }
            Request request = requestOpt.get();
            request.setDone(true);
            requestRepository.save(request);

        return "Request marked as done!";



    }





}
