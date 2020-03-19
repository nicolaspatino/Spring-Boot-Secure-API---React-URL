package com.eci.cosw.springbootsecureapi.persistence;

import com.eci.cosw.springbootsecureapi.model.Task;
import com.eci.cosw.springbootsecureapi.model.User;

import java.util.List;

public interface TaskRepository {

    List<Task> getAll();

    Task getById(Long id);

    List<Task> getByUserId(Long userId);

    Task assignTaskToUser(Long taskId, User user);

    Task create(Task task);

    void remove(Long taskId);

    Task update(Task task);

}