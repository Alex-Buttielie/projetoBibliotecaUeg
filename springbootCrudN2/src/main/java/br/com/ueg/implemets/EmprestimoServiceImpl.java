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

        this.atualizarEmprestimoNoLivro(emprestimo);
        this.atualizarEmprestimoNaPessoa(emprestimo);

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

        Emprestimo empresAtualizado = consultar(emprestimo.getCodEmprestimo());
        Livro livro = livroService.consultar(emprestimo.getLivro().getCodigoLivro());
        Pessoa pessoa = pessoaService.consultar(emprestimo.getPessoa().getCodPessoa());
        this.atualizaDadosEmprestimo(livro, pessoa, emprestimo, empresAtualizado);

        Map<String, Emprestimo> resposta =  new HashMap<>();
        resposta.put("Emprestimo alterado", emprestimoRep.save(empresAtualizado));

        return  ResponseEntity.ok(resposta);
    }


    private void atualizaDadosEmprestimo(Livro livro,
                                         Pessoa pessoa,
                                         Emprestimo emprestimo,
                                         Emprestimo empresAtualizado) {

        empresAtualizado.setLivro(livro);
        empresAtualizado.setPessoa(pessoa);
        empresAtualizado.setObservacao(emprestimo.getObservacao());
        empresAtualizado.setIsEmprestimoAtivo(emprestimo.getIsEmprestimoAtivo());
        empresAtualizado.setDataEmprestimo(emprestimo.getDataEmprestimo());
    }

    @Override
    public ResponseEntity<Map<String, Long>> excluir(Long id) {

        Emprestimo emprestimo = consultar(id);

        Livro livro = emprestimo.getLivro();
        this.desvinculaLivroEmprestimo(livro);

        Pessoa pessoa = emprestimo.getPessoa();
        this.desvinculaPessoaEmprestimo(pessoa);

        emprestimoRep.delete(emprestimo);
        Map<String, Long> resposta = new HashMap();
        resposta.put("Emprestimo excluído: ", id);

        return ResponseEntity.ok(resposta);
    }

    private void desvinculaPessoaEmprestimo(Pessoa pessoa) {
        pessoa.setEmprestimo(null);
        pessoaService.alterar(pessoa);
    }

    private void desvinculaLivroEmprestimo(Livro livro) {
        livro.setEmprestado(false);
        livro.setEmprestimo(null);
        livroService.alterar(livro);
    }
}
