package com.attornatus.gerenciador.mapper;

import com.attornatus.gerenciador.dto.PessoaViewDto;
import com.attornatus.gerenciador.model.Pessoa;
import org.springframework.stereotype.Component;

@Component
public class PessoaViewMapper implements Mapper <Pessoa, PessoaViewMapper>{
    @Override
    public PessoaViewDto map(Pessoa pessoa) {
        return new PessoaViewDto(
                pessoa.getId(),
                pessoa.getNome(),
                pessoa.getDataNascimento()
        );
    }
}
