package br.com.ueg.implemets;

import br.com.ueg.model.Emprestimo;
import br.com.ueg.model.Livro;
import br.com.ueg.model.Pessoa;
import br.com.ueg.repository.EmprestimoRepository;
import br.com.ueg.service.EmprestimoService;
import br.com.ueg.service.LivroService;
import br.com.ueg.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmprestimoServiceImpl implements EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRep;

    @Autowired
    private LivroService livroService;

    @Autowired
    private PessoaService pessoaService;


    @Autowired
    public EmprestimoServiceImpl(EmprestimoRepository emprestimoRepository) {
        this.emprestimoRep = emprestimoRepository;
    }

    @Override
    public List<Emprestimo> listar() {
        return emprestimoRep.findAll();
    }

    @Override
    public Emprestimo consultar(Long id) {
        Emprestimo emprestimo = emprestimoRep.findById(id).orElse(null);
        return emprestimo;

    }

    @Override
    public Emprestimo salvar(Emprestimo emprestimo) {


        emprestimo.getLivro().setEmprestado(true);
        emprestimo = emprestimoRep.save(emprestimo);

        emprestimo = atualizarEmprestimoNoLivro(emprestimo);
        emprestimo = atualizarEmprestimoNaPessoa(emprestimo);

        return emprestimo;
    }

    private Emprestimo atualizarEmprestimoNaPessoa(Emprestimo emprestimo) {
        emprestimo.getPessoa().setEmprestimo(emprestimo);
        Pessoa pessoa = emprestimo.getPessoa();
        pessoaService.alterar(pessoa);
        return emprestimo;
    }

    private Emprestimo atualizarEmprestimoNoLivro(Emprestimo emprestimo) {

        emprestimo.getLivro().setEmprestimo(emprestimo);
        Livro livro = emprestimo.getLivro();
        livroService.alterar(livro);
        return emprestimo;
    }

    @Override
    public ResponseEntity<Map<String, Emprestimo>> alterar(Emprestimo emprestimo) {

        Emprestimo empresAtualizado = new Emprestimo();
        empresAtualizado = consultar(emprestimo.getCodEmprestimo());

        Long idLivro = emprestimo.getLivro().getCodigoLivro();
        Livro livro = livroService.consultar(idLivro);

        Long idPessoa = emprestimo.getPessoa().getCodPessoa();
        Pessoa pessoa = pessoaService.consultar(idPessoa);

        empresAtualizado.setLivro(livro);
        empresAtualizado.setPessoa(pessoa);
        empresAtualizado.setObservacao(emprestimo.getObservacao());
        empresAtualizado.setIsEmprestimoAtivo(emprestimo.getIsEmprestimoAtivo());
        empresAtualizado.setDataEmprestimo(emprestimo.getDataEmprestimo());

        Map<String, Emprestimo> resposta =  new HashMap<>();
        resposta.put("Emprestimo alterado", emprestimoRep.save(empresAtualizado));

        return  ResponseEntity.ok(resposta);
    }

    @Override
    public ResponseEntity<Map<String, Long>> excluir(Long id) {

        Emprestimo emprestimo = consultar(id);

        Livro livro = emprestimo.getLivro();
        livro.setEmprestado(false);
        livro.setEmprestimo(null);
        livroService.alterar(livro);

        Pessoa pessoa = emprestimo.getPessoa();
        pessoa.setEmprestimo(null);
        pessoaService.alterar(pessoa);
        
        emprestimoRep.delete(emprestimo);
        Map<String, Long> resposta = new HashMap();
        resposta.put("Emprestimo excluído: ", id);



        return ResponseEntity.ok(resposta);

    }
}
