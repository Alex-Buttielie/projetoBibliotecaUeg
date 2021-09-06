package br.com.ueg.controller;

import br.com.ueg.model.Livro;
import br.com.ueg.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("livroController")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @GetMapping("/livros")
    public List<Livro> listar(){
        return livroService.listar();
    }

    @GetMapping("/livro/{id}")
    public Livro consultar(@PathVariable Long id){
        return livroService.consultar(id);
    }

    @PostMapping("/salvar")
    public Livro salvar (@RequestBody Livro livro){
        return livroService.salvar(livro);
    }

    @DeleteMapping("/livro/{id}")
    public ResponseEntity <Map<String, Long>> excluir (@PathVariable Long id){
        return  livroService.excluir(id);
    }

    @PutMapping("/livro/{id}")
    public ResponseEntity <Map<String, Livro>> alterar (@RequestBody Livro livro){
        return livroService.alterar(livro);
    }
}
