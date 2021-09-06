package br.com.ueg.service;

import br.com.ueg.model.Livro;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;


public interface LivroService{


    List<Livro> listar();
    Livro consultar(Long id);
    Livro salvar(Livro livro);
    ResponseEntity<Map<String, Livro>> alterar(Livro livro);
    ResponseEntity<Map<String, Long>> excluir(Long id);
}
