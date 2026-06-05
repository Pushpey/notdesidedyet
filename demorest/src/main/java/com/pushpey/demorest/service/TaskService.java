package com.pushpey.demorest.service;

import com.pushpey.demorest.entity.Task;
import com.pushpey.demorest.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repo ;
    public TaskService(TaskRepository repo){
        this.repo = repo;
    }

    public Task create(Task task){
        task.setStatus("PENDING");
        task.setCreatedAt(LocalDateTime.now());
        return repo.save(task);
    }

    public List<Task> getAll(){
        return repo.findAll();
    }
    public Task getById(Long id ){
        return repo.findById(id).orElseThrow();
    }
    public Task update(Long id , Task updated){
        Task task = getById(id);
        task.setTitle(updated.getTitle());
        task.setDescription(updated.getDescription());
        return repo.save(task);
    }

    public void delete(Long id ){
        repo.deleteById(id);
    }
    public Task updateStatus(Long id , String status){
        Task task = getById(id);
        task.setStatus(status);
        return repo.save(task);
    }

    public List<Task> search(String title){
        return repo.findByTitleContainingIgnoreCase(title);
    }
}
