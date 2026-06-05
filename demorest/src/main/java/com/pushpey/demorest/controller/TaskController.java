package com.pushpey.demorest.controller;

import com.pushpey.demorest.entity.Task;
import com.pushpey.demorest.service.TaskService;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {


    private final TaskService service ;
    public  TaskController(TaskService service){
        this.service = service;
    }

    @PostMapping
    public Task create(@RequestBody Task task){
        return service.create(task);
    }

    @GetMapping
    public List<Task> getAll(){
        return service.getAll();
    }
    @GetMapping("/{id}")
    public Task getByid(@PathVariable Long id){
        return service.getById(id);

    }
    @PutMapping("/{id}")
    public Task update(@PathVariable Long id, @RequestBody Task task ){
        return service.update(id, task);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
    @PatchMapping("/{id}/status")
    public Task updateStatus(
            @PathVariable Long id,
            @RequestParam String value) {
        return service.updateStatus(id, value);
    }
    @GetMapping("/search")
    public List<Task> search(@RequestParam String title){
        return service.search(title);
    }
}
