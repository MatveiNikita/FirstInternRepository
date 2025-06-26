package com.example.firstinternrepository.repository;

import com.example.firstinternrepository.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends MongoRepository<Task, Long> { ;

    List<Task> findTasksByHandler_Id(String handlerId);
    Optional<Task> getTasksById(String id);
    boolean existsById(String id);
    void deleteById(String id);
}
