package com.eci.cosw.springbootsecureapi.persistence.impl;

import com.eci.cosw.springbootsecureapi.model.Task;
import com.eci.cosw.springbootsecureapi.model.User;
import com.eci.cosw.springbootsecureapi.persistence.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class InMemoryTaskRepository implements TaskRepository {

    static Map<Long, Task> taskMap = new ConcurrentHashMap<>();

    public InMemoryTaskRepository() {
        User user = new User();
        user.setId(1); user.setFirstname("User");user.setLastname("Backend"); user.setEmail("userbe@mail.com");
        Task task = new Task();
        task.setTaskId(1L);task.setResponsible(user); task.setDescription("Sample BackendTask"); task.setStatus("Ready"); task.setDueDate("Sun Mar 01 2020 15:26:51 GMT-0500");
        taskMap.put(task.getTaskId(),task);
    }

    @Override
    public List<Task> getAll() {
        Set<Map.Entry<Long, Task>> entrySet = taskMap.entrySet();
        List<Task> tasks = new ArrayList<>();
        for (Map.Entry<Long, Task> entry : entrySet) {
            tasks.add(entry.getValue());
        }
        return tasks;
    }

    @Override
    public Task getById(Long id) {
        return taskMap.get(id);
    }

    @Override
    public List<Task> getByUserId(Long userId) {
        Set<Map.Entry<Long, Task>> entrySet = taskMap.entrySet();
        List<Task> tasks = new ArrayList<>();
        for (Map.Entry<Long, Task> entry : entrySet) {
            Task task = entry.getValue();
            if (task.getResponsible().getId() == userId) {
                tasks.add(task);
            }
        }
        return tasks;
    }

    @Override
    public Task assignTaskToUser(Long taskId, User user) {
        Task task = taskMap.get(taskId);
        if (task != null) {
            task.setResponsible(user);
            taskMap.put(taskId, task);
        }
        return task;
    }

    @Override
    public Task create(Task task) {
        taskMap.put(task.getTaskId(), task);
        return task;
    }

    @Override
    public void remove(Long taskId) {
        taskMap.remove(taskId);
    }

    @Override
    public Task update(Task task) {
        Task taskM = taskMap.get(task.getTaskId());
        if (taskM != null) {
            taskMap.put(task.getTaskId(), task);
        }
        return task;
    }
}