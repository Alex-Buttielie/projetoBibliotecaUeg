package br.com.ueg.service;

import br.com.ueg.model.Emprestimo;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface EmprestimoService{

    List<Emprestimo> listar();
    Emprestimo consultar(Long id);
    Emprestimo salvar(Emprestimo emprestimo);
    ResponseEntity <Map<String, Emprestimo>>  alterar(Emprestimo emprestimo);
    ResponseEntity<Map<String, Long>> excluir(Long id);
}
