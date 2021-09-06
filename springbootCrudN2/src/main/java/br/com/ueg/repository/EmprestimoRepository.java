package br.com.ueg.repository;

import br.com.ueg.model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long>{

}
