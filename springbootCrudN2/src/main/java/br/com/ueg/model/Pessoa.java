package br.com.ueg.model;


import javax.persistence.*;

@Entity
@Table(name = "pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codPessoa;
    private String nomePessoa;

    public Long getCodPessoa() {
        return codPessoa;
    }

    public void setCodPessoa(Long codPessoa) {
        this.codPessoa = codPessoa;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }
}
