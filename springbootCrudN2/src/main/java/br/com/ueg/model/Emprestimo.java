package br.com.ueg.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "emprestimo")
public class Emprestimo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codEmprestimo;
    private Date dataEmprestimo;
    private String observacao;
    private Boolean isEmprestimoAtivo;

    @OneToOne
    @JoinColumn(nullable = false, unique = false)
    private Livro livro;

    @OneToOne
    @JoinColumn(nullable = false, unique = false)
    private Pessoa pessoa;

    public Long getCodEmprestimo() {
        return codEmprestimo;
    }

    public void setCodEmprestimo(Long codEmprestimo) {
        this.codEmprestimo = codEmprestimo;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Boolean getIsEmprestimoAtivo() {
        return isEmprestimoAtivo;
    }

    public void setIsEmprestimoAtivo(Boolean isEmprestimoAtivo) {
        this.isEmprestimoAtivo = isEmprestimoAtivo;
    }


    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

}
