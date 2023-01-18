package com.attornatus.gerenciador.controller;

import com.attornatus.gerenciador.dto.PessoaFormDto;
import com.attornatus.gerenciador.dto.PessoaViewDto;
import com.attornatus.gerenciador.exception.NotFoundException;
import com.attornatus.gerenciador.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.text.ParseException;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService service;

    //Inserir pessoas
    @PostMapping
    public ResponseEntity<PessoaViewDto> inserirPessoa(
            @RequestBody PessoaFormDto pessoaFormDto,
            UriComponentsBuilder uriBuilder) throws ParseException {
        PessoaViewDto pessoaViewDto = service.inserirPessoa(pessoaFormDto);
        URI uri = uriBuilder.path("/pessoa/" + pessoaViewDto.getId()).build().toUri();
        return ResponseEntity.created(uri).body(pessoaViewDto);
    }

    //Alterar dados de determinada pessoa
    @PutMapping
    public ResponseEntity<PessoaViewDto> alterarPessoa(@RequestBody PessoaFormDto pessoaFormDto) throws NotFoundException {
        PessoaViewDto pessoaViewDto = service.alterarPessoa(pessoaFormDto);
        return ResponseEntity.ok(pessoaViewDto);
    }

    //Consultar pessoa por id
    @GetMapping("/{id}")
    public void consultarPessoa(@PathVariable String id){

    }

    //Listar todas as pessoas
    @GetMapping
    public void listarTodos(){

    }

}
