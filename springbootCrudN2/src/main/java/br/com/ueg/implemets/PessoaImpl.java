package br.com.ueg.implemets;

import br.com.ueg.exception.ResourceNotFoundException;
import br.com.ueg.model.Pessoa;
import br.com.ueg.repository.PessoaRepository;
import br.com.ueg.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PessoaImpl implements PessoaService {


    @Autowired
    private PessoaRepository pessoaRep;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public PessoaImpl (PessoaRepository pessoaRepository){
        this.pessoaRep = pessoaRepository;
    }

    @Override
    public List<Pessoa> buscarPessoasLivresParaEmprestimo() {
        return pessoaRep.findAll(Sort.by(Sort.Direction.ASC));
    }

    private StringBuilder buscarPessoasLivresEmprestimo() {
        StringBuilder sql = new StringBuilder();
         return sql
                .append("SELECT * FROM pessoa ")
                .append("WHERE emprestimo_cod_emprestimo isnull");

    }

    @Override
    public List<Pessoa> listar() {
        return pessoaRep.findAll(Sort.by(Sort.Direction.ASC, "codPessoa"));
    }

    @Override
    public Pessoa consultar(Long id) {
        return pessoaRep.findById(id).orElse(null);
    }

    @Override
    public Pessoa salvar(Pessoa pessoa) {
        return pessoaRep.save(pessoa);
    }

    @Override
    public ResponseEntity<Map<String, Pessoa>> alterar(Pessoa pessoa) {

        Pessoa pessoaAtualizada = new Pessoa();
        pessoaAtualizada =  consultar(pessoa.getCodPessoa());
        pessoaAtualizada.setNomePessoa(pessoa.getNomePessoa());

        Map<String, Pessoa> resposta =  new HashMap<>();
        resposta.put("Pessoa alterada", pessoaRep.save(pessoa));


        return ResponseEntity.ok(resposta);
    }

    @Override
    public ResponseEntity<Map<String, Long>> excluir(Long id) {
        Pessoa pessoa = consultar(id);
        pessoaRep.delete(pessoa);
        Map<String, Long> resposta =  new HashMap<>();
        resposta.put("Pessoa excluída", id);
        return ResponseEntity.ok(resposta);

    }
}
