package br.com.ueg.controller;

import br.com.ueg.exception.ResourceNotFoundException;
import br.com.ueg.model.Emprestimo;
import br.com.ueg.model.Livro;
import br.com.ueg.model.Pessoa;
import br.com.ueg.service.EmprestimoService;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/emprestimoController")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;


    @GetMapping("/emprestimos")
    public List<Emprestimo> listar() { return emprestimoService.listar();
    }

    @GetMapping("/emprestimo/{id}")
    public Emprestimo consultar(@PathVariable Long id){
        return emprestimoService.consultar(id);
    }

    @PostMapping("/salvar")
    public Emprestimo salvar (@RequestBody Emprestimo emprestimo){
        return emprestimoService.salvar(emprestimo);
    }

    @PutMapping("/emprestimo/{id}")
    public ResponseEntity <Map<String, Emprestimo>>  alterar (@RequestBody Emprestimo emprestimo){
        return emprestimoService.alterar(emprestimo);

    }

    @DeleteMapping("emprestimo/{id}")
    public ResponseEntity<Map<String, Long>> excluir (@PathVariable Long id){
       return emprestimoService.excluir(id);
    }

}
