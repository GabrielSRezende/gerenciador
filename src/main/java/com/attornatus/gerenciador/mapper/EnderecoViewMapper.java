package com.attornatus.gerenciador.mapper;

import com.attornatus.gerenciador.dto.EnderecoViewDto;
import com.attornatus.gerenciador.model.Endereco;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class EnderecoViewMapper implements Mapper<Endereco, EnderecoViewDto> {
    @Override
    public EnderecoViewDto map(Endereco endereco) throws ParseException {
        return new EnderecoViewDto(
                endereco.getId(),
                endereco.getLogradouro(),
                endereco.getCep(),
                endereco.getNumero(),
                endereco.getPrincipal(),
                endereco.getPessoa()
        );
    }
}
