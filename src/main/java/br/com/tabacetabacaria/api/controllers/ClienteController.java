package br.com.tabacetabacaria.api.controllers;


import br.com.tabacetabacaria.domain.dtos.ClienteDTO;
import br.com.tabacetabacaria.service.impl.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clientes")
@Tag(name = "Abaeté Tabacaria")
public class ClienteController {

    private static final String EMAIL = "/{email}";
    private static final String EMAIL_INVALIDO = "Email passado é inválido";

    private ClienteService service;

    @Operation(summary = "Lista todos os clientes", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listagem realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao listar Clientes")
    })
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listarClientes(){
        return ResponseEntity.ok(service.buscarTodosClientes());
    }


    @Operation(summary = "Busca cliente pelo email", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = EMAIL_INVALIDO),
            @ApiResponse(responseCode = "500", description = "Erro ao buscar cliente")
    })
    @GetMapping(EMAIL)
    public ResponseEntity<ClienteDTO> buscarClientePorEmail(@PathVariable String nome){
        return ResponseEntity.ok(service.buscarClientePorEmail(nome));
    }


    @Operation(summary = "Adicionar cliente", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente adicionado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao adicionar cliente")
    })
    @PostMapping
    public ResponseEntity<ClienteDTO> adicionarCliente(@RequestBody ClienteDTO dto) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path(EMAIL)
                .buildAndExpand(service.salvarCliente(dto).getNome())
                .toUri();
        return ResponseEntity.created(uri).build();
    }


    @Operation(summary = "Atualizar cliente", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = EMAIL_INVALIDO),
            @ApiResponse(responseCode = "500", description = "Erro ao atualizar cliente")
    })
    @PutMapping(EMAIL)
    public ResponseEntity<ClienteDTO> atualizarCliente
            (@PathVariable String nome, @RequestBody ClienteDTO dto) {

        return ResponseEntity.ok(service.atualizarCliente(EMAIL, dto));
    }


    @Operation(summary = "Deletar Cliente pelo email", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente deletado com sucesso"),
            @ApiResponse(responseCode = "400", description = EMAIL_INVALIDO),
            @ApiResponse(responseCode = "500", description = "Erro ao deletar cliente")
    })
    @DeleteMapping(EMAIL)
    public ResponseEntity<ClienteDTO> deletarCliente(@PathVariable String email) {
        service.deletarCliente(email);
        return ResponseEntity.noContent().build();
    }
}
