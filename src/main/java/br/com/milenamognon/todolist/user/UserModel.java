package br.com.milenamognon.todolist.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;
// @Getter // cria somente os getter
// @Setter // cria somente os setters

@Data // Cria todos do getters e setters automaticamente
@Entity(name = "tb_users")
public class UserModel {
  
  @Id
  @GeneratedValue(generator = "UUID")
  private UUID id;
  
  // @Getter // coloca o get apenas nesse atributo
  @Column(unique = true)
  private String username;
  
  // @Setter // coloca o setter apenas nesse atributo
  private String name;
  private String password;
  
  @CreationTimestamp
  private LocalDateTime createdAt;
}
