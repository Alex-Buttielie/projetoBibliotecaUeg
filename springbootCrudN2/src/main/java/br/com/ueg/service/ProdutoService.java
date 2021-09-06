package br.com.ueg.service;

import br.com.ueg.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface ProdutoService extends JpaRepository<Produto, Long>{

}
