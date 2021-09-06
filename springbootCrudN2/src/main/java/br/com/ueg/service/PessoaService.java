package br.com.ueg.service;

import br.com.ueg.model.Pessoa;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface PessoaService{

    List<Pessoa> listar();
    Pessoa consultar(Long id);
    Pessoa salvar(Pessoa pessoa);
    ResponseEntity<Map<String, Pessoa>> alterar(Pessoa pessoa);
    ResponseEntity<Map<String, Long>> excluir(Long id);
}
