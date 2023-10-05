package br.com.ricardoricarte.apirh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.ricardoricarte.apirh.model.Funcionario;
import br.com.ricardoricarte.apirh.repository.FuncionarioRepository;

@Service
public class FuncionarioService {


    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<Funcionario> listarTodosFuncionarios() {
        return funcionarioRepository.findAll();
    }

    public Optional<Funcionario> buscarFuncionarioPorId(Long funcionarioId) {
        return funcionarioRepository.findById(funcionarioId);
    }

    public Funcionario cadastrarFuncionario(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public ResponseEntity<Funcionario> atualizarFuncionario(Long funcionarioId, Funcionario funcionario) {
        if (!funcionarioRepository.existsById(funcionarioId)) {
            return ResponseEntity.notFound().build();
        }
        funcionario.setId(funcionarioId);
        Funcionario funcionarioAtualizado = funcionarioRepository.save(funcionario);
        return ResponseEntity.ok(funcionarioAtualizado);
    }

    public ResponseEntity<Void> deletarFuncionario(Long funcionarioId) {
        if (!funcionarioRepository.existsById(funcionarioId)) {
            return ResponseEntity.notFound().build();
        }

        funcionarioRepository.deleteById(funcionarioId);
        return ResponseEntity.noContent().build();
    }
}