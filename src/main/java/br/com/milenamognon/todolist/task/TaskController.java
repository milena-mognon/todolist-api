package br.com.milenamognon.todolist.task;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {
  @Autowired
  private ITaskRepository taskRepository;
  
  @PostMapping("/")
  public ResponseEntity create(@RequestBody TaskModel taskModel, HttpServletRequest request) {
    var idUser = (UUID) request.getAttribute("idUser");
    taskModel.setIdUser(idUser);
    
    var currentDate = LocalDateTime.now();
    
    if(currentDate.isAfter(taskModel.getStartAt()) || currentDate.isAfter(taskModel.getEndAt())) {
      return ResponseEntity.status(400).body("A data de inicio e/ou final deve ser maior que a data atual");
    }
    
    if(taskModel.getEndAt().isBefore(taskModel.getStartAt())) {
      return ResponseEntity.status(400).body("A data de final deve ser maior que a data inicial");
    }
    
    var task = this.taskRepository.save(taskModel);
    
    return ResponseEntity.status(200).body(task);
  }
  
  @GetMapping("/")
  public ResponseEntity list(HttpServletRequest request) {
    var idUser = (UUID) request.getAttribute("idUser");
    
    var tasks = this.taskRepository.findByIdUser(idUser);
    
    return ResponseEntity.status(200).body(tasks);
  }
  
  @PutMapping("/{id}")
  public ResponseEntity update(@RequestBody TaskModel taskModel, @PathVariable UUID id, HttpServletRequest request) {
    var idUser = (UUID) request.getAttribute("idUser");
    taskModel.setIdUser(idUser);
    taskModel.setId(id);
    var task = this.taskRepository.save(taskModel);
    return ResponseEntity.status(200).body(task);
  }
}
