package br.com.ricardoricarte.apirh.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.ricardoricarte.apirh.model.Funcionario;
import br.com.ricardoricarte.apirh.service.FuncionarioService;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

  @Autowired
  private FuncionarioService funcionarioService;

  @GetMapping
  public List<Funcionario> funcionarios() {
    return funcionarioService.listarTodosFuncionarios();
  }

  @GetMapping("/{funcionarioId}")
  public ResponseEntity<Funcionario> buscarPorId(@PathVariable Long funcionarioId) {
    Optional<Funcionario> funcionario = funcionarioService.buscarFuncionarioPorId(funcionarioId);
    return funcionario.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Funcionario cadastrar(@RequestBody Funcionario funcionario) {
    return funcionarioService.cadastrarFuncionario(funcionario);
  }

  @PutMapping("{funcionarioId}")
  public ResponseEntity<Funcionario> atualizar(@PathVariable Long funcionarioId, @RequestBody Funcionario funcionario) {
    return funcionarioService.atualizarFuncionario(funcionarioId, funcionario);
  }

  @DeleteMapping("{funcionarioId}")
  public ResponseEntity<Void> deletar(@PathVariable Long funcionarioId) {
    return funcionarioService.deletarFuncionario(funcionarioId);
  }
}
