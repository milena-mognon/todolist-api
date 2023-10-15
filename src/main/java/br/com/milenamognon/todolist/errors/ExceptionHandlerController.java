package br.com.milenamognon.todolist.errors;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // usada para definir classes globais para tratamento de exceções
public class ExceptionHandlerController {
  @ExceptionHandler(HttpMessageNotReadableException.class) // toda a exceção desse tipo vai passa por aqui
  public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
    return ResponseEntity.status(400).body(e.getMostSpecificCause().getMessage());
  }
}
