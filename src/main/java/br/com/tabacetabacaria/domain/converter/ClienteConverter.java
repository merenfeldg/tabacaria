package br.com.tabacetabacaria.domain.converter;

import br.com.tabacetabacaria.domain.dtos.ClienteDTO;
import br.com.tabacetabacaria.domain.models.Cliente;
import org.springframework.stereotype.Component;


@Component
public class ClienteConverter {

    public ClienteDTO clienteParaClienteDTO(Cliente cliente) {
        return ClienteDTO.builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .sobrenome(cliente.getSobrenome())
                .email(cliente.getEmail())
                .dataNascimento(cliente.getDataNascimento())
                .build();
    }

    public Cliente clienteDTOParaCliente(ClienteDTO clienteDTO) {
        return Cliente.builder()
                .id(clienteDTO.getId())
                .nome(clienteDTO.getNome())
                .sobrenome(clienteDTO.getSobrenome())
                .email(clienteDTO.getEmail())
                .dataNascimento(clienteDTO.getDataNascimento())
                .build();
    }
    public Cliente paraClienteAtualizar(Cliente cliente, ClienteDTO dto, Long id) {
        return Cliente.builder()
                .id(id)
                .nome(dto.getNome() != null ? dto.getNome() : cliente.getNome())
                .sobrenome(dto.getSobrenome() != null ? dto.getSobrenome() : cliente.getSobrenome())
                .email(dto.getEmail() != null ? dto.getEmail() : cliente.getEmail())
                .dataNascimento(cliente.getDataNascimento())
                .build();
    }
}
