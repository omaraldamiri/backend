package HciProject.com.HelpingRequests.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name="requests")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="creator_id")
    private Long creatorId;
    @Column(name="request_body")
    private String requestBody;
    @Column(name="location")
    private String location;
    @Column(name="done")
    private boolean isDone;
    @Column(name="contact_number")
    private String contactNumber;
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
    @Column(name="requested_date")
    private LocalDate requestedDate;
    @Column(name="requested_time")
    private LocalTime requestedTime;

    public Request(){};

    public Request(long id, Long creatorId, String requestBody, String location, boolean isDone, String contactNumber, LocalDateTime createdAt, LocalDate requestedDate, LocalTime requestedTime) {
        this.id = id;
        this.creatorId = creatorId;
        this.requestBody = requestBody;
        this.location = location;
        this.isDone = isDone;
        this.contactNumber = contactNumber;
        this.createdAt = createdAt;
        this.requestedDate = requestedDate;
        this.requestedTime = requestedTime;
    }

    public void setDone(boolean done) {
        isDone = done;
    }



    public long getId() {
        return id;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public String getLocation() {
        return location;
    }

    public boolean isDone() {
        return isDone;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Long getCreatorId() {
        return creatorId;
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

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
