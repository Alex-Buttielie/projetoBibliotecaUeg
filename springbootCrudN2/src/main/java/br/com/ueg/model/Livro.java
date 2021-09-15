package br.com.ueg.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "livro")
@JsonIdentityInfo(scope = Livro.class,
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "codigoLivro")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  codigoLivro;
    private String  nomeLivro;
    private Date dataPublicacao;

    @OneToOne
    @JoinColumn(nullable = true, unique = false)
    private Emprestimo emprestimo;

    private Boolean isEmprestado;

    public Long getCodigoLivro() {
        return codigoLivro;
    }

    public void setCodigoLivro(Long codigoLivro) {
        this.codigoLivro = codigoLivro;
    }

    public String getNomeLivro() {
        return nomeLivro;
    }

    public void setNomeLivro(String nomeLivro) {
        this.nomeLivro = nomeLivro;
    }

    public Date getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(Date dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestimo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }

    public Boolean getEmprestado() {
        return isEmprestado;
    }

    public void setEmprestado(Boolean emprestado) {
        isEmprestado = emprestado;
    }
}