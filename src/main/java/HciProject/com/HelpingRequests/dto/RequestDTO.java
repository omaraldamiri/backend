package HciProject.com.HelpingRequests.dto;

import HciProject.com.HelpingRequests.entity.Request;
import HciProject.com.HelpingRequests.entity.User;

import java.time.LocalDate;
import java.time.LocalTime;

public class RequestDTO {
    private String creatorName;
    private String requestBody;
    private String location;
    private LocalDate requestedDate;
    private LocalTime requestedTime;
    private Long creatorId;
    private User user;
    public RequestDTO() {}

    public RequestDTO(Request request,User user) {
        this.requestBody = request.getRequestBody();
        this.location = request.getLocation();
        this.requestedDate = request.getRequestedDate();
        this.requestedTime = request.getRequestedTime();
        this.creatorName = user.getFirstName() + " " + user.getLastName();
        // this.creatorId=request.getCreatorId();
        this.user=user;
        this.creatorId=user.getId();
    }

    public String getRequestBody() {
        return requestBody;
    }

    public String getLocation() {
        return location;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    

    public LocalDate getRequestedDate() {
        return requestedDate;
    }

    public LocalTime getRequestedTime() {
        return requestedTime;
    }

    public void setRequestedDate(LocalDate requestedDate) {
        this.requestedDate = requestedDate;
    }

    public void setRequestedTime(LocalTime requestedTime) {
        this.requestedTime = requestedTime;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user=user;
    }
}
