package br.com.ueg.implemets;

import br.com.ueg.model.Livro;
import br.com.ueg.repository.LivroRepository;
import br.com.ueg.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LivroServiceImpl implements LivroService {

    @Autowired
    private LivroRepository livroRep;

    @Autowired LivroServiceImpl(LivroRepository livroRepository){
        this.livroRep = livroRepository;
    }

    @Override
    public List<Livro> listar() {
        return  livroRep.findAll();
    }

    @Override
    public Livro consultar(Long id) {
        Livro livro = livroRep.findById(id).orElse(null);
        return livro;
    }

    @Override
    public Livro salvar(Livro livro) {
        return livroRep.save(livro);
    }

    @Override
    public ResponseEntity<Map<String, Livro>> alterar(Livro livro) {

        Livro LivroAtualizado = new Livro();

        LivroAtualizado = consultar(livro.getCodigoLivro());

        LivroAtualizado.setNomeLivro(livro.getNomeLivro());
        LivroAtualizado.setEmprestado(livro.getEmprestado());
        LivroAtualizado.setDataPublicacao(livro.getDataPublicacao());
        LivroAtualizado.setEmprestimo(livro.getEmprestimo());

        Map<String, Livro> resposta =  new HashMap<>();
        resposta.put("Livro alterado", livroRep.save(livro));

        return ResponseEntity.ok(resposta);

    }

    @Override
    public ResponseEntity<Map<String, Long>> excluir(Long id) {
        Livro livro = consultar(id);
        livroRep.delete(livro);
        Map<String, Long> resposta =  new HashMap<>();
        resposta.put("Livro excluido", id);

        return ResponseEntity.ok(resposta);
    }
}
