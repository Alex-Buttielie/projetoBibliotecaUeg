package br.com.ueg.repository;

import br.com.ueg.model.Pessoa;
import org.hibernate.mapping.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
