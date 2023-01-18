package com.attornatus.gerenciador.mapper;

import com.attornatus.gerenciador.dto.PessoaFormDto;
import com.attornatus.gerenciador.model.Pessoa;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class PessoaFormMapper implements Mapper<PessoaFormDto, Pessoa>{
    @Override
    public Pessoa map(PessoaFormDto pessoaFormDto) throws ParseException {

        //Formatando data


        return new Pessoa(
                pessoaFormDto.getId(),
                pessoaFormDto.getNome(),
                pessoaFormDto.getDataNascimento()
        );
    }
}
