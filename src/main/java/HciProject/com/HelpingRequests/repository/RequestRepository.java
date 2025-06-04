package HciProject.com.HelpingRequests.repository;

import HciProject.com.HelpingRequests.dto.RequestDTO;
import HciProject.com.HelpingRequests.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request,Long> {
    List<Request> findByIsDoneOrderByCreatedAtDesc(Boolean isDone);

    List<Request> findByCreatorIdAndIsDone(Long creatorId,Boolean isDone);
}
