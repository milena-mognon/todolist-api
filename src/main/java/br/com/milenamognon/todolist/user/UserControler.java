package br.com.milenamognon.todolist.user;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserControler {
  @Autowired // gerencia o repositório (exemplo: instancia)
  private IUserRepository userRepository;
  
  @PostMapping("/")
  public ResponseEntity create(@RequestBody UserModel userModel) {
    var user = this.userRepository.findByUsername(userModel.getUsername());
    if(user != null) {
      return ResponseEntity.status(400).body("Usuário já existe!");
    }
    var passwordHashed = BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray());
    
    userModel.setPassword(passwordHashed);
    
    var userCreated = this.userRepository.save(userModel);
    return ResponseEntity.status(200).body(userCreated);
  }
  
  @GetMapping("/{id}")
  public ResponseEntity show(@PathVariable UUID id) {
    var user = this.userRepository.findById(id);
    
    if(user == null) {
      return ResponseEntity.status(400).body("Usuário não existe!");
    }
    
    return ResponseEntity.status(200).body(user);
  }
}
