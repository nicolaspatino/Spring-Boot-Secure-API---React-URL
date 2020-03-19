package com.eci.cosw.springbootsecureapi.service;

import com.eci.cosw.springbootsecureapi.model.Task;
import com.eci.cosw.springbootsecureapi.model.User;
import com.eci.cosw.springbootsecureapi.persistence.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<Task> getAll() {
        return taskRepository.getAll();
    }

    @Override
    public Task getById(Long id) {
        return taskRepository.getById(id);
    }

    @Override
    public List<Task> getByUserId(Long userId) {
        return taskRepository.getByUserId(userId);
    }

    @Override
    public Task assignTaskToUser(Long taskId, User user) {
        return taskRepository.assignTaskToUser(taskId, user);
    }

    @Override
    public Task create(Task task) {
        return taskRepository.create(task);
    }

    @Override
    public void remove(Long taskId) {
        taskRepository.remove(taskId);
    }

    @Override
    public Task update(Task task) {
        return taskRepository.update(task);
    }
}