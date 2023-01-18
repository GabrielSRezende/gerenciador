package com.attornatus.gerenciador.service;

import com.attornatus.gerenciador.dto.PessoaFormDto;
import com.attornatus.gerenciador.dto.PessoaViewDto;
import com.attornatus.gerenciador.exception.NotFoundException;
import com.attornatus.gerenciador.mapper.PessoaFormMapper;
import com.attornatus.gerenciador.mapper.PessoaViewMapper;
import com.attornatus.gerenciador.model.Pessoa;
import com.attornatus.gerenciador.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.util.List;

@Service
public class PessoaService {

    @Autowired
    private PessoaFormMapper pessoaFormMapper;

    @Autowired
    private PessoaViewMapper pessoaViewMapper;

    @Autowired
    private PessoaRepository repository;

    public PessoaViewDto inserirPessoa(PessoaFormDto pessoaFormDto) throws ParseException {

        //Convertendo PessoaFormDto para Pessoa
        Pessoa pessoa = pessoaFormMapper.map(pessoaFormDto);

        //Salvando pessoa
        repository.save(pessoa);

        //Convertendo e retornando PessoaViewDto
        return pessoaViewMapper.map(pessoa);
    }

    public PessoaViewDto alterarPessoa(PessoaFormDto pessoaFormDto) throws NotFoundException {
        //Buscando pessoa pelo id, caso nao encontre, uma excption personalizada sera enviada
        Pessoa pessoa = repository.findById(pessoaFormDto.getId()).orElseThrow(
                () -> new NotFoundException("Nenhum usuário existente com essas informações!")
            );
        //Alterando os campos da pessoa com base no formulario
        pessoa.setNome(pessoaFormDto.getNome());
        pessoa.setDataNascimento(pessoaFormDto.getDataNascimento());
        //Salvando pessoa
        repository.save(pessoa);

        //Convertendo e retornando PessoaViewDto
        return pessoaViewMapper.map(pessoa);
    }

    public PessoaViewDto consultaPessoa(Long id) throws NotFoundException {
        //Buscando pessoa pelo id, caso nao encontre, uma excption personalizada sera enviada
        Pessoa pessoa = repository.findById(id).orElseThrow(
                () -> new NotFoundException("Nenhum usuário existente com essas informações!")
        );
        //Convertendo e retornando PessoaViewDto
        return pessoaViewMapper.map(pessoa);
    }

    public Page<PessoaViewDto> listarTodos(Pageable pageable) {
        Page<Pessoa> pessoas = repository.findAll(pageable);
        return pessoas.map(pessoa ->
            pessoaViewMapper.map(pessoa)
        );
    }
}
