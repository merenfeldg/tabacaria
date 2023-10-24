package br.com.tabacetabacaria.api.controllers;

import br.com.tabacetabacaria.domain.dtos.EnderecoDTO;
import br.com.tabacetabacaria.service.impl.EnderecoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/enderecos")
@RequiredArgsConstructor
public class EnderecoController {

    private static final String ID = "/{id}";

    private EnderecoService service;

    @Operation(summary = "Lista todos os Enderecos", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listagem realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao listar endereço")
    })
    @GetMapping
    public ResponseEntity<List<EnderecoDTO>> listarEnderecos() {
        return ResponseEntity.ok(service.buscarTodosEnderecos());
    }


    @Operation(summary = "Busca enderecos pelo ID", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "ID passado é inválido"),
            @ApiResponse(responseCode = "500", description = "Erro ao buscar endereço")
    })
    @GetMapping(ID)
    public ResponseEntity<EnderecoDTO> buscarEndereco(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarEndereco(id));
    }


    @Operation(summary = "Adicionar enderecos", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Adicionado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao adicionar endereço")
    })
    @PostMapping
    public ResponseEntity<EnderecoDTO> adicionarEndereco(@RequestBody EnderecoDTO dto) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path(ID)
                .buildAndExpand(service.salvarEndereco(dto))
                .toUri();
        return ResponseEntity.created(uri).build();
    }
}
