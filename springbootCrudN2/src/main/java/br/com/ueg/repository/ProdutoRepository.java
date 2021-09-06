package br.com.ueg.repository;

import br.com.ueg.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.*;



@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
