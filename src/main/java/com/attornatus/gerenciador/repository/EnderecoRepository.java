package com.attornatus.gerenciador.repository;

import com.attornatus.gerenciador.model.Endereco;
import com.attornatus.gerenciador.model.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public
interface EnderecoRepository extends JpaRepository<Endereco, Long>{
    Page<Endereco> findAllByPessoa(Pessoa pessoa, Pageable pageable);
}
