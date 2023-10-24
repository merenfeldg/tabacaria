package br.com.tabacetabacaria.service.impl;

import br.com.tabacetabacaria.domain.converter.EnderecoConverter;
import br.com.tabacetabacaria.domain.dtos.EnderecoDTO;
import br.com.tabacetabacaria.repository.EnderecoRepository;
import br.com.tabacetabacaria.service.exceptions.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private EnderecoRepository repository;
    private EnderecoConverter converter;

    public List<EnderecoDTO> buscarTodosEnderecos(){
        return repository.findAll()
                .stream()
                .map(endereco -> converter.enderecoParaEnderecoDTO(endereco))
                .collect(Collectors.toList());
    }
    public EnderecoDTO buscarEndereco(Long id) {
        return converter.enderecoParaEnderecoDTO(repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException
                        (format("Não existe nenhum Endereco com esse id: %l", id))));
    }
    public EnderecoDTO salvarEndereco(EnderecoDTO dto) {
        try {
            var endereco = repository.save(converter.enderecoDTOParaEndereco(dto));
            return converter.enderecoParaEnderecoDTO(endereco);
        }
        catch(Exception e) {
            throw new RuntimeException("Erro ao adicionar Endereco");
        }
    }
    public void deletarEndereco(Long id) {
        try {
            repository.deleteById(id);
        }
        catch(Exception e) {
            throw new RuntimeException("Erro ao deletar Endereco");
        }
    }
}
