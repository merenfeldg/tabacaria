package br.com.tabacetabacaria.domain.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class EnderecoDTO {
    private Long id;
    private String cep;
    private String rua;
    private String bairro;
    private String cidade;
    private Integer numero;
    private String complemento;
}
