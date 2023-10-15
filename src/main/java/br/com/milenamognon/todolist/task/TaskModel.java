package br.com.milenamognon.todolist.task;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "tb_tasks")
public class TaskModel {
  @Id
  @GeneratedValue
  private UUID id;
  private String description;
  
  @Column(length = 50)
  private String title;
  private LocalDateTime startAt;
  private LocalDateTime endAt;
  private String priority;
  
  @CreationTimestamp
  private LocalDateTime createdAt;
  
  private UUID idUser;
  
  public void setTitle(String title) throws Exception { // repassa a exception para a camada acima ue será
    // responsável por tratá-la
    if(title.length() > 50) {
      throw new Exception("Titulo deve ter no máximo 50 caracteres.");
    }
    this.title = title;
  }
}
