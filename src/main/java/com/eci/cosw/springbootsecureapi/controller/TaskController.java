import com.eci.cosw.springbootsecureapi.model.Task;
import com.eci.cosw.springbootsecureapi.model.User;
import com.eci.cosw.springbootsecureapi.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping( "/api/tasks" )
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<?> getAllTasks() {
        List<Task> tasks = taskService.getAll();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable String id) {
        Task task = taskService.getById(Long.parseLong(id));
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @GetMapping(value = "/user={userId}")
    public ResponseEntity<?> getByUserId(@PathVariable String userId) {
        List<Task> tasks = taskService.getByUserId(Long.parseLong(userId));
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PutMapping(value = "/assign/{taskId}")
    public ResponseEntity<?> assignTaskToUser(@PathVariable String taskId, @RequestBody User user) {
        Task updatedTask = taskService.assignTaskToUser(Long.parseLong(taskId), user);
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody Task task) {
        task.setTaskId((long)task.hashCode());
        Task createdTask = taskService.create(task);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteTaskById(@PathVariable String id) {
        taskService.remove(Long.parseLong(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateTask(@RequestBody Task task) {
        Task updatedTask = taskService.update(task);
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }
}