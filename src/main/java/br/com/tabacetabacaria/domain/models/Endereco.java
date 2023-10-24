package br.com.tabacetabacaria.domain.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Entity
@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {

    private static final String CAMPO_NAO_INFORMADO = "Campo não informado";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = CAMPO_NAO_INFORMADO)
    private String cep;

    @NotBlank(message = CAMPO_NAO_INFORMADO)
    @Length(min = 2, max = 70, message = "Nome deve ter entre 2 a 100 caracteres")
    private String rua;

    @NotBlank(message = CAMPO_NAO_INFORMADO)
    @Length(min = 2, max = 50, message = "Nome deve ter entre 2 a 50 caracteres")
    private String bairro;

    @NotBlank(message = CAMPO_NAO_INFORMADO)
    @Length(min = 2, max = 50, message = "Nome deve ter entre 2 a 50 caracteres")
    private String cidade;

    @NotBlank(message = CAMPO_NAO_INFORMADO)
    @Positive(message = "Número tem que ser positivo")
    private Integer numero;

    @Length(min = 2, max = 100, message = "Nome deve ter entre 2 a 100 caracteres")
    private String complemento;
}
