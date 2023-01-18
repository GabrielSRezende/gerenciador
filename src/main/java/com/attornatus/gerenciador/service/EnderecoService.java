package com.attornatus.gerenciador.service;

import com.attornatus.gerenciador.dto.EnderecoFormDto;
import com.attornatus.gerenciador.dto.EnderecoViewDto;
import com.attornatus.gerenciador.exception.NotFoundException;
import com.attornatus.gerenciador.mapper.EnderecoFormMapper;
import com.attornatus.gerenciador.mapper.EnderecoViewMapper;
import com.attornatus.gerenciador.model.Endereco;
import com.attornatus.gerenciador.model.Pessoa;
import com.attornatus.gerenciador.repository.EnderecoRepository;
import com.attornatus.gerenciador.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoFormMapper enderecoFormMapper;

    @Autowired
    private EnderecoViewMapper enderecoViewMapper;

    @Autowired
    private EnderecoRepository repository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public EnderecoViewDto inserirEndereco(EnderecoFormDto enderecoFormDto) throws ParseException {
        //Convertendo enderecoDto para endereco
        Endereco endereco = enderecoFormMapper.map(enderecoFormDto);

        //Salvando endereco
        repository.save(endereco);

        //Convertendo e retornando EnderecoView
        return enderecoViewMapper.map(endereco);
    }

    public Page<EnderecoViewDto> listarTodos(
            Long id,
            @PageableDefault(size = 50, sort = "id", direction = Sort.Direction.ASC) Pageable pageable
    ) throws NotFoundException {
        //Buscando pessoa
        Pessoa pessoa = pessoaRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Nenhum usuário existente com essas informações!")
        );

        //Buscando enderecos de determina pessoa
        Page<Endereco> enderecos = repository.findAllByPessoa(pessoa, pageable);
        return enderecos.map(endereco ->
        {
            try {
                return enderecoViewMapper.map(endereco);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public EnderecoViewDto informarPrincipal(
            EnderecoFormDto enderecoFormDto,
            Pageable pageable) throws NotFoundException, ParseException {

        //Verificando se já existe endereco principal
        Page<EnderecoViewDto> enderecos = listarTodos(enderecoFormDto.getPessoa().getId(), pageable);

        // Condição desejada
        Predicate<EnderecoViewDto> filtro = p -> p.isPrincipal();
        Optional<EnderecoViewDto> enderecoPrincipal = enderecos.stream().filter(filtro).findFirst();
        System.out.println(enderecoPrincipal);

        if(!enderecoPrincipal.isEmpty()){
            throw new NotFoundException("Endereco principal já cadastrado: " + enderecoPrincipal);
        }

        //Buscando endereco pelo id, caso nao encontre, uma exception personalizada sera enviada
        Endereco endereco = repository.findById(enderecoFormDto.getId()).orElseThrow(
                () -> new NotFoundException("Nenhum endereco existente com essas informações!")
        );

        //Alterando informacoes
        endereco.setPrincipal(true);
        System.out.println(endereco.getPrincipal());

        //Salvando endereco
        repository.save(endereco);

        return enderecoViewMapper.map(endereco);
    }
}
