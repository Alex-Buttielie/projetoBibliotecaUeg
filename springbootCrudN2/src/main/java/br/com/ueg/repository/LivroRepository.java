package br.com.ueg.repository;

import br.com.ueg.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LivroRepository extends JpaRepository<Livro, Long>{

}
