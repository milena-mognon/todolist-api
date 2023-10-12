package br.com.milenamognon.todolist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// @Controller // é mais flexível para retornar mais do que só apenas dados
@RestController //  é usado para rest API
@RequestMapping("/todolist")// responsável por estruturar a rota
public class TodolistController {
  @GetMapping("/")
  public String index() {
    return "Funcionou";
  }
}
