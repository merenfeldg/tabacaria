package br.com.tabacetabacaria.domain.converter;

import br.com.tabacetabacaria.domain.dtos.EnderecoDTO;
import br.com.tabacetabacaria.domain.models.Endereco;
import org.springframework.stereotype.Component;

@Component
public class EnderecoConverter {
    public EnderecoDTO enderecoParaEnderecoDTO(Endereco endereco) {
        return EnderecoDTO.builder()
                .id(endereco.getId())
                .cep(endereco.getCep())
                .rua(endereco.getRua())
                .bairro(endereco.getBairro())
                .cidade(endereco.getCidade())
                .numero(endereco.getNumero())
                .complemento(endereco.getComplemento())
                .build();
    }
    public Endereco enderecoDTOParaEndereco(EnderecoDTO dto) {
        return Endereco.builder()
                .id(dto.getId())
                .cep(dto.getCep())
                .rua(dto.getRua())
                .bairro(dto.getBairro())
                .cidade(dto.getCidade())
                .numero(dto.getNumero())
                .complemento(dto.getComplemento())
                .build();
    }

    public Endereco paraEnderecoAtualizar(Endereco endereco, EnderecoDTO dto, Long id) {
        return Endereco.builder()
                .id(id)
                .cep(dto.getCep() != null ? dto.getCep() : endereco.getCep())
                .rua(dto.getRua() != null ? dto.getRua() : endereco.getRua())
                .bairro(dto.getBairro() != null ? dto.getBairro() : endereco.getBairro())
                .cidade(dto.getCidade() != null ? dto.getCidade() : endereco.getCidade())
                .numero(dto.getNumero() != null ? dto.getNumero() : endereco.getNumero())
                .complemento(dto.getComplemento() != null ? dto.getComplemento() : endereco.getComplemento())
                .build();
    }
}
