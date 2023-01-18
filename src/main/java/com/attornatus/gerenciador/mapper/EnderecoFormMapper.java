package com.attornatus.gerenciador.mapper;

import com.attornatus.gerenciador.dto.EnderecoFormDto;
import com.attornatus.gerenciador.model.Endereco;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class EnderecoFormMapper implements Mapper<EnderecoFormDto, Endereco>{
    @Override
    public Endereco map(EnderecoFormDto enderecoFormDto) throws ParseException {
        return new Endereco(
                enderecoFormDto.getId(),
                enderecoFormDto.getLogradouro(),
                enderecoFormDto.getCep(),
                enderecoFormDto.getNumero(),
                enderecoFormDto.getPrincipal(),
                enderecoFormDto.getPessoa()
        );
    }
}
