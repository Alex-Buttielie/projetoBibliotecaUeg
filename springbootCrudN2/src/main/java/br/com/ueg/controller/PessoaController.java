package br.com.ueg.controller;

import br.com.ueg.model.Pessoa;
import br.com.ueg.service.PessoaService;
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
import java.util.Map;

@RestController
@RequestMapping("pessoaController")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping("/pessoas")
    public java.util.List<Pessoa> listar(){
        return pessoaService.listar();
    }

    @GetMapping("/pessoa/{id}")
    public Pessoa consultar(@PathVariable Long id){
    return pessoaService.consultar(id);
    }

    @PostMapping("/salvar")
    public Pessoa salvar (@RequestBody Pessoa pessoa){
        return pessoaService.salvar(pessoa);
    }

    @DeleteMapping("/pessoa/{id}")
    public ResponseEntity <Map<String, Long>> excluir (@PathVariable Long id){
      return pessoaService.excluir(id);

    }

    @PutMapping("/pessoa/{id}")
    public ResponseEntity <Map<String, Pessoa>>  alterar (@RequestBody Pessoa pessoa){
        return pessoaService.alterar(pessoa);

    }


}
