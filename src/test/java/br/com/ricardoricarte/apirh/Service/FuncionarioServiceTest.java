package br.com.ricardoricarte.apirh.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.ricardoricarte.apirh.model.Funcionario;
import br.com.ricardoricarte.apirh.repository.FuncionarioRepository;
import br.com.ricardoricarte.apirh.service.FuncionarioService;

import java.util.Optional;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FuncionarioServiceTest {

    @InjectMocks
    private FuncionarioService funcionarioService;

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testBuscarFuncionarioPorIdExistente() {
        Long funcionarioId = 1L;
        Funcionario funcionarioMock = new Funcionario();
        funcionarioMock.setId(funcionarioId);

        when(funcionarioRepository.findById(funcionarioId)).thenReturn(Optional.of(funcionarioMock));

        Optional<Funcionario> resultado = funcionarioService.buscarFuncionarioPorId(funcionarioId);

        assertTrue(resultado.isPresent());
        assertEquals(funcionarioId, resultado.get().getId());
    }

    @Test
    public void testBuscarFuncionarioPorIdNaoExistente() {
        Long funcionarioId = 2L;

        when(funcionarioRepository.findById(funcionarioId)).thenReturn(Optional.empty());

        Optional<Funcionario> resultado = funcionarioService.buscarFuncionarioPorId(funcionarioId);

        assertFalse(resultado.isPresent());
    }
}

