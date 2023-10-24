package br.com.tabacetabacaria.service.impl;

import br.com.tabacetabacaria.domain.converter.ClienteConverter;
import br.com.tabacetabacaria.domain.dtos.ClienteDTO;
import br.com.tabacetabacaria.repository.ClienteRepository;
import br.com.tabacetabacaria.service.exceptions.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private ClienteConverter converter;
    private ClienteRepository repository;

    public List<ClienteDTO> buscarTodosClientes(){
        return repository.findAll()
                .stream()
                .map(cliente -> converter.clienteParaClienteDTO(cliente))
                .collect(Collectors.toList());
    }
    public ClienteDTO buscarClientePorEmail(String email){
        return converter.clienteParaClienteDTO(repository.findByEmail(email)
                .orElseThrow(() -> new ObjectNotFoundException
                        (format("Não existe nenhum usuário com o email: %s", email))));
    }

    public ClienteDTO salvarCliente(ClienteDTO dto) {
        try {
            var cliente = converter.clienteDTOParaCliente(dto);
            return converter.clienteParaClienteDTO(repository.save(cliente));
        }
        catch(Exception e) {
            throw new RuntimeException("Erro ao salvar Cliente");
        }
    }
    public ClienteDTO atualizarCliente(String email, ClienteDTO dto) {
        try{
            var cliente = repository.findByEmail(email)
                    .orElseThrow(() -> new ObjectNotFoundException
                            (format("Não existe nenhum usuário com o email: %s", email)));

            var clienteAtualizado = converter.paraClienteAtualizar(cliente, dto, cliente.getId());
            return salvarCliente(converter.clienteParaClienteDTO(clienteAtualizado));
        }
        catch(Exception e) {
            throw new RuntimeException("Erro ao atualizar Cliente");
        }
    }
    public void deletarCliente(String email){
        try {
            repository.deleteByEmail(email);
        }
        catch(Exception e) {
            throw new RuntimeException("Erro ao remover Cliente");
        }
    }
}
