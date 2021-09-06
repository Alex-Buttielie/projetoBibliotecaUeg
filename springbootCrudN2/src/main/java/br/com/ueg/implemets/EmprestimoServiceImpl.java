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
        return emprestimoRep.save(emprestimo);
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
        emprestimoRep.delete(emprestimo);
        Map<String, Long> resposta = new HashMap();
        resposta.put("Emprestimo excluído: ", id);
        return ResponseEntity.ok(resposta);

    }
}
