package com.attornatus.gerenciador.controller;

import com.attornatus.gerenciador.dto.EnderecoFormDto;
import com.attornatus.gerenciador.dto.EnderecoViewDto;
import com.attornatus.gerenciador.exception.NotFoundException;
import com.attornatus.gerenciador.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.text.ParseException;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService service;

    //Inserir enderecos
    @PostMapping
    public ResponseEntity<EnderecoViewDto> inserirEndereco(
            @RequestBody EnderecoFormDto enderecoFormDto,
            UriComponentsBuilder uriBuilder
    ) throws ParseException {
        EnderecoViewDto enderecoViewDto = service.inserirEndereco(enderecoFormDto);
        URI uri = uriBuilder.path("/endereco/" + enderecoViewDto.getId()).build().toUri();
        return ResponseEntity.created(uri).body(enderecoViewDto);
    }

    //Listar endereco(s) da pessoa
    @GetMapping("/{id}")
    public ResponseEntity<Page<EnderecoViewDto>> listarTodos(
            @PathVariable Long id,
            Pageable pageable
        ) throws NotFoundException {
        Page<EnderecoViewDto> enderecoViewDto = service.listarTodos(id, pageable);
        return ResponseEntity.ok().body(enderecoViewDto);
    }

    //Informar endereco principal
    @PutMapping("/principal")
    public ResponseEntity<EnderecoViewDto> informarPrincipal(
            @RequestBody EnderecoFormDto enderecoFormDto,
            @PageableDefault(size = 50, sort = "id", direction = Sort.Direction.ASC) Pageable pageable
    ) throws NotFoundException, ParseException {
        EnderecoViewDto enderecoViewDto = service.informarPrincipal(enderecoFormDto, pageable);
        return ResponseEntity.ok().body(enderecoViewDto);
    }
}
