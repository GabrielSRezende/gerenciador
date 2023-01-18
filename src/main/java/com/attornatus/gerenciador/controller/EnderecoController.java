package com.attornatus.gerenciador.controller;

import com.attornatus.gerenciador.model.Endereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired

    //Inserir enderecos
    @PostMapping
    public void inserirEndereco(@RequestBody Endereco endereco){

    }

    //Listar endereco(s) da pessoa
    @GetMapping("/{id}")
    public void listarTodos(){

    }

    //Informar endereco principal
    @PutMapping("/principal")
    public void informarPrincipal(@RequestBody Endereco endereco){

    }



}
