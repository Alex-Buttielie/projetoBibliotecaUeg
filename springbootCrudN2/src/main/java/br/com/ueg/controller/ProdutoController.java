package br.com.ueg.controller;

import br.com.ueg.exception.ResourceNotFoundException;
import br.com.ueg.model.Produto;
import br.com.ueg.repository.ProdutoRepository;
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

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/pcontroller")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRep;

    @GetMapping("/produtos")
    public java.util.List<Produto> listar(){
        return this.produtoRep.findAll();
    }

    @GetMapping("/produto/{id}")
    public ResponseEntity<Produto> consultar(@PathVariable Long id){
        Produto produto = produtoRep.findById(id).orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado: " + id));

        return ResponseEntity.ok(produto);
    }

    @PostMapping("/salvar")
    public Produto salvar (@RequestBody Produto produto){
        return produtoRep.save(produto);
    }

    @DeleteMapping("/produto/{id}")
    public ResponseEntity <Map<String, Boolean>> excluir (@PathVariable Long id){
        Produto produto = produtoRep.findById(id).orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado: " + id));
        produtoRep.delete(produto);

        Map<String, Boolean> resposta =  new HashMap<>();
        resposta.put("Produto excluído", Boolean.TRUE);
        return ResponseEntity.ok(resposta);

    }

    @PutMapping("/produto/{id}")
    public ResponseEntity <Map<String, Produto>>  alterar (@PathVariable Long id, @RequestBody Produto produto){
        Produto prod = produtoRep.findById(id).orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado: " + id));

        prod.setCodigo(produto.getCodigo());
        prod.setNome(produto.getNome());
        prod.setQuantidade(produto.getQuantidade());

        Produto proAtualizado = produtoRep.save(prod);

        Map<String, Produto> resposta =  new HashMap<>();
        resposta.put("Produto alterado", proAtualizado);
        return ResponseEntity.ok(resposta);

    }


}
